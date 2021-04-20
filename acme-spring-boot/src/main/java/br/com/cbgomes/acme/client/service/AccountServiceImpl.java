package br.com.cbgomes.acme.client.service;

import org.springframework.beans.factory.annotation.Autowired;



import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.AccountRepository;

public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository repository;
	
	@Override
	public double getwithdraw(String agency, String accountNumber) {
		Account account = this.loadAccountForNumber(agency, accountNumber);
		return account.getSaldo();
		
	}
	
	public Account loadAccountForNumber(String agency, String accountNumber) {
		Account account = repository.findByAgencyAndAccountNumber(agency, accountNumber);
		if (account == null) {
			//throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INEXISTENTE);
		}
		return account;
	}

	@Override
	public void withdrawMoney(Account account, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depositMoney(Account account, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferMoney(Account accountOrigin, Account accountDestiny, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account createAccount(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
