package com.wemabank.vendorapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wemabank.vendorapi.service.FintrakService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping({ "/api/v1/bank" })
@Slf4j
public class BankController {
	
	@Autowired
    FintrakService finService;
	
	@RequestMapping(value = "/{branch}", method = RequestMethod.GET)
    public ResponseEntity<?> getVaultSolAmount(@PathVariable("branch") String branch) {
        log.info("Request Input for Vault: {} ", branch);
        return finService.getVaultAmount(branch);
    }

}
