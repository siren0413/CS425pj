package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.proj425.dao.CityDAO;
import com.proj425.domain.City;
import com.proj425.exception.DAOException;

import com.proj425.utils.JDBC_Conn;

public class CityDAO_Impl implements CityDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<City> queryAllCities() {

		List<City> city_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from cities ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (city_list == null)
					city_list = new ArrayList<City>();

				City city = new City();

				city.setCity_id(rs.getString("city_id"));
				city.setCity(rs.getString("city"));
				city.setCountry(rs.getString("country"));

				city_list.add(city);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return city_list;

	}

	public City queryCityById(String city_id) {

		City city = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from cities where city_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				city = new City();

				city.setCity_id(rs.getString("city_id"));
				city.setCity(rs.getString("city"));
				city.setCountry(rs.getString("country"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return city;

	}

	public List<City> queryCityByCondition(City city) {

		List<City> city_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from cities where ";

			if (city.getCity()!=null && !"".equals(city.getCity())) {
				sql += " city='" + city.getCity() + "'" + " and ";
				count++;
			}

			if (city.getCountry()!=null && !"".equals(city.getCountry())) {
				sql += " country='" + city.getCountry() + "'" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllCities();

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			while (rs.next()) {
				if (city_list == null)
					city_list = new ArrayList<City>();

				city = new City();

				city.setCity_id(rs.getString("city_id"));
				city.setCity(rs.getString("city"));
				city.setCountry(rs.getString("country"));

				city_list.add(city);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return city_list;

	}

	public void addCity(City city) {

		try {

			conn = JDBC_Conn.getConnection();
			String sql = "insert into cities values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getCity_id());
			stmt.setString(2, city.getCity());
			stmt.setString(3, city.getCountry());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteCity(String city_id) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from cities where city_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city_id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteCitySet(String city_id_set) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from cities where city_id in ( "+city_id_set+" )";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void updateCity(City city) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "update cities set city=?, country=? where city_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getCity());
			stmt.setString(2, city.getCountry());
			stmt.setString(3, city.getCity_id());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

}
