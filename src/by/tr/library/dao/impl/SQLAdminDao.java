package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLAdminDao implements AdminDao {
	private static final String COLUMN_NAME_LOGIN = "login";
	private static final String COLUMN_NAME_PASSWORD = "password";

	private static final String COLUMN_NAME_TITLE = "title";
	private static final String COLUMN_NAME_AUTHOR = "author";
	private static final String COLUMN_NAME_PRICE = "price";
	private static final String COLUMN_NAME_LEVEL = "level";
	private static final String COLUMN_NAME_LANGUAGE = "language";

	private static final String UPDATE_USER_BLOCKED = "UPDATE `users` SET `blocked`=`0` WHERE `login`=?";
	private static final String UPDATE_USER_UNBLOCKED = "UPDATE `users` SET `blocked`=`1` WHERE `login`=?";
	private static final String DELETE_USER_BY_LOGIN = "DELETE FROM `users` WHERE `login`=?";

	private static final String INSERT_BOOK = "INSERT INTO `books`(`title`,`author`,`price`) VALUES(?,?,?)";
	private static final String INSERT_PROGRAMMER_BOOK = "INSERT INTO `programmer_books` (`title`,`author`,`price`," +
			"`language`,`level`) VALUES(?,?,?,?,?)";
	private static final String DELETE_BOOK_BY_TITLE = "DELETE FROM `books` WHERE `title`=?";
	private static final String DELETE_PROGRAMMER_BOOK_BY_TITLE = "DELETE FROM `programmer_books` WHERE `title`=?";

	@Override
	public boolean blockUserByLogin(String login) throws DAOException {
		boolean result = false;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_BLOCKED)
		){
			statement.setString(1, login);
			if (statement.executeUpdate() == 1){
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Block user by login dao exception", e);
		}
		return result;
	}

	@Override
	public boolean unblockUserByLogin(String login) throws DAOException {
		boolean result = false;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_UNBLOCKED)
		){
			statement.setString(1, login);
			if (statement.executeUpdate() == 1){
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Unblock user by login dao exception", e);
		}
		return result;
	}

	@Override
	public boolean addNewBook(Book book) throws DAOException {
		boolean result = false;
		if (book.getClass() == ProgrammerBook.class) {
			ProgrammerBook programmerBook = (ProgrammerBook) book;
			try (
					Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT_PROGRAMMER_BOOK)
			) {
				statement.setString(1, programmerBook.getTitle());
				statement.setString(2, programmerBook.getAuthor());
				statement.setInt(3, programmerBook.getPrice());
				statement.setString(4, programmerBook.getLanguage());
				statement.setString(5, programmerBook.getLevel());
				if (statement.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				throw new DAOException("Add new programmer book dao exception", e);
			}
		} else {
			try (
					Connection connection = ConnectionPool.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement(INSERT_PROGRAMMER_BOOK)
			) {
				statement.setString(1, book.getTitle());
				statement.setString(2, book.getAuthor());
				statement.setInt(3, book.getPrice());
				if (statement.executeUpdate() == 1) {
					result = true;
				}
			} catch (SQLException e) {
				throw new DAOException("Add new book dao exception", e);
			}
		}
		return result;
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
