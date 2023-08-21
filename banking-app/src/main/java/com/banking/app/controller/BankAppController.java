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

    @GetMapping("/canLogin/{userName}/{password}")
    public String canLoginUser(@PathVariable String userName, @PathVariable String password){
        final UserCredential userExists = userCredentialService.getUserCredentialsByUserName(userName);
        if(userExists != null){
            return userExists.getPassword().contentEquals(password) ? "YES" : "";
        }

        return "NO";
    }
}
