package ru.factorts.page;

import com.codeborne.selenide.*;
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
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ru.factorts.page.CreationSOPSPage.transactionalityPolicy.MQMTransactionPolicy;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N5_WmqSingleTest extends Base {

    @Rule
    public TestName testName = new TestName();

    public SpecificQueuePage specificQueuePage;
    public SOPSPage sopsPage = new SOPSPage("empty");
    //    public QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    public CreationSOPSPage creationSOPSPage = new CreationSOPSPage("empty");
    public BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    public CommonComponents commonComponents = new CommonComponents();
    public Api api = new Api();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    SOPSApi sopsApi = new SOPSApi();
    static public Api staticApi = new Api();
    static public IbmMqApi ibmMqApi = new IbmMqApi();
    public MessagePage messagePage = new MessagePage();
    public SpecificMessagePage specificMessagePage = new SpecificMessagePage("empty");
    IbmMqPage ibmMqPage = new IbmMqPage();
    static ConfigurationPage configurationPage = new ConfigurationPage();
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();


    String URL_IBM = "https://192.168.57.240:9443/ibmmq/console/login.html";
    String Cook = null;
    static public String CookWMQ = null;
    public String domainIdForTestWMQ = null;
    private String domainNameForTestWMQ = commonComponents.createIndividualName("DomainNameForTestWMQ-");
    public static String binName = "WMQ";
    public static String transactionPolicy = "WMQTransactionPolicy";
    public static String loginForBinWMQ = "admin";
    public static String passwordForBinWMQ = "passw0rd";
    public static String appNameForBinWMQ = "CBR_TEST";
    public static String sizeOfPoolThreadForBinWMQ = "50";
    //  public  static String ipAdressForBinWMQ = "ibm-mq";
    public static String ipAdressForBinWMQ = "192.168.57.240";
    public static String portForBinWMQ = "1414";
    public static String managerForBinWMQ = "QM1";
    public static String canalNameForBinWMQ = "DEV.ADMIN.SVRCONN";
    public static String timeout = "8888";
    String managerName = "QM";

    String sopsNameForTestWMQ_1 = commonComponents.createIndividualName("SopsNameForTestWMQ-");
    String sopsNameForTestWMQ_2 = commonComponents.createIndividualName("SopsNameForTestWMQ-");
    String localQueueNameForTestWMQ_1 = commonComponents.createIndividualName("LocalQueueNameInForTestWMQ-");
    String localQueueNameForTestWMQ_2 = commonComponents.createIndividualName("LocalQueueNameOutForTestWMQ-");
    String SopsIDForTestWMQ_1 = null;
    String SopsIDForTestWMQ_2 = null;

    String groupDomains = "WMQ_INTEGRATION_TESTS";
    String domainPrepaireData = "Подготовка данных";
    String domainPrepaireDataId = "sops-a38356e7-4291-48bb-b5d3-6c0736b252fc";
    String domainSpeedId = null;
    String domainForTestcheckSOPSWithIbmAndLocalQueueId = null;
    String sopsDataForTransaction = "Данные для транзакции";
    String sopsDataForCycle = "Данные для цикла";


    @BeforeClass
    public static void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        configurationPage.importConfiguration("/share/wmq-tests-config.zip", ConfigurationPage.ReloadType.Broker);
        String CookWMQ = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        for (int i = 0; i < 11; i++) {
            ibmMqApi.createQueueToWmqAPI(CookWMQ, "A" + (i + 1), "QM1");
        }

        for (int i = 0; i < 6; i++) {
            ibmMqApi.createQueueToWmqAPI(CookWMQ, "B" + (i + 1), "QM1");
        }

        for (int i = 0; i < 6; i++) {
            ibmMqApi.createQueueToWmqAPI(CookWMQ, "C" + (i + 1), "QM1");
        }

        ibmMqApi.createQueueToWmqAPI(CookWMQ, "RECURSIVE.IN", "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, "RECURSIVE.OUT", "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, "SPEED.IN.FESB", "QM1");
        ibmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SPEED.IN.FESB", "500000", "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, "SPEED.OUT.FESB", "QM1");
        ibmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SPEED.OUT.FESB", "500000", "QM1");
        ibmMqApi.setMaxQueueDepthWMQ(CookWMQ, "B4", "500000", "QM1");
        ibmMqApi.setMaxQueueDepthWMQ(CookWMQ, "B5", "500000", "QM1");
        staticBasePage.logout();

        Selenide.closeWebDriver();
        System.out.println("Перед тестом, выполнился beforeClass\n\n\n");
    }

    @Before
    public void before() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        domainIdForTestWMQ = sopsApi.createDomainAPI(Cook, domainNameForTestWMQ);
        SopsIDForTestWMQ_1 = sopsApi.generateSopsIdAPI(Cook, domainIdForTestWMQ, sopsNameForTestWMQ_1);
        SopsIDForTestWMQ_2 = sopsApi.generateSopsIdAPI(Cook, domainIdForTestWMQ, sopsNameForTestWMQ_1);
        sopsApi.createBinAPI(Cook, binName, domainNameForTestWMQ, domainIdForTestWMQ, loginForBinWMQ, passwordForBinWMQ,
                appNameForBinWMQ, sizeOfPoolThreadForBinWMQ, ipAdressForBinWMQ, portForBinWMQ, managerForBinWMQ, canalNameForBinWMQ, timeout);
        System.out.println("Перед тестом, выполнился beforeTest\n\n\n");
    }

    @After
    public void afterTest() {
        System.out.println("\n\n\nТест закончился, выполняется afterTest");
        Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.stopDomainAPI(Cook, domainIdForTestWMQ);
//        sopsApi.stopDomainAPI(Cook, domainPrepaireDataId);
        if (domainSpeedId != null) sopsApi.stopDomainAPI(Cook, domainSpeedId);
        if (domainForTestcheckSOPSWithIbmAndLocalQueueId != null)
            sopsApi.stopDomainAPI(Cook, domainForTestcheckSOPSWithIbmAndLocalQueueId);
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }


    @AfterClass
    public static void afterClass() {
        String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "true|true", "true|true", "false|false", "false|false", "true|true", "false|false", "false|false", "false|false");

        System.out.println("\n\n\nТесты закончились, выполняется afterClass");

        String CookWMQ = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));

        ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "RECURSIVE.IN", "QM1");
        ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "RECURSIVE.OUT", "QM1");
        ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "SPEED.IN.FESB", "QM1");
        ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "SPEED.OUT.FESB", "QM1");

        for (int i = 0; i < 11; i++) {
            ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "A" + (i + 1), "QM1");
        }

        for (int i = 0; i < 5; i++) {
            ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "B" + (i + 1), "QM1");
        }

        for (int i = 0; i < 6; i++) {
            ibmMqApi.deleteQueueToWmqAPI(CookWMQ, "C" + (i + 1), "QM1");
        }

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-5-1");
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-cbr");
        }

        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void checkSOPSWithIbmAndLocalQueue() throws Exception {
        String domain = "Проверка local-IBM";
        String sops1 = "Fesb-IBM";
        String sops2 = "IBM-Fesb";
        String nameLocalQueue = "Вход";
        String nameLocalQueueIBM = "WMQ";
        String nameQueueFromIbm2 = "FROM_IBM";
        String URL_IBM = "https://192.168.57.240:9443/ibmmq/console/login.html";

        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        ibmMqApi.createQueueToWmqAPI(CookWMQ1, nameLocalQueueIBM, "QM1");

        Object[] constant1 = {"constantIP", "192.168.57.240", true};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        open("/");
        sopsPage = new SOPSPage();
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domain);
//        sopsPage.createDomain(domain);
        domainForTestcheckSOPSWithIbmAndLocalQueueId = url().split("/broker/")[1];
        System.out.println(domainForTestcheckSOPSWithIbmAndLocalQueueId);
        sopsPage.clickToAddSOPSButton(domain);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "Менеджер очередей", "QM", nameLocalQueue, null, null);
        creationSOPSPage.addWmqInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, nameLocalQueueIBM);
        creationSOPSPage.addParameterWMQ("Фабрика сессий IBM MQ", CreationSOPSPage.ParameterType.select, "", "MQM", true);
        creationSOPSPage.inputSOPSNameAndSave(sops1);
        basePage.click(creationSOPSPage.returnToSOPSList);
        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsPage.createWmqSessionFactoryInAdditionalConfiguration("MQM", "${constantIP}", "1414", "QM1",
                "DEV.ADMIN.SVRCONN", "admin", "passw0rd", "CBR_TEST", "50");

        sopsPage.clickToAddSOPSButton(domain);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addWmqInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, nameLocalQueueIBM);
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Менеджер очередей", "QM", nameQueueFromIbm2, null, null);
        creationSOPSPage.addTransactionsToConfigSops(MQMTransactionPolicy);
        creationSOPSPage.inputSOPSNameAndSave(sops2);
        sopsPage.startDomain(domain);

        sopsPage.sendNSavedMessages(sops1, 2);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueCheckAllParameters("QM", nameQueueFromIbm2, "2", "0", "2", "0", "Локальная", "", "");

