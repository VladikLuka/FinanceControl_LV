package main.by.javatr.dao;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.DAOException.DAOException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AccountDAO {

    public boolean add(Account account) throws DAOException;
    public Account update(Account account) throws DAOException;
    public Account find(Account account) throws DAOException;
    public boolean findByLogin(String login) throws DAOException;
    public boolean delete(Account account) throws DAOException;
    public Account get(Account account) throws DAOException;

}
