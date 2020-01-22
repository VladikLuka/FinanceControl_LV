package main.by.javatr.controller;

import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.dao.DAOException.DAOException;
import main.by.javatr.service.ServiceException.ServiceException;

import java.io.IOException;

public interface ControllerInterface {

    public String executeTask(String request) throws ControllerException;

}
