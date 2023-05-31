package br.com.erudio.springproject.service;

import br.com.erudio.springproject.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());



    public Person findById(String id) {

        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jefferson");
        person.setLastName("Monteiro");
        person.setAddress("Campo Formoso - BA");
        person.setGender("male");

        return person;
    }
    public List<Person> findall() {
        List<Person> persons = new ArrayList<>();
        for (int i=0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);

        }
        return persons;
    }
    public Person create(Person person) {
        logger.info("Creating one Person");


        return person;
    }
    public Person update(Person person) {
        logger.info("Updating one Person");


        return person;
    }
    public void delete(String id) {
        logger.info("Deleting one Person");

    }

    private Person mockPerson(int i) {

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("person name" + i);
        person.setLastName("last Name" + i);
        person.setAddress("Some address in Brasil" + i);
        person.setGender("gender " +i);

        return person;
    }


}
