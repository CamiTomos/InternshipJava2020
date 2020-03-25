package com.arobs.project.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("bookJDBCRepository")
public class BookJDBCRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks() {
        String query = "select * from books";
        return jdbcTemplate.query(query, ((resultSet, i) -> new Book(
                resultSet.getInt("id"),
                resultSet.getString("bookTitle"),
                resultSet.getString("bookAuthor"),
                resultSet.getString("bookDescription")
        )));
    }

    public Book insertBook(Book book) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement(
                    "insert into books (bookTitle, bookAuthor, bookDescription, bookAddedDate) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getBookTitle());
            statement.setString(2, book.getBookAuthor());
            statement.setString(3, book.getBookDescription());
            statement.setTimestamp(4, book.getBookAddedDate());
            return statement;
        }, holder);
        book.setId((int) holder.getKey().longValue());
        return book;
    }

    public Book updateBook(Book book) {
        String query = "UPDATE books SET bookTitle=?, bookAuthor=?, bookDescription=?, bookAddedDate=? WHERE id=? ";
        jdbcTemplate.update(query,
                book.getBookTitle(),
                book.getBookAuthor(),
                book.getBookDescription(),
                book.getBookAddedDate(),
                book.getId());
        return findById(book.getId());
    }

    public boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE id=?";
        int rows = jdbcTemplate.update(query, id);
        return rows > 0;
    }

    public Book findById(int id) {
        String query = "Select * FROM books WHERE id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) -> new Book(
                resultSet.getInt("id"),
                resultSet.getString("bookTitle"),
                resultSet.getString("bookAuthor"),
                resultSet.getString("bookDescription")
        ));
    }
}
