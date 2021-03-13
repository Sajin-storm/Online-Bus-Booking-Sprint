package com.cg.OnlineBusBooking.entities;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

//Code start - By Saurabh Dadhich & Sidharth Menon

//Entity Creation with annotation
@Entity
public class BusOperator {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Username for Bus Operator")
	private String busOperatorUsername;
	@ApiModelProperty(notes = "password for Bus Operator")
	private String password;
	@ApiModelProperty(notes = "List of case numbers for Bus Operator")
	private ArrayList<Integer> caseNumber;
	
	//Unidirectional mapping to Bus table
	@ManyToOne(cascade = CascadeType.PERSIST)
	@ApiModelProperty(notes = "Bus for Bus Operator [Bus ID]")
	Bus bus;
	
	//Non-parameterized constructor
	public BusOperator() {
		super();
	}

	//Parameterized constructor
	public BusOperator(String busOperatorUsername, String password, ArrayList<Integer> caseNumber, Bus bus) {
		super();
		this.busOperatorUsername = busOperatorUsername;
		this.password = password;
		this.caseNumber = caseNumber;
		this.bus = bus;
	}
	
	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusOperatorUsername() {
		return busOperatorUsername;
	}
	public void setBusOperatorUsername(String busOperatorUsername) {
		this.busOperatorUsername = busOperatorUsername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Integer> getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(ArrayList<Integer> caseNumber) {
		this.caseNumber = caseNumber;
	}
	public Bus getBus() {
		return bus;
	}
	public void setBus(Bus bus) {
		this.bus = bus;
	}
	
//Code end - By Saurabh Dadhich & Sidharth Menon
	
}
