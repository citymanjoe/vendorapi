package com.wemabank.vendorapi.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class CRMFinancial {
    private String fundSource;
    private Integer employeeNo;
    private BigDecimal annualIncome;

    private String cifId;

    public CRMFinancial(String fundSource, Integer employeeNo, BigDecimal annualIncome, String cifId) {
        this.fundSource = fundSource;
        this.employeeNo = employeeNo;
        this.annualIncome = annualIncome;
        this.cifId = cifId;
    }
    public CRMFinancial(){
        super();
    }

}
