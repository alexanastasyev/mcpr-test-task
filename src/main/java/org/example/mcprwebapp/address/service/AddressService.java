package org.example.mcprwebapp.address.service;

import org.example.mcprwebapp.address.Address;
import org.example.mcprwebapp.address.database.AddressConverter;
import org.example.mcprwebapp.address.database.AddressEntity;
import org.example.mcprwebapp.address.database.AddressRepository;
import org.example.mcprwebapp.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public Map<String, Object> getAllAddresses() {
        List<AddressEntity> addressEntities = addressRepository.getAll();
        List<Address> addresses = new ArrayList<>();
        for (AddressEntity addressEntity : addressEntities) {
            addresses.add(addressConverter.convertEntityToAddress(addressEntity));
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
        result.put(ServiceUtils.ANSWER_RESULT, addresses);
        return result;
    }

    @GetMapping(ServiceUtils.GET_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> getAddressById(@RequestParam(name = "id") String id) {
        Map<String, Object> result = new LinkedHashMap<>();
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity != null) {
            Address address = addressConverter.convertEntityToAddress(addressEntity);
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, address);
            return result;
        } else {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
        }
    }

    @DeleteMapping(ServiceUtils.DELETE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> deleteAddressById(@RequestParam(name = "id") String id) {
        Map<String, Object> result = new LinkedHashMap<>();
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity == null) {
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_ERROR);
            result.put(ServiceUtils.ANSWER_RESULT, ServiceUtils.EMPTY_RESULT);
        } else {
            addressRepository.deleteById(id);
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, addressConverter.convertEntityToAddress(addressEntity));
        }
        return result;
    }

    @PutMapping(ServiceUtils.UPDATE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> updateAddressById(
        @RequestParam(name = "id") String id,
        @RequestParam(name = "street", required = false) String newStreet,
        @RequestParam(name = "city", required = false) String newCity,
        @RequestParam(name = "state", required = false) String newState,
        @RequestParam(name = "postalCode", required = false) String newPostalCode,
        @RequestParam(name = "country", required = false) String newCountry
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity == null) {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
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
        AddressEntity newAddressEntity = addressRepository.getById(id);
        result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
        result.put(ServiceUtils.ANSWER_RESULT, addressConverter.convertEntityToAddress(newAddressEntity));
        return result;
    }
}
