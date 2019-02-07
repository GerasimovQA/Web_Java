package Organization.CreateCabinet;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

@RunWith(JUnitParamsRunner.class)
public class CreateCabinetLogic {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private CreateCabinetPage p;

    private int c = 0;

    public ArrayList<String> Workplaces = new ArrayList<>();

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
            p = new CreateCabinetPage(driver);
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

    public void createCabinet(String NameCabinet, String DescriptionCabinet, String Workplace1, String Workplace2) {
//Создание кабинета
        driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.pr(ConfigProperties.getTestProperty("baseurl") + "/#/departments/5be180eb5a4c8615d6881f25#cabinets");
        p.sleep(1000);
        p.preloader();
        p.click(p.buttonAddCabinetInList);
        p.clickAndSendKeys(p.inputNameNumberCabinet, NameCabinet);
        switch (NameCabinet) {
            case ("Кабинет №1"):
                p.clickAndSendKeys(p.inputDescriptionCabinet, DescriptionCabinet);
                break;
            case ("Кабинет №2"):
                p.pr("Пропускаем заполнение описания");
                break;
            case ("Кабинет №3"):
                p.pr("Пропускаем заполнение описания");
                break;
            default:
                throw new Error("Блок описание пропущен");
        }

        switch (NameCabinet) {
            case ("Кабинет №1"):
                p.pr("Пропускаем создание рабочего места");
                Workplaces.add("Рабочее место 1");
                break;
            case ("Кабинет №2"):
                p.click(p.linkAddWorplaceCabinet);
                p.clickAndSendKeys(p.inputNameWorplaceCabinet.get(0), Workplace1);
                Workplaces.add(Workplace1);
                break;
            case ("Кабинет №3"):
                p.click(p.linkAddWorplaceCabinet);
                p.clickAndSendKeys(p.inputNameWorplaceCabinet.get(0), Workplace1);
                p.click(p.linkAddWorplaceCabinet);
                p.clickAndSendKeys(p.inputNameWorplaceCabinet.get(1), Workplace2);
                Workplaces.add(Workplace1);
                Workplaces.add(Workplace2);
                break;
            default:
                throw new Error("Блок создание рабочего места пропушен");
        }

//Проверка создания
        p.click(p.buttonAddCabinet);
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
            throw new Error("Кабинет не найден или найдено больше одного кабинета");
        }

        switch (NameCabinet) {
            case ("Кабинет №1"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            case ("Кабинет №2"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            case ("Кабинет №3"):
                p.compareWebelementsListAndArrayList(p.listNameWorkplace, Workplaces, Workplaces.size());
                break;
            default:
                throw new Error("Блок 'Поиск созданного рабочего места' пропушен");
        }
    }
}