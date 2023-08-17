package com.banking.app.controller;

import com.banking.app.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send")
    //why?
    public String sendOtp(@RequestParam String email) {
        otpService.sendOTPMail(email);
        return "OTP sent successfully.";
    }
}