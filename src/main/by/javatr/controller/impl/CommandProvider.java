package main.by.javatr.controller.impl;

import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.command.CommandName;
import main.by.javatr.controller.command.impl.*;
import main.by.javatr.controller.command.impl.adminCommand.BanAccount;
import main.by.javatr.controller.command.impl.adminCommand.DeleteAccount;
import main.by.javatr.controller.command.impl.adminCommand.GetAdmin;

import javax.swing.text.ChangedCharSetException;
import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider(){

        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.LOGIN, new LogIn());
        repository.put(CommandName.WRONG_REQUEST, new Wrong_request());
        repository.put(CommandName.CHANGE_BALANCE, new ChangeBalance());
        repository.put(CommandName.CHANGE_FOOD, new ChangeFood());
        repository.put(CommandName.CHANGE_ENTERTAINMENT, new ChangeEntertainment());
        repository.put(CommandName.CHANGE_TRANSPORT, new ChangeTransport());
        repository.put(CommandName.CHANGE_OTHER, new ChangeOther());
        repository.put(CommandName.CLEAR_ALL, new ClearAll());
        repository.put(CommandName.CLEAR_CATEGORY, new ClearCategory());
        repository.put(CommandName.LOGOUT, new LogOut());
        repository.put(CommandName.BAN, new BanAccount());
        repository.put(CommandName.GET_ADMIN, new GetAdmin());
        repository.put(CommandName.DELETE_ACCOUNT, new DeleteAccount());
        repository.put(CommandName.CHANGE_CURRENCY, new ChangeCurrency());

    }

    public Command getCommand(String name){
        CommandName commandName = null;
        Command command = null;

        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch (NullPointerException | IllegalArgumentException e){
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;


    }


}