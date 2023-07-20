package br.com.erudio.springproject.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonPropertyOrder({"id", "author", "publicationDate", "price","title"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private static final long serialVersionUID = -4594282845865289075L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String title;
    private String author;
    private OffsetDateTime publicationDate;
    private Double price;

    public BookVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public OffsetDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(OffsetDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO booksVO = (BookVO) o;
        return Objects.equals(key, booksVO.key) && Objects.equals(title, booksVO.title) && Objects.equals(author, booksVO.author) && Objects.equals(publicationDate, booksVO.publicationDate) && Objects.equals(price, booksVO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, title, author, publicationDate, price);
    }
}
