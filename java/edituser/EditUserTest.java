package edituser;

import global.EnvironmentUser;
import junitparams.Parameters;
import org.junit.Test;

public class EditUserTest extends EditUserLogic {
    @Test   //3.1
    @Parameters(value = {
            "Астралов-Мастралов | Сергей | Иванович         | Shaa887ouoiy61| 6simbo | 45al1911@mail.ru | " +
                    "+79863028361 | active| Астралов | Антуан | Экзепери | true",
            "Ананасов-Smith-Cole | Jonatan | Michael         | Sh12aa887ouoiрорy61| 7simbol | 45al191hhg1@mail.ru | " +
                    "+79063028361 | active| Ананасов | Эрдоган | Ануфриевич | true",
    })
    public void editWorkerFioTest(String NewSecondName, String NewFirstName, String NewMiddleName, String Login,
                                  String Password, String Email, String Phone, String Status, String SecondName,
                                  String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerFio(SecondName, NewSecondName, NewFirstName, NewMiddleName);
    }

    @Test   //3.2
    @Parameters(value = {
            "Кабачков-Smith-Cole | Jonatan | Michael     | Sh1290dfрорy61| 7simbol | 4g5al191h7g1@mail.ru | " +
                    "+79063095361 | active| Андерсен | Ганс | Христиан | true",
    })
    public void editWorkerFioCancelTest(String NewSecondName, String NewFirstName, String NewMiddleName, String Login
            , String Password, String Email, String Phone, String Status, String SecondName, String FirstName,
                                        String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerFioCancel(SecondName, NewSecondName, NewFirstName, NewMiddleName);
    }

    @Test   //3.3
    @Parameters(value = {
            "1mh34dfd1290dfроfg61| 7simbol | 4g5al1vn5431h7g1@mail.ru | +0 (006) 364-85-31 | active| Апельсинкина | " +
                    "Жанна | Хасановна | true",
    })
    public void editWorkerFioDeleteMiddlenameTest(String Login, String Password, String Email, String Phone,
                                                  String Status,
                                                  String SecondName, String FirstName, String MiddleName,
                                                  String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerFioDeleteMiddlename(SecondName);
    }

    @Test   //3.4
    @Parameters(value = {
            "1mh34521290dfрорy61| 7simbol | 4g5al194231h7g1@mail.ru | +00063095361 | active| Антонов | Ганс | " +
                    "Христиан | true",
    })
    public void editWorkerFioDeleteMiddlenameCancelTest(String Login, String Password, String Email, String Phone,
                                                        String Status,
                                                        String SecondName, String FirstName, String MiddleName,
                                                        String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerFioDeleteMiddlenameCancel(SecondName);
    }


    @Test   //3.7
    @Parameters(value = {
            "2ko.gi_ni-dze14 | maillidze14@mail.ru | 0033333333      | S9jK9rmay61 | 6simbo | 09JKHyhl1911@gmail.com " +
                    "| 9862648300 | active | Антонова | Анна | Григорьевна | true",
    })
    public void editWorkerLoginEmailPhonelTest(String NewLogin, String NewEmail, String NewPhone, String Login,
                                               String Password, String Email, String Phone, String Status,
                                               String SecondName, String FirstName, String MiddleName,
                                               String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerLoginEmailPhone(SecondName, NewLogin, NewEmail, NewPhone);
    }

    @Test   //3.8
    @Parameters(value = {
            "3lo.gi_ni-dze1jh4 | ma56illidze1hk4@mail.ru | 0033453333        | SLKJ98sdi61 | 6simbo | " +
                    "09JKj09JKh@gmail.com | 9329548300 | active | Понтонова | Анна | Григорьевна | true",
    })
    public void editWorkerLoginEmailPhoneCancelTest(String NewLogin, String NewEmail, String NewPhone, String Login,
                                                    String Password, String Email, String Phone, String Status,
                                                    String SecondName, String FirstName, String MiddleName,
                                                    String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerLoginEmailPhoneCancel(SecondName, NewLogin, NewEmail, NewPhone);
    }

    @Test   //3.9
    @Parameters(value = {
            "6simbo         | SLbnbdO8sdi61 | Vmmkl98f | 0lkj@gmail.com | 9314988300 | active | Андреева | Жанна | " +
                    "Хасановна | true",
            "7simbol        | SLbskdj61 | Vmm2DS8f | 0lk56fFfd@gmail.com | 9373588300 | active | Бананасова | Ирина |" +
                    " Владимировна | true",
    })
    public void editWorkerPasswordTest(String NewPassword, String Login, String Password, String Email, String Phone,
                                       String Status, String SecondName, String FirstName, String MiddleName,
                                       String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerPassword(SecondName, Login, NewPassword);
    }

    @Test   //3.10
    @Parameters(value = {
            "4si8m63         | SLbskng | VlM09S8f | 0lk5zxczz@gmail.com | 9336853001 | active | Аннушкина | Анна | " +
                    "Анатольевна | true",
    })
    public void editWorkerPasswordCancelTest(String NewPassword, String Login, String OldPassword, String Email,
                                             String Phone, String Status, String SecondName, String FirstName,
                                             String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, OldPassword, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerPasswordCancel(SecondName, Login, OldPassword, NewPassword);
    }

