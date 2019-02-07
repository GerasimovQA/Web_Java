package Service.EditService;

import Global.GlobalPage;
import junitparams.Parameters;
import org.junit.Test;

public class EditServiceTest extends EditServiceLogic {
    private EditServiceAPI editServiceAPI = new EditServiceAPI();

    private String Token = null;
    private String ServiceID = null;

    @Test   //15.1
    @Parameters(value = {
            "1 Код для услуги 1 | Название для услуги 1 | Название для услуги для печати 1 |  5bc0d3595fdf7508cc91dafd | " +
                    "Артикул для услуги 1 | Противопоказания для услуги 1 | String Creator | Описание для услуги 1 |" +
                    " Предусловия для услуги 1 | Операция | 1001 | 2002 | 3003 | |  | false | false |" +

                    "Новый Код для услуги 1 | Новое Название для услуги 1 | Новое Название для услуги для печати 1 |" +
                    "  5bd6d8011c7030734863e056 | Новый Артикул для услуги 1 | Новые Противопоказания для услуги 1 |" +
                    " String Creator | Новое Описание для услуги 1 | Новые Предусловия для услуги 1 | Операция |" +
                    " 10011 | 20022 | 30033 | | | false | false ",

            "2 Код для услуги 2 | Название для услуги 2 | Название для услуги для печати 2 |  5bd6d8011c7030734863e056 | " +
                    "Артикул для услуги 2 | Противопоказания для услуги 2 | String Creator | Описание для услуги 2 |" +
                    " Предусловия для услуги 2 | Операция | 1003 | 2003 | 3003 | | | false | false |" +

                    "Новый Код для услуги 2 | Новое Название для услуги 2 | Новое Название для услуги для печати 2 |" +
                    "  5bd6d8011c7030734863e056 | Новый Артикул для услуги 2 | Новые Противопоказания для услуги 2 |" +
                    " String Creator | Новое Описание для услуги 2 | Новые Предусловия для услуги 2 | Операция |" +
                    " 1004 | 2004 | 3004 | | | false | false ",
    })
    public void editServiceTest(String CodeService, String NameService,
                                String PrintNameService, String Parent, String VendorService,
                                String ContraindicationsService, String CreatorService,
                                String DescriptionService, String PreconditionService, String TypeService,
                                String DmsCost, String OmsCost, String PmuCost, String NameOtherCosts, String OtherCosts,
                                String Favorit, String Record,

                                String NewCodeService, String NewNameService,
                                String NewPrintNameService, String NewParent, String NewVendorService,
                                String NewContraindicationsService, String NewCreatorService,
                                String NewDescriptionService, String NewPreconditionService, String NewTypeService,
                                String NewDmsCost, String NewOmsCost, String NewPmuCost, String NewNameOtherCosts,
                                String NewOtherCosts,
                                String NewFavorit, String NewRecord) {
        Token = editServiceAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        ServiceID = editServiceAPI.createServiceAPI(Token, CodeService, NameService, PrintNameService, Parent, VendorService,
                ContraindicationsService, CreatorService, DescriptionService, PreconditionService, TypeService,
                DmsCost, OmsCost, PmuCost, NameOtherCosts, OtherCosts, Favorit, Record);
        editService(ServiceID, CodeService, NameService, PrintNameService, VendorService, ContraindicationsService,
                DescriptionService, PreconditionService, TypeService, DmsCost, OmsCost, PmuCost, OtherCosts,
                NewCodeService, NewNameService, NewPrintNameService, NewVendorService, NewContraindicationsService,
                NewDescriptionService, NewPreconditionService, NewDmsCost, NewOmsCost, NewPmuCost, NewOtherCosts);
    }

    @Test   //15.2
    @Parameters(value = {
            "1 Код для Раздела 1 | 1 Название для раздела 1 | 5bd6d8011c7030734863e056 | Название раздела для печати 1 |" +
                    " Арт для раздела 1 |" +

                    "1 Новый Код для Раздела 1 | 1 Новое Название для раздела 1 | 5bc0d3425fdf7508cc91dafb |" +
                    " Новое Название раздела для печати 1 | Новый Арт для раздела 1",

            "2 Код для Раздела 2 | 2 Название для раздела 2 | 5bd6d8011c7030734863e056 | Название раздела для печати 2 |" +
                    " Арт для раздела 2|" +

                    "2 Новый Код для Раздела 2 | 2 Новое Название для раздела 2 | 5bc0d3425fdf7508cc91dafb |" +
                    " Новое Название раздела для печати 2 | Новый Арт для раздела 2"
    })
    public void editSectionService(String CodeSectionService, String NameSectionService, String Parent,
                                   String PrintNameSectionService, String VendorSectionService,

                                   String NewCodeSectionService, String NewNameSectionService, String NewParent,
                                   String NewPrintNameSectionService, String NewVendorSectionService) {
        Token = editServiceAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        String sectionServiceID = editServiceAPI.createServicesSectionAPI(Token, CodeSectionService, NameSectionService, Parent,
                PrintNameSectionService, VendorSectionService);
        editSectionService(sectionServiceID, NameSectionService, PrintNameSectionService, CodeSectionService, VendorSectionService,
                NewNameSectionService, NewPrintNameSectionService, NewCodeSectionService, NewVendorSectionService);
    }

