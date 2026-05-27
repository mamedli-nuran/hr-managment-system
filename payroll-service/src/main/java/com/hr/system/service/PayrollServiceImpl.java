package com.hr.system.service;

import com.hr.system.client.EmployeeClient;
import com.hr.system.dto.request.PayrollCreateRequest;
import com.hr.system.dto.response.PayrollInfoResponse;
import com.hr.system.exceptions.EmployeeNotFoundException;
import com.hr.system.mapper.PayrollMapper;
import com.hr.system.model.Payroll;
import com.hr.system.repository.PayrollRepository;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.hr.system.exceptions.ErrorMessage.EMPLOYEE_NOT_FOUND;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeClient employeeClient;
    private final PayrollMapper payrollMapper;


    @Override
    public List<PayrollInfoResponse> getHistoryByEmployeeId(UUID employeeId) {

        if(!employeeClient.checkEmployeeExists(employeeId)){
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND.getMessage(), employeeId));
        }


        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(payrollMapper::toPayrollInfoResponse)
                .toList();
    }

    @Override
    public PayrollInfoResponse createPayroll(PayrollCreateRequest payrollCreateRequest) {
        return null;
    }
}