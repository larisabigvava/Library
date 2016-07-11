package by.tr.library.dao;

import by.tr.library.dao.impl.*;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	//sql dao
	private final CommonDao sqlCommonDao = new SQLCommonDao();
	private final UserDao sqlUserDao = new SQLUserDao();
	private final AdminDao sqlAdminDao = new SQLAdminDao();

	//file dao
	private final CommonDao fileCommonDao = new FileCommonDao();
	private final UserDao fileUserDao = new FileUserDao();
	private final AdminDao fileAdminDao = new FileAdminDao();


	private DAOFactory(){}

	public CommonDao getSqlCommonDao() {
		return sqlCommonDao;
	}

	public UserDao getSqlUserDao() {
		return sqlUserDao;
	}

	public AdminDao getSqlAdminDao() {
		return sqlAdminDao;
	}

	public static DAOFactory getInstance(){
		return factory;
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
