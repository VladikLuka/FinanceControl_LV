package main.by.javatr.dao.factory;

import main.by.javatr.dao.AccountDAO;

public abstract class DAOFactory {

    public abstract AccountDAO getAccountDAO();

}
