package createorganization;

import global.Ashot;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;

@RunWith(JUnitParamsRunner.class)
public class CreateOrgnizationLogic {

    private static WebDriver driver;
    private static CreateOrgnizationPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new CreateOrgnizationPage(driver);
            driver.get(ConfigProperties.getTestProperty("baseurl"));
//            driver.manage().addCookie(page.readCookiesFromFile());
//            driver.get("http://localhost:8000/#/");
            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

    public void stepOneCreateOrganization(String OrganizationName, String OrganizationNameAbbr,
                                          String SecondNameDirectorOrganization, String FirstNameDirectorOrganization,
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
        page.waitE_ClickableAndClick(page.buttonSearch);
        page.waitE_visibilityOf(page.FIODirecor);
        Assert.assertTrue(page.photoDirecor.isEnabled() & page.FIODirecor.getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.FIODirecor);
        Assert.assertTrue(page.photoAssignedDirecor.isEnabled() & page.FIOAssignedDirecor.getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.bulletHeadOrganization);
        page.waitE_ClickableAndClick(page.selectOrganizationConditions);

        WebElement OrganizationConditions = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//span[contains (text(), \"" + ConditionsOrganization + "\")]")));
        OrganizationConditions.click();

        page.waitE_ClickableAndSendKeys(page.inputOrganizationProfile, OrganizationProfile);
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep1);
    }

    public void stepTwoCreateOrganization() {
        page.waitE_ClickableAndClick(page.checkServices1);
        page.waitE_ClickableAndClick(page.checkServices2);
        page.waitE_ClickableAndClick(page.checkServices3);
        page.waitE_ClickableAndClick(page.checkServices4);
        page.waitE_ClickableAndClick(page.buttonAddOrganizationStep2);
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
        page.sleep(5000);
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
        page.waitE_ClickableAndClick(page.buttonListOrganizations);
        page.sleep(2000);
        for (WebElement oneOrganization : page.listOrganizations) {
            if (oneOrganization.getText().equals(OrganizationName)) {
                System.out.println("Созданная организация найдена в списке всех организаций");
                return;
            }

        }
        throw new AssertionError("Созданная организация НЕ найдена в списке всех организаций");
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

        System.out.println("Введенные значения: " + page.inputSecondNameDirectorOrganization.getAttribute("value") +
                page.inputFirstNameDirectorOrganization.getAttribute("value") + page.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertTrue(page.inputSecondNameDirectorOrganization.getAttribute("value")
                .equals(SecondNameDirectorOrganization));
        Assert.assertTrue(page.inputFirstNameDirectorOrganization.getAttribute("value")
                .equals(FirstNameDirectorOrganization));
        Assert.assertTrue(page.inputMiddleNameDirectorOrganization.getAttribute("value")
                .equals(MiddleNameDirectorOrganization));

        page.waitE_ClickableAndClick(page.buttonReset);
        System.out.println("Значения сброшены: " + page.inputSecondNameDirectorOrganization.getAttribute("value") +
                page.inputFirstNameDirectorOrganization.getAttribute("value") + page.inputMiddleNameDirectorOrganization.getAttribute("value"));

        Assert.assertTrue(page.inputSecondNameDirectorOrganization.getAttribute("value")
                .equals(""));
        Assert.assertTrue(page.inputFirstNameDirectorOrganization.getAttribute("value")
                .equals(""));
        Assert.assertTrue(page.inputMiddleNameDirectorOrganization.getAttribute("value")
                .equals(""));
        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.sleep(1000);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.FIODirecor.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Сотрудники не найдены и это хорошо");
        }
        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, SecondNameDirectorOrganization);
        page.waitE_ClickableAndClick(page.buttonSearch);
        page.waitE_visibilityOf(page.FIODirecor);
        Assert.assertTrue(page.photoDirecor.isEnabled() & page.FIODirecor.getText().equals(FIODirector));
        page.waitE_ClickableAndClick(page.clearListDirecors);
        page.sleep(500);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.FIODirecor.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Сотрудники не найдены и это хорошо-2");
        }

        page.waitE_ClickableAndClick(page.buttonEmployeeSearch);
        page.waitE_ClickableAndSendKeys(page.inputSecondNameDirectorOrganization, "а");
        page.waitE_ClickableAndClick(page.buttonSearch);
        page.waitE_visibilityOf(page.FIODirecor);
        Assert.assertTrue(page.photoDirecor.isEnabled() & page.FIODirecor.isEnabled());
        page.waitE_ClickableAndClick(page.ListFIODirecor.get(0));
        page.waitE_ClickableAndClick(page.changeDirecor);
        page.waitE_ClickableAndClick(page.ListFIODirecor.get(1));
        page.waitE_ClickableAndClick(page.changeDirecor);
        page.waitE_ClickableAndClick(page.clearListDirecors);
        page.sleep(500);
        try {
            Assert.assertTrue("Значения не сброшены, сотрудники найдены", !page.FIODirecor.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Сотрудники не найдены и это хорошо-возращение");
        }

        WebElement head = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (text(), \"ФГАОУ ВО " +
                        "Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\")]")));
        head.click();
        WebElement underHead1 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (text(), " +
                        "\"Университетская клиническая больница №3\")]")));
        underHead1.click();

        WebElement underHead2 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (text(), " +
                        "\"Клиника нервных болезней\")]")));
        underHead2.click();

        WebElement bulletUnderHead3 = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains (text(), " +
                        "\"Психотерапевтическое отделение\")]/../label")));
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
        page.sleep(5000);
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
                                                       String FIODirector,
                                                       String OrganizationProfile, String DescriptionOrgnization,
                                                       String AppointmentPhone1, String PhoneNumber1,
                                                       String AppointmentPhone2,
                                                       String PhoneNumber2, String Adress, String Email, String Vk,
                                                       String Facebook, String Instagram, String Other, String Code,
                                                       String IdentifierOMS, String RegistrationNumber,
                                                       String IdentifierPUMP,
                                                       String HeadOrganization, String ConditionsOrganization) {
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
        page.waitE_ClickableAndClick(page.buttonListOrganizations);
        page.sleep(2000);
        for (WebElement oneOrganization : page.listOrganizations) {
            if (oneOrganization.getText().equals(OrganizationName)) {
                System.out.println("Созданная организация найдена в списке всех организаций");
                return;
            }

        }
        throw new AssertionError("Созданная организация НЕ найдена в списке всех организаций");
    }
}