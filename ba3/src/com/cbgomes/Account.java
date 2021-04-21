package com.cbgomes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private Long id;
	private String number;
	private String agency;
	private Double balance;
	private Client client;
	private List<Transaction> transacoes = new ArrayList<>(); 
	public Account() {
		super();
	}
	public Account(Long id, String number, String agency, Double balance, Client client, List<Transaction> transacoes) {
		super();
		this.id = id;
		this.number = number;
		this.agency = agency;
		this.balance = balance;
		this.client = client;
		this.transacoes = transacoes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Transaction> getTransacoes() {
		return transacoes;
	}
	public void setTransacoes(List<Transaction> transacoes) {
		this.transacoes = transacoes;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", agency=" + agency + ", balance=" + balance + ", client="
				+ client + ", transacoes=" + transacoes + "]";
	}
	
	
	
	//----------------------------------------------------------------------------
	
	public Double sacar(Double value) {
		if (this.balance >= value) {
			this.balance -= value;
		}
		this.transacoes.add(createTransaction(this, value, "Saque"));
		return this.balance;
	}

	public Double depositar(Double value) {
		this.transacoes.add(createTransaction(this, value, "deposito"));
		return this.balance += value;
	}
	
	public Double transferencia(Account destino, Double value) {
		if(this.balance >= value) {
			this.sacar(value);
			destino.depositar(value);
		}
		return this.balance;
	}

	private Transaction createTransaction(Account account, Double value, String typeTransaction) {
		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setValue(value);
		transaction.setTypeTransaction(typeTransaction);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		transaction.setDateTimeTrasaction(LocalDateTime.now().format(formatter));
		
		return transaction;
	}
	
}
