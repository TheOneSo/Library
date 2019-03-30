package com.oneso.library.services;

import com.oneso.library.dao.BookDao;
import com.oneso.library.domain.Author;
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
    public void addBook(String book, Author author, Genre genre) {
        bookDao.insert(new Book(book, author, genre));
    }

    @Override
    public void showAllBook() {
        List<Book> books = bookDao.findAll();

        books.forEach(b -> System.out.printf("[Book_%d]: %s%n", b.getId(), b.getName()));
    }

    @Override
    public void showAllBookByAuthor(long authorId) {
        List<Book> books = bookDao.findAllBookByAuthorId(authorId);

        if(!books.isEmpty()) {
            System.out.printf("[Author_%d]: %s%n", books.get(0).getAuthor().getId(), books.get(0).getAuthor().getName());

            for(Book temp : books) {
                System.out.printf("[Book_%d]: %s%n", temp.getId(), temp.getName());
                System.out.printf("[Genre_%d]: %s%n", temp.getGenre().getId(), temp.getGenre().getName());
            }
        }
    }

    @Override
    public void showInfoByBook(String name) {
        Book book = bookDao.findByName(name);

        System.out.printf("[Book]: %s%n", book.getName());
        System.out.printf("[Author]: %s%n", book.getAuthor().getName());
        System.out.printf("[Genre]: %s%n", book.getGenre().getName());
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteById(id);
    }
}
