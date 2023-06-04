package br.com.erudio.springproject.service;

import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.data.vo.v2.PersonVOV2;
import br.com.erudio.springproject.exceptions.ResourceNotFoundException;
import br.com.erudio.springproject.mapper.DozerMapper;
import br.com.erudio.springproject.mapper.custom.PersonMapper;
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

    @Autowired
    PersonMapper mapper;

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity =repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }
    public List<PersonVO> findall() {
        logger.info("Finding one person!");

        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }
    public PersonVO create(PersonVO person) {
        logger.info("Creating one Person");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }
    public PersonVO update(PersonVO person) {
        logger.info("Updating one Person");

        var entity = repository.findById(person.getId()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one Person");
        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        repository.delete(entity);
    }



}
