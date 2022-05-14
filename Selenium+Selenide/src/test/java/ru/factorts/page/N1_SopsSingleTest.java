package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import interfaces.speed;
import io.qameta.allure.Description;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import utils.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N1_SopsSingleTest extends Base {

    @Rule
    public TestName testName = new TestName();

    private BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    private static SOPSPage sopsPage;
    private QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    //    private QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    QueueManagerArtemisPage queueManagerArtemisPage = new QueueManagerArtemisPage();
    private QueueManagerArtemisApi queueManagerArtemisApi = new QueueManagerArtemisApi();
    private MessagePage messagePage = new MessagePage();
    static SettingUserPage settingUserPage = new SettingUserPage();
    SpecificMessagePage specificMessagePage;
    SpecificQueuePage specificQueuePage;
    private CreationSOPSPage creationSOPSPage;
    static StaticAPI staticAPI = new StaticAPI();
    CommonComponents commonComponents = new CommonComponents();
    private Api api = new Api();
    static Api staticApi = new Api();
    SOPSApi sopsApi = new SOPSApi();
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();
    DataBasePage dataBasePage = new DataBasePage();
    SettingsPage settingsPage;

    private static final String DOMAIN_NAME = "TEST_DOMAIN",
            SOPS_NAME = "TEST_SOPS",
            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT",
            IMPORT_DOMAIN_NAME = "CONTEXT_MENU_DOMAIN",
            SPEED_XSLT_DOMAIN_NAME = "Домен для проверки speedXslt",
            SOPS_EDIT = "SOPS_EDIT",
            SOPS_EDITED = "SOPS_EDITED",
            SOPS_CLONED = "SOPS_CLONE(Клонированный)",
            SOPS_CLONE = "SOPS_CLONE",
            SOPS_MOVE = "SOPS_MOVE",
            TEST_QUEUE_IN_EDIT = "TEST_QUEUE_IN_EDIT",
            TEST_QUEUE_OUT_EDIT = "TEST_QUEUE_OUT_EDIT",
            TEST_QUEUE_IN_CLONE = "TEST_QUEUE_IN_CLONE",
            TEST_QUEUE_OUT_CLONE = "TEST_QUEUE_OUT_CLONE",
            TEST_QUEUE_IN_MOVE = "TEST_QUEUE_IN_MOVE",
            TEST_QUEUE_OUT_MOVE = "TEST_QUEUE_OUT_MOVE",
            MY_EXAMPLES_DOMAIN_NAME = "MY_EXAMPLES",
            MY_EXAMPLE_SOAP_IN_QUEUE_NAME = "MY.EXAMPLE.SOAP.IN",
            MY_EXAMPLE_SOAP_OUT_QUEUE_NAME = "MY.EXAMPLE.SOAP.OUT",
            MY_EXAMPLE_SOAP_SOPS_NAME = "MY.EXAMPLE.SOAP",
            LocalQueueForEnvironment1 = "Локальная очередь 1",
            LocalQueueForEnvironment2 = "Локальная очередь 2",
            DomainNameForStopTest = "Домен 1";

    String SopsForEnvironment1 = commonComponents.createIndividualName("СОПС-");
    String cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
    String domainName = commonComponents.createIndividualName("domain");
    String sopsName = commonComponents.createIndividualName("СОПС-");
    String localQueueName1 = commonComponents.createIndividualName("localQueue");
    String localQueueName2 = commonComponents.createIndividualName("localQueue");
    String localQueueArtemis1 = null;
    String localQueueArtemis2 = null;
    String localQueueInName = null;
    String localQueueOutName = null;
    String managerName = "QM";

    String addConfigurationName = commonComponents.createIndividualName("addConfigurationName");
    String addConfigurationName2 = commonComponents.createIndividualName("addConfigurationName");

    @BeforeClass
    public static void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        sopsPage = new SOPSPage();
        contextMenuTest1Import();
        myExamplesDomain11CreateDomainRunStop();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/speedXSLT.zip", SPEED_XSLT_DOMAIN_NAME);
        sopsPage.startDomain(SPEED_XSLT_DOMAIN_NAME);
        Selenide.closeWebDriver();
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());

        logs = WebDriverRunner.getWebDriver().manage().logs();
        sopsPage = new SOPSPage();
    }

    @After
    public void afterTest() {
        System.out.println(testName.getMethodName());
        if (testName.getMethodName().equals("checkLocalQueue2AndLocalQueue2")) {
            queueManagerArtemisApi.clearQueueAPI(cook, localQueueArtemis1);
            queueManagerArtemisApi.clearQueueAPI(cook, localQueueArtemis2);
        }

        if (testName.getMethodName().contains("speedWithTwoLocalQueue")) {
            try {
                queueManagerMultyApi.deleteQueueAPI(cook, baseUrl(), "QM", localQueueInName);
            } catch (AssertionError e) {
                basePage.sout("Очереди пусты, ничего страшного");
            }
            try {
                queueManagerMultyApi.deleteQueueAPI(cook, baseUrl(), "QM", localQueueOutName);
            } catch (AssertionError e) {
                basePage.sout("Очереди пусты, ничего страшного");
            }
        }

        if (testName.getMethodName().contains("speedXslt")) {
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "XSLT.THREADS.IN");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "MODEL.XML.TEST.IN");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "XSLT.TEST.OUT");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "MODEL.XML.TEST.OUT");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
        }

        if (testName.getMethodName().contains("speedXML")) {
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "XML.FILE.XPATH");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "XML.FILE.MODELS");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
            try {
                queueManagerMultyApi.clearQueueAPI(cook, "QM", "XML.FILE.GROOVY");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
        }
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "true|true", "true|true", "false|false", "false|false", "true|true", "false|false", "false|false", "false|false");

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-1-1");
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-1-2");
        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    /**
     * Import test domain, this test must be first
     */

    @Description("Import test domain, this test must be first")
    public static void contextMenuTest1Import() {
        new IndexPage().sopsPage();
        sleep(2000);
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/sops_context_menu.xml", IMPORT_DOMAIN_NAME);
        sopsPage.startDomain(IMPORT_DOMAIN_NAME);
    }

    /**
     * Edit sops name and check his working capacity
     */

    @Description("Edit sops name and check his working capacity")
    @Test
    public void contextMenuTest2Edit() {
        new IndexPage().sopsPage();
        sopsPage.editSOPSName(IMPORT_DOMAIN_NAME, SOPS_EDIT, SOPS_EDITED);
        sopsPage.sendNSavedMessages(SOPS_EDITED, 2);
        queueManagerMultyPage.queueCheckAllParameters(managerName, TEST_QUEUE_IN_EDIT, "0", "1", "2", "2", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, TEST_QUEUE_OUT_EDIT, "2", "0", "2", "0", "Локальная", "", "");
        queueManagerMultyPage.sopsPage();
        sopsPage.deleteSOPS(IMPORT_DOMAIN_NAME, SOPS_EDITED);
        queueManagerMultyPage.deleteSeveralQueues(managerName, TEST_QUEUE_OUT_EDIT, TEST_QUEUE_IN_EDIT);
    }

    /**
     * Clone sops and check his working capacity
     */

    @Description("Clone sops and and check his working capacity")
    @Test
    public void contextMenuTest3Clone() {
        new IndexPage().sopsPage();
        sopsPage.cloneSOPS(IMPORT_DOMAIN_NAME, SOPS_CLONE);
        sopsPage.sendNSavedMessages(SOPS_CLONED, 2);
        queueManagerMultyPage.queueShouldHaveNConsumersAndNMessagesInSentAndTaken(managerName, TEST_QUEUE_IN_CLONE, 2, 2, 2);
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName, TEST_QUEUE_OUT_CLONE, 2);
        queueManagerMultyPage.sopsPage();
        sopsPage.deleteSOPS(IMPORT_DOMAIN_NAME, SOPS_CLONED);
        sopsPage.deleteSOPS(IMPORT_DOMAIN_NAME, SOPS_CLONE);
        queueManagerMultyPage.deleteSeveralQueues(managerName, TEST_QUEUE_IN_CLONE, TEST_QUEUE_OUT_CLONE);
    }

    /**
     * Move sops and check his working capacity
     */

    @Description("Move sops and check his working capacity")
    @Test
    public void contextMenuTest4Move() {
        String domainNameTo = commonComponents.createIndividualName(DOMAIN_NAME);

        new IndexPage().sopsPage();
        sopsPage.createDomain(domainNameTo);
        sopsPage.startDomain(domainNameTo);
        sopsPage.moveSOPS(IMPORT_DOMAIN_NAME, SOPS_MOVE, domainNameTo);
        sopsPage.sendNSavedMessages(SOPS_MOVE, 3);
        queueManagerMultyPage.queueCheckAllParameters(managerName, TEST_QUEUE_IN_MOVE, "0", "1", "3", "3", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, TEST_QUEUE_OUT_MOVE, "3", "0", "3", "0", "Локальная", "", "");
    }

    /**
     * Create and run myExamples domain
     */
    @Description("Create and run myExamples domain")
    public static void myExamplesDomain11CreateDomainRunStop() {
        sopsPage.createDomain(MY_EXAMPLES_DOMAIN_NAME);
        sopsPage.startDomain(MY_EXAMPLES_DOMAIN_NAME);
    }

    /**
     * Create and check MY.EXAMPLE.SOAP SOPS
     */
    @Description("Create and check MY.EXAMPLE.SOAP SOPS")
    @Test
    public void myExamplesDomain12createAndCheckMyExampleSOAP() {
        String message = "<s:Envelope xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\"\n" +
                "  xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\">\n" +
                "  <s:Header> \n" +
                "    <wsa:MessageID>uuid:aaaabbbb-cccc-dddd-eeee-ffffffffffff</wsa:MessageID>\n" +
                "    <wsa:To>localmq://MY.EXAMPLE.SOAP.OUT</wsa:To>\n" +
                "  </s:Header>\n" +
                "  <s:Body>\n" +
                "       Example Test Message\n" +
                "  </s:Body>\n" +
                "</s:Envelope>";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + "QM" + "|" + MY_EXAMPLE_SOAP_IN_QUEUE_NAME};
        String[] point1_2 = new String[]{"Выход|Выход в таблицу маршрутов", "параметры таблицы маршрутов|MY_EXAMPLE_SOAP|Маршрут по умолчанию-Ручной ввод-'localmq://MY.EXAMPLE.SOAP.DEFAULT'-|(.*)-Ручной ввод-'$FESB_SRC_ADDR'", "селект|Маршрутизировать по следующему типу|xpath", "инпут|Маршрутизировать по следующему значению|//S:Header/wsa:To", "селект|Тип таблицы маршрутов|Отправить по маршрутам", "селект|Таблица маршрутов|MY_EXAMPLE_SOAP", "чек-бокс2|Использовать только первый подходящий маршрут|вкл", "чек-бокс2|Игнорировать повторные маршруты|вкл"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] schemesAll_1 = {"Пространство имен XML| ", "S|http://www.w3.org/2003/05/soap-envelope"};
        String[] schemesAll_2 = {"Пространство имен XML| ", "wsa|http://schemas.xmlsoap.org/ws/2004/08/addressing"};

        sopsPage.createAdditionalConfiguration(MY_EXAMPLES_DOMAIN_NAME, schemesAll_1);
        sopsPage.createAdditionalConfiguration(MY_EXAMPLES_DOMAIN_NAME, schemesAll_2);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(MY_EXAMPLES_DOMAIN_NAME, MY_EXAMPLE_SOAP_SOPS_NAME, pointAll_1);

        basePage.goToMessagePage();
        messagePage = new MessagePage();
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName, MY_EXAMPLE_SOAP_IN_QUEUE_NAME, message, 3);
        queueManagerMultyPage.queueCheckAllParameters(managerName, MY_EXAMPLE_SOAP_IN_QUEUE_NAME, "0", "1", "3", "3", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, MY_EXAMPLE_SOAP_OUT_QUEUE_NAME, "3", "0", "3", "0", "Локальная", "", "");
    }


    /**
     * Create and check MY.EXAMPLE.XSL SOPS
     */
    @Description("Create and check MY.EXAMPLE.XSL SOPS")
    @Test
    public void myExamplesDomain13createAndCheckMyExampleXSL() {
        String queueInName = "MY.EXAMPLE.XSL.IN";
        String queueOutName = "MY.EXAMPLE.XSL.OUT";
        String sopsName = "MY.EXAMPLE.XSL";
        String transaformationText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:param name=\"JMSMessageID\"></xsl:param><xsl:output omit-xml-declaration=\"no\"/><xsl:template match=\"node()|@*\"><xsl:copy><xsl:apply-templates select=\"node()|@*\"/></xsl:copy></xsl:template><xsl:template match=\"Root\"><S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><S:Header><wsa:MessageID><xsl:value-of select=\"$JMSMessageID\" /></wsa:MessageID><wsa:To>localmq://EXAMPLE.XSL.OUT</wsa:To></S:Header><S:Body><AddPreson><name><xsl:value-of select=\"name\" /></name></AddPreson></S:Body></S:Envelope></xsl:template></xsl:stylesheet>";
        String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                " <Root><age>55</age><city>Moscow</city><name>Vova</name></Root>";
        sopsPage = new SOPSPage();
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "Мультименеджер очередей", managerName, queueInName, null, null);
        creationSOPSPage.addXSLTTransformation(transaformationText);
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Мультименеджер очередей", managerName, queueOutName, null, null);
        creationSOPSPage.changeSOPSProperties(true, false);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);
        sopsPage.goToMessagePage();
        messagePage = new MessagePage();
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName, queueInName, message, 3);
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueInName, "0", "1", "3", "3", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueOutName, "3", "0", "3", "0", "Локальная", "", "");

        queueManagerMultyPage.contentSpecificQueue(managerName, queueOutName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.openFirstMessage();
        specificMessagePage = new SpecificMessagePage("");
        String recivedMessage = specificMessagePage.messageBody.getText();
        String messageID = commonComponents.findString(recivedMessage, "ID:.*?<");
        String recivedMessageMustBe = String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?><S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\"><S:Header><wsa:MessageID>%s</wsa:MessageID><wsa:To>localmq://EXAMPLE.XSL.OUT</wsa:To></S:Header><S:Body><AddPreson><name>Vova</name></AddPreson></S:Body></S:Envelope>", messageID);
        basePage.compareStringAndString(recivedMessageMustBe, recivedMessage);
