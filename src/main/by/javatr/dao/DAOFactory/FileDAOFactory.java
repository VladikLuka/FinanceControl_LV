package main.by.javatr.dao.DAOFactory;

import main.by.javatr.dao.AccountDAO;
import main.by.javatr.dao.impl.FileAccountDAO;

public class FileDAOFactory extends DAOFactory {



    @Override
    public AccountDAO getAccountDAO() {

        return new FileAccountDAO();
    }
}
