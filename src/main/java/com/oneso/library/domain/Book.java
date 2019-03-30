package com.oneso.library.domain;

public class Book {

    private long id;

    private String name;

    private Autor autor;

    private Genre genre;

    public Book(long id, String name, Autor autor, Genre genre) {
        this.id = id;
        this.name = name;
        this.autor = autor;
        this.genre = genre;
    }

    public Book(String name, Autor autor, Genre genre) {
        this(0, name, autor, genre);
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
