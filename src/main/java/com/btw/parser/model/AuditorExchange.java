package com.btw.parser.model;

import java.io.Serializable;

/**
 * Created by ydc on 2020/9/13.
 */
public class AuditorExchange implements Serializable{

    private String balMonth;

    private String cnjTicketNo;

    private String issueDate;

    private String cnjNo;

    private String orgTicketNo;

    private String exchangeStatus;

    private String orgIssueDate;

    private String source;

    private String oriSource;

    public void setBalMonth(String balMonth) {
        this.balMonth = balMonth;
    }

    public String getBalMonth() {
        return balMonth;
    }

    public void setCnjTicketNo(String cnjTicketNo) {
        this.cnjTicketNo = cnjTicketNo;
    }

    public String getCnjTicketNo() {
        return cnjTicketNo;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setCnjNo(String cnjNo) {
        this.cnjNo = cnjNo;
    }

    public String getCnjNo() {
        return cnjNo;
    }

    public void setOrgTicketNo(String orgTicketNo) {
        this.orgTicketNo = orgTicketNo;
    }

    public String getOrgTicketNo() {
        return orgTicketNo;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setOrgIssueDate(String orgIssueDate) {
        this.orgIssueDate = orgIssueDate;
    }

    public String getOrgIssueDate() {
        return orgIssueDate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setOriSource(String oriSource) {
        this.oriSource = oriSource;
    }

    public String getOriSource() {
        return oriSource;
    }

    public AuditorExchange test() {
        this.balMonth = "";
        this.cnjTicketNo = "";
        this.issueDate = "";
        this.cnjNo = "";
        this.orgTicketNo = "";
        this.exchangeStatus = "";
        this.orgIssueDate = "";;
        this.source = "";
        this.oriSource = "";
        return this;
    }
}
