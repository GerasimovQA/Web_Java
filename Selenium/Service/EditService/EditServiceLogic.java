package Service.EditService;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class EditServiceLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditServicePage p;

    int i = -1;
    int u = 0;

    private String RandomSubsectionName = "";

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new EditServicePage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            System.out.println(driver.getCurrentUrl());
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditService);
        }
    };

    public void editService(String ServiceID1, String CodeService, String NameService, String PrintNameService, String VendorService,
                            String ContraindicationsService, String DescriptionService, String PreconditionService, String TypeService,
                            String DmsCost, String OmsCost, String PmuCost, String OtherCosts, String NewCodeService,
                            String NewNameService,
                            String NewPrintNameService, String NewVendorService,
                            String NewContraindicationsService,
                            String NewDescriptionService, String NewPreconditionService,
                            String NewDmsCost, String NewOmsCost, String NewPmuCost, String NewOtherCosts) {
// Редактирование услуги
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID1);
        p.sleep(1000);
        p.preloader();

        p.click(p.linkChangeNameService);
        p.clickAndSendKeys(p.inputNameServiceEdit, NewNameService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveNameServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveNameServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Название\" ");
        }

        p.click(p.linkChangeNameForPrintService);
        p.clickAndSendKeys(p.inputNameForPrintServiceEdit, NewPrintNameService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveNameForPrintServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveNameForPrintServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Название для печати\" ");
        }

        p.click(p.linkChangeCodeService);
        p.clickAndSendKeys(p.inputCodeServiceEdit, NewCodeService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveCodeServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveCodeServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Код\" ");
        }

        p.click(p.linkChangeVendorService);
        p.clickAndSendKeys(p.inputVendorrServiceEdit, NewVendorService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveVendorServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveVendorServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Артикул\" ");
        }

        p.click(p.linkChangeSectionService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.bulletSectionServiceEdit);
                p.click(p.buttonSaveSectionServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.mainSectionServiceNameEdit);
                p.waitVisibility(p.bulletSubsectionServiceEdit.get(0));
                int RandomSubsection = p.random(p.bulletSubsectionServiceEdit.size() - 1);
                RandomSubsectionName = p.nameSubsectionServiceEdit.get(RandomSubsection).getText();
                System.out.println("Выбранный подраздел услуг: " + RandomSubsectionName);
                p.click(p.bulletSubsectionServiceEdit.get(RandomSubsection));
                p.click(p.buttonCancelSaveSectionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить головной раздел\" ");
        }

        p.click(p.linkChangeCostService);
        p.clickAndSendKeys(p.inputCostOMSServiceEdit, NewOmsCost);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveCostServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.clickAndSendKeys(p.inputCostDMSServiceEdit, NewDmsCost);
                p.clickAndSendKeys(p.inputCostPMUServiceEdit, NewPmuCost);
                p.click(p.buttonCancelSaveCostServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Стоимости\" ");
        }

        p.click(p.linkChangeCharacterService);
        String randCharacter = "";
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.characterServicEdit);
                int randomCharacter = p.random(p.listCharacterServiceEdit.size() - 1);
                randCharacter = p.listCharacterServiceEdit.get(randomCharacter).getText();
                System.out.println("Название рандомного характера: " + randCharacter);
                p.click(p.listCharacterServiceEdit.get(randomCharacter));
                p.click(p.buttonSaveCharacterServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.characterServicEdit);
                randomCharacter = p.random(p.listCharacterServiceEdit.size() - 1);
                randCharacter = p.listCharacterServiceEdit.get(randomCharacter).getText();
                System.out.println("Название рандомного характера: " + randCharacter);
                p.click(p.listCharacterServiceEdit.get(randomCharacter));
                p.click(p.buttonCancelSaveCharacterServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Характер\" ");
        }

        p.click(p.linkChangeDesriptionService);
        p.clickAndSendKeys(p.inputDescriptionServiceEdit, NewDescriptionService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveDescriptionServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveDescriptionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Описание\" ");
        }

        p.click(p.linkChangePreconditionService);
        p.clickAndSendKeys(p.inputPreconditionServiceEdit, NewPreconditionService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSavePreconditionServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSavePreconditionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Предусловия\" ");
        }

        p.click(p.linkChangeContraindicationService);
        p.clickAndSendKeys(p.inputContraindicationServiceEdit, NewContraindicationsService);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveContraindicationServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveContraindicationServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Противопоказания\" ");
        }

        p.click(p.linkChangeRecordService);
        p.click(p.switchRecordServiceEdit);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveRecordServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveRecordServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить возможность записи\" ");
        }

        p.click(p.linkChangeChosenRecordServiceEdit);
        p.click(p.checkBoxChosenServiceEdit);
        switch (NameService) {
            case ("Название для услуги 1"):
                p.click(p.buttonSaveChosenServiceEdit);
                break;
            case ("Название для услуги 2"):
                p.click(p.buttonCancelSaveChosenServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить избранность услуги\" ");
        }
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameService);
        p.preloader();
