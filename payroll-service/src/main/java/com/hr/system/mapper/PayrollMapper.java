package com.hr.system.mapper;

import com.hr.system.dto.response.PayrollInfoResponse;
import com.hr.system.model.Payroll;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PayrollMapper {

     PayrollInfoResponse toPayrollInfoResponse(Payroll payroll);
}
