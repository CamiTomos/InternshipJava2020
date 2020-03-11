package com.arobs.project.copy;

import com.arobs.project.book.Book;
import com.arobs.project.bookRent.BookRent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "copies")
public class Copy implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "copyFlag")
    private boolean copyFlag;

    @Column(name = "copyStatus")
    private String copyStatus;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private Book book;

    @OneToMany(mappedBy = "copy")
    private Set<BookRent> bookRents = new HashSet<>();

    public Copy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }

    public String getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        this.copyStatus = copyStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Set<BookRent> getBookRents() {
        return bookRents;
    }

    public void setBookRents(Set<BookRent> bookRents) {
        this.bookRents = bookRents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copy copy = (Copy) o;
        return id == copy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Copy{" +
                "id=" + id +
                ", copyFlag=" + copyFlag +
                ", copyStatus='" + copyStatus + '\'' +
                ", book=" + book +
                ", bookRents=" + bookRents +
                '}';
    }
}
