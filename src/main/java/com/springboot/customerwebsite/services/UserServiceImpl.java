package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.User;
import com.springboot.customerwebsite.repositories.RoleRepo;
import com.springboot.customerwebsite.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;




    @Override
    public User saveUser(User user) throws Exception {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new Exception("User already exists");
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
        }
        return userRepo.save(user);
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}