package com.cakefactory.user;

public interface SignupService {
    public boolean accountExists(String email);

    public void register(String email, String password, String line1, String line2, String postcode);
}
