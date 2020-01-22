package main.by.javatr.controller.impl;

import main.by.javatr.controller.ControllerInterface;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.service.ServiceException.ServiceException;

import org.apache.log4j.Logger;


public final class Controller implements ControllerInterface {

    private final CommandProvider provider = new CommandProvider();


    private static Logger log = Logger.getLogger(Controller.class.getName());

    public String executeTask(String request) throws ControllerException {

        String[] commandName;
        Command executeCommand;

        commandName = request.split(" ");
        executeCommand = provider.getCommand(commandName[0]);


        String response;


        try {
            response = executeCommand.execute(request);
        } catch (ServiceException e) {
            log.error("Exception", e);
            throw new ControllerException("Service Exception",e);
        }


        return response;
    }

}