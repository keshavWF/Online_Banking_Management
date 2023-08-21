package com.banking.app.repository;

import com.banking.app.model.Account;
import com.banking.app.model.Payee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends JpaRepository<Payee, Integer>{
     List<Payee> findByUserCredential_UserName(String userName);
}
