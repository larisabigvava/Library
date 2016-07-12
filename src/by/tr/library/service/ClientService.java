package by.tr.library.service;

import by.tr.library.bean.User;
import by.tr.library.service.exception.ServiceException;

public interface ClientService {
       User authorization (String login, String password) throws ServiceException;
       User registration (String login, String password) throws ServiceException;
       boolean deleteUserByLogin (String login) throws ServiceException;
       boolean blockUserByLogin (String login) throws ServiceException;
       boolean changePassword (String login, String password) throws ServiceException;
       boolean unblockUserByLogin(String login) throws ServiceException;
}
