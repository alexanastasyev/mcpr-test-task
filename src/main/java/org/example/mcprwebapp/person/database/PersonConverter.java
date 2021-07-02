package org.example.mcprwebapp.person.database;

import org.example.mcprwebapp.address.Address;
import org.example.mcprwebapp.address.database.AddressConverter;
import org.example.mcprwebapp.address.database.AddressEntity;
import org.example.mcprwebapp.address.database.AddressRepository;
import org.example.mcprwebapp.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    public PersonConverter(
        @Autowired AddressRepository addressRepository,
        @Autowired AddressConverter addressConverter
    ) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    public Person convertEntityToPerson(PersonEntity personEntity) {
        AddressEntity addressEntity = addressRepository.getById(personEntity.getAddressId());
        Address address = addressConverter.convertEntityToAddress(addressEntity);
        return new Person(
            personEntity.getId(),
            personEntity.getName(),
            personEntity.getPhone(),
            personEntity.getEmail(),
            address
        );
    }
}
