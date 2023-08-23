package com.banking.app.repository;

import com.banking.app.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("SELECT u FROM Transaction u WHERE u.fromAccountNumber = :account")
    List<Transaction> findTransactionsFromAccountNumber(@Param("account") int account);

    @Query("SELECT u FROM Transaction u WHERE u.toAccountNumber = :account")
    List<Transaction> findTransactionsToAccountNumber(@Param("account") int account);

    @Query("SELECT u FROM Transaction u WHERE u.userName = :userName")
    List<Transaction> findTransactionsForUsername(@Param("userName") String userName);
}