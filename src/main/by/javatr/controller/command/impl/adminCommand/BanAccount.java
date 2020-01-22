package main.by.javatr.controller.command.impl.adminCommand;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

public class BanAccount implements Command {
    @Override
    public String execute(String request) {

        if(Account.isIsAdmin()){

            String[] str = request.split(" ");
            Account account = Account.getInstance();

            Account account1 = new Account();

            account1.setLogin(str[1]);

            AccountService service = new AccountServiceImpl();
            try {
                if (service.checkRegistration(account)){
                    account1 = service.logIn(account1);
                    account1.setBan(true);
                    service.update(account1);
                    return account1.getLogin() + " Banned";
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return "failed";
    }
}
