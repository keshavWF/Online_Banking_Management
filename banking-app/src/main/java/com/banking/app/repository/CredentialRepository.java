package com.banking.app.repository;

import com.banking.app.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<UserCredential, String> {
    @Query("SELECT u FROM UserCredential u WHERE u.isAdmin = :isAdmin")
    List<UserCredential> findUserNameByApproved(@Param("isAdmin") String isAdmin);
}
