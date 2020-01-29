package main.by.javatr.controller.command.impl;

import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.service.ServiceException.ServiceException;

public class Exit implements Command {
    @Override
    public String execute(String request){

        if(request.trim().length() != 4){
            return "wrong request";
        }

        System.exit(0);
        return null;
    }
}
