package com.springboot.customerwebsite.repositories;

import com.springboot.customerwebsite.models.securitymodels.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserPrincipalRepo extends JpaRepository<UserPrincipal, Long> {
    Optional<UserPrincipal> findByEmail(String email);

    Optional<UserPrincipal> findByUsername(String username);
}