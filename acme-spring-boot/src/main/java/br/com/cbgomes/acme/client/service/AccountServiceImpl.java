package br.com.cbgomes.acme.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.dto.AccountDTO;
import br.com.cbgomes.acme.client.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

		
	@Autowired
	private AccountRepository repository;
	


	@Override //Saldo
	public double getwithdraw(String agency, String accountNumber) {
		Account account = this.loadAccountForNumber(agency, accountNumber);
		return account.getBalance();
	}

	private Account loadAccountForNumber(String agency, String accountNumber) {
		Account account = repository.findByAgencyAndAccountNumber(agency, accountNumber);
		if (account == null) {
			//throw new AplicacaoException(ExceptionValidacoes.ERRO_CONTA_INEXISTENTE);
		}
		return account;
	}

	@Override //Retirar Dinheiro - Sacar
	public void withdrawMoney(Account account, double amount) {
	
	}

	@Override //Depositar
	public void depositMoney(Account account, double amount) {
	
	}

	@Override //Transferencia
	public void transferMoney(Account accountOrigin, Account accountDestiny, double amount) {
		
	}

	@Override
	public Account createAccount(Client client) {
		return null;
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getByNumberAccount(Long numberAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeByNumberAccount(int numberAccount) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account create(AccountDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getByTypeAccount(Long typeAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAccount(Account accountRequest) {
		// TODO Auto-generated method stub
		
	}

	
}
