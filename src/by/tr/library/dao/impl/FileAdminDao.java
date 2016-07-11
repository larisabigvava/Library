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

    private final static String fileNameBooks = System.getProperty("java.class.path") + "/books.txt";
    private final static String fileNameUsers = System.getProperty("java.class.path") + "/users.txt";

    public static FileAdminDao getInstance() {
        return instance;
    }

    @Override
    public boolean blockUserById(int idUser) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean addNewBook(String book) throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(fileNameBooks, true)) {
            PrintWriter writer = new PrintWriter(fos);
            writer.println(book);
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("add new book dao exception", ex);
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


}
