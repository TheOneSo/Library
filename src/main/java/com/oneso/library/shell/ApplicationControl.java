package com.oneso.library.shell;

import com.oneso.library.domain.Autor;
import com.oneso.library.services.AutorService;
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
    private final AutorService autorService;
    private final GenreService genreService;

    private boolean avialable = true;

    public ApplicationControl(BookService bookService, AutorService autorService, GenreService genreService) {
        this.bookService = bookService;
        this.autorService = autorService;
        this.genreService = genreService;
    }

    @ShellMethod("Show all in library")
    public void showLibrary() {
        System.out.println("Autors:");
        autorService.showAllAutors();
        System.out.println("Books:");
        bookService.showAllBook();
        System.out.println("Genre:");
        genreService.showAllGenre();
    }

    @ShellMethod("Create new book")
    public void addNewBook(@ShellOption String nameBook, @ShellOption String nameAutor, @ShellOption String nameGenre) {
        bookService.addBook(nameBook, autorService.getAutor(nameAutor), genreService.getGenre(nameGenre));
    }

    @ShellMethod("Create new autor")
    public void addNewAutor(@ShellOption String name) {
        autorService.addAutor(name);
    }

    @ShellMethod("Create new genre")
    public void addNewGenre(@ShellOption String name) {
        genreService.addGenre(name);
    }

    @ShellMethod("Deleted book")
    @ShellMethodAvailability("showBooks")
    public void deleteBook(@ShellOption String name) {
        bookService.deleteBook(name);
    }

    @ShellMethod("Show all book in library")
    public Availability showBooks() {
        bookService.showAllBook();
        return avialable ? Availability.available() : Availability.unavailable("not ok");
    }

    @ShellMethod("Deleted autor")
    @ShellMethodAvailability("showAutors")
    public void deleteAutor(@ShellOption String name) {
        autorService.deleteAutor(name);
    }

    @ShellMethod("Show all autor in library")
    public Availability showAutors() {
        autorService.showAllAutors();
        return avialable ? Availability.available() : Availability.unavailable("not ok");
    }

    @ShellMethod("Show all information to autor")
    public void showInfoAutor(@ShellOption String name) {
        autorService.showAllInfoAutor(name);
    }
}
