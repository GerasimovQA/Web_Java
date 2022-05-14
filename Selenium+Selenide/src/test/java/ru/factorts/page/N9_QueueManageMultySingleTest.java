package ru.factorts.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
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

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N9_QueueManageMultySingleTest extends Base {
    String numberSendMessage = null;
    String corellationIdMessage = null;
    String queueForReply = null;
    String typeJmsMessage = null;
    String groupIdMessage = null;
    String numberInGroupMessage = null;
    String valuePriorityMessage = null;
    String validityMessage = null;
    String initialDelay = null;
    String timeToResendMessage = null;
    String headerOfCounerMessage = null;
    String numberOfResendsMessage = null;
    String cronMessage = null;
    String nameFileMessage = null;
    String classMessage = null;
    String sizeBodyMessage = null;
    String redirectedMessage = null;

    //    static String cook1;
    static String cook1 = null;
    static String cook2 = null;
    String connectorID = null;

    @Rule
    public TestName testName = new TestName();

    private QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    private QueuesPage queuesPage;
    private SpecificMessagePage specificMessagePage;
    private SpecificQueuePage specificQueuePage;
    private MessagePage messagePage = new MessagePage();
    private TopicsPage topicsPage;
    private SOPSPage sopsPage;
    private CreationSOPSPage creationSOPSPage = new CreationSOPSPage("Empty");
    private SpecifecTopicPage specifecTopicPage;
    IndexPage indexPage;
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    CommonComponents commonComponents = new CommonComponents();
    Api api = new Api();
    static Api staticApi = new Api();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    SOPSApi sopsApi = new SOPSApi();
    EventInterceptionPage eventInterceptionPage = new EventInterceptionPage();
    SettingsPage settingsPage;
    static SettingsPage staticSettingsPage;
    static PolicyDeliveryPage policyDeliveryPage = new PolicyDeliveryPage();


    private static final String QUEUE_NAME = "TEST_QUEUE",
            TOPIC_NAME = "TEST_TOPIC",
            VIRTUAL_QUEUE_NAME = "VIRTUAL_QUEUE",
            CANAL_NAME = "TEST_CANAL",
            DOMAIN_NAME = "TEST_DOMAIN",
            SOPS_NAME = "TEST_SOPS",
            LOCAL_QUEUE_IN = "TEST_QUEUE_IN",
            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT";

    @BeforeClass
    public static void beforeClass() {
        cook1 = staticApi.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        cook2 = staticApi.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        staticSettingsPage = new SettingsPage();
        staticSettingsPage.turnOnModule("Монитор транзакций");
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();

        if ($x(".//aside").waitUntil(Condition.enabled, 2000).getAttribute("class").equals("main_sidebar sidebar_close")) {
            $x(".//i[@class='fa fa-bars']").click();
            new IndexPage().queueManagerPage();
        }
    }

    @After
    public void afterTest() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        cook1 = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook1, baseUrl(), "true|true", "true|true", "false|false", "false|false", "true|true", "true|true", "true|true", "false|false");

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-9-1");
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-9-2");
        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void createManager() {
        String managerName1 = testName.getMethodName() + "_1";
        String managerName2 = testName.getMethodName() + "_2";
        queueManagerMultyPage.createManager(managerName1);
        queueManagerMultyPage.startManager(managerName1);
        queueManagerMultyPage.createManager(managerName2);
        queueManagerMultyPage.startManager(managerName2);
    }

    @Test
    public void deleteManagerWhithoutData() {
        String managerName1 = testName.getMethodName() + "_1";
        String managerName2 = testName.getMethodName() + "_2";

        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName2);

//        open(baseUrl());
        queueManagerMultyPage.deleteManager(managerName1);
        queueManagerMultyPage.deleteManager(managerName2);
        queueManagerMultyPage.checkManagerNotExist(managerName1);
        queueManagerMultyPage.checkManagerNotExist(managerName2);
    }

    /**
     * Check redirection from virtual to local queue
     *
     * @throws Exception
     */
    @Test
    public void redirectionFromVirtualToLocalQueue() {
        String managerName1 = testName.getMethodName();
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);

        String localOueueName1 = commonComponents.createIndividualName(QUEUE_NAME);
        String localOueueName2 = commonComponents.createIndividualName(QUEUE_NAME);
        String topicName1 = commonComponents.createIndividualName(TOPIC_NAME);
        String topicName2 = commonComponents.createIndividualName(TOPIC_NAME);
        String virtualQueueName = commonComponents.createIndividualName(VIRTUAL_QUEUE_NAME);
//        open(baseUrl());
        queueManagerMultyPage.createVirtualQueue(managerName1, virtualQueueName, localOueueName1, localOueueName2, topicName1, topicName2);
        commonComponents.closeNotificationIfExist();
        queueManagerMultyPage.sendNSavedMessagesWithoutCheck(managerName1, virtualQueueName, 3);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, virtualQueueName);

        queueManagerMultyPage.queueCheckAllParameters(managerName1, localOueueName1, "3", "0", "3", "0", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName1, localOueueName2, "3", "0", "3", "0", "Локальная", "", "");
        queueManagerMultyPage.topicCheckAllParameters(managerName1, topicName1, "0", "3", "0", "Локальный", "", "");
        queueManagerMultyPage.topicCheckAllParameters(managerName1, topicName2, "0", "3", "0", "Локальный", "", "");
        String[] queueArrray = {localOueueName1, localOueueName2};
        for (String name : queueArrray) {
            queueManagerMultyPage.sucessDeleteQueue(managerName1, name);
        }

        String[] topicArrray = {topicName1, topicName2};
        for (String name : topicArrray) {
            queueManagerMultyPage.sucessDeleteTopic(managerName1, name);
        }
    }

    @Description("Create Check And Delete Transport Queue")
    @Test
    @Parameters(value = {
            "NotDuplex | 61614",
            "Duplex | 61615",
    })
    public void createCheckAndDeleteTransportQueue(String TypeCanal, String recieverPort) {
        String managerName1 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "1";
        String managerName2 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        String recieverName = managerName1;
        String recieverProtocol = "tcp";
        String recieverAddress = "0.0.0.0";
        ArrayList<String> ipList = new ArrayList<>();
        ArrayList<String> portList = new ArrayList<>();
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.сanalsList();
        String channalName = commonComponents.createIndividualName(CANAL_NAME);
        String transportQueueName = commonComponents.createIndividualName(QUEUE_NAME);
        String topicName = commonComponents.createIndividualName(TOPIC_NAME);
        String localQueueName = transportQueueName;
        ipList.add(baseUrl().split("//")[1].split(":")[0]);
        portList.add(recieverPort);
        String port = ":9992";
        int messagesNumber = 4;

        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);

        queueManagerMultyPage.createChannel(managerName1, channalName, ipList, portList, transportQueueName, topicName, "static", TypeCanal, userName, userPassword);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);
        queueManagerMultyApi.createRecieverAPI(cook2, baseUrl_2(), managerName2, recieverName, recieverProtocol, recieverAddress, recieverPort);
        sleep(10000);
