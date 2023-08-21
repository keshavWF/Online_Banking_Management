package com.banking.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int referenceID;
	@Column(nullable = false)
	private int userName;
	@Column(nullable = false)
	private int Amount;
	@Column(nullable = false)
	private String Payee;
	@Column(nullable = false)
	private String Date;
	private String Remarks;
	

}
