package com.kakura.springcourse.dao;

import com.kakura.springcourse.dao.mapper.PersonMapper;
import com.kakura.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {
    private static final String SQL_SELECT_PEOPLE = "SELECT person_id, name, year_of_birth FROM person";
    private static final String SQL_INSERT_INTO_PERSON = "INSERT INTO person(name, year_of_birth) VALUES(?, ?)";
    private static final String SQL_SELECT_PERSON_BY_ID = "SELECT person_id, name, year_of_birth FROM person WHERE person_id=?";
    private static final String SQL_UPDATE_PERSON_BY_ID = "UPDATE person SET name=?, year_of_birth=? WHERE person_id=?";
    private static final String SQL_DELETE_PERSON_BY_ID = "DELETE FROM person WHERE person_id=?";
    private static final String SQL_SELECT_PERSON_BY_NAME = "SELECT person_id, name, year_of_birth FROM person WHERE name=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(SQL_SELECT_PEOPLE, new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_ID, new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String name) {
        return jdbcTemplate.query(SQL_SELECT_PERSON_BY_NAME, new Object[]{name}, new PersonMapper()).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update(SQL_INSERT_INTO_PERSON, person.getName(), person.getYearOfBirth());
    }

    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_PERSON_BY_ID, id);
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(SQL_UPDATE_PERSON_BY_ID, updatedPerson.getName(), updatedPerson.getYearOfBirth(), updatedPerson.getId());
    }
}