//        commonComponents.checkEquality(recivedMessage, recivedMessageMustBe);
        basePage.click(specificMessagePage.returnInQueueMultyButton);
        basePage.click(specificQueuePage.returnInQueuesListPageMultyButton);
        queueManagerMultyPage.sopsPage();
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
//        sopsPage.queueListPage();
        queueManagerMultyPage.deleteSeveralQueues(managerName, queueInName, queueOutName);
    }

    /**
     * Create and check MY.EXAMPLE.JSON2XML SOPS
     */
    @Description("Create and check MY.EXAMPLE.JSON2XML SOPS")
    @Test
    public void myExamplesDomain14createAndCheckMyExampleJSON2XML() {
        String queueInName = "MY.EXAMPLE.JSON2XML.IN";
        String queueOutName = "MY.EXAMPLE.JSON2XML.OUT";
        String sopsName = "MY.EXAMPLE.JSON2XML";
        String jsonMessage = "{ \"name\":\"Vova\", \"age\":55, \"city\":\"Moscow\", \"lastname\":\"Ivanov\"}";
        String xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Root><age>55</age><city>Moscow</city><lastname>Ivanov</lastname><name>Vova</name></Root>";
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "Мультименеджер очередей", managerName, queueInName, null, null);
        creationSOPSPage.treatmentAddFormatConvertion("JSON → XML");
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Мультименеджер очередей", managerName, queueOutName, null, null);
        basePage.click(creationSOPSPage.sopsPropertiesIcon);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);
        sopsPage.goToMessagePage();
        messagePage = new MessagePage();
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName, queueInName, jsonMessage, 3);
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueInName, "0", "1", "3", "3", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueOutName, "3", "0", "3", "0", "Локальная", "", "");


//        queueManagerPage.queueCheckAllParameters(queueInName, "0", "1", "3", "3", "Локальная", "");
//        queueManagerPage.queueCheckAllParameters(queueOutName, "3", "0", "3", "0", "Локальная", "");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, queueOutName, xmlMessage);
//        queueManagerPage.sopsPage();
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
//        sopsPage.queueListPage();
        queueManagerMultyPage.deleteSeveralQueues(managerName, queueInName, queueOutName);
    }

    /**
     * Create and check MY.GET.CURRENCY SOPS
     */
    @Description("Create and check MY.GET.CURRENCY SOPS")
    @Test
    public void myExamplesDomain15createAndCheckMyGetCurrency() {
        String queueInName = "MY.EXAMPLE.CURRENCY.IN";
        String queueOutName = "MY.EXAMPLE.CURRENCY.OUT";
        String sopsName = "MY.GET.CURRENCY";
        String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <GetCursOnDate xmlns=\"http://web.cbr.ru/\">\n" +
                "      <On_date>2017-01-30</On_date>\n" +
                "    </GetCursOnDate>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "Мультименеджер очередей", managerName, queueInName, null, null);
        creationSOPSPage.treatmentAddInstallHeadings("Content-Type", "simple", "По умолчанию", "text/xml");
        creationSOPSPage.treatmentAddEnrichment("Точки выхода", "jetty://http://cbr.ru/DailyInfoWebServ/DailyInfo.asmx");
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Мультименеджер очередей", managerName, queueOutName, null, null);
        creationSOPSPage.changeSOPSProperties(true, false);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);
        sopsPage.goToMessagePage();
        messagePage = new MessagePage();
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", managerName, queueInName, message, 3);
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueInName, "0", "1", "3", "3", "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, queueOutName, "3", "0", "3", "0", "Локальная", "", "");

//        queueManagerPage.queueCheckAllParameters(queueInName, "0", "1", "3", "3", "Локальная", "");
//        queueManagerPage.queueCheckAllParameters(queueOutName, "3", "0", "3", "0", "Локальная", "");
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, queueOutName, "Фунт стерлингов Соединенного королевства");
//        queueManagerPage.sopsPage();
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
//        sopsPage.queueListPage();
        queueManagerMultyPage.deleteSeveralQueues(managerName, queueInName, queueOutName);
    }

    //    /**
//     * Create and check MY.HTTP_SOAP SOPS
//     */
    @Description("Create and check MY.HTTP_SOAP SOPS")
    @Test
    public void myExamplesDomain16createAndCheckMyHTTPSOAP() throws Exception {
        String domainName = commonComponents.createIndividualName(testName.getMethodName() + "-");
        String sopsName = "MY.HTTP_SOAP";
        String message = "<S:Envelope xmlns:S=\"http://www.w3.org/2003/05/soap-envelope\"\n" +
                "  xmlns:wsa=\"http://schemas.xmlsoap.org/ws/2004/08/addressing\">\n" +
                "  <S:Header> \n" +
                "    <wsa:MessageID>uuid:aaaabbbb-cccc-dddd-eeee-ffffffffffff</wsa:MessageID>\n" +
                "    <wsa:To>localmq://EXAMPLE.SOAP.OUT?disableReplyTo=true</wsa:To>\n" +
                "  </S:Header>\n" +
                "  <S:Body>\n" +
                "       Example Test Message\n" +
                "  </S:Body>\n" +
                "</S:Envelope>";
        String[] responseArray;

        String[] point1_0 = new String[]{"Настройки СОПС|Настройки СОПС|Описание|Обработчик ошибок домена", "чек-бокс2|Транзакционность|вкл", "селект|Политика распространения транзакций|JMS_TRANSACTED", "чек-бокс2|Кэширование потока|выкл"};
        String[] point1_1 = new String[]{"Вход|HTTP|0.0.0.0:" + portForMyExamplesDomain16createAndCheckMyHTTPSOAP + "/soap2"};
        String[] point1_2 = new String[]{"Выход|Выход в таблицу маршрутов", "параметры таблицы маршрутов|MY_HTTP_SOAP|Маршрут по умолчанию-Ручной ввод-'localmq://MY.HTTP.SOAP.DEFAULT'-|(.*)-Ручной ввод-'$FESB_SRC_ADDR'", "селект|Маршрутизировать по следующему типу|xpath", "инпут|Маршрутизировать по следующему значению|//S:Header/wsa:To", "селект|Тип таблицы маршрутов|Отправить по маршрутам", "селект|Таблица маршрутов|MY_HTTP_SOAP", "чек-бокс2|Использовать только первый подходящий маршрут|выкл"};
        String[][] pointAll_1 = {point1_0, point1_1, point1_2};

        String[] schemesAll_1 = {"Пространство имен XML| ", "S|http://www.w3.org/2003/05/soap-envelope"};
        String[] schemesAll_2 = {"Пространство имен XML| ", "wsa|http://schemas.xmlsoap.org/ws/2004/08/addressing"};

        String cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainID = sopsApi.createDomainAPI(cook, domainName);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName, sopsName, pointAll_1);

        sopsPage.createAdditionalConfiguration(domainName, schemesAll_1);
        sopsPage.createAdditionalConfiguration(domainName, schemesAll_2);
        sopsApi.startDomainAPI(cook, DomainID);

        sleep(10000);
        responseArray = commonComponents.httpRequestPost("http://192.168.57.240:" + portForMyExamplesDomain16createAndCheckMyHTTPSOAP + "/soap2", message);
        commonComponents.checkEquality(responseArray[0], "200");
        basePage.click(sopsPage.listSOPSTab);
        sopsPage.sopsShouldHasNInProcessedQuantity(sopsName, 1, 10000);
    }

    //
