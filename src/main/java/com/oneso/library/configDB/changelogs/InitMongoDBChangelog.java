package com.oneso.library.configDB.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class InitMongoDBChangelog {

    private Genre fantasy;
    private Genre horrors;
    private Genre romance;

    private Author king;
    private Author rowlong;
    private Author glukhovsky;

    private Book it;

    @ChangeSet(order = "001", author = "sergey", id = "dropDB", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", author = "sergey", id = "initGenre", runAlways = true)
    public void initGenre(MongoTemplate template) {
        fantasy = template.save(new Genre("Fantasy"));
        horrors = template.save(new Genre("Horrors"));
        romance = template.save(new Genre("Romance"));
    }

    @ChangeSet(order = "003", author = "sergey", id = "initAuthor", runAlways = true)
    public void initAuthor(MongoTemplate template) {
        king = template.save(new Author("Stephen Edwin King"));
        rowlong = template.save(new Author("Joanne Rowling"));
        glukhovsky = template.save(new Author("Dmitry Alexeevich Glukhovsky"));
    }

    @ChangeSet(order = "004", author = "sergey", id = "initBook", runAlways = true)
    public void initBook(MongoTemplate template) {
        template.save(new Book("Harry Potter and the Philosopher's Stone", rowlong, fantasy));
        template.save(new Book("Metro 2034", glukhovsky, romance));
        it = template.save(new Book("It", king, horrors));
    }

    @ChangeSet(order = "004", author = "sergey", id = "initComment", runAlways = true)
    public void initComment(MongoTemplate template) {
        template.save(new Comment("Good", it));
    }
}
