package com.example.sipmfsimulatorbackend.features.mutualFund;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.MfSchemeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MfApiClient {

    private final WebClient mfApiWebClient;

    public List<MfSchemeResponse> fetchAllFunds() {

        return mfApiWebClient.get().uri("/mf").retrieve().bodyToFlux(MfSchemeResponse.class).collectList().block();
    }
}
