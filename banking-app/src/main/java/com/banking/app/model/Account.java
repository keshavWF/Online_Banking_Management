package com.banking.app.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNumber;
	@Column
	private double accountBalance;
	@Column(nullable = false)
	private String accountType;

	@JsonBackReference
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "userName")
	private UserCredential userCredential;

	public Account() {

	}

	public Account(double accountBalance, int userID, String accountType) {
		super();

		this.accountBalance = accountBalance;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setUserCredential(UserCredential userCredential) { this.userCredential = userCredential; }

	public String getUserName()
	{
		return userCredential.getUsername();
	}
}