package by.tr.library.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

public class SQLUserDao implements UserDao{

	@Override
	public Catalog getBooksByTitle(String title) throws DAOException {
		return null;
	}

	@Override
	public Catalog getBooksByAuthor(String author) throws DAOException {
		return null;
	}
}

