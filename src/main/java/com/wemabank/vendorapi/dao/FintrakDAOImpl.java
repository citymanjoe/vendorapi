package com.wemabank.vendorapi.dao;

import com.wemabank.vendorapi.mapper.*;
import com.wemabank.vendorapi.model.*;
import com.wemabank.vendorapi.service.XMLService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.time.Month;
import java.time.Period;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        Transaction transact = null;
        StringBuilder query = new StringBuilder();

        //query.append("SELECT XMLTYPE(CustomSystem.out.println("Last date of the month: "+lastDayOfMonthDate);erTransaction(?,?,?)) XMLVALUE FROM dual");
        //query.append("SELECT MIN(NVL((CASE WHEN PART_TRAN_TYPE = 'C' THEN TRAN_AMT ELSE 0 END),0)) AS MIN_CREDIT_BALANCE,");
        //query.append("MIN(NVL((CASE WHEN PART_TRAN_TYPE = 'D' THEN TRAN_AMT ELSE 0 END),0)) AS MIN_DEBIT_BALANCE,");
        //query.append("MAX(NVL((CASE WHEN PART_TRAN_TYPE = 'C' THEN TRAN_AMT ELSE 0 END),0)) AS MAX_CREDIT_BALANCE,");
        //query.append("MAX(NVL((CASE WHEN PART_TRAN_TYPE = 'D' THEN TRAN_AMT ELSE 0 END),0)) AS MAX_DEBIT_BALANCE,");
        //query.append("FORACID,a.CUST_ID,CIF_ID,SCHM_CODE,SCHM_TYPE,");
        //query.append("SUM(NVL((CASE WHEN PART_TRAN_TYPE = 'C' THEN TRAN_AMT ELSE 0 END),0)) AS TOTAL_CREDIT,");
        //query.append("SUM(NVL((CASE WHEN PART_TRAN_TYPE = 'D' THEN TRAN_AMT ELSE 0 END),0)) AS TOTAL_DEBIT,");
        //query.append("EXTRACT(YEAR FROM (TO_DATE((?),'DD-MM-YYYY'))) AS S_YEAR,");
        //query.append("EXTRACT(MONTH FROM (TO_DATE((?),'DD-MM-YYYY'))) AS S_MONTH  ");
        //query.append("FROM tbaadm.gam a, tbaadm.htd b WHERE a.acid = b.acid ");
        //query.append("AND FORACID = ? AND TRAN_DATE BETWEEN TO_DATE((?),'DD-MM-YYYY') AND TO_DATE((?),'DD-MM-YYYY') ");
        //query.append("GROUP BY FORACID,a.CUST_ID,CIF_ID,SCHM_CODE,SCHM_TYPE ");
        query.append("SELECT MIN(DECODE(PART_TRAN_TYPE,'C',tran_amt,0)) AS MIN_CREDIT_BALANCE,");
        query.append("MIN(DECODE(PART_TRAN_TYPE,'D',tran_amt,0)) AS MIN_DEBIT_BALANCE,");
        query.append("MAX(DECODE(PART_TRAN_TYPE,'C',tran_amt,0)) AS MAX_CREDIT_BALANCE,");
        query.append("MAX(DECODE(PART_TRAN_TYPE,'D',tran_amt,0)) AS MAX_DEBIT_BALANCE,");
        query.append("FORACID,a.CUST_ID,CIF_ID,SCHM_CODE,SCHM_TYPE,");
        query.append("SUM(DECODE(PART_TRAN_TYPE,'C',tran_amt,0)) AS TOTAL_CREDIT,");
        query.append("SUM(DECODE(PART_TRAN_TYPE,'D',tran_amt,0)) AS TOTAL_DEBIT,");
        query.append("EXTRACT(YEAR FROM (TO_DATE((?),'DD-MM-YYYY'))) AS S_YEAR,");
        query.append("EXTRACT(MONTH FROM (TO_DATE((?),'DD-MM-YYYY'))) AS S_MONTH  ");
        query.append("FROM tbaadm.gam a, tbaadm.htd b WHERE a.acid = b.acid AND FORACID = ?  ");
        query.append("AND TRAN_DATE BETWEEN TO_DATE((?),'DD-MM-YYYY') AND TO_DATE((?),'DD-MM-YYYY') ");
        query.append("GROUP BY FORACID,a.CUST_ID,CIF_ID,SCHM_CODE,SCHM_TYPE");
        String sql = query.toString();

        LocalDate localStart = LocalDate.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDate localEnd = LocalDate.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
        Period age = Period.between(localStart, localEnd);
        int months = age.getMonths();
        System.out.println("The Number month: "+months);
        int z = 0;
        Date vDate = null;
        while (z <= months) {
            Date sDate;
            Date eDate = null;
            accountNo = accountNo;
            if(z == 0) {
                sDate = startDate;
                System.out.println("Start date of the month: " + sDate);
                LocalDate lastDayOfMonthDate = localStart.withDayOfMonth(localStart.getMonth().length(localStart.isLeapYear()));
                Date tDate = Date.from(lastDayOfMonthDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                System.out.println("Last date of the month: " + tDate);
                eDate = tDate;
            }else {
                LocalDate parsedDate = LocalDate.ofInstant(vDate.toInstant(), ZoneId.systemDefault());
                LocalDate addedDate = parsedDate.plusDays(1);
                sDate = Date.from(addedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                System.out.println("Start date of the month: " + sDate);
                LocalDate lastDayOfMonthDate = addedDate.withDayOfMonth(addedDate.getMonth().length(addedDate.isLeapYear()));
                Date tDate = Date.from(lastDayOfMonthDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
                System.out.println("Last date of the month: " + tDate);
                eDate = tDate;
            }
            if (eDate.compareTo(currentDate) < 0) {

                try {
                    TransactionMapper rowMapper = new TransactionMapper();
                    log.info("PROCESSING DATE: " + eDate + "|" + sDate);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String sqlCurrent = formatter.format(eDate);
                    String sqlStart = formatter.format(sDate);
                    log.info("PROCESSING CONVERT DATE: " + sqlCurrent + "|" + sqlStart);
                    formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    String stDate = formatter.format(sDate);
                    String enDate = formatter.format(eDate);
                    log.info("PROCESSING CONVERT DATE: " + enDate + "|" + stDate);
                    //Object[] params = new Object[]{sqlCurrent, sqlCurrent, accountNo, sqlStart, sqlCurrent};
                    Object[] params = new Object[]{enDate, enDate, accountNo, stDate, enDate};
                    transact = jdbcTemplate.queryForObject(sql, rowMapper, params);
                    transaction.add(transact);
                } catch (Exception ex) {
                    log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
                    return null;
                }
            }
            vDate = eDate;
            z = z + 1;
            System.out.println("Index Number : " + z);
        }
        return transaction;
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

	@Override
	public VaultCash getVaultCash(String branch) {
		VaultCash cash = new VaultCash();
		StringBuilder query = new StringBuilder();
        query.append("select  sum(clr_bal_amt) VaultBal, acct_crncy_code VaultCrncy from tbaadm.gam ");
        query.append("where GL_SUB_HEAD_CODE in ('10000','10003','10015') ");
        query.append("and bacid in ('1010000008','1010003001','1010000001','1010000001') ");
        query.append("and sol_id = '999' and clr_bal_amt != 0 and acct_crncy_code in ('NGN','USD','EUR','GBP') ");
        query.append("group by acct_crncy_code");
        String sql = query.toString();
        try {
        	VaultCashMapper rowMapper = new VaultCashMapper();
            Object[] params = new Object[] { branch };
            cash = jdbcTemplate.queryForObject(sql, rowMapper, params);
            return cash;
        } catch (Exception ex) {
            log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
            return null;
        }
	}

    @Override
    public CRMFinancial getCRMSourceFund(String cifId) {
        CRMFinancial financial = new CRMFinancial();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ORGKEY,SOURCE_OF_FUNDS,AVERAGE_ANNUALINCOME,NUMBEROF_EMPLOYEES ");
        query.append("FROM CRMUSER.CORPORATE, CRMUSER.FINANCIAL ");
        query.append("WHERE ORGKEY = CORP_KEY AND CORP_KEY IN (?) ");
        String sql = query.toString();
        try {
            CRMFinancialMapper rowMapper = new CRMFinancialMapper();
            Object[] params = new Object[] { cifId };
            financial = jdbcTemplate.queryForObject(sql, rowMapper, params);
            return financial;
        } catch (Exception ex) {
            log.error("An error Occured: Cause: {} \r\n Message: {}", ex.getCause(), ex.getMessage());
            return null;
        }
    }

    @Override
    public void postCRMSourceFund(CRMFinancial crm) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE CRMUSER.FINANCIAL SET NUMBEROF_EMPLOYEES = ? WHERE ORGKEY = ?");

        StringBuilder query = new StringBuilder();
        query.append("UPDATE CRMUSER.CORPORATE SET SOURCE_OF_FUNDS = ?, ");
        query.append("AVERAGE_ANNUALINCOME = ? WHERE CORP_KEY = ?");

        int records = 0;
        try {
            Object[] params1 = new Object[] { crm.getEmployeeNo(), crm.getCifId() };
            Object[] params2 = new Object[] { crm.getFundSource(), crm.getAnnualIncome(), crm.getCifId() };
            int x = jdbcTemplate.update(sql.toString(), params1);
            if (x == 1) {
                log.info("FINANCIAL TABLE RECORD UPDATED: " + crm.getCifId() + "|" + crm.getEmployeeNo());
                int z = jdbcTemplate.update(query.toString(), params2);
                log.info("CORPORATE TABLE RECORD UPDATED: " + crm.getCifId() + "|" + crm.getFundSource());
            }
        } catch (EmptyResultDataAccessException ex) {
            log.error(ex.getMessage());
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            log.error("history already exist");
        }
    }
}
