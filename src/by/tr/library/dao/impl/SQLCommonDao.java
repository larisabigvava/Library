package by.tr.library.dao.impl;

import java.sql.*;
import java.util.List;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.bean.User;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.pool.ConnectionPool;

public class SQLCommonDao implements CommonDao {
    private static final String UPDATE_USER_PASSWORD = "UPDATE `users` SET `password`=? WHERE `login`=?";
    private static final String INSERT_NEW_USER = "INSERT INTO `users` (`login`,`password`, `role`, `blocked`) VALUES(?,?,?,?,?)";
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
		if (isUsersEmpty(login)){
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

	private boolean isUsersEmpty(String login) throws DAOException {
		boolean result = false;
		try (
				Connection connection = ConnectionPool.getInstance().getConnection();
				Statement statement = connection.createStatement();
				){
            ResultSet set = statement.executeQuery(SELECT_USERS);
            if (set.next()){
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
                user.setLogin(set.getString(1));
                user.setPassword(set.getString(2));
                user.setRole(set.getString(3));
                user.setBlocked(set.getBoolean(4));
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
                Statement statement = connection.createStatement();
                ResultSet setOfBooks = statement.executeQuery(SELECT_BOOKS);
                ResultSet setOfProgrammerBooks = statement.executeQuery(SELECT_PROGRAMMER_BOOKS)
        ){
            if (setOfBooks.next() || setOfProgrammerBooks.next()){
                catalog = new Catalog();
                while (setOfBooks.next()){
                    Book book = new Book();
                    book.setTitle(setOfBooks.getString(1));
                    book.setAuthor(setOfBooks.getString(2));
                    book.setPrice(setOfBooks.getInt(3));
                    catalog.addBook(book);
                }
                while (setOfProgrammerBooks.next()){
                    ProgrammerBook programmerBook = new ProgrammerBook();
                    programmerBook.setTitle(setOfBooks.getString(1));
                    programmerBook.setAuthor(setOfBooks.getString(2));
                    programmerBook.setPrice(setOfBooks.getInt(3));
                    programmerBook.setLanguage(setOfBooks.getString(4));
                    programmerBook.setLevel(setOfBooks.getString(5));
                    catalog.addProgrammerBook(programmerBook);
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Get catalog dao excption", e);
        }
        return catalog;
	}
}
