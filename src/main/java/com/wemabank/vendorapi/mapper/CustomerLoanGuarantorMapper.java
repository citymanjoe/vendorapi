package com.wemabank.vendorapi.mapper;

import com.wemabank.vendorapi.model.CustomerLoanGuarantor;
import com.wemabank.vendorapi.model.CustomerLoanInterest;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerLoanGuarantorMapper implements RowMapper<CustomerLoanGuarantor> {
    @Override
    public CustomerLoanGuarantor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Date repayment_due_date = rs.getDate("REPAYMENT_DUE_DATE");
        String product_description = rs.getString("PRODUCT_DESC");
        String product_category = rs.getString("PRODUCT_CATEGORY");
        String branch_code = rs.getString("SOL_ID");
        String currency_code = rs.getString("ACCT_CRNCY_CODE");
        String customer_name = rs.getString("ACCT_NAME");
        String customer_no = rs.getString("CIF_ID");
        String loan_account_no = rs.getString("FORACID");
        Date book_date = rs.getDate("BOOK_DATE");
        Date value_date = rs.getDate("VALUE_DATE");
        Date maturity_date = rs.getDate("MATURITY_DATE");
        String loan_status = rs.getString("LOAN_STATUS");
        BigDecimal amount_disbursed = rs.getBigDecimal("AMOUNT_DISBURSED");
        String loan_tenor = rs.getString("LOAN_TENOR");
        return new CustomerLoanGuarantor(repayment_due_date, product_description,
                product_category, branch_code, currency_code,
                customer_name, customer_no, loan_account_no,
                book_date, value_date, maturity_date,
                amount_disbursed, new BigDecimal("0.00"),
                new BigDecimal("0.00"), loan_tenor, loan_status);
    }
}
