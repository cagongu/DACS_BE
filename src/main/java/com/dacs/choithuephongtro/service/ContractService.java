package com.dacs.choithuephongtro.service;

import com.dacs.choithuephongtro.Exception.UserNotFoundException;
import com.dacs.choithuephongtro.entities.LeaseContract;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ContractService {
    CompletableFuture<Void> CreateContract(UUID roomId, LeaseContract leaseContract) throws IOException, UserNotFoundException;
}
