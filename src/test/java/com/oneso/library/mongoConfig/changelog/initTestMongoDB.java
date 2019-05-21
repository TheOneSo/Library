package com.oneso.library.mongoConfig.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.domain.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeLog(order = "001")
public class initTestMongoDB {

    private Genre testG;

    private Author testA;

    private Book testB;

    @ChangeSet(order = "001", author = "sergey", id = "dropDB", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", author = "sergey", id = "initGenre", runAlways = true)
    public void initGenre(MongoTemplate template) {
        testG = template.save(new Genre("testG", "1"));
    }

    @ChangeSet(order = "003", author = "sergey", id = "initAuthor", runAlways = true)
    public void initAuthor(MongoTemplate template) {
        testA = template.save(new Author("testA", "1"));
    }

    @ChangeSet(order = "004", author = "sergey", id = "initBook", runAlways = true)
    public void initBook(MongoTemplate template) {
        testB = template.save(new Book("testB", "1", testA, testG));
    }

    @ChangeSet(order = "004", author = "sergey", id = "initComment", runAlways = true)
    public void initComment(MongoTemplate template) {
        template.save(new Comment("testC", "1", testB));
    }
}
