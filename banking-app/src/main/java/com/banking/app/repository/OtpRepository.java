package com.banking.app.repository;

import com.banking.app.model.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, String> {

    // Add custom query methods here if needed
}