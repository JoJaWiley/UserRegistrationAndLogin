package com.cakefactory.user.persistence.Account;

import org.springframework.stereotype.Component;

@Component
public class JpaAccountService {

    private final AccountRepository accountRepository;

    JpaAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //what happens when it's already in DB?
    public Boolean createAccount(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        this.accountRepository.save(new AccountEntity(email, password));
        return true;
    }

    public Boolean accountAlreadyExists(String email) {
        return this.accountRepository.findByEmail(email).isEmpty();
    }
}
