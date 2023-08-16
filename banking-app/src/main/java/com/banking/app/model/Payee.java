package com.banking.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payee")
public class Payee {
    @Id
    private int payeeID;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private int accountNumber;


    @JsonBackReference
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "userId")
    private Account account;

    public Payee() {

    }

    public Payee(int payeeID, String firstName, String lastName, String nickName, int accountNumber, Account account) {
        super();

        this.payeeID = payeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.accountNumber = accountNumber;
        this.account = account;
    }

    public int getPayeeID() {
        return payeeID;
    }

    public void setPayeeID(int payeeID) {
        this.payeeID = payeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getUserIDByAccount() {
        return account.getUserID();
    }
}

