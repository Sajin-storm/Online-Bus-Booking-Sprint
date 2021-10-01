package com.cg.OnlineBusBooking.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.OnlineBusBooking.entities.AdminUser;
import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.Bus;
import com.cg.OnlineBusBooking.entities.BusOperator;
import com.cg.OnlineBusBooking.entities.BusOperatorRequest;
import com.cg.OnlineBusBooking.entities.BusRoute;
import com.cg.OnlineBusBooking.exceptions.BusDoesnotExistException;
import com.cg.OnlineBusBooking.repositories.IAdminRepository;
import com.cg.OnlineBusBooking.repositories.IBookingRepository;
import com.cg.OnlineBusBooking.repositories.IBusOperatorRepository;
import com.cg.OnlineBusBooking.repositories.IBusOperatorRequestRepository;
import com.cg.OnlineBusBooking.repositories.IBusRepository;
import com.cg.OnlineBusBooking.repositories.IBusRouteRepository;
import com.cg.OnlineBusBooking.repositories.IFeedbackRepository;
import com.cg.OnlineBusBooking.serviceinterfaces.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService{
	
	//Dependency injections of required repositories
	
	@Autowired
	IAdminRepository adminRepository;
	
	@Autowired
	IBusOperatorRepository busOperatorRepository;
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	IBusRepository busRepository;
	
	@Autowired
	IBusOperatorRequestRepository busOperatorRequestRepository;
	
	@Autowired
	IBusRouteRepository busRouteRepository;
	
	@Autowired
	IFeedbackRepository feedbackRepository;
	
	//Code start - By Sajin S
	
	//Method to find all Bus Operators
	@Override
	public List<BusOperator> getAllBusOperator(){
		return busOperatorRepository.findAll();
	}
	
	//Method to find all Bus Operators of a specific bus route
	@Override
	public List<BusOperator> getAllBusOperatorByRoute(String route){
		List<BusOperator> busOperator = busOperatorRepository.findByBusBusRouteRouteName(route);
		return busOperator;
	}
	
	//Code end - By Sajin S
	
	//Code start - By Sagar KC
	
	//Method to update the journey duration of a booking
	@Override
	public void updateBusTime(long bookingId, LocalTime startTime, LocalTime endTime) {
		Optional<Booking> b = bookingRepository.findByBookingId(bookingId);
		Booking booking = null;
		if(b.isPresent()) {
			booking = b.get();
		}
		booking.setJourneyStartTime(startTime);
		booking.setJourneyEndTime(endTime);
	}
	
	//Method to update the bus route
	@Override
	public void updateBusRoute(Bus bus, String route) {
		BusRoute b = busRouteRepository.findByBusBusNumber(bus.getBusNumber());
		b.setRouteName(route);
	}
	
	//Code end - By Sagar KC
	
	//Code start - By Dhavala B
	
	//Method to update the bus far of a specific bus
	@Override
	public void updateBusFare(Bus bus, int fare) {
		Bus b = busRepository.findByBusNumber(bus.getBusNumber());
		b.setFare(fare);
	}
	
	//Method to find all bus operator requests
	@Override
	public List<BusOperatorRequest> getAllBusOperatorsRequest(){
		List<BusOperatorRequest> busOperatorRequest = busOperatorRequestRepository.findAll();
		return busOperatorRequest;
	}
	
	//Code end - By Dhavala B
	
	//Code start - By Saurabh Dadhich
	
	//Method to delete a bus
	@Override
	public void deleteBus(Bus bus) {
		Bus b = busRepository.findByBusNumber(bus.getBusNumber());
		if(b != null) {
			List<Booking> booking = bookingRepository.findByBusBusNumber(b.getBusNumber());
			for(Booking book : booking) {
				book.setBus(null);
				//bookingRepository.delete(book);	
			}
			List<BusOperator> busOperator = busOperatorRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperator busOp : busOperator) {
				busOp.setBus(null);
			}
			List<BusOperatorRequest> busOperatorRequest = busOperatorRequestRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperatorRequest busOpR : busOperatorRequest) {
				busOpR.setBus(null);
				//busOperatorRequestRepository.delete(busOpR);	
			}
			
			
//			List<Feedback> feedback = feedbackRepository.
			b.setBusRoute(null);
			b.setBusOperator(null);
			busRepository.delete(b);
		} else {
			throw new BusDoesnotExistException("Bus does not exist!");
		}
	}
	
	//Code end - By Saurabh Dadhich
	
	//Code start - By Sidharth Menon
	
	//Method to delete a bus by Bus Operator
	@Override
	public void deleteBusByOperator(String busOperatorUsername) {
		Bus b = busRepository.findByBusOperatorBusOperatorUsername(busOperatorUsername);
		if(b != null) {
			List<Booking> booking = bookingRepository.findByBusBusNumber(b.getBusNumber());
			for(Booking book : booking) {
				book.setBus(null);
				//bookingRepository.delete(book);	
			}
			List<BusOperator> busOperator = busOperatorRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperator busOp : busOperator) {
				busOp.setBus(null);
			}
			List<BusOperatorRequest> busOperatorRequest = busOperatorRequestRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperatorRequest busOpR : busOperatorRequest) {
				busOpR.setBus(null);
				//busOperatorRequestRepository.delete(busOpR);	
			}
			b.setBusOperator(null);
			b.setBusRoute(null);
			busRepository.delete(b);
		} else {
			throw new BusDoesnotExistException("BusOperator does not exist!");
		}
	}
	
	//Code end - By Sidharth Menon
	
	//Code start - By Sadathulla Shariff
	
	//Method to get revenue generated on certain date
	@Override
	public int getRevenueByDate(LocalDate date) {
		int i = 0;
		List<Booking> booking = bookingRepository.findByDate(date);
		for (Booking b : booking) {
			i=i+b.getAmountPaid();
		}
		return i;
	}
	
	//Method to get revenue generated from specific Bus route
	@Override
	public int getRevenueByBusRoute(String route) {
		int i = 0;
		List<Booking> booking = bookingRepository.findByBusRouteRouteName(route);
		for (Booking b: booking) {
			i=i+b.getAmountPaid();
		}
		return i;
	}
	
	//Code end - By Sadathulla Shariff
	
	//Code start - By Saurabh Dadhich
	
	//Method to get revenue generated from specific Bus Operator
	@Override
	public int getRevenueByBusOperator(String busOperatorUsername) {
		int i = 0;
		List<Booking> booking = bookingRepository.findByBusBusOperatorBusOperatorUsername(busOperatorUsername);
		for (Booking b: booking) {
			System.out.println(b);
			i=i+b.getAmountPaid();
		}
		return i;
	}
	
	//Code end - By Saurabh Dadhich
	
	
	@Override
	public List<Bus> getAllBus(){
		return busRepository.findAll();
	}
	
	
	@Override
	public void deleteBus(String busNumber) {
		Bus b = busRepository.findByBusNumber(busNumber);
		if(b != null) {
			List<Booking> booking = bookingRepository.findByBusBusNumber(b.getBusNumber());
			for(Booking book : booking) {
				book.setBus(null);
				//bookingRepository.delete(book);	
			}
			List<BusOperator> busOperator = busOperatorRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperator busOp : busOperator) {
				busOp.setBus(null);
			}
			List<BusOperatorRequest> busOperatorRequest = busOperatorRequestRepository.findByBusBusNumber(b.getBusNumber());
			for(BusOperatorRequest busOpR : busOperatorRequest) {
				busOpR.setBus(null);
				//busOperatorRequestRepository.delete(busOpR);	
			}
			
			
//			List<Feedback> feedback = feedbackRepository.
			b.setBusRoute(null);
			b.setBusOperator(null);
			busRepository.delete(b);
		} else {
			throw new BusDoesnotExistException("Bus does not exist!");
		}
	}

	@Override
	public List<AdminUser> viewAllAdmin(){
		return adminRepository.findAll();
	}
	
	@Override
	public void addAdmin(AdminUser admin){
		AdminUser adminUser = adminRepository.findByAdminUsername(admin.getAdminUsername());
		if(adminUser == null){
			adminRepository.save(admin);
		}
		else {
			throw new BusDoesnotExistException("Admin already exists!");
		}

	}

	@Override
	public void updateAdminPassword(String adminUsername, String password){
		AdminUser adminUser = adminRepository.findByAdminUsername(adminUsername);
		if(adminUser != null){
			adminUser.setPassword(password);
		} else {
			throw new BusDoesnotExistException("Admin does not exists!");
		}

	}

	@Override
	public void deleteAdmin(String adminUsername){
		AdminUser adminUser = adminRepository.findByAdminUsername(adminUsername);
		if(adminUser != null){
			adminRepository.delete(adminUser);
		} else {
			throw new BusDoesnotExistException("Admin does not exists!");
		}
	}

	@Override
	public void adminSignIn(String adminUsername, String password){
		AdminUser adminUser = adminRepository.findByAdminUsernameAndPassword(adminUsername,password);
		if (adminUser == null){
			throw new BusDoesnotExistException("Admin does not exists!");
		}
	}
		
	


}

