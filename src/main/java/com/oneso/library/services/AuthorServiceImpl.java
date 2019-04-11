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
    public Author getAuthor(long id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(long id) {
        authorRepository.deleteById(id);
    }
}
