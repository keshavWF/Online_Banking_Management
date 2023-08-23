package com.banking.app.model;

import jakarta.persistence.*;

@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referenceID;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private double Amount;
	@Column(nullable = false)
	private String Payee;
	@Column(nullable = false)
	private String Date;
	private String Remarks;

	private int fromAccountNumber;

	private int toAccountNumber;


	public Transaction (String userName, double amount, String payee, String date, int fromAccountNumber, int toAccountNumber){
		this.userName = userName;
		this.Amount = amount;
		this.Payee = payee;
		this.Date = date;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
	}





	// getters and setters

	public int getReferenceID() {
		return referenceID;
	}

	public String getUserName() {
		return userName;
	}

	public double getAmount() {
		return Amount;
	}

	public String getPayee() {
		return Payee;
	}

	public String getDate() {
		return Date;
	}

	public String getRemarks() {
		return Remarks;
	}

	public int getPayerAccountNumber() {
		return fromAccountNumber;
	}

	public int getPayeeAccountNumber() {
		return toAccountNumber;
	}

	public void setReferenceID(int referenceID) {
		this.referenceID = referenceID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public void setPayee(String payee) {
		Payee = payee;
	}

	public void setDate(String date) {
		Date = date;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}

	public void setToAccountNumber(int toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
}
