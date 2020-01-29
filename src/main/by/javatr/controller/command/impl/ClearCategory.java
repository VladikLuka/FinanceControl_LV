package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import org.apache.log4j.Logger;

public class ClearCategory implements Command {

    private static Logger log = Logger.getLogger(ClearCategory.class.getName());

    @Override
    public String execute(String request){
        log.info("Controller layer execute");

        request = request.toUpperCase();

        String[] str = request.trim().split(" +");

        if(str.length != 2) return "wrong request";

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(!session.isLogin()) return "wrong request";

        if(!account.isBan()) {

            switch (str[1]) {
                case "BALANCE":
                    account.setBalance(0);
                    break;
                case "EXPENSES":
                    account.setExpenses(0);
                    break;
                case "FOOD":
                    account.setFood(0);
                    break;
                case "TRANSPORT":
                    account.setTransport(0);
                    break;
                case "ENTERTAINMENT":
                    account.setEntertainment(0);
                    break;
                case "OTHER":
                    account.setOther(0);
                    break;
                default: return  "wrong request";
            }
            return new String("Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur());

        }
    return "wrong request";
    }
}
