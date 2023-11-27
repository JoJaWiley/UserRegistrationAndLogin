package com.cakefactory.user;

import com.cakefactory.user.persistence.Account.AccountService;
import com.cakefactory.user.persistence.Address.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final AccountService accountService;
    private final AddressService addressService;

    public SignupController(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    //what happens when it's already in DB? use primary keys
    @PostMapping
    String createUser(@RequestParam String email, @RequestParam String password, @RequestParam String line1,
                        @RequestParam String line2, @RequestParam String postcode) {
        this.accountService.CreateAccount(email, password);
        this.addressService.CreateAddress(line1, line2, postcode);
        return "redirect:/";
    }
}
