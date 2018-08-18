package autorization;

import createuser.CreateUserPage;
import global.IgnoreSsl;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigProperties;

@RunWith(JUnitParamsRunner.class)
public class AutorizationLogic {

    public static WebDriver driver;
    public static AutorizationPage page;

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new AutorizationPage(driver);
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
//        page = new AutorizationPage(driver);
//    }

       public void sucsses(String login, String password, String phrase) {
        page.auth(login, password);
        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.userinfoname, phrase);
    }

    public void emptyLoginPass(String login, String password, String phrase) {
        page.waitE_ClickableAndSendKeys(page.authInputLogin, login);
        page.waitE_ClickableAndSendKeys(page.authInputPassword, password);
        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.emptyloginpassword, phrase);
    }

    public void errorLoginPass(String login, String password, String phrase) {
        page.waitE_ClickableAndSendKeys(page.authInputLogin, login);
        page.waitE_ClickableAndSendKeys(page.authInputPassword, password);
        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.errorloginpassword, phrase);
    }
}