package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

public class ChangeBalance implements Command {


    @Override
    public String execute(String request) throws ServiceException {

        String response;
        String[] str = request.split(" ");

        if(str.length != 2) return "wrong request";

        if(Account.getAccount() == null) return "wrong request";

        if(!Account.getAccount().isBan()) {

            Account account = Account.getInstance();

            //Account account = new Account();

            account.setBalance(account.getBalance() + Double.parseDouble(str[1]));

            AccountService accountService = new AccountServiceImpl();
            accountService.changeBalance(account);
            return "Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur();

        }
    return "wrong request";
    }
}
