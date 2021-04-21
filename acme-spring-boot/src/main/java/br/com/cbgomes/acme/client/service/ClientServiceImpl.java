package br.com.cbgomes.acme.client.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.ClientDTO;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.exception.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public List<Client> getAll() {
		if (repository.findAll().isEmpty()){
			throw new EntityNotFoundException("Client not found");
		}else {
			return repository.findAll();
		}
		
	}

	public Client getById(Long id) {
		return repository.findById(id).orElseThrow(() 
				-> new EntityNotFoundException("Client ID not found"));
	}


	@Override
	public Client getByEmail(String email) {
		return this.repository.findByEmail(email).orElseThrow(() 
				-> new EntityNotFoundException("Client not found"));
	}

	@Override
	public void removeById(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Client removeByEmail(String email) {
		Client client = this.getByEmail(email);
		this.removeById(client.getId());
		return client;
	}

	@Override
	public Client create(Client client) {
		return this.repository.save(client);
	}

	
}
