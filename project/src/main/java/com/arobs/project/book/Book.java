package com.arobs.project.book;

import com.arobs.project.copy.Copy;
import com.arobs.project.rentRequest.RentRequest;
import com.arobs.project.tag.Tag;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "bookTitle")
    private String bookTitle;

    @Column(name = "bookAuthor")
    private String bookAuthor;

    @Column(name = "bookDescription")
    private String bookDescription;

    @Column(name = "bookAddedDate")
    private Timestamp bookAddedDate;

    @OneToMany(mappedBy = "book")
    private Set<Copy> copies = new HashSet<>();

    @ManyToMany(mappedBy = "books")
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "book")
    Set<RentRequest> rentRequests = new HashSet<>();

    public Book() {
    }


    public Book(int id, String bookTitle, String bookAuthor, String bookDescription, Timestamp bookAddedDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookAddedDate = bookAddedDate;
    }

    public Book(String bookTitle, String bookAuthor, String bookDescription, Timestamp bookAddedDate) {
        this(1, bookTitle, bookAuthor, bookDescription, bookAddedDate);
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

    public Timestamp getBookAddedDate() {
        return bookAddedDate;
    }

    public void setBookAddedDate(Timestamp bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    public Set<Copy> getCopies() {
        return copies;
    }

    public void setCopies(Set<Copy> copies) {
        this.copies = copies;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(Set<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookAddedDate=" + bookAddedDate +
//                ", copies=" + copies +
//                ", tags=" + tags +
//                ", rentRequests=" + rentRequests +
                '}';
    }
}
