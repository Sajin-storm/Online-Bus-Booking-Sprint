package com.cg.OnlineBusBooking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.OnlineBusBooking.entities.Feedback;
import com.cg.OnlineBusBooking.entities.User;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Integer>{

	//Repository methods to be used for fetching data
	
	public Optional<Feedback> findByUser(User user);

	public List<Feedback> findByRouteName(String routeName);

	public Feedback findByUsername(String username);

	public List<Feedback> findByUserUsername(String username);

	public List<Feedback> findByBusOperatorBusOperatorUsername(String busOperatorusername);

	public List<Feedback> findByRouteNameAndBusOperatorBusOperatorUsername(String routeName,
			String busOperatorUsername);

}
