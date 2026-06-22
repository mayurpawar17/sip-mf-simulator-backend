package com.example.sipmfsimulatorbackend.features.sip.service;

import com.example.sipmfsimulatorbackend.core.exception.ResourceNotFoundException;
import com.example.sipmfsimulatorbackend.core.utils.enums.SipStatus;
import com.example.sipmfsimulatorbackend.features.auth.entity.User;
import com.example.sipmfsimulatorbackend.features.auth.repo.UserRepository;
import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import com.example.sipmfsimulatorbackend.features.mutualFund.repo.MutualFundRepository;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipCreateRequestDTO;
import com.example.sipmfsimulatorbackend.features.sip.dto.SipResponseDTO;
import com.example.sipmfsimulatorbackend.features.sip.entity.Sip;
import com.example.sipmfsimulatorbackend.features.sip.repo.SipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        MutualFund mutualFund = mutualFundRepository.findById(request.fundId()).orElseThrow(() -> new ResourceNotFoundException("Target Mutual Fund not found for ID: " + request.fundId()));


        User mockUser = User.builder().id(MOCK_USER_ID).name("Mayur Pawar").email("mayur@gmail.com").mobileNumber("9876543210").password("Password@123").build();

        Sip sip = Sip.builder().user(mockUser).mutualFund(mutualFund).monthlyAmount(request.monthlyAmount()).sipDate(request.sipDate()).startDate(request.startDate()).build();

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
        Sip sip = sipRepository.findByIdWithFundDetails(sipId).orElseThrow(() -> new ResourceNotFoundException("SIP target record allocation not discovered for ID: " + sipId));

        return new SipResponseDTO(sip);
    }


    @Override
    @Transactional(readOnly = true)
    public List<SipResponseDTO> getUserSips(Long userId) {
        List<Sip> userSips = sipRepository.findByUserId(userId);

        return userSips.stream().map(SipResponseDTO::new) // Clean mapping using your record constructor reference
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public SipResponseDTO updateSip(Long sipId, SipCreateRequestDTO request) {
        Sip sip = sipRepository.findById(sipId)
                .orElseThrow(() -> new EntityNotFoundException("SIP record not found with ID: " + sipId));

        // Update properties
        sip.setMonthlyAmount(request.monthlyAmount());
        // Map other updateable fields as required (e.g., date, frequency)

        Sip updatedSip = sipRepository.save(sip);
        return new SipResponseDTO(updatedSip);
    }

    @Override
    @Transactional
    public SipResponseDTO pauseSip(Long sipId) {
        Sip sip = sipRepository.findById(sipId)
                .orElseThrow(() -> new EntityNotFoundException("SIP record not found with ID: " + sipId));

        if (sip.getStatus() == SipStatus.CANCELLED) {
            throw new IllegalStateException("Cannot pause a cancelled SIP.");
        }

        sip.setStatus(SipStatus.PAUSED);
        return new SipResponseDTO(sipRepository.save(sip));
    }

    @Override
    @Transactional
    public SipResponseDTO resumeSip(Long sipId) {
        Sip sip = sipRepository.findById(sipId)
                .orElseThrow(() -> new EntityNotFoundException("SIP record not found with ID: " + sipId));

        if (sip.getStatus() != SipStatus.PAUSED) {
            throw new IllegalStateException("Only paused SIPs can be resumed.");
        }

        sip.setStatus(SipStatus.ACTIVE);
        return new SipResponseDTO(sipRepository.save(sip));
    }

    @Override
    @Transactional
    public SipResponseDTO cancelSip(Long sipId) {
        Sip sip = sipRepository.findById(sipId)
                .orElseThrow(() -> new EntityNotFoundException("SIP record not found with ID: " + sipId));

        sip.setStatus(SipStatus.CANCELLED);
        return new SipResponseDTO(sipRepository.save(sip));
    }
}
