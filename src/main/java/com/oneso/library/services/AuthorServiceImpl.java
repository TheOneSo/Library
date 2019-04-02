package com.oneso.library.services;

import com.oneso.library.dao.AuthorDao;
import com.oneso.library.domain.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void addAuthor(String name) {
        Author author = new Author(name);
        authorDao.insert(author);
    }

    @Override
    public Author getAuthor(String name) {
        return authorDao.findByName(name);
    }

    @Override
    public Author getAuthor(long id) {
        return authorDao.findById(id);
    }

    @Override
    public void showAllAuthors() {
        List<Author> authors = authorDao.findAll();

        authors.forEach(author -> System.out.printf("[ID]: %d\n[Author]: %s", author.getId(), author.getName()));
    }

    @Override
    public void deleteAuthor(long id) {
        authorDao.deleteById(id);
    }
}
