package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.bean.User;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class AuthorizationCommand implements Command{

	@Override
	public Response execute(Request request) throws CommandException {
		
		String login = request.getLogin();
		String password = request.getPassword();

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		boolean result;
		try {
			result = service.authorization(login, password);
		} catch (ServiceException e) {
			throw new CommandException("Authorization command exception", e);
		}
		Response response = new Response();
		if (result) {
			response.setErrorMessage(null);
			response.setMessage("Authorization completed successfully");
		} else {
			response.setErrorMessage("There is no unblocked user with such credentials");
			response.setMessage(null);
		}
		return response;
	}

}
