package com.dacs.choithuephongtro.controller;


import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.Response.LeaseContractRequest;
import com.dacs.choithuephongtro.entities.LeaseContract;
import com.dacs.choithuephongtro.service.ContractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractServiceImpl contractService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createContract(@RequestParam UUID roomId, @RequestBody LeaseContractRequest contractRequest) throws IOException, UserNotFoundException {
        // Map request to LeaseContract entity
        LeaseContract leaseContract = mapToLeaseContract(contractRequest);
        return contractService.CreateContract(roomId, leaseContract)
                .thenApply(result -> ResponseEntity.ok("Hợp đồng đã được tạo thành công"))
                .exceptionally(e -> ResponseEntity.status(500).body("Lỗi khi tạo hợp đồng: " + e.getMessage()));

    }

    private LeaseContract mapToLeaseContract(LeaseContractRequest request) {
        return LeaseContract.builder()
                .address(request.getAddress())
                .lessor(request.getLessor())
                .lessee(request.getLessee())
                .contractDuration(request.getContractDuration())
                .RevenuesRent(request.getRevenuesRent())
                .UnitPriceOfWater(request.getUnitPriceOfWater())
                .unitPriceOfElectricity(request.getUnitPriceOfElectricity())
                .build();
    }
}