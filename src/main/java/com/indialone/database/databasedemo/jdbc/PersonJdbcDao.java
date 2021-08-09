package com.indialone.database.databasedemo.jdbc;

import com.indialone.database.databasedemo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/*
    use of @Repository here is for tell that we are accessing the database otherwise @Component and @Service
    are make same work there no problem found if we use them
 */

//@Repository
//@Component
//@Service
public class PersonJdbcDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class PersonRowMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            Person person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setLocation(resultSet.getString("location"));
            person.setBirthDate(resultSet.getTimestamp("birth_date"));
            return person;
        }
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonRowMapper());
    }

    public Person findPersonById(int id) {
        return jdbcTemplate
                .queryForObject("select * from person where id=?", new PersonRowMapper(), id);

                /*  below method is deprecated so use above method for queryForObject()


                .queryForObject("select * from person where id=?", new Object[] {id},
                        new BeanPropertyRowMapper<>(Person.class));

                 */
    }

    public int deletePersonById(int id) {
        return jdbcTemplate.update("delete from person where id=?", id);
    }

    public int insert(Person person) {
        return jdbcTemplate.update(
                "insert into person (id, name, location, birth_date) values(?, ?, ?, ?)",
                person.getId(),
                person.getName(),
                person.getLocation(),
                new Timestamp(person.getBirthDate().getTime()));
    }

    public int update(Person person) {
        return jdbcTemplate.update(
                "update person " +
                        " set name = ?, location = ?, birth_date = ?" +
                        " where id = ?",
                person.getName(),
                person.getLocation(),
                new Timestamp(person.getBirthDate().getTime()),
                person.getId());
    }

}
