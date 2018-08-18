package edituser;

import global.Ashot;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigProperties;

import java.io.File;

@RunWith(JUnitParamsRunner.class)
public class EditUserLogic {

    private static WebDriver driver;
    private static EditUserPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new EditUserPage(driver);
            driver.get(ConfigProperties.getTestProperty("baseurl"));
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            captureScreenshot(description.getMethodName());
        }
    };

    private void captureScreenshot(String Name) {
        page.screenFailedTest(Name);
    }


//    @BeforeClass
//    public static void beforClass() {
//        page = new EditUserPage(driver);
//    }


    public void stepOneWithoutInfoToEmail(String Login, String Email, String Phone, String Password, String SecondName, String FirstName, String MiddleName, String Education, String EmailInfo, String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber, String Facebook, String Other, String Photo) {
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.checkboxInfoToEmail);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_evisibilityOf(page.successSecondStep);
        page.stepTwo();
        page.stepThree();
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo);
        page.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        try {
            if (page.labelSuperadministrator.isEnabled()) {
                throw new AssertionError("У простого юзера присутствует метка \"Суперадминистратор\"");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Все хорошо, метка \"Суперадминистратор\" не найдена");
        }
    }

    public void stepOneWithInfoToEmail(String Login, String Email, String Phone, String Password, String SecondName, String FirstName, String MiddleName, String Education, String EmailInfo, String PhoneInfo, String Instagram, String Vk, String Whatsapp, String Viber, String Facebook, String Other, String Photo) {
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.createUserStepOneBase(Login, Email, Phone, Password, SecondName, FirstName, MiddleName);
        page.waitE_ClickableAndClick(page.switchSuperadmin);
        page.waitE_ClickableAndClick(page.buttonAddUser);
        page.waitE_evisibilityOf(page.successSecondStep);
        page.stepTwo();
        page.stepThree();
        page.createUserStepFourBase(Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other, Photo);
        page.createUserStepFive(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.createUserStepSix(Login, Email, Phone, Password, SecondName, FirstName, MiddleName, Education, EmailInfo, PhoneInfo, Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.waitE_evisibilityOf(page.labelSuperadministrator);
        System.out.println("Все хорошо, метка \"Суперадминистратор\" найдена");
    }


    public void stepOneGeneratePassword() {
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
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
        Ashot.screenshot(driver, GlobalPage.NameFileShowPassword, 200);
        page.waitE_ClickableAndClick(page.notShowPassword);
        Ashot.screenshot(driver, GlobalPage.NameFileShowPassword, 200);
        File fileExpected = new File(GlobalPage.expectedDir + GlobalPage.NameFileShowPassword + ".png");
        File fileActual = new File(GlobalPage.actualDir + GlobalPage.NameFileShowPassword + ".png");
        File filediff = new File(GlobalPage.diffDir + GlobalPage.NameFileShowPassword + ".png");
        fileExpected.delete();
        fileActual.delete();
        filediff.delete();
    }


}