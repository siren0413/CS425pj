package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj425.dao.SunRatingDAO;
import com.proj425.domain.SunRating;
import com.proj425.domain.SunRating;
import com.proj425.domain.SunRating;
import com.proj425.exception.DAOException;
import com.proj425.utils.JDBC_Conn;

public class SunRatingDAO_Impl implements SunRatingDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<SunRating> queryAllSunRatings() {

		List<SunRating> sunRating_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from sun_rating";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				if (sunRating_list == null)
					sunRating_list = new ArrayList<SunRating>();

				SunRating sunRating = new SunRating();

				sunRating.setRating(rs.getString("rating"));
				sunRating.setRating(rs.getString("luxury_level"));

				sunRating_list.add(sunRating);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return sunRating_list;
	}

	public SunRating querySunRatingById(String sunRating_id) {
		SunRating sunRating = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from sunRatings where sunRating_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sunRating_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sunRating = new SunRating();

				sunRating.setRating(rs.getString("rating"));
				sunRating.setRating(rs.getString("luxury_level"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return sunRating;
	}

	public void addSunRating(SunRating sunRating) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "insert into sun_rating values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sunRating.getRating());
			stmt.setString(2, sunRating.getLuxury_level());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void updateSunRating(SunRating sunRating) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "update sun_rating set luxury_level=?  where rating=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, sunRating.getLuxury_level());
			stmt.setString(2, sunRating.getRating());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

}
