package by.tr.library.service;

import by.tr.library.service.exception.ServiceException;

public interface ClientService {
       boolean authorization (String login, String password) throws ServiceException;
       boolean registration (String login, String password) throws ServiceException;
       boolean deleteUserById (int id) throws ServiceException;
       boolean deleteUserByLogin (String login) throws ServiceException;
       boolean blockUserById (int id) throws ServiceException;
       boolean changePassword (String login, String password) throws ServiceException;

}
