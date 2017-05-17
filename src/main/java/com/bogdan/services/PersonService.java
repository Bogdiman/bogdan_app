package com.bogdan.services;

import com.bogdan.data.Person;
import com.bogdan.repositories.PersonRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bitfoi on 5/17/2017.
 */
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPersons() {
        return Lists.newArrayList(personRepository.findAll());
    }

    public void save(Person p) {
        personRepository.save(p);
    }
}
