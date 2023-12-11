package com.cakefactory.signup;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final OGSignupService signupService;

    public SignupController(OGSignupService signupService,
                            PasswordEncoder passwordEncoder) {
        this.signupService = signupService;
    }

    @GetMapping
    String signup() {
        return "signup";
    }


    //what happens when it's already in DB? use primary key, only matters for account
    @PostMapping
    String signup(@RequestParam String email, @RequestParam String password, @RequestParam String line1,
                        @RequestParam String line2, @RequestParam String postcode) {
        if (this.signupService.accountExists(email)) {
            return "redirect:/login";
        }

        this.signupService.register(email, password, line1, line2, postcode);
        return "redirect:/";
    }
}
