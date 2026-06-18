package com.example.sipmfsimulatorbackend.core.utils.mapper;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;

public class MutualFundMapper {

    private MutualFundMapper() {
    }

    public static FundResponse toResponse(MutualFund fund) {
        return FundResponse.builder().id(fund.getId()).schemeName(fund.getSchemeName()).schemeCode(String.valueOf(fund.getSchemeCode())).isinGrowth(fund.getIsinGrowth()).isinDivReinvestment(fund.getIsinDivReinvestment()).build();
    }

    public static FundDetailsResponse toDetails(MutualFund fund) {
        return FundDetailsResponse.builder().id(fund.getId()).schemeName(fund.getSchemeName()).schemeCode(String.valueOf(fund.getSchemeCode())).isinGrowth(fund.getIsinGrowth()).isinDivReinvestment(fund.getIsinDivReinvestment()).build();
    }
}
