package com.example.sipmfsimulatorbackend.features.mutualFund.services;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.MfApiResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundBatchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MutualFundSyncServiceImpl implements MutualFundSyncService {

    private static final int BATCH_SIZE = 1000;

    private final WebClient mfApiWebClient;
    private final MutualFundBatchRepository repository;

    @Override
    public void syncMutualFunds() {

        log.info("Starting MF Sync");

        List<MfApiResponse> allFunds = mfApiWebClient.get().uri("/mf").retrieve().bodyToFlux(MfApiResponse.class).collectList().block();

        if (allFunds == null || allFunds.isEmpty()) {

            log.warn("No funds received");

            return;
        }

        log.info("Received {} funds", allFunds.size());

        processInBatches(allFunds);

        log.info("Mutual Fund Sync Completed");
    }

    private void processInBatches(List<MfApiResponse> funds) {

        int totalRecords = funds.size();

        for (int start = 0; start < totalRecords; start += BATCH_SIZE) {

            int end = Math.min(start + BATCH_SIZE, totalRecords);

            List<MfApiResponse> batch = funds.subList(start, end);

            repository.batchUpsert(batch);

            log.info("Processed {} to {}", start, end);
        }
    }
}
