package Specialty.EditSpecialty;

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
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class EditSpecialtyLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditSpecialtyPage p;

    private int RandomIcon = 0;
    private String NameIcon = "";

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
            p = new EditSpecialtyPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditSpecialty);
        }
    };

    public void editSpecialty(String SpecialtyID, String NameSpecialty1, String NewNameSpecialty1,
                              String NewDescription1, String NewIcon1, String NewParent1) {
        p.sleep(1000);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        p.sleep(1000);
        p.preloader();
        switch (NameSpecialty1) {
            case ("Тестовый Патологоанатом"):
                p.click(p.linkEditNameSpecialty);
                p.clickAndSendKeys(p.inputNewNameSpecialty, NewNameSpecialty1);
                p.click(p.buttonSaveNewNameSpecialty);

                p.click(p.linkEditNameSectionSpecialty);
                p.click(p.fieldEditSectionSpecialty);
                p.click(p.sectionNotMedSpecialty);
                p.click(p.buttonSaveNewectionSpecialty);

                p.click(p.linkEditNameDescriptionSpecialty);
                p.clickAndSendKeys(p.inputDescriptionSpecialty, NewDescription1);
                p.click(p.buttonSaveNewDescriptionSpecialty);

                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
                p.sleep(1000);
                p.preloader();

                p.waitTextPresent(p.nameSpecialtyProfile, NewNameSpecialty1);
                p.waitTextPresent(p.descriptionSpecialtyProfile, NewDescription1);
                p.waitTextPresent(p.sectionSpecialtyProfile, "Немедицинская");
                System.out.println((p.iconSpecialtyProfile.getAttribute("alt")) + " =  "+ "nonmedical_specialty");
                Assert.assertTrue(p.iconSpecialtyProfile.getAttribute("alt").contains("nonmedical_specialty"));
                break;

            case ("Ноголом"):
                p.click(p.linkEditNameSpecialty);
                p.clickAndSendKeys(p.inputNewNameSpecialty, NewNameSpecialty1);
                p.click(p.buttonSaveNewNameSpecialty);

                p.click(p.linkEditNameDescriptionSpecialty);
                p.click(p.inputDescriptionSpecialty);
                p.inputDescriptionSpecialty.sendKeys("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                p.clickAndSendKeys(p.inputDescriptionSpecialty, NewDescription1);
                p.click(p.buttonSaveNewDescriptionSpecialty);

                p.click(p.linkEditIconSpecialty);
                p.click(p.inputNewDescriptionSpecialty);
                RandomIcon = p.random(p.iconSpecialty.size() - 1);
                p.click(p.iconSpecialty.get(RandomIcon));
                NameIcon = p.iconSpecialty.get(RandomIcon + 1).getAttribute("alt");
                System.out.println("Выбпанная иконка: " + NameIcon);
                p.click(p.buttonSaveNewIconSpecialty);

                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
                p.sleep(1000);
                p.preloader();

                p.waitTextPresent(p.nameSpecialtyProfile, NewNameSpecialty1);
                p.waitTextPresent(p.descriptionSpecialtyProfile, NewDescription1);
                p.waitTextPresent(p.sectionSpecialtyProfile, "Медицинская");
                System.out.println((p.iconSpecialtyProfile.getAttribute("alt"))+ " =  "+ NameIcon);
                Assert.assertTrue(p.iconSpecialtyProfile.getAttribute("alt").contains(NameIcon));
                break;

            case ("Рукоправ"):
                p.click(p.linkEditNameSpecialty);
                p.clickAndSendKeys(p.inputNewNameSpecialty, NewNameSpecialty1);
                p.click(p.buttonSaveNewNameSpecialty);

                p.click(p.linkEditNameSectionSpecialty);
                p.click(p.fieldEditSectionSpecialty);
                p.click(p.sectionMedSpecialty);
                p.click(p.buttonSaveNewectionSpecialty);

                p.click(p.linkEditNameDescriptionSpecialty);
                p.click(p.inputDescriptionSpecialty);
                p.inputDescriptionSpecialty.sendKeys("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                p.clickAndSendKeys(p.inputDescriptionSpecialty, NewDescription1);
                p.click(p.buttonSaveNewDescriptionSpecialty);

                p.click(p.linkEditIconSpecialty);
                p.click(p.inputNewDescriptionSpecialty);
                RandomIcon = p.random(p.iconSpecialty.size() - 1);
                p.click(p.iconSpecialty.get(RandomIcon));
                NameIcon = p.iconSpecialty.get(RandomIcon + 1).getAttribute("alt");
                System.out.println("Выбпанная иконка: " + NameIcon);
                p.click(p.buttonSaveNewIconSpecialty);

                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
                p.sleep(1000);
                p.preloader();

                p.waitTextPresent(p.nameSpecialtyProfile, NewNameSpecialty1);
                p.waitTextPresent(p.descriptionSpecialtyProfile, NewDescription1);
                p.waitTextPresent(p.sectionSpecialtyProfile, "Медицинская");
                System.out.println((p.iconSpecialtyProfile.getAttribute("alt"))+ " =  "+ NameIcon);
                Assert.assertTrue(p.iconSpecialtyProfile.getAttribute("alt").contains(NameIcon));
        }
    }

    public void cancelEditSpecialty(String SpecialtyID, String NameSpecialty1, String NewNameSpecialty1, String Description1,
                                    String NewDescription1, String NewIcon1, String NewParent1) {
        p.sleep(1000);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
        p.sleep(1000);
        p.preloader();

        p.click(p.buttonEditSpecialty);
        p.click(p.linkEditNameSpecialty);
        p.clickAndSendKeys(p.inputNewNameSpecialty, NewNameSpecialty1);
        p.click(p.buttonCancelSaveNewNameSpecialty);

        p.click(p.linkEditNameSectionSpecialty);
        p.click(p.fieldEditSectionSpecialty);
        p.click(p.sectionNotMedSpecialty);
        p.click(p.buttonCancelSaveNewSectionSpecialty);

        p.click(p.linkEditNameDescriptionSpecialty);
        p.clickAndSendKeys(p.inputDescriptionSpecialty, NewDescription1);
        p.click(p.buttonCancelSaveNewDescriptionSpecialty);

        p.click(p.linkEditIconSpecialty);
        p.click(p.inputNewDescriptionSpecialty);
        RandomIcon = p.random(p.iconSpecialty.size());
        p.click(p.iconSpecialty.get(RandomIcon));
        NameIcon = p.iconSpecialty.get(RandomIcon).getAttribute("alt");
        p.click(p.buttonCancelSaveNewIconSpecialty);

        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties/" + SpecialtyID);
        p.sleep(1000);
        p.preloader();

        p.waitTextPresent(p.nameSpecialtyProfile, NameSpecialty1);
        p.waitTextPresent(p.descriptionSpecialtyProfile, Description1);
        p.waitTextPresent(p.sectionSpecialtyProfile, "Медицинская");
        System.out.println((p.iconSpecialtyProfile.getAttribute("alt"))+ " = " + "neurosurgeon");
        Assert.assertTrue(p.iconSpecialtyProfile.getAttribute("alt").contains("neurosurgeon"));
    }

    public void deleteSpecialty(String SpecialtyID, String NameSpecialty1) {
        p.sleep(1000);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkDeleteSpecialty);
        p.waitVisibility(p.buttonDeleteSpecialty);
        p.click(p.buttonDeleteSpecialty);
        p.sleep(1000);

        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties");
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties");
        p.sleep(1000);
        p.preloader();
        p.click(p.sectionMedSpecialtyInListSpecialty);
        p.click(p.sectionNotMedSpecialtyInListSpecialty);

        for (WebElement Name : p.nameSpecialtyInListSpecialty) {
            p.moveMouse(Name);
            System.out.println(Name.getText());
            if (Name.getText().equals(NameSpecialty1)) {
                throw new AssertionError("Удаленная специальность найдена");
            }
        }
        System.out.println("Удаленная специальность не найдена");
    }

    public void cancelDeleteSpecialty(String SpecialtyID, String NameSpecialty1) {
        p.sleep(1000);
        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/specialty-edit/" + SpecialtyID);
        p.sleep(1000);
        p.preloader();
        p.click(p.linkDeleteSpecialty);
        p.click(p.buttonCancelDeleteSpecialty);

        System.out.println(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties");
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/specialties");
        p.sleep(1000);
        p.preloader();
        p.click(p.sectionMedSpecialtyInListSpecialty);
        p.click(p.sectionNotMedSpecialtyInListSpecialty);

        for (WebElement Name : p.nameSpecialtyInListSpecialty) {
            p.moveMouse(Name);
            System.out.println(Name.getText());
            if (Name.getText().equals(NameSpecialty1)) {
                System.out.println("Организация найдена");
                return;
            }
        }
        throw new AssertionError("Организация НЕнайдена");
    }
}