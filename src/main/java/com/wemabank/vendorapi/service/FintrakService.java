package com.wemabank.vendorapi.service;

import com.wemabank.vendorapi.model.CRMFinancial;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface FintrakService {

    ResponseEntity<?> getCustomerTransactionDetail(String accountNo, Date startDate, Date currentDate);

    ResponseEntity<?> getCustomerLoanInterestDetail(String accountNo, int monthNo);

    ResponseEntity<?> getCustomerLoanNotificationDetail(String loanNo);
    
    public ResponseEntity<?> getVaultAmount(String branch);

    public ResponseEntity<?> getSourceFund(String cifId);

    public ResponseEntity<?> postSourceFund(CRMFinancial crm);
}
