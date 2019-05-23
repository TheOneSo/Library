package com.oneso.library.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "genre")
public class Genre {

    @Id
    private String id;

    @Field("name")
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public Genre(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Genre() {}

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
