package com.proj425.service.impl;

import com.proj425.dao.AgentAccountDAO;
import com.proj425.dao.impl.AgentAccountDAO_Impl;
import com.proj425.domain.AgentAccount;
import com.proj425.service.AgentAccountService;

public class AgentAccountServiceImpl implements AgentAccountService {

	private AgentAccountDAO dao = new AgentAccountDAO_Impl();
	
	public AgentAccount findAgentAccount(String username, String password) {
		return dao.queryAgentAccount(username, password);
	}

}
