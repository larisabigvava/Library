package by.tr.library.dao;

import by.tr.library.dao.impl.*;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	public static DAOFactory getInstance(){
		return factory;
	}

	private DAOFactory(){}

	private final CommonDao sqlCommonDao = new SQLCommonDao();
	private final UserDao sqlUserDao = new SQLUserDao();
	private final AdminDao sqlAdminDao = new SQLAdminDao();
	private final CommonDao fileCommonDao = new FileCommonDao();
	private final UserDao fileUserDao = new FileUserDao();
	private final AdminDao fileAdminDao = new FileAdminDao();

	public CommonDao getSqlCommonDao() {
		return sqlCommonDao;
	}

	public UserDao getSqlUserDao() {
		return sqlUserDao;
	}

	public AdminDao getSqlAdminDao() {
		return sqlAdminDao;
	}

	public CommonDao getFileCommonDao() {
		return fileCommonDao;
	}

	public UserDao getFileUserDao() {
		return fileUserDao;
	}

	public AdminDao getFileAdminDao() {
		return fileAdminDao;
	}
}
