package com.hr.system.employeeservice.model;

import com.hr.system.employeeservice.model.enums.EmployeePosition;
import com.hr.system.employeeservice.model.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static com.hr.system.employeeservice.model.enums.EmployeeStatus.ACTIVE;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;

    //todo сделать доп проверку на existsByEmail
    private String email;

    @CreationTimestamp
    private LocalDate hireDate;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = ACTIVE;

    @Enumerated(EnumType.STRING)
    private EmployeePosition position;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;
}
