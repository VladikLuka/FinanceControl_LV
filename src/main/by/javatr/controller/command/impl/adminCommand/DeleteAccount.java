package main.by.javatr.controller.command.impl.adminCommand;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class DeleteAccount implements Command {

    private static Logger log = Logger.getLogger(DeleteAccount.class.getName());

    @Override
    public String execute(String request) {

        log.info("Controller layer execute");

        if(Session.checkAccount() == null) return "wrong request";
        Account account = Session.getAccount();


        if(account.isAdmin()){

            String[] str = request.split(" ");

            if(str.length != 2){
                return "wrong request";
            }

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
