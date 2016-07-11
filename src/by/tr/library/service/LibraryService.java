package by.tr.library.service;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.service.exception.ServiceException;

public interface LibraryService {
	List<Book> findByAuthor(String author)throws ServiceException;
	Book findByTitle(String title)throws ServiceException;
	boolean addBook(Book book)throws ServiceException;
	boolean deleteBookByTitle(String title)throws ServiceException;
	List<Book> getCatalog()throws ServiceException;
}
