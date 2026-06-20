package com.example.sipmfsimulatorbackend.features.sip.repo;

import com.example.sipmfsimulatorbackend.features.sip.entity.Sip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SipRepository extends JpaRepository<Sip, Long> {

    /**
     * Uses dynamic fetch join behavior to load entity graphs cleanly in a single SQL operation.
     * Prevents N+1 query execution side-effects during target DTO transformation.
     */
    @Query("SELECT s FROM Sip s JOIN FETCH s.mutualFund WHERE s.id = :sipId")
    Optional<Sip> findByIdWithFundDetails(@Param("sipId") Long sipId);
}


