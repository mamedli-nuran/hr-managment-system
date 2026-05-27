package com.hr.system.contoller;

import com.hr.system.dto.response.PayrollInfoResponse;
import com.hr.system.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;


    @GetMapping("/{employeeId}")
    public ResponseEntity<List<PayrollInfoResponse>> getHistoryByEmployeeId(@PathVariable UUID employeeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(payrollService.getHistoryByEmployeeId(employeeId));
    }

}