//        queueManagerPage.queuesList();
        queueManagerMultyPage.channelCheckAllParameters(managerName1, managerName2, channalName, ipList, portList, transportQueueName, topicName, "static", TypeCanal);
        queueManagerMultyPage.queueCheckAllParameters(managerName1, transportQueueName, "0", "1", "0", "0", "Транспортная", channalName, "");

        switch (TypeCanal) {
            case ("NotDuplex"):
                queueManagerMultyPage.sendNSavedMessagesToQueue(managerName1, transportQueueName, messagesNumber);
                queueManagerMultyPage.sendNSavedMessagesToTopic(managerName1, topicName, messagesNumber);
                break;
            case ("Duplex"):
                basePage.contextClick(queueManagerMultyPage.afterSearchQueueRow);
                basePage.click(queueManagerMultyPage.contextSendMessage);
                messagePage = new MessagePage();
                messagePage.sendMessageInQueueNTimesAtOnceWithoutText(transportQueueName, messagesNumber);
//                queueManagerPage = new QueueManagerPage();
//                basePage.click(queueManagerPage.queuesList);
//                queuesPage = new QueuesPage();
                queueManagerMultyPage.sendNSavedMessagesToTopic(managerName1, topicName, messagesNumber);
                break;
            default:
                throw new AssertionError("Пропущена отправка сообщения в транспортную очередь");
        }
        queueManagerMultyPage.queueCheckAllParameters(managerName1, transportQueueName, "0", "1",
                Integer.toString(messagesNumber), Integer.toString(messagesNumber), "Транспортная", channalName, "");
        queueManagerMultyPage.topicCheckAllParameters(managerName1, topicName, "1", Integer.toString(messagesNumber), Integer.toString(messagesNumber), "Транспортный", channalName, "");

        open("http://" + ipList.get(0) + port);
        loginPage.loginClickButton("root", "root");

        switch (TypeCanal) {
            case ("NotDuplex"):
                queueManagerMultyPage.queueCheckAllParameters(managerName2, localQueueName, Integer.toString(messagesNumber), "0", Integer.toString(messagesNumber), "0", "Локальная", "", "");
                queueManagerMultyPage.topicCheckAllParameters(managerName2, topicName, "0", Integer.toString(messagesNumber), "0", "Локальный", "", "");

                break;
            case ("Duplex"):
                queueManagerMultyPage.queueCheckAllParameters(managerName2, localQueueName, Integer.toString(messagesNumber), "1", Integer.toString(messagesNumber), "0", "Локальная", "", "");
                queueManagerMultyPage.topicCheckAllParameters(managerName2, topicName, "1", Integer.toString(messagesNumber), "0", "Локальный", "", "");
                break;
            default:
                throw new AssertionError("Пропушена проверка параметров локальной очереди на удаленной машине");
        }
//
//        basePage.openPage(baseUrl());
//        queueManagerPage.sucessDeleteQueue(localQueueName);
//        queueManagerPage.сanalsList();
//        queueManagerPage.deleteCanal(canalName);
//        indexPage = new IndexPage();
//        indexPage.logout();
//
//        open("http://" + ipList.get(0) + port);
//        loginPage.loginClickButton("root", "root");
//        open("http://" + ipList.get(0) + port + "/manager/#/queue-manager/queues");
//        queueManagerPage.queueCheckAllParameters(localQueueName, Integer.toString(messagesNumber), "0",
//                Integer.toString(messagesNumber), "0", "Локальная", "");
//        queueManagerPage.queueManagerPage();
//        queueManagerPage.queuesList();
//        queueManagerPage.sucessDeleteQueue(localQueueName);
//        indexPage = new IndexPage();
//        indexPage.logout();
    }

    @Test
    @Parameters(value = {
            "Локальная очередь",
            "Виртуальная очередь",
            "Очередь сегментации",
            "Очередь агрегации",
            "Транспортная очередь",
    })
    public void propertiesAndMessgesOfQueue(String typeQueue) {
        String text = "test message";
        int sumMessages = 2;
        String managerName1 = "propertiesAndMessgesOfQueue_1";
        String managerName2 = "propertiesAndMessgesOfQueue_2";
        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String cookies2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();

        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);

        switch (typeQueue) {
            case ("Локальная очередь"):
                String nameLocalQueue = commonComponents.createIndividualName("Local_ForInformationAboutQueue");
                queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueue);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameLocalQueue, text, sumMessages);
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameLocalQueue, Integer.toString(sumMessages), "0",
                        Integer.toString(sumMessages), "0", "Локальная", "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameLocalQueue, typeQueue, "", "");
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameLocalQueue, text);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                queueManagerPage.checkActiveCustomersQueue(nameLocalQueue);
//                queueManagerPage.checkActiveProducersQueue(nameLocalQueue);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
                break;

            case ("Виртуальная очередь"):
                String nameVirtualQueueFrom = commonComponents.createIndividualName("VirtualFrom_ForInformationAboutQueue");
                String nameVirtualQueueTo = commonComponents.createIndividualName("VirtualTo_ForInformationAboutQueue");
                String nameVirtualQueueTopicTo = commonComponents.createIndividualName("VirtualTopicTo_ForInformationAboutQueue");
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.virtual, nameVirtualQueueFrom, nameVirtualQueueTo, nameVirtualQueueTopicTo);
//                queueManagerMultyApi.restartMqAPI(cookies);
                sleep(1000);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameVirtualQueueFrom, text, sumMessages);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameVirtualQueueTo, text, sumMessages);
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameVirtualQueueFrom, "-", "2", "-", "-", "Виртуальная", "", "VirtualTo_ForInformati...");
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameVirtualQueueTo, Integer.toString(sumMessages + sumMessages), "0", Integer.toString(sumMessages + sumMessages), "0", "Локальная", "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameVirtualQueueFrom, typeQueue, nameVirtualQueueTo, nameVirtualQueueTopicTo);
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameVirtualQueueFrom, text);
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameVirtualQueueTo, "Локальная очередь", "", "");
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameVirtualQueueTo, text);
                queueManagerMultyPage.topicCheckAllParameters(managerName1, nameVirtualQueueTopicTo, "0", Integer.toString(sumMessages), "0", "Локальный", "", "");
                queueManagerMultyPage.checkParametersTopicInTableAndProperties(managerName1, nameVirtualQueueTopicTo);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                queueManagerPage.checkActiveCustomersQueue(nameVirtualQueueFrom);
