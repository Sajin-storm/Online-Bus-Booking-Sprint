package com.cg.OnlineBusBooking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.OnlineBusBooking.entities.BusOperator;
import com.cg.OnlineBusBooking.entities.Feedback;
import com.cg.OnlineBusBooking.serviceinterfaces.IFeedbackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

//Code start - By Dhavala B

@RestController
@RequestMapping(path = "/api/v1/feedbacks") //URL specification before every method
@Api(value = "Feedback", tags = { "FeedbackAPI" })
public class FeedbackController {
	
	//Dependency Injection
	@Autowired
	IFeedbackService feedbackService;
	
	/**
	 * This method is for adding a feedback
	 * 
	 * @param Feedback
	 */
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a feedback", response = Feedback.class)
	public Feedback addFeedback(@RequestBody Feedback feedback) {
		return feedbackService.addFeedback(feedback);
	}
	
	/**
	 * This method is to view all feedbacks of a Bus Operator for certain Bus Route
	 * 
	 * @param String, BusOperator
	 * @return List<Feedback>
	 * @throws NotFoundException
	 */
	@GetMapping("/routename/{routeName}/")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "View all feedbacks of Bus Operator for certain Bus Route", response = Feedback.class)
	public List<Feedback> viewAllFeedbacks(@PathVariable String routeName, @RequestBody BusOperator busOperator){
		return feedbackService.viewAllFeedbacks(routeName, busOperator);
	}
	
	/**
	 * This method is to view all feedbacks of a Bus Operator
	 * 
	 * @param BusOperator
	 * @return List<Feedback>
	 * @throws NotFoundException
	 */
	@GetMapping("/busoperator/")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "View all feedbacks of Bus Operator", response = Feedback.class)
	public List<Feedback> viewAllFeedbacks(@RequestBody BusOperator busOperator){
		return feedbackService.viewAllFeedbacks(busOperator);
	}
	
	//Code end - By Dhavala B

}
