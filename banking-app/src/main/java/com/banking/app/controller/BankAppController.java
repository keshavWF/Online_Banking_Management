package com.banking.app.controller;

import com.banking.app.model.UserCredential;
import com.banking.app.service.Interfaces.IUserCredentialService;
import com.banking.app.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAppController {

    @Autowired
    private IUserCredentialService userCredentialService;

    @Autowired
    private OTPService otpService;

    @GetMapping("/canLogin/{userName}/{password}")
    public String canLoginUser(@PathVariable String userName, @PathVariable String password){
        final UserCredential userExists = userCredentialService.getUserCredentialsByUserName(userName);
        if(userExists != null){
            return userExists.getPassword().contentEquals(password) ? "YES" : "IncorrectPassword";
        }

        return "UserDoesNotExists";
    }

    @GetMapping("/exists/{userName}")
    public String userNameAlreadyExists(@PathVariable String userName){
        return userCredentialService.getUserCredentialsByUserName(userName) != null ? "YES" : "NO";
    }

    @GetMapping("/checkOTP/{Email}/{OTP}")
    public String validateOTP(@PathVariable String Email, @PathVariable String OTP){
        return otpService.getOTPByEmail(Email).contentEquals(OTP) ? "VALID" : "INVALID";
    }
}
