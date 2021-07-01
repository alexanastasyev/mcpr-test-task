package org.example.mcprwebapp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("rest/address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/all")
    @ResponseBody
    public List<Address> showAllAddresses() {
        Iterable<Address> addresses = addressRepository.findAll();
        return (List<Address>) addresses;

//        Address mockAddress = new Address("2", "Velikanova", "Ryazan", "Ryazan State",
//                "390044", "Russia");
//        List<Address> mockAddressList = new ArrayList<>();
//        mockAddressList.add(mockAddress);
//        return mockAddressList;
    }

    @GetMapping("")
    @ResponseBody
    public Address showAddressById(
            @RequestParam(name = "id", required = true) String id
    ) {
        Address mockAddress = new Address(id, "Velikanova", "Ryazan", "Ryazan State",
                "390044", "Russia");
        return mockAddress;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deleteAddressById(
            @RequestParam(name = "id", required = true) String id
    ) {
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
        return "success";
    }
}
