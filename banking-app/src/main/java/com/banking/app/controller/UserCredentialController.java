package com.banking.app.controller;

import com.banking.app.model.AuthenticationRequest;
import com.banking.app.model.AuthenticationResponse;
import com.banking.app.model.RegisterRequest;
import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(userCredentialService.authenticate(request));
    }

    @GetMapping("/fetchUser/{userName}")
    public UserCredential fetchDetails(@PathVariable String userName){
        final UserCredential fetchedUser = userCredentialService.getUserCredentialsByUserName(userName);
        return fetchedUser;
    }

    @PutMapping("/updateUser")
    public void updateUserDetails(@RequestBody UserCredential userCredential){
        userCredentialService.updateUserCredentials(userCredential);
    }
}
