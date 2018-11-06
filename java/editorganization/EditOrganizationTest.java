package editorganization;

import global.EnvironmentOrganization;
import global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditOrganizationTest extends EditOrganizationLogic {

    @Test   //6.1
    @Parameters(value = {
            "1.Новое название отделения №1 | 1.Нов.сокр.назв. \"1\" | Акул | | | Акулин Аристарх Анатольевич |" +
                    " ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | " +
                    "Стационар | Чудеса неведанные |" +

                    "1.Название отделения №1 | Отд. \"1\" | 5b58130f0faa2638b94fc4d7 | Поликлиника | Левое ухо |" +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c",

            "2.Новое название отделения №2 | 2.Нов.сокр.назв.\"2\" | | Арис | | Акулин Аристарх Анатольевич " +
                    "| ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > " +
                    "Стоматологический центр > Отделение терапевтической стоматологии > Технический отдел > Второе " +
                    "неврологическое отделение | Дневной стационар | Чудеса неведанные |" +

                    "2.Название отделения №2 | Отд. \"2\" | 5b58130f0faa2638b94fc4d7 | Поликлиника | Левое ухо |" +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c",

            "3.Новое название отделения №3 | 3.Нов.сокр.назв.\"3\" | | | Анат | Акулин Аристарх Анатольевич " +
                    "|ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | " +
                    "Поликлиника | Чудеса неведанные |" +

                    "3.Название отделения №3 | Отд.\"3\" | 5b58130f0faa2638b94fc4d7 | Стационар | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c",
    })
    public void editDataOrganizationTest(String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                         String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                         String FIODirector, String NewHead, String ConditionsOrganization,
                                         String ProfileOrganization,

                                         String Name, String Abbr, String Chief, String Help_conditions,
                                         String Org_profile, String Description, String Address, String GeodataX,
                                         String GeodataY, String Email, String Facebook, String Instagram, String Vk,
                                         String Other, String NamePhone1, String NumberPhone1, String NamePhone2,
                                         String NumberPhone2, String Code, String Oms_number, String Pump,
                                         String Oms_id, String Status, String Parent) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editDataOrganization(Name, NewName, NewAbbr, SecondNameDirectorOrganization, FirstNameDirectorOrganization,
                MiddleNameDirectorOrganization, FIODirector, NewHead, ConditionsOrganization, ProfileOrganization,
                Abbr, Help_conditions, Org_profile, Description, Address, Email, Facebook, Instagram, Vk, Other,
                NamePhone1, NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
    }

    @Test   //6.2
    @Parameters(value = {
            "4.Новое название отделения №4 | 1.Нов.сокр.назв.\"4\" | Акул | Арис | Анат | Акулин Аристарх " +
                    "Анатольевич | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)" +
                    "| Стационар | Чудеса неведанные |" +

                    "4.Название отделения №4 | Отд. \"4\" | 5bd30bf63f0b202191c82aef | Поликлиника |" +
                    " Левое ухо |Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru |" +
                    " https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон" +
                    " №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 " +
                    "| jlkjTF86857 | JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c",

    })
    public void editDataOrganizationCancelTest(String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                               String FirstNameDirectorOrganization,
                                               String MiddleNameDirectorOrganization,
                                               String FIODirector, String NewHead, String ConditionsOrganization,
                                               String ProfileOrganization,

                                               String Name, String Abbr, String Chief, String Help_conditions,
                                               String Org_profile, String Description, String Address,
                                               String GeodataX, String GeodataY, String Email,
                                               String Facebook, String Instagram, String Vk, String Other,
                                               String NamePhone1, String NumberPhone1, String NamePhone2,
                                               String NumberPhone2, String Code, String Oms_number, String Pump,
                                               String Oms_id, String Status, String Parent) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editDataOrganizationCancel(Name, NewName, NewAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, NewHead,
                ConditionsOrganization, ProfileOrganization, Abbr, Help_conditions, Org_profile, Description,
                Address, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1, NamePhone2, NumberPhone2,
                Code, Oms_number, Pump, Oms_id, Status);
    }


    @Test   //6.3
    @Parameters(value = {
            "1.Услуги | 1.Услуги | 5bbb863a04a40f37b17433f1 | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5bdc0dce5a4c8615d6881b0c |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Стоматологический центр > Отделение " +
                    "терапевтической стоматологии > Технический отдел > Второе неврологическое отделение| Акулин " +
                    "Аристарх Анатольевич",

            "2.Услуги | 2.Услуги | 5bbb863a04a40f37b17433f1 | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | org:5bdc0dce5a4c8615d6881b0c |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Стоматологический центр > Отделение " +
                    "терапевтической стоматологии > Технический отдел > Второе неврологическое отделение| Акулин " +
                    "Аристарх Анатольевич",

            "3.Услуги | 3.Услуги | 5bbb863a04a40f37b17433f1 | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | org:5bdc0dce5a4c8615d6881b0c |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Стоматологический центр > Отделение " +
                    "терапевтической стоматологии > Технический отдел > Второе неврологическое отделение| Акулин " +
                    "Аристарх Анатольевич",

            "4.Услуги | 4.Услуги | 5bbb863a04a40f37b17433f1 | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5bdc0dce5a4c8615d6881b0c | el-checkbox__input is-checked is-focus" +
                    " | el-checkbox__input is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | " +
                    "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > " +
                    "Стоматологический центр > Отделение терапевтической стоматологии > Технический отдел > Второе " +
                    "неврологическое отделение| Акулин Аристарх Анатольевич",

            "5.Услуги | 5.Услуги | 5bbb863a04a40f37b17433f1 | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп | Телефон №1 | +7 (000) " +
                    "111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 | jlkjTF86857 |" +
                    " JKHKJHnbvnb768 | active | org:5bdc0dce5a4c8615d6881b0c | el-checkbox__input is-checked is-focus" +
                    " | el-checkbox__input is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | " +
                    "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > " +
                    "Стоматологический центр > Отделение терапевтической стоматологии > Технический отдел > Второе " +
                    "неврологическое отделение| Акулин Аристарх Анатольевич",

    })
    public void editServicesOrganizationTest(String Name, String Abbr, String Chief, String Help_conditions,
                                             String Org_profile, String Description, String Address, String GeodataX,
                                             String GeodataY, String Email, String Facebook, String Instagram,
                                             String Vk, String Other, String NamePhone1, String NumberPhone1,
                                             String NamePhone2, String NumberPhone2, String Code, String Oms_number,
                                             String Pump, String Oms_id, String Status, String Parent,
                                             String CheckedAndFocus, String Checkeds, String Indeterminate,
                                             String Empty, String Head, String FIODirector) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editServicesOrganization(Name, Abbr, CheckedAndFocus, Checkeds, Indeterminate, Empty, Help_conditions,
                Org_profile, Description, Address, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status, Head, FIODirector);
    }


    @Test   //6.5
    @Parameters(value = {
            "5.Измененное описание | Новое назначение телефона №1 | 9999999999 | Москва волков 4 | NEW@mail.ru | " +
                    "https://NEWvk.com/ | https://NEWfacebook.com/ | https://NEWinstagram.com/ | NEW другое | ajdJK7" +
                    "(*)| maLKJLK09^& | lkkJN32^& | vbjJHB987(* | " +

                    "5.Название отделения №5 | Отд. \"5\" | 5bbb863a04a40f37b17433f1 | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru | " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c | ФГАОУ " +
                    "ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Университетская " +
                    "клиническая больница №2 > Клиника пропедевтики > Гастроэнтерологическое отделение | Акулин " +
                    "Аристарх Анатольевич",

    })
    public void editAdditionalInformationOrganizationTest(String DescriptionOrganization, String NewAppointmentPhone1,
                                                          String NewNumberPhone1, String NewAdress, String NewEmail,
                                                          String NewVk, String NewFacebook, String NewInstagram,
                                                          String NewOther, String NewCode, String NewRegistryNumber,
                                                          String NewIdentifierPUMP, String NewIdentifierOMS,

                                                          String Name, String Abbr, String Chief,
                                                          String Help_conditions, String Org_profile,
                                                          String Description, String Address, String GeodataX,
                                                          String GeodataY, String Email, String Facebook,
                                                          String Instagram, String Vk, String Other, String NamePhone1,
                                                          String NumberPhone1, String NamePhone2, String NumberPhone2,
                                                          String Code, String Oms_number, String Pump, String Oms_id,
                                                          String Status, String Parent, String Head,
                                                          String FIODirector) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editAdditionalInformationOrganization(Name, DescriptionOrganization, NewAppointmentPhone1, NewNumberPhone1,
                NewAdress, NewEmail, NewVk, NewFacebook, NewInstagram, NewOther, NewCode, NewRegistryNumber,
                NewIdentifierPUMP, NewIdentifierOMS, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, Email, Facebook, Instagram, Vk, Other, Code, Oms_number, Pump, Oms_id, Status, Parent, Head,
                FIODirector);
    }

    @Test   //6.6
    @Parameters(value = {
            "6.Измененное описание | Новое назначение телефона №1 | 9999999999 | Москва волков 4 | NEW@mail.ru | " +
                    "https://NEWvk.com/ | https://NEWfacebook.com/ | https://NEWinstagram.com/ | NEW другое | ajdJK7" +
                    "(*)| maLKJLK09^& | lkkJN32^& | vbjJHB987(* | " +

                    "6.Название отделения 6 | Отд.\"6\" | 5bbb863a04a40f37b17433f1 | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru |" +
                    " https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c | ФГАОУ " +
                    "ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Университетская " +
                    "клиническая больница №2 > Клиника пропедевтики > Гастроэнтерологическое отделение | Акулин " +
                    "Аристарх Анатольевич",

    })
    public void editAdditionalInformationOrganizationCancelTest(String DescriptionOrganization,
                                                                String NewAppointmentPhone1,
                                                                String NewNumberPhone1, String NewAdress,
                                                                String NewEmail,
                                                                String NewVk, String NewFacebook, String NewInstagram,
                                                                String NewOther, String NewCode,
                                                                String NewRegistryNumber,
                                                                String NewIdentifierPUMP, String NewIdentifierOMS,

                                                                String Name, String Abbr, String Chief,
                                                                String Help_conditions, String Org_profile,
                                                                String Description, String Address, String GeodataX,
                                                                String GeodataY, String Email, String Facebook,
                                                                String Instagram, String Vk, String Other,
                                                                String NamePhone1,
                                                                String NumberPhone1, String NamePhone2,
                                                                String NumberPhone2,
                                                                String Code, String Oms_number, String Pump,
                                                                String Oms_id, String Status, String Parent,
                                                                String Head, String FIODirector) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editAdditionalInformationOrganizationCancel(Name, DescriptionOrganization, NewAppointmentPhone1,
                NewNumberPhone1,
                NewAdress, NewEmail, NewVk, NewFacebook, NewInstagram, NewOther, NewCode, NewRegistryNumber,
                NewIdentifierPUMP, NewIdentifierOMS, Status, Head, FIODirector, Address, Email, Facebook,
                Instagram, Vk, Other, Code, Oms_number, Pump, Oms_id, Description);
    }


    @Test   //6.7
    @Parameters(value = {
            "Новый телефон №2 | 9999999999 |" +

                    "7.Название отделения №7 | Отд. \"7\" | 5b58130f0faa2638b94fc4d7 | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое!:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | org:5b868d35a396ac26895e401c",

    })
    public void editPhonesOrganizationTest(String NewAppointmentPhone2, String NewNumberPhone2,

                                           String Name, String Abbr, String Chief,
                                           String Help_conditions, String Org_profile,
                                           String Description, String Address, String GeodataX,
                                           String GeodataY, String Email, String Facebook,
                                           String Instagram, String Vk, String Other, String NamePhone1,
                                           String NumberPhone1, String NamePhone2, String NumberPhone2,
                                           String Code, String Oms_number, String Pump,
                                           String Oms_id, String Status, String Parent) {
        EnvironmentOrganization.login();
        EnvironmentOrganization.createOrganizationAPI();
        dateCreateOrg();
        EnvironmentOrganization.profileOrganizationAPI(Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Email, Facebook, Instagram, Vk, Other, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        EnvironmentOrganization.createOrganizationParent(Parent);
        editPhonesOrganization(Name, NamePhone1, NumberPhone1, NewAppointmentPhone2, NewNumberPhone2);
    }
}
