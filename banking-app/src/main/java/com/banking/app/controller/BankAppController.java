package com.banking.app.controller;

import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAppController {

    @Autowired
    private IUserCredentialService userCredentialService;

    @GetMapping("/canLogin/{userID}/{password}")
    public String canLoginUser(@PathVariable int userID, @PathVariable String password){
        final UserCredential userExists = userCredentialService.getUserCredentialsByUserID(userID);
        if(userExists != null){
            return userExists.getPassword().contentEquals(password) ? "YES" : "";
        }

        return "NO";
    }
}
