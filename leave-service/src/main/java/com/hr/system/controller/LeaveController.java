package com.hr.system.controller;

import com.hr.system.dto.request.LeaveCreateRequest;
import com.hr.system.dto.response.LeaveInfoResponse;
import com.hr.system.service.LeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveController {
    private final LeaveService leaveService;

    @PostMapping("/{employeeId}")
    public ResponseEntity<LeaveInfoResponse> createLeaveRequest(
            @PathVariable UUID employeeId,
            @RequestBody @Valid LeaveCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(leaveService.createLeaveRequest(employeeId, request));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveInfoResponse> approveLeave(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(leaveService.approveLeave(id));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveInfoResponse> rejectLeave(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(leaveService.rejectLeave(id));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveInfoResponse>> getLeaveHistoryByEmployeeId(@PathVariable UUID employeeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(leaveService.getLeaveHistoryByEmployeeId(employeeId));
    }
}
