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
import org.openqa.selenium.remote.SessionId;
import utils.ConfigProperties;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N4_ConfigurationSingleTest extends Base {

    @Rule
    public final TestName testName = new TestName();

    SmokeApi workProtocolPage = new SmokeApi();
    QueueManagerPage queueManagerPage = new QueueManagerPage("empty");
    SOPSPage sopsPage;
    SettingsPage settingsPage;
    BasePage basePage = new BasePage();
    UserPage userPage = new UserPage();
    DataBasePage dataBasePage = new DataBasePage();
    MessagePage messagePage = new MessagePage();
    IndexPage indexPage;
    ConfigurationPage configurationPage;
    CommonComponents commonComponents = new CommonComponents();
    ConfigurationServer configurationServer = new ConfigurationServer();
    ResourceMonitor resourceMonitor = new ResourceMonitor();
    Api api = new Api();
    static Api staticApi = new Api();
    RestApi restApi = new RestApi();
    CreationUserApi createUserAPI = new CreationUserApi();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    QueueManagerArtemisApi queueManagerArtemisApi = new QueueManagerArtemisApi();
    SOPSApi sopsApi = new SOPSApi();
    static SOPSApi staticSopsApi = new SOPSApi();
    DashboardApi dashboardApi = new DashboardApi();
    WebServcecApi webServcecApi = new WebServcecApi();
    CreationUserPage creationUserPage = new CreationUserPage();
    UserApi userApi = new UserApi();
    ClusterPage clusterPage = new ClusterPage();
    static BasePage staticBasePage = new BasePage();
    static ConfigurationServer staticConfigurationServer = new ConfigurationServer();
    static CommonComponents staticCommonComponents = new CommonComponents();
    static LoginPage staticLoginPage = new LoginPage();
    static UserPage staticUserPage = new UserPage();
    String host = baseUrl().split("//")[1].split(":")[0];
    int port = 5099;
    String domainName = commonComponents.createIndividualName("domainName-");
    String loginReadonly = commonComponents.createIndividualName("LoginJmxReadonly");
    String passwordReadonly = commonComponents.createIndividualName("PasswordJmxReadonly");
    String loginLocalUser = commonComponents.createIndividualName("loginLocalUser");
    String passwordLocalUser = commonComponents.createIndividualName("passwordLocalUser");
    String loginReadwrite = commonComponents.createIndividualName("LoginJmxReadwrite");
    String passwordReadwrite = commonComponents.createIndividualName("PasswordJmxReadwrite");
    String readonly = "readonly";
    String readwrite = "readwrite";
    String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

    @BeforeClass
    public static void beforeClass() {
        String Cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticSopsApi.startDomainAPI(Cook, "examples");


//        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));



        staticBasePage.deleteFile("/home/fesb/share/exportFull.zip");
        staticBasePage.deleteFile("/home/fesb/share/exportConfFesb.zip");
        staticApi.startAllModules(baseUrl());
        Configuration.browserCapabilities.setCapability("name", getClassName());
        Base.basePage.openPage(baseUrl());
        staticConfigurationServer.switchAutorizationJmx(ConfigurationServer.Enable.Yes);
        if (staticCommonComponents.reloadButton.isDisplayed()) {
            sleep(3000);
            staticBasePage.restartFesb();
            staticLoginPage.loginInput.waitUntil(Condition.visible, 300000);
        } else {
            staticBasePage.settingsPage();
            staticUserPage = new UserPage();
            staticUserPage.usersAndGroupPage();
            staticBasePage.click(staticUserPage.jmxUsersTab);
            sleep(3000);
            staticBasePage.click(staticCommonComponents.reloadButton);
            staticLoginPage.loginInput.waitUntil(Condition.visible, 300000);
        }
        Selenide.closeWebDriver();
    }

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
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "true|true", "true|true",
                "true|true", "true|true", "true|true", "true|true",
                "true|true", "true|true");

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-4-1");
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-4-2");
        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

