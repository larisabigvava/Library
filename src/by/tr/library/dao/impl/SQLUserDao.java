package by.tr.library.dao.impl;

import java.util.ArrayList;
import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;

public class SQLUserDao implements UserDao{

	@Override
	public List<Book> getCatalog() {
		// TODO Auto-generated method stub
		return new ArrayList<Book>();
	}

	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBooksByAuthor(String title) throws DAOException {
		return null;
	}

}
