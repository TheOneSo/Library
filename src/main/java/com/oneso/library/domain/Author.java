package com.oneso.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection = "author")
public class Author {

    @Id
    private String id;

    @Field("name")
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Author() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
