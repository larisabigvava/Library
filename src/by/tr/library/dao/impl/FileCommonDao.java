package by.tr.library.dao.impl;

import by.tr.library.bean.Book;
import by.tr.library.bean.Catalog;
import by.tr.library.bean.ProgrammerBook;
import by.tr.library.bean.User;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.exception.DAOException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Larisa_Bigvava on 7/8/2016.
 */
public class FileCommonDao implements CommonDao {

    private final static FileCommonDao instance = new FileCommonDao();

    private final static String BOOKS_FILE = "C:\\Users\\Larisa_Bigvava@epam.com\\Desktop\\Library\\books.txt";
    private final static String USERS_FILE = "C:\\Users\\Larisa_Bigvava@epam.com\\Desktop\\Library\\users.txt";
    private List<String> users = null;

    public static FileCommonDao getInstance() {
        return instance;
    }

    private FileCommonDao(){}

    @Override
    public User authorization(String login, String password) throws DAOException {
        if (users == null){
            users = readUsersFile();
        }
        User user = null;
        String roleAdmin = "ADMIN";
        String roleUser = "USER";
        String blocked = "false";
        for (String userStr: users){
            if (userStr.startsWith(login)){
                if (userStr.contentEquals(login+"::"+password+"::"+roleAdmin+"::"+blocked)){
                    user = new User();
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(roleAdmin);
                    user.setBlocked(false);
                } else if (userStr.contentEquals(login+"::"+password+"::"+roleUser+"::"+blocked)){
                    user = new User();
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setRole(roleUser);
                    user.setBlocked(false);
                }
            }
        }
        return user;
    }

    @Override
    public User registration(String login, String password) throws DAOException {
        if (users == null){
            users = readUsersFile();
        }
        User user = null;
        String role = (users.size() == 0) ? "ADMIN" : "USER";
        boolean blocked = false;
        users = null;
        try (FileWriter writer = new FileWriter(USERS_FILE, true)) {
            writer.append(login)
                    .append("::")
                    .append(password)
                    .append("::")
                    .append(role)
                    .append("::")
                    .append(String.valueOf(blocked))
                    .append("\n");
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
            user.setBlocked(false);
        } catch (IOException ex) {
            throw new DAOException("Registration dao exception", ex);
        }
        return user;
    }

    @Override
    public boolean changePassword(String login,String password) throws DAOException {
        String role;
        boolean result = false;
        for (String user: users) {
            if (user.startsWith(login)) {
                users.remove(user);
                if (user.contains("::ADMIN::")) {
                    role = "ADMIN";
                    user = login + "::" + password + "::" + role + "::" + false;
                } else if (user.contains("::USER::")) {
                    role = "USER";
                    user = login + "::" + password + "::" + role + "::" + false;
                }
                users.add(user);
                try (FileWriter writer = new FileWriter(USERS_FILE, false)) {
                    for (String userStr: users) {
                        writer.append(userStr)
                                .append("\n");
                    }
                } catch (IOException ex) {
                    throw new DAOException("Change password dao exception", ex);
                }
                result = true;
            }
        }
            return result;
    }

    @Override
    public Catalog getCatalog() throws DAOException {
        Catalog catalog = new Catalog();
        try (FileInputStream fis = new FileInputStream(BOOKS_FILE)) {
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                String[] recordFields = record.split("::");
                if (recordFields.length == 5) {
                    ProgrammerBook programmerBook = new ProgrammerBook();
                    programmerBook.setTitle(recordFields[0]);
                    programmerBook.setAuthor(recordFields[1]);
                    programmerBook.setPrice(Integer.parseInt(recordFields[2]));
                    programmerBook.setLevel(recordFields[3]);
                    programmerBook.setLanguage(recordFields[4]);
                    catalog.addProgrammerBook(programmerBook);
                } else {
                    Book book = new Book();
                    book.setTitle(recordFields[0]);
                    book.setAuthor(recordFields[1]);
                    book.setPrice(Integer.parseInt(recordFields[2]));
                    catalog.addBook(book);
                }

            }
        } catch (IOException ex) {
            throw new DAOException("Get catalog dao exception", ex);
        }
        return catalog;
    }

    public List<String> readUsersFile() throws DAOException {
        List<String> strings = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(USERS_FILE))) {
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (IOException ex) {
            throw new DAOException("File with users data not found!", ex);
        }
        return strings;
    }
}
