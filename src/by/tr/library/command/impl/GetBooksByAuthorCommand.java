package by.tr.library.command.impl;

import by.tr.library.bean.Book;
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
        List <Book> books;
        try {
            books = service.findByAuthor(author);
        } catch (ServiceException e) {
            throw new CommandException("get book by title command exception", e);
        }
        Response response = new Response();
        if (books != null) {
            response.setErrorMessage(null);
            response.setMessage("Book was founded.");
            response.setListBook(books);
        } else {
            response.setErrorMessage("There is no book with this title.");
            response.setMessage(null);
            response.setBook(null);
        }
        return response;
    }
}
