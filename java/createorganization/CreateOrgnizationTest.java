package createorganization;

import junitparams.Parameters;
import org.junit.Test;



public class CreateOrgnizationTest extends CreateOrgnizationLogic {

    @Test   //5.1
    @Parameters(value = {
            "1.Больничка Ордена красного знамени №1 | Больница №1 | Акул | | | Акулин Аристарх Анатольевич |" +
                    " Болезни сердца и души | Очень подробное писание организации | Телефон №1 |" +
                    " 1231231234 | Телефон №2 | 4564567899 | Москва Газетный пер 5 | org123@mail.ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | ДругоеОРjjkhILLKM23456!@#$%^&* |" +
                    "hj(!@#$GFбьДОЛ8798 | 876орРППОmbnGJH!@#$ | @#$%^&*()dfBNитР876 | " +
                    "567(*?:%;прТОkuYU | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) |" +
                    " Поликлиника",
            "2.Больничка Ордена красного знамени №2 | Больница №2 | | Арис | | Акулин Аристарх Анатольевич |" +
                    " Болезни сердца и души | Очень подробное писание организации | Телефон №1 |" +
                    " 1231231234 | Телефон №2 | 4564567899 | Москва Газетный пер 5 | org123@mail.ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | ДругоеОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) |" +
                    " Дневной стационар",
            "3.Больничка Ордена красного знамени №3 | Больница №3 | | | Анат | Акулин Аристарх Анатольевич |" +
                    " Болезни сердца и души | Очень подробное писание организации | Телефон №1 |" +
                    " 1231231234 | Телефон №2 | 4564567899 | Москва Газетный пер 5 | org123@mail.ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | ДругоеОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) |" +
                    " Стационар",
    })
    public void CreateOrganizationTest(String OrganizationName, String OrganizationNameAbbr,
                                       String SecondNameDirectorOrganization, String FirstNameDirectorOrganization,
                                       String MiddleNameDirectorOrganization, String FIODirector,
                                       String OrganizationProfile, String DescriptionOrgnization,
                                       String AppointmentPhone1, String PhoneNumber1, String AppointmentPhone2,
                                       String PhoneNumber2, String Adress, String Email, String Vk, String Facebook,
                                       String Instagram, String Other, String Code, String IdentifierOMS,
                                       String RegistrationNumber, String IdentifierPUMP, String HeadOrganization,
                                       String ConditionsOrganization) {
        stepOneCreateOrganization(OrganizationName, OrganizationNameAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, OrganizationProfile,
                ConditionsOrganization);
        stepTwoCreateOrganization();
        stepThreeCreateOrganization(DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2,
                PhoneNumber2, Adress, Email, Vk,
                Facebook, Instagram, Other, Code, IdentifierOMS, RegistrationNumber, IdentifierPUMP);
        stepFourCreateOrganization(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress,
                Email, Vk, Facebook, Instagram, Other, Code, IdentifierOMS, RegistrationNumber, IdentifierPUMP,
                HeadOrganization, ConditionsOrganization);
    }

    @Test   //5.2
    @Parameters(value = {
            "4.Медицинская организация №13 | Заведение №13 | Акул | Арис | Анат | Акулин Аристарх Анатольевич |" +
                    " Болезни сердца и души | Очень подробное писание организации | Телефон №1 |" +
                    " 1231231234 | Телефон №\"2\" | 4564567899 | Москва Газетный пер 5 | org123@mail.ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | ДругоеОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) >" +
                    " Университетская клиническая больница №3 > Клиника нервных болезней > Психотерапевтическое " +
                    "отделение | Поликлиника",
    })
    public void CreateOrganizationTestDirectorTest(String OrganizationName, String OrganizationNameAbbr,
                                                   String SecondNameDirectorOrganization,
                                                   String FirstNameDirectorOrganization,
                                                   String MiddleNameDirectorOrganization, String FIODirector,
                                                   String OrganizationProfile, String DescriptionOrgnization,
                                                   String AppointmentPhone1, String PhoneNumber1,
                                                   String AppointmentPhone2,
                                                   String PhoneNumber2, String Adress, String Email, String Vk,
                                                   String Facebook,
                                                   String Instagram, String Other, String Code, String IdentifierOMS,
                                                   String RegistrationNumber, String IdentifierPUMP,
                                                   String HeadOrganization,
                                                   String ConditionsOrganization) {
        stepOneCreateOrganizationTestDirector(OrganizationName, OrganizationNameAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, OrganizationProfile,
                ConditionsOrganization);
        stepTwoCreateOrganization();
        stepThreeCreateOrganizationTestDirector(DescriptionOrgnization, AppointmentPhone1, PhoneNumber1,
                AppointmentPhone2,
                PhoneNumber2, Adress, Email, Vk,
                Facebook, Instagram, Other, Code, IdentifierOMS, RegistrationNumber, IdentifierPUMP);
        stepFourCreateOrganizationTestDirector(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress,
                Email, Vk, Facebook, Instagram, Other, Code, IdentifierOMS, RegistrationNumber, IdentifierPUMP,
                HeadOrganization, ConditionsOrganization);
    }

//    @Test   //2.2
//    @Parameters(value = {
//            "4.ta_mo9n- | al2004@google.com | 4476854328712 | 8simbols | Djon | Le Mon | Smith | High Shcool 13 |
// gerasimovqa@gmail.com | 9270075051 | http://instagram.com | http://Vk/.ru | http://Whatsapp.com | 12345678910 |
// http://Facebook.com | Other | medicina_1696.jpg",
//    })
//    public void stepOneWithInfoToEmailTest(String Login, String Email, String Phone, String Password, String
// SecondName, String FirstName, String MiddleName, String Education, String EmailInfo, String PhoneInfo, String
// Instagram, String Vk, String Whatsapp, String Viber, String Facebook, String Other, String Photo) {
//        createUserWithInfoToEmail(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
// EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo);
//    }
//
//    @Test   //2.3
//    public void stepOnePasswordTest() {
//        stepOneGeneratePassword();
//        stepOneShowPassword();
//    }


}
