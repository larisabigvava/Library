package by.tr.library.command.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class AddBookCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		boolean result = false;
		Book book = new Book();
		book.setAuthor(request.getAuthor());
		book.setTitle(request.getTitle());
		book.setPrice(request.getPrice());
		if (request.getLanguage() != null && request.getLevel() != null){
			ProgrammerBook programmerBook = new ProgrammerBook();
			programmerBook.setTitle(request.getTitle());
			programmerBook.setAuthor(request.getAuthor());
			programmerBook.setPrice(request.getPrice());
			programmerBook.setLevel(request.getLevel());
			programmerBook.setLanguage(request.getLanguage());
			try {
				result = service.addBook(programmerBook);
			} catch (ServiceException e) {
				throw new CommandException("add book command exception", e);
			}
		} else {
			try {
				result = service.addBook(book);
			} catch (ServiceException e) {
				throw new CommandException("add book command exception", e);
			}
		}
		Response response = new Response();
		if (result) {
			response.setErrorMessage(null);
			response.setMessage("Adding book completed successfully");
		} else {
			response.setErrorMessage("There is book with such characteristics");
			response.setMessage(null);
		}
		return response;
	}

}
