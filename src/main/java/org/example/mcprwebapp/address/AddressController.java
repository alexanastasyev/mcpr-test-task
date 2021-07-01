package org.example.mcprwebapp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("rest/address")
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Address> showAllAddresses() {
        Iterable<Address> addresses = addressRepository.findAll();
        return (List<Address>) addresses;
    }

    @GetMapping("")
    @ResponseBody
    public Address showAddressById(
            @RequestParam(name = "id", required = true) String id
    ) {
        return addressRepository.getById(id);
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteAddressById(
            @RequestParam(name = "id", required = true) String id
    ) {
        // TODO: doesn't work
        addressRepository.deleteById(id);
        return "success";
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateAddressById(
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "street", required = false) String newStreet,
            @RequestParam(name = "city", required = false) String newCity,
            @RequestParam(name = "state", required = false) String newState,
            @RequestParam(name = "postal_code", required = false) String newPostalCode,
            @RequestParam(name = "country", required = false) String newCountry
    ) {
        Address address = addressRepository.getById(id);
        // TODO: complete it
        return "success";
    }
}
