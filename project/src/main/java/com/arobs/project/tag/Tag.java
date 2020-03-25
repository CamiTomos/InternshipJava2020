package com.arobs.project.tag;

import com.arobs.project.book.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tagDescription")
    private String tagDescription;

    @ManyToMany(mappedBy = "tags")
    private Set<Book> books = new HashSet<>();

    public Tag() {
    }

    public Tag(int id, String tagDescription) {
        this.id = id;
        this.tagDescription = tagDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id == tag.id &&
                tagDescription.equals(tag.tagDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagDescription);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagDescription='" + tagDescription +
                '}';
    }
}
