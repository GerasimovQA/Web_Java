package Service.CreateService;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class CreateServiceLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private CreateServicePage p;

    private int u = 0;

    private String RandomSubsectionName = "";
    private String RandCharacter = "";
    private String ServiceID = "";

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
            p = new CreateServicePage(driver);
            System.out.println(driver.getCurrentUrl());
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsCreateService);
        }
    };

    public void createService(String CodeService, String NameService, String PrintNameService,
                              String VendorService, String ContraindicationsService,
                              String DescriptionService, String PreconditionService,
                              String DmsCost, String OmsCost, String PmuCost, String OtherCosts) {
// Создание услуги
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/add");
        p.sleep(1000);
        p.preloader();
        p.clickAndSendKeys(p.inputNameService, NameService);
        switch (NameService) {
            case ("Услужное название 1"):
                p.clickAndSendKeys(p.inputNameServiceForPrint, PrintNameService);
                break;
            case ("Услужное название 2"):
                System.out.println("Пропускаем название для печати");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Название для печати\" ");
        }
        p.clickAndSendKeys(p.inputCodeService, CodeService);
        p.clickAndSendKeys(p.inputVendorService, VendorService);

        switch (NameService) {
            case ("Услужное название 1"):
                p.click(p.checkBoxSectionService);
                break;
            case ("Услужное название 2"):
                p.click(p.mainSectionServiceName);
                p.waitVisibility(p.checkBoxSubsectionService.get(0));
                int RandomSubsection = p.random(p.checkBoxSubsectionService.size() - 1);
                RandomSubsectionName = p.nameSubsectionService.get(RandomSubsection).getText();
                System.out.println("Выбранный подраздел услуг: " + RandomSubsectionName);
                p.click(p.checkBoxSubsectionService.get(RandomSubsection));
                break;
            default:
                throw new AssertionError("Пропущен блок \"Выбор раздела\" ");

        }
        p.clickAndSendKeys(p.inputCostOMS, OmsCost);
        p.clickAndSendKeys(p.inputCostDMS, DmsCost);
        p.clickAndSendKeys(p.inputCostPMU, PmuCost);

        switch (NameService) {
            case ("Услужное название 1"):
                p.click(p.linkAddCost);
                p.clickAndSendKeys(p.inputNewCostName, "Бла-бла");
                p.clickAndSendKeys(p.inputNewCostSum, OtherCosts);
                p.click(p.linkDeleteCost);
                break;
            case ("Услужное название 2"):
                p.click(p.linkAddCost);
                p.clickAndSendKeys(p.inputNewCostName, "Бла-бла");
                p.clickAndSendKeys(p.inputNewCostSum, OtherCosts);
                break;
            default:
                throw new AssertionError("Пропущен блок \"Дополнительная стоимость\" ");
        }

        switch (NameService) {
            case ("Услужное название 1"):
                p.click(p.characterService);
                int RandomCharacter = p.random(p.listCharacterService.size() - 1);
                RandCharacter = p.listCharacterService.get(RandomCharacter).getText();
                System.out.println("Название рандомного характера: " + RandCharacter);
                p.click(p.listCharacterService.get(RandomCharacter));
                break;
            case ("Услужное название 2"):
                System.out.println("Пропускаем выбор характера услуги");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Характер услуги\" ");
        }

        switch (NameService) {
            case ("Услужное название 1"):
                p.clickAndSendKeys(p.inputDescriptionService, DescriptionService);
                p.clickAndSendKeys(p.inputContraindicationService, ContraindicationsService);
                p.clickAndSendKeys(p.inputPreconditionService, PreconditionService);
                break;
            case ("Услужное название 2"):
                System.out.println("Пропускаем заполнения описания");
                System.out.println("Пропускаем заполнения Противопоказаний");
                System.out.println("Пропускаем заполнения Предусловий");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Пропущены: описание, противоп. и предусл.\" ");
        }

        switch (NameService) {
            case ("Услужное название 1"):
                p.click(p.switchRecordService);
                p.click(p.switchChosenRecordService);
                break;
            case ("Услужное название 2"):
                System.out.println("Оставляем включенной возможность записи");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Свитчеры\" ");
        }
        p.click(p.buttonAddService);

