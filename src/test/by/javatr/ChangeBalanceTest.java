package test.by.javatr;

import main.by.javatr.bean.Session;
import main.by.javatr.controller.command.Command;
import main.by.javatr.controller.command.impl.ChangeBalance;
import main.by.javatr.controller.exception.ControllerException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeBalanceTest {

    @Test
    public void executeWithoutLogin() throws ControllerException {
        Command command = new ChangeBalance();

        String actual = command.execute("change_balance 20");
        String expected = "wrong request";


        assertEquals(expected,actual);
    }

    @Test
    public void execute() throws ControllerException {

        Session session = Session.getInstance();

        session.setLogin(true);

        Command command = new ChangeBalance();



        String actual = command.execute("change_balance 20");
        String expected = "Balance 20.0$ Expenses 0.0$ Food 0.0$ Transport 0.0$ Entertainment 0.0$ Other 0.0$";


        assertEquals(expected,actual);
    }
}