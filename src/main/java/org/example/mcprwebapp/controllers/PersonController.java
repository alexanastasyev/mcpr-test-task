package org.example.mcprwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonController {
    @GetMapping("/rest/person/all")
    public String showAllPersons(Model model) {
        model.addAttribute("text", "Person: full list");
        return "output";
    }

    @GetMapping("rest/person")
    public String showPersonById(
        @RequestParam(name = "id", required = true) String id,
        Model model
    ) {
        model.addAttribute("text", "Person: id = " + id);
        return "output";
    }

    @GetMapping("rest/person/delete")
    public String deletePersonById(
            @RequestParam(name = "id", required = true) String id,
            Model model
    ) {
        model.addAttribute("text", "Person: delete. id = " + id);
        return "output";
    }

    @GetMapping("rest/person/update")
    public String updatePersonById(
            @RequestParam(name = "id", required = true) String id,
            @RequestParam(name = "name", required = false) String newName,
            @RequestParam(name = "phone", required = false) String newPhone,
            @RequestParam(name = "email", required = false) String newEmail,
            @RequestParam(name = "address", required = false) String newAddress,
            Model model
    ) {
        StringBuilder builder = new StringBuilder();

        builder.append("Person: update. id = ").append(id);
        builder.append(" new name = ").append(newName);
        builder.append(" new phone = ").append(newPhone);
        builder.append(" new email = ").append(newEmail);
        builder.append(" new address = ").append(newAddress);

        model.addAttribute("text", builder.toString());
        return "output";
    }
}
