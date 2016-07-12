package by.tr.library.dao.impl;

import java.io.IOException;
import java.util.List;

import by.tr.library.bean.Catalog;
import by.tr.library.bean.User;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

public class SQLCommonDao implements CommonDao {


	@Override
	public User authorization(String login, String password) throws DAOException {
		return null;
	}

	@Override
	public User registration(String login, String password) throws DAOException {
		return null;
	}

	@Override
	public boolean changePassword(String login, String password) throws DAOException {
		return false;
	}

	@Override
	public Catalog getCatalog() throws DAOException {
		return null;
	}

	@Override
	public List<String> readUsersFile() throws DAOException {
		return null;
	}
}
