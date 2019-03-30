package com.oneso.library.shell;

import com.oneso.library.services.AuthorService;
import com.oneso.library.services.BookService;
import com.oneso.library.services.GenreService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationControl {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    private boolean avialable = true;

    public ApplicationControl(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod("Show all in library")
    public void showLibrary() {
        System.out.println("Authors:");
        authorService.showAllAuthors();
        System.out.println("Books:");
        bookService.showAllBook();
        System.out.println("Genre:");
        genreService.showAllGenre();
    }

    @ShellMethod("Create new book")
    public void addNewBook(@ShellOption String nameBook, @ShellOption String nameAuthor, @ShellOption String nameGenre) {
        bookService.addBook(nameBook, authorService.getAuthor(nameAuthor), genreService.getGenre(nameGenre));
    }

    @ShellMethod("Create new author")
    public void addNewAuthor(@ShellOption String name) {
        authorService.addAuthor(name);
    }

    @ShellMethod("Create new genre")
    public void addNewGenre(@ShellOption String name) {
        genreService.addGenre(name);
    }

    @ShellMethod("Deleted book")
    @ShellMethodAvailability("showBooks")
    public void deleteBook(@ShellOption long id) {
        bookService.deleteBook(id);
    }

    @ShellMethod("Show all book in library")
    public Availability showBooks() {
        bookService.showAllBook();
        return avialable ? Availability.available() : Availability.unavailable("not ok");
    }

    @ShellMethod("Deleted author")
    @ShellMethodAvailability("showAuthors")
    public void deleteAuthor(@ShellOption long id) {
        authorService.deleteAuthor(id);
    }

    @ShellMethod("Show all author in library")
    public Availability showAuthors() {
        authorService.showAllAuthors();
        return avialable ? Availability.available() : Availability.unavailable("not ok");
    }

    @ShellMethod("Show all information to author")
    public void showInfoAuthor(@ShellOption long id) {
        bookService.showAllBookByAuthor(id);
    }
}
