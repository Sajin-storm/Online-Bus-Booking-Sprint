package com.cg.OnlineBusBooking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.OnlineBusBooking.entities.BusRoute;

public interface IBusRouteRepository extends JpaRepository<BusRoute, Integer>{

	//Repository methods to be used for fetching data
	
	BusRoute findByBusBusNumber(String busNumber);

}
