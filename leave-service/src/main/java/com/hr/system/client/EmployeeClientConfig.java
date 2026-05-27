package com.hr.system.client;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class EmployeeClientConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
