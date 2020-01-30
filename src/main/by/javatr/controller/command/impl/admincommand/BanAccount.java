package main.by.javatr.controller.command.impl.admincommand;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.exception.ControllerException;
import main.by.javatr.service.AccountService;
import main.by.javatr.service.exception.ServiceException;
import main.by.javatr.service.impl.AccountServiceImpl;
import org.apache.log4j.Logger;

public class BanAccount implements Command {

    private static Logger log = Logger.getLogger(BanAccount.class.getName());

    @Override
    public String execute(String request) throws ControllerException {

        log.info("Controller layer execute");

        Session session = Session.getInstance();
        Account account = session.getAccount();


        if(!session.isLogin()) return "wrong request";

        if(account.isAdmin()){

            String[] str = request.split(" ");

            if(str.length != 2){
                return "wrong request";
            }

            Account account1 = new Account();

            account1.setLogin(str[1]);

            AccountService service = new AccountServiceImpl();
            try {
                if (service.checkRegistration(account1)){
                    account1 = service.getAccountByLogin(account1);
                    if(account1.isBan()) {
                        account1.setBan(false);
                        service.update(account1);
                        return new String(account1.getLogin() + " Unbanned");
                    }else {
                        account1.setBan(true);
                        service.update(account1);
                        return new String(account1.getLogin() + " Banned");
                    }
                }
            } catch (ServiceException e) {
                throw new ControllerException("ServiceException", e);
            }
        }

        return new String("failed");
    }
}
