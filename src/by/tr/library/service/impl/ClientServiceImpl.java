package by.tr.library.service.impl;

import by.tr.library.bean.User;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.ClientService;
import by.tr.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService{

	@Override
	public User authorization (String login, String password) throws ServiceException  {
		User user = null;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao fileCommonDao = factory.getSqlCommonDao();

		try {
			user = fileCommonDao.authorization(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Authorization service exception", e);
		}
		
		return user;
	}

	@Override
	public User registration(String login, String password) throws ServiceException {
		User user = null;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao fileCommonDao = factory.getSqlCommonDao();

		try {
			user = fileCommonDao.registration(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Registration service exception", e);
		}

		return user;
	}


	@Override
	public boolean deleteUserByLogin(String login) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getSqlAdminDao();

		try {
			result = adminDao.deleteUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("Delete user service exception", e);
		}

		return result;
	}

	@Override
	public boolean blockUserByLogin(String login) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getSqlAdminDao();

		try {
			result = adminDao.blockUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("Block user service exception", e);
		}

		return result;
	}

	@Override
	public boolean changePassword(String login, String password) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getSqlCommonDao();

		try {
			result = commonDao.changePassword(login, password);
		} catch (DAOException e) {
			throw new ServiceException("Change password service exception", e);
		}

		return result;
	}

	@Override
	public boolean unblockUserByLogin(String login) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getSqlAdminDao();

		try {
			result = adminDao.unblockUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("Unblock user service exception", e);
		}

		return result;
	}

}
