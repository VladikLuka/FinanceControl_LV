package main.by.javatr.controller.command.impl.admincommand;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.command.impl.LogIn;
import main.by.javatr.controller.exception.ControllerException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.exception.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class GetAccountByID implements Command {

    private static Logger log = Logger.getLogger(GetAccountByID.class.getName());

    @Override
    public String execute(String request) throws ControllerException {
        log.info("Controller layer exception");
        AccountService accountService = new AccountServiceImpl();

        String[] str = request.trim().split(" +");

        if(str.length != 2) return new String("wrong request");

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(!session.isLogin() || account.isBan()){
            session.delAccount();
            return "wrong request";
        }

        Account account1;

        try {
            account1 = accountService.getAccountById(Integer.parseInt(str[1]));

            return new String(account1.getId() + " " + account1.getLogin() + " " + account1.getPassword() + " " +"Balance " + account1.getBalance() + account1.getCurrentCur() + " Expenses " + account1.getExpenses() + account1.getCurrentCur() + " Food " + account1.getFood() + account1.getCurrentCur() + " Transport " + account1.getTransport() + account1.getCurrentCur() + " Entertainment " + account1.getEntertainment() + account1.getCurrentCur() + " Other " + account1.getOther() + account1.getCurrentCur());
        } catch (ServiceException e) {
            throw new ControllerException("ServiceException",e);
        }
    }
}
