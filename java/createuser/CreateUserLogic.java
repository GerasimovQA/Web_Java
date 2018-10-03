package createuser;

import global.Ashot;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigProperties;

@RunWith(JUnitParamsRunner.class)
public class CreateUserLogic {

    private static WebDriver driver;
    private static CreateUserPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new CreateUserPage(driver);
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

//    private void captureScreenshot(String Name) {
//        page.screenFailedTest(Name);
//    }

//
//    @BeforeClass
//    public static void beforClass() {
//        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments(ConfigProperties.getTestProperty("head"));
//        options.addArguments("window-size=1200,800");
//        driver = new ChromeDriver(options);
//        page = new CreateOrgnizationPage(driver);
//        driver.get(ConfigProperties.getTestProperty("baseurl"));
//        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
//        page.waitE_visibilityOf(page.userinfoname);
//        page.writeCookiesToFile(driver);
//        driver.quit();
//    }


    public void createUserWithoutInfoToEmail(String Login, String Email, String Phone, String Password,
                                             String SecondName, String FirstName, String MiddleName, String Education,
                                             String EmailInfo, String PhoneInfo, String Instagram, String Vk,
                                             String Whatsapp, String Viber, String Facebook, String Other,
                                             String Photo, String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        page.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.checkboxInfoToEmail);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_visibilityOf(page.successSecondStep);
        page.stepTwo();
        page.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        page.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        try {
            if (page.labelSuperadministrator.isEnabled()) {
                throw new AssertionError("У простого юзера присутствует метка \"Суперадминистратор\"");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все хорошо, метка \"Суперадминистратор\" не найдена");
        }
    }

    public void createUserWithInfoToEmail(String Login, String Email, String Phone, String Password, String SecondName,
                                          String FirstName, String MiddleName, String Education, String EmailInfo,
                                          String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber,
                                          String Facebook, String Other, String Photo, String CheckedAndFocus,
                                          String Checkeds, String Indeterminate, String Empty) {
//        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.switchSuperadmin);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_visibilityOf(page.successSecondStep);
        page.stepTwo();
        page.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        page.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.waitE_visibilityOf(page.labelSuperadministrator);
        System.out.println("Все хорошо, метка \"Суперадминистратор\" найдена");
    }


    public void stepOneGeneratePassword() {
//        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.waitE_ClickableAndClick(page.menuWorkers);
        page.waitE_ClickableAndClick(page.menuCreateWorker);
        String Pass = "kozaybra";
        page.waitE_ClickableAndSendKeys(page.inputPassword, Pass);
        for (int i = 0; i < 10; i++) {
            page.waitE_ClickableAndClick(page.generatePassword);
            String PassGen = page.inputPassword.getAttribute("value");
            Assert.assertTrue("Пароль не поменялся или null", !Pass.equals(PassGen) || !PassGen.equals(""));
            Pass = PassGen;
            System.out.println(PassGen);
        }
    }

    public void stepOneShowPassword() {
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 200);
        page.waitE_ClickableAndClick(page.notShowPassword);
        page.sleep(1000);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 200);
//        File fileExpected = new File(GlobalPage.expectedDir + GlobalPage.ScreenshotAshot + ".png");
//        File fileActual = new File(GlobalPage.actualDir + GlobalPage.ScreenshotAshot + ".png");
//        File filediff = new File(GlobalPage.diffDir + GlobalPage.ScreenshotAshot + ".png");
//        fileExpected.delete();
//        fileActual.delete();
//        filediff.delete();
    }


}