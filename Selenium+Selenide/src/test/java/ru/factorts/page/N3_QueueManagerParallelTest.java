//package ru.factorts.page;
//
//import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import io.qameta.allure.Description;
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
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Selenide.$x;
//import static com.codeborne.selenide.Selenide.sleep;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(JUnitParamsRunner.class)
//public class N3_QueueManagerParallelTest extends Base {
//    @Rule
//    public TestName testName = new TestName();
//
//    QueueManagerPage queueManagerPage;
//    SpecificQueuePage specificQueuePage = new SpecificQueuePage("Empty");
//    MessagePage messagePage = new MessagePage();
//    SpecifecTopicPage specifecTopicPage;
//    SpecificMessagePage specificMessagePage = new SpecificMessagePage("empty");
//    BasePage basePage = new BasePage();
//    static BasePage staticBasePage = new BasePage();
//    CommonComponents commonComponents = new CommonComponents();
//    static SettingsPage staticSettingsPage;
//    Api api = new Api();
//    QueueManagerApi queueManagerApi = new QueueManagerApi();
//    SOPSApi sopsApi = new SOPSApi();
//
//    private static final String TOPIC_NAME = "TEST_TOPIC";
//    private static final String QUEUE_NAME = "TEST_QUEUE",
//            SEGMENT_QUEUE_NAME = "QS",
//            DESTINATION_QUEUE_NAME = "OUT",
//            AGREG_QUEUE_NAME = "QAGR",
//            LOCAL_QUEUE_NAME = "LOCAL",
//            VIRTUAL_QUEUE_NAME = "VIRTUAL_QUEUE",
//            CANAL_NAME = "TEST_CANAL",
//            DOMAIN_NAME = "TEST_DOMAIN",
//            SOPS_NAME = "TEST_SOPS",
//            LOCAL_QUEUE_IN = "TEST_QUEUE_IN",
//            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT";
//
//    @BeforeClass
//    public static void beforeClass() {
//        staticBasePage.deleteFile("/home/fesb/Stand/share/download/TestKodirovka.txt");
//    }
//
//    @Before
//    public void setUpQueue() {
//        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
//        basePage.openWithLog("/manager/");
//        basePage.openPage(baseUrl());
//        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        logs = WebDriverRunner.getWebDriver().manage().logs();
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
//        try {
//            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        } catch (IllegalStateException | InvalidSelectorException e) {
//            System.out.println("Браузер не запущен");
//        }
//    }
//
//    @Test
//    public void createAndDeleteLocalQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        queueManagerPage.successCreateLocalQueue(localOueueName);
//        queueManagerPage.sucessDeleteQueue(localOueueName);
//    }
//
//    @Test
//    public void cancelCreateLocalQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        queueManagerPage.cancelCreateLocalQueue(localOueueName);
//    }
//
//    @Test
//    public void cancelDeleteLocalQueue() {
//        String cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        queueManagerApi.createQueueLocalAPI(cook, QueueManagerApi.typeQueue.local, localOueueName, baseUrl());
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        queueManagerPage.cancelDeleteQueue(localOueueName);
//    }
//
//    @Test
//    public void createAndDeleteSegregationQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//        queueManagerPage.createSegregQueue(segmOueueName, destQueueName, 1);
//        queueManagerPage.sucessDeleteQueue(segmOueueName);
//        queueManagerPage.sucessDeleteQueue(destQueueName);
//    }
//
//    @Test
//    public void createAndDeleteAgregationQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//        queueManagerPage.createAgregQueue(agregOueueName, destQueueName);
//        queueManagerPage.sucessDeleteQueue(agregOueueName);
//        queueManagerPage.sucessDeleteQueue(destQueueName);
//    }
//
//
//    /**
//     * Check segmentation and agregation 10 messages of 100 Bite each and content message in out queue
//     *
//     * @throws Exception
//     */
//    @Description("Check segmentation and agregation 10 messages of 100 Bite each and content message in out queue")
//    @Test
//    public void segAgrMessagePMITest() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
//        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//        queueManagerPage.createAgregQueue(agregOueueName, destQueueName);
//        queueManagerPage.createSegregQueue(segmOueueName, agregOueueName, 1);
//        queueManagerPage.goToMessagePage();
//        messagePage = new MessagePage();
//        String message = messagePage.createMessageWithBiteSize(100);
//        messagePage.sendMessageInQueueNTimes("Менеджер очередей", "", segmOueueName, message, 5);
//        messagePage.sendMessageInQueueNTimesAtOnce("Менеджер очередей", "", segmOueueName, message, 5);
//        messagePage.queueListPage();
//        queueManagerPage.queueShouldHaveNMessagesInDepth(destQueueName, 10, 120000);
//        queueManagerPage.queueShouldHaveSpecificMessage(destQueueName, message);
//        queueManagerPage.queueShouldHaveNMessagesInSentAndTaken(agregOueueName, 1000, 1000, 4000);
//        String[] queueArrray = {segmOueueName, agregOueueName, destQueueName};
//        for (String name : queueArrray) {
//            queueManagerPage.sucessDeleteQueue(name);
//        }
//    }
//
//    /**
//     * Check segmentation and agregation 10 files of 10 MB each
//     *
//     * @throws Exception
//     */
//    @Description("Check segmentation and agregation 10 files of 10 MB each")
//    @Test
//    public void segAgrFilePMITest() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
//        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//        queueManagerPage.createAgregQueue(agregOueueName, destQueueName);
//        queueManagerPage.createSegregQueue(segmOueueName, agregOueueName, 100000);
//        queueManagerPage.goToMessagePage();
//        messagePage = new MessagePage();
//        messagePage.sendFileInQueueNTimesAtOnce("Менеджер очередей", "", segmOueueName, "/share/test", 10);
//        messagePage.queueManagerPage();
//        queueManagerPage.queuesList();
//        queueManagerPage.queueShouldHaveNMessagesInDepth(destQueueName, 10, 300000);
//        sleep(5000);
//        queueManagerPage.queueShouldHaveNMessagesInSentAndTaken(agregOueueName, 1000, 1000, 4000);
//        String[] queueArrray = {segmOueueName, agregOueueName, destQueueName};
//        for (String name : queueArrray) {
//            queueManagerPage.sucessDeleteQueue(name);
//        }
//    }
//
//    /**
//     * Check clear queue and delete messages separately
//     *
//     * @throws Exception
//     */
//    @Description("Check clear queue and delete messages separately")
//    @Test
//    public void deleteMessagesAndClearQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        queueManagerPage.successCreateLocalQueue(localOueueName);
//        queueManagerPage.sendNSavedMessages(localOueueName, 3);
//        queueManagerPage.doubleClickInSpecificQueue(localOueueName);
//        specificQueuePage = new SpecificQueuePage();
//        specificQueuePage.successDeleteNMessages(2);
//        specificQueuePage.returnInQueuesListPage.click();
//        queueManagerPage.queuesList();
//        queueManagerPage.queueShouldHaveNMessagesInDepth(localOueueName, 1, 3000);
//        queueManagerPage.successClearQueue(localOueueName);
//        queueManagerPage.sucessDeleteQueue(localOueueName);
//    }
//
//    @Test
//    public void cancelDeleteMessagesAndCancelClearQueue() {
//        int sumMessages = 3;
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
//        queueManagerPage.successCreateLocalQueue(localOueueName);
//        queueManagerPage.sendNSavedMessages(localOueueName, sumMessages);
//        queueManagerPage.doubleClickInSpecificQueue(localOueueName);
//        specificQueuePage = new SpecificQueuePage();
//        specificQueuePage.cancelDeleteNMessages(1);
//        specificQueuePage.returnInQueuesListPage.click();
//        queueManagerPage.queuesList();
//        queueManagerPage.queueShouldHaveNMessagesInDepth(localOueueName, 3, 3000);
//        queueManagerPage.cancelClearQueue(localOueueName, Integer.toString(sumMessages));
//
//    }
//
//    /**
//     * Check edit all kind queues in local queue
//     *
//     * @throws Exception
//     */
//    @Description("Check edit all kind queues in local queue")
//    @Test
//    public void editQueuesInLocalQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
//        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//        queueManagerPage.createAgregQueue(agregOueueName, destQueueName);
//        sleep(500);
//        queueManagerPage.searchQueue(agregOueueName);
//        queueManagerPage.afterSearchQueueType.shouldHave(text("Агрегация"));
//        queueManagerPage.searchQueue(destQueueName);
//        queueManagerPage.afterSearchQueueType.shouldHave(text("Локальная"));
//        queueManagerPage.createSegregQueue(segmOueueName, agregOueueName, 1);
//        sleep(500);
//        queueManagerPage.searchQueue(segmOueueName);
//        queueManagerPage.afterSearchQueueType.shouldHave(text("Сегментация"));
//        queueManagerPage.searchQueue(agregOueueName);
//        queueManagerPage.afterSearchQueueType.shouldHave(text("Агрегация"));
//        queueManagerPage.editQueueTypeInLocalQueue(segmOueueName);
//        queueManagerPage.editQueueTypeInLocalQueue(agregOueueName);
//        String[] queueArrray = {segmOueueName, agregOueueName, destQueueName};
//        for (String name : queueArrray) {
//            queueManagerPage.sucessDeleteQueue(name);
//        }
//    }
//
//    /**
//     * Sent to topic some messages in three ways
//     */
//    @Description("Sent to topic some messages in three ways")
//    @Test
//    public void sendNMessagesInTopicInThreeWays(String managerName) {
//        String topicName = commonComponents.createIndividualName(TOPIC_NAME);
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.topicsList();
//        queueManagerPage.createTopic(topicName);
//        queueManagerPage.goToMessagePage();
//        messagePage = new MessagePage();
//        String message = messagePage.createMessageWithBiteSize(100);
//        messagePage.sendMessageInTopicNTimes("Менеджер очередей", "", topicName, message, 3);
//        messagePage.topicsList();
//        queueManagerPage.clickOptionInContextMenu(topicName, "Отправить сообщение");
//        messagePage.sendMessageWithOutCheck(message, 4);
//        messagePage.topicsList();
//        queueManagerPage.searchTopic(topicName);
//        basePage.doubleClick($x(String.format(queueManagerPage.afterSearchTopicName, topicName)).should(Condition.text(TOPIC_NAME)));
//        specifecTopicPage = new SpecifecTopicPage();
//        basePage.click(specifecTopicPage.sendMessage);
//        messagePage.sendMessageWithOutCheck(message, 5);
//        messagePage.topicsList();
//        queueManagerPage.topicShouldHaveNMessagesInSent(topicName, 12);
//        queueManagerPage.deleteTopic(topicName);
//    }
//
//    /**
//     * Sent to topic some messages in three ways
//     */
//    @Description("Editing in each type of queue")
//    @Test
//    public void editingInEachTypeOfQueue() {
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
//
//        queueManagerPage.successCreateLocalQueue(localOueueName);
//        queueManagerPage.editQueueTypeInSegmetationQueue(localOueueName, destQueueName, 1);
//        queueManagerPage.editQueueTypeInLocalQueue(localOueueName);
//        queueManagerPage.editQueueTypeInAgregationQueue(localOueueName, destQueueName);
//    }
//
//    /**
//     * `
//     * Download message
//     */
//    @Description("Download message")
//    @Test
//    @Parameters(value = {
//            "файл",
//            "1 без галки",
//            "1 с галкой",
//            "все",
//    })
//    public void downloadMessage(String Type) {
//        String text = "текст";
//        String fileName = "forTests.txt";
//        String filePath = "/share/upload/" + fileName;
//        String downloadFilePath = "/home/fesb/Stand/share/download/TestKodirovka.txt";
//        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
//        basePage.sout("Сессия: " + sessionId.toString());
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//
//        queueManagerPage.successCreateLocalQueue(localOueueName);
//        basePage.contextClick(queueManagerPage.afterSearchQueueName);
//        basePage.click(queueManagerPage.contextSendMessage);
//        switch (Type) {
//            case "файл":
//                messagePage.sendFileInQueueNTimes(localOueueName, filePath, 1);
//                break;
//            case "1 без галки":
//            case "1 с галкой":
//            case "все":
//                messagePage.sendMessageInQueueNTimesAtOnce("Менеджер очередей", "", localOueueName, text, 3);
//                break;
//            default:
//                throw new AssertionError("Пропущена отправка сообщения");
//
//        }
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String downloadedFileName = queueManagerPage.downloadMessage(localOueueName, Type);
//        switch (Type) {
//            case "файл":
//                queueManagerPage.checkContentDowloadedFile(sessionId.toString(), text, Type, downloadFilePath, sessionId, fileName, downloadFilePath);
//                break;
//            case "1 без галки":
//            case "1 с галкой":
//                queueManagerPage.checkContentDowloadedFile(sessionId.toString(), text, Type, "/home/fesb/Stand/share/forDownloadedMessages/" + downloadedFileName + ".zip", sessionId, downloadedFileName, downloadedFileName);
//                break;
//            case "все":
//                queueManagerPage.checkContentDowloadedFile(sessionId.toString(), text, Type, "/home/fesb/Stand/share/forDownloadedMessages/", sessionId, downloadedFileName, downloadedFileName);
//                break;
//            default:
//                throw new AssertionError("Пропущена отправка сообщения");
//        }
//
//    }
//
//    /**
//     * Move/Copy message
//     */
//    @Description("Move/Copy message")
//    @Test
//    @Parameters(value = {
//            "Переместить 1",
//            "Переместить все",
//            "Скопировать 1",
//            "Скопировать все",
//    })
//    public void moveAndCopyMessage(String Type) {
//        String text = "текст2";
//        int SumMessage = 3;
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        String localOueueName1 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//        String localOueueName2 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//
//        queueManagerPage.successCreateLocalQueue(localOueueName1);
//        queueManagerPage.successCreateLocalQueue(localOueueName2);
//        basePage.contextClick(queueManagerPage.afterSearchQueueName);
//        basePage.click(queueManagerPage.contextSendMessage);
//        messagePage.sendMessageInQueueNTimesAtOnce("Менеджер очередей", "", localOueueName2, text, SumMessage);
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
//        queueManagerPage.moveMessage(Type, localOueueName2, localOueueName1, SumMessage, text);
//    }
//
//    @Description("Проверка учета приоритета сообщений")
//    @Test
//    @Parameters(value = {
//            "Стойкое",
//            "Нестойкое",
//    })
//    public void priorityMessage(String TypeMessage) {
//        String text = "тест Приоритета";
//        int sumMessage = 1;
//        boolean persistent;
//        if (TypeMessage.equals("Стойкое")) {
//            persistent = true;
//        } else {
//            persistent = false;
//        }
//        String priority1 = "4";
//        String priority2 = "6";
//        String priority3 = "1";
//        String priority4 = "3";
//
//        String domainName = commonComponents.createIndividualName(DOMAIN_NAME);
//        String sopsName = commonComponents.createIndividualName(SOPS_NAME);
//        String localOueueName1 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//        String localOueueName2 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
//
//        String cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String DomainID = sopsApi.createDomainAPI(cookies, domainName);
//        sopsApi.generateSopsIdAPI(cookies, DomainID, sopsName);
//        sopsApi.createSopsWith2LocalQueueAPI(cookies, baseUrl(), DomainID, sopsName, domainName, sopsName, localOueueName1, localOueueName2);
//
//        queueManagerApi.sendMessgeInQueueAPI(cookies, baseUrl(), localOueueName1, text, sumMessage, persistent, priority1);
//        queueManagerApi.sendMessgeInQueueAPI(cookies, baseUrl(), localOueueName1, text, sumMessage, persistent, priority2);
//        queueManagerApi.sendMessgeInQueueAPI(cookies, baseUrl(), localOueueName1, text, sumMessage, persistent, priority3);
//        queueManagerApi.sendMessgeInQueueAPI(cookies, baseUrl(), localOueueName1, text, sumMessage, persistent, priority4);
//        sopsApi.startDomainAPI(cookies, DomainID);
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.contentSpecificQueue(localOueueName2);
//        System.out.println(specificQueuePage.priorityMessageListInTable.toString());
//        basePage.compareStringAndElementText(priority2, specificQueuePage.priorityMessageListInTable.get(0));
//        basePage.compareStringAndElementText(priority1, specificQueuePage.priorityMessageListInTable.get(1));
//        basePage.compareStringAndElementText(priority4, specificQueuePage.priorityMessageListInTable.get(2));
//        basePage.compareStringAndElementText(priority3, specificQueuePage.priorityMessageListInTable.get(3));
//    }
//}