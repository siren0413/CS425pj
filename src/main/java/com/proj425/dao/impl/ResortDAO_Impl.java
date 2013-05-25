package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.proj425.dao.ResortDAO;
import com.proj425.domain.Amenity;
import com.proj425.domain.City;
import com.proj425.domain.Resort;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.utils.CommUtils;
import com.proj425.utils.JDBC_Conn;

public class ResortDAO_Impl implements ResortDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<Resort> queryAllResorts() {

		List<Resort> resort_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from resorts natural join cities natural join sun_rating";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (resort_list == null)
					resort_list = new ArrayList<Resort>();

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				city.setCountry(rs.getString("country"));
				city.setCity(rs.getString("city"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				rating.setLuxury_level(rs.getString("luxury_level"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				resort_list.add(resort);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return resort_list;

	}

	public Resort queryResortById(String resort_id) {

		Resort resort = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from resorts natural join cities natural join sun_rating where resort_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resort_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				city.setCountry(rs.getString("country"));
				city.setCity(rs.getString("city"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				rating.setLuxury_level(rs.getString("luxury_level"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return resort;

	}

	public List<Resort> queryResortByCondition(Resort resort) {

		List<Resort> resort_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from resorts natural join cities natural join sun_rating where ";

			if (resort.getResort_nm() != null && !"".equals(resort.getResort_nm())) {
				sql += " resort_nm='" + resort.getResort_nm() + "'" + " and ";
				count++;
			}

			if (resort.getPhone_number() != null && !"".equals(resort.getPhone_number())) {
				sql += " phone_number='" + resort.getPhone_number() + "'" + " and ";
				count++;
			}

			if (resort.getCity()!=null && resort.getCity().getCity() != null && !"".equals(resort.getCity().getCity())) {
				sql += " city='" + resort.getCity().getCity() + "'" + " and ";
				count++;
			}

			if (resort.getCity()!=null && resort.getCity().getCountry() != null && !"".equals(resort.getCity().getCountry())) {
				sql += " country='" + resort.getCity().getCountry() + "'" + " and ";
				count++;
			}

			if (resort.getRating()!=null && resort.getRating().getRating() != null && !"".equals(resort.getRating().getRating())) {
				sql += " rating='" + resort.getRating().getRating() + "'" + " and ";
				count++;
			}

			if (resort.getRating()!=null && resort.getRating().getLuxury_level() != null && !"".equals(resort.getRating().getLuxury_level())) {
				sql += " luxury_level='" + resort.getRating().getLuxury_level() + "'" + " and ";
				count++;
			}

			if (resort.getAddress() != null && !"".equals(resort.getAddress())) {
				sql += " address='" + resort.getAddress() + "'" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllResorts();

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				if (resort_list == null)
					resort_list = new ArrayList<Resort>();

				resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				city.setCountry(rs.getString("country"));
				city.setCity(rs.getString("city"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				rating.setLuxury_level(rs.getString("luxury_level"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				resort_list.add(resort);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return resort_list;

	}

	public void addResort(Resort resort) {

		try {

			conn = JDBC_Conn.getConnection();
			String sql = "insert into resorts values(?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resort.getResort_id());
			stmt.setString(2, resort.getResort_nm());
			stmt.setString(3, resort.getCity().getCity_id());
			stmt.setString(4, resort.getPhone_number());
			stmt.setString(5, resort.getAddress());
			stmt.setString(6, resort.getRating().getRating());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteResort(String resort_id) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from resorts where resort_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resort_id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteResortSet(String resort_id_set) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from resorts where resort_id in ( " + resort_id_set + " )";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void updateResort(Resort resort) {

		try {
			conn = JDBC_Conn.getConnection();

			// update resort
			String sql = "update resorts set city_id=?, phone_number=?, resort_nm=?, rating=?, address=? where resort_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resort.getCity().getCity_id());
			stmt.setString(2, resort.getPhone_number());
			stmt.setString(3, resort.getResort_nm());
			stmt.setString(4, resort.getRating().getRating());
			stmt.setString(5, resort.getAddress());
			stmt.setString(6, resort.getResort_id());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public List<Resort> queryResortByAmenity(List<Amenity> amenity_list) {

		List<Resort> resort_list = null;

		if (amenity_list == null || amenity_list.size() == 0)
			return null;

		String am_nm_list = "'" + amenity_list.get(0).getAm_nm() + "'";

		for (int i = 1; i < amenity_list.size(); i++) {
			am_nm_list += " , " + "'" + amenity_list.get(i).getAm_nm() + "'";
		}
		

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from resorts natural join cities natural join sun_rating natural join (select distinct ra.resort_id from Resort_Amenities ra minus (select m.resort_id from ( select r1.resort_id, a1.am_id from resorts r1, amenities a1  where a1.name in ( "
					+ am_nm_list + " ) minus (select resort_id, am_id from Resort_Amenities) ) m ))";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (resort_list == null)
					resort_list = new ArrayList<Resort>();

				Resort resort = new Resort();

				City city = new City();
				SunRating rating = new SunRating();

				city.setCity_id(rs.getString("city_id"));
				city.setCountry(rs.getString("country"));
				city.setCity(rs.getString("city"));
				resort.setCity(city);

				rating.setRating(rs.getString("rating"));
				rating.setLuxury_level(rs.getString("luxury_level"));
				resort.setRating(rating);

				resort.setResort_id(rs.getString("resort_id"));
				resort.setPhone_number(rs.getString("phone_number"));
				resort.setResort_nm(CommUtils.initCap(rs.getString("resort_nm")));
				resort.setAddress(rs.getString("address"));

				resort_list.add(resort);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return resort_list;

	}

	public List<Amenity> queryAllAmenities() {

		List<Amenity> amenity_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from amenities ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (amenity_list == null)
					amenity_list = new ArrayList<Amenity>();

				Amenity amenity = new Amenity();

				amenity.setAm_id(rs.getString("am_id"));
				amenity.setAm_nm(rs.getString("name"));

				amenity_list.add(amenity);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return amenity_list;

	}

	public List<Amenity> queryAmenitiyByResort(String resort_id) {
		List<Amenity> amenity_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from amenities natural join resort_amenities  where resort_id=? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, resort_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (amenity_list == null)
					amenity_list = new ArrayList<Amenity>();

				Amenity amenity = new Amenity();

				amenity.setAm_id(rs.getString("am_id"));
				amenity.setAm_nm(rs.getString("name"));

				amenity_list.add(amenity);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return amenity_list;
	}
	
	
	
	

}
