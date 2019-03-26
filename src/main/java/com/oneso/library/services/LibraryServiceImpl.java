package com.oneso.library.services;

import com.oneso.library.dao.LibraryDao;
import com.oneso.library.domain.Library;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryDao libraryDao;

    public LibraryServiceImpl(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    private String show(List<String> text) {
        StringBuilder builder = new StringBuilder();

        for(String temp : text) {
            builder.append(temp);
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public String showAll() {
        StringBuilder builder = new StringBuilder();
        List<Library> libraries = libraryDao.getAll();

        for(Library temp : libraries) {
            builder.append(temp);
            builder.append("\n");
        }

        return builder.toString();
    }

    @Override
    public String showAllBook() {
        return show(libraryDao.getAllBook());
    }

    @Override
    public String showAllAutors() {
        return show(libraryDao.getAllAutor());
    }

    @Override
    public String showAllGenres() {
        return show(libraryDao.getAllGenre());
    }

    @Override
    public String showBook(String name) {
        StringBuilder builder = new StringBuilder();
        List<Library> libraries = libraryDao.getByNameBook(name);

        for(Library temp : libraries) {
            builder.append(temp);
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public void createAutor(String name) {
        if(libraryDao.findIdAutor(name) == 0) {
            libraryDao.insertAutor(name);
        }
    }

    @Override
    public void createGenre(String genre) {
        if(libraryDao.findIdGenre(genre) == 0) {
            libraryDao.insertGenre(genre);
        }
    }

    @Override
    public void createBook(String book, String autor, String genre) {
        createAutor(autor);
        createGenre(genre);

        int autorId = libraryDao.findIdAutor(autor);
        int genreId = libraryDao.findIdGenre(genre);

        libraryDao.insertBook(book, autorId, genreId);
    }

    @Override
    public void deleteAutor(String autor) {
        libraryDao.deleteByNameAutor(autor);
    }

    @Override
    public void deleteBook(String book) {
        libraryDao.deleteByNameBook(book);
    }

    @Override
    public void deleteGenre(String genre) {
        libraryDao.deleteByNameGenre(genre);
    }
}
