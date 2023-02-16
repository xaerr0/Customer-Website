package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.User;

public interface UserService {
    User saveUser(User user) throws Exception;

    User getUser(Long id);

    void deleteUser(Long id);


}