//                queueManagerPage.checkActiveCustomersQueue(nameVirtualQueueTo);
//                queueManagerPage.checkActiveProducersQueue(nameVirtualQueueFrom);
//                queueManagerPage.checkActiveProducersQueue(nameVirtualQueueTo);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
                break;

            case ("Очередь сегментации"):
                String nameSegmentQueueFrom = commonComponents.createIndividualName("SegmentFrom_ForInformationAboutQueue");
                String nameSegmentQueueTo = commonComponents.createIndividualName("SegmentTo_ForInformationAboutQueue");
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.fragment, nameSegmentQueueFrom, nameSegmentQueueTo, "");
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameSegmentQueueFrom, text, sumMessages);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameSegmentQueueTo, text, sumMessages);
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameSegmentQueueFrom, "0", "1", Integer.toString(sumMessages), Integer.toString(sumMessages), "Сегментация", "", "");
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameSegmentQueueTo, Integer.toString(sumMessages + sumMessages),
                        "0", Integer.toString(sumMessages + sumMessages), "0", "Локальная", "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameSegmentQueueFrom, typeQueue, "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameSegmentQueueTo, "Локальная очередь", "", "");
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameSegmentQueueFrom, text);
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameSegmentQueueTo, text);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                queueManagerPage.checkActiveCustomersQueue(nameSegmentQueueFrom);
//                queueManagerPage.checkActiveCustomersQueue(nameSegmentQueueTo);
//                queueManagerPage.checkActiveProducersQueue(nameSegmentQueueFrom);
//                queueManagerPage.checkActiveProducersQueue(nameSegmentQueueTo);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
                break;
            case ("Очередь агрегации"):
                String nameAgregateQueueFrom = commonComponents.createIndividualName("AgregateFrom_ForInformationAboutQueue");
                String nameAgregateQueueTo = commonComponents.createIndividualName("AgregateTo_ForInformationAboutQueue");
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.aggregate, nameAgregateQueueFrom, nameAgregateQueueTo, "");
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameAgregateQueueFrom, text, sumMessages);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameAgregateQueueTo, text, sumMessages);
                sleep(2000);
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameAgregateQueueFrom, "0", "1",
                        Integer.toString(sumMessages), Integer.toString(sumMessages), "Агрегация", "", "");
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameAgregateQueueTo, Integer.toString(sumMessages), "0",
                        Integer.toString(sumMessages), "0", "Локальная", "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameAgregateQueueFrom, typeQueue, "", "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameAgregateQueueTo, "Локальная очередь", "", "");
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameAgregateQueueFrom, text);
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameAgregateQueueTo, text);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                queueManagerPage.checkActiveCustomersQueue(nameAgregateQueueFrom);
//                queueManagerPage.checkActiveCustomersQueue(nameAgregateQueueTo);
//                queueManagerPage.checkActiveProducersQueue(nameAgregateQueueFrom);
//                queueManagerPage.checkActiveProducersQueue(nameAgregateQueueTo);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
                break;
            case ("Транспортная очередь"):
                String userName = managerName1;
                String userPassword = managerName1;
                String userGroups = "[\"read\", \"admin\", \"write\"]";
                String recieverName = managerName1;
                String recieverProtocol = "tcp";
                String recieverAddress = "0.0.0.0";
                String recieverPort = "61613";
                String url = "fesb-test-9-2";
                String nameTransportQueue = commonComponents.createIndividualName("Transport_ForInformationAboutQueue");
                String nameTransportTopic = commonComponents.createIndividualName("Transport_ForInformationAboutTopic");
                String nameCanal = commonComponents.createIndividualName("Canal_ForInformationAboutTransportQueue");

                queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
                queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
                queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
                queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);
                queueManagerMultyApi.createRecieverAPI(cook2, baseUrl_2(), managerName2, recieverName, recieverProtocol, recieverAddress, recieverPort);

                queueManagerMultyApi.createChannelAPI(cook1, managerName1, QueueManagerMultyApi.typeQueue.TRANSPORT, nameTransportQueue, nameTransportTopic, nameCanal, false, url, recieverPort, userName, userPassword);
                sleep(5000);
                queueManagerMultyApi.sendMessgeInQueueAPI(cookies, managerName1, nameTransportQueue, text, sumMessages);
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameTransportQueue, "0", "1",
                        Integer.toString(sumMessages), Integer.toString(sumMessages), "Транспортная", nameCanal, "");
                queueManagerMultyPage.checkParametersQueueInTableAndProperties(managerName1, nameTransportQueue, typeQueue, "", "");
                queueManagerMultyPage.checkMessagesQueueInTableAndProperties(managerName1, nameTransportQueue, text);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                queueManagerPage.checkActiveCustomersQueue(nameTransportQueue);
