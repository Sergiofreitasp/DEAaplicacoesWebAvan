package com.cbgomes;

import java.time.LocalDateTime;

public class Transaction {

	private Account account;
	private Double value;
	private String dateTimeTrasaction;
	private String typeTransaction;
	public Transaction() {
		super();
	}
	public Transaction(Account account, Double value, String dateTimeTrasaction, String typeTransaction) {
		super();
		this.account = account;
		this.value = value;
		this.dateTimeTrasaction = dateTimeTrasaction;
		this.typeTransaction = typeTransaction;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getDateTimeTrasaction() {
		return dateTimeTrasaction;
	}
	public void setDateTimeTrasaction(String dateTimeTrasaction) {
		this.dateTimeTrasaction = dateTimeTrasaction;
	}
	public String getTypeTransaction() {
		return typeTransaction;
	}
	public void setTypeTransaction(String typeTransaction) {
		this.typeTransaction = typeTransaction;
	}
	@Override
	public String toString() {
		return "Transaction [account=" + account + ", value=" + value + ", dateTimeTrasaction=" + dateTimeTrasaction
				+ ", typeTransaction=" + typeTransaction + "]";
	}
	
	
}
