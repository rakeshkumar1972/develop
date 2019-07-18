package com.cardina.schedular.event.repository;


import org.springframework.data.repository.CrudRepository;

import com.cardinal.schedular.model.Person;

import java.util.List;

public interface PersonRepository<P> extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
}
