package com.hr.system.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID employeeId;

    @Column(name = "base_salary")
    private BigDecimal baseSalary;


    private BigDecimal bonus;
    private BigDecimal tax;

    @Column(name = "final_salary")
    private BigDecimal finalSalary;

    @Column(name = "pay_date")
    private LocalDate payDate;
}
