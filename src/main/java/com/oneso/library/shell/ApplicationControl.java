package com.oneso.library.shell;

import com.oneso.library.services.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationControl {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;
    private final EntityPrinterService entityPrinterService;

    public ApplicationControl(BookService bookService, AuthorService authorService, GenreService genreService,
                              CommentService commentService, EntityPrinterService entityPrinterService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.entityPrinterService = entityPrinterService;
    }

    @ShellMethod(value = "Show all Books, Author, Genre in Library", key = "show-all")
    public String showAll() {
        return entityPrinterService.preparePrintAuthors(authorService.getAllAuthors()) +
                entityPrinterService.preparePrintBooks(bookService.getAllBooks()) +
                entityPrinterService.preparePrintGenres(genreService.getAllGenres());
    }

    @ShellMethod(value = "Show all books in library", key = "show-all-books")
    public String showAllBooks() {
        return entityPrinterService.preparePrintBooks(bookService.getAllBooks());
    }

    @ShellMethod(value = "Show all authors in library", key = "show-all-authors")
    public String showAllAuthors() {
        return entityPrinterService.preparePrintAuthors(authorService.getAllAuthors());
    }

    @ShellMethod(value = "Show all genres in library", key = "show-all-genres")
    public String showAllGenres() {
        return entityPrinterService.preparePrintGenres(genreService.getAllGenres());
    }

    @ShellMethod(value = "Show all comments for book", key = "show-all-comments")
    public String showAllComments(@ShellOption long book_id) {
        return entityPrinterService.preparePrintComments(commentService.getAllCommentsByBookId(book_id));
    }

    @ShellMethod(value = "Show all info for book", key = "show-book")
    public String showBook(@ShellOption long book_id) {
        return entityPrinterService.preparePrintBook(bookService.getBookById(book_id));
    }

    @ShellMethod(value = "Show all info for author", key = "show-author")
    public String showAuthor(@ShellOption long author_id) {
        return entityPrinterService.preparePrintAuthorWithBook(bookService.getAllBookByAuthorId(author_id));
    }

    @ShellMethod(value = "Show all info for genre", key = "show-genre")
    public String showGenre(@ShellOption long genre_id) {
        return entityPrinterService.preparePrintGenreWithBook(bookService.getAllBookByGenreId(genre_id));
    }

    @ShellMethod(value = "Add new book in library", key = "add-book")
    public void addBook(@ShellOption String book, @ShellOption long author_id, @ShellOption long genre_id) {
        bookService.addBook(book, author_id, genre_id);
    }

    @ShellMethod(value = "Add new author in library", key = "add-author")
    public void addAuthor(@ShellOption String author) {
        authorService.addAuthor(author);
    }

    @ShellMethod(value = "Add new genre in library", key = "add-genre")
    public void addGenre(@ShellOption String genre) {
        genreService.addGenre(genre);
    }

    @ShellMethod(value = "Add new comment for book", key = "add-comment")
    public void addComment(@ShellOption String comment, long book_id) {
        commentService.addComment(comment, book_id);
    }

    @ShellMethod(value = "Delete book in library", key = "delete-book")
    public void deleteBook(@ShellOption long book_id) {
        bookService.deleteBook(book_id);
    }

    @ShellMethod(value = "Delete author in library", key = "delete-author")
    public void deleteAuthor(@ShellOption long author_id) {
        authorService.deleteAuthor(author_id);
    }

    @ShellMethod(value = "Delete genre in library", key = "delete-genre")
    public void deleteGenre(@ShellOption long genre_id) {
        genreService.deleteGenre(genre_id);
    }

    @ShellMethod(value = "Delete comment in book", key = "delete-comment")
    public void deleteComment(@ShellOption long comment_id) {
        commentService.deleteById(comment_id);
    }
}
