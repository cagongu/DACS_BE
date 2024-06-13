package com.dacs.choithuephongtro.controller;


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
    public CompletableFuture<ResponseEntity<String>> createContract(@RequestParam UUID roomId, @RequestBody LeaseContractRequest contractRequest) throws IOException {
        // Map request to LeaseContract entity
        LeaseContract leaseContract = mapToLeaseContract(contractRequest);
        contractService.CreateContract(roomId, leaseContract);
        return contractService.CreateContract(roomId, leaseContract)
                .thenApply(result -> ResponseEntity.ok("Hợp đồng đã được tạo thành công"))
                .exceptionally(e -> ResponseEntity.status(500).body("Lỗi khi tạo hợp đồng: " + e.getMessage()));

    }

    private LeaseContract mapToLeaseContract(LeaseContractRequest request) {
        LeaseContract leaseContract = new LeaseContract();
        leaseContract.setCreatedDate(request.getCreatedDate());
        leaseContract.setAddress(request.getAddress());
        leaseContract.setLessor(request.getLessor());
        leaseContract.setLessee(request.getLessee());
        leaseContract.setRevenuesRent(request.getRevenuesRent());
        leaseContract.setUnitPriceOfElectricity(request.getUnitPriceOfElectricity());
        leaseContract.setUnitPriceOfWater(request.getUnitPriceOfWater());
        leaseContract.setContractDuration(request.getContractDuration());
        return leaseContract;
    }
}