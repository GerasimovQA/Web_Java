package Permissions.EditUser;


import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditUserTest extends EditUserLogic {
    private EditUserAPI editUserAPI = new EditUserAPI();

    private String Token = null;
    private String UserID = null;

    @Test   //9.90
    @Parameters(value = {
//            В своем подразделении
            "MYjsdfkacby61| 6fgsimbo | avbcg1@ml.ru | +2 | active| Гусин | Игорь | Иванович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | ok | ",
//          В чужом подразделении
            "OTHERmjfgdf| 6ffffo | alv1@ml.ru | +22 | active| Гуськов | Игорь | Михайлович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | ok | ",
//         Суперадминистратора  В чужом подразделении
            "Suoermjscvxcfbfg61| 6simvo | al19@ml.ru | +222 | active| Гусев | Игорь | Игоревич | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
    })
    public void createUserSuperadminTest(String Login, String Password, String Email,
                                         String Phone, String Status, String SecondName, String FirstName,
                                         String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                         String Post1, String Post2, String Role1, String Role2, String Response) {

        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
        editUser("SuperAdmin", UserID, SecondName, FirstName, MiddleName);
    }

    @Test   //9.91
    @Parameters(value = {
//          В своем подразделении
            "MYjscvcb| 6svbvvbo | alvbvb32v1@ml.ru | +2222 | active | Свинов | Игорь | Иванович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | | ok",
//          В чужом подразделении
            "OTHERcvxcv361| 6cvcbo | acvcd1@ml.ru | +22222 | active | Свинин | Игорь | Петрович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
//         Суперадминистратора  В чужом подразделении
            "SUPERmcvc1| 6sicvcdo | al1934221@ml.ru | +222222 | active| Свиняев | Игорь | Юобевич | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
    })
    public void createUserAdminTest(String Login, String Password, String Email,
                                    String Phone, String Status, String SecondName, String FirstName,
                                    String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                    String Post1, String Post2, String Role1, String Role2, String Response) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
        editUser("Admin", UserID, SecondName, FirstName, MiddleName);
    }

    @Test   //9.92
    @Parameters(value = {
//          В своем подразделении
            "MYjbjy61| 6s444tm | alvfdgg1@ml.ru | +2222222 | active | Кони | Игорь | Александрович | false | false |" +
                    " 5b7d08aa068d7552d904d8be | active | Врач | | doctor | | ok",
//          В чужом подразделении
            "OTHERmjvdvd61| 6cvcdfdfbo | a453rt1@ml.ru | +22222222 | active | Конев | Айк | Айкович | false | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
//         Суперадминистратора  В чужом подразделении
            "SUPERfsfdfs1| 6sdffdo | al1n1@ml.ru | +222222222 | active | Коняшкин | Игорь | Игоревич | true | false |" +
                    " 5be94fd31bf3332af1f646d9 | active | Врач | | doctor | | ok",
    })
    public void createUserSpecTest(String Login, String Password, String Email,
                                   String Phone, String Status, String SecondName, String FirstName,
                                   String MiddleName, String Superuser, String SendEmail, String OrgID, String OrgStatus,
                                   String Post1, String Post2, String Role1, String Role2, String Response) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA, "ok");
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2, Response);
        editUser("Spec", UserID, SecondName, FirstName, MiddleName);
    }
}




