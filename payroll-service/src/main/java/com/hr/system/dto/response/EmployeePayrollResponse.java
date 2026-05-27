package com.hr.system.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record EmployeePayrollResponse(
        UUID id,
        BigDecimal baseSalary
) {
}
