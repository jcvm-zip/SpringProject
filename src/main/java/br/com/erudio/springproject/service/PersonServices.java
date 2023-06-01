package br.com.erudio.springproject.service;

import br.com.erudio.springproject.exceptions.ResourceNotFoundException;
import br.com.erudio.springproject.model.Person;
import br.com.erudio.springproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {

        logger.info("Finding one person!");

        return repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));
    }
    public List<Person> findall() {
        logger.info("Finding one person!");

        return repository.findAll();
    }
    public Person create(Person person) {
        logger.info("Creating one Person");


        return repository.save(person);
    }
    public Person update(Person person) {
        logger.info("Updating one Person");

        Person entity = repository.findById(person.getId()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }
    public void delete(Long id) {
        logger.info("Deleting one Person");
        Person entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        repository.delete(entity);
    }



}
