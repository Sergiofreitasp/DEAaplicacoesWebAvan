package br.com.cbgomes.acme.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.CourrentTransaction;


@Repository
public interface CourrentTransactionRepository extends JpaRepository<CourrentTransaction, Long>{

	List<CourrentTransaction> findBycaccount(Account account);
}
