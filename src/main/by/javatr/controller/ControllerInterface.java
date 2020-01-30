package main.by.javatr.controller;

import main.by.javatr.controller.exception.ControllerException;

public interface ControllerInterface {

    public String executeTask(String request) throws ControllerException;

}
