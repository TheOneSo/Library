package com.oneso.library.services;

import com.oneso.library.domain.Autor;

public interface AutorService {

    void addAutor(String name);

    Autor getAutor(String name);

    void showAllAutors();

    void showAllInfoAutor(String name);

    void deleteAutor(String name);
}
