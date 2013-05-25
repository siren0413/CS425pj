package com.proj425.dao.impl;

import com.proj425.dao.ClientDAO;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.exception.DAOException;
import com.proj425.utils.CommUtils;
import com.proj425.utils.JDBC_Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientDAO_Impl implements ClientDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	/**
	 * query all the clients in the database and store it in javabean.
	 * 
	 * @return List
	 * @throws Exception 
	 */
	public List<Client> queryAllClients(Page page)  {
		List<Client> client_list = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from clients";
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();

			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());

			}
			while (rs.next()) {
				if (client_list == null)
					client_list = new ArrayList<Client>();

				Client client = new Client();

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

	/**
	 * query client by client_id in database. store the client in javabean.
	 * 
	 * @param client_id
	 * @return
	 */
	public Client queryClientById(String client_id) {

		Client client = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from clients where client_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client_id);
			rs = stmt.executeQuery();
			while (rs.next()) {
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

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return client;
	}

	/**
	 * query clients by certain conditions.
	 * 
	 */

	public List<Client> queryClientByCondition(Client client, Page page) {

		List<Client> client_list = null;
		int count = 0;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from clients where ";

			if (client.getFirst_nm() != null && !"".equals(client.getFirst_nm())) {
				sql += " first_nm='" + client.getFirst_nm() + "'" + " and ";
				count++;
			}

			if (client.getLast_nm() != null && !"".equals(client.getLast_nm())) {
				sql += " last_nm='" + client.getLast_nm() + "'" + " and ";
				count++;
			}

			if (client.getGender() != null && !"".equals(client.getGender())) {
				sql += " gender='" + client.getGender() + "'" + " and ";
				count++;
			}

			if (client.getZip() != null && !"".equals(client.getZip())) {
				sql += " zip='" + client.getZip() + "'" + " and ";
				count++;
			}

			if (client.getEmail() != null && !"".equals(client.getEmail())) {
				sql += " email='" + client.getEmail() + "'" + " and ";
				count++;
			}

			if (client.getPhone_number() != null && !"".equals(client.getPhone_number())) {
				sql += " phone_number='" + client.getPhone_number() + "'" + " and ";
				count++;
			}

			if (client.getDob() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dob_str = sdf.format(client.getDob());
				sql += " dob= " + "to_date('" + dob_str + "'," + "'MM/dd/yyyy')" + " and ";
				count++;
			}

			if (count == 0)
				return queryAllClients(page);

			int last_index = sql.lastIndexOf("and");
			sql = sql.substring(0, last_index);

			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.setMaxRows(page.getEndIndex());
			rs = stmt.executeQuery();
			
			if (page.getBeginIndex() > 0) {
				rs.absolute(page.getBeginIndex());
			}

			while (rs.next()) {
				if (client_list == null)
					client_list = new ArrayList<Client>();

				Client client1 = new Client();

				client1.setClient_id(rs.getString("client_id"));
				client1.setPhone_number(rs.getString("phone_number"));
				client1.setEmail(rs.getString("email"));
				client1.setFirst_nm(CommUtils.initCap(rs.getString("first_nm")));
				client1.setLast_nm(CommUtils.initCap(rs.getString("last_nm")));

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date dob = sdf.parse(rs.getString("dob"));

				client1.setDob(dob);
				client1.setZip(rs.getString("zip"));
				client1.setGender(rs.getString("gender"));

				client_list.add(client1);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
		return client_list;

	}

	/**
	 * add one client to database.
	 * 
	 * @param client
	 */

	public void addClient(Client client) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "insert into clients values(?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClient_id());
			stmt.setString(2, client.getPhone_number());
			stmt.setString(3, client.getEmail());
			stmt.setString(4, client.getFirst_nm());
			stmt.setString(5, client.getLast_nm());
			stmt.setDate(6, new java.sql.Date(client.getDob().getTime()));
			stmt.setString(7, client.getZip());
			stmt.setString(8, client.getGender());
			stmt.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	/**
	 * delete one client from database by client_id.
	 * 
	 * @param client_id
	 */
	public void deleteClient(String client_id) {
		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from clients where client_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client_id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}
	}

	public void deleteClientSet(String client_id_set) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "delete from clients where client_id in ( " + client_id_set + " )";
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

	}

	/**
	 * update client's information in database.
	 * 
	 * @param client
	 */
	public void updateClient(Client client) {

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "update clients set phone_number=?, email=?, first_nm=?, last_nm=?, dob=?, zip=?, gender=? where client_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getPhone_number());
			stmt.setString(2, client.getEmail());
			stmt.setString(3, client.getFirst_nm());
			stmt.setString(4, client.getLast_nm());
			stmt.setDate(5, new java.sql.Date(client.getDob().getTime()));
			stmt.setString(6, client.getZip());
			stmt.setString(7, client.getGender());
			stmt.setString(8, client.getClient_id());
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
			String sql = "select count(*) from clients ";
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

	public int getTotalRowsByCondition(Client client) {
		int count = 0;
		int totalRows = 0;
		try {
			
			
			String sql = "select count(*) from clients where ";

			if (client.getFirst_nm() != null && !"".equals(client.getFirst_nm())) {
				sql += " first_nm='" + client.getFirst_nm() + "'" + " and ";
				count++;
			}

			if (client.getLast_nm() != null && !"".equals(client.getLast_nm())) {
				sql += " last_nm='" + client.getLast_nm() + "'" + " and ";
				count++;
			}

			if (client.getGender() != null && !"".equals(client.getGender())) {
				sql += " gender='" + client.getGender() + "'" + " and ";
				count++;
			}

			if (client.getZip() != null && !"".equals(client.getZip())) {
				sql += " zip='" + client.getZip() + "'" + " and ";
				count++;
			}

			if (client.getEmail() != null && !"".equals(client.getEmail())) {
				sql += " email='" + client.getEmail() + "'" + " and ";
				count++;
			}

			if (client.getPhone_number() != null && !"".equals(client.getPhone_number())) {
				sql += " phone_number='" + client.getPhone_number() + "'" + " and ";
				count++;
			}

			if (client.getDob() != null) {

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				String dob_str = sdf.format(client.getDob());
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

}
