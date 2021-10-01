package com.cg.OnlineBusBooking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

//Code start - By All	

//Entity Creation with annotation
@Entity
public class Passenger {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Username")
	private String username;
	@ApiModelProperty(notes = "First name")
	private String firstName;
	@ApiModelProperty(notes = "Last name")
	private String lastName;
	@ApiModelProperty(notes = "Phone number of user")
	private long phoneNumber;
	@ApiModelProperty(notes = "E-Mail of user")
	private String email;
	@ApiModelProperty(notes = "Unique document ID")
	private long identityDocNumber;
	@ApiModelProperty(notes = "Unique document name")
	private String identityDocName;
	
	//Unidirectional mapping to Booking table
	@ManyToOne
	@ApiModelProperty(notes = "Booking ID for the passenger [Booking ID]")
	Booking booking;  //many passengers for in 1 booking
	
	//Non-parameterized constructor
	public Passenger() {
		super();
	}

	//Parameterized constructor
	public Passenger(String username, String firstName, String lastName, long phoneNumber, String email,
			long identityDocNumber, String identityDocName, Booking booking) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.identityDocNumber = identityDocNumber;
		this.identityDocName = identityDocName;
		this.booking = booking;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getIdentityDocNumber() {
		return identityDocNumber;
	}
	public void setIdentityDocNumber(long identityDocNumber) {
		this.identityDocNumber = identityDocNumber;
	}
	public String getIdentityDocName() {
		return identityDocName;
	}
	public void setIdentityDocName(String identityDocName) {
		this.identityDocName = identityDocName;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
//Code end - By All		
	
}
