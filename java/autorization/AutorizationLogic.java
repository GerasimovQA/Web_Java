package autorization;

import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.util.logging.Level;

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

            LoggingPreferences logPrefs = new LoggingPreferences();     //
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);            //
            options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);  //


            driver = new ChromeDriver(options);
            page = new AutorizationPage(driver);
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            driver.manage().logs().get(LogType.BROWSER);        //

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

    public void sucsses(String login, String password, String phrase) {
        page.auth(login, password);
//        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.userinfoname, phrase);

        String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
        System.out.println(netData);

    }

    public void emptyLoginPass(String login, String password, String phrase) {
        page.auth(login, password);
//        page.waitE_ClickableAndSendKeys(page.authInputLogin, login);
//        page.waitE_ClickableAndSendKeys(page.authInputPassword, password);
//        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.emptyloginpassword, phrase);
    }

    public void errorLoginPass(String login, String password, String phrase) {
        page.auth(login, password);
//        page.waitE_ClickableAndSendKeys(page.authInputLogin, login);
//        page.waitE_ClickableAndSendKeys(page.authInputPassword, password);
//        page.waitE_ClickableAndClick(page.authButtonEnter);
        page.autorization(page.errorloginpassword, phrase);
    }

//    @BeforeClass
//    public static void beforClass() {
//
//                //        TestEnvironmentTest.environment();
////        EnvironmentLogic.createUserAPI();
//    }
}