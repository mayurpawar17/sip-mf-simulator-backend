package com.example.sipmfsimulatorbackend.features.sip.service;

import com.example.sipmfsimulatorbackend.core.exception.ResourceNotFoundException;
import com.example.sipmfsimulatorbackend.features.auth.entity.User;
import com.example.sipmfsimulatorbackend.features.auth.repo.UserRepository;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundRepository;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipCreateRequestDTO;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipResponseDTO;
import com.example.sipmfsimulatorbackend.features.sip.entity.Sip;
import com.example.sipmfsimulatorbackend.features.sip.repo.SipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SipServiceImpl implements SipService {

    private final SipRepository sipRepository;
    private final UserRepository userRepository;
    private final MutualFundRepository mutualFundRepository;
    private static final Long MOCK_USER_ID = 1L;
//    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional
    public SipResponseDTO createSip(Long userId, SipCreateRequestDTO request) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User identity reference allocation not found for ID: " + userId));

        MutualFund mutualFund = mutualFundRepository.findById(request.fundId())
                .orElseThrow(() -> new ResourceNotFoundException("Target Mutual Fund not found for ID: " + request.fundId()));


        User mockUser = User.builder()
                .id(MOCK_USER_ID)
                .name("Mayur Pawar")
                .email("mayur@gmail.com")
                .mobileNumber("9876543210")
                .password("Password@123")
                .build();

        Sip sip = Sip.builder()
                .user(mockUser)
                .mutualFund(mutualFund)
                .monthlyAmount(request.monthlyAmount())
                .sipDate(request.sipDate())
                .startDate(request.startDate())
                .build();

        Sip savedSip = sipRepository.save(sip);

//        auditLogRepository.save(AuditLog.builder()
//                .action("SIP_CREATED - ID: " + savedSip.getId() + " | Fund: " + mutualFund.getFundName())
//                .timestamp(LocalDateTime.now())
//                .build());

        return new SipResponseDTO(savedSip);
    }

    @Override
    @Transactional(readOnly = true)
    public SipResponseDTO getSipDetails(Long sipId) {
        Sip sip = sipRepository.findByIdWithFundDetails(sipId)
                .orElseThrow(() -> new ResourceNotFoundException("SIP target record allocation not discovered for ID: " + sipId));

        return new SipResponseDTO(sip);
    }
}
