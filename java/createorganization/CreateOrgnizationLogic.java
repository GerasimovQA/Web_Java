package createorganization;

import autorization.AutorizationPage;
import global.Ashot;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

import java.util.ArrayList;
import java.util.logging.Level;

@RunWith(JUnitParamsRunner.class)
public class CreateOrgnizationLogic {

    private static WebDriver driver;
    private static CreateOrgnizationPage page;

    String NowDate = "";
    public ArrayList<String> ListSaveServices = new ArrayList<>();
    public ArrayList<String> ListServicesInProfile = new ArrayList<>();


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new CreateOrgnizationPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
////            driver.manage().addCookie(page.readCookiesFromFile());
////            driver.get("http://localhost:8000/#/");
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            page.SelectedServices.clear();
            driver.navigate().refresh();
//            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

    @BeforeClass
    public static void beforClass() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(ConfigProperties.getTestProperty("head"));
        options.addArguments("window-size=1200,800");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(options);
        page = new CreateOrgnizationPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void stepOneCreateOrganization(String OrganizationName, String OrganizationNameAbbr,
                                          String SecondNameDirectorOrganization, String FirstNameDirectorOrganization,
                                          String MiddleNameDirectorOrganization, String FIODirector,
                                          String OrganizationProfile, String ConditionsOrganization) {
        try {
            page.waitE_ClickableAndClick(page.buttonAddOrganization);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.buttonOrganization);
            page.waitE_ClickableAndClick(page.buttonAddOrganization);
        }

        page.waitE_ClickableAndSendKeys(page.inputOrganizationName, OrganizationName);
        page.waitE_ClickableAndSendKeys(page.inputOrganizationNameAbbr, OrganizationNameAbbr);
//        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);
        page.waitE_ClickableAndClick(page.buttonSearchDirector);
        page.sleep(2000);
        page.waitE_visibilityOf(page.listFIODirecor.get(0));

        for (WebElement DirectorPhoto : page.listPhotoDirecor) {
            Assert.assertTrue(DirectorPhoto.isEnabled());
        }
        for (WebElement DirectorFIO : page.listFIODirecor) {
            Assert.assertTrue(DirectorFIO.isEnabled());
        }

        WebElement buttonSelectDirector = driver.findElement(By.xpath("//span[text()=\"Акулин\"]/../../." +
                ".//button/span[text()=\"Выбрать руководителя\"]"));


        page.waitE_ClickableAndClick(buttonSelectDirector);
        Assert.assertTrue(page.photoAssignedDirecor.isEnabled() & page.FIOAssignedDirecor.getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.inputStructureOrganization);
        page.waitE_ClickableAndClick(page.bulletHeadOrganization);
        page.waitE_ClickableAndClick(page.selectOrganizationConditions);

        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();

