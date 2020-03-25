package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BookDTO {
    private int id;
    @NotNull
    private String bookTitle;
    @NotNull
    private String bookAuthor;
    @NotNull
    private String bookDescription;
    private Set<TagDTO> tags = new HashSet<>();

    public BookDTO() {
    }

    public BookDTO(int id, String bookTitle, String bookAuthor, String bookDescription, Set<TagDTO> tags) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.tags = tags;
    }

    public BookDTO(int id, String bookTitle, String bookAuthor, String bookDescription) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDescription='" + bookDescription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return id == bookDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
