package main.by.javatr.controller.command.impl.adminCommand;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.ServiceException.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class GetAdmin implements Command {

    private static Logger log = Logger.getLogger(GetAdmin.class.getName());

    @Override
    public String execute(String request){

        log.info("Controller layer execute");

        if(Session.checkAccount() == null) return "wrong request";

        Account account = Session.getAccount();

        if(account.isAdmin()){

            String[] str = request.split(" ");

            if(str.length != 2){
                return "wrong request";
            }

            AccountService service = new AccountServiceImpl();

            Account account1 = new Account();
            account1.setLogin(str[1]);


            try {
                if (service.checkRegistration(account)){
                    account1 = service.logIn(account1);
                    account1.setAdmin(true);
                    service.update(account1);
                    return account1.getLogin() + " become a admin";
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }


        }
            return "failed";
    }
}
