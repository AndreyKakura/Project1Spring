package com.kakura.springcourse.models;

public class AbstractEntity {
    private int id;

    public AbstractEntity() {

    }

    public AbstractEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
