package com.oneso.library.events;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import com.oneso.library.repository.AuthorRepository;
import com.oneso.library.repository.GenreRepository;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

        if(book.getAuthor() != null) {
            if(!authorRepository.findAuthorByName(book.getAuthor().getName()).isPresent()) {
                authorRepository.save(book.getAuthor());
            } else {
                Optional<Author> author = authorRepository.findAuthorByName(book.getAuthor().getName());
                book.setAuthor(author.get());
            }
        }

        if(book.getGenre() != null) {
            if(!genreRepository.findGenreByName(book.getGenre().getName()).isPresent()) {
                genreRepository.save(book.getGenre());
            } else {
                Optional<Genre> genre = genreRepository.findGenreByName(book.getGenre().getName());
                book.setGenre(genre.get());
            }
        }
    }
}
