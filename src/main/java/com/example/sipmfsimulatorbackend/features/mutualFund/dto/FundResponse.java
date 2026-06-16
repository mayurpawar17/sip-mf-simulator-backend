package com.example.sipmfsimulatorbackend.features.mutualFund.dto;



import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundResponse {

    private Long fundId;

    private String fundName;

    private String schemeCode;

    private BigDecimal latestNav;
}

