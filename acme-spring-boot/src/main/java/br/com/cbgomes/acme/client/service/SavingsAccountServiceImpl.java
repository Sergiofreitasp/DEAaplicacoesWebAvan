package br.com.cbgomes.acme.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.domain.SavingsAccount;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.client.repository.CurrentAccountRepository;
import br.com.cbgomes.acme.client.repository.SavingsAccountRepository;
import br.com.cbgomes.acme.exception.EntityNotFoundException;
import br.com.cbgomes.acme.exception.ValidationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SavingsAccountServiceImpl implements SavingsAccountService{

	@Autowired
	private SavingsAccountRepository repository;
	
	@Autowired
	private ClientService clinetService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public double getwithdraw(String agency, String accontNumber) {
		Account account = this.loadAccountForNumber(agency, accontNumber);
		return account.getSaldo();
	}
	
	public SavingsAccount loadAccountForNumber(String agency, String accountNumber) {
		SavingsAccount account = repository.findByAgenciaAndNumeroConta(agency, accountNumber);
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
		this.transactionService.recordsSTransactions("Saque", account, amount);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void depositMoney(SavingsAccount account, double amount) {
		account.setSaldo(account.getSaldo()+amount);
		this.repository.saveAndFlush(account);
		this.transactionService.recordsSTransactions("Deposit", account, amount);
		
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
		this.transactionService.recordsSTransactions("Juros do mes", account, account.getSaldo()*0.002);
	}

}
