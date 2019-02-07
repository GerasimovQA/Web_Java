package Organization.EditCabinet;

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
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class EditCabinetLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditCabinetPage p;

    private int c = 0;

    private ArrayList<String> Workplaces = new ArrayList<>();

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
            p = new EditCabinetPage(driver);
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
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsCreateCabinet);
        }
    };

    public void editCabinet(String CabinetID, String NameCabinet, String NameWorkplace, String NewNameCabinet, String NewDescriptionCabinet,
                            String NewWorkplace1, String NewWorkplace2) {
//Редактирование кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.sleep(1000);
        p.preloader();

        p.click(p.linkEditNameCabinet);
        p.clickAndSendKeys(p.inputNameCabinetEdit, NewNameCabinet);
        switch (NameCabinet) {
            case ("3 название кабинета №3"):
                p.click(p.buttonSaveNameCabinetEdit);
                break;
            case ("4 название кабинета №4"):
                p.click(p.buttonCancelSaveNameCabinetEdit);
                break;
            default:
                throw new Error("Блок название кабинета пропущен");
        }

        p.click(p.linkEditDescriptionCabinet);
        p.clickAndSendKeys(p.inputDescriptionCabinetEdit, NewDescriptionCabinet);
        switch (NameCabinet) {
            case ("3 название кабинета №3"):
                p.click(p.buttonSaveDescriptionCabinetEdit);
                break;
            case ("4 название кабинета №4"):
                p.click(p.buttonCancelSaveDescriptionCabinetEdit);
                break;
            default:
                throw new Error("Блок описание пропущен");
        }

        p.click(p.iconEditNameWorkplace.get(0));
        switch (NameCabinet) {
            case ("3 название кабинета №3"):
                p.clickAndSendKeys(p.inputNameWorkplaceEdit.get(0), NewWorkplace1);
                p.click(p.buttonSaveWorkplaceEdit.get(0));
                p.click(p.iconAddWorkplaceEdit.get(0));
                p.clickAndSendKeys(p.inputNameWorkplaceEdit.get(0), NewWorkplace2);
                p.click(p.buttonSaveWorkplaceEdit.get(0));
                Workplaces.add(NewWorkplace1);
                Workplaces.add(NewWorkplace2);
                break;
            case ("4 название кабинета №4"):
                p.clickAndSendKeys(p.inputNameWorkplaceEdit.get(0), NewWorkplace1);
                p.click(p.buttonCancelSaveWorkplaceEdit);
                p.click(p.iconAddWorkplaceEdit.get(0));
                p.clickAndSendKeys(p.inputNameWorkplaceEdit.get(0), NewWorkplace2);
                p.click(p.buttonCancelSaveWorkplaceEdit);
                Workplaces.add(NameWorkplace);
                break;
            default:
                throw new Error("Блок создание рабочего места пропушен");
        }

//Проверка изменений
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.sleep(1000);
        p.preloader();

        switch (NameCabinet) {
            case ("3 название кабинета №3"):
                for (WebElement cab : p.listNameCabinet) {
                    p.moveMouse(cab);
                    p.pr(cab.getText());
                    if (NewNameCabinet.equals(cab.getText())) {
                        c = c + 1;
                        p.pr("Кабинет найден");
                        p.click(cab);
                    }
                }
                if (c != 1) {
                    throw new Error("Кабинет не найден");
                }
                break;
            case ("4 название кабинета №4"):
                for (WebElement cab : p.listNameCabinet) {
                    p.moveMouse(cab);
                    p.pr(cab.getText());
                    if (NameCabinet.equals(cab.getText())) {
                        c = c + 1;
                        p.pr("Кабинет найден");
                        p.click(cab);
                    }
                }
                if (c != 1) {
                    throw new Error("Кабинет не найден");
                }
                break;
            default:
                throw new Error("Блок 'Поиск созданного кабинета' пропушен");
        }

        switch (NameCabinet) {
            case ("3 название кабинета №3"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            case ("4 название кабинета №4"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            default:
                throw new Error("Блок 'Поиск созданного рабочего места' пропушен");
        }
    }

    public void deleteWorkplaceEdit(String CabinetID, String NameCabinet, String NameWorkplace) {
//Удаление рабочего места кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.sleep(1000);
        p.preloader();
        p.click(p.iconDeleteWorkplaceEdit.get(0));
        switch (NameCabinet) {
            case ("5 название кабинета №5"):
                p.click(p.buttonConfirmationDeleteWorkplace);
                break;
            case ("6 название кабинета №6"):
                p.click(p.buttonCancelDeleteWorkplace);
                Workplaces.add(NameWorkplace);
                break;
            default:
                throw new Error("Блок удаление кабинета пропущен");
        }
//Проверка удаления
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.sleep(1000);
        p.preloader();
        for (WebElement cab : p.listNameCabinet) {
            p.moveMouse(cab);
            p.pr(cab.getText());
            if (NameCabinet.equals(cab.getText())) {
                c = c + 1;
                p.pr("Кабинет найден");
                p.click(cab);
            }
        }
        if (c != 1) {
            throw new Error("Кабинет не найден");
        }

        switch (NameCabinet) {
            case ("5 название кабинета №5"):
                Assert.assertEquals(0, p.listNameWorkplace.size());
                break;
            case ("6 название кабинета №6"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            default:
                throw new Error("Блок 'Поиск созданного рабочего места' пропущен");
        }
    }

    public void deleteWorkplaceList(String NameCabinet, String NameWorkplace) {
//Удаление рабочего места кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.sleep(1000);
        p.preloader();
        for (WebElement cab : p.listNameCabinet) {
            p.moveMouse(cab);
            p.pr(cab.getText());
            if (NameCabinet.equals(cab.getText())) {
                c = c + 1;
                p.pr("Кабинет найден");
                p.click(cab);
            }
        }
        if (c != 1) {
            throw new Error("Кабинет не найден");
        }
        p.click(driver.findElement(By.xpath("//span[text()=\"" + NameWorkplace + "\"]/../../../following-sibling::div//i")));
        p.click(p.buttonDeleteInList);
        switch (NameCabinet) {
            case ("7 название кабинета №7"):
                p.click(p.buttonConfirmationDeleteWorkplace);
                break;
            case ("8 название кабинета №8"):
                p.click(p.buttonCancelDeleteWorkplace);
                Workplaces.add(NameWorkplace);
                break;
            default:
                throw new Error("Блок удаление кабинета пропущен");
        }
//Проверка удаления
        c = 0;
        driver.navigate().refresh();
        p.sleep(1000);
        p.preloader();
        for (WebElement cab : p.listNameCabinet) {
            p.moveMouse(cab);
            p.pr(cab.getText());
            if (NameCabinet.equals(cab.getText())) {
                c = c + 1;
                p.pr("Кабинет найден");
                p.click(cab);
            }
        }
        if (c != 1) {
            throw new Error("Кабинет не найден");
        }

        switch (NameCabinet) {
            case ("7 название кабинета №7"):
                Assert.assertEquals(0, p.listNameWorkplace.size());
                break;
            case ("8 название кабинета №8"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            default:
                throw new Error("Блок 'Поиск созданного рабочего места' пропущен");
        }
    }

    public void deleteCabinetEdit(String CabinetID, String NameCabinet, String NameWorkplace) {
//Удаление рабочего места кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/organization/edit-cabinet/" + CabinetID);
        p.sleep(1000);
        p.preloader();
        p.click(p.body);
        p.scrollEndPage();
        p.click(p.linkDeleteCabinetEdit);
        switch (NameCabinet) {
            case ("9 название кабинета №9"):
                p.click(p.buttonConfirmationDeleteCabinet);
                break;
            case ("10 название кабинета №10"):
                p.click(p.buttonCancelDeleteCabinet);
                Workplaces.add(NameWorkplace);
                break;
            default:
                throw new Error("Блок удаление кабинета пропущен");
        }
//Проверка удаления
        switch (NameCabinet) {
            case ("9 название кабинета №9"):
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
                p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
                p.sleep(1000);
                p.preloader();
                try {
                    for (WebElement cab : p.listNameCabinet) {
                        p.moveMouse(cab);
                        p.pr(cab.getText());
                        if (NameCabinet.equals(cab.getText())) {
                            c = c + 1;
                            throw new Error("Найден удаленный кабинет");
                        }
                    }
                } catch (TimeoutException e) {
                    p.pr("Кабинет не найден и это хорошо");
                }
                break;
            case ("10 название кабинета №10"):
                driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
                p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
                p.sleep(1000);
                p.preloader();
                for (WebElement cab : p.listNameCabinet) {
                    p.moveMouse(cab);
                    p.pr(cab.getText());
                    if (NameCabinet.equals(cab.getText())) {
                        c = c + 1;
                        p.pr("Кабинет найден");
                        p.click(cab);
                    }
                }
                if (c != 1) {
                    throw new Error("Кабинет не найден");
                }
                break;
            default:
                throw new Error("Блок 'Поиск удаленного кабинета пропушен");
        }
    }

    public void deleteCabinetList(String NameCabinet, String NameWorkplace) {
//Удаление кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.sleep(1000);
        p.preloader();
        p.click(driver.findElement(By.xpath("//span[text()=\"" + NameCabinet + "\"]/../../../following-sibling::div//i")));
        p.click(p.buttonDeleteInList);
        switch (NameCabinet) {
            case ("11 название кабинета №11"):
                p.click(p.buttonConfirmationDeleteCabinet);
                break;
            case ("12 название кабинета №12"):
                p.click(p.buttonCancelDeleteCabinet);
                break;
            default:
                throw new Error("Блок удаление кабинета пропущен");
        }
//Проверка удаления
        switch (NameCabinet) {
            case ("11 название кабинета №11"):
                driver.navigate().refresh();
                p.sleep(1000);
                p.preloader();
                try {
                    for (WebElement cab : p.listNameCabinet) {
                        p.moveMouse(cab);
                        p.pr(cab.getText());
                        if (NameCabinet.equals(cab.getText())) {
                            c = c + 1;
                            throw new Error("Найден удаленный кабинет");
                        }
                    }
                } catch (TimeoutException e) {
                    p.pr("Кабинет не найден и это хорошо");
                }
                break;
            case ("12 название кабинета №12"):
                driver.navigate().refresh();
                p.sleep(1000);
                p.preloader();
                for (WebElement cab : p.listNameCabinet) {
                    p.moveMouse(cab);
                    p.pr(cab.getText());
                    if (NameCabinet.equals(cab.getText())) {
                        c = c + 1;
                        p.pr("Кабинет найден");
                        p.click(cab);
                    }
                }
                if (c != 1) {
                    throw new Error("Кабинет не найден");
                }
                break;
            default:
                throw new Error("Блок 'Поиск удаленного кабинета пропушен");
        }
    }
}
