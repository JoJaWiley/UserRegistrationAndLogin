package com.cakefactory.user.persistence.Account;

import com.cakefactory.user.Account;
import com.cakefactory.user.AccountService;
import org.springframework.stereotype.Component;

@Component
public class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;

    JpaAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void register(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return;
        }
        this.accountRepository.save(new AccountEntity(email, password));
    }

    @Override
    public Account find(String email) {
        AccountEntity accountEntity = this.accountRepository.findByEmail(email);
        return new Account(email, accountEntity.getPassword());
    }

    @Override
    public boolean exists(String email) {
        return this.accountRepository.findByEmail(email) != null;
    }
}
