package br.com.cbgomes.acme.client.service;

import java.util.List;

import br.com.cbgomes.acme.client.domain.Account;
import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.domain.CurrentAccount;

public interface CurrentAccountService {

	
	public double getwithdraw(String agency, String accontNumber);//
	
	public void withdrawMoney(CurrentAccount account, double amount);//
	
	public void depositMoney(CurrentAccount account, double amount);//
	
	public void transferMoney(CurrentAccount accountOrigin, CurrentAccount accountDestiny,double amount);//
	
	public CurrentAccount createAccount(CurrentAccount account, Long clinetId);
	
	CurrentAccount unlinkCliente(CurrentAccount account, Long clinetId);
	
	CurrentAccount getById(Long id);
	
	void removeById(Long id);
}
