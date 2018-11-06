package createuser;

import junitparams.Parameters;
import org.junit.Test;

public class CreateUserTest extends CreateUserLogic {
    @Test   //2.1
    @Parameters(value = {
            "1.haverma123 | 6simbo | al200123@mail.ru | 0023543210 | Аничкин | Сергей | Иванович | Высшее медицинское" +
                    " " +
                    "| al1982@mail.ru | 9270075051 | http://instagram1.com | http://Vk2/.ru | http://Whatsapp3.com | " +
                    "12345678910 | http://Facebook4.com | Good-Ква%821_ | medicina_1696.jpg | el-checkbox__input " +
                    "is-checked is-focus | el-checkbox__input is-checked | el-checkbox__input is-indeterminate |" +
                    " el-checkbox__input | Врач",

            "2.t7a_mE-n- | 7simbol | al2001@mail.ru | 1176543210 | Аниськин | Иван || Высшее медицинское | " +
                    "samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru |" +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Директор",

            "3.t7a_mJ-n- | 7simbol | al2002@mail.ru | 2276543217 | Авитисян | Владимир | Антонович | Высшее " +
                    "медицинское | samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru | " +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Руководитель",

            "4.t7a_mY-n- | 7simbol| al2003@mail.ru | 3375233217 | Аннуфриев | Олег | Евгеньевич | Высшее медицинское" +
                    " | samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru | " +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Врач",

    })
    public void createUserWithoutInfoToEmailTest(String Login, String Password, String Email, String Phone,
                                                 String SecondName, String FirstName, String MiddleName,
                                                 String Education, String EmailInfo, String PhoneInfo, String Instagram,
                                                 String Vk, String Whatsapp, String Viber, String Facebook, String Other
            , String Photo, String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty,
                                                 String Position) {
        createUserWithoutInfoToEmail(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo, CheckedAndFocus,
                Checkeds, Indeterminate, Empty, Position);
    }

    @Test   //2.2
    @Parameters(value = {
            "5.ta_mo9n- | 8simbols | al2004@google.com | 4476854328712 | Djon | Le Mon | Smith | High Shcool 13 | " +
                    "gerasimovqa@gmail.com | 9270075051 | http://instagram.com | http://Vk/.ru | http://Whatsapp.com " +
                    "| 12345678910 | http://Facebook.com | Other | medicina_1696.jpg | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Врач",
    })
    public void createUserWithInfoToEmailTest(String Login, String Password, String Email, String Phone,
                                              String SecondName, String FirstName, String MiddleName, String Education,
                                              String EmailInfo, String PhoneInfo, String Instagram, String Vk,
                                              String Whatsapp, String Viber, String Facebook, String Other,
                                              String Photo, String CheckedAndFocus, String Checkeds,
                                              String Indeterminate, String Empty, String Position) {
        createUserWithInfoToEmail(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo, CheckedAndFocus, Checkeds,
                Indeterminate, Empty, Position);
    }

    @Test   //2.3
    public void stepOnePasswordTest() {
        stepOneGeneratePassword();
        stepOneShowPassword();
    }
}
