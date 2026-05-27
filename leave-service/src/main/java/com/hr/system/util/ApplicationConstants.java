package com.hr.system.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicationConstants {
    public static final String LEAVE_TYPE_REQUIRED_MESSAGE = "Leave type is required";
    public static final String LEAVE_START_DATE_REQUIRED_MESSAGE = "Start date is required";
    public static final String LEAVE_END_DATE_REQUIRED_MESSAGE = "End date is required";
    public static final String LEAVE_REASON_REQUIRED_MESSAGE = "Reason is required";
    public static final String LEAVE_REASON_SIZE_MESSAGE = "Reason must not exceed 500 characters";
    public static final String END_DATE_AFTER_START_DATE_MESSAGE = "End date cannot be earlier than start date";
}
