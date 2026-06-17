package com.example.sipmfsimulatorbackend.features.admin.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.services.FundSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/funds")
@RequiredArgsConstructor
public class FundAdminController {

    private final FundSyncService fundSyncService;

    @PostMapping("/sync")
    public ResponseEntity<ApiResponse<Integer>> syncFunds() {

        Integer count = fundSyncService.syncFunds();

        return ResponseEntity.ok(ApiResponse.success("Funds synchronized successfully", count));
    }
}
