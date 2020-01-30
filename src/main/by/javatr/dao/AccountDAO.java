package main.by.javatr.dao;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.exception.DAOException;

import java.util.List;

public interface AccountDAO<T> {

    boolean add(Account account) throws DAOException;
    Account update(Account account) throws DAOException;
    boolean find(Account account) throws DAOException;
    boolean findByLogin(String login) throws DAOException;
    boolean delete(Account account) throws DAOException;
    Account get(Account account) throws DAOException;
    List<T> getAll() throws DAOException;
    Account getAccountById(int id) throws  DAOException;

}
