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
    public Map<String, Object> getAddressById(@RequestParam(name = ServiceUtils.PARAM_ID) String id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity != null) {
            Map<String, Object> result = new LinkedHashMap<>();
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, addressConverter.convertEntityToAddress(addressEntity));
            return result;
        } else {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
        }
    }

    @DeleteMapping(ServiceUtils.DELETE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> deleteAddressById(@PathVariable(name = ServiceUtils.PARAM_ID) String id) {
        AddressEntity addressEntity = addressRepository.getById(id);
        if (addressEntity == null) {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
        } else {
            addressRepository.deleteById(id);
            Map<String, Object> result = new LinkedHashMap<>();
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, addressConverter.convertEntityToAddress(addressEntity));
            return result;
        }
    }

    @PutMapping(ServiceUtils.UPDATE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> updateAddressById(
        @PathVariable(name = ServiceUtils.PARAM_ID) String id,
        @RequestParam(name = ServiceUtils.ADDRESS_PARAM_STREET, required = false) String newStreet,
        @RequestParam(name = ServiceUtils.ADDRESS_PARAM_CITY, required = false) String newCity,
        @RequestParam(name = ServiceUtils.ADDRESS_PARAM_STATE, required = false) String newState,
        @RequestParam(name = ServiceUtils.ADDRESS_PARAM_POSTAL_CODE, required = false) String newPostalCode,
        @RequestParam(name = ServiceUtils.ADDRESS_PARAM_COUNTRY, required = false) String newCountry
    ) {
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
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
        result.put(ServiceUtils.ANSWER_RESULT, addressConverter.convertEntityToAddress(newAddressEntity));
        return result;
    }
}
