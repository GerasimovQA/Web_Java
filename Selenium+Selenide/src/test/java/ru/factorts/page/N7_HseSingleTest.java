package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.io.IOException;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N7_HseSingleTest extends Base {
    ClusterPage clusterPage = new ClusterPage();
    Api api = new Api();
    static Api staticApi = new Api();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    SOPSApi sopsApi = new SOPSApi();
    SOPSPage sopsPage;
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    MessagePage messagePage = new MessagePage();
    CommonComponents commonComponents = new CommonComponents();
    CreationSOPSPage creationSOPSPage = new CreationSOPSPage("Empty");
    N6_SystemSingleTest n6_systemSingleTest;
    static BasePage staticBasePage = new BasePage();
    static ConfigurationPage staticConfiguration = new ConfigurationPage();

    String cookies1;
    String cookies2;
    String cookies3;
    String domainName = commonComponents.createIndividualName("domainForCluster");
    String sopsName = commonComponents.createIndividualName("sopsName");
    String addConfigurationName = commonComponents.createIndividualName("addConfigurationName");
    String url1 = "http://192.168.57.201:8181";
    String url2 = "http://192.168.57.202:8181";
    String url3 = "http://192.168.57.240:9971";
    String shortUrl1 = url1.replace("http://", "").replace(":8181", "");
    String shortUrl2 = url2.replace("http://", "").replace(":8181", "");
    String shortUrl3 = url3.replace("http://", "").replace(":9971", "");


    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        staticConfiguration.importConfiguration("/share/HSE/config-SQL-Postgres.zip", ConfigurationPage.ReloadType.Modules);
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        cookies1 = api.loginAPI(url1, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        cookies2 = api.loginAPI(url2, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.openWithLog("/manager/");
        open("/manager/");
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        cookies3 = api.loginAPI(url3, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.openPage(url3, "/manager/", "root", "root");
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void after() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "true|true", "true|true", "false|false", "true|true", "true|true", "true|true", "false|false", "false|false");

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-7-1");
        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void aUpdate() {
        String verShouldBe = api.getVerFromGitlabAPI("Версия ВШЭ:");
        Update update = new Update();
        update.update(verShouldBe);
    }

    @Test
    public void sql() {
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies3, managerNameDefault, "MSSQL.TEST.IN", "", 1);
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerNameDefault, "MSSQL.TEST.OUT", "5");
    }

    @Test
    public void postgres() {
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies3, managerNameDefault, "POSTGRES.TEST.IN", "", 1);
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerNameDefault, "POSTGRES.TEST.OUT", "5");
    }

    @Test
    public void zKillActiveFesbInClaster() {
        String localQueueForSopsName1 = commonComponents.createIndividualName("localQueueName");
        String localQueueForSopsName2 = commonComponents.createIndividualName("localQueueName");
        String localQueueForChannelName1 = commonComponents.createIndividualName("localQueueName");
        String topicForChannelName1 = commonComponents.createIndividualName("topicName");
        String localQueueName4 = commonComponents.createIndividualName("localQueueName");
        String user1 = "fesb";
        String user2 = "root";
        String user3 = "fesb";
        String password = "1qaz!QAZ";
        String password3 = "fesb2016";
        String pathToStorageOfQueue = "/home/fesb/share";
        String activeNodeSwitchingDelay = "100";
        String modeWorkOfCluster = "Активный/пассивный";
        String userForAutorization = "Системный администратор";
        String modeQmMaster = "Master";
        String modeQmSlave = "Slave";
        String channelName = "Канал для кластера";
        String LocalQueueNameIn = "Вход для кластера";
        String LocalQueueNameOut = "Очередь для кластера";
        String methodConnection = "masterslave";
        String typeCanalDuplex = "Duplex";
        String typeCanalNotDuplex = "NotDuplex";
        String bashCommandStartDaemon = "cd fesb/wrapper/bin; echo '1qaz!QAZ' | sudo -S ./startDaemon.sh";
        String bashCommandStopDaemon = "cd fesb/wrapper/bin; echo '1qaz!QAZ' | sudo -S ./stopDaemon.sh";
        String bashCommandUpdate201 = "sshpass -p 1qaz!QAZ scp /home/fesb/Stand/upload/* fesb@192.168.57.201:/home/fesb/app/update/";
        String bashCommandUpdate202 = "sshpass -p 1qaz!QAZ scp /home/fesb/Stand/upload/* fesb@192.168.57.202:/home/fesb/app/update/";
        String bashCommandRebootMachine = "echo '1qaz!QAZ' | sudo -S reboot";
        String parameterName = "Фабрика соединений Менеджера очередей";
        int numMessage = 25000;
        String managerName = testName.getMethodName();

        ArrayList<String> schemeList = new ArrayList<>();
        ArrayList<String> adressesMQList = new ArrayList<>();
        ArrayList<String> portList = new ArrayList<>();
        ArrayList<String> jmxPortList = new ArrayList<>();
        ArrayList<String> ipForCanalList = new ArrayList<>();

        adressesMQList.add("localhost");
        adressesMQList.add("192.168.58.241");
        portList.add("61616");
        portList.add("61616");
        jmxPortList.add("1099");
        jmxPortList.add("1099");
        ipForCanalList.add(shortUrl1);
        ipForCanalList.add(shortUrl2);

        String[] schemesAll = {"Фабрика соединений Менеджера очередей|" + addConfigurationName + "|Обычная", "Сетевое соединение|192.168.57.201|61616", "Сетевое соединение|192.168.57.202|61616"};

        String[] point1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2, "селект-в-параметрах|" + parameterName + "|" + addConfigurationName};
        String[][] pointAll = {point1, point2};