    @Test   //15.3
    @Parameters(value = {
            "3 Код для услуги 3 | Название для услуги 3 | Название для услуги для печати 3 |  5bd6d8011c7030734863e056 | " +
                    "Артикул для услуги 3 | Противопоказания для услуги 3 | String Creator | Описание для услуги 3 |" +
                    " Предусловия для услуги 3 | Операция | 1001 | 2002 | 3003 | |  | false | false",

            "4 Код для услуги 4 | Название для услуги 4 | Название для услуги для печати 4 |  5bd6d8011c7030734863e056 | " +
                    "Артикул для услуги 4 | Противопоказания для услуги 4 | String Creator | Описание для услуги 4 |" +
                    " Предусловия для услуги 4 | Операция | 1003 | 2003 | 3003 | |  | false | false",
    })
    public void deleteServiceTest(String CodeService, String NameService,
                                  String PrintNameService, String Parent, String VendorService,
                                  String ContraindicationsService, String CreatorService,
                                  String DescriptionService, String PreconditionService, String TypeService,
                                  String DmsCost, String OmsCost, String PmuCost, String NameOtherCosts, String OtherCosts,
                                  String Favorit, String Record) {
        Token = editServiceAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        ServiceID = editServiceAPI.createServiceAPI(Token, CodeService, NameService, PrintNameService, Parent, VendorService,
                ContraindicationsService, CreatorService, DescriptionService, PreconditionService, TypeService,
                DmsCost, OmsCost, PmuCost, NameOtherCosts, OtherCosts, Favorit, Record);
        deleteService(ServiceID, NameService);
    }

    @Test   //15.4
    @Parameters(value = {
            "3 Код для Раздела 3 | 3 Название для раздела 3 | 5bd6d8011c7030734863e056 | Название раздела для печати 3 |" +
                    " Арт для раздела 3",

            "4 Код для Раздела 4 | 4 Название для раздела 4 | 5bd6d8011c7030734863e056 | Название раздела для печати 4 |" +
                    " Арт для раздела 4"
    })
    public void deleteSectionService(String CodeSectionService, String NameSectionService, String Parent,
                                     String PrintNameSectionService, String VendorSectionService) {
        Token = editServiceAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        editServiceAPI.createServicesSectionAPI(Token, CodeSectionService, NameSectionService, Parent,
                PrintNameSectionService, VendorSectionService);
        deleteSectionService(CodeSectionService, NameSectionService);
    }

    @Test   //15.5
    @Parameters(value = {
            "5 Код для услуги 5 | Название для услуги 5 | Название для услуги для печати 5 |  5bc0d3595fdf7508cc91dafd | " +
                    "Артикул для услуги 5 | Противопоказания для услуги 5 | String Creator | Описание для услуги 5 |" +
                    " Предусловия для услуги 5 | Операция | 1001 | 2002 | 3003 | Доп.стоим. | 4482 | false | false",

    })
    public void editOtherCostServiceTest(String CodeService, String NameService,
                                         String PrintNameService, String Parent, String VendorService,
                                         String ContraindicationsService, String CreatorService,
                                         String DescriptionService, String PreconditionService, String TypeService,
                                         String DmsCost, String OmsCost, String PmuCost, String NameOtherCosts, String OtherCosts,
                                         String Favorit, String Record) {
        Token = editServiceAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        ServiceID = editServiceAPI.createServiceAPI(Token, CodeService, NameService, PrintNameService, Parent, VendorService,
                ContraindicationsService, CreatorService, DescriptionService, PreconditionService, TypeService,
                DmsCost, OmsCost, PmuCost, NameOtherCosts, OtherCosts, Favorit, Record);
        editOtherCostService(ServiceID, DmsCost, OmsCost, PmuCost, NameOtherCosts, OtherCosts);
    }
}
