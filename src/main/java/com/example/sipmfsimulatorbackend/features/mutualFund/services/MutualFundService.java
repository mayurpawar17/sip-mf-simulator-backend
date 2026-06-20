package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponseDTO;
import org.springframework.data.domain.Page;

public interface MutualFundService {

    Page<FundResponseDTO> getAllFunds(int page, int size, String sortBy, String sortDir);

    FundDetailsResponseDTO getFundById(Long fundId);

    Page<FundResponseDTO> searchFunds(String keyword, int page, int size, String sortBy, String sortDir);
}

