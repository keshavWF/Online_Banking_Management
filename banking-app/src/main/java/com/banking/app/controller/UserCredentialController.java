package com.banking.app.controller;

import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserCredentialController {
    @Autowired
    private IUserCredentialService userCredentialService;

    @PostMapping("/addUser")
    public void add(@RequestBody UserCredential userCredential) {
        userCredentialService.saveUserCredentials(userCredential);
    }

    @GetMapping("/fetchUser/{userID}")
    public UserCredential fetchDetails(@PathVariable int userID){
        final UserCredential fetchedUser = userCredentialService.getUserCredentialsByUserID(userID);
        return fetchedUser;
    }

    @PutMapping("/updateUser")
    public void updateUserDetails(@RequestBody UserCredential userCredential){
        userCredentialService.updateUserCredentials(userCredential);
    }
}
