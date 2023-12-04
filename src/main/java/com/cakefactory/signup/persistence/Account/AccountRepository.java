package com.cakefactory.signup.persistence.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    AccountEntity findByEmail(String email);
}
