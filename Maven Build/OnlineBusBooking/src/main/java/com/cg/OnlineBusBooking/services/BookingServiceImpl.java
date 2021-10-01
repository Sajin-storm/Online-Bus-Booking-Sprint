	package com.cg.OnlineBusBooking.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.Bus;
import com.cg.OnlineBusBooking.entities.Feedback;
import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.exceptions.BookingAlreadyExistException;
import com.cg.OnlineBusBooking.exceptions.BookingNotFoundException;
import com.cg.OnlineBusBooking.exceptions.UserNotFoundException;
import com.cg.OnlineBusBooking.repositories.IBookingRepository;
import com.cg.OnlineBusBooking.repositories.IBusOperatorRepository;
import com.cg.OnlineBusBooking.repositories.IBusRepository;
import com.cg.OnlineBusBooking.repositories.IFeedbackRepository;
import com.cg.OnlineBusBooking.repositories.IUserRepository;
import com.cg.OnlineBusBooking.serviceinterfaces.IBookingService;

@Service
@Transactional
public class BookingServiceImpl implements IBookingService {
	
	//Dependency injections of required repositories
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	IBusRepository busRepository;
	
	@Autowired
	IFeedbackRepository feedbackRepository;
	
	@Autowired
	IBusOperatorRepository busOperatorRepository;
	
	@Autowired
	IUserRepository userRepository;
	
	//Code start - By Sadathulla Shariff
	
	//getting all booking by date
	@Override
	public List<Booking> getAllBookingByDate(LocalDate date){
		List<Booking> booking = bookingRepository.findByDate(date);
		return booking;
	}
	
	//getting all bookings by Bus route
	@Override
	public List<Booking> getAllBookingByBusRoute(String routeName){
		List<Booking> booking = bookingRepository.findByBusRouteRouteName(routeName);
		return booking;
	}
	
	//Method to update a booking date by its ID
	@Override
	public boolean updateBookingDate(long bookingId,String date) {
		boolean result = false;
		Optional<Booking> b = bookingRepository.findByBookingId(bookingId);
		Booking b1 = null;
		if(b.isPresent()) {
			b1 = b.get();
			b1.setDate(LocalDate.parse(date));
			result = true;
		}
		return result;
	}
	
	//deleting a booking
	@Override
	public boolean deleteBooking (long bookingid) {
		Optional<Booking> booking = bookingRepository.findByBookingId(bookingid);
		Booking b = null;
		boolean result = false;	
		if (booking.isPresent()) {
			b=booking.get();
			b.setBus(null);
			b.setBusRoute(null);
			b.setUser(null);
			bookingRepository.delete(b);
			result = true;
		} else {
			throw new BookingNotFoundException("booking doesn't exist!!!");
		}
		return result;
	}
	
	//adding a feedback passing username , Booking Id and comment
	@Override
	public void addFeedback(String username, long bookingid, String comment) {
		Optional<Booking> booking = bookingRepository.findByBookingId(bookingid);
		Feedback feedback = feedbackRepository.findByUsername(username);
		Feedback f = null;
		Feedback f1 = new Feedback();
		Booking b = null;
		if(booking.isPresent()) {
			b = booking.get();
		}

		Optional<User> user = userRepository.findByUsername(username);
		User u = null;
		if(user.isPresent()) {
			u = user.get();
		}
		
		if(booking.isPresent() && feedback != null) {
			f = feedback;
			f.setComment(comment);
			feedbackRepository.save(f);
		}
		else {
			f1.setRouteName((b.getBusRoute()).getRouteName());
			f1.setComment(comment);
			f1.setUsername(username);
			f1.setUser(u);
			feedbackRepository.save(f1);
		}
	}
	
	//Code end - By Sadathulla Shariff
	
	
	//Code start - By Sajin S
	
