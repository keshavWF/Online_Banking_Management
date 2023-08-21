package com.banking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banking.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}