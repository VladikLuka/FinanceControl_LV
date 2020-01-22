package main.by.javatr.controller.command.impl;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;

public class LogOut implements Command {
    @Override
    public String execute(String request){

        String[] str = request.split(" ");

        if(str.length != 1) return "wrong request";

        Account.delInstance();

        return "";
    }
}
