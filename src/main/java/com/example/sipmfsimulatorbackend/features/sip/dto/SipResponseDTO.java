package com.example.sipmfsimulatorbackend.features.sip.dto;

import com.example.sipmfsimulatorbackend.features.sip.entity.Sip;

import java.math.BigDecimal;

public record SipResponseDTO(
        Long sipId,
        String fundName,
        BigDecimal monthlyAmount,
        String status
) {
    /**
     * Compact projection constructor to map entity properties directly.
     */
    public SipResponseDTO(Sip sip) {
        this(
                sip.getId(),
                sip.getMutualFund().getSchemeName(),
                sip.getMonthlyAmount(),
                sip.getStatus().name()
        );
    }
}


