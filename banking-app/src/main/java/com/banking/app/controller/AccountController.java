package com.banking.app.controller;

import jakarta.persistence.NoResultException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banking.app.model.Account;
import com.banking.app.service.Interfaces.IAccountService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/addAccount/{userName}")
	public void add(@RequestBody Account account, @PathVariable String userName) {
		accountService.saveAccount(account, userName);
	}

	@GetMapping("/fetchAccount/{accountNumber}")
	public ResponseEntity<Object> fetchDetails(@PathVariable int accountNumber){
		try
		{
            return ResponseEntity.ok(accountService.getDetailsByAccountNumber(accountNumber));
		}
		catch(NoResultException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/getUsername/{accountNumber}")
	public String fetchUsername(@PathVariable int accountNumber){
		try {
			final String fetchedUsername = accountService.getUserNameAPI(accountNumber);
			return fetchedUsername;
		}
		catch(NoResultException e)
		{
			return "Can't fetch username, user account does not exist!";
		}
	}

	@PutMapping("/updateAccount")
	public String updateDetails(@RequestBody Account account){
		try{
			final String user = this.fetchUsername(account.getAccountNumber()); // a way to check account exists
			accountService.updateDetailsByAccountNumber(account);
			return "Account Updated";
		}
		catch(NoResultException e)
		{
			return "Failure to update details, account does not exist!";
		}

	}

	@GetMapping("/getAccount/{userName}")
	public List<Account> getAccountDetailsByUserName(@PathVariable String userName){
		return accountService.getAccountsByUserName(userName);
	}
}
