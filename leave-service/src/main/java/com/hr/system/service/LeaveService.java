package com.hr.system.service;

import com.hr.system.dto.request.LeaveCreateRequest;
import com.hr.system.dto.response.LeaveInfoResponse;

import java.util.List;
import java.util.UUID;

public interface LeaveService {
    LeaveInfoResponse createLeaveRequest(UUID employeeId, LeaveCreateRequest request);

    LeaveInfoResponse approveLeave(UUID leaveId);

    LeaveInfoResponse rejectLeave(UUID leaveId);

    List<LeaveInfoResponse> getLeaveHistoryByEmployeeId(UUID employeeId);
}