//                queueManagerPage.checkActiveProducersQueue(nameTransportQueue);
                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/

                queueManagerMultyApi.queueCheckAllParametersAPI(cookies2, baseUrl_2(), managerName2, nameTransportQueue, 3, 0, 0, 3, 0, null, null, null, null);
                break;
            default:
                throw new AssertionError("Тест пропущен для типа: " + typeQueue);
        }
    }

    @Test
    public void queueActionInformation() {
        String managerName1 = testName.getMethodName() + "1";
        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        String text = "test message";
        int sumMessages = 2;
        String nameLocalQueue1 = commonComponents.createIndividualName("Local_ForInformationAboutQueue");
        String nameLocalQueue2 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_Change");
        String nameLocalQueue3 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_Change2");
        String nameLocalQueue4 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_import");

        queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueue1);
        queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueue2);
        queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueue3);
        queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueue4);
        specificQueuePage = new SpecificQueuePage("empty");

        queueManagerMultyPage.searchQueue(managerName1, nameLocalQueue1);
        basePage.autoUpdateOff();
        String valueNameQueueInTable = queueManagerMultyPage.afterSearchQueueName.getText().trim();
        basePage.click(queueManagerMultyPage.afterSearchQueueName);
        Assert.assertEquals(valueNameQueueInTable, queueManagerMultyPage.nameChoosenQueueList.getText());

        /*Отправка сообщений со страницы информации об очереди*/
        specificQueuePage = new SpecificQueuePage("Empty");
        basePage.click(specificQueuePage.sentMessageButton);
        messagePage.sendMessageWithOutCheck(text, sumMessages);
        back();

        /*Перехватчик событий*/
        basePage.click(specificQueuePage.actionButton);
        basePage.click(specificQueuePage.eventInterceptorButton);
        Assert.assertTrue(url().contains("/qms/" + managerName1 + "/configuration/capture"));
        specificQueuePage.addEventInterceptorButton.shouldBe(Condition.visible);
        back();

        /*Редактирование очереди*/
        Assert.assertEquals(valueNameQueueInTable, queueManagerMultyPage.nameChoosenQueueList.getText());
        basePage.click(specificQueuePage.actionButton);
        basePage.click(specificQueuePage.editButton);
        queueManagerMultyPage.headerInEditForm.shouldBe(Condition.visible);
        Assert.assertEquals(nameLocalQueue1, specificQueuePage.nameQueueInEditForm.getValue());
        basePage.closeForm();

        /*Экспорт очереди*/
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        String urlDownload = "file:///home/selenium/Downloads/";
        String downloadFilePath = "/home/fesb/share/Downloaded/downloadedQueueMulty.zip";
        String downloadedFileName = null;
        basePage.click(specificQueuePage.actionButton);
        basePage.click(specificQueuePage.exportButton);
        sleep(2000);
        open(urlDownload);
        downloadedFileName = queueManagerMultyPage.downloadedFileName.getText();
        basePage.compareElementTextShouldContainsText(nameLocalQueue1, queueManagerMultyPage.downloadedFileName);
        queueManagerMultyPage.checkContentDowloadedFile(sessionId.toString(), nameLocalQueue1, "очередь", downloadFilePath, sessionId, downloadedFileName, downloadFilePath);
        back();
        back();

        /*Импорт очереди*/
        queueManagerMultyPage.searchQueue(managerName1, nameLocalQueue4);
        basePage.click(queueManagerMultyPage.afterSearchQueueName);
        basePage.click(specificQueuePage.actionButton);
        sleep(20000);
        specificQueuePage.importInput.sendKeys("/share/Downloaded/downloadedQueueMulty.zip");
        queueManagerMultyPage.rowsMessagesInTable.shouldHaveSize(sumMessages);

        /*Очистка очереди*/
        refresh();
        basePage.click(specificQueuePage.actionButton);
        basePage.click(specificQueuePage.cleanOutButton);
        queueManagerMultyPage.headerInClearForm.shouldBe(Condition.visible);
        Assert.assertTrue(queueManagerMultyPage.textInConfirmClearQueue.getText().contains(nameLocalQueue4));
        basePage.click(specificQueuePage.confirmCleanOutButton);
        queueManagerMultyPage.rowsMessagesInTable.shouldHaveSize(0);
        basePage.compareStringAndElementText("Нет данных", queueManagerMultyPage.noDataText);

        /*Удаление очереди*/
        basePage.click(specificQueuePage.actionButton);
        basePage.click(specificQueuePage.deleteButton);
        basePage.click(specificQueuePage.confirmDeleteButtonInInfo);

        System.out.println(url());
        basePage.compareStringAndStringShouldContainsText(url(), "/qms/" + managerName1 + "/queues");
        queueManagerMultyPage.searchQueue(managerName1, nameLocalQueue4);
        queueManagerMultyPage.afterSearchTableRows.shouldHave(size(0));

        /*Изменение выбранной очереди*/
        queueManagerMultyPage.searchQueue(managerName1, nameLocalQueue2);
        basePage.click(queueManagerMultyPage.afterSearchQueueName);
        Assert.assertEquals(nameLocalQueue2, queueManagerMultyPage.nameChoosenQueueList.getText());
        basePage.click(queueManagerMultyPage.nameChoosenQueueList);
        basePage.val(queueManagerMultyPage.inputChoosenQueueList, nameLocalQueue3);
        queueManagerMultyPage.resultsQueueListForChoose.shouldHaveSize(1);
        basePage.click(queueManagerMultyPage.resultsQueueListForChoose.get(0));
        Assert.assertEquals(nameLocalQueue3, queueManagerMultyPage.nameChoosenQueueList.getText());
        specificQueuePage = new SpecificQueuePage("empty");
        basePage.click(specificQueuePage.propertiesButton);
        Assert.assertEquals(nameLocalQueue3, specificQueuePage.valueNameIhInPropertiesQueue.getText());
    }

    @Test
    @Parameters(value = {
            "Локальная очередь | Текст",
            "Виртуальная очередь | Текст",
            "Очередь сегментации | Файл",
            "Очередь агрегации | Сгенерировать",
            "Транспортная очередь | Сгенерировать в диапазоне",
    })
    public void messageFunctional(String typeQueue, String typeMessage) {
        String numberOfMessage = null;
        Object[] parametersOfMessage;
        Object[] parametersOfSent;

        String managerName1 = "messageFunctional";
        String managerName2 = "messageFunctional2";
        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);

        switch (typeQueue) {
            case ("Локальная очередь"):
                groupIdMessage = "";
                queueForReply = "";
                numberInGroupMessage = "0";
                validityMessage = "";
                classMessage = "ActiveMQTextMessage";
                sizeBodyMessage = "228";
                String nameLocalQueueTo = commonComponents.createIndividualName("Local_ForTestMessagesFunctional");

                queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameLocalQueueTo);
                basePage.click(basePage.sendMessageTab);
                basePage.click(messagePage.moduleSelect);
                basePage.valAndSelectElement(messagePage.moduleInput, "Мультименеджер очередей");
                basePage.click(messagePage.mqSelect);
                basePage.valAndSelectElement(messagePage.mqInput, managerName1);
                basePage.click(messagePage.addresseSelect);
                basePage.valAndSelectElement(messagePage.addresseInput, nameLocalQueueTo);
                basePage.click(messagePage.kindOfContentMessageSelect);
                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
                basePage.val(messagePage.messageTextAria, messagePage.messageWithAllSimbols);
                basePage.click(messagePage.makePersistentCheckBox);
                basePage.click(messagePage.sendMessageButton);

                queueManagerMultyPage = new QueueManagerMultyPage();
                queueManagerMultyPage.contentSpecificQueue(managerName1, nameLocalQueueTo);
                specificQueuePage = new SpecificQueuePage("");
                specificQueuePage.saveParametersMessageInTable();
                specificQueuePage.openFirstMessage();
                specificMessagePage = new SpecificMessagePage("");
                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable, specificQueuePage.valueIdMessageInTable, specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage, specificQueuePage.valuePersistenceMessageInTable, queueForReply, numberInGroupMessage, validityMessage, specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage, specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable, specificQueuePage.valueTypeMessageInTable, messagePage.messageWithAllSimbols);
                break;
            case ("Виртуальная очередь"):
                numberSendMessage = "1";
                corellationIdMessage = "888";
                queueForReply = commonComponents.createIndividualName("NewLocalQueue_ForTestMessagesFunctional");
                typeJmsMessage = "Тип JMS-Virt";
                groupIdMessage = "123";
                numberInGroupMessage = "7";
                valuePriorityMessage = "5";
                validityMessage = "90";
                initialDelay = "30000";
                timeToResendMessage = "3333333";
                headerOfCounerMessage = "Заголовок счетчика-Virt";
                numberOfResendsMessage = "0";
                cronMessage = "Сообщение Cron-Virt";
                nameFileMessage = "имя файла-Virt";
                classMessage = "ActiveMQTextMessage";
                sizeBodyMessage = "228";
                redirectedMessage = "Нет";
                String nameVirtualQueueFrom = commonComponents.createIndividualName("VirtualFrom_ForInformationAboutQueue");
                String nameVirtualQueueTo = commonComponents.createIndividualName("VirtualTo_ForInformationAboutQueue");
                String nameVirtualTopicTo = commonComponents.createIndividualName("VirtualToTopic_ForInformationAboutQueue");

                queueManagerMultyApi.editConfigMainSettingsMqAPI(cookies, baseUrl(), managerName1, true, true, false);
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.virtual, nameVirtualQueueFrom, nameVirtualQueueTo, nameVirtualTopicTo);
                staticApi.switchModuleAPI(cook1, Base.baseUrl(), "factor-qms", "deactivate");
                staticApi.switchModuleAPI(cook1, Base.baseUrl(), "factor-qms", "activate");
//                queueManagerApi.restartMqAPI(cookies);
//                sleep(5000);

                basePage.click(basePage.sendMessageTab);
                basePage.click(messagePage.moduleSelect);
                basePage.valAndSelectElement(messagePage.moduleInput, "Мультименеджер очередей");
                basePage.click(messagePage.mqSelect);
                basePage.valAndSelectElement(messagePage.mqInput, managerName1);
                basePage.click(messagePage.addresseSelect);
                basePage.valAndSelectElement(messagePage.addresseInput, nameVirtualQueueFrom);
                basePage.click(messagePage.kindOfContentMessageSelect);
                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
                basePage.val(messagePage.messageTextAria, messagePage.messageWithAllSimbols);
                messagePage.fillInAdditionalFields(MessagePage.Persistant.Yes, numberSendMessage, corellationIdMessage, queueForReply, typeJmsMessage, groupIdMessage, numberInGroupMessage, valuePriorityMessage, validityMessage, initialDelay, timeToResendMessage, headerOfCounerMessage, numberOfResendsMessage, cronMessage, nameFileMessage);
                basePage.click(messagePage.sendMessageButton);

