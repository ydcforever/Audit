package com.btw.parser.model;

import java.io.Serializable;

public class AuditorCouponTaxTest implements Serializable {

    private String ticketNo;

    private String issueDate;

    private String taxCode;

    private String taxDefinition;

    private String salesCurrencyCode;

    private double taxAmountReceived;

    private double taxAmountDue;

    private double diffAmount;

    private Integer lineNo;

    private double diffPrecent;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxDefinition() {
        return taxDefinition;
    }

    public void setTaxDefinition(String taxDefinition) {
        this.taxDefinition = taxDefinition;
    }

    public String getSalesCurrencyCode() {
        return salesCurrencyCode;
    }

    public void setSalesCurrencyCode(String salesCurrencyCode) {
        this.salesCurrencyCode = salesCurrencyCode;
    }

    public double getTaxAmountReceived() {
        return taxAmountReceived;
    }

    public void setTaxAmountReceived(double taxAmountReceived) {
        this.taxAmountReceived = taxAmountReceived;
    }

    public double getTaxAmountDue() {
        return taxAmountDue;
    }

    public void setTaxAmountDue(double taxAmountDue) {
        this.taxAmountDue = taxAmountDue;
    }

    public double getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(double diffAmount) {
        this.diffAmount = diffAmount;
    }

    public double getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public double getDiffPrecent() {
        return diffPrecent;
    }

    public void setDiffPrecent(double diffPrecent) {
        this.diffPrecent = diffPrecent;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public AuditorCouponTaxTest test(){
        this.ticketNo="";
        this.taxCode="";
        this.issueDate = "";
        this.taxDefinition="";
        this.salesCurrencyCode="";
        this.taxAmountReceived=0;
        this.taxAmountDue=0;
        this.diffAmount=0;
        this.lineNo=0;
        this.diffPrecent=0;
        return this;
    }
}
