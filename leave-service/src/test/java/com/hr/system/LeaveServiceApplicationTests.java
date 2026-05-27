package com.hr.system;

import com.hr.system.client.EmployeeClient;
import com.hr.system.mapper.LeaveMapper;
import com.hr.system.repository.LeaveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration"
})
class LeaveServiceApplicationTests {

    @MockBean
    private LeaveRepository leaveRepository;

    @MockBean
    private LeaveMapper leaveMapper;

    @MockBean
    private EmployeeClient employeeClient;

    @Test
    void contextLoads() {
    }

}
