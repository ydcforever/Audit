package com.btw.parser.model;

import java.io.Serializable;

/**
 * Created by ydc on 2020/9/13.
 */
public class AuditorExchange implements Serializable{

    private String dateMonth;

    private String changeTicketNo;

    private String changeIssueDate;

    private String no;

    private String originTicketNo;

    private String correspondence;

    private String originIssueDate;

    private String salesSource;

    private String oriSource;

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setChangeTicketNo(String changeTicketNo) {
        this.changeTicketNo = changeTicketNo;
    }

    public String getChangeTicketNo() {
        return changeTicketNo;
    }

    public void setChangeIssueDate(String changeIssueDate) {
        this.changeIssueDate = changeIssueDate;
    }

    public String getChangeIssueDate() {
        return changeIssueDate;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

    public void setOriginTicketNo(String originTicketNo) {
        this.originTicketNo = originTicketNo;
    }

    public String getOriginTicketNo() {
        return originTicketNo;
    }

    public void setCorrespondence(String correspondence) {
        this.correspondence = correspondence;
    }

    public String getCorrespondence() {
        return correspondence;
    }

    public void setOriginIssueDate(String originIssueDate) {
        this.originIssueDate = originIssueDate;
    }

    public String getOriginIssueDate() {
        return originIssueDate;
    }

    public void setSalesSource(String salesSource) {
        this.salesSource = salesSource;
    }

    public String getSalesSource() {
        return salesSource;
    }

    public void setOriSource(String oriSource) {
        this.oriSource = oriSource;
    }

    public String getOriSource() {
        return oriSource;
    }

    public AuditorExchange test() {
        this.dateMonth = "";
        this.changeTicketNo = "";
        this.changeIssueDate = "";
        this.no = "";
        this.originTicketNo = "";
        this.correspondence = "";
        this.originIssueDate = "";
        this.salesSource = "";
        this.oriSource = "";
        return this;
    }
}
