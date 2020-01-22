package main.by.javatr.service.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.dao.DAOFactory.DAOFactory;
import main.by.javatr.dao.impl.FileAccountDAO;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;


public class AccountServiceImpl implements AccountService {


    private static FileAccountDAO getFileAccountDAO(){
        DAOFactory fileDAOFactory = DAOFactory.getDAOFactory(DAOFactory.FILE);
        return (FileAccountDAO) fileDAOFactory.getAccountDAO();
    }

    @Override
    public boolean checkRegistration(Account account) throws ServiceException {

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

        FileAccountDAO fileAccountDAO = AccountServiceImpl.getFileAccountDAO();
        try {
            fileAccountDAO.delete(account);
        } catch (DAOException e) {
            throw new ServiceException("DAOException", e);
        }
        return true;
    }
}
