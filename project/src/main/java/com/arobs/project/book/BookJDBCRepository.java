package com.arobs.project.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("bookRepository")
public class BookJDBCRepository {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.dataSource=dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public List<Book> getAllBooks(){
        String query="select * from books";
        return jdbcTemplate.query(query,((resultSet, i) -> new Book(
                resultSet.getInt("id"),
                resultSet.getString("bookTitle"),
                resultSet.getString("bookAuthor"),
                resultSet.getString("bookDescription"),
                resultSet.getTimestamp("bookAddedDate")
        )));
    }

    public int insertBook(Book book){
        String query="insert into books (bookTitle, bookAuthor, bookDescription, bookAddedDate) values (?,?,?,?)";
        return jdbcTemplate.update(query,book.getBookTitle(),book.getBookAuthor(),book.getBookDescription(),book.getBookAddedDate());
    }

    public void updateBook(Book book) {
        String query = "UPDATE books SET bookTitle=?, bookAuthor=?, bookDescription=?, bookAddedDate=? WHERE id=? ";
        jdbcTemplate.update(query, book.getBookTitle(),book.getBookAuthor(),book.getBookDescription(),book.getBookAddedDate(),book.getId());
    }

    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id=?";
        jdbcTemplate.update(query, id);
    }

    public Book findById(int id){
        String query = "Select * FROM books WHERE id=?";
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        Book book = jdbcTemplate.queryForObject(query, rowMapper, id);
        return book;
    }

}
