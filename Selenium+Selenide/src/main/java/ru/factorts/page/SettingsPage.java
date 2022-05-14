package ru.factorts.page;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SettingsPage extends BasePage {

    SmokeApi workProtocolPage = new SmokeApi();
    CommonComponents commonComponents = new CommonComponents();
    LoginPage loginPage = new LoginPage();
    LogsPage logsPage = new LogsPage();

    static String nameLibraryInTable = "//td[text()='%s']";
    static String turnOffConfirmationButton = "//p[text()='Вы уверены, что хотите отключить модуль \"%s\"?']/../following-sibling::div//span[text()='Отключить']/..";
    static String pointLevelInSelect = "//div[text()='%s']";
    static String pointAppendrInSelect = "//div[text()='%s']";

    SelenideElement
            //Server config
            serverConfig = $x(".//a[@href=\"#/settings/jetty\"]"),
            mqModuleSwitcher = $x(".//div[label[text()='Менеджер очередей']]/following-sibling::div"),
    //                systemManagmentTab = $x(".//a[text()='Управление системой']/.."),
//            libraryManagmentTab = $x(".//a[text()='Управление библиотеками']/.."),
//            configurationServerTab = $x(".//a[text()='Конфигурация сервера']/.."),
    deleteButtonInContextMenu = $$x(".//div[text()=' Удалить']").find(visible),
            deleteRecoverPointInContextMenu = $$x(".//*[contains(text(),'Удалить')]").find(visible),
            deleteButton = $x(".//span[text()='Удалить']/.."),

    //    loggingTab = $x(".//a[text()='Журналирование']/.."),
    appendersTab = $x(".//*[text()='Аппендеры']"),
            addAppenderOrLoggerOrInterceptionButton = $$x(".//button/span[text()=' Добавить']/..").find(visible),
            appenderNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            appenderRotationSelect = $x(".//label[text()='Ротация']/../following-sibling::div//div"),
            appenderRotationInput = $x(".//label[text()='Ротация']/../following-sibling::div//input"),
            appenderFileNameInput = $x(".//label[text()='Шаблон имени файла']/../following-sibling::div//input"),
            appenderPatternInput = $x(".//label[text()='Шаблон сообщения']/../following-sibling::div//input"),
            appenderMaxSizeInput = $x(".//label[text()='Максимальный размер файла (МБ)']/../following-sibling::div//input"),
            appenderSumArchivesInput = $x(".//label[text()='Количество архивных файлов на диске']/../following-sibling::div//input"),
            appenderNameInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[1]").find(visible),
            appenderRptationInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]").find(visible),
            appenderFileInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[3]").find(visible),
            appenderMaxSizeInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[4]").find(visible),
            appenderSumArchivesInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[5]").find(visible),
            appenderPatternInTable = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[6]").find(visible),

    loggersTab = $x(".//*[text()='Логгеры']"),
            loggerNameSelect = $x(".//label[text()='Имя']/../following-sibling::div//div"),
            loggerNameInput = $x(".//label[text()='Имя']/../following-sibling::div//div//input"),
            loggerLevelSelect = $x(".//label[text()='Уровень']/../following-sibling::div//div"),
            loggerLevelInput = $x(".//label[text()='Уровень']/../following-sibling::div//input"),
            appendersSelect = $x(".//label[text()='Аппендеры']/../following-sibling::div//div"),
            appendersInput = $x(".//label[text()='Аппендеры']/../following-sibling::div//input"),
            appendersClearSelect = $x(".//label[text()='Аппендеры']/../following-sibling::div//span[@class=\"anticon anticon-close-circle\"]"),
            sendEventCheckBox = $x(".//span[text()='Передавать событие родителю']/preceding-sibling::span"),
            loggerNameInTable = $$x("(//tr[@data-row-key]/td[1])[last()]").find(visible),
            loggersAppenderInTable = $x("(//tr[@data-row-key]/td[2])[last()]"),
            loggerLevelInTable = $x("(//tr[@data-row-key]/td[3])[last()]"),
            loggerSendEventInTable = $x("(//tr[@data-row-key]/td[4])[last()]"),

    interceptionTab = $x(".//*[text()='Перехват сообщений']"),
            interceptionClassInTable = $$x("(//tr[@data-row-key]/td[1])[last()]").find(visible),
            interceptionRegularExpressionInTable = $$x("(//tr[@data-row-key]/td[2])[last()]").find(visible),
            interceptionChangeInTable = $x("(//tr[@data-row-key]/td[3])[last()]"),
            interceptionDebugInTable = $x("(//tr[@data-row-key]/td[4])[last()]"),


    loadLibraryButton = $x(".//p[text()='Загрузить файлы библиотеки']/../../input"),
    successLoadedIcon = $x(".//span[@aria-label=\"check-circle\"]"),
            fullResetFesbButton = $x(".//h5[text()='После загрузки новых библиотек необходим полный перезапуск FESB']/following-sibling::div//button[text()='Полный перезапуск FESB']"),
            librarySearchInput = $$x(".//label[text()='Поиск ']/input").find(visible),
            cellNameLibraryInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]//td[1]"),
            cellDateLoadedLibraryInTable = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]//td[2]"),

    backToRestorePointButton = $x(".//span[text()='Вернуться к точке восстановления']/.."),
            restoreInContexMenu = $x(".//span[text()='Восстановить']"),
            autoUpdatePage = $x(".//input[@type=\"checkbox\"]/.."),
            manualUpdatePage = $x(".//span[@aria-label=\"reload\"]/ancestor::button[@class=\"ant-btn ant-btn-icon-only\"]"),


    //System management
    dashboardOn = $x(".//div[text()='Dashboard']/following-sibling::div//button[text()=' Запустить']"),

    //Confirmation menu
    turnOnConfirmationButton = $x(".//span[text()='Запустить']");

    ElementsCollection selectedAppenders = $$x(".//label[text()='Аппендеры']/../following-sibling::div//span[@class=\"ant-select-selection-item-content\"]");

    private SpecificQueuePage specificQueuePage;

    public enum SendEvent {ENABLED, DISABLED}

    public enum Debug {Yes, No}

    public SettingsPage() {
        if (!settingsPageTab.attr("class").contains("active")) {
            click(settingsPageTab);
        }
    }

    public SettingsPage(String empty) {
    }

    @Step("Search {0} SOPS")
    public void search(String sopsName) {
        librarySearchInput.clear();
        val(librarySearchInput, sopsName);
    }

    @Step("Turn off module - {0}")
    public void turnOffModule(String ModuleName) {
        click(systemManagmentTab);
        $$x(String.format(CommonComponents.moduleStartXpath, ModuleName)).find(visible).shouldHave(attribute("disabled"));
        $$x(String.format(CommonComponents.moduleActiveStatusXpath, ModuleName)).find(visible).shouldHave(exactText("Активирован"));
        $$x(String.format(CommonComponents.moduleStateXpath, ModuleName)).find(visible).shouldHave(exactText("Запущен"));
        switch (ModuleName) {
            case ("Менеджер очередей"):
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "Брокер")).find(visible).shouldHave(exactText("Активирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "Брокер")).find(visible).shouldHave(exactText("Запущен"));
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "REST")).find(visible).shouldHave(exactText("Активирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "REST")).find(visible).shouldHave(exactText("Запущен"));
            case ("Брокер"):
            case ("Dashboard"):
            case ("REST"):
            case ("База данных"):
            case ("Монитор транзакций"):
            case ("Веб-сервисы"):
            case ("Расширенный Менеджер очередей"):
            case ("Мультименеджер очередей"):
                break;
            default:
                throw new AssertionError("Проверка до ВЫКЛючения модуля пропущена");
        }

        click($$x(String.format(CommonComponents.moduleTurnOffXpath, ModuleName)).find(visible));
        click($x(String.format(turnOffConfirmationButton, ModuleName)));
        commonComponents.closeNotification();
        click(manualUpdatePage);

        $$x(String.format(CommonComponents.moduleTurnOffXpath, ModuleName)).find(visible).waitUntil(attribute("disabled"), 20000);
        $$x(String.format(CommonComponents.moduleStartXpath, ModuleName)).find(visible).shouldNotHave(attribute("disabled"));
        $$x(String.format(CommonComponents.moduleActiveStatusXpath, ModuleName)).find(visible).shouldHave(exactText("Деактивирован"));
        $$x(String.format(CommonComponents.moduleStateXpath, ModuleName)).find(visible).shouldHave(exactText("Остановлен"));
        switch (ModuleName) {
            case ("Менеджер очередей"):
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "Брокер")).find(visible).shouldHave(exactText("Деактивирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "Брокер")).find(visible).shouldHave(exactText("Остановлен"));
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "REST")).find(visible).shouldHave(exactText("Деактивирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "REST")).find(visible).shouldHave(exactText("Остановлен"));
            case ("Брокер"):
            case ("Dashboard"):
            case ("REST"):
            case ("База данных"):
            case ("Веб-сервисы"):
            case ("Расширенный Менеджер очередей"):
            case ("Мультименеджер очередей"):
                break;
            case ("Монитор транзакций"):
                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "База данных")).find(visible).shouldHave(exactText("Активирован"));
                $$x(String.format(CommonComponents.moduleStateXpath, "База данных")).find(visible).shouldHave(exactText("Запущен"));
                break;
            default:
                throw new AssertionError("Проверка после ВЫКЛючения модуля пропущена");
        }

    }

    @Step("Turn on module - {0}")
    public void turnOnModule(String ModuleName) {
        click(systemManagmentTab);
        $$x(String.format(CommonComponents.moduleStartXpath, ModuleName)).find(visible).shouldNot(attribute("disabled"));

        switch (ModuleName) {
            case ("Менеджер очередей"):
            case ("Dashboard"):
            case ("База данных"):
            case ("Монитор транзакций"):
            case ("Веб-сервисы"):
            case ("Расширенный Менеджер очередей"):
            case ("Мультименеджер очередей"):
                $$x(String.format(CommonComponents.moduleActiveStatusXpath, ModuleName)).find(visible).shouldHave(exactText("Деактивирован"));
                $$x(String.format(CommonComponents.moduleStateXpath, ModuleName)).find(visible).shouldHave(exactText("Остановлен"));
                break;
            case ("Брокер"):
            case ("REST"):
                break;
            default:
                throw new AssertionError("Проверка до ВКЛючения модуля пропущена");
        }

        click($$x(String.format(CommonComponents.moduleStartXpath, ModuleName)).find(visible));
        click(manualUpdatePage);

        $$x(String.format(CommonComponents.moduleTurnOffXpath, ModuleName)).find(visible).shouldNotHave(attribute("disabled"));
        $$x(String.format(CommonComponents.moduleStartXpath, ModuleName)).find(visible).shouldHave(attribute("disabled"));
        $$x(String.format(CommonComponents.moduleActiveStatusXpath, ModuleName)).find(visible).shouldHave(exactText("Активирован"));
        $$x(String.format(CommonComponents.moduleStateXpath, ModuleName)).find(visible).shouldHave(exactText("Запущен"));
        switch (ModuleName) {
            case ("Менеджер очередей"):
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "Брокер")).find(visible).shouldHave(exactText("Активирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "Брокер")).find(visible).shouldHave(exactText("Остановлен"));
//                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "REST")).find(visible).shouldHave(exactText("Активирован"));
//                $$x(String.format(CommonComponents.moduleStateXpath, "REST")).find(visible).shouldHave(exactText("Остановлен"));
                break;
            case ("Брокер"):
            case ("Dashboard"):
            case ("REST"):
            case ("База данных"):
            case ("Веб-сервисы"):
            case ("Расширенный Менеджер очередей"):
            case ("Мультименеджер очередей"):
                break;
            case ("Монитор транзакций"):
                $$x(String.format(CommonComponents.moduleActiveStatusXpath, "База данных")).find(visible).shouldHave(exactText("Активирован"));
                $$x(String.format(CommonComponents.moduleStateXpath, "База данных")).find(visible).shouldHave(exactText("Запущен"));
                break;
            default:
                throw new AssertionError("Проверка после ВКЛючения модуля пропущена");
        }
    }

    @Step("Recover from Points")
    public void recoverFromPoints(String Ver) {
        click(recoverPointsTab);
        doubleClick($x(".//td[text()='" + Ver + "']"));
        click(backToRestorePointButton);
    }

    @Step("Delete Recover Point")
    public void deleteRecoverPoint(String Ver) {
        click(recoverPointsTab);
        contextClick($x(".//td[text()='" + Ver + "']"));
        click(deleteRecoverPointInContextMenu);
        click(confirmationDeleteButton);
        $x(".//td[text()='" + Ver + "']").shouldNotBe(visible);
    }

    @Step("point Must Not Be Exist")
    public void pointMustNotBeExist(String Ver) {
        click(recoverPointsTab);
        $x(".//td[text()='" + Ver + "']").shouldNotBe(visible);
    }

    public void startDashboard() {
        click(dashboardOn);
    }

    public void loadLibrary(String NameLibrary) {
        settingsPage();
        click(libraryManagmentTab);
        loadLibraryButton.sendKeys(NameLibrary);
        successLoadedIcon.shouldBe(visible);
        sleep(500);
    }

    public void loadLibrary199(String NameLibrary) {
        settingsPage();
        click(libraryManagmentTab199);
        loadLibraryButton.sendKeys(NameLibrary);
        successLoadedIcon.shouldBe(visible);
        sleep(500);
    }

    public void checkLibraryParameters(String Type, String NameLibrary) {
        settingsPage();
        click(libraryManagmentTab);
        search(NameLibrary);
        switch (Type) {
            case ("Полностью"):
            case ("199"):
            case ("850"):
            case ("6.0"):
                cellNameLibraryInTable.shouldHave(exactText(NameLibrary));
                cellDateLoadedLibraryInTable.shouldNotBe(empty);
                break;
            case ("Конфигурация Фесб"):
                cellNameLibraryInTable.shouldNotBe(exist);
                break;
            default:
                throw new AssertionError("Пропущена проверка библиотек");
        }
    }

    public void deleteLibrary(String NameLibrary) {
        settingsPage();
        click(libraryManagmentTab);
        contextClick($x(String.format(nameLibraryInTable, NameLibrary)));
        click(deleteButtonInContextMenu);
        click(deleteButton);
    }

    public void addAppender(String appenderName, String appenderFileName, String appenderPattern, String appenderMaxSize,
                            String appenderSumArchives) {
        click(loggingTab);
        click(addAppenderOrLoggerOrInterceptionButton);
        val(appenderNameInput, appenderName);
        click(appenderRotationSelect);
        valAndSelectElement(appenderRotationInput, "По размеру");
        val(appenderFileNameInput, appenderFileName);
        val(appenderPatternInput, appenderPattern);
        val(appenderMaxSizeInput, appenderMaxSize);
        val(appenderSumArchivesInput, appenderSumArchives);
        click(saveButton);
    }

    public void cancelAddAppender(String appenderName, String appenderFileName, String appenderPattern, String appenderMaxSize,
                                  String appenderSumArchives) {
        click(loggingTab);
        click(addAppenderOrLoggerOrInterceptionButton);
        val(appenderNameInput, appenderName);
        click(appenderRotationSelect);
        valAndSelectElement(appenderRotationInput, "По размеру");
        val(appenderFileNameInput, appenderFileName);
        val(appenderPatternInput, appenderPattern);
        val(appenderMaxSizeInput, appenderMaxSize);
        val(appenderSumArchivesInput, appenderSumArchives);
        closeForm();
    }

    public void editAppender(String oldAppenderName, String newAppenderName, String newAppenderFileName, String newAppenderPattern,
                             String newAppenderMaxSize, String newAppenderSumArchives) {
        click(loggingTab);
        search(oldAppenderName);
        doubleClick(appenderNameInTable);
        val(appenderNameInput, newAppenderName);
        click(appenderRotationSelect);
        valAndSelectElement(appenderRotationInput, "По размеру");
        val(appenderFileNameInput, newAppenderFileName);
        val(appenderPatternInput, newAppenderPattern);
        val(appenderMaxSizeInput, newAppenderMaxSize);
        val(appenderSumArchivesInput, newAppenderSumArchives);
        click(saveButton);
    }

    public void cancelEditAppender(String oldAppenderName, String newAppenderName, String newAppenderFileName, String newAppenderPattern,
                                   String newAppenderMaxSize, String newAppenderSumArchives) {
        click(loggingTab);
        search(oldAppenderName);
        doubleClick(appenderNameInTable);
        val(appenderNameInput, newAppenderName);
        click(appenderRotationSelect);
        valAndSelectElement(appenderRotationInput, "По размеру");
        val(appenderFileNameInput, newAppenderFileName);
        val(appenderPatternInput, newAppenderPattern);
        val(appenderMaxSizeInput, newAppenderMaxSize);
        val(appenderSumArchivesInput, newAppenderSumArchives);
        closeForm();
    }

    public void deleteAppender(String appenderName) {
        click(loggingTab);
        click(appendersTab);
        search(appenderName);
        contextClick(appenderNameInTable);
        click(deleteButtonInContextMenu);
        click(deleteButton);
    }

    public void checkDeletedAppender(String appenderName) {
        click(loggingTab);
        click(appendersTab);
        search(appenderName);
        Assert.assertFalse(loggerNameInTable.exists());
    }

    public void cancelDeleteAppender(String appenderName) {
        click(loggingTab);
        click(appendersTab);
        search(appenderName);
        contextClick(appenderNameInTable);
        click(deleteButtonInContextMenu);
        closeForm();
    }

    public void appenderCheckAllParameters(String appenderName, String appenderFileName, String appenderPattern, String appenderMaxSize,
                                           String appenderSumArchives) {
        click(loggingTab);
        click(appendersTab);
        search(appenderName);
        appenderNameInTable.shouldHave(exactText(appenderName));
        appenderFileInTable.shouldHave(exactText(appenderFileName));
        appenderMaxSizeInTable.shouldHave(exactText(appenderMaxSize));
        appenderSumArchivesInTable.shouldHave(exactText(appenderSumArchives));
        appenderPatternInTable.shouldHave(exactText(appenderPattern));
    }

    public void addLogger(String loggerName, String level, String appender) {
        click(loggingTab);
        click(loggersTab);
        click(addAppenderOrLoggerOrInterceptionButton);
        click(loggerNameSelect);
        val(loggerNameInput, loggerName);
        click(loggerLevelSelect);
        valAndSelectElement(loggerLevelInput, level);
//        click($x(String.format(pointLevelInSelect, level)));
        click(appendersSelect);
        valAndSelectElement(appendersInput, appender);
//        click($x(String.format(pointAppendrInSelect, appender)));
        click(modalWindow);
        click(saveButton);
    }

    public void cancelAddLogger(String loggerName, String level, String appender) {
        click(loggingTab);
        click(loggersTab);
        click(addAppenderOrLoggerOrInterceptionButton);
        click(loggerNameSelect);
        val(loggerNameInput, loggerName);
        click(loggerLevelSelect);
        valAndSelectElement(loggerLevelInput, level);
//        click($x(String.format(pointLevelInSelect, level)));
        click(appendersSelect);
        valAndSelectElement(appendersInput, appender);
//        click($x(String.format(pointAppendrInSelect, appender)));
        closeForm();
    }

    public void editLogger(String oldLoggerName, String newLoggerName, String newLevel, String renameAppender, String newAppender, SendEvent presence) {
        click(loggingTab);
        click(loggersTab);
        search(oldLoggerName);
        doubleClick(loggerNameInTable);
        click(loggerNameSelect);
        val(loggerNameInput, newLoggerName);
        click(loggerLevelSelect);
        valAndSelectElement(loggerLevelInput, newLevel);
//        click($x(String.format(pointLevelInSelect, newLevel)));
        sleep(1000);
        sout(selectedAppenders.toString());
        sout("Проверяется: " + renameAppender + " - " + selectedAppenders.get(0).getText());
        Assert.assertEquals(renameAppender, selectedAppenders.get(0).getText());
        appendersClearSelect.hover();
        click(appendersClearSelect);
        click(appendersSelect);
        valAndSelectElement(appendersInput, newAppender);
        click(modalWindow);
        if (presence.equals(SendEvent.ENABLED) && !sendEventCheckBox.isSelected()) {
            click(sendEventCheckBox);
        }
        if (presence.equals(SendEvent.DISABLED) && sendEventCheckBox.isSelected()) {
            click(sendEventCheckBox);
        }
        click(saveButton);
    }

    public void cancelEditLogger(String oldLoggerName, String newLoggerName, String newLevel, String renameAppender, String newAppender, SendEvent presence) {
        click(loggingTab);
        click(loggersTab);
        search(oldLoggerName);
        doubleClick(loggerNameInTable);
        click(loggerNameSelect);
        val(loggerNameInput, newLoggerName);
        click(loggerLevelSelect);
        valAndSelectElement(loggerLevelInput, newLevel);
        sout("Проверяется: " + renameAppender + " - " + selectedAppenders.get(0).getText());
        Assert.assertEquals(renameAppender, selectedAppenders.get(0).getText());
        appendersClearSelect.hover();
        click(appendersClearSelect);
        click(appendersSelect);
        valAndSelectElement(appendersInput, newAppender);
        click(modalWindow);
        if (presence.equals(SendEvent.ENABLED) && !sendEventCheckBox.isSelected()) {
            click(sendEventCheckBox);
        }
        if (presence.equals(SendEvent.DISABLED) && sendEventCheckBox.isSelected()) {
            click(sendEventCheckBox);
        }
        closeForm();
    }

    public void deleteLogger(String loggerName) {
        click(loggingTab);
        click(loggersTab);
        search(loggerName);
        contextClick(loggerNameInTable);
        click(deleteButtonInContextMenu);
        click(deleteButton);
    }

    public void checkDeletedLogger(String loggerName) {
        click(loggingTab);
        click(loggersTab);
        search(loggerName);
        Assert.assertFalse(loggerNameInTable.exists());
    }

    public void cancelDeleteLogger(String loggerName) {
        click(loggingTab);
        click(loggersTab);
        search(loggerName);
        contextClick(loggerNameInTable);
        click(deleteButtonInContextMenu);
        closeForm();
    }

    public void loggerCheckAllParameters(String loggerName, String levelRus, String appender, String sendEvent) {
        click(loggingTab);
        click(loggersTab);
        search(loggerName);
        loggerNameInTable.shouldHave(exactText(loggerName));
        loggersAppenderInTable.shouldHave(exactText(appender));
        loggerLevelInTable.shouldHave(exactText(levelRus));
        loggerSendEventInTable.shouldHave(exactText(sendEvent));
    }

    public void addInterception(String className, String regularExpression, String change) {
        click(loggingTab);
        click(interceptionTab);
        click(addAppenderOrLoggerOrInterceptionButton);
        click(logsPage.classInInterceptionSettingSelect);
        valAndSelectElement(logsPage.classInInterceptionSettingInput, className);
//        click($x(String.format(logsPage.classInterception, className)));
//        val($x(String.format(logsPageTab.classInterception,className),));

//        val(logsPageTab.classInputInInterceptionSetting, className);
        val(logsPage.regularExpressionInputInInterceptionSetting, regularExpression);
        val(logsPage.changeInputInInterceptionSetting, change);
        click(logsPage.saveButtonInInterceptionSetting);
    }

    public void editInterception(String oldRegularExpression, String className, String newRegularExpression, String newClassName, String newChange, Debug debug) {
        click(loggingTab);
        click(interceptionTab);
        search(oldRegularExpression);
        doubleClick(interceptionRegularExpressionInTable);

        click(logsPage.classInInterceptionSettingSelect);
        valAndSelectElement(logsPage.classInInterceptionSettingInput, newClassName);
//        click($x(String.format(logsPage.classInterception, newClassName)));

        val(logsPage.regularExpressionInputInInterceptionSetting, newRegularExpression);
        val(logsPage.changeInputInInterceptionSetting, newChange);

        if (debug.equals(Debug.Yes) && !logsPage.debugCheckBoxInInterceptionSetting.isSelected()) {
            click(logsPage.debugCheckBoxInInterceptionSetting);
        }
        if (debug.equals(Debug.No) && logsPage.debugCheckBoxInInterceptionSetting.isSelected()) {
            click(logsPage.debugCheckBoxInInterceptionSetting);
        }
        click(logsPage.saveButtonInInterceptionSetting);
    }

    public void cancelEditInterception(String oldRegularExpression, String className, String newRegularExpression, String newClassName, String newChange, Debug debug) {
        click(loggingTab);
        click(interceptionTab);
        search(oldRegularExpression);
        doubleClick(interceptionRegularExpressionInTable);

        click(logsPage.classInInterceptionSettingSelect);
        valAndSelectElement(logsPage.classInInterceptionSettingInput, className);
//        click($x(String.format(logsPage.classInterception, newClassName)));

        val(logsPage.regularExpressionInputInInterceptionSetting, newRegularExpression);
        val(logsPage.changeInputInInterceptionSetting, newChange);

        if (debug.equals(Debug.Yes) && !logsPage.debugCheckBoxInInterceptionSetting.isSelected()) {
            click(logsPage.debugCheckBoxInInterceptionSetting);
        }
        if (debug.equals(Debug.No) && logsPage.debugCheckBoxInInterceptionSetting.isSelected()) {
            click(logsPage.debugCheckBoxInInterceptionSetting);
        }
        closeForm();
    }

    public void interceptionCheckAllParameters(String className, String regularExpression, String change, String debug) {
        click(loggingTab);
        click(interceptionTab);
        search(regularExpression);
        interceptionClassInTable.shouldHave(exactText(className));
        interceptionRegularExpressionInTable.shouldHave(exactText(regularExpression));
        interceptionChangeInTable.shouldHave(exactText(change));
        interceptionDebugInTable.shouldHave(exactText(debug));
    }

    public void deleteInterception(String regularExpression) {
        click(loggingTab);
        click(interceptionTab);
        search(regularExpression);
        contextClick(interceptionRegularExpressionInTable);
        click(deleteButtonInContextMenu);
        click(deleteButton);
    }

    public void canceldeleteInterception(String regularExpression) {
        click(loggingTab);
        click(interceptionTab);
        search(regularExpression);
        contextClick(interceptionRegularExpressionInTable);
        click(deleteButtonInContextMenu);
        closeForm();
    }

    public void checkDeletedInterception(String regularExpression) {
        click(loggingTab);
        click(interceptionTab);
        search(regularExpression);
        Assert.assertFalse(interceptionRegularExpressionInTable.exists());
    }
}
