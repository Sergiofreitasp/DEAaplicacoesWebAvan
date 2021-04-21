package br.com.cbgomes.acme.client.domain.dto;

public class AccountDTO {
	
	
private ClientDTO client;
	

	private int numberAccount;
	
	private int numberAgency;
	
	private Double balance;

	
	
	
	
	
	
	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public int getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(int numberAccount) {
		this.numberAccount = numberAccount;
	}

	public int getNumberAgency() {
		return numberAgency;
	}

	public void setNumberAgency(int numberAgency) {
		this.numberAgency = numberAgency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}


	
	
}
