package com.wemabank.vendorapi.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class CustomerTransaction {
    private String month;
    private String year;
    private String cust_id;
    private String foracid;
    private String schm_type;
    private BigDecimal min_debit_balance;
    private BigDecimal max_debit_balance;
    private BigDecimal min_credit_balance;
    private BigDecimal max_credit_balance;
    private BigDecimal debit_turnover;
    private BigDecimal credit_turnover;
    private BigDecimal management_fee;
    private BigDecimal commitment_fee;
    private BigDecimal commitment_contingent_liability;
}