//    /**
//     * Create and check MY.HTTP_JSON2XML SOPS
//     */
    @Description("Create and check MY.HTTP_JSON2XML SOPS")
    @Test
    public void myExamplesDomain17createAndCheckMyHTTP_JSON2XML() throws Exception {
        String queueOutName = "MY.HTTP_JSON2XML.OUT";
        String sopsName = "MY.HTTP_JSON2XML";
        String jsonMessage = "{ \"name\":\"Vova\", \"age\":55, \"city\":\"Moscow\", \"lastname\":\"Ivanov\"}";
        String xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Root><age>55</age><city>Moscow</city><lastname>Ivanov</lastname><name>Vova</name></Root>";
        String[] responseArray;
        sopsPage = new SOPSPage();
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addHttpInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "0.0.0.0:" + portForMyExamplesDomain17createAndCheckMyHTTP_JSON2XML + "/json2xml2");
        creationSOPSPage.treatmentAddFormatConvertion("JSON → XML");
        creationSOPSPage.addLocalQueueWithParameterInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, managerName, queueOutName, "Отключение синхронного ответа JMS-получателю", true);
        creationSOPSPage.changeSOPSProperties(true, true);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);
        responseArray = commonComponents.httpRequestPost("http://192.168.57.240:" + portForMyExamplesDomain17createAndCheckMyHTTP_JSON2XML + "/json2xml2", jsonMessage);
        commonComponents.checkEquality(responseArray[0], "200", responseArray[1], xmlMessage + "\n");
        sopsPage.sopsShouldHasNInProcessedQuantity(sopsName, 1, 10000);
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
//        sopsPage.queueListPage();
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName, queueOutName, 1);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, queueOutName, xmlMessage);
        queueManagerMultyPage.deleteSeveralQueues(managerName, queueOutName);
    }

    //
//    /**
//     * Create and check MY.HTTP_JSON2XML SOPS
//     */
    @Description("Create and check MY.HTTP_XML2JSON SOPS")
    @Test
    public void myExamplesDomain18createAndCheckMyHTTP_XML2JSON() throws Exception {
        String queueOutName = "MY.HTTP_XML2JSON.OUT";
        String sopsName = "MY.HTTP_XML2JSON";
        String jsonMessage = "{\"Root\":{\"age\":\"55\",\"city\":\"Moscow\",\"name\":\"Vova\"}}";
        String xmlMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Root><age>55</age><city>Moscow</city><name>Vova</name></Root>";
        String[] responseArray;
        sopsPage = new SOPSPage();
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addHttpInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "0.0.0.0:" + portForMyExamplesDomain18createAndCheckMyHTTP_XML2JSON + "/XML2JSON2");
        creationSOPSPage.treatmentAddFormatConvertion("XML → JSON");
        creationSOPSPage.addLocalQueueWithParameterInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, managerName, queueOutName, "Отключение синхронного ответа JMS-получателю", true);
        creationSOPSPage.changeSOPSProperties(true, true);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);
        responseArray = commonComponents.httpRequestPost("http://192.168.57.240:" + portForMyExamplesDomain18createAndCheckMyHTTP_XML2JSON + "/XML2JSON2", xmlMessage);
        commonComponents.checkEquality(responseArray[0], "200", responseArray[1], jsonMessage + "\n");
        sopsPage.sopsShouldHasNInProcessedQuantity(sopsName, 1, 10000);
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
//        sopsPage.queueListPage();
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queuesList();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName, queueOutName, 1);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, queueOutName, jsonMessage);
        queueManagerMultyPage.deleteSeveralQueues(managerName, queueOutName);
    }

    //    /**
//     * Create and check MY.GET.CURRENCY.HTTP SOPS
//     */
    @Description("Create and check MY.GET.CURRENCY.HTTP SOPS")
    @Test
    public void myExamplesDomain19createAndCheckMyGETCurrencyHTTP() throws Exception {
        String sopsName = "MY.GET.CURRENCY.HTTP";
        String message = "<?xml version=\\\"1.0\\\" encoding=\\\"utf-8\\\"?>" +
                "<soap:Envelope xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\" xmlns:soap=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\">" +
                "  <soap:Body>" +
                "    <GetCursOnDate xmlns=\\\"http://web.cbr.ru/\\\">" +
                "      <On_date>2017-01-30</On_date>" +
                "    </GetCursOnDate>" +
                "  </soap:Body>" +
                "</soap:Envelope>";
        String responseArray;
        sopsPage.clickToAddSOPSButton(MY_EXAMPLES_DOMAIN_NAME);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.inputPointAddHTTPWith2Parameters("0.0.0.0:" + portForMyExamplesDomain19createAndCheckMyGETCurrencyHTTP + "/currency", "Сопоставление префикса URI", true, "Пользовательский параметр", "bridgeEndpoint.true");
        creationSOPSPage.treatmentAddClearHeadings("*");
        creationSOPSPage.treatmentAddInstallHeadings("Content-Type", "simple", "По умолчанию", "text/xml");
        creationSOPSPage.treatmentAddEnrichment("Точки выхода", "jetty://http://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002");
        creationSOPSPage.changeSOPSProperties(true, true);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        basePage.elementShouldNotBeVisible(creationSOPSPage.notificationPreserved);
        basePage.click(creationSOPSPage.returnToSOPSList);

        responseArray = api.postMessageToHttpApi(message, "http://192.168.57.240:" + portForMyExamplesDomain19createAndCheckMyGETCurrencyHTTP + "/currency");

        Assert.assertTrue(responseArray.contains("Фунт стерлингов Соединенного королевства"));

//        responseArray = commonComponents.httpRequestPost("http://192.168.57.240:" + portForMyExamplesDomain19createAndCheckMyGETCurrencyHTTP + "/currency", message);
        //  commonComponents.checkEqualityAndContains(responseArray[0], "200", responseArray[1], "Фунт стерлингов Соединенного королевства");
        sopsPage.sopsShouldHasNInProcessedQuantity(sopsName, 1, 10000);
        sopsPage.deleteSOPS(MY_EXAMPLES_DOMAIN_NAME, sopsName);
    }

    @Description("Пустышка, без нее не запускаются категории тестов в классе")
    @Test()
    @Category(speed.class)
    public void dummy() {
    }

    /**
     * Create and check SOPS with two local queue
     */
//    @Description("Create and check SOPS with two local queue")
    @Test()
    @Category(speed.class)
    @Parameters(value = {
            "1| 45 | 2800 | нестойкое",
            "10| 20 |9800 | нестойкое",
            "1| 65 | 1600 | стойкое",
            "10| 35 |3100 | стойкое",
    })
    public void speedWithTwoLocalQueue(String numberThreads, long optimalTimeSops1, float optimalSpeedSops1, String persistenceOfMessage) {
        String domainName = ("Домен для проверки СОПС Лок.оч-Лок.оч");
        String sopsName = ("СОПС Лок.оч-Лок.оч");
        localQueueInName = ("Очередь 1 для проверки СОПС Лок.оч-Лок.оч");
        localQueueOutName = ("Очередь 2 для проверки СОПС Лок.оч-Лок.оч");

        int sumMessage = 100000;
//        System.out.println("Темература процессора = " + basePage.getCPUTemperature() + "'C");
        basePage.click(basePage.generalInformationTab);
        String versionFesb = generalInformationPage.productVersion.getText();

        if (numberThreads.equals("1") && persistenceOfMessage.equals("нестойкое")) {
            sopsPage = new SOPSPage();
            sopsPage.createDomain(domainName);
            sopsPage.clickToAddSOPSButton(domainName);
            creationSOPSPage = new CreationSOPSPage();
            creationSOPSPage.createSOPSWithTwoLocalQueues(sopsName, "Мультименеджер очередей", "QM", localQueueInName, localQueueOutName, null, null);
        }

        sopsPage = new SOPSPage();
        sopsPage.stopDomain(domainName);
        sopsPage.editProperties(domainName, null, null, numberThreads, numberThreads, null, null, null, null, null, null, null, null, null, null, null, null, null, numberThreads, null, numberThreads, null, null);

        //        if (numberThreads.equals("1") && persistenceOfMessage.equals("нестойкое")) {
//        } else {
//            basePage.click(sopsPage.restartDomainButtonInPopUp);
//        }


        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.goToMessagePage();
        messagePage = new MessagePage();
        String message = messagePage.createMessageWithBiteSize(100);
        long timeStartSentMessages = System.currentTimeMillis();

        Object[] parametersOfMessage = new Object[]{MessagePage.ContentOfMessage.TEXT, message, persistenceOfMessage};
        Object[] parametersOfSent = new Object[]{Integer.toString(sumMessage)};
        messagePage.sendMessageThroughForm(localQueueInName, managerName, null, parametersOfMessage, parametersOfSent);

//        queueManagerPage = new QueueManagerPage();
//        messagePage.queueListPage();
        queueManagerMultyPage.searchQueue(managerName, localQueueInName);
        queueManagerMultyPage.autoUpdateOn();
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(exactText(Integer.toString(sumMessage)), 120000);
        long timeFinishSentMessages = System.currentTimeMillis();
        System.out.println("Время отправки сообщений: " + timeStartSentMessages);
        System.out.println("Время получения сообщений: " + timeFinishSentMessages);
        long difference = (timeFinishSentMessages - timeStartSentMessages) / 1000;
        long optimalTimeForSentMessages = 40;
        String messageForWriteToFile = "Отправка сообщений в очередь :\nРазница в секундах = " + difference + " при норме ~ " + optimalTimeForSentMessages + " сек\n";
        basePage.sout(messageForWriteToFile);
        basePage.writeMessageToFile(messageForWriteToFile, "/home/fesb/Stand/speedStatistic_LocQ-LocQ.txt");

        sopsPage.startDomain(domainName);
        sopsPage.searchSops(sopsName);
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessage)), 240000);
        sopsPage.stopSOPS(domainName, sopsName);
        sopsPage.checkSpeedAndWriteToFile(sopsName, versionFesb, optimalTimeSops1, optimalSpeedSops1, "speedStatistic_LocQ-LocQ.txt");

        if (numberThreads.equals("10") && persistenceOfMessage.equals("стойкое")) {
            basePage.writeMessageToFile("-------------------------------------------------------------------------------------\n", "/home/fesb/Stand/speedStatistic_LocQ-LocQ.txt");
        }
//        queueManagerPage = new QueueManagerPage();
//        messagePage.queueListPage();
        queueManagerMultyPage.searchQueue(managerName, localQueueInName);

        queueManagerMultyPage.queueCheckAllParameters(managerName, localQueueInName, "0", "0", Integer.toString(sumMessage), Integer.toString(sumMessage), "Локальная", "", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, localQueueOutName, Integer.toString(sumMessage), "0", Integer.toString(sumMessage), "0", "Локальная", "", "");
