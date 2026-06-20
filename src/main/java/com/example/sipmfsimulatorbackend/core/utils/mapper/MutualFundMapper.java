package com.example.sipmfsimulatorbackend.core.utils.mapper;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;

public class MutualFundMapper {

    private MutualFundMapper() {
    }

    public static FundResponseDTO toResponse(MutualFund fund) {
        return FundResponseDTO.builder().id(fund.getId()).schemeName(fund.getSchemeName()).schemeCode(String.valueOf(fund.getSchemeCode())).isinGrowth(fund.getIsinGrowth()).isinDivReinvestment(fund.getIsinDivReinvestment()).build();
    }

    public static FundDetailsResponseDTO toDetails(MutualFund fund) {
        return FundDetailsResponseDTO.builder().id(fund.getId()).schemeName(fund.getSchemeName()).schemeCode(String.valueOf(fund.getSchemeCode())).isinGrowth(fund.getIsinGrowth()).isinDivReinvestment(fund.getIsinDivReinvestment()).build();
    }
}
