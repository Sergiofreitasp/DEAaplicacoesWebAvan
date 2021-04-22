package br.com.cbgomes.acme.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.CurrentAccount;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long>{

	CurrentAccount findByAgenciaAndNumeroConta(String agencia, String numeroConta);
	
}
