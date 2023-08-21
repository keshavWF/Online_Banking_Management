package com.banking.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
    @Id
    private String userName;
    @Column
    private String firstName;
    @Column
    private String SecondName;
    @Column
    private String CurrentAddress;
    @Column
    private String Gender;
    @Column
    private String DOB;
    @Column
    private String FatherName;
    @Column
    private String PermanentAddress;
    @Column
    private String PhoneNumber;
    @Column
    private String Aadhar;

    public User() {

    }
    public User(String userName, String firstName, String secondName, String currentAddress, String gender, String dob,
                String fatherName, String permanentAddress, String phoneNumber, String aadhar) {
        super();
        this.userName = userName;
        this.firstName = firstName;
        this.SecondName = secondName;
        this.CurrentAddress = currentAddress;
        this.Gender = gender;
        this.DOB = dob;
        this.FatherName = fatherName;
        this.PermanentAddress = permanentAddress;
        this.PhoneNumber = phoneNumber;
        this.Aadhar = aadhar;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return SecondName;
    }
    public void setSecondName(String secondName) {
        this.SecondName = secondName;
    }
    public String getCurrentAddress() {
        return CurrentAddress;
    }
    public void setCurrentAddress(String currentAddress) {
        this.CurrentAddress = currentAddress;
    }
    public String getGender() {
        return Gender;
    }
    public void setGender(String gender) {
        this.Gender = gender;
    }
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    public String getFatherName() {
        return FatherName;
    }
    public void setFatherName(String fatherName) {
        this.FatherName = fatherName;
    }
    public String getPermanentAddress() {
        return PermanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
        this.PermanentAddress = permanentAddress;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }
    public String getAadhar() {
        return Aadhar;
    }
    public void setAadhar(String aadhar) {
        this.Aadhar = aadhar;
    }

}
