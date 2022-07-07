package com.wemabank.vendorapi.mapper;

import com.wemabank.vendorapi.model.CustomerLoanInterest;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerLoanInterestMapper implements RowMapper<CustomerLoanInterest> {
    @Override
    public CustomerLoanInterest mapRow(ResultSet rs, int rowNum) throws SQLException {
        String month = rs.getString("S_MONTH");
        String year = rs.getString("S_YEAR");
        String monthYear = month + "-" + year;
        String period = monthYear;
        String foracid = rs.getString("FORACID");
        String schm_type = rs.getString("SCHM_TYPE");
        double float_charge = 0.0;
        double interest = rs.getDouble("INTEREST_RATE");
        String account_name = rs.getString("ACCT_NAME");
        String cust_id = rs.getString("CUST_ID");
        String cif_id = rs.getString("CIF_ID");
        return new CustomerLoanInterest(month, year, period,
                foracid, schm_type, float_charge, interest, account_name, cust_id, cif_id);
    }
}
