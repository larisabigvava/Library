package by.tr.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

public class SQLUserDao implements UserDao{
	private static final String COLUMN_NAME_TITLE = "title";
	private static final String COLUMN_NAME_AUTHOR = "author";
	private static final String COLUMN_NAME_PRICE = "price";
	private static final String COLUMN_NAME_LEVEL = "level";
	private static final String COLUMN_NAME_LANGUAGE = "language";
	private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM `books` WHERE `title`= ?";
	private static final String SELECT_PROGRAMMER_BOOK_BY_TITLE = "SELECT * FROM `programmer_books` WHERE `title`= ?";
	private static final String SELECT_BOOK_BY_AUTHOR = "SELECT * FROM `books` WHERE `author`= ?";
	private static final String SELECT_PROGRAMMER_BOOK_BY_AUTHOR = "SELECT * FROM `programmer_books` WHERE `title`= ?";


	@Override
	public Catalog getBooksByTitle(String title) throws DAOException {
		Catalog catalog = null;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement booksStatement = connection.prepareStatement(SELECT_BOOK_BY_TITLE);
				PreparedStatement programmerBooksStatement = connection.prepareStatement(SELECT_PROGRAMMER_BOOK_BY_TITLE)
		){
			booksStatement.setString(1, title);
			programmerBooksStatement.setString(1, title);
			ResultSet setOfBooks = booksStatement.executeQuery();
			ResultSet setOfProgrammerBooks = programmerBooksStatement.executeQuery();
			while (setOfBooks.next()){
				Book book = new Book();
				book.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
				book.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
				book.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
				catalog.addBook(book);
			}
			while (setOfProgrammerBooks.next()){
				ProgrammerBook programmerBook = new ProgrammerBook();
				programmerBook.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
				programmerBook.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
				programmerBook.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
				programmerBook.setLanguage(setOfBooks.getString(COLUMN_NAME_LANGUAGE));
				programmerBook.setLevel(setOfBooks.getString(COLUMN_NAME_LEVEL));
				catalog.addProgrammerBook(programmerBook);
			}
		} catch (SQLException e) {
			throw new DAOException("Get books by title dao exception", e);
		}
		return catalog;
	}

	@Override
	public Catalog getBooksByAuthor(String author) throws DAOException {
		Catalog catalog = null;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement booksStatement = connection.prepareStatement(SELECT_BOOK_BY_AUTHOR);
				PreparedStatement programmerBooksStatement = connection.prepareStatement(SELECT_PROGRAMMER_BOOK_BY_AUTHOR)
		){
			booksStatement.setString(1, author);
			programmerBooksStatement.setString(1, author);
			ResultSet setOfBooks = booksStatement.executeQuery();
			ResultSet setOfProgrammerBooks = programmerBooksStatement.executeQuery();
			while (setOfBooks.next()){
				Book book = new Book();
				book.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
				book.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
				book.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
				catalog.addBook(book);
			}
			while (setOfProgrammerBooks.next()){
				ProgrammerBook programmerBook = new ProgrammerBook();
				programmerBook.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
				programmerBook.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
				programmerBook.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
				programmerBook.setLanguage(setOfBooks.getString(COLUMN_NAME_LANGUAGE));
				programmerBook.setLevel(setOfBooks.getString(COLUMN_NAME_LEVEL));
				catalog.addProgrammerBook(programmerBook);
			}
		} catch (SQLException e) {
			throw new DAOException("Get books by title dao exception", e);
		}
		return catalog;
	}
}

