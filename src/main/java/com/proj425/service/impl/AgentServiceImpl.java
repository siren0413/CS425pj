package com.proj425.service.impl;

import java.util.List;

import com.proj425.dao.AgentDAO;
import com.proj425.dao.impl.AgentDAO_Impl;
import com.proj425.domain.Agent;
import com.proj425.domain.Page;
import com.proj425.service.AgentService;
import com.proj425.utils.CommUtils;

public class AgentServiceImpl implements AgentService {
	private AgentDAO dao = new AgentDAO_Impl();

	public List<Agent> findAllAgents(Page page) {

		int totalRows = 1;
		totalRows = dao.getTotalRows();
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryAllAgents(page);
	}

	public Agent findAgentById(String id) {
		return dao.queryAgentById(id);
	}

	public List<Agent> findAgentByCondition(Agent agent, Page page) {
		int totalRows = 1;
		totalRows = dao.getTotalRowsByCondition(agent);
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryAgentByCondition(agent, page);
	}

	public void addAgent(Agent agent) {
		agent.setAgent_id(CommUtils.getId()); // set ID.
		dao.addAgent(agent);
	}

	public void deleteAgent(String agent_id) {
		dao.deleteAgent(agent_id);
	}

	public void deleteAgentSet(String agent_id_set) {
		dao.deleteAgentSet(agent_id_set);

	}

	public void updateAgent(Agent agent) {
		dao.updateAgent(agent);
	}

	public List<Agent> findClientPerAgent() {
		
		return dao.getClientsPerAgent();
		
	}

}
