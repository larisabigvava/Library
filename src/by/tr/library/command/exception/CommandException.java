package by.tr.library.command.exception;

public class CommandException extends Exception{
	private static final long serialVersionUID = 1L;

	public CommandException(String message){
		super(message);
	}

	public CommandException(String message, Exception e){
		super(message, e);
	}
}
