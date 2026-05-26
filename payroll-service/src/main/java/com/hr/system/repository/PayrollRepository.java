package com.hr.system.repository;

import com.hr.system.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, UUID> {
}