//        queueManagerMultyPage.queueCheckAllParameters(localQueueInName, "0", "0", Integer.toString(sumMessage), Integer.toString(sumMessage), "Локальная", "");
//        queueManagerMultyPage.queueCheckAllParameters(localQueueOutName, Integer.toString(sumMessage), "0", Integer.toString(sumMessage), "0", "Локальная", "");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueOutName, message);
    }

    @Test
    @Category(speed.class)
    public void checkLocalQueue2AndLocalQueue2() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String sopsName2 = "CОПС для проверки " + testName.getMethodName() + "-2";
        localQueueArtemis1 = testName.getMethodName() + "-1";
        localQueueArtemis2 = testName.getMethodName() + "-2";

        long optimalTimeValue = 20;
        float optimalSpeedValue = 8000;
        int sumMessage = 100000;
        String message = messagePage.createMessageWithBiteSize(100);

        String[] point2_1 = new String[]{"Вход|Локальная очередь РМО|" + localQueueArtemis1};
        String[] point2_2 = new String[]{"Выход|Локальная очередь РМО|" + localQueueArtemis2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String[] propertySops2 = {sopsName2, "Описание", "2", String.valueOf(CreationSOPSPage.propertySOPS.errorHandlingInsideSopsOff), "Обработчик ошибок домена", String.valueOf(CreationSOPSPage.propertySOPS.autostartOff), String.valueOf(CreationSOPSPage.propertySOPS.streamCachingOff), String.valueOf(CreationSOPSPage.propertySOPS.tracingOff), CreationSOPSPage.propertySOPS.transactionalityOff + "|" + " "};

        basePage.click(basePage.generalInformationTab);
        String versionFesb = generalInformationPage.productVersion.getText();

        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.switchModuleAPI(Cook1, baseUrl(), "factor-qme", "activate");
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);

        sopsPage = new SOPSPage();
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, propertySops2, pointAll_2);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueArtemisAPI(Cook1, localQueueArtemis1, message, sumMessage);
        sopsPage.sendMessage(sopsName2, 1, message);

        sopsPage.startSOPS(domainName1, sopsName2);
        sopsPage.searchSops(sopsName2);
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessage + 1)), 240000);
        sopsPage.stopSOPS(domainName1, sopsName2);
        sopsPage.checkSpeedAndWriteToFile(sopsName1, versionFesb, optimalTimeValue, optimalSpeedValue, "speedStatistic_Artemis-Artemis.txt");

        sopsPage.startSOPS(domainName1, sopsName2);
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemis2, localQueueArtemis2, Integer.toString(sumMessage + 1), "0", Integer.toString(sumMessage + 1), "0", "ANYCAST");
    }

    @Test
    public void checkSopsFtpAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки FtpAndLocalQueue-");
        String sopsName1 = "CОПС для проверки FtpAndLocalQueue-1";
        String sopsName2 = "CОПС для проверки FtpAndLocalQueue-2";
        String localQueueForSopsName1 = "FtpAndLocalQueue-1";
        String localQueueForSopsName2 = "FtpAndLocalQueue-2";
        String ftpAdressSops = "192.168.57.240:21";
        String managerName1 = testName.getMethodName();


        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|FTP|" + ftpAdressSops, "селект-в-параметрах|Имя пользователя|julien", "селект-в-параметрах|Пароль|test", "чекбокс-в-параметрах|Пассивный режим соединения|вкл"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|FTP|" + ftpAdressSops, "селект-в-параметрах|Имя пользователя|julien", "селект-в-параметрах|Пароль|test", "чекбокс-в-параметрах|Пассивный режим соединения|вкл", "чекбокс-в-параметрах|Удалить файл|вкл"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        queueManagerMultyApi.createManagerAPI(Cook1, baseUrl(), managerName1);
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        open(baseUrl());
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName1, localQueueForSopsName1, "text", 1);
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
    }

    @Test
    public void checkSopsJmsAndJms() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки JmsAndJms-");
        String port1 = portForCheckSopsJmsAndJms1;
        String port2 = portForCheckSopsJmsAndJms2;
        String sopsName1 = "CОПС для проверки JmsAndJms-1";
        String sopsName2 = "CОПС для проверки JmsAndJms-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки JmsAndJms-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки JmsAndJms-2";
        String localQueueForSopsName3 = "Локальная очередь для проверки JmsAndJms-3";
        String localQueueForSopsName4 = "Локальная очередь для проверки JmsAndJms-4";
        String managerName1 = testName.getMethodName();
        String userName = managerName1;
        String userPassword = managerName1;
        String userGroups = "[\"read\", \"admin\", \"write\"]";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|JMS|" + localQueueForSopsName2, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|JMS|" + localQueueForSopsName3, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName4};
        String[][] pointAll_2 = {point2_1, point2_2};

        String[] scheme1 = {"Фабрика соединений Менеджера очередей|" + addConfigurationName + "|Обычная", "Сетевое соединение|${constantHost}|" + port2};
//        String[][] schemesAll = {scheme1};

        Object[] constant1 = {"constantHost", "fesb-test-1-2", false};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(Cook1, baseUrl(), managerName1);
        queueManagerMultyApi.createManagerAPI(Cook2, baseUrl_2(), managerName1, true, port2);
        queueManagerMultyApi.editConfigAutorisationSettingsMqAPI(Cook2, baseUrl_2(), managerName1, true, true, "anonymous", "anonymous", false);
//        queueManagerMultyApi.createUserAPI(Cook1, baseUrl(), managerName1, userName, userPassword, userGroups);
//        queueManagerMultyApi.createUserAPI(Cook2, baseUrl(), managerName1, userName, userPassword, userGroups);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);

        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsPage.createAdditionalConfiguration(domainName1, scheme1);

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName1, "text", 1, false, "4");
//        queueManagerApi.sendMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "", 1, false, "4");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName1, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl_2());

        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName1, localQueueForSopsName3, "text", 1, false, "4");
//        queueManagerApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), localQueueForSopsName3, "", 1, false, "4");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName4, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName4, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkSopsJmsAndJmsAndCachedMq() {
        String port2 = portForcheckSopsJmsAndJmsAndCachedMq;
        String domainName1 = commonComponents.createIndividualName("Домен для проверки JmsAndJms-");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String managerName2 = testName.getMethodName();

        String[] point1_1 = new String[]{"Вход|JMS|" + localQueueForSopsName1, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[] point1_2 = new String[]{"Выход|JMS|" + localQueueForSopsName2, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName2};
        String[][] pointAll_1 = {point1_1, point1_2};
        String[] scheme1 = {"Фабрика соединений Менеджера очередей|" + addConfigurationName + "|С кэшированными соединениями", "1|true|true|false|null|null", " Сетевое соединение|${constantHost}|61616"};
        String[] scheme2 = {"Фабрика соединений Менеджера очередей|" + addConfigurationName2 + "|С кэшированными соединениями", "1|true|true|false|null|null", " Сетевое соединение|${constantHost}|" + portForcheckSopsJmsAndJmsAndCachedMq};
        Object[] constant1 = {"constantHost", "127.0.0.1", false};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(Cook1, baseUrl(), managerName2, true, port2);
        queueManagerMultyApi.editConfigAutorisationSettingsMqAPI(Cook1, baseUrl(), managerName, true, true, "anonymous", "anonymous", false);
        queueManagerMultyApi.editConfigAutorisationSettingsMqAPI(Cook1, baseUrl(), managerName2, true, true, "anonymous", "anonymous", false);
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);

        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage.createAdditionalConfiguration(domainName1, scheme1);
        sopsPage.createAdditionalConfiguration(domainName1, scheme2);

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "text", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName2, localQueueForSopsName2, 1, 0, 1, 1, 0, null, null, null, null);
    }

    @Test
    public void checkSopsFtpsAndFtps() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String sopsName2 = "CОПС для проверки " + testName.getMethodName() + "-2";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String managerName1 = testName.getMethodName();

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|FTPS|192.168.194.69/test_ftps", "инпут-в-параметрах|Имя пользователя|{{user}}", "инпут-в-параметрах|Пароль|{{pass}}", "чекбокс-в-параметрах|Пассивный режим соединения|вкл"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|FTPS|192.168.194.69/test_ftps", "инпут-в-параметрах|Имя пользователя|{{user}}", "инпут-в-параметрах|Пароль|{{pass}}", "селект-в-параметрах|Ссылка на параметры SSL контекста|#SSLContextParameters", "чекбокс-в-параметрах|Пассивный режим соединения|вкл", "чекбокс-в-параметрах|Удалить файл|вкл"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String[] schemesAll = {"Параметры SSL контекста|SSLContextParameters", "123456|${constantsPathToKeyManager}|123456", "./conf/ssl/ftps.keystore|12345678", "100|NONE"};

        String[] constantsOfDomain1 = {"user", "fesb"};
        String[] constantsOfDomain2 = {"pass", "fesb1!"};
        String[] constantsPathToKeyManager = {"constantsPathToKeyManager", "./conf/ssl/fesb.jks"};
        String[][] allConstantsOfDomain = {constantsOfDomain1, constantsOfDomain2, constantsPathToKeyManager};

        basePage.executeBashCommand("sudo docker cp /home/fesb/share/files/ftps.keystore fesb-test-1-1:fesb/conf/ssl");
        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(Cook1, baseUrl(), managerName1);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        sopsPage = new SOPSPage();
        sopsPage.createConstantsOfDomain(domainName1, allConstantsOfDomain);
        sopsPage.createAdditionalConfiguration(domainName1, schemesAll);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName1, testName.getMethodName(), 1, false, "4");
//        queueManagerApi.sendMessgeInQueueAPI(Cook1, localQueueForSopsName1, testName.getMethodName(), 1);
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName1, localQueueForSopsName2, testName.getMethodName());
    }

    @Test
    public void checkSopsLocalQueueAndLdap() {
        String managerName1 = testName.getMethodName();
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
//        String domainName2 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-2");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|LDAP|LDAP_FESB|CN=Users,DC=factormsk,DC=ru"};
        String[] point1_3 = new String[]{"Выход|Преобразовать тело сообщения|java.lang.String|UTF-8"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName1 + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String[] schemesAll = {"Конфигурация LDAP-соединения|LDAP_FESB", "${constantHost}|Логин/Пароль|kbrtestuser|123qweasd!QAZ"};
//        String[][] schemesAll = {setAddConfig};

        Object[] constant1 = {"constantHost", "ldap://192.168.194.225:389", false};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(Cook1, baseUrl(), managerName1);
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, schemesAll);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName1, localQueueForSopsName1, "(&(objectClass=user)(objectCategory=person))", 1);
//        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, localQueueForSopsName1, "(&(objectClass=user)(objectCategory=person))", 1);
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName1, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName1, localQueueForSopsName2, "user");
    }

