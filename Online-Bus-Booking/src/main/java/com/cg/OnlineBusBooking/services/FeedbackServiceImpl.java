package com.cg.OnlineBusBooking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.OnlineBusBooking.entities.BusOperator;
import com.cg.OnlineBusBooking.entities.Feedback;
import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.exceptions.UserNotFoundException;
import com.cg.OnlineBusBooking.repositories.IBusOperatorRepository;
import com.cg.OnlineBusBooking.repositories.IFeedbackRepository;
import com.cg.OnlineBusBooking.repositories.IUserRepository;
import com.cg.OnlineBusBooking.serviceinterfaces.IFeedbackService;

//Code start - By Dhavala B

@Service
public class FeedbackServiceImpl implements IFeedbackService {
	
	//Dependency injections of required repositories
	
	@Autowired
	IFeedbackRepository feedbackRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IBusOperatorRepository busOperatorRepository;
	
	//Method to add a feedback
	//feedback has user id and busOp id 
	@Override
	public Feedback addFeedback(Feedback feedback) {
		Feedback f = null;
		Optional<User> user = userRepository.findByUsername(feedback.getUsername());
		if(user.isPresent()) {
			feedback.setUser(user.get());
			f = feedbackRepository.save(feedback);
		} else {
			throw new UserNotFoundException("User not found!!!");
		}
		return f;
	}
	
	//Method to view all feedbacks of a certain bus operator from specific bus route
	@Override
	public List<Feedback> viewAllFeedbacks(String routeName , BusOperator busOperator){
		List<Feedback> f1 = feedbackRepository.findByRouteNameAndBusOperatorBusOperatorUsername(routeName, busOperator.getBusOperatorUsername());
		return f1;
	}
	
	//Method to view all feedbacks of a certain bus operator
	@Override
	public List<Feedback> viewAllFeedbacks(BusOperator busOperator){
		List<Feedback> feedback = feedbackRepository.findByBusOperatorBusOperatorUsername(busOperator.getBusOperatorUsername());
		return feedback;
	}
	
	@Override
	public List<Feedback> getAllFeedbacks(){
		List<Feedback> feedback = feedbackRepository.findAll();
		return feedback;
	}

//Code end - By Dhavala B	
	
}
