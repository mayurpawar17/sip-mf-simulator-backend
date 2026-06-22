package com.example.sipmfsimulatorbackend.features.sip.service;

import com.example.sipmfsimulatorbackend.features.sip.dto.SipCreateRequestDTO;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipResponseDTO;

import java.util.List;

public interface SipService {
    SipResponseDTO createSip(Long userId, SipCreateRequestDTO request);

    SipResponseDTO getSipDetails(Long sipId);

    List<SipResponseDTO> getUserSips(Long userId);

    SipResponseDTO updateSip(Long sipId, SipCreateRequestDTO request);
    SipResponseDTO pauseSip(Long sipId);
    SipResponseDTO resumeSip(Long sipId);
    SipResponseDTO cancelSip(Long sipId);
}



