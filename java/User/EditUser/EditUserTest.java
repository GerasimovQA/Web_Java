package User.EditUser;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditUserTest extends EditUserLogic {

    private String Token = null;
    private String UserID = null;

    @Test   //3.1
    @Parameters(value = {
            "Федосеев-Шукшин | Василий | Иванович |" +

                    "Shaa887ouocviy61| 6simbo | 45al1911@dgfgf.ru | + 7 (986) 333-83-61 | active| Астралов | Антуан | " +
                    "Экзепери | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor |",

            "Smith-Cole | Jonatan | Michael | " +

                    "Sh12aafg887ouoiрорy61| 7simbol | 45al19fde1hhg1@ml.ru | + 7 (957) 382-83-61 | active| Ананасов |" +
                    " Эрдоган | Ануфриевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerFioTest(String NewSecondName, String NewFirstName, String NewMiddleName,

                                  String Login, String Password, String Email, String Phone, String Status,
                                  String SecondName, String FirstName, String MiddleName, String Superuser,
                                  String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                  String Post2, String Role1, String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerFio(SecondName, NewSecondName, NewFirstName, NewMiddleName, UserID);
    }

    @Test   //3.2
    @Parameters(value = {
            "Michele | Eva | Michael|" +

                    " Sh1290dfрорy61| 7simbol | 4g5al191h7g1@ml.ru | + 7 (906) 309-53-61 | active| Андерсен | Ганс |" +
                    " Христиан | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerFioCancelTest(String NewSecondName, String NewFirstName, String NewMiddleName, String Login,
                                        String Password, String Email, String Phone, String Status, String SecondName,
                                        String FirstName, String MiddleName, String Superuser, String SendEmail, String OrgID,
                                        String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                        String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerFioCancel(SecondName, NewSecondName, NewFirstName, NewMiddleName, UserID);
    }

    @Test   //3.3
    @Parameters(value = {
            "1mh34dfd1290dfроfg61| 7simbol | 4g5al1vn5431h7g1@ml.ru | +0 (006) 364-85-31 | active| Апельсинкина | " +
                    "Жанна | Хасановна | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerFioDeleteMiddlenameTest(String Login, String Password, String Email, String Phone,
                                                  String Status,
                                                  String SecondName, String FirstName, String MiddleName,
                                                  String Superuser, String SendEmail, String OrgID,
                                                  String Depart, String OrgStatus, String Post1, String Post2,
                                                  String Role1,
                                                  String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerFioDeleteMiddlename(SecondName, UserID);
    }

    @Test   //3.4
    @Parameters(value = {
            "1mh34521290dfрорy61| 7simbol | 4g5al194231h7g1@ml.ru | + 0 (006) 309-53-61 | active| Антонов | Ганс | " +
                    "Христиан | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerFioDeleteMiddlenameCancelTest(String Login, String Password, String Email, String Phone,
                                                        String Status, String SecondName, String FirstName,
                                                        String MiddleName, String Superuser, String SendEmail,
                                                        String OrgID,
                                                        String Depart, String OrgStatus, String Post1, String Post2,
                                                        String Role1,
                                                        String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerFioDeleteMiddlenameCancel(SecondName, UserID);
    }

    @Test   //3.7
    @Parameters(value = {
            "2ko.gi_ni-dze14 | maillidze14@ml.ru | 00333364333 |" +

                    " S9jK9rmay61 | 6simbo | 09JKHyhl1911@li.pro  | + 5 (986) 264-83-00 | active | Антонова | Анна |" +
                    " Григорьевна | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerLoginEmailPhonelTest(String NewLogin, String NewEmail, String NewPhone, String Login,
                                               String Password, String Email, String Phone, String Status,
                                               String SecondName, String FirstName, String MiddleName,
                                               String Superuser, String SendEmail, String OrgID,
                                               String Depart, String OrgStatus, String Post1, String Post2,
                                               String Role1,
                                               String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerLoginEmailPhone(SecondName, NewLogin, NewEmail, NewPhone, UserID);
    }

    @Test   //3.8
    @Parameters(value = {
            "3lo.gi_ni-dze1jh4 | ma56illidze1hk4@ma.ru | + 8 (003) 345-33-33 | SLKJ98sdi61 | 6simbo | 09JKj09JKh@l.pro  |" +
                    " 9329548300 | active | Понтонова | Анна | Григорьевна | true | false | 5b7d08aa068d7552d904d8be " +
                    "| ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | " +
                    "Врач | | doctor | ",
    })
    public void editWorkerLoginEmailPhoneCancelTest(String NewLogin, String NewEmail, String NewPhone, String Login,
                                                    String Password, String Email, String Phone, String Status,
                                                    String SecondName, String FirstName, String MiddleName,
                                                    String Superuser, String SendEmail, String OrgID,
                                                    String Depart, String OrgStatus, String Post1, String Post2,
                                                    String Role1,
                                                    String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerLoginEmailPhoneCancel(SecondName, NewLogin, NewEmail, NewPhone, UserID);
    }

    @Test   //3.9
    @Parameters(value = {
            "6simbo |" +

                    " SLbnbdO8sdi61 | Vmmkl98f | 0lkj@il.com | + 2 (931) 498-83-00 | active | Андреева | Жанна | " +
                    "Хасановна | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",

            "7simbol | " +

                    "SLbskdj61 | Vmm2DS8f | 0lk56fFfd@gm.com | + 8 (937) 358-83-00 | active | Банан | Ирина |" +
                    " Владимировна | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова" +
                    " Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerPasswordTest(String NewPassword, String Login, String Password, String Email, String Phone,
                                       String Status, String SecondName, String FirstName, String MiddleName,
                                       String Superuser, String SendEmail, String OrgID,
                                       String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                       String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerPassword(SecondName, Login, NewPassword, UserID);
    }

    @Test   //3.10
    @Parameters(value = {
            "4si8m63 |" +

                    " SLbskng | VlM09S8f | 0lk5zxczz@gl.com | + 4 (933) 685-30-01 | active | Аннушкина | Анна | " +
                    "Анатольевна | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerPasswordCancelTest(String NewPassword, String Login, String OldPassword, String Email,
                                             String Phone, String Status, String SecondName, String FirstName,
                                             String MiddleName, String Superuser, String SendEmail, String OrgID,
                                             String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                             String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, OldPassword, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerPasswordCancel(SecondName, Login, OldPassword, NewPassword, UserID);
    }

    @Test   //3.11
    @Parameters(value = {
            "5nju8mbo |" +

                    " SLbskng7897 | Vlsd678mnS8f | 0lkczz@il.com | + 6 (900) 085-30-01 | active | Калныш | " +
                    "Ивар | | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова " +
                    "Минздрава России (Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerPasswordShowPasswordTest(String NewPassword, String Login, String OldPassword, String Email
            , String Phone, String Status, String SecondName, String FirstName, String MiddleName, String Superuser,
                                                   String SendEmail, String OrgID,
                                                   String Depart, String OrgStatus, String Post1, String Post2,
                                                   String Role1,
                                                   String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, OldPassword, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerPasswordShowPassword(SecondName, NewPassword, UserID);
    }

    @Test   //3.12
    @Parameters(value = {
            "8pyb8ju9I | niu987HKJs | 0l999Uhz@il.com | + 4 (900) 000-00-01 | active | Десперадова | Ирина | | true | false" +
                    " | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России " +
                    "(Сеченовский Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerStatusTest(String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser,
                                     String SendEmail, String OrgID,
                                     String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                     String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerStatus(SecondName, UserID);
    }

    @Test   //3.13
    @Parameters(value = {
            "9dLnIOI7hkjBJKHKHJHGHJVnLKJJKNBHJHGI | nbkjsduYYCGHFDhjsdawwds | 0nnklnlkahssday9" +
                    ".kjasdhia8hkjs-sdhk@gl.com | +8 (900) 002-58-01 | active | Bob | De | Niro | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active | Врач | | doctor | ",
    })
    public void editWorkerStatusCancelTest(String Login, String Password, String Email, String Phone, String Status,
                                           String SecondName, String FirstName, String MiddleName, String Superuser,
                                           String SendEmail, String OrgID,
                                           String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                                           String Role2) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editWorkerStatusCancel(SecondName, UserID);
    }

    @Test   //3.14
    @Parameters(value = {
            "10cLnIOI789dhfkjh9KNBHJHGI | nbk8s7duhkCGHFDhjsdawwds | 0nnkjsd908sssday9.kjasdhhkjs-sdhk@il.com | " +
                    "+ 2 (900) 001-26-81 | active | Говорунов | Игорь | Васильевич | true | false | 5b7d08aa068d7552d904d8be |" +
                    " ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | " +
                    "Врач | | doctor | | medicina_1696.jpg |2002.png",
    })
    public void editWorkerPhotoTest(String Login, String Password, String Email, String Phone, String Status,
                                    String SecondName, String FirstName, String MiddleName, String Superuser,
                                    String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                    String Post2, String Role1, String Role2, String Photo, String NewPhoto) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        EditUserAPI.uploadPhotoUserAPI(Token, UserID, Photo);
        editWorkerPhoto(SecondName, NewPhoto, UserID);
    }

    @Test   //3.15
    @Parameters(value = {
            "11vLnIhskdjs8GI | nbk8ssdkjdhkCjvkhj78wds | 0nnk3439dsy9.kjas34hdfs-sd8k@il.com | + 2 (902) 097-26-81 | active" +
                    " | Мастеров | Михаил | Григорьевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый " +
                    "МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active | Врач | | doctor | |" +
                    " medicina_1696.jpg |2002.png",
    })
    public void editWorkerPhotoCancelTest(String Login, String Password, String Email, String Phone, String Status,
                                          String SecondName, String FirstName, String MiddleName, String Superuser,
                                          String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                          String Post2, String Role1, String Role2, String Photo, String NewPhoto) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.uploadPhotoUserAPI(Token, UserID, Photo);
        editWorkerPhotoCancel(SecondName, NewPhoto, UserID);
    }

    @Test   //3.16
    @Parameters(value = {
            "Высшее медицинское |" +

                    "1SLnlkjfdGI | 1nbdf545dhkCjvkhj78wds | 104654139dsy9.kjas34hdfs-sd8k@gl.com | " +
                    "+ 3 (901) 111-67-81 | active | Букина | Василиса | | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ " +
                    "ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | " +
                    "doctor | | 5bc0a53043aef1053d0ada91 | | 5bc0d40b5fdf7508cc91db03 | | Какое уж есть | qua@ma.ru" +
                    " | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerEducationTest(String NewEducation, String Login, String Email, String Phone,
                                        String Password, String Status, String SecondName, String FirstName,
                                        String MiddleName, String Superuser, String SendEmail, String OrgID,
                                        String Depart, String OrgStatus, String Post1,
                                        String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                        String Service1, String Service2, String Regalia, String EmailCont,
                                        String PhoneCont, String Instagram, String Vk, String Whatsapp, String Viber,
                                        String Facebook, String Other) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerEducation(SecondName, NewEducation, UserID);
    }

    @Test   //3.17
    @Parameters(value = {
            "Среднее специальное | Sl69kxbdfdGI | 04654asv3dfs-sd8k@gl.com | +7 (000) 990-35-81 | " +
                    "nbdf545dhk8wds | active | Кукина | Екатерина | Владимировна | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active| Врач | | doctor | | 5bc0a53043aef1053d0ada91 | 5bc0a51d43aef1053d0ada8f |" +
                    " 5bc0d40b5fdf7508cc91db03" +
                    " || Какое уж есть | qua@ma.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerEducationCancelTest(String NewEducation, String Login, String Email, String Phone,
                                              String Password, String Status, String SecondName, String FirstName,
                                              String MiddleName, String Superuser, String SendEmail, String OrgID,
                                              String Depart, String OrgStatus, String Post1,
                                              String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                              String Service1, String Service2, String Regalia,
                                              String EmailCont, String PhoneCont, String Instagram, String Vk,
                                              String Whatsapp, String Viber, String Facebook, String Other) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerEducationCancel(SecondName, NewEducation, UserID);
    }

    @Test   //3.18
    @Parameters(value = {
            "12al666@ma.ru | 23657865565 | http://instagram2.com | http://Vk2/.ru | + 9 (951) 753-21-89 | 12345678910 | " +
                    "http://Facebook2.com | Good-Ква%821_ | SLnlkfjfdGI | nbdf545dhk8wds | 04654dsy9" +
                    ".kjas3dfs-sd8k@gil.com | 9020903581 | active | Доверчивая | Мария | Егоровна | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active| Врач | | doctor | | 5bc0a53043aef1053d0ada91 | | 5bc0d40b5fdf7508cc91db03" +
                    " | | Какое уж есть | qua@ml.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerCommunicationMethodsProfileTest(String NewEmailContact, String NewPhoneContact,
                                                          String NewInstagram, String NewVk, String NewWhatsapp,
                                                          String NewViber, String NewFacebook, String NewOther,
                                                          String Login, String Password, String Email, String Phone,
                                                          String Status, String SecondName, String FirstName,
                                                          String MiddleName, String Superuser, String SendEmail,
                                                          String OrgID, String Depart, String OrgStatus, String Post1,
                                                          String Post2, String Role1, String Role2, String Spec1,
                                                          String Spec2, String Service1, String Service2,
                                                          String Regalia, String EmailCont,
                                                          String PhoneCont, String Instagram, String Vk,
                                                          String Whatsapp, String Viber, String Facebook,
                                                          String Other) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerCommunicationMethodsProfile(SecondName, NewEmailContact, NewPhoneContact, NewInstagram, NewVk,
                NewWhatsapp, NewViber, NewFacebook, NewOther, UserID);
    }

    @Test   //3.19
    @Parameters(value = {
            "13al666@ml.ru | +7 (236) 578-65-65 | http://instagram2.com | http://Vk2/.ru | + 1 (951) 753-21-89 | " +
                    "12345678910| http://Facebook2.com | Good-Ква%821_| " +

                    "SLnjdsafdGI | nbdf54590d8fidwds | 046dsy9" +
                    ".kjasfs-sdk@gml.com | +7 (902) 090-35-81 | active | Щукина | Вера | Павловна | true | false | " +
                    "5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | active| Врач | | doctor | | 5bc0a53043aef1053d0ada91 | | 5bc0d40b5fdf7508cc91db03" +
                    " | | Какое уж есть | qua@ml.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerEditCommunicationMethodsProfileCancelTest(String NewEmailContact, String NewPhoneContact,
                                                                    String NewInstagram, String NewVk,
                                                                    String NewWhatsapp, String NewViber,
                                                                    String NewFacebook, String NewOther, String Login,
                                                                    String Password, String Email, String Phone,
                                                                    String Status, String SecondName,
                                                                    String FirstName,
                                                                    String MiddleName, String Superuser,
                                                                    String SendEmail, String OrgID, String Depart,
                                                                    String OrgStatus, String Post1,
                                                                    String Post2, String Role1, String Role2,
                                                                    String Spec1,
                                                                    String Spec2, String Service1, String Service2,
                                                                    String Regalia, String EmailCont,
                                                                    String PhoneCont, String Instagram, String Vk,
                                                                    String Whatsapp, String Viber, String Facebook,
                                                                    String Other) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerCommunicationMethodsProfileCancel(SecondName, NewEmailContact, NewPhoneContact, NewInstagram, NewVk
                , NewWhatsapp, NewViber, NewFacebook, NewOther, UserID);
    }

    @Test   //3.20
    @Parameters(value = {
            "Доктор | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | " +

                    "87IjkSLnjdsafdGI | nb4df54590ddwds | 046.kjasfdk@gl.com | +7 (147) 090-35-81 | active | " +
                    "Мистеров | Анатолий | Сергеевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ" +
                    " им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | admin | | " +
                    "5bc0a53043aef1053d0ada91 | 5bc44ce943aef1053d0adabc | 5bc0d40b5fdf7508cc91db03 | | Какое уж есть |" +
                    " qua@ml.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",

            "Лекарь | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | " +

                    "87IsafdGI | nb0ddwds | 0467.kja56fdk@gl.com | +7 (257) 090-35-81 | active | " +
                    "Цаплин | Марис | Торезович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ" +
                    " им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | Коновал | admin | doctor | " +
                    "5c04c681cfd8255eba908a35 | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerWorkplaces(String NewPost, String NewDepart, String Login, String Password, String Email,
                                     String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser,
                                     String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                     String Post2, String Role1, String Role2, String Spec1,
                                     String Spec2, String Service1, String Service2,
                                     String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                                     String Whatsapp, String Viber, String Facebook, String Other) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerWorkplaces(SecondName, NewPost, NewDepart, Post1, UserID);
    }

    @Test   //3.21
    @Parameters(value = {
            "1.sjfdjkhlk | nb4dopsdfpoi9dwds | 046.kjadfedk@lionsdigital.pro  | +7 (147) 157-35-81 | active | " +
                    "Кардашьян | Иван |  | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    "el-checkbox__input is-indeterminate | el-checkbox__input",

            "2.njdsafdGI | gfdgf0ddwds | ап4asfdk@gl.com | +7 (147) 090-78-81 | active | " +
                    "Киров | Олег | | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    "el-checkbox__input is-indeterminate | el-checkbox__input",

            "3. 87IjkSLnjdsafdGI | nb4dmn0ddwds | 046.kjavbfdk@gl.com | +7 (123) 090-35-81 | active | " +
                    "Ковалев | Андрей | Михайлович | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ " +
                    "им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    "el-checkbox__input is-indeterminate | el-checkbox__input",

            "4.87IjkSLnjdsafdGI | nb4dfzsddss | 046.kj435k@gml.com | +7 (147) 090-35-32 | active | " +
                    "Кондртьев | Иван | Андреевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им" +
                    ". И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    "el-checkbox__input is-indeterminate | el-checkbox__input",

            "5.87IjkSLnjdsafdGI | nb486fgddwds | 046.krg34dk@gl.com | +7 (147) 090-35-00 | active | " +
                    "Курочкин | Игорь | Васильевич | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ " +
                    "им. И.М. Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | | " +
                    "5bc0a53043aef1053d0ada91 | | | | Какое уж есть | qua@ml.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    "el-checkbox__input is-indeterminate | el-checkbox__input",
    })
    public void editWorkerServicesTest(String Login, String Password, String Email, String Phone, String Status,
                                       String SecondName, String FirstName, String MiddleName, String Superuser,
                                       String SendEmail, String OrgID, String Depart, String OrgStatus, String Post1,
                                       String Post2, String Role1, String Role2, String Spec1,
                                       String Spec2, String Service1, String Service2,
                                       String Regalia, String EmailCont, String PhoneCont, String Instagram, String
                                               Vk,
                                       String Whatsapp, String Viber, String Facebook, String Other,
                                       String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        Token = editUserAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = editUserAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName,
                MiddleName, Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        editUserAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerServices(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                Depart, Regalia, EmailCont, PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                CheckedAndFocus, Checkeds, Indeterminate, Empty, UserID);
    }
}
