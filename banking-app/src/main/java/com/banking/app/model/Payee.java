package com.banking.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "payee")
public class Payee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn(name = "userID")
    private UserCredential userCredential;

    public Payee() {

    }

    public Payee(String firstName, String lastName, String nickName, int accountNumber) {
        super();

        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.accountNumber = accountNumber;
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
        return userCredential.getUserID();
    }
}

