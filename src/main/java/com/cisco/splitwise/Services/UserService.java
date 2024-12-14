package com.cisco.splitwise.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cisco.splitwise.Models.User;

import lombok.ToString;

@ToString
public class UserService {
    public List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

}
