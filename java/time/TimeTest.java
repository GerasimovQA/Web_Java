package time;

import junitparams.Parameters;
import org.junit.Test;


public class TimeTest extends TimeLogic {

    @Test   //
    @Parameters(value = {
            "Shavernos61 | al1956uhg67@mail.ru | 9863258361 | 6simbo | active | Коршенидзе | Джамал | Васильевич | true | Сеченовский университет > Больница 1 | Врач | Специалист| Дантист | Консультации | 0 | Консультация доцента | 3000 руб. | 1 | Высшее медицинское | qua@mail.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER | medicina_1696.jpg",
    })
    public void TimeTestApiLoginCreateProfileUploadphoto(String Login, String Email, String Phone, String Password, String Status, String SecondName, String FirstName, String MiddleName, String Superuser, String Depart, String Post, String Role, String Specialities, String Label, String ID, String LabelChild, String Cost, String IDchild, String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk, String Whatsapp, String Viber, String Facebook, String Other, String Photo) {

        createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName, Superuser);
        profileUserAPI(Depart, Post, Role, Specialities, Label, ID, LabelChild, Cost, IDchild, Regalia, EmailCont, PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        uploadPhotoUserAPI(Photo);
    }
}