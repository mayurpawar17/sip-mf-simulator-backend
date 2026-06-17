package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.features.mutualFund.MfApiClient;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.MfSchemeResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FundSyncServiceImpl implements FundSyncService {

    private final MfApiClient mfApiClient;

    private final MutualFundRepository fundRepository;

    @Override
    public Integer syncFunds() {

        List<MfSchemeResponse> schemes = mfApiClient.fetchAllFunds();

        int insertedCount = 0;

        for (MfSchemeResponse scheme : schemes) {

            if (fundRepository.existsBySchemeCode(scheme.getSchemeCode())) {
                continue;
            }

            MutualFund fund = MutualFund.builder().schemeCode(scheme.getSchemeCode()).fundName(scheme.getSchemeName()).latestNav(BigDecimal.ZERO).build();

            fundRepository.save(fund);

            insertedCount++;
        }

        return insertedCount;
    }
}
