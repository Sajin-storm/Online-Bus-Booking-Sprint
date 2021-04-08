package com.cg.OnlineBusBooking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.Feedback;
import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.exceptions.UserAlreadyExistException;
import com.cg.OnlineBusBooking.exceptions.UserNotFoundException;
import com.cg.OnlineBusBooking.repositories.IBookingRepository;
import com.cg.OnlineBusBooking.repositories.IFeedbackRepository;
import com.cg.OnlineBusBooking.repositories.IUserRepository;
import com.cg.OnlineBusBooking.serviceinterfaces.IUserService;

//Code start - By Sagar KC

@Service
public class UserServiceImpl implements IUserService {

	//Dependency injections of required repositories
	
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	IFeedbackRepository feedbackRepository;
	
	//Method to add a user
	@Override
	public void addUser(User user) {
		Optional<User> u = userRepository.findByUsername(user.getUsername());
		if(u.isPresent()) {
			throw new UserAlreadyExistException("User already exists!!!");
		} else {
			userRepository.save(user);
		}
	}
	
	//Method to delete a user
	@Override
	public void deleteUser(String username) {
		User newUser = null;
		Optional<User> u =userRepository.findByUsername(username);
		List<Booking> b =bookingRepository.findByUserUsername(username);
		for (Booking b1 : b) {
			b1.setUser(null);
		}
		List<Feedback> f =feedbackRepository.findByUserUsername(username);
		for (Feedback f1 : f) {
			f1.setUser(null);
		}
		if(u.isPresent()) {
			newUser = u.get();
			
			userRepository.delete(newUser);
		} else {
			throw new UserNotFoundException("User with username "+username+" does not exist");
		}
	}
	
	//Method to update a users password
	@Override
	public void updateUser(String username, String password) {
		User newUser = null;
		Optional<User> u =userRepository.findByUsername(username);
		if(u.isPresent()) {
			newUser = u.get();
			newUser.setPassword(password);
		} else {
			throw new UserNotFoundException("User with username "+username+" does not exist");
		}
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
//Code end - By Sagar KC
	
	@Override
	public User findByUsername(String username) {
		User newUser = null;
		Optional<User> u =userRepository.findByUsername(username);
		if(u.isPresent()) {
			newUser = u.get();
		} else {
			throw new UserNotFoundException("User not found!!!");
		}
		return newUser;
	}

	public boolean signIn(String username, String password){
		boolean result = false;
		User newUser = null;
		Optional<User> u = userRepository.findByUsername(username);
		if(u.isPresent()){
			newUser = u.get();
		} else {
			throw new UserAlreadyExistException("User with Username: "+username+" not found!!!");
		}

		if(password == newUser.getPassword() ){
			result = true;
			throw new UserNotFoundException("Password doesn't match Try again!!!");
		} 
		return result;

	}
}

