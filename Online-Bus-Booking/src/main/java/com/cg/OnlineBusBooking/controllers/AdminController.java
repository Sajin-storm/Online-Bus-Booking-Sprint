package com.cg.OnlineBusBooking.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.Bus;
import com.cg.OnlineBusBooking.entities.BusOperator;
import com.cg.OnlineBusBooking.entities.BusOperatorRequest;
import com.cg.OnlineBusBooking.serviceinterfaces.IAdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

//Code start - By All

@RestController
@RequestMapping(path = "api/v1/admin") //URL specification before every method
@Api(value = "Admin", tags = { "AdminAPI" })
public class AdminController {
	
	//Dependency Injection
	@Autowired
	IAdminService adminService;
	
	/**
	 * This method is for getting all bus operators
	 * 
	 * @return List<BusOperator>
	 * @throws NotFoundException
	 */
	@GetMapping("/getall")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get all Bus Operators", response = BusOperator.class)
	public List<BusOperator> getAllBusOperator(){
		return adminService.getAllBusOperator();
	}
	
	/**
	 * This method is for getting all bus operators by route
	 * 
	 * @return List<BusOperator>
	 * @throws NotFoundException
	 */
	@GetMapping("/getbyroute/{route}")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get all Bus Operators by route", response = BusOperator.class)
	public List<BusOperator> getAllBusOperatorByRoute(@PathVariable("route")String route){
		return adminService.getAllBusOperatorByRoute(route);
	}
	
	/**
	 * This method is for getting all bus operator requests
	 * 
	 * @return List<BusOperatorRequest>
	 * @throws NotFoundException
	 */
	@GetMapping("/getrequest")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get all Bus Operator requests", response = BusOperatorRequest.class)
	public List<BusOperatorRequest> getAllBusOperatorsRequest(){
		return adminService.getAllBusOperatorsRequest();
	}
	
	/**
	 * This method is for updating the journey time of a booking
	 * 
	 * @param long, LocalDate, LocalDat
	 * @throws NotFoundException
	 */
	@PutMapping("/updatetime/{bookingId}/{startTime}/{endTime}")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Update bus time", notes = "Provide the booking ID, with start and end time", response = Booking.class)
	public void updateBusTime(@PathVariable("bookingId") long bookingId,@PathVariable("startTime")String startTime,@PathVariable("endTime") String endTime) {
		LocalTime startTime1 = LocalTime.parse(startTime);
		LocalTime endTime1 = LocalTime.parse(endTime);
		adminService.updateBusTime(bookingId, startTime1, endTime1);
	}
	
	/**
	 * This method is for updating the bus route of a bus
	 * 
	 * @param Bus, String
	 * @throws NotFoundException
	 */
	@PutMapping("/updatebusroute/{route}")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Update the bus route", notes = "Provide the bus with new route name", response = Booking.class)
	public void updateBusRoute(@RequestBody Bus bus,@PathVariable("route") String route) {
		adminService.updateBusRoute(bus, route);
	}
	
	/**
	 * This method is for updating the bus fare of a bus
	 * 
	 * @param Bus, int
	 * @throws NotFoundException
	 */
	@PutMapping("/updatebusfare/{fare}")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Update the bus fare", notes = "Provide bus along with new fare", response = Bus.class)
	public void updateBusFare(@RequestBody Bus bus,@PathVariable("fare") int fare) {
		adminService.updateBusFare(bus, fare);
	}
	
	/**
	 * This method is for deleting a bus
	 * 
	 * @param Bus
	 * @throws NotFoundException
	 */
	@DeleteMapping("/deletebus/")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a bus", response = Bus.class)
	public void deleteBus(@RequestBody Bus bus) {
		adminService.deleteBus(bus);
	}
	
	/**
	 * This method is for deleting a bus by the Bus Operator
	 * 
	 * @param String
	 * @throws NotFoundException
	 */
	@DeleteMapping("/deletebyoperator/{operator}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Delete a bus by operator", notes = "Provide Bus Operator name", response = Bus.class)
	public void deleteBusByOperator(@PathVariable("operator")String operator) {
		adminService.deleteBusByOperator(operator);
	}
	
	/**
	 * This method is for getting the revenue generated on a specific date
	 * 
	 * @param String
	 * @return int
	 * @throws NotFoundException
	 */
	@GetMapping("getrevenuebydate/{date}")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get revenuew by date", notes = "Provide date in YYYY-MM-DD format", response = Booking.class)
	public int getRevenueByDate(@PathVariable("date") String date) {
		LocalDate date1 = LocalDate.parse(date);
		return adminService.getRevenueByDate(date1);
	}
	
	/**
	 * This method is for getting the revenue generated by a specific bus route
	 * 
	 * @param String
	 * @return int
	 * @throws NotFoundException
	 */
	@GetMapping("/getrevenuebybusroute/{route}")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get revenue by route", notes = "Provide bus route", response = Booking.class)
	public int getRevenueByBusRoute(@PathVariable("route") String route) {
		return adminService.getRevenueByBusRoute(route);
	}
	
	/**
	 * This method is for getting the revenue generated by a specific bus operator
	 * 
	 * @param String
	 * @return int
	 * @throws NotFoundException
	 */
	@GetMapping("/getrevenuebybusoperator/{busOperatorUsername}")
	@ResponseStatus(HttpStatus.FOUND)
	@ApiOperation(value = "Get revenue by bus operator", notes = "Provide bus operator username", response = Booking.class)
	public int getRevenueByBusOperator(@PathVariable("busOperatorUsername") String busOperatorUsername) {
		return adminService.getRevenueByBusOperator(busOperatorUsername);
	}

	//Code end - By All	
	
}
