package com.banking.app.service;

import com.banking.app.model.*;
import com.banking.app.repository.CredentialRepository;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCredentialService implements IUserCredentialService {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse saveUserCredentials(RegisterRequest registerRequest) {
        var userCredential = UserCredential.builder()
                .userName(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole() == null ? Role.USER : Role.ADMIN)
                .build();
        //System.out.println(userCredential);
        credentialRepository.save(userCredential);
        var jwtToken = jwtService.generateToken(userCredential);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public UserCredential getUserCredentialsByUserName(String userName){
        return credentialRepository.findById(userName).orElse(null);
    }

    @Override
    public UserCredential updateUserCredentials(UserCredential userCredential){
        final UserCredential credentials = credentialRepository
                .findById(userCredential.getUsername()).orElse(null);

        if(credentials != null){
            credentials.setUserName(userCredential.getUsername());
            credentials.setPassword(userCredential.getPassword());
            credentials.setIsAdmin(userCredential.getIsAdmin());
            credentialRepository.save(credentials);
            return credentials;
        }

        return null;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword()
                )
        );
        var user = credentialRepository.findById(authenticationRequest.getUserName())
                .orElseThrow();
        //System.out.println(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public List<UserCredential> getUnapprovedUserList(){
        return credentialRepository.findUserNameByApproved("false");
    }
}
