/**
 * 
 */
package br.com.cbgomes.acme.client.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.ClientConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.ClientDTO;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.client.service.ClientService;
import br.com.cbgomes.acme.client.service.ClientServiceImpl;

/**
 * @author cbgomes
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	

	@GetMapping
	public ResponseEntity<List<ClientDTO>> getAllClients(){
		return ResponseEntity.ok().body(ClientConverterDTO.converterListClients(service.getAll()));
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<ClientDTO> getClientById(@PathVariable long id){
	   return ResponseEntity.ok().body(ClientConverterDTO.converterToClientDTO(service.getById(id)));
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO dto) {
		return ResponseEntity.ok().body(ClientConverterDTO.converterToClientDTO(service.create(ClientConverterDTO.modelMapper().map(dto, Client.class))));
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<String> deleteClient(@PathVariable long id) {		
		service.removeById(service.getById(id).getId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping 
	public ResponseEntity<ClientDTO> update( @RequestBody ClientDTO dto, @PathVariable
	  ("id") Long id) { 
		
		return ResponseEntity.ok().body(ClientConverterDTO.converterToClientDTO(service.create(service.getById(id))));
	}
	 
	
}











