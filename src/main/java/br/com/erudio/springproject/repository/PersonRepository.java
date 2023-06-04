package br.com.erudio.springproject.repository;

import br.com.erudio.springproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
