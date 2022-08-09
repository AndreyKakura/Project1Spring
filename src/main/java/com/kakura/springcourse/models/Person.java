package com.kakura.springcourse.models;

import javax.validation.constraints.*;

public class Person extends AbstractEntity{

    @Pattern(regexp = "[А-Я\\p{Upper}][а-яё\\p{Lower}]{1,19} [А-Я\\p{Upper}][а-яё\\p{Lower}]{1,19} [А-Я\\p{Upper}][а-яё\\p{Lower}]{1,19}", message = "Введите в формате \"Фамилия Имя Отчество\"")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String name, int yearOfBirth) {
        super(id);
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
