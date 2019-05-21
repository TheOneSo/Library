package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import com.oneso.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBook(String bookName, String author_name, String genre_name) {
        Book book = new Book(bookName, new Author(author_name), new Genre(genre_name));
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(String name) {
        return bookRepository.findBookByName(name).orElseGet(Book::new);
    }

    @Override
    public List<Book> getAllBookByAuthorName(String name) {
        return bookRepository.findBookByAuthorName(name);
    }

    @Override
    public List<Book> getAllBookByGenreName(String name) {
        return bookRepository.findBookByGenreName(name);
    }

    @Override
    public void deleteBook(String name) {
        bookRepository.deleteBookByName(name);
    }
}
