package com.banking.app.service.Interfaces;

import com.banking.app.model.UserCredential;

public interface IUserCredentialService {
    public void saveUserCredentials(UserCredential userCredential);

    public UserCredential getUserCredentialsByUserID(int userID);

    public UserCredential updateUserCredentials(UserCredential userCredential);
}
