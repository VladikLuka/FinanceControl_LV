package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class ChangeCurrency implements Command {

    private static Logger log = Logger.getLogger(ChangeCurrency.class.getName());

    @Override
    public String execute(String request) throws ControllerException, ServiceException {
        log.info("Controller layer execute");

        String[] str = request.split(" ");

        if(str.length != 2) return "wrong request";

        if(Account.getAccount() == null) return "wrong request";

        if(!Account.getAccount().isBan()) {


            Account account = Account.getInstance();

            if(str[1].equals("dollar")) {
                account.setCurrentCur('$');
            }else if(str[1].equals("euro")){
                account.setCurrentCur('€');
            }else if(str[1].equals("rub")){
                account.setCurrentCur('₽');
            }
            AccountService accountService = new AccountServiceImpl();
            try {
                accountService.update(account);
            } catch (ServiceException e) {
                throw new ControllerException("Service Exc", e);
            }
            return "Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur();

        }
        return "wrong request";
    }
}
