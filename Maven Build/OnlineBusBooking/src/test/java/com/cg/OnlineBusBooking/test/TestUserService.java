package com.cg.OnlineBusBooking.test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.serviceinterfaces.IUserService;

@SpringBootTest
class TestUserService {
	
	@Autowired
	IUserService userService;
	
	@Test
	void testAddUser() {
		User user = new User();
		user.setUsername("Ramesh");
		user.setPassword("Ramesh123");
		userService.addUser(user);
	}

	@Test
	void testDeleteUser() {
		userService.deleteUser("Ravi");
	}

	@Test
	void testUpdateUser() {
		userService.updateUser("Ravi","Ravi567");
	}

	@Test
	void testGetAllUsers() {
		fail("Not yet implemented");
	}

}
