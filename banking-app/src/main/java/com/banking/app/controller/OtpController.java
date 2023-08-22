package com.banking.app.controller;

import com.banking.app.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/otp")
public class OtpController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {
        otpService.sendOTPMail(email);
        return "OTP sent successfully.";
    }

}