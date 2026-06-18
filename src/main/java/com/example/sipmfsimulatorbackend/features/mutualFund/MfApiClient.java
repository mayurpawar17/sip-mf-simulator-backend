package com.example.sipmfsimulatorbackend.features.mutualFund;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.MfApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MfApiClient {

    private final WebClient mfApiWebClient;

    public List<MfApiResponse> fetchAllFunds() {

        return mfApiWebClient.get().uri("/mf").retrieve().bodyToFlux(MfApiResponse.class).collectList().block();
    }
}
