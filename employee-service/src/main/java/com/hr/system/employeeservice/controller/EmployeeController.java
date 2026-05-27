package com.hr.system.employeeservice.controller;

import com.hr.system.employeeservice.dto.request.EmployeeCreateRequest;
import com.hr.system.employeeservice.dto.request.EmployeeUpdateRequest;
import com.hr.system.employeeservice.dto.response.EmployeeInfoResponse;
import com.hr.system.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeInfoResponse> addEmployee(@RequestBody @Valid EmployeeCreateRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.addEmployee(request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeInfoResponse> getEmployee(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.getEmployeeById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeInfoResponse> updateEmployee(
            @PathVariable UUID id, @RequestBody @Valid EmployeeUpdateRequest request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeService.updateEmployee(id, request));
    }


    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> checkEmployeeExists(@PathVariable UUID id) {
        return employeeService.existsById(id) ?
                ResponseEntity.status(HttpStatus.OK).body(true) : ResponseEntity.status(HttpStatus.OK).body(false);
    }

}
