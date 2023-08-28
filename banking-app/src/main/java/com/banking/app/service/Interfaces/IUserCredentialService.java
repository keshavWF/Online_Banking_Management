package com.banking.app.service.Interfaces;

import com.banking.app.model.AuthenticationRequest;
import com.banking.app.model.AuthenticationResponse;
import com.banking.app.model.RegisterRequest;
import com.banking.app.model.UserCredential;

import java.util.List;

public interface IUserCredentialService {
    public AuthenticationResponse saveUserCredentials(RegisterRequest registerRequest);

    public UserCredential getUserCredentialsByUserName(String userName);

    public UserCredential updateUserCredentials(UserCredential userCredential);
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    public List<UserCredential> getUnapprovedUserList();
}
