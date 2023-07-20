package br.com.erudio.springproject.mapper.custom;

import br.com.erudio.springproject.data.vo.v1.BookVO;
import br.com.erudio.springproject.model.Book;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
public class BookMapper {

    public BookVO convertEntityToVo(Book book) {
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setTitle(book.getTitle());
        vo.setAuthor(book.getAuthor());
        vo.setPrice(book.getPrice());
        vo.setPublicationDate(OffsetDateTime.now());
        return vo;
    }
    public Book convertVoToEntity(BookVO booksVO) {
        Book entity = new Book();
        entity.setId(booksVO.getKey());
        entity.setTitle(booksVO.getTitle());
        entity.setAuthor(booksVO.getAuthor());
        entity.setPrice(booksVO.getPrice());
        entity.setPublicationDate(OffsetDateTime.now());
        return entity;
    }
}
