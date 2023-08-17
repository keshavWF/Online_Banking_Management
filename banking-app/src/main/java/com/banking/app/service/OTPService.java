package com.banking.app.service;

import com.banking.app.model.OtpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.banking.app.repository.OtpRepository;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;


@Service
public class OTPService
{
    private final JavaMailSender sender;


    private OtpRepository otpRepository;


    @Autowired
    public OTPService(JavaMailSender sender, OtpRepository otpRepository)
    {

        this.sender = sender;
        this.otpRepository = otpRepository;
    }
    // injects dependencies automatically into the spring-managed method/bean
    // automatically populates method or constructor parameter

    public void sendOTPMail(String email)
    {
        String otp = generateOtp();
        OtpEntity otpEntity = new OtpEntity(email, otp, calculateExpiryTimeStamp());
        otpRepository.save(otpEntity);// save?
        sendMail(email, "Forgot your password? Use this OTP to reset", otp);
    }

    private String generateOtp() {
        int otpLength = 6; // OTP length
        Random random = new Random();

        StringBuilder otpBuilder = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otpBuilder.append(random.nextInt(10)); // Append a random digit (0-9)
        }

        return otpBuilder.toString();
    }
    private LocalDateTime calculateExpiryTimeStamp() {
        int otpValidityMinutes = 15; // OTP validity period in minutes
        return LocalDateTime.now().plus(otpValidityMinutes, ChronoUnit.MINUTES);
    }

    private void sendMail(String to, String subject , String otp)
    {
        String text = "Your otp: "+otp;
        SimpleMailMessage message;
        message =  new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        sender.send(message);
    }


}