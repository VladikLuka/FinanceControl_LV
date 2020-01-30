package main.by.javatr.service;

import main.by.javatr.bean.Account;
import main.by.javatr.service.exception.ServiceException;

import java.util.List;

public interface AccountService {

    boolean checkRegistration(Account account) throws ServiceException;
    boolean registration(Account account) throws ServiceException;
    boolean logIn(Account account) throws ServiceException;
    Account changeBalance(Account account) throws ServiceException;
    Account changeCategory(Account account) throws ServiceException;
    Account update(Account account) throws ServiceException;
    boolean delete(Account account) throws ServiceException;
    Account getAccountByLogin(Account account) throws ServiceException;
    List<Account> getAll() throws ServiceException;
    Account getAccountById(int id) throws ServiceException;
}
