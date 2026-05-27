package com.hr.system.repository;

import com.hr.system.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, UUID> {
    List<Leave> findByEmployeeIdOrderByCreatedAtDesc(UUID employeeId);
}
