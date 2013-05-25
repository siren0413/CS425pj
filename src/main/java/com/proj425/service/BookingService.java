package com.proj425.service;

import java.util.List;

import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.ClientStatistics;
import com.proj425.domain.Page;
import com.proj425.domain.Revenue;

public interface BookingService {

	List<Booking> findAllBookings(Page page);

	Booking findBookingById(String id);

	List<Booking> findBookingByCondition(Booking booking, Page page);

	List<Booking> findBookingByDateRange(Booking booking, Page page);

	void addBooking(Booking booking);

	void deleteBooking(String booking_id);

	void deleteBookingSet(String booking_id_set);

	void updateBooking(Booking booking);

	List<Revenue> findRevenueByAgents();

	List<Revenue> findRevenueByAgent(String first_nm, String last_nm);

	List<Revenue> findRevenueByCountry(String country);

	List<Revenue> findRevenueByCity(String city);

	ClientStatistics findAvgDaysPerTripByClient(String client_id);

	List<Agent> findBestAgent();

	List<Client> findFrequentClient(String start_date, String end_date);

	List<Client> findTopClient(String start_date, String end_date);
}
