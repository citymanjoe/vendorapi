package com.wemabank.vendorapi.service;

import com.wemabank.vendorapi.dao.FintrakDAO;
import com.wemabank.vendorapi.model.CustomerLoanGuarantor;
import com.wemabank.vendorapi.util.ErrorResponse;
import com.wemabank.vendorapi.util.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FintrakServiceImpl implements FintrakService {

    @Autowired
    FintrakDAO finDAO;

    @Override
    public ResponseEntity<?> getCustomerTransactionDetail(String accountNo, Date startDate, Date currentDate) {
        try{
            return new ResponseEntity<>(new SuccessResponse("CUSTOMER TRANSACTION DETAIL.", finDAO.GetCustTransDetail(accountNo,startDate,currentDate)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getCustomerLoanInterestDetail(String accountNo, int monthNo) {
        try{
            return new ResponseEntity<>(new SuccessResponse("CUSTOMER INTEREST DETAIL.", finDAO.GetCustLoanIntDetail(accountNo, monthNo)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getCustomerLoanNotificationDetail(String loanNo) {
        try{
            List<CustomerLoanGuarantor> cust = finDAO.GetCustLoanNotifDetail(loanNo);
            SuccessResponse response = new SuccessResponse("LOAN DETAIL.", cust);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
