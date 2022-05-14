package Permissions.EditUser;

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
public class EditUserLogic {

    private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditUserPage p;

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
            p = new EditUserPage(driver);
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

    public void editUser(String Role, String UserID, String SecondName, String FirstName, String MiddleName) {
        switch (Role) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.waitTextPresent(p.profFio, "Акулин Аристарх Анатольевич");
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/" + UserID);
                p.sleep(2000);
                p.preloader();
                p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);
                p.click(p.buttonChangeProfile);
                p.sleep(2000);
                p.preloader();
                Assert.assertTrue(driver.getCurrentUrl().contains("/edit-user/" + UserID));
                p.waitVisibility(p.linkEditAuthProfile);

                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.waitTextPresent(p.profFio, "Акулов Игорь Иванович");
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/" + UserID);
                p.sleep(2000);
                p.preloader();
                p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);

                switch (SecondName) {
                    case ("Свинов"):
                        p.click(p.buttonChangeProfile);
                        p.sleep(2000);
                        p.preloader();
                        Assert.assertTrue(driver.getCurrentUrl().contains("/edit-user/" + UserID));
                        p.waitVisibility(p.linkEditAuthProfile);
                        break;

                    default:
                        try {
                            p.click(p.buttonChangeProfile);
                        } catch (TimeoutException e) {
                            p.pr("Кнопка 'Редактировать профиль' отсутствует и это хорошо");
                        }
                        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/edit-user/" + UserID);
                        p.sleep(2000);
                        p.preloader();
                        Assert.assertTrue("Админ проник на страницу редактироания чужого юзера",
                                driver.getCurrentUrl().contains("access/denied"));
                }
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.waitTextPresent(p.profFio, "Акульев Илья Петрович");
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/" + UserID);
                p.sleep(2000);
                p.preloader();
                p.waitTextPresent(p.profFio, SecondName + " " + FirstName + " " + MiddleName);
                try {
                    p.click(p.buttonChangeProfile);
                } catch (TimeoutException e) {
                    p.pr("Кнопка 'Редактировать профиль' отсутствует и это хорошо");
                }

                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/edit-user/" + UserID);
                p.sleep(2000);
                p.preloader();
                Assert.assertTrue("Специалист проник на страницу редактироания юзера",
                        driver.getCurrentUrl().contains("access/denied"));
                try {
                    p.waitVisibility(p.linkEditAuthProfile);
                } catch (NoSuchElementException e) {
                    p.pr("Ссылка 'Измениь данные для входа' отсутствует на станице редактирования и это хорошо");
                }
                break;

            default:
                throw new Error("Тест пропущен");
        }
    }
}



