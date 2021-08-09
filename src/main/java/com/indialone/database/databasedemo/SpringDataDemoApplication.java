package com.indialone.database.databasedemo;

import com.indialone.database.databasedemo.entity.Person;
import com.indialone.database.databasedemo.jpa.PersonJPARepository;
import com.indialone.database.databasedemo.springdata.PersonSpringDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonSpringDataRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("User at id 1001 - {}", repository.findById(1001));
//        logger.info("Deleted users - {}", personJdbcDao.deletePersonById(1003));
        logger.info("Insert Person - {}", repository.save(new Person("Urvashi", "Deesa", new Date())));
        logger.info("update person - {}", repository.save(new Person(1003, "Urudo", "Mahor", new Date())));
        repository.deleteById(1003);
        logger.info("All Users - {}", repository.findAll());
//        logger.info("All Users - {}", personJdbcDao.findAll());
    }
}

/*
 * INSERT INTO PERSON (ID,NAME,LOCATION,BIRTH_DATE) VALUES(1001,
 * 'Natu','Mahor',sysdate());
 */
