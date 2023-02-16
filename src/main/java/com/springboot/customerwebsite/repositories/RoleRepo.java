package com.springboot.customerwebsite.repositories;


import com.springboot.customerwebsite.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
}