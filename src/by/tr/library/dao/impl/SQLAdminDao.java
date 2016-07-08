package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.exception.DAOException;

public class SQLAdminDao implements AdminDao {

	@Override
	public boolean blockUserById(int idUser) throws DAOException {
		return false;
	}

	@Override
	public boolean addNewBook(Book book) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBookByTitle(String title) throws DAOException {
		return false;
	}

	@Override
	public boolean deleteUserById(int id) throws DAOException {
		return false;
	}

	@Override
	public boolean deleteUserByLogin(String login) throws DAOException {
		return false;
	}

}
