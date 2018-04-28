package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    Person findByLogin(String login);
    Person findByEmail(String email);
    Person findByPid (long id);
}
