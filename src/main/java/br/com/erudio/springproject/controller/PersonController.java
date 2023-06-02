package br.com.erudio.springproject.controller;

import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping(value="/{id}")
    public PersonVO findById(@PathVariable("id") Long id) throws Exception {
        return services.findById(id);
    }

    @GetMapping
    public List<PersonVO> findAll() {
        return services.findall();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create(@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person) {
        return services.create(person);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}