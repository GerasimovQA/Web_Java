package Time;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;


public class TimeTest extends TimeLogic {
    private TimeLogic timeLogic = new TimeLogic();

    @Test   //
    @Parameters(value = {
            "1SLnakjsdhidGI | 1nbdf545dhkC78wds | 104y9.kjas34fs-sd8k@gmail.com | 90195246781 | active |" +
                    " Жукина | Василиса | | true | false | 5b7d08aa068d7552d904d8be | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) | active| Врач | | doctor | |" +
                    " 5bc0a53043aef1053d0ada91 | 5bc44d0b43aef1053d0adabe | 5bc0d40b5fdf7508cc91db03 | |" +
                    " Высшее медицинское | qua@mail.ru " +
                    "| 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER | medicina_1696.jpg",
    })
    public void TimeTestApiLoginCreateProfileUploadphoto(String Login, String Email, String Phone,
                                                         String Password, String Status, String SecondName, String FirstName,
                                                         String MiddleName, String Superuser, String SendEmail, String OrgID,
                                                         String Depart, String OrgStatus, String Post1,
                                                         String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                                         String Service1, String Service2,
                                                         String Regalia, String EmailCont, String PhoneCont,
                                                         String Instagram, String Vk, String Whatsapp, String Viber,
                                                         String Facebook, String Other, String Photo) {

        String token = timeLogic.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        String userID = timeLogic.createUserAPI(token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        timeLogic.profileUserAPI(token, userID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont, Instagram, Vk
                , Whatsapp, Viber, Facebook, Other);
        timeLogic.uploadPhotoUserAPI(token, userID, Photo);
    }
}