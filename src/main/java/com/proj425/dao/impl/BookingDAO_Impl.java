package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.proj425.dao.BookingDAO;
import com.proj425.domain.Agent;
import com.proj425.domain.Booking;
import com.proj425.domain.City;
import com.proj425.domain.Client;
import com.proj425.domain.ClientStatistics;
import com.proj425.domain.Page;
import com.proj425.domain.Resort;
import com.proj425.domain.Revenue;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.utils.CommUtils;
import com.proj425.utils.JDBC_Conn;

public class BookingDAO_Impl implements BookingDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<Booking> queryAllBookings(Page page) {

		List<Booking> booking_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select b.*,  "
					+ "c.phone_number c_phone_number, c.email c_email, c.first_nm c_first_nm, c.last_nm c_last_nm, c.dob c_dob, c.zip c_zip, c.gender c_gender, "
					+ "a.phone_number a_phone_number, a.email a_email, a.first_nm a_first_nm, a.last_nm a_last_nm, a.dob a_dob, a.zip a_zip, a.gender a_gender, "
					+ "r.resort_id resort_id, r.phone_number r_phone_number, r.city_id city_id, r.resort_nm resort_nm, r.rating rating, r.address address "
					+ "from bookings b, clients c, agents a, resorts r where b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id ";

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();

			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());

			}
			while (rs.next()) {
				if (booking_list == null)
					booking_list = new ArrayList<Booking>();

				Booking booking = new Booking();

				booking.setBooking_id(rs.getString("booking_id"));

				// set client
				Client client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("c_phone_number"));
				client.setEmail(rs.getString("c_email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("c_first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("c_last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("c_dob"));

				client.setDob(dob);
				client.setZip(rs.getString("c_zip"));
				client.setGender(rs.getString("c_gender"));

				booking.setClient(client);

				// set agent

				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("a_phone_number"));
				agent.setEmail(rs.getString("a_email"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("a_first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("a_last_nm")));

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dob = sdf.parse(rs.getString("a_dob"));

				agent.setDob(dob);
				agent.setZip(rs.getString("a_zip"));
				agent.setGender(rs.getString("a_gender"));

				booking.setAgent(agent);

				// set resort

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("r_phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				booking.setResort(resort);

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = sdf.parse(rs.getString("book_date"));

				booking.setBook_date(date);

				date = sdf.parse(rs.getString("arrive_date"));

				booking.setArrive_date(date);

				date = sdf.parse(rs.getString("departure_date"));

				booking.setDeparture_date(date);

				booking.setRoom_type(rs.getString("room_type"));
				booking.setActivity(rs.getString("activity"));

				booking_list.add(booking);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return booking_list;
	}

	public Booking queryBookingById(String booking_id) {

		Booking booking = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select b.*, "
					+ "c.phone_number c_phone_number, c.email c_email, c.first_nm c_first_nm, c.last_nm c_last_nm, c.dob c_dob, c.zip c_zip, c.gender c_gender, "
					+ "p.position position, a.phone_number a_phone_number, a.email a_email, a.first_nm a_first_nm, a.last_nm a_last_nm, a.dob a_dob, a.zip a_zip, a.gender a_gender, "
					+ "r.resort_id resort_id, r.phone_number r_phone_number, r.city_id city_id, r.resort_nm resort_nm, r.rating rating, r.address address "
					+ "from bookings b, clients c, agents a, resorts r, position p where b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id and  a.agent_id=p.agent_id "
					+ " and  booking_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, booking_id);
			rs = stmt.executeQuery();
			while (rs.next()) {

				booking = new Booking();

				booking.setBooking_id(rs.getString("booking_id"));

				// set client
				Client client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("c_phone_number"));
				client.setEmail(rs.getString("c_email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("c_first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("c_last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("c_dob"));

				client.setDob(dob);
				client.setZip(rs.getString("c_zip"));
				client.setGender(rs.getString("c_gender"));

				booking.setClient(client);

				// set agent

				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("a_phone_number"));
				agent.setEmail(rs.getString("a_email"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("a_first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("a_last_nm")));

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dob = sdf.parse(rs.getString("a_dob"));

				agent.setDob(dob);
				agent.setZip(rs.getString("a_zip"));
				agent.setGender(rs.getString("a_gender"));
				agent.setPosition(rs.getString("position"));

				booking.setAgent(agent);

				// set resort

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("r_phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				booking.setResort(resort);

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = sdf.parse(rs.getString("book_date"));

				booking.setBook_date(date);

				date = sdf.parse(rs.getString("arrive_date"));

				booking.setArrive_date(date);

				date = sdf.parse(rs.getString("departure_date"));

				booking.setDeparture_date(date);

				booking.setRoom_type(rs.getString("room_type"));
				booking.setActivity(rs.getString("activity"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return booking;
	}

	public List<Booking> queryBookingByCondition(Booking booking, Page page) {

		List<Booking> booking_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select b.*, "
					+ "c.phone_number c_phone_number, c.email c_email, c.first_nm c_first_nm, c.last_nm c_last_nm, c.dob c_dob, c.zip c_zip, c.gender c_gender, "
					+ "a.phone_number a_phone_number, a.email a_email, a.first_nm a_first_nm, a.last_nm a_last_nm, a.dob a_dob, a.zip a_zip, a.gender a_gender, "
					+ "r.resort_id resort_id, r.phone_number r_phone_number, r.city_id city_id, r.resort_nm resort_nm, r.rating rating ,r.address address "
					+ "from bookings b, clients c, agents a, resorts r where b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id"
					+ " and ";

			// set client
			if (booking.getClient().getFirst_nm() != null && !"".equals(booking.getClient().getFirst_nm())) {
				sql += " c.first_nm='" + booking.getClient().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getClient().getLast_nm() != null && !"".equals(booking.getClient().getLast_nm())) {
				sql += " c.last_nm='" + booking.getClient().getLast_nm() + "'" + " and ";
				count++;
			}

			// set agent

			if (booking.getAgent().getFirst_nm() != null && !"".equals(booking.getAgent().getFirst_nm())) {
				sql += " a.first_nm='" + booking.getAgent().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getAgent().getLast_nm() != null && !"".equals(booking.getAgent().getLast_nm())) {
				sql += " a.last_nm='" + booking.getAgent().getLast_nm() + "'" + " and ";
				count++;
			}

			// set booking date
			if (booking.getBook_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getBook_date());
				sql += " b.book_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set arrive date
			if (booking.getArrive_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getArrive_date());
				sql += " b.arrive_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set departure date
			if (booking.getDeparture_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getDeparture_date());
				sql += " b.departure_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set resort name
			if (booking.getResort().getResort_nm() != null && !"".equals(booking.getResort().getResort_nm())) {
				sql += " r.resort_nm='" + booking.getResort().getResort_nm() + "'" + " and ";
				count++;
			}

			// set room type
			if (booking.getRoom_type() != null && !"".equals(booking.getRoom_type())) {
				sql += " b.room_type='" + booking.getRoom_type() + "'" + " and ";
				count++;
			}

			// activity
			if (booking.getActivity() != null && !"".equals(booking.getActivity())) {
				sql += " b.activity='" + booking.getActivity() + "'" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllBookings(page);

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();

			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());

			}
			while (rs.next()) {
				if (booking_list == null)
					booking_list = new ArrayList<Booking>();

				booking = new Booking();

				booking.setBooking_id(rs.getString("booking_id"));

				// set client
				Client client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("c_phone_number"));
				client.setEmail(rs.getString("c_email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("c_first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("c_last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("c_dob"));

				client.setDob(dob);
				client.setZip(rs.getString("c_zip"));
				client.setGender(rs.getString("c_gender"));

				booking.setClient(client);

				// set agent

				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("a_phone_number"));
				agent.setEmail(rs.getString("a_email"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("a_first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("a_last_nm")));

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dob = sdf.parse(rs.getString("a_dob"));

				agent.setDob(dob);
				agent.setZip(rs.getString("a_zip"));
				agent.setGender(rs.getString("a_gender"));

				booking.setAgent(agent);

				// set resort

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("r_phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				booking.setResort(resort);

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = sdf.parse(rs.getString("book_date"));

				booking.setBook_date(date);

				date = sdf.parse(rs.getString("arrive_date"));

				booking.setArrive_date(date);

				date = sdf.parse(rs.getString("departure_date"));

				booking.setDeparture_date(date);

				booking.setRoom_type(rs.getString("room_type"));
				booking.setActivity(rs.getString("activity"));

				booking_list.add(booking);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return booking_list;

	}

	public List<Booking> queryBookingByDateRange(Booking booking, Page page) {
		List<Booking> booking_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select b.*, "
					+ "c.phone_number c_phone_number, c.email c_email, c.first_nm c_first_nm, c.last_nm c_last_nm, c.dob c_dob, c.zip c_zip, c.gender c_gender, "
					+ "a.phone_number a_phone_number, a.email a_email, a.first_nm a_first_nm, a.last_nm a_last_nm, a.dob a_dob, a.zip a_zip, a.gender a_gender, "
					+ "r.resort_id resort_id, r.phone_number r_phone_number, r.city_id city_id, r.resort_nm resort_nm, r.rating rating ,r.address address "
					+ "from bookings b, clients c, agents a, resorts r where b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id"
					+ " and ";

			// set client
			if (booking.getClient().getFirst_nm() != null && !"".equals(booking.getClient().getFirst_nm())) {
				sql += " c.first_nm='" + booking.getClient().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getClient().getLast_nm() != null && !"".equals(booking.getClient().getLast_nm())) {
				sql += " c.last_nm='" + booking.getClient().getLast_nm() + "'" + " and ";
				count++;
			}

			// set agent

			if (booking.getAgent().getFirst_nm() != null && !"".equals(booking.getAgent().getFirst_nm())) {
				sql += " a.first_nm='" + booking.getAgent().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getAgent().getLast_nm() != null && !"".equals(booking.getAgent().getLast_nm())) {
				sql += " a.last_nm='" + booking.getAgent().getLast_nm() + "'" + " and ";
				count++;
			}

			// set booking date
			if (booking.getBook_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getBook_date());
				sql += " b.book_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set resort name
			if (booking.getResort().getResort_nm() != null && !"".equals(booking.getResort().getResort_nm())) {
				sql += " r.resort_nm='" + booking.getResort().getResort_nm() + "'" + " and ";
				count++;
			}

			// set room type
			if (booking.getRoom_type() != null && !"".equals(booking.getRoom_type())) {
				sql += " b.room_type='" + booking.getRoom_type() + "'" + " and ";
				count++;
			}

			// activity
			if (booking.getActivity() != null && !"".equals(booking.getActivity())) {
				sql += " b.activity='" + booking.getActivity() + "'" + " and ";
				count++;
			}

			// date range
			if (booking.getArrive_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getArrive_date());
				sql += " b.arrive_date>= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (booking.getDeparture_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getDeparture_date());
				sql += " b.departure_date<= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllBookings(page);

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();

			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());

			}
			while (rs.next()) {
				if (booking_list == null)
					booking_list = new ArrayList<Booking>();

				booking = new Booking();

				booking.setBooking_id(rs.getString("booking_id"));

				// set client
				Client client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("c_phone_number"));
				client.setEmail(rs.getString("c_email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("c_first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("c_last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("c_dob"));

				client.setDob(dob);
				client.setZip(rs.getString("c_zip"));
				client.setGender(rs.getString("c_gender"));

				booking.setClient(client);

				// set agent

				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("a_phone_number"));
				agent.setEmail(rs.getString("a_email"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("a_first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("a_last_nm")));

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dob = sdf.parse(rs.getString("a_dob"));

				agent.setDob(dob);
				agent.setZip(rs.getString("a_zip"));
				agent.setGender(rs.getString("a_gender"));

				booking.setAgent(agent);

				// set resort

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("r_phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				booking.setResort(resort);

				sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date date = sdf.parse(rs.getString("book_date"));

				booking.setBook_date(date);

				date = sdf.parse(rs.getString("arrive_date"));

				booking.setArrive_date(date);

				date = sdf.parse(rs.getString("departure_date"));

				booking.setDeparture_date(date);

				booking.setRoom_type(rs.getString("room_type"));
				booking.setActivity(rs.getString("activity"));

				booking_list.add(booking);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return booking_list;
	}

	public void addBooking(Booking booking) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "insert into bookings values(?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, booking.getBooking_id());
			stmt.setString(2, booking.getClient().getClient_id());
			stmt.setString(3, booking.getResort().getResort_id());
			stmt.setDate(4, new java.sql.Date(booking.getBook_date().getTime()));
			stmt.setDate(5, new java.sql.Date(booking.getArrive_date().getTime()));
			stmt.setDate(6, new java.sql.Date(booking.getDeparture_date().getTime()));
			stmt.setString(7, booking.getRoom_type());
			stmt.setString(8, booking.getAgent().getAgent_id());
			stmt.setString(9, booking.getActivity());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteBooking(String booking_id) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from bookings where booking_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, booking_id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteBookingSet(String booking_id_set) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from bookings where booking_id in (" + booking_id_set + ")";
			stmt = conn.prepareStatement(sql);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}
	public void updateBooking(Booking booking) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "update bookings set book_date=?, arrive_date=?, departure_date=?, activity=?, room_type=? ,client_id=? ,resort_id=?, agent_id=? where booking_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(booking.getBook_date().getTime()));
			stmt.setDate(2, new java.sql.Date(booking.getArrive_date().getTime()));
			stmt.setDate(3, new java.sql.Date(booking.getDeparture_date().getTime()));
			stmt.setString(4, booking.getActivity());
			stmt.setString(5, booking.getRoom_type());
			stmt.setString(6, booking.getClient().getClient_id());
			stmt.setString(7, booking.getResort().getResort_id());
			stmt.setString(8, booking.getAgent().getAgent_id());
			stmt.setString(9, booking.getBooking_id());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public List<Revenue> queryRevenueByAgents() {

		List<Revenue> revenue_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select aa.agent_id, aa.phone_number,aa.first_nm,aa.last_nm , m.revenue from agents aa,  (select b.agent_id aid, sum((b.departure_date - b.arrive_date) * p.price) revenue  from bookings b, agents a, room p  where b.resort_id = p.resort_id and b.room_type = p.room_type and a.agent_id = b.agent_id group by b.agent_id order by b.agent_id) m where aa.agent_id = m.aid ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (revenue_list == null) {
					revenue_list = new ArrayList<Revenue>();
				}

				Revenue revenue = new Revenue();
				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));
				agent.setPhone_number(rs.getString("phone_number"));
				revenue.setObj(agent);
				revenue.setRevenue(rs.getString("revenue"));

				revenue_list.add(revenue);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return revenue_list;

	}

	public List<Revenue> queryRevenueByAgent(String first_nm, String last_nm) {
		List<Revenue> revenue_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select aa.agent_id, aa.phone_number,aa.first_nm,aa.last_nm , m.revenue from agents aa, (select b.agent_id aid, sum((b.departure_date - b.arrive_date) * p.price) revenue  from bookings b, agents a, room p  where b.resort_id = p.resort_id and b.room_type = p.room_type  and a.agent_id = b.agent_id and a.first_nm = ? and a.last_nm = ? group by b.agent_id order by b.agent_id) m where aa.agent_id = m.aid";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, first_nm);
			stmt.setString(2, last_nm);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (revenue_list == null) {
					revenue_list = new ArrayList<Revenue>();
				}

				Revenue revenue = new Revenue();
				Agent agent = new Agent();
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));
				agent.setPhone_number(rs.getString("phone_number"));
				revenue.setObj(agent);
				revenue.setRevenue(rs.getString("revenue"));

				revenue_list.add(revenue);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return revenue_list;
	}

	public List<Revenue> queryRevenueByCity(String city) {
		List<Revenue> revenue_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select cc.city,cc.country, m.revenue from cities cc,  (select c.city_id cid,sum((b.departure_date - b.arrive_date) * p.price) revenue from bookings b, resorts r, room p , cities c where b.resort_id = p.resort_id and b.room_type = p.room_type and r.resort_id = b.resort_id and r.city_id = c.city_id  group by c.city_id order by c.city_id) m where cc.city_id = m.cid and cc.city =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (revenue_list == null) {
					revenue_list = new ArrayList<Revenue>();
				}

				Revenue revenue = new Revenue();
				City city1 = new City();
				city1.setCity(rs.getString("city"));
				city1.setCountry(rs.getString("country"));
				revenue.setObj(city1);
				revenue.setRevenue(rs.getString("revenue"));

				revenue_list.add(revenue);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return revenue_list;

	}

	public List<Revenue> queryRevenueByCountry(String country) {

		List<Revenue> revenue_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select c.country, sum((b.departure_date - b.arrive_date) * p.price) revenue from bookings b, resorts r, room p , cities c where b.resort_id = p.resort_id and b.room_type = p.room_type and r.resort_id = b.resort_id and r.city_id = c.city_id  group by c.country having c.country = ? order by c.country";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (revenue_list == null) {
					revenue_list = new ArrayList<Revenue>();
				}

				Revenue revenue = new Revenue();
				City city = new City();
				city.setCountry(rs.getString("country"));
				revenue.setObj(city);
				revenue.setRevenue(rs.getString("revenue"));

				revenue_list.add(revenue);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return revenue_list;

	}

	public ClientStatistics queryAvgDaysPerTripByClient(String client_id) {
		ClientStatistics clientStatistics = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select c.first_nm, c.last_nm, bb.avg from clients c, (select b.client_id client_id, avg(departure_date - arrive_date) avg from bookings b group by b.client_id) bb where c.client_id = bb.client_id  and c.client_id = '"+client_id+"'  ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {

				clientStatistics = new ClientStatistics();
				Client client = new Client();
				client.setFirst_nm(rs.getString("first_nm"));
				client.setLast_nm(rs.getString("last_nm"));
				clientStatistics.setClient(client);
				clientStatistics.setAvgDaysPerTrip(rs.getString("avg"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return clientStatistics;
	}

	public List<Agent> queryBestAgent() {

		List<Agent> agent_list = null;
		Agent agent = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select agent_id, phone_number, first_nm, last_nm, email, zip , gender, dob, position, m.num from agents natural join position natural join (select b.agent_id agent_id, count(*) num from bookings b group by b.agent_id having count(*) >= (select max(count(*)) from bookings bb group by bb.agent_id)) m";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (agent_list == null)
					agent_list = new ArrayList<Agent>();

				agent = new Agent();

				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("phone_number"));
				agent.setEmail(rs.getString("email"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("dob"));

				agent.setDob(dob);
				agent.setZip(rs.getString("zip"));
				agent.setGender(rs.getString("gender"));
				agent.setPosition(rs.getString("position"));

				agent_list.add(agent);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return agent_list;
	}

	public List<Client> queryFrequentClient(String start_date, String end_date) {
		List<Client> client_list = null;
		Client client = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select client_id, phone_number,first_nm, last_nm, dob, zip, email, gender, dd.num_of_trip from clients c natural join (select bb.client_id client_id, count(*) num_of_trip from   (select * from bookings b, room p where book_date between to_date('"
					+ start_date
					+ "','mm/dd/yyyy')	 and to_date('"
					+ end_date
					+ "','mm/dd/yyyy') and  b.resort_id = p.resort_id and b.room_type = p.room_type  ) bb  group by bb.client_id having count(*) >=(select max(count(booking_id)) from (select * from bookings where book_date between to_date('"
					+ start_date + "','mm/dd/yyyy')	and to_date('" + end_date + "','mm/dd/yyyy')) bc group by bc.client_id)	) dd";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (client_list == null)
					client_list = new ArrayList<Client>();

				client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("phone_number"));
				client.setEmail(rs.getString("email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("dob"));

				client.setDob(dob);
				client.setZip(rs.getString("zip"));
				client.setGender(rs.getString("gender"));

				client_list.add(client);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return client_list;
	}

	public List<Client> queryTopClient(String start_date, String end_date) {
		List<Client> client_list = null;
		Client client = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select client_id, phone_number,first_nm, last_nm, zip, email, dob, gender, dd.amount from clients c natural join (select bb.client_id client_id, sum((bb.departure_date - bb.arrive_date)*bb.price) amount from   (select * from bookings b, room p where book_date between to_date('"
					+ start_date
					+ "','mm/dd/yyyy')	and to_date('"
					+ end_date
					+ "','mm/dd/yyyy') and  b.resort_id = p.resort_id and b.room_type = p.room_type  ) bb group by bb.client_id having sum((bb.departure_date - bb.arrive_date)*bb.price) >=(select max(sum((bc.departure_date - bc.arrive_date)*bc.price)) from (	select * from bookings b, room p where book_date between to_date('"
					+ start_date
					+ "','mm/dd/yyyy')	and to_date('"
					+ end_date
					+ "','mm/dd/yyyy') and b.resort_id = p.resort_id and b.room_type = p.room_type  ) bc group by bc.client_id)	) dd";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (client_list == null)
					client_list = new ArrayList<Client>();

				client = new Client();

				client.setClient_id(rs.getString("client_id"));
				client.setPhone_number(rs.getString("phone_number"));
				client.setEmail(rs.getString("email"));
				client.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				client.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("dob"));

				client.setDob(dob);
				client.setZip(rs.getString("zip"));
				client.setGender(rs.getString("gender"));

				client_list.add(client);

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return client_list;
	}

	public int getTotalRows() {
		int totalRows = 0;
		try {

			conn = JDBC_Conn.getConnection();
			String sql = "select count(*) from bookings ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalRows = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return totalRows;

	}

	public int getTotalRowsByCondition(Booking booking) {
		int count = 0;
		int totalRows = 0;
		try {

			String sql = "select count(*) from bookings b, resorts r, agents a, clients c where  b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id and ";

			// set client
			if (booking.getClient().getFirst_nm() != null && !"".equals(booking.getClient().getFirst_nm())) {
				sql += " c.first_nm='" + booking.getClient().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getClient().getLast_nm() != null && !"".equals(booking.getClient().getLast_nm())) {
				sql += " c.last_nm='" + booking.getClient().getLast_nm() + "'" + " and ";
				count++;
			}

			// set agent

			if (booking.getAgent().getFirst_nm() != null && !"".equals(booking.getAgent().getFirst_nm())) {
				sql += " a.first_nm='" + booking.getAgent().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getAgent().getLast_nm() != null && !"".equals(booking.getAgent().getLast_nm())) {
				sql += " a.last_nm='" + booking.getAgent().getLast_nm() + "'" + " and ";
				count++;
			}

			// set booking date
			if (booking.getBook_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getBook_date());
				sql += " b.book_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set arrive date
			if (booking.getArrive_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getArrive_date());
				sql += " b.arrive_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set departure date
			if (booking.getDeparture_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getDeparture_date());
				sql += " b.departure_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set resort name
			if (booking.getResort().getResort_nm() != null && !"".equals(booking.getResort().getResort_nm())) {
				sql += " r.resort_nm='" + booking.getResort().getResort_nm() + "'" + " and ";
				count++;
			}

			// set room type
			if (booking.getRoom_type() != null && !"".equals(booking.getRoom_type())) {
				sql += " b.room_type='" + booking.getRoom_type() + "'" + " and ";
				count++;
			}

			// activity
			if (booking.getActivity() != null && !"".equals(booking.getActivity())) {
				sql += " b.activity='" + booking.getActivity() + "'" + " and ";
				count++;
			}

			if (count == 0)
				return getTotalRows();

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			conn = JDBC_Conn.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalRows = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return totalRows;

	}

	public int getTotalRowsByDateRange(Booking booking) {
		int count = 0;
		int totalRows = 0;
		try {

			String sql = "select count(*) from bookings b, resorts r, agents a, clients c  where b.client_id = c.client_id  and b.agent_id = a.agent_id and b.resort_id = r.resort_id and ";

			// set client
			if (booking.getClient().getFirst_nm() != null && !"".equals(booking.getClient().getFirst_nm())) {
				sql += " c.first_nm='" + booking.getClient().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getClient().getLast_nm() != null && !"".equals(booking.getClient().getLast_nm())) {
				sql += " c.last_nm='" + booking.getClient().getLast_nm() + "'" + " and ";
				count++;
			}

			// set agent

			if (booking.getAgent().getFirst_nm() != null && !"".equals(booking.getAgent().getFirst_nm())) {
				sql += " a.first_nm='" + booking.getAgent().getFirst_nm() + "'" + " and ";
				count++;
			}

			if (booking.getAgent().getLast_nm() != null && !"".equals(booking.getAgent().getLast_nm())) {
				sql += " a.last_nm='" + booking.getAgent().getLast_nm() + "'" + " and ";
				count++;
			}

			// set booking date
			if (booking.getBook_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getBook_date());
				sql += " b.book_date= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			// set resort name
			if (booking.getResort().getResort_nm() != null && !"".equals(booking.getResort().getResort_nm())) {
				sql += " r.resort_nm='" + booking.getResort().getResort_nm() + "'" + " and ";
				count++;
			}

			// set room type
			if (booking.getRoom_type() != null && !"".equals(booking.getRoom_type())) {
				sql += " b.room_type='" + booking.getRoom_type() + "'" + " and ";
				count++;
			}

			// activity
			if (booking.getActivity() != null && !"".equals(booking.getActivity())) {
				sql += " b.activity='" + booking.getActivity() + "'" + " and ";
				count++;
			}

			// date range
			if (booking.getArrive_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getArrive_date());
				sql += " b.arrive_date>= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (booking.getDeparture_date() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String date_str = sdf.format(booking.getDeparture_date());
				sql += " b.departure_date<= " + "to_date('" + date_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (count == 0)
				return getTotalRows();

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			conn = JDBC_Conn.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalRows = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return totalRows;
	}

}
