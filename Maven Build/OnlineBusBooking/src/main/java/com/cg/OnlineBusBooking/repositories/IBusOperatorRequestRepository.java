package com.cg.OnlineBusBooking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.OnlineBusBooking.entities.BusOperatorRequest;

@Repository
public interface IBusOperatorRequestRepository extends JpaRepository<BusOperatorRequest,Integer>{

	List<BusOperatorRequest> findByBusBusNumber(String busNumber);

}
