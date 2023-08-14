package com.banking.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payee")
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payee_id;
    @Column
    private String FirstName;
    @Column
    private String LastName;
    @Column
    private String NickName;
    @Column
    private int AccountNumber;


    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "userid")
    private Account account;

    public Payee() {

    }

    public Payee(int payee_id, String FirstName, String LastName, String NickName, int AccountNumber) {
        super();
        this.payee_id = payee_id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.AccountNumber = AccountNumber;
    }

    public int getPayee_id() {
        return payee_id;
    }

    public void setPayee_id(int payee_id) {
        this.payee_id = payee_id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        this.NickName = nickName;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.AccountNumber = accountNumber;
    }

}

