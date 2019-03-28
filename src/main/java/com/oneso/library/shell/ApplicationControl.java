package com.oneso.library.shell;

import com.oneso.library.services.LibraryService;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationControl {

    private final LibraryService libraryService;

    private boolean avialable = true;

    public ApplicationControl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod("Show all library")
    public String showLibrary() {
        return libraryService.showAll();
    }

    @ShellMethod("add new Book, Autor, Genre")
    public void addBook(@ShellOption String book, @ShellOption String autor, @ShellOption String genre) {
        libraryService.createBook(book, autor, genre);
    }

    @ShellMethod("delete book")
    @ShellMethodAvailability("showBooks")
    public void delete(@ShellOption String book) {
        libraryService.deleteBook(book);
    }

    @ShellMethod("show all books")
    public Availability showBooks() {
        System.out.println(libraryService.showAllBook());
        return avialable ? Availability.available() : Availability.unavailable("not books");
    }
}
