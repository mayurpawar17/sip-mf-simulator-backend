package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import org.springframework.data.domain.Page;

public interface MutualFundService {

    Page<FundResponse> getAllFunds(
            int page,
            int size,
            String sortBy
    );

    FundDetailsResponse getFundById(
            Long fundId
    );
}

