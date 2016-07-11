package by.tr.library.dao;

import by.tr.library.dao.impl.*;

public class DAOFactory {
	private static final DAOFactory factory = new DAOFactory();

	private DAOFactory(){}

	//sql dao
//	private final CommonDao sqlCommonDao = new SQLCommonDao();
//	private final UserDao sqlUserDao = new SQLUserDao();
//	private final AdminDao sqlAdminDao = new SQLAdminDao();

//	public CommonDao getSqlCommonDao() {
//		return sqlCommonDao;
//	}
//
//	public UserDao getSqlUserDao() {
//		return sqlUserDao;
//	}
//
//	public AdminDao getSqlAdminDao() {
//		return sqlAdminDao;
//	}

	public static DAOFactory getInstance(){
		return factory;
	}

	public CommonDao getFileCommonDao() {
		return FileCommonDao.getInstance();
	}

	public UserDao getFileUserDao() {
		return FileUserDao.getInstance();
	}

	public AdminDao getFileAdminDao() {
		return FileAdminDao.getInstance();
	}
}
