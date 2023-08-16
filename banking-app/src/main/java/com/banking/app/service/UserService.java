package com.banking.app.service;

import com.banking.app.model.User;
import com.banking.app.repository.UserRepository;
import com.banking.app.service.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User getUserDetailsByUserID(int userID){
        return userRepository.findById(userID).orElse(null);
    }

    @Override
    public User updateUserDetails(User user){
        final User userData = userRepository.findById(user.getUserID()).orElse(null);
        if(userData != null){
            userData.setFirstName(user.getFirstName());
            userData.setSecondName(user.getSecondName());
            userData.setCurrentAddress(user.getCurrentAddress());
            userData.setGender(user.getGender());
            userData.setDOB(user.getDOB());
            userData.setFatherName(user.getFatherName());
            userData.setPermanentAddress(user.getPermanentAddress());
            userData.setPhoneNumber(user.getPhoneNumber());
            userData.setAadhar(user.getAadhar());
            userRepository.save(userData);
            return userData;
        }

        return null;
    }
}
