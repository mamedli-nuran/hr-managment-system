package com.hr.system.repository;

import com.hr.system.dto.response.PayrollInfoResponse;
import com.hr.system.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, UUID> {
    List<Payroll> findByEmployeeIdOrderByPayDateDesc(UUID id);
}