//    /**
//     * Check working transaction sops
//     */
//    @Description("Check working transaction sops")
//    @Test
//    public void transactionTest() {
//        String domainName = commonComponents.createIndividualName(DOMAIN_NAME);
//        String localQueueIn = "TransIn";
//        String localQueueOut = "TransOut";
//        String localQueueMid="TransMid";
//        String queueError = "DEAD.LETTER.QUEUE";
//        String sopsName = "Sops_Transaction";
//        sopsPage = new SOPSPage();
//        sopsPage.createDomain(domainName);
//        sopsPage.runDomain(domainName);
//        sopsPage.clickToAddSOPSButton(domainName);
//        creationSOPSPage = new CreationSOPSPage();
//        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, localQueueIn,null, null);
//        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, localQueueOut,null, null);
//        creationSOPSPage.treatmentOutputPoint("Локальная очередь", localQueueMid);
//        creationSOPSPage.treatmentAddFormatConvertion("JSON → XML");
//        creationSOPSPage.inputSOPSNameAndSave(sopsName);
//        creationSOPSPage.returnToSOPSList.shouldBe(enabled).click();
//        sopsPage.queueListPage();
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.deleteQueueIfExist(queueError);
//        basePage.click(basePage.sendMessageTab);
//        messagePage = new MessagePage();
//        messagePage.sendMessageInQueueNTimes(localQueueIn, "test", 1);
//        messagePage.queueListPage();
//        queueManagerPage.queueShouldHaveNMessagesInDepth(localQueueMid, 1);
//        queueManagerPage.queueShouldHaveNMessagesInDepth(queueError, 1);
//        queueManagerPage.sopsPage();
//        sopsPage.goToSops(domainName,sopsName);
//        creationSOPSPage.changeSOPSProperties(true, false);
//        creationSOPSPage.saveIcon.click();
//        basePage.click(basePage.oldSaveButton);
//        creationSOPSPage.returnToSOPSList.shouldBe(enabled).click();
//        basePage.click(basePage.sendMessageTab);
//        messagePage = new MessagePage();
//        messagePage.sendMessageInQueueNTimes(localQueueIn, "test", 1);
//        messagePage.queueListPage();
//        queueManagerPage.queueShouldHaveNMessagesInDepthWithBeforeWait(localQueueMid, 1, 7000);
//        queueManagerPage.queueShouldHaveNMessagesInDepth(queueError, 2);
//        queueManagerPage.deleteSeveralQueues(localQueueIn, localQueueMid, queueError);
//        queueManagerPage.sopsPage();
//        sopsPage.deleteDomain(domainName);
//    }

    @Test
    public void stopSendMessageAndStartSops() {
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainIDForStopTest = sopsApi.createDomainAPI(Cook, DomainNameForStopTest);
        sopsApi.startDomainAPI(Cook, DomainIDForStopTest);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(Cook, DomainIDForStopTest, SopsForEnvironment1);
        sopsApi.createSopsWith2LocalQueueAPI(Cook, baseUrl(), DomainIDForStopTest, SopsIDForStopTest, DomainNameForStopTest, SopsForEnvironment1, LocalQueueForEnvironment1, LocalQueueForEnvironment2);

        refresh();
        sopsPage = new SOPSPage();
        sopsPage.stopSOPS(DomainNameForStopTest, SopsForEnvironment1);
        sopsPage.sendMessage(SopsForEnvironment1, 1, "ап");

        sopsPage = new SOPSPage();
        basePage.click($x(String.format(SOPSPage.specificDomainButtonXpath, DomainNameForStopTest)));
        sopsPage.searchSops(SopsForEnvironment1);
        sopsPage.cellProcessedSOPS.shouldHave(exactText("0"));

        sopsPage.goToDomaineAndstartSOPS(DomainNameForStopTest, SopsForEnvironment1);
        sopsPage.sopsCheckAllParameters(SopsForEnvironment1, "Локальная очередь: " + LocalQueueForEnvironment1, 1);
    }


    //    @Description("Create and check SOPS with local file and local queue")
    @Test
    @Category(speed.class)
    @Parameters(value = {
            "upload100 | download100",
            "upload500 | download500",
    })
    public void checkSOPSWithLocalFileAndLocalQueue(String LocalQueueEntry, String LocalQueueExit) {
        String localFileInPath = "/home/fesb/Stand/share/" + LocalQueueEntry;
        String localFileOutPath = "/home/fesb/Stand/share/" + LocalQueueExit;
        File folderIn = new File(localFileInPath);
        File folderOut = new File(localFileOutPath);
        ArrayList<String> arrayListIn = new ArrayList<>();
        ArrayList<String> arrayListOut = new ArrayList<>();
        String domainName = commonComponents.createIndividualName(DOMAIN_NAME);
        String sopsName1 = commonComponents.createIndividualName(SOPS_NAME);
        String sopsName2 = commonComponents.createIndividualName(SOPS_NAME);

        for (int x = 1; x < 11; x++) {
            writeStringInFile("/home/fesb/Stand/share/" + LocalQueueEntry + "/" + LocalQueueEntry.replaceAll("\\D", "") + "mb" + x + ".txt", messagePage.createMessageWithBiteSize(Integer.parseInt(LocalQueueEntry.replaceAll("\\D", "")) * 1000000));
        }

        String localQueueOutName = commonComponents.createIndividualName(LOCAL_QUEUE_OUT);
        int SumLocalFilesIn = Objects.requireNonNull(new File(localFileInPath).listFiles()).length;
        int SumLocalFilesOut = Objects.requireNonNull(new File(localFileOutPath).listFiles()).length;

        new IndexPage().sopsPage();
        sopsPage = new SOPSPage();
        basePage.sout("В директории:" + localFileInPath + " находится " + SumLocalFilesIn + " файлов");
        Assert.assertEquals(10, SumLocalFilesIn); // в каталоге существует папка ".camel", поэтому значение на 1 больше
        basePage.sout("В директории:" + localFileOutPath + " находится " + SumLocalFilesOut + " файл");
        Assert.assertEquals(0, SumLocalFilesOut);
        getControlSumAllFileInDirectory(folderIn, arrayListIn);

        sopsPage.createDomain(domainName);
        sopsPage.clickToAddSOPSButton(domainName);
        creationSOPSPage = new CreationSOPSPage();

        switch (LocalQueueEntry) {
            case ("upload100"):
                creationSOPSPage.createSOPSWithLocalFileAndLocalQueue(sopsName1, null, null, "/fesb/tests/" + LocalQueueEntry, localQueueOutName, null, null);
                basePage.autoUpdateOff();
                sopsPage.startDomain(domainName);
                basePage.autoUpdateOn();
                sopsPage.cellProcessedSOPS.waitUntil(exactText("10"), 90000);
                sopsPage.sopsCheckAllParameters(sopsName1, "Локальный файл: /fesb/tests/" + LocalQueueEntry, 10);
                break;
            case ("upload500"):
                creationSOPSPage.createSOPSWithSreamingAndLocalQueue(sopsName1, "Мультименеджер очередей", "QM", "/fesb/tests/" + LocalQueueEntry, localQueueOutName, null, null);
                sopsPage.clickToAddSOPSButton(domainName);
                creationSOPSPage.createSOPSWithLocalQueueAndSreaming(sopsName2, "Мультименеджер очередей", "QM", "/fesb/tests/" + LocalQueueExit, localQueueOutName, null, null);
                sopsPage.sopsCheckAllParameters(sopsName1, "Потоковая передача: /fesb/tests/" + LocalQueueEntry, 0);
                sopsPage.sopsCheckAllParameters(sopsName2, "Локальная очередь: " + localQueueOutName, 0);
                basePage.autoUpdateOff();
                sopsPage.startDomain(domainName);
                sopsPage.searchSops(sopsName1);
                basePage.autoUpdateOn();
                sopsPage.cellProcessedSOPS.waitUntil(exactText("4880"), 600000);
                sopsPage.sopsCheckAllParameters(sopsName1, "Потоковая передача: /fesb/tests/" + LocalQueueEntry, 4880);
                sopsPage.searchSops(sopsName2);
                sopsPage.cellProcessedSOPS.waitUntil(exactText("4880"), 600000);
                sopsPage.sopsCheckAllParameters(sopsName2, "Локальная очередь: " + localQueueOutName, 4880);
                break;
            default:
                throw new AssertionError("Пропущено создание СОПСа");
        }
//        queueManagerPage = new QueueManagerPage();
//        basePage.queueListPage();

        switch (LocalQueueEntry) {
            case ("upload100"):
                queueManagerMultyPage.queueCheckAllParameters(managerName, localQueueOutName, "10", "0", "10", "0", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(localQueueOutName, "10", "0", "10", "0", "Локальная", "");
                break;
            case ("upload500"):
                queueManagerMultyPage.queueCheckAllParameters(managerName, localQueueOutName, "0", "1", "4880", "4880", "Локальная", "", "");
//                queueManagerPage.queueCheckAllParameters(localQueueOutName, "0", "1", "4880", "4880", "Локальная", "");
                break;
            default:
                throw new AssertionError("Пропущена проверка параметров точки выхода - Локальная очередь");
        }

        switch (LocalQueueEntry) {
            case ("upload100"):
                basePage.sopsPage();
                sopsPage.clickToAddSOPSButton(domainName);
                creationSOPSPage = new CreationSOPSPage();
                creationSOPSPage.createSOPSWithLocalQueueAndLocalFile(sopsName2, "Менеджер очередей", "", "/fesb/tests/" + LocalQueueExit, localQueueOutName, null, null);
                sopsPage.sopsCheckAllParameters(sopsName1, "Локальный файл: /fesb/tests/" + LocalQueueEntry, 10);
                sopsPage.sopsCheckAllParameters(sopsName2, "Локальная очередь: " + localQueueOutName, 10);
//                queueManagerPage = new QueueManagerPage();
//                basePage.queueListPage();
                queueManagerMultyPage.queueCheckAllParameters(managerName, localQueueOutName, "0", "1",
                        "10", "10", "Локальная", "", "");

//                queueManagerPage.queueCheckAllParameters(localQueueOutName, "0", "1", "10",
//                        "10", "Локальная", "");
                break;

            case ("upload500"):
                break;

            default:
                throw new AssertionError("Пропущено создание СОПС - Локальная очередь/Локальный файл(потоковая передача)");
        }

        sopsPage.stopSOPS(domainName, sopsName1);
        sopsPage.stopSOPS(domainName, sopsName2);

        SumLocalFilesIn = Objects.requireNonNull(new File(localFileInPath).listFiles()).length;
        SumLocalFilesOut = Objects.requireNonNull(new File(localFileOutPath).listFiles()).length;

        basePage.sout("В директории:" + localFileInPath + " находится " + SumLocalFilesIn + " файлов");
        Assert.assertEquals(1, SumLocalFilesIn); // в каталоге деректория ".camel", поэтому значение на 1 больше
        basePage.sout("В директории:" + localFileOutPath + " находится " + SumLocalFilesOut + " файлов");
        Assert.assertEquals(10, SumLocalFilesOut);

        getControlSumAllFileInDirectory(folderOut, arrayListOut);
        Assert.assertEquals(arrayListIn, arrayListOut);
    }

    @Description("Export/Import domain. Изначально был написан тест на каждый пункт экспорта, но после того как их стало больше, остались только тест на все пункты. тест на все пункты с редактированием и тест на один пункт")
    @Test
    @Parameters(value = {
            "все | ТестИмпортаДомена всеПараметры | js-TEST(all).js | groovy-TEST(all).groovy | tableOfRouts(all).xml |" +
                    " xsd-TEST(all).xsd | xslt-TEST(all).xsl | json-TEST(all).json | Локальная очередь для JS + Groovy + XSLT + XSD |" +
                    " /share/DomainForImport/sourceForImportAll.zip | /home/fesb/share/Downloaded/domainForImportAll.zip |" +
                    " /share/Downloaded/domainForImportAll.zip",
//            "домен + JS и Groovy | ТестИмпортаДомена JSGroovy | js-TEST(js_groovy).js | groovy-TEST(js_groovy).groovy | tableOfRouts(js_groovy).xml |" +
//                    " xsd-TEST(js_groovy).xsd | xslt-TEST(js_groovy).xsl | json-TEST(js_groovy).json |" +
//                    " Локальная очередь для JS + Groovy + XSLT + XSD.json | /share/DomainForImport/sourceForImportJsAndGroovy.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportJsAndGroovy.zip | /share/DomainForImport/domainForImportJsAndGroovy.zip",
//            "домен + XSLT | Тестимпортадомена XSLT | js-TEST(xslt).js | groovy-TEST(xslt).groovy | tableOfRouts(xslt).xml |" +
//                    " xsd-TEST(xslt).xsd | xslt-TEST(xslt).xsl | json-TEST(xslt).json |" +
//                    " Локальная очередь для JS + Groovy + XSLT + XSD.json | /share/DomainForImport/sourceForImportXslt.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXslt.zip | /share/DomainForImport/domainForImportXslt.zip",
//            "домен + XSD | ТестИмпортаДомена XSD | js-TEST(xsd).js | groovy-TEST(xsd).groovy | tableOfRouts(xsd).xml |" +
//                    " xsd-TEST(xsd).xsd | xslt-TEST(xsd).xsl | /share/DomainForImport/sourceForImportXsd.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXsd.zip | /share/DomainForImport/domainForImportXsd.zip",
//            "домен + JSON | ТестИмпортаДомена XSD | js-TEST(xsd).js | groovy-TEST(xsd).groovy | tableOfRouts(xsd).xml |" +
//                    " xsd-TEST(xsd).xsd | xslt-TEST(xsd).xsl | /share/DomainForImport/sourceForImportXsd.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXsd.zip | /share/DomainForImport/domainForImportXsd.zip",
//            "домен + модели | ТестИмпортаДомена XSD | js-TEST(xsd).js | groovy-TEST(xsd).groovy | tableOfRouts(xsd).xml |" +
//                    " xsd-TEST(xsd).xsd | xslt-TEST(xsd).xsl | /share/DomainForImport/sourceForImportXsd.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXsd.zip | /share/DomainForImport/domainForImportXsd.zip",
//            "домен + таблицы маршрутов | ТестИмпортаДомена таблицыМаршрутов | js-TEST(табл.марш.).js | groovy-TEST(табл.марш.).groovy |" +
//                    " tableOfRouts(табл.марш.).xml | xsd-TEST(табл.марш.).xsd | xslt-TEST(табл.марш.).xsl |" +
//                    "/share/DomainForImport/sourceForImportTableRouts.zip | /home/fesb/share/DomainForImport/domainForImportTableRouts.zip |" +
//                    " /share/DomainForImport/domainForImportTableRouts.zip",
//            "домен + константы | ТестИмпортаДомена XSD | js-TEST(xsd).js | groovy-TEST(xsd).groovy | tableOfRouts(xsd).xml |" +
//                    " xsd-TEST(xsd).xsd | xslt-TEST(xsd).xsl | /share/DomainForImport/sourceForImportXsd.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXsd.zip | /share/DomainForImport/domainForImportXsd.zip",
//            "домен + сообщения | ТестИмпортаДомена XSD | js-TEST(xsd).js | groovy-TEST(xsd).groovy | tableOfRouts(xsd).xml |" +
//                    " xsd-TEST(xsd).xsd | xslt-TEST(xsd).xsl | /share/DomainForImport/sourceForImportXsd.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportXsd.zip | /share/DomainForImport/domainForImportXsd.zip",
//            "домен + переменные домена | ТестИмпортаДомена ПеременныеДомена | js-TEST(variables).js | groovy-TEST(variables).groovy |" +
//                    " tableOfRouts(variables).xml | xsd-TEST(variables).xsd | xslt-TEST(variables).xsl |" +
//                    " /share/DomainForImport/sourceForImportVariables.zip | /home/fesb/share/DomainForImport/domainForImportVariables.zip | " +
//                    "/share/DomainForImport/domainForImportVariables.zip",
//            "домен + история изменения СОПС | ТестИмпортаДомена History | js-TEST(history).js | groovy-TEST(history).groovy |" +
//                    " tableOfRouts(history).xml | xsd-TEST(history).xsd | xslt-TEST(history).xsl | /share/DomainForImport/sourceForImportHistory.zip |" +
//                    " /home/fesb/share/DomainForImport/domainForImportHistory.zip | /share/DomainForImport/domainForImportHistory.zip",
            "все с редактированием данных в форме импорта |ТестИмпортаДомена allAndEdit | js-TEST(edit).js | groovy-TEST(edit).groovy | " +
                    "tableOfRouts(edit).xml | xsd-TEST(edit).xsd | xslt-TEST(edit).xsl | json-TEST(edit).json |" +
                    " Локальная очередь для JS + Groovy + XSLT + XSD + EDIT |" +
                    " /share/DomainForImport/sourceForImportAllAndEdit.zip | /home/fesb/share/Downloaded/domainForImportAllAndEdit.zip |" +
                    " /share/Downloaded/domainForImportAllAndEdit.zip",
            "только домен | ТестИмпортаДомена только домен | js-TEST(only).js | groovy-TEST(only).groovy | " +
                    "tableOfRouts(only).xml | xsd-TEST(only).xsd | xslt-TEST(only).xsl | json-TEST(only).json |" +
                    " Локальная очередь для иморта домена без параметров | " +
                    " /share/DomainForImport/sourceForImportDomainOnly.zip | /home/fesb/share/Downloaded/domainForImportDomainOnly.zip |" +
                    " /share/Downloaded/domainForImportDomainOnly.zip",
    })
    public void exportImportDomain(String type, String nameDomain, String jsFileName, String groovyFileName, String routsFileName, String xsdFileName, String xsltFileName, String jsonFileName, String localQueueMessgeFileName, String sourceDomainForImport, String filePath, String domainFile) throws IOException {
        String domainNameBase = "Тест импорта домена";
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        basePage.sout("Сессия: " + sessionId);
        String domainName = commonComponents.createIndividualName(nameDomain);
//        String localQueueName1 = "Локальная очередь для JS + Groovy + XSLT + XSD";
        String sopsName1 = "СОПС - JS + Groovy + XSLT + XSD";
        String pointIn = "localmq://" + localQueueMessgeFileName;
        String constanteName = "Переменная";
        String constanteValue = "Вся такая непостоянная";
        String nameSoftwareTransformation = "Тестовая программная трансформация";
        String jsName = "Программная трансформация JS";
//        String jsPointName = sopsName1 + " : " + jsName;
        String jsPointName = "СОПС: " + sopsName1 + " / " + jsName;
        String groovyName = "Программная трансформация Groovy";
//        String groovyPointName = sopsName1 + " : " + groovyName;
        String groovyPointName = "СОПС: " + sopsName1 + " / " + groovyName;
        String routsDirectoryName = "Таблицы маршрутов";
        String routsName = "Выход в таблицу маршрутов";
//        String routsUsedIN = sopsName1 + " : Таблица маршрутов/сопоставления";
        String routsUsedIN = "СОПС: " + sopsName1 + " / " + routsName;
        String xsdName = "XSD Валидация";
//        String xsdPointName = sopsName1 + " : " + xsdName;
        String xsdPointName = "СОПС: " + sopsName1 + " / " + xsdName;
        String xsltName = "XSLT Трансформация";
//        String xsltPointName = sopsName1 + " : " + xsltName;
        String xsltPointName = "СОПС: " + sopsName1 + " / " + xsltName;
        String jsonName = "JSON Валидация";
//        String jsonPointName = sopsName1 + " : " + jsonName;
        String jsonPointName = "СОПС: " + sopsName1 + " / " + jsonName;
        String localQueueMessgeName = "Локальная очередь";
//        String localQueueMessgePointName = sopsName1 + " : " + xsltName;
        String localQueueMessgePointName = "СОПС: " + sopsName1 + " / " + localQueueMessgeFileName;
//        СОПС: СОПС - JS + Groovy + XSLT + XSD / Локальная очередь для JS + Groovy + XSLT + XSD;
//        СОПС: СОПС - JS + Groovy + XSLT + XSD/Локальная очередь для JS + Groovy + XSLT + XSD'
        String nameAddConfig = "test_configuration";
        String typeAddConfig = "Обработчик ошибок";
        String property1AddConfig = "class org.apache.camel.builder.DeadLetterChannelBuilder";
        String property2AddConfig = "deadLetterUri direct://myDLC";
        String property3AddConfig = "useOriginalMessage false";
        String property4AddConfig = "redeliveryPolicy myRedeliveryPolicyConfig";
        String model = "ModelForImport - modelForImport.json JSON Да";
        String savedMessageLocalQueue = "Тестовое сохраненное сообщение";

        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, sourceDomainForImport, nameDomain);
        sopsPage.exportDomain(type, nameDomain);

        basePage.writeContentDowloadedFile(filePath, sessionId, sopsPage.DownloadedFileName);

        open(baseUrl_2());
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.importDomain(type, domainName, domainFile, pointIn, nameAddConfig, typeAddConfig, property1AddConfig, property2AddConfig, property3AddConfig, property4AddConfig, sopsName1, jsFileName, jsPointName, xsdFileName, xsdPointName, groovyFileName, groovyPointName, routsFileName, routsUsedIN, xsltFileName, xsltPointName, jsonFileName, jsonPointName, localQueueMessgeFileName, localQueueMessgePointName, constanteName, constanteValue);

        sopsPage.checkImportedDomain(type, domainName, sopsName1, localQueueMessgeFileName, xsdName, xsdFileName, jsName, jsFileName, groovyName, groovyFileName, xsltName, xsltFileName, jsonName, jsonFileName, routsDirectoryName, routsName, routsFileName, constanteName, constanteValue, nameAddConfig, typeAddConfig, property1AddConfig, property2AddConfig, property3AddConfig, model, savedMessageLocalQueue);
    }

    @Test
    @Parameters(value = {
            "editAllData | ДоменCвойствNew | С поддержкой XA-транзакций | Всегда запускать при старте брокера | 1 | 2 | 3 | 4 | DiscardOldest | По умолчанию (Транзакционный СОПС) | По умолчанию | ERROR | 5 | 6 | 7 | true | 8 | true | 0.50 | 9 | 10 | 11 | Локальное подключение | editProperties-1",
            "editWithoutDomainName | null | С поддержкой XA-транзакций | Всегда запускать при старте брокера | 1 | 2 | 3 | 4 | DiscardOldest | По умолчанию (Транзакционный СОПС) | По умолчанию | ERROR | 5 | 6 | 7 | true | 8 | true | 0.50 | 9 | 10 | 11 | Локальное подключение | editProperties-2",
    })
    public void editProperties(String testName, String domainNameNew, String domainType, String strategyOfStart, String threadPoolSize, String maximumThreadPoolSize, String freeFlowLifetime, String maximumNumberTasksInWorkQueue, String abandonmentStrategy, String errorHandler, String policyResend, String loggingLevel, String maximumAttempts, String initialDelayBetweenRetries, String maximumDelayBetweenRetries, String asynchronousDelayBetweenDeliveries, String multiplierDelayRedelivery, String useCollisionAvoidance, String collisionAvoidanceRatio, String maximumNumberOfConnections, String connectionsPerSession, String asynchronousRecipients, String typeOfConnect, String managerName) {
        String domainID = sopsApi.createDomainAPI(cook, domainName);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
        sopsApi.createSopsWith2LocalQueueAPI(cook, baseUrl(), domainID, SopsIDForStopTest, domainName, sopsName, localQueueName1, localQueueName2);
        sopsApi.startDomainAPI(cook, domainID);
        sopsPage = new SOPSPage();
        String cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        queueManagerMultyApi.createManagerAPI(cookies, baseUrl(), managerName);

        if (testName.equals("editWithoutDomainName")) {
            domainNameNew = domainName;
        }
        refresh();
        sopsPage.editProperties(domainName, domainNameNew, strategyOfStart, threadPoolSize, maximumThreadPoolSize, freeFlowLifetime, maximumNumberTasksInWorkQueue, abandonmentStrategy, errorHandler, policyResend, loggingLevel, maximumAttempts, initialDelayBetweenRetries, maximumDelayBetweenRetries, asynchronousDelayBetweenDeliveries, multiplierDelayRedelivery, useCollisionAvoidance, collisionAvoidanceRatio, maximumNumberOfConnections, connectionsPerSession, asynchronousRecipients, typeOfConnect, managerName);
        basePage.click(basePage.closeNotificationIcon);
        sopsPage.checkProperties(domainNameNew, domainType, strategyOfStart, threadPoolSize, maximumThreadPoolSize, freeFlowLifetime, maximumNumberTasksInWorkQueue, abandonmentStrategy, errorHandler, policyResend, loggingLevel, maximumAttempts, initialDelayBetweenRetries, maximumDelayBetweenRetries, asynchronousDelayBetweenDeliveries, multiplierDelayRedelivery, useCollisionAvoidance, collisionAvoidanceRatio, maximumNumberOfConnections, connectionsPerSession, asynchronousRecipients, typeOfConnect, managerName);
        sopsPage.restartDomainInWidget();
        sleep(10000);
        sopsPage.checkProperties(domainNameNew, domainType, strategyOfStart, threadPoolSize, maximumThreadPoolSize, freeFlowLifetime, maximumNumberTasksInWorkQueue, abandonmentStrategy, errorHandler, policyResend, loggingLevel, maximumAttempts, initialDelayBetweenRetries, maximumDelayBetweenRetries, asynchronousDelayBetweenDeliveries, multiplierDelayRedelivery, useCollisionAvoidance, collisionAvoidanceRatio, maximumNumberOfConnections, connectionsPerSession, asynchronousRecipients, typeOfConnect, managerName);
    }

    @Test
    public void loadNewVersionExistingLibrary() {
//        String managerName1 = testName.getMethodName();
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        queueManagerMultyApi.createManagerAPI(Cook, baseUrl(), managerName);
//        queueManagerMultyApi.startManagerAPI(Cook, baseUrl(), managerName1);

        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/Aggregation.zip", testName.getMethodName());
        settingsPage = new SettingsPage();
        settingsPage.loadLibrary("/share/Librarys/libs/v1/AggStrat.jar");
        sopsPage.startDomain(testName.getMethodName());

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, "IN.AGG", "AAA#BBB", 1);
//        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, "IN.AGG", "AAA#BBB", 1);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, "OUT.AGG",
                "AAA version 1\n" +
                        " version 1 \n" +
                        "BBB version 1");

        settingsPage = new SettingsPage();
        settingsPage.loadLibrary("/share/Librarys/libs/v2/AggStrat.jar");
        sopsPage.restartDomainInWidget();
