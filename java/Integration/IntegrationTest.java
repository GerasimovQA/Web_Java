package Integration;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class IntegrationTest extends IntegrationLogic {
    private IntegrationAPI integrationAPI = new IntegrationAPI();

    private String Token = null;
    private String UserID = null;
    private String UserID1 = null;
    private String UserID2 = null;
    private String UserID3 = null;
    private String OrgID = null;
    private String ServicesSectionID = null;
    private String ServiceID = null;
    private String ServiceID1 = null;
    private String ServiceID2 = null;
    private String ServiceID3 = null;
    private String SpecialtyID3 = null;
    private String SpecialtyID4 = null;


    @Test   //7.1
    @Parameters(value = {
            "Новый Код раздела услуг №1. | Новое Название раздела услуг \"1\" | Нов. Назв." +
                    " разд. усл. для печ. | Новый арт. | " +

                    "1.Код раздела услуг №1. | Название раздела услуг \"1\" | 000000000000000000000000 | Назв. разд. " +
                    "усл. для печ. | арт. 152 |" +

                    "Код услуги | Название услуги | Название услуги для печати |  | Артикул " +
                    "услуги | Противопоказания к услуге | String Creator | Описание услуги | Предусловия к услуге | " +
                    "Операция | 100 | 200 | 300 |  | false | false |" +

                    "SLn5345fdGI | nbdf5454wds | 04623423fs-sdk@gma12il.com | +7 (902) 011-68-81 | active | " +
                    "Коренной | Антон | Павлович | false | false | 5be180eb5a4c8615d6881f25 | Ref |" +
                    " Врач | doctor | 5bc44ce943aef1053d0adabc | 5bc44d0b43aef1053d0adabe | Высшее | " +
                    "qua@m12ail.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Новый Код услуги | Новое Название услуги | Нов. Назв. услуги для печати |" +
                    " 5bd6d8011c7030734863e056 | Новый Артикул услуги | Новые Противопоказания к услуге | Новый " +
                    "String Creator | Новое Описание услуги | Новые Предусловия к услуге | Операция | 111 | 222 |" +
                    " 333 |  | false | false",
    })
    public void servicesUserTest(String NewCodeSection, String NewNameSectionme, String NewPrintNameSection,
                                 String NewVendorSection,

                                 String CodeSection, String NameSection, String ParentSection,
                                 String PrintNameSection, String VendorSection,

                                 String CodeService, String NameService,
                                 String PrintNameService, String Parent, String VendorService,
                                 String ContraindicationsService, String CreatorService,
                                 String DescriptionService, String PreconditionService, String TypeService,
                                 String DmsCost, String OmsCost, String PmuCost, String OtherCosts,
                                 String Favorit, String Record,

                                 String Login, String Password, String Email, String Phone, String Status,
                                 String SecondName, String FirstName, String MiddleName, String Superuser,
                                 String SendEmail, String Depart, String Ref, String Post, String Role,
                                 String Spec1, String Spec2, String Regalia, String EmailCont,
                                 String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                 String Viber, String Facebook, String Other,

                                 String NewCodeService, String NewNameService,
                                 String NewPrintNameService, String NewParent, String NewVendorService,
                                 String NewContraindicationsService, String NewCreatorService,
                                 String NewDescriptionService, String NewPreconditionService,
                                 String NewTypeService, String NewDmsCost, String NewOmsCost, String NewPmuCost,
                                 String NewOtherCosts, String NewFavorit, String NewRecord) {
        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        ServicesSectionID = integrationAPI.createServicesSectionAPI(Token, CodeSection, NameSection, ParentSection, PrintNameSection,
                VendorSection);
        ServiceID = integrationAPI.createServiceAPI(Token, ServicesSectionID, CodeService, NameService, PrintNameService, Parent,
                VendorService, ContraindicationsService, CreatorService, DescriptionService, PreconditionService,
                TypeService, DmsCost, OmsCost, PmuCost, OtherCosts, Favorit, Record);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        compareServiceUserOne(UserID, SecondName, NameService, VendorService, DmsCost, OmsCost, PmuCost, OtherCosts);

        integrationAPI.editServicesSectionAPI(Token, ServicesSectionID, NewCodeSection, NewNameSectionme, NewPrintNameSection,
                NewVendorSection);
        compareServiceUserOne(UserID, SecondName, NameService, VendorService, DmsCost, OmsCost, PmuCost, OtherCosts);

        integrationAPI.editServiceAPI(Token, ServiceID, NewCodeService, NewNameService, NewPrintNameService,
                NewVendorService, NewContraindicationsService, NewCreatorService, NewDescriptionService,
                NewPreconditionService, NewTypeService, NewDmsCost, NewOmsCost, NewPmuCost, NewOtherCosts,
                NewFavorit, NewRecord);
        integrationAPI.editServiceParentAPI(Token, ServiceID, NewParent);
        compareServiceUserTwo(NewNameService, NewVendorService, NewDmsCost, NewOmsCost, NewPmuCost, NewOtherCosts);

        integrationAPI.deleteServiceAPI(Token, ServiceID);
        compareServiceUserThree();
    }

    @Test   //7.2
    @Parameters(value = {
            "Название специальности | Описание спец. | neurosurgeon | 000000000000000000000001 |" +

                    "SLnjdg6754I | nbdsd25645wds | 04ksdk@gma12il.com | +7 (902) 864-35-81 | active | " +
                    "Веткин | Александр | Павлович | false | false | 5be180eb5a4c8615d6881f25 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 | " +
                    "Высшее | qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Новое Назв. спец. | Новое Описание спец. | chiropractor | 000000000000000000000002 |" +

                    "Название специальности 2 | Описание спец.2 | neurosurgeon | 000000000000000000000002",
    })
    public void specialtysUserTest(String NameSpecialty1, String Description1, String Icon1, String Parent1,

                                   String Login, String Password, String Email, String Phone, String Status,
                                   String SecondName, String FirstName, String MiddleName, String Superuser,
                                   String SendEmail, String Depart, String Ref, String Post, String Role,
                                   String Spec1, String Spec2, String Service, String Regalia, String EmailCont,
                                   String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                   String Viber, String Facebook, String Other,

                                   String NewNameSpecialty, String NewDescription, String NewIcon, String NewParent,

                                   String NameSpecialty2, String Description2, String Icon2, String Parent2) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        SpecialtyID3 = integrationAPI.createSpecialtyAPI(Token, NameSpecialty1, Description1, Icon1, Parent1);
        SpecialtyID4 = integrationAPI.createSpecialtyAPI(Token, NameSpecialty2, Description2, Icon2, Parent2);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, Service, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        integrationAPI.editUserSpecialtyAPI(Token, UserID, Spec1, Spec2, SpecialtyID3, SpecialtyID4);
        compareSpecialtyUserOne(UserID, SecondName, FirstName, MiddleName);

        integrationAPI.editSpecialtyAPI(Token, Spec1, NewNameSpecialty, NewDescription);
        integrationAPI.editParentSpecialtyAPI(Token, Spec2, NewIcon, NewParent);
        compareSpecialtyUserTwo(SecondName, FirstName, MiddleName);

        integrationAPI.deleteSpecialtyAPI(Token, Spec1);
        compareSpecialtyUserThree(SecondName, FirstName, MiddleName);


    }


    @Test   //7.3
    @Parameters(value = {
            "Название организации| АББРОРГ | | Поликлиника | Левое ухо | Описание организации |" +
                    " Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@21ru| https://facebook.com/ |" +
                    " https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5b7d08aa068d7552d904d8be | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ " +
                    "им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 " +
                    "> Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | | | | |" +

                    "SLnjdmbkfhafdGI | n548fidwds | y9kfs-sdk@gmai12l.com | +7 (902) 011-30-81 | active | " +
                    "Листьев | Влад | Павлович | false | false | | Ref |" +
                    " Врач | doctor | 5bc44ce943aef1053d0adabc | 5bc44d0b43aef1053d0adabe | Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Новое название организации, 5be94fd31bf3332af1f646d9",
    })
    public void organizationUserTest(String NameOrg, String AbbrOrg, String ChiefOrg, String Help_conditionsOrg,
                                     String Org_profileOrg, String DescriptionOrg, String AddressOrg, String GeodataX,
                                     String GeodataY, String SiteOrg, String EmailOrg, String FacebookOrg,
                                     String InstagramOrg, String VkOrg, String OtherNameOrg, String OtherValueOrg,
                                     String NamePhone1Org, String NumberPhone1Org, String NamePhone2Org,
                                     String NumberPhone2Org, String CodeOrg, String Oms_number, String Pump,
                                     String Oms_id, String StatusOrg, String ParentOrg, String CheckedAndFocus,
                                     String Checkeds, String Indeterminate, String Empty, String HeadOrg,
                                     String FIODirectorOrg, String Service1, String Service2, String Service3,

                                     String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser,
                                     String SendEmail, String Depart, String Ref, String Post, String Role,
                                     String Spec1, String Spec2, String Regalia, String EmailCont,
                                     String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                     String Viber, String Facebook, String Other,

                                     String NewNameOrg, String NewNParentOrg) {
        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID = integrationAPI.createOrganizationAPI(Token, ParentOrg);
        integrationAPI.profileOrganizationAPI(Token, OrgID, NameOrg, AbbrOrg, ChiefOrg, Help_conditionsOrg,
                Org_profileOrg, DescriptionOrg, AddressOrg, GeodataX, GeodataY, SiteOrg, EmailOrg, FacebookOrg,
                InstagramOrg, VkOrg, OtherNameOrg, OtherValueOrg, NamePhone1Org, NumberPhone1Org, NamePhone2Org,
                NumberPhone2Org, CodeOrg, Oms_number, Pump, Oms_id, StatusOrg, Service1, Service2, Service3);

        integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        compareOrganizationUserOne(UserID, SecondName, NameOrg);

        integrationAPI.editNameOrganizationAPI(Token, OrgID, NewNameOrg);
        compareOrganizationUserTwo(NewNameOrg);

        integrationAPI.editParentOrganizationAPI(Token, OrgID, NewNParentOrg);
        compareOrganizationUserThree(NewNameOrg);

        integrationAPI.deleteOrganizationAPI(Token, OrgID);
        compareOrganizationUserFour();
    }

    @Test   //7.4
    @Parameters(value = {
            "Код услуги 1 | Название услуги 1 | Название услуги для печати | 5bd6d8011c7030734863e056 | Артикул услуги" +
                    " 1 | Противопоказания к услуге | String Creator | Описание услуги | Предусловия к услуге | " +
                    "Операция | 100 | 200 | 300 |  | false | false |" +

                    "Код услуги 2 | Название услуги 2 | Название услуги для печати | 5bd6d8011c7030734863e056 |" +
                    " Артикул услуги 2 | Противопоказания к услуге | String Creator | Описание услуги |" +
                    " Предусловия к услуге | Операция | 99 | 199 | 299 |  | false | false | " +

                    "Код услуги 3 | Название услуги 3 | Название услуги для печати | 5bd6d8011c7030734863e056 |" +
                    " Артикул услуги 3 | Противопоказания к услуге | String Creator | Описание услуги |" +
                    " Предусловия к услуге | Операция | 101 | 201 | 301 |  | false | false |" +

                    "Название организации 2 | АББРОРГ 2 | | Поликлиника | Левое ухо | Описание организации |" +
                    " Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@ma21il.ru| https://facebook.com/ |" +
                    " https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 5be94fd31bf3332af1f646d9 | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ " +
                    "им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 " +
                    "> Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | | 5bc0d40b5fdf7508cc91db03 |" +
                    " 5bc0d4b55fdf7508cc91db08 | 5bc0d60f5fdf7508cc91db0e |" +

                    "Новый Код услуги | Новое Название услуги | Нов. Назв. услуги для печати | " +
                    "5bc0d3425fdf7508cc91dafb | Новый Артикул услуги | Новые Противопоказания к услуге |" +
                    " Новый String Creator | Новое Описание услуги | Новые Предусловия к услуге | Операция |" +
                    " 111 | 222 | 333 | 444 | false | false |" +

                    "SLn55f111 | nf545111 | 04623423fs-s111@gma21il.com | +7 (902) 111-68-81 | active | Азов | " +
                    "Антон | Павлович | false | false | 5be180eb5a4c8615d6881f25 | Ref | Врач | doctor | " +
                    "5bc44ce943aef1053d0adabc | 5bc44d0b43aef1053d0adabe | Высшее | qua@ma21il.ru | 7954357543 | INSTA " +
                    "| VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "SLn45f222 | nbf5454222 | 04623423222-sdk@gma21il.com | +7 (902) 222-68-81 | active | Азин | " +
                    "Боис | Иванович | false | false | 5be180eb5a4c8615d6881f25 | Ref | Доктор | doctor | " +
                    "5bc44ce943aef1053d0adabc | 5bc44d0b43aef1053d0adabe | Высшее | qua@ma12il.ru | 7954357543 | INSTA " +
                    "| VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "SL345f333 | nbf5454333 | 04623423333-sdk@gm23ail.com | +7 (902) 333-68-81 | active | Азор | " +
                    "Вадим | Игоревич | false | false | 5be180eb5a4c8615d6881f25 | Ref | Хирург | doctor | " +
                    "5bc44ce943aef1053d0adabc | 5bc44d0b43aef1053d0adabe | Высшее | qua@ma45il.ru | 7954357543 | INSTA " +
                    "| VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Игнатьев | Валентин | Васильевич | Доктор твоего тела",
    })
    public void servicesOrgTest(String CodeService1, String NameService1, String PrintNameService1, String Parent1,
                                String VendorService1, String ContraindicationsService1, String CreatorService1,
                                String DescriptionService1, String PreconditionService1, String TypeService1,
                                String DmsCost1, String OmsCost1, String PmuCost1, String OtherCosts1, String Favorit1,
                                String Record1,

                                String CodeService2, String NameService2, String PrintNameService2, String Parent2,
                                String VendorService2, String ContraindicationsService2, String CreatorService2,
                                String DescriptionService2, String PreconditionService2, String TypeService2,
                                String DmsCost2, String OmsCost2, String PmuCost2, String OtherCosts2, String Favorit2,
                                String Record2,

                                String CodeService3, String NameService3, String PrintNameService3, String Parent3,
                                String VendorService3, String ContraindicationsService3, String CreatorService3,
                                String DescriptionService3, String PreconditionService3, String TypeService3,
                                String DmsCost3, String OmsCost3, String PmuCost3, String OtherCosts3, String Favorit3,
                                String Record3,

                                String NameOrg, String AbbrOrg, String ChiefOrg, String Help_conditionsOrg,
                                String Org_profileOrg, String DescriptionOrg, String AddressOrg, String GeodataX,
                                String GeodataY, String SiteOrg, String EmailOrg, String FacebookOrg,
                                String InstagramOrg, String VkOrg, String OtherNameOrg, String OtherValueOrg,
                                String NamePhone1Org, String NumberPhone1Org, String NamePhone2Org,
                                String NumberPhone2Org, String CodeOrg, String Oms_number, String Pump,
                                String Oms_id, String StatusOrg, String ParentOrg, String CheckedAndFocus,
                                String Checkeds, String Indeterminate, String Empty, String HeadOrg,
                                String FIODirectorOrg, String Service1, String Service2, String Service3,

                                String NewCodeService1, String NewNameService1, String NewPrintNameService1,
                                String NewParent1, String NewVendorService1, String NewContraindicationsService1,
                                String NewCreatorService1, String NewDescriptionService1, String NewPreconditionService1,
                                String NewTypeService1, String NewDmsCost1, String NewOmsCost1, String NewPmuCost1,
                                String NewOtherCosts1, String NewFavorit1, String NewRecord1,

                                String Login1, String Password1, String Email1, String Phone1, String Status1,
                                String SecondName1, String FirstName1, String MiddleName1, String Superuser1,
                                String SendEmail1, String Depart1, String Ref1, String Post1, String Role1,
                                String Spec11, String Spec21, String Regalia1, String EmailCont1,
                                String PhoneCont1, String Instagram1, String Vk1, String Whatsapp1,
                                String Viber1, String Facebook1, String Other1,

                                String Login2, String Password2, String Email2, String Phone2, String Status2,
                                String SecondName2, String FirstName2, String MiddleName2, String Superuser2,
                                String SendEmail2, String Depart2, String Ref2, String Post2, String Role2,
                                String Spec12, String Spec22, String Regalia2, String EmailCont2,
                                String PhoneCont2, String Instagram2, String Vk2, String Whatsapp2,
                                String Viber2, String Facebook2, String Other2,

                                String Login3, String Password3, String Email3, String Phone3, String Status3,
                                String SecondName3, String FirstName3, String MiddleName3, String Superuser3,
                                String SendEmail3, String Depart3, String Ref3, String Post3, String Role3,
                                String Spec13, String Spec23, String Regalia3, String EmailCont3,
                                String PhoneCont3, String Instagram3, String Vk3, String Whatsapp3,
                                String Viber3, String Facebook3, String Other3,

                                String NewSecondName1, String NewFirstName1, String NewMiddleName1, String NewPost1) {
        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);

        ServiceID1 = integrationAPI.createServiceOrgAPI(Token, CodeService1, NameService1, PrintNameService1, Parent1,
                VendorService1, ContraindicationsService1, CreatorService1, DescriptionService1, PreconditionService1,
                TypeService1, DmsCost1, OmsCost1, PmuCost1, OtherCosts1, Favorit1, Record1);
        ServiceID2 = integrationAPI.createServiceOrgAPI(Token, CodeService2, NameService2, PrintNameService2, Parent2,
                VendorService2, ContraindicationsService2, CreatorService2, DescriptionService2, PreconditionService2,
                TypeService2, DmsCost2, OmsCost2, PmuCost2, OtherCosts2, Favorit2, Record2);
        ServiceID3 = integrationAPI.createServiceOrgAPI(Token, CodeService3, NameService3, PrintNameService3, Parent3,
                VendorService3, ContraindicationsService3, CreatorService3, DescriptionService3, PreconditionService3,
                TypeService3, DmsCost3, OmsCost3, PmuCost3, OtherCosts3, Favorit3, Record3);

        OrgID = integrationAPI.createOrganizationAPI(Token, ParentOrg);
        integrationAPI.profileOrganizationOrgAPI(Token, OrgID, NameOrg, AbbrOrg, ChiefOrg, Help_conditionsOrg,
                Org_profileOrg, DescriptionOrg, AddressOrg, GeodataX, GeodataY, SiteOrg, EmailOrg, FacebookOrg,
                InstagramOrg, VkOrg, OtherNameOrg, OtherValueOrg, NamePhone1Org, NumberPhone1Org, NamePhone2Org,
                NumberPhone2Org, CodeOrg, Oms_number, Pump, Oms_id, StatusOrg, ServiceID1, ServiceID2, ServiceID3);
        compareServiceOrgOne(OrgID, NameOrg, NameService1, VendorService1, DmsCost1, OmsCost1, PmuCost1, NameService2,
                VendorService2, DmsCost2, OmsCost2, PmuCost2, NameService3, VendorService3, DmsCost3, OmsCost3,
                PmuCost3);

        UserID1 = integrationAPI.createUserOrgAPI(Token, OrgID, Login1, Password1, Email1, Phone1, Status1, SecondName1, FirstName1,
                MiddleName1, Superuser1, SendEmail1, Depart1, Post1, Role1);
        integrationAPI.profileUserOrg1APInew(Token, UserID1, ServiceID1, Depart1, Ref1, Post1, Status1, Role1, Spec11, Spec21, Regalia1,
                EmailCont1, PhoneCont1, Instagram1, Vk1, Whatsapp1, Viber1, Facebook1, Other1);
        UserID2 = integrationAPI.createUserOrgAPI(Token, OrgID, Login2, Password2, Email2, Phone2, Status2, SecondName2, FirstName2,
                MiddleName2, Superuser2, SendEmail2, Depart2, Post2, Role2);
        integrationAPI.profileUserOrg2APInew(Token, UserID2, ServiceID1, ServiceID2, Depart2, Ref2, Post2, Status2, Role2, Spec12, Spec22, Regalia2,
                EmailCont2, PhoneCont2, Instagram2, Vk2, Whatsapp2, Viber2, Facebook2, Other2);
        UserID3 = integrationAPI.createUserOrgAPI(Token, OrgID, Login3, Password3, Email3, Phone3, Status3, SecondName3, FirstName3,
                MiddleName3, Superuser3, SendEmail3, Depart3, Post3, Role3);
        integrationAPI.profileUserOrg3APInew(Token, UserID3, ServiceID1, ServiceID2, ServiceID3, Depart3, Ref3, Post3, Status3, Role3, Spec13, Spec23, Regalia3,                EmailCont3, PhoneCont3, Instagram3, Vk3, Whatsapp3, Viber3, Facebook3, Other3);
        compareServiceOrgTwo(NameOrg, NameService1, VendorService1, DmsCost1, OmsCost1, PmuCost1, NameService2,
                VendorService2, DmsCost2, OmsCost2, PmuCost2, NameService3, VendorService3, DmsCost3, OmsCost3,
                PmuCost3, SecondName1, FirstName1, MiddleName1, Post1, SecondName2, FirstName2, MiddleName2, Post2, SecondName3,
                FirstName3, MiddleName3, Post3);

        integrationAPI.editServiceAPI(Token, ServiceID1, NewCodeService1, NewNameService1, NewPrintNameService1,
                NewVendorService1, NewContraindicationsService1, NewCreatorService1, NewDescriptionService1,
                NewPreconditionService1, NewTypeService1, NewDmsCost1, NewOmsCost1, NewPmuCost1, NewOtherCosts1, NewFavorit1, NewRecord1);
        integrationAPI.editServiceParentAPI(Token, ServiceID1, NewParent1);
        integrationAPI.editUserNamesAPI(Token, UserID1, NewSecondName1, NewFirstName1, NewMiddleName1);
        integrationAPI.editUserChangeWorkplaceAPI(Token, UserID1, OrgID, Depart1, NewPost1, Role1, Status1);
        compareServiceOrgThree(NewNameService1, NewVendorService1, NewDmsCost1, NewOmsCost1, NewPmuCost1, NameService2,
                VendorService2, DmsCost2, OmsCost2, PmuCost2, NameService3, VendorService3, DmsCost3, OmsCost3,
                PmuCost3, NewSecondName1, NewFirstName1, NewMiddleName1, NewPost1, SecondName2, FirstName2, MiddleName2,
                Post2, SecondName3, FirstName3, MiddleName3, Post3);

        integrationAPI.deleteServiceAPI(Token, ServiceID1);
        compareServiceOrgFour(NameService2, VendorService2, DmsCost2, OmsCost2, PmuCost2, NameService3, VendorService3,
                DmsCost3, OmsCost3, PmuCost3);
    }

    @Test   //7.8
    @Parameters(value = {
            "1rieurwoei | nbdsd25df645wds | test50@sech.lionsdigital.pro | +7 (902) 000-35-81 | active | " +
                    "Карлсон | Коловрат | Индустриалович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Шапокляк | Изольда | Марксовна | test51@sech.lionsdigital.pro | +3 (000) 000-11-11 | slkdjfskljdf",

            "2uytwoei | nbdsd25wds | test52@sech.lionsdigital.pro | +7 (902) 000-11-12 | active | " +
                    "Крюгер | Эскалоп | Лениннович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Чайкин | Джонатан | Ливингстонович | test53@sech.lionsdigital.pro | +3 (000) 000-11-13 | 53454df",

            "3hgg47uytwoei | nbd5wds | test54@sech.lionsdigital.pro | +7 (902) 000-11-14 | active | " +
                    "Рыцарев | Арагорн | Батькович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Колдунов | Саурон | Саруманович | test55@sech.lionsdigital.pro | +3 (000) 000-11-15 | slkd2204df",

            "4hgрорoei | nbdrwerwds | test56@sech.lionsdigital.pro | +7 (902) 000-11-16 | active | " +
                    "Орков | Назгул | Чернорыцаревич | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Гномов | Гимли | Гогигович | test57@sech.lionsdigital.pro | +3 (000) 000-11-17 | sl873uif",

            "5hgр8jhei | nbdskwds | test58@sech.lionsdigital.pro | +7 (902) 000-11-18 | active | " +
                    "Хоббитов | Фродо | Ширович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Эльфов | Легалас | Блумович | test59@sech.lionsdigital.pro | +3 (000) 000-11-19 | slkd2dgf",

            "6ddsjhei | nbds67567s | test60@sech.lionsdigital.pro | +7 (902) 000-11-20 | active | " +
                    "Сумкин | Бильбо | Начальнич | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    "Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "Волшебников | Гендальф | Чародеевич | test61@sech.lionsdigital.pro | +3 (000) 000-11-21 | slkdу43gf",
    })
    public void changeUserSecondFistMiddleNameTest(String Login, String Password, String Email, String Phone, String Status,
                                                   String SecondName, String FirstName, String MiddleName, String Superuser,
                                                   String SendEmail, String Depart, String Ref, String Post, String Role,
                                                   String Spec1, String Spec2, String Service, String Regalia, String EmailCont,
                                                   String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                                   String Viber, String Facebook, String Other,

                                                   String NewSecondName, String NewFirstName, String NewMiddleName, String NewEmail,
                                                   String NewPhone, String NewLogin) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        integrationAPI.editUserNamesAPI(Token, UserID, NewSecondName, NewFirstName, NewMiddleName);
        integrationAPI.editUserAuthAPI(Token, UserID, NewEmail, NewPhone, NewLogin);
        changeUserSecondFistMiddleName(Login, Email, Phone, SecondName, FirstName, MiddleName, NewSecondName, NewFirstName,
                NewMiddleName, NewEmail, NewPhone, NewLogin);
    }

    @Test   //7.9
    @Parameters(value = {
            "7rie8678ei | nbdsd25df645wds | gdgerg@sech.lionsdigital.pro | +7 (902) 000-11-22 | active | " +
                    "Джонатанидзе | Иоанн Ш | Мадестович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "5b7d08aa068d7552d904d8be | Лаборант | doctor | active",

            "8riedsgehei | nbdsd25df645wds | bcvbehh@sech.lionsdigital.pro | +7 (902) 000-11-23 | active | " +
                    "Ганибалов | Энтони | Хопкинс | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач лечащий людей | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "5b7d08aa068d7552d904d8be | Главный по анализам | doctor | active",

            "9riefgtoei | nbdsd25df645wds | htrfgf@sech.lionsdigital.pro | +7 (902) 000-11-24 | active | " +
                    "Дженифер | Энистон | Безотчественна | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "5b7d08aa068d7552d904d8be | Мойщик шприцов | admin | active",

    })
    public void changeUserOrgPostRoleTest(String Login, String Password, String Email, String Phone, String Status,
                                          String SecondName, String FirstName, String MiddleName, String Superuser,
                                          String SendEmail, String Depart, String Ref, String Post, String Role,
                                          String Spec1, String Spec2, String Regalia, String EmailCont,
                                          String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                          String Viber, String Facebook, String Other,

                                          String NewDepart, String NewPost, String NewRole, String NewStatus) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        integrationAPI.editUserDeleteWorkplaceAPI(Token, UserID, Depart);
        integrationAPI.editUserAddWorkplaceAPI(Token, UserID, NewDepart, NewPost, NewRole, NewStatus);

        changeUserOrgPostRole(Login, NewDepart, NewPost, NewRole, SecondName, FirstName, MiddleName);
    }

    @Test   //7.10
    @Parameters(value = {
            "10aaie8678ei | nbdsd25dsddf645wds | sdsdrg@sech.lionsdigital.pro | +7 (902) 000-11-25 | active | " +
                    "Пит | Бред | Голливудович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    " Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "5b7d08aa068d7552d904d8be | Лаборант | doctor | active",
    })
    public void changeUserStatusWorkTest(String Login, String Password, String Email, String Phone, String Status,
                                         String SecondName, String FirstName, String MiddleName, String Superuser,
                                         String SendEmail, String Depart, String Ref, String Post, String Role,
                                         String Spec1, String Spec2, String Regalia, String EmailCont,
                                         String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                         String Viber, String Facebook, String Other,

                                         String NewDepart, String NewPost, String NewRole, String NewStatus) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
