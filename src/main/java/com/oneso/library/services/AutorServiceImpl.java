package com.oneso.library.services;

import com.oneso.library.dao.AutorDao;
import com.oneso.library.domain.Autor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorDao autorDao;

    public AutorServiceImpl(AutorDao autorDao) {
        this.autorDao = autorDao;
    }

    @Override
    public void addAutor(String name) {
        Autor _autor = new Autor(name);
        autorDao.insert(_autor);
    }

    @Override
    public Autor getAutor(String name) {
        return autorDao.findByName(name);
    }

    @Override
    public void showAllAutors() {
        List<Autor> autors = autorDao.findAll();

        autors.forEach(autor -> System.out.printf("[Autor]: %s%n", autor.getName()));
    }

    @Override
    public void showAllInfoAutor(String name) {
        Autor _autor = autorDao.findByName(name);
        _autor.setBooks(autorDao.findAllBookByAutor(name));

        System.out.printf("[Autor]: %s%n", _autor.getName());
        _autor.getBooks().forEach(book -> System.out.printf("[Book]: %s%n", book));
    }

    @Override
    public void deleteAutor(String name) {
        autorDao.deleteByName(name);
    }
}