//        basePage.click(sopsPage.restartDomainButtonInPopUp);
        queueManagerMultyApi.clearQueueAPI(Cook, managerName, "OUT.AGG");
//        sopsPage.reloadDomain(testName.getMethodName());
        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, "IN.AGG", "AAA#BBB", 1);

//        queueManagerApi.sendMessgeInQueueAPI(Cook, "IN.AGG", "AAA#BBB", 1);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, "OUT.AGG",
                "AAA version 2\n" +
                        " version 2 \n" +
                        "BBB version 2");
    }

    @Test
    public void importExistedDomain() {
        String domainNameOld = testName.getMethodName() + "Old";
        String domainNameNew = testName.getMethodName() + "New";
        String sopsNameOld = "СОПС - JS + Groovy + XSLT + XSD";
        String sopsNameNew = "СОПС - JS + Groovy + XSLT + XSD - NEW";
        String addConfigurationNameOld = "test_configuration";
        String addConfigurationNameNew = "addConfigurationNameNew";
        String xsdFileNameOld = "xsd-TEST(all).xsd";
        String xsdFileNameNew = "xsd-NEW.xsd";
        String routsFileNameOld = "tableOfRouts(all).xml";
        String routsFileNameNew = "tableOfRouts(NEW).xml";
        String modelNameOld = "ModelForImport";
        String modelNameNew = "ModelForImport-NEW";
        String localQueueName = "Локальная очередь для JS + Groovy + XSLT + XSD";
        String textSavedMessageLocalQueueOld = "Тестовое сохраненное сообщение";
        String textSavedMessageLocalQueueNew = "NEW сохраненное сообщение";
        String variableNameOld = "Переменная";
        String variableNameNew = "Переменная-New";
        String variableValueOld = "Вся такая непостоянная";
        String variableValueNew = "Вся такая непостоянная-New";
        String[] constantsOfDomain1 = {variableNameNew, variableValueNew};
        String[][] allConstantsOfDomain = {constantsOfDomain1};
        String[] settingAddConfiguration1 = {"Обработчик ошибок|" + addConfigurationNameNew, "Обработчик по умолчанию|Политика по умолчанию"};
        String Cookies = StaticAPI.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/importExistedDomain.zip", testName.getMethodName() + "Old");
        sopsPage.startDomain(domainNameOld);
        String domainID = url().split("/broker/")[1];
        sopsPage.editSOPSName(domainNameOld, sopsNameOld, sopsNameNew);
        sopsPage.existNumRowsAfterSearch(domainNameOld, sopsNameNew, 1);

        sopsPage.goToSops(domainNameOld, sopsNameNew);
        sopsPage.checkHistoryInSops(13);

        sopsPage.editConstantsOfDomain(domainNameOld, variableNameOld, allConstantsOfDomain);
        sopsPage.checkConstantsOfDomain(0, 1, variableNameNew, variableValueNew);

        sopsPage.editAdditionalConfiguration(domainNameOld, addConfigurationNameOld, settingAddConfiguration1);
        sopsPage.checkAdditionalConfiguration(domainNameOld, "Обработчик ошибок", addConfigurationNameNew, settingAddConfiguration1);

        sopsPage.editProperties(domainNameOld, domainNameNew, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        sopsPage.checkProperties(domainNameNew, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        sopsPage.editNameImportedFiles(xsdFileNameOld, xsdFileNameNew);
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, xsdFileNameNew);

//        sopsPage.editNameImportedFilesInDirectory("Таблицы маршрутов", routsFileNameOld, routsFileNameNew);
//        sopsPage.checkImportedFilesInDirectory("Таблицы маршрутов", SOPSPage.thingEnableOrDisable.ENABLED, routsFileNameNew);

        sopsPage.editModelData(domainNameNew, modelNameOld, modelNameNew, null, null, null);
        sopsPage.checkModelData(domainNameNew, modelNameNew, null, null, null);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.editSavedMessages(managerName, localQueueName, textSavedMessageLocalQueueNew);
        queueManagerMultyPage.openFormForSendMessageToQueue(managerName, localQueueName);
        basePage.compareStringAndElementText(textSavedMessageLocalQueueNew, messagePage.messageTextAria);

        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(Cookies, domainID, "Новый СОПС");
        sopsApi.createSopsWith2LocalQueueAPI(Cookies, baseUrl(), domainID, SopsIDForStopTest, domainNameNew, "Новый СОПС", "Оч.1", "Оч.2");
        String[] addConfig = {"Пользовательская", domainID, "NewConfig", "java.lang.String", "DEFAULT"};
        sopsApi.createAddConfigurationAPI(Cookies, baseUrl(), addConfig);
        String idFileModelData = sopsApi.uploadModeleAPI(Cookies, "/home/fesb/Stand/share/ModelsData/schemaForRest.json");
        sopsApi.createModelDataAPI(Cookies, domainID, "JSON", "modelName2", "asdf", true, idFileModelData);

        sopsPage.importDomain(SOPSPage.typeOfDomain.EXISTED, "/share/DomainForImport/importExistedDomain.zip", testName.getMethodName() + "New");
        sopsPage.restartDomainInWidget();
//        basePage.click(basePage.restartDomainButtonInPopUp);

        sopsPage.goToDomain(domainNameNew);
        sopsPage.searchSops(sopsNameOld);
        sopsPage.cellNameSOPS.shouldBe(exist);

        sopsPage.checkConstantsOfDomain(0, 2, variableNameNew, variableValueNew);
        sopsPage.checkConstantsOfDomain(1, 2, variableNameOld, variableValueOld);


        basePage.click(sopsPage.additionConfigurationTab);
        basePage.click($x(String.format(SOPSPage.expandConfigurationForAdditionalConfigurationButton, "Обработчик ошибок", addConfigurationNameOld)));
        basePage.compareStringAndElementText("class org.apache.camel.builder.DeadLetterChannelBuilder", sopsPage.rowInexpandConfiguration.get(0));
        basePage.compareStringAndElementText("deadLetterUri direct://myDLC", sopsPage.rowInexpandConfiguration.get(1));
        basePage.compareStringAndElementText("useOriginalMessage false", sopsPage.rowInexpandConfiguration.get(2));
        basePage.compareStringAndElementText("redeliveryPolicy myRedeliveryPolicyConfig", sopsPage.rowInexpandConfiguration.get(3));

        basePage.click(sopsPage.additionConfigurationTab);
        basePage.click($x(String.format(SOPSPage.expandConfigurationForAdditionalConfigurationButton, "Обработчик ошибок", addConfigurationNameNew)));
        basePage.compareStringAndElementText("class org.apache.camel.builder.DeadLetterChannelBuilder", sopsPage.rowInexpandConfiguration.get(0));
        basePage.compareStringAndElementText("deadLetterUri direct://myDLC", sopsPage.rowInexpandConfiguration.get(1));
        basePage.compareStringAndElementText("useOriginalMessage false", sopsPage.rowInexpandConfiguration.get(2));
        basePage.compareStringAndElementText("redeliveryPolicy myRedeliveryPolicyConfig", sopsPage.rowInexpandConfiguration.get(3));

        basePage.click(sopsPage.propertiesOfDomainButton);
        basePage.click(sopsPage.propertiesConnectionMqTab);
        basePage.compareStringAndElementValue(domainNameNew, sopsPage.nameDomainInPropertiesInput);
        basePage.closeForm();

        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, xsdFileNameOld);
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, xsdFileNameNew);
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, "js-TEST(all).js");
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, "groovy-TEST(all).groovy");
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, "xslt-TEST(all).xsl");
        sopsPage.checkImportedFiles(SOPSPage.thingEnableOrDisable.ENABLED, "json-TEST(all).json");
