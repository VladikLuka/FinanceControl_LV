package main.by.javatr.service;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.service.ServiceException.ServiceException;

import java.io.IOException;

public interface AccountService {

    boolean checkRegistration(Account account) throws ServiceException;
    boolean registration(Account account) throws ServiceException;
    Account logIn(Account account) throws ServiceException;
    Account changeBalance(Account account) throws ServiceException;
    Account changeCategory(Account account) throws ServiceException;
    Account update(Account account) throws ServiceException;
    boolean delete(Account account) throws ServiceException;
    Account getAccountByLogin(Account account) throws ServiceException;

}