//        queueManagerMultyApi.createManagerAPI(cookies1, url1, managerName);
//        queueManagerMultyApi.createManagerAPI(cookies2, url2, managerName1);
        queueManagerMultyApi.createManagerAPI(cookies3, url3, managerName);


        /*Создание СОПСа с доп конфигурацией "Фабрика менеджера MQ"*/
        String domainID = sopsApi.createDomainAPI(url3, cookies3, domainName);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName, schemesAll);
//        sopsPage.editProperties(domainName, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "20", null, null);
        creationSOPSPage.createSOPS(domainName, sopsName, pointAll);
        /*Создание СОПСа с доп конфигурацией "Фабрика менеджера MQ"*/

        /*Настройка канала.*/
//        queueManagerMultyPage.createChannel(channelName, ipForCanalList, portList, localQueueForChannelName1, methodConnection, typeCanalNotDuplex);
        queueManagerMultyPage.createChannel(managerName, channelName, ipForCanalList, portList, localQueueForChannelName1, null, "static", typeCanalNotDuplex, "", "");

//        basePage.restartModule();
        sleep(3000);
        queueManagerMultyApi.restartMqAPI(cookies3, url3);
        queueManagerMultyPage.stopChannel(managerName, channelName);
        /*Настройка канала*/

        /*Настройка кластера*/
        basePage.openPage(url1, "/manager/", "root", "root");
        clusterPage.createSettingCluster850(modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay);
        basePage.openPage(url2, "/manager/", "root", "root");
        clusterPage.createSettingCluster850(modeWorkOfCluster, pathToStorageOfQueue, activeNodeSwitchingDelay);
        /*Настройка кластера*/

        /*Обновляем кластер*/
        sleep(10000);
        basePage.executeBashCommandToSsh(shortUrl3, 22, user3, password3, bashCommandUpdate201);
        sleep(60000);
        basePage.executeBashCommandToSsh(shortUrl3, 22, user3, password3, bashCommandUpdate202);
        /*Обновляем кластер*/

        /*Отправка сообщений*/
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies3, url3, managerName, localQueueForSopsName1, messagePage.createMessageWithBiteSize(75000), numMessage, true, "1");
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies3, url3, managerName, localQueueForChannelName1, messagePage.createMessageWithBiteSize(75000), numMessage, true, "1");
        /*Отправка сообщений*/

        basePage.openPage(url1, "/manager/", "root", "root");
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmMaster);

        basePage.openPage(url3, "/manager/", "root", "root");
        queueManagerMultyPage.startChannel(managerName, channelName);
        sopsApi.startDomainAPI(cookies3, url3, domainID);

        basePage.openPage(url2, "/manager/", "root", "root");
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmSlave);

        basePage.executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandRebootMachine);
        queueManagerMultyPage.searchQueue(managerNameDefault, localQueueForSopsName1);
        queueManagerMultyPage.autoUpdateOn();
        sleep(30000);
        refresh();
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(Condition.visible, 600000);
        queueManagerMultyPage.searchQueue(managerNameDefault, localQueueForSopsName2);
        queueManagerMultyPage.autoUpdateOn();
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(Condition.exactText(Integer.toString(numMessage)), 600000);
        open(url2);
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmMaster);

        try {
            basePage.waitAvailibleUrl(url1, 600000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        basePage.openPage(url1, "/manager/", "root", "root");
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmSlave);
        basePage.executeBashCommandToSsh(shortUrl2, 22, user2, password, bashCommandRebootMachine);

        queueManagerMultyPage.searchQueue(managerNameDefault, localQueueForSopsName2);
        queueManagerMultyPage.autoUpdateOn();
//        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(Condition.visible, 600000);

        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(Condition.exactText(Integer.toString(numMessage)), 600000);
        queueManagerMultyPage.searchQueue(managerNameDefault, localQueueForChannelName1);
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(Condition.exactText(Integer.toString(numMessage)), 600000);
        open(url1);
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmMaster);

        try {
            basePage.waitAvailibleUrl(url2, 600000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        basePage.openPage(url2, "/manager/", "root", "root");
        queueManagerMultyPage.checkModeQM(managerNameDefault, modeQmSlave);

        String cookies1 = api.loginAPI(url1, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.deleteQueueAPI(cookies1, url1, managerNameDefault, localQueueForSopsName2);
        queueManagerMultyApi.deleteQueueAPI(cookies1, url1, managerNameDefault, localQueueForChannelName1);
    }

}
