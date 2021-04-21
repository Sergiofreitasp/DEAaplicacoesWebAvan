package br.com.cbgomes.acme.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cbgomes.acme.client.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Account findByAgencyAndAccountNumber(String agency, String accountNumber);

}
