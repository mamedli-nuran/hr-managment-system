package com.hr.system.employeeservice.dto.request;

import com.hr.system.employeeservice.model.enums.EmployeePosition;
import com.hr.system.employeeservice.utill.ApplicationConstants;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.hr.system.employeeservice.utill.ApplicationConstants.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequest {

    @NotBlank(message = FIRST_NAME_IS_REQUIRED)
    private String firstName;

    @NotBlank(message = LAST_NAME_IS_REQUIRED)
    private String lastName;

    @NotBlank(message = EMAIL_IS_REQUIRED)
    @Email(message = NOT_VALID_EMAIL)
    private String email;

    @NotNull(message = EMPLOYEE_POSITION_IS_REQUIRED)
    private EmployeePosition position;

}