//        АПИ на изменение статуса работы
//        EnvironmentIntegration.editUserDeleteWorkplaceAPI(Depart);
//        EnvironmentIntegration.editUserAddWorkplaceAPI(NewDepart, NewPost, NewRole, NewStatus);

        changeUserStatusWork(Login, SecondName, FirstName, MiddleName);
    }

    @Test   //7.11
    @Parameters(value = {
            "11riefg | nbds645wds | htr2388fgf@sech.lionsdigital.pro | +7 (902) 000-11-26 | active | " +
                    "Стоун | Шерон | Смитовна | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    "Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER |" +

                    "closed",

    })
    public void changeUserStatusInSystemTest(String Login, String Password, String Email, String Phone, String Status,
                                             String SecondName, String FirstName, String MiddleName, String Superuser,
                                             String SendEmail, String Depart, String Ref, String Post, String Role,
                                             String Spec1, String Spec2, String Regalia, String EmailCont,
                                             String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                             String Viber, String Facebook, String Other,

                                             String NewStatus) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        integrationAPI.editUserStatusInSystemAPI(Token, UserID, NewStatus);

        changeUserStatusInSystem(Login, Status, NewStatus, SecondName, FirstName, MiddleName);
    }


    @Test   //7.12
    @Parameters(value = {
            "12riefg | nbddfwds | htr2cvf@sech.lionsdigital.pro | +7 (902) 000-11-27 | active | " +
                    "Джоли | Анджелина | Питовна | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    "Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",

    })
    public void changeUserStatusOnOffTest(String Login, String Password, String Email, String Phone, String Status,
                                          String SecondName, String FirstName, String MiddleName, String Superuser,
                                          String SendEmail, String Depart, String Ref, String Post, String Role,
                                          String Spec1, String Spec2, String Regalia, String EmailCont,
                                          String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                          String Viber, String Facebook, String Other) {
        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        changeUserStatusOnOff(Login, "Оффлайн", "Онлайн", SecondName, FirstName, MiddleName);

        integrationAPI.loginAuth(Login, Password);
        changeUserStatusOnOff(Login, "Онлайн", "Оффлайн", SecondName, FirstName, MiddleName);

        integrationAPI.logoutAllUserSessionsAPI(Token, UserID);
        changeUserStatusOnOff(Login, "Оффлайн", "Онлайн", SecondName, FirstName, MiddleName);
    }

    @Test   //7.13
    @Parameters(value = {
            "13cghriefg | nbdssd645wds | hxctr2388fgf@sech.lionsdigital.pro | +7 (902) 000-11-28 | active | " +
                    "Агрономов | Венеамин | Полиглотович | false | false | 5be94fd31bf3332af1f646d9 | Ref |" +
                    " Врач | doctor | 5bc0a51d43aef1053d0ada8f | 5bd6d875dd89ae51736eca52 | 5bd6d7d21c7030734863e054 |" +
                    "Высшее | " +
                    "qua@ma12il.ru | 7954357543 | INSTA | VK | WATSAPP | VIBER | FACEBOOK | OTHER",
    })
    public void changeUserGlobalRoleTest(String Login, String Password, String Email, String Phone, String Status,
                                         String SecondName, String FirstName, String MiddleName, String Superuser,
                                         String SendEmail, String Depart, String Ref, String Post, String Role,
                                         String Spec1, String Spec2, String Regalia, String EmailCont,
                                         String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                         String Viber, String Facebook, String Other) {

        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = integrationAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, Depart, Post, Role);
        integrationAPI.profileUserAPInew(Token, UserID, ServiceID, Depart, Ref, Post, Status, Role, Spec1, Spec2, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        integrationAPI.editUserGlobalRoleAPI(Token, UserID);

        changeUserGlobalRole(Login, SecondName, FirstName, MiddleName);
    }

    @Test   //7.14
    @Parameters(value = {
            "Дамблдор| Дамб | | Поликлиника | Левое ухо | Описание организации |" +
                    " Москва | 55.753215 | 37.622504 | https://site.ru | hg8799jlkj@21ru| https://facebook.com/ |" +
                    " https://instagram.com/ | https://vk.com/ | Другое | !:*%675ывырп |" +
                    " Телефон №1 | +7 (000) 111-22-33 | \"Телефон №2\" | +7 (111) 222-33-44 | 78564hjghjKJH |" +
                    " vmnbvJHFH66365 | jlkjTF86857 | JKHKJHnbvnb768 | active | 000000000000000000000000 | " +
                    "el-checkbox__input is-checked is-focus | el-checkbox__input is-checked | " +
                    "el-checkbox__input is-indeterminate | el-checkbox__input | ФГАОУ ВО Первый МГМУ " +
                    "им. И.М. Сеченова Минздрава России (Сеченовский Университет) > Кардиоцентр №1 " +
                    "> Кардиоцентр №2 > Кардиоцентр №3 > Кардиоцентр №4 | | | | |" +

                    "Чёрный замок, 5be2dbaf371a1c6b17fbe6ee",
    })
    public void changeTreeOrgTest(String NameOrg, String AbbrOrg, String ChiefOrg, String Help_conditionsOrg,
                                  String Org_profileOrg, String DescriptionOrg, String AddressOrg, String GeodataX,
                                  String GeodataY, String SiteOrg, String EmailOrg, String FacebookOrg,
                                  String InstagramOrg, String VkOrg, String OtherNameOrg, String OtherValueOrg,
                                  String NamePhone1Org, String NumberPhone1Org, String NamePhone2Org,
                                  String NumberPhone2Org, String CodeOrg, String Oms_number, String Pump,
                                  String Oms_id, String StatusOrg, String ParentOrg, String CheckedAndFocus,
                                  String Checkeds, String Indeterminate, String Empty, String HeadOrg,
                                  String FIODirectorOrg, String Service1, String Service2, String Service3,

                                  String NewNameOrg, String NewNParentOrg) {
        Token = integrationAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        OrgID = integrationAPI.createOrganizationAPI(Token, ParentOrg);
        integrationAPI.profileOrganizationAPI(Token, OrgID, NameOrg, AbbrOrg, ChiefOrg, Help_conditionsOrg,
                Org_profileOrg, DescriptionOrg, AddressOrg, GeodataX, GeodataY, SiteOrg, EmailOrg, FacebookOrg,
                InstagramOrg, VkOrg, OtherNameOrg, OtherValueOrg, NamePhone1Org, NumberPhone1Org, NamePhone2Org,
                NumberPhone2Org, CodeOrg, Oms_number, Pump, Oms_id, StatusOrg, Service1, Service2, Service3);
        compareTreeOrgOne(NameOrg, AbbrOrg);

        integrationAPI.editNameOrganizationAPI(Token, OrgID, NewNameOrg);
        integrationAPI.editParentOrganizationAPI(Token, OrgID, NewNParentOrg);
        compareTreeOrgTwo(NameOrg, NewNameOrg);


    }
}
