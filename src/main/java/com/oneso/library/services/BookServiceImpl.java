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
        Author author = new Author();
        author.setId(author_id);
        Genre genre = new Genre();
        genre.setId(genre_id);

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.insert(book);
    }

    @Override
    public String getAllBooks() {
        StringBuilder builder = new StringBuilder();
        List<Book> books = bookRepository.findAll();

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));

        return builder.toString();
    }

    @Override
    public String getBookById(long id) {
        StringBuilder builder = new StringBuilder();
        Book book = bookRepository.findById(id);

        builder.append(String.format("[Author]: %s\n", book.getAuthor().getName()));
        builder.append(String.format("[Book]: %s\n", book.getName()));
        builder.append(String.format("[Genre]: %s\n", book.getGenre().getName()));

        return builder.toString();
    }

    @Override
    public String getAllBookByAuthorId(long author_id) {
        StringBuilder builder = new StringBuilder();
        List<Book> books = bookRepository.findAllBookByAuthorId(author_id);

        builder.append(String.format("[Author_%d]: %s\n",
                books.get(0).getAuthor().getId(), books.get(0).getAuthor().getName()));

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));

        return builder.toString();
    }

    @Override
    public String getAllBookByGenreId(long genre_id) {
        StringBuilder builder = new StringBuilder();
        List<Book> books = bookRepository.findAllBookByGenreId(genre_id);

        builder.append(String.format("[Genre_%d]: %s\n",
                books.get(0).getGenre().getId(), books.get(0).getGenre().getName()));

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));

        return builder.toString();
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
