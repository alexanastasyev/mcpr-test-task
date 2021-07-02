package org.example.mcprwebapp.person.service;

import org.example.mcprwebapp.person.Person;
import org.example.mcprwebapp.person.database.PersonConverter;
import org.example.mcprwebapp.person.database.PersonEntity;
import org.example.mcprwebapp.person.database.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("rest/person")
public class PersonController {
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonController(
        @Autowired PersonRepository personRepository,
        @Autowired PersonConverter personConverter
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Person> showAllPersons() {
        List<PersonEntity> personEntities = personRepository.getAll();
        List<Person> persons = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            persons.add(personConverter.convertEntityToPerson(personEntity));
        }
        return persons;
    }

    @GetMapping("/find")
    @ResponseBody
    public Person showPersonById(@RequestParam(name = "id") String id) {
        PersonEntity personEntity = personRepository.getById(id);
        if (personEntity != null) {
            return personConverter.convertEntityToPerson(personEntity);
        } else {
            return Person.NULL_PERSON;
        }
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deletePersonById(@RequestParam(name = "id") String id) {
        personRepository.deleteById(id); // TODO: handle exception
        return "success";
    }

    @GetMapping("/update")
    @ResponseBody
    public String updatePersonById(
        @RequestParam(name = "id") String id,
        @RequestParam(name = "name", required = false) String newName,
        @RequestParam(name = "phone", required = false) String newPhone,
        @RequestParam(name = "email", required = false) String newEmail,
        @RequestParam(name = "addressId", required = false) String newAddressId
    ) {
        PersonEntity personEntity = personRepository.getById(id);
        if (personEntity == null) {
            return "Error: person with id \"" + id + "\" doesn't exist";
        }
        if (newName == null) {
            newName = personEntity.getName();
        }
        if (newPhone == null) {
            newPhone = personEntity.getPhone();
        }
        if (newEmail == null) {
            newEmail = personEntity.getEmail();
        }
        if (newAddressId == null) {
            newAddressId = personEntity.getAddressId();
        }
        personRepository.updateById(id, newName, newPhone, newEmail, newAddressId); // TODO: handle exception
        return "success";
    }
}
