package com.wemabank.vendorapi.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.wemabank.vendorapi.model.VaultCash;

public class VaultCashMapper implements RowMapper<VaultCash> {

	@Override
	public VaultCash mapRow(ResultSet rs, int rowNum) throws SQLException {
		String crncyCode = rs.getString("VaultCrncy");
		BigDecimal balance = rs.getBigDecimal("VaultBal");
		return new VaultCash(balance, crncyCode);
	}

}
