package org.example.mcprwebapp.address.service;

import org.example.mcprwebapp.address.Address;
import org.example.mcprwebapp.address.database.AddressConverter;
import org.example.mcprwebapp.address.database.AddressEntity;
import org.example.mcprwebapp.address.database.AddressRepository;
import org.example.mcprwebapp.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping(ServiceUtils.ADDRESS_SERVICE_PATH)
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    public AddressService(
        @Autowired AddressRepository addressRepository,
        @Autowired AddressConverter addressConverter
    ) {
        this.addressRepository = addressRepository;
        this.addressConverter = addressConverter;
    }

    @GetMapping(ServiceUtils.GET_ALL_PATH)
    @ResponseBody
    public List<Address> getAllAddresses() {
        List<AddressEntity> addressEntities = addressRepository.getAll();
        List<Address> addresses = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntities) {
            addresses.add(addressConverter.convertEntityToAddress(addressEntity));
        }
        return addresses;
    }

    @GetMapping(ServiceUtils.GET_BY_ID_PATH)
    @ResponseBody
    public Address getAddressById(@RequestParam(name = "id") String id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity != null) {
            return addressConverter.convertEntityToAddress(addressEntity);
        } else {
            return Address.NULL_ADDRESS;
        }
    }

    @DeleteMapping(ServiceUtils.DELETE_BY_ID_PATH)
    @ResponseBody
    public String deleteAddressById(@RequestParam(name = "id") String id) {
        addressRepository.deleteById(id);
        return "success";
    }

    @PutMapping(ServiceUtils.UPDATE_BY_ID_PATH)
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
        if (addressEntity == null) {
            return "Error: address with id \"" + id + "\" doesn't exist";
        }
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
