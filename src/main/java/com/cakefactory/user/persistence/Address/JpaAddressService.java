package com.cakefactory.user.persistence.Address;

import org.springframework.stereotype.Component;

@Component
public class JpaAddressService {

    private final AddressRepository addressRepository;

    JpaAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    //what happens when it's already in DB?
    public Boolean createAddress(String line1, String line2, String postcode) {
        if (line1 == null || line1.isEmpty() || line2 == null || line2.isEmpty() || postcode == null || postcode.isEmpty()) {
            return false;
        }

        addressRepository.save(new AddressEntity(line1, line2, postcode));
        return true;
    }
}
