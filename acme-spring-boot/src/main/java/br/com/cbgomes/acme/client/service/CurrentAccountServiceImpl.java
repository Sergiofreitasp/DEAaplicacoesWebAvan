package br.com.cbgomes.acme.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.CurrentAccount;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.client.repository.CurrentAccountRepository;
import br.com.cbgomes.acme.exception.EntityNotFoundException;
import br.com.cbgomes.acme.exception.ValidationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentAccountServiceImpl implements CurrentAccountService{

	@Autowired
	private CurrentAccountRepository repository;
	
	@Autowired
	private ClientService clinetService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public double getwithdraw(String agency, String accountNumber) {
		Account account = this.loadAccountForNumber(agency, accountNumber);
		return account.getSaldo();
		
	}
	
	public CurrentAccount loadAccountForNumber(String agency, String accountNumber) {
		CurrentAccount account = repository.findByAgenciaAndNumeroConta(agency, accountNumber);
		if (account == null) {
			throw new EntityNotFoundException("Accont does not exist");
		}
		return account;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void withdrawMoney(CurrentAccount account, double amount) {
		account.setSaldo(account.getSaldo() - amount);
		this.repository.saveAndFlush(account);
		this.transactionService.recordsCTransactions("withdrawMoney",account, amount);
		
	}

	@Override
	public void depositMoney(CurrentAccount account, double amount) {
		if (account.getSaldo() < 0) {
			account.setSaldo(amount -((account.getSaldo()*(-1))+amount*0.005));
		}else {
			account.setSaldo(account.getSaldo()+amount);
		}
		this.repository.saveAndFlush(account);
		this.transactionService.recordsCTransactions("Deposit",account, amount);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void transferMoney(CurrentAccount accountOrigin, CurrentAccount accountDestiny, double amount) {
		
		if (accountOrigin.getSaldo() < amount) {
			throw new ValidationException("insufficient balance for transfer");
		}
		double tax = 0;
		if(accountOrigin.getAgencia() != accountDestiny.getAgencia()) {
			tax = 0.0001;
		}
		
		accountOrigin.setSaldo(accountOrigin.getSaldo() - amount);
		this.repository.saveAndFlush(accountOrigin);
		this.transactionService.recordsCTransactions("Transferiu",accountOrigin, amount);
		accountOrigin.setSaldo(accountOrigin.getSaldo() - (amount * tax));
		this.repository.saveAndFlush(accountOrigin);
		this.transactionService.recordsCTransactions("Taxa de transferencia",accountOrigin, amount * tax);
		
		accountDestiny.setSaldo(accountDestiny.getSaldo() + amount);
		this.repository.saveAndFlush(accountDestiny);
		this.transactionService.recordsCTransactions("Recebido",accountDestiny, amount);
	}

	@Override
	public CurrentAccount createAccount(CurrentAccount account, Long clinetId) {
		Client client = this.clinetService.getById(clinetId);
		account.setClient(client);
		return this.repository.save(account);
	}
	
	public CurrentAccount unlinkCliente(CurrentAccount account, Long clinetId) {
		Client client = this.clinetService.getById(clinetId);
		if(client != account.getClient()) {
			throw new ValidationException("Client id and account does not match");
		}
		account.setSaldo(0);
		account.setClient(null);
		return this.repository.save(account);
	}
	
	public CurrentAccount getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account ID not found"));
	}
	
	public void removeById(Long id) {
		CurrentAccount account = this.getById(id);
		if(account.getClient() != null) {
			throw new ValidationException("To remove this account, unlink the client!");
		}
		this.repository.deleteById(id);
	}

}
