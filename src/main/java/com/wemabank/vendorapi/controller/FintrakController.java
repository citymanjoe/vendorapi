package com.wemabank.vendorapi.controller;

import com.wemabank.vendorapi.service.FintrakService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping({ "/api/v1/fintrak" })
@Slf4j
public class FintrakController {

    @Autowired
    FintrakService finService;

    @RequestMapping(value = "/transaction/{accountNo}/{startDate}/{currentDate}", method = RequestMethod.GET)
    public ResponseEntity<?> GetCustomerTransaction(@PathVariable("accountNo") String accountNo,
                                                    @PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                    @PathVariable("currentDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date currentDate) {
        log.info("Request Input for customer transaction: {} | {} | {}", accountNo, startDate, currentDate);
        return finService.getCustomerTransactionDetail(accountNo,startDate,currentDate);
    }

    @RequestMapping(value = "/loan/{accountNo}/{monthNo}", method = RequestMethod.GET)
    public ResponseEntity<?> GetCustomerLoanInterestDetail(@PathVariable("accountNo") String accountNo,
                                                           @PathVariable("monthNo") int monthNo) {
        log.info("Request Input for customer loan interest: {} | {}", accountNo, monthNo);
        return finService.getCustomerLoanInterestDetail(accountNo,monthNo);
    }

    @RequestMapping(value = "/loan/{loanNo}", method = RequestMethod.GET)
    public ResponseEntity<?> GetCustomerNotificationDetail(@PathVariable("loanNo") String loanNo) {
        log.info("Request Input for loan notification: {} ", loanNo);
        ResponseEntity<?> entity = finService.getCustomerLoanNotificationDetail(loanNo);
        log.info("Response Result: {}", entity);
        return entity;
    }
}
