package br.com.erudio.springproject.unittests.mapper;

import br.com.erudio.springproject.data.vo.v1.BookVO;
import br.com.erudio.springproject.data.vo.v1.PersonVO;
import br.com.erudio.springproject.data.vo.v1.mock.MockBook;
import br.com.erudio.springproject.data.vo.v1.mock.MockPerson;
import br.com.erudio.springproject.mapper.DozerMapper;
import br.com.erudio.springproject.model.Book;
import br.com.erudio.springproject.model.Person;
import com.github.dozermapper.core.DozerConverter;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterBookTest {
    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Some Title0", output.getTitle());
        assertEquals("Some Author0", output.getAuthor());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerMapper.parseListObject(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Some Title0", outputZero.getTitle());
        assertEquals("Some Author0", outputZero.getAuthor());

        BookVO outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Some Title7", outputSeven.getTitle());
        assertEquals("Some Author7", outputSeven.getAuthor());

        BookVO outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Some Title12", outputTwelve.getTitle());
        assertEquals("Some Author12", outputTwelve.getAuthor());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = DozerMapper.parseObject(inputObject.mockVO(), Book.class);

        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Some Title0", output.getTitle());
        assertEquals("Some Author0", output.getAuthor());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = DozerMapper.parseListObject(inputObject.mockVOList(), Book.class);

        Book outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Some Title0", outputZero.getTitle());
        assertEquals("Some Author0", outputZero.getAuthor());

        Book outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Some Title7", outputSeven.getTitle());
        assertEquals("Some Author7", outputSeven.getAuthor());

        Book outputTwelve = outputList.get(12);

        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Some Title12", outputTwelve.getTitle());
        assertEquals("Some Author12", outputTwelve.getAuthor());
    }
}
