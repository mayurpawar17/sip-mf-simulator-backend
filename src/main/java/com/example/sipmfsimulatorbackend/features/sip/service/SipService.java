package com.example.sipmfsimulatorbackend.features.sip.service;

import com.example.sipmfsimulatorbackend.features.sip.dto.SipCreateRequestDTO;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipResponseDTO;

public interface SipService {
    SipResponseDTO createSip(Long userId, SipCreateRequestDTO request);

    SipResponseDTO getSipDetails(Long sipId);
}



