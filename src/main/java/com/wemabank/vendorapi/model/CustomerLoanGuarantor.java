package com.wemabank.vendorapi.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class CustomerLoanGuarantor {
    private Date repayment_due_date;
    private String product_description;
    private String product_category;
    private String branch_code;
    private String currency_code;
    private String customer_name;
    private String customer_no;
    private String loan_account_no;
    private Date book_date;
    private Date value_date;
    private Date maturity_date;
    private BigDecimal amount_disbursed;
    private BigDecimal amount_financed;
    private BigDecimal user_reference_no;
    private String loan_tenor;
    private String loan_status;

    public CustomerLoanGuarantor(Date repayment_due_date, String product_description, String product_category, String branch_code, String currency_code, String customer_name, String customer_no, String loan_account_no, Date book_date, Date value_date, Date maturity_date, BigDecimal amount_disbursed, BigDecimal amount_financed, BigDecimal user_reference_no, String loan_tenor, String loan_status) {
        this.repayment_due_date = repayment_due_date;
        this.product_description = product_description;
        this.product_category = product_category;
        this.branch_code = branch_code;
        this.currency_code = currency_code;
        this.customer_name = customer_name;
        this.customer_no = customer_no;
        this.loan_account_no = loan_account_no;
        this.book_date = book_date;
        this.value_date = value_date;
        this.maturity_date = maturity_date;
        this.amount_disbursed = amount_disbursed;
        this.amount_financed = amount_financed;
        this.user_reference_no = user_reference_no;
        this.loan_tenor = loan_tenor;
        this.loan_status = loan_status;
    }
}
