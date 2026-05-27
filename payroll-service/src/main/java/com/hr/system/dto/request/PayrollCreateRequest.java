package com.hr.system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

import static com.hr.system.utill.ApplicationConstants.*;

public record PayrollCreateRequest(

        @NotNull(message = BASE_SALARY_NOT_NULL_MESSAGE)
        @PositiveOrZero(message = BASE_SALARY_POSITIVE_OR_ZERO_MESSAGE)
        BigDecimal baseSalary,

        @PositiveOrZero(message = BONUS_POSITIVE_OR_ZERO_MESSAGE)
        BigDecimal bonus


){
}
