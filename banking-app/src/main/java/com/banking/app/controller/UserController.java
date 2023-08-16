package com.banking.app.controller;

import com.banking.app.model.User;
import com.banking.app.service.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userDetails")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public void add(@RequestBody User user) {
        userService.saveUser(user);
    }

    @GetMapping("/fetchUser/{userID}")
    public User fetchDetails(@PathVariable int userID){
        final User fetchedUserDetails = userService.getUserDetailsByUserID(userID);
        return fetchedUserDetails;
    }

    @PutMapping("/updateUser")
    public void updateUserDetails(@RequestBody User user){
        userService.updateUserDetails(user);
    }
}
