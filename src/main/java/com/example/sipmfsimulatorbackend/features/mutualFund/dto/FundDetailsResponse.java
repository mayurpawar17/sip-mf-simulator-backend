package com.example.sipmfsimulatorbackend.features.mutualFund.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundDetailsResponse {

    private Long fundId;

    private String fundName;

    private String schemeCode;

    private String amc;

    private BigDecimal latestNav;

    private LocalDate navDate;
}
