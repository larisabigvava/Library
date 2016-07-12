package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileUserDao implements UserDao {

    private final static FileUserDao instance = new FileUserDao();
    public static FileUserDao getInstance() {
        return instance;
    }

    private FileUserDao(){}

    @Override
    public Catalog getBooksByTitle(String title) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        Catalog catalog = daoFactory.getFileCommonDao().getCatalog();
        Iterator<Book> iterator = catalog.getBooks().iterator();
        Iterator<ProgrammerBook> programmerBookIterator = catalog.getProgrammerBooks().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if (book.getTitle().equals(title)){
                catalog.addBook(book);
            }
        }
        while (programmerBookIterator.hasNext()){
            ProgrammerBook programmerBook = programmerBookIterator.next();
            if (programmerBook.getTitle().equals(title)){
                catalog.addProgrammerBook(programmerBook);
            }
        }
        return catalog;
    }

    @Override
    public Catalog getBooksByAuthor(String author) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        Catalog catalog = daoFactory.getFileCommonDao().getCatalog();
        Iterator<Book> iterator = catalog.getBooks().iterator();
        Iterator<ProgrammerBook> programmerBookIterator = catalog.getProgrammerBooks().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if (book.getAuthor().equals(author)){
                catalog.addBook(book);
            }
        }
        while (programmerBookIterator.hasNext()){
            ProgrammerBook programmerBook = programmerBookIterator.next();
            if (programmerBook.getAuthor().equals(author)){
                catalog.addProgrammerBook(programmerBook);
            }
        }
        return catalog;
    }
}