//    /**
//     * Import and check domain and sops existing
//     */
//    @Description("Import configuration and check domain and sops existing")
//    @Test
//    public void impExpTest11ImportDomain() {
//        configurationPage = new ConfigurationPage();
//        sleep(1000);
//        configurationPage.importConfiguration(new File(System.getProperty("user.dir") +
//                File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator +
//                "resources" + File.separator + "configuration.zip"));
//        basePage = new BasePage();
//        basePage.domainsOfBrokerTab();
//        sopsPage = new SOPSPage();
//        sleep(3000);
//        $x(String.format(specificDomainButtonXpath, MY_IMP_EXP_DOMAIN)).click();
//        sleep(3000);
//        refresh();
//        sopsPage.search(MY_IMP_EXP_SOPS);
//        sopsPage.existNSOPSRowsAfterSearch(1);
//    }
//
//    /**
//     * Check existing queue and specific message
//     */
//    @Description("Check existing queue and specific message")
//    @Test
//    public void impExpTest12ageExisting() {
//        basePage = new BasePage();
//        basePage.queueListPage();
//        queueManagerTab = new QueueManagerPage();
//        queueManagerTab.queueShouldHaveNMessagesInDepth(MY_IMP_EXP_OUT, 1);
//        queueManagerTab.queueShouldHaveSpecificMessage(MY_IMP_EXP_OUT, "my unique test message");
//    }
//
//
//    /**
//     * Get path to FESB directory
//     */
//    @Description("Get path to FESB directory")
//    @Test
//    public void impExpTest13getToPathFESBDirectory() {
//        basePage = new BasePage();
//        basePage.generalInformationTab();
//        generalInformationTab = new IndexPage();
//        pathToFESBDirectory = generalInformationTab.getPathFESB();
//    }
//
//    /**
//     * Check existing lib in files
//     */
//    @Description("Check existing lib in files")
//    @Test
//    public void impExpTest14CheckLibExisting() {
//        String pathToFile = pathToFESBDirectory + File.separator + "lib" + File.separator + "integrationPaymentDocument-1.0-SNAPSHOT.jar";
//        CommonComponents.checkFileExisting(new File(pathToFile));
//    }
//
//
//    /**
//     * Check existing log in files
//     */
//    @Description("Check existing log in files")
//    @Test
//    public void impExpTest15CheckLogFileExisting() {
//        String pathToFile = pathToFESBDirectory + File.separator + "data" + File.separator + "logs"+ File.separator + "factor-core.2017-10-13.0.log";
//        CommonComponents.checkFileExisting(new File(pathToFile));
//    }
//
//    /**
//     * Check existing specific message in logs
//     */
//    @Description("Check existing specific message in logs")
//    @Test
//    public void impExpTest16ageInLogs() {
//        basePage = new BasePage();
//        basePage.logsPageTab();
//        logsPageTab = new LogsPage();
//
//        logsPageTab.setLogsSettings("Все","Ошибки", true);
//        logsPageTab.search("my unique test message");
//        logsPageTab.existNLogsRowsAfterSearch(1);
//        logsPageTab.clearSearchInput();
//        logsPageTab.setLogsSettings("Все", "Информационный", false);
//        logsPageTab.afterSearchTableRow.waitUntil(visible, 120000);
//        logsPageTab.search("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//        logsPageTab.existNLogsRowsAfterSearch(50);
//    }
//
//    /**
//     * Check imported SOPS working capacity
//     */
//    @Description("Check imported SOPS working capacity")
//    @Test
//    public void impExpTest17CheckSOPSWorking() {
//        String message = "Check imported SOPS working capacity";
//        basePage = new BasePage();
//        basePage.queueListPage();
//        queueManagerTab = new QueueManagerPage();
//        String messagesInDepth = queueManagerTab.queueGetMessagesInDepth(MY_IMP_EXP_OUT);
//        int numberOfMessagesInDepth = Integer.parseInt(messagesInDepth);
//        queueManagerTab.sendMessageTab();
//        sendMessageTab = new MessagePage();
//        sendMessageTab.sendMessageInQueueNTimes(MY_IMP_EXP_IN, message, 1);
//        sendMessageTab.queueListPage();
//        queueManagerTab.queueShouldHaveNMessagesInDepth(MY_IMP_EXP_OUT,numberOfMessagesInDepth+1);
//        queueManagerTab.logsPageTab();
//        logsPageTab = new LogsPage();
//        logsPageTab.setLogsSettings("Все", "Ошибки", true);
//        logsPageTab.search(message);
//        logsPageTab.existNLogsRowsAfterSearch(1);
//    }
//

    /**
     * Export and check configuration with all data
     */
    @Description("Export and check configuration with all data")
    @Test
    @Parameters(value = {
            "Полностью | Локальная очередь для импорта конфигурации 1_1 | Локальная очередь для импорта конфигурации 1_2 | 44 | 55 |  СОПС для импорта конфигурации | any1.jar | 1-ТеStИк!@#$ | exportFull | 5678 | RestPathFull | screenDashboardNameFull | bdNameFull | 6666 | 3 | 51 | keyConstantOfBroker1 | valueConstantOfBroker1 | 666 | xsd | schemaXSD.xsd | certificateForImportFullDomain.crt | TestSvc.wsdl",
//            "Конф + Дополнительно загруженные библиотеки | Локальная очередь для импорта конфигурации 2_1 | Локальная очередь для импорта конфигурации 2_2 | Домен для импорта конфигурации 2 | СОПС для импорта конфигурации | any2.jar | 2-ТеStИк!@#$ | exportLib.zip",
//            "Конф + Протокол работы | Локальная очередь для импорта конфигурации 3_1 | Локальная очередь для импорта конфигурации 3_2 | Домен для импорта конфигурации 3 | СОПС для импорта конфигурации | any3.jar | 3-ТеStИк!@#$ | exportLog.zip",
//            "Конф + Данные | Локальная очередь для импорта конфигурации 4_1 | Локальная очередь для импорта конфигурации 4_2 | Домен для импорта конфигурации 4 | СОПС для импорта конфигурации | any4.jar | 4-ТеStИк!@#$ | exportData.zip",
            "Конфигурация Фесб | Локальная очередь для импорта конфигурации 1_1 | Локальная очередь для импорта конфигурации 1_2 | 66 | 77 | СОПС для импорта конфигурации | any2.jar | 1-ТеStИк!@#$ | exportConfFesb | 6789 | RestPathConfFesb | screenDashboardNameConfFesb | bdNameConfFesb | 7777 | 5 | 61 | keyConstantOfBroker2 | valueConstantOfBroker2 | 777 | xsd | schemaXSD.xsd | certificateForImportOnlyConfigDomain.crt | TestSvc.wsdl",
    })
    public void impExpTest18ExportImportConf(String Type, String LocalQueueFoExportConf1, String LocalQueueFoExportConf2, String hardConfigArtemis, String ramconfigArtemis, String SopsForExportConf, String LibraryName, String Message, String exportFileName, String maxMemoryWrapper, String restPath, String screenDashboardName, String bdName, String bdPort, int numMessage, int jvmHeapUsage, String keyConstantOfBroker, String valueConstantOfBroker, String trackedMessageCacheSize, String directoryNameXSD, String fileName, String certificate, String wsdlName) {
        String defaultDashboardScreen = "[{\"name\": \"examples\", \"label\": \"Примеры\"}]";
        String createDashboardScreen = "[{\"name\": \"examples\", \"label\": \"Примеры\"}, {\"name\": \"" + screenDashboardName + "\", \"label\": \"" + screenDashboardName + "\"}]";

        String modeWorkOfCluster = null;
        String pathToStorageOfQueue = null;
        String activeNodeSwitchingDelay = null;
        Boolean continueTryingToReconnect = false;
        Boolean createAndHoldOpenSecondConnection = false;
        String delayBeforeFirstSwitching = null;
        ArrayList<String> schemeList = null;
        ArrayList<String> adressesMQList = null;
        ArrayList<String> portList = null;

        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        sopsPage = new SOPSPage("Empty");
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String DomainIDForExportImportTest = sopsApi.createDomainAPI(Cook1, domainName);
        String domainEmptyName = commonComponents.createIndividualName("Пустой домен");
        String domainEmptyID = sopsApi.createDomainAPI(Cook1, domainEmptyName);

        configurationPage = new ConfigurationPage();
        configurationPage.preparationToExportConfiguration(Cook1, baseUrl(), maxMemoryWrapper, DomainIDForExportImportTest, SopsForExportConf, domainName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, jvmHeapUsage, restPath, createDashboardScreen, bdName, bdPort, Message, numMessage, LibraryName, loginLocalUser, passwordLocalUser, loginReadonly, passwordReadonly, keyConstantOfBroker, valueConstantOfBroker, modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, trackedMessageCacheSize, directoryNameXSD, fileName, certificate, wsdlName);

        basePage = new BasePage();
        basePage.settingsPage();
        configurationPage = new ConfigurationPage();
        configurationPage.exportConfiguration(Type, exportFileName);

        basePage.openPage(baseUrl_2());
        try {
            configurationPage.forImportConfiguration(baseUrl_2(), sessionId.toString(), exportFileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Импорт конфигурации не удался");
        }
        configurationPage.checkImportedConfiguration(Type, maxMemoryWrapper, domainName, domainEmptyID, domainEmptyName, SopsForExportConf, Integer.toString(jvmHeapUsage), restPath, screenDashboardName, bdName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, keyConstantOfBroker, valueConstantOfBroker, LibraryName, Message, loginLocalUser, loginReadonly, trackedMessageCacheSize, directoryNameXSD, fileName, certificate);

        //Возвращаем к исходному состоянию url1
        basePage.openPage(baseUrl(), "", "root", "root");
        dataBasePage.deleteDB(bdName);
        settingsPage = new SettingsPage();
        settingsPage.deleteLibrary(LibraryName);
//        clusterPage.createSettingCluster(modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, "131072");
        configurationPage.deleteCertificate("esb.factor-ts.ru");
        api.changeSettingWrapperAPI(Cook1, baseUrl(), "4096", "300", "15", "300", "5");
        sopsApi.deleteDomainAPI(Cook1, baseUrl(), DomainIDForExportImportTest);
        queueManagerMultyApi.editConfigMainSettingsMqAPI(Cook1, baseUrl(), "QM", true, false, false);

//        queueManagerApi.changeSettingQueueManagerAPI(Cook1, baseUrl(), false, true, false, 50, "100 gb", "50 gb");
        queueManagerArtemisApi.changeSettingConfigurationArtemisAPI(Cook1, baseUrl(), "100", "");
        restApi.changeSettingRestAPI(Cook1, baseUrl(), "rest", "REST", "/", "0.0.0.0", 9090, "swagger", "1.0", "REST API", false, "users", "ENABLE_CORS");
        dashboardApi.createScreenAPI(Cook1, baseUrl(), defaultDashboardScreen);


        String webServiceId = webServcecApi.getListWebServiceAPI(Cook1, baseUrl(), "name");
        webServcecApi.deleteWebServiceAPI(Cook1, baseUrl(), webServiceId);


        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), "QM", LocalQueueFoExportConf1, Message, numMessage, true, "1");
        queueManagerMultyApi.deleteQueueAPI(Cook1, baseUrl(), "QM", LocalQueueFoExportConf1);
        queueManagerMultyApi.deleteQueueAPI(Cook1, baseUrl(), "QM", LocalQueueFoExportConf2);
        sopsApi.deleteAllConstantsOfBrokerAPI(Cook1, baseUrl());
        sopsApi.deleteFileAPI(Cook1, baseUrl(), directoryNameXSD, fileName);
        sopsApi.deleteDirectoryAPI(Cook1, baseUrl(), directoryNameXSD);
        userApi.deleteLocalUserAPI(Cook1, baseUrl(), loginLocalUser);
        userApi.deleteJmxUserAPI(Cook1, baseUrl(), loginReadonly);

        //Возвращаем к исходному состоянию url2

        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        if (Type.equals("Полностью")) {
            basePage.openPage(baseUrl_2(), "", "root", "root");
            dataBasePage.deleteDB(bdName);
            settingsPage = new SettingsPage();
            settingsPage.deleteLibrary(LibraryName);
//            clusterPage.createSettingCluster(modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, "131072");
            configurationPage.deleteCertificate("esb.factor-ts.ru");
            sopsApi.deleteDomainAPI(Cook2, baseUrl_2(), DomainIDForExportImportTest);
            queueManagerMultyApi.editConfigMainSettingsMqAPI(Cook1, baseUrl(), "QM", true, false, false);
//            queueManagerApi.changeSettingQueueManagerAPI(Cook2, baseUrl_2(), false, true, false, 50, "100 gb", "50 gb");
            queueManagerArtemisApi.changeSettingConfigurationArtemisAPI(Cook2, baseUrl_2(), "100", "");
            restApi.changeSettingRestAPI(Cook2, baseUrl_2(), "rest", "REST", "/", "0.0.0.0", 9090, "swagger", "1.0", "REST API", false, "users", "ENABLE_CORS");
            dashboardApi.createScreenAPI(Cook2, baseUrl_2(), defaultDashboardScreen);


            webServiceId = webServcecApi.getListWebServiceAPI(Cook2, baseUrl_2(), "name");
            webServcecApi.deleteWebServiceAPI(Cook2, baseUrl_2(), webServiceId);


            queueManagerMultyApi.editConfigMainSettingsMqAPI(Cook2, baseUrl_2(), "QM", true, false, false);

            queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), "QM", LocalQueueFoExportConf1, Message, numMessage, true, "1");
            queueManagerMultyApi.deleteQueueAPI(Cook2, baseUrl_2(), "QM", LocalQueueFoExportConf1);
            queueManagerMultyApi.deleteQueueAPI(Cook2, baseUrl_2(), "QM", LocalQueueFoExportConf2);
            sopsApi.deleteAllConstantsOfBrokerAPI(Cook2, baseUrl_2());
            sopsApi.deleteFileAPI(Cook2, baseUrl_2(), directoryNameXSD, fileName);
            sopsApi.deleteDirectoryAPI(Cook2, baseUrl_2(), directoryNameXSD);
            userApi.deleteLocalUserAPI(Cook2, baseUrl_2(), loginLocalUser);
            userApi.deleteJmxUserAPI(Cook2, baseUrl_2(), loginReadonly);
        }

        api.switchModuleAPI(Cook2, baseUrl_2(), "factor-dashboard", "deactivate");
        api.switchModuleAPI(Cook2, baseUrl_2(), "factor-store", "deactivate");
        api.switchModuleAPI(Cook2, baseUrl_2(), "factor-transaction-monitor", "deactivate");
        api.switchModuleAPI(Cook2, baseUrl_2(), "factor-webservices", "deactivate");
        api.changeSettingWrapperAPI(Cook2, baseUrl_2(), "4096", "300", "15", "300", "5");
    }

    //    Тест должен выполняться последним, поэтому вначале z
    @Test
    @Parameters(value = {"20", "-1",})
    public void zTimeLifeSession(String time) {
        settingsPage = new SettingsPage();
        configurationServer.setTimeLifeSession(time);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        open("/manager/#/settings/modules");
        configurationServer.checkLifeSession(time);
    }

    @Test
    @Parameters(value = {"privateKey.jks | 111111 | 222222",
//            "privateKey.p12 | 111111 | 222222",
//            "privateKey.pks | 111111 | 222222",
    })
    public void uploadValidCertificateAndValidPasswords(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        configurationServer.uploadCertificate(certificate, passwordStorageKeys, passwordManagerKeys);
        commonComponents.reloadConfiguration();
        basePage.openPage(baseUrl());
        configurationServer.checkCertificateName(certificate);
    }

    @Test
    @Parameters(value = {"privateKey.jks | abra | cadabra",
//            "privateKey.p12 | abra | cadabra",
//            "privateKey.pks | abra | cadabra",
    })
    public void uploadValidCertificateAndNotValidPasswords(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        configurationServer.uploadCertificate(certificate, passwordStorageKeys, passwordManagerKeys);
        configurationServer.checkCertificateWithNotValidPasswordsNotUploaded(certificate);
    }


    @Test
    @Parameters(value = {"notValideCertificate.jpg | abra | cadabra",})
    public void uploadNotValidCertificateAndNotValidPasswords(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        configurationServer.uploadCertificate(certificate, passwordStorageKeys, passwordManagerKeys);
        configurationServer.checkNonValideCertificateWithNotValidPasswordsNotUploaded(certificate);
    }

    @Test
    @Parameters(value = {"notValideCertificate.jpg | abra | cadabra",})
    public void cancelUploadCertificate(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        configurationServer.cancelUploadCertificate(certificate, passwordStorageKeys, passwordManagerKeys);
        configurationServer.checkNonUploadCertificate(certificate);
    }

    @Test
    public void shutdownAllProtocols() {
        settingsPage = new SettingsPage();
        configurationServer.enableAdressHttps(ConfigurationServer.Enable.No);
        configurationServer.enableAdressHttp(ConfigurationServer.Enable.Yes);
        Assert.assertNotEquals("1.HTTPS выделен", configurationServer.enableHttpsCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked");
        Assert.assertEquals("1.HTTP НЕвыделен", configurationServer.enableHttpCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked ant-checkbox-disabled");


        configurationServer.enableAdressHttps(ConfigurationServer.Enable.Yes);
        Assert.assertEquals("2.HTTPS НЕвыделен", configurationServer.enableHttpsCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked");
        Assert.assertEquals("2.HTTP НЕвыделен", configurationServer.enableHttpCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked");

        configurationServer.enableAdressHttp(ConfigurationServer.Enable.No);
//        configurationServer.enableAdressHttps(ConfigurationServer.Enable.No);
        Assert.assertEquals("3.HTTPS НЕвыделен", configurationServer.enableHttpsCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked ant-checkbox-disabled");
        Assert.assertNotEquals("3.HTTP НЕвыделен", configurationServer.enableHttpCheckBox.getAttribute("class"), "ant-checkbox ant-checkbox-checked");
    }

    @Test
    public void editWrapper() {
        String bashCommandRestartContainer = "sudo docker restart fesb-test-4-1";
        String defaulSizeMemmoryInConfig = "4096";
        String newSizeMemmoryInConfig = "8000";
        String defaulSizeMemmoryInResourceMonitor = "3.55 Гб";
        String newSizeMemmoryInResourceMonitor = "6.94 Гб";

        resourceMonitor.checkMaxSizeMemoryInResourceMonitor(defaulSizeMemmoryInResourceMonitor);
        configurationServer.checkSizeMemmoryWrapperInConfig(defaulSizeMemmoryInConfig);
        configurationServer.editWrapper(newSizeMemmoryInConfig);
        basePage.restartFesb();
//        configurationServer.restartWrapperInDocker(bashCommandRestartContainer);
        try {
            basePage.chekDisableUrl(baseUrl(), 20000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            basePage.waitAvailibleUrl(baseUrl(), 120000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName());
        basePage.openPage(baseUrl());
        resourceMonitor.checkMaxSizeMemoryInResourceMonitor(newSizeMemmoryInResourceMonitor);
        configurationServer.checkSizeMemmoryWrapperInConfig(newSizeMemmoryInConfig);
    }

    @Test
    @Parameters(value = {"Валидные данные | NameForTest_showDataServerValid | http://192.168.57.240:9942/manager | root | root", "Невалидные данные | NameForTest_showDataServerNotValid | http://192.168.57.240:9942/manager | abrakadabre | abrakadabre",})
    public void showDataServer(String type, String ServerName, String hostOfServer, String login, String password) {
        String defaultAdress = "fesb-test-4-1";
//        String defaultAdress = "test01";
        String defaultServer = "LOCALHOST";
        String newAdress = "fesb-test-4-2";
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        Selenide.clearBrowserLocalStorage();
        api.addRemoteServerAPI(Cook, ServerName, hostOfServer, login, password);
        indexPage = new IndexPage();
        indexPage.checkIpAdressInListAdresses(defaultAdress);
        configurationServer.valueCurrentServerNotEquals(ServerName);
        configurationServer.editCurrentServer(ServerName);

        switch (type) {
            case ("Валидные данные"):
                configurationServer.valueCurrentServerEquals(ServerName);
                indexPage = new IndexPage();
                indexPage.checkIpAdressInListAdresses(newAdress);
                configurationServer.editCurrentServer(defaultServer);
                sleep(2000);
                break;
            case ("Невалидные данные"):
                refresh();
                configurationServer.valueCurrentServerEquals(defaultServer);
                indexPage = new IndexPage();
                indexPage.checkIpAdressInListAdresses(defaultAdress);
                break;
            default:
                throw new AssertionError("Пропущен тест с отображением данных сервера");
        }
    }

    @Test
    public void accessToJmxWithoutAutorization() {
        configurationServer.switchAutorizationJmx(ConfigurationServer.Enable.No);
        try {
            configurationServer.jmxWrite(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createJmxUser() throws Exception {
        String readonly = "readonly";
        String readwrite = "readwrite";
        creationUserPage.successCreateJmxUser(loginReadonly, passwordReadonly, readonly);
        creationUserPage.successCreateJmxUser(loginReadwrite, passwordReadwrite, readwrite);
        basePage.restartFesb();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readonly не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Fail);
        } catch (SecurityException e) {
            e.printStackTrace();
            basePage.sout("Пользователь с правами readonly не может записывать и это хорошо");
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может записывать и это плохо");
        }
    }

    @Test
    public void cancelCreateJmxUser() throws Exception {
        String readonly = "readonly";
        String readwrite = "readwrite";
        creationUserPage.cancelCreateJmxUser(loginReadonly, passwordReadonly, readonly);
        creationUserPage.cancelCreateJmxUser(loginReadwrite, passwordReadwrite, readwrite);

        basePage.restartFesbthroughManageOfSystem();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsApi.startDomainAPI(Cook, "examples");

        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }
    }

    @Test
    public void checkPermissionNonexistJmxUserReadonly() throws Exception {
        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }
    }

    @Test
    public void checkPermissionNonexistJmxUserReadwrite() throws Exception {
        try {
            configurationServer.jmxWrite(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }
    }

    @Test
    public void editJmxUser() throws Exception {


        String passwordReadonlyNew = "123";
        String passwordReadWriteNew = "456";

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createJmxUserApi(Cook, loginReadonly, passwordReadonly, readonly);
        createUserAPI.createJmxUserApi(Cook, loginReadwrite, passwordReadwrite, readwrite);
        creationUserPage.successEditRoleJmxUser(loginReadonly, readwrite);
        creationUserPage.successEditRoleJmxUser(loginReadwrite, readonly);
        creationUserPage.checkRoleJmxUsers(loginReadonly, readwrite);
        creationUserPage.checkRoleJmxUsers(loginReadwrite, readonly);
        creationUserPage.successEditPasswordJmxUser(loginReadonly, passwordReadonlyNew);
        creationUserPage.successEditPasswordJmxUser(loginReadwrite, passwordReadWriteNew);

        basePage.restartFesb();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);

        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsApi.startDomainAPI(Cook, "examples");
//        sopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");

        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonlyNew, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadonly, passwordReadonlyNew, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может записывать и это плохо");
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadWriteNew, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readonly не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadwrite, passwordReadWriteNew, ConfigurationServer.ReqwestResult.Fail);
        } catch (SecurityException e) {
            e.printStackTrace();
            basePage.sout("Пользователь с правами readonly не может записывать и это хорошо");
        }
    }

    @Test
    public void cancelEditJmxUser() throws Exception {
        String readonly = "readonly";
        String readwrite = "readwrite";

        String passwordReadonlyNew = "123";
        String passwordReadWriteNew = "456";

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        createUserAPI.createJmxUserApi(Cook, loginReadonly, passwordReadonly, readonly);
        createUserAPI.createJmxUserApi(Cook, loginReadwrite, passwordReadwrite, readwrite);
        creationUserPage.cancelEditRoleJmxUser(loginReadonly, readwrite);
        creationUserPage.cancelEditRoleJmxUser(loginReadwrite, readonly);
        creationUserPage.checkRoleJmxUsers(loginReadonly, readonly);
        creationUserPage.checkRoleJmxUsers(loginReadwrite, readwrite);
        creationUserPage.cancelEditPasswordJmxUser(loginReadonly, passwordReadonlyNew);
        creationUserPage.cancelEditPasswordJmxUser(loginReadwrite, passwordReadWriteNew);

        basePage.restartFesb();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsApi.startDomainAPI(Cook, "examples");
//        sopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");

        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

//Убеждаемся, что пользователь не может пройти авторизацию по новым данным
        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonlyNew, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadWriteNew, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }

//Убеждаемся, что доступ неотредактированного юзера соответствует установленным ролям
        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readonly не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Fail);
        } catch (SecurityException e) {
            e.printStackTrace();
            basePage.sout("Пользователь с правами readonly не может записывать и это хорошо");
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readwrite не может записывать и это плохо");
        }
    }

    @Test
    public void deleteJmxUser() throws Exception {
        String readonly = "readonly";
        String readwrite = "readwrite";

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createJmxUserApi(Cook, loginReadonly, passwordReadonly, readonly);
        createUserAPI.createJmxUserApi(Cook, loginReadwrite, passwordReadwrite, readwrite);
        creationUserPage.successDeleteJmxUser(loginReadonly);
        creationUserPage.checkDeletedJmxUsers(loginReadonly);
        creationUserPage.successDeleteJmxUser(loginReadwrite);
        creationUserPage.checkDeletedJmxUsers(loginReadwrite);

        basePage.restartFesbthroughManageOfSystem();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);

        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");


        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }

        try {
            configurationServer.jmxRead(host, port, loginReadwrite, passwordReadwrite, ConfigurationServer.ReqwestResult.Pass);
        } catch (SecurityException e) {
            basePage.sout("Авторизация JMX не пройдена");
            e.printStackTrace();
        }
    }

    @Test
    public void cancelDeleteJmxUser() throws Exception {
        String readonly = "readonly";

        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createJmxUserApi(Cook, loginReadonly, passwordReadonly, readonly);
        creationUserPage.cancelDeleteJmxUser(loginReadonly);
//        basePage.restartFesb();
        basePage.restartFesbthroughManageOfSystem();
        loginPage.loginInput.waitUntil(Condition.visible, 300000);


        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.startDomainAPI(Cook, "examples");
        staticSopsApi.checkStatusOfDomainAPI(Cook, "examples", "\"SYNCED\"");
        staticSopsApi.startSopsAPI(Cook, baseUrl(), "examples", "39ba21dc-1a02-449f-902a-4859292d1fb3");

        try {
            configurationServer.jmxRead(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Pass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Пользователь с правами readonly не может читать и это плохо");
        }
        try {
            configurationServer.jmxWrite(host, port, loginReadonly, passwordReadonly, ConfigurationServer.ReqwestResult.Fail);
        } catch (SecurityException e) {
            e.printStackTrace();
            basePage.sout("Пользователь с правами readonly не может записывать и это хорошо");
        }
    }

    @Test
    public void createJmxUserWithNonUniqueLogin() {
        String readonly = "readonly";
        String login1 = "jmxUserTestNotUniqLogin";
        String login2 = "jmxUserTestNotUniqLogin";


        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createJmxUserApi(Cook, login1, passwordReadonly, readonly);
        creationUserPage.successCreateJmxUser(login2, passwordReadonly, readonly);
        creationUserPage.saveButton.shouldBe(Condition.visible);
    }

//    @Test
//    public void addViewDownloadDeleteCertificate() {
//        String domain = testName.getMethodName();
//        String localQueueForSopsName1 = "addCertificate - 1";
//        String localQueueForSopsName2 = "addCertificate - 2";
//        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
//        String downloadFilePath = "/home/fesb/Stand/share/download/_.google.com.pem";
//        String certificateGoogle = "-----BEGIN CERTIFICATE-----\n" +
//                "MIIKPDCCCSSgAwIBAgIRAM3K/mJAVEWNCAAAAABi1rIwDQYJKoZIhvcNAQELBQAw\n" +
//                "QjELMAkGA1UEBhMCVVMxHjAcBgNVBAoTFUdvb2dsZSBUcnVzdCBTZXJ2aWNlczET\n" +
//                "MBEGA1UEAxMKR1RTIENBIDFPMTAeFw0yMDExMDMwNzIyMDBaFw0yMTAxMjYwNzIy\n" +
//                "MDBaMGYxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpDYWxpZm9ybmlhMRYwFAYDVQQH\n" +
//                "Ew1Nb3VudGFpbiBWaWV3MRMwEQYDVQQKEwpHb29nbGUgTExDMRUwEwYDVQQDDAwq\n" +
//                "Lmdvb2dsZS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCvHt19\n" +
//                "rsQ37eRBT8BFp8mtDBD5X9tzDlv6vjW3b1ViiQDjfnwOHv7Jl1up4bILQKI82u1m\n" +
//                "wtzVvYjO0LVs+uy/Elu0JkpxFE6oI1FYqMFFEQ9lu9X+H18lDqXm3jj8Tc041jxP\n" +
//                "IMM7tSYAch0YBgAGF4DPqWCClj5ViLZxvujwf8q/br11W1uZe2Cgte133k3RtmEV\n" +
//                "OLqIUWmndeACpIwIXMESNAGGoqw8N8gWjo16fjyRaiZ5qnBJunhzapyd8q77N+vs\n" +
//                "z4dmQbJRu8kHAjwFHvThy9TNsWe6GlwxnSVhwrla68kL5mfUAjGUugJ/MH6Zbmqo\n" +
//                "7bnNfTdSrfNayjJfAgMBAAGjggcHMIIHAzAOBgNVHQ8BAf8EBAMCBaAwEwYDVR0l\n" +
//                "BAwwCgYIKwYBBQUHAwEwDAYDVR0TAQH/BAIwADAdBgNVHQ4EFgQU/zMLNTNph0JU\n" +
//                "OkdLPDDRtmnpHzswHwYDVR0jBBgwFoAUmNH4bhDrz5vsYJ8YkBug630J/SswaAYI\n" +
//                "KwYBBQUHAQEEXDBaMCsGCCsGAQUFBzABhh9odHRwOi8vb2NzcC5wa2kuZ29vZy9n\n" +
//                "dHMxbzFjb3JlMCsGCCsGAQUFBzAChh9odHRwOi8vcGtpLmdvb2cvZ3NyMi9HVFMx\n" +
//                "TzEuY3J0MIIEwgYDVR0RBIIEuTCCBLWCDCouZ29vZ2xlLmNvbYINKi5hbmRyb2lk\n" +
//                "LmNvbYIWKi5hcHBlbmdpbmUuZ29vZ2xlLmNvbYIJKi5iZG4uZGV2ghIqLmNsb3Vk\n" +
//                "Lmdvb2dsZS5jb22CGCouY3Jvd2Rzb3VyY2UuZ29vZ2xlLmNvbYIYKi5kYXRhY29t\n" +
//                "cHV0ZS5nb29nbGUuY29tggYqLmcuY2+CDiouZ2NwLmd2dDIuY29tghEqLmdjcGNk\n" +
//                "bi5ndnQxLmNvbYIKKi5nZ3BodC5jboIOKi5na2VjbmFwcHMuY26CFiouZ29vZ2xl\n" +
//                "LWFuYWx5dGljcy5jb22CCyouZ29vZ2xlLmNhggsqLmdvb2dsZS5jbIIOKi5nb29n\n" +
//                "bGUuY28uaW6CDiouZ29vZ2xlLmNvLmpwgg4qLmdvb2dsZS5jby51a4IPKi5nb29n\n" +
//                "bGUuY29tLmFygg8qLmdvb2dsZS5jb20uYXWCDyouZ29vZ2xlLmNvbS5icoIPKi5n\n" +
//                "b29nbGUuY29tLmNvgg8qLmdvb2dsZS5jb20ubXiCDyouZ29vZ2xlLmNvbS50coIP\n" +
//                "Ki5nb29nbGUuY29tLnZuggsqLmdvb2dsZS5kZYILKi5nb29nbGUuZXOCCyouZ29v\n" +
//                "Z2xlLmZyggsqLmdvb2dsZS5odYILKi5nb29nbGUuaXSCCyouZ29vZ2xlLm5sggsq\n" +
//                "Lmdvb2dsZS5wbIILKi5nb29nbGUucHSCEiouZ29vZ2xlYWRhcGlzLmNvbYIPKi5n\n" +
//                "b29nbGVhcGlzLmNughEqLmdvb2dsZWNuYXBwcy5jboIUKi5nb29nbGVjb21tZXJj\n" +
//                "ZS5jb22CESouZ29vZ2xldmlkZW8uY29tggwqLmdzdGF0aWMuY26CDSouZ3N0YXRp\n" +
//                "Yy5jb22CEiouZ3N0YXRpY2NuYXBwcy5jboIKKi5ndnQxLmNvbYIKKi5ndnQyLmNv\n" +
//                "bYIUKi5tZXRyaWMuZ3N0YXRpYy5jb22CDCoudXJjaGluLmNvbYIQKi51cmwuZ29v\n" +
//                "Z2xlLmNvbYITKi53ZWFyLmdrZWNuYXBwcy5jboIWKi55b3V0dWJlLW5vY29va2ll\n" +
//                "LmNvbYINKi55b3V0dWJlLmNvbYIWKi55b3V0dWJlZWR1Y2F0aW9uLmNvbYIRKi55\n" +
//                "b3V0dWJla2lkcy5jb22CByoueXQuYmWCCyoueXRpbWcuY29tghphbmRyb2lkLmNs\n" +
//                "aWVudHMuZ29vZ2xlLmNvbYILYW5kcm9pZC5jb22CG2RldmVsb3Blci5hbmRyb2lk\n" +
//                "Lmdvb2dsZS5jboIcZGV2ZWxvcGVycy5hbmRyb2lkLmdvb2dsZS5jboIEZy5jb4II\n" +
//                "Z2dwaHQuY26CDGdrZWNuYXBwcy5jboIGZ29vLmdsghRnb29nbGUtYW5hbHl0aWNz\n" +
//                "LmNvbYIKZ29vZ2xlLmNvbYIPZ29vZ2xlY25hcHBzLmNughJnb29nbGVjb21tZXJj\n" +
//                "ZS5jb22CGHNvdXJjZS5hbmRyb2lkLmdvb2dsZS5jboIKdXJjaGluLmNvbYIKd3d3\n" +
//                "Lmdvby5nbIIIeW91dHUuYmWCC3lvdXR1YmUuY29tghR5b3V0dWJlZWR1Y2F0aW9u\n" +
//                "LmNvbYIPeW91dHViZWtpZHMuY29tggV5dC5iZTAhBgNVHSAEGjAYMAgGBmeBDAEC\n" +
//                "AjAMBgorBgEEAdZ5AgUDMDMGA1UdHwQsMCowKKAmoCSGImh0dHA6Ly9jcmwucGtp\n" +
//                "Lmdvb2cvR1RTMU8xY29yZS5jcmwwggEEBgorBgEEAdZ5AgQCBIH1BIHyAPAAdgBE\n" +
//                "lGUusO7Or8RAB9io/ijA2uaCvtjLMbU/0zOWtbaBqAAAAXWNMtmtAAAEAwBHMEUC\n" +
//                "IQDVfU/Oz5wny+fJ9Ja9voqQaK2gk5SJNQbtlgYdmIxEsgIgWcX5JeAvHAEz4RSF\n" +
//                "leKVrqwRWL4/tPFLTes5TDX/5JgAdgB9PvL4j/+IVWgkwsDKnlKJeSvFDngJfy5q\n" +
//                "l2iZfiLw1wAAAXWNMtmpAAAEAwBHMEUCIHpkm5YAmd5jvGhZEdciROifYYtWz8ZV\n" +
//                "C1bm4w1+qCYOAiEAxF7lf7s3Z5sHsLm+jI6jz0dXN4XqaoBaVdJH8cLlrnAwDQYJ\n" +
//                "KoZIhvcNAQELBQADggEBACMBPVKip6rpLcuCJ2+hOK1ryFbOHqcK6Bp6iH3akq0t\n" +
//                "nsbVktRhyMXj9IfOGdXmtUu3dlpss2cg1UZkytZSL/DxLclkDhSIUzBSlYqIR8SL\n" +
//                "eEikzrPEwy49q59mvgW1NR2euClIUuvtEuBcGWAcLLtgHwWhiOmlui8gb6h+Ijt+\n" +
//                "1cnBRcf/ZsdGTIzqas3XS+v1wy0gBcvW/IMfaa8BaM6L3bnOVjhi5GzTTmOVqHOT\n" +
//                "FEdT516YG4+nEdxUC0QKhUgLtxsAsbtXNjpEjFqyzVarYcSnOvjjoRJ6QCZt6Oq8\n" +
//                "dzYRssiZJ48hZumOusIHsjKPczt18GQIHbNvFBqRMOs=\n" +
//                "-----END CERTIFICATE-----";
//
//        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsPage = new SOPSPage();
//        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/testCreateCertificate.zip", testName.getMethodName());
//        basePage.click(sopsPage.runDomainButton);
//        sleep(1000);
//        basePage.elementShouldBeVisible(sopsPage.runDomainButton);
//
//        queueManagerApi.sentMessgeInQueueAPI(Cook, baseUrl(), localQueueForSopsName1, "textBody", 1, false, "4");
//        sleep(1000);
//        queueManagerApi.queueShouldNotExist(Cook, localQueueForSopsName2, baseUrl());
//
//        configurationPage = new ConfigurationPage();
//        configurationPage.addCertificate("Загрузить сертификат из файла", "/share/Cerificates/forAddCertificate.crt");
//        configurationPage.addCertificate("Ввести сертификат как текст", certificateGoogle);
//        configurationPage.restartFesb();
//        loginPage.loginInput.waitUntil(Condition.visible, 300000);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        configurationPage.viewCertificate("esb.factor-ts.ru");
//        basePage.click(basePage.closeWindowIcon);
//        configurationPage.viewCertificate("*.google.com");
//        basePage.click(basePage.closeWindowIcon);
//        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsPage.runDomain(domain);
//        queueManagerApi.sentMessgeInQueueAPI(Cook, baseUrl(), localQueueForSopsName1, "textBody", 1, false, "4");
//        sleep(3000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
//        configurationPage.downloadCertificate("*.google.com");
//        sleep(3000);
//        queueManagerPage.checkContentDowloadedFile(sessionId.toString(), certificateGoogle, "empty", "empty", sessionId, "_.google.com.pem", "empty");
//
//        open("/");
//        configurationPage.deleteCertificate("esb.factor-ts.ru");
//        queueManagerApi.sentMessgeInQueueAPI(Cook, baseUrl(), localQueueForSopsName1, "textBody", 1, false, "4");
//        sleep(1000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook, localQueueForSopsName2, 2, 0, 0, 2, 0, "local", "null", baseUrl());
//        configurationPage.restartFesb();
//        loginPage.loginInput.waitUntil(Condition.visible, 300000);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerApi.sentMessgeInQueueAPI(Cook, baseUrl(), localQueueForSopsName1, "textBody", 1, false, "4");
//        sleep(1000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook, localQueueForSopsName2, 0, 0, 0, 0, 0, "local", "null", baseUrl());
//        configurationPage.checkCertificateNotExist("esb.factor-ts.ru");
//    }

    @Test
    public void sshConnection() {
        String domain = testName.getMethodName();
        String adressSsh = "192.168.57.240";
        int portSsh = 8585;
        int portSshTestServer = 22;
        String domainID = sopsApi.createDomainAPI(Cook, domain);
        sopsApi.checkStatusOfDomainAPI(Cook, domainID, "\"REGISTERED\"");
        String bashCommand = "sshpass -p root ssh root@" + adressSsh + " -p " + portSsh + " domain start " + domainID;

        configurationServer.setupSshconnection(adressSsh, "8585");
        System.out.println(basePage.executeBashCommandToSsh(baseUrl().replace("http://", "").replace(":8181", ""), portSshTestServer, "fesb", "fesb2016", bashCommand));

        sopsApi.checkStatusOfDomainAPI(Cook, domain, "\"SYNCED\"");
    }
}