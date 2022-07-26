package com.wemabank.vendorapi.mapper;

import com.wemabank.vendorapi.model.CRMFinancial;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRMFinancialMapper implements RowMapper<CRMFinancial> {
    @Override
    public CRMFinancial mapRow(ResultSet rs, int rowNum) throws SQLException {
        String fundSource = rs.getString("SOURCE_OF_FUNDS");
        Integer employeeNo = rs.getInt("NUMBEROF_EMPLOYEES");
        BigDecimal annualIncome = rs.getBigDecimal("AVERAGE_ANNUALINCOME");
        String cifId = rs.getString("ORGKEY");
        return new CRMFinancial(fundSource, employeeNo, annualIncome, cifId);
    }
}
