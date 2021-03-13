package com.cg.OnlineBusBooking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;

//Code start - By All	

//Entity Creation with annotation
@Entity
public class BusRoute {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Route name")
	private String routeName;
	@ApiModelProperty(notes = "Source location")
	private String source;
	@ApiModelProperty(notes = "Destination location")
	private String destination;
	
	//bidirectional mapping to Bus table
	@OneToMany(mappedBy = "busRoute", cascade = {CascadeType.ALL})
	@ApiModelProperty(notes = "Bus for the Bus Route [Bus ID]")
	List<Bus> bus;
	
	//Non-parameterized constructor
	public BusRoute() {
		super();
	}

	//Parameterized constructor
	public BusRoute(String routeName, String source, String destination) {
		super();
		this.routeName = routeName;
		this.source = source;
		this.destination = destination;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
//Code end - By All		
	
}
