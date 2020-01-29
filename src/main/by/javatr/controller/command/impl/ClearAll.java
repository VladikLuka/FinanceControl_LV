package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.ServiceException.ServiceException;
import org.apache.log4j.Logger;

public class ClearAll implements Command {

    private static Logger log = Logger.getLogger(ClearAll.class.getName());

    @Override
    public String execute(String request) throws ControllerException{
        log.info("Controller layer execute");

        String[] str = request.trim().split(" +");

        if(str.length != 1) return "wrong request";

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(!session.isLogin()) return "wrong request";

        if(!account.isBan()) {

            account.setOther(0);
            account.setFood(0);
            account.setEntertainment(0);
            account.setTransport(0);
            account.setExpenses(0);
            account.setBalance(0);
            account.setCurrentCur('$');

            return new String("Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur());
        }
    return "wrong request";
    }
}
