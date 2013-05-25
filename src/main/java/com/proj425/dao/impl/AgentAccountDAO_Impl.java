package com.proj425.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.proj425.dao.AgentAccountDAO;
import com.proj425.domain.Agent;
import com.proj425.domain.AgentAccount;
import com.proj425.exception.DAOException;
import com.proj425.utils.JDBC_Conn;

public class AgentAccountDAO_Impl implements AgentAccountDAO {

	

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	public AgentAccount queryAgentAccount(String username, String password) {
		
		
		AgentAccount agentAccount = null;

		try {
			conn = JDBC_Conn.getConnection();
			String sql = "select * from Agent_Account natural join Agents natural join position where user_name=? and password=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				agentAccount = new AgentAccount();
				Agent agent = new Agent();
				
				agent.setAgent_id(rs.getString("agent_id"));
				agent.setPosition(rs.getString("position"));
				agent.setFirst_nm(rs.getString("first_nm"));
				agent.setLast_nm(rs.getString("last_nm"));
				
				agentAccount.setAgent(agent);
				agentAccount.setUsername(username);
				agentAccount.setPassword(password);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException();
		} finally {
			JDBC_Conn.releaseConnection(conn, stmt, rs);
		}

		return agentAccount;
		
	}

}
