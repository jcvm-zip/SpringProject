package br.com.erudio.springproject.repository;

import br.com.erudio.springproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