//Проверка сохраненных данных в карточке услуги
        switch (NameService) {
            case ("Название для услуги 1"):
                p.waitTextPresent(p.nameServiceProf, NewNameService);
                p.waitTextPresent(p.nameServicePrintProf, NewPrintNameService);
                p.waitTextPresent(p.recordServiceProf, "Да");
                p.waitTextPresent(p.characterServiceProf, randCharacter);
                p.waitTextPresent(p.contraindicationServiceProf, NewContraindicationsService);
                p.waitTextPresent(p.preconditionServiceProf, NewPreconditionService);
                p.waitTextPresent(p.descriptionServiceProf, NewDescriptionService);
                p.waitTextPresent(p.costOMSProf, NewOmsCost);
                p.waitTextPresent(p.costDMSProf, DmsCost);
                p.waitTextPresent(p.costPMUProf, PmuCost);
                p.waitTextPresent(p.buttonEditServiceProf, "Ред. услугу");
                p.waitTextPresent(p.сodeServiceProf, NewCodeService);
                p.waitTextPresent(p.vendorServiceProf, NewVendorService);
                break;
            case ("Название для услуги 2"):
                p.waitTextPresent(p.nameServiceProf, NameService);
                p.waitTextPresent(p.nameServicePrintProf, PrintNameService);
                p.waitTextPresent(p.recordServiceProf, "Нет");
                p.waitTextPresent(p.characterServiceProf, TypeService);
                p.waitTextPresent(p.contraindicationServiceProf, ContraindicationsService);
                p.waitTextPresent(p.preconditionServiceProf, PreconditionService);
                p.waitTextPresent(p.descriptionServiceProf, DescriptionService);
                p.waitTextPresent(p.costOMSProf, OmsCost);
                p.waitTextPresent(p.costDMSProf, DmsCost);
                p.waitTextPresent(p.costPMUProf, PmuCost);
                p.waitTextPresent(p.buttonEditServiceProf, "Ред. услугу");
                p.waitTextPresent(p.сodeServiceProf, CodeService);
                p.waitTextPresent(p.vendorServiceProf, VendorService);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Проверка сохраненных данных\" ");
        }
    }

    public void editSectionService(String SectionServiceID, String NameSectionService, String PrintNameSectionService,
                                   String CodeSectionService, String VendorSectionService,
                                   String NewNameSectionService, String NewPrintNameSectionService,
                                   String NewCodeSectionService, String NewVendorSectionService) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit-node/" + SectionServiceID);
        p.sleep(1000);
        p.preloader();
