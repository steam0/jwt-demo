package com.example.jwt.demo.controller;

import com.example.jwt.demo.domain.model.ApplicationUser;
import com.example.jwt.demo.domain.exceptions.UsernameAlreadyInUseException;
import com.example.jwt.demo.domain.repositories.ApplicationUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController(value = "/")
public class WebRestController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    public WebRestController(BCryptPasswordEncoder bCryptPasswordEncoder, ApplicationUserRepository applicationUserRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    @GetMapping("/authorize")
    public ApplicationUser getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Returning userinfo for user: {} ", authentication.getPrincipal().toString());

        return applicationUserRepository.findUserByUsername(authentication.getPrincipal().toString());
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        ApplicationUser existingUser = applicationUserRepository.findUserByUsername(user.getUsername());

        if (existingUser != null) {
            throw new UsernameAlreadyInUseException("Username already exist.");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user = applicationUserRepository.save(user);
        log.info("Saving new user={} with username={} and password={}", user.getId(), user.getUsername(), user.getPassword());
    }
}
