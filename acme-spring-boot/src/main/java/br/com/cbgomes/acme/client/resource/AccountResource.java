package br.com.cbgomes.acme.client.resource;




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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.AccountConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;
import br.com.cbgomes.acme.client.domain.dto.ClientConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.ClientDTO;
import br.com.cbgomes.acme.client.service.AccountService;
import br.com.cbgomes.acme.client.service.ClientService;





@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/Account")
public class AccountResource {
	
		//saque withdraw money
		//deposito deposit
		//transferencia transfer
		//criar conta CreateAccount
	

	
	@Autowired
	private AccountService service;
	
	@Autowired
	private ClientService Clientservice;
	
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<AccountDTO>> getAllClients() {
		return ResponseEntity.ok(AccountConverterDTO.conveterListAccount(this.service.getAll()));
	}
	
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<AccountDTO> getAccountByNumberAccount(@PathVariable long id){
	   return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.getByNumberAccount(id)));
	}
	
	
	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto) {
		return ResponseEntity.ok(AccountConverterDTO.convertToAccountDTO(service.create(dto)));
	}
	
	
	@DeleteMapping("/{numberAccount}")
	public ResponseEntity<Void> deleteAccount(@PathVariable("numberAccount") int numberAccount) {
		try {
			this.service.removeByNumberAccount(numberAccount);
			return ResponseEntity.ok().build();
			
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	@PutMapping //UPDATE
	public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO dto, @RequestParam("numberAccount") Long numberAccount) { 
		return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO
				(this.service.getByNumberAccount(numberAccount)));
	}
	 
	
	
	
	
	
	

	

	
	
	
	
	
	
	
	
	
	
	
	

}
