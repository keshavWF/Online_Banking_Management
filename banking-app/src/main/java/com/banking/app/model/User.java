package com.banking.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity

public class User {
	@Id
	private int userID;
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
	private long PhoneNumber;
	@Column
	@NotNull(message = "Cannot be empty")
	private long Aadhar;

	public User() {

	}
	public User(int userID, String firstName, String secondName, String currentAddress, String gender, String DOB, String fatherName, String permanentAddress, long phoneNumber, long aadhar) {
		super();
		this.userID = userID;
		this.FirstName = firstName;
		this.SecondName = secondName;
		this.CurrentAddress = currentAddress;
		this.Gender = gender;
		this.DOB = DOB;
		this.FatherName = fatherName;
		this.PermanentAddress = permanentAddress;
		this.PhoneNumber = phoneNumber;
		this.Aadhar = aadhar;
	}
	
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

