package com.example.sipmfsimulatorbackend.features.mutualFund.controller;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import com.example.sipmfsimulatorbackend.core.utils.dto.Pagination;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundDetailsResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.dto.FundResponse;
import com.example.sipmfsimulatorbackend.features.mutualFund.services.MutualFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class MutualFundController {

    private final MutualFundService fundService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FundResponse>>> getAllFunds(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size

    ) {

        Page<FundResponse> expensePage = fundService.getAllFunds(page, size);

        Pagination<FundResponse> pagination = new Pagination<>();
        pagination.setPage(expensePage.getNumber());
        pagination.setSize(expensePage.getSize());
        pagination.setTotalElements(expensePage.getTotalElements());
        pagination.setTotalPages(expensePage.getTotalPages());
        pagination.setLast(expensePage.isLast());

        var data = expensePage.getContent();
        ApiResponse<List<FundResponse>> body = ApiResponse.success("Funds fetched successfully!", data, pagination);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/{fundId}")
    public ResponseEntity<ApiResponse<FundDetailsResponse>> getFundDetails(@PathVariable Long fundId) {

        return ResponseEntity.ok(ApiResponse.success("Fund details fetched successfully", fundService.getFundById(fundId)));
    }
}
