package br.com.cbgomes.acme.client.service;


import br.com.cbgomes.acme.client.domain.SavingsAccount;

public interface SavingsAccountService {

	public double getwithdraw(String agency, String accontNumber);//
	
	public void withdrawMoney(SavingsAccount account, double amount);//
	
	public void depositMoney(SavingsAccount account, double amount);//
	
	public SavingsAccount createAccount(SavingsAccount account, Long clinetId);
	
	SavingsAccount unlinkCliente(SavingsAccount account, Long clinetId);
	
	SavingsAccount getById(Long id);
	
	void removeById(Long id);
	
	void applyInterest(SavingsAccount account);
}
