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

    private final static String BOOKS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\books.txt";
    private final static String USERS_FILE = "C:\\ \\_poit+epam\\test automation\\лаба\\Library\\src\\resources\\users.txt";
    private List<String> users = null;

    public static FileCommonDao getInstance() {
        return instance;
    }

    private FileCommonDao(){}

    @Override
    public boolean authorization(String login, String password) throws DAOException {
        if (users == null){
            users = readFile();
        }
        String roleAdmin = "ADMIN";
        String roleUser = "USER";
        String blocked = "false";
        for (String user: users){
            if (user.startsWith(login)){
                if (user.contentEquals(login+"::"+password+"::"+roleAdmin+"::"+blocked)
                        || user.contentEquals(login+"::"+password+"::"+roleUser+"::"+blocked)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password) throws DAOException {
        if (users == null){
            users = readFile();
        }
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
        } catch (IOException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
        return true;
    }

    @Override
    public boolean changePassword(String login,String password) throws DAOException {
        String role;
        for (String user: users){
            if (user.startsWith(login)){
                users.remove(user);
                if (user.contains("::ADMIN::")){
                    role = "ADMIN";
                    user = login+"::"+password+"::"+role+"::"+false;
                } else if (user.contains("::USER::")){
                    role = "USER";
                    user = login+"::"+password+"::"+role+"::"+false;
                }
                users.add(user);
            }
        }
        return false;
    }

    @Override
    public Catalog getCatalog() throws DAOException {
        Catalog catalog = new Catalog();
        try (FileInputStream fis = new FileInputStream(BOOKS_FILE)) {
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                String[] recordFields = record.split("::");
                Book book = new Book();
                book.setTitle(recordFields[0]);
                book.setAuthor(recordFields[1]);
                book.setPrice(Integer.parseInt(recordFields[2]));
                if (recordFields.length == 5){
                    ProgrammerBook programmerBook = (ProgrammerBook)book;
                    programmerBook.setLevel(recordFields[3]);
                    programmerBook.setLanguage(recordFields[4]);
                    catalog.addProgrammerBook(programmerBook);
                } else {
                    catalog.addBook(book);
                }
            }
        } catch (IOException ex) {
            throw new DAOException("get catalog dao exception", ex);
        }
        return catalog;
    }

    private List<String> readFile() throws DAOException {
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
