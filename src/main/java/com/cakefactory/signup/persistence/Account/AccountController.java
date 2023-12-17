package com.cakefactory.signup.persistence.Account;

import com.cakefactory.signup.Address;
import com.cakefactory.signup.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AddressService addressService;

    public AccountController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    ModelAndView account(Principal principal) {
        HashMap<String, Object> model = new HashMap<>();

        Address address = this.addressService.findOrEmpty(principal.getName());
        model.put("line1", address.getLine1());
        model.put("line2", address.getLine2());
        model.put("postcode", address.getPostcode());


        return new ModelAndView("account", model);
    }

    @PostMapping
    String updateAddress(Principal principal, @RequestParam String line1, @RequestParam String line2,
                         @RequestParam String postcode) {
        if (principal == null) {
            return "redirect:/login";
        }

        this.addressService.update(principal.getName(), line1, line2, postcode);

        return "redirect:/account";
    }

}
