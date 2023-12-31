package com.banking.app.controller;

import com.banking.app.model.User;
import com.banking.app.service.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/userDetails")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public void add(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/fetchUser/{userName}")
    public User fetchDetails(@PathVariable String userName){
        final User fetchedUserDetails = userService.getUserDetailsByUserName(userName);
        return fetchedUserDetails;
    }

    @PutMapping("/updateUser")
    public void updateUserDetails(@RequestBody User user){
        userService.updateUserDetails(user);
    }

    @GetMapping("/getUser/{email}")
    public String getUserByEmail(@PathVariable String email){
        return userService.getUserNameByEmail(email);
    }
}
