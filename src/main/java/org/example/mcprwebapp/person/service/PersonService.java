package org.example.mcprwebapp.person.service;

import org.example.mcprwebapp.person.Person;
import org.example.mcprwebapp.person.database.PersonConverter;
import org.example.mcprwebapp.person.database.PersonEntity;
import org.example.mcprwebapp.person.database.PersonRepository;
import org.example.mcprwebapp.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequestMapping(ServiceUtils.PERSON_SERVICE_PATH)
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonService(
        @Autowired PersonRepository personRepository,
        @Autowired PersonConverter personConverter
    ) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    @GetMapping(ServiceUtils.GET_ALL_PATH)
    @ResponseBody
    public Map<String, Object> getAllPersons() {
        List<PersonEntity> personEntities = personRepository.getAll();
        List<Person> persons = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            persons.add(personConverter.convertEntityToPerson(personEntity));
        }
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
        result.put(ServiceUtils.ANSWER_RESULT, persons);
        return result;
    }

    @GetMapping(ServiceUtils.GET_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> getPersonById(@RequestParam(name = ServiceUtils.PARAM_ID) String id) {
        PersonEntity personEntity = personRepository.getById(id);
        if (personEntity != null) {
            Map<String, Object> result = new LinkedHashMap<>();
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, personConverter.convertEntityToPerson(personEntity));
            return result;
        } else {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
        }
    }

    @DeleteMapping(ServiceUtils.DELETE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> deletePersonById(@PathVariable(name = ServiceUtils.PARAM_ID) String id) {
        PersonEntity personEntity = personRepository.getById(id);
        if (personEntity == null) {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
        } else {
            personRepository.deleteById(id);
            Map<String, Object> result = new LinkedHashMap<>();
            result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
            result.put(ServiceUtils.ANSWER_RESULT, personConverter.convertEntityToPerson(personEntity));
            return result;
        }
    }

    @PutMapping(ServiceUtils.UPDATE_BY_ID_PATH)
    @ResponseBody
    public Map<String, Object> updatePersonById(
        @PathVariable(name = ServiceUtils.PARAM_ID) String id,
        @RequestParam(name = ServiceUtils.PERSON_PARAM_NAME, required = false) String newName,
        @RequestParam(name = ServiceUtils.PERSON_PARAM_PHONE, required = false) String newPhone,
        @RequestParam(name = ServiceUtils.PERSON_PARAM_EMAIL, required = false) String newEmail,
        @RequestParam(name = ServiceUtils.PERSON_PARAM_ADDRESS_ID, required = false) String newAddressId
    ) {
        PersonEntity personEntity = personRepository.getById(id);
        if (personEntity == null) {
            return ServiceUtils.ERROR_EMPTY_ANSWER;
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
        personRepository.updateById(id, newName, newPhone, newEmail, newAddressId);
        PersonEntity newPersonEntity = personRepository.getById(id);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put(ServiceUtils.ANSWER_STATUS, ServiceUtils.STATUS_SUCCESS);
        result.put(ServiceUtils.ANSWER_RESULT, personConverter.convertEntityToPerson(newPersonEntity));
        return result;
    }
}
