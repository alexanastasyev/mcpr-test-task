package org.example.mcprwebapp.person;

import org.example.mcprwebapp.address.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    @GetMapping("/rest/person/all")
    @ResponseBody
    public List<Person> showAllPersons() {
        Address mockAddress = new Address("2", "Velikanova", "Ryazan",
                "Ryazan State", "390044", "Russia");
        Person mockPerson = new Person("1", "Alex", "123456", "alex.anastasyev@mail.ru", mockAddress);
        List<Person> mockPersonList = new ArrayList<>();
        mockPersonList.add(mockPerson);
        return mockPersonList;
    }

    @GetMapping("rest/person")
    @ResponseBody
    public Person showPersonById(
        @RequestParam(name = "id", required = true) String id
    ) {
        Address mockAddress = new Address("2", "Velikanova", "Ryazan",
                "Ryazan State", "390044", "Russia");
        Person mockPerson = new Person(id, "Alex", "123456", "alex.anastasyev@mail.ru", mockAddress);
        return mockPerson;
    }

    @GetMapping("rest/person/delete")
    @ResponseBody
    public String deletePersonById(
        @RequestParam(name = "id", required = true) String id
    ) {
        return "success";
    }

    @GetMapping("rest/person/update")
    @ResponseBody
    public String updatePersonById(
        @RequestParam(name = "id", required = true) String id,
        @RequestParam(name = "name", required = false) String newName,
        @RequestParam(name = "phone", required = false) String newPhone,
        @RequestParam(name = "email", required = false) String newEmail,
        @RequestParam(name = "address", required = false) String newAddress
    ) {
        return "success";
    }
}
