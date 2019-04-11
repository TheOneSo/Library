package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.domain.Genre;

import java.util.List;

public interface EntityPrinterService {

    String preparePrintBooks(List<Book> books);

    String preparePrintAuthors(List<Author> authors);

    String preparePrintGenres(List<Genre> authors);

    String preparePrintComments(List<Comment> comments);

    String preparePrintBook(Book book);

    String preparePrintAuthorWithBook(List<Book> books);

    String preparePrintGenreWithBook(List<Book> books);
}
