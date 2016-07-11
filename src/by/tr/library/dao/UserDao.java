package by.tr.library.dao;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.dao.exception.DAOException;

public interface UserDao {
	Book getBookByTitle(String title) throws DAOException;
	Catalog getBooksByAuthor(String title) throws DAOException;
}
