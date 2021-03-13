package com.cg.OnlineBusBooking.test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.OnlineBusBooking.entities.Booking;
import com.cg.OnlineBusBooking.entities.Bus;
import com.cg.OnlineBusBooking.repositories.IBookingRepository;
import com.cg.OnlineBusBooking.repositories.IBusRepository;
import com.cg.OnlineBusBooking.services.AdminServiceImpl;

@SpringBootTest
class TestAdminServiceImpl {
	
	@Autowired
	AdminServiceImpl adminServiceImpl;
	
	@Autowired
	IBookingRepository bookingRepository;
	
	@Autowired
	IBusRepository busRepository;
	
//	@Test
	void testGetAllBusOperator() {
		System.out.println(adminServiceImpl.getAllBusOperator());
	}

	//@Test
	void testGetAllBusOperatorByRoute() {
		System.out.println(adminServiceImpl.getAllBusOperatorByRoute("AtoB"));
	}

//	@Test
	void testGetAllBusOperatorsRequest() {
		System.out.println(adminServiceImpl.getAllBusOperatorsRequest());
	}

//	@Test
	@Transactional
	void testUpdateBusTime() {
		Bus bus = new Bus("ABC123",2,200);
		LocalTime startTime = LocalTime.parse("08:00");
		LocalTime endTime = LocalTime.parse("13:00");
		adminServiceImpl.updateBusTime(12341, startTime, endTime);
		
//		List<Booking> b = bookingRepository.findByBusBusNumber(bus.getBusNumber());
//		//System.out.println(bus);
//		System.out.println(b);
//		System.out.println(b.getJourneyStartTime());
	}

//	@Test
	@Transactional
	void testUpdateBusRoute() {
		Bus bus = new Bus("ABC123",2,200);
		String busRoute = "aaa";
		adminServiceImpl.updateBusRoute(bus, busRoute);
		List<Booking> b = bookingRepository.findByBusBusNumber(bus.getBusNumber());
//		System.out.println(bus);
//		System.out.println(b);
//		System.out.println(b.getBusRoute());
	}

//	@Test
	@Transactional
	void testUpdateBusFare() {
		Bus bus = new Bus("ABC123",2,200);
		adminServiceImpl.updateBusFare(bus, 500);
		Bus b2 = busRepository.findByBusNumber(bus.getBusNumber());
		System.out.println(bus);
		System.out.println(b2);
		System.out.println(b2.getFare());
	}

// 	@Test
	void testDeleteBus() {
		Bus bus = new Bus("ABC123",2,200);
		adminServiceImpl.deleteBus(bus);
	}

//	@Test
	void testDeleteBusByOperator() {
		adminServiceImpl.deleteBusByOperator("Ravi");
	}

//	@Test
	void testGetRevenueByDate() {
		LocalDate date = LocalDate.parse("2021-03-02");
		adminServiceImpl.getRevenueByDate(date);
	}

//	@Test
	void testGetRevenueByBusRoute() {
		adminServiceImpl.getRevenueByBusRoute("A to B");
	}

//	@Test
	@Transactional //to make the session open (to not lazily fetch)
	void testGetRevenueByBusOperator() {
		adminServiceImpl.getRevenueByBusOperator("Ravi");
	}

}
