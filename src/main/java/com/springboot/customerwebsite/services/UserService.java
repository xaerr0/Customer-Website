package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;


public interface UserService extends UserDetailsService {
    UserPrincipal saveUser(UserPrincipal user) throws Exception;

    UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException;

    UserPrincipal getUser(Long id);

    void deleteUser(Long id);


}