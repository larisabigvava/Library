package by.tr.library.dao.impl;

import java.io.IOException;

import by.tr.library.bean.Catalog;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

public class SQLCommonDao implements CommonDao {
	@Override
	public boolean authorization(String login, String password) throws DAOException {
		return false;
	}

	@Override
	public boolean registration(String login, String password) throws DAOException {
		return false;
	}

	@Override
	public boolean changePassword(String password) throws DAOException {
		return false;
	}

	@Override
	public Catalog getCatalog() throws DAOException {
		return null;
	}

//	@Override
//	public boolean authorization(String login, String password) throws DAOException {
//
//		try {
//			int x = 0;
//			if (x > 0) {
//				throw new IOException("IO message");
//			}
//		} catch (IOException e) {
//			throw new DAOException("my message", e);
//		}
//		// STUB
//		return true;
//	}
//
//	@Override
//	public boolean registration(String login, String password) throws DAOException {
//		return false;
//	}

}
