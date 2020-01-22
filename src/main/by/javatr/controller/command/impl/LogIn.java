package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

public class LogIn implements Command {

    @Override
    public String execute(String request) throws ServiceException {

        AccountService accountService = new AccountServiceImpl();
        String[] str = request.split(" ");

        if(str.length != 3) return "wrong request";

        Account account = Account.getInstance();
        account.setLogin(str[1]);
        account.setPassword(str[2]);

        if(accountService.checkRegistration(account)) {
            account = accountService.logIn(account);
            if(account.isAdmin()){
                Account.setIsAdmin(true);
            }else Account.setIsAdmin(false);
            return "Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur();
        }else Account.delInstance();

        return "Log in failed, try again";

    }
}
