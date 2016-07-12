package by.tr.library.service;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.service.exception.ServiceException;

public interface LibraryService {
	Catalog findByAuthor(String author)throws ServiceException;
	Catalog findByTitle(String title)throws ServiceException;
	boolean addBook(Book book)throws ServiceException;
	boolean deleteBookByTitle(String title)throws ServiceException;
	Catalog getCatalog()throws ServiceException;
}