//                queueManagerMultyPage = new QueueManagerMultyPage();
//                queueManagerMultyPage.queueShouldHaveDepth(managerName1, nameVirtualQueueTo, 0);
                queueManagerMultyPage.queueShouldNotExist(managerName1, nameVirtualQueueTo);
                sleep(Integer.parseInt(initialDelay));
//                refresh();
                queueManagerMultyPage.queueShouldHaveDepth(managerName1, nameVirtualQueueTo, Integer.parseInt(numberSendMessage));

                queueManagerMultyPage.contentSpecificQueue(managerName1, nameVirtualQueueTo);
                specificQueuePage = new SpecificQueuePage("");
                specificQueuePage.saveParametersMessageInTable();
                specificQueuePage.messageCheckAllParameters(corellationIdMessage, MessagePage.Persistant.Yes, valuePriorityMessage,
                        redirectedMessage, "1418", typeJmsMessage, nameFileMessage);
                specificQueuePage.openFirstMessage();
                specificMessagePage = new SpecificMessagePage("");
                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable, specificQueuePage.valueIdMessageInTable, specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage, specificQueuePage.valuePersistenceMessageInTable, queueForReply, numberInGroupMessage, validityMessage, specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage, specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable,
                        specificQueuePage.valueTypeMessageInTable, messagePage.messageWithAllSimbols);

                queueManagerMultyPage = new QueueManagerMultyPage();
                sleep(Integer.parseInt(validityMessage) * 1000);
                refresh();
                queueManagerMultyPage.queueShouldHaveDepth(managerName1, nameVirtualQueueTo, 0);
                break;
            case ("Очередь сегментации"):
                groupIdMessage = "";
                queueForReply = "";
                numberInGroupMessage = "0";
                validityMessage = "0";
                validityMessage = "";
                classMessage = "ActiveMQBytesMessage";
                sizeBodyMessage = "2688";
                String fileName = "fileForMessage.txt";
                String nameSegmentQueueFrom = commonComponents.createIndividualName("SegmentFrom_ForInformationAboutQueue");
                String nameAgregateQueue = commonComponents.createIndividualName("AgregateFrom_ForInformationAboutQueue");
//                String nameAgregateQueueTo = commonComponents.createIndividualName("AgregateTo_ForInformationAboutQueue");
                nameLocalQueueTo = commonComponents.createIndividualName("AggregateTo_ForInformationAboutQueue");
                String textInFile = "Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово ";

                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.fragment, nameSegmentQueueFrom, nameAgregateQueue, "");
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.aggregate, nameAgregateQueue, nameLocalQueueTo, "");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.aggregate, nameAggregateQueueTo, nameLocalQueueTo);
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.fragment, nameSegmentQueueFrom, nameAggregateQueueTo);
                basePage.click(basePage.sendMessageTab);
                basePage.click(messagePage.moduleSelect);
                basePage.valAndSelectElement(messagePage.moduleInput, "Мультименеджер очередей");
                basePage.click(messagePage.mqSelect);
                basePage.valAndSelectElement(messagePage.mqInput, managerName1);
                basePage.click(messagePage.addresseSelect);
                basePage.valAndSelectElement(messagePage.addresseInput, nameSegmentQueueFrom);
                basePage.click(messagePage.kindOfContentMessageSelect);
                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
                messagePage.fileAria.sendKeys("/share/files/" + fileName);
                sleep(5000);
                basePage.click(messagePage.sendMessageButton);

                queueManagerMultyPage = new QueueManagerMultyPage();
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameSegmentQueueFrom, "0", "1", "1", "1", "Сегментация", "", "");
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameAgregateQueue, "0", "1", "27", "27", "Агрегация", "", "");
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameLocalQueueTo, "1", "0", "1", "0", "Локальная", "", "");

                queueManagerMultyPage = new QueueManagerMultyPage();
                queueManagerMultyPage.contentSpecificQueue(managerName1, nameLocalQueueTo);
                specificQueuePage = new SpecificQueuePage("");
                specificQueuePage.messageCheckAllParameters("", MessagePage.Persistant.No,
                        "4", "Нет", "3712", "", fileName);
                specificQueuePage.saveParametersMessageInTable();
                specificQueuePage.openFirstMessage();
                specificMessagePage = new SpecificMessagePage("");
                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable,
                        specificQueuePage.valueIdMessageInTable, specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage,
                        specificQueuePage.valuePersistenceMessageInTable, queueForReply, numberInGroupMessage, validityMessage,
                        specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage,
                        specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable,
                        specificQueuePage.valueTypeMessageInTable,
                        textInFile);
                break;
            case ("Очередь агрегации"):
                numberOfMessage = "3";
                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.GENERATE, "785000"};
                parametersOfSent = new Object[]{numberOfMessage};
                nameAgregateQueue = commonComponents.createIndividualName("AgregateFrom_ForInformationAboutQueue");
                nameLocalQueueTo = commonComponents.createIndividualName("AggregateTo_ForInformationAboutQueue");
                queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cookies, managerName1, QueueManagerMultyApi.typeQueue.aggregate, nameAgregateQueue, nameLocalQueueTo, "");
                messagePage.sendMessageThroughForm(nameAgregateQueue, managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);
                sleep(1000);
                queueManagerMultyApi.queueCheckAllParametersAPI(cookies, baseUrl(), managerName1, nameAgregateQueue, 0, 1, 0, Integer.parseInt(numberOfMessage), Integer.parseInt(numberOfMessage), null, "{queue=" + nameAgregateQueue + ", target=" + nameLocalQueueTo + "}", null, null);
                queueManagerMultyApi.queueCheckAllParametersAPI(cookies, baseUrl(), managerName1, "TEMP.AGGREGATION.", Integer.parseInt(numberOfMessage), 0, 0, Integer.parseInt(numberOfMessage), 0, null, null, null, null);
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, "TEMP.AGGREGATION.", "minMessageSize", "786024", baseUrl());
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, "TEMP.AGGREGATION.", "maxMessageSize", "786024", baseUrl());
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, "TEMP.AGGREGATION.", "memoryUsageByteCount", "2358072", baseUrl());
                break;
            case ("Транспортная очередь"):
                String url = "fesb-test-9-2";
                numberOfMessage = "100";
                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.GENERATE_IN_RANGE, "666|50|-777|10"};
                parametersOfSent = new Object[]{numberOfMessage};
                String nameTransportQueue = commonComponents.createIndividualName("Transport_ForInformationAboutQueue");
                String nameTransportTopic = commonComponents.createIndividualName("Transport_ForInformationAboutTopic");
                String nameCanal = commonComponents.createIndividualName("Canal_ForInformationAboutTransportQueue");
                String userName = managerName1;
                String userPassword = managerName1;
                String userGroups = "[\"read\", \"admin\", \"write\"]";
                String recieverName = managerName1;
                String recieverProtocol = "tcp";
                String recieverAddress = "0.0.0.0";
                String recieverPort = "61612";

                queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
                queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
                queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
                queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);
                queueManagerMultyApi.createRecieverAPI(cook2, baseUrl_2(), managerName2, recieverName, recieverProtocol, recieverAddress, recieverPort);
                queueManagerMultyApi.createChannelAPI(cook1, managerName1, QueueManagerMultyApi.typeQueue.TRANSPORT, nameTransportQueue, nameTransportTopic, nameCanal, false, url, recieverPort, userName, userPassword);
                sleep(5000);
                messagePage.sendMessageThroughForm(nameTransportQueue, managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);
                sleep(5000);
                queueManagerMultyApi.queueCheckAllParametersAPI(cookies, baseUrl(), managerName1, nameTransportQueue, 0, 1, 0, 60, 60, null, null, "name=" + nameCanal, null);
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, nameTransportQueue, "minMessageSize", "1690", baseUrl());
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, nameTransportQueue, "maxMessageSize", "1801", baseUrl());
                queueManagerMultyApi.queueCheckSpecificParameterAPI(cookies, managerName1, nameTransportQueue, "memoryUsageByteCount", "0", baseUrl());
                break;
            default:
                throw new AssertionError("Тест пропущен для типа: " + typeQueue);
        }
    }

    @Test
    @Parameters(value = {
            "Записать событие в СОПС | Очередь для перехвата сообщений СОПС | null | СОПС для перехвата",
//            "Записать событие в монитор транзакций | Очередь для перехвата сообщений монитор | null | null",
            "Записать событие в очередь | Очередь для перехвата сообщений очередь | Очередь для перехвата сообщений очередь | null",
            "Записать событие в файл | Очередь для перехвата сообщений файл | null | fileForEnterception",
    })
    public void eventInterception(String typeInterception, String nameQueue1, String nameQueue2, String nameAcceptor) {
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String message = "Тестируем";
        ArrayList<String> listQueue = new ArrayList<>();
        Object[] parametersOfMessage;
        Object[] parametersOfSent;
        listQueue.add(nameQueue1);
        if (nameQueue2 != null) listQueue.add(nameQueue2);
        System.out.println(listQueue);

        String managerName1 = "eventInterception";
//        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
//        queueManagerMultyApi.editConfigAutorisationSettingsMqAPI(cook1, managerName1, true, false, "anonymous", "anonymous", false);
//        queueManagerMultyApi.createQueueLocalAPI(cookies, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, nameQueue1);
        switch (typeInterception) {
            case "Записать событие в СОПС":
                String nameDomain = "Домен для перехвата событий Multy";
                String domainID = sopsApi.createDomainAPI(Cook, nameDomain);
                sopsApi.startDomainAPI(Cook, domainID);
//                queueManagerApi.createQueueLocalAPI(Cook, QueueManagerApi.typeQueue.local, typeInterception, baseUrl());
                creationSOPSPage.createSOPSWithLinkToSopsAndLocalQueue(nameDomain, "Мультименеджер очередей", managerName1, nameAcceptor, nameAcceptor, nameQueue1, null, null);
                eventInterceptionPage.createEventInterception("Мультименеджер очередей", managerName1, typeInterception, listQueue, nameAcceptor, "");
                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);

                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);

                Response response = sopsApi.getStatisticOfDomainAPI(Cook, baseUrl(), domainID);
                Assert.assertEquals("[1]", response.path("processedQty").toString());
                SOPSPage sopsPage = new SOPSPage();
                open(baseUrl());
                sopsPage.goToDomain(nameDomain);
                refresh();
                sleep(3000);
                queueManagerMultyApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, managerName1, typeInterception.replace("в ", "в."), baseUrl());
                sopsPage.sopsCheckAllParameters(nameAcceptor, "Ссылка на СОПС: " + nameAcceptor, 3);
