package com.proj425.dao;

import com.proj425.domain.AgentAccount;

public interface AgentAccountDAO {
	
	AgentAccount queryAgentAccount(String username, String password);
	
}
