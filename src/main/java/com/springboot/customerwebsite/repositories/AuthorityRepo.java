package com.springboot.customerwebsite.repositories;


import com.springboot.customerwebsite.models.securitymodels.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, Long> {
    Authority findRoleById(Long id);
}