//Проверка Созданной услуги
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
        p.preloader();
        switch (NameService) {
            case ("Услужное название 1"):
                p.click(p.mainSectionServiceInList);
                for (WebElement Section : p.sectionService2LevInList) {
                    p.moveMouse(Section);
                    System.out.println("Услуга в списке: " + Section.getText());
                    if (Section.getText().equals(NameService)) {
                        u = u + 1;
                    }
                }
                Assert.assertEquals("Количество найденных услуг не равно 1", 1, u);
                p.click(driver.findElement(By.xpath
                        ("//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span[text()=\""
                                + NameService + "\"]/../..//span[text()=\"Подробнее\"]")));
                p.waitTextPresent(p.nameServiceProf, NameService);
                ServiceID = driver.getCurrentUrl().substring(46);
                System.out.println(ServiceID);
                p.waitTextPresent(p.nameServicePrintProf, PrintNameService);
                p.waitTextPresent(p.recordServiceProf, "Нет");
                p.waitTextPresent(p.characterServiceProf, RandCharacter);
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
            case ("Услужное название 2"):
                p.click(p.mainSectionServiceInList);

                p.click(driver.findElement(By.xpath
                        ("//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span[text()=\""
                                + RandomSubsectionName + "\" ]")));
                for (WebElement Section : p.sectionService3LevInList) {
                    p.moveMouse(Section);
                    System.out.println("Услуга в списке: " + Section.getText());
                    if (Section.getText().equals(NameService)) {
                        u = u + 1;
                    }
                }
                Assert.assertEquals("Количество найденных услуг не равно 1", 1, u);
                p.click(driver.findElement(By.xpath
                        ("//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span[text()=\""
                                + NameService + "\" ]/../..//span[text()=\"Подробнее\"]")));
                p.preloader();
                p.waitTextPresent(p.nameServiceProf, NameService);
                p.waitTextPresent(p.nameServicePrintProf, "Не указано");
                p.waitTextPresent(p.recordServiceProf, "Да");
                p.waitTextPresent(p.characterServiceProf, RandCharacter);
                p.waitTextPresent(p.contraindicationServiceProf, "");
                p.waitTextPresent(p.preconditionServiceProf, "");
                p.waitTextPresent(p.descriptionServiceProf, "");
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

    public void createSectionService(String NameSectionService, String PrintNameSectionService,
                                     String CodeSectionService, String VendorSectionService) {
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/add-node");
        p.sleep(1000);
        p.preloader();
// Создание раздела услуг
        p.clickAndSendKeys(p.inputNameSectionService, NameSectionService);
        System.out.println(NameSectionService);
        switch (NameSectionService) {
            case ("1 Раздельное название 1"):
                p.clickAndSendKeys(p.inputNameSectionServiceForPrint, PrintNameSectionService);
                break;
            case ("2 Раздельное название 2"):
                System.out.println("Пропускаем название для печати");
                break;
            default:
                throw new AssertionError("Пропущен блок \"Название для печати\" ");
        }
        p.clickAndSendKeys(p.inputCodeSectionService, CodeSectionService);
        p.clickAndSendKeys(p.inputVendorSectionService, VendorSectionService);

        switch (NameSectionService) {
            case ("1 Раздельное название 1"):
                p.click(p.bulletSectionServiceForSection);
                break;
            case ("2 Раздельное название 2"):
                p.click(p.mainSectionServiceNameForSection);
                p.waitVisibility(p.bulletSubsectionServiceForSection.get(0));
                System.out.println(p.bulletSubsectionServiceForSection.size());
                int RandomSubsection = p.random(p.bulletSubsectionServiceForSection.size() - 1);
                RandomSubsectionName = p.nameSubsectionServiceForSection.get(RandomSubsection).getText();
                System.out.println("Выбранный подраздел услуг: " + RandomSubsectionName);
                p.click(p.bulletSubsectionServiceForSection.get(RandomSubsection));
                break;
            default:
                throw new AssertionError("Пропущен блок \"Головной раздел\" ");
        }
        p.click(p.buttonAddSectionService);
        p.preloader();
// Проверка сохраненных данных
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/services/list");
        p.preloader();
        p.waitVisibility(p.mainSectionServiceInList);
        switch (NameSectionService) {
            case ("1 Раздельное название 1"):
                p.click(p.mainSectionServiceInList);
                for (WebElement Section : p.sectionService2LevInList) {
                    p.moveMouse(Section);
                    System.out.println("Раздел Услуг в списке: " + Section.getText());
                    if (Section.getText().equals(CodeSectionService + " " + NameSectionService)) {
                        u = u + 1;
                    }
                }
                Assert.assertEquals("Количество найденных разделов услуг не равно 1", 1, u);
                break;
            case ("2 Раздельное название 2"):
                p.click(p.mainSectionServiceInList);
                p.preloader();
                p.click(driver.findElement(By.xpath
                        ("//div[@class=\"services-tree__row name vendor_code price actions details favorite\"]/div/span[contains(text(),\""
                                + RandomSubsectionName + "\")]")));
                p.preloader();
                for (WebElement Section : p.sectionService3LevInList) {
                    p.moveMouse(Section);
                    System.out.println("Раздел Услуг в списке: " + Section.getText());
                    if (Section.getText().equals(CodeSectionService + " " + NameSectionService)) {
                        u = u + 1;
                    }
                }
                Assert.assertEquals("Количество найденных услуг не равно 1", 1, u);
                break;
            default:
                throw new AssertionError("Пропущена проверка сохраненных данных");
        }
        p.click(driver.findElement(By.xpath
                ("//span[contains(text(),\"" + NameSectionService + "\")]/../..//" +
                        "i[@class=\"services-tree-dropdown-menu__drop-icon el-icon-more\"]")));
        p.click(driver.findElement(By.xpath("(//span[contains(text(),\"Удалить\")])[last()]")));
    }
}