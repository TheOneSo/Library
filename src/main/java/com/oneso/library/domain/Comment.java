package com.oneso.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "comment")
public class Comment {

    @Id
    private String id;

    @Field("text")
    private String text;

    //@DBRef
    @Field("book")
    private Book book;

    public Comment(String name, Book book) {
        this.text = name;
        this.book = book;
    }

    public Comment() {}

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
