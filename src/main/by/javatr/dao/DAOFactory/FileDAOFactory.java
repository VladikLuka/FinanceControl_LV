package main.by.javatr.dao.DAOFactory;

import main.by.javatr.dao.AccountDAO;
import main.by.javatr.dao.impl.FileAccountDAO;

import org.apache.log4j.Logger;

public class FileDAOFactory extends DAOFactory {

    private static Logger log = Logger.getLogger(FileDAOFactory.class.getName());

    @Override
    public AccountDAO getAccountDAO() {
        log.info("DAO layer getAccountDAO");
        return new FileAccountDAO();
    }
}
