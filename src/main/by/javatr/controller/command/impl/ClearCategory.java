package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;

public class ClearCategory implements Command {
    @Override
    public String execute(String request){

        request = request.toUpperCase();

        String[] str = request.split(" ");

        if(str.length != 2) return "wrong request";

        if(Account.getAccount() == null) return "wrong request";

        if(!Account.getAccount().isBan()) {


            Account account = Account.getAccount();

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
            }
            return "Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur();

        }
    return "wrong request";
    }
}
