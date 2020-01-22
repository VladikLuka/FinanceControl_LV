package main.by.javatr.dao.DAOFactory;

import main.by.javatr.dao.AccountDAO;

public abstract class DAOFactory {

    public static final int FILE = 1;

    public abstract AccountDAO getAccountDAO();

    public static DAOFactory getDAOFactory(int whichFactory){

        switch (whichFactory){

            case FILE : return new FileDAOFactory();
            default: return null;
        }

    }
}
