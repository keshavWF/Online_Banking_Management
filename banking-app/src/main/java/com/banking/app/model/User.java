package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;


@Entity

public class User {
	@Id
	private int userID;
	@Autowired
	@Column
	@NotNull(message = "Cannot be empty")
	@Size(min = 3, max = 30, message = "Name between 3 and 30 characters")
	private String FirstName;
	@Column
	private String SecondName;
	@Column
	@NotNull(message = "Cannot be empty")
	private String CurrentAddress;
	@Column
	@NotNull(message = "Cannot be empty")
	private String Gender;
	@Column
	@NotNull(message = "Cannot be empty")
	private String DOB;
	@Column
	@NotNull(message = "Cannot be empty")
	private String FatherName;
	@Column
	@NotNull(message = "Cannot be empty")
	private String PermanentAddress;
	@Column
	@NotNull(message = "Cannot be empty")
	private String Username;
	@Column
	@NotNull(message = "Cannot be empty")
	private String Password;
	@Column
	@NotNull(message = "Cannot be empty")
	private String isAdmin;
	@Column
	@NotNull(message = "Cannot be empty")
	private long PhoneNumber;
	@Column
	@NotNull(message = "Cannot be empty")
	private long Aadhar;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
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
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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

