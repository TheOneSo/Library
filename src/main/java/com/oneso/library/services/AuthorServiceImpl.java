package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.repository.AuthorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
    }

    @Override
    public Author getAuthor(long id) {
        Optional<Author> author = authorRepository.findById(id);

        return author.orElseGet(Author::new);
    }

    @Override
    public Author getAuthor(String name) {
        Optional<Author> author = authorRepository.findAuthorByName(name);

        return author.orElseGet(Author::new);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll(Sort.by(Sort.Order.asc("name")));
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
