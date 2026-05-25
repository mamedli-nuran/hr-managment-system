package com.hr.system.employeeservice.mapper;


import com.hr.system.employeeservice.dto.request.EmployeeUpdateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.model.Employee;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeUpdateMapper {
    EmployeeUpdateMapper UPDATE_EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeUpdateMapper.class);

    EmployeeInfoResponse toResponse(Employee employee);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromDto(EmployeeUpdateRequest dto, @MappingTarget Employee entity);
}
