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
		UserDao fileUserDao = factory.getFileUserDao();

		try {
			catalog = fileUserDao.getBooksByAuthor(author);
		} catch (DAOException e){
			throw new ServiceException("find book service exception", e);
		}

		return catalog;
	}

	@Override
	public Book findByTitle(String title) throws ServiceException {
		Book book = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDao fileUserDao = factory.getFileUserDao();

		try {
			book = fileUserDao.getBookByTitle(title);
		} catch (DAOException e){
			throw new ServiceException("find book service exception", e);
		}

		return book;
	}

	@Override
	public boolean addBook(Book book) throws ServiceException {
		boolean result = false;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();
		
		// call method check
		try {
			result = adminDao.addNewBook(book);
		} catch (DAOException e) {
			throw new ServiceException("add book service exception", e);
		}
		
		return result;
	}

	@Override
	public boolean deleteBookByTitle(String title) throws ServiceException {
		boolean result = false;

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();

		try {
			result = adminDao.deleteBookByTitle(title);
		} catch (DAOException e) {
			throw new ServiceException("delete book service exception", e);
		}

		return result;
	}

	@Override
	public Catalog getCatalog() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		CommonDao commonDao = factory.getFileCommonDao();
		
		Catalog catalog = null;
		try {
			catalog = commonDao.getCatalog();
		} catch (DAOException e) {
			throw new ServiceException("get catalog service exception", e);
		}
		return catalog;
	}


}

















