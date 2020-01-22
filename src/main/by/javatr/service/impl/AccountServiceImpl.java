package main.by.javatr.service.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.dao.DAOFactory.DAOFactory;
import main.by.javatr.dao.impl.FileAccountDAO;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import org.apache.log4j.Logger;


public class AccountServiceImpl implements AccountService {

    private static Logger log = Logger.getLogger(AccountServiceImpl.class.getName());

    private static FileAccountDAO getFileAccountDAO(){

        log.info("Service layer getFileAccountDAO");
        DAOFactory fileDAOFactory = DAOFactory.getDAOFactory(DAOFactory.FILE);
        return (FileAccountDAO) fileDAOFactory.getAccountDAO();
    }

    @Override
    public boolean checkRegistration(Account account) throws ServiceException {

        log.info("Service layer checkRegistration");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();

        boolean b = false;
        try {
            b = fileAccountDAO.findByLogin(account.getLogin());
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
        return b;
    }

    @Override
    public boolean registration(Account account) throws ServiceException {
        log.info("Service layer registration");

        try {
            boolean check = checkRegistration(account);
            if (!check) {

                FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();

                fileAccountDAO.add(account);
            }else return false;

        }catch (DAOException e){
            throw new ServiceException("DAOException",e);
        }

        return true;
    }

    @Override
    public Account logIn(Account account) throws ServiceException {
        log.info("Service layer logIn");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();

        try {
            account = fileAccountDAO.find(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }

        return account;
    }

    @Override
    public Account changeBalance(Account account) throws ServiceException {
        log.info("Service layer changeBalance");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();
        try {
            account = fileAccountDAO.update(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }

        return account;
    }

    @Override
    public Account changeCategory(Account account) throws ServiceException {
        log.info("Service layer changeCategory");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();
        try {

            account.setExpenses(account.getFood() + account.getTransport() + account.getEntertainment() + account.getOther());

            account = fileAccountDAO.update(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException",e);
        }


        return account;
    }

    public String isBanned(Account account) throws ServiceException {
        log.info("Service layer isBanned");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();

        try {
            fileAccountDAO.get(account);
            if(account.isBan()){
                return "You are banned";
            }

        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }

        return "";
    }

    @Override
    public Account update(Account account) throws ServiceException {
        log.info("Service layer update");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();

        try {
            fileAccountDAO.update(account);
        } catch (DAOException e) {
            throw new ServiceException("DAO Exception", e);
        }
        return account;
    }

    @Override
    public boolean delete(Account account) throws ServiceException {
        log.info("Service layer delete");

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();
        try {
            fileAccountDAO.delete(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
        return true;
    }
}
