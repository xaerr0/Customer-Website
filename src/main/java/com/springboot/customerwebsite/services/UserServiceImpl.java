package com.springboot.customerwebsite.services;

//import com.springboot.customerwebsite.models.UserMeta;

import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import com.springboot.customerwebsite.repositories.AuthorityRepo;
import com.springboot.customerwebsite.repositories.UserPrincipalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserPrincipalRepo userPrincipalRepo;

    @Autowired
    AuthorityRepo authorityRepo;

    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    UserMetaRepo userMetaRepo;


    @Override
    public UserPrincipal saveUser(UserPrincipal user) throws Exception {
//        if (userPrincipalRepo.findByEmail(user.getEmail()).isPresent()) {
//            throw new Exception("User already exists");
//        } else {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singletonList(authorityRepo.findRoleById(1L)));
        checkPassword(user.getPassword());
        try {
            return userPrincipalRepo.save(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }

    }
//    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPrincipalRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + "was not found."));
    }

    @Override
    public UserPrincipal getUser(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {


    }

    private void checkPassword(String password) {
        if (password == null) {
            throw new IllegalStateException("You must set a password");
        }
        if (password.length() < 6) {
            throw new IllegalStateException("Password is too short. Must be larger than 6 characters");
        }
    }
}