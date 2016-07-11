package by.tr.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.tr.library.command.Command;
import by.tr.library.command.impl.*;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();
//	private Map<String, Command> commands = new HashMap<>();

	public CommandHelper(){
		commands.put(CommandName.AUTHORIZATION_COMMAND, new AuthorizationCommand());
		commands.put(CommandName.ADD_BOOK_COMMAND, new AddBookCommand());
		commands.put(CommandName.REGISTER_COMMAND, new RegisterUserCommand());
		commands.put(CommandName.GET_CATALOG_COMMAND, new GetCalatogCommand());
		commands.put(CommandName.BLOCK_USER_BY_ID_COMMAND, new BlockUserByIdCommand());
		commands.put(CommandName.CHANGE_PASSWORD_COMMAND, new ChangePasswordCommand());
		commands.put(CommandName.DELETE_BOOK_BY_TITLE_COMMAND, new DeleteBookByTitleCommand());
		commands.put(CommandName.DELETE_USER_BY_ID_COMMAND, new DeleteUserByIdCommand());
		commands.put(CommandName.DELETE_USER_BY_LOGIN_COMMAND, new DeleteUserByLoginCommand());
		commands.put(CommandName.GET_BOOK_BY_TITLE_COMMAND, new GetBookByTitleCommand());
		commands.put(CommandName.GET_BOOKS_BY_AUTHOR_COMMAND, new GetBooksByAuthorCommand());
	}
	
	public Command getCommand(String commandName){//"REGISTER_USER"
		CommandName command = CommandName.valueOf(commandName);
		Command executeCommand = commands.get(command);
		return executeCommand;
	}
	

}
