package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.exception.ControllerException;
import main.by.javatr.controller.validation.ControllerValidation;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.exception.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class LogIn implements Command {

    private static Logger log = Logger.getLogger(LogIn.class.getName());

    @Override
    public String execute(String request) throws ControllerException {
        log.info("Controller layer execute");

        AccountService accountService = new AccountServiceImpl();

        String[] str = request.trim().split(" +");

        if(!ControllerValidation.isLogin() && str.length == 3) {

            Session session = Session.getInstance();
            Account account = session.getAccount();

            account.setLogin(str[1]);
            account.setPassword(str[2]);

            try {
                if (accountService.checkRegistration(account)) {
                    boolean login = accountService.logIn(account);
                    if (!login) {
                        session.delAccount();
                        return new String("You are not registered");
                    } else {
                        session.setLogin(true);
                    }
                    return new String("Balance " + account.getBalance() + account.getCurrentCur() + " Expenses " + account.getExpenses() + account.getCurrentCur() + " Food " + account.getFood() + account.getCurrentCur() + " Transport " + account.getTransport() + account.getCurrentCur() + " Entertainment " + account.getEntertainment() + account.getCurrentCur() + " Other " + account.getOther() + account.getCurrentCur());
                } else session.delAccount();
            } catch (ServiceException e) {
                throw new ControllerException("ServiceException", e);
            }
        }
            return new String("Log in failed, try again");

    }
}
