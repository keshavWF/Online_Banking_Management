package com.banking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banking.app.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    List<Account> findByUserCredential_UserName(String userName);

    Account findByAccountNumber(double accountNumber);
}