    @Test   //3.11
    @Parameters(value = {
            "5nju8mbo       | SLbskng7897 | Vlsd678mnS8f | 0lkczz@gmail.com | 9000853001 | active | Апокалипс | " +
                    "Сатанина | | true",
    })
    public void editWorkerPasswordShowPasswordTest(String NewPassword, String Login, String OldPassword, String Email
            , String Phone, String Status, String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, OldPassword, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerPasswordShowPassword(SecondName, NewPassword);
    }

    @Test   //3.12
    @Parameters(value = {
            "8pyb8ju9I | niu987HKJs | 0l999Uhz@gmail.com | 9000000001 | active | Десперадова | Ирина | | true",
    })
    public void editWorkerStatusTest(String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerStatus(SecondName);
    }

    @Test   //3.13
    @Parameters(value = {
            "9dLnIOI7hkjBJKHKHJHGHJVnLKJJKNBHJHGI | nbkjsduYYCGHFDhjsdawwds | 0nnklnlkahssday9" +
                    ".kjasdhia8hkjs-sdhk@gmail.com | 9000025801 | active | Badun | Katun | Morgun | true",
    })
    public void editWorkerStatusCancelTest(String Login, String Password, String Email, String Phone, String Status,
                                           String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        editWorkerStatusCancel(SecondName);
    }


