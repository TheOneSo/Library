package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreparePreparePrintServiceImpl implements PreparePrintService {

    @Override
    public String preparePrintBooks(List<Book> books) {
        StringBuilder builder = new StringBuilder();

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));
        return builder.toString();
    }

    @Override
    public String preparePrintAuthors(List<Author> authors) {
        StringBuilder builder = new StringBuilder();

        authors.forEach(a -> builder.append(String.format("[Author_%d]: %s\n", a.getId(), a.getName())));
        return builder.toString();
    }

    @Override
    public String preparePrintGenres(List<Genre> genres) {
        StringBuilder builder = new StringBuilder();

        genres.forEach(g -> builder.append(String.format("[Genre_%d]: %s\n", g.getId(), g.getName())));
        return builder.toString();
    }

    @Override
    public String preparePrintComments(List<Comment> comments) {
        StringBuilder builder = new StringBuilder();

        comments.forEach(c -> builder.append(String.format("[%d]: %s\n", c.getId(), c.getText())));
        return builder.toString();
    }

    @Override
    public String preparePrintBook(Book book) {
        return String.format("[Author]: %s\n", book.getAuthor().getName()) +
                String.format("[Book]: %s\n", book.getName()) +
                String.format("[Genre]: %s\n", book.getGenre().getName());
    }

    @Override
    public String preparePrintAuthorWithBook(List<Book> books) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("[Author_%d]: %s\n",
                books.get(0).getAuthor().getId(), books.get(0).getAuthor().getName()));

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));

        return builder.toString();
    }

    @Override
    public String preparePrintGenreWithBook(List<Book> books) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("[Genre_%d]: %s\n",
                books.get(0).getGenre().getId(), books.get(0).getGenre().getName()));

        books.forEach(b -> builder.append(String.format("[Book_%d]: %s\n", b.getId(), b.getName())));

        return builder.toString();
    }
}
