package Permissions.CreateUser;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class CreateUserLogic {

    private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private CreateUserPage p;

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
            p = new CreateUserPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditPassword);
        }
    };

    public void createUser(String Role) {
        switch (Role) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
                p.click(p.menuWorkers);
                p.click(p.menuCreateWorker);
                p.sleep(2000);
                p.preloader();
                Assert.assertTrue(driver.getCurrentUrl().contains("users/create"));
                p.waitTextPresent(p.buttonAddUser, "Добавить сотрудника");
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
                p.click(p.menuWorkers);
                p.click(p.menuCreateWorker);
                p.sleep(2000);
                p.preloader();
                Assert.assertTrue(driver.getCurrentUrl().contains("users/create"));
                p.waitTextPresent(p.buttonAddUser, "Добавить сотрудника");
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
                p.click(p.menuWorkers);
                try {
                    p.click(p.menuCreateWorker);
                } catch (TimeoutException e) {
                    p.pr("Кнопка 'Создать сотрудника' отсутствует и это хорошо");
                }


                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/create");
                p.sleep(2000);
                p.preloader();
                Assert.assertTrue("Специалист проник на страницу создания юзера",
                        driver.getCurrentUrl().contains("access/denied"));
                try {
                    p.waitTextPresent(p.buttonAddUser, "Добавить сотрудника");
                } catch (NoSuchElementException e) {
                    p.pr("Кнопка 'Добавить сотрудника' отсутствует на станице создания сотрудника и это хорошо");
                }
                break;

            default:
                throw new Error("Тест пропущен");
        }
    }
}



