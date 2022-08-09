package com.kakura.springcourse.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book extends AbstractEntity{
    private Person person;
    @Size(min = 1, max = 100, message = "Название должно быть от 1 до 100 символов")
    private String title;
    @Pattern(regexp = "[А-Я\\p{Upper}][а-яё\\p{Lower}]{1,19} [А-Я\\p{Upper}][а-яё\\p{Lower}]{1,19}", message = "Введите в формате \"Фамилия Имя\"")
    private String author;
    @NotNull(message = "Заполните это поле")
    private int year;

    public Book() {

    }

    public Book(int id, Person person, String title, String author, int year) {
        super(id);
        this.person = person;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
