package com.example.sipmfsimulatorbackend.features.mutualFund.repo;

import com.example.sipmfsimulatorbackend.features.mutualFund.dto.MfApiResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MutualFundBatchRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void batchUpsert(List<MfApiResponseDTO> funds) {

        String sql = """
                INSERT INTO mutual_funds
                (
                    scheme_code,
                    scheme_name,
                    isin_growth,
                    isin_div_reinvestment
                )
                VALUES (?, ?,?,?)
                
                ON CONFLICT (scheme_code)
                DO UPDATE SET
                scheme_name =
                EXCLUDED.scheme_name
                """;

        jdbcTemplate.batchUpdate(sql, funds, 1000, (ps, fund) -> {

            ps.setLong(1, Long.parseLong(fund.getSchemeCode()));

            ps.setString(2, fund.getSchemeName());
            ps.setString(3, fund.getIsinGrowth());
            ps.setString(4, fund.getIsinDivReinvestment());
        });
    }
}
