package br.com.cbgomes.acme.client.service;

import java.util.List;
import java.util.Optional;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.ClientDTO;

public interface ClientService {
	
	List<Client> getAll();
	
	Client getById(Long id);
	
	Client getByEmail(String email);
	
	void removeById(Long id);
	
	Client removeByEmail(String email);
	
	Client create(Client client);
	
	//void openAccount(Long id);
}
