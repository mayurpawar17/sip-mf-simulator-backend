package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface MutualFundService {

    Page<FundResponse> getAllFunds(int page, int size, String sortBy, String sortDir);

    FundDetailsResponse getFundById(Long fundId);

    Page<FundResponse> searchFunds(String keyword, int page, int size, String sortBy, String sortDir);
}

