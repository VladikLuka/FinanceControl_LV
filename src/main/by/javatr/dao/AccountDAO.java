package main.by.javatr.dao;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.DAOException.DAOException;

import java.util.List;

public interface AccountDAO<T> {

    public boolean add(Account account) throws DAOException;
    public Account update(Account account) throws DAOException;
    public boolean find(Account account) throws DAOException;
    public boolean findByLogin(String login) throws DAOException;
    public boolean delete(Account account) throws DAOException;
    public Account get(Account account) throws DAOException;
    public List<T> getAll() throws DAOException;

}
