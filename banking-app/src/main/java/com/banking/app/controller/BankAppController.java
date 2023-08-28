package com.banking.app.controller;

import com.banking.app.model.OtpEntity;
import com.banking.app.model.UserCredential;
import com.banking.app.repository.OtpRepository;
import com.banking.app.service.Interfaces.IUserCredentialService;
import com.banking.app.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/bank")
public class BankAppController {

    @Autowired
    private IUserCredentialService userCredentialService;

    @Autowired
    private OTPService otpService;

    @GetMapping("/canLogin/{userName}/{password}/{isAdmin}")
    public String canLoginUser(@PathVariable String userName, @PathVariable String password, @PathVariable String isAdmin){
        final UserCredential userExists = userCredentialService.getUserCredentialsByUserName(userName);

        if(userExists != null){
            return userExists.getRole().toString().equals(isAdmin) ? "YES" : "IncorrectCredentials";
        }

        return "UserDoesNotExists";
    }

    @GetMapping("/exists/{userName}")
    public String userNameAlreadyExists(@PathVariable String userName){
        return userCredentialService.getUserCredentialsByUserName(userName) != null ? "YES" : "NO";
    }



    @Autowired
    private OtpRepository otpRepository;

    @GetMapping("/checkOTP/{Email}/{OTP}")
    public String validateOTP(@PathVariable String Email, @PathVariable String OTP) {
        LocalDateTime otpReceivedTime = LocalDateTime.now();



        Optional<OtpEntity> otpEntityOptional = otpRepository.findByEmail(Email);
        OtpEntity otpEntity;

        if (otpEntityOptional.isPresent()) {
            otpEntity = otpEntityOptional.get();
        }
        else {
            return "INVALID";
        }

        Duration duration = Duration.between(otpReceivedTime, otpEntity.getExpiryTimestamp());
        if(duration.getSeconds()<900)
        {
            return otpService.getOTPByEmail(Email).contentEquals(OTP) ? "VALID" : "INVALID";
        }
        return "INVALID";
    }
}
