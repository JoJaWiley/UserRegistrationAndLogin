package com.cakefactory.user.persistence.Address;

import org.springframework.stereotype.Component;

@Component
public class AddressService {

    private final AddressRepository addressRepository;

    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //what happens when it's already in DB?
    public Boolean CreateAddress(String line1, String line2, String postcode) {
        if (line1 == null || line1.isEmpty() || line2 == null || line2.isEmpty() || postcode == null || postcode.isEmpty()) {
            return false;
        }

        addressRepository.save(new AddressEntity(line1, line2, postcode));
        return true;
    }
}
