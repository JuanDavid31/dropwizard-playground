package org.mobile.db;

import org.jdbi.v3.core.Jdbi;
import org.mobile.entity.Person;

import java.util.Optional;

public class PersonDao {

    private Jdbi jdbi;

    public PersonDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Optional<Person> addPerson(Person person) {
        return jdbi.withHandle(handle -> handle.createUpdate("INSERT INTO PERSON(name) VALUES(:name)")
            .bindBean(person)
            .executeAndReturnGeneratedKeys()
            .mapToBean(Person.class)
            .findOne());
    }
}
