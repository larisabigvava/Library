package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileAdminDao implements AdminDao {

    @Override
    public boolean blockUserById(int idUser) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean addNewBook(Book book) throws DAOException {
        //TODO
        return false;
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
