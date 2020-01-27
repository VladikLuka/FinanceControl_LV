package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class ChangeBalance implements Command {

    private static Logger log = Logger.getLogger(ChangeBalance.class.getName());

    @Override
    public String execute(String request) throws ControllerException {

        log.info("Controller layer execute");

        String response;
        String[] str = request.split(" ");

        if(str.length != 2) return "wrong request";

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(!session.isLogin()) return "wrong request";


        if(!account.isBan()) {

            try {
                account.setBalance(account.getBalance() + Double.parseDouble(str[1]));
            }catch(NumberFormatException e){
                throw new ControllerException("NumericFormatException",e);
            }


            AccountService accountService = new AccountServiceImpl();
            try {
                accountService.changeBalance(account);
            } catch (ServiceException e) {
                throw new ControllerException("ServiceException",e);
            }
            return new String("Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur());

        }
    return "wrong request";
    }
}
