package com.wemabank.vendorapi.service;

import com.wemabank.vendorapi.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class XMLService {

    public List<Transaction> parseCourse(String inputXML) {

        Transaction transact = null;

        //create an empty list for Transaction
        List<Transaction> trans = new ArrayList<>();
        try {
            // returns XML response
            String URL = inputXML;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);

            // normalize XML response
            doc.getDocumentElement().normalize();

            //read students list
            NodeList nodeList = doc.getElementsByTagName("ROW");

            //loop all available student nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    //read course details first
                    transact = new Transaction(
                            new BigDecimal(elem.getElementsByTagName("MIN_CREDIT_BALANCE").item(0).getTextContent()),
                            new BigDecimal(elem.getElementsByTagName("MAX_CREDIT_BALANCE").item(0).getTextContent()),
                            new BigDecimal(elem.getElementsByTagName("MIN_DEBIT_BALANCE").item(0).getTextContent()),
                            new BigDecimal(elem.getElementsByTagName("MAX_DEBIT_BALANCE").item(0).getTextContent()),
                            new BigDecimal(elem.getElementsByTagName("TOTAL_CREDIT").item(0).getTextContent()),
                            new BigDecimal(elem.getElementsByTagName("TOTAL_DEBIT").item(0).getTextContent()),
                            elem.getElementsByTagName("FORACID").item(0).getTextContent(),
                            elem.getElementsByTagName("CIF_ID").item(0).getTextContent(),
                            elem.getElementsByTagName("CUST_ID").item(0).getTextContent(),
                            elem.getElementsByTagName("SCHM_CODE").item(0).getTextContent(),
                            elem.getElementsByTagName("SCHM_TYPE").item(0).getTextContent(),
                            elem.getElementsByTagName("S_MONTH").item(0).getTextContent(),
                            elem.getElementsByTagName("S_YEAR").item(0).getTextContent());
                    trans.add(transact);
                }}
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return trans;
    }
}
