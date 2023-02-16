package com.springboot.customerwebsite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .antMatchers("/", "/webjars/**", "/css/**",
                        "/login/**", "/images/**", "/register").permitAll()
                        .antMatchers("/customer-view").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin();
        return httpSecurity.build();
    }
}