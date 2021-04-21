package br.com.cbgomes.acme.client.domain.dto;

public class AccountDTO {
	
	private ClientDTO client;


	private String numberAccount;

	private String numberAgency;

	private Double balance;


	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public String getNumberAccount() {
		return numberAccount;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public String getNumberAgency() {
		return numberAgency;
	}

	public void setNumberAgency(String numberAgency) {
		this.numberAgency = numberAgency;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}



}
