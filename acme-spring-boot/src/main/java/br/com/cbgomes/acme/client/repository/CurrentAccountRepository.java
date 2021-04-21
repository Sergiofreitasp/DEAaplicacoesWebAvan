package br.com.cbgomes.acme.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.CurrentAccount;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long>{

	Account findByAgencyAndAccountNumber(String agency, String accountNumber);
	
}
