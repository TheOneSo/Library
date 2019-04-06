package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.insert(author);
    }

    @Override
    public String getAuthor(long id) {
        Author author = authorRepository.findById(id);

        return String.format("[Author_%d]: %s", author.getId(), author.getName());
    }

    @Override
    public String getAllAuthors() {
        StringBuilder builder = new StringBuilder();
        List<Author> authors = authorRepository.findAll();

        authors.forEach(a -> builder.append(String.format("[Author_%d]: %s\n", a.getId(), a.getName())));

        return builder.toString();
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
