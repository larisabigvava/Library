package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAdminDao implements AdminDao {

	private static final String UPDATE_USER_BLOCKED = "UPDATE `users` SET `blocked`=`0` WHERE `login`=?";
	private static final String UPDATE_USER_UNBLOCKED = "UPDATE `users` SET `blocked`=`1` WHERE `login`=?";
	private static final String DELETE_USER_BY_LOGIN = "DELETE FROM `users` WHERE `login`=?";
	private static final String INSERT_BOOK = "INSERT INTO `books`(`title`,`author`,`price`) VALUES(?,?,?)";
	private static final String INSERT_PROGRAMMER_BOOK = "INSERT INTO `programmer_books` (`title`,`author`,`price`," +
			"`language`,`level`) VALUES(?,?,?,?,?)";
	private static final String DELETE_BOOK_BY_TITLE = "DELETE FROM `books` WHERE `title`=?";
	private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM `books` WHERE `title`=?";
	private static final String SELECT_PROGRAMMER_BOOK_BY_TITLE = "SELECT * FROM `programmer_books` WHERE `title`=?";
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
					PreparedStatement statement = connection.prepareStatement(INSERT_BOOK)
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
		boolean result = false;
		if (selectBookByTitle(title)){
			try (
					Connection booksConnection = ConnectionPool.getInstance().getConnection();
					PreparedStatement deleteBooksStatement = booksConnection.prepareStatement(DELETE_BOOK_BY_TITLE)
					){
				deleteBooksStatement.setString(1, title);
				if (deleteBooksStatement.executeUpdate() != 0){
					result = true;
				}

			} catch (SQLException e) {
				throw new DAOException("Deleting book by title dao exception", e);
			}
		} else if (selectProgrammerBooksByTitle(title)){
			try (
					Connection programmerBooksConnection = ConnectionPool.getInstance().getConnection();
					PreparedStatement deleteProgrammerBooksStatement = programmerBooksConnection.prepareStatement(DELETE_PROGRAMMER_BOOK_BY_TITLE)
			){
				deleteProgrammerBooksStatement.setString(1, title);
				if (deleteProgrammerBooksStatement.executeUpdate() != 0){
					result = true;
				}

			} catch (SQLException e) {
				throw new DAOException("Deleting programmer book by title dao exception", e);
			}
		}
		return result;
	}

	private boolean selectBookByTitle(String title) throws DAOException {
		boolean result = false;
		try (
				Connection booksConnection = ConnectionPool.getInstance().getConnection();
				PreparedStatement selectBooksStatement = booksConnection.prepareStatement(SELECT_BOOK_BY_TITLE)
		){
			selectBooksStatement.setString(1, title);
			ResultSet setOfBooks = selectBooksStatement.executeQuery();
			if (setOfBooks.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Selecting by title dao exception", e);
		}
		return result;
	}

	private boolean selectProgrammerBooksByTitle(String title) throws DAOException {
		boolean result = false;
		try (
				Connection programmerBooksConnection = ConnectionPool.getInstance().getConnection();
				PreparedStatement selectProgrammerBooksStatement = programmerBooksConnection.prepareStatement(SELECT_PROGRAMMER_BOOK_BY_TITLE)
		) {
			selectProgrammerBooksStatement.setString(1, title);
			ResultSet setOfProgrammerBooks = selectProgrammerBooksStatement.executeQuery();
			if (setOfProgrammerBooks.next()) {
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Selecting programmer books by title dao exception", e);
		}
		return result;
	}

	@Override
	public boolean deleteUserByLogin(String login) throws DAOException {
		boolean result = false;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_LOGIN)
				){
			statement.setString(1, login);
			if (statement.executeUpdate() == 1){
				result = true;
			}
		} catch (SQLException e) {
			throw new DAOException("Deleting user by login exception", e);
		}

		return result;
	}
}
