package com.tgt.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/hello")
public class SecurityTestResource {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String hello(@AuthenticationPrincipal final UserDetails userDetails) {

        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities
                .forEach(System.out::println);

        return "Hello World";
    }
}