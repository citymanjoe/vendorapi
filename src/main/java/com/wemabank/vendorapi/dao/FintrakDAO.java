package com.wemabank.vendorapi.dao;

import com.wemabank.vendorapi.model.*;

import java.util.Date;
import java.util.List;

public interface FintrakDAO {

    List<Transaction> GetCustTransDetail(String accountNo, Date startDate, Date currentDate);

    List<CustomerLoanInterest> GetCustLoanIntDetail(String accountNo, int monthNo);

    List<CustomerLoanGuarantor> GetCustLoanNotifDetail(String loanNo);
    
    VaultCash getVaultCash(String branch);

    CRMFinancial getCRMSourceFund(String cifId);

    void postCRMSourceFund(CRMFinancial crm);

}
