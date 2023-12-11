package com.cakefactory.signup.persistence.Account;

import com.cakefactory.signup.Account;
import com.cakefactory.signup.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    JpaAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return;
        }
        this.accountRepository.save(toAccountEntity(email, password, passwordEncoder));
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

    public AccountEntity toAccountEntity(String email, String password, PasswordEncoder passwordEncoder) {
        return new AccountEntity(
                email, passwordEncoder.encode(password));
    }
}