//        queueManagerPage.queueCheckAllParameters(nameQueueFromIbm2, "2", "0", "2", "0", "Локальная", "");

        sopsPage.goToDomain(domain);
        sopsPage.sopsCheckAllParameters(sops1, "Локальная очередь: " + nameLocalQueue, 2);
        sopsPage.sopsCheckAllParameters(sops2, "IBM MQ: " + nameLocalQueueIBM, 2);
        sopsApi.stopDomainAPI(Cook1, domainID_1);
    }

    @Description("Wmq Check-Box")
    @Test
    @Parameters(value = {
            "Выполнять при условии simple | ${body} == 'Astra' | Astra  | Бубль Гум | A1",
//            "Выполнять при условии HEADER | ${body} == 'Astra' | A2",
//            "Выполнять при условии SPEL | ${body} == 'Astra' | A3",
//            "Выполнять при условии GROOVY | ${body} == 'Astra' | A4",
//            "Выполнять при условии JAVASCRIPT | ${body} == 'Astra' | A5",
//            "Выполнять при условии XPATH | ${body} == 'Astra' | A6",
//            "Работать с кешом | null | null | null | A7",
            "Включить поддержку Простого языка | ${body} | A8 | slon | A8",
            "Включить поддержку SLA | null | 1 | 5000 | A9",
//            "Обработать в отдельном потоке (результат не модифицирует сообщения) | null | A10",
    })
    public void WmqDefaultCheckBox(String typeCondition, String valueConditions, String passMessage, String failMessage, String ququeWmq) {
        sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
        sopsApi.createSopsWithLocalQueueAndWmqAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_1, sopsNameForTestWMQ_1,
                localQueueNameForTestWMQ_1, ququeWmq, transactionPolicy);
        sopsApi.createSopsWithWmqAndLocalQueueAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_2, sopsNameForTestWMQ_2,
                localQueueNameForTestWMQ_2, ququeWmq, transactionPolicy);
        sopsPage.goToDomain(domainNameForTestWMQ);
        sopsPage.searchSops(sopsNameForTestWMQ_1);
        basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
        basePage.click($x(String.format(creationSOPSPage.namePointInSops, ququeWmq)));

        switch (typeCondition) {
            case "Выполнять при условии simple":
            case "Выполнять при условии HEADER":
            case "Выполнять при условии SPEL":
            case "Выполнять при условии GROOVY":
            case "Выполнять при условии JAVASCRIPT":
            case "Выполнять при условии XPATH":
                basePage.switchCheckBox(BasePage.thingEnableOrDisable.ENABLED, "Выполнять при условии");
                basePage.click($x(String.format(creationSOPSPage.somethingActivate2, "Тип условия")));
                basePage.valAndSelectElement($x(String.format(creationSOPSPage.somethingInput, "Тип условия")), typeCondition.split(" ")[3]);
                basePage.val(creationSOPSPage.conditionInput, valueConditions);
                creationSOPSPage.saveSOPS();
                sleep(2000);
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, passMessage, 1);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueNameForTestWMQ_2, passMessage);
                queueManagerMultyApi.clearQueueAPI(Cook, managerName, localQueueNameForTestWMQ_2);
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, failMessage, 1);
                queueManagerMultyPage.queueShouldBeEmpty(managerName, localQueueNameForTestWMQ_2);
                break;

            case "Работать с кешом":
                throw new AssertionError("Параметр не работает");
            case "Включить поддержку Простого языка":
                basePage.switchCheckBox(BasePage.thingEnableOrDisable.ENABLED, typeCondition);
                basePage.valAndSelectElement(creationSOPSPage.wmqNameInput, valueConditions);
                creationSOPSPage.saveSOPS();
                basePage.sleepAndRefresh(ThreadLocalRandom.current().nextInt(5000, 15000));
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, passMessage, 1);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueNameForTestWMQ_2, passMessage);
                basePage.sleepAndRefresh(ThreadLocalRandom.current().nextInt(5000, 15000));
                queueManagerMultyApi.clearQueueAPI(Cook, managerName, localQueueNameForTestWMQ_2);

                sopsPage.goToDomain(domainNameForTestWMQ);
                sopsPage.searchSops(sopsNameForTestWMQ_1);
                basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
                basePage.click($x(String.format(creationSOPSPage.namePointInSops, valueConditions)));
                basePage.switchCheckBox(BasePage.thingEnableOrDisable.DISABLED, typeCondition);
                creationSOPSPage.saveSOPS();
                basePage.sleepAndRefresh(ThreadLocalRandom.current().nextInt(5000, 15000));
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, passMessage, 1);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.queueShouldBeEmpty(managerName, localQueueNameForTestWMQ_2);
                break;
            case "Включить поддержку SLA":
                int numMessage = 5;
                basePage.switchCheckBox(BasePage.thingEnableOrDisable.ENABLED, typeCondition);
                basePage.val(creationSOPSPage.maxNumMessagePerPeriodInput, passMessage);
                basePage.val(creationSOPSPage.timeOfOnePeriodInput, failMessage);
                creationSOPSPage.saveSOPS();
                basePage.sleepAndRefresh(ThreadLocalRandom.current().nextInt(5000, 15000));
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, passMessage, numMessage);
                sleep(25000);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.contentSpecificQueue(managerName, localQueueNameForTestWMQ_2);
                specificQueuePage = new SpecificQueuePage("");
                specificQueuePage.dispatchTimeMessageInTableList.shouldHaveSize(5);
                ArrayList<LocalTime> timeList = new ArrayList<>();

                for (SelenideElement oneTime : specificQueuePage.dispatchTimeMessageInTableList) {
                    System.out.println("Пытаемся добавить в список: " + oneTime);
                    timeList.add(basePage.stringToTime(oneTime.getText().split(" ")[1]));
                }

                basePage.sout("Список времени сообщений в таблице:" + timeList);

                for (int i = 0; i < numMessage - 1; i++) {
                    basePage.sout("Сравниваем: " + timeList.get(i).plusSeconds(5) + " и " + timeList.get(i + 1));
                    Assert.assertEquals(timeList.get(i).plusSeconds(5).toString().split("\\.")[0], (timeList.get(i + 1).toString().split("\\.")[0]));
                }
                break;

            case "Обработать в отдельном потоке (результат не модифицирует сообщения)":
                break;

            default:
                throw new AssertionError("Пропущен тест");
        }
    }

    @Description("Wmq Parameter Time Of Life")
    @Test
    @Parameters(value = {
            "Время жизни - бесконечность (по умолчанию) | null | B1",
            "Время жизни 5 сек - проверка жизни в WMQ | 50 | B2",
            "Время жизни 5 сек - проверка жизни в FESB | 100 | B3",
    })
    public void WmqParameterTimeOfLife(String name, String timeOfLife, String ququeWmq) {
        String message = "Проверка";
        String corellationIdMessage = null;
        String valuePriorityMessage = null;
        String valueRedirectedMessage = null;
        String typeJmsMessage = null;
        String nameFileMessage = null;

        int sumMessages;

        sopsApi.createSopsWithLocalQueueAndWmqAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_1, sopsNameForTestWMQ_1,
                localQueueNameForTestWMQ_1, ququeWmq, transactionPolicy);
        sopsApi.createSopsWithWmqAndLocalQueueAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_2, sopsNameForTestWMQ_2,
                localQueueNameForTestWMQ_2, ququeWmq, transactionPolicy);

        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));

        switch (name) {
            case "Время жизни - бесконечность (по умолчанию)":
                message = "test message";
                corellationIdMessage = "";
                valuePriorityMessage = "0";
                valueRedirectedMessage = "Нет";
                typeJmsMessage = "";
                nameFileMessage = "";
                sumMessages = 3;

                sopsPage.goToDomain(domainNameForTestWMQ);
                sopsPage.searchSops(sopsNameForTestWMQ_1);
                sleep(1000);
                basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
                basePage.click($x(String.format(creationSOPSPage.namePointInSops, ququeWmq)));

                creationSOPSPage.saveSOPS();
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, message, sumMessages);
                sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.queueShouldHaveDepth(managerName, localQueueNameForTestWMQ_2, 3);
                queueManagerMultyPage.contentSpecificQueue(managerName, localQueueNameForTestWMQ_2);
                specificQueuePage = new SpecificQueuePage("");
                specificQueuePage.messageCheckAllParameters(corellationIdMessage, MessagePage.Persistant.Yes, valuePriorityMessage, valueRedirectedMessage, "1376", typeJmsMessage, nameFileMessage);
                break;
            case "Время жизни 5 сек - проверка жизни в WMQ":
                sumMessages = 6;

                sopsPage.goToDomain(domainNameForTestWMQ);
                sopsPage.searchSops(sopsNameForTestWMQ_1);
                basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
                basePage.click($x(String.format(creationSOPSPage.namePointInSops, ququeWmq)));

                creationSOPSPage.addParameterWMQ("Время жизни сообщения", CreationSOPSPage.ParameterType.input, "-1", timeOfLife, true);
                creationSOPSPage.saveSOPS();
                sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
                sopsPage.stopSOPS(domainNameForTestWMQ, sopsNameForTestWMQ_2);

                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, message, sumMessages);
                sleep(2000);
                basePage.compareIntAndInt(sumMessages, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                sleep(4000);
                sopsPage.goToDomaineAndstartSOPS(domainNameForTestWMQ, sopsNameForTestWMQ_2);
                sleep(2000);
                queueManagerMultyApi.queueShouldNotExist(Cook, baseUrl(), managerName, localQueueNameForTestWMQ_2);
                basePage.compareIntAndInt(0, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                break;
            case "Время жизни 5 сек - проверка жизни в FESB":
                sumMessages = 6;

                sopsPage.goToDomain(domainNameForTestWMQ);
                sopsPage.searchSops(sopsNameForTestWMQ_1);
                basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
                basePage.click($x(String.format(creationSOPSPage.namePointInSops, ququeWmq)));

                creationSOPSPage.addParameterWMQ("Время жизни сообщения", CreationSOPSPage.ParameterType.input, "-1", timeOfLife, true);
                creationSOPSPage.saveSOPS();
                sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
                queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, message, sumMessages);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.queueShouldHaveDepth(managerName, localQueueNameForTestWMQ_2, sumMessages);
                sleep(10000);
                queueManagerMultyPage.queueShouldHaveDepth(managerName, localQueueNameForTestWMQ_2, sumMessages);

//                ibmMqPage.autorizationInIBM(URL_IBM);
                Assert.assertEquals(ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq), 0);
                break;
            case "Время жизни 0 сек | 0":
                break;
            default:
                throw new AssertionError("Тест пропущен");
        }
    }

    @Description("Wmq Parameter Reply To Queue Manager Name")
    @Test
    @Parameters(value = {
            "COA | MQRO_COA_WITH_FULL_DATA | null | C1 | C2",
            "COD | MQRO_COD_WITH_FULL_DATA | null | C3 | C4",
            "COA+COD | MQRO_COA_WITH_FULL_DATA | MQRO_COD_WITH_FULL_DATA | C5 | C6",
    })
    public void WmqParameterReplyToQueueManagerName(String nameTest, String report1, String report2, String ququeWmq, String ququeWmqForReplay) {
        String QM = "QM1";
        String message = "Проверялово";
        int sumMessages = 1;
        sopsApi.createSopsWithLocalQueueAndWmqAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_1, sopsNameForTestWMQ_1,
                localQueueNameForTestWMQ_1, ququeWmq, transactionPolicy);

