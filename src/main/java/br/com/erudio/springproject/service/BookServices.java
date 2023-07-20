package br.com.erudio.springproject.service;

import br.com.erudio.springproject.controller.BooksController;
import br.com.erudio.springproject.controller.PersonController;
import br.com.erudio.springproject.data.vo.v1.BookVO;
import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.exceptions.RequiredObjectIsNullException;
import br.com.erudio.springproject.exceptions.ResourceNotFoundException;
import br.com.erudio.springproject.mapper.DozerMapper;
import br.com.erudio.springproject.mapper.custom.BookMapper;
import br.com.erudio.springproject.model.Book;
import br.com.erudio.springproject.model.Person;
import br.com.erudio.springproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    public BookVO findById(Long id) throws Exception {

        logger.info("Finding one book!");

        var entity =repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<BookVO> findall() {
        logger.info("Finding one Book!");

        var books = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
        books
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return books;

    }

    public BookVO create(BookVO book) throws Exception {

        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Book");

        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);

        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) throws Exception {

        if(book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Book");

        var entity = repository.findById(book.getKey()).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPublicationDate(book.getPublicationDate());
        entity.setPrice(book.getPrice());
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }
    public void delete(Long id) {
        logger.info("Deleting one Book");
        var entity = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!!"));

        repository.delete(entity);
    }

}
