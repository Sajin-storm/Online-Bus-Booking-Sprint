package com.cg.OnlineBusBooking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.OnlineBusBooking.entities.Booking;

@Repository
public interface IBookingRepository extends JpaRepository<Booking,Integer>{

	//Repository methods to be used for fetching data
	
	public Optional<Booking> findById(int id);
	
	public List<Booking> findByUsername(String username);
	
	public List<Booking> findByDate(LocalDate date);
	
	public Optional<Booking> findByBookingId(long id);
	
	public List<Booking> findByBusNumber(String busNumber);
	
	public List<Booking> findBySource(String source);
	
	public List<Booking> findByDestination(String destination);
	
	public List<Booking> findByBusRouteRouteName(String routeName);
	
	public List<Booking> findByBusBusNumber(String busNumber);
	
	public List<Booking> findByBusBusOperatorBusOperatorUsername(String busOperatorUsername);
	
	public List<Booking> findByBusRouteRouteNameAndDate(String routeName, LocalDate date);

	public Optional<Booking> findByUsernameAndBusNumber(String username, String busNumber);

	public List<Booking> findByUserUsername(String username);

}
