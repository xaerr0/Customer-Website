package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Role;
import com.springboot.customerwebsite.models.User;
import com.springboot.customerwebsite.repositories.RoleRepo;
import com.springboot.customerwebsite.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder encoder;




    @Override
    @Transactional
    public User saveUser(User user) throws Exception {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new Exception("User already exists");
        } else {
            checkPassword(user.getPassword());
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole((Role) roleRepo.findRoleById(1L));
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

    private void checkPassword(String password) {
        if(password == null) {
            throw new IllegalStateException("You must set a password");
        }
        if(password.length() < 6) {
            throw new IllegalStateException("Password is too short. Must be larger than 6 characters");
        }
    }
}