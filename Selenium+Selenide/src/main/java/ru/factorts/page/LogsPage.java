package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LogsPage extends BasePage {

    static String levelLoggind = "//div[text()='%s']";
    static String classInterception = "//label[text()='Класс']/../following-sibling::div//span[text()='%s']";

    SelenideElement levelInput = $x(".//div[label[text()='Минимальный уровень: ']]//div[@class='Select-control']"),
            searchInput = $$x(".//label[text()=\"Поиск \"]/input").find(visible),
            settingButton = $x(".//span[text()=\"Настроить\"]/.."),
            getReportButton = $x(".//span[text()=\"Получить отчет\"]/.."),
            mainCheckbox = $x(".//th[@rowspan='1']//input[@value='on']"),
            savaButton = $x(".//div[text()='Фильтр логирования']/../..//span[text()='Сохранить']/.."),
    //Filter
    setAllJournalTypesCheckbox = $x(".//th[text()=\"Тип журнала\"]/preceding-sibling::th//input/.."),
    //            filterButtonAll = $x(".//button[text()='Все']"),
    minimumLevel = $x(".//label[text()='Минимальный уровень:']/../following-sibling::div"),
            expandListSelectorsForLog = $x(".//thead//span[@aria-label=\"down\"]"),
            pickAllLogs = $x(".//li[text()='Выбрать все']");
    public SelenideElement afterSearchTableRow = $x(".//table/tbody/tr[@role=\"row\"]");
    private ElementsCollection afterSearchTableRows = $$x(".//table/tbody/tr[@role=\"row\"]").filterBy(visible);
    private ElementsCollection rowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]");
    public SelenideElement cellPlusInRowOfLogs = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[1]");
    public SelenideElement cellTimeInRowOfLogs = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[2]");
    public SelenideElement cellSourceInRowOfLogs = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[3]");
    public SelenideElement cellTypeInRowOfLogs = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[4]");
    public SelenideElement cellMessageInRowOfLogs = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[5]");

    public ElementsCollection listCellPlusInRowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[1]");
    public ElementsCollection listCellTimeInRowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[2]");
    public ElementsCollection listCellSourceInRowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[3]");
    public ElementsCollection listCellTypeInRowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[4]");
    public ElementsCollection listCellMessageInRowOfLogs = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level\")]/td[5]");
    //Context menu
    public SelenideElement addFilterInContextMenu = $x(".//div[text()=' Настройка перехвата']");
    //Interception Setting
    public SelenideElement classInInterceptionSettingSelect = $x(".//label[text()='Класс']/../following-sibling::div//div");
    public SelenideElement classInInterceptionSettingInput = $x(".//label[text()='Класс']/../following-sibling::div//input");
    public SelenideElement regularExpressionInputInInterceptionSetting = $x(".//label[text()='Регулярное выражение']/../following-sibling::div//input");
    public SelenideElement changeInputInInterceptionSetting = $x(".//label[text()='Замена']/../following-sibling::div//input");
    public SelenideElement debugCheckBoxInInterceptionSetting = $x(".//span[text()='Отладка']/preceding-sibling::span");
    public SelenideElement saveButtonInInterceptionSetting = $x(".//div[text()='Перехват сообщений']/../..//span[text()='Сохранить']/..");

    public enum Debug {YES, NO}

    ;

    /**
     * Set logs settings
     */
    @Step("Set logs settins: files {0}, level {1}")
    public void setLogsSettings(String files, String level, Boolean turnOn) {
        click(settingButton);
        click($x(String.format(".//button[contains(text(),'%s')]", files)));
        click(levelInput);
        click(levelInput.$x(String.format(".//*[contains(text(),'%s')]", level)));
        if (turnOn) {
            click(mainCheckbox);
        }
        click(savaButton);
    }

    /**
     * Choose logs files
     */
    @Step("Choose logs files {0}")
    public void chooseLogsFiles(String files) {
        click(settingButton);
        click($x(String.format(".//button[contains(text(),'%s')]", files)));
    }

    /**
     * Search and filter by Name
     */
    @Step("Searching {0}")
    public void search(String text) {
        refresh();
        val(searchInput, text);
    }

    @Step("Searching {0}")
    public void searchWithoutRefresh(String text) {
        val(searchInput, text);
    }

    /**
     * Clear search input
     */
    @Step("Clear search input")
    public void clearSearchInput() {
        searchInput.clear();
    }

    /**
     * Logs rows after search should be exist
     */
    @Step("{0} logs rows after search should be exist")
    public void existNLogsRowsAfterSearch(int expectedSize) {
        $x(String.format(".//table/tbody/tr[@role=\"row\"][%d]", expectedSize)).isDisplayed();
    }


    /**
     * Set show all logs
     */
    @Step("Set show all logs")
    public void setShowAllLogs(String level) {
        click(logsPageTab);
        click(settingButton);
//        click(filterButtonAll);
        click(minimumLevel);
        click($x(String.format(levelLoggind, level)));
//        sout(setAllJournalTypesCheckbox.getAttribute("class"));
//        if (!setAllJournalTypesCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
//            click(setAllJournalTypesCheckbox);
//        }
        expandListSelectorsForLog.hover();
        click(pickAllLogs);
        click(savaButton);
    }

    @Step("Setting log")
    public void findRecordsInLog(String record) {
        click(logsPageTab);
        searchWithoutRefresh(record);
        sout("Ишем в логе запись: " + record);
        cellMessageInRowOfLogs.shouldHave(exactText(record));
//        Assert.assertTrue("Искомая запись не найдена в логе",cellMessageInRowOfLogs.isDisplayed());
    }

    @Step("Setting log")
    public void notFindRecordsInLog(String record) {
        click(logsPageTab);
        searchWithoutRefresh(record);
        sleep(1000);
        sout("Ишем в логе запись: " + record);
        Assert.assertFalse(cellMessageInRowOfLogs.isDisplayed());
    }

    @Step
    public void interceptionSetting(String record, String changedRecord, Debug debug) {
        click(logsPageTab);
        searchWithoutRefresh(record);
        contextClick(cellMessageInRowOfLogs);
        click(addFilterInContextMenu);
        val(changeInputInInterceptionSetting, changedRecord);
        if (debug.equals(Debug.YES) && !debugCheckBoxInInterceptionSetting.isSelected()) {
            click(debugCheckBoxInInterceptionSetting);
        }
        if (debug.equals(Debug.NO) && debugCheckBoxInInterceptionSetting.isSelected()) {
            click(debugCheckBoxInInterceptionSetting);
        }
        click(saveButtonInInterceptionSetting);
    }

    @Step
    public void cancelInterceptionSetting(String record, String changedRecord, Debug debug) {
        click(logsPageTab);
        searchWithoutRefresh(record);
        contextClick(cellMessageInRowOfLogs);
        click(addFilterInContextMenu);
        val(changeInputInInterceptionSetting, changedRecord);
        if (debug.equals(Debug.YES) && !debugCheckBoxInInterceptionSetting.isSelected()) {
            click(debugCheckBoxInInterceptionSetting);
        }
        if (debug.equals(Debug.NO) && debugCheckBoxInInterceptionSetting.isSelected()) {
            click(debugCheckBoxInInterceptionSetting);
        }
        closeForm();
    }

    @Step("Check all parameters log")
    public void logCheckAllParameters(String source, String type, String message) {
        click(logsPageTab);
        autoUpdateOff();
        searchWithoutRefresh(message);
        cellPlusInRowOfLogs.shouldBe(visible);
        sout("Фактическое значемя \"Время\": " + cellTimeInRowOfLogs.getText());
        cellTimeInRowOfLogs.shouldNotBe(empty);
        sout("Ожидаемое значение \"Источник\": " + source + ". Фактическое : " + cellSourceInRowOfLogs.getText());
        cellSourceInRowOfLogs.shouldHave(exactText(source));
        sout("Ожидаемое значение \"Тип\": " + type + ". Фактическое : " + cellTypeInRowOfLogs.getText());
        cellTypeInRowOfLogs.shouldHave(exactText(type));
        sout("Ожидаемое значение \"Сообщение\": " + message + " содержится в фактическом : " + cellMessageInRowOfLogs.getText());
        cellMessageInRowOfLogs.shouldHave(text(message));
    }

    @Step("Check all parameters ew log")
    public void specifiedStringLogCheckAllParameters(int numString, String source, String type, String message) {
        click(logsPageTab);
        autoUpdateOff();
        searchWithoutRefresh(message);

//        listCellPlusInRowOfLogs.get(numString).shouldBe(visible);
//        sout("Фактическое значемя \"Время\": " + listCellTimeInRowOfLogs.get(numString).getText());
//        listCellTimeInRowOfLogs.get(numString).shouldNotBe(empty);
//        sout("Ожидаемое значение \"Источник\": " + source + ". Фактическое : " + listCellSourceInRowOfLogs.get(numString).getText());
//        listCellSourceInRowOfLogs.get(numString).shouldHave(exactText(source));
//        sout("Ожидаемое значение \"Тип\": " + type + ". Фактическое : " + listCellTypeInRowOfLogs.get(numString).getText());
//        listCellTypeInRowOfLogs.get(numString).shouldHave(exactText(type));
//        sout("Ожидаемое значение \"Сообщение\": " + message + " содержится в фактическом : " + listCellMessageInRowOfLogs.get(numString).getText());
//        listCellMessageInRowOfLogs.get(numString).shouldHave(text(message));


        cellPlusInRowOfLogs.shouldBe(visible);
        sout("Фактическое значемя \"Время\": " + cellTimeInRowOfLogs.getText());
        cellTimeInRowOfLogs.shouldNotBe(empty);
        sout("Ожидаемое значение \"Источник\": " + source + ". Фактическое : " + cellSourceInRowOfLogs.getText());
        cellSourceInRowOfLogs.shouldHave(exactText(source));
        sout("Ожидаемое значение \"Тип\": " + type + ". Фактическое : " + cellTypeInRowOfLogs.getText());
        cellTypeInRowOfLogs.shouldHave(exactText(type));
        sout("Ожидаемое значение \"Сообщение\": " + message + " содержится в фактическом : " + cellMessageInRowOfLogs.getText());
        cellMessageInRowOfLogs.shouldHave(text(message));
    }

    @Step("Check all parameters ew log")
    public void specifiedStringLogNotFound(String message) {
        click(logsPageTab);
        autoUpdateOff();
        searchWithoutRefresh(message);
        elementShouldNotBeVisible(cellMessageInRowOfLogs);
    }

}

