package com.oneso.library.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Autor {

    private long id;

    private String name;

    private List<String> books;

    public Autor(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Autor(String name) {
        this(0, name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {

        if(books.isEmpty()) {
            books = new ArrayList<>();
        }

        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
