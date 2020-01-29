package main.by.javatr.view.menu;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;

public class MenuAdmin {

    public String getAdminMenu(){

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(account.isAdmin()){

        }

        return null;
    }
}
