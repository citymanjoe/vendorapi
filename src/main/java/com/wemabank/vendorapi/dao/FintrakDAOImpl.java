package com.wemabank.vendorapi.dao;

import com.wemabank.vendorapi.mapper.CustomerLoanGuarantorMapper;
import com.wemabank.vendorapi.mapper.CustomerLoanInterestMapper;
import com.wemabank.vendorapi.model.CustomerLoanGuarantor;
import com.wemabank.vendorapi.model.CustomerLoanInterest;
import com.wemabank.vendorapi.model.CustomerTransaction;
import com.wemabank.vendorapi.model.Transaction;
import com.wemabank.vendorapi.service.XMLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FintrakDAOImpl implements FintrakDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    XMLService xService;

    @Override
    public List<Transaction> GetCustTransDetail(String accountNo, Date startDate, Date currentDate) {
        List<Transaction> transaction = new ArrayList<Transaction>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT XMLTYPE(CustomerTransaction(?,?,?)) XMLVALUE FROM dual");
        String sql = query.toString();
        try {
            Object[] params = new Object[] { accountNo,startDate,currentDate };
            String customer = jdbcTemplate.queryForObject(sql, String.class, params);
            transaction = xService.parseCourse(customer);
            return transaction;
        } catch (Exception ex) {
            log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
            return null;
        }
    }

    @Override
    public List<CustomerLoanInterest> GetCustLoanIntDetail(String accountNo, int monthNo) {
        List<CustomerLoanInterest> accountList = new ArrayList<CustomerLoanInterest>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT FORACID,ACCT_NAME,CIF_ID,CUST_ID,SCHM_CODE,SCHM_TYPE,INTEREST_RATE,");
        query.append("EXTRACT(YEAR FROM (to_DATE((SYSDATE),'DD-MON-YYYY'))) AS S_YEAR,");
        query.append("EXTRACT(MONTH FROM (to_DATE((SYSDATE),'DD-MON-YYYY'))) AS S_MONTH ");
        query.append("FROM tbaadm.eit a, tbaadm.gam b where a.entity_id = b.acid and foracid = ?");
        String sql = query.toString();
        try {
            CustomerLoanInterestMapper rowMapper = new CustomerLoanInterestMapper();
            Object[] params = new Object[] { accountNo };
            accountList = jdbcTemplate.query(sql, rowMapper, params);
            return accountList;
        } catch (Exception ex) {
            log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
            return null;
        }
    }

    @Override
    public List<CustomerLoanGuarantor> GetCustLoanNotifDetail(String loanNo) {
        List<CustomerLoanGuarantor> accountList = new ArrayList<CustomerLoanGuarantor>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT FORACID,CIF_ID,a.SOL_ID,ACCT_CRNCY_CODE,ACCT_NAME,EI_PERD_START_DATE AS BOOK_DATE,");
        query.append("DIS_SHDL_DATE AS VALUE_DATE,EI_PERD_END_DATE AS MATURITY_DATE,REP_PERD_MTHS AS LOAN_TENOR,");
        query.append("NEXT_DMD_DATE AS REPAYMENT_DUE_DATE,DIS_AMT AS AMOUNT_DISBURSED,");
        query.append("(CASE WHEN ACCT_CLS_FLG = 'N' THEN 'ACTIVE' ELSE 'CLOSED' END) AS LOAN_STATUS,");
        query.append("(CASE WHEN SCHM_TYPE = 'LAA' THEN 'TERM LOAN' WHEN SCHM_TYPE = 'ODA' THEN 'OVERDRAFT' ELSE 'UNKNOWN' END) AS PRODUCT_DESC,");
        query.append("(CASE WHEN SCHM_TYPE = 'LAA' THEN 'Contingent Asset' WHEN SCHM_TYPE = 'ODA' THEN 'Contingent Liability' ELSE 'UNKNOWN' END) AS PRODUCT_CATEGORY  ");
        query.append("FROM TBAADM.GAM a, TBAADM.LAM b,TBAADM.LRS c WHERE a.acid = b.acid  AND a.acid = c.acid AND b.acid = c.acid  ");
        query.append("AND FORACID= ?");
        String sql = query.toString();
        try {
            CustomerLoanGuarantorMapper rowMapper = new CustomerLoanGuarantorMapper();
            Object[] params = new Object[] { loanNo };
            CustomerLoanGuarantor account = jdbcTemplate.queryForObject(sql, rowMapper, params);
            accountList.add(account);
            return accountList;
        } catch (Exception ex) {
            log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
            return null;
        }
    }
}
