package br.com.cbgomes.acme.client.service;

import java.util.List;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;

public interface AccountService {
	
public double getwithdraw(String agency, String accontNumber);
	
	public void withdrawMoney(Account account, double amount);
	
	public void depositMoney(Account account, double amount);
	
	public void transferMoney(Account accountOrigin, Account accountDestiny,double amount);
	
	List<Account> getAll();
	
	Account getByNumberAccount(Long numberAccount);
	
	void removeByNumberAccount(int numberAccount) throws Exception;
	
	Account create(AccountDTO dto);

	List<Account> getByTypeAccount(Long typeAccount);

	void saveAccount(Account accountRequest);

	Account createAccount(Client client);


}

