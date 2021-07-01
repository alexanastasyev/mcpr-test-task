package org.example.mcprwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressController {
    @GetMapping("/rest/address/all")
    public String showAllAddresses(Model model) {
        model.addAttribute("text", "Address: full list");
        return "output";
    }

    @GetMapping("rest/address")
    public String showAddressById(
            @RequestParam(name = "id", required = true) String id,
            Model model
    ) {
        model.addAttribute("text", "Address: id = " + id);
        return "output";
    }

    @GetMapping("rest/address/delete")
    public String deleteAddressById(
            @RequestParam(name = "id", required = true) String id,
            Model model
    ) {
        model.addAttribute("text", "Address: delete. id = " + id);
        return "output";
    }

    @GetMapping("rest/address/update")
    public String updateAddressById(
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "street", required = false) String newStreet,
            @RequestParam(name = "city", required = false) String newCity,
            @RequestParam(name = "state", required = false) String newState,
            @RequestParam(name = "postal_code", required = false) String newPostalCode,
            @RequestParam(name = "country", required = false) String newCountry,
            Model model
    ) {
        StringBuilder builder = new StringBuilder();

        builder.append("Address: update. id = ").append(id);
        builder.append(" new street = ").append(newStreet);
        builder.append(" new city = ").append(newCity);
        builder.append(" new state = ").append(newState);
        builder.append(" new postal code = ").append(newPostalCode);
        builder.append(" new country = ").append(newCountry);

        model.addAttribute("text", builder.toString());
        return "output";
    }
}
