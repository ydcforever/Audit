package com.btw.parser.model;

import java.io.Serializable;

public class AuditorStatus implements Serializable {
    private String ticketNo;

    private String cnjTicketNo;

    private String issueDate;

    private String operation;

    private String couponStatus;

    private String oriSource;

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketNo() {
        return ticketNo;
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

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    public void setOriSource(String oriSource) {
        this.oriSource = oriSource;
    }

    public String getOriSource() {
        return oriSource;
    }

    public AuditorStatus test() {
        this.ticketNo = "";
        this.cnjTicketNo = "";
        this.issueDate = "";
        this.operation = "";
        this.couponStatus = "";
        this.oriSource = "";
        return this;
    }


}
