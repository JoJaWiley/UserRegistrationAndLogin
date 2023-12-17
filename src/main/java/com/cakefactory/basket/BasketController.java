package com.cakefactory.basket;



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
@RequestMapping("/basket")
class BasketController {

    private final Basket basket;
    private final AddressService addressService;

    BasketController(Basket basket, AddressService addressService) {
        this.basket = basket;
        this.addressService = addressService;
    }

    @PostMapping
    String addToBasket(@RequestParam String sku) {
        this.basket.add(sku);
        return "redirect:/";
    }

    @GetMapping
    ModelAndView showBasket(Principal principal) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("items", basket.getItems());
        if (principal != null) {
            Address address = this.addressService.findOrEmpty(principal.getName());
            model.put("line1", address.getLine1());
            model.put("line2", address.getLine2());
            model.put("postcode", address.getPostcode());
        } else {
            model.put("line1", "");
            model.put("line2", "");
            model.put("postcode", "");
        }

        return new ModelAndView("basket", model);
    }

    @PostMapping("/delete")
    String removeFromBasket(@RequestParam String sku) {
        this.basket.remove(sku);
        return "redirect:/basket";
    }

}