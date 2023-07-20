package br.com.erudio.springproject.data.vo.v1.mock;

import br.com.erudio.springproject.data.vo.v1.BookVO;
import br.com.erudio.springproject.model.Book;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVO() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> booksVOS = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksVOS.add(mockVO(i));
        }
        return booksVOS;
    }
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setId(number.longValue());
        book.setTitle("Some Title" + number);
        book.setAuthor("Some Author" + number);
        book.setPrice(50.5);
        book.setPublicationDate(OffsetDateTime.now());
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO bookVo = new BookVO();
        bookVo.setKey(number.longValue());
        bookVo.setTitle("Some Title" + number);
        bookVo.setAuthor("Some Author" + number);
        bookVo.setPrice(50.5);
        bookVo.setPublicationDate(OffsetDateTime.now());
        return bookVo;
    }

}
