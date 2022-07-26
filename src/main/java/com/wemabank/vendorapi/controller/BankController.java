package com.wemabank.vendorapi.controller;

import com.wemabank.vendorapi.model.CRMFinancial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/crm/{cifId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCRMSourceFund(@PathVariable("cifId") String cifId) {
        log.info("Request Input for Vault: {} ", cifId);
        return finService.getSourceFund(cifId);
    }

    @RequestMapping(value = "/crm", method = RequestMethod.PUT)
    public ResponseEntity<?> postCRMSourceFund(@RequestBody CRMFinancial crm) {
        log.info("Request Input for Vault: {} | {}", crm);
        return finService.postSourceFund(crm);
    }

}
