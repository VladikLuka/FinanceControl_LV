package main.by.javatr.controller.command.impl.adminCommand;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;

public class DeleteAccount implements Command {
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
                    service.delete(account1);
                    return account1.getLogin() + " Deleted";
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return "failed";
    }
}
