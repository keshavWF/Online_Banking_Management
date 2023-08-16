package com.banking.app.service;

import com.banking.app.model.UserCredential;
import com.banking.app.repository.CredentialRepository;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialService implements IUserCredentialService {
    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public void saveUserCredentials(UserCredential userCredential) {
        credentialRepository.save(userCredential);
    }

    @Override
    public UserCredential getUserCredentialsByUserID(int userID){
        return credentialRepository.findById(userID).orElse(null);
    }

    @Override
    public UserCredential updateUserCredentials(UserCredential userCredential){
        final UserCredential credentials = credentialRepository
                .findById(userCredential.getUserID()).orElse(null);

        if(credentials != null){
            credentials.setUserName(userCredential.getUserName());
            credentials.setPassword(userCredential.getPassword());
            credentials.setIsAdmin(userCredential.getIsAdmin());
            credentialRepository.save(credentials);
            return credentials;
        }

        return null;
    }
}
