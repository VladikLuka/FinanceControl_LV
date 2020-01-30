package main.by.javatr.controller.command;

import main.by.javatr.controller.exception.ControllerException;

public interface Command {

    String execute(String request) throws ControllerException;

}
