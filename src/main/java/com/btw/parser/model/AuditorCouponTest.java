package com.btw.parser.model;

import java.io.Serializable;

public class AuditorCouponTest implements Serializable {

    private String ticketNo;

    private String salesCurrencyCode;

    private String routing;

    private String issueDate;

    private String dDate;

    private String aDate;

    private String clazz;

    private String paxType;

    private double fare;

    private String etType;

    private String salesType;

    private String salesStation;

    private String gdsSystem;

    private String salesSource;

    private String agentIataNo;

    private String itaxChecked;

    private String operatingCarrier;

    private String oriSource;

    private String filler1;

    private String ticketUnionNo;

    private String dTime;

    private String aTime;

    private String departureDate;

    private String arriveDate;

    private String eRFlag;

    private String prorateFlag;

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setSalesCurrencyCode(String salesCurrencyCode) {
        this.salesCurrencyCode = salesCurrencyCode;
    }

    public String getSalesCurrencyCode() {
        return salesCurrencyCode;
    }

    public void setRouting(String routing) {
        this.routing = routing;
    }

    public String getRouting() {
        return routing;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getDDate() {
        return dDate;
    }

    public void setADate(String aDate) {
        this.aDate = aDate;
    }

    public String getADate() {
        return aDate;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }

    public void setPaxType(String paxType) {
        this.paxType = paxType;
    }

    public String getPaxType() {
        return paxType;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public Double getFare() {
        return fare;
    }

    public void setEtType(String etType) {
        this.etType = etType;
    }

    public String getEtType() {
        return etType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesStation(String salesStation) {
        this.salesStation = salesStation;
    }

    public String getSalesStation() {
        return salesStation;
    }

    public void setGdsSystem(String gdsSystem) {
        this.gdsSystem = gdsSystem;
    }

    public String getGdsSystem() {
        return gdsSystem;
    }

    public void setSalesSource(String salesSource) {
        this.salesSource = salesSource;
    }

    public String getSalesSource() {
        return salesSource;
    }

    public void setAgentIataNo(String agentIataNo) {
        this.agentIataNo = agentIataNo;
    }

    public String getAgentIataNo() {
        return agentIataNo;
    }

    public void setItaxChecked(String itaxChecked) {
        this.itaxChecked = itaxChecked;
    }

    public String getItaxChecked() {
        return itaxChecked;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOriSource(String oriSource) {
        this.oriSource = oriSource;
    }

    public String getOriSource() {
        return oriSource;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getFiller1() {
        return filler1;
    }

    public void setTicketUnionNo(String ticketUnionNo) {
        this.ticketUnionNo = ticketUnionNo;
    }

    public String getTicketUnionNo() {
        return ticketUnionNo;
    }

    public void setDTime(String dTime) {
        this.dTime = dTime;
    }

    public String getDTime() {
        return dTime;
    }

    public void setATime(String aTime) {
        this.aTime = aTime;
    }

    public String getATime() {
        return aTime;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setERFlag(String eRFlag) {
        this.eRFlag = eRFlag;
    }

    public String getERFlag() {
        return eRFlag;
    }

    public void setProrateFlag(String prorateFlag) {
        this.prorateFlag = prorateFlag;
    }

    public String getProrateFlag() {
        return prorateFlag;
    }

    public AuditorCouponTest test(){
        this.ticketNo="";
        this.salesCurrencyCode="";
        this.routing="";
        this.issueDate="";
        this.dDate="";
        this.aDate="";
        this.clazz="";
        this.paxType="";
        this.fare=0;
        this.etType="";
        this.salesType="";
        this.salesStation="";
        this.gdsSystem="";
        this.salesSource="";
        this.agentIataNo="";
        this.itaxChecked="";
        this.operatingCarrier="";
        this.oriSource="";
        this.filler1="";
        this.ticketUnionNo="";
        this.dTime="";
        this.aTime="";
        this.departureDate="";
        this.arriveDate="";
        this.eRFlag="";
        this.prorateFlag="";
        return this;
    }

}
