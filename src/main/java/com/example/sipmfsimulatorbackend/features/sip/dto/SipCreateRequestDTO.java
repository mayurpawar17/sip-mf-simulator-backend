package com.example.sipmfsimulatorbackend.features.sip.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SipCreateRequestDTO(
        @NotNull(message = "Fund ID is required")
        Long fundId,

        @NotNull(message = "Monthly investment amount is required")
        @DecimalMin(value = "500.00", message = "Minimum monthly SIP installment must be 500.00")
        BigDecimal monthlyAmount,

        @NotNull(message = "Sip installment processing date is required")
        @Min(value = 1, message = "SIP date cannot be less than 1")
        @Max(value = 28, message = "SIP date cannot be greater than 28 to manage calendar variance consistently")
        Integer sipDate,

        @NotNull(message = "SIP start date is required")
        LocalDate startDate
) {
}