// Редактирование раздела услуг
        p.click(p.linkChangeNameSectionService);
        p.clickAndSendKeys(p.inputNameSectionServiceEdit, NewNameSectionService);
        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.click(p.buttonSaveNameSectionServiceEdit);
                break;
            case ("2 Название для раздела 2"):
                p.click(p.buttonCancelSaveNameSectionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Название\" ");
        }

        p.click(p.linkChangeNameForPrintSectionService);
        p.clickAndSendKeys(p.inputNameSectionForPrintServiceEdit, NewPrintNameSectionService);
        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.click(p.buttonSaveNameSectionForPrintServiceEdit);
                break;
            case ("2 Название для раздела 2"):
                p.click(p.buttonCancelSaveNameForPrintSectionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить Название для печати\" ");
        }

        p.click(p.linkChangeCodeSectionService);
        p.clickAndSendKeys(p.inputCodeSectionServiceEdit, NewCodeSectionService);
        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.click(p.buttonSaveCodeSectionServiceEdit);
                break;
            case ("2 Название для раздела 2"):
                p.click(p.buttonCancelSaveCodeSectionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить код\" ");
        }

        p.click(p.linkChangeVendorSectionService);
        p.clickAndSendKeys(p.inputVendorSectionServiceEdit, NewVendorSectionService);
        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.click(p.buttonSaveVendorSectionServiceEdit);
                break;
            case ("2 Название для раздела 2"):
                p.click(p.buttonCancelSaveVendorSectionServiceEdit);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить артикул\" ");
        }

        p.click(p.linkChangeHeadSectionForSectionService);
        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.click(p.bulletSectionServiceEdittForSectionService);
                p.click(p.buttonSaveSectionServiceEditForSectionService);
                break;
            case ("2 Название для раздела 2"):
                p.click(p.mainSectionServiceNameEditForSectionService);
                p.waitVisibility(p.bulletSubsectionServiceEditForSectionService.get(0));
                int RandomSubsection = p.random(p.bulletSubsectionServiceEditForSectionService.size() - 1);
                RandomSubsectionName = p.nameSubsectionServiceEditForSectionService.get(RandomSubsection).getText();
                System.out.println("Выбранный подраздел услуг: " + RandomSubsectionName);
                p.click(p.bulletSubsectionServiceEditForSectionService.get(RandomSubsection));
                p.click(p.buttonCancelSaveSectionServiceEditForSectionService);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Изменить головной раздел\" ");
        }

        switch (NameSectionService) {
            case ("1 Название для раздела 1"):
                p.waitTextPresent(p.nameSectionServiceEdit, NewNameSectionService);
                p.waitTextPresent(p.nameForPrintSectionServiceEdit, NewPrintNameSectionService);
                p.waitTextPresent(p.codeSectionServiceEdit, NewCodeSectionService);
                p.waitTextPresent(p.vendorSectionServiceEdit, NewVendorSectionService);
                p.waitTextPresent(p.headSectionServiceForSectionServiceEdit,
                        "Консультации врачей, отдельные манипуляции");

                break;
            case ("2 Название для раздела 2"):
                p.waitTextPresent(p.nameSectionServiceEdit, NameSectionService);
                p.waitTextPresent(p.nameForPrintSectionServiceEdit, PrintNameSectionService);
                p.waitTextPresent(p.codeSectionServiceEdit, CodeSectionService);
                p.waitTextPresent(p.vendorSectionServiceEdit, VendorSectionService);
                p.waitTextPresent(p.headSectionServiceForSectionServiceEdit, "Услуги Степашина.");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Проверка сохраненной информации\" ");
        }
    }

    public void deleteService(String ServiceID1, String NameService) {

        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID1);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkDeleteSrvice);
        p.waitVisibility(p.buttonDeleteService);


        switch (NameService) {
// Удаление услуги
            case ("Название для услуги 3"):
                p.click(p.buttonDeleteService);
                p.sleep(1000);

                System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
                p.sleep(1000);
                p.preloader();
                p.click(p.mainSectionServiceInList);
                p.click(p.subSectionServiceStepashinInList);

                for (WebElement Name : p.sectionService3LevInList) {
                    p.moveMouse(Name);
                    System.out.println(Name.getText());
                    if (Name.getText().equals(NameService)) {
                        throw new AssertionError("Удаленная услуга найдена");
                    }
                }
                System.out.println("Удаленная специальность не найдена");
                break;
// Отмена Удаления услуги
            case ("Название для услуги 4"):
                p.click(p.buttonCancelDeleteService);
                p.sleep(1000);

                System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
                p.sleep(1000);
                p.preloader();
                p.click(p.mainSectionServiceInList);
                p.click(p.subSectionServiceStepashinInList);

                for (WebElement Name : p.sectionService3LevInList) {
                    p.moveMouse(Name);
                    System.out.println(Name.getText());
                    if (Name.getText().equals(NameService)) {
                        System.out.println("Услуга найдена");
                        return;
                    }
                }
                break;
            default:
                throw new AssertionError("Пропущен блок \"Удаление услуги\" ");
        }
    }

    public void deleteSectionService(String CodeSectionService, String NameSectionService) {

        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
        p.sleep(1000);
        p.preloader();
        p.click(p.mainSectionServiceInList);
        p.click(p.subSectionServiceStepashinInList);
        driver.findElementByXPath("//span[contains(text(),\"" + NameSectionService + "\")]/../../..//i").click();


        switch (NameSectionService) {
// Удаление раздела услуг
            case ("3 Название для раздела 3"):
                p.click(p.buttonDeleteSectionServiceInGamburger);
                p.sleep(1000);
                p.click(p.mainSectionServiceInList);
                p.click(p.subSectionServiceStepashinInList);

                for (WebElement Name : p.sectionService3LevInList) {
                    p.moveMouse(Name);
                    System.out.println(Name.getText());
                    if (Name.getText().equals(CodeSectionService + " " + NameSectionService)) {
                        throw new AssertionError("Удаленный раздел услуг найдена");
                    }
                }
                System.out.println("Удаленный раздел услуг не найден");
                break;
// Отмена Удаления раздела услуг
            case ("4 Название для раздела 4"):
                p.click(p.userinfoname);
                p.sleep(1000);

                driver.navigate().refresh();
                p.sleep(1000);
                p.preloader();
                p.click(p.mainSectionServiceInList);
                p.click(p.subSectionServiceStepashinInList);

                for (WebElement Name : p.sectionService3LevInList) {
                    p.moveMouse(Name);
                    System.out.println(Name.getText());
                    if (Name.getText().equals(CodeSectionService + " " + NameSectionService)) {
                        System.out.println("Раздел услуг найден");
                        return;
                    }
                }
                throw new AssertionError("Раздел услуг НЕ найден");

            default:
                throw new AssertionError("Пропущен блок \"Удаление раздела услуг\" ");
        }
    }

    public void editOtherCostService(String ServiceID, String DmsCost, String OmsCost, String PmuCost, String NameOtherCosts,
                                     String OtherCosts) {
// Удаление дополнительной стоимости
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID);
        p.sleep(1000);
        p.preloader();

        p.click(p.linkChangeCostService);
        p.click(p.linkDeleteAdditionalCostServiceEdit);
        p.click(p.buttonSaveCostServiceEdit);
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameService);
        p.preloader();
