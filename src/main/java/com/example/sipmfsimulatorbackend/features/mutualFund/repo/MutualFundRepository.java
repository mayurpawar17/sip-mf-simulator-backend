package com.example.sipmfsimulatorbackend.features.mutualFund.repo;

import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualFundRepository
        extends JpaRepository<MutualFund, Long> {

    boolean existsBySchemeCode(String schemeCode);

}


