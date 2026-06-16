package com.example.sipmfsimulatorbackend.core.utils.mapper;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;

public class MutualFundMapper {

    private MutualFundMapper() {
    }

    public static FundResponse toResponse(
            MutualFund fund
    ) {
        return FundResponse.builder()
                .fundId(fund.getId())
                .fundName(fund.getFundName())
                .schemeCode(fund.getSchemeCode())
                .latestNav(fund.getLatestNav())
                .build();
    }

    public static FundDetailsResponse toDetails(
            MutualFund fund
    ) {
        return FundDetailsResponse.builder()
                .fundId(fund.getId())
                .fundName(fund.getFundName())
                .schemeCode(fund.getSchemeCode())
                .amc(fund.getAmc())
                .latestNav(fund.getLatestNav())
                .navDate(fund.getNavDate())
                .build();
    }
}
