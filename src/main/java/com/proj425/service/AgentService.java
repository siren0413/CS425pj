package com.proj425.service;

import java.util.List;

import com.proj425.domain.Agent;
import com.proj425.domain.Page;

public interface AgentService {

    List<Agent> findAllAgents(Page page);
    
    Agent findAgentById(String id);
    
    List<Agent> findAgentByCondition(Agent agent, Page page);
    
    void addAgent(Agent agent);
    
    void deleteAgent(String agent_id);
    
    void deleteAgentSet(String agent_id_set);

    void updateAgent(Agent agent);
    
    List<Agent> findClientPerAgent();

}
