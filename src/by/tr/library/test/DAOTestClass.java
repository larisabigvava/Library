package by.tr.library.test;

import by.tr.library.bean.Book;
import by.tr.library.dao.AdminDao;
import by.tr.library.dao.CommonDao;
import by.tr.library.dao.DAOFactory;
import by.tr.library.dao.exception.DAOException;
import by.tr.library.dao.impl.FileUserDao;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by Seagull on 12.07.2016.
 */
public class DAOTestClass {

    @Test
    public void tstBookMethods(){
        DAOFactory factory = DAOFactory.getInstance();
        AdminDao adminDao = factory.getFileAdminDao();
        FileUserDao userDao = FileUserDao.getInstance();
        String title = "title";
        String author = "author";
        int price = 123;
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPrice(123);
        try {
            Assert.assertTrue(adminDao.addNewBook(book), "adding book failed");
            Assert.assertNotNull(userDao.getBooksByAuthor(author), "getting books by author failed");
            Assert.assertNotNull(userDao.getBooksByTitle(title), "getting books by title failed");
            Assert.assertTrue(adminDao.deleteBookByTitle(title), "deleting book by title failed");
            Assert.assertNull(userDao.getBooksByTitle(title), "find book by title while file is empty");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tstUserMethods(){
        DAOFactory factory = DAOFactory.getInstance();
        AdminDao adminDao = factory.getFileAdminDao();
        CommonDao commonDao = factory.getFileCommonDao();
        String login = "login", password = "password";
        try {
            Assert.assertNotNull(commonDao.registration(login, password), "registration failed");
            Assert.assertNotNull(commonDao.authorization(login, password), "authorization failed");
            Assert.assertTrue(adminDao.blockUserByLogin(login), "blocking failed");
            Assert.assertTrue(adminDao.unblockUserByLogin(login), "unblocking failed");
            Assert.assertTrue(adminDao.deleteUserByLogin(login), "deleting failed");
            Assert.assertFalse(adminDao.deleteUserByLogin(login), "delete user while file is empty");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
