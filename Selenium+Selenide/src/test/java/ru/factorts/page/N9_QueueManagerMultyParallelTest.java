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

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N9_QueueManagerMultyParallelTest extends Base {
    @Rule
    public TestName testName = new TestName();

    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    SpecificQueuePage specificQueuePage = new SpecificQueuePage("Empty");
    MessagePage messagePage = new MessagePage();
    SpecifecTopicPage specifecTopicPage;
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    CommonComponents commonComponents = new CommonComponents();
    Api api = new Api();
    static Api staticApi = new Api();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    SOPSApi sopsApi = new SOPSApi();

    private static final String TOPIC_NAME = "TEST_TOPIC";
    private static final String QUEUE_NAME = "TEST_QUEUE",
            SEGMENT_QUEUE_NAME = "QS",
            DESTINATION_QUEUE_NAME = "OUT",
            AGREG_QUEUE_NAME = "QAGR",
            LOCAL_QUEUE_NAME = "LOCAL",
            VIRTUAL_QUEUE_NAME = "VIRTUAL_QUEUE",
            CANAL_NAME = "TEST_CANAL",
            DOMAIN_NAME = "TEST_DOMAIN",
            SOPS_NAME = "TEST_SOPS",
            LOCAL_QUEUE_IN = "TEST_QUEUE_IN",
            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT";
    static String cook1 = null;

    @BeforeClass
    public static void beforeClass() {

        cook1 = staticApi.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.switchModuleAPI(cook1, Base.baseUrl(), "factor-qms", "activate");
//        staticBasePage.deleteFile("/home/fesb/Stand/share/download/TestKodirovka.txt");
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
        open(baseUrl());
    }

    @After
    public void afterTest() {
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
    public void createUser() {
        String userName = testName.getMethodName();
        String userPassword = testName.getMethodName() + "aj79!@#$%^&*()_+-";
        String[] userGroups = new String[]{"read", "admin", "write"};
        String managerName1 = testName.getMethodName();
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
//        open(baseUrl());
        queueManagerMultyPage.createUser(managerName1, userName, userPassword, userGroups);
        open(baseUrl());
        queueManagerMultyPage.checkAllParametersUser(managerName1, userName, userGroups);
    }

    @Test
    public void createAndDeleteLocalQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.successCreateLocalQueue(managerName1, localOueueName);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, localOueueName);
    }

    @Test
    public void cancelCreateLocalQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.cancelCreateLocalQueue(managerName1, localOueueName);
    }

    @Test
    public void cancelDeleteLocalQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        ;
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localOueueName);
        queueManagerMultyPage.cancelDeleteQueue(managerName1, localOueueName);
    }

    @Test
    public void createAndDeleteSegregationQueue() {
        String managerName1 = testName.getMethodName();
        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.createSegregQueue(managerName1, segmOueueName, destQueueName, 1);
        queueManagerMultyApi.sendMessgeInQueueAPI(cook1, managerName1, segmOueueName, "text", 1);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, segmOueueName);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, destQueueName);
    }

    @Test
    public void createAndDeleteAgregationQueue() {
        String managerName1 = testName.getMethodName();
        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.createAgregQueue(managerName1, agregOueueName, destQueueName);
        queueManagerMultyApi.sendMessgeInQueueAPI(cook1, managerName1, agregOueueName, "text", 1);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, agregOueueName);
    }


    /**
     * Check segmentation and agregation 10 messages of 100 Bite each and content message in out queue
     *
     * @throws Exception
     */
    @Description("Check segmentation and agregation 10 messages of 100 Bite each and content message in out queue")
    @Test
    public void segAgrMessagePMITest() {
        String managerName1 = testName.getMethodName();
        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.createAgregQueue(managerName1, agregOueueName, destQueueName);
        queueManagerMultyPage.createSegregQueue(managerName1, segmOueueName, agregOueueName, 1);

        queueManagerMultyPage.goToMessagePage();
        messagePage = new MessagePage();
        String message = messagePage.createMessageWithBiteSize(100);
        messagePage.sendMessageInQueueNTimes("Мультименеджер очередей", managerName1, segmOueueName, message, 5);
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName1, segmOueueName, message, 5);
//        messagePage.queueListPage();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName1, destQueueName, 10, 120000);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName1, destQueueName, message);
        queueManagerMultyPage.queueShouldHaveNMessagesInSentAndTaken(managerName1, agregOueueName, 1000, 1000, 4000);
        String[] queueArrray = {segmOueueName, agregOueueName, destQueueName};
        for (String name : queueArrray) {
            queueManagerMultyPage.sucessDeleteQueue(managerName1, name);
        }
    }

    /**
     * Check segmentation and agregation 10 files of 10 MB each
     *
     * @throws Exception
     */
    @Description("Check segmentation and agregation 10 files of 10 MB each")
    @Test
    public void segAgrFilePMITest() {
        String managerName1 = testName.getMethodName();
        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.createAgregQueue(managerName1, agregOueueName, destQueueName);
        queueManagerMultyPage.createSegregQueue(managerName1, segmOueueName, agregOueueName, 100000);

        queueManagerMultyPage.goToMessagePage();
        messagePage = new MessagePage();
        messagePage.sendFileInQueueNTimesAtOnce("Мультименеджер очередей", managerName1, segmOueueName, "/share/test", 10);
//        messagePage.queueManagerPage();
//        queueManagerMultyPage.searchQueue(managerName1, destQueueName);
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName1, destQueueName, 10, 300000);
        sleep(5000);
        queueManagerMultyPage.queueShouldHaveNMessagesInSentAndTaken(managerName1, agregOueueName, 1000, 1000, 4000);
        String[] queueArrray = {segmOueueName, agregOueueName, destQueueName};
        for (String name : queueArrray) {
            queueManagerMultyPage.sucessDeleteQueue(managerName1, name);
        }
    }

    /**
     * Check clear queue and delete messages separately
     *
     * @throws Exception
     */
    @Description("Check clear queue and delete messages separately")
    @Test
    public void deleteMessagesAndClearQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.successCreateLocalQueue(managerName1, localOueueName);

        queueManagerMultyPage.sendNSavedMessagesToQueue(managerName1, localOueueName, 3);
        queueManagerMultyPage.doubleClickInSpecificQueue(managerName1, localOueueName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.successDeleteNMessages(2);
        specificQueuePage.returnInQueuesListPageMultyButton.click();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName1, localOueueName, 1, 3000);
        queueManagerMultyPage.successClearQueue(managerName1, localOueueName);
        queueManagerMultyPage.sucessDeleteQueue(managerName1, localOueueName);
    }

    @Test
    public void cancelDeleteMessagesAndCancelClearQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.successCreateLocalQueue(managerName1, localOueueName);
        queueManagerMultyPage.sendNSavedMessagesToQueue(managerName1, localOueueName, 3);
        queueManagerMultyPage.doubleClickInSpecificQueue(managerName1, localOueueName);
        int sumMessages = 3;

        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.cancelDeleteNMessages(1);
        specificQueuePage.returnInQueuesListPageMultyButton.click();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName1, localOueueName, 3, 3000);
        queueManagerMultyPage.cancelClearQueue(managerName1, localOueueName, Integer.toString(sumMessages));

    }

    /**
     * Check edit all kind queues in local queue
     *
     * @throws Exception
     */
    @Description("Check edit all kind queues in local queue")
    @Test
    public void editQueuesInLocalQueue() {
        String managerName1 = testName.getMethodName();
        String segmOueueName = commonComponents.createIndividualName(SEGMENT_QUEUE_NAME);
        String agregOueueName = commonComponents.createIndividualName(AGREG_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.createAgregQueue(managerName1, agregOueueName, destQueueName);

        queueManagerMultyPage.searchQueue(managerName1, agregOueueName);
        queueManagerMultyPage.afterSearchQueueType.shouldHave(text("Агрегация"));
        queueManagerMultyPage.createSegregQueue(managerName1, segmOueueName, agregOueueName, 1);
        sleep(500);
        queueManagerMultyPage.searchQueue(managerName1, segmOueueName);
        queueManagerMultyPage.afterSearchQueueType.shouldHave(text("Сегментация"));
        queueManagerMultyPage.searchQueue(managerName1, agregOueueName);
        queueManagerMultyPage.afterSearchQueueType.shouldHave(text("Агрегация"));
        queueManagerMultyPage.editQueueTypeInLocalQueue(managerName1, segmOueueName);
        queueManagerMultyPage.editQueueTypeInLocalQueue(managerName1, agregOueueName);
        String[] queueArrray = {segmOueueName, agregOueueName};
        for (String name : queueArrray) {
            queueManagerMultyPage.sucessDeleteQueue(managerName1, name);
        }
    }

    /**
     * Sent to topic some messages in three ways
     */
    @Description("Sent to topic some messages in three ways")
    @Test
    public void sendNMessagesInTopicInThreeWays() {
        String managerName1 = testName.getMethodName();
        String topicName = commonComponents.createIndividualName(TOPIC_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);

        queueManagerMultyPage.createTopic(managerName1, topicName);
        queueManagerMultyPage.goToMessagePage();
        messagePage = new MessagePage();
        String message = messagePage.createMessageWithBiteSize(100);
        messagePage.sendMessageInTopicNTimes("Мультименеджер очередей", managerName1, topicName, message, 3);
        queueManagerMultyPage.goToManager(managerName1);
        basePage.click($x(String.format(queueManagerMultyPage.topicsOfSpecificManagerButton, managerName1)));
        queueManagerMultyPage.clickOptionInContextMenu(managerName1, topicName, "Отправить сообщение");
        messagePage.sendMessageWithOutCheck(message, 4);
        queueManagerMultyPage.searchTopic(managerName1, topicName);
        basePage.doubleClick($x(String.format(queueManagerMultyPage.afterSearchTopicNameS, topicName)).should(Condition.text(TOPIC_NAME)));
        specifecTopicPage = new SpecifecTopicPage("");
        basePage.click(specifecTopicPage.sendMessage);
        messagePage.sendMessageWithOutCheck(message, 5);
//        messagePage.topicsList();
        queueManagerMultyPage.topicShouldHaveNMessagesInSent(managerName1, topicName, 12);
        queueManagerMultyPage.deleteTopic(managerName1, topicName);
    }

    /**
     * Sent to topic some messages in three ways
     */
    @Description("Editing in each type of queue")
    @Test
    public void editingInEachTypeOfQueue() {
        String managerName1 = testName.getMethodName();
        String localOueueName = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyPage.successCreateLocalQueue(managerName1, localOueueName);
        queueManagerMultyPage.editQueueTypeInSegmetationQueue(managerName1, localOueueName, destQueueName, 1);
        queueManagerMultyPage.editQueueTypeInLocalQueue(managerName1, localOueueName);
        queueManagerMultyPage.editQueueTypeInAgregationQueue(managerName1, localOueueName, destQueueName);
    }

    /**
     * `
     * Download message
     */
    @Description("Download message")
    @Test
    @Parameters(value = {
            "файл",
            "1 без галки",
            "1 с галкой",
            "все",
    })
    public void downloadMessage(String Type) {
        String text = "текст";
        String fileName = "forTests.txt";
        String filePath = "/share/uploadMulty/" + fileName;
        String downloadFilePath = "/home/fesb/Stand/share/download/TestKodirovka.txt";
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        basePage.sout("Сессия: " + sessionId.toString());

        String managerName1 = testName.getMethodName().split("\\(")[0];
        String localOueueName = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        String destQueueName = commonComponents.createIndividualName(DESTINATION_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localOueueName);

        queueManagerMultyPage.goToManager(managerName1);
        basePage.click($x(String.format(QueueManagerMultyPage.queuesOfSpecificManagerButton, managerName1)));

        switch (Type) {
            case "файл":
                basePage.contextClick(queueManagerMultyPage.afterSearchQueueName);
                basePage.click(queueManagerMultyPage.contextSendMessage);
                messagePage.sendFileInQueueNTimes(localOueueName, filePath, 1);
                break;
            case "1 без галки":
            case "1 с галкой":
            case "все":
                queueManagerMultyApi.sendMessgeInQueueAPI(cook1, managerName1, localOueueName, text, 3);
//                messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName1, localOueueName, text, 3);
//                messagePage.sendMessageInQueueNTimesAtOnce(localOueueName,"Менеджер очередей", text, 3);
                break;
            default:
                throw new AssertionError("Пропущена отправка сообщения");

        }
        String downloadedFileName = queueManagerMultyPage.downloadMessage(managerName1, localOueueName, Type);
        switch (Type) {
            case "файл":
                queueManagerMultyPage.checkContentDowloadedFile(sessionId.toString(), text, Type, downloadFilePath, sessionId, fileName, downloadFilePath);
                break;
            case "1 без галки":
            case "1 с галкой":
                queueManagerMultyPage.checkContentDowloadedFile(sessionId.toString(), text, Type, "/home/fesb/Stand/share/forDownloadedMessagesMulty/" + downloadedFileName + ".zip", sessionId, downloadedFileName, downloadedFileName);
                break;
            case "все":
                queueManagerMultyPage.checkContentDowloadedFile(sessionId.toString(), text, Type, "/home/fesb/Stand/share/forDownloadedMessagesMulty/", sessionId, downloadedFileName, downloadedFileName);
                break;
            default:
                throw new AssertionError("Пропущена отправка сообщения");
        }
    }

    /**
     * Move/Copy message
     */
    @Description("Move/Copy message")
    @Test
    @Parameters(value = {
            "Переместить 1",
            "Переместить все",
            "Скопировать 1",
            "Скопировать все",
    })
    public void moveAndCopyMessage(String Type) {
        String text = "текст2";
        int SumMessage = 3;

        String managerName1 = testName.getMethodName().split("\\(")[0];
        String localOueueName1 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        String localOueueName2 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localOueueName1);
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localOueueName2);
        queueManagerMultyApi.sendMessgeInQueueAPI(cook1, managerName1, localOueueName2, text, SumMessage);
        queueManagerMultyPage.moveMessage(managerName1, Type, localOueueName2, localOueueName1, SumMessage, text);
    }

    @Description("Проверка учета приоритета сообщений")
    @Test
    @Parameters(value = {
            "Стойкое",
            "Нестойкое",
    })
    public void priorityMessage(String TypeMessage) {
        String text = "тест Приоритета";
        int sumMessage = 1;
        boolean persistent;
        if (TypeMessage.equals("Стойкое")) {
            persistent = true;
        } else {
            persistent = false;
        }
        String priority1 = "4";
        String priority2 = "6";
        String priority3 = "1";
        String priority4 = "3";

        String domainName = commonComponents.createIndividualName(DOMAIN_NAME);
        String sopsName = commonComponents.createIndividualName(SOPS_NAME);
        String localOueueName1 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        String localOueueName2 = commonComponents.createIndividualName(LOCAL_QUEUE_NAME);
        String managerName1 = testName.getMethodName().split("\\(")[0] + "1";
        String managerName2 = testName.getMethodName().split("\\(")[0] + "2";


        String cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainID = sopsApi.createDomainAPI(cookies, domainName);
        sopsApi.generateSopsIdAPI(cookies, DomainID, sopsName);
        sopsApi.createSopsWith2LocalQueueMultyAPI(cookies, baseUrl(), DomainID, sopsName, sopsName, managerName1, localOueueName1, managerName2, localOueueName2);

        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createManagerAPI(cook1, baseUrl(), managerName2);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cook1, baseUrl(), managerName2);
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName1, QueueManagerMultyApi.typeQueue.local, localOueueName1);
        queueManagerMultyApi.createQueueLocalAPI(cook1, baseUrl(), managerName2, QueueManagerMultyApi.typeQueue.local, localOueueName2);

        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName1, localOueueName1, text, sumMessage, persistent, priority1);
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName1, localOueueName1, text, sumMessage, persistent, priority2);
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName1, localOueueName1, text, sumMessage, persistent, priority3);
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName1, localOueueName1, text, sumMessage, persistent, priority4);
        sopsApi.startDomainAPI(cookies, DomainID);

        queueManagerMultyPage.contentSpecificQueue(managerName2, localOueueName2);
        System.out.println(specificQueuePage.priorityMessageListInTable.toString());
        basePage.compareStringAndElementText(priority2, specificQueuePage.priorityMessageListInTable.get(0));
        basePage.compareStringAndElementText(priority1, specificQueuePage.priorityMessageListInTable.get(1));
        basePage.compareStringAndElementText(priority4, specificQueuePage.priorityMessageListInTable.get(2));
        basePage.compareStringAndElementText(priority3, specificQueuePage.priorityMessageListInTable.get(3));
    }
}