//        basePage.openPage(baseUrl());
        refresh();
        sopsPage.goToDomain(domainNameForTestWMQ);
        sopsPage.searchSops(sopsNameForTestWMQ_1);
        basePage.doubleClick(sopsPage.rowAfterSearchSOPS);
        basePage.click($x(String.format(creationSOPSPage.namePointInSops, ququeWmq)));

        creationSOPSPage.addParameterWMQ("Имя менеджера очередей для ответа", CreationSOPSPage.ParameterType.input, "", QM, true);
        creationSOPSPage.addParameterWMQ("Имя очереди для ответа", CreationSOPSPage.ParameterType.input, "", ququeWmqForReplay, true);

        switch (nameTest) {
            case "COA":
            case "COD":
                creationSOPSPage.addParameterWMQ("Тип отчета", CreationSOPSPage.ParameterType.select, "", report1, true);
                break;
            case "COA+COD":
                ArrayList<String> parametersList = new ArrayList<>();
                parametersList.add(report1);
                parametersList.add(report2);
                creationSOPSPage.addSeveralParametersWMQ("Тип отчета", CreationSOPSPage.ParameterType.select, "", parametersList, true);
                break;
            default:
                throw new AssertionError("Пропущена проверка количества сообщений в WMQ");
        }

        creationSOPSPage.saveSOPS();
        sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, message, sumMessages);


        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));


