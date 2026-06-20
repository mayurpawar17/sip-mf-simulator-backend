package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.core.exception.ResourceNotFoundException;
import com.example.sipmfsimulatorbackend.core.utils.mapper.MutualFundMapper;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MutualFundServiceImpl implements MutualFundService {


    private final MutualFundRepository fundRepository;


    @Override
    public Page<FundResponseDTO> getAllFunds(int page, int size, String sortBy, String sortDir) {
        // 1. Safeguard pagination size limits against API abuse (e.g., someone asking for size=10000)
        int maxPageSize = Math.min(size, 100);

        // 2. Build dynamic sort conditions safely
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, maxPageSize, sort);

        return fundRepository.findAll(pageable).map(MutualFundMapper::toResponse);
    }

    @Override
    public FundDetailsResponseDTO getFundById(Long fundId) {

        MutualFund fund = fundRepository.findById(fundId).orElseThrow(() -> new ResourceNotFoundException("Fund not found with id : " + fundId));

        return MutualFundMapper.toDetails(fund);
    }

    @Override
    public Page<FundResponseDTO> searchFunds(String keyword, int page, int size, String sortBy, String sortDir) {
        // 1. Guard rails against malicious API sizing
        int maxPageSize = Math.min(size, 100);

        // 2. Sanitize search inputs
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllFunds(page, maxPageSize, sortBy, sortDir);
        }

        // 3. Dynamic Sort configuration
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, maxPageSize, sort);

        return fundRepository.searchBySchemeName(keyword.trim(), pageable)
                .map(MutualFundMapper::toResponse);
    }
}


