package by.tr.library.dao.impl;

import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileCommonDao implements CommonDao {

    @Override
    public boolean authorization(String login, String password) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean registration(String login, String password) throws DAOException {
        //TODO
        return false;
    }

    @Override
    public boolean changePassword(String password) throws DAOException {
        return false;
    }
}
