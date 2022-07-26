package com.wemabank.vendorapi.mapper;

import com.wemabank.vendorapi.model.CustomerLoanInterest;
import com.wemabank.vendorapi.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionMapper implements RowMapper<Transaction>  {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        String monthNo = rs.getString("S_MONTH");
        String yearNo = rs.getString("S_YEAR");
        String accountNo = rs.getString("FORACID");
        BigDecimal debitTurnover = rs.getBigDecimal("TOTAL_DEBIT");
        BigDecimal creditTurnover = rs.getBigDecimal("TOTAL_CREDIT");
        BigDecimal maxDebitBal = rs.getBigDecimal("MAX_DEBIT_BALANCE");
        BigDecimal minDebitBal = rs.getBigDecimal("MIN_DEBIT_BALANCE");
        BigDecimal minCreditBal = rs.getBigDecimal("MIN_CREDIT_BALANCE");
        BigDecimal maxCreditBal = rs.getBigDecimal("MAX_CREDIT_BALANCE");
        String schmCode = rs.getString("SCHM_CODE");
        String schmType = rs.getString("SCHM_TYPE");
        String custId = rs.getString("CUST_ID");
        String cifId = rs.getString("CIF_ID");
        return new Transaction(minCreditBal, maxCreditBal,
                minDebitBal, maxDebitBal, creditTurnover,
                debitTurnover, accountNo, cifId, custId,
                schmCode, schmType, monthNo, yearNo);
    }
}
