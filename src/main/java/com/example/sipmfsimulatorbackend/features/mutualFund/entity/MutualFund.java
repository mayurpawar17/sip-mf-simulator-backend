package com.example.sipmfsimulatorbackend.features.mutualFund.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "mutual_funds",
        indexes = {
                @Index(name = "idx_scheme_code", columnList = "scheme_code"),
                @Index(name = "idx_fund_name", columnList = "fund_name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutualFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fundName;

    @Column(nullable = false, unique = true)
    private String schemeCode;

    @Column(nullable = false)
    private String amc;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal latestNav;

    private LocalDate navDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

