package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileUserDao implements UserDao {

    private final static FileUserDao instance = new FileUserDao();

    private final static String BOOKS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\books.txt";
    private final static String USERS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\users.txt";

    public static FileUserDao getInstance() {
        return instance;
    }

    private FileUserDao(){}

    @Override
    public Book getBookByTitle(String title) throws DAOException {
        //TODO
        return null;
    }

    @Override
    public Catalog getBooksByAuthor(String author) throws DAOException {
        //TODO
        return null;
    }

    private Catalog readFile() throws DAOException {
//        Catalog catalog = new Catalog();
//        try (FileInputStream fis = new FileInputStream(BOOKS_FILE)) {
//            Scanner scanner = new Scanner(fis);
//            while (scanner.hasNextLine()) {
//                Book book = new Book();;
//                catalog.set(scanner.nextLine());
//            }
//        } catch (IOException ex) {
//            throw new DAOException(ex.getMessage(), ex);
//        }
//        return books;
        return null;
    }
}
