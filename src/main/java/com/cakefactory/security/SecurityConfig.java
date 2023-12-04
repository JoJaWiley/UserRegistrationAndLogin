package com.cakefactory.security;

import com.cakefactory.User;
import com.cakefactory.signup.Account;
import com.cakefactory.signup.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AccountService accountService) {
        return email -> {
            Account userAccount = accountService.find(email);
            if (userAccount != null)
                return new User(email, userAccount.getPassword());

            throw new UsernameNotFoundException("User '" + email + "' not found");
        };
    }
}
