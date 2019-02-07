package Organization.CreateOrganization;

import junitparams.Parameters;
import org.junit.Test;

public class CreateOrgnizationTest extends CreateOrgnizationLogic {

    @Test   //5.1
    @Parameters(value = {
            "1 Больница Ордена красного знамени | Больница №1 | Акул | | | Акулин Аристарх Анатольевич |" +
                    " Болезни сердца | Очень подробное писание организации | Телефон №1 |" +
                    " 12312312341 | Телефон №2 | 45645678969 | Москва Газетный пер 5 | https://site.ru | org123@mail" +
                    ".ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | Другое | ОРjjkhILLKM23456!@#$%^&* |" +
                    "hj(!@#$GFбьДОЛ8798 | 876орРППОmbnGJH!@#$ | @#$%^&*()dfBNитР876 | " +
                    "567(*?:%;прТОkuYU | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский " +
                    "Университет) | Поликлиника | el-checkbox__input is-checked is-focus | el-checkbox__input " +
                    "is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | Актуальное",

            "2 Больница Ордена красного знамени | Больница №2 | | Арис | | Акулин Аристарх Анатольевич |" +
                    " Болезни уха | Описание | Телефон №1 |" +
                    " 12312312342 | Телефон №2 | 45645678699 | Москва Газетный пер 5 | https://site.ru | org123@mail" +
                    ".ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | Другое | ОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) |" +
                    "Дневной стационар | el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Актуальное",

            "3 Больница Ордена красного знамени | Больница №3 | | | Анат | Акулин Аристарх Анатольевич |" +
                    " Болезни ног | Лечим ноги за полцены | Телефон №1 |" +
                    " 12312312343 | Телефон №2 | 45645667899 | Москва Газетный пер 5 | https://site.ru | org123@mail" +
                    ".ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | Другое | ОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) |" +
                    " Стационар | el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Актуальное",

            "4 Больница Ордена красного знамени | Больница №4 | | | Анат | Акулин Аристарх Анатольевич |" +
                    " Болезни рук | Выравниваем руки | Телефон №1 |" +
                    " 12312312344 | Телефон №2 | 45645678799 | Москва Газетный пер 5 | https://site.ru | org123@mail" +
                    ".ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | Другое | ОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) |" +
                    " Стационар | el-checkbox__input is-checked is-focus | el-checkbox__input is-checked |" +
                    " el-checkbox__input is-indeterminate | el-checkbox__input | Актуальное",

    })
    public void CreateOrganizationTest(String OrganizationName, String OrganizationNameAbbr,
                                       String SecondNameDirectorOrganization, String FirstNameDirectorOrganization,
                                       String MiddleNameDirectorOrganization, String FIODirector,
                                       String OrganizationProfile, String DescriptionOrgnization,
                                       String AppointmentPhone1, String PhoneNumber1, String AppointmentPhone2,
                                       String PhoneNumber2, String Adress, String Site, String Email, String Vk,
                                       String Facebook,
                                       String Instagram, String OtherName, String OtherValue, String Code,
                                       String IdentifierOMS,
                                       String RegistrationNumber, String IdentifierPUMP, String HeadOrganization,
                                       String ConditionsOrganization, String CheckedAndFocus, String Checkeds,
                                       String Indeterminate, String Empty, String Status) {
        stepOneCreateOrganization(OrganizationName, OrganizationNameAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, OrganizationProfile,
                ConditionsOrganization);
        stepTwoCreateOrganization(OrganizationNameAbbr, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        stepThreeCreateOrganization(DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2,
                PhoneNumber2, Adress, Site, Email, Vk, Facebook, Instagram, OtherName, OtherValue, Code,
                IdentifierOMS, RegistrationNumber,
                IdentifierPUMP);
        stepFourCreateOrganization(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress, Site,
                Email, Vk, Facebook, Instagram, OtherName, OtherValue, Code, IdentifierOMS, RegistrationNumber,
                IdentifierPUMP,
                HeadOrganization, ConditionsOrganization);
        stepFiveCreateOrganization(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress, Site,
                Email, Vk, Facebook, Instagram, OtherName, OtherValue, Code, IdentifierOMS, RegistrationNumber,
                IdentifierPUMP, HeadOrganization, ConditionsOrganization, Status);
    }

    @Test   //5.2
    @Parameters(value = {
            "5 Медицинская организация | Мед.орг. №5 | Акул | Арис | Анат | Акулин Аристарх Анатольевич |" +
                    " Болезни | Описание длиной более 180 символов 11111111111 111111111111 111111111111111" +
                    "111111111111111111 1111111111111111111111 111111111111111111 1111111111111 1111111111111111" +
                    "1111111111111111 11111111111111111111 1111111111111111 1 1 1111111111111111 11111 11111111" +
                    "111111111 1111111111111111 11111111111111111111 111111111111111111 111111111111 | Телефон №1 |" +
                    " 12312312345 | Телефон №\"2\" | 456456778990 | Москва Газетный пер 5 | https://site.ru | " +
                    "org123@ma65il.ru |" +
                    "http://Vk/.ru | http://Facebook.com | http://instagram.com | Другое | ОРjjkhILLKM23456!@#$%^&* |" +
                    "854534843216841351816 | 32534665867664547856 | 5643184132185189416541354534653 | " +
                    "3457899878 | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > " +
                    "Кардиоцентр №1 > Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | Поликлиника |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input " +
                    "is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | Актуальное",
    })
    public void CreateOrganizationTestDirectorTest(String OrganizationName, String OrganizationNameAbbr,
                                                   String SecondNameDirectorOrganization,
                                                   String FirstNameDirectorOrganization,
                                                   String MiddleNameDirectorOrganization, String FIODirector,
                                                   String OrganizationProfile, String DescriptionOrgnization,
                                                   String AppointmentPhone1, String PhoneNumber1,
                                                   String AppointmentPhone2, String PhoneNumber2, String Adress,
                                                   String Site,
                                                   String Email, String Vk, String Facebook, String Instagram,
                                                   String OtherName, String OtherValue, String Code,
                                                   String IdentifierOMS,
                                                   String RegistrationNumber, String IdentifierPUMP,
                                                   String HeadOrganization, String ConditionsOrganization,
                                                   String CheckedAndFocus, String Checkeds, String Indeterminate,
                                                   String Empty, String Status) {
        stepOneCreateOrganizationTestDirector(OrganizationName, OrganizationNameAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, OrganizationProfile,
                ConditionsOrganization);
        stepTwoCreateOrganization(OrganizationNameAbbr, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        stepThreeCreateOrganizationTestDirector(DescriptionOrgnization, AppointmentPhone1, PhoneNumber1,
                AppointmentPhone2, PhoneNumber2, Adress, Site, Email, Vk, Facebook, Instagram, OtherName, OtherValue,
                Code, IdentifierOMS, RegistrationNumber, IdentifierPUMP);
        stepFourCreateOrganizationTestDirector(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress, Site,
                Email, Vk, Facebook, Instagram, OtherName, OtherValue, Code, IdentifierOMS, RegistrationNumber,
                IdentifierPUMP,
                HeadOrganization, ConditionsOrganization, Status);
        stepFiveCreateOrganizationTestDirector(OrganizationName, OrganizationNameAbbr, FIODirector, OrganizationProfile,
                DescriptionOrgnization, AppointmentPhone1, PhoneNumber1, AppointmentPhone2, PhoneNumber2, Adress, Site,
                Email, Vk, Facebook, Instagram, OtherName, OtherValue, Code, IdentifierOMS, RegistrationNumber,
                IdentifierPUMP, HeadOrganization, ConditionsOrganization, Status);
    }
}
