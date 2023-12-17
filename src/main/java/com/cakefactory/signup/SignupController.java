package com.cakefactory.signup;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final SignupService signupService;

    private final SecurityContextRepository securityContextRepository;

    public SignupController(SignupService signupService, SecurityContextRepository securityContextRepository) {
        this.signupService = signupService;
        this.securityContextRepository = securityContextRepository;
    }

    @GetMapping
    String signup() {
        return "/signup";
    }


    //what happens when it's already in DB? use primary key, only matters for account
    @PostMapping
    String signup(@RequestParam String email, @RequestParam String password, @RequestParam String line1,
                        @RequestParam String line2, @RequestParam String postcode) {

        if (this.signupService.accountExists(email)) {
            return "redirect:/login";
        }

        this.signupService.register(email, password, line1, line2, postcode);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, "", List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(token);
        //spring security 6.x requires saving context explicitly after changes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        securityContextRepository.saveContext(securityContext, attributes.getRequest(), attributes.getResponse());
        return "redirect:/";
    }
}