    @Test   //3.14
    @Parameters(value = {
            "10cLnIOI789dhfkjh9KNBHJHGI | nbk8s7duhkCGHFDhjsdawwds | 0nnkjsd908sssday9.kjasdhhkjs-sdhk@gmail.com | " +
                    "9000012681 | active | Godun | Shatun-Kachun | Polzun | true | medicina_1696.jpg | 2002.png",
    })
    public void editWorkerPhotoTest(String Login, String Password, String Email, String Phone, String Status,
                                    String SecondName, String FirstName, String MiddleName, String Superuser,
                                    String Photo, String NewPhoto) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.uploadPhotoUserAPI(Photo);
        editWorkerPhoto(SecondName, NewPhoto);
    }

    @Test   //3.15
    @Parameters(value = {
            "11vLnIhskdjs8GI | nbk8ssdkjdhkCjvkhj78wds | 0nnk3439dsy9.kjas34hdfs-sd8k@gmail.com | 9020972681 | active" +
                    " | Potun | Gorbun | Atun | true | medicina_1696.jpg | 2002.png",
    })
    public void editWorkerPhotoCancelTest(String Login, String Password, String Email, String Phone, String Status,
                                          String SecondName, String FirstName, String MiddleName, String Superuser,
                                          String Photo, String NewPhoto) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.uploadPhotoUserAPI(Photo);
        editWorkerPhotoCancel(SecondName, NewPhoto);
    }

    @Test   //3.16
    @Parameters(value = {
            "Самое Best`овое        | SLnlkjfdGI | nbdf545dhkCjvkhj78wds | 04654139dsy9.kjas34hdfs-sd8k@gmail.com | " +
                    "9020926781 | active | Букина | Бука | Буковна | true | Сеченовский университет > Больница 1 | " +
                    "Ref | Врач | Специалист| Дантист | Консультации | 0 | Консультация доцента | 3000 руб. | 1 | " +
                    "Какое уже есть | qua@mail.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerEducationTest(String NewEducation, String Login, String Email, String Phone,
                                        String Password, String Status, String SecondName, String FirstName,
                                        String MiddleName, String Superuser, String Depart, String Ref, String Post,
                                        String Role, String Specialities, String Label, String ID, String LabelChild,
                                        String Cost, String IDchild, String Regalia, String EmailCont,
                                        String PhoneCont, String Instagram, String Vk, String Whatsapp, String Viber,
                                        String Facebook, String Other) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.profileUserAPI(Depart, Ref, Post, Role, Status, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerEducation(SecondName, NewEducation);
    }

    @Test   //3.17
    @Parameters(value = {
            "Ну самое- Best`овое | SLnl69kbdGI | 04654dsy9.kjas3dfs-sd8k@gmail.com | +7 (902) 090-35-81 | " +
                    "nbdf545dhk8wds | active | Кукина | Кука | Куковна | true | Сеченовский университет > Больница 1 " +
                    "| Ref | Врач | Специалист| Дантист | Консультации | 0 | Консультация доцента | 3000 руб. | 1 | " +
                    "Какое уже есть | qua@mail.ru | +7 (954) 357-54-32 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | " +
                    "OTHER",
    })
    public void editWorkerEducationCancelTest(String NewEducation, String Login, String Email, String Phone,
                                              String Password, String Status, String SecondName, String FirstName,
                                              String MiddleName, String Superuser, String Depart, String Ref,
                                              String Post, String Role, String Specialities, String Label, String ID,
                                              String LabelChild, String Cost, String IDchild, String Regalia,
                                              String EmailCont, String PhoneCont, String Instagram, String Vk,
                                              String Whatsapp, String Viber, String Facebook, String Other) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.profileUserAPI(Depart, Ref, Post, Role, Status, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerEducationCancel(SecondName, NewEducation);
    }

    @Test   //3.18
    @Parameters(value = {
            "12al666@mail.ru | 2365786565 | http://instagram2.com | http://Vk2/.ru | 9517532189 | 12345678910 | " +
                    "http://Facebook2.com | Good-Ква%821_ | SLnlkfjfdGI | nbdf545dhk8wds | 04654dsy9" +
                    ".kjas3dfs-sd8k@gmail.com | 9020903581 | active | Фукина | Фука | Фуковна | true | Сеченовский " +
                    "университет > Больница 1 | Ref | Врач | Специалист| Дантист | Консультации | 0 | Консультация " +
                    "доцента | 3000 руб. | 1 | Какое уже есть | qua@mail.ru | 7954357543 | INSTA | VK | WATSAPP | " +
                    "VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerCommunicationMethodsProfileTest(String NewEmailContact, String NewPhoneContact,
                                                          String NewInstagram, String NewVk, String NewWhatsapp,
                                                          String NewViber, String NewFacebook, String NewOther,
                                                          String Login, String Password, String Email, String Phone,
                                                          String Status, String SecondName, String FirstName,
                                                          String MiddleName, String Superuser, String Depart,
                                                          String Ref, String Post, String Role, String Specialities,
                                                          String Label, String ID, String LabelChild, String Cost,
                                                          String IDchild, String Regalia, String EmailCont,
                                                          String PhoneCont, String Instagram, String Vk,
                                                          String Whatsapp, String Viber, String Facebook,
                                                          String Other) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.profileUserAPI(Depart, Ref, Post, Role, Status, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerCommunicationMethodsProfile(SecondName, NewEmailContact, NewPhoneContact, NewInstagram, NewVk,
                NewWhatsapp, NewViber, NewFacebook, NewOther);
    }

    @Test   //3.19
    @Parameters(value = {
            "13al666@mail.ru | +7 (236) 578-65-65 | http://instagram2.com | http://Vk2/.ru | 9517532189 | 12345678910" +
                    " | http://Facebook2.com | Good-Ква%821_    | SLnjdsafdGI | nbdf54590d8fidwds | 046dsy9" +
                    ".kjasfs-sdk@gmail.com | +7 (902) 090-35-81 | active | Щукина | Щука | Щуковна | true | " +
                    "Сеченовский университет > Больница 1 | Ref | Врач | Специалист| Дантист | Консультации | 0 | " +
                    "Консультация доцента | 3000 руб. | 1 | Какое уже есть | qua@mail.ru | 7954357543 | INSTA | VK | " +
                    "WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerEditCommunicationMethodsProfileCancelTest(String NewEmailContact, String NewPhoneContact,
                                                                    String NewInstagram, String NewVk,
                                                                    String NewWhatsapp, String NewViber,
                                                                    String NewFacebook, String NewOther, String Login,
                                                                    String Password, String Email, String Phone,
                                                                    String Status, String SecondName,
                                                                    String FirstName,
                                                                    String MiddleName, String Superuser,
                                                                    String Depart, String Ref, String Post,
                                                                    String Role, String Specialities, String Label,
                                                                    String ID, String LabelChild, String Cost,
                                                                    String IDchild, String Regalia, String EmailCont,
                                                                    String PhoneCont, String Instagram, String Vk,
                                                                    String Whatsapp, String Viber, String Facebook,
                                                                    String Other) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.profileUserAPI(Depart, Ref, Post, Status, Role, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerCommunicationMethodsProfileCancel(SecondName, NewEmailContact, NewPhoneContact, NewInstagram, NewVk
                , NewWhatsapp, NewViber, NewFacebook, NewOther);
    }

    @Test   //3.20
    @Parameters(value = {
            "Врачелло | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)       | " +
                    "87IjkSLnjdsafdGI | nb4df54590ddwds | 046.kjasfdk@gmail.com | +7 (147) 090-35-81 | active | " +
                    "Мистеров |" +
                    " Мистер | Мистерович | true | Сеченовский университет > Больница 1 | Ref | Врач | Специалист| " +
                    "Дантист" +
                    " | Консультации | 0 | Консультация доцента | 3000 руб. | 1 | Какое уже есть | qua@mail.ru | " +
                    "7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void editWorkerWorkplaces(String NewPost, String NewDepart, String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser,
                                     String Depart, String Ref, String Post, String Role, String Specialities,
                                     String Label, String ID, String LabelChild, String Cost, String IDchild,
                                     String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                                     String Whatsapp, String Viber, String Facebook, String Other) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        EnvironmentUser.profileUserAPI(Depart, Ref, Post, Status, Role, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        editWorkerWorkplaces(SecondName, NewPost, NewDepart);
    }


}