//        sopsPage.checkImportedFilesInDirectory("Таблицы маршрутов", SOPSPage.thingEnableOrDisable.ENABLED, routsFileNameOld);
//        sopsPage.checkImportedFilesInDirectory("Таблицы маршрутов", SOPSPage.thingEnableOrDisable.ENABLED, routsFileNameNew);

        sopsPage.goToDomain(domainNameNew);
        basePage.click(sopsPage.modelsTab);
        basePage.compareElementTextShouldContainsText("ModelForImport - modelForImport.json JSON", sopsPage.modelListRowsAfterSearch.get(0));
        basePage.compareElementTextShouldContainsText("schemaForRest.json JSON Да", sopsPage.modelListRowsAfterSearch.get(1));

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.searchQueue(managerName, localQueueName);
        basePage.contextClick(queueManagerMultyPage.afterSearchQueueName);
        basePage.click(queueManagerMultyPage.contextSendMessage);
        basePage.compareStringAndElementText("Тестовое сохраненное сообщение", messagePage.messageTextAria);
    }

    @Test
    public void debug() {
        String domainID = sopsApi.createDomainAPI(cook, domainName);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
//        sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
        sopsApi.createSopsWith2LocalQueueMultyAPI(cook, baseUrl(), domainID, SopsIDForStopTest, sopsName, managerName, localQueueName1, managerName, localQueueName2);


//        sopsApi.createSopsWith2LocalQueueMultyAPI(cook, baseUrl(), domainID, SopsIDForStopTest, domainName, sopsName, localQueueName1, localQueueName2);
        sopsApi.startDomainAPI(cook, domainID);
        refresh();
        sopsPage = new SOPSPage();
        sopsPage.checkDebugMode(cook, domainName, sopsName, managerName, localQueueName2, baseUrl());
    }


    @Test()
    @Category(speed.class)
    @Parameters(value = {
            "1| 200 | 1000 | 180 | 1100",
            "10| 40 | 5600 | 45 | 4800",
            "20| 35 | 6000 | 35 | 5500",
            "100| 40 | 6500 | 50 | 4300",
    })
    public void speedXslt(String numberThreads, long optimalTimeSops1, float optimalSpeedSops1, long optimalTimeSops2, float optimalSpeedSops2) {
        String message =
                "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>" +
                        " <Root>" +
                        "    <age>55</age>" +
                        "    <city>Moscow</city>" +
                        "    <name>Vova</name>" +
                        "</Root>";
        String[] sopses = {"XSLT.TEST", "MODEL.XML.TEST"};
        String[] localQueuesIn = {"XSLT.THREADS.IN", "MODEL.XML.TEST.IN"};
        long[] optimalTimeValue = {optimalTimeSops1, optimalTimeSops2};
        float[] optimalSpeedValue = {optimalSpeedSops1, optimalSpeedSops2};
        int sumMessage = 200000;
        String managerName = "QM";

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        basePage.click(basePage.generalInformationTab);
        String versionFesb = generalInformationPage.productVersion.getText();
        sopsPage = new SOPSPage();
        sopsPage.editProperties(SPEED_XSLT_DOMAIN_NAME, null, null, numberThreads, numberThreads, null, null, null, null, null, null, null, null, null, null, null, null, null, numberThreads, null, numberThreads, null, null);
        if (!numberThreads.equals("1")) {
            basePage.click(basePage.restartDomainButtonInPopUp);
        }

        for (int x = 0; x < sopses.length; x++) {
            sopsPage.stopSOPS(SPEED_XSLT_DOMAIN_NAME, sopses[x]);
            sleep(30000);
            queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueuesIn[x], message, sumMessage, false, "4");

//            queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), localQueuesIn[x], message, sumMessage, false, "4");
            sopsPage.startSOPS(SPEED_XSLT_DOMAIN_NAME, sopses[x]);
            sopsPage.searchSops(sopses[x]);
            sopsPage.autoUpdateOn();
            sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessage)), 300000);

            if (!numberThreads.equals("100")) {
                sopsPage.stopSOPS(SPEED_XSLT_DOMAIN_NAME, sopses[x]);
            }

            sopsPage.checkSpeedAndWriteToFile(sopses[x] + testName.getMethodName(), versionFesb, optimalTimeValue[x], optimalSpeedValue[x], "speedStatistic_XSLT.txt");
        }

        if (numberThreads.equals("100")) {
            basePage.writeMessageToFile("-------------------------------------------------------------------------------------\n", "/home/fesb/Stand/speedStatistic_XSLT.txt");
        }
    }

    @Test()
    @Category(speed.class)
    public void speedXML() {
        int sumMessages = 30780;
        String domainName1 = testName.getMethodName();
        basePage.click(basePage.generalInformationTab);
        String versionFesb = generalInformationPage.productVersion.getText();
        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/speedXML.zip", domainName1);
        sopsPage.startDomain(domainName1);

        sopsPage.startSOPS(domainName1, "FILES.READER");
        sopsPage.searchSops("FILES.READER");
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessages)), 240000);
        sopsPage.stopSOPS(domainName1, "FILES.READER");

        long optimalTimeValue_XML_XPATH = 220;
        float optimalSpeedValue_XML_XPATH = 130;
