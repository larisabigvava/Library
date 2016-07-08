package by.tr.library.command.impl;

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
		String author = request.getAuthor(), title = request.getTitle();
		int price = request.getPrice();
		ServiceFactory factory = ServiceFactory.getInstance();
		LibraryService service = factory.getLibraryService();
		boolean result;
		try {
			result = service.addBook(title, author, price);
		} catch (ServiceException e) {
			throw new CommandException("add book command exception", e);
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
