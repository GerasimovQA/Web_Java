package User.CreateUser;

import Global.Ashot;
import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class CreateUserLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private CreateUserPage p;

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
            driver.setFileDetector(new LocalFileDetector());
            p = new CreateUserPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            driver.manage().logs().get(LogType.BROWSER);
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(),  GlobalPage.failedTestsCreateUser);
        }
    };

    public void createUserWithoutInfoToEmail(String Login, String Password, String Email, String Phone,
                                             String SecondName, String FirstName, String MiddleName, String Education,
                                             String EmailInfo, String PhoneInfo, String Instagram, String Vk,
                                             String Whatsapp, String Viber, String Facebook, String Other,
                                             String Photo, String CheckedAndFocus, String Checkeds,
                                             String Indeterminate, String Empty, String Position) {
        p.createUserStepOneBase(Login, Password, Email, Phone, SecondName, FirstName, MiddleName);
        p.click(p.checkboxInfoToEmail);
        p.click(p.buttonAddUser);
        p.waitVisibility(p.successSecondStep);
        p.stepTwo(Position, SecondName);
        p.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        p.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        p.createUserStepFive(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        p.createUserStepSix(Login, Password, Email, Phone, SecondName, FirstName, MiddleName, Position, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        try {
            if (p.profLabelSuperadministrator.isEnabled()) {
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
        p.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        p.click(p.switchSuperadmin);
        p.click(p.buttonAddUser);
        p.waitVisibility(p.successSecondStep);
        p.stepTwo(Position, SecondName);
        p.stepThree(SecondName, CheckedAndFocus, Checkeds, Indeterminate, Empty);
        p.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other,
                Photo);
        p.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education,
                EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        p.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Position, Education, EmailInfo
                , PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        p.waitVisibility(p.profLabelSuperadministrator);
        System.out.println("Все хорошо, метка \"Суперадминистратор\" найдена");
    }

    public void stepOneGeneratePassword() {
        p.waitTextPresent(p.menuWorkers, "Сотрудники");
        try {
            p.click(p.menuCreateWorker);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.menuCreateWorker);
        }
        String Pass = "kozaybra";
        p.clickAndSendKeys(p.inputPassword, Pass);
        for (int i = 0; i < 10; i++) {
            p.click(p.generatePassword);
            String PassGen = p.inputPassword.getAttribute("value");
            Assert.assertTrue("Пароль не поменялся или null", !Pass.equals(PassGen) || !PassGen.equals(""));
            Pass = PassGen;
            System.out.println(PassGen);
        }
    }

    public void stepOneShowPassword() {
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 200,400);
        p.click(p.notShowPassword);
        p.sleep(1000);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 200,400);
//        File fileExpected = new File(GlobalPage.expectedDir + GlobalPage.ScreenshotAshot + ".png");
//        File fileActual = new File(GlobalPage.actualDir + GlobalPage.ScreenshotAshot + ".png");
//        File filediff = new File(GlobalPage.diffDir + GlobalPage.ScreenshotAshot + ".png");
//        fileExpected.delete();
//        fileActual.delete();
//        filediff.delete();
    }


}