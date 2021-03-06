package by.tr.library.command.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

import java.util.List;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class GetBooksByAuthorCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        String author = request.getAuthor();

        ServiceFactory factory = ServiceFactory.getInstance();
        LibraryService service = factory.getLibraryService();
        boolean result = false;
        Catalog catalog = null;
        try {
            catalog = service.findByAuthor(author);
        } catch (ServiceException e) {
            throw new CommandException("Get book by title command exception", e);
        }
        Response response = new Response();
        if (catalog.getBooks().isEmpty() && catalog.getProgrammerBooks().isEmpty()) {
            response.setErrorMessage("There is no such books");
            response.setMessage(null);
            response.setCatalog(null);
        } else {
            response.setErrorMessage(null);
            response.setMessage("Books were founded.");
            response.setCatalog(catalog);
        }
        return response;
    }
}
