package br.com.cbgomes.acme.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import br.com.cbgomes.acme.client.repository.CurrentAccountRepository;
import br.com.cbgomes.acme.client.repository.SavingsAccountRepository;
import br.com.cbgomes.acme.exception.EntityNotFoundException;
import br.com.cbgomes.acme.exception.ValidationException;

public class SavingsAccountServiceImpl implements SavingsAccountService{

	@Autowired
	private SavingsAccountRepository repository;
	
	@Autowired
	private ClientService clinetService;
	
	
	@Override
	public double getwithdraw(String agency, String accontNumber) {
		Account account = this.loadAccountForNumber(agency, accontNumber);
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
	public void withdrawMoney(SavingsAccount account, double amount) {
		if (account.getSaldo() < amount) {
			throw new ValidationException("insufficient balance");
		}
		account.setSaldo(account.getSaldo() - amount);
		this.repository.saveAndFlush(account);
		//ADICIONAR TRANSAÇÃO 
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void depositMoney(SavingsAccount account, double amount) {
		account.setSaldo(account.getSaldo()+amount);
		this.repository.saveAndFlush(account);
		// ADICIONAR TRANSAÇÃO
		
	}

	@Override
	public SavingsAccount createAccount(SavingsAccount account, Long clinetId) {
		Client client = this.clinetService.getById(clinetId);
		account.setClient(client);
		return this.repository.save(account);
	}

	@Override
	public SavingsAccount unlinkCliente(SavingsAccount account, Long clinetId) {
		Client client = this.clinetService.getById(clinetId);
		if(client != account.getClient()) {
			throw new ValidationException("Client id and account does not match");
		}
		account.setSaldo(0);
		account.setClient(null);
		return this.repository.save(account);
	}

	@Override
	public SavingsAccount getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account ID not found"));
	}

	@Override
	public void removeById(Long id) {
		SavingsAccount account = this.getById(id);
		if(account.getClient() != null) {
			throw new ValidationException("To remove this account, unlink the client!");
		}
		this.repository.deleteById(id);
		
	}

	@Override
	public void applyInterest(SavingsAccount account) {
		account.setSaldo(account.getSaldo() + (account.getSaldo()*0.002));
	}

}
