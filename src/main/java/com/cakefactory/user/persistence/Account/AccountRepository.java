package com.cakefactory.user.persistence.Account;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    Optional<AccountEntity> findByEmail(String email);
}
