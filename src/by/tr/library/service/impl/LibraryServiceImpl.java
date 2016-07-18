package by.tr.library.service.impl;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService{

	@Override
	public Catalog findByAuthor(String author) throws ServiceException {
		Catalog catalog = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getSqlUserDao();

		try {
			catalog = userDao.getBooksByAuthor(author);
		} catch (DAOException e){
			throw new ServiceException("Find book by author service exception", e);
		}

		return catalog;
	}

	@Override
	public Catalog findByTitle(String title) throws ServiceException {
		Catalog catalog = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getSqlUserDao();

		try {
			catalog = userDao.getBooksByTitle(title);
		} catch (DAOException e){
			throw new ServiceException("Find book by title service exception", e);
		}

		return catalog;
	}

	@Override
	public boolean addBook(Book book) throws ServiceException {
		boolean result = false;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getSqlAdminDao();
		
		try {
			result = adminDao.addNewBook(book);
		} catch (DAOException e) {
			throw new ServiceException("Add book service exception", e);
		}
		
		return result;
	}

	@Override
	public boolean deleteBookByTitle(String title) throws ServiceException {
		boolean result = false;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getSqlAdminDao();

		try {
			result = adminDao.deleteBookByTitle(title);
		} catch (DAOException e) {
			throw new ServiceException("Delete book service exception", e);
		}

		return result;
	}

	@Override
	public Catalog getCatalog() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getSqlCommonDao();
		
		Catalog catalog = null;
		try {
			catalog = commonDao.getCatalog();
		} catch (DAOException e) {
			throw new ServiceException("Get catalog service exception", e);
		}
		return catalog;
	}


}

















