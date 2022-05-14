package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import utils.ConfigProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ConfigurationPage extends BasePage {
    String urlDownload = "file:///home/selenium/Downloads/";

    SmokeApi workProtocolPage = new SmokeApi();
    SOPSPage sopsPage;
    LoginPage loginPage = new LoginPage();
    //    QueueManagerPage queueManagerPage;
    QueueManagerArtemisPage queueManagerArtemisPage = new QueueManagerArtemisPage();
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    RestPage restPage = new RestPage();
    CreationUserPage creationUserPage = new CreationUserPage();
    DashboardPage dashboardPage = new DashboardPage();
    SettingsPage settingsPage;
    UserPage userPage = new UserPage();
    Base base = new Base();
    ConfigurationServer configurationServer = new ConfigurationServer();
    ClusterPage clusterPage = new ClusterPage();
    Api api = new Api();
    SOPSApi sopsApi = new SOPSApi();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerArtemisApi queueManagerArtemisApi = new QueueManagerArtemisApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    RestApi restApi = new RestApi();
    DashboardApi dashboardApi = new DashboardApi();
    CreationUserApi createUserAPI = new CreationUserApi();
    WebServcecApi webServcecApi = new WebServcecApi();

    SelenideElement import_configuration = $x(".//span[text()='Импорт конфигурации']/.."),
            import_configurationOld = $x(".//label[text()=' Импорт конфигурации FESB']"),
            exportFileNameInput = $x(".//label[text()='Имя файла']/../following-sibling::div//input"),
            importFileInput = $x(".//p[text()='Архив конфигурации']/../../input"),
            importConfigurationButton = $x(".//span[text()='Импортировать конфигурацию']/ancestor::button[not(@disabled)]"),
            exportConfiguration = $x(".//span[text()='Экспорт конфигурации']/.."),
            systemManagment = $x(".//a[text()='Управление системой']/.."),
            certificatesManagment = $x(".//a[text()='Управление сертификатами']/.."),
            uploadCertificateFileInput = $x(".//label[text()='Файл сертификата']/../following-sibling::div//input[@type=\"file\"]"),
            uploadCertificateTextInput = $x(".//label[text()='Сертификат']/../following-sibling::div//textarea"),
            expendPropertyCertificateButton = $x(".//button[@aria-label=\"Развернуть строку\"]"),
            certificateIdInput = $x(".//label[text()='Идентификатор сертификата']/../..//input[@name=\"alias\"]"),
            addCertificateButton = $x(".//div[text()=\"Добавить сертификаты в хранилище\"]/../following-sibling::div//span[text()=\"Добавить\"]/.."),
            versionInOpenedCertificate = $x(".//th/span[text()='Версия']/../following-sibling::td"),

    //Export menu
    logs = $x(".//label[text()='Имя файла']/../../..//span[text()='Протокол работы']"),
            generalConfigurationCheckBox = $x(".//div[text()='Общая конфигурация']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            multyMqCheckBox = $x(".//div[text()='Мультименеджер очередей']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            mq2CheckBox = $x(".//div[text()='Расширенный МО']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            brokerCheckBox = $x(".//div[text()='Брокер']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            configDashboardCheckBox = $x(".//div[text()='Конфигурация Dashboard']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            restCheckBox = $x(".//div[text()='REST']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            configWebServicesCheckBox = $x(".//div[text()='Конфигурация Веб-сервисов']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            savedDataBdCheckBox = $x(".//div[text()='Сохраненные данные Базы данных']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            configMonitorTransactionCheckBox = $x(".//div[text()='Конфигурация Монитора транзакций']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            generalFilesCheckBox = $x(".//div[text()='Общие файлы/ресурсы']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            savedMessagesCheckBox = $x(".//div[text()='Сохраненные сообщения']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
            addinionalLibsCheckBox = $x(".//div[text()='Дополнительно загруженные библиотеки']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),


//    configFesbCheckBox = $x(".//span[text()='Конфигурация FESB']/../../..//input/.."),
//            configBrokerCheckBox = $x(".//span[text()='Конфигурация Брокера']/../../..//input/.."),
//            configMqCheckBox = $x(".//span[text()='Конфигурация Менеджера очередей']/../../..//input/.."),
//            configRestCheckBox = $x(".//span[text()='Конфигурация Rest']/../../..//input/.."),
//            configDashboardCheckBox = $x(".//span[text()='Конфигурация Dashboard']/../../..//input/.."),
//            certificateStoreCheckBox = $x(".//span[text()='Хранилище сертификатов']/../../..//input/.."),
//            savedDataBdCheckBox = $x(".//div[text()='Сохраненные данные Базы данных']/../../../preceding-sibling::span//span[@class=\"ant-tree-checkbox-inner\"]"),
//            savedDataMqCheckBox = $x(".//span[text()='Сохраненные данные Менеджера очередей']/../../..//input/.."),
//            configMonitorTransactionCheckBox = $x(".//span[text()='Конфигурация Монитора транзакций']/../../..//input/.."),
//            addinionalLibsCheckBox = $x(".//span[text()='Дополнительно загруженные библиотеки']/../../..//input/.."),
//            localUsersCheckBox = $x(".//span[text()='Локальные пользователи']/../../..//input/.."),
//            jmxUsersCheckBox = $x(".//span[text()='JMX пользователи']/../../..//input/.."),
//            keysForSshServerCheckBox = $x(".//span[text()='Ключи от ssh сервера и push-уведомлений']/../../..//input/.."),
//            ClusterNodesConfigurationCheckBox = $x(".//span[text()='Конфигурация узлов кластера']/../../..//input/.."),

    closeExportMenuButton = $x(".//h4[text()='Экспорт']/preceding-sibling::button[@class='close']"),
            exportButton = $x(".//span[text()='Экспортировать']/.."),
    //Confirmation menu
    fullReloadConfirmationButton = $x(".//span[text()='Перезапустить FESB']/.."),
            fullReloadConfirmationOldButton = $x(".//span[text()='Перезагрузить FESB']/.."),
            moduleReloadConfirmationButton = $x(".//button[text()=' Перезапустить модуль']"),
            modulesReloadConfirmationButton = $x(".//button[text()=' Перезапустить модули']"),
            brokerReloadConfirmationButton = $x(".//button[text()=' Перезапустить модуль '][text()='Брокер']"),
            brokerOldReloadConfirmationButton = $x(".//span[text()='Перезагрузить Брокер']"),
            finishImportConfigurationButton = $x(".//span[text()='Завершить']/.."),
            rebootNotRequaredNotfication = $x(".//span[@class=\"ant-alert-message\"]//div[text()='Файлы распакованы, перезагрузка не требуется.']");

    ElementsCollection listSizeModulesForExportImport = $$x(".//div[@class=\"configuration-size\"]");

    public enum ReloadType {Module, Modules, Full, Broker, Broker850, Nope}

    /**
     * Importing configuration
     */
    @Step("Importing configuration")
    public void forImportConfiguration(String url, String SessionID, String NameFile) throws IOException {

        String UrlAcceptor;
        sout("Операционка: " + System.getProperty("os.name"));
        UrlAcceptor = "/home/fesb/share/" + NameFile + ".zip";
        System.out.println("1");
        File file = new File(UrlAcceptor);
        System.out.println("2");
        URL urlDonor = new URL("http://192.168.57.240:4444/download/" + SessionID + "/" + NameFile);
        System.out.println("3");
        ReadableByteChannel rbc = Channels.newChannel(urlDonor.openStream());
        System.out.println("4");
        FileOutputStream fos = new FileOutputStream(file);
        System.out.println("5");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("6");
        fos.close();
        rbc.close();

//        open(url);
//        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sout("Размер файла импортируемой конфигурации = " + file.length());
        importConfiguration("/share/" + NameFile + ".zip", ReloadType.Full);
    }

    @Step("Import configuration")
    public void importConfiguration(String nameFile, Enum reloadType) {
        IndexPage indexPage = new IndexPage();
        indexPage.settingsPage();
        SettingsPage settingsPage = new SettingsPage();

        if (nameFile.equals("/share/configCBR.zip")) {
            click(settingsPage.systemManagmentTab199);
            click(import_configurationOld);
        } else {
            click(settingsPage.systemManagmentTab);
            click(import_configuration);
        }
        importFileInput.sendKeys(nameFile);

        for (SelenideElement size : listSizeModulesForExportImport) {
            String s = size.getText().split(" ")[0];
            sout(s);
            Assert.assertTrue(!s.equals(" ") && !s.equals(""));
        }


        if ("exportFull.zip".equals(nameFile)) {
            generalConfigurationCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            multyMqCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            mq2CheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            brokerCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            configDashboardCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            restCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            configWebServicesCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            savedDataBdCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            configMonitorTransactionCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            generalFilesCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            savedMessagesCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            addinionalLibsCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        } else if ("Конфигурация Фесб".equals(nameFile)) {
            generalConfigurationCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            elementShouldNotBeVisible(multyMqCheckBox);
            elementShouldNotBeVisible(mq2CheckBox);
            elementShouldNotBeVisible(brokerCheckBox);
            elementShouldNotBeVisible(configDashboardCheckBox);
            elementShouldNotBeVisible(restCheckBox);
            elementShouldNotBeVisible(configWebServicesCheckBox);
            elementShouldNotBeVisible(savedDataBdCheckBox);
            elementShouldNotBeVisible(configMonitorTransactionCheckBox);
            elementShouldNotBeVisible(generalFilesCheckBox);
            elementShouldNotBeVisible(savedMessagesCheckBox);
            elementShouldNotBeVisible(addinionalLibsCheckBox);
        }
        click(importConfigurationButton);

        if (reloadType.equals(ReloadType.Module)) {
            moduleReloadConfirmationButton.shouldBe(enabled);
            sleep(1000);
            click(moduleReloadConfirmationButton);
            click(finishImportConfigurationButton);
            loginPage.loginButton.waitUntil(visible, 300000);
            loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        } else if (reloadType.equals(ReloadType.Modules)) {
            modulesReloadConfirmationButton.shouldBe(enabled);
            sleep(1000);
            click(modulesReloadConfirmationButton);
            click(finishImportConfigurationButton);
//            modulesReloadConfirmationButton.waitUntil(Not.visible, 60000);
        } else if (reloadType.equals(ReloadType.Full)) {

            if ("/share/configCBR.zip".equals(nameFile)) {
                fullReloadConfirmationOldButton.shouldBe(enabled);
                sleep(1000);
                click(fullReloadConfirmationOldButton);
            } else {
                fullReloadConfirmationButton.shouldBe(enabled);
                sleep(1000);
                click(fullReloadConfirmationButton);
            }
            loginPage.loginButton.waitUntil(visible, 300000);
            loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        } else if (reloadType.equals(ReloadType.Broker)) {
            brokerReloadConfirmationButton.shouldBe(enabled);
            sleep(1000);
            click(brokerReloadConfirmationButton);
            click(finishImportConfigurationButton);
        } else if (reloadType.equals(ReloadType.Broker850)) {
            brokerOldReloadConfirmationButton.shouldBe(enabled);
            sleep(1000);
            click(brokerOldReloadConfirmationButton);
        } else if (reloadType.equals(ReloadType.Nope)) {
            rebootNotRequaredNotfication.shouldBe(visible);
            refresh();
        }
    }

    /**
     * Export configuration
     */
    @Step("Export configuration: additional libs - {0}, logs - {1}, data - {2}")
    public void exportConfiguration(String Type, String exportFileName) {
        click(systemManagment);
        click(exportConfiguration);
        listSizeModulesForExportImport.get(0).waitUntil(visible, 1000);
        for (SelenideElement size : listSizeModulesForExportImport) {
            String s = size.getText().split(" ")[0];
            sout(s);
            Assert.assertTrue(!s.equals(" ") && !s.equals(""));
        }

        val(exportFileNameInput, exportFileName);
        switch (Type) {
            case ("Полностью"):
                click(multyMqCheckBox);
                click(mq2CheckBox);
                click(configWebServicesCheckBox);
                click(savedDataBdCheckBox);
                click(configMonitorTransactionCheckBox);
                click(savedMessagesCheckBox);
                break;
            case ("Конфигурация Фесб"):
                click(multyMqCheckBox);
                multyMqCheckBox = $x("//div[text()='Мультименеджер очередей']/../../../preceding-sibling::span[@class=\"ant-tree-checkbox ant-tree-checkbox-checked\"]");
                click(multyMqCheckBox);
                click(mq2CheckBox);
                mq2CheckBox = $x("//div[text()='Расширенный МО']/../../../preceding-sibling::span[@class=\"ant-tree-checkbox ant-tree-checkbox-checked\"]");
                click(mq2CheckBox);
                click(brokerCheckBox);
                click(configDashboardCheckBox);
                click(restCheckBox);
                click(generalFilesCheckBox);
                click(addinionalLibsCheckBox);
                break;
            default:
                throw new AssertionError("Пропущен экспорт конфигурации");
        }
        click(exportButton);
//        queueManagerPage = new QueueManagerPage();
        sleep(5000);
        open(urlDownload);
        Assert.assertEquals(exportFileName, queueManagerMultyPage.downloadedFileName.getText());
    }

    @Step
    public void preparationToExportConfiguration(String cook, String url, String maxMemoryWrapper, String DomainIDForExportImportTest, String SopsForExportConf, String domainName, String LocalQueueFoExportConf1, String LocalQueueFoExportConf2, String hardConfigArtemis, String ramconfigArtemis, int jvmHeapUsage, String restPath, String createDashboardScreen, String bdName, String bdPort, String Message, int numMessage, String LibraryName, String loginLocalUser, String passwordLocalUser, String loginReadonly, String passwordReadonly, String keyConstantOfBroker, String valueConstantOfBroker, String modeWorkOfCluster, String pathToStorageOfQueue, String activeNodeSwitchingDelay, ArrayList<String> schemeList, ArrayList<String> adressesMQList, ArrayList<String> portList, Boolean continueTryingToReconnect, Boolean createAndHoldOpenSecondConnection, String delayBeforeFirstSwitching, String trackedMessageCacheSize, String directoryName, String fileName, String certificate, String wsdlName) {
        IndexPage indexPage = new IndexPage();
        String verFesb = indexPage.getVersionFESB();
        sout(verFesb);
//Общая конфигурация
        if (!verFesb.equals("5.0.199")) {
            api.changeSettingWrapperAPI(cook, url, maxMemoryWrapper, "300", "15", "300", "5");
        }
        else {
            api.changeSettingWrapper199API(cook, url, maxMemoryWrapper);
        }
        try {
            sopsApi.startDomainAPI(cook, DomainIDForExportImportTest);
        } catch (ComparisonFailure e) {
            System.out.println("Различные ответы в различных версиях, не страшно");
        }

        createUserAPI.createUserAPI(cook, "ADMIN", loginLocalUser, passwordLocalUser);
        createUserAPI.createJmxUserApi(cook, loginReadonly, passwordReadonly, "readonly");
//Менеджер очередей
        if (!verFesb.equals("5.0.199") && !verFesb.contains("5.1.850") && !verFesb.equals("6.0.507.24")) {
            queueManagerMultyApi.editConfigMainSettingsMqAPI(cook, Base.baseUrl(), "QM", true, true, false);
        }
        //        queueManagerApi.changeSettingQueueManagerAPI(cook, url, false, true, false, jvmHeapUsage, "100 gb", "50 gb");
//Расширенный МО
        if (!verFesb.equals("5.0.199") && !verFesb.contains("5.1.850") && !verFesb.equals("6.0.507.24")) {
            queueManagerArtemisApi.changeSettingConfigurationArtemisAPI(cook, url, hardConfigArtemis, ramconfigArtemis);
        }
//Брокер и Дополнительно загруженные библиотеки
        String SopsIDForExportImport = sopsApi.generateSopsIdAPI(cook, DomainIDForExportImportTest, SopsForExportConf);
        sopsApi.createSopsWith2LocalQueueAPI(cook, url, DomainIDForExportImportTest, SopsIDForExportImport, domainName, SopsForExportConf, LocalQueueFoExportConf1, LocalQueueFoExportConf2);

//        clusterPage.createSettingCluster(modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay, schemeList, adressesMQList, portList, continueTryingToReconnect, createAndHoldOpenSecondConnection, delayBeforeFirstSwitching, trackedMessageCacheSize);

        indexPage = new IndexPage();
        verFesb = indexPage.getVersionFESB();
        sout(verFesb);
        settingsPage = new SettingsPage();
        if (verFesb.equals("5.0.199")) {
            settingsPage.loadLibrary199("/share/" + LibraryName);
            sopsApi.createConstantsOfBroker199API(cook, url, keyConstantOfBroker, valueConstantOfBroker);
        } else {
            settingsPage.loadLibrary("/share/" + LibraryName);
            sopsApi.createConstantsOfBrokerAPI(cook, url, keyConstantOfBroker, valueConstantOfBroker);
        }
//Дашборд
        dashboardApi.createScreenAPI(cook, url, createDashboardScreen);
//Rest
        if (!verFesb.equals("5.0.199") && !verFesb.contains("5.1.850") && !verFesb.equals("6.0.507.24")) {
            restApi.changeSettingRestAPI(cook, url, "rest", "REST", restPath, "0.0.0.0", 9090, "swagger", "1.0", "REST API", false, "users", "ENABLE_CORS");
            queueManagerMultyApi.sendMessgeInQueueAPI(cook, url, "QM", LocalQueueFoExportConf1, Message, numMessage, true, "1");
            api.switchModuleAPI(cook, url, "factor-dashboard", "activate");
            api.switchModuleAPI(cook, url, "factor-store", "activate");
            api.switchModuleAPI(cook, url, "factor-transaction-monitor", "activate");
            api.switchModuleAPI(cook, url, "factor-webservices", "activate");
            api.switchModuleAPI(cook, url, "factor-qme", "activate");
        } else if (verFesb.equals("6.0.507.24")) {
            restApi.changeSettingRestAPI(cook, url, "rest", "REST", restPath, "0.0.0.0", 9090, "swagger", "1.0", "REST API", false, "users", "ENABLE_CORS");
            queueManagerApi.sendMessgeInQueue6API(cook, url, LocalQueueFoExportConf1, Message, numMessage, true, "1");

        } else {
            restApi.changeSettingRest199API(cook, url, restPath, "0.0.0.0", 9090, "swagger", "1.0", "REST API", false, "users", "ENABLE_CORS");
            queueManagerApi.sendMessgeInQueue199API(cook, url, LocalQueueFoExportConf1, Message, numMessage, true, "1");
        }
//Веб сервисы
        if (!verFesb.equals("5.0.199") && !verFesb.contains("5.1.850")) {
//            sopsApi.createDirectoryAPI(cook, "wsdl");
//            String idFileWsdl = sopsApi.uploadFileAPI(cook, "/home/fesb/Stand/share/" + wsdlName);
//            sopsApi.setDirectoryForFileAPI(cook, "wsdl", idFileWsdl, wsdlName);
            webServcecApi.createWebServiceAPI(cook, Base.baseUrl(), "DIRECT", "name", "resources/wsdl/" + wsdlName);
        }
//Базы данных
        api.createBdAPI(cook, bdName, bdPort);
        settingsPage = new SettingsPage();
//Общие файлы/ресурсы
        if (!verFesb.equals("5.0.199") && !verFesb.contains("5.1.850")) {
            sopsApi.createDirectoryAPI(cook, directoryName);
            String idFileModelData = sopsApi.uploadFileAPI(cook, "/home/fesb/Stand/share/" + fileName);
            sopsApi.setDirectoryForFileAPI(cook, directoryName, idFileModelData, fileName);

            addCertificate("Загрузить сертификат из файла", "/share/Cerificates/" + certificate);
        }
    }


    /**
     * Export configuration
     */
    @Step("Check Imported configuration")
    public void checkImportedConfiguration(String Type, String maxMemoryWrapper, String DomainName, String domainEmptyID, String domainEmptyName, String
            SopsName, String jvmHeapUsage, String RestPath, String screenDashboardName, String bdName, String
                                                   LocalQueueName1, String LocalQueueName2, String hardConfigArtemis, String ramconfigArtemis, String constantOfDomainName, String valueOfDomainName, String
                                                   LibraryName, String Message, String loginLocalUser, String loginJmxUser, String trackedMessageCacheSize, String directoryName, String fileName, String certificate) {

        String cook = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String SopsForExportConf = " СОПС в пустом домене";
        String LocalQueueFoExportConf1 = "Лок.ОчередьВпустомДомене1";
        String LocalQueueFoExportConf2 = "Лок.ОчередьВпустомДомене2";


        switch (Type) {
            case ("Полностью"):
                api.checkStatusAllModulesAPI(cook, Base.baseUrl(), "true|true", "true|true", "true|true", "true|true", "true|true", "true|true", "true|true", "true|true");
//Общая конфигурация
                configurationServer.checkSizeMemmoryWrapperInConfig(maxMemoryWrapper);

//                queueManagerPage = new QueueManagerPage();
                userPage.searchLocalUser(loginLocalUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                userPage.searchJmxUser(loginJmxUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
//Менеджер очередей
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.checkSettingOfShedullerManager("ant-checkbox ant-checkbox-checked");

//                click(queueManagerTab);
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "0", "0", "Локальная", "", "");
//                queueManagerMultyPage.queueCheckAllParameters("QM",LocalQueueName1, "0", "1", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "3", "0", "0", "0", "Локальная", "", "");
//                queueManagerMultyPage.queueCheckAllParameters("QM",LocalQueueName2, "3", "0", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueShouldHaveSpecificMessage("QM", LocalQueueName2, Message);
                queueManagerMultyPage.sendNMessages("QM", LocalQueueName1, "олрлоЛОРРОИшг87687:*8&^*&YUg", 1);
//                queueManagerPage = new QueueManagerPage();
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "1", "1", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters                      (LocalQueueName1, "0", "1", "1", "1", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "4", "0", "1", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters                      (LocalQueueName2, "4", "1", "1", "0", "Локальная", "");
//Расширенный МО
                queueManagerArtemisPage.checkSettingConfigurationArtemis(hardConfigArtemis, ramconfigArtemis);
//Брокер
                sopsPage = new SOPSPage();
                click($x(String.format(SOPSPage.specificDomainButtonXpath, DomainName)));
                sopsPage.sopsCheckAllParameters(SopsName, "Локальная очередь: " + LocalQueueName1, 1);
                sopsPage.checkConstantsOfBroker(0, 1, constantOfDomainName, valueOfDomainName);
                String SopsIDForExportImport = sopsApi.generateSopsIdAPI(cook, domainEmptyID, SopsForExportConf);
                sopsApi.createSopsWith2LocalQueueAPI(cook, Base.baseUrl(), domainEmptyID, SopsIDForExportImport, domainEmptyName, SopsForExportConf, LocalQueueFoExportConf1, LocalQueueFoExportConf2);

//                clusterPage.checkSettingCluster(null, null, null, null, null, null, null, null, null, trackedMessageCacheSize);
//Дашборд
                creationUserPage.checkAvaliabilityTab(dashboardTab, $x(String.format(dashboardPage.specificMonitor, screenDashboardName)), CreationUserPage.Availability.Availible);
//Rest
                restPage.checkSettingRest("REST", RestPath);
//Веб сервисы
                creationUserPage.checkAvaliabilityTab(webServicesTab, $x(String.format(".//table//tr/td[text()='%s']", "name")), CreationUserPage.Availability.Availible);
//Базы данных
                creationUserPage.checkAvaliabilityTab(dataBaseTab, $x(String.format(dashboardPage.specificMonitor, bdName)), CreationUserPage.Availability.Availible);
//Общие файлы/ресурсы
                sopsPage.checkImportedFileInDirectory(SOPSPage.thingEnableOrDisable.ENABLED, directoryName, fileName);
                viewCertificate(certificate.replace(".crt", ""));
                click(closeWindowIcon);
//Дополнительно загруженные библиотеки
                settingsPage = new SettingsPage();
                settingsPage.checkLibraryParameters(Type, LibraryName);
                break;
            case ("Конфигурация Фесб"):
                api.checkStatusAllModulesAPI(cook, Base.baseUrl(), "true|true", "true|true", "true|true", "true|true", "true|true", "true|true", "true|true", "true|true");
//Общая конфигурация
                configurationServer.checkSizeMemmoryWrapperInConfig(maxMemoryWrapper);

//                queueManagerPage = new QueueManagerPage();
                userPage.searchLocalUser(loginLocalUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                userPage.searchJmxUser(loginJmxUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
//                userPage.failedSsearchLocalUser(loginLocalUser);
//                userPage.failedSsearchJmxUser(loginJmxUser);
//Менеджер очередей
                queueManagerMultyPage.checkSettingOfShedullerManager("ant-checkbox");
                queueManagerMultyPage.queueShouldNotExist("QM", LocalQueueName1);
                queueManagerMultyPage.queueShouldNotExist("QM", LocalQueueName2);
//Расширенный МО
                queueManagerArtemisPage.checkSettingConfigurationArtemis("100", "");
//Брокер
                sopsPage = new SOPSPage();
                elementShouldNotBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, DomainName)));
                sopsPage.checkConstantsOfBroker(0, 0, "", "");
                SopsIDForExportImport = sopsApi.generateSopsIdAPI(cook, domainEmptyID, SopsForExportConf);
                sopsApi.createSopsWith2LocalQueueAPI(cook, Base.baseUrl(), domainEmptyID, SopsIDForExportImport, domainEmptyName, SopsForExportConf, LocalQueueFoExportConf1, LocalQueueFoExportConf2);

//                clusterPage.checkSettingCluster(null, null, null, null, null, null, null, null, null, "131072");
//Дашборд
                click(dashboardTab);
                elementShouldNotBeVisible($x(String.format(dashboardPage.specificMonitor, screenDashboardName)));
//Rest
                restPage.checkSettingRest("REST", "");
//Веб сервисы
                click(webServicesTab);
                elementShouldNotBeVisible($x(String.format(".//table//tr/td[text()='%s']", "name")));
//                creationUserPage.checkAvaliabilityTab(webServicesTab, $x(String.format(".//table//tr/td[text()='%s']", "name")), CreationUserPage.Availability.NotAvailible);
//Базы данных
                click(dataBaseTab);
                elementShouldNotBeVisible($x(String.format(dashboardPage.specificMonitor, bdName)));
//                creationUserPage.checkAvaliabilityTab(dataBaseTab, $x(String.format(dashboardPage.specificMonitor, bdName)), CreationUserPage.Availability.NotAvailible);
//Общие файлы/ресурсы
                sopsPage.checkImportedDirectory(SOPSPage.thingEnableOrDisable.DISABLED, directoryName);
                viewCertificate(certificate.replace(".crt", ""));
                click(closeWindowIcon);
//Дополнительно загруженные библиотеки
                settingsPage = new SettingsPage();
                settingsPage.checkLibraryParameters(Type, LibraryName);
                break;
            case ("199"):
                configurationServer.checkSizeMemmoryWrapperInConfig(maxMemoryWrapper);
                sopsPage = new SOPSPage();

//                sopsPage.stopSOPS("SA5CTUPF","SNT NORMAL");
//                sopsPage.stopSOPS("SA5CTUPF","SNT_SBP_BG");
//                sopsPage.stopSOPS("SA5CTUPF","SNT_TAB");
//                sopsPage.stopSOPS("SA5CTUPF","SNT(наваливать файлы в АС БУ)");
//                sopsPage.stopSOPS("SA5CTUPF","SNT01");
//                sopsPage.stopSOPS("SA5CTUPF","Нагрузка");

                click($x(String.format(SOPSPage.specificDomainButtonXpath, DomainName)));
                sopsPage.sopsCheckAllParameters(SopsName, "Локальная очередь: " + LocalQueueName1, 0);
                sopsPage.checkConstantsOfBroker(0, 1, constantOfDomainName, valueOfDomainName);


                SopsIDForExportImport = sopsApi.generateSopsIdAPI(cook, domainEmptyID, SopsForExportConf);
                sopsApi.createSopsWith2LocalQueueAPI(cook, Base.baseUrl(), domainEmptyID, SopsIDForExportImport, domainEmptyName, SopsForExportConf, LocalQueueFoExportConf1, LocalQueueFoExportConf2);


//                queueManagerPage = new QueueManagerPage();
//                queueManagerMultyPage.checkSettingOfShedullerManager("ant-checkbox ant-checkbox-checked");
                restPage.checkSettingRest("REST", RestPath);
                creationUserPage.checkAvaliabilityTab(dashboardTab, $x(String.format(dashboardPage.specificMonitor, screenDashboardName)), CreationUserPage.Availability.Availible);
                creationUserPage.checkAvaliabilityTab(dataBaseTab, $x(String.format(dashboardPage.specificMonitor, bdName)), CreationUserPage.Availability.Availible);
//                click(queueManagerTab);
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "0", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters("QM",LocalQueueName1, "0", "1", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "3", "0", "0", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName2, "3", "0", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueShouldHaveSpecificMessage("QM", LocalQueueName2, Message);
                queueManagerMultyPage.sendNMessages("QM", LocalQueueName1, "олрлоЛОРРОИшг87687:*8&^*&YUg", 1);
//                queueManagerPage = new QueueManagerPage();
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "1", "1", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName1, "0", "1", "1", "1", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "4", "0", "1", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName2, "4", "0", "1", "0", "Локальная", "");
                settingsPage = new SettingsPage();
                settingsPage.checkLibraryParameters(Type, LibraryName);
//                clusterPage.checkSettingCluster(null, null, null, null, null, null, null, null, null, "131072");
//                indexPage = new IndexPage();
//                verFesb = indexPage.getVersionFESB();
//                sout(verFesb);

                checkNoCertificateExist();
//                queueManagerPage = new QueueManagerPage();
                userPage.searchLocalUser(loginLocalUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                userPage.searchJmxUser(loginJmxUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                break;
            case ("850"):
            case ("6.0"):
                configurationServer.checkSizeMemmoryWrapperInConfig(maxMemoryWrapper);
                sopsPage = new SOPSPage();
                click($x(String.format(SOPSPage.specificDomainButtonXpath, DomainName)));
                sopsPage.sopsCheckAllParameters(SopsName, "Локальная очередь: " + LocalQueueName1, 0);
                sopsPage.checkConstantsOfBroker(0, 1, constantOfDomainName, valueOfDomainName);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.checkSettingOfShedullerManager("ant-checkbox");
                restPage.checkSettingRest("REST", RestPath);
                creationUserPage.checkAvaliabilityTab(dashboardTab, $x(String.format(dashboardPage.specificMonitor, screenDashboardName)), CreationUserPage.Availability.Availible);
                creationUserPage.checkAvaliabilityTab(dataBaseTab, $x(String.format(dashboardPage.specificMonitor, bdName)), CreationUserPage.Availability.Availible);
//                click(queueManagerTab);
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "0", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName1, "0", "1", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "3", "0", "0", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName2, "3", "0", "0", "0", "Локальная", "");
                queueManagerMultyPage.queueShouldHaveSpecificMessage("QM", LocalQueueName2, Message);
                queueManagerMultyPage.sendNMessages("QM", LocalQueueName1, "олрлоЛОРРОИшг87687:*8&^*&YUg", 1);
//                queueManagerPage = new QueueManagerPage();
//                click(queuelistTab);
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName1, "0", "1", "1", "1", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName1, "0", "1", "1", "1", "Локальная", "");
                queueManagerMultyPage.queueCheckAllParameters("QM", LocalQueueName2, "4", "0", "1", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(LocalQueueName2, "4", "0", "1", "0", "Локальная", "");
                settingsPage = new SettingsPage();
                settingsPage.checkLibraryParameters(Type, LibraryName);
//                clusterPage.checkSettingCluster(null, null, null, null, null, null, null, null, null, trackedMessageCacheSize);
//                indexPage = new IndexPage();
//                verFesb = indexPage.getVersionFESB();
//                sout(verFesb);

//                queueManagerPage = new QueueManagerPage();
                userPage.searchLocalUser(loginLocalUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                userPage.searchJmxUser(loginJmxUser);
                elementShouldBeVisible(userPage.afterSearchTableRows.get(0));
                break;

            default:
                throw new AssertionError("Пропущена проверка импорта данных");
        }

    }

    @Step("Add certificate")
    public void addCertificate(String type, String certificate) {
        click(settingsPageTab);
        click(certificatesManagment);
        click(addButton2);
        click($x(".//label[text()='Тип загрузки сертификата']/../following-sibling::div"));
        valAndSelectElement($x(String.format(somethingInput, "Тип загрузки сертификата")), type);

        switch (type) {
            case ("Загрузить сертификат из файла"):
                uploadCertificateFileInput.sendKeys(certificate);
                click(continueButton);
                click(expendPropertyCertificateButton);
                val(certificateIdInput, certificate);
                break;
            case ("Ввести сертификат как текст"):
                val(uploadCertificateTextInput, certificate);
                click(continueButton);
                break;
            default:
                throw new AssertionError("Пропущен тип добавления сертификата");
        }

        click(addCertificateButton);
    }

    @Step("Add certificate")
    public void viewCertificate(String nameSertificate) {
        click(settingsPageTab);
        click(certificatesManagment);
        search(nameSertificate);
        contextClick(rowAfterSearch);
        click(viewInContextMenu);
        versionInOpenedCertificate.shouldNotBe(empty);
    }

    @Step("Download certificate")
    public void downloadCertificate(String nameSertificate) {
        click(settingsPageTab);
        click(certificatesManagment);
        search(nameSertificate);
        contextClick(rowAfterSearch);
        click(downloadInContextMenu);
    }

    @Step("Delete certificate")
    public void deleteCertificate(String nameSertificate) {
        click(settingsPageTab);
        click(certificatesManagment);
        search(nameSertificate);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(confirmationDeleteButton);
    }

    @Step("Check certificate not exist")
    public void checkCertificateNotExist(String nameSertificate) {
        click(settingsPageTab);
        click(certificatesManagment);
        searchWithoutRepeat(nameSertificate);
        sleep(2000);
        rowAfterSearch.shouldNotBe(visible);
    }

    @Step("Check No certificate exist")
    public void checkNoCertificateExist() {
        click(settingsPageTab);
        click(certificatesManagment);
        rowAfterSearch.shouldNotBe(visible);
    }
}
