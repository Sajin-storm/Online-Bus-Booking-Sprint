package com.cg.OnlineBusBooking.serviceinterfaces;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cg.OnlineBusBooking.entities.Bus;
import com.cg.OnlineBusBooking.entities.BusOperator;
import com.cg.OnlineBusBooking.entities.BusOperatorRequest;

public interface IAdminService {
	
	//Service methods to be implemented by Service Implementation Class
	
	public List<BusOperator> getAllBusOperator();
	
	public List<BusOperator> getAllBusOperatorByRoute(String route);
	
	public List<BusOperatorRequest> getAllBusOperatorsRequest();
	
	public void updateBusTime(long bookingId,LocalTime startTime, LocalTime endTime);
	
	public void updateBusRoute(Bus bus, String route);
	
	public void updateBusFare(Bus bus, int fare);
	
	public void deleteBus(Bus bus);
	
	public void deleteBusByOperator(String operator);
	
	public int getRevenueByDate(LocalDate date);
	
	public int getRevenueByBusRoute(String route);
	
	public int getRevenueByBusOperator(String operator);
}
