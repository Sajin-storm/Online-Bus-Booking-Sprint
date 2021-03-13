package com.cg.OnlineBusBooking.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.exceptions.UserNotFoundException;
import com.cg.OnlineBusBooking.serviceinterfaces.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//Code start - By Sagar KC

@RestController
@RequestMapping(path = "/api/v1/users") //URL specification before every method
@Api(value = "User", tags = { "UserAPI" })
public class UserController {
	
	//Dependency Injection
	@Autowired
	IUserService userService;
	
	/**
	 * This method is for adding a user
	 * 
	 * @param User
	 * @throws UserAlreadyExistsException
	 */
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Add a user", response = User.class)
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	/**
	 * This method is to delete a user
	 * 
	 * @param String
	 * @throws UserNotFoundException
	 */
	@DeleteMapping("/delete/{username}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a user", response = User.class)
	public void deleteUser(@PathVariable("username") String username) {
		userService.deleteUser(username);
	}
	
	/**
	 * This method is to update a users password
	 * 
	 * @param String, String
	 * @throws UserNotFoundException
	 */
	@PutMapping("/update/{username}:{password}")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Update a user passwoord", notes = "Provide old and new password", response = User.class)
	public void updateUser(@PathVariable("username") String username, @PathVariable("password") String password) {
		userService.updateUser(username, password);
	}
	
	//Code end - By Sagar KC
	
}
