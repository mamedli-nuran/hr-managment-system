package com.hr.system.dto.response;

import com.hr.system.model.enums.LeaveStatus;
import com.hr.system.model.enums.LeaveType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record LeaveInfoResponse(
        UUID id,
        UUID employeeId,
        LeaveType leaveType,
        LocalDate startDate,
        LocalDate endDate,
        String reason,
        LeaveStatus status,
        LocalDateTime createdAt
) {
}