//        System.out.println("Темература процессора = " + basePage.getCPUTemperature() + "'C");
        sopsPage.startSOPS(domainName1, "XML.XPATH");
        sopsPage.searchSops("XML.XPATH");
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessages)), 240000);
        sopsPage.stopSOPS(domainName1, "XML.XPATH");
        sopsPage.checkSpeedAndWriteToFile("XML.XPATH", versionFesb, optimalTimeValue_XML_XPATH, optimalSpeedValue_XML_XPATH, "speedStatistic_XML.txt");

        long optimalTimeValue_XML_MODELS = 20;
        float optimalSpeedValue_XML_MODELS = 1600;
        sopsPage.startSOPS(domainName1, "XML.MODELS");
        sopsPage.searchSops("XML.MODELS");
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessages)), 180000);
        sopsPage.stopSOPS(domainName1, "XML.MODELS");
        sopsPage.checkSpeedAndWriteToFile("XML.MODELS", versionFesb, optimalTimeValue_XML_MODELS, optimalSpeedValue_XML_MODELS, "speedStatistic_XML.txt");

        long optimalTimeValue_XML_GROOVY = 10;
        float optimalSpeedValue_XML_GROOVY = 3500;
        sopsPage.startSOPS(domainName1, "XML.GROOVY");
        sopsPage.searchSops("XML.GROOVY");
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessages)), 180000);
        sopsPage.stopSOPS(domainName1, "XML.GROOVY");
        sopsPage.checkSpeedAndWriteToFile("XML.GROOVY", versionFesb, optimalTimeValue_XML_GROOVY, optimalSpeedValue_XML_GROOVY, "speedStatistic_XML.txt");


        basePage.writeMessageToFile("-------------------------------------------------------------------------------------\n", "/home/fesb/Stand/speedStatistic_XML.txt");
    }

    @Test
    @Ignore
    @Parameters(value = {
            "SPRING TIMER REQUIRED TM",
            "SPRING ENDPOINT TM",
            "LOCAL TM",
    })
    public void checkTransactions(String sopsName) {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");

        sopsPage.prepareForCheckTransactions(domainName1, sopsName);


//        String localQueueIn1 = "AGGREGATOR.IN";
//
//        String localQueueOut2 = "OUT.AGG";
//        String message = "222";
//
//        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + localQueueIn1};
//        String[] point1_2 = new String[]{"Выход|Установить заголовки", "параметры-заголовков|id|Simple|По умолчанию|${body}"};
//        String[] point1_3 = new String[]{"Выход|Установить переменные|Exchange.AGGREGATION_COMPLETE_ALL_GROUPS|Constant|По умолчанию|true", "чек-бокс|Выполнять при условии|вкл|simple|${body} == '6'"};
//        String[] point1_4 = new String[]{"Выход|Агрегация|header|id|addAggStr", "чек-бокс|Установить выражение размера завершения|constant|2"};
//        String[] point1_5 = new String[]{"Обработка-Агрегация-0|Локальная очередь|" + localQueueOut1};
//        String[] point1_6 = new String[]{"Выход|Локальная очередь|" + localQueueOut2};
//
//        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6};
//
//        String[] settingConfig1 = {"Пользовательский|addAggStr", "ru.factorts.module.broker.strategy.aggregation.AppendAggregationStrategy"};
//
//        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
//        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
//        sopsPage = new SOPSPage();
//        sopsPage.createAdditionalConfiguration(domainName1, settingConfig1);
//        creationSOPSPage = new CreationSOPSPage("empty");
//        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
//        sleep(1000);
//        queueManagerApi.sendMessgeInQueueAPI(Cook1, localQueueIn1, message, 10);
//
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queueShouldHaveSpecificMessage(localQueueOut1, message + "" + message);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueOut1, 5, 0, 0, 5, 0, "local", "null", baseUrl());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueOut2, 10, 0, 0, 10, 0, "local", "null", baseUrl());

        throw new AssertionError("No");
    }
}