//                response = sopsApi.getStatisticOfDomainAPI(Cook, baseUrl(), domainID);
//                Assert.assertEquals("[3]", response.path("processedQty").toString());
                break;
            case "Записать событие в монитор транзакций":
                ArrayList<String> trueInfoAboutEvent1 = new ArrayList<>();
                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-PUT");
                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-EXPIRE");
                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-GET");
//                String[] queueName = {"Записать событие в.>"};
//                String policyType = "Очередь";
//                Enum[] interceptCheckBox = {PolicyDeliveryPage.receiveEvent.Yes, PolicyDeliveryPage.sendEvent.Yes, PolicyDeliveryPage.removeEvent.Yes, PolicyDeliveryPage.fastSenderEvent.Yes};
//                policyDeliveryPage.createPoliceDelivery("Мультименеджер очередей", managerName1, policyType, queueName, interceptCheckBox);

                eventInterceptionPage.createEventInterception("Мультименеджер очередей", managerName1, typeInterception, listQueue, nameAcceptor, "");
                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);

                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);

                sleep(1500);
                refresh();
                queueManagerMultyApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, managerName1, typeInterception.replace("в ", "в."), baseUrl());
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.checkTransactionMonitor(trueInfoAboutEvent1);
                break;

            case "Записать событие в очередь":
//                queueManagerMultyApi.createQueueLocalAPI(Cook, baseUrl(),managerName1, QueueManagerMultyApi.typeQueue.local, typeInterception);
//                queueManagerMultyApi.createQueueLocalAPI(Cook, baseUrl(),managerName1, QueueManagerMultyApi.typeQueue.local, nameQueue2);
                eventInterceptionPage.createEventInterception("Мультименеджер очередей", managerName1, typeInterception, listQueue, nameQueue2, "");
                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);

                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);

                sleep(3000);
//                sleep(1500);
                queueManagerMultyApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, managerName1, typeInterception.replace("в ", "в."), baseUrl());
//                queueManagerMultyApi.queueCheckAllParametersAPI(Cook, nameQueue2, 3, 0, 0, 3, 0, "local", "null", baseUrl());
                queueManagerMultyPage.queueCheckAllParameters(managerName1, nameQueue2, "3", "0", "3", "0", "Локальная", "", "");
