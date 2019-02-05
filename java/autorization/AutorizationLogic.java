package Autorization;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;
import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class AutorizationLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private AutorizationPage p;

    @Rule
    public final TestRule screenshotRule = new TestWatcher() {

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
            p = new AutorizationPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
                                }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsAutorization);
        }
    };

    public void sucsses(String login, String password, String phrase) {
        p.auth(login, password);
        p.autorization(p.userinfoname, phrase);
    }

    public void emptyLoginPass(String login, String password, String phrase) {
        p.auth(login, password);
        p.autorization(p.emptyloginpassword, phrase);
    }

    public void errorLoginPass(String login, String password, String phrase) {
        System.out.println("Autorization begin");
        p.clickAndSendKeys(p.authInputLogin, login);
        System.out.println("Login: " + login);
        p.clickAndSendKeys(p.authInputPassword, password);
        System.out.println("Password: " + password);
        p.click(p.authButtonEnter);
        System.out.println("click Enter");
        p.sleep(7000);
        try {
            p.click(p.userinfoname);
        }catch (TimeoutException e){
            System.out.println("Не удалось аторизоваться и это хорошо");
            return;
        }
        throw new Error("Выполнена авторизация несуществующего юзера");
    }
}