	//addBooking a booking 
	 @Override
	 public long addBooking(Booking booking) {
		 Optional<Booking> b1 = bookingRepository.findByBookingId(booking.getBookingId());
		 Booking b2 = null;
		 Optional<User> u1 =userRepository.findByUsername(booking.getUsername());
		 User u2 = null;
		 if(u1.isPresent()) {
			 u2 = u1.get();
		 } else {
			 throw new UserNotFoundException("User not found!!!");
		 }
		 
		 Bus bus = busRepository.findByBusNumber(booking.getBusNumber());
		 if(bus==null) {
			 throw new BookingNotFoundException("Bus not Found!!!");
		 }
		 
		 if(b1.isPresent()) {
			 throw new BookingAlreadyExistException("Booking Already exist");
		 } else {
			 b2 = booking;
			 b2.setUser(u2);
			 b2.setBus(bus);
			 b2.setBusRoute(bus.getBusRoute());
			 bookingRepository.save(b2);
		 }
		 return booking.getBookingId();
	 }
	 

//		@Override
//		public long addBooking(Booking booking) {
//			Optional<User> user = userRepository.findByUsername(booking.getUsername());
//			User u = null;
//			if(user.isPresent()) {
//				u= user.get();
//			} else {
//				throw new UserNotFoundException("User not found!!!");
//			}
//			
//			 Bus bus = busRepository.findByBusNumber(booking.getBusNumber());
//			 if(bus == null) {
//			 	throw new BookingNotFoundException("Bus with number "+booking.getBusNumber() +" not found!!!");
//			 }
//		Optional<Booking> b1 = bookingRepository.findByUsernameAndBusNumber(booking.getUsername(),booking.getBusNumber());
//			Booking b = null;
//			if(b1.isPresent()) {
//				throw new BookingAlreadyExistException("Booking already exist by the user "+booking.getUsername()+" on the Bus Number "+booking.getBusNumber()+" !!!");
//			} 
//			else {
//				b = booking;
//				b.setBus(bus);
//				b.setUser(u);
//				b.setBusRoute(bus.getBusRoute());
//				bookingRepository.save(b);
//				}
//			return booking.getBookingId();
//		}

	//getting booking details by booking Id
	@Override
	public Booking getBookingDetailsById(long bookingid) {
		Booking b = null;
		Optional<Booking> booking = bookingRepository.findByBookingId(bookingid); 
		if (booking.isPresent()) {
			b= booking.get();
		}
		else {
			throw new BookingNotFoundException("booking doesn't exist!!!");
		}
		return b;
	}
	
	//getting feedback details by bus route
	@Override
	public List<Feedback> getFeedbackByBusRoute(String routeName){
		List<Feedback> feedback = feedbackRepository.findByRouteName(routeName);
		return feedback;
	}
	
	//adding a feedback passing only User and Booking Id
	@Override
	public void addFeedback(User user,long bookingId) {
		Optional<Booking> booking = bookingRepository.findByBookingId(bookingId);
		Booking b = null;
		Feedback f = new Feedback();
		if(booking.isPresent()) {
			b = booking.get();
		} else {
			throw new BookingNotFoundException("Booking not found");
		}
		
		Optional<User> user1 = userRepository.findByUsername(user.getUsername());
		User u = null;
		if(user1.isPresent()) {
			u = user1.get();
		} else {
			throw new UserNotFoundException("User not found!!!");
		}
		
		
		f.setRouteName((b.getBusRoute()).getRouteName());
		f.setUsername(u.getUsername());
		f.setUser(u);
		feedbackRepository.save(f);
 	}
	
	// finding all the bookings made
	@Override
	public List<Booking> findAllBookings(){
		List<Booking> booking = bookingRepository.findAll();
		if (booking.isEmpty()) {
			throw new BookingNotFoundException("bookings does not exist!!!");
		}
		return booking;
	}
	
	//Code end - By Sajin S
	
	
	//extra methods
	@Override
	public List<String> findAllBusNumbers(){
		List<Bus> bus = busRepository.findAll();
		List<String> busNames = new ArrayList<>();
		for (Bus b : bus) {
			busNames.add(b.getBusNumber());
		}
		return busNames;
		
	}

	@Override
	public List<Booking> findAllBookingByUser(String username){
		
		Optional<User> u = userRepository.findByUsername(username);
		if(!u.isPresent()){
			throw new UserNotFoundException("User not found!!!");
		}
		List<Booking> bookings = bookingRepository.findByUsername(username);
		
		return bookings;
	}



	
}
