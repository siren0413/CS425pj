package com.proj425.service;

import java.util.List;

import com.proj425.domain.Client;
import com.proj425.domain.Page;

/**
 * Created with IntelliJ IDEA.
 * User: Siren
 * Date: 4/16/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ClientService {

    List<Client> findAllClients(Page page);
    
    Client findClientById(String id);
    
    List<Client> findClientByCondition(Client client, Page page );
    
    void addClient(Client client);
    
    void deleteClient(String client_id);
    
    void deleteClientSet(String client_id_set);
    
    void updateClient(Client client);

}
