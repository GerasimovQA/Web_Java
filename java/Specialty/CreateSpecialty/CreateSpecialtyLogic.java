package Specialty.CreateSpecialty;

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
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class CreateSpecialtyLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static RemoteWebDriver driver;
    private static CreateSpecialtyPage p;

    int i = -1;
    int u = 0;


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
            p = new CreateSpecialtyPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            driver.manage().logs().get(LogType.BROWSER);
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
//            p.sleep(1000);
//            p.preloader();
            driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties-add");
            p.sleep(1000);
            p.preloader();
        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsCreateSpecialty);
        }
    };

    @BeforeClass
    public static void beforClass() {
    }

    @AfterClass
    public static void afterClass() {
           }

    public void createSpecialty(String NameSpecialty, String DescriptionSpecialty) {
        p.clickAndSendKeys(p.inputNameSpecialty, NameSpecialty);
        if (NameSpecialty.equals("Название специальности №1")) {
            p.clickAndSendKeys(p.inputNameDescription, DescriptionSpecialty);
        }
        p.click(p.fieldSectionSpecialty);

        if (NameSpecialty.equals("Название специальности №1")) {
            p.click(p.sectionMedSpecialty);
        } else {
            p.click(p.sectionNotMedSpecialty);
        }

        if (NameSpecialty.equals("Название специальности №1")) {
            p.click(p.fieldIconSpecialty);
            p.click(p.iconSpecialty.get(p.random(p.iconSpecialty.size())));
        }
        p.click(p.buttonCreateSpecialty);

        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties");
        p.sleep(1000);
        p.preloader();

        switch (NameSpecialty) {
            case ("Название специальности №1"):
                p.click(p.sectionMedSpecialtyInListSpecialty);
                for (WebElement Name : p.nameSpecialtyInListSpecialty) {
                    i = i + 1;
                    p.moveMouse(Name);
                    System.out.println(Name.getText());
                    if (Name.getText().equals(NameSpecialty)) {
                        u = u + 1;
                        break;
                    }
                }
                p.moveMouse(p.descriptionSpecialtyInListSpecialty.get(i));
                System.out.println(p.descriptionSpecialtyInListSpecialty.get(i).getText());
                Assert.assertEquals(DescriptionSpecialty, p.descriptionSpecialtyInListSpecialty.get(i).getText());
                break;

            case ("One Specialty"):
                p.click(p.sectionNotMedSpecialtyInListSpecialty);
                for (WebElement Name : p.nameSpecialtyInListSpecialty) {
                    i = i + 1;
                    p.moveMouse(Name);
                    if (Name.getText().equals(NameSpecialty)) {
                        u = u + 1;
                        break;
                    }
                }
                p.moveMouse(p.descriptionSpecialtyInListSpecialty.get(i));
                System.out.println(p.descriptionSpecialtyInListSpecialty.get(i).getText());
                Assert.assertTrue(!DescriptionSpecialty.equals(p.descriptionSpecialtyInListSpecialty.get(i).getText()));
        }
    }


}