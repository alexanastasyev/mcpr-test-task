package org.example.mcprwebapp.address.database;

import org.example.mcprwebapp.address.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {
    public Address convertEntityToAddress(AddressEntity addressEntity) {
        return new Address(
            addressEntity.getId(),
            addressEntity.getStreet(),
            addressEntity.getCity(),
            addressEntity.getState(),
            addressEntity.getPostalCode(),
            addressEntity.getCountry()
        );
    }
}
