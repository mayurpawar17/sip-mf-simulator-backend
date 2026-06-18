package com.example.sipmfsimulatorbackend.features.mutualFund.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundResponse {

    private Long id;

    private String schemeName;

    private String schemeCode;

    private String isinGrowth;
    private String isinDivReinvestment;

}

