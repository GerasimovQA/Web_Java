package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N6_SystemParallelTest extends Base {
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    SettingsPage settingsPage;
    SOPSPage sopsPage;
    Api api = new Api();
    CreationUserApi createUserAPI = new CreationUserApi();
    SOPSApi sopsApi = new SOPSApi();
    static Api staticApi = new Api();
    CreationSOPSPage creationSOPSPage;
    LogsPage logsPage = new LogsPage();
    CommonComponents commonComponents = new CommonComponents();

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void afterTest() {
//        api.getStatusAllModulesAPI();
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void createLogging() {
        String appenderName = "Тестовый аппендер";
        String logFileName = "testlog.log";
        String appenderFileName = "${FESB_DATA}/logs/" + logFileName;
        String appenderPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String appenderMaxSize = "100";
        String appenderSumArchives = "5";
        String loggerName = "Тестовый логгер";
        String loggerMessage = "Error with ${id}";
        String sendEvent = "Нет";
        String finalLoggerMessage = "Error with ID:";
        String level = "INFO";
        String finalLevel = "Информационный";
        String domain = "Тестируем логгер";
        String localQueueName = "Локальная очередь для тестирования логгера";
        String sopsName = "СОПС для тестирования логгера";
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainID = sopsApi.createDomainAPI(Cook, domain);
        sopsApi.startDomainAPI(Cook, DomainID);

        settingsPage = new SettingsPage();
        settingsPage.addAppender(appenderName, appenderFileName, appenderPattern, appenderMaxSize, appenderSumArchives);
        settingsPage.appenderCheckAllParameters(appenderName, appenderFileName, appenderPattern, appenderMaxSize, appenderSumArchives);

        settingsPage.addLogger(loggerName, finalLevel, appenderName);
        settingsPage.loggerCheckAllParameters(loggerName, finalLevel, appenderName, sendEvent);

        sopsPage = new SOPSPage();
        sopsPage.clickToAddSOPSButton(domain);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT,"Менеджер очередей","QM",  localQueueName, null, null);
        creationSOPSPage.addLoggerToConfigSops(loggerName, loggerMessage, finalLevel, loggerName);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);

        sopsPage.goToDomain(domain);
        sopsPage.sendNSavedMessages(sopsName, 1);
        sopsPage.sopsCheckAllParameters(sopsName, "Локальная очередь: " + localQueueName, 1);

        logsPage.setShowAllLogs(finalLevel);
        logsPage.logCheckAllParameters(logFileName, finalLevel, finalLoggerMessage);
    }

    @Test
    public void cancelCreateLogging() {
        String appenderName = "Тестовый аппендер для отмены создания логирования";
        String logFileName = "cancelCreateLoggingtestlog.log";
        String appenderFileName = "${FESB_DATA}/logs/" + logFileName;
        String appenderPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String appenderMaxSize = "100";
        String appenderSumArchives = "5";
        String loggerName = "Тестовый логгер для отмены создания логирования";
        String finalLevel = "Информационный";

        settingsPage = new SettingsPage();
        settingsPage.cancelAddAppender(appenderName, appenderFileName, appenderPattern, appenderMaxSize, appenderSumArchives);
        settingsPage.checkDeletedAppender(appenderName);

        settingsPage.cancelAddLogger(loggerName, finalLevel, "jetty-debug");
        settingsPage.checkDeletedLogger(loggerName);
    }

    @Test
    public void editLogging() {
        String nameDomain = "Домен для редактирования логгера";
        String nameSops = "СОПС для редактирования логгера";
        String nameLocalQueue1 = "Локальная очередь 1 для редактирования логгера";
        String nameLocalQueue2 = "Локальная очередь 2 для редактирования логгера";
        String finalLoggerMessage = "Errorrr with ID";
        String editfinalLoggerMessage = "Ошибка ссс ID:";

        String idAppender = commonComponents.createIndividualName("idAppender");
        String nameAppender = "Аппендер для редактирования логгера";
        String fileAppender = "editLogging.log";
        String pathToAppenderFile = "${FESB_DATA}/logs/" + fileAppender;
        String patternAppender = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String sizeAppender = "100";
        String maxAppender = "5";
        String totalFileSize = "";

        String editedNameAppender = "Новый аппендер для редактирования логгера";
        String editedFileAppender = "new-" + fileAppender;
        String pathToEditAppenderFile = "${FESB_DATA}/logs/" + editedFileAppender;
        String editedAppenderPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %X{domainName}%X{sopsName}%msg%n";
        String editedAppenderMaxSize = "333";
        String editedAppenderSumArchives = "444";

        String loggerID = commonComponents.createIndividualName("loggerID");
        String loggerMessage = "Errorrr with ${id}";
        String loggerName = "Логгер для редактирования логгера";
        String loggerLevel = "INFO";
        String loggerLevelRus = "Информационный";

        String editedLoggerName = "Новый Логгер для редактирования логгера";
        String editedLoggerLevelRus = "Отладка";
        String editedsendEven = "Нет";
        String editedLoggerSendEven = "Нет";

        String loggerMessage2 = "Ошибка ссс ${id}";
        String loggerLevelRus2 = "Отладка";

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createAppenderAPI(Cookies, nameAppender, Api.TypeAppender.SIZE, pathToAppenderFile, sizeAppender, maxAppender, patternAppender, totalFileSize);
        api.createLoggerAPI(Cookies, loggerID, nameAppender, loggerName, loggerLevel, false);

        String DomainID = sopsApi.createDomainAPI(Cookies, nameDomain);
        sopsApi.startDomainAPI(Cookies, DomainID);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainID, nameSops);
        sopsApi.createSopsWith2LocalQueueAndLoggerAPI(Cookies, DomainID, SopsID, nameSops, nameLocalQueue1, nameLocalQueue2,
                loggerName, loggerLevel, loggerMessage, loggerName);

        sopsPage = new SOPSPage();
        sopsPage.goToDomain(nameDomain);
        sopsPage.sendNSavedMessages(nameSops, 1);
        logsPage.setShowAllLogs(loggerLevelRus);
        logsPage.logCheckAllParameters(fileAppender, loggerLevelRus, finalLoggerMessage);

        settingsPage = new SettingsPage();
        settingsPage.appenderCheckAllParameters(nameAppender, pathToAppenderFile, patternAppender, sizeAppender, maxAppender);
        settingsPage.editAppender(nameAppender, editedNameAppender, pathToEditAppenderFile, editedAppenderPattern, editedAppenderMaxSize, editedAppenderSumArchives);
        settingsPage.appenderCheckAllParameters(editedNameAppender, pathToEditAppenderFile, editedAppenderPattern, editedAppenderMaxSize, editedAppenderSumArchives);

        settingsPage.loggerCheckAllParameters(loggerName, loggerLevelRus, nameAppender, editedsendEven);
        settingsPage.editLogger(loggerName, editedLoggerName, editedLoggerLevelRus, nameAppender, editedNameAppender, SettingsPage.SendEvent.DISABLED);
        settingsPage.loggerCheckAllParameters(editedLoggerName, editedLoggerLevelRus, editedNameAppender, editedLoggerSendEven);

        sopsPage = new SOPSPage();
        sopsPage.goToSops(nameDomain, nameSops);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.editLoggerToConfigSops(loggerMessage, loggerLevelRus, loggerName, loggerMessage2, editedLoggerLevelRus, editedLoggerName);
        creationSOPSPage.inputSOPSNameAndSave(nameSops);
        sopsPage.goToDomain(nameDomain);
        sopsPage.sendNSavedMessages(nameSops, 1);
        sopsPage.sopsCheckAllParameters(nameSops, "Локальная очередь: " + nameLocalQueue1, 1);

        logsPage.setShowAllLogs(loggerLevelRus);
        Selenide.refresh();
        logsPage.specifiedStringLogNotFound(editfinalLoggerMessage);
        Selenide.refresh();
        logsPage.setShowAllLogs(loggerLevelRus2);
        logsPage.specifiedStringLogCheckAllParameters(1, editedFileAppender, loggerLevelRus2, editfinalLoggerMessage);
    }

    @Test
    public void cancelEditLogging() {
        String idAppender = commonComponents.createIndividualName("idAppender");
        String nameAppender = "Аппендер для отмены редактирования логгера";
        String patternAppender = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String sizeAppender = "100";
        String maxAppender = "5";
        String fileAppender = "cancelEditLogging";
        String pathToAppenderFile = "${FESB_DATA}/logs/" + fileAppender;
        String totalFileSize = "";

        String editedNameAppender = "Новый аппендер для отмены редактирования логгера";
        String editedlogFileName = "нью-кириллица-а-а-а.txt";
        String editedAppenderFileName = "${FESB_DATA}/logs/new" + editedlogFileName;
        String editedAppenderPattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %X{domainName}%X{sopsName}%msg%n";
        String editedAppenderMaxSize = "3333";
        String editedAppenderSumArchives = "4444";

        String nameAppender2 = "Аппендер для редактирования отмены редактирования логгера 2";

        String loggerID = commonComponents.createIndividualName("loggerID");
        String logFileName = "кириллица.txt";
        String loggerFile = "${FESB_DATA}/logs/" + logFileName;
        String loggerName = "Логгер для отмены редактирования логгинга";
        String loggerLevel = "INFO";
        String loggerLevelRus = "Информационный";

        String editedLoggerName = "Новый Логгер для отмены редактирования логгера";
        String editedLoggerLevelRus = "Ошибки";
        String editedsendEven = "Нет";


        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createAppenderAPI(Cookies, nameAppender, Api.TypeAppender.SIZE, pathToAppenderFile, sizeAppender, maxAppender, patternAppender, totalFileSize);
        api.createLoggerAPI(Cookies, loggerID, nameAppender, loggerName, loggerLevel, false);

        settingsPage = new SettingsPage();
        settingsPage.appenderCheckAllParameters(nameAppender, pathToAppenderFile, patternAppender, sizeAppender, maxAppender);
        settingsPage.cancelEditAppender(nameAppender, editedNameAppender, editedAppenderFileName, editedAppenderPattern, editedAppenderMaxSize, editedAppenderSumArchives);
        settingsPage.appenderCheckAllParameters(nameAppender, pathToAppenderFile, patternAppender, sizeAppender, maxAppender);

        settingsPage.loggerCheckAllParameters(loggerName, loggerLevelRus, nameAppender, editedsendEven);
        settingsPage.cancelEditLogger(loggerName, editedLoggerName, editedLoggerLevelRus, nameAppender, "CONSOLE", SettingsPage.SendEvent.ENABLED);
        settingsPage.loggerCheckAllParameters(loggerName, loggerLevelRus, nameAppender, editedsendEven);
    }

    @Test
    public void deleteLogging() {
        String nameDomain = "Домен для удаления логгера";
        String nameSops = "СОПС для удаления логгера";
        String nameLocalQueue1 = "Локальная очередь 1 для удаления логгера";
        String nameLocalQueue2 = "Локальная очередь 2 для удаления логгера";

        String idAppender = commonComponents.createIndividualName("idAppender");
        String nameAppender = "Аппендер для удаления логгера";
        String patternAppender = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String sizeAppender = "100";
        String maxAppender = "5";
        String fileAppender = "deleteLogging";
        String pathToAppenderFile = "${FESB_DATA}/logs/" + fileAppender;
        String totalFileSize = "";

        String loggerID = commonComponents.createIndividualName("loggerID");
        String loggerMessage = "Error with ${id}";
        String logFileName = "кириллицаДляУдаленияЛоггера.txt";
        String loggerFile = "${FESB_DATA}/logs/" + logFileName;
        String loggerName = "Логгер для удаления логгера";
        String loggerLevel = "INFO";


        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createAppenderAPI(Cookies, nameAppender, Api.TypeAppender.SIZE, pathToAppenderFile, sizeAppender, maxAppender, patternAppender, totalFileSize);
        api.createLoggerAPI(Cookies, loggerID, nameAppender, loggerName, loggerLevel, false);

        String DomainID = sopsApi.createDomainAPI(Cookies, nameDomain);
        sopsApi.startDomainAPI(Cookies, DomainID);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainID, nameSops);
        sopsApi.createSopsWith2LocalQueueAndLoggerAPI(Cookies, DomainID, SopsID, nameSops, nameLocalQueue1, nameLocalQueue2,
                loggerName, loggerLevel, loggerMessage, loggerName);
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(nameDomain);
        sopsPage.indicatorOfStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-success"));
        sleep(5000);
        sopsPage.goToSops(nameDomain, nameSops);

        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.deleteLoggerToConfigSops(loggerName);
        creationSOPSPage.inputSOPSNameAndSave(nameSops);
        sopsPage.goToSops(nameDomain, nameSops);
        creationSOPSPage.checkDeleteLoggerToConfigSops(loggerName);
        creationSOPSPage.inputSOPSNameAndSave(nameSops);

        settingsPage = new SettingsPage();
        settingsPage.deleteLogger(loggerName);
        settingsPage.checkDeletedLogger(loggerName);

        settingsPage.deleteAppender(nameAppender);
        settingsPage.checkDeletedAppender(nameAppender);
    }

    @Test
    public void cancelDeleteLogging() {
        String nameDomain = "Домен для отмены удаления логгера";
        String nameSops = "СОПС для удаления отмены логгера";
        String nameLocalQueue1 = "Локальная очередь 1 для отмены удаления логгера";
        String nameLocalQueue2 = "Локальная очередь 2 для отмены удаления логгера";

        String idAppender = commonComponents.createIndividualName("idAppender");
        String nameAppender = "Аппендер для отмены удаления логгера";
        String patternAppender = "%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level [%-20.20thread] %-50.50logger{36} - %msg%n";
        String sizeAppender = "100";
        String maxAppender = "5";
        String fileAppender = "cancelDeleteLogging";
        String pathToAppenderFile = "${FESB_DATA}/logs/" + fileAppender;
        String totalFileSize = "";

        String loggerID = commonComponents.createIndividualName("loggerID");
        String loggerMessage = "Error with ${id}";
        String logFileName = "кириллицаДляОтменыУдаленияЛоггера.txt";
        String loggerFile = "${FESB_DATA}/logs/" + logFileName;
        String loggerName = "Логгер для отмены удаления логгера";
        String loggerLevel = "INFO";
        String loggerLevelRus = "Информационный";
        String editedsendEven = "Нет";


        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createAppenderAPI(Cookies, nameAppender, Api.TypeAppender.SIZE, pathToAppenderFile, sizeAppender, maxAppender, patternAppender, totalFileSize);
        api.createLoggerAPI(Cookies, loggerID, nameAppender, loggerName, loggerLevel, false);

        String DomainID = sopsApi.createDomainAPI(Cookies, nameDomain);
        sopsApi.startDomainAPI(Cookies, DomainID);
        String SopsID = sopsApi.generateSopsIdAPI(Cookies, DomainID, nameSops);
        sopsApi.createSopsWith2LocalQueueAndLoggerAPI(Cookies, DomainID, SopsID, nameSops, nameLocalQueue1, nameLocalQueue2,
                loggerName, loggerLevel, loggerMessage, loggerName);
        sleep(2000);
        sopsPage = new SOPSPage();
        sopsPage.goToSops(nameDomain, nameSops);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.cancelDeleteLoggerToConfigSops(loggerName);
        creationSOPSPage.inputSOPSNameAndSave(nameSops);
        sopsPage.goToSops(nameDomain, nameSops);
        creationSOPSPage.checkCancelDeleteLoggerToConfigSops(loggerName);
        creationSOPSPage.inputSOPSNameAndSave(nameSops);

        settingsPage = new SettingsPage();
        settingsPage.cancelDeleteLogger(loggerName);
        settingsPage.loggerCheckAllParameters(loggerName, loggerLevelRus, nameAppender, editedsendEven);

        settingsPage.cancelDeleteAppender(nameAppender);
        settingsPage.appenderCheckAllParameters(nameAppender, pathToAppenderFile, patternAppender, sizeAppender, maxAppender);
    }

    @Test
    public void createInterception() {
        String className = "*";
        String regularExpression = "^Logout: root$";
        String change = "Пользователь покинул нас";
        String record = "Logout: root";
        String level = "Информационный";
        String debug = "Нет";

        basePage.logout();
        basePage.openPage(baseUrl(), "/manager/", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(record);
        logsPage.notFindRecordsInLog(change);
        settingsPage = new SettingsPage();
        settingsPage.addInterception(className, regularExpression, change);
        settingsPage.interceptionCheckAllParameters(className, regularExpression, change, debug);

        basePage.logout();
        basePage.openPage(baseUrl(), "/manager/", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(change);
    }

    @Test
    public void createInterceptionWithEmptyCnangeInput() {
        String roleApi = "OPERATOR";
        String login = "EmptyCnangeInput";
        String password = "EmptyCnangeInput";

        String className = "ru.factorts.fesb.security.SecurityAudit";
        String regularExpression = "^Login: EmptyCnangeInput$";
        String change = "";
        String record = "Login: EmptyCnangeInput";
        String level = "Информационный";
        String debug = "Нет";

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, roleApi, login, password);

        settingsPage = new SettingsPage();
        settingsPage.addInterception(className, regularExpression, change);
        settingsPage.interceptionCheckAllParameters(className, regularExpression, change, debug);

        basePage.logout();
        loginPage.loginButton.waitUntil(visible, 600000);
        loginPage.loginClickButton(login, password);
        logsPage.setShowAllLogs(level);
        logsPage.notFindRecordsInLog(record);
    }

    @Test
    public void cancelEditInterception() {
        String idInterception = commonComponents.createIndividualName("idInterception");
        String oldClassName = "*";
        String newClassName = "org.h2";
        String oldRegularExpression = "^111111$";
        String newRegularExpression = "^222222$";
        String oldChange = "3333333333";
        String newChange = "4444444444";
        String level = "Информационный";
        String debug = "Нет";

        basePage.logout();
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createInterceptionAPI(Cookies, idInterception, oldClassName, oldRegularExpression, oldChange);

        loginPage.loginButton.waitUntil(visible, 600000);
        loginPage.loginClickButton("admin", "admin");
        settingsPage = new SettingsPage();
        settingsPage.interceptionCheckAllParameters(oldClassName, oldRegularExpression, oldChange, debug);
        settingsPage.cancelEditInterception(oldRegularExpression, oldClassName, newRegularExpression, newClassName, newChange, SettingsPage.Debug.No);
        settingsPage.interceptionCheckAllParameters(oldClassName, oldRegularExpression, oldChange, debug);

    }

    @Test
    public void deleteInterception() {
        String idInterception = commonComponents.createIndividualName("idInterception");
        String className = commonComponents.createIndividualName("class");
        String regularExpression = commonComponents.createIndividualName("regularExpression");
        String change = commonComponents.createIndividualName("change");
        String debug = "Нет";

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createInterceptionAPI(Cookies, idInterception, className, regularExpression, change);

        settingsPage = new SettingsPage();
        settingsPage.interceptionCheckAllParameters(className, regularExpression, change, debug);
        settingsPage.deleteInterception(regularExpression);
        settingsPage.checkDeletedInterception(regularExpression);
    }

    @Test
    public void cancelDeleteInterception() {
        String idInterception = commonComponents.createIndividualName("idInterception");
        String className = commonComponents.createIndividualName("class");
        String regularExpression = commonComponents.createIndividualName("regularExpression");
        String change = commonComponents.createIndividualName("change");
        String debug = "Нет";

        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createInterceptionAPI(Cookies, idInterception, className, regularExpression, change);

        settingsPage = new SettingsPage();
        settingsPage.interceptionCheckAllParameters(className, regularExpression, change, debug);
        settingsPage.canceldeleteInterception(regularExpression);
        settingsPage.interceptionCheckAllParameters(className, regularExpression, change, debug);
    }

    @Test
    public void interceptionSettingInLogPage() {
        String record = "Login: root";
        String changedRecord = "Измененная запись";
        String level = "Информационный";
        String className = "*";
        String regularExpression = "^Login: root$";
        String debug = "Нет";

        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(record);
        logsPage.notFindRecordsInLog(changedRecord);
        logsPage.interceptionSetting(record, changedRecord, LogsPage.Debug.NO);
        basePage.logout();
        basePage.openPage(baseUrl());

        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(changedRecord);
        settingsPage = new SettingsPage();
        settingsPage.interceptionCheckAllParameters(className, regularExpression, changedRecord, debug);
    }

    @Test
    public void cancelInterceptionSettingInLogPage() {
        String record = "JMX is enabled";
        String changedRecord = "Отмена измененния записи";
        String level = "Информационный";
        String regularExpression = "^" + record + "$";

        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(record);
        logsPage.notFindRecordsInLog(changedRecord);
        logsPage.cancelInterceptionSetting(record, changedRecord, LogsPage.Debug.NO);

        settingsPage = new SettingsPage();
        settingsPage.checkDeletedInterception(regularExpression);
    }

    @Test
    public void documentation() {
        basePage.click(basePage.documentationTab);
        basePage.changeWindow();
        basePage.click(basePage.domainsOfBrokerTab);
        Assert.assertTrue("Ошибка в URL", url().contains("/manager/#/doc/Domains_page"));
        $x(".//h2[text()='Синтаксис']").scrollIntoView(false);
        sleep(1000);
        $x(".//a[text()='Транзакции']").shouldHave(attribute("class", "current"));
    }
}
