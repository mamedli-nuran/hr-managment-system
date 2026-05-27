package com.hr.system.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record PayrollInfoResponse (
        UUID id,
        BigDecimal baseSalary,
        BigDecimal bonus,
        BigDecimal tax,
        BigDecimal finalSalary,
        LocalDate payDate
){
}

