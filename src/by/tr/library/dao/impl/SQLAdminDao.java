package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

public class SQLAdminDao implements AdminDao {
	private static final String COLUMN_NAME_LOGIN = "login";
	private static final String COLUMN_NAME_PASSWORD = "password";

	private static final String COLUMN_NAME_TITLE = "title";
	private static final String COLUMN_NAME_AUTHOR = "author";
	private static final String COLUMN_NAME_PRICE = "price";
	private static final String COLUMN_NAME_LEVEL = "level";
	private static final String COLUMN_NAME_LANGUAGE = "language";

	private static final String UPDATE_USER_BLOCKED = "UPDATE `users` SET `blocked`=? WHERE `login`=?";
	private static final String UPDATE_USER_UNBLOCKED = "UPDATE `users` SET `blocked`=? WHERE `login`=?";
	private static final String DELETE_USER_BY_LOGIN = "DELETE FROM `users` WHERE `login`=?";

	private static final String INSERT_BOOK = "INSERT INTO `books`(`title`,`author`,`price`) VALUES(?,?,?)";
	private static final String INSERT_PROGRAMMER_BOOK = "INSERT INTO books(`title`,`author`,`price`," +
			"`language`,`level`) VALUES(?,?,?,?,?)";
	private static final String DELETE_BOOK_BY_TITLE = "DELETE FROM `books` WHERE `title`=?";
	private static final String DELETE_PROGRAMMER_BOOK_BY_TITLE = "DELETE FROM `programmer_books` WHERE `title`=?";

	@Override
	public boolean blockUserByLogin(String login) throws DAOException {
		return false;
	}

	@Override
	public boolean unblockUserByLogin(String login) throws DAOException {
		return false;
	}

	@Override
	public boolean addNewBook(Book book) throws DAOException {
		return false;
	}

	@Override
	public boolean deleteBookByTitle(String title) throws DAOException {
		return false;
	}

	@Override
	public boolean deleteUserByLogin(String login) throws DAOException {
		return false;
	}
}
