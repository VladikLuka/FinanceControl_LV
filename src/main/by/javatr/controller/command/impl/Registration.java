package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Registration implements Command {

    private static Logger log = Logger.getLogger(Registration.class.getName());

    @Override
    public String execute(String request) throws ControllerException{
        log.info("Controller layer execute");

        String[] str = request.trim().split(" +");

        if(str.length != 3) return "wrong request";


        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(str[1].matches("[^0-9_ /w][0-9a-zA-Z]{2,}"))
            account.setLogin(str[1]);
        else{
            session.delAccount();
            return "failed";
        }
        if(str[2].matches("[0-9a-zA-Z!@#$%^&*]{6,}"))
            account.setPassword(str[2]);
        else{
            session.delAccount();
            return "failed";
        }

        AccountService accountService = new AccountServiceImpl();
        try {
            if(accountService.registration(account)){
                session.setLogin(true);
                return new String("Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur());
            }else session.delAccount();
        } catch (ServiceException e) {
            throw new ControllerException("ServiceException",e);
        }

        return new String("account already registered");

    }
}
