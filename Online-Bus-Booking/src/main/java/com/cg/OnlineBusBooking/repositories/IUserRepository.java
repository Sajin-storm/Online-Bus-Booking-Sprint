package com.cg.OnlineBusBooking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.OnlineBusBooking.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User,Integer>{

	//Repository methods to be used for fetching data
	
	public Optional<User> findByUsername(String username);


}
