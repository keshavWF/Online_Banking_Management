package com.banking.app.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;


@Entity
@Table(name = "account")
public class Account {
	@Id
	private int accountNumber;
	@Column
	private double accountBalance;
	@Column(name = "userId", nullable = false)
	private int userID;
	@Column(nullable = false)
	private String accountType;

	@JsonManagedReference
	@OneToMany(mappedBy = "account",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payee> payees;

	public Account() {

	}

	public Account(int accountNumber, double accountBalance, int userID, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.userID = userID;
		this.accountType = accountType;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}