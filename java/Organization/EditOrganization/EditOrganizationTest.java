package Organization.EditOrganization;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditOrganizationTest extends EditOrganizationLogic {
    private EditOrganizationAPI editOrganizationAPI = new EditOrganizationAPI();

    private String Token = null;
    private String OrgID = null;

    @Test   //6.1
    @Parameters(value = {
            "1.Новое название отделения №1 | 1.Нов.сокр.назв. \"1\" | Акул | | | Акулин Аристарх Анатольевич | ФГАОУ " +
                    "ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | " +
                    "Стационар | Чудеса неведанные |" +

                    "1.Название отделения №1 | Отд. \"1\" | 5b58130f0faa2638b94fc4d7 | Поликлиника | Левое ухо |" +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | " +
                    "Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | " +
                    "vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa",

            "2.Новое Название отделения №2 | 2.Нов.сокр.назв.\"2\" | | Арис | | Акулин Аристарх Анатольевич " +
                    "| ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 >" +
                    " Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | Дневной стационар | Чудеса неведанные |" +

                    "2.Название отделения №2 | Отд. \"2\" | 5b58130f0faa2638b94fc4d7 | Поликлиника | Левое ухо |" +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | " +
                    "Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | " +
                    "vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5b7d08aa068d7552d904d8be",

            "3.Новое название отделения №3 | 3.Нов.сокр.назв.\"3\" | | | Анат | Акулин Аристарх Анатольевич " +
                    "|ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) | Поликлиника" +
                    " | Чудеса неведанные |" +

                    "3.Название отделения №3 | Отд.\"3\" | 5b58130f0faa2638b94fc4d7 | Стационар | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | " +
                    "Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | " +
                    "vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa",
    })
    public void editDataOrganizationTest(String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                         String FirstNameDirectorOrganization, String MiddleNameDirectorOrganization,
                                         String FIODirector, String NewHead, String ConditionsOrganization,
                                         String ProfileOrganization,

                                         String Name, String Abbr, String Chief, String Help_conditions,
                                         String Org_profile, String Description, String Address, String GeodataX,
                                         String GeodataY, String Site, String Email, String Facebook,
                                         String Instagram, String Vk,
                                         String OtherName, String OtherValue, String NamePhone1, String NumberPhone1,
                                         String NamePhone2, String NumberPhone2, String Code, String Oms_number,
                                         String Pump, String Oms_id, String Status, String Parent) {
        Token = editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID = editOrganizationAPI.createOrganizationAPI(Token, Parent);
        dateCreateOrg();
        GlobalPage.nowDate();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        editDataOrganization(OrgID, Name, NewName, NewAbbr, SecondNameDirectorOrganization, FirstNameDirectorOrganization,
                MiddleNameDirectorOrganization, FIODirector, NewHead, ConditionsOrganization, ProfileOrganization,
                Abbr, Help_conditions, Org_profile, Description, Address, Site, Email, Facebook, Instagram, Vk,
                OtherName, OtherValue,
                NamePhone1, NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
    }

    @Test   //6.2
    @Parameters(value = {
            "4.Новое название отделения №4 | 1.Нов.сокр.назв.\"4\" | Акул | Арис | Анат | Акулин Аристарх " +
                    "Анатольевич | ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)" +
                    "| Стационар | Чудеса неведанные |" +

                    "4.Название отделения №4 | Отд. \"4\" | 5bd30bf63f0b202191c82aef | Поликлиника |" +
                    " Левое ухо |Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | " +
                    "hg8799jlkj@mail.ru |" +
                    " https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | " +
                    "Телефон" +
                    " №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 " +
                    "| jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa",

    })
    public void editDataOrganizationCancelTest(String NewName, String NewAbbr, String SecondNameDirectorOrganization,
                                               String FirstNameDirectorOrganization,
                                               String MiddleNameDirectorOrganization,
                                               String FIODirector, String NewHead, String ConditionsOrganization,
                                               String ProfileOrganization,

                                               String Name, String Abbr, String Chief, String Help_conditions,
                                               String Org_profile, String Description, String Address,
                                               String GeodataX, String GeodataY, String Site, String Email,
                                               String Facebook, String Instagram, String Vk, String OtherName,
                                               String OtherValue,
                                               String NamePhone1, String NumberPhone1, String NamePhone2,
                                               String NumberPhone2, String Code, String Oms_number, String Pump,
                                               String Oms_id, String Status, String Parent) {
        Token =  editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID =  editOrganizationAPI.createOrganizationAPI(Token, Parent);
        dateCreateOrg();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
//        EnvironmentOrganization.createOrganizationParent(Parent);
        editDataOrganizationCancel(OrgID, Name, NewName, NewAbbr, SecondNameDirectorOrganization,
                FirstNameDirectorOrganization, MiddleNameDirectorOrganization, FIODirector, NewHead,
                ConditionsOrganization, ProfileOrganization, Abbr, Help_conditions, Org_profile, Description,
                Address, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1, NumberPhone1,
                NamePhone2, NumberPhone2,
                Code, Oms_number, Pump, Oms_id, Status);
    }


    @Test   //6.3
    @Parameters(value = {
            "1.Услуги | 1.Услуги | 5bf3be3422ee2270923814af | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > Кардиоцентр №2 > " +
                    "Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

            "2.Услуги | 2.Услуги | 5bf3be3422ee2270923814af | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > Кардиоцентр №2 > " +
                    "Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

            "3.Услуги | 3.Услуги | 5bf3be3422ee2270923814af | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa |" +
                    " el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > Кардиоцентр №2 > " +
                    "Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

            "4.Услуги | 4.Услуги | 5bf3be3422ee2270923814af | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | " +
                    "Телефон №1 |" +
                    " +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa | el-checkbox__input " +
                    "is-checked is-focus" +
                    " | el-checkbox__input is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | " +
                    "ФГАОУ ВО Первый МГМУ им. И.М. " +
                    "Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > Кардиоцентр №2 > " +
                    "Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

            "5.Услуги | 5.Услуги | 5bf3be3422ee2270923814af | Поликлиника | Левое ухо | " +
                    "Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@mail.ru| " +
                    "https://facebook" +
                    ".com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп | Телефон №1 | " +
                    "+7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH | vmnbvJHFH66365 |" +
                    " jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa | el-checkbox__input " +
                    "is-checked is-focus" +
                    " | el-checkbox__input is-checked | el-checkbox__input is-indeterminate | el-checkbox__input | " +
                    "ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр " +
                    "№1 > Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

    })
    public void editServicesOrganizationTest(String Name, String Abbr, String Chief, String Help_conditions,
                                             String Org_profile, String Description, String Address, String GeodataX,
                                             String GeodataY, String Site, String Email, String Facebook,
                                             String Instagram, String Vk, String OtherName, String OtherValue,
                                             String NamePhone1, String NumberPhone1, String NamePhone2,
                                             String NumberPhone2, String Code, String Oms_number, String Pump,
                                             String Oms_id, String Status, String Parent, String CheckedAndFocus,
                                             String Checkeds, String Indeterminate, String Empty, String Head,
                                             String FIODirector) {
        Token =   editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID =  editOrganizationAPI.createOrganizationAPI(Token, Parent);
//        dateCreateOrg();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
        editServicesOrganization(OrgID, Name, Abbr, CheckedAndFocus, Checkeds, Indeterminate, Empty, Help_conditions,
                Org_profile, Description, Address, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue,
                NamePhone1, NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status, Head,
                FIODirector);
    }


    @Test   //6.5
    @Parameters(value = {
            "5.Измененное описание | Новое назначение телефона №1 | 99999999999 | Москва волков 4 |" +
                    " https://NEWsite.ru | NEW@mail.ru | https://NEWvk.com/ | https://NEWfacebook.com/ |" +
                    " https://NEWinstagram.com/ | NEW др.назв | NEW знач | ajdJK7 | maLKJLK09^& |" +
                    " lkkJN32^& | vbjJHB987(* | " +

                    "5.Название отделения №5 | Отд. \"5\" | 5bf3be3422ee2270923814af | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | " +
                    "hg8799jlkj@mail.ru | " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa | ФГАОУ ВО " +
                    "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > " +
                    "Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | Акулин " +
                    "Аристарх Анатольевич",

    })
    public void editAdditionalInformationOrganizationTest(String DescriptionOrganization, String NewAppointmentPhone1,
                                                          String NewNumberPhone1, String NewAdress, String NewSite,
                                                          String NewEmail,
                                                          String NewVk, String NewFacebook, String NewInstagram,
                                                          String NewOtherName, String NewOtherValue, String NewCode,
                                                          String NewRegistryNumber,
                                                          String NewIdentifierPUMP, String NewIdentifierOMS,

                                                          String Name, String Abbr, String Chief,
                                                          String Help_conditions, String Org_profile,
                                                          String Description, String Address, String GeodataX,
                                                          String GeodataY, String Site, String Email, String Facebook,
                                                          String Instagram, String Vk, String OtherName,
                                                          String OtherValue, String NamePhone1,
                                                          String NumberPhone1, String NamePhone2, String NumberPhone2,
                                                          String Code, String Oms_number, String Pump, String Oms_id,
                                                          String Status, String Parent, String Head,
                                                          String FIODirector) {
        Token =  editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID = editOrganizationAPI.createOrganizationAPI(Token, Parent);
        dateCreateOrg();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
//        EnvironmentOrganization.createOrganizationParent(Parent);
        editAdditionalInformationOrganization(OrgID, Name, DescriptionOrganization, NewAppointmentPhone1, NewNumberPhone1,
                NewAdress, NewSite, NewEmail, NewVk, NewFacebook, NewInstagram, NewOtherName, NewOtherValue, NewCode,
                NewRegistryNumber,
                NewIdentifierPUMP, NewIdentifierOMS, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, Code, Oms_number, Pump, Oms_id,
                Status, Parent, Head,
                FIODirector);
    }

    @Test   //6.6
    @Parameters(value = {
            "6.Измененное описание | Новое назначение телефона №1 | 9999999999 | Москва волков 4 | https://NEWsite.ru" +
                    " | NEW@mail.ru | " +
                    "https://NEWvk.com/ | https://NEWfacebook.com/ | https://NEWinstagram.com/ | NEW др.назв | NEW " +
                    "знач | ajdJK7| maLKJLK09^& | lkkJN32^& | vbjJHB987(* | " +

                    "6.Название отделения 6 | Отд.\"6\" | 5bf3be3422ee2270923814af | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | " +
                    "hg8799jlkj@mail.ru |" +
                    " https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa | ФГАОУ ВО " +
                    "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 > " +
                    "Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | Акулин Аристарх Анатольевич",

    })
    public void editAdditionalInformationOrganizationCancelTest(String DescriptionOrganization,
                                                                String NewAppointmentPhone1,
                                                                String NewNumberPhone1, String NewAdress,
                                                                String NewSite,
                                                                String NewEmail, String NewVk, String NewFacebook,
                                                                String NewInstagram,
                                                                String NewOtherName, String NewOtherValue,
                                                                String NewCode,
                                                                String NewRegistryNumber,
                                                                String NewIdentifierPUMP, String NewIdentifierOMS,

                                                                String Name, String Abbr, String Chief,
                                                                String Help_conditions, String Org_profile,
                                                                String Description, String Address, String GeodataX,
                                                                String GeodataY, String Site, String Email,
                                                                String Facebook,
                                                                String Instagram, String Vk, String OtherName,
                                                                String OtherValue,
                                                                String NamePhone1,
                                                                String NumberPhone1, String NamePhone2,
                                                                String NumberPhone2,
                                                                String Code, String Oms_number, String Pump,
                                                                String Oms_id, String Status, String Parent,
                                                                String Head, String FIODirector) {
        Token =  editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID =   editOrganizationAPI.createOrganizationAPI(Token, Parent);
        dateCreateOrg();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, GeodataY, Site, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1, NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);

        editAdditionalInformationOrganizationCancel(OrgID, Name, Abbr, DescriptionOrganization, NewAppointmentPhone1,
                NewNumberPhone1,
                NewAdress, NewSite, NewEmail, NewVk, NewFacebook, NewInstagram, NewOtherName, NewOtherValue, NewCode,
                NewRegistryNumber,
                NewIdentifierPUMP, NewIdentifierOMS, Status, Head, Help_conditions, Org_profile, FIODirector, Address
                , Site,
                Email, Facebook, Instagram, Vk, OtherName, OtherValue, Code, Oms_number, Pump, Oms_id, Description);
    }


    @Test   //6.7
    @Parameters(value = {
            "Новый телефон №2 | 99999999999 |" +

                    "7.Название отделения №7 | Отд. \"7\" | 5b58130f0faa2638b94fc4d7 | Поликлиника |" +
                    " Левое ухо | Описание организации | Москва | 55.753215 | 37.622504 | https://site.ru | " +
                    "hg8799jlkj@mail.ru| " +
                    "https://facebook.com/ | https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be2dc17371a1c6b17fbe6fa",

    })
    public void editPhonesOrganizationTest(String NewAppointmentPhone2, String NewNumberPhone2,

                                           String Name, String Abbr, String Chief,
                                           String Help_conditions, String Org_profile,
                                           String Description, String Address, String GeodataX,
                                           String GeodataY, String Site, String Email, String Facebook,
                                           String Instagram, String Vk, String OtherName, String OtherValue,
                                           String NamePhone1,
                                           String NumberPhone1, String NamePhone2, String NumberPhone2,
                                           String Code, String Oms_number, String Pump,
                                           String Oms_id, String Status, String Parent) {
        Token =  editOrganizationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID =  editOrganizationAPI.createOrganizationAPI(Token, Parent);
        dateCreateOrg();
        editOrganizationAPI.profileOrganizationAPI(Token, OrgID, Name, Abbr, Chief, Help_conditions, Org_profile, Description,
                Address, GeodataX, Site, GeodataY, Email, Facebook, Instagram, Vk, OtherName, OtherValue, NamePhone1,
                NumberPhone1,
                NamePhone2, NumberPhone2, Code, Oms_number, Pump, Oms_id, Status);
//        EnvironmentOrganization.createOrganizationParent(Parent);
        editPhonesOrganization(OrgID, Name, NamePhone1, NumberPhone1, NewAppointmentPhone2, NewNumberPhone2);
    }
}
