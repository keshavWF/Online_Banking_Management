package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class Transaction {
	@Id
	@GeneratedValue
	private int referenceID;
	@Column(nullable = false)
	private int userID;
	@Column(nullable = false)
	private int Amount;
	@Column(nullable = false)
	private String Payee;
	@Column(nullable = false)
	private String Date;
	private String Remarks;
	

}