//                queueManagerMultyApi.queueCheckAllParametersAPI(Cook, baseUrl(), managerName1, nameQueue2, 3, 0, 0, 3, 0, null, null, null, null);
                break;

            case "Записать событие в файл":
                String path = "/home/fesb/Stand/share/upload/interceptingMulty.log";
                basePage.chekExistFile(BasePage.FileExist.No, path);
                eventInterceptionPage.createEventInterception("Мультименеджер очередей", managerName1, typeInterception, listQueue, nameAcceptor, "/fesb/tests/uploadMulty/interceptingMulty.log");
                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);

                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), managerName1, "Мультименеджер очередей", parametersOfMessage, parametersOfSent);

                sleep(1500);
                queueManagerMultyApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, managerName1, typeInterception.replace("в ", "в."), baseUrl());
                basePage.chekExistFile(BasePage.FileExist.Yes, path);
                break;
            default:
                throw new AssertionError("Пропущена подготовка к тесту");
        }
    }

    @Test
    public void createConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "TCP";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort = portForCreateConnectorMulty;
        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};

        final String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");

        String[] connectorStatisticBefore = {"|"};
        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};

        String managerName1 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "1";
        String managerName2 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);

        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyPage.successCreateConnector(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort, false, localQueueName, topicName, userName, userPassword);
        open(baseUrl());
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort, connectorStatisticAfter);

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 0, 0, null, null, channelName, null);
        try {
            queueManagerMultyApi.queueShouldNotExist(Cook1, baseUrl(), managerName1, localQueueName);
        } catch (NullPointerException e) {
            basePage.sout("Очередь не найдена и это хорошо");
        }

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, true, "1");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 1, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);
    }

    @Test
    public void cancelCreateConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "TCP";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort = portForCancelCreateConnectorMulty;
        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};

        String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");

        String managerName1 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "1";
        String managerName2 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyPage.cancelCreateConnector(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorName);
        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
        sleep(3000);
        refresh();
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorName);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort, false, localQueueName, topicName, userName, userPassword);

        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, true, "1");
        sleep(5000);

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 1, 0, 0, 1, 0, null, null, channelName, null);
        queueManagerMultyApi.queueShouldNotExist(Cook1, baseUrl(), managerName1, localQueueName);
    }

    @Test
    public void editConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorNameNew = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "tcp";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort1 = portForEditConnectorMulty1;
        String connectorPortNew = portForEditConnectorMulty2;
        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};

        final String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");
        String[] connectorStatisticBefore = {"null|null"};
        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};

        ArrayList<String> ipList = new ArrayList<>();
        ipList.add(channelIp);
        ArrayList<String> portList = new ArrayList<>();
        portList.add(connectorPortNew);

        String managerName1 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "1";
        String managerName2 = testName.getMethodName().split("\\|")[0].split("\\[")[0].replace("(", "_").replace(")", "_").trim() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyApi.createRecieverAPI(Cook1, baseUrl(), managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort1, false, localQueueName, topicName, userName, userPassword);
        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 1, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);

        queueManagerMultyPage.successEditConnector(managerName1, connectorName, connectorNameNew, connectorProtocol, connectorIp, connectorPortNew, connectorParametersNew);

        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorName);
        queueManagerMultyPage.connectorShouldNotHaveStatistic(managerName1, connectorNameNew);
        refresh();
        queueManagerMultyPage.connectorShouldNotHaveStatistic(managerName1, connectorNameNew);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 1, 0, 0, 2, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);

        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyPage.editChannel(managerName2, channelName, channelName, ipList, portList, localQueueName, "static", "NotDuplex");

        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");

        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorNameNew, connectorProtocol, connectorIp, connectorPortNew, connectorStatisticAfter);

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 3, 3, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 3, 0, 0, 3, 0, null, null, null, null);
    }

    @Test
    public void cancelEditConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorNameNew = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "tcp";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort1 = portForCancelEditConnectorMulty1;
        String connectorPort2 = getPortForCancelEditConnectorMulty2;
        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};

        final String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");
        String[] connectorStatisticBefore = {"null|null"};
        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};

        ArrayList<String> ipList = new ArrayList<>();
        ipList.add(channelIp);
        ArrayList<String> portList = new ArrayList<>();
        portList.add(connectorPort2);

        String managerName1 = testName.getMethodName() + "1";
        String managerName2 = testName.getMethodName() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyApi.createRecieverAPI(Cook1, baseUrl(), managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort1, false, localQueueName, topicName, userName, userPassword);
        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 1, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);

        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyPage.cancelEditConnector(managerName1, connectorName, connectorNameNew, connectorProtocol, connectorIp, connectorPort2, connectorParametersNew);
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorNameNew);
        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
        open(baseUrl());
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorNameNew);

        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 2, 2, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 2, 0, 0, 2, 0, null, null, null, null);
    }

    @Test
    public void deleteConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorNameNew = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "tcp";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort1 = portForDeleteConnectorMulty;
        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};

        String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");

        ArrayList<String> ipList = new ArrayList<>();
        ipList.add(channelIp);

        String managerName1 = testName.getMethodName() + "1";
        String managerName2 = testName.getMethodName() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyApi.createRecieverAPI(Cook1, baseUrl(), managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort1, false, localQueueName, topicName, userName, userPassword);
        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 1, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);

        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyPage.successDeleteConnector(managerName1, connectorName);
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorName);
        refresh();
        queueManagerMultyPage.connectorShouldNotExist(managerName1, connectorName);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 1, 0, 0, 2, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);
    }

    @Test
    public void cancelDeleteConnector() {
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String connectorName = commonComponents.createIndividualName("connectorName");
        String connectorNameNew = commonComponents.createIndividualName("connectorName");
        String connectorProtocol = "tcp";
        String connectorIp = "0.0.0.0";
        String channelIp = baseUrl().split("//")[1].split(":")[0];
        String connectorPort1 = portForCancelDeleteConnectorMulty;
        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};

        final String channelName = commonComponents.createIndividualName("channelName");
        String channelProtocol = "tcp";
        String localQueueName = commonComponents.createIndividualName("localQueueName");
        String topicName = commonComponents.createIndividualName("topicName");
        String[] connectorStatisticBefore = {"null|null"};
        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};

        ArrayList<String> ipList = new ArrayList<>();
        ipList.add(channelIp);
        String managerName1 = testName.getMethodName() + "1";
        String managerName2 = testName.getMethodName() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        queueManagerMultyApi.createRecieverAPI(Cook1, baseUrl(), managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1);
        queueManagerMultyApi.createChanelAPI(Cook2, baseUrl_2(), managerName2, channelName, channelProtocol, channelIp, connectorPort1, false, localQueueName, topicName, userName, userPassword);
        sleep(5000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 1, 1, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 1, 0, 0, 1, 0, null, null, null, null);

        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        queueManagerMultyPage.cancelDeleteConnector(managerName1, connectorName);
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
        refresh();
        queueManagerMultyPage.connectorCheckAllParameters(managerName1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName2, localQueueName, "text", 1, false, "1");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName2, localQueueName, 0, 1, 0, 2, 2, null, null, channelName, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueName, 2, 0, 0, 2, 0, null, null, null, null);
    }

