package com.banking.app.controller;

import com.banking.app.model.AuthenticationRequest;
import com.banking.app.model.AuthenticationResponse;
import com.banking.app.model.RegisterRequest;
import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.NoResultException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserCredentialController {
    @Autowired
    private IUserCredentialService userCredentialService;

    @CrossOrigin
    @PostMapping("/addUser")
    public ResponseEntity<AuthenticationResponse> add(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userCredentialService.saveUserCredentials(registerRequest));
    }

    @GetMapping("/authenticate/{userName}/{password}")
    public AuthenticationResponse authenticate(@PathVariable String userName, @PathVariable String password){
        final AuthenticationRequest request = AuthenticationRequest.builder()
                .userName(userName)
                .password(password)
                .build();
        return userCredentialService.authenticate(request);
    }

    @GetMapping("/fetchUser/{userName}")
    public ResponseEntity<Object> fetchDetails(@PathVariable String userName){
        try {
            final UserCredential fetchedUser = userCredentialService.getUserCredentialsByUserName(userName);
            return ResponseEntity.ok(fetchedUser);
        }
        catch (NoResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/updateUser")
    public void updateUserDetails(@RequestBody UserCredential userCredential){
        userCredentialService.updateUserCredentials(userCredential);
    }
}
