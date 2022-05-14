package ru.factorts.page;

import com.codeborne.selenide.Condition;
import org.junit.ComparisonFailure;
import utils.ConfigProperties;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class Update extends Base {

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


    public String oldVer = System.getProperty("oldVer").substring(System.getProperty("oldVer").indexOf('-') + 1, System.getProperty("oldVer").indexOf('t') - 1);
    public String lastVer = System.getProperty("lastVer").substring(System.getProperty("lastVer").indexOf('-') + 1, System.getProperty("lastVer").indexOf('t') - 1);

//    String old = "fesb-6.2.1.3.tar.gz";
//    String curr = "fesb-6.2.1.4.tar.gz";
//    String oldVer = old.substring(old.indexOf('-') + 1, old.indexOf('t') - 1);
//    String lastVer = curr.substring(curr.indexOf('-') + 1, curr.indexOf('t') - 1);

    public void update(String verShouldBe) {
        basePage.sout("oldVer:" + oldVer);
        basePage.sout("lastVer:" + lastVer);

        //Проверяем текущую версию
        IndexPage indexPage = new IndexPage();
        indexPage.checkVersionFESB(oldVer);
        basePage.compareStringAndString(verShouldBe, oldVer);

        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        System.out.println(getClassName());
        String Type = null;
        if (oldVer.equals("5.0.199")) {
            Type = "199";
        } else if (oldVer.contains("5.1.850")) {
            Type = "850";
        } else if (oldVer.contains("6.0")) {
            Type = "6.0";
        } else {
            Type = "Полностью";
        }

        String LocalQueueFoExportConf1 = "Лок.ОчередьUppate1";
        String LocalQueueFoExportConf2 = "Лок.ОчередьUppate2";
        String SopsForExportConf = " СОПС Update";
        String LibraryName = "any1.jar";
        String Message = "update";
        String maxMemoryWrapper = "6666";
        String restPath = "/RestPathFullUpdate";
        String screenDashboardName = "screenDashboardNameUpdate";
        String bdName = "bdNameFullUpdate";
        String bdPort = "7777";
        int numMessage = 3;
        int jvmHeapUsage = 51;
        String hardConfigArtemis = "33";
        String ramconfigArtemis = "44";
        String keyConstantOfBroker = "keyConstantOfBroker1";
        String valueConstantOfBroker = "valueConstantOfBroker1";
        String trackedMessageCacheSize = "666";
        String domainName = commonComponents.createIndividualName(testName.getMethodName());
        String domainEmptyName = commonComponents.createIndividualName("Пустой домен");
        String DomainIDForExportImportTest = sopsApi.createDomainAPI(Cook1, domainName);
        String domainEmptyID = sopsApi.createDomainAPI(Cook1, domainEmptyName);
        String loginReadonly = commonComponents.createIndividualName("LoginJmxReadonly");
        String passwordReadonly = commonComponents.createIndividualName("PasswordJmxReadonly");
        String loginLocalUser = commonComponents.createIndividualName("loginLocalUser");
        String passwordLocalUser = commonComponents.createIndividualName("passwordLocalUser");
        String createDashboardScreen = "[{\"name\": \"examples\", \"label\": \"Примеры\"}, {\"name\": \"" + screenDashboardName + "\", \"label\": \"" + screenDashboardName + "\"}]";
        String directoryName = "xsd";
        String fileName = "schemaXSD.xsd";
        String wsdlName = "TestSvc.wsdl";
        String modeWorkOfCluster = null;
        String pathToStorageOfQueue = null;
        String activeNodeSwitchingDelay = null;
        Boolean continueTryingToReconnect = false;
        Boolean createAndHoldOpenSecondConnection = false;
        String delayBeforeFirstSwitching = null;
        ArrayList<String> schemeList = null;
        ArrayList<String> adressesMQList = null;
        ArrayList<String> portList = null;
        if (oldVer.equals("5.0.199")) {
            try {
                api.switchModuleAPI(Cook1, baseUrl(), "factor-rest", "activate");
            } catch (ComparisonFailure e) {
                System.out.println("Различные ответы в различных версиях, не страшно");
            }
        }
        try {
            api.switchModuleAPI(Cook1, baseUrl(), "factor-dashboard", "activate");
        } catch (ComparisonFailure e) {
            System.out.println("Различные ответы в различных версиях, не страшно");
        }
        try {
            api.switchModuleAPI(Cook1, baseUrl(), "factor-store", "activate");
        } catch (ComparisonFailure e) {
            System.out.println("Различные ответы в различных версиях, не страшно");
        }

        configurationPage = new ConfigurationPage();
        configurationPage.preparationToExportConfiguration(Cook1, baseUrl(), maxMemoryWrapper, DomainIDForExportImportTest, SopsForExportConf, domainName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, jvmHeapUsage, restPath, createDashboardScreen, bdName, bdPort, Message, numMessage, LibraryName, loginLocalUser, passwordLocalUser, loginReadonly, passwordReadonly, keyConstantOfBroker, valueConstantOfBroker, modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, trackedMessageCacheSize, directoryName, fileName, "forAddCertificate.crt", wsdlName);

        //Обновляемся и проверяем версию
        settingsPage = new SettingsPage();
        updatePage.update(oldVer, lastVer, UpdatePage.RecoveryPoint.Yes);
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        indexPage = new IndexPage();
        indexPage.checkVersionFESB(lastVer);
        sopsPage = new SOPSPage();

        //Откатываемся, проверяем версию и удаляем точку восстановления
        settingsPage = new SettingsPage();
        settingsPage.recoverFromPoints(oldVer);
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        indexPage = new IndexPage();
        indexPage.checkVersionFESB(oldVer);

//        if (!oldVer.equals("5.0.199") && !oldVer.contains("5.1.850")) {
//            configurationPage.checkImportedConfiguration(Type, maxMemoryWrapper, domainName, SopsForExportConf, Integer.toString(jvmHeapUsage), restPath.replace("/", ""), screenDashboardName, bdName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, keyConstantOfBroker, valueConstantOfBroker, LibraryName, Message, loginLocalUser, loginReadonly, trackedMessageCacheSize, directoryName, fileName, "esb.factor-ts.ru");
//        }

        settingsPage = new SettingsPage();
        settingsPage.deleteRecoverPoint(oldVer);

        //Повторно обновляемся и проверяем версию
        settingsPage = new SettingsPage();
        updatePage.update(oldVer, lastVer, UpdatePage.RecoveryPoint.No);
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        indexPage = new IndexPage();
        indexPage.checkVersionFESB(lastVer);
        sopsPage = new SOPSPage();

        configurationPage.checkImportedConfiguration(Type, maxMemoryWrapper, domainName, domainEmptyID, domainEmptyName, SopsForExportConf, Integer.toString(jvmHeapUsage), restPath.replace("/", ""), screenDashboardName, bdName, LocalQueueFoExportConf1, LocalQueueFoExportConf2, hardConfigArtemis, ramconfigArtemis, keyConstantOfBroker, valueConstantOfBroker, LibraryName, Message, loginLocalUser, loginReadonly, trackedMessageCacheSize, directoryName, fileName, "esb.factor-ts.ru");

        settingsPage = new SettingsPage();
        settingsPage.pointMustNotBeExist(oldVer);
    }
}


