package by.tr.library.command.impl;

import by.tr.library.bean.Request;
import by.tr.library.bean.Response;
import by.tr.library.command.Command;
import by.tr.library.command.exception.CommandException;
import by.tr.library.service.ClientService;
import by.tr.library.service.ServiceFactory;
import by.tr.library.service.exception.ServiceException;

public class RegisterUserCommand implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		String login = request.getLogin();
		String password = request.getPassword();

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService service = factory.getClientService();
		boolean result;
		try {
			result = service.registration(login, password);
		} catch (ServiceException e) {
			throw new CommandException("registration command exception", e);
		}
		Response response = new Response();
		if (result) {
			response.setErrorMessage(null);
			response.setMessage("Registration completed successfully");
		} else {
			response.setErrorMessage("There is user with such credentials.Please choose new login");
			response.setMessage(null);
		}
		return response;
	}

}
