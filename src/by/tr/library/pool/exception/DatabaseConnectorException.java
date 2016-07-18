package by.tr.library.pool.exception;

/**
 * Created by Я on 17.07.2016.
 */
public class DatabaseConnectorException extends Exception{
    public DatabaseConnectorException(String message){
        super(message);
    }

    public DatabaseConnectorException(String message, Exception e){
        super(message, e);
    }
}
