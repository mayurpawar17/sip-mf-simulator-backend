package com.example.sipmfsimulatorbackend.features.sip.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipCreateRequestDTO;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipResponseDTO;
import com.example.sipmfsimulatorbackend.features.sip.service.SipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sips")
@RequiredArgsConstructor
public class SipController {

    private final SipService sipService;

    // Abstracting active User ID context extraction until JWT Security Context integration
    private final Long DEPLOYED_SECURITY_CONTEXT_USER_ID = 1L;

    @PostMapping
    public ResponseEntity<ApiResponse<SipResponseDTO>> createSip(@Valid @RequestBody SipCreateRequestDTO request) {
        SipResponseDTO responseData = sipService.createSip(DEPLOYED_SECURITY_CONTEXT_USER_ID, request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success( "SIP created successfully",responseData));
    }

    @GetMapping("/{sipId}")
    public ResponseEntity<ApiResponse<SipResponseDTO>> getSipDetails(@PathVariable Long sipId) {
        SipResponseDTO responseData = sipService.getSipDetails(sipId);
        return ResponseEntity
                .ok(ApiResponse.success( "Operation successful",responseData));
    }
}
