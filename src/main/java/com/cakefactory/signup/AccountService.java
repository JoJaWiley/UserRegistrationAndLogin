package com.cakefactory.signup;

public interface AccountService {
    public void register(String email, String password);

    public Account find(String email);

    public boolean exists(String email);
}
