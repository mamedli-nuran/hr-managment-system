package com.hr.system.employeeservice.mapper;


import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.model.Employee;



public enum EmployeeMapper {
    EMPLOYEE_MAPPER;



    public Employee toEntity(EmployeeCreateRequest employeeCreateRequest) {
        return Employee.builder()
                .firstName(employeeCreateRequest.firstName())
                .lastName(employeeCreateRequest.lastName())
                .email(employeeCreateRequest.email())
                .position(employeeCreateRequest.position())
                .baseSalary(employeeCreateRequest.baseSalary())
                .build();
    }

    public EmployeeInfoResponse toResponse(Employee employee) {
        return EmployeeInfoResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .position(employee.getPosition())
                .baseSalary(employee.getBaseSalary())
                .status(employee.getStatus())
                .hireDate(employee.getHireDate())
                .build();
    }

}
