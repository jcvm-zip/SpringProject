package br.com.erudio.springproject;

import br.com.erudio.springproject.exceptions.UnsupportedMathOperationException;
import br.com.erudio.springproject.model.Person;
import br.com.erudio.springproject.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @RequestMapping(value="/{id}",
            method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable("id") String id) throws Exception {

        return services.findById(id);
    }

    @RequestMapping(
            method=RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return services.findall();
    }

    @RequestMapping(method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {
        return services.create(person);
    }

    @RequestMapping(method=RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {
        return services.create(person);
    }

    @RequestMapping(value="/{id}",
            method=RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) throws Exception {
        services.delete(id);
    }



}