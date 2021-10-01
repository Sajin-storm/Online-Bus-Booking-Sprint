package com.cg.OnlineBusBooking.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModelProperty;

//Code start - By All

//Entity Creation with annotation
@Entity
public class BusOperatorRequest {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Case number for Bus Operator Request")
	private int caseNumber;
	@ApiModelProperty(notes = "Username for Bus Operator")
	private String busOperatorUsername;
	@ApiModelProperty(notes = "Request message of Bus Operator")
	private String requestMsg;
	@ApiModelProperty(notes = "Requesting for")
	private String requestFor;
	@ApiModelProperty(notes = "Request status")
	private boolean status;
	@ApiModelProperty(notes = "Date of request [YYYY-MM-DD]")
	private LocalDate requestDate;
	
	//Unidirectional mapping to Bus table
	@OneToOne(cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "Bus for Bus Operator Request [Bus ID]")
	Bus bus;
	
	//Unidirectional mapping to BusOperator table
	@ManyToOne(cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "BusOperator for Bus Operator Request [BusOperator ID]")
	BusOperator busOperator;
	
	//Non-parameterized constructor
	public BusOperatorRequest() {
		super();
	}

	//Parameterized constructor
	public BusOperatorRequest(int caseNumber, String busOperatorUsername, String requestMsg, String requestFor,
			boolean status, LocalDate requestDate,Bus bus,BusOperator busOperator) {
		super();
		this.caseNumber = caseNumber;
		this.busOperatorUsername = busOperatorUsername;
		this.requestMsg = requestMsg;
		this.requestFor = requestFor;
		this.status = status;
		this.requestDate = requestDate;
		this.bus=bus;
		this.busOperator=busOperator;
	}

	//Getters and Setters
	public BusOperator getBusOperator() {
		return busOperator;
	}
	public void setBusOperator(BusOperator busOperator) {
		this.busOperator = busOperator;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(int caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getBusOperatorUsername() {
		return busOperatorUsername;
	}
	public void setBusOperatorUsername(String busOperatorUsername) {
		this.busOperatorUsername = busOperatorUsername;
	}
	public String getRequestMsg() {
		return requestMsg;
	}
	public void setRequestMsg(String requestMsg) {
		this.requestMsg = requestMsg;
	}
	public String getRequestFor() {
		return requestFor;
	}
	public void setRequestFor(String requestFor) {
		this.requestFor = requestFor;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public LocalDate getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(LocalDate requestDate) {
		this.requestDate = requestDate;
	}
	
//Code end - By All	
	
}
