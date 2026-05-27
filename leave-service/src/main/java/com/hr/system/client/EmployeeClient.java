package com.hr.system.client;

import com.hr.system.dto.response.EmployeeClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "employee-service",
        url = "${service.employee-service.url}",
        path = "/api/employees",
        configuration = EmployeeClientConfig.class
)
public interface EmployeeClient {

    @GetMapping("/{id}")
    EmployeeClientResponse getEmployeeById(@PathVariable UUID id);
}
