package br.com.cbgomes.acme.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.CourrentTransaction;
import br.com.cbgomes.acme.client.domain.SavingsTransaction;

@Repository
public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long>{

	List<SavingsTransaction> findBysaccount(Account account);
}
