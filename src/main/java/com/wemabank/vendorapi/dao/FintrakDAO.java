package com.wemabank.vendorapi.dao;

import com.wemabank.vendorapi.model.CustomerLoanGuarantor;
import com.wemabank.vendorapi.model.CustomerLoanInterest;
import com.wemabank.vendorapi.model.Transaction;

import java.util.Date;
import java.util.List;

public interface FintrakDAO {

    List<Transaction> GetCustTransDetail(String accountNo, Date startDate, Date currentDate);

    List<CustomerLoanInterest> GetCustLoanIntDetail(String accountNo, int monthNo);

    List<CustomerLoanGuarantor> GetCustLoanNotifDetail(String loanNo);
}
