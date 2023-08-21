package com.banking.app.service.Interfaces;

import com.banking.app.model.Account;

import java.util.List;

public interface IAccountService {
	public void saveAccount(Account account);

	public Account getDetailsByAccountNumber(int accountNumber);

	public Account updateDetailsByAccountNumber(Account account);

	public List<Account> getAccountsByUserName(String userName);
}
