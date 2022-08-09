package com.kakura.springcourse.dao;

import com.kakura.springcourse.dao.mapper.BookMapper;
import com.kakura.springcourse.models.Book;
import com.kakura.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDao {
    private static final String SQL_SELECT_BOOKS = "SELECT book_id, person_id, title, author, year FROM book";
    private static final String SQL_INSERT_INTO_BOOK = "INSERT INTO book(title, author, year) VALUES(?, ?, ?)";
    private static final String SQL_SELECT_BOOK_BY_ID = "SELECT book_id, person_id, title, author, year FROM book WHERE book_id=?";
    private static final String SQL_UPDATE_BOOK_BY_ID = "UPDATE book SET title=?, author=?, year=? WHERE book_id=?";
    private static final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM book WHERE book_id=?";
    private static final String SQL_SET_PERSON = "UPDATE book SET person_id=? WHERE book_id=?";
    private static final String SQL_REMOVE_PERSON = "UPDATE book SET person_id=null WHERE book_id=?";
    private static final String SQL_SELECT_BOOKS_BY_PERSON_ID = "SELECT book_id, person_id, title, author, year FROM book WHERE person_id=?";


    private final JdbcTemplate jdbcTemplate;

    private final BookMapper bookMapper;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate, BookMapper bookMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookMapper = bookMapper;
    }

    public List<Book> index() {
        return jdbcTemplate.query(SQL_SELECT_BOOKS, bookMapper);
    }

    public Book show(int id) {
        return jdbcTemplate.query(SQL_SELECT_BOOK_BY_ID, new Object[]{id}, bookMapper).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_BOOK_BY_ID, id);
    }

    public void save(Book book) {
        jdbcTemplate.update(SQL_INSERT_INTO_BOOK, book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(SQL_UPDATE_BOOK_BY_ID, updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void setPerson(int personId, int bookId) {
        jdbcTemplate.update(SQL_SET_PERSON, personId, bookId);
    }

    public void removePerson(int bookId) {
        jdbcTemplate.update(SQL_REMOVE_PERSON, bookId);
    }

    public List<Book> getPersonBooks(int personId) {
        return jdbcTemplate.query(SQL_SELECT_BOOKS_BY_PERSON_ID, new Object[]{personId}, bookMapper);
    }
}
