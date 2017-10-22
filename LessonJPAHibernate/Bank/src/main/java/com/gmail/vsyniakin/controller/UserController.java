package com.gmail.vsyniakin.controller;

import com.gmail.vsyniakin.entity.User;
import com.gmail.vsyniakin.query.UserQuery;

import javax.persistence.EntityManager;

public class UserController {

    public static void addUser(String name, String password, EntityManager em) {
        User user = new User(name, password);
        AccountController.createAccountNewUser(user);
        UserQuery.addUserToDB(user, em);
    }

    public static User checkPassword(String name, String password, EntityManager em) {
        User user = UserQuery.selectUserByName(name, em);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("Incorrect password.");
                return null;
            }
        } else {
            System.out.println("User not found.");
            return null;
        }
    }
}
