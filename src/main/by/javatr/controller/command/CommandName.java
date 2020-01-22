package main.by.javatr.controller.command;

import main.by.javatr.controller.command.impl.ClearAll;

public enum CommandName {
    LOGIN,
    REGISTRATION,
    WRONG_REQUEST,
    CHANGE_BALANCE,
    CHANGE_FOOD,
    CHANGE_EXPENSES,
    CHANGE_TRANSPORT,
    CHANGE_ENTERTAINMENT,
    CHANGE_OTHER,
    CLEAR_CATEGORY,
    CLEAR_ALL,
    LOGOUT,
    BAN,
    DELETE_ACCOUNT,
    CHANGE_CURRENCY,
    GET_ADMIN;
}
