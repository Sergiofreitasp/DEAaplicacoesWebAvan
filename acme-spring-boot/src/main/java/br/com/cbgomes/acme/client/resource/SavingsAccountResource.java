package br.com.cbgomes.acme.client.resource;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import br.com.cbgomes.acme.client.domain.dto.AccountConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;
import br.com.cbgomes.acme.client.domain.dto.ClientConverterDTO;
import br.com.cbgomes.acme.client.service.CurrentAccountService;
import br.com.cbgomes.acme.client.service.SavingsAccountService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/savingsaccount")
public class SavingsAccountResource {

	@Autowired
	private SavingsAccountService service;
	
	@GetMapping(value = "/getSavingtAccount/{id}")
	public ResponseEntity<AccountDTO> getSAccountByID(@PathVariable Long id){
	   return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.getById(id)));
	}
	
	@PostMapping(value = "/createSavingsAccount/{clientId}")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId ) {
		return ResponseEntity.ok(AccountConverterDTO.convertToAccountDTO(service.createAccount(AccountConverterDTO.modelMapper().map(dto, SavingsAccount.class), clientId)));
	}
	
	@DeleteMapping(value = "/delete/{clientId}")
	public ResponseEntity<Void> deleteAccount(@RequestBody AccountDTO dto, @PathVariable Long clientId) {

		this.service.removeById(this.service.unlinkCliente(AccountConverterDTO.modelMapper().map(dto, SavingsAccount.class), clientId).getId());
		return ResponseEntity.ok().build();
	}
	
	@PutMapping (value = "/update/{id}/{clientId}")
	public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO dto, @PathVariable Long id, @PathVariable Long clientId) { 
		SavingsAccount sa =this.service.getById(id);
		
		sa.setAgencia(dto.getNumberAgency());
		sa.setNumeroConta(dto.getNumberAccount());
		sa.setSaldo(dto.getBalance());

		Client cli = ClientConverterDTO.modelMapper().map(dto.getClient(), Client.class);
		sa.setClient(cli);
		return ResponseEntity.ok().body(AccountConverterDTO.convertToAccountDTO(service.createAccount(sa, clientId)));
		
	}
	
	@PostMapping(value = "/deposit/{amount}")
	public ResponseEntity<Void> deposit(@RequestBody AccountDTO dto, @PathVariable Double amount) {
		this.service.depositMoney(AccountConverterDTO.modelMapper().map(dto, SavingsAccount.class), amount);
		return ResponseEntity.ok().build();
				
	}
	
	@PostMapping(value = "/withdraw/{amount}")
	public ResponseEntity<Void> withdrawMoney( @RequestBody AccountDTO dto, @PathVariable Double amount) {
		this.service.withdrawMoney(AccountConverterDTO.modelMapper().map(dto, SavingsAccount.class), amount);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/check_balance/{agency}/{accountNumber}")
	public ResponseEntity<Double> checkBalance( @PathVariable  String agency, @PathVariable String accountNumber){
		return ResponseEntity.ok().body(this.service.getwithdraw(agency, accountNumber));
	}
	
	@PostMapping(value = "/applyInterest")
	public ResponseEntity<Void> applyInterest(@RequestBody AccountDTO dto) {
		this.service.applyInterest(AccountConverterDTO.modelMapper().map(dto, SavingsAccount.class));
		return ResponseEntity.ok().build();
				
	}
}
