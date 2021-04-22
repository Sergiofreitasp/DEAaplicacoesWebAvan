package br.com.cbgomes.acme.client.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.dto.AccountConverterDTO;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;
import br.com.cbgomes.acme.client.service.CurrentAccountService;
import br.com.cbgomes.acme.client.service.TransactionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/Transaction")
public class TransactionResource {

	@Autowired
	private TransactionService service;
	
	
	@GetMapping(value = "/getTransactionCurrent/{id}")
	public ResponseEntity<List<TansactionDTO>> getCurrentTansactionByaccount( @PathVariable  String agency, @PathVariable String accountNumber){
	   return ResponseEntity.ok().body(TransactionConverterDTO.convertToAccountDTO(service.getCourrentTransactions(agency, accountNumber)));
	}
	
}
