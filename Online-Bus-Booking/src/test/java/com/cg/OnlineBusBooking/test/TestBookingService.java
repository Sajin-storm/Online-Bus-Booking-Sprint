package com.cg.OnlineBusBooking.test;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.User;
import com.cg.OnlineBusBooking.serviceinterfaces.IBookingService;

@SpringBootTest
class TestBookingService {
	
	@Autowired
	IBookingService bookingService;
	
	@Test
	void testAddBooking() {
		Booking b = new Booking(56738,"Arijith","ABC123","C","D",4,400,null,null,null,null,null,null);
		b.setDate(LocalDate.parse("2021-03-14"));
		b.setJourneyStartTime(LocalTime.parse("09:00"));
		b.setJourneyEndTime(LocalTime.parse("20:00"));
		bookingService.addBooking(b);
	}

	@Test
	void testUpdateBookingDate() {
		bookingService.updateBookingDate(101,"2021-03-02");
	}

	@Test
	void testDeleteBooking() {
		bookingService.deleteBooking(1234);
	}

	@Test
	void testGetBookingDetailsById() {
		bookingService.getBookingDetailsById(101);
	}

	@Test
	void testGetAllBookingByDate() {
		LocalDate date = LocalDate.parse("2021-03-02");
		bookingService.getAllBookingByDate(date);
	}

	@Test
	void testGetAllBookingByBusRoute() {
		bookingService.getAllBookingByBusRoute("A to B");
	}

	@Test
	void testGetFeedbackByBusRoute() {
		bookingService.getFeedbackByBusRoute("A to B");
	}

	@Test
	void testFindAllBookings() {
		bookingService.findAllBookings();
	}

	@Test
	void testAddFeedbackUserLong() {
		User user = new User();
		user.setUsername("Ravi");
		user.setPassword("Ravi123");
		bookingService.addFeedback(user, 101);
	}

	@Test
	void testAddFeedbackStringLongString() {
		bookingService.addFeedback("Ravi", 101, "Good ride");
	}

}
