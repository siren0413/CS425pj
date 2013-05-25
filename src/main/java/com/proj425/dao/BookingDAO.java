package com.proj425.dao;

import java.util.List;

import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.Client;
import com.proj425.domain.ClientStatistics;
import com.proj425.domain.Page;
import com.proj425.domain.Revenue;

public interface BookingDAO {
	

	List<Booking> queryAllBookings(Page page);

    Booking queryBookingById(String booking_id);
    
    List<Booking> queryBookingByCondition(Booking booking, Page page);
    
    List<Booking> queryBookingByDateRange(Booking booking, Page page);
    
    void addBooking(Booking booking);

    void deleteBooking(String booking_id);
    
    void deleteBookingSet(String booking_id_set);

    void updateBooking(Booking booking);
    
    List<Revenue> queryRevenueByAgents();
    
    List<Revenue> queryRevenueByAgent(String first_nm, String last_nm);
    
    List<Revenue> queryRevenueByCity(String city);
    
    List<Revenue> queryRevenueByCountry(String country);
	
	ClientStatistics queryAvgDaysPerTripByClient(String client_id);
	
	List<Agent> queryBestAgent();
	
	List<Client> queryFrequentClient(String start_date, String end_date);
	
	List<Client> queryTopClient(String start_date, String end_date);
	
	int getTotalRows();
	
	int getTotalRowsByCondition(Booking booking);
	
	int getTotalRowsByDateRange(Booking booking);
	
}
