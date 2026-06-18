package com.example.sipmfsimulatorbackend.features.mutualFund.repo;

import com.example.sipmfsimulatorbackend.features.mutualFund.entity.MutualFund;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund, Long> {

    boolean existsBySchemeCode(String schemeCode);

    Page<MutualFund> findBySchemeNameContainingIgnoreCase(
            String keyword,
            Pageable pageable
    );

    // Optimized case-insensitive partial match search using JPQL
    @Query("SELECT m FROM MutualFund m WHERE LOWER(m.schemeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<MutualFund> searchBySchemeName(@Param("keyword") String keyword, Pageable pageable);

}


