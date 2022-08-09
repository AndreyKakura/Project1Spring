package com.kakura.springcourse.dao.mapper;

import com.kakura.springcourse.dao.PersonDao;
import com.kakura.springcourse.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookMapper implements RowMapper<Book> {


    private PersonDao personDao;

    @Autowired
    public BookMapper(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("book_id"));
        book.setPerson(personDao.show(rs.getInt("person_id")));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        return book;
    }
}
