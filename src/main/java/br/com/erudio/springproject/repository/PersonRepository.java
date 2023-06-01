package br.com.erudio.springproject.repository;

import br.com.erudio.springproject.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
