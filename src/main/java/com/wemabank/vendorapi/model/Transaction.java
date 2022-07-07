package com.wemabank.vendorapi.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class Transaction {
    private BigDecimal minCreditBal;
    private BigDecimal maxCreditBal;
    private BigDecimal minDebitBal;
    private BigDecimal maxDebitBal;
    private BigDecimal creditTurnover;
    private BigDecimal debitTurnover;
    private String accountNo;
    private String cifId;
    private String custId;
    private String schmCode;
    private String schmType;
    private String monthNo;
    private String yearNo;

    public Transaction(BigDecimal minCreditBal, BigDecimal maxCreditBal, BigDecimal minDebitBal, BigDecimal maxDebitBal, BigDecimal creditTurnover, BigDecimal debitTurnover, String accountNo, String cifId, String custId, String schmCode, String schmType, String monthNo, String yearNo) {
        this.minCreditBal = minCreditBal;
        this.maxCreditBal = maxCreditBal;
        this.minDebitBal = minDebitBal;
        this.maxDebitBal = maxDebitBal;
        this.creditTurnover = creditTurnover;
        this.debitTurnover = debitTurnover;
        this.accountNo = accountNo;
        this.cifId = cifId;
        this.custId = custId;
        this.schmCode = schmCode;
        this.schmType = schmType;
        this.monthNo = monthNo;
        this.yearNo = yearNo;
    }
}
