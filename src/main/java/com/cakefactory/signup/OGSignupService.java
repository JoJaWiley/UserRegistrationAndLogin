package com.cakefactory.signup;

import com.cakefactory.signup.persistence.Account.JpaAccountService;
import com.cakefactory.signup.persistence.Address.JpaAddressService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OGSignupService implements SignupService {
    private final JpaAccountService accountService;
    private final JpaAddressService addressService;

    public OGSignupService(JpaAccountService accountService, JpaAddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @Override
    public boolean accountExists(String email) {
        return this.accountService.exists(email);
    }

    @Override
    @Transactional
    public void register(String email, String password, String line1, String line2, String postcode) {
        this.accountService.register(email, password);
        this.addressService.update(email, line1, line2, postcode);
    }

}
