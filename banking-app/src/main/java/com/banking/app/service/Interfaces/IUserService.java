package com.banking.app.service.Interfaces;

import com.banking.app.model.User;

public interface IUserService {
    public void saveUser(User user);

    public User getUserDetailsByUserID(int userID);

    public User updateUserDetails(User user);
}
