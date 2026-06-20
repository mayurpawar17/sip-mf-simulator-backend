package com.example.sipmfsimulatorbackend.features.mutualFund.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.core.utils.dto.Pagination;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponseDTO;
import com.example.sipmfsimulatorbackend.features.mutualFund.services.MutualFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class MutualFundController {

    private final MutualFundService fundService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<FundResponseDTO>>> getAllFunds(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "schemeName") String sortBy, @RequestParam(defaultValue = "DESC") String sortDir

    ) {

        // 1. Call updated service with flexible sorting parameters
        Page<FundResponseDTO> fundPage = fundService.getAllFunds(page, size, sortBy, sortDir);

        // 2. Map standard meta info structures clearly
        Pagination<FundResponseDTO> pagination = new Pagination<>();
        pagination.setPage(fundPage.getNumber());
        pagination.setSize(fundPage.getSize());
        pagination.setTotalElements(fundPage.getTotalElements());
        pagination.setTotalPages(fundPage.getTotalPages());
        pagination.setLast(fundPage.isLast());

        ApiResponse<List<FundResponseDTO>> body = ApiResponse.success("Funds fetched successfully!", fundPage.getContent(), pagination);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/{fundId}")
    public ResponseEntity<ApiResponse<FundDetailsResponseDTO>> getFundDetails(@PathVariable Long fundId) {

        return ResponseEntity.ok(ApiResponse.success("Fund details fetched successfully", fundService.getFundById(fundId)));
    }


    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FundResponseDTO>>> searchFunds(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "schemeName") String sortBy, @RequestParam(defaultValue = "DESC") String sortDir) {
        // 1. Trigger the business layer execution loop
        Page<FundResponseDTO> searchPage = fundService.searchFunds(keyword, page, size, sortBy, sortDir);

        // 2. Construct uniform reusable pagination metadata
        Pagination<FundResponseDTO> pagination = new Pagination<>();
        pagination.setPage(searchPage.getNumber());
        pagination.setSize(searchPage.getSize());
        pagination.setTotalElements(searchPage.getTotalElements());
        pagination.setTotalPages(searchPage.getTotalPages());
        pagination.setLast(searchPage.isLast());

        ApiResponse<List<FundResponseDTO>> body = ApiResponse.success("Search results retrieved successfully!", searchPage.getContent(), pagination);

        return ResponseEntity.ok(body);
    }
}
