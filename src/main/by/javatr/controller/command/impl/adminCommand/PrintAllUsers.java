package main.by.javatr.controller.command.impl.adminCommand;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintAllUsers implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        String[] str = request.trim().split(" +");

        if(str.length != 1) return "wrong request";

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if (!session.isLogin()) return "wrong request";

        if (!account.isBan()) {

            if(account.isAdmin()) {

                AccountService accountService = new AccountServiceImpl();
                List<Account> list;
                StringBuilder s = new StringBuilder("");
                try {
                    list = accountService.getAll();
                    for (Account a:
                         list) {

                        s.append(a.getLogin()).append(" ")
                                .append(a.getPassword())
                                .append(" Balance ").append(a.getBalance()).append(a.getCurrentCur()).append(" ")
                                .append(" Expenses ").append(a.getExpenses()).append(a.getCurrentCur()).append(" ")
                                .append(" Food ").append(a.getFood()).append(a.getCurrentCur()).append(" ")
                                .append(" Transport ").append(a.getTransport()).append(a.getCurrentCur()).append(" ")
                                .append(" Entertainment ").append(a.getEntertainment()).append(a.getCurrentCur()).append(" ")
                                .append(" Other ").append(a.getOther()).append(a.getCurrentCur()).append("\n");

                    }

                } catch (ServiceException e) {
                    throw new ControllerException("Service Exception", e);
                }
                return s.toString();
            }
        }
        return "wrong request";
    }
}
