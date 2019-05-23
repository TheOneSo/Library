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
    public String showAllComments(@ShellOption String id) {
        return entityPrinterService.preparePrintComments(commentService.getAllCommentsByBookId(id));
    }

    @ShellMethod(value = "Show all info for book", key = "show-book")
    public String showBook(@ShellOption String id) {
        return entityPrinterService.preparePrintBook(bookService.getBook(id));
    }

    @ShellMethod(value = "Show all info for author", key = "show-author")
    public String showAuthor(@ShellOption String id) {
        return entityPrinterService.preparePrintAuthorWithBook(bookService.getAllBookByAuthorId(id));
    }

    @ShellMethod(value = "Show all info for genre", key = "show-genre")
    public String showGenre(@ShellOption String id) {
        return entityPrinterService.preparePrintGenreWithBook(bookService.getAllBookByGenreId(id));
    }

    @ShellMethod(value = "Add new book in library", key = "add-book")
    public void addBook(@ShellOption String bookName, @ShellOption String authorName, @ShellOption String genreName) {
        bookService.addBook(bookName, authorName, genreName);
    }

    @ShellMethod(value = "Add new author in library", key = "add-author")
    public void addAuthor(@ShellOption String authorName) {
        authorService.addAuthor(authorName);
    }

    @ShellMethod(value = "Add new genre in library", key = "add-genre")
    public void addGenre(@ShellOption String genreName) {
        genreService.addGenre(genreName);
    }

    @ShellMethod(value = "Add new comment for book", key = "add-comment")
    public void addComment(@ShellOption String comment, String bookId) {
        commentService.addComment(comment, bookId);
    }

    @ShellMethod(value = "Delete book in library", key = "delete-book")
    public void deleteBook(@ShellOption String id) {
        bookService.deleteBook(id);
    }

    @ShellMethod(value = "Delete author in library", key = "delete-author")
    public void deleteAuthor(@ShellOption String id) {
        authorService.deleteAuthor(id);
    }

    @ShellMethod(value = "Delete genre in library", key = "delete-genre")
    public void deleteGenre(@ShellOption String id) {
        genreService.deleteGenre(id);
    }

    @ShellMethod(value = "Delete comment in book", key = "delete-comment")
    public void deleteComment(@ShellOption String id) {
        commentService.deleteById(id);
    }
}
