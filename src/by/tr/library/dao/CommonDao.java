package by.tr.library.dao;

import by.tr.library.bean.Catalog;
import by.tr.library.bean.User;
import by.tr.library.dao.exception.DAOException;

import java.util.List;

public interface CommonDao {
	User authorization(String login, String password) throws DAOException;
	User registration(String login, String password) throws DAOException;
	boolean changePassword(String login, String password) throws DAOException;
	Catalog getCatalog() throws DAOException;
	public List<String> readUsersFile() throws DAOException;

}
