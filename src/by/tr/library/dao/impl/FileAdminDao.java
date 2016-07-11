package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileAdminDao implements AdminDao {

    private final static FileAdminDao instance = new FileAdminDao();

    private final static String BOOKS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\books.txt";
    private final static String USERS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\users.txt";

    public static FileAdminDao getInstance() {
        return instance;
    }

    private FileAdminDao(){}

    @Override
    public boolean blockUserById(int idUser) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean addNewBook(Book book) throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(BOOKS_FILE, true)) {
            PrintWriter writer = new PrintWriter(fos);
            writer.println(prepare(book));
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("Add new book dao exception", ex);
        }
        return true;
    }

    @Override
    public boolean deleteBookByTitle(String title) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        Catalog catalog = daoFactory.getFileCommonDao().getCatalog();
        FileOutputStream fos = null;
        boolean result = false;
        Iterator<Book> iterator = catalog.getBooks().iterator();
        Iterator<ProgrammerBook> programmerBookIterator = catalog.getProgrammerBooks().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if (book.getTitle().equals(title)){
                iterator.remove();
                result = true;
            }
        }
        while (programmerBookIterator.hasNext()){
            ProgrammerBook programmerBook = programmerBookIterator.next();
            if (programmerBook.getTitle().equals(title)){
                programmerBookIterator.remove();
                result = true;
            }
        }
        if (result) {
            try {
                Files.delete(Paths.get(BOOKS_FILE));
                Files.createFile(Paths.get(BOOKS_FILE));
                fos = new FileOutputStream(BOOKS_FILE);
                PrintWriter writer = new PrintWriter(fos);
                for (Book book : catalog.getBooks()) {
                    writer.println(prepare(book));
                }
                for (ProgrammerBook programmerBook : catalog.getProgrammerBooks()) {
                    writer.println(prepare(programmerBook));
                }
                writer.flush();
            } catch (IOException ex) {
                throw new DAOException("Delete book dao exception", ex);
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException ex) {
                    throw new DAOException(ex.getMessage(), ex);
                }
            }
        }
        return result;
    }

    private String prepare(Book book) {
        StringBuilder record = new StringBuilder(book.getTitle());
        record.append("::")
                .append(book.getAuthor())
                .append("::")
                .append(book.getPrice());
        if (book.getClass() == ProgrammerBook.class) {
            ProgrammerBook programmerBook = (ProgrammerBook) book;
            record.append("::")
                    .append(programmerBook.getLevel())
                    .append("::")
                    .append(programmerBook.getLanguage());
        }
        return record.toString();
    }

    @Override
    public boolean deleteUserById(int id) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean deleteUserByLogin(String login) throws DAOException {
        //TODO
        return false;
    }


}
