package system;

import edituser.EditUserPage;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.util.logging.Level;

@RunWith(JUnitParamsRunner.class)
public class SystemLogic {

    private static WebDriver driver;
    private static SystemPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new SystemPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
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
        page = new SystemPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void logoutFront() {
        page.sleep(1000);
        System.out.println("0");
        if (driver.getCurrentUrl().contains("http://localhost:8000/#/login")) {return;}
        page.waitE_ClickableAndClick(page.menuWorkers);
        System.out.println("1");
        if (driver.getCurrentUrl().contains("http://localhost:8000/#/login")) {return;}
        page.waitE_ClickableAndClick(page.buttonUserList);
        System.out.println("2");
        page.sleep(5000);
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("http://localhost:8000/#/login"));
    }
}



