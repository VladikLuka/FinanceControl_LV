package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import org.apache.log4j.Logger;

public class LogOut implements Command {

    private static Logger log = Logger.getLogger(LogOut.class.getName());

    @Override
    public String execute(String request){
        log.info("Controller layer execute");

        String[] str = request.trim().split(" +");

        Session session = Session.getInstance();
        Account account = session.getAccount();

        if(str.length != 1) return "wrong request";

        session.delAccount();
        session.setLogin(false);

        return new String("");
    }
}
