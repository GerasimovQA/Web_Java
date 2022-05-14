package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.url;

public class DataBasePage extends BasePage {
    String specificDb = ".//a[text()='База данных']/../..//a[text()='%s']";

    public SelenideElement identificatorHeader = $x(".//span[text()='Идентификатор']");
    public SelenideElement dbNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement dbPortInput = $x(".//label[text()='Порт']/../following-sibling::div//input");
    public SelenideElement inOperativeCheckBox = $x(".//span[text()='В оперативной памяти']/preceding-sibling::span");
    public SelenideElement specifyLocationCheckBox = $x(".//span[text()='Указать расположение']/preceding-sibling::span");
    public SelenideElement sectionDbNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement locationInput = $x(".//label[text()='Расположение']/../following-sibling::div//input");
    public SelenideElement tablesSectionDBTab = $x(".//div[text()='Таблицы']");
    public SelenideElement consoleSectionDBTab = $x(".//div[text()='Консоль']");
    public SelenideElement usersSectionDBTab = $x(".//div[text()='Пользователи']");
    public SelenideElement textAreaConsoleSectionDBImput = $x(".//span[text()='Консоль Базы Данных']/../../following-sibling::div//textarea[not(@name)]");
    public SelenideElement userNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    public SelenideElement levelAccessActivate = $x(".//label[text()=\"Доступ\"]/../..//input/../..");
    public SelenideElement levelAccessInput = $x(".//label[text()=\"Доступ\"]/../..//input");
    public SelenideElement userPasswordInput = $x(".//label[text()='Пароль']/../following-sibling::div//input");
    public ElementsCollection headers = $$(By.xpath(".//div[@ class=\"panel-heading\"]"));

    public enum TypeDB {inMemory, notInMemory}

    @Step
    public void createDb(TypeDB typeDB, String dbName, String dbPort, String location) {
        click(dataBaseTab);
        click(createDbTab);
        val(dbNameInput, dbName);
        val(dbPortInput, dbPort);
        if (typeDB.equals(TypeDB.inMemory)) {
            click(inOperativeCheckBox);
        } else if (typeDB.equals(TypeDB.notInMemory)) {
            click(specifyLocationCheckBox);
            val(locationInput, location);
        }
        click(saveButton);
    }

    @Step
    public void deleteDB(String dbName) {
        click(dataBaseTab);
        click($x(String.format(specificDb, dbName)));
        click(actionButton);
        click(deleteButton);
        click(confirmationDeleteButton);
    }

    @Step
    public void createSectioDb(String dbName, String sectionName) {
        if (!url().contains("/store/instance")) {
            click(dataBaseTab);
        }
        click($x(String.format(specificDb, dbName)));
        click(addButton);
        val(sectionDbNameInput, sectionName);
        click(saveButton);
    }

    @Step
    public void goToSectionDb(String dbName, String sectionName) {
        if (!url().contains("/store/instance")) {
            click(dataBaseTab);
        }
        click($x(String.format(specificDb, dbName)));
        val(searchInput, sectionName);
        doubleClick(rowAfterSearch);
    }

    @Step
    public void createUserDb(String dbName, String sectionName, String userBdName, String levelAccess, String userBdPassword) {
        if (!url().contains("/store/instance")) {
            click(dataBaseTab);
        }
        click($x(String.format(specificDb, dbName)));
        val(searchInput, sectionName);
        click(rowAfterSearch);
        click(usersSectionDBTab);
        click(addButton);
        val(userNameInput, userBdName);
        click(levelAccessActivate);
        valAndSelectElement(levelAccessInput, levelAccess);
        val(userPasswordInput, userBdPassword);
        click(saveButton);
    }

    @Step
    public void executeCommandInDbConsole(String dbName, String sectionName, String command) {
        if (!url().contains("/store/instance")) {
            click(dataBaseTab);
        }
        click($x(String.format(specificDb, dbName)));
        val(searchInput, sectionName);
        doubleClick(rowAfterSearch);
        click(consoleSectionDBTab);
//        Selenide.executeJavaScript("document.querySelector(\"form > div > div > div:nth-child(1) > textarea\").style=\"opacity: 1;\"");
//        textAreaConsoleSectionDBImput.sendKeys(command);
        valInTextareaWithCodemirror(command);
        click(executeButton);
    }

}


