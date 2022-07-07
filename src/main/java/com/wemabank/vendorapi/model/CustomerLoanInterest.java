package com.wemabank.vendorapi.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CustomerLoanInterest {
    private String month;
    private String year;
    private String period;
    private Date as_of_date = new Date();
    private String foracid;
    private String schm_type;
    private double float_charge;
    private double interest;
    private String account_name;
    private String cust_id;
    private String cif_id;

    public CustomerLoanInterest(String month, String year, String period, String foracid, String schm_type, double float_charge, double interest, String account_name, String cust_id, String cif_id) {
        this.month = month;
        this.year = year;
        this.period = period;
        this.as_of_date = as_of_date;
        this.foracid = foracid;
        this.schm_type = schm_type;
        this.float_charge = float_charge;
        this.interest = interest;
        this.account_name = account_name;
        this.cust_id = cust_id;
        this.cif_id = cif_id;
    }
}
