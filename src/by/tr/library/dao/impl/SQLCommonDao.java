package by.tr.library.dao.impl;

import java.sql.*;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.bean.User;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

public class SQLCommonDao implements CommonDao {
    private static final String COLUMN_NAME_LOGIN = "login";
    private static final String COLUMN_NAME_PASSWORD = "password";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_AUTHOR = "author";
    private static final String COLUMN_NAME_PRICE = "price";
    private static final String COLUMN_NAME_LEVEL = "level";
    private static final String COLUMN_NAME_LANGUAGE = "language";
    private static final String COLUMN_NAME_BLOCKED = "blocked";
    private static final String COLUMN_NAME_ROLE = "role";
    private static final String UPDATE_USER_PASSWORD = "UPDATE `users` SET `password`=? WHERE `login`=?";
    private static final String INSERT_NEW_USER = "INSERT INTO `users`(`login`,`password`,`role`,`blocked`) VALUES(?,?,?,?)";
	private static final String SELECT_USERS_BY_LOGIN = "SELECT * FROM `users` WHERE `login` = ?";
	private static final String SELECT_USERS = "SELECT * FROM `users`";
	private static final String SELECT_BOOKS = "SELECT * FROM `books`";
	private static final String SELECT_PROGRAMMER_BOOKS = "SELECT * FROM `programmer_books`";

    @Override
	public User authorization(String login, String password) throws DAOException {
		User foundedUser = selectUserByLogin(login);
        User user = null;
        if (foundedUser.getPassword().equals(password)){
            if (!foundedUser.getBlocked()) {
                user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(foundedUser.getRole());
                user.setBlocked(foundedUser.getBlocked());
            }
        }
		return user;
	}

	@Override
	public User registration(String login, String password) throws DAOException {
		User user = null;
        String role;
		if (isUsersEmpty()){
            role = "ADMIN";
		} else {
		    role = "USER";
        }
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_USER)
                ){
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.setBoolean(4, false);
            if (statement.executeUpdate() == 1){
                user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                user.setBlocked(false);
            }
        } catch (SQLException e) {
            throw new DAOException("Registration dao exception", e);
        }
		return user;
	}

	private boolean isUsersEmpty() throws DAOException {
		boolean result = false;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement();
				){
            ResultSet set = statement.executeQuery(SELECT_USERS);
            if (!set.next()){
                result = true;
            }
		} catch (SQLException e) {
			throw new DAOException("Select users dao exception", e);
		}
		return result;
	}

	private User selectUserByLogin(String login) throws DAOException {
	    User user = new User();
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_LOGIN)
				){
			statement.setString(1, login);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                user.setLogin(set.getString(COLUMN_NAME_LOGIN));
                user.setPassword(set.getString(COLUMN_NAME_PASSWORD));
                user.setRole(set.getString(COLUMN_NAME_ROLE));
                user.setBlocked(set.getBoolean(COLUMN_NAME_BLOCKED));
            }
		} catch (SQLException e) {
			throw new DAOException("select user by login dao exception", e);
		}
		return user;
	}

	@Override
	public boolean changePassword(String login, String password) throws DAOException {
		boolean result = false;
        if (!selectUserByLogin(login).getPassword().equals(password)){
            try (
                    Connection connection = ConnectionPool.getInstance().getConnection();
                    PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)
                    ){
                statement.setString(1, password);
                statement.setString(2, login);
                if (statement.executeUpdate() == 1){
                    result = true;
                }
            } catch (SQLException e) {
                throw new DAOException("Change password dao exception", e);
            }
        }
        return result;
	}

	@Override
    public Catalog getCatalog() throws DAOException {
        Catalog catalog = null;
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet setOfBooks = statement.executeQuery(SELECT_BOOKS);
            catalog = new Catalog();
            while (setOfBooks.next()){
                Book book = new Book();
                book.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
                book.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
                book.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
                catalog.addBook(book);
            }
            ResultSet setOfProgrammerBooks = statement.executeQuery(SELECT_PROGRAMMER_BOOKS);
            while (setOfProgrammerBooks.next()){
                ProgrammerBook programmerBook = new ProgrammerBook();
                programmerBook.setTitle(setOfBooks.getString(COLUMN_NAME_TITLE));
                programmerBook.setAuthor(setOfBooks.getString(COLUMN_NAME_AUTHOR));
                programmerBook.setPrice(setOfBooks.getInt(COLUMN_NAME_PRICE));
                programmerBook.setLanguage(setOfBooks.getString(COLUMN_NAME_LANGUAGE));
                programmerBook.setLevel(setOfBooks.getString(COLUMN_NAME_LEVEL));
                catalog.addProgrammerBook(programmerBook);
            }

        } catch (SQLException e) {
            throw new DAOException("Get catalog dao exception", e);
        }
        return catalog;
    }
}
