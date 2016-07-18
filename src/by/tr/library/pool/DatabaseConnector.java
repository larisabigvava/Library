package by.tr.library.pool;

import by.tr.library.pool.exception.DatabaseConnectorException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnector() {}

    public static ProxyConnection getConnection() throws DatabaseConnectorException {
        ProxyConnection connection;
        try {
            connection = new ProxyConnection(DriverManager.getConnection(URL, USER, PASSWORD));
        } catch (SQLException ex) {
            throw new DatabaseConnectorException("get connection exception", ex);
        }

        return connection;
    }
}
