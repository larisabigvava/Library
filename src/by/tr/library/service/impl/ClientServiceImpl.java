package by.tr.library.service.impl;

import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.ClientService;
import by.tr.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService{

	@Override
	public boolean authorization (String login, String password) throws ServiceException  {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao fileCommonDao = factory.getFileCommonDao();

		try {
			result = fileCommonDao.authorization(login, password);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

	@Override
	public boolean registration(String login, String password) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao fileCommonDao = factory.getFileCommonDao();

		try {
			result = fileCommonDao.registration(login, password);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean deleteUserById(int id) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();

		try {
			result = adminDao.deleteUserById(id);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean deleteUserByLogin(String login) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();

		try {
			result = adminDao.deleteUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean blockUserById(int id) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();

		try {
			result = adminDao.blockUserById(id);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

	@Override
	public boolean changePassword(String password) throws ServiceException {
		boolean result;

		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getFileCommonDao();

		try {
			result = commonDao.changePassword(password);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}

		return result;
	}

}
