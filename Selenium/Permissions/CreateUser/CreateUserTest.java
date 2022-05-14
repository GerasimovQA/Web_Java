package Permissions.CreateUser;


import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class CreateUserTest extends CreateUserLogic {
    private CreateUserAPI createUserAPI = new CreateUserAPI();

    private String Token = null;

    @Test   //9.70
    @Parameters(value = {
            "SuperAdmin",

            "Admin",

            "Spec",
    })
    public void createUserTest(String Role) {
        createUser(Role);
    }

    @Test   //9.71
    @Parameters(value = {
//            В своем подразделении
            "mjsdfkay61| 6simbo | alvbfg1@ml.ru | +1 | active| Апос | Игорь | Апосович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | ok | ",
//          В чужом подразделении
            "mj2325361| 645mbo | al1bnv1@ml.ru | +11 | active| Аглос | Игорь | Аглосович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | ok | ",
//         Суперадминистратора  В чужом подразделении
            "mjscbfg61| 6simgfghbo | al191vbnbn1@ml.ru | +111 | active| Афос | Игорь | Афосович | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
    })
    public void createUserSuperadminTest(String Login, String Password, String Email,
                                         String Phone, String Status, String SecondName, String FirstName,
                                         String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                         String Post1, String Post2, String Role1, String Role2, String Response) {
        Token = createUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
        createUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
    }

    @Test   //9.72
    @Parameters(value = {
//          В своем подразделении
            "mjscvdfkay61| 6simcvbo | alvcvdffg1@ml.ru | +1111 | active | Хаос | Игорь | Хаосович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | | ok",
//          В чужом подразделении
            "mj2325fdf361| 645mdfdbo | al1dfd1@ml.ru | +11111 | active | Хеос | Игорь | Хеосович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | E_ACCESS",
//         Суперадминистратора  В чужом подразделении
            "mfdsf61| 6simgfdo | al191vbndfdn1@ml.ru | +111111 | active| Хиос | Игорь | Хиосович | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | E_ACCESS",
    })
    public void createUserAdminTest(String Login, String Password, String Email,
                                    String Phone, String Status, String SecondName, String FirstName,
                                    String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                    String Post1, String Post2, String Role1, String Role2, String Response) {
        Token = createUserAPI.loginAPI(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A, "ok");
        createUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
    }

    @Test   //9.73
    @Parameters(value = {
//          В своем подразделении
            "mjkjy61| 6simmnm | alvg1@ml.ru | +1111111 | active | Наус | Игорь | Наусович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | | E_ACCESS",
//          В чужом подразделении
            "mjdf3461| 645mdfbo | asaddd1@ml.ru | +11111111 | active | Ноус | Игорь | Ноусович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | E_ACCESS",
//         Суперадминистратора  В чужом подразделении
            "mfdsds1| 6sfdfdo | al191vccddfn1@ml.ru | +111111111 | active | Неус | Игорь | Неусович | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | E_ACCESS",
    })
    public void createUserSpecTest(String Login, String Password, String Email,
                                   String Phone, String Status, String SecondName, String FirstName,
                                   String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                   String Post1, String Post2, String Role1, String Role2, String Response) {
        Token = createUserAPI.loginAPI(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S, "ok");
        createUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
    }
}




