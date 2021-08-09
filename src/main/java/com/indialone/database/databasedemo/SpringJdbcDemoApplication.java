package com.indialone.database.databasedemo;

import com.indialone.database.databasedemo.entity.Person;
import com.indialone.database.databasedemo.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

//@SpringBootApplication
public class SpringJdbcDemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJdbcDao personJdbcDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All Users - {}", personJdbcDao.findAll());
        logger.info("User at id 1002 - {}", personJdbcDao.findPersonById(1002));
        logger.info("Deleted users - {}", personJdbcDao.deletePersonById(1003));
        logger.info("Insert Person - {}", personJdbcDao.insert(new Person(1004, "Urvashi", "Deesa", new Date())));
        logger.info("update person - {}", personJdbcDao.update(new Person(1004, "Urudo", "Mahor", new Date())));
        logger.info("All Users - {}", personJdbcDao.findAll());
    }
}

/*
 * INSERT INTO PERSON (ID,NAME,LOCATION,BIRTH_DATE) VALUES(1001,
 * 'Natu','Mahor',sysdate());
 */
