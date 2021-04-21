package br.com.cbgomes.acme.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.AccountRepository;
import br.com.cbgomes.acme.exception.EntityNotFoundException;
import br.com.cbgomes.acme.exception.ValidationException;

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
			throw new EntityNotFoundException("Accont does not exist");
		}
		return account;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void withdrawMoney(Account account, double amount) {
		account.setSaldo(account.getSaldo() - amount);
		this.repository.saveAndFlush(account);
		//ADICIONAR TRANSAÇÃO 
		
	}

	@Override
	public void depositMoney(Account account, double amount) {
		if (account.getSaldo() < 0) {
			account.setSaldo(amount -((account.getSaldo()*(-1))+amount*0.005));
		}else {
			account.setSaldo(account.getSaldo()+amount);
		}
		this.repository.saveAndFlush(account);
		// ADICIONAR TRANSAÇÃO
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void transferMoney(Account accountOrigin, Account accountDestiny, double amount) {
		if (accountOrigin.getSaldo() < amount) {
			throw new ValidationException("insufficient balance for transfer");
		}
		accountOrigin.setSaldo(accountOrigin.getSaldo() - amount);
		this.repository.saveAndFlush(accountOrigin);
		
		this.depositMoney(accountDestiny, amount);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account createAccount(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

}
