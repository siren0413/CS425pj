package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.proj425.dao.AmenityDAO;
import com.proj425.domain.Amenity;
import com.proj425.exception.DAOException;
import com.proj425.utils.CommUtils;
import com.proj425.utils.JDBC_Conn;

public class AmenityDAO_Impl implements AmenityDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<Amenity> queryAllAmenitys() {
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
				amenity.setAm_nm(CommUtils.initCap(rs.getString("name")));

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

	public Amenity queryAmenityById(String amenity_id) {
		Amenity amenity = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from amenities where am_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, amenity_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				amenity = new Amenity();

				amenity.setAm_id(rs.getString("am_id"));
				amenity.setAm_nm(CommUtils.initCap(rs.getString("name")));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return amenity;
	}

	public Amenity queryAmenityByName(String am_nm) {
		Amenity amenity = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from amenities where name = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, am_nm);
			rs = stmt.executeQuery();
			while (rs.next()) {
				amenity = new Amenity();

				amenity.setAm_id(rs.getString("am_id"));
				amenity.setAm_nm(CommUtils.initCap(rs.getString("name")));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return amenity;
	}

	public List<Amenity> queryAmenityFuzzy(String am_nm) {

		List<Amenity> amenity_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from amenities where name like '%" + am_nm + "%' ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (amenity_list == null)
					amenity_list = new ArrayList<Amenity>();

				Amenity amenity = new Amenity();

				amenity.setAm_id(rs.getString("am_id"));
				amenity.setAm_nm(CommUtils.initCap(rs.getString("name")));

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

	public void addAmenity(Amenity amenity) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "insert into amenities values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, amenity.getAm_id());
			stmt.setString(2, amenity.getAm_nm());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

}
