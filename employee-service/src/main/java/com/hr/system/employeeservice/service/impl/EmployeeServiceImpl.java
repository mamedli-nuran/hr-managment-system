package com.hr.system.employeeservice.service.impl;

import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.exceptions.EmailAlreadyExistsException;
import com.hr.system.employeeservice.model.Employee;
import com.hr.system.employeeservice.repository.EmployeeRepository;
import com.hr.system.employeeservice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hr.system.employeeservice.mapper.EmployeeMapper.EMPLOYEE_MAPPER;
import static com.hr.system.employeeservice.exceptions.ErrorMessage.EMAIL_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeInfoResponse addEmployee(EmployeeCreateRequest request) {
        if(employeeRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS.getValue());
        }

        Employee addedEmployee = employeeRepository.save(EMPLOYEE_MAPPER.toEntity(request));
        return EMPLOYEE_MAPPER.toResponse(addedEmployee);
    }
}
