package com.proj425.service.impl;

import com.proj425.dao.ClientDAO;
import com.proj425.dao.impl.ClientDAO_Impl;
import com.proj425.domain.Client;
import com.proj425.domain.Page;
import com.proj425.service.ClientService;
import com.proj425.utils.CommUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: Siren Date: 4/16/13 Time: 6:47 PM To change
 * this template use File | Settings | File Templates.
 */

public class ClientServiceImpl implements ClientService {
	private ClientDAO dao = new ClientDAO_Impl();

	public List<Client> findAllClients(Page page) {

		int totalRows = 1;
		totalRows = dao.getTotalRows();
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryAllClients(page);
	}

	public Client findClientById(String id) {
		return dao.queryClientById(id);
	}

	public List<Client> findClientByCondition(Client client, Page page) {

		int totalRows = 1;
		totalRows = dao.getTotalRowsByCondition(client);
		page.setTotalRows(totalRows);
		page.init();
		return dao.queryClientByCondition(client, page);
	}

	public void addClient(Client client) {
		client.setClient_id(CommUtils.getId()); // set ID.
		dao.addClient(client);
	}

	public void deleteClient(String client_id) {
		dao.deleteClient(client_id);
	}

	public void deleteClientSet(String client_id_set) {
		dao.deleteClientSet(client_id_set);

	}

	public void updateClient(Client client) {
		dao.updateClient(client);
	}
}
