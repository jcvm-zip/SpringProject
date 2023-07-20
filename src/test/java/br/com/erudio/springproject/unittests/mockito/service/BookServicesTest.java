package br.com.erudio.springproject.unittests.mockito.service;

import br.com.erudio.springproject.data.vo.v1.BookVO;
import br.com.erudio.springproject.data.vo.v1.mock.MockBook;
import br.com.erudio.springproject.exceptions.RequiredObjectIsNullException;
import br.com.erudio.springproject.model.Book;
import br.com.erudio.springproject.repository.BookRepository;
import br.com.erudio.springproject.service.BookServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices services;
    @Mock
    BookRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() throws Exception {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = services.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));

        assertEquals("Some Title1", result.getTitle());
        assertEquals("Some Author1", result.getAuthor());
    }

    @Test
    void findAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);

        var books = services.findall();

        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getKey());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Some Title1", bookOne.getTitle());
        assertEquals("Some Author1", bookOne.getAuthor());


        var bookSeven = books.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getKey());
        assertNotNull(bookSeven.getLinks());
        assertTrue(bookSeven.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
        assertEquals("Some Title7", bookSeven.getTitle());
        assertEquals("Some Author7", bookSeven.getAuthor());

        var bookTwelve = books.get(12);
        assertNotNull(bookTwelve);
        assertNotNull(bookTwelve.getKey());
        assertNotNull(bookTwelve.getLinks());
        assertTrue(bookTwelve.toString().contains("links: [</api/books/v1/12>;rel=\"self\"]"));
        assertEquals("Some Title12", bookTwelve.getTitle());
        assertEquals("Some Author12", bookTwelve.getAuthor());
    }

    /*
    @Test
    void create() throws Exception {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = services.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Some Title1", result.getTitle());
        assertEquals("Some Author1", result.getAuthor());

    }

     */
    @Test
    void testCreateWithNullBook() throws Exception {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> { services.create(null); });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
    @Test
    void testUpdateWithNullBook() throws Exception {

        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> { services.update(null); });

        String expectedMessage = "It is not allowed to persist a null object";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void update() throws Exception {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = services.update(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
        assertEquals("Some Title1", result.getTitle());
        assertEquals("Some Author1", result.getAuthor());
    }

    @Test
    void delete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        services.delete(1L);
    }
}