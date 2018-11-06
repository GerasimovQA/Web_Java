package system;

import global.EnvironmentSystem;
import junitparams.Parameters;
import org.junit.Test;

public class SystemTest extends SystemLogic {
    @Test   //99.1
    @Parameters(value = {
            "Sh12aa887ouoiрорy61| 7simbol | 45al191hhg1@mail.ru | +79063028361 | active| Ананасов |" +
                    " Эрдоган | Ануфриевич | true",
    })
    public void logoutSessionTest(String Login, String Password, String Email, String Phone, String Status,
                                  String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentSystem.login();
        EnvironmentSystem.logoutAllUserSessionsAPI();
        EnvironmentSystem.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        logoutFront();
    }
}


