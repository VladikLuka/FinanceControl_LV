package main.by.javatr.controller.command.impl;

import main.by.javatr.controller.command.Command;

public class Wrong_request implements Command {
    @Override
    public String execute(String request) {
        return "wrong request";
    }
}
