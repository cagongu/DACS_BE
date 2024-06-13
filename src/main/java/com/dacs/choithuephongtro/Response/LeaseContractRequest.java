package com.dacs.choithuephongtro.Response;

import com.dacs.choithuephongtro.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaseContractRequest {
    private Timestamp createdDate;
    private String address;
    private User lessor;
    private User lessee;
    private BigDecimal revenuesRent;
    private BigDecimal unitPriceOfElectricity;
    private BigDecimal unitPriceOfWater;
    private Timestamp contractDuration;
}