//        ibmMqPage.autorizationInIBM(URL_IBM);
        sleep(10000);

        switch (nameTest) {
            case "COA":
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqForReplay));

//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmq));
//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmqForReplay));
                break;
            case "COD":
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                basePage.compareIntAndSize(0, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqForReplay));

//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmq));
//                basePage.compareIntAndSize(0, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmqForReplay));

                sopsApi.createSopsWithWmqAndLocalQueueAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_2, sopsNameForTestWMQ_2,
                        localQueueNameForTestWMQ_2, ququeWmq, transactionPolicy);
                sleep(10000);

                basePage.compareIntAndSize(0, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqForReplay));

//                basePage.compareIntAndSize(0, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmq));
//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmqForReplay));
                break;
            case "COA+COD":
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqForReplay));


//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmq));
//                basePage.compareIntAndSize(1, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmqForReplay));

                sopsApi.createSopsWithWmqAndLocalQueueAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_2, sopsNameForTestWMQ_2,
                        localQueueNameForTestWMQ_2, ququeWmq, transactionPolicy);
                sleep(10000);

                basePage.compareIntAndSize(0, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmq));
                basePage.compareIntAndSize(2, ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqForReplay));

//                basePage.compareIntAndSize(0, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmq));
//                basePage.compareIntAndSize(2, ibmMqPage.returnNumMessagesInBrowseQueueWMQ(ququeWmqForReplay));
                break;
            default:
                throw new AssertionError("Пропущена проверка количества сообщений в WMQ");
        }
    }

    @Test
    public void stopStartDomainAndCheckSumMessages() {
        String IMPORT_DOMAIN_NAME = commonComponents.createIndividualName("TEST_WMQ_RECURSIVE-");
        String SendMessagesToRecursiveIn = "SendMessagesToRECURSIVE.IN";
        String SendMessagesToRecursiveOut = "SendMessagesToRECURSIVE.OUT";
        String ibmRecursiveIn = "RECURSIVE.IN";
        String ibmRecursiveOut = "RECURSIVE.OUT";
        int totalAmount = 1000;

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, SendMessagesToRecursiveIn, "message", totalAmount / 2);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, SendMessagesToRecursiveOut, "message", totalAmount / 2);

        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/TEST_WMQ_RECURSIVE.zip", IMPORT_DOMAIN_NAME);
        sopsPage.startDomain(IMPORT_DOMAIN_NAME);

        basePage.sleepAndRefresh(60000);
        sopsPage.stopDomain(IMPORT_DOMAIN_NAME);
        System.out.println("3");
        basePage.sleepAndRefresh(15000);

        CookWMQ = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        int sumMessagesIbmRecursiveIn = ibmMqApi.getQueueDepthWMQ(CookWMQ, ibmRecursiveIn);
        System.out.println(sumMessagesIbmRecursiveIn);
        int sumMessagesIbmRecursiveOut = ibmMqApi.getQueueDepthWMQ(CookWMQ, ibmRecursiveOut);
        System.out.println(sumMessagesIbmRecursiveOut);
        basePage.compareIntAndSize(totalAmount, sumMessagesIbmRecursiveIn + sumMessagesIbmRecursiveOut);
    }

    @Test
    @Category(speed.class)
    public void speedWmq() {
        Date date1 = null;
        Date date2 = null;
        String IMPORT_DOMAIN_NAME1 = commonComponents.createIndividualName("TEST_WMQ_SPEED-");
        String preparationSops = "Отправка в SPEED.IN.FESB";
        String shiftingSops = "только перекладка (IBM)";
        String versionFesb = null;

        versionFesb = generalInformationPage.productVersion.getText();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/speed.zip", IMPORT_DOMAIN_NAME1);
        sleep(3000);
        sopsPage.autoUpdateOff();
        sopsPage.startDomain(IMPORT_DOMAIN_NAME1);
        domainSpeedId = url().split("/broker/")[1];

        sopsPage.goToDomaineAndstartSOPS(IMPORT_DOMAIN_NAME1, preparationSops);
        sopsPage.searchSops(preparationSops);
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText("200000"), 120000);

        sopsPage.goToDomaineAndstartSOPS(IMPORT_DOMAIN_NAME1, shiftingSops);
        basePage.autoUpdateOn();
        sopsPage.searchSops(shiftingSops);
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText("200000"), 120000);
        sopsPage.stopSOPS(IMPORT_DOMAIN_NAME1, shiftingSops);
        sleep(1000);
        sopsPage.checkSpeedAndWriteToFile(shiftingSops, versionFesb, 35, 5800, "speedStatistic_WMQ-WMQ.txt");
    }

    @Test
    @Parameters(value = {
            "Транзакция | 1000 | 1000",
//            "Транзакция откат | 0 | 1000",
    })
    public void transactionWmq(String domainName, int amountWwqTransactionOut, int amountWwqTransactionDLQ) {
        sopsPage.goToDomainInGroup(groupDomains, domainPrepaireData);
        basePage.click(sopsPage.runDomainButton);
        sopsPage.sendMessage(sopsDataForTransaction, 1, "");
        sleep(5000);
        basePage.click(sopsPage.stopDomainButton);
        sopsPage.runDomainButton.shouldBe(Condition.visible);

        sopsPage.goToDomainInGroup(groupDomains, domainName);
        sopsPage.autoUpdateOn();
        basePage.click(sopsPage.runDomainButton);
        sopsPage.searchSops("IN.TO.DLQ");
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText("1000"), 300000);
        sopsPage.searchSops("DLQ");
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText("1000"), 300000);
        basePage.click(sopsPage.stopDomainButton);
        sleep(5000);
        sopsPage.runDomainButton.shouldBe(Condition.visible);


        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        int sumMessagesIbmTransactionOut = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "WMQ_TRANSACTION_OUT");

