package org.example.mcprwebapp.address.database;

import org.example.mcprwebapp.address.Address;

public class AddressConverter {
    public static Address convertEntityToAddress(AddressEntity addressEntity) {
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
