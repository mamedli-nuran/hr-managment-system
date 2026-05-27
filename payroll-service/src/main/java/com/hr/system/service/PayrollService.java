package com.hr.system.service;

import com.hr.system.dto.request.PayrollCreateRequest;
import com.hr.system.dto.response.PayrollInfoResponse;

import java.util.List;
import java.util.UUID;

public interface PayrollService {
    List<PayrollInfoResponse> getHistoryByEmployeeId(UUID employeeId);
    PayrollInfoResponse createPayroll(UUID employeeId, PayrollCreateRequest payrollCreateRequest);
}
