package main.by.javatr.view;

import main.by.javatr.bean.Session;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.controller.impl.Controller;
import main.by.javatr.controller.ControllerInterface;

import java.util.Scanner;
import org.apache.log4j.Logger;

public class Runner {

    private static Logger log = Logger.getLogger(Runner.class.getName());

    public static void main(String[] args) {
        log.info("View layer main");
        Scanner scanner;
        String request;
        ControllerInterface contr = new Controller();
        String response = null;

        Session session = Session.getInstance();

        while (true) {

            if(!session.isLogin()){

                System.out.println("LogIn or Registration");

                scanner = new Scanner(System.in);
                request = scanner.nextLine();

                try {
                    response = contr.executeTask(request);
                } catch (ControllerException e) {
                    System.out.println("wrong request");
                }

                System.out.println(response);

            }else if(session.getAccount().isBan()){
                System.out.println("You are banned\nLogout\nexit");
                scanner = new Scanner(System.in);
                request = scanner.nextLine();

                try {
                    response = contr.executeTask(request);
                } catch (ControllerException e) {
                    System.out.println("wrong request");
                }
                System.out.println(response);
            }

            else {
                if (!session.getAccount().isAdmin()) {
                    System.out.println("change_Balance\nchange_Transport\nchange_Food\nchange_Entertainment\nchange_Other\nchange_currency\nclear_All\nclear_Category\nLogout\nexit");

                    scanner = new Scanner(System.in);
                    request = scanner.nextLine();

                    try {
                        response = contr.executeTask(request);
                    } catch (ControllerException e) {
                        System.out.println("wrong request");
                    }
                    System.out.println(response);
                }
                else {
                    System.out.println("change_Balance\nchange_Transport\nchange_Food\nchange_Entertainment\nchange_Other\nchange_currency\nclear_All\nclear_Category\nget_Admin\nban\ndelete_account\nLogout\nexit");

                    scanner = new Scanner(System.in);
                    request = scanner.nextLine();

                    try {
                        response = contr.executeTask(request);
                    } catch (ControllerException e) {
                        System.out.println("wrong request");
                    }
                    System.out.println(response);
                }

            }
        }

    }

}
