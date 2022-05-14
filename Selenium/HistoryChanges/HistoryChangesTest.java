package HistoryChanges;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class HistoryChangesTest extends HistoryChangesLogic {

    @Test //4.1
    public void logLoginTest() {
        logLogin();
    }

    @Test //4.2
    public void logLogoutTest() {
        logLogout();
    }

    @Test   //4.3
    @Parameters(value = {
            "Shaaskjd9xv0oiy61| 6simbo | 45al1913erfd1@ma56il.ru | +72083343461 | active| Алиханов | Антуанет | " +
                    "Михайлович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor |",
    })
    public void logCreateTest(String Login, String Password, String Email, String Phone, String Status,
                              String SecondName, String FirstName, String MiddleName, String Superuser,
                              String SendEmail, String OrgID,
                              String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                              String Role2) {
        logCreate(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, Depart, OrgStatus, Post1, Post2, Role1, Role2);
    }

    @Test   //4.4
    @Parameters(value = {
            "26fo.gi_ni-dze14 | maillidzanov@ma2323il.ru | 0033253333     |Shaaskjda2r4fy61| bbcu9s89jkdslk | " +
                    "smdlskjiofd1@ma23il.ru | +72083179561 | active | Алиханидзе | Антуанета | Васильевна   | true | " +
                    "false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России " +
                    "(Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void logEditWorkerTest(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                                  String Email, String Phone, String Status, String SecondName, String FirstName,
                                  String MiddleName, String Superuser, String SendEmail, String OrgID,
                                  String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                  String Role2) {
        logEditWorker(NewLogin, NewEmail, NewPhone, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, Depart, OrgStatus, Post1, Post2, Role1, Role2);
    }

    @Test //4.5
    public void logNoUnknownTest() {
        logNoUnknown();
    }

    @Test //4.6
    public void logSumReportsDefoulteTest() {
        logSumReportsDefoulte();
    }

//    @Test //4.7
//    @Parameters(value = {
//            "Shaaskfsfsdfoiy61 | msnd87987uidkjs | 11111111@mai32l.ru | + 7 (111) 111-11-11 |  active | Рудь | " +
//                    "Владимир | Иванович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
//                    "Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor |",
//    })
//    public void popoverInfoBlockTest(String Login, String Password, String Email, String Phone, String Status,
//                                     String SecondName, String FirstName, String MiddleName, String Superuser,
//                                     String SendEmail, String OrgID,
//                                     String Depart, String OrgStatus, String Post1, String Post2, String Role1,
//                                     String Role2) {
//        popoverInfoBlock(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
//                SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
//    }

    @Test //4.8
    public void moreInfoBlockLoginTest() {
        moreInfoBlockLogin();
    }

    @Test //4.9
    public void moreInfoBlockLogoutTest() {
        moreInfoBlockLogout();
    }

    @Test //4.10
    @Parameters(value = {
            "Shaas3skjd90oiy61| 6simbefo | 45al191533fd1@123.ru | +7 (208) 398-28-61 | active | Алиман | Джамал | " +
                    "Пак | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | 5bd6d875dd89ae51736eca52 | 5bc0d40b5fdf7508cc91db03 | | " +
                    "Какое уж есть | qua@mai564l.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void moreInfoBlockCreateTest(String Login, String Password, String Email, String Phone, String Status,
                                        String SecondName, String FirstName, String MiddleName, String Superuser,
                                        String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                        String Post2, String Role1, String Role2, String Spec1,
                                        String Spec2, String Service1, String Service2,
                                        String Regalia, String EmailCont,
                                        String PhoneCont, String Instagram, String Vk,
                                        String Whatsapp, String Viber, String Facebook,
                                        String Other) {
        moreInfoBlockCreate(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                SendEmail, OrgID, Depart, OrgStatus,
                Post1, Post2, Role1, Role2, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
    }

    @Test //4.11
    @Parameters(value = {
            "Shaaskjd90oi531y61| 6жdjfkkmbo | 45al1913reg4fd1@m789ail.ru | +72083022461 | active | Jon | Jack | Job | " +
                    "true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава " +
                    "России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | 5bc0d40b5fdf7508cc91db03 | | Какое уж есть | qua@ma89il.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void moreInfoBlockChangeTest(String Login, String Password, String Email, String Phone, String Status,
                                        String SecondName, String FirstName, String MiddleName, String Superuser,
                                        String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                        String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                        String Service1, String Service2, String Regalia, String EmailCont,
                                        String PhoneCont, String Instagram, String Vk, String Whatsapp, String Viber,
                                        String Facebook, String Other) {
        moreInfoBlockChange(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                SendEmail, OrgID, Depart, OrgStatus, Post1, Post2, Role1, Role2, Spec1, Spec2, Service1, Service2);
    }

    @Test //4.12
    public void PopoverTest() {
        popover();
    }

    @Test //4.13
    @Parameters(value = {
            "SLghugkbdGI | 046vtdsy9.kjas3dfs-sd8k@gma89il.com | +7 (000) 090-35-81 | " +
                    "nbdf545dhk8wds | active | Кукина | Екатерина | Владимировна | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active | Врач | | doctor | ",
    })
    public void actionFilterTest(String Login, String Email, String Phone,
                                 String Password, String Status, String SecondName, String FirstName,
                                 String MiddleName, String Superuser, String SendEmail, String OrgID,
                                 String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                 String Role2) {
        String Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        actionFilter();
    }

    @Test //4.14
    public void actionFilterCalendarTest() {
        actionFilterCalendar();
    }

    @Test //4.15
    public void allReportToYearAPITest() {
        allReportToYearAPI();
    }

///////////////////////////////////////////Test-User////////////////////////////////////////////////////////////////////


    @Test //4.16
    @Parameters(value = {
            "Акулин",
    })
    public void logLoginUserTest(String SecondName) {
        logLoginUser(SecondName);
    }

    @Test //4.17
    @Parameters(value = {
            "Акулин",
    })
    public void logLogoutUserTest(String SecondName) {
        logLogoutUser(SecondName);
    }

    @Test   //4.18
    @Parameters(value = {
            "Sh547skjd90oiy61| 6simbo | 45alhet3fd1@ma89il.ru | +720830326361 | active| Кобальт | Жан | Поль | true | " +
                    "false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России " +
                    "(Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void logCreateUserTest(String Login, String Password, String Email, String Phone, String Status,
                                  String SecondName, String FirstName, String MiddleName, String Superuser,
                                  String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                  String Post2, String Role1, String Role2) {
        logCreateUser(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
    }

    @Test   //4.19
    @Parameters(value = {
            "26fo.gi_ni-dzijf4 | mail579nov@mai65l.ru | 0033230933     |Sha908sdda2r4fy61| bbcu9s898fdulk | " +
                    "smdl456ofd1@ma56il.ru | +72062479561 | active| Popov | Oleg | Dzy | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active | Врач | | doctor | ",
    })
    public void logEditWorkerUserTest(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                                      String Email, String Phone, String Status, String SecondName, String FirstName,
                                      String MiddleName, String Superuser, String SendEmail, String OrgID,
                                      String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                      String Role2) {
        logEditWorkerUser(NewLogin, NewEmail, NewPhone, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
    }

//    @Test //4.20
//    @Parameters(value = {
//            "Акулин",
//    })
//    public void logSumReportsDefoulteUserTest(String SecondName) {
//        logSumReportsDefoulteUser(SecondName);
//    }

    @Test //4.21
    @Parameters(value = {
            "Акулин",
    })
    public void logLoadMoreUserTest(String SecondName) {
        logLoadMoreUserUser(SecondName);
    }

    @Test //4.22
    @Parameters(value = {
            "Sh0686sfsdfoiy61 | msnd87923dkjs | 111231111@m23ail.ru | + 7 (123) 111-11-11 |  active | Smith | " +
                    "Al | Back | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void popoverInfoBlockUserTest(String Login, String Password, String Email, String Phone, String Status,
                                         String SecondName, String FirstName, String MiddleName, String Superuser,
                                         String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                         String Post2, String Role1, String Role2) {
        popoverInfoBlockUser(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
    }

    @Test //4.23
    @Parameters(value = {
            "Акулин",
    })
    public void moreInfoBlockLoginUserTest(String SecondName) {
        moreInfoBlockLoginUser(SecondName);
    }

    @Test //4.24
    @Parameters(value = {
            "Акулин",
    })
    public void moreInfoBlockLogoutUserTest(String SecondName) {
        moreInfoBlockLogoutUser(SecondName);
    }

    @Test //4.25
    @Parameters(value = {
            "Shaskdj990oiy61| 6simbefo | 45akjdfhkdsj533fd1@ma56il.ru | +7 (208) 343-28-61 | active | Алмаз | " +
                    "Иннокентий | " +
                    "Борисович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | 5bc0d40b5fdf7508cc91db03 | | Образование | емайлК | +7 (000) " +
                    "000-00-00 | инста | ВК | Ватцап | вайбер | Фейсбук | Др",
    })
    public void moreInfoBlockCreateUserTest(String Login, String Password, String Email, String Phone, String Status,
                                            String SecondName, String FirstName, String MiddleName, String Superuser,
                                            String SendEmail, String OrgID,
                                            String Depart, String OrgStatus, String Post1,
                                            String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                            String Service1, String Service2, String Regalia, String EmailCont,
                                            String PhoneCont,
                                            String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
                                            String Other) {
        moreInfoBlockCreateUser(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                SendEmail, OrgID, Depart, OrgStatus, Post1, Post2, Role1, Role2, Spec1, Spec2, Service1, Service2,
                Regalia, EmailCont, PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
    }

    @Test //4.26
    @Parameters(value = {
            "Shaaslkl3j431y61| 6жdjf354mbo | 45al154dsffd1@ma56il.ru | +72082542461 | active | Hook |Leonid | Игоревич|" +
                    " true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава " +
                    "России (Сеченовский Университет) | active | Врач | | doctor |",
    })
    public void moreInfoBlockChangeUserTest(String Login, String Password, String Email, String Phone, String Status,
                                            String SecondName, String FirstName, String MiddleName, String Superuser,
                                            String SendEmail, String OrgID,
                                            String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                            String Role2) {
        moreInfoBlockChangeUser(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
    }

    @Test //4.27
    @Parameters(value = {
            "Акулин",
    })
    public void PopoverUserTest(String SecondName) {
        popoverUser(SecondName);
    }

//    @Test //4.28
//    @Parameters(value = {
//            "Акулин |" +
//
//                    " SLghxcvdGI | 0424436sy9.kjas3dfs-sd8k@gm89ail.com | +7 (000) 475-35-81 | nbdf545dhk8wds |" +
//                    " active | Авангардова | Екатерина | Владимировна | true | false | 5b7d08aa068d7552d904d8be | " +
//                    "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active |" +
//                    " Врач | | doctor | ",
//    })
//    public void actionFilterUserTest(String User, String Login, String Email, String Phone,
//                                     String Password, String Status, String SecondName, String FirstName,
//                                     String MiddleName, String Superuser, String SendEmail, String OrgID,
//                                     String Depart, String OrgStatus, String Post1, String Post2, String Role1,
//                                     String Role2) {
//        EnvironmentUser.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
//                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
//        actionFilterUser(User);
//    }

    @Test //4.29
    @Parameters(value = {
            "Акулин",
    })
    public void actionFilterCalendarUserTest(String SecondName) {
        actionFilterCalendarUser(SecondName);
    }

    @Test //4.30
    public void allReportToYearAPIUserTest() {
        allReportToYearAPIUser();
    }

}