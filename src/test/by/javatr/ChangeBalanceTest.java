package test.by.javatr;

import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.command.impl.ChangeBalance;
import main.by.javatr.controller.controllerException.ControllerException;
import main.by.javatr.service.ServiceException.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeBalanceTest {

    @Test
    public void execute() throws ControllerException, ServiceException {
        Command command = new ChangeBalance();

        String actual = command.execute("change_balance 20");
        String expected = "failed";


        assertEquals(expected,actual);
    }
}