//    @Test
//    public void remoteAccessToMq() {
//        String localOueueName1 = testName.getMethodName() + commonComponents.createIndividualName("-");
//        String message = "pwd";
//        String cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        String managerName1 = testName.getMethodName() + "1";
//        String managerName2 = testName.getMethodName() + "2";
//        String userName = managerName1;
//        String userPassword = managerName1;
//        String userGroups = "[\"read\", \"admin\", \"write\"]";
//        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
//        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
//        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
//        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
//        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
//        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);
//
//        queueManagerMultyApi.createQueueLocalAPI(cook2, baseUrl_2(), managerName2, QueueManagerMultyApi.typeQueue.local, localOueueName1);
//        queueManagerMultyApi.sendMessgeInQueueAPI(cook2, baseUrl_2(), managerName2, localOueueName1, "text", 1, false, "1");
//        sleep(1000);
//        queueManagerMultyApi.queueShouldNotExist(cook1, managerName1, localOueueName1, baseUrl());
//        queueManagerPage.settingRemoteAccess("on", "tcp://fesb-test-3-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        try {
//            queueManagerPage.queueShouldHaveSpecificMessage(localOueueName1, message);
//            queueManagerPage.queueCheckAllParameters(localOueueName1, "1", "0", "1", "0", "Локальная", "");
//            queueManagerPage.settingRemoteAccess("off", "tcp://fesb-test-0-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        } catch (Exception e) {
//            queueManagerPage.settingRemoteAccess("off", "tcp://fesb-test-0-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        }
//    }

    @Test
    public void commandInterface() {
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        String urlDownload = "file:///home/selenium/Downloads/";
        String downloadFilePath = "/home/fesb/share/Downloaded/downloadedQueue.zip";
        String downloadedFileName = null;
        String connectorName = "testConnector";
        String topicName = "testTopic";
        String subscraberName = "testSubscriber";
        String channelName1 = "testChannel";
        String virtualQueueName1 = "testVirtualQueue1";
        String virtualQueueName2 = "testVirtualQueue2";
        String localQueueName1 = "testlocalQueueName1";
        String connectorPort1 = portForcommandInterfaceMulty1;
        String connectorPort2 = "61617";

        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};

        String cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String managerName1 = testName.getMethodName() + "1";
        String managerName2 = testName.getMethodName() + "2";
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createUserAPI(cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
        queueManagerMultyApi.createManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook2, baseUrl_2(), managerName2);
        queueManagerMultyApi.createUserAPI(cook2, baseUrl_2(), managerName2, userName, userPassword, userGroups);

        try {
            String responseListOfConnectors = queueManagerMultyApi.getListOfConnectorAPI(cook2, managerName2, baseUrl_2()).asString();
            basePage.compareStringAndStringShouldContainsText(responseListOfConnectors, connectorName);
            throw new AssertionError("Найден несуществующий коннектор");
        } catch (AssertionError e) {
            System.out.println("Коннектор не найден и это хорошо");
        }
        try {
            String responseListOfTopic = queueManagerMultyApi.getListOfTopicAPI(cook2, managerName2, baseUrl_2()).asString();
            basePage.compareStringAndStringShouldContainsText(responseListOfTopic, topicName);
            throw new AssertionError("Найден несуществующий раздел");
        } catch (AssertionError e) {
            System.out.println("Раздел не найден и это хорошо");
        }
        try {
            String responseListOfSubscriber = queueManagerMultyApi.getListOfSubscriberAPI(cook2, managerName2, baseUrl_2()).asString();
            basePage.compareStringAndStringShouldContainsText(responseListOfSubscriber, subscraberName);
            throw new AssertionError("Найден несуществующий подписчик");
        } catch (AssertionError e) {
            System.out.println("Подписчик не найден и это хорошо");
        }
        queueManagerMultyApi.queueShouldNotExist(cook2, baseUrl_2(), managerName2, virtualQueueName1);
        queueManagerMultyApi.queueShouldNotExist(cook2, baseUrl_2(), managerName2, virtualQueueName2);
        queueManagerMultyApi.queueShouldNotExist(cook2, baseUrl_2(), managerName2, localQueueName1);
        try {
            String responseListOfChannel = queueManagerMultyApi.getListOfChannelAPI(cook2, managerName2, baseUrl_2()).asString();
            basePage.compareStringAndStringShouldContainsText(responseListOfChannel, channelName1);
            throw new AssertionError("Найден несуществующий канал");
        } catch (AssertionError e) {
            System.out.println("Канал не найден и это хорошо");
        }
        queueManagerMultyApi.createRecieverAPI(cook1, baseUrl(), managerName1, connectorName, "tcp", "fesb-test-9-1", connectorPort1);
        queueManagerMultyApi.createTopicAPI(cook1, managerName1, topicName);
        queueManagerMultyApi.createSubscraberAPI(cook1, managerName1, topicName, subscraberName, "idDescriber");
        String virtualQueueFrom = commonComponents.createIndividualName("VirtFrom_ForCom");
        String nameVirtualQueueTo = commonComponents.createIndividualName("VirtTo_ForCom");
        String nameVirtualQueueTopicTo = commonComponents.createIndividualName("VirtTopicTo_ForCom");
        queueManagerMultyApi.createQueueVirtualSegmentationAggregationAPI(cook1, managerName1, QueueManagerMultyApi.typeQueue.virtual, virtualQueueFrom, nameVirtualQueueTo, nameVirtualQueueTopicTo);

        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localQueueName1);
        queueManagerMultyApi.createChanelAPI(cook1, baseUrl(), managerName1, channelName1, "TCP", "fesb-test-9-1", connectorPort1, false, localQueueName1, topicName, userName, userPassword);

        queueManagerMultyPage.exportInFile(managerName1);
        sleep(2000);
        open(urlDownload);
        downloadedFileName = queueManagerMultyPage.downloadedFileName.getText();
        basePage.compareElementTextShouldContainsText("factor-mq-dump-", queueManagerMultyPage.downloadedFileName);
        queueManagerMultyPage.checkContentDowloadedFile(sessionId.toString(), "factor-mq-dump-", "экспорт менеджера", downloadFilePath, sessionId, downloadedFileName, downloadFilePath);
        String command = queueManagerMultyPage.exportedMQ.replace("fesb-test-9-1", "fesb-test-9-2").replace("\n", "\\n").replace(connectorPort1, connectorPort2);
        basePage.openPage(baseUrl_2());

        queueManagerMultyPage.executeCommand(managerName2, command);
        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(cook2, baseUrl_2(), managerName2, virtualQueueFrom, "", 1, true, "1");

        queueManagerMultyPage.queueCheckAllParameters(managerName2, virtualQueueFrom, "-", "2", "-", "-", "Виртуальная", "", nameVirtualQueueTo + ", ...");
        queueManagerMultyPage.queueCheckAllParameters(managerName2, nameVirtualQueueTo, "1", "0", "1", "0", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName2, localQueueName1, "0", "0", "0", "0", "Транспортная", "testChannel", "");

        queueManagerMultyPage.topicCheckAllParameters(managerName2, topicName, "1", "0", "0", "Транспортный", "testChannel", "");
        queueManagerMultyPage.topicCheckAllParameters(managerName2, nameVirtualQueueTopicTo, "0", "1", "0", "Локальный", "", "");

        String responseListOfSubscriber = queueManagerMultyApi.getListOfSubscriberAPI(cook2, managerName2, baseUrl_2()).asString();
        basePage.compareStringAndString("[{\"active\":false,\"clientId\":\"idDescriber\",\"name\":\"" + subscraberName + "\",\"topic\":\"testTopic\",\"selector\":null,\"connectionId\":\"NOTSET\",\"pendingQueueSize\":0,\"dispatchedQueueSize\":0,\"dispatchedCounter\":0}]", responseListOfSubscriber);

        String responseListOfConnectors = queueManagerMultyApi.getListOfConnectorAPI(cook2, managerName2, baseUrl_2()).asString();
        basePage.compareStringAndStringShouldContainsText(responseListOfConnectors, connectorName);

        String responseListOfChannel = queueManagerMultyApi.getListOfChannelAPI(cook2, managerName2, baseUrl_2()).asString();
        basePage.compareStringAndStringShouldContainsText(responseListOfChannel, channelName1);
    }
}