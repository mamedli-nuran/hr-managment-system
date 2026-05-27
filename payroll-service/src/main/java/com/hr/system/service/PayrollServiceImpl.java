package com.hr.system.service;

import com.hr.system.client.EmployeeClient;
import com.hr.system.dto.request.PayrollCreateRequest;
import com.hr.system.dto.response.EmployeePayrollResponse;
import com.hr.system.dto.response.PayrollInfoResponse;
import com.hr.system.exceptions.EmployeeNotFoundException;
import com.hr.system.mapper.PayrollMapper;
import com.hr.system.model.Payroll;
import com.hr.system.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.hr.system.exceptions.ErrorMessage.EMPLOYEE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayrollServiceImpl implements PayrollService {
    private static final BigDecimal DEFAULT_TAX_RATE = new BigDecimal("0.10");
    private static final BigDecimal ZERO_AMOUNT = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    private final PayrollRepository payrollRepository;
    private final EmployeeClient employeeClient;
    private final PayrollMapper payrollMapper;


    @Override
    @Transactional(readOnly = true)
    public List<PayrollInfoResponse> getHistoryByEmployeeId(UUID employeeId) {
        getEmployeeOrThrow(employeeId);

        return payrollRepository.findByEmployeeIdOrderByPayDateDesc(employeeId)
                .stream()
                .map(payrollMapper::toPayrollInfoResponse)
                .toList();
    }

    @Override
    @Transactional
    public PayrollInfoResponse createPayroll(UUID employeeId, PayrollCreateRequest payrollCreateRequest) {
        EmployeePayrollResponse employee = getEmployeeOrThrow(employeeId);

        BigDecimal baseSalary = normalizeAmount(employee.baseSalary());
        BigDecimal bonus = resolveBonus(payrollCreateRequest);
        BigDecimal grossSalary = baseSalary.add(bonus);
        BigDecimal tax = calculateTax(grossSalary);
        BigDecimal finalSalary = calculateFinalSalary(grossSalary, tax);

        Payroll payroll = new Payroll();
        payroll.setEmployeeId(employee.id());
        payroll.setBaseSalary(baseSalary);
        payroll.setBonus(bonus);
        payroll.setTax(tax);
        payroll.setFinalSalary(finalSalary);
        payroll.setPayDate(LocalDate.now());

        Payroll savedPayroll = payrollRepository.save(payroll);
        log.info("Payroll created for employeeId={}, payrollId={}, finalSalary={}", employeeId, savedPayroll.getId(), finalSalary);

        return payrollMapper.toPayrollInfoResponse(savedPayroll);
    }

    private EmployeePayrollResponse getEmployeeOrThrow(UUID employeeId) {
        try {
            return employeeClient.getEmployeeById(employeeId);
        } catch (FeignException.NotFound exception) {
            log.warn("Payroll operation failed. Employee not found with ID: {}", employeeId);
            throw new EmployeeNotFoundException(String.format(EMPLOYEE_NOT_FOUND.getMessage(), employeeId));
        }
    }

    private BigDecimal resolveBonus(PayrollCreateRequest payrollCreateRequest) {
        if (payrollCreateRequest == null || payrollCreateRequest.bonus() == null) {
            return ZERO_AMOUNT;
        }

        return normalizeAmount(payrollCreateRequest.bonus());
    }

    private BigDecimal calculateTax(BigDecimal grossSalary) {
        return grossSalary
                .multiply(DEFAULT_TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalSalary(BigDecimal grossSalary, BigDecimal tax) {
        BigDecimal calculatedSalary = grossSalary.subtract(tax).setScale(2, RoundingMode.HALF_UP);

        return calculatedSalary.signum() < 0 ? ZERO_AMOUNT : calculatedSalary;
    }

    private BigDecimal normalizeAmount(BigDecimal amount) {
        if (amount == null) {
            return ZERO_AMOUNT;
        }

        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
