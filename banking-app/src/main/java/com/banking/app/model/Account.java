package com.banking.app.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int acc_number;
	@Column
	private double acc_balance;
	@Column(name = "userid")
	private int userid;
	@Column
	private String acc_type;

	@JsonManagedReference
	@OneToMany(mappedBy = "account",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Payee> payees;
	//
	public Account() {

	}

	public Account(double acc_balance, int userid, String acc_type) {
		super();
		this.acc_balance = acc_balance;
		this.userid = userid;
		this.acc_type = acc_type;
	}

	public int getAcc_number() {
		return acc_number;
	}

	public void setAcc_number(int acc_number) {
		this.acc_number = acc_number;
	}

	public double getAcc_balance() {
		return acc_balance;
	}

	public void setAcc_balance(double acc_balance) {
		this.acc_balance = acc_balance;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}


}