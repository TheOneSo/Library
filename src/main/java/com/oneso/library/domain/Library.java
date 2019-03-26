package com.oneso.library.domain;

public class Library {

    private String autor;

    private String book;

    private String genre;

    public Library(String autor, String book, String genre) {
        this.autor = autor;
        this.book = book;
        this.genre = genre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("[Autor]: %s\n[Book]: %s\n[Genre]: %s", this.getAutor(), this.getBook(), this.getGenre());
    }
}
