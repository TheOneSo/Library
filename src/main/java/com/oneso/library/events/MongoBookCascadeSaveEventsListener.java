package com.oneso.library.events;

import com.oneso.library.domain.Book;
import com.oneso.library.repository.AuthorRepository;
import com.oneso.library.repository.GenreRepository;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public MongoBookCascadeSaveEventsListener(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);

        Book book = event.getSource();

        if(book.getAuthor().getId() != null) {
            book.setAuthor(authorRepository.findById(book.getAuthor().getId()).orElseGet(
                    () -> authorRepository.save(book.getAuthor())));
        } else {
            authorRepository.save(book.getAuthor());
        }

        if(book.getGenre().getId() != null) {
            book.setGenre(genreRepository.findById(book.getGenre().getId()).orElseGet(
                    () -> genreRepository.save(book.getGenre())));
        } else {
            genreRepository.save(book.getGenre());
        }
    }
}
