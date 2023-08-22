package com.banking.app.service;

import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IAccountService;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.model.Account;
import com.banking.app.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private IUserCredentialService userCredentialService;

	@Override
	public void saveAccount(Account account, String userName) {
		account.setUserCredential(userCredentialService.getUserCredentialsByUserName(userName));
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

	@Override
	public List<Account> getAccountsByUserName(String userName) {
		return accountRepository.findByUserCredential_UserName(userName);
	}
}
