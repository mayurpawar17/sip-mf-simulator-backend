package com.example.sipmfsimulatorbackend.features.mutualFund.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.services.MutualFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class MutualFundController {

    private final MutualFundService fundService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<FundResponse>>>
    getAllFunds(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "fundName")
            String sortBy
    ) {

        Page<FundResponse> response =
                fundService.getAllFunds(
                        page,
                        size,
                        sortBy
                );

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Funds fetched successfully",
                        response
                )
        );
    }

    @GetMapping("/{fundId}")
    public ResponseEntity<ApiResponse<FundDetailsResponse>>
    getFundDetails(
            @PathVariable Long fundId
    ) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Fund details fetched successfully",
                        fundService.getFundById(fundId)
                )
        );
    }
}
