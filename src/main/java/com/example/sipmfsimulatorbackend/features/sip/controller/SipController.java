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

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("SIP created successfully", responseData));
    }

    @GetMapping("/{sipId}")
    public ResponseEntity<ApiResponse<SipResponseDTO>> getSipDetails(@PathVariable Long sipId) {
        SipResponseDTO responseData = sipService.getSipDetails(sipId);
        return ResponseEntity.ok(ApiResponse.success("Operation successful", responseData));
    }


    /**
     * Get all SIPs for the currently authenticated/simulated user.
     * Accessible via: GET /api/v1/sips
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<SipResponseDTO>>> getUserSips() {
        List<SipResponseDTO> responseData = sipService.getUserSips(DEPLOYED_SECURITY_CONTEXT_USER_ID);
        return ResponseEntity.ok(ApiResponse.success("User SIPs retrieved successfully", responseData));
    }


    /**
     * Update an existing SIP's details (e.g., amount, date).
     * PUT /api/v1/sips/{sipId}
     */
    @PutMapping("/{sipId}")
    public ResponseEntity<ApiResponse<SipResponseDTO>> updateSip(@PathVariable Long sipId, @Valid @RequestBody SipCreateRequestDTO request) {
        SipResponseDTO responseData = sipService.updateSip(sipId, request);
        return ResponseEntity.ok(ApiResponse.success("SIP updated successfully", responseData));
    }

    /**
     * Pause an active SIP.
     * PATCH /api/v1/sips/{sipId}/pause
     */
//    @ some what appropriate annotation like @PutMapping or @PatchMapping
    @PatchMapping("/{sipId}/pause")
    public ResponseEntity<ApiResponse<SipResponseDTO>> pauseSip(@PathVariable Long sipId) {
        SipResponseDTO responseData = sipService.pauseSip(sipId);
        return ResponseEntity.ok(ApiResponse.success("SIP paused successfully", responseData));
    }

    /**
     * Resume a paused SIP.
     * PATCH /api/v1/sips/{sipId}/resume
     */
    @PatchMapping("/{sipId}/resume")
    public ResponseEntity<ApiResponse<SipResponseDTO>> resumeSip(@PathVariable Long sipId) {
        SipResponseDTO responseData = sipService.resumeSip(sipId);
        return ResponseEntity.ok(ApiResponse.success("SIP resumed successfully", responseData));
    }

    /**
     * Cancel a SIP entirely.
     * PATCH /api/v1/sips/{sipId}/cancel
     */
    @PatchMapping("/{sipId}/cancel")
    public ResponseEntity<ApiResponse<SipResponseDTO>> cancelSip(@PathVariable Long sipId) {
        SipResponseDTO responseData = sipService.cancelSip(sipId);
        return ResponseEntity.ok(ApiResponse.success("SIP cancelled successfully", responseData));
    }
}
