package com.hr.system.dto.request;

import com.hr.system.model.enums.LeaveType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import static com.hr.system.util.ApplicationConstants.END_DATE_AFTER_START_DATE_MESSAGE;
import static com.hr.system.util.ApplicationConstants.LEAVE_END_DATE_REQUIRED_MESSAGE;
import static com.hr.system.util.ApplicationConstants.LEAVE_REASON_REQUIRED_MESSAGE;
import static com.hr.system.util.ApplicationConstants.LEAVE_REASON_SIZE_MESSAGE;
import static com.hr.system.util.ApplicationConstants.LEAVE_START_DATE_REQUIRED_MESSAGE;
import static com.hr.system.util.ApplicationConstants.LEAVE_TYPE_REQUIRED_MESSAGE;

public record LeaveCreateRequest(

        @NotNull(message = LEAVE_TYPE_REQUIRED_MESSAGE)
        LeaveType leaveType,

        @NotNull(message = LEAVE_START_DATE_REQUIRED_MESSAGE)
        LocalDate startDate,

        @NotNull(message = LEAVE_END_DATE_REQUIRED_MESSAGE)
        LocalDate endDate,

        @NotBlank(message = LEAVE_REASON_REQUIRED_MESSAGE)
        @Size(max = 500, message = LEAVE_REASON_SIZE_MESSAGE)
        String reason
) {

    @AssertTrue(message = END_DATE_AFTER_START_DATE_MESSAGE)
    public boolean isDateRangeValid() {
        if (startDate == null || endDate == null) {
            return true;
        }

        return !endDate.isBefore(startDate);
    }
}
