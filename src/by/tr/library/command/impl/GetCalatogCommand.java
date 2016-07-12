package by.tr.library.command.impl;

import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class GetCalatogCommand implements Command{

	@Override
	public Response execute(Request request) throws CommandException {

		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();

		Catalog catalog = null;
		try {
			catalog = service.getCatalog();
		} catch (ServiceException e) {
			throw new CommandException("Get catalog command exception", e);
		}
		Response response = new Response();
		if (catalog.getBooks().isEmpty() && catalog.getProgrammerBooks().isEmpty()) {
			response.setErrorMessage("Library is empty.");
			response.setMessage(null);
			response.setCatalog(null);
		} else {
			response.setErrorMessage(null);
			response.setMessage("Library contains books");
			response.setCatalog(catalog);
		}
		return response;
	}

}
