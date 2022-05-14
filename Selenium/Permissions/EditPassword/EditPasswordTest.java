package Permissions.EditPassword;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditPasswordTest extends EditPasswordLogic {
    private EditPasswordAPI editPasswordAPI = new EditPasswordAPI();

    private String Token = null;
    private String UserID = null;
    private String UserID2 = null;

    @Test   //9.1
    @Parameters(value = {
            " SuperAdmin2 | Vmm65sc98f | ippolitunov@gmail.com | + 4 (051) 498-83-00 | active | Унов | Ипполит | " +
                    "Матвеевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            " Admin2 | V2mm38f3 | test5@sech.lionsdigital.pro | + 4 (031) 498-33-11 | active | Ухов | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            " Spec2 | Vmmkl98f5 | test6@sech.lionsdigital.pro | + 4 (055) 498-83-00 | active | Улов | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfMyselfTest(String LoginIN, String PasswordIN, String EmailIN, String PhoneIN,
                                         String StatusIN, String SecondNameIN, String FirstNameIN, String MiddleNameIN,
                                         String SuperuserIN, String SendEmailIN, String OrgIDIN,
                                         String DepartIN, String OrgStatusIN, String Post1IN, String Post2IN, String Role1IN,
                                         String Role2IN,

                                         String NewPasswordIN) {
        cleanGoogle(LoginIN, PasswordIN, EmailIN);
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginIN, PasswordIN, EmailIN, PhoneIN, StatusIN, SecondNameIN, FirstNameIN, MiddleNameIN, SuperuserIN, SendEmailIN, OrgIDIN, OrgStatusIN, Post1IN, Post2IN, Role1IN, Role2IN);
        editPasswordMyself(SecondNameIN, FirstNameIN, MiddleNameIN, LoginIN, PasswordIN, NewPasswordIN);
        findLetterGoogleMail(LoginIN, PasswordIN, EmailIN, NewPasswordIN);
    }

    @Test   //9.2
    @Parameters(value = {
            " Admin3 | 1231we98f | asdanklnl8978@yandex.ru | + 4 (031) 111-33-11 | active | Бодров | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin |",

    })
    public void recoveryPasswordOfMyselfTest(String LoginIN, String PasswordIN, String EmailIN, String PhoneIN,
                                             String StatusIN, String SecondNameIN, String FirstNameIN, String MiddleNameIN,
                                             String SuperuserIN, String SendEmailIN, String OrgIDIN,
                                             String DepartIN, String OrgStatusIN, String Post1IN, String Post2IN, String Role1IN,
                                             String Role2IN) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginIN, PasswordIN, EmailIN, PhoneIN, StatusIN, SecondNameIN, FirstNameIN, MiddleNameIN, SuperuserIN, SendEmailIN, OrgIDIN, OrgStatusIN, Post1IN, Post2IN, Role1IN, Role2IN);
        cleanYandex(LoginIN, PasswordIN, EmailIN);
        recoveryPasswordMyself(SecondNameIN, FirstNameIN, MiddleNameIN, LoginIN, PasswordIN, EmailIN);
        findLetterYandex(LoginIN, PasswordIN, EmailIN, SecondNameIN, FirstNameIN);

    }

    @Test   //9.3
    @Parameters(value = {
            "SuperAdmin |" +

                    " SuperAdmin6 | V666kl98f | 0sdf666dkj@mail.ru | + 4 (666) 498-83-00 | active | Уколов | Ипполит | " +
                    "Матвеевич | true | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin7 | V2777mm38f3 | test10@sech.lionsdigital.pro | + 4 (777) 498-33-11 | active | Уколин |" +
                    " Ипполит | Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М." +
                    " Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Spec8888 | Vmm898f5 | test11@sech.lionsdigital.pro | + 4 (888) 888-83-00 | active | Укоров |" +
                    " Игорь | Иванович | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М." +
                    " Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfSuperAdminTest(String RoleIn,

                                             String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                             String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                             String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                             String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                             String Role1OBJ, String Role2OBJ,

                                             String NewPasswordOBJ) {
        cleanMail(LoginOBJ, PasswordOBJ, EmailOBJ);
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfSuperAdmin(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ, UserID);
        findLetterMailRu(LoginOBJ, NewPasswordOBJ, EmailOBJ, SecondNameOBJ, FirstNameOBJ);
    }

    @Test   //9.4
    @Parameters(value = {
            "SuperAdmin |" +

                    " Admin9 | V99998f | test12@sech.lionsdigital.pro | + 4 (999) 498-83-00 | active | Ландышев | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin10 | V21010mm38f3 | test13@sech.lionsdigital.pro | + 4 (101) 498-33-11 | active | Луков | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Admin11 | Vmm111l98f5 | test14@sech.lionsdigital.pro | + 4 (123) 498-83-00 | active | Лютиков | Игорь | " +
                    "Иванович | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfAdminMyselfOrgTest(String RoleIn,

                                                 String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                 String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                 String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                 String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                 String Role1OBJ, String Role2OBJ,

                                                 String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfAdmin(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ, UserID);
    }

    @Test   //9.5
    @Parameters(value = {
            "SuperAdmin |" +

                    " Admin12 | V991212f | test15@sech.lionsdigital.pro | + 4 (123) 498-54-00 | active | Кранов | Ипполит | " +
                    "Матвеевич | false | false | 5be2dbaf371a1c6b17fbe6ee | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin13 | V21313m38f3 | test16@sech.lionsdigital.pro | + 4 (131) 498-33-11 | active | Кантов | Ипполит | " +
                    "Матвеевич | false | false | 5be2dbaf371a1c6b17fbe6ee | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Admin14 | V14148f5 | test17@sech.lionsdigital.pro | + 4 (141) 498-83-00 | active | Камнев | Игорь | " +
                    "Иванович | false | false | 5be2dbaf371a1c6b17fbe6ee | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfAdmin2LevelOrgTest(String RoleIn,

                                                 String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                 String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                 String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                 String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                 String Role1OBJ, String Role2OBJ,

                                                 String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfAdmin(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ, UserID);
    }

    @Test   //9.6
    @Parameters(value = {
            "SuperAdmin |" +

                    " Admin15 | V15152f | test18@sech.lionsdigital.pro | + 4 (123) 155-54-00 | active | Малов | Ипполит | " +
                    "Матвеевич | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin16 | V21616m38f3 | test19@sech.lionsdigital.pro | + 4 (131) 166-33-11 | active | Маслов | Ипполит | " +
                    "Матвеевич | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Admin17 | V117178f5 | test20@sech.lionsdigital.pro | + 4 (141) 177-83-00 | active | Мылов | Игорь | " +
                    "Иванович | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfAdmin4LevelOrgTest(String RoleIn,

                                                 String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                 String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                 String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                 String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                 String Role1OBJ, String Role2OBJ,

                                                 String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfAdmin(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ, UserID);
    }

    @Test   //9.7
    @Parameters(value = {
            "SuperAdmin |" +

                    "SuperAdmin515 | fgdgf52f | test21@sech.lionsdigital.pro | + 1 (100) 353-98-00 | active | Крахов | Ипполит | " +
                    "Матвеевич | true | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Admin515 | gfgr2f | test22@sech.lionsdigital.pro | + 4 (123) 155-95-21 | active | Крабов | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin514 | V288o8f3 | test23@sech.lionsdigital.pro | + 4 (175) 162-33-11 | active | Кромов | Ипполит | " +
                    "Матвеевич | false | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Admin516 | lfjlkfkld52f | test24@sech.lionsdigital.pro | + 1 (143) 125-54-35 | active | Крымов | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Spec |" +

                    " Spec17 | V11dfdитv8f5 | test25@sech.lionsdigital.pro | + 3 (674) 452-83-00 | active | Крылов | Игорь | " +
                    "Иванович | false | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Admin517 | V151cbbv52f | test26@sech.lionsdigital.pro | + 4 (123) 167-54-90 | active | Крыжов | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",
    })
    public void editPasswordOfAdminHighLevelOrgTest(String RoleIN,
                                                    String LoginIN, String PasswordIN, String EmailIN, String PhoneIN,
                                                    String StatusIN, String SecondNameIN, String FirstNameIN, String MiddleNameIN,
                                                    String SuperuserIN, String SendEmailIN, String OrgIDIN,
                                                    String DepartIN, String OrgStatusIN, String Post1IN, String Post2IN,
                                                    String Role1IN, String Role2IN,

                                                    String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                    String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                    String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                    String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                    String Role1OBJ, String Role2OBJ,

                                                    String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginIN, PasswordIN, EmailIN, PhoneIN, StatusIN, SecondNameIN, FirstNameIN, MiddleNameIN, SuperuserIN, SendEmailIN, OrgIDIN, OrgStatusIN, Post1IN, Post2IN, Role1IN, Role2IN);
        UserID2 = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfUserHighLevelOrg(RoleIN, LoginIN, PasswordIN, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ,
                UserID2);
    }

    @Test   //9.8
    @Parameters(value = {
            "SuperAdmin |" +

                    "SuperAdmin516 | fgdgf52f | test27@sech.lionsdigital.pro | + 1 (100) 325-98-00 | active | Ларин | Ипполит | " +
                    "Матвеевич | true | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Spec520 | gfgr2f | test28@sech.lionsdigital.pro | + 4 (123) 155-95-55 | active | Лакин | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin521 | V288o8f3 | test29@sech.lionsdigital.pro | + 4 (175) 163-33-11 | active | Латин | Ипполит | " +
                    "Матвеевич | false | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Spec516 | lfjlkfkld52f | test30@sech.lionsdigital.pro | + 1 (143) 625-54-35 | active | Лаптин | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Spec |" +

                    " Spec171 | V11d5646f5 | test31@sech.lionsdigital.pro | + 8 (674) 452-83-00 | active | Ладов | Игорь | " +
                    "Иванович | false | false | 5c06189ea375ae38d3306b8d | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Spec517 | V151cbbv52f | test32@sech.lionsdigital.pro | + 9 (123) 167-54-90 | active | Лажов | Ипполит | " +
                    "Матвеевич | false | false | 5c061871a375ae38d3306b89 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",
    })
    public void editPasswordOfSpecHighLevelOrgTest(String RoleIN, String LoginIN, String PasswordIN, String EmailIN, String PhoneIN,
                                                   String StatusIN, String SecondNameIN, String FirstNameIN, String MiddleNameIN,
                                                   String SuperuserIN, String SendEmailIN, String OrgIDIN,
                                                   String DepartIN, String OrgStatusIN, String Post1IN, String Post2IN,
                                                   String Role1IN, String Role2IN,

                                                   String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                   String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                   String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                   String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                   String Role1OBJ, String Role2OBJ,

                                                   String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginIN, PasswordIN, EmailIN, PhoneIN, StatusIN, SecondNameIN, FirstNameIN, MiddleNameIN, SuperuserIN, SendEmailIN, OrgIDIN, OrgStatusIN, Post1IN, Post2IN, Role1IN, Role2IN);
        UserID2 = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfUserHighLevelOrg(RoleIN, LoginIN, PasswordIN, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ
                , UserID2);
    }

    @Test   //9.9
    @Parameters(value = {
            "SuperAdmin |" +

                    " Admin18 | V118182f | test33@sech.lionsdigital.pro | + 4 (123) 181-54-00 | active | Грузов | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Admin19 | V21919m38f3 | test34@sech.lionsdigital.pro | + 4 (131) 199-33-11 | active | Грунтов | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Admin20 | V20208f5 | test35@sech.lionsdigital.pro | + 4 (141) 202-83-00 | active | Грушин | Игорь | " +
                    "Иванович | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfAdminAnotherOrgTest(String RoleIn,

                                                  String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                  String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                  String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                  String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                  String Role1OBJ, String Role2OBJ,

                                                  String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfAdminAnotherOrgTest(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ,
                UserID);
    }

    @Test   //9.10
    @Parameters(value = {
            "SuperAdmin |" +

                    " Spec1 | V82121f | test36@sech.lionsdigital.pro | + 4 (212) 498-83-00 | active | Дровяной | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Spec6552 | V22222mm38f3 | test37@sech.lionsdigital.pro | + 4 (222) 498-33-11 | active | Дробяной | Ипполит | " +
                    "Матвеевич | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Spec3 | Vmm12323f5 | test38@sech.lionsdigital.pro | + 4 (232) 498-83-00 | active | Дробов | Игорь | " +
                    "Иванович | false | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",

    })
    public void editPasswordOfSpecMyselfOrgTest(String RoleIn,

                                                String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                String Role1OBJ, String Role2OBJ,

                                                String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfSpecMyselfOrgTest(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ
                , UserID);
    }

    @Test   //9.11
    @Parameters(value = {
            "SuperAdmin |" +

                    " Spec4 | V444442f | test39@sech.lionsdigital.pro | + 4 (123) 448-54-00 | active | Дубов | Ипполит | " +
                    "Матвеевич | false | false | 5bd6d09d1c7030734863e04c | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Spec5 | V21555558f3 | test40@sech.lionsdigital.pro | + 4 (551) 498-33-11 | active | Дубин | Ипполит | " +
                    "Матвеевич | false | false | 5bd6d09d1c7030734863e04c | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Spec6 | V146666f5 | test41@sech.lionsdigital.pro | + 4 (141) 498-66-00 | active | Дубровский | Игорь | " +
                    "Иванович | false | false | 5bd6d09d1c7030734863e04c | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfSpec2LevelOrgTest(String RoleIn,

                                                String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                String Role1OBJ, String Role2OBJ,

                                                String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ, Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfSpecMyselfOrgTest(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ
                , UserID);
    }

    @Test   //9.12
    @Parameters(value = {
            "SuperAdmin |" +

                    " Spec7 | V17772f | test42@sech.lionsdigital.pro | + 4 (123) 777-54-00 | active | Кашин | Ипполит | " +
                    "Матвеевич | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Spec8 | V2188838f3 | test43@sech.lionsdigital.pro | + 4 (131) 166-88-11 | active | Кашев | Ипполит | " +
                    "Матвеевич | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Spec9 | V119995 | test44@sech.lionsdigital.pro | + 4 (199) 177-83-00 | active | Кашпировский | Игорь | " +
                    "Иванович | false | false | 5be180eb5a4c8615d6881f25 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfSpec4LevelOrgTest(String RoleIn,

                                                String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                String Role1OBJ, String Role2OBJ,

                                                String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ,
                SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ,
                Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfSpecMyselfOrgTest(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ,
                UserID);
    }

    @Test   //9.13
    @Parameters(value = {
            "SuperAdmin |" +

                    " Spec10 | 10108182f | test45@sech.lionsdigital.pro | + 4 (123) 10-54-00 | active | Барин | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",

            "Admin |" +

                    " Spec11 | V211111m38f3 | test46@sech.lionsdigital.pro | + 4 (131) 111-13-11 | active | Баринов | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f4",

            "Spec |" +

                    " Spec12 | V212125 | test47@sech.lionsdigital.pro | + 4 (141) 122-83-00 | active | Барский | Игорь | " +
                    "Иванович | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f6",
    })
    public void editPasswordOfSpecAnotherOrgTest(String RoleIn,

                                                 String LoginOBJ, String PasswordOBJ, String EmailOBJ, String PhoneOBJ,
                                                 String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                 String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                 String DepartOBJ, String OrgStatusOBJ, String Post1OBJ, String Post2OBJ,
                                                 String Role1OBJ, String Role2OBJ,

                                                 String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ,
                SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ,
                Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        editPasswordOfSpecAnotherOrgTest(RoleIn, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, LoginOBJ, NewPasswordOBJ,
                UserID);
    }

    @Test   //9.14
    @Parameters(value = {
            "Admin |" +

                    " AdminSpec521 | V288o8f3 | test48@sech.lionsdigital.pro | + 4 (175) 163-23-11 | active | Лютый | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | admin | doctor |" +

                    " Spec5126 | lfjlkfkld52f | test49@sech.lionsdigital.pro | + 1 (148) 625-54-35 | active | Лукин | Ипполит | " +
                    "Матвеевич | false | false | 5be94fd31bf3332af1f646d9 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +

                    " Vmmkl98f2",
    })
    public void adminSpecEditPasswordOfSpecMyselfOrgTest(String RoleIN, String LoginIN, String PasswordIN, String EmailIN,
                                                         String PhoneIN,
                                                         String StatusIN, String SecondNameIN, String FirstNameIN,
                                                         String MiddleNameIN,
                                                         String SuperuserIN, String SendEmailIN, String OrgIDIN,
                                                         String DepartIN, String OrgStatusIN, String Post1IN,
                                                         String Post2IN,
                                                         String Role1IN, String Role2IN,

                                                         String LoginOBJ, String PasswordOBJ, String EmailOBJ,
                                                         String PhoneOBJ,
                                                         String StatusOBJ, String SecondNameOBJ, String FirstNameOBJ,
                                                         String MiddleNameOBJ,
                                                         String SuperuserOBJ, String SendEmailOBJ, String OrgIDOBJ,
                                                         String DepartOBJ, String OrgStatusOBJ, String Post1OBJ,
                                                         String Post2OBJ,
                                                         String Role1OBJ, String Role2OBJ,

                                                         String NewPasswordOBJ) {
        Token = editPasswordAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editPasswordAPI.createUserAPI(Token, LoginIN, PasswordIN, EmailIN, PhoneIN, StatusIN, SecondNameIN,
                FirstNameIN, MiddleNameIN, SuperuserIN, SendEmailIN, OrgIDIN, OrgStatusIN, Post1IN, Post2IN, Role1IN,
                Role2IN);
        UserID2 = editPasswordAPI.createUserAPI(Token, LoginOBJ, PasswordOBJ, EmailOBJ, PhoneOBJ, StatusOBJ,
                SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ, SuperuserOBJ, SendEmailOBJ, OrgIDOBJ, OrgStatusOBJ,
                Post1OBJ, Post2OBJ, Role1OBJ, Role2OBJ);
        adminSpecEditPasswordOfSpecMyselfOrg(RoleIN, LoginIN, PasswordIN, SecondNameOBJ, FirstNameOBJ, MiddleNameOBJ,
                LoginOBJ, NewPasswordOBJ, UserID2);
    }
}
