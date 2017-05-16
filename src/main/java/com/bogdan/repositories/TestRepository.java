package com.bogdan.repositories;

import com.bogdan.data.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by bitfoi on 5/16/2017.
 */
public interface TestRepository extends CrudRepository<Person, Long>{
    Person findByName(String name);
}
