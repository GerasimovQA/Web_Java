package createuser;

import junitparams.Parameters;
import org.junit.Test;

public class CreateUserTest extends CreateUserLogic {
    @Test   //2.1
    @Parameters(value = {
//            "Shaverma123 | al200123@mail.ru | 0023543210 | 6simbo | Аничкин | Сергей | Иванович | Высшее медицинское " +
//                    "| al1982@mail.ru | 9270075051 | http://instagram1.com | http://Vk2/.ru | http://Whatsapp3.com | " +
//                    "12345678910 | http://Facebook4.com | Good-Ква%821_ | medicina_1696.jpg | el-checkbox__input " +
//                    "is-checked is-focus | el-checkbox__input is-checked | el-checkbox__input is-indeterminate |" +
//                    " el-checkbox__input",

            "1.t7a_mE-n- | al2001@mail.ru | 1176543210 | 7simbol | Аниськин | Иван || Высшее медицинское | " +
                    "samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru |" +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input ",

            "2.t7a_mJ-n- | al2002@mail.ru | 2276543217 | 7simbol | Авитисян | Владимир | Антонович | Высшее " +
                    "медицинское | samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru | " +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input ",

            "3.t7a_mY-n- | al2003@mail.ru | 3375233217 | 7simbol | Аннуфриев | Олег | Евгеньевич | Высшее медицинское" +
                    " | samara.gerasimov@yandex.ru | 9270075051 | http://instagram.com | http://Vk/.ru | " +
                    "http://Whatsapp.com | 12345678910 | http://Facebook.com | Other | 2002.png | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input ",

    })
    public void createUserWithoutInfoToEmailTest(String Login, String Email, String Phone, String Password,
                                                 String SecondName, String FirstName, String MiddleName,
                                                 String Education, String EmailInfo, String PhoneInfo, String Instagram,
                                                 String Vk, String Whatsapp, String Viber, String Facebook, String Other
            , String Photo, String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        createUserWithoutInfoToEmail(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo, CheckedAndFocus,
                Checkeds, Indeterminate, Empty);
    }

    @Test   //2.2
    @Parameters(value = {
            "4.ta_mo9n- | al2004@google.com | 4476854328712 | 8simbols | Djon | Le Mon | Smith | High Shcool 13 | " +
                    "gerasimovqa@gmail.com | 9270075051 | http://instagram.com | http://Vk/.ru | http://Whatsapp.com " +
                    "| 12345678910 | http://Facebook.com | Other | medicina_1696.jpg | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input ",
    })
    public void createUserWithInfoToEmailTest(String Login, String Email, String Phone, String Password,
                                              String SecondName, String FirstName, String MiddleName, String Education,
                                              String EmailInfo, String PhoneInfo, String Instagram, String Vk,
                                              String Whatsapp, String Viber, String Facebook, String Other,
                                              String Photo, String CheckedAndFocus, String Checkeds,
                                              String Indeterminate, String Empty) {
        createUserWithInfoToEmail(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo, CheckedAndFocus, Checkeds,
                Indeterminate, Empty);
    }

    @Test   //2.3
    public void stepOnePasswordTest() {
        stepOneGeneratePassword();
        stepOneShowPassword();
    }
}
