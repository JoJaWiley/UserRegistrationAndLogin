package com.cakefactory.user;

import com.cakefactory.user.Address;

public interface AddressService {

    public Address findOrEmpty(String email);

    public void update(String email, String line1, String line2, String postcode);

}
