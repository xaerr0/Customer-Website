package com.springboot.customerwebsite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

//TODO create user-dashboard html - edit profile (use add new customer)

    //TODO When user is registered, admin login no longer works?
    //TODO make instrument-list only viewable by admin, spruce it up
    //TODO make it so after user created, they edit their customer info (finish-registration.html)
    //TODO Admin - fix delete customer
    //TODO Logout page?
    //TODO add logout page

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(auth -> auth
                        .antMatchers("/", "/webjars/**", "/css/**",
                        "/login/**", "/images/**", "/register", "/error", "/landing-page").permitAll()
                        .antMatchers("/customer-list", "/admin-dashboard", "/assign-instrument/**", "/delete/**").hasRole("ADMIN")

                        .antMatchers("/user-dashboard").hasRole("USER")
                        .anyRequest().hasRole("USER"))
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(loginSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");





        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}