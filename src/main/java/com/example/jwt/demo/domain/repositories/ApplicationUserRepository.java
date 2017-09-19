package com.example.jwt.demo.domain.repositories;

import com.example.jwt.demo.domain.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findUserByUsername(String username);
}
