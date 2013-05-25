package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.BookingDAO;
import com.proj425.dao.impl.BookingDAO_Impl;
import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.City;
import com.proj425.domain.Client;
import com.proj425.domain.ClientStatistics;
import com.proj425.domain.Page;
import com.proj425.domain.Revenue;
import com.proj425.service.BookingService;
import com.proj425.service.ClientService;
import com.proj425.utils.CommUtils;

public class BookingServiceImpl implements BookingService {

	private BookingDAO dao = new BookingDAO_Impl();

	public List<Booking> findAllBookings(Page page) {
		int totalRows = 1;
		totalRows = dao.getTotalRows();
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryAllBookings(page);
	}

	public Booking findBookingById(String id) {
		return dao.queryBookingById(id);
	}

	public List<Booking> findBookingByCondition(Booking booking, Page page) {
		int totalRows = 1;
		totalRows = dao.getTotalRowsByCondition(booking);
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryBookingByCondition(booking, page);
	}

	public List<Booking> findBookingByDateRange(Booking booking, Page page) {
		int totalRows = 1;
		totalRows = dao.getTotalRowsByDateRange(booking);
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryBookingByDateRange(booking, page);
	}

	public void addBooking(Booking booking) {

		booking.setBooking_id(CommUtils.getId()); // set ID.

		dao.addBooking(booking);
	}

	public void deleteBooking(String booking_id) {
		dao.deleteBooking(booking_id);

	}

	public void deleteBookingSet(String booking_id_set) {
		dao.deleteBookingSet(booking_id_set);
	}

	public void updateBooking(Booking booking) {
		dao.updateBooking(booking);

	}

	public List<Revenue> findRevenueByAgents() {
		return dao.queryRevenueByAgents();
	}

	public List<Revenue> findRevenueByAgent(String first_nm, String last_nm) {
		return dao.queryRevenueByAgent(first_nm, last_nm);
	}

	public List<Revenue> findRevenueByCountry(String country) {
		return dao.queryRevenueByCountry(country);
	}

	public List<Revenue> findRevenueByCity(String city) {
		return dao.queryRevenueByCity(city);
	}

	public ClientStatistics findAvgDaysPerTripByClient(String client_id) {
		return dao.queryAvgDaysPerTripByClient(client_id);
	}

	public List<Agent> findBestAgent() {
		return dao.queryBestAgent();
	}

	public List<Client> findFrequentClient(String start_date, String end_date) {
		return dao.queryFrequentClient(start_date, end_date);
	}
	public List<Client> findTopClient(String start_date, String end_date) {
		return dao.queryTopClient(start_date, end_date);
	}

}
