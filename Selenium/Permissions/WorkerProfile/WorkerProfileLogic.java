package Permissions.WorkerProfile;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class WorkerProfileLogic {

    private ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private WorkerProfilePage p;

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
            p = new WorkerProfilePage(driver);
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

    public void viewWorkerProfile(String Role) {
        switch (Role) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5b58130f0faa2638b94fc4d7");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                p.waitVisibility(p.profAccLogin);
                p.waitVisibility(p.profAccEmail);
                p.waitVisibility(p.profAccPhone);
                p.waitVisibility(p.profSystemID);
                p.waitVisibility(p.profSystemStatus);
                p.waitVisibility(p.profQR);
                p.waitVisibility(p.profLabelSuperadministrator);
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5c04d3bc7d05c82a53ec8d07");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                p.waitVisibility(p.profAccLogin);
                p.waitVisibility(p.profAccEmail);
                p.waitVisibility(p.profAccPhone);
                p.waitVisibility(p.profSystemID);
                p.waitVisibility(p.profSystemStatus);
                p.waitVisibility(p.profQR);
                try {
                    p.waitVisibility(p.profLabelSuperadministrator);
                } catch (NoSuchElementException e) {
                    System.out.println("Метка 'Супрадминистратор' не найдена и это хорошо");
                }
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/5b58130f0faa2638b94fc4d7");
                p.sleep(2000);
                p.preloader();
                p.waitVisibility(p.photoProfUser);
                p.waitVisibility(p.profEmail);
                try {
                    p.waitVisibility(p.profAccLogin);
                } catch (NoSuchElementException e) {
                    System.out.println("'Логин' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profAccEmail);
                } catch (NoSuchElementException e) {
                    System.out.println("'Емейл' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profAccPhone);
                } catch (NoSuchElementException e) {
                    System.out.println("'Телефон' для входа не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profSystemID);
                } catch (NoSuchElementException e) {
                    System.out.println("'ID' не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profSystemStatus);
                } catch (NoSuchElementException e) {
                    System.out.println("'Статус' не найден и это хорошо");
                }
                try {
                    p.waitVisibility(p.profLabelSuperadministrator);
                } catch (NoSuchElementException e) {
                    System.out.println("Метка 'Супрадминистратор' не найдена и это хорошо");
                }
                p.waitVisibility(p.profQR);
                p.waitVisibility(p.profFio);
                p.waitVisibility(p.profLabelOnline);
                p.waitVisibility(p.profPosition.get(0));
                p.waitVisibility(p.listProfSpeciality.get(0));
                p.waitVisibility(p.listProfNameWorkspace.get(0));
                p.waitVisibility(p.profEducation);
                p.waitVisibility(p.listProfServices.get(0));
                break;

            default:
                throw new Error("Тест пропущен");
        }
    }
}



