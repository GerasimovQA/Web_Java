//package ru.factorts.page;
//
//
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import io.qameta.allure.Description;
//import io.restassured.response.Response;
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//import org.junit.*;
//import org.junit.rules.TestName;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//import org.openqa.selenium.InvalidSelectorException;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.SessionId;
//import utils.ConfigProperties;
//
//import java.util.ArrayList;
//
//import static com.codeborne.selenide.CollectionCondition.size;
//import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.WebDriverRunner.*;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(JUnitParamsRunner.class)
//public class N3_QueueManagerSingleTest extends Base {
//    String numberSendMessage = null;
//    String corellationIdMessage = null;
//    String queueForReply = null;
//    String typeJmsMessage = null;
//    String groupIdMessage = null;
//    String numberInGroupMessage = null;
//    String valuePriorityMessage = null;
//    String validityMessage = null;
//    String initialDelay = null;
//    String timeToResendMessage = null;
//    String headerOfCounerMessage = null;
//    String numberOfResendsMessage = null;
//    String cronMessage = null;
//    String nameFileMessage = null;
//    String classMessage = null;
//    String sizeBodyMessage = null;
//    String redirectedMessage = null;
//
//    String Cook1 = null;
//    String Cook2 = null;
//    String connectorID = null;
//
//    @Rule
//    public TestName testName = new TestName();
//
//    private QueueManagerPage queueManagerPage;
//    private QueuesPage queuesPage;
//    private SpecificMessagePage specificMessagePage;
//    private SpecificQueuePage specificQueuePage;
//    private MessagePage messagePage = new MessagePage();
//    private TopicsPage topicsPage;
//    private SOPSPage sopsPage;
//    private CreationSOPSPage creationSOPSPage = new CreationSOPSPage("Empty");
//    private SpecifecTopicPage specifecTopicPage;
//    IndexPage indexPage;
//    BasePage basePage = new BasePage();
//    static BasePage staticBasePage = new BasePage();
//    CommonComponents commonComponents = new CommonComponents();
//    Api api = new Api();
//    static Api staticApi = new Api();
//    QueueManagerApi queueManagerApi = new QueueManagerApi();
//    SOPSApi sopsApi = new SOPSApi();
//    EventInterceptionPage eventInterceptionPage = new EventInterceptionPage();
//    SettingsPage settingsPage;
//    static SettingsPage staticSettingsPage;
//    static PolicyDeliveryPage policyDeliveryPage = new PolicyDeliveryPage();
//
//    private static final String QUEUE_NAME = "TEST_QUEUE",
//            VIRTUAL_QUEUE_NAME = "VIRTUAL_QUEUE",
//            CANAL_NAME = "TEST_CANAL",
//            DOMAIN_NAME = "TEST_DOMAIN",
//            SOPS_NAME = "TEST_SOPS",
//            LOCAL_QUEUE_IN = "TEST_QUEUE_IN",
//            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT";
//
//    @BeforeClass
//    public static void beforeClass() {
//        String[] queueName = {"Записать событие в.>"};
//        String policyType = "Очередь";
//        Enum[] interceptCheckBox = {PolicyDeliveryPage.receiveEvent.Yes, PolicyDeliveryPage.sendEvent.Yes, PolicyDeliveryPage.removeEvent.Yes, PolicyDeliveryPage.fastSenderEvent.Yes};
//        Configuration.browserCapabilities.setCapability("name", getClassName());
//        staticBasePage.openPage(baseUrl());
//        staticSettingsPage = new SettingsPage();
//        staticSettingsPage.turnOnModule("Монитор транзакций");
//        policyDeliveryPage.createPoliceDelivery("Менеджер очередей", "", policyType, queueName, interceptCheckBox);
//        Selenide.closeWebDriver();
//    }
//
//    @Before
//    public void setUpQueue() {
//        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
//        basePage.openWithLog("/manager/");
//        basePage.openPage(baseUrl());
//        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        logs = WebDriverRunner.getWebDriver().manage().logs();
//
//        if ($x(".//aside").waitUntil(Condition.enabled, 2000).getAttribute("class").equals("main_sidebar sidebar_close")) {
//            $x(".//i[@class='fa fa-bars']").click();
//            new IndexPage().queueManagerPage();
//        }
//    }
//
//    @After
//    public void afterTest() {
//        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        writeLogsOfBrowser(getClassName(), testName.getMethodName());
//        Selenide.closeWebDriver();
//    }
//
//    @AfterClass
//    public static void afterClass() {
//        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "false|false", "true|true", "true|true", "false|false", "false|false", "true|true", "true|true", "true|true", "false|false");
//
//        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
//        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
//            staticBasePage.executeBashCommand("sudo docker stop fesb-test-3-1");
//            staticBasePage.executeBashCommand("sudo docker stop fesb-test-3-2");
//        }
//        try {
//            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        } catch (IllegalStateException | InvalidSelectorException e) {
//            System.out.println("Браузер не запущен");
//        }
//    }
//
//    /**
//     * Check redirection from virtual to local queue
//     *
//     * @throws Exception
//     */
//    @Description("Check redirection from virtual to local queue")
//    @Test
//    public void redirectionFromVirtualToLocalQueue() {
//
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName1 = commonComponents.createIndividualName(QUEUE_NAME);
//        String localOueueName2 = commonComponents.createIndividualName(QUEUE_NAME);
//        String virtualQueueName = commonComponents.createIndividualName(VIRTUAL_QUEUE_NAME);
//        queueManagerPage.createVirtualQueue(virtualQueueName, localOueueName1, localOueueName2);
//        commonComponents.closeNotificationIfExist();
//        queueManagerPage.sendNSavedMessagesWithoutCheck(virtualQueueName, 3);
//        queueManagerPage.sucessDeleteQueue(virtualQueueName);
//        queueManagerPage.queueShouldHaveNMessagesInDepth(localOueueName1, 3);
//        queueManagerPage.queueShouldHaveNMessagesInDepth(localOueueName2, 3);
//        String[] queueArrray = {localOueueName1, localOueueName2};
//        for (String name : queueArrray) {
//            queueManagerPage.sucessDeleteQueue(name);
//        }
//    }
//
//    @Description("Create Check And Delete Transport Queue")
//    @Test
//    @Parameters(value = {
//            "NotDuplex",
//            "Duplex",
//    })
//    public void createCheckAndDeleteTransportQueue(String TypeCanal) {
//        ArrayList<String> ipList = new ArrayList<>();
//        ArrayList<String> portList = new ArrayList<>();
//        queueManagerPage = new QueueManagerPage();
////        queueManagerPage.сanalsList();
//        String canalName = commonComponents.createIndividualName(CANAL_NAME);
//        String transportQueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        String localQueueName = transportQueueName;
//        ipList.add(baseUrl().split("//")[1].split(":")[0]);
//        portList.add("61617");
//        String port = ":9932";
//
//        int messagesNumber = 4;
//        queueManagerPage.createChannel(canalName, ipList, portList, transportQueueName, "static", TypeCanal);
//        queueManagerPage.channelCheckAllParameters(canalName, ipList, portList, transportQueueName, "static", TypeCanal);
//        queueManagerPage.queuesList();
//        queueManagerPage.queueCheckAllParameters(transportQueueName, "0", "1", "0", "0", "Транспортная", canalName);
//
//        switch (TypeCanal) {
//            case ("NotDuplex"):
//                queueManagerPage.sendNSavedMessages(transportQueueName, messagesNumber);
//                break;
//            case ("Duplex"):
//                basePage.contextClick(queueManagerPage.afterSearchQueueRow);
//                basePage.click(queueManagerPage.contextSendMessage);
//                messagePage = new MessagePage();
//                messagePage.sendMessageInQueueNTimesAtOnceWithoutText(transportQueueName, messagesNumber);
//                queueManagerPage = new QueueManagerPage();
//                basePage.click(queueManagerPage.queuesList);
//                queuesPage = new QueuesPage();
//                break;
//            default:
//                throw new AssertionError("Пропущена отправка сообщения в транспортную очередь");
//        }
//        queueManagerPage.queueCheckAllParameters(transportQueueName, "0", "1",
//                Integer.toString(messagesNumber), Integer.toString(messagesNumber), "Транспортная", canalName);
//
//        open("http://" + ipList.get(0) + port);
//        loginPage.loginClickButton("root", "root");
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//
//        switch (TypeCanal) {
//            case ("NotDuplex"):
//                queueManagerPage.queueCheckAllParameters(localQueueName, Integer.toString(messagesNumber), "0",
//                        Integer.toString(messagesNumber), "0", "Локальная", "");
//                break;
//            case ("Duplex"):
//                queueManagerPage.queueCheckAllParameters(localQueueName, Integer.toString(messagesNumber), "1",
//                        Integer.toString(messagesNumber), "0", "Локальная", "");
//                break;
//            default:
//                throw new AssertionError("Пропушена проверка параметров локальной очереди на удаленной машине");
//        }
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
//    }
//
//    @Test
//    @Parameters(value = {
//            "Локальная очередь",
//            "Виртуальная очередь",
//            "Очередь сегментации",
//            "Очередь агрегации",
//            "Транспортная очередь",
//    })
//    public void propertiesAndMessgesOfQueue(String typeQueue) {
//        String url = "fesb-test-3-2:61616";
//        String text = "test message";
//        int sumMessages = 2;
//
//        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String cookies2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//
//        switch (typeQueue) {
//            case ("Локальная очередь"):
//                String nameLocalQueue = commonComponents.createIndividualName("Local_ForInformationAboutQueue");
//                queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue, baseUrl());
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameLocalQueue, text, sumMessages);
//                queueManagerPage.queueCheckAllParameters(nameLocalQueue, Integer.toString(sumMessages), "0",
//                        Integer.toString(sumMessages), "0", "Локальная", "");
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameLocalQueue, typeQueue);
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameLocalQueue, text);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
////                queueManagerPage.checkActiveCustomersQueue(nameLocalQueue);
////                queueManagerPage.checkActiveProducersQueue(nameLocalQueue);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                break;
//
//            case ("Виртуальная очередь"):
//                String nameVirtualQueueFrom = commonComponents.createIndividualName("VirtualFrom_ForInformationAboutQueue");
//                String nameVirtualQueueTo = commonComponents.createIndividualName("VirtualTo_ForInformationAboutQueue");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.virtual, nameVirtualQueueFrom, nameVirtualQueueTo);
//                queueManagerApi.restartMqAPI(cookies);
//                sleep(5000);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameVirtualQueueFrom, text, sumMessages);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameVirtualQueueTo, text, sumMessages);
//                queueManagerPage.queueCheckAllParameters(nameVirtualQueueFrom, "-", "1",
//                        "-", "-", "Виртуальная", nameVirtualQueueTo);
//                queueManagerPage.queueCheckAllParameters(nameVirtualQueueTo, Integer.toString(sumMessages + sumMessages),
//                        "0", Integer.toString(sumMessages + sumMessages), "0", "Локальная", "");
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameVirtualQueueFrom, typeQueue);
////                queueManagerPage.checkMessagesQueueInTableAndProperties(nameVirtualQueueFrom, text);
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameVirtualQueueTo, "Локальная очередь");
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameVirtualQueueTo, text);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
////                queueManagerPage.checkActiveCustomersQueue(nameVirtualQueueFrom);
////                queueManagerPage.checkActiveCustomersQueue(nameVirtualQueueTo);
////                queueManagerPage.checkActiveProducersQueue(nameVirtualQueueFrom);
////                queueManagerPage.checkActiveProducersQueue(nameVirtualQueueTo);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                break;
//
//            case ("Очередь сегментации"):
//                String nameSegmentQueueFrom = commonComponents.createIndividualName("SegmentFrom_ForInformationAboutQueue");
//                String nameSegmentQueueTo = commonComponents.createIndividualName("SegmentTo_ForInformationAboutQueue");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.fragment, nameSegmentQueueFrom, nameSegmentQueueTo);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameSegmentQueueFrom, text, sumMessages);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameSegmentQueueTo, text, sumMessages);
//                queueManagerPage.queueCheckAllParameters(nameSegmentQueueFrom, "0", "1",
//                        Integer.toString(sumMessages), Integer.toString(sumMessages), "Сегментация", "");
//                queueManagerPage.queueCheckAllParameters(nameSegmentQueueTo, Integer.toString(sumMessages + sumMessages),
//                        "0", Integer.toString(sumMessages + sumMessages), "0", "Локальная", "");
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameSegmentQueueFrom, typeQueue);
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameSegmentQueueTo, "Локальная очередь");
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameSegmentQueueFrom, text);
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameSegmentQueueTo, text);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
////                queueManagerPage.checkActiveCustomersQueue(nameSegmentQueueFrom);
////                queueManagerPage.checkActiveCustomersQueue(nameSegmentQueueTo);
////                queueManagerPage.checkActiveProducersQueue(nameSegmentQueueFrom);
////                queueManagerPage.checkActiveProducersQueue(nameSegmentQueueTo);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                break;
//            case ("Очередь агрегации"):
//                String nameAgregateQueueFrom = commonComponents.createIndividualName("AgregateFrom_ForInformationAboutQueue");
//                String nameAgregateQueueTo = commonComponents.createIndividualName("AgregateTo_ForInformationAboutQueue");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.aggregate, nameAgregateQueueFrom, nameAgregateQueueTo);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameAgregateQueueFrom, text, sumMessages);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameAgregateQueueTo, text, sumMessages);
//                sleep(2000);
//                queueManagerPage.queueCheckAllParameters(nameAgregateQueueFrom, "0", "1",
//                        Integer.toString(sumMessages), Integer.toString(sumMessages), "Агрегация", "");
//                queueManagerPage.queueCheckAllParameters(nameAgregateQueueTo, Integer.toString(sumMessages), "0",
//                        Integer.toString(sumMessages), "0", "Локальная", "");
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameAgregateQueueFrom, typeQueue);
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameAgregateQueueTo, "Локальная очередь");
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameAgregateQueueFrom, text);
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameAgregateQueueTo, text);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
////                queueManagerPage.checkActiveCustomersQueue(nameAgregateQueueFrom);
////                queueManagerPage.checkActiveCustomersQueue(nameAgregateQueueTo);
////                queueManagerPage.checkActiveProducersQueue(nameAgregateQueueFrom);
////                queueManagerPage.checkActiveProducersQueue(nameAgregateQueueTo);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//                break;
//            case ("Транспортная очередь"):
//                String nameTransportQueue = commonComponents.createIndividualName("Transport_ForInformationAboutQueue");
//                String nameCanal = commonComponents.createIndividualName("Canal_ForInformationAboutTransportQueue");
//                queueManagerApi.createQueueTransportAPI(cookies, QueueManagerApi.typeQueue.TRANSPORT, nameTransportQueue, nameCanal, url);
//                sleep(5000);
//                queueManagerApi.sendMessgeInQueueAPI(cookies, nameTransportQueue, text, sumMessages);
//                queueManagerPage.queueCheckAllParameters(nameTransportQueue, "0", "1",
//                        Integer.toString(sumMessages), Integer.toString(sumMessages), "Транспортная", nameCanal);
//                queueManagerPage.checkParametersQueueInTableAndProperties(nameTransportQueue, typeQueue);
//                queueManagerPage.checkMessagesQueueInTableAndProperties(nameTransportQueue, text);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
////                queueManagerPage.checkActiveCustomersQueue(nameTransportQueue);
////                queueManagerPage.checkActiveProducersQueue(nameTransportQueue);
//                /* Раскомментировать после исправления бага с отображением производителей и потребителей*/
//
//                queueManagerApi.queueCheckAllParametersAPI(cookies2, nameTransportQueue, 3, 0, 0, 3, 0, "local", "null", baseUrl_2());
//                break;
//            default:
//                throw new AssertionError("Тест пропущен для типа: " + typeQueue);
//        }
//    }
//
//    @Test
//    public void queueActionInformation() {
//        String text = "test message";
//        int sumMessages = 2;
//        String nameLocalQueue = commonComponents.createIndividualName("Local_ForInformationAboutQueue");
//        String nameLocalQueue2 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_Change");
//        String nameLocalQueue3 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_Change2");
//        String nameLocalQueue4 = commonComponents.createIndividualName("Local_ForInformationAboutQueue_import");
//        String cookies = api.loginAPI(BUrl, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue, baseUrl());
//        queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue2, baseUrl());
//        queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue3, baseUrl());
//        queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue4, baseUrl());
//
//        queueManagerPage = new QueueManagerPage();
//        if (!queueManagerPage.queuesList.attr("class").contains("active")) basePage.click(queueManagerPage.queuesList);
//        queueManagerPage.searchQueue(nameLocalQueue);
//        basePage.autoUpdateOff();
//        String valueNameQueueInTable = queueManagerPage.afterSearchQueueName.getText().trim();
//        basePage.click(queueManagerPage.afterSearchQueueName);
//        Assert.assertEquals(valueNameQueueInTable, queueManagerPage.nameChoosenQueueList.getText());
//
//
//        /*Отправка сообщений со страницы информации об очереди*/
//        specificQueuePage = new SpecificQueuePage();
//        basePage.click(specificQueuePage.sentMessageButton);
//        messagePage.sendMessageInQueueNTimesAtOnce("Менеджер очередей", "", nameLocalQueue, text, sumMessages);
//        back();
//
//        /*Перехватчик событий*/
//        specificQueuePage = new SpecificQueuePage();
//        basePage.click(specificQueuePage.actionButton);
//        basePage.click(specificQueuePage.eventInterceptorButton);
//        Assert.assertTrue(url().contains("/manager/#/queue-manager/configuration/capture"));
//        specificQueuePage.addEventInterceptorButton.shouldBe(Condition.visible);
//        back();
//
//        /*Редактирование очереди*/
//        Assert.assertEquals(valueNameQueueInTable, queueManagerPage.nameChoosenQueueList.getText());
//        basePage.click(specificQueuePage.actionButton);
//        basePage.click(specificQueuePage.editButton);
//        queueManagerPage.headerInEditForm.shouldBe(Condition.visible);
//        Assert.assertEquals(nameLocalQueue, specificQueuePage.nameQueueInEditForm.getValue());
//        basePage.closeForm();
//
//        /*Экспорт очереди*/
//        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
//        String urlDownload = "file:///home/selenium/Downloads/";
//        String downloadFilePath = "/home/fesb/share/Downloaded/downloadedQueue.zip";
//        String downloadedFileName = null;
//        basePage.click(specificQueuePage.actionButton);
//        basePage.click(specificQueuePage.exportButton);
//        sleep(1000);
//        open(urlDownload);
//        downloadedFileName = queueManagerPage.downloadedFileName.getText();
//        basePage.compareElementTextShouldContainsText("messages", queueManagerPage.downloadedFileName);
//        queueManagerPage.checkContentDowloadedFile(sessionId.toString(), "messages-", "очередь", downloadFilePath, sessionId, downloadedFileName, downloadFilePath);
//        back();
//        back();
//
//        /*Импорт очереди*/
//        queueManagerPage.searchQueue(nameLocalQueue4);
//        basePage.click(queueManagerPage.afterSearchQueueName);
//        basePage.click(specificQueuePage.actionButton);
//        specificQueuePage.importInput.sendKeys("/share/Downloaded/downloadedQueue.zip");
//        queueManagerPage.rowsMessagesInTable.shouldHaveSize(sumMessages);
//
//        /*Очистка очереди*/
//        refresh();
//        basePage.click(specificQueuePage.actionButton);
//        basePage.click(specificQueuePage.cleanOutButton);
//        queueManagerPage.headerInClearForm.shouldBe(Condition.visible);
//        Assert.assertTrue(queueManagerPage.textInConfirmClearQueue.getText().contains(nameLocalQueue4));
//        basePage.click(specificQueuePage.confirmCleanOutButton);
//        queueManagerPage.rowsMessagesInTable.shouldHaveSize(0);
//        basePage.compareStringAndElementText("Нет данных", queueManagerPage.noDataText);
//
//        /*Удаление очереди*/
//        basePage.click(specificQueuePage.actionButton);
//        basePage.click(specificQueuePage.deleteButton);
//        basePage.click(specificQueuePage.confirmDeleteButtonInInfo);
//
//        basePage.compareStringAndStringShouldContainsText(url(), "/manager/#/queue-manager/queues");
////        Assert.assertTrue(url().contains("/manager/#/queue-manager/queues"));
//        queueManagerPage.searchQueue(nameLocalQueue4);
//        queueManagerPage.afterSearchTableRows.shouldHave(size(0));
//
//        /*Изменение выбранной очереди*/
//        queueManagerPage.searchQueue(nameLocalQueue2);
//        basePage.click(queueManagerPage.afterSearchQueueName);
//        Assert.assertEquals(nameLocalQueue2, queueManagerPage.nameChoosenQueueList.getText());
//        basePage.click(queueManagerPage.openQueueListIcon);
//        basePage.val(queueManagerPage.inputChoosenQueueList, nameLocalQueue3);
//        queueManagerPage.resultsQueueListForChoose.shouldHaveSize(1);
//        basePage.click(queueManagerPage.resultsQueueListForChoose.get(0));
//        Assert.assertEquals(nameLocalQueue3, queueManagerPage.nameChoosenQueueList.getText());
//        specificQueuePage = new SpecificQueuePage();
//        basePage.click(specificQueuePage.propertiesButton);
//        Assert.assertEquals(nameLocalQueue3, specificQueuePage.valueNameIhInPropertiesQueue.getText());
//    }
//
//    @Test
//    @Parameters(value = {
//            "Локальная очередь | Текст",
//            "Виртуальная очередь | Текст",
//            "Очередь сегментации | Файл",
//            "Очередь агрегации | Сгенерировать",
//            "Транспортная очередь | Сгенерировать в диапазоне",
//    })
//    public void messageFunctional(String typeQueue, String typeMessage) {
//        String numberOfMessage = null;
//        Object[] parametersOfMessage;
//        Object[] parametersOfSent;
//        String cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String cookies2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        switch (typeQueue) {
//            case ("Локальная очередь"):
//                groupIdMessage = "";
//                queueForReply = "";
//                numberInGroupMessage = "0";
//                validityMessage = "";
//                classMessage = "ActiveMQTextMessage";
//                sizeBodyMessage = "228";
//                String nameLocalQueue = commonComponents.createIndividualName("Local_ForTestMessagesFunctional");
//
//                queueManagerApi.createQueueLocalAPI(cookies, QueueManagerApi.typeQueue.local, nameLocalQueue, baseUrl());
//                basePage.click(basePage.sendMessageTab);
//                basePage.click(messagePage.addresseSelect);
//                basePage.valAndSelectElement(messagePage.addresseInput, nameLocalQueue);
//                basePage.click(messagePage.kindOfContentMessageSelect);
//                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
//                basePage.val(messagePage.messageTextAria, messagePage.messageWithAllSimbols);
//                basePage.click(messagePage.makePersistentCheckBox);
//                basePage.click(messagePage.sendMessageButton);
//
//                queueManagerPage = new QueueManagerPage();
//                queueManagerPage.contentSpecificQueue(nameLocalQueue);
//                specificQueuePage = new SpecificQueuePage();
//                specificQueuePage.saveParametersMessageInTable();
//                specificQueuePage.openFirstMessage();
//                specificMessagePage = new SpecificMessagePage();
//                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable, specificQueuePage.valueIdMessageInTable, specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage, specificQueuePage.valuePersistenceMessageInTable, queueForReply, numberInGroupMessage, validityMessage, specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage, specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable, specificQueuePage.valueTypeMessageInTable, messagePage.messageWithAllSimbols);
//                break;
//            case ("Виртуальная очередь"):
//                numberSendMessage = "1";
//                corellationIdMessage = "888";
//                queueForReply = commonComponents.createIndividualName("NewLocalQueue_ForTestMessagesFunctional");
//                typeJmsMessage = "Тип JMS-Virt";
//                groupIdMessage = "123";
//                numberInGroupMessage = "7";
//                valuePriorityMessage = "5";
//                validityMessage = "90";
//                initialDelay = "25000";
//                timeToResendMessage = "3333333";
//                headerOfCounerMessage = "Заголовок счетчика-Virt";
//                numberOfResendsMessage = "0";
//                cronMessage = "Сообщение Cron-Virt";
//                nameFileMessage = "имя файла-Virt";
//                classMessage = "ActiveMQTextMessage";
//                sizeBodyMessage = "228";
//                redirectedMessage = "Нет";
//                String nameVirtualQueueFrom = commonComponents.createIndividualName("VirtualFrom_ForInformationAboutQueue");
//                String nameVirtualQueueTo = commonComponents.createIndividualName("VirtualTo_ForInformationAboutQueue");
//
//                queueManagerApi.configMqAPI(cookies, true, true, 50, "100 gb", "50 gb");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.virtual, nameVirtualQueueFrom, nameVirtualQueueTo);
//                queueManagerApi.restartMqAPI(cookies);
//                sleep(5000);
//                basePage.click(basePage.sendMessageTab);
//                basePage.click(messagePage.addresseSelect);
//                basePage.valAndSelectElement(messagePage.addresseInput, nameVirtualQueueFrom);
//                basePage.click(messagePage.kindOfContentMessageSelect);
//                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
//                basePage.val(messagePage.messageTextAria, messagePage.messageWithAllSimbols);
//                messagePage.fillInAdditionalFields(MessagePage.Persistant.Yes, numberSendMessage, corellationIdMessage, queueForReply, typeJmsMessage, groupIdMessage, numberInGroupMessage, valuePriorityMessage, validityMessage, initialDelay, timeToResendMessage, headerOfCounerMessage, numberOfResendsMessage, cronMessage, nameFileMessage);
//                basePage.click(messagePage.sendMessageButton);
//
//                queueManagerPage = new QueueManagerPage();
//                queueManagerPage.queueShouldHaveDepth(nameVirtualQueueTo, 0);
//                sleep(Integer.parseInt(initialDelay));
//                refresh();
//                queueManagerPage.queueShouldHaveDepth(nameVirtualQueueTo, 1);
//
//                queueManagerPage.contentSpecificQueue(nameVirtualQueueTo);
//                specificQueuePage = new SpecificQueuePage();
//                specificQueuePage.saveParametersMessageInTable();
//                specificQueuePage.messageCheckAllParameters(corellationIdMessage, MessagePage.Persistant.Yes, valuePriorityMessage,
//                        redirectedMessage, "1418", typeJmsMessage, nameFileMessage);
//                specificQueuePage.openFirstMessage();
//                specificMessagePage = new SpecificMessagePage();
//                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable,
//                        specificQueuePage.valueIdMessageInTable, specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage,
//                        specificQueuePage.valuePersistenceMessageInTable, queueForReply, numberInGroupMessage, validityMessage,
//                        specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage,
//                        specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable,
//                        specificQueuePage.valueTypeMessageInTable, messagePage.messageWithAllSimbols);
//
//                queueManagerPage = new QueueManagerPage();
//                sleep(Integer.parseInt(validityMessage) * 1000);
//                refresh();
//                queueManagerPage.queueShouldHaveDepth(nameVirtualQueueTo, 0);
//                break;
//            case ("Очередь сегментации"):
//                groupIdMessage = "";
//                queueForReply = "";
//                numberInGroupMessage = "0";
//                validityMessage = "0";
//                validityMessage = "";
//                classMessage = "ActiveMQBytesMessage";
//                sizeBodyMessage = "2688";
//                String fileName = "fileForMessage.txt";
//                String nameSegmentQueueFrom = commonComponents.createIndividualName("SegmentFrom_ForInformationAboutQueue");
//                String nameLocalQueueTo = commonComponents.createIndividualName("LocalTo_ForInformationAboutQueue");
//                String nameAggregateQueueTo = commonComponents.createIndividualName("AggregateTo_ForInformationAboutQueue");
//                String textInFile = "Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово Сообщалово ";
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.aggregate, nameAggregateQueueTo, nameLocalQueueTo);
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.fragment, nameSegmentQueueFrom, nameAggregateQueueTo);
//                basePage.click(basePage.sendMessageTab);
//                basePage.click(messagePage.addresseSelect);
//                basePage.valAndSelectElement(messagePage.addresseInput, nameSegmentQueueFrom);
//                basePage.click(messagePage.kindOfContentMessageSelect);
//                basePage.valAndSelectElement(messagePage.kindOfContentMessageInput, typeMessage);
//                messagePage.fileAria.sendKeys("/share/files/" + fileName);
//                sleep(5000);
//                basePage.click(messagePage.sendMessageButton);
//
//                queueManagerPage = new QueueManagerPage();
//                queueManagerPage.queueCheckAllParameters(nameSegmentQueueFrom, "0", "1", "1", "1", "Сегментация", "");
//                queueManagerPage.queueCheckAllParameters(nameAggregateQueueTo, "0", "1", "27", "27", "Агрегация", "");
//                queueManagerPage.queueCheckAllParameters(nameLocalQueueTo, "1", "0", "1", "0", "Локальная", "");
//
//                queueManagerPage = new QueueManagerPage();
//                queueManagerPage.contentSpecificQueue(nameLocalQueueTo);
//                specificQueuePage = new SpecificQueuePage();
//                specificQueuePage.messageCheckAllParameters("", MessagePage.Persistant.No,
//                        "4", "Нет", "3712", "", fileName);
//                specificQueuePage.saveParametersMessageInTable();
//                specificQueuePage.openFirstMessage();
//                specificMessagePage = new SpecificMessagePage();
//                specificMessagePage.checkParametersMessageInTableAndInfo(specificQueuePage, specificQueuePage.valueDispatchTimeMessageInTable, specificQueuePage.valueIdMessageInTable,
//                        specificQueuePage.valueIdCorrelationMessageInTable, groupIdMessage, specificQueuePage.valuePersistenceMessageInTable, queueForReply,
//                        numberInGroupMessage, validityMessage, specificQueuePage.valuePriorityMessageInTable, specificQueuePage.valueRepeatDeliveryMessageInTable, classMessage,
//                        specificQueuePage.valueSizeMessageInTable, sizeBodyMessage, specificQueuePage.valueFileNameMessageInTable, specificQueuePage.valueTypeMessageInTable,
//                        textInFile);
//                break;
//            case ("Очередь агрегации"):
//                numberOfMessage = "3";
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.GENERATE, "785000"};
//                parametersOfSent = new Object[]{numberOfMessage};
//                String nameAgregateQueueFrom = commonComponents.createIndividualName("AgregateFrom_ForInformationAboutQueue");
//                String nameAgregateQueueTo = commonComponents.createIndividualName("AgregateTo_ForInformationAboutQueue");
//                queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cookies, QueueManagerApi.typeQueue.aggregate, nameAgregateQueueFrom, nameAgregateQueueTo);
//                messagePage.sendMessageThroughForm(nameAgregateQueueFrom, "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//                sleep(1000);
//                queueManagerApi.queueCheckAllParametersAPI(cookies, nameAgregateQueueFrom, 0, 1, 0, Integer.parseInt(numberOfMessage), Integer.parseInt(numberOfMessage), "aggregation", "null", baseUrl());
//                queueManagerApi.queueCheckAllParametersAPI(cookies, "TEMP.AGGREGATION.", Integer.parseInt(numberOfMessage), 0, 0, Integer.parseInt(numberOfMessage), 0, "local", "null", baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, "TEMP.AGGREGATION.", "minMessageSize", "786024", baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, "TEMP.AGGREGATION.", "maxMessageSize", "786024", baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, "TEMP.AGGREGATION.", "storeMessageSize", "2358072", baseUrl());
//                break;
//            case ("Транспортная очередь"):
//                String url = "fesb-test-3-2:61616";
//                numberOfMessage = "100";
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.GENERATE_IN_RANGE, "666|50|-777|10"};
//                parametersOfSent = new Object[]{numberOfMessage};
//                String nameTransportQueue = commonComponents.createIndividualName("Transport_ForInformationAboutQueue");
//                String nameCanal = commonComponents.createIndividualName("Canal_ForInformationAboutTransportQueue");
//                queueManagerApi.createQueueTransportAPI(cookies, QueueManagerApi.typeQueue.TRANSPORT, nameTransportQueue, nameCanal, url);
//                queueManagerApi.restartMqAPI(cookies);
//                messagePage.sendMessageThroughForm(nameTransportQueue, "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//
//                sleep(5000);
//                queueManagerApi.queueCheckAllParametersAPI(cookies, nameTransportQueue, 0, 1, 0, 60, 60, "transport", nameCanal, baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, nameTransportQueue, "minMessageSize", "1690", baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, nameTransportQueue, "maxMessageSize", "1801", baseUrl());
//                queueManagerApi.queueCheckSpecificParameterAPI(cookies, nameTransportQueue, "storeMessageSize", "0", baseUrl());
//                break;
//            default:
//                throw new AssertionError("Тест пропущен для типа: " + typeQueue);
//        }
//    }
//
//    @Test
//    @Parameters(value = {
//            "Записать событие в СОПС | Очередь для перехвата сообщений СОПС | null | СОПС для перехвата",
//            "Записать событие в монитор транзакций | Очередь для перехвата сообщений монитор | null | null",
//            "Записать событие в очередь | Очередь для перехвата сообщений очередь | Очередь для перехвата сообщений очередь | null",
//            "Записать событие в файл | Очередь для перехвата сообщений файл | null | fileForEnterception",
//    })
//    public void eventInterception(String typeInterception, String nameQueue1, String nameQueue2, String nameAcceptor) {
//        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String message = "Тестируем";
//        ArrayList<String> listQueue = new ArrayList<>();
//        Object[] parametersOfMessage;
//        Object[] parametersOfSent;
//        listQueue.add(nameQueue1);
//        if (nameQueue2 != null) listQueue.add(nameQueue2);
//        System.out.println(listQueue);
//
//        switch (typeInterception) {
//            case "Записать событие в СОПС":
//                String nameDomain = "Домен для перехвата событий";
//                String domainID = sopsApi.createDomainAPI(Cook, nameDomain);
//                sopsApi.startDomainAPI(Cook, domainID);
//                queueManagerApi.createQueueLocalAPI(Cook, QueueManagerApi.typeQueue.local, typeInterception, baseUrl());
//                creationSOPSPage.createSOPSWithLinkToSopsAndLocalQueue(nameDomain, "Менеджер очередей", "", nameAcceptor, nameAcceptor, nameQueue1, null, null);
//                eventInterceptionPage.createEventInterception("Менеджер очередей", "", typeInterception, listQueue, nameAcceptor,"");
//                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);
//
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
//                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
//                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//
//                Response response = sopsApi.getStatisticOfDomainAPI(Cook, baseUrl(), domainID);
//                Assert.assertEquals("[1]", response.path("processedQty").toString());
//                sleep(1500);
//                queueManagerApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, typeInterception.replace("в ", "в."), baseUrl());
//                response = sopsApi.getStatisticOfDomainAPI(Cook, baseUrl(), domainID);
//                Assert.assertEquals("[3]", response.path("processedQty").toString());
//                break;
//            case "Записать событие в монитор транзакций":
//                ArrayList<String> trueInfoAboutEvent1 = new ArrayList<>();
//                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-PUT");
//                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-EXPIRE");
//                trueInfoAboutEvent1.add("Записать событие в.монитор транзакций-GET");
//
//                eventInterceptionPage.createEventInterception(typeInterception, "Менеджер очередей", typeInterception, listQueue, nameAcceptor,"");
//                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);
//
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
//                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
//                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//
//                sleep(1500);
//                queueManagerApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, typeInterception.replace("в ", "в."), baseUrl());
//                queueManagerPage = new QueueManagerPage();
//                queueManagerPage.checkTransactionMonitor(trueInfoAboutEvent1);
//                break;
//
//            case "Записать событие в очередь":
//                queueManagerApi.createQueueLocalAPI(Cook, QueueManagerApi.typeQueue.local, typeInterception, baseUrl());
//                queueManagerApi.createQueueLocalAPI(Cook, QueueManagerApi.typeQueue.local, nameQueue2, baseUrl());
//                eventInterceptionPage.createEventInterception(typeInterception, "Менеджер очередей", typeInterception, listQueue, nameQueue2,"");
//                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);
//
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
//                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
//                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//
//                sleep(3000);
////                sleep(1500);
//                queueManagerApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, typeInterception.replace("в ", "в."), baseUrl());
//                queueManagerApi.queueCheckAllParametersAPI(Cook, nameQueue2, 3, 0, 0, 3, 0, "local", "null", baseUrl());
//                break;
//
//            case "Записать событие в файл":
//                basePage.chekExistFile(BasePage.FileExist.No, "/home/fesb/Stand/share/upload/intercepting.log");
//                eventInterceptionPage.createEventInterception(typeInterception, "Менеджер очередей", typeInterception, listQueue, nameAcceptor,"/fesb/tests/upload?/intercepting.log");
//                basePage.userProfileMenu.waitUntil(Condition.enabled, 60000);
//
//                parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, "нестойкое"};
//                parametersOfSent = new Object[]{Integer.toString(1), "Время жизни (мс)-1000"};
//                messagePage.sendMessageThroughForm(typeInterception.replace("в ", "в."), "", "Менеджер очередей", parametersOfMessage, parametersOfSent);
//
//                sleep(1500);
//                queueManagerApi.listOfMessagesInQueueShouldBeEmptyAPI(Cook, typeInterception.replace("в ", "в."), baseUrl());
//                basePage.chekExistFile(BasePage.FileExist.Yes, "/home/fesb/Stand/share/upload/intercepting.log");
//                break;
//            default:
//                throw new AssertionError("Пропущена подготовка к тесту");
//        }
//    }
//
//    @Test
//    public void createConnector() {
//        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort = portForCreateConnector;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//
//        final String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
//
//        String[] connectorStatisticBefore = {"|"};
//        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};
//
////        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.successCreateConnector(connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
////        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort, connectorStatisticBefore);
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort, localQueueName, false, baseUrl_2());
//        refresh();
//        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort, connectorStatisticAfter);
//
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 0, 0, "transport", channelName, baseUrl_2());
//        try {
//            queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 0, 0, 0, 0, 0, "local", channelName, baseUrl());
//        } catch (NullPointerException e) {
//            basePage.sout("Очередь не найдена и это хорошо");
//        }
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 1, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//    }
//
//    @Test
//    public void cancelCreateConnector() {
//        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort = portForCancelCreateConnector;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//
//        String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
////
////        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.cancelCreateConnector(connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
//
//        queueManagerPage.connectorShouldNotExist(connectorName);
//        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
//        sleep(3000);
//        refresh();
//        queueManagerPage.connectorShouldNotExist(connectorName);
//
//
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort, localQueueName, false, baseUrl_2());
////        queueManagerApi.restartMqAPI(Cook2, baseUrl_2());
//        sleep(5000);
//
//        try {
//            queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, -0, -0, -0, -0, -0, null, null, baseUrl_2());
//        } catch (NullPointerException e) {
//            basePage.sout("Очередь не найдена и это хорошо");
//        }
//        try {
//            queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, -0, -0, -0, -0, -0, null, null, baseUrl_2());
//        } catch (NullPointerException e) {
//            basePage.sout("Очередь не найдена и это хорошо");
//        }
//    }
//
//    @Test
//    public void editConnector() {
//        Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorNameNew = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort1 = portForEditConnector1;
//        String connectorPortNew = portForEditConnector2;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};
//
//        final String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
//        String[] connectorStatisticBefore = {"null|null"};
//        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};
//
//        ArrayList<String> ipList = new ArrayList<>();
//        ipList.add(channelIp);
//        ArrayList<String> portList = new ArrayList<>();
//        portList.add(connectorPortNew);
//
//        queueManagerApi.createConnectorAPI(Cook1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorParameters, baseUrl());
//        refresh();
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort1, localQueueName, false, baseUrl_2());
//        sleep(5000);
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 1, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
////        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.successEditConnector(connectorName, connectorNameNew, connectorProtocol, connectorIp, connectorPortNew, connectorParametersNew);
//
//        queueManagerPage.connectorShouldNotExist(connectorName);
//        queueManagerPage.connectorShouldNotHaveStatistic(connectorNameNew);
//        refresh();
//        queueManagerPage.connectorShouldNotHaveStatistic(connectorNameNew);
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 1, 0, 0, 2, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
//        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.editChannel(channelName, channelName, ipList, portList, localQueueName, "static", "NotDuplex");
//
//        sleep(5000);
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//
//        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage.connectorCheckAllParameters(connectorNameNew, connectorProtocol, connectorIp, connectorPortNew, connectorStatisticAfter);
//
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 3, 3, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 3, 0, 0, 3, 0, "local", "null", baseUrl());
//    }
//
//    @Test
//    public void cancelEditConnector() {
//        Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorNameNew = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort1 = portForCancelEditConnector1;
//        String connectorPort2 = portForCancelEditConnector2;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};
//
//        final String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
//        String[] connectorStatisticBefore = {"null|null"};
//        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};
//
//        ArrayList<String> ipList = new ArrayList<>();
//        ipList.add(channelIp);
//        ArrayList<String> portList = new ArrayList<>();
//        portList.add(connectorPort2);
//
//        queueManagerApi.createConnectorAPI(Cook1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorParameters, baseUrl());
//
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort1, localQueueName, false, baseUrl_2());
//        sleep(5000);
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 1, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
//        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.cancelEditConnector(connectorName, connectorNameNew, connectorProtocol, connectorIp, connectorPort2, connectorParametersNew);
//        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
//        queueManagerPage.connectorShouldNotExist(connectorNameNew);
//        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
//        refresh();
//        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
//        queueManagerPage.connectorShouldNotExist(connectorNameNew);
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 2, 2, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 2, 0, 0, 2, 0, "local", "null", baseUrl());
//    }
//
//    @Test
//    public void deleteConnector() {
//        Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorNameNew = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort1 = portForDeleteConnector;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//
//        String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
//
//        ArrayList<String> ipList = new ArrayList<>();
//        ipList.add(channelIp);
//
//        queueManagerApi.createConnectorAPI(Cook1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorParameters, baseUrl());
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort1, localQueueName, false, baseUrl_2());
//        refresh();
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 1, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
//        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerPage = new QueueManagerPage();
//
//        queueManagerPage.successDeleteConnector(connectorName);
//        queueManagerPage.connectorShouldNotExist(connectorName);
//        refresh();
//        queueManagerPage.connectorShouldNotExist(connectorName);
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 1, 0, 0, 2, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//    }
//
//    @Test
//    public void cancelDeleteConnector() {
//        Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        Cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String connectorName = commonComponents.createIndividualName("connectorName");
//        String connectorNameNew = commonComponents.createIndividualName("connectorName");
//        String connectorProtocol = "TCP";
//        String connectorIp = "0.0.0.0";
//        String channelIp = baseUrl().split("//")[1].split(":")[0];
//        String connectorPort1 = portForCancelDeleteConnector;
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//        String[] connectorParametersNew = {"maximumConnections|999", "wireFormat.maxFrameSize|1048576999"};
//
//        final String channelName = commonComponents.createIndividualName("channelName");
//        String channelProtocol = "tcp";
//        String localQueueName = commonComponents.createIndividualName("localQueueName");
//        String[] connectorStatisticBefore = {"null|null"};
//        String[] connectorStatisticAfter = {channelName + "_QM_outbound|null"};
//
//        ArrayList<String> ipList = new ArrayList<>();
//        ipList.add(channelIp);
//        queueManagerApi.createConnectorAPI(Cook1, connectorName, connectorProtocol, connectorIp, connectorPort1, connectorParameters, baseUrl());
//        queueManagerApi.createChanelAPI(Cook2, false, channelName, channelProtocol, channelIp, connectorPort1, localQueueName, false, baseUrl_2());
//        sleep(5000);
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 1, 1, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//
//        open(baseUrl());
//        queueManagerPage = new QueueManagerPage();
//
//        queueManagerPage.cancelDeleteConnector(connectorName);
//        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
//        basePage.restartModuleButton.find(Condition.visible).shouldNotBe(Condition.visible);
//        refresh();
//        queueManagerPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorIp, connectorPort1, connectorStatisticAfter);
//
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueName, "text", 1, false, "1");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueName, 0, 1, 0, 2, 2, "transport", channelName, baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueName, 2, 0, 0, 2, 0, "local", "null", baseUrl());
//    }
//
//    @Test
//    public void remoteAccessToMq() {
//        String localOueueName1 = testName.getMethodName() + commonComponents.createIndividualName("-");
//        String message = "pwd";
//        String cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        queueManagerApi.createQueueLocalAPI(cook2, QueueManagerApi.typeQueue.local, localOueueName1, baseUrl_2());
//        queueManagerApi.sendMessgeInQueueAPI(cook2, baseUrl_2(), localOueueName1, message, 1, false, "4");
//        queueManagerPage = new QueueManagerPage("empty");
//        sleep(1000);
//        queueManagerApi.queueShouldNotExist(cook1, localOueueName1, baseUrl());
//        queueManagerPage.settingRemoteAccess("on", "tcp://fesb-test-3-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        try {
//            queueManagerPage.queueShouldHaveSpecificMessage(localOueueName1, message);
//            queueManagerPage.queueCheckAllParameters(localOueueName1, "1", "0", "1", "0", "Локальная", "");
//            queueManagerPage.settingRemoteAccess("off", "tcp://fesb-test-0-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        } catch (Exception e) {
//            queueManagerPage.settingRemoteAccess("off", "tcp://fesb-test-0-2:61616", "factor-admin", "!fesbROOT", "fesb-test-3-2:1099", "", "");
//        }
//    }
//
//    @Test
//    public void commandInterface() {
//        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
//        String urlDownload = "file:///home/selenium/Downloads/";
//        String downloadFilePath = "/home/fesb/share/Downloaded/downloadedQueue.zip";
//        String downloadedFileName = null;
//        String connectorName = "testConnector";
//        String topicName = "testTopic";
//        String subscraberName = "testSubscriber";
//        String channelName1 = "testChannel";
//        String virtualQueueName1 = "testVirtualQueue1";
//        String virtualQueueName2 = "testVirtualQueue2";
//        String localQueueName1 = "testlocalQueueName1";
//
//        String[] connectorParameters = {"maximumConnections|1000", "wireFormat.maxFrameSize|1048576000"};
//
//        String cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String cook2 = api.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        try {
//            String responseListOfConnectors = queueManagerApi.getListOfConnectorAPI(cook2, baseUrl_2()).asString();
//            basePage.compareStringAndStringShouldContainsText(responseListOfConnectors, connectorName);
//            throw new AssertionError("Найден несуществующий коннектор");
//        } catch (AssertionError e) {
//            System.out.println("Коннектор не найден и это хорошо");
//        }
//        try {
//            String responseListOfTopic = queueManagerApi.getListOfTopicAPI(cook2, baseUrl_2()).asString();
//            basePage.compareStringAndStringShouldContainsText(responseListOfTopic, topicName);
//            throw new AssertionError("Найден несуществующий раздел");
//        } catch (AssertionError e) {
//            System.out.println("Раздел не найден и это хорошо");
//        }
//        try {
//            String responseListOfSubscriber = queueManagerApi.getListOfSubscriberAPI(cook2, baseUrl_2()).asString();
//            basePage.compareStringAndStringShouldContainsText(responseListOfSubscriber, subscraberName);
//            throw new AssertionError("Найден несуществующий подписчик");
//        } catch (AssertionError e) {
//            System.out.println("Подписчик не найден и это хорошо");
//        }
//        queueManagerApi.queueShouldNotExist(cook2, virtualQueueName1, baseUrl_2());
//        queueManagerApi.queueShouldNotExist(cook2, virtualQueueName2, baseUrl_2());
//        queueManagerApi.queueShouldNotExist(cook2, localQueueName1, baseUrl_2());
//        try {
//            String responseListOfChannel = queueManagerApi.getListOfChannelAPI(cook2, baseUrl_2()).asString();
//            basePage.compareStringAndStringShouldContainsText(responseListOfChannel, channelName1);
//            throw new AssertionError("Найден несуществующий канал");
//        } catch (AssertionError e) {
//            System.out.println("Канал не найден и это хорошо");
//        }
//
//        queueManagerApi.createConnectorAPI(cook1, connectorName, "TCP", "fesb-test-3-1", "3005", connectorParameters, baseUrl());
//        queueManagerApi.createTopicAPI(cook1, topicName);
//        queueManagerApi.createSubscraberAPI(cook1, topicName, subscraberName, "idDescriber");
//        queueManagerApi.createQueueVirtualSegmentationAggregationAPI(cook1, QueueManagerApi.typeQueue.virtual, virtualQueueName1, virtualQueueName2);
//        queueManagerApi.createQueueLocalAPI(cook1, QueueManagerApi.typeQueue.local, localQueueName1, baseUrl());
//        queueManagerApi.createChanelAPI(cook1, false, channelName1, "TCP", "fesb-test-3-1", "61616", "testLocal", false, baseUrl());
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.exportInFile();
//        sleep(5000);
//        open(urlDownload);
//        downloadedFileName = queueManagerPage.downloadedFileName.getText();
//        basePage.compareElementTextShouldContainsText("factor-mq-dump-", queueManagerPage.downloadedFileName);
//        queueManagerPage.checkContentDowloadedFile(sessionId.toString(), "factor-mq-dump-", "экспорт менеджера", downloadFilePath, sessionId, downloadedFileName, downloadFilePath);
//        String command = queueManagerPage.exportedMQ.replace("fesb-test-3-1", "fesb-test-3-2").replace("\n", "\\n");
//        basePage.openPage(baseUrl_2());
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.executeCommand(command);
//        sleep(3000);
//
//        String responseListOfConnectors = queueManagerApi.getListOfConnectorAPI(cook2, baseUrl_2()).asString();
//        basePage.compareStringAndStringShouldContainsText(responseListOfConnectors, connectorName);
//        String responseListOfTopic = queueManagerApi.getListOfTopicAPI(cook2, baseUrl_2()).asString();
//        basePage.compareStringAndStringShouldContainsText(responseListOfTopic, topicName);
//        String responseListOfSubscriber = queueManagerApi.getListOfSubscriberAPI(cook2, baseUrl_2()).asString();
//        basePage.compareStringAndStringShouldContainsText(responseListOfSubscriber, subscraberName);
//        queueManagerApi.queueCheckAllParametersAPI(cook2, virtualQueueName1, 0, 1, 0, 0, 0, "virtual", "testVirtualQueue2", baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(cook2, virtualQueueName2, 0, 0, 0, 0, 0, "local", "null", baseUrl_2());
//        queueManagerApi.queueCheckAllParametersAPI(cook2, localQueueName1, 0, 0, 0, 0, 0, "local", "null", baseUrl_2());
//        String responseListOfChannel = queueManagerApi.getListOfChannelAPI(cook2, baseUrl_2()).asString();
//        basePage.compareStringAndStringShouldContainsText(responseListOfChannel, channelName1);
//    }
//}
//
//
//
