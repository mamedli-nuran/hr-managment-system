package com.hr.system.employeeservice.service.impl;

import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.request.EmployeeUpdateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.exceptions.EmailAlreadyExistsException;
import com.hr.system.employeeservice.exceptions.EmployeeNotFoundException;
import com.hr.system.employeeservice.model.Employee;
import com.hr.system.employeeservice.repository.EmployeeRepository;
import com.hr.system.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.hr.system.employeeservice.exceptions.ErrorMessage.EMPLOYEE_NOT_FOUND;
import static com.hr.system.employeeservice.mapper.EmployeeMapper.EMPLOYEE_MAPPER;
import static com.hr.system.employeeservice.exceptions.ErrorMessage.EMAIL_ALREADY_EXISTS;
import static com.hr.system.employeeservice.mapper.EmployeeUpdateMapper.UPDATE_EMPLOYEE_MAPPER;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    @Override
    public EmployeeInfoResponse addEmployee(EmployeeCreateRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            log.warn("Failed to add employee. Email already exists: {}", request.email());
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS.getValue());
        }

        Employee addedEmployee = employeeRepository.save(EMPLOYEE_MAPPER.toEntity(request));
        log.info("Add a new employee with email:{}", request.email());

        return EMPLOYEE_MAPPER.toResponse(addedEmployee);
    }

    @Override
    public EmployeeInfoResponse getEmployeeById(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            log.warn("Employee execution failed. Employee not found with ID: {}", id);
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND.getValue(), id));
        }

        return EMPLOYEE_MAPPER.toResponse(employee.get());
    }

    @Override
    public EmployeeInfoResponse updateEmployee(UUID id, EmployeeUpdateRequest request) {
        Employee employeeForUpdate = employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND.getValue(), id)));

        UPDATE_EMPLOYEE_MAPPER.updateEntityFromDto(request, employeeForUpdate);

        return EMPLOYEE_MAPPER.toResponse(employeeForUpdate);
    }
}
