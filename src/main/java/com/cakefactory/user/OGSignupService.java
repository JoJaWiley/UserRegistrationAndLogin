package com.cakefactory.user;

import com.cakefactory.user.persistence.Account.JpaAccountService;
import com.cakefactory.user.persistence.Address.JpaAddressService;
import org.springframework.stereotype.Component;

@Component
public class OGSignupService {
    private final JpaAccountService accountService;
    private final JpaAddressService addressService;

    public OGSignupService(JpaAccountService accountService, JpaAddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    public void register(String email, String password, String line1, String line2, String postcode) {
        if (this.accountExists(email)) {
            return;
        }

        this.accountService.createAccount(email, password);
        this.addressService.createAddress(line1, line2, postcode);
    }

    public Boolean accountExists(String email) {
        return this.accountService.accountAlreadyExists(email);
    }
}
