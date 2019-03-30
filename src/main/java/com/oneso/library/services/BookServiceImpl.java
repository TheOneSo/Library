package com.oneso.library.services;

import com.oneso.library.dao.BookDao;
import com.oneso.library.domain.Autor;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook(String book, Autor autor, Genre genre) {
        bookDao.insert(new Book(book, autor, genre));
    }

    @Override
    public void showAllBook() {
        List<Book> books = bookDao.findAll();

        books.forEach(b -> System.out.printf("[Book]: %s%n", b.getName()));
    }

    @Override
    public void showAllInfoToBook(String name) {
        Book _book = bookDao.findByName(name);

        System.out.printf("[Book]: %s%n", _book.getName());
        System.out.printf("[Autor]: %s%n", _book.getAutor().getName());
        System.out.printf("[Genre]: %s%n", _book.getGenre().getName());
    }

    @Override
    public void deleteBook(String name) {
        bookDao.deleteByName(name);
    }
}