        page.waitE_ClickableAndSendKeys(page.inputOrganizationProfile, OrganizationProfile);
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep1);
    }

    public void stepTwoCreateOrganization(String OrganizationNameAbbr, String CheckedAndFocus, String Checkeds,
                                          String Indeterminate, String Empty) {
        page.stepTwo(OrganizationNameAbbr, CheckedAndFocus, Checkeds, Indeterminate, Empty);
    }


    public void stepThreeCreateOrganization(String DescriptionOrgnization, String AppointmentPhone1,
                                            String PhoneNumber1, String AppointmentPhone2, String PhoneNumber2,
                                            String Adress, String Email, String Vk,
                                            String Facebook, String Instagram, String Other, String Code,
                                            String IdentifierOMS, String RegistrationNumber, String IdentifierPUMP) {
        page.waitE_ClickableAndSendKeys(page.inputDescriptionOrgnization, DescriptionOrgnization);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(0), AppointmentPhone1);
        page.waitE_ClickableAndSendKeys(page.inputPhoneNumber.get(0), PhoneNumber1);

        page.waitE_ClickableAndClick(page.addPhoneNumber);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(1), AppointmentPhone2);
        page.waitE_ClickableAndSendKeys(page.inputPhoneNumber.get(1), PhoneNumber2);
        page.waitE_ClickableAndClick(page.deletePhoneNumber);

        page.waitE_ClickableAndSendKeys(page.inputAdress, Adress);
        page.sleep(10000);
        page.waitE_visibilityOf(page.map);
        page.waitE_visibilityOf(page.confirmAdress);
        page.waitE_ClickableAndClick(page.confirmAdress);
        page.waitE_ClickableAndSendKeys(page.inputEmail, Email);
        page.waitE_ClickableAndSendKeys(page.inputVk, Vk);
        page.waitE_ClickableAndSendKeys(page.inputFacebook, Facebook);
        page.waitE_ClickableAndSendKeys(page.inputInstagram, Instagram);
        page.waitE_ClickableAndSendKeys(page.inputOther, Other);
        page.waitE_ClickableAndSendKeys(page.inputCode, Code);
        page.waitE_ClickableAndSendKeys(page.inputRegistryNumber, RegistrationNumber);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierPUMP, IdentifierPUMP);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierOMS, IdentifierOMS);
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep3);
    }

    public void stepFourCreateOrganization(String OrganizationName, String OrganizationNameAbbr,
                                           String FIODirector,
                                           String OrganizationProfile, String DescriptionOrgnization,
                                           String AppointmentPhone1, String PhoneNumber1, String AppointmentPhone2,
                                           String PhoneNumber2, String Adress, String Email, String Vk,
                                           String Facebook, String Instagram, String Other, String Code,
                                           String IdentifierOMS, String RegistrationNumber, String IdentifierPUMP,
                                           String HeadOrganization, String ConditionsOrganization) {
        page.waitE_visibilityOf(page.checkNameOrganization);

        System.out.println(page.checkNameOrganization.getText() + " = " + OrganizationName);
        Assert.assertEquals(page.checkNameOrganization.getText(), OrganizationName);

        System.out.println(page.checkNameAbbrOrganization.getText() + " = " + OrganizationNameAbbr);
        Assert.assertEquals(page.checkNameAbbrOrganization.getText(), OrganizationNameAbbr);

        System.out.println(page.checkDirectorOrganization.getText() + " = " + FIODirector);
        Assert.assertEquals(page.checkDirectorOrganization.getText(), FIODirector);

        System.out.println(page.checkHeadOrganization.getText() + " = " + HeadOrganization);
        Assert.assertEquals(page.checkHeadOrganization.getText(), HeadOrganization);

        System.out.println(page.checkConditionsOrganization.getText() + " = " + ConditionsOrganization);
        Assert.assertEquals(page.checkConditionsOrganization.getText(), ConditionsOrganization);

        System.out.println(page.checkProfileOrganization.getText() + " = " + OrganizationProfile);
        Assert.assertEquals(page.checkProfileOrganization.getText(), OrganizationProfile);

        System.out.println(page.checkDescriptionOrganization.getText() + " = " + DescriptionOrgnization);
        Assert.assertEquals(page.checkDescriptionOrganization.getText(), DescriptionOrgnization);


        for (WebElement Service : page.saveServices) {
            ListSaveServices.add(Service.getText().replace("\n", " "));
        }
        System.out.println(page.SelectedServices);
        System.out.println(ListSaveServices);
        Assert.assertEquals(page.SelectedServices, ListSaveServices);

        System.out.println(page.checkAdressOrganization.getText() + " = " + Adress);
        Assert.assertEquals(page.checkAdressOrganization.getText(), Adress);

        System.out.println(page.checkEmailOrganization.getText() + " = " + Email);
        Assert.assertEquals(page.checkEmailOrganization.getText(), Email);

        System.out.println(page.checkVkOrganization.getText() + " = " + Vk);
        Assert.assertEquals(page.checkVkOrganization.getText(), Vk);

        System.out.println(page.checkFacebookOrganization.getText() + " = " + Facebook);
        Assert.assertEquals(page.checkFacebookOrganization.getText(), Facebook);

        System.out.println(page.checkInstagramOrganization.getText() + " = " + Instagram);
        Assert.assertEquals(page.checkInstagramOrganization.getText(), Instagram);

        System.out.println(page.checkOtherOrganization.getText() + " = " + Other);
        Assert.assertEquals(page.checkOtherOrganization.getText(), Other);

        System.out.println(page.checkAppointmentPhone1.get(0).getText() + " = " + AppointmentPhone1);
        Assert.assertEquals(page.checkAppointmentPhone1.get(0).getText(), AppointmentPhone1);

        System.out.println(page.checkPhoneNumber1.get(0).getText() + " = " + "+7 (" + PhoneNumber1.charAt(0) + PhoneNumber1.charAt(1) +
                PhoneNumber1.charAt(2) + ") " + PhoneNumber1.charAt(3) + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + "-" +
                PhoneNumber1.charAt(6) + PhoneNumber1.charAt(7) + "-" + PhoneNumber1.charAt(8) + PhoneNumber1.charAt(9));
        Assert.assertEquals(page.checkPhoneNumber1.get(0).getText(),
                "+7 (" + PhoneNumber1.charAt(0) + PhoneNumber1.charAt(1) +
                        PhoneNumber1.charAt(2) + ") " + PhoneNumber1.charAt(3) + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + "-" +
                        PhoneNumber1.charAt(6) + PhoneNumber1.charAt(7) + "-" + PhoneNumber1.charAt(8) + PhoneNumber1.charAt(9));

        page.waitE_ClickableAndClick(page.buttonFinish);
        NowDate = page.nowDate();
        page.waitE_ClickableAndClick(page.menuOrganizations);
        page.sleep(1000);
    }

    public void stepOneCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                      String SecondNameDirectorOrganization,
                                                      String FirstNameDirectorOrganization,
                                                      String MiddleNameDirectorOrganization, String FIODirector,
                                                      String OrganizationProfile, String ConditionsOrganization) {
        page.waitE_ClickableAndClick(page.buttonOrganization);
        page.waitE_ClickableAndClick(page.buttonAddOrganization);
        page.waitE_ClickableAndSendKeys(page.inputOrganizationName, OrganizationName);
        page.waitE_ClickableAndSendKeys(page.inputOrganizationNameAbbr, OrganizationNameAbbr);
        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputFirstNameDirectorOrganization, FirstNameDirectorOrganization);
        page.waitE_ClickableAndSendKeys(page.inputMiddleNameDirectorOrganization, MiddleNameDirectorOrganization);

        System.out.println("Введенные значения: " + page.inputSecondNameDirectorOrganization.getAttribute("value")
                + " " + page.inputFirstNameDirectorOrganization.getAttribute("value")
                + " " + page.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertEquals(page.inputSecondNameDirectorOrganization.getAttribute("value"),
                SecondNameDirectorOrganization);
        Assert.assertEquals(page.inputFirstNameDirectorOrganization.getAttribute("value"),
                FirstNameDirectorOrganization);
        Assert.assertEquals(page.inputMiddleNameDirectorOrganization.getAttribute("value"),
                MiddleNameDirectorOrganization);

        page.waitE_ClickableAndClick(page.buttonClear);
        System.out.println("Значения сброшены: " + page.inputSecondNameDirectorOrganization.getAttribute("value") +
                page.inputFirstNameDirectorOrganization.getAttribute("value") + page.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertEquals("", page.inputSecondNameDirectorOrganization.getAttribute("value"));
        Assert.assertEquals("", page.inputFirstNameDirectorOrganization.getAttribute("value"));
        Assert.assertEquals("", page.inputMiddleNameDirectorOrganization.getAttribute("value"));
        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.sleep(1000);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо");
        }
        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndClick(page.buttonSearchDirector);
        page.sleep(2000);
        page.waitE_visibilityOf(page.listFIODirecor.get(0));
        Assert.assertTrue(page.listPhotoDirecor.get(0).isEnabled() & page.listFIODirecor.get(0).getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.buttonClear);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо-2");
        }

        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputFirstNameDirectorOrganization, "вас");
        page.waitE_ClickableAndClick(page.buttonSearchDirector);
        page.sleep(2000);
        page.waitE_visibilityOf(page.listFIODirecor.get(0));
        Assert.assertTrue(page.listPhotoDirecor.get(1).isEnabled() & page.listFIODirecor.get(1).isEnabled());
        page.waitE_ClickableAndClick(page.buttonSelectDirecor.get(1));
        page.waitE_ClickableAndClick(page.changeDirecor);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, "а");
        page.waitE_ClickableAndClick(page.buttonSearchDirector);
        page.sleep(2000);
        page.waitE_visibilityOf(page.listFIODirecor.get(0));
        page.waitE_ClickableAndClick(page.buttonSelectDirecor.get(0));
        page.waitE_ClickableAndClick(page.changeDirecor);
        page.waitE_ClickableAndClick(page.buttonClear);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.listFIODirecor.get(0).isEnabled());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Сотрудники не найдены и это хорошо-возращение");
        }

        page.waitE_ClickableAndClick(page.inputStructureOrganization);
        WebElement head = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(), \"ФГАОУ ВО " +
                        "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\")]")));
        head.click();
        WebElement underHead1 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(), " +
                        "\"Университетская клиническая больница №3\")]")));
        underHead1.click();

        WebElement underHead2 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(), " +
                        "\"Клиника нервных болезней\")]")));
        underHead2.click();

        WebElement bulletUnderHead3 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains (text(), " +
                        "\"Лечебно-диагностическое отделение №2\")]/../label")));
        bulletUnderHead3.click();
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep1);
    }

    public void stepThreeCreateOrganizationTestDirector(String DescriptionOrgnization, String AppointmentPhone1,
                                                        String PhoneNumber1, String AppointmentPhone2,
                                                        String PhoneNumber2,
                                                        String Adress, String Email, String Vk,
                                                        String Facebook, String Instagram, String Other, String Code,
                                                        String IdentifierOMS, String RegistrationNumber,
                                                        String IdentifierPUMP) {
        page.waitE_ClickableAndSendKeys(page.inputDescriptionOrgnization, DescriptionOrgnization);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(0), AppointmentPhone1);
        page.waitE_ClickableAndSendKeys(page.inputPhoneNumber.get(0), PhoneNumber1);

        page.waitE_ClickableAndClick(page.addPhoneNumber);
        page.waitE_ClickableAndSendKeys(page.inputAppointmentPhone.get(1), AppointmentPhone2);
        page.waitE_ClickableAndSendKeys(page.inputPhoneNumber.get(1), PhoneNumber2);

        page.waitE_ClickableAndSendKeys(page.inputAdress, Adress);
        page.sleep(10000);
        page.waitE_visibilityOf(page.map);
        Ashot.screenshot(driver, "stepThreeCreateOrganizationTestDirector", 0);
        page.waitE_visibilityOf(page.confirmAdress);
        page.waitE_ClickableAndClick(page.confirmAdress);
        page.waitE_ClickableAndSendKeys(page.inputEmail, Email);
        page.waitE_ClickableAndSendKeys(page.inputVk, Vk);
        page.waitE_ClickableAndSendKeys(page.inputFacebook, Facebook);
        page.waitE_ClickableAndSendKeys(page.inputInstagram, Instagram);
        page.waitE_ClickableAndSendKeys(page.inputOther, Other);
        page.waitE_ClickableAndSendKeys(page.inputCode, Code);
        page.waitE_ClickableAndSendKeys(page.inputRegistryNumber, RegistrationNumber);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierPUMP, IdentifierPUMP);
        page.waitE_ClickableAndSendKeys(page.inputIdentifierOMS, IdentifierOMS);
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep3);
    }

    public void stepFourCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                       String FIODirector, String OrganizationProfile,
                                                       String DescriptionOrgnization, String AppointmentPhone1,
                                                       String PhoneNumber1, String AppointmentPhone2,
                                                       String PhoneNumber2, String Adress, String Email, String Vk,
                                                       String Facebook, String Instagram, String Other, String Code,
                                                       String IdentifierOMS, String RegistrationNumber,
                                                       String IdentifierPUMP, String HeadOrganization,
                                                       String ConditionsOrganization, String Status) {
        page.waitE_visibilityOf(page.checkNameOrganization);

        System.out.println(page.checkNameOrganization.getText() + " = " + OrganizationName);
        Assert.assertEquals(page.checkNameOrganization.getText(), OrganizationName);

        System.out.println(page.checkNameAbbrOrganization.getText() + " = " + OrganizationNameAbbr);
        Assert.assertEquals(page.checkNameAbbrOrganization.getText(), OrganizationNameAbbr);

        try {
            Assert.assertTrue("Найден руководитель", !page.checkDirectorOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Руководитель не назначен и это хорошо");
        }

        System.out.println(page.checkHeadOrganization.getText() + " = " + HeadOrganization);
        Assert.assertEquals(page.checkHeadOrganization.getText(), HeadOrganization);

        try {
            Assert.assertTrue("Найдены условия оказания помощи", !page.checkConditionsOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Условия оказания помощи не найдены и это хорошо");
        }


        try {
            Assert.assertTrue("Найдены профиль организации", !page.checkProfileOrganization.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Профиль организации не найден и это хорошо");
        }

        System.out.println(page.checkDescriptionOrganization.getText() + " = " + DescriptionOrgnization);
        Assert.assertEquals(page.checkDescriptionOrganization.getText(), DescriptionOrgnization);

        for (WebElement Service : page.saveServices) {
            ListSaveServices.add(Service.getText().replace("\n", " "));
        }
        System.out.println(page.SelectedServices);
        System.out.println(ListSaveServices);
        Assert.assertEquals(page.SelectedServices, ListSaveServices);

        System.out.println(page.checkAdressOrganization.getText() + " = " + Adress);
        Assert.assertEquals(page.checkAdressOrganization.getText(), Adress);

        System.out.println(page.checkEmailOrganization.getText() + " = " + Email);
        Assert.assertEquals(page.checkEmailOrganization.getText(), Email);

        System.out.println(page.checkVkOrganization.getText() + " = " + Vk);
        Assert.assertEquals(page.checkVkOrganization.getText(), Vk);

        System.out.println(page.checkFacebookOrganization.getText() + " = " + Facebook);
        Assert.assertEquals(page.checkFacebookOrganization.getText(), Facebook);

        System.out.println(page.checkInstagramOrganization.getText() + " = " + Instagram);
        Assert.assertEquals(page.checkInstagramOrganization.getText(), Instagram);

        System.out.println(page.checkOtherOrganization.getText() + " = " + Other);
        Assert.assertEquals(page.checkOtherOrganization.getText(), Other);

        System.out.println(page.checkAppointmentPhone1.get(0).getText() + " = " + AppointmentPhone1);
        Assert.assertEquals(page.checkAppointmentPhone1.get(0).getText(), AppointmentPhone1);

        System.out.println(page.checkPhoneNumber1.get(0).getText() + " = " + "+7 (" + PhoneNumber1.charAt(0) + PhoneNumber1.charAt(1) +
                PhoneNumber1.charAt(2) + ") " + PhoneNumber1.charAt(3) + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + "-" +
                PhoneNumber1.charAt(6) + PhoneNumber1.charAt(7) + "-" + PhoneNumber1.charAt(8) + PhoneNumber1.charAt(9));
        Assert.assertEquals(page.checkPhoneNumber1.get(0).getText(),
                "+7 (" + PhoneNumber1.charAt(0) + PhoneNumber1.charAt(1) +
                        PhoneNumber1.charAt(2) + ") " + PhoneNumber1.charAt(3) + PhoneNumber1.charAt(4) + PhoneNumber1.charAt(5) + "-" +
                        PhoneNumber1.charAt(6) + PhoneNumber1.charAt(7) + "-" + PhoneNumber1.charAt(8) + PhoneNumber1.charAt(9));

        System.out.println(page.checkAppointmentPhone1.get(1).getText() + " = " + AppointmentPhone2);
        Assert.assertEquals(page.checkAppointmentPhone1.get(1).getText(), AppointmentPhone2);

        System.out.println(page.checkPhoneNumber1.get(1).getText() + " = " + "+7 (" + PhoneNumber2.charAt(0) + PhoneNumber2.charAt(1) +
                PhoneNumber2.charAt(2) + ") " + PhoneNumber2.charAt(3) + PhoneNumber2.charAt(4) + PhoneNumber2.charAt(5) + "-" +
                PhoneNumber2.charAt(6) + PhoneNumber2.charAt(7) + "-" + PhoneNumber2.charAt(8) + PhoneNumber2.charAt(9));
        Assert.assertEquals(page.checkPhoneNumber1.get(1).getText(),
                "+7 (" + PhoneNumber2.charAt(0) + PhoneNumber2.charAt(1) +
                        PhoneNumber2.charAt(2) + ") " + PhoneNumber2.charAt(3) + PhoneNumber2.charAt(4) + PhoneNumber2.charAt(5) + "-" +
                        PhoneNumber2.charAt(6) + PhoneNumber2.charAt(7) + "-" + PhoneNumber2.charAt(8) + PhoneNumber2.charAt(9));

        page.waitE_ClickableAndClick(page.buttonFinish);
        NowDate = page.nowDate();
        page.waitE_ClickableAndClick(page.menuOrganizations);
        page.sleep(1000);
    }

    public void stepFiveCreateOrganization(String OrganizationName, String OrganizationNameAbbr, String FIODirector,
                                           String OrganizationProfile, String DescriptionOrgnization,
                                           String AppointmentPhone1, String PhoneNumber1, String AppointmentPhone2,
                                           String PhoneNumber2, String Adress, String Email, String Vk, String Facebook,
                                           String Instagram, String Other, String Code, String IdentifierOMS,
                                           String RegistrationNumber, String IdentifierPUMP, String HeadOrganization,
                                           String ConditionsOrganization, String Status) {
        page.moveToProfileOrg(OrganizationName);
        page.waitE_visibilityOf(page.qrProfOrg);

        page.compareStringAndWebelement(OrganizationName, page.nameProfOrg);
//       Сокращенное название
        page.compareStringAndWebelement(HeadOrganization, page.headOrganizationProfOrg);
        //       Условия
        //     Профиль
        Assert.assertTrue(page.linkAppointmentServicesProfOrg.isEnabled());
        String FioDirectorProfOrg =
                page.directorFirstNameProfOrg.getText() + " " +
                        page.directorSecondNameProfOrg.getText() + " " + page.directorMiddleNameProfOrg.getText();
        page.compareStringAndString(FioDirectorProfOrg, FIODirector);
        page.compareStringAndWebelement(DescriptionOrgnization, page.descriptionProfOrg);
        page.elementIsDisabled(page.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(page.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(page.headerServicesProfOrg.isEnabled());

        int x = -1;
        for (WebElement Service : page.listNamesServicesProfOrg) {
            x = x + 1;
            ListServicesInProfile.add(page.listNamesServicesProfOrg.get(x).getText() + " " +
                    page.listVendorsServicesProfOrg.get(x).getText() + " " + page.listCostsServicesProfOrg.get(x).getText());
        }
        System.out.println(page.SelectedServices);
        System.out.println(ListServicesInProfile);
        Assert.assertEquals(page.SelectedServices, ListServicesInProfile);
        Assert.assertTrue(page.headerExecutorsProfOrg.isEnabled());
        Assert.assertTrue(page.mapProfOrg.isEnabled());
        page.waitE_ClickableAndClick(page.buttonEditProfOrg);
        page.waitE_visibilityOf(page.headerEditOrg);
        Assert.assertEquals("Редактировать организацию: " + OrganizationName, page.headerEditOrg.getText());
        driver.navigate().back();
        page.waitE_visibilityOf(page.adressProfOrg);
        page.compareStringAndWebelement(Adress, page.adressProfOrg);
        //телефоны
        page.compareStringAndWebelement(Email, page.emailProfOrg);
        page.compareStringAndWebelement(Vk, page.vkProfOrg);
        page.compareStringAndWebelement(Instagram, page.instagramProfOrg);
        page.compareStringAndWebelement(Facebook, page.facebookProfOrg);
        page.compareStringAndWebelement(Other, page.otherProfOrg);
        Assert.assertTrue(page.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(page.idProfOrg.getText()));
        page.compareStringAndWebelement(Code, page.codeProfOrg);
        page.compareStringAndWebelement(RegistrationNumber, page.numberOmsProfOrg);
        page.compareStringAndWebelement(IdentifierOMS, page.identifierOmsProfOrg);
        page.compareStringAndWebelement(IdentifierPUMP, page.identifierPumpProfOrg);
        page.compareStringAndWebelement(Status, page.statusProfOrg);
        page.compareStringAndWebelement(NowDate, page.dateCreateProfOrg);
        page.compareStringAndWebelement(NowDate, page.lastChangeProfOrg);
        Assert.assertTrue(page.qrProfOrg.isEnabled());
    }

    public void stepFiveCreateOrganizationTestDirector(String OrganizationName, String OrganizationNameAbbr,
                                                       String FIODirector,
                                                       String OrganizationProfile, String DescriptionOrgnization,
                                                       String AppointmentPhone1, String PhoneNumber1,
                                                       String AppointmentPhone2,
                                                       String PhoneNumber2, String Adress, String Email, String Vk,
                                                       String Facebook,
                                                       String Instagram, String Other, String Code,
                                                       String IdentifierOMS,
                                                       String RegistrationNumber, String IdentifierPUMP,
                                                       String HeadOrganization,
                                                       String ConditionsOrganization, String Status) {
        page.moveToProfileOrg(OrganizationName);
        page.waitE_visibilityOf(page.qrProfOrg);

        page.compareStringAndWebelement(OrganizationName, page.nameProfOrg);
//       Сокращенное название
        page.compareStringAndWebelement(HeadOrganization, page.headOrganizationProfOrg);
        //       Условия
        //     Профиль
        Assert.assertTrue(page.linkAppointmentServicesProfOrg.isEnabled());
        page.compareStringAndWebelement("Не указан", page.directorFirstNameProfOrg);
        page.compareStringAndWebelement("Описание длиной более 180 символов 11111111111 111111111111 " +
                        "111111111111111111111111111111111 1111111111111111111111 111111111111111111 1111111111111...",
                page.descriptionProfOrg);
        page.waitE_ClickableAndClick(page.linkDescriptionAdditionalProfOrg);
        page.compareStringAndWebelement(DescriptionOrgnization, page.fullDescriptionProfOrg);
        page.elementIsDisabled(page.linkDescriptionAdditionalProfOrg);
        page.waitE_ClickableAndClick(page.linkFullDescriptionHideProfOrg);
        page.compareStringAndWebelement("Описание длиной более 180 символов 11111111111 111111111111 " +
                        "111111111111111111111111111111111 1111111111111111111111 111111111111111111 1111111111111...",
                page.descriptionProfOrg);
        page.waitE_visibilityOf(page.linkDescriptionAdditionalProfOrg);
        Assert.assertTrue(page.headerStructureProfOrg.isEnabled());
        Assert.assertTrue(page.headerServicesProfOrg.isEnabled());

        int x = -1;
        for (WebElement Service : page.listNamesServicesProfOrg) {
            x = x + 1;
            ListServicesInProfile.add(page.listNamesServicesProfOrg.get(x).getText() + " " +
                    page.listVendorsServicesProfOrg.get(x).getText() + " " + page.listCostsServicesProfOrg.get(x).getText());
        }
        System.out.println(page.SelectedServices);
        System.out.println(ListServicesInProfile);
        Assert.assertEquals(page.SelectedServices, ListServicesInProfile);
        Assert.assertTrue(page.headerExecutorsProfOrg.isEnabled());
        Assert.assertTrue(page.mapProfOrg.isEnabled());
        page.waitE_ClickableAndClick(page.buttonEditProfOrg);
        page.waitE_visibilityOf(page.headerEditOrg);
        Assert.assertEquals("Редактировать организацию: " + OrganizationName, page.headerEditOrg.getText());
        driver.navigate().back();
        page.waitE_visibilityOf(page.adressProfOrg);
        page.compareStringAndWebelement(Adress, page.adressProfOrg);
        //телефоны
        page.compareStringAndWebelement(Email, page.emailProfOrg);
        page.compareStringAndWebelement(Vk, page.vkProfOrg);
        page.compareStringAndWebelement(Instagram, page.instagramProfOrg);
        page.compareStringAndWebelement(Facebook, page.facebookProfOrg);
        page.compareStringAndWebelement(Other, page.otherProfOrg);
        Assert.assertTrue(page.idProfOrg.isEnabled());
        Assert.assertTrue(driver.getCurrentUrl().contains(page.idProfOrg.getText()));
        page.compareStringAndWebelement(Code, page.codeProfOrg);
        page.compareStringAndWebelement(RegistrationNumber, page.numberOmsProfOrg);
        page.compareStringAndWebelement(IdentifierOMS, page.identifierOmsProfOrg);
        page.compareStringAndWebelement(IdentifierPUMP, page.identifierPumpProfOrg);
        page.compareStringAndWebelement(Status, page.statusProfOrg);
        page.compareStringAndWebelement(NowDate, page.dateCreateProfOrg);
        page.compareStringAndWebelement(NowDate, page.lastChangeProfOrg);
        Assert.assertTrue(page.qrProfOrg.isEnabled());
    }
}