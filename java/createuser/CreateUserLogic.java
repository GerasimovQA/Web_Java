package createuser;

import createorganization.CreateOrgnizationPage;
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
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.util.logging.Level;

@RunWith(JUnitParamsRunner.class)
public class CreateUserLogic {

    private static WebDriver driver;
    private static CreateUserPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new CreateUserPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
////            driver.manage().addCookie(page.readCookiesFromFile());
////            driver.get("http://localhost:8000/#/");
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            page.SelectedServices.clear();
            page.SelectedSpecialtys.clear();
            page.SelectedOrg.clear();
            page.SelectedPositions.clear();
            page.SelectedRoles.clear();
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
        page = new CreateUserPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void createUserWithoutInfoToEmail(String Login, String Password, String Email, String Phone,
                                             String SecondName, String FirstName, String MiddleName, String Education,
                                             String EmailInfo, String PhoneInfo, String Instagram, String Vk,
                                             String Whatsapp, String Viber, String Facebook, String Other,
                                             String Photo, String CheckedAndFocus, String Checkeds,
                                             String Indeterminate, String Empty, String Position) {
        page.createUserStepOneBase(Login, Password, Email, Phone, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.checkboxInfoToEmail);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_visibilityOf(page.successSecondStep);
        page.stepTwo(Position);
        page.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        page.createUserStepFive(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Position, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        try {
            if (page.profLabelSuperadministrator.isEnabled()) {
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
                                          String Checkeds, String Indeterminate, String Empty, String Position) {
        page.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.switchSuperadmin);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_visibilityOf(page.successSecondStep);
        page.stepTwo(Position);
        page.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        page.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName,Position, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.waitE_visibilityOf(page.profLabelSuperadministrator);
        System.out.println("Все хорошо, метка \"Суперадминистратор\" найдена");
    }


    public void stepOneGeneratePassword() {
        try {
            page.waitE_ClickableAndClick(page.menuCreateWorker);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.menuCreateWorker);
        }
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