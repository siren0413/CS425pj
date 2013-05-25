package com.proj425.dao;

import java.util.List;

import com.proj425.domain.Agent;
import com.proj425.domain.AgentAccount;
import com.proj425.domain.Page;

public interface AgentDAO {
	
	
	List<Agent> queryAllAgents(Page page);

    Agent queryAgentById(String agent_id);
    
    List<Agent> queryAgentByCondition(Agent agent, Page page);
    
    void addAgent(Agent agent);

    void deleteAgent(String agent_id);
    
    void deleteAgentSet(String agent_id_set);

    void updateAgent(Agent agent);
    
    int getTotalRows();
    
    int getTotalRowsByCondition(Agent agent);
    
    List<Agent> getClientsPerAgent();

}
