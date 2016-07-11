package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.LibraryService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class DeleteBookByTitleCommand implements Command{
    @Override
    public Response execute(Request request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        LibraryService service = factory.getLibraryService();
        boolean result;
        try {
            result = service.deleteBookByTitle(request.getTitle());
        } catch (ServiceException e) {
            throw new CommandException("deleting book command exception", e);
        }
        Response response = new Response();
        if (result) {
            response.setErrorMessage(null);
            response.setMessage("Deleting book completed successfully");
        } else {
            response.setErrorMessage("There is book with such characteristics");
            response.setMessage(null);
        }
        return response;
    }
}
