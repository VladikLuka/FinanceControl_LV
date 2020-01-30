package main.by.javatr.controller.exception;

public class ControllerException extends Exception {

    public ControllerException(){
        super();
    }
    public ControllerException(String message, Exception cause){
        super(message, cause);
    }
    public ControllerException(String message){
        super(message);
    }

}
