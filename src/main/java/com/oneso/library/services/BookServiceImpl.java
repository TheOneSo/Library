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
    public void addBook(String bookName, long author_id, long genre_id) {
        Book book = new Book(bookName, new Author(author_id), new Genre(genre_id));
        bookRepository.insert(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBookByAuthorId(long author_id) {
        return bookRepository.findAllBookByAuthorId(author_id);
    }

    @Override
    public List<Book> getAllBookByGenreId(long genre_id) {
        return bookRepository.findAllBookByGenreId(genre_id);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
