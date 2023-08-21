package com.banking.app.controller;

import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserCredentialController {
    @Autowired
    private IUserCredentialService userCredentialService;

    @CrossOrigin
    @PostMapping("/addUser")
    public void add(@RequestBody UserCredential userCredential) {
        userCredentialService.saveUserCredentials(userCredential);
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
