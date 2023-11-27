package com.cakefactory.user.persistence;

import com.cakefactory.user.Account;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

    private final AccountRepository accountRepository;

    AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //what happens when it's already in DB?
    public Boolean CreateAccount(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        accountRepository.save(new AccountEntity(email, password));
        return true;
    }
}
