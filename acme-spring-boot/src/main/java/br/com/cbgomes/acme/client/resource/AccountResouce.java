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
@RequestMapping("/api/currentaccount")
public class AccountResouce {
	
	@Autowired
	private CurrentAccountService service;

	@GetMapping(value = "/getCurrentAccount/{id}")
	public ResponseEntity<AccountDTO> getCurrentAccountByID(@PathVariable Long id){
	   return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.getById(id)));
	}


	@PostMapping(value = "/createAccount/{clientId}")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId ) {
		return ResponseEntity.ok(AccountConverterDTO.convertToAccountDTO(service.createAccount(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), clientId)));
	}


	@DeleteMapping(value = "/delete/{clientId}")
	public ResponseEntity<Void> deleteAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId) {

		this.service.removeById(this.service.unlinkCliente(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), clientId).getId());
		return ResponseEntity.ok().build();
	}


	@PutMapping (value = "/update/{id}/{clientId}")
	public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO dto, @PathVariable Long id, @PathVariable Long clientId) { 
		CurrentAccount ca =this.service.getById(id);
		
		ca.setAgencia(dto.getNumberAgency());
		ca.setNumeroConta(dto.getNumberAccount());
		ca.setSaldo(dto.getBalance());

		Client cli = ClientConverterDTO.modelMapper().map(dto.getClient(), Client.class);
		ca.setClient(cli);
		
		return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.createAccount(ca, clientId)));
		
	}
	
	
	
	//Depositar
	@PostMapping(value = "/deposit/{amount}")
	public ResponseEntity<Void> deposit(@RequestBody AccountDTO dto, @PathVariable Double amount) {
		this.service.depositMoney(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), amount);
		return ResponseEntity.ok().build();
				
	}
		
	//Sacar
	@PostMapping(value = "/withdraw/{amount}")
	public ResponseEntity<Void> withdrawMoney( @RequestBody AccountDTO dto, @PathVariable Double amount) {
		this.service.withdrawMoney(AccountConverterDTO.modelMapper().map(dto, CurrentAccount.class), amount);
		return ResponseEntity.ok().build();
	}
		

	//Transferencia
	@PostMapping(value = "/transfer/{amount}")
	public ResponseEntity<Void>transferencia(@RequestBody AccountDTO dtoO, @RequestBody AccountDTO dtoD, @PathVariable Double amount) {
		this.service.transferMoney(AccountConverterDTO.modelMapper().map(dtoO, CurrentAccount.class), AccountConverterDTO.modelMapper().map(dtoD, CurrentAccount.class), amount);
		return ResponseEntity.ok().build();
		}
		
	//Saldo
	@GetMapping(value = "/check_balance/{agency}/{accountNumber}")
	public ResponseEntity<Double> checkBalance( @PathVariable  String agency, @PathVariable String accountNumber){
		return ResponseEntity.ok().body(this.service.getwithdraw(agency, accountNumber));
			
		}
}
