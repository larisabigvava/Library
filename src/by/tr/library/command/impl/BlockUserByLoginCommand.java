package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class BlockUserByLoginCommand implements Command {
    @Override
    public Response execute(Request request) throws CommandException {
        ServiceFactory factory = ServiceFactory.getInstance();
        ClientService service = factory.getClientService();
        boolean result;
        try {
            result = service.blockUserByLogin(request.getLogin());
        } catch (ServiceException e) {
            throw new CommandException("Block user command exception", e);
        }
        Response response = new Response();
        if (result) {
            response.setErrorMessage(null);
            response.setMessage("Blocking completed successfully");
        } else {
            response.setErrorMessage("There is no user with such id");
            response.setMessage(null);
        }
        return response;
    }
}
