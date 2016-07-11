package by.tr.library.bean;

import java.util.ArrayList;

/**
 * Created by Seagull on 11.07.2016.
 */
public class Catalog {
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<ProgrammerBook> programmerBooks = new ArrayList<ProgrammerBook>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<ProgrammerBook> getProgrammerBooks() {
        return programmerBooks;
    }

    public void setProgrammerBooks(ArrayList<ProgrammerBook> programmerBooks) {
        this.programmerBooks = programmerBooks;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void addProgrammerBook(ProgrammerBook programmerBook){
        programmerBooks.add(programmerBook);
    }
}
