package br.com.erudio.springproject.service;

import br.com.erudio.springproject.controller.PersonController;
import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.data.vo.v2.PersonVOV2;
import br.com.erudio.springproject.exceptions.RequiredObjectIsNullException;
import br.com.erudio.springproject.exceptions.ResourceNotFoundException;
import br.com.erudio.springproject.mapper.DozerMapper;
import br.com.erudio.springproject.mapper.custom.PersonMapper;
import br.com.erudio.springproject.model.Person;
import br.com.erudio.springproject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding one person!");

        var entity =repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }
    public List<PersonVO> findall() {
        logger.info("Finding one person!");

        var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return persons;
    }

    public PersonVO create(PersonVO person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Person");

        var entity = repository.findById(person.getKey()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one Person");
        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        repository.delete(entity);
    }



}
