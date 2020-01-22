package main.by.javatr.service.ServiceException;

public class ServiceException extends Exception {
    public ServiceException(){
        super();
    }
    public ServiceException(String message, Exception cause){
        super(message, cause);
    }
    public ServiceException(String message){
        super(message);
    }


}
