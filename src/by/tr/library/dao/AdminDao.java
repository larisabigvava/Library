package by.tr.library.dao;

import by.tr.library.bean.Book;
import by.tr.library.dao.exception.DAOException;

public interface AdminDao {
	boolean blockUserByLogin(String login)throws DAOException;
	boolean unblockUserByLogin(String login)throws DAOException;
	boolean addNewBook(Book book)throws DAOException;
	boolean deleteBookByTitle(String title)throws DAOException;
	boolean deleteUserByLogin(String login)throws DAOException;
}
