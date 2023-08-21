package com.banking.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class User {
    @Id
    private String userName;
    @Column
    @Size(min = 3, max = 30, message = "Name between 3 and 30 characters")
    private String name;
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
    private long PhoneNumber;
    @Column
    private long Aadhar;

    public User() {

    }
    public User(String userName, String firstName, String secondName, String currentAddress, String gender, String DOB,
                String fatherName, String permanentAddress, long phoneNumber, long aadhar) {
        super();
        this.userName = userName;
        this.name = firstName;
        this.SecondName = secondName;
        this.CurrentAddress = currentAddress;
        this.Gender = gender;
        this.DOB = DOB;
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
        return name;
    }
    public void setFirstName(String firstName) {
        this.name = firstName;
    }
    public String getSecondName() {
        return SecondName;
    }
    public void setSecondName(String secondName) {
        SecondName = secondName;
    }
    public String getCurrentAddress() {
        return CurrentAddress;
    }
    public void setCurrentAddress(String currentAddress) {
        CurrentAddress = currentAddress;
    }
    public String getGender() {
        return Gender;
    }
    public void setGender(String gender) {
        Gender = gender;
    }
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String dOB) {
        DOB = dOB;
    }
    public String getFatherName() {
        return FatherName;
    }
    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }
    public String getPermanentAddress() {
        return PermanentAddress;
    }
    public void setPermanentAddress(String permanentAddress) {
        PermanentAddress = permanentAddress;
    }
    public long getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public long getAadhar() {
        return Aadhar;
    }
    public void setAadhar(long aadhar) {
        Aadhar = aadhar;
    }

}
