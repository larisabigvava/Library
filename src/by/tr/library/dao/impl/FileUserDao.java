package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

import java.util.List;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileUserDao implements UserDao {
    @Override
    public List<Book> getCatalog() throws DAOException {
        //TODO
        return null;
    }

    @Override
    public Book getBookByTitle(String title) throws DAOException {
        //TODO
        return null;
    }

    @Override
    public Book getBooksByAuthor(String title) throws DAOException {
        //TODO
        return null;
    }
}
