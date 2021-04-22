package br.com.cbgomes.acme.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

	SavingsAccount findByAgenciaAndNumeroConta(String agencia, String numeroConta);
}
