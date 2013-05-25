package com.proj425.dao;

import com.proj425.domain.Client;
import com.proj425.domain.Page;

import java.util.List;


public interface ClientDAO {

    List<Client> queryAllClients(Page page);

    Client queryClientById(String client_id);
    
    List<Client> queryClientByCondition(Client client, Page page);
    
    void addClient(Client client);

    void deleteClient(String client_id);
    
    void deleteClientSet(String client_id_set);

    void updateClient(Client client);
    
    int getTotalRows();
    
    int getTotalRowsByCondition(Client client);

}
