package br.com.cbgomes.acme.client.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.com.cbgomes.acme.client.domain.CourrentTransaction;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import br.com.cbgomes.acme.client.domain.SavingsTransaction;
import br.com.cbgomes.acme.client.repository.CourrentTransactionRepository;
import br.com.cbgomes.acme.client.repository.CurrentAccountRepository;
import br.com.cbgomes.acme.client.repository.SavingsTransactionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private CourrentTransactionRepository c_repository;
	
	@Autowired
	private SavingsTransactionRepository s_repository;
	
	@Autowired
	private CurrentAccountService currentAccount_s;
	
	@Autowired
	private SavingsAccountService savingsAccount_s;
	
	public List<CourrentTransaction> getCourrentTransactions(String agency, String accountNumber) {
		CurrentAccount ca = currentAccount_s.loadAccountForNumber(agency, accountNumber);
		return c_repository.findBycaccount(ca);
	}
	
	public void recordsCTransactions(String type, CurrentAccount ca, Double value) {
		Date data = new Date(System.currentTimeMillis());
		CourrentTransaction t = new CourrentTransaction();
		t.setId(null);
		t.setDate(data);
		t.setType(type);
		t.setCaccount(ca);
		t.setValue(value);
		this.c_repository.saveAndFlush(t);
		
		

	}
	
	public List<SavingsTransaction> getSavingsTransactions(String agency, String accountNumber) {
		SavingsAccount sa = savingsAccount_s.loadAccountForNumber(agency, accountNumber);
		return s_repository.findBysaccount(sa);
	}
	
	public void recordsSTransactions(String type, SavingsAccount sa, Double value) {
		Date data = new Date(System.currentTimeMillis());
		SavingsTransaction t = new SavingsTransaction();
		t.setId(null);
		t.setDate(data);
		t.setType(type);
		t.setSaccount(sa);
		t.setValue(value);
		this.s_repository.saveAndFlush(t);
		
		

	}
	
}
