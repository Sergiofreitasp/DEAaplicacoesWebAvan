package br.com.cbgomes.acme.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findBycaccount(Account account);
}