// Проверка удаления дополнительной стоимости
        p.waitTextPresent(p.costOMSProf, OmsCost);
        p.waitTextPresent(p.costDMSProf, DmsCost);
        p.waitTextPresent(p.costPMUProf, PmuCost);
        try {
            p.waitVisibility(driver.findElement(By.xpath("(//div[contains(text(),\"" + NameOtherCosts + "\")]/div)[1]")));
            throw new Error("Найдена удаленная стоимость");
        } catch (NoSuchElementException e) {
            System.out.println("Дополнительная стоимость не найдена и это хорошо");
        }
// Добавление дополнительной стоимости
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkChangeCostService);
        p.click(p.linkAddCostEdit);
        p.clickAndSendKeys(p.inputNameAdditionalCostServiceEdit, NameOtherCosts);
        p.clickAndSendKeys(p.inputCostAdditionalCostServiceEdit, OtherCosts);
        p.click(p.buttonSaveCostServiceEdit);
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameService);
        p.preloader();

//Проверка добавления дополнительной стоимости
        p.waitTextPresent(p.costOMSProf, OmsCost);
        p.waitTextPresent(p.costDMSProf, DmsCost);
        p.waitTextPresent(p.costPMUProf, PmuCost);
        p.waitTextPresent(driver.findElement(By.xpath("(//div[contains(text(),\"" + NameOtherCosts + "\")]" +
                "/div)[1]")), OtherCosts);

//Отмена удаления Добавления дополнительной стоимости
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkChangeCostService);
        p.click(p.linkDeleteAdditionalCostServiceEdit);
        p.click(p.buttonCancelSaveCostServiceEdit);
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameService);
        p.preloader();

//Проверка отмены добавления дополнительной стоимости
        p.waitTextPresent(p.costOMSProf, OmsCost);
        p.waitTextPresent(p.costDMSProf, DmsCost);
        p.waitTextPresent(p.costPMUProf, PmuCost);
        p.waitTextPresent(driver.findElement(By.xpath("(//div[contains(text(),\"" + NameOtherCosts + "\")]" +
                "/div)[1]")), OtherCosts);

// Отмена Добавления дополнительной стоимости
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/edit/" + ServiceID);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkChangeCostService);
        p.click(p.linkDeleteAdditionalCostServiceEdit);
        p.click(p.buttonSaveCostServiceEdit);
        p.click(p.linkChangeCostService);
        p.click(p.linkAddCostEdit);
        p.clickAndSendKeys(p.inputNameAdditionalCostServiceEdit, NameOtherCosts);
        p.clickAndSendKeys(p.inputCostAdditionalCostServiceEdit, OtherCosts);
        p.click(p.buttonCancelSaveCostServiceEdit);
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.linkNameService);
        p.preloader();

//Проверка отмены добавления дополнительной стоимости
        p.waitTextPresent(p.costOMSProf, OmsCost);
        p.waitTextPresent(p.costDMSProf, DmsCost);
        p.waitTextPresent(p.costPMUProf, PmuCost);
        try {
            p.waitVisibility(driver.findElement(By.xpath("(//div[contains(text(),\"" + NameOtherCosts + "\")]/div)[1]")));
            throw new Error("Найдена удаленная стоимость");
        } catch (NoSuchElementException e) {
            System.out.println("Дополнительная стоимость не найдена и это хорошо");
        }
    }
}