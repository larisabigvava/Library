package by.tr.library.service.impl;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.UserDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.exception.ServiceException;

public class LibraryServiceImpl implements LibraryService{

	@Override
	public Book findByAuthor(String author) throws ServiceException {
		Book book = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDao fileUserDao = factory.getFileUserDao();

		try {
			book = fileUserDao.getBooksByAuthor(author);
		} catch (DAOException e){
			throw new ServiceException("service message", e);
		}

		return book;
	}

	@Override
	public Book findByTitle(String title) throws ServiceException {
		Book book = null;

		DAOFactory factory = DAOFactory.getInstance();
		UserDao fileUserDao = factory.getFileUserDao();

		try {
			book = fileUserDao.getBookByTitle(title);
		} catch (DAOException e){
			throw new ServiceException("service message", e);
		}

		return book;
	}

	@Override
	public boolean addBook(String title, String author, int price) throws ServiceException {
		boolean result = false;
		
		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(title);
		book.setPrice(price);

		DAOFactory factory = DAOFactory.getInstance();
		AdminDao adminDao = factory.getFileAdminDao();
		
		// call method check
		try {
			result = adminDao.addNewBook(book);
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}
		
		return result;
	}

	@Override
	public boolean deleteBookByTitle(String title) throws ServiceException {
		//TODO
		return false;
	}

	@Override
	public List<Book> getCatalog() throws ServiceException {
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDao userDao = factory.getFileUserDao();
		
		List<Book> listBook;
		try {
			listBook = userDao.getCatalog();
		} catch (DAOException e) {
			throw new ServiceException("service message", e);
		}
		
		return listBook;
	}

}

















