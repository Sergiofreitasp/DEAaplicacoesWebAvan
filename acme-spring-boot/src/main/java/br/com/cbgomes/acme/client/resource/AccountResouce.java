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
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.dto.AccountConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;
import br.com.cbgomes.acme.client.domain.dto.ClientConverterDTO;
import br.com.cbgomes.acme.client.service.ClientService;
import br.com.cbgomes.acme.client.service.CurrentAccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/Account")
public class AccountResouce {

	//saque withdraw money
	//deposito deposit
	//transferencia transfer
	//criar conta CreateAccount
	
	@Autowired
	private CurrentAccountService service;

	


	/*@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<AccountDTO>> getAllClients() {
		return ResponseEntity.ok(AccountConverterDTO.conveterListAccount(this.service.getAll()));
	}*/


	@GetMapping(path = {"/{id}"})
	public ResponseEntity<AccountDTO> getCurrentAccountByID(@PathVariable Long id){
	   return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.getById(id)));
	}


	@PostMapping
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId ) {
		return ResponseEntity.ok(AccountConverterDTO.convertToAccountDTO(service.createAccount(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), clientId)));
	}


	@DeleteMapping("/{numberAccount}")
	public ResponseEntity<Void> deleteAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId) {

		this.service.removeById(this.service.unlinkCliente(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), clientId).getId());
		return ResponseEntity.ok().build();
	}


	@PutMapping //UPDATE
	public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO dto, @RequestParam("Account id") Long id, @RequestParam("Client id") Long clientId) { 
		CurrentAccount ca =this.service.getById(id);
		
		ca.setAgencia(dto.getNumberAgency());
		ca.setNumeroConta(dto.getNumberAccount());
		ca.setSaldo(dto.getBalance());

		Client cli = ClientConverterDTO.modelMapper().map(dto.getClient(), Client.class);
		ca.setClient(cli);
		
		return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.createAccount(ca, clientId)));
		
	}
	
	//sacar depositar transferir e pegar o saldo
	
}
