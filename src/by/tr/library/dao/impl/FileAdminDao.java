package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.bean.User;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.dao.util.ReadUsersFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileAdminDao implements AdminDao {

    private final static Logger LOGGER = LogManager.getRootLogger();

    private final static FileAdminDao instance = new FileAdminDao();
    private final static String BOOKS_FILE = "C:\\Users\\Larisa_Bigvava@epam.com\\Desktop\\Library\\books.txt";
    private final static String USERS_FILE = "C:\\Users\\Larisa_Bigvava@epam.com\\Desktop\\Library\\users.txt";
    private List<String> users = null;

    public static FileAdminDao getInstance() {
        return instance;
    }

    public FileAdminDao(){}

    @Override
    public boolean blockUserByLogin(String login) throws DAOException {
        boolean result = false;
        DAOFactory factory = DAOFactory.getInstance();
        CommonDao commonDao = factory.getFileCommonDao();
        users = ReadUsersFile.readUsers();
        String newUser = "";
        Iterator<String> iterator = users.iterator();
        while (iterator.hasNext()){
            String userStr = iterator.next();
            if (userStr.startsWith(login)) {
                iterator.remove();
                User user = new User();
                user.setLogin(login);
                userStr = userStr.substring(login.length() + 2);
                user.setPassword(userStr.substring(0, userStr.indexOf("::")));
                userStr = userStr.substring(user.getPassword().length() + 2);
                user.setRole(userStr.substring(0, userStr.indexOf("::")));
                user.setBlocked(true);
                newUser = prepareUser(user);
            }
        }
        if (!newUser.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream(USERS_FILE, false)) {
                PrintWriter writer = new PrintWriter(fos);
                users.add(newUser);
                for(String user : users){
                    writer.println(user);
                }
                result = true;
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException("Block user by login dao exception", e);
            }
        }
        return result;
    }

    @Override
    public boolean unblockUserByLogin(String login) throws DAOException {
        boolean result = false;
        DAOFactory factory = DAOFactory.getInstance();
        CommonDao commonDao = factory.getFileCommonDao();
        users = ReadUsersFile.readUsers();
        String newUser = "";
        Iterator<String> iterator = users.iterator();
        while (iterator.hasNext()){
             String userStr = iterator.next();
            if (userStr.startsWith(login)) {
                iterator.remove();
                User user = new User();
                user.setLogin(login);
                userStr = userStr.substring(login.length() + 2);
                user.setPassword(userStr.substring(0, userStr.indexOf("::")));
                userStr = userStr.substring(user.getPassword().length() + 2);
                user.setRole(userStr.substring(0, userStr.indexOf("::")));
                user.setBlocked(false);
                newUser = prepareUser(user);
            }
        }
        if (!newUser.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream(USERS_FILE, false)) {
                PrintWriter writer = new PrintWriter(fos);
                users.add(newUser);
                for(String user : users){
                    writer.println(user);
                }
                result = true;
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException("Unblock user by login dao exception", e);
            }
        }
        return result;
    }

    @Override
    public boolean addNewBook(Book book) throws DAOException {
        try (FileOutputStream fos = new FileOutputStream(BOOKS_FILE, true)) {
            PrintWriter writer = new PrintWriter(fos);
            writer.println(prepareBook(book));
            writer.flush();
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
            throw new DAOException("Add new book dao exception", ex);
        }
        return true;
    }

    @Override
    public boolean deleteBookByTitle(String title) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        Catalog catalog = daoFactory.getFileCommonDao().getCatalog();
        boolean result = false;
        Iterator<Book> iterator = catalog.getBooks().iterator();
        Iterator<ProgrammerBook> programmerBookIterator = catalog.getProgrammerBooks().iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if (book.getTitle().equals(title)){
                iterator.remove();
                result = true;
            }
        }
        while (programmerBookIterator.hasNext()){
            ProgrammerBook programmerBook = programmerBookIterator.next();
            if (programmerBook.getTitle().equals(title)){
                programmerBookIterator.remove();
                result = true;
            }
        }
        if (result) {
            try (FileOutputStream fos = new FileOutputStream(BOOKS_FILE)) {
                PrintWriter writer = new PrintWriter(fos);
                for (Book book : catalog.getBooks()) {
                    writer.println(prepareBook(book));
                }
                for (ProgrammerBook programmerBook : catalog.getProgrammerBooks()) {
                    writer.println(prepareBook(programmerBook));
                }
                writer.flush();
            } catch (IOException ex) {
                LOGGER.error(ex.getMessage());
                throw new DAOException("Delete book dao exception", ex);
            }
        }
        return result;
    }

    private String prepareBook(Book book) {
        StringBuilder record = new StringBuilder(book.getTitle());
        record.append("::")
                .append(book.getAuthor())
                .append("::")
                .append(book.getPrice());
        if (book.getClass() == ProgrammerBook.class) {
            ProgrammerBook programmerBook = (ProgrammerBook) book;
            record.append("::")
                    .append(programmerBook.getLevel())
                    .append("::")
                    .append(programmerBook.getLanguage());
        }
        return record.toString();
    }

    private String prepareUser(User user) {
        StringBuilder record = new StringBuilder(user.getLogin());
        record.append("::")
                .append(user.getPassword())
                .append("::")
                .append(user.getRole())
                .append("::")
                .append(user.getBlocked());
        return record.toString();
    }

    @Override
    public boolean deleteUserByLogin(String login) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        users = ReadUsersFile.readUsers();
        boolean result = false;
        Iterator<String> iterator = users.iterator();
        while (iterator.hasNext()){
            String userStr = iterator.next();
            if (userStr.startsWith(login)) {
                iterator.remove();
                try (FileOutputStream fos = new FileOutputStream(USERS_FILE, false)){
                    PrintWriter writer = new PrintWriter(fos);
                    writer.println(users);
                    result = true;
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    throw new DAOException("Unblock user by login dao exception", e);
                }
            }
        }
        return result;
    }


}
