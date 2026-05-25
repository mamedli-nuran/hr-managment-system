package com.hr.system.employeeservice.service;

import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.model.Employee;

import java.util.UUID;

public interface EmployeeService {
    EmployeeInfoResponse addEmployee(EmployeeCreateRequest request);
    EmployeeInfoResponse getEmployeeById(UUID id);
}
