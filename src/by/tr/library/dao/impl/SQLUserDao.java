package by.tr.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

public class SQLUserDao implements UserDao{
	private static final String SELECT_BOOK_BY_TITLE = "SELECT * FROM `books` WHERE `title`=?";
	private static final String SELECT_PROGRAMMER_BOOK_BY_TITLE = "SELECT * FROM `programmer_books` WHERE `title`=?";
	private static final String SELECT_BOOK_BY_AUTHOR = "SELECT * FROM `books` WHERE `author`=?";
	private static final String SELECT_PROGRAMMER_BOOK_BY_AUTHOR = "SELECT * FROM `programmer_books` WHERE `title`=?";


	@Override
	public Catalog getBooksByTitle(String title) throws DAOException {
		Catalog catalog = null;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement booksStatement = connection.prepareStatement(SELECT_BOOK_BY_TITLE);
				PreparedStatement programmerBooksStatement = connection.prepareStatement(SELECT_PROGRAMMER_BOOK_BY_TITLE)
				){
			ResultSet setOfBooks = booksStatement.executeQuery();
			ResultSet setOfProgrammerBooks = programmerBooksStatement.executeQuery();
			if (setOfBooks.next() || setOfProgrammerBooks.next()){
				catalog = new Catalog();
				while (setOfBooks.next()){
					Book book = new Book();
					book.setTitle(setOfBooks.getString(1));
					book.setAuthor(setOfBooks.getString(2));
					book.setPrice(setOfBooks.getInt(3));
					catalog.addBook(book);
				}
				while (setOfProgrammerBooks.next()){
					ProgrammerBook programmerBook = new ProgrammerBook();
					programmerBook.setTitle(setOfBooks.getString(1));
					programmerBook.setAuthor(setOfBooks.getString(2));
					programmerBook.setPrice(setOfBooks.getInt(3));
					programmerBook.setLanguage(setOfBooks.getString(4));
					programmerBook.setLevel(setOfBooks.getString(5));
					catalog.addProgrammerBook(programmerBook);
				}
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
			ResultSet setOfBooks = booksStatement.executeQuery();
			ResultSet setOfProgrammerBooks = programmerBooksStatement.executeQuery();
			if (setOfBooks.next() || setOfProgrammerBooks.next()){
				catalog = new Catalog();
				while (setOfBooks.next()){
					Book book = new Book();
					book.setTitle(setOfBooks.getString(1));
					book.setAuthor(setOfBooks.getString(2));
					book.setPrice(setOfBooks.getInt(3));
					catalog.addBook(book);
				}
				while (setOfProgrammerBooks.next()){
					ProgrammerBook programmerBook = new ProgrammerBook();
					programmerBook.setTitle(setOfBooks.getString(1));
					programmerBook.setAuthor(setOfBooks.getString(2));
					programmerBook.setPrice(setOfBooks.getInt(3));
					programmerBook.setLanguage(setOfBooks.getString(4));
					programmerBook.setLevel(setOfBooks.getString(5));
					catalog.addProgrammerBook(programmerBook);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Get books by title dao exception", e);
		}
		return catalog;
	}
}

