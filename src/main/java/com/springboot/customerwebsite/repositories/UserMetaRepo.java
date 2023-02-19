package com.springboot.customerwebsite.repositories;

import com.springboot.customerwebsite.models.UserMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMetaRepo extends JpaRepository<UserMeta, Long> {
}