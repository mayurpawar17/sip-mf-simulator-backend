package com.example.sipmfsimulatorbackend.features.mutualFund.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MfApiResponse {
    private String schemeCode;

    private String schemeName;

    private String isinGrowth;

    private String isinDivReinvestment;
}

