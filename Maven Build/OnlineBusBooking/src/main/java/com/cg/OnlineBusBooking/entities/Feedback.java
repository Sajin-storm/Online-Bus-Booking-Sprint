package com.cg.OnlineBusBooking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

//Code start - By Dhavala B

//Entity Creation with annotation
@Entity
public class Feedback {
	
	//Primary key field
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	//fields (or) columns
	@ApiModelProperty(notes = "Rating out of 5")
	private int rating;
	@ApiModelProperty(notes = "Comment about the journey")
	private String comment;
	@ApiModelProperty(notes = "Username")
	private String username;
	@ApiModelProperty(notes = "Route name")
	private String routeName;
	
	//Unidirectional mapping to User table
	@ManyToOne(cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "User for the Feedback [User ID]")
	User user;
	
	//Unidirectional mapping to BusOperator table
	@ManyToOne(cascade = CascadeType.ALL)
	@ApiModelProperty(notes = "Feedback given to Bus Operator [BusOperator ID]")
	BusOperator busOperator;
	
	//Non-parameterized constructor
	public Feedback() {
		super();
	}

	//Parameterized constructor
	public Feedback(int rating, String comment, String username, String routeName,User user,BusOperator busOperator) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.username = username;
		this.routeName = routeName;
		this.user = user;
		this.busOperator=busOperator;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BusOperator getBusOperator() {
		return busOperator;
	}
	public void setBusOperator(BusOperator busOperator) {
		this.busOperator = busOperator;
	}
	
//Code end - By Dhavala B	
	
}
