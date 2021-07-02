package org.example.mcprwebapp.address.service;

import org.example.mcprwebapp.address.Address;
import org.example.mcprwebapp.address.database.AddressConverter;
import org.example.mcprwebapp.address.database.AddressEntity;
import org.example.mcprwebapp.address.database.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("rest/address")
public class AddressController {
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    public AddressController(
        @Autowired AddressRepository addressRepository,
        @Autowired AddressConverter addressConverter
    ) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Address> showAllAddresses() {
        List<AddressEntity> addressEntities = addressRepository.getAll();
        List<Address> addresses = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntities) {
            addresses.add(addressConverter.convertEntityToAddress(addressEntity));
        }
        return addresses;
    }

    @GetMapping("/find")
    @ResponseBody
    public Address showAddressById(@RequestParam(name = "id") String id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        return addressConverter.convertEntityToAddress(addressEntity);
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteAddressById(@RequestParam(name = "id") String id) {
        addressRepository.deleteById(id);
        return "success";
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateAddressById(
        @RequestParam(name = "id") String id,
        @RequestParam(name = "street", required = false) String newStreet,
        @RequestParam(name = "city", required = false) String newCity,
        @RequestParam(name = "state", required = false) String newState,
        @RequestParam(name = "postal_code", required = false) String newPostalCode,
        @RequestParam(name = "country", required = false) String newCountry
    ) {
        AddressEntity addressEntity = addressRepository.getById(id);
        if (newStreet == null) {
            newStreet = addressEntity.getStreet();
        }
        if (newCity == null) {
            newCity = addressEntity.getCity();
        }
        if (newState == null) {
            newState = addressEntity.getState();
        }
        if (newPostalCode == null) {
            newPostalCode = addressEntity.getPostalCode();
        }
        if (newCountry == null) {
            newCountry = addressEntity.getCountry();
        }
        addressRepository.updateById(id, newStreet, newCity, newState, newPostalCode, newCountry);
        return "success";
    }
}
