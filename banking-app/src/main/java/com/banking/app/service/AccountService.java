package com.banking.app.service;

import com.banking.app.service.Interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.model.Account;
import com.banking.app.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void saveAccount(Account account) {
		accountRepository.save(account);
	}

	@Override
	public Account getDetailsByAccountNumber(int accountNumber){
		return accountRepository.findById(accountNumber).orElse(null);
	}

	@Override
	public Account updateDetailsByAccountNumber(Account account){
		final Account accountData = accountRepository.findById(account.getAccountNumber()).orElse(null);
		if(accountData != null){
			accountData.setAccountBalance(account.getAccountBalance());
			accountData.setAccountType(account.getAccountType());
			accountRepository.save(accountData);
			return accountData;
		}

		return null;
	}
}
