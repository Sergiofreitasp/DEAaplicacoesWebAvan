/**
 * 
 */
package br.com.cbgomes.acme.client.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.ClientRepository;

/**
 * @author cbgomes
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientResource {
	
	@Autowired
	private ClientRepository repository;
	

	//put
	
	@GetMapping
	public List<Client> getAllClients(){
		List<Client> clients = this.repository.findAll();
		return clients;
	}
	
	@GetMapping(path = {"/{id}"})
	public Client getClientById(@PathVariable long id){
	   /*return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());*/
		return repository.findById(id).get();
	}
	
	@PostMapping
	public Client createClient(@RequestBody Client client) {
		return repository.save(client);
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<String> deleteClient(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado");
				}).orElse(ResponseEntity.badRequest().body("client nao encontrado"));
		//this.repository.deleteById(id);
	}
	
	@PutMapping
	public ResponseEntity<String> update( @RequestBody Client client, @RequestParam ("id") Long id) {
		return repository.findById(id)
		           .map(record -> {
		               repository.save(client);
		               return ResponseEntity.ok().body("foi");
		           }).orElse(ResponseEntity.badRequest().body("ID Nao encontrado"));
	}
}