//        ibmMqPage.autorizationInIBM(URL_IBM);
//        int sumMessagesIbmTransactionOut = ibmMqPage.depthQueueWMQ("WMQ_TRANSACTION_OUT");
        System.out.println(sumMessagesIbmTransactionOut);
        basePage.compareIntAndSize(amountWwqTransactionOut, sumMessagesIbmTransactionOut);

        int sumMessagesIbmTransactionDlq = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "WMQ_TRANSACTION_DLQ");
//        int sumMessagesIbmTransactionDlq = ibmMqPage.depthQueueWMQ("WMQ_TRANSACTION_DLQ");
        System.out.println(sumMessagesIbmTransactionDlq);
        basePage.compareIntAndSize(amountWwqTransactionDLQ, sumMessagesIbmTransactionDlq);

        sleep(5000);
        ibmMqApi.checkStatusChannelWmqApi(CookWMQ1, canalNameForBinWMQ, IbmMqApi.StatusChannel.Inactive);
//        ibmMqPage.checkStatusChanel(canalNameForBinWMQ, IbmMqPage.StatusChannel.Inactive);
    }

    @Test
    @Parameters(value = {
            "Цикл | 2000",
            "Цикл с пакетами | 2000",
            "Цикл с раздельной транзакцией | 2000",
    })
    public void cycleWmq(String domainName, int generalAmount) {
        sopsPage.goToDomainInGroup(groupDomains, domainPrepaireData);
        basePage.click(sopsPage.runDomainButton);
        sopsPage.sendMessage(sopsDataForCycle, 1, "");
        sleep(5000);
        basePage.click(sopsPage.stopDomainButton);
        sopsPage.runDomainButton.shouldBe(Condition.visible);

        sopsPage.goToDomainInGroup(groupDomains, domainName);
        basePage.click(sopsPage.runDomainButton);
        sleep(30000);
        basePage.click(sopsPage.stopDomainButton);
        sopsPage.runDomainButton.shouldBe(Condition.visible);


        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        int sumMessagesWMQ_CYCLE_IN = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "WMQ_CYCLE_IN");
        System.out.println("WMQ_CYCLE_IN = " + sumMessagesWMQ_CYCLE_IN);

        int sumMessagesWMQ_CYCLE_OUT = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "WMQ_CYCLE_OUT");
        System.out.println("WMQ_CYCLE_OUT = " + sumMessagesWMQ_CYCLE_OUT);

        basePage.compareIntAndSize(generalAmount, sumMessagesWMQ_CYCLE_IN + sumMessagesWMQ_CYCLE_OUT);
        System.out.println(generalAmount + " = " + sumMessagesWMQ_CYCLE_IN + sumMessagesWMQ_CYCLE_OUT);
        sleep(5000);
        ibmMqApi.checkStatusChannelWmqApi(CookWMQ1, canalNameForBinWMQ, IbmMqApi.StatusChannel.Inactive);
    }

    @Test
    public void checkReconnectionAfterRestartWMQ() {
        String ququeWmqIn = "B4";
        String ququeWmqOut = "B5";
        int sumSendMessages = 70000;

        sopsApi.startDomainAPI(Cook, domainIdForTestWMQ);
        sopsApi.createSopsWithLocalQueueAndWmqAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_1, sopsNameForTestWMQ_1,
                localQueueNameForTestWMQ_1, ququeWmqIn, transactionPolicy);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook, managerName, localQueueNameForTestWMQ_1, "message", sumSendMessages);
        sopsPage.goToDomain(domainNameForTestWMQ);
        sopsPage.searchSops(sopsNameForTestWMQ_1);
        basePage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText(Integer.toString(sumSendMessages)), 120000);
        sopsPage.stopSOPS(domainNameForTestWMQ, sopsNameForTestWMQ_1);
        sopsApi.createSopsWithWmqAndWmqAPI(Cook, domainIdForTestWMQ, SopsIDForTestWMQ_2, sopsNameForTestWMQ_2,
                ququeWmqIn, ququeWmqOut, transactionPolicy);

        sleep(20000);
        basePage.sout("Выполняем скрипт выключения IBM");
        basePage.executeBashCommand("sudo docker pause ibm-mq");
        basePage.sout("Выполнили скрипт выключения IBM");
        sleep(10000);
        try {
            open(URL_IBM);
        } catch (TimeoutException e) {
            System.out.println("IBM не отвечает и это хорошо");
        }

        basePage.sout("Выполняем скрипт включения IBM");
        basePage.executeBashCommand("sudo docker unpause ibm-mq");
        basePage.sout("Выполнили скрипт включения IBM");
        sleep(20000);
        open(URL_IBM);
        ibmMqPage.usernameInput.shouldBe(Condition.visible);

        open("/manager/");
        sopsPage.goToDomain(domainNameForTestWMQ);
        sopsPage.searchSops(sopsNameForTestWMQ_2);
        basePage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(Condition.exactText(Integer.toString(sumSendMessages)), 180000);

        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));

        int sumMessagesIbmququeWmqOut = ibmMqApi.getQueueDepthWMQ(CookWMQ1, ququeWmqOut);
        System.out.println(sumMessagesIbmququeWmqOut);
        basePage.compareIntAndSize(sumSendMessages, sumMessagesIbmququeWmqOut);
    }

    @Test
    public void checkRollbackTransaction() {
        String sopsName1 = "Обработка ошибок";
        String sopsName2 = "Обработка";

        String wmqQueueName1 = "WMQ.NESTED_TRANSACTION_TEST.IN";
        String wmqQueueName2 = "WMQ.NESTED_TRANSACTION_TEST.EMPTY_QUEUE";
        String wmqQueueName3 = "WMQ.NESTED_TRANSACTION_TEST.PROCESS_QUEUE";
        String wmqQueueName4 = "WMQ.NESTED_TRANSACTION_TEST.DLQ";

        String[] point2_0 = new String[]{"Настройки СОПС|Настройки СОПС|Описание|Обработчик ошибок домена", "чек-бокс2|Транзакционность|вкл", "селект|Политика распространения транзакций|wmqSessionFacotryTransactionPolicy", "чек-бокс2|Кэширование потока|выкл"};
        String[] point2_1 = new String[]{"Вход|IBM MQ|" + wmqQueueName1};
        String[] point2_2 = new String[]{"Выход|IBM MQ|" + wmqQueueName2};
        String[] point2_3 = new String[]{"Выход|IBM MQ|" + wmqQueueName3, "чекбокс-в-параметрах|Запуск во вложенной транзакции|вкл"};
        String[] point2_4 = new String[]{"Выход|Программная трансформация|Groovy|Ручной ввод|throw new RuntimeException();"};
        String[][] pointAll_2 = {point2_0, point2_1, point2_2, point2_3, point2_4};

        String[] schemesAllErrorHandlers = {"Обработчик ошибок|errorHandler", "Обработка ошибок|Политика по умолчанию"};
        String[] schemesAllSessionFactoryIbm = {"Фабрика сессий IBM MQ|wmqSessionFacotry", "выкл|192.168.57.240|1414|QM1|DEV.ADMIN.SVRCONN|выкл|admin|passw0rd|FESB_TEST_NESTED_TRANSACTION|13|5000|Раздельная транзакция на точку входа и выхода|null|null"};

        String CookWMQ = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        ibmMqApi.createQueueToWmqAPI(CookWMQ, wmqQueueName1, "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, wmqQueueName2, "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, wmqQueueName3, "QM1");
        ibmMqApi.createQueueToWmqAPI(CookWMQ, wmqQueueName4, "QM1");

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.deleteBinAPI(Cook1, baseUrl(), domainIdForTestWMQ, "WMQ");
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainIdForTestWMQ);

        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainNameForTestWMQ, schemesAllErrorHandlers);
        sopsPage.goToSops(domainNameForTestWMQ, sopsName1);
        for (int x = 0; x < sopsPage.deletePointsIcon.size() - 1; ) {
            System.out.println(sopsPage.deletePointsIcon.size());
            basePage.click(sopsPage.deletePointsIcon.get(1));
            basePage.click(sopsPage.deleteButton);
        }
        creationSOPSPage = new CreationSOPSPage("empty");
        basePage.click($x(String.format(creationSOPSPage.pointInLeftMenu, "Ссылка на СОПС")));

        if ($x(String.format(creationSOPSPage.checkBoxOfParameter2, "Глобальная")).parent().attr("class").equals("ant-checkbox-wrapper ant-checkbox-wrapper-checked")) {
            basePage.click($x(String.format(creationSOPSPage.checkBoxOfParameter2, "Глобальная")));
        }

        creationSOPSPage.addWmqInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, wmqQueueName4);
        creationSOPSPage.addParameter("Запуск во вложенной транзакции", true);
        creationSOPSPage.addRollBackInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "");
        basePage.click($x(String.format(creationSOPSPage.checkBoxOfParameter2, "Откатывать только последнюю транзакцию")));
        creationSOPSPage.saveSOPS();

        sopsPage.createAdditionalConfiguration(domainNameForTestWMQ, schemesAllSessionFactoryIbm);
        creationSOPSPage.createSOPS(domainNameForTestWMQ, sopsName2, pointAll_2);

        sopsPage.editProperties(domainNameForTestWMQ, null, null, null, null, null, null, null, "errorHandler", null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        basePage.click(basePage.restartDomainButtonInPopUp);
        sleep(5000);
        CookWMQ = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        ibmMqApi.sendMessageToQueueWmqAPI(CookWMQ, wmqQueueName1, "QM1", testName.getMethodName());
        sleep(5000);
        basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ, wmqQueueName3));
        basePage.compareIntAndSize(1, ibmMqApi.getQueueDepthWMQ(CookWMQ, wmqQueueName4));
        basePage.compareIntAndSize(0, ibmMqApi.getQueueDepthWMQ(CookWMQ, wmqQueueName1));
        basePage.compareIntAndSize(0, ibmMqApi.getQueueDepthWMQ(CookWMQ, wmqQueueName2));
    }
}



