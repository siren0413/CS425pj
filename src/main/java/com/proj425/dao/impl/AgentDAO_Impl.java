package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.proj425.dao.AgentDAO;
import com.proj425.domain.Agent;
import com.proj425.domain.Page;
import com.proj425.exception.DAOException;
import com.proj425.utils.CommUtils;
import com.proj425.utils.JDBC_Conn;

public class AgentDAO_Impl implements AgentDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public List<Agent> queryAllAgents(Page page) {
		List<Agent> agent_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from agents natural join position";
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();
			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());
			}

			while (rs.next()) {
				if (agent_list == null)
					agent_list = new ArrayList<Agent>();

				Agent agent = new Agent();

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

	public Agent queryAgentById(String agent_id) {

		Agent agent = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from agents natural join position where agent_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return agent;

	}

	public List<Agent> queryAgentByCondition(Agent agent, Page page) {

		List<Agent> agent_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from agents natural join position where ";

			if (agent.getFirst_nm() != null && !"".equals(agent.getFirst_nm())) {
				sql += " first_nm='" + agent.getFirst_nm() + "'" + " and ";
				count++;
			}

			if (agent.getLast_nm() != null && !"".equals(agent.getLast_nm())) {
				sql += " last_nm='" + agent.getLast_nm() + "'" + " and ";
				count++;
			}

			if (agent.getGender() != null && !"".equals(agent.getGender())) {
				sql += " gender='" + agent.getGender() + "'" + " and ";
				count++;
			}

			if (agent.getZip() != null && !"".equals(agent.getZip())) {
				sql += " zip='" + agent.getZip() + "'" + " and ";
				count++;
			}

			if (agent.getEmail() != null && !"".equals(agent.getEmail())) {
				sql += " email='" + agent.getEmail() + "'" + " and ";
				count++;
			}

			if (agent.getPhone_number() != null && !"".equals(agent.getPhone_number())) {
				sql += " phone_number='" + agent.getPhone_number() + "'" + " and ";
				count++;
			}

			if (agent.getPosition() != null && !"".equals(agent.getPosition())) {
				sql += " position='" + agent.getPosition() + "'" + " and ";
				count++;
			}

			if (agent.getDob() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dob_str = sdf.format(agent.getDob());
				sql += " dob= " + "to_date('" + dob_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllAgents(page);

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();
			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());
			}

			while (rs.next()) {
				if (agent_list == null)
					agent_list = new ArrayList<Agent>();

				Agent agent1 = new Agent();

				agent1.setAgent_id(rs.getString("agent_id"));
				agent1.setPhone_number(rs.getString("phone_number"));
				agent1.setEmail(rs.getString("email"));
				agent1.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				agent1.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("dob"));

				agent1.setDob(dob);
				agent1.setZip(rs.getString("zip"));
				agent1.setGender(rs.getString("gender"));
				agent1.setPosition(rs.getString("position"));

				agent_list.add(agent1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return agent_list;
	}

	public void addAgent(Agent agent) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "insert into agents values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent.getAgent_id());
			stmt.setString(2, agent.getPhone_number());
			stmt.setString(3, agent.getEmail());
			stmt.setString(4, agent.getFirst_nm());
			stmt.setString(5, agent.getLast_nm());
			stmt.setDate(6, new java.sql.Date(agent.getDob().getTime()));
			stmt.setString(7, agent.getZip());
			stmt.setString(8, agent.getGender());
			stmt.executeUpdate();

			sql = "insert into position values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent.getAgent_id());
			stmt.setString(2, agent.getPosition());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteAgent(String agent_id) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from agents where agent_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent_id);
			stmt.executeUpdate();

			sql = "delete from position where agent_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent_id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void deleteAgentSet(String agent_id_set) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from agents where agent_id in ( " + agent_id_set + " )";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public void updateAgent(Agent agent) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "update agents set phone_number=?, email=?, first_nm=?, last_nm=?, dob=?, zip=?, gender=? where agent_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent.getPhone_number());
			stmt.setString(2, agent.getEmail());
			stmt.setString(3, agent.getFirst_nm());
			stmt.setString(4, agent.getLast_nm());
			stmt.setDate(5, new java.sql.Date(agent.getDob().getTime()));
			stmt.setString(6, agent.getZip());
			stmt.setString(7, agent.getGender());
			stmt.setString(8, agent.getAgent_id());
			stmt.executeUpdate();

			sql = "update position set position = ? where agent_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, agent.getPosition());
			stmt.setString(2, agent.getAgent_id());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	public int getTotalRows() {
		int totalRows = 0;
		try {

			conn = JDBC_Conn.getConnection();
			String sql = "select count(*) from agents ";
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

	public int getTotalRowsByCondition(Agent agent) {
		int count = 0;
		int totalRows = 0;
		try {

			String sql = "select count(*) from agents natural join position where ";

			if (agent.getFirst_nm() != null && !"".equals(agent.getFirst_nm())) {
				sql += " first_nm='" + agent.getFirst_nm() + "'" + " and ";
				count++;
			}

			if (agent.getLast_nm() != null && !"".equals(agent.getLast_nm())) {
				sql += " last_nm='" + agent.getLast_nm() + "'" + " and ";
				count++;
			}

			if (agent.getGender() != null && !"".equals(agent.getGender())) {
				sql += " gender='" + agent.getGender() + "'" + " and ";
				count++;
			}

			if (agent.getZip() != null && !"".equals(agent.getZip())) {
				sql += " zip='" + agent.getZip() + "'" + " and ";
				count++;
			}

			if (agent.getEmail() != null && !"".equals(agent.getEmail())) {
				sql += " email='" + agent.getEmail() + "'" + " and ";
				count++;
			}

			if (agent.getPhone_number() != null && !"".equals(agent.getPhone_number())) {
				sql += " phone_number='" + agent.getPhone_number() + "'" + " and ";
				count++;
			}

			if (agent.getPosition() != null && !"".equals(agent.getPosition())) {
				sql += " position='" + agent.getPosition() + "'" + " and ";
				count++;
			}

			if (agent.getDob() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dob_str = sdf.format(agent.getDob());
				sql += " dob= " + "to_date('" + dob_str + "'," + "'MM/dd/yyyy')" + " and ";
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

	public List<Agent> getClientsPerAgent() {

		List<Agent> agent_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select agent_id, first_nm, last_nm, phone_number, bb.num from agents natural join (select agent_id, count(booking_id) num from bookings group by agent_id) bb order by bb.num desc";
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (agent_list == null)
					agent_list = new ArrayList<Agent>();

				Agent agent = new Agent();

				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPhone_number(rs.getString("phone_number"));
				agent.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				agent.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));
				agent.setNbr_clients(rs.getInt("num"));
				
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

}
