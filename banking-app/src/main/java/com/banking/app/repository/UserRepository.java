package com.banking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.banking.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    @Query("SELECT u.userName FROM User u WHERE u.Email = :email")
    String findUserNameByEmail(@Param("email") String email);
}