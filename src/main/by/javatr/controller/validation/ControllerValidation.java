package main.by.javatr.controller.validation;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;

public class ControllerValidation {

     public static boolean isLogin(){

        Session session = Session.getInstance();
        return session.isLogin();
    }


    public static boolean isBan(){

        Session session = Session.getInstance();
        Account account = session.getAccount();
        return account.isBan();
    }
}
