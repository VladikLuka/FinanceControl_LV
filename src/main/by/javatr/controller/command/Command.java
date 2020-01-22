package main.by.javatr.controller.command;

import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.service.ServiceException.ServiceException;

import java.io.IOException;

public interface Command {

    public String execute(String request) throws ControllerException, ServiceException;

}
