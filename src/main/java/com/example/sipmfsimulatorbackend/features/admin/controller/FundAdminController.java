package com.example.sipmfsimulatorbackend.features.admin.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.services.MutualFundSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/funds")
@RequiredArgsConstructor
public class FundAdminController {

    private final MutualFundSyncService service;

    @PostMapping("/sync")
    public ResponseEntity<ApiResponse<String>> sync() {

        service.syncMutualFunds();
        ApiResponse<String> response = ApiResponse.success("Fund Sync Completed!!!", null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
