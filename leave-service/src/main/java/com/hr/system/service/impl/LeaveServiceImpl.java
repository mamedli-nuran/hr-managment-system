package com.hr.system.service.impl;

import com.hr.system.client.EmployeeClient;
import com.hr.system.dto.request.LeaveCreateRequest;
import com.hr.system.dto.response.LeaveInfoResponse;
import com.hr.system.exception.EmployeeNotFoundException;
import com.hr.system.exception.EmployeeServiceUnavailableException;
import com.hr.system.exception.LeaveAlreadyProcessedException;
import com.hr.system.exception.LeaveNotFoundException;
import com.hr.system.mapper.LeaveMapper;
import com.hr.system.model.Leave;
import com.hr.system.model.enums.LeaveStatus;
import com.hr.system.repository.LeaveRepository;
import com.hr.system.service.LeaveService;
import feign.FeignException;
import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.hr.system.exception.ErrorMessage.EMPLOYEE_NOT_FOUND;
import static com.hr.system.exception.ErrorMessage.EMPLOYEE_SERVICE_UNAVAILABLE;
import static com.hr.system.exception.ErrorMessage.LEAVE_ALREADY_PROCESSED;
import static com.hr.system.exception.ErrorMessage.LEAVE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;
    private final LeaveMapper leaveMapper;
    private final EmployeeClient employeeClient;

    @Override
    @Transactional
    public LeaveInfoResponse createLeaveRequest(UUID employeeId, LeaveCreateRequest request) {
        validateEmployeeExists(employeeId);

        Leave leave = leaveMapper.toEntity(request, employeeId);
        Leave savedLeave = leaveRepository.save(leave);

        log.info(
                "Leave request created. leaveId={}, employeeId={}, status={}",
                savedLeave.getId(),
                employeeId,
                savedLeave.getStatus());

        return leaveMapper.toResponse(savedLeave);
    }

    @Override
    @Transactional
    public LeaveInfoResponse approveLeave(UUID leaveId) {
        Leave leave = getLeaveOrThrow(leaveId);
        validatePendingStatus(leave);

        leave.setStatus(LeaveStatus.APPROVED);

        log.info("Leave approved. leaveId={}, employeeId={}", leaveId, leave.getEmployeeId());
        return leaveMapper.toResponse(leave);
    }

    @Override
    @Transactional
    public LeaveInfoResponse rejectLeave(UUID leaveId) {
        Leave leave = getLeaveOrThrow(leaveId);
        validatePendingStatus(leave);

        leave.setStatus(LeaveStatus.REJECTED);

        log.info("Leave rejected. leaveId={}, employeeId={}", leaveId, leave.getEmployeeId());
        return leaveMapper.toResponse(leave);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaveInfoResponse> getLeaveHistoryByEmployeeId(UUID employeeId) {
        validateEmployeeExists(employeeId);

        return leaveRepository.findByEmployeeIdOrderByCreatedAtDesc(employeeId)
                .stream()
                .map(leaveMapper::toResponse)
                .toList();
    }

    private Leave getLeaveOrThrow(UUID leaveId) {
        return leaveRepository.findById(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException(String.format(LEAVE_NOT_FOUND.getMessage(), leaveId)));
    }

    private void validatePendingStatus(Leave leave) {
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new LeaveAlreadyProcessedException(
                    String.format(LEAVE_ALREADY_PROCESSED.getMessage(), leave.getId(), leave.getStatus()));
        }
    }

    private void validateEmployeeExists(UUID employeeId) {
        try {
            employeeClient.getEmployeeById(employeeId);
        } catch (FeignException.NotFound exception) {
            log.warn("Employee not found during leave operation. employeeId={}", employeeId);
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND.getMessage(), employeeId));
        } catch (RetryableException | FeignException.ServiceUnavailable exception) {
            log.error("Employee service unavailable during leave operation. employeeId={}", employeeId, exception);
            throw new EmployeeServiceUnavailableException(EMPLOYEE_SERVICE_UNAVAILABLE.getMessage(), exception);
        } catch (FeignException exception) {
            if (exception.status() >= 500 || exception.status() == -1) {
                log.error("Employee service returned server error. employeeId={}", employeeId, exception);
                throw new EmployeeServiceUnavailableException(EMPLOYEE_SERVICE_UNAVAILABLE.getMessage(), exception);
            }
            throw exception;
        }
    }
}
