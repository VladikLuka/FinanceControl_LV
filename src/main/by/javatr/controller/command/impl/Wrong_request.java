package main.by.javatr.controller.command.impl;

import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.impl.Controller;
import org.apache.log4j.Logger;

public class Wrong_request implements Command {

    private static Logger log = Logger.getLogger(Wrong_request.class.getName());

    @Override
    public String execute(String request) {
        log.info("Controller layer execute");

        return "wrong request";
    }
}
