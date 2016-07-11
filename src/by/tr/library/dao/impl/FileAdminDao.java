package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileAdminDao implements AdminDao {

    private final static FileAdminDao instance = new FileAdminDao();

    private final static String fileName = System.getProperty("java.class.path") + "/books.txt";

    public static FileAdminDao getInstance() {
        return instance;
    }

    @Override
    public boolean blockUserById(int idUser) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean addNewBook(Book book) throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            PrintWriter writer = new PrintWriter(fos);
            writer.println(prepare(book));
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return true;
    }

    @Override
    public boolean deleteBookByTitle(String title) throws DAOException {
        //TODO
        return false;
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
}
