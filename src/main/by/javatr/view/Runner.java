package main.by.javatr.view;

import main.by.javatr.bean.Account;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.controller.ControllerInterface;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner;
        String request;
        ControllerInterface contr = new Controller();
        String response = null;

        while (true) {

            if(Account.getAccount() == null){

                System.out.println("LogIn or Registration");

                scanner = new Scanner(System.in);
                request = scanner.nextLine();

                try {
                    response = contr.executeTask(request);
                } catch (ControllerException e) {
                    System.out.println("ControllerException");
                }


                System.out.println(response);
            }else if(Account.getInstance().isBan()){
                System.out.println("You are banned\nLogout");
                scanner = new Scanner(System.in);
                request = scanner.nextLine();

                try {
                    response = contr.executeTask(request);
                } catch (ControllerException e) {
                    System.out.println("ControllerException");
                }
            }

            else {
                if (!Account.isIsAdmin()) {
                    System.out.println("change_Balance\nchange_Transport\nchange_Food\nchange_Entertainment\nchange_Other\nchange_currency\nclear_All\nclear_Category\nLogout");

                    scanner = new Scanner(System.in);
                    request = scanner.nextLine();

                    try {
                        response = contr.executeTask(request);
                    } catch (ControllerException e) {
                        System.out.println("ControllerException");
                    }
                    System.out.println(response);
                }
                else {
                    System.out.println("change_Balance\nchange_Transport\nchange_Food\nchange_Entertainment\nchange_Other\nchange_currency\nclear_All\nclear_Category\nget_Admin\nban\ndelete_account\nLogout");

                    scanner = new Scanner(System.in);
                    request = scanner.nextLine();

                    try {
                        response = contr.executeTask(request);
                    } catch (ControllerException e) {
                        System.out.println("ControllerException");
                    }

                    System.out.println(response);
                }

            }
        }

    }

}
