package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

import java.io.IOException;

public class Registration implements Command {


    @Override
    public String execute(String request) throws ControllerException{

        String[] arrRequest = request.split(" ");

        if(arrRequest.length != 3) return "wrong request";


        Account account = Account.getInstance();

        if(arrRequest[1].matches("[0-9a-zA-Z]{3,}"))
        account.setLogin(arrRequest[1]);
        else return "failed";
        if(arrRequest[2].matches("[0-9a-zA-Z!@#$%^&*]{6,}"))
        account.setPassword(arrRequest[2]);
        else return "failed";

        AccountService accountService = new AccountServiceImpl();
        try {
            if(accountService.registration(account)){
                if (account.isAdmin()){
                    Account.setIsAdmin(true);
                }else Account.setIsAdmin(false);
                return "Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur();
            }else Account.delInstance();
        } catch (ServiceException e) {
            throw new ControllerException("ServiceException",e);
        }

        return "account already registered";

    }
}
