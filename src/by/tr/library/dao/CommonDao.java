package by.tr.library.dao;

import by.tr.library.bean.Catalog;
import by.tr.library.dao.exception.DAOException;

public interface CommonDao {
	boolean authorization(String login, String password) throws DAOException;
	boolean registration(String login, String password) throws DAOException;
	boolean changePassword(String login, String password) throws DAOException;
	Catalog getCatalog() throws DAOException;

}
