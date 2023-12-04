package com.cakefactory.signup.persistence.Address;

import com.cakefactory.signup.Address;
import com.cakefactory.signup.AddressService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaAddressService implements AddressService {

    private final Address emptyAddress = new Address("", "", "");

    private final AddressRepository addressRepository;

    JpaAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findOrEmpty(String email) {
        Optional<AddressEntity> ae = this.addressRepository.findById(email);

        return ae.map(e -> new Address(e.getLine1(), e.getLine2(), e.getPostcode())).orElse(emptyAddress);

    }

    @Override
    public void update(String email, String line1, String line2, String postcode) {
        addressRepository.save(new AddressEntity(email, line1, line2, postcode));
    }
}
