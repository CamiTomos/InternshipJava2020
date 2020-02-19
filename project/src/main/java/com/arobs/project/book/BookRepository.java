package com.arobs.project.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("bookRepository")
public class BookRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
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

}
