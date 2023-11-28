package com.cakefactory.user.persistence.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    String email;

    @NotBlank
    String password;

    public AccountEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountEntity() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AccountEntity) {
            AccountEntity other = (AccountEntity) obj;
            return Objects.equals(this.email, other.email);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.email);
    }
}
