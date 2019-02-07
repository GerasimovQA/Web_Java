package System;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class SystemTest extends SystemLogic {
    private SystemAPI systemAPI = new SystemAPI();

    @Test   //99.1
    @Parameters(value = {
            "Shaa887ouoiy61| 6simbo | 45al1911@m121ail.ru | +79863028361 | active| Астралов | Антуан | Экзюпери | true |" +
                    " false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России " +
                    "(Сеченовский Университет) | active | Врач | | doctor |",
    })
    public void logoutSessionTest(String Login, String Password, String Email, String Phone, String Status,
                                  String SecondName, String FirstName, String MiddleName, String Superuser,
                                  String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                  String Post2, String Role1, String Role2) {
        String token = systemAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
        String userID = systemAPI.createUserAPI(token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        loginFront(Login, Password);
        systemAPI.logoutAllUserSessionsAPI(token, userID);
        logoutFront();
    }
}


