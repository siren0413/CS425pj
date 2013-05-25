package com.proj425.service;

import com.proj425.domain.AgentAccount;

public interface AgentAccountService {
	
	AgentAccount findAgentAccount(String username, String password);
	
}
