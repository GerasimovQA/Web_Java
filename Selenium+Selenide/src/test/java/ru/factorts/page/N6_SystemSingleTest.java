package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N6_SystemSingleTest extends Base {

    private SettingsPage settingsPage;
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    Api api = new Api();
    static Api staticApi = new Api();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    SOPSApi sopsApi = new SOPSApi();
    SOPSPage sopsPage;
    CreationUserApi createUserAPI = new CreationUserApi();
    ConfigurationServer configurationServer = new ConfigurationServer();
    ConfigurationPage configurationPage = new ConfigurationPage();
    CommonComponents commonComponents = new CommonComponents();
    UpdatePage updatePage = new UpdatePage();
    CreationSOPSPage creationSOPSPage;
    LogsPage logsPage = new LogsPage();
    Update update = new Update();

    String loginReadonly = commonComponents.createIndividualName("LoginJmxReadonly");
    String passwordReadonly = commonComponents.createIndividualName("PasswordJmxReadonly");
    String loginReadwrite = commonComponents.createIndividualName("LoginJmxReadwrite");
    String passwordReadwrite = commonComponents.createIndividualName("PasswordJmxReadwrite");

    private String oldVer = update.oldVer;
    private String lastVer = update.lastVer;

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
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());

        if (testName.getMethodName().equals("aUpdate")) {
            String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
            api.switchModuleAPI(Cook1, baseUrl(), "factor-dashboard", "deactivate");
            api.switchModuleAPI(Cook1, baseUrl(), "factor-store", "deactivate");
            api.switchModuleAPI(Cook1, baseUrl(), "factor-webservices", "deactivate");
//            api.switchModuleAPI(Cook1, baseUrl(), "factor-qms", "deactivate");
            api.switchModuleAPI(Cook1, baseUrl(), "factor-qme", "deactivate");
        }

        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    static public void afterClass() {
        String cook = staticApi.loginAPI("http://192.168.57.240:3334", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook, "http://192.168.57.240:3334", "true|true", "true|true", "false|false", "false|false", "true|true", "true|true", "false|false", "false|false");

        recordFesbLogs(getClassName());
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    //    Тест должен выполняться первым, поэтому вначале a
    @Test
    public void ayMemmoryLeakWithoutWork() {
        String memmory1 = null;
        String memmory2 = null;
        float intMemmory1;
        float intMemmory2;
        basePage.click(basePage.resourceMonitorTab);
        sleep(5000);
        configurationServer.usedMemoryInput.shouldNotHave(Condition.exactText("0 Б"));
        memmory1 = configurationServer.usedMemoryInput.getText().split(" ")[0];
        System.out.println(configurationServer.usedMemoryInput.getText().split(" ")[0] + " - " + configurationServer.usedMemoryInput.getText().split(" ")[1]);
        if (configurationServer.usedMemoryInput.getText().split(" ")[1].equals("Гб")) {
            intMemmory1 = Float.parseFloat(memmory1) * 1000;
        } else {
            intMemmory1 = Float.parseFloat(memmory1);
        }
        sleep(60000);
        memmory2 = configurationServer.usedMemoryInput.getText().split(" ")[0];
        if (configurationServer.usedMemoryInput.getText().split(" ")[1].equals("Гб")) {
            intMemmory2 = Float.parseFloat(memmory2) * 1000;
        } else {
            intMemmory2 = Float.parseFloat(memmory2);
        }
        float differenceMemmory = intMemmory2 - intMemmory1;

        System.out.println(intMemmory2 + " - " + intMemmory1 + " = " + differenceMemmory);
        Assert.assertTrue("Утекает оператива без нагрузки", differenceMemmory < 300);
    }


    //    Тест должен выполняться первым, поэтому вначале a
    @Description("update app")
    @Test
    public void aUpdate() {
        update.update(oldVer);
//
//
//        if (verShouldBe.equals(null)) {
//            verShouldBe = oldVer;
//        }
//        basePage.sout("oldVer:" + oldVer);
//        basePage.sout("lastVer:" + lastVer);
//        basePage.compareStringAndString(verShouldBe, oldVer);
//        //Проверяем текущую версию
//        IndexPage indexPage = new IndexPage();
//        indexPage.checkVersionFESB(oldVer);
//
//        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        System.out.println(getClassName());
//        String Type = null;
//        if (oldVer.equals("5.0.199")) {
//            Type = "199";
//        } else if (oldVer.contains("5.1.850")) {
//            Type = "850";
//        } else if (oldVer.contains("6.0")) {
//            Type = "6.0";
//        } else {
//            Type = "Полностью";
//        }
//
//        String LocalQueueFoExportConf1 = "Лок.ОчередьUppate1";
//        String LocalQueueFoExportConf2 = "Лок.ОчередьUppate2";
//        String SopsForExportConf = " СОПС Update";
//        String LibraryName = "any1.jar";
//        String Message = "update";
//        String maxMemoryWrapper = "6666";
//        String restPath = "/RestPathFullUpdate";
//        String screenDashboardName = "screenDashboardNameUpdate";
//        String bdName = "bdNameFullUpdate";
//        String bdPort = "7777";
//        int numMessage = 3;
//        int jvmHeapUsage = 51;
//        String hardConfigArtemis = "33";
//        String ramconfigArtemis = "44";
//        String keyConstantOfBroker = "keyConstantOfBroker1";
//        String valueConstantOfBroker = "valueConstantOfBroker1";
//        String trackedMessageCacheSize = "666";
//        String domainName = commonComponents.createIndividualName(testName.getMethodName());
//        String domainEmptyName = commonComponents.createIndividualName("Пустой домен");
//        String DomainIDForExportImportTest = sopsApi.createDomainAPI(Cook1, domainName);
//        String domainEmptyID = sopsApi.createDomainAPI(Cook1, domainEmptyName);
//        String loginReadonly = commonComponents.createIndividualName("LoginJmxReadonly");
//        String passwordReadonly = commonComponents.createIndividualName("PasswordJmxReadonly");
//        String loginLocalUser = commonComponents.createIndividualName("loginLocalUser");
//        String passwordLocalUser = commonComponents.createIndividualName("passwordLocalUser");
//        String createDashboardScreen = "[{\"name\": \"examples\", \"label\": \"Примеры\"}, {\"name\": \"" + screenDashboardName + "\", \"label\": \"" + screenDashboardName + "\"}]";
//        String directoryName = "xsd";
//        String fileName = "schemaXSD.xsd";
//        String wsdlName = "TestSvc.wsdl";
//        String modeWorkOfCluster = null;
//        String pathToStorageOfQueue = null;
//        String activeNodeSwitchingDelay = null;
//        Boolean continueTryingToReconnect = false;
//        Boolean createAndHoldOpenSecondConnection = false;
//        String delayBeforeFirstSwitching = null;
//        ArrayList<String> schemeList = null;
//        ArrayList<String> adressesMQList = null;
//        ArrayList<String> portList = null;
//        if (oldVer.equals("5.0.199")) {
//            try {
//                api.switchModuleAPI(Cook1, baseUrl(), "factor-rest", "activate");
//            } catch (ComparisonFailure e) {
//                System.out.println("Различные ответы в различных версиях, не страшно");
//            }
//        }
//        try {
//            api.switchModuleAPI(Cook1, baseUrl(), "factor-dashboard", "activate");
//        } catch (ComparisonFailure e) {
//            System.out.println("Различные ответы в различных версиях, не страшно");
//        }
//        try {
//            api.switchModuleAPI(Cook1, baseUrl(), "factor-store", "activate");
//        } catch (ComparisonFailure e) {
//            System.out.println("Различные ответы в различных версиях, не страшно");
//        }
//
//        configurationPage = new ConfigurationPage();
//        configurationPage.preparationToExportConfiguration(Cook1, baseUrl(), maxMemoryWrapper, DomainIDForExportImportTest, SopsForExportConf, domainName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, jvmHeapUsage, restPath, createDashboardScreen, bdName, bdPort, Message, numMessage, LibraryName, loginLocalUser, passwordLocalUser, loginReadonly, passwordReadonly, keyConstantOfBroker, valueConstantOfBroker, modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, trackedMessageCacheSize, directoryName, fileName, "forAddCertificate.crt", wsdlName);
//
//        //Обновляемся и проверяем версию
//        settingsPage = new SettingsPage();
//        updatePage.update(oldVer, lastVer, UpdatePage.RecoveryPoint.Yes);
//        loginPage.loginInput.waitUntil(Condition.visible, 300000);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        indexPage = new IndexPage();
//        indexPage.checkVersionFESB(lastVer);
//        sopsPage = new SOPSPage();
//
//        //Откатываемся, проверяем версию и удаляем точку восстановления
//        settingsPage = new SettingsPage();
//        settingsPage.recoverFromPoints(oldVer);
//        loginPage.loginInput.waitUntil(Condition.visible, 300000);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        indexPage = new IndexPage();
//        indexPage.checkVersionFESB(oldVer);
//
////        if (!oldVer.equals("5.0.199") && !oldVer.contains("5.1.850")) {
////            configurationPage.checkImportedConfiguration(Type, maxMemoryWrapper, domainName, SopsForExportConf, Integer.toString(jvmHeapUsage), restPath.replace("/", ""), screenDashboardName, bdName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, keyConstantOfBroker, valueConstantOfBroker, LibraryName, Message, loginLocalUser, loginReadonly, trackedMessageCacheSize, directoryName, fileName, "esb.factor-ts.ru");
////        }
//
//        settingsPage = new SettingsPage();
//        settingsPage.deleteRecoverPoint(oldVer);
//
//        //Повторно обновляемся и проверяем версию
//        settingsPage = new SettingsPage();
//        updatePage.update(oldVer, lastVer, UpdatePage.RecoveryPoint.No);
//        loginPage.loginInput.waitUntil(Condition.visible, 300000);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        indexPage = new IndexPage();
//        indexPage.checkVersionFESB(lastVer);
//        sopsPage = new SOPSPage();
//
//        configurationPage.checkImportedConfiguration(Type, maxMemoryWrapper, domainName, domainEmptyID, domainEmptyName, SopsForExportConf, Integer.toString(jvmHeapUsage), restPath.replace("/", ""), screenDashboardName, bdName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, keyConstantOfBroker, valueConstantOfBroker, LibraryName, Message, loginLocalUser, loginReadonly, trackedMessageCacheSize, directoryName, fileName, "esb.factor-ts.ru");
//
//        settingsPage = new SettingsPage();
//        settingsPage.pointMustNotBeExist(oldVer);
    }

    @Test
    @Parameters(value = {
            "Брокер",
            "Расширенный Менеджер очередей",
            "Dashboard",
            "Мультименеджер очередей",
            "REST",
            "База данных",
            "Монитор транзакций",
            "Веб-сервисы",
    })
    public void onAndOffModules(String Module) {
        settingsPage = new SettingsPage();
        switch (Module) {
            case ("Менеджер очередей"):
            case ("Брокер"):
            case ("Мультименеджер очередей"):
                settingsPage.turnOffModule(Module);
                settingsPage.turnOnModule(Module);
                break;
            case ("REST"):
                settingsPage.turnOnModule(Module);
                settingsPage.turnOffModule(Module);
                settingsPage.turnOnModule(Module);
                break;
            case ("Dashboard"):
            case ("База данных"):
            case ("Монитор транзакций"):
            case ("Веб-сервисы"):
            case ("Расширенный Менеджер очередей"):
                settingsPage.turnOnModule(Module);
                settingsPage.turnOffModule(Module);
                break;
            default:
                throw new AssertionError("Тест на включение/выключение модуля пропущен");
        }
    }

    @Test
    public void editJmx() throws Exception {
        String readonly = "readonly";
        String readwrite = "readwrite";
        String host = "192.168.57.240";
        String port = "5098";
        String portHttps = "9494";
        String newURLHttps = "https://" + host + ":" + portHttps;
        int maxTime = 120000;

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        createUserAPI.createJmxUserApi(Cook, loginReadonly, passwordReadonly, readonly);
        createUserAPI.createJmxUserApi(Cook, loginReadwrite, passwordReadwrite, readwrite);

        settingsPage = new SettingsPage();
        configurationServer.switchAutorizationJmx(ConfigurationServer.Enable.Yes);
        configurationServer.editJmx(host, port);
        sleep(5000);
        basePage.restartFesb();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        sleep(5000);
        String cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        basePage.openPage(baseUrl());
        sopsPage = new SOPSPage();
        sopsPage.startDomainInGroup("Архив", "Примеры");

//        sopsApi.startDomainAPI(cookies, "examples");
//        sopsApi.checkStatusOfDomainAPI(cookies, "examples", "\"SYNCED\"");
        sopsApi.startSopsAPI(cookies, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

        try {
            configurationServer.jmxRead(host, Integer.parseInt(port), loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            configurationServer.jmxWrite(host, Integer.parseInt(port), loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Fail);
        } catch (SecurityException e) {
            e.printStackTrace();
            basePage.sout("Пользователь с правами readonly не может записывать и это хорошо");
        }

        try {
            configurationServer.jmxRead(host, Integer.parseInt(port), loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            configurationServer.jmxWrite(host, Integer.parseInt(port), loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    Тест должен выполняться последним, поэтому вначале z
    @Test
    public void zEditHttpAndHttps() {
        String ipHttps = "192.168.57.240";
        String portHttps = "3333";
        String newURLHttps = "https://" + ipHttps + ":" + portHttps;
        String ipHttp = "192.168.57.240";
        String portHttp = "3334";
        String newURLHttp = "http://" + ipHttp + ":" + portHttp;
        int maxTime = 120000;

        settingsPage = new SettingsPage();
        configurationServer.editHttps(ipHttps, portHttps);
        configurationServer.editHttp(ipHttp, portHttp);
        basePage.restartFesb();
        try {
            basePage.waitAvailibleUrl(newURLHttp, maxTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sleep(5000);
        open(newURLHttps);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        Selenide.clearBrowserCookies();
        open(newURLHttp);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        try {
            basePage.chekDisableUrl(baseUrl(), 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void checkSopsKafkaAndKafka() {
//        String domainName1 = commonComponents.createIndividualName("Домен для проверки KafkaAndKafka-");
//        String sopsName1 = "CОПС для проверки KafkaAndKafka-1";
//        String sopsName2 = "CОПС для проверки KafkaAndKafka-2";
//        String localQueueForSopsName1 = "Локальная очередь для проверки KafkaAndKafka-1";
//        String localQueueForSopsName2 = "Локальная очередь для проверки KafkaAndKafka-2";
//
//        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + localQueueForSopsName1,};
//        String[] point1_2 = new String[]{"Выход|Kafka|192.168.194.69:9092", "инпут-в-параметрах|Тема|test", "инпут-в-параметрах|Брокеры|192.168.194.69:9092"};
//        String[][] pointAll_1 = {point1_1, point1_2};
//
//        String[] point2_1 = new String[]{"Вход|Kafka|192.168.194.69:9092", "инпут-в-параметрах|Тема|test", "инпут-в-параметрах|Брокеры|192.168.194.69:9092"};
//        String[] point2_2 = new String[]{"Выход|Установить заголовки", "параметры-заголовков|JMSExpiration|Simple|По умолчанию|0", "параметры-заголовков|JMSPriority|Simple|По умолчанию|1"};
//        String[] point2_3 = new String[]{"Выход|Локальная очередь|" + localQueueForSopsName2};
//        String[][] pointAll_2 = {point2_1, point2_2, point2_3};
//
//        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
//        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
//        refresh();
//
//        creationSOPSPage = new CreationSOPSPage("empty");
//        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
//        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
//        sleep(1000);
//        queueManagerApi.sentMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "Test KafkaAndKafka", 1, false, "4");
//        sleep(3000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//    }

    @Test
    public void editInterception() {
        String idInterception = commonComponents.createIndividualName("idInterception");
        String oldClassName = "*";
        String newClassName = "ru.factorts.fesb.security.SecurityAudit";
        String oldRegularExpression = "^Login: admin$";
        String newRegularExpression = "^Logout: admin$";
        String oldChange = "Админ залогинился";
        String newChange = "Админ отлогинился";
        String level = "Информационный";
        String debug = "Нет";

        basePage.logout();
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.createInterceptionAPI(Cookies, idInterception, oldClassName, oldRegularExpression, oldChange);

        loginPage.loginButton.waitUntil(visible, 600000);
        loginPage.loginClickButton("admin", "admin");
        settingsPage = new SettingsPage();
        settingsPage.interceptionCheckAllParameters(oldClassName, oldRegularExpression, oldChange, debug);
        settingsPage.editInterception(oldRegularExpression, oldClassName, newRegularExpression, newClassName, newChange, SettingsPage.Debug.No);
        settingsPage.interceptionCheckAllParameters(newClassName, newRegularExpression, newChange, debug);

        basePage.logout();
        basePage.openPage(baseUrl());
        logsPage.setShowAllLogs(level);
        logsPage.findRecordsInLog(newChange);
    }
}