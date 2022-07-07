package com.wemabank.vendorapi.model;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VaultCash {
	
	private BigDecimal balance;
	
	private String crncyCode;

	public VaultCash(BigDecimal balance, String crncyCode) {
		super();
		this.balance = balance;
		this.crncyCode = crncyCode;
	}

	public VaultCash() {
		super();
	}	

}
