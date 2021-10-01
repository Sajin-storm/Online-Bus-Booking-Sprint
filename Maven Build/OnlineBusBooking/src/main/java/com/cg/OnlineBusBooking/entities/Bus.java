package com.cg.OnlineBusBooking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import io.swagger.annotations.ApiModelProperty;

//Code start - By All

//Entity Creation with annotation
@Entity
public class Bus {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OnDelete(action = OnDeleteAction.CASCADE)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Bus Number")
	private String busNumber;
	@ApiModelProperty(notes = "The total number of seats in bus")
	private int totalSeats;
	@ApiModelProperty(notes = "the fare for on ticket")
	private int fare;
	
	//bidirectional mapping to Bus table
	@OneToMany(mappedBy = "bus", cascade = {CascadeType.ALL})
	@ApiModelProperty(notes = "BusOperator for Bus [BusOperator ID]")
	List<BusOperator> busOperator;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "bus_operator")
//	BusOperator busOperator;
	
	//bidirectional mapping to BusRoute table
	@ManyToOne
	@JoinColumn(name = "busRoute")
	@ApiModelProperty(notes = "BusRoute for Bus [BusRoute ID]")
	BusRoute busRoute;

	//Non-parameterized constructor
	public Bus() {
		super();
	}

	//Parameterized constructor
	public Bus(String busNumber, int totalSeats, int fare) {
		super();
		this.busNumber = busNumber;
		this.totalSeats = totalSeats;
		this.fare = fare;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public BusRoute getBusRoute() {
		return busRoute;
	}
	public void setBusRoute(BusRoute busRoute) {
		this.busRoute = busRoute;
	}

//	public List<BusOperator> getBusOperator() {
//		return busOperator;
//	}

	public void setBusOperator(List<BusOperator> busOperator) {
		this.busOperator = busOperator;
	}
	
//Code end - By All	
	
}
