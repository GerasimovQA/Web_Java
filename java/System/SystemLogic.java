package System;

import Global.Capa;
import Global.GlobalPage;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class SystemLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private SystemPage p;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("\n\n\n");
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new SystemPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsSystem);
        }
    };

    @BeforeClass
    public static void beforClass() {
    }

    @AfterClass
    public static void afterClass() {
            }

    public void loginFront(String Login, String Password) {
        p.auth(Login, Password);
        p.sleep(2000);
    }

    public void logoutFront() {
        p.sleep(1000);
        driver.navigate().refresh();
        p.sleep(3000);
//        System.out.println("0");
        System.out.println("Текуший URL: " + driver.getCurrentUrl());
//        if (driver.getCurrentUrl().contains("http://localhost:8000/#/login")) {return;}
//        p.click(p.menuWorkers);
//        System.out.println("1");
//        if (driver.getCurrentUrl().contains("http://localhost:8000/#/login")) {return;}
//        p.click(p.buttonUserList);
//        System.out.println("2");
//        p.sleep(5000);
//        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertEquals(ConfigProperties.getTestProperty("baseurl") + "/#/login", driver.getCurrentUrl());
    }
}



