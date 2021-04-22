package br.com.cbgomes.acme.client.service;

import java.util.List;

import br.com.cbgomes.acme.client.domain.CourrentTransaction;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import br.com.cbgomes.acme.client.domain.SavingsTransaction;

public interface TransactionService {

	public List<CourrentTransaction> getCourrentTransactions(String agency, String accountNumber);
	
	public void recordsCTransactions(String type, CurrentAccount ca, Double value);
	
	public List<SavingsTransaction> getSavingsTransactions(String agency, String accountNumber);
	
	public void recordsSTransactions(String type, SavingsAccount sa, Double value);
}
