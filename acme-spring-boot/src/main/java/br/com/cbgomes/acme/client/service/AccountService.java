package br.com.cbgomes.acme.client.service;

import java.util.List;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;

public interface AccountService {

	public double getwithdraw(String agency, String accontNumber);
	
	public void withdrawMoney(Account account, double amount);
	
	public void depositMoney(Account account, double amount);
	
	public void transferMoney(Account accountOrigin, Account accountDestiny,double amount);
	
	public Account createAccount(Client client);
}
