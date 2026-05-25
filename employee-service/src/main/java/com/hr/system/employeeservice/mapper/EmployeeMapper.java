package com.hr.system.employeeservice.mapper;


import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.model.Employee;
import org.springframework.stereotype.Component;



public enum EmployeeMapper {
    EMPLOYEE_MAPPER;


    public Employee toEntity(EmployeeCreateRequest employeeCreateRequest) {
        return Employee.builder()
                .firstName(employeeCreateRequest.getFirstName())
                .lastName(employeeCreateRequest.getLastName())
                .email(employeeCreateRequest.getEmail())
                .position(employeeCreateRequest.getPosition())
                .build();
    }

    public EmployeeInfoResponse toResponse(Employee employee) {
        return EmployeeInfoResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .position(employee.getPosition())
                .status(employee.getStatus())
                .hireDate(employee.getHireDate())
                .build();
    }
}
