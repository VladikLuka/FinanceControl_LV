package main.by.javatr.controller.impl;

import main.by.javatr.controller.ControllerInterface;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.exception.ControllerException;

import org.apache.log4j.Logger;


public final class Controller implements ControllerInterface {

    private final CommandProvider provider = new CommandProvider();


    private static Logger log = Logger.getLogger(Controller.class.getName());

    public String executeTask(String request) throws ControllerException {
        log.info("Controller layer executeTask");

        String[] commandName;
        Command executeCommand;

        commandName = request.trim().split(" +");
        executeCommand = provider.getCommand(commandName[0]);


        String response = null;


        try {
            response = executeCommand.execute(request);
        } catch (ControllerException e) {
            log.error("ControllerException",e);
            throw new ControllerException("Controller Exception", e);
        }


        return response;
    }

}
