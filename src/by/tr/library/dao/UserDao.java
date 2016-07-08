package by.tr.library.dao;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface UserDao {
	List<Book> getCatalog() throws DAOException;
	Book getBookByTitle(String title) throws DAOException;
	Book getBooksByAuthor(String title) throws DAOException;
}
