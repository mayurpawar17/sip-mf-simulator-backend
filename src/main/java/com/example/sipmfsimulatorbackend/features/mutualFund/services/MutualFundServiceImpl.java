package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.core.exception.ResourceNotFoundException;
import com.example.sipmfsimulatorbackend.core.utils.mapper.MutualFundMapper;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MutualFundServiceImpl implements MutualFundService {


    private final MutualFundRepository fundRepository;


    @Override
    public Page<FundResponse> getAllFunds(int page, int size) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by("schemeName").descending()
                );

        return fundRepository.findAll(pageable).map(MutualFundMapper::toResponse);
    }

    @Override
    public FundDetailsResponse getFundById(Long fundId) {

        MutualFund fund = fundRepository.findById(fundId).orElseThrow(() -> new ResourceNotFoundException("Fund not found with id : " + fundId));

        return MutualFundMapper.toDetails(fund);
    }
}


