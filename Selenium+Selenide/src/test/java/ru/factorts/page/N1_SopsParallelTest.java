package ru.factorts.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N1_SopsParallelTest extends Base {

    @Rule
    public TestName testName = new TestName();

    SOPSPage sopsPage;
    QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    QueueManagerArtemisPage queueManagerArtemisPage = new QueueManagerArtemisPage();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    CreationSOPSPage creationSOPSPage;
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    MessagePage messagePage = new MessagePage();
    static Api staticAPI = new Api();
    Api api = new Api();
    SOPSApi sopsApi = new SOPSApi();
    SettingsPage settingsPage;
    CommonComponents commonComponents = new CommonComponents();
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();
    DataBasePage dataBasePage = new DataBasePage();
    LogsPage logsPage = new LogsPage();
    RestPage restPage = new RestPage();

    String cookies = null;
    String domainName = commonComponents.createIndividualName("domainForCluster");
    String DomainNameForStopTest = "Домен 1";
    String DomainNameForForceStopTest = "Домен 2";
    String DomainNameForCopySopsToServer = "Домен 3";
    String SopsForEnvironment1 = commonComponents.createIndividualName("СОПС-1-");
    String SopsForEnvironment2 = commonComponents.createIndividualName("СОПС-2-");
    String LocalQueueForEnvironment1 = commonComponents.createIndividualName("Локальная очередь 1-");
    String LocalQueueForEnvironment2 = commonComponents.createIndividualName("Локальная очередь 2-");
    String LocalQueueForEnvironment3 = commonComponents.createIndividualName("Локальная очередь 3-");
    String LocalQueueForEnvironment4 = commonComponents.createIndividualName("Локальная очередь 4-");
    String LocalQueueForEnvironment5 = commonComponents.createIndividualName("Локальная очередь 5-");
    String LocalQueueForEnvironment6 = commonComponents.createIndividualName("Локальная очередь 6-");

    String addConfigurationName = commonComponents.createIndividualName("addConfigurationName");
    String managerName = "QM";

    @BeforeClass
    static public void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticAPI.startAllModules(baseUrl());
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        cookies = StaticAPI.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void afterTest() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        if (testName.getMethodName().contains("speedXML")) {
            try {
                queueManagerMultyApi.clearQueueAPI(cookies, "QM", "Локальная очередь для проверки ShedulerAndLocalQueue-1");
            } catch (AssertionError e) {
                System.out.println("Нет очереди для очистки, ничего страшного");
            }
        }
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
    public void checkSOPSWithHttpAndHttp() throws Exception {
        String port = ":" + portForCheckSOPSWithHttpAndLocalQueue;
        String domainName = "Домен для проверки HTTP";
        String sopsName1 = "CОПС для проверки HTTP-1";
        String sopsName2 = "CОПС для проверки HTTP-2";
        String sopsName3 = "CОПС для проверки HTTP-3";
        String message = "test HTTP";
        String localQueue1 = "Локальная очередь для проверки HTTP-1";
        String localQueue2 = "Локальная очередь для проверки HTTP-2";
        String linkHttp1 = "0.0.0.0" + port + "/testhttp";
        String linkHttp2 = "0.0.0.0" + port + "/testhttp2";
        int numMessageToForm = 0;
        int numMessageToApi = 10;
        String login = "log1";
        String password = "pas1";

        String[] point1_1 = new String[]{"Вход|HTTP|0.0.0.0:2001/testhttp"};
        String[] point1_2 = new String[]{"Выход|Преобразовать тело сообщения|java.lang.String|UTF-8"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueue1, "чекбокс-в-параметрах|Отключение синхронного ответа JMS-получателю|вкл"};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String[] point2_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueue1};
        String[] point2_2 = new String[]{"Выход|Очистить заголовки|*"};
        String[] point2_3 = new String[]{"Выход|HTTP|0.0.0.0:2001/testhttp2", "инпут|Тайм-аут сокета (мс)|300000", "инпут|Тайм-аут запроса соединения из диспетчера соединений (мс)|300000", "инпут-в-параметрах|Имя пользователя для аутентификации|{{login}}", "инпут-в-параметрах|Пароль пользователя для аутентификации|{{password}}", "селект-в-параметрах|HTTP метод|POST"};
        String[][] pointAll_2 = {point2_1, point2_2, point2_3};

        String[] point3_1 = new String[]{"Вход|HTTP|0.0.0.0:2001/testhttp2", "селект-в-параметрах|Настройки аутентификации (ссылка)|Auth"};
        String[] point3_2 = new String[]{"Выход|Преобразовать тело сообщения|java.lang.String|UTF-8"};
        String[] point3_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueue2, "чекбокс-в-параметрах|Отключение синхронного ответа JMS-получателю|вкл"};
        String[][] pointAll_3 = {point3_1, point3_2, point3_3};

        String[] configAuthHttp = {"Аутентификация HTTP (Локальные пользователи)|Auth|Basic", "${login}|${password}|role1-test2|test2|role2", "*|/*|role1|true-POST|/path/|role2|false"};

        String[] constantsOfDomain1 = {"login", login};
        String[] constantsOfDomain2 = {"password", password};
        String[][] allConstantsOfDomain = {constantsOfDomain1, constantsOfDomain2};

        sopsPage = new SOPSPage();
        sopsPage.createDomain(domainName);
        sopsPage.createConstantsOfDomain(domainName, allConstantsOfDomain);
        sopsPage.createAdditionalConfiguration(domainName, configAuthHttp);

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName, sopsName2, pointAll_2);
        creationSOPSPage.createSOPS(domainName, sopsName3, pointAll_3);
        sopsPage.startDomain(domainName);

        String Cookies = staticAPI.loginAPI(BUrl, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        for (int x = 0; x < numMessageToApi; x++) {
            api.postMessageToHttpApiWithCheckAnswer(Cookies, message, port, linkHttp1, login, password, message);
        }
        sopsPage.sopsCheckAllParameters(sopsName1, "HTTP: " + linkHttp1, numMessageToForm + numMessageToApi);
        sopsPage.sopsCheckAllParameters(sopsName2, "Локальная очередь: " + localQueue1, numMessageToForm + numMessageToApi);
        sopsPage.sopsCheckAllParameters(sopsName3, "HTTP: " + linkHttp2, numMessageToForm + numMessageToApi);

//        queueManagerPage = new QueueManagerPage();
//        messagePage.queueListPage();
        queueManagerMultyPage.queueCheckAllParameters(managerName, localQueue1, "0", "1", Integer.toString(numMessageToForm + numMessageToApi), Integer.toString(numMessageToForm + numMessageToApi), "Локальная", "", "");


//        queueManagerPage.queueCheckAllParameters(localQueue1, "0", "1", Integer.toString(numMessageToForm + numMessageToApi),
//                Integer.toString(numMessageToForm + numMessageToApi), "Локальная", "");
        queueManagerMultyPage.queueCheckAllParameters(managerName, localQueue2, Integer.toString(numMessageToForm + numMessageToApi), "0", Integer.toString(numMessageToForm + numMessageToApi), "0", "Локальная", "", "");


//        queueManagerPage.queueCheckAllParameters(localQueue2, Integer.toString(numMessageToForm + numMessageToApi), "0", Integer.toString(numMessageToForm + numMessageToApi), "0", "Локальная", "");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueue2, message);
    }

    @Test
    public void checkSOPSWithHttpAndAddBodyMessageAndAutentificationHttpLdap() throws Exception {
        String port = ":" + checkSOPSWithHttpAndAddBodyMessageAndAutentificationHttpLdap;
        String domainName = commonComponents.createIndividualName("Домен для проверки checkSOPSWithHttpAndAddBodyMessageAndAutentificationHttpLdap-");
        String sopsName1 = "CОПС для проверки HTTP-1";
        String linkHttp1 = "0.0.0.0" + port + "/testhttp";
        String login = "fry";
        String password = "fry";

        String[] point1_1 = new String[]{"Вход|HTTP|0.0.0.0:" + checkSOPSWithHttpAndAddBodyMessageAndAutentificationHttpLdap + "/testhttp", "селект-в-параметрах|Настройки аутентификации (ссылка)|" + addConfigurationName};
        String[] point1_2 = new String[]{"Выход|Установить тело сообщения|Hello world!"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] configAuthHttp = {"Аутентификация HTTP (LDAP)|" + addConfigurationName + "|Basic|${login adress}|dc=planetexpress,dc=com|cn=admin,dc=planetexpress,dc=com|GoodNewsEveryone|uid|uid|ou=people| | ", "*|/*||true-"};

        String[] constantsOfDomain1 = {"login adress", "ldap://192.168.57.240:389"};
        String[][] allConstantsOfDomain = {constantsOfDomain1};

        String Cookies = staticAPI.loginAPI(BUrl, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsApi.createDomainAPI(baseUrl(), Cookies, domainName);

        sopsPage = new SOPSPage();
        sopsPage.createConstantsOfDomain(domainName, allConstantsOfDomain);
        sopsPage.createAdditionalConfiguration(domainName, configAuthHttp);

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName, sopsName1, pointAll_1);
        sopsPage.startDomain(domainName);

        api.postMessageToHttpApiWithCheckAnswer(Cookies, "message", port, linkHttp1, login, password, "Hello world!");
    }

    @Test
    public void checkTimerAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки TimerAndLocalQueue-");
        String sopsName1 = "CОПС для проверки TimerAndLocalQueue-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки TimerAndLocalQueue-1";

        String[] point1_1 = new String[]{"Вход|Таймер|" + "Таймер для проверки TimerAndLocalQueue", "инпут-в-параметрах|Период|10s", "чекбокс-в-параметрах|Фиксированный интервал|вкл"};
        String[] point1_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[][] pointAll_1 = {point1_1, point1_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);

//        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sleep(2000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName1, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName1, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        sleep(10000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 2, 0, 0, 2, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName1, 2, 0, 0, 2, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkSopsSshAndSsh() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки SshAndSsh-");
        String sopsName1 = "CОПС для проверки SshAndSsh-1";
        String sopsName2 = "CОПС для проверки SshAndSsh-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки SshAndSsh-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки SshAndSsh-2";
        String localQueueForSopsName3 = "Локальная очередь для проверки SshAndSsh-3";
        String address1 = "fesb:fesb2016@192.168.57.240:22";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|SSH|" + address1};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2,};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String[] point2_1 = new String[]{"Вход|SSH|" + address1, "инпут-в-параметрах|Команда опроса|uname"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[][] pointAll_2 = {point2_1, point2_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "pwd", 1, false, "4");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "/home/fesb");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName3, "Linux");
    }

    @Test
    public void checkSopLinkToSopsAndLinkToSops() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LinkToSopsAndLinkToSops-");
        String sopsName1 = "CОПС для проверки LinkToSopsAndLinkToSops-1";
        String sopsName2 = "CОПС для проверки LinkToSopsAndLinkToSops-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки LinkToSopsAndLinkToSops-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LinkToSopsAndLinkToSops-2";

        String[] point1_1 = new String[]{"Вход|Ссылка на СОПС|" + managerName + "|" + sopsName1};
        String[] point1_2 = new String[]{"Выход|Установить тело сообщения|Конечное ${body}"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point2_2 = new String[]{"Выход|Ссылка на СОПС|" + sopsName1};
        String[] point2_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2, point2_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "тело сообщения", 1, false, "4");

//        queueManagerApi.sendMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "тело сообщения", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Конечное тело сообщения");
    }

    @Test
    public void checkSopsShedulerAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки ShedulerAndLocalQueue-");
        String sopsName1 = "CОПС для проверки ShedulerAndLocalQueue-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки ShedulerAndLocalQueue-1";

        String[] point1_1 = new String[]{"Вход|Планировщик|Cron-строка|* * * ? * *"};
        String[] point1_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[][] pointAll_1 = {point1_1, point1_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(10000);
        queueManagerMultyApi.queueShouldHaveRangeMessagesAPI(Cook1, managerName, localQueueForSopsName1, 10, 20, baseUrl());
    }

    @Test
    public void checkSopsLocalQueueAndTelegram() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки checkSopsLocalQueueAndTelegram-");
        String sopsName1 = "CОПС для проверки checkSopsLocalQueueAndTelegram-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки checkSopsLocalQueueAndTelegram-1";

        String botToken = "1029930068:AAFy2Cu0Re6lxbieD2O3IuvZBlo299tGbj4";
        String botID = "401813659";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Телеграм|" + botToken, "инпут-в-параметрах|ID Чата|" + botID};
        String[][] pointAll_1 = {point1_1, point1_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Допрое утро, Влатика", 1, false, "4");
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 0, 1, 0, 1, 1, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName1, 0, 1, 0, 1, 1, "local", "null", baseUrl());
    }

    @Test
    public void checkSopsTelegramAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки checkSopsTelegramAndLocalQueue-");
        String sopsName1 = "CОПС для проверки checkSopsTelegramAndLocalQueue-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки checkSopsTelegramAndLocalQueue-1";

        String botToken = "1029930068:AAFy2Cu0Re6lxbieD2O3IuvZBlo299tGbj4";

        String[] point1_1 = new String[]{"Вход|Телеграм|" + botToken};
        String[] point1_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[][] pointAll_1 = {point1_1, point1_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        basePage.userProfileMenu.shouldBe(visible);
    }

    @Test
    public void checkSopsSetVariablesAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки SetVariablesAndLocalQueue-");
        String sopsName1 = "CОПС для проверки SetVariablesAndLocalQueue-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки SetVariablesAndLocalQueue-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки SetVariablesAndLocalQueue-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Установить переменные|nameVar|Simple|По умолчанию|textVar"};
        String[] point1_3 = new String[]{"Выход|Установить тело сообщения|${body} + ${exchangeProperty.nameVar}"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "textBody", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "textBody + textVar");
    }

    @Test
    public void checkSopsRecordGlobalVariableAndReadGlobalVariable() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки RecordGlobalVariableAndReadGlobalVariable-");
        String sopsName1 = "CОПС для проверки RecordGlobalVariableAndReadGlobalVariable-1";
        String sopsName2 = "CОПС для проверки RecordGlobalVariableAndReadGlobalVariable-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки RecordGlobalVariableAndReadGlobalVariable-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки RecordGlobalVariableAndReadGlobalVariable-2";
        String localQueueForSopsName3 = "Локальная очередь для проверки RecordGlobalVariableAndReadGlobalVariable-3";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Записать глобальную переменную|Домен|nameVar|Simple|По умолчанию|textGlobalVar"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[] point2_2 = new String[]{"Выход|Прочитать глобальную переменную|Домен|Тело сообщения|nameVar"};
        String[] point2_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[][] pointAll_2 = {point2_1, point2_2, point2_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "textBody", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, "textBody", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName3, "textGlobalVar");
    }

//    @Test
//    public void checkSopsSmbAndSmb() {
//        String domainName1 = commonComponents.createIndividualName("Домен для проверки SmbAndSmb-");
//        String sopsName1 = "CОПС для проверки SmbAndSmb-1";
//        String sopsName2 = "CОПС для проверки SmbAndSmb-2";
//        String localQueueForSopsName1 = "Локальная очередь для проверки SmbAndSmb-1";
//        String localQueueForSopsName2 = "Локальная очередь для проверки SmbAndSmb-2";
//
//        String path = "greg@192.168.57.216/Users/greg/Desktop/ForTests";
//        String password = "1qaz!QAZ";
//        String message = "SMB";
//
//        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + localQueueForSopsName1};
//        String[] point1_2 = new String[]{"Выход|SMB|" + path, "инпут-в-параметрах|Пароль|" + password};
//        String[][] pointAll_1 = {point1_1, point1_2};
//
//        String[] point2_1 = new String[]{"Вход|SMB|" + path, "инпут-в-параметрах|Пароль|" + password, "чекбокс-в-параметрах|Удалить файл|вкл"};
//        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + localQueueForSopsName2};
//        String[][] pointAll_2 = {point2_1, point2_2};
//
//        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
//        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
//        refresh();
//
//        creationSOPSPage = new CreationSOPSPage("empty");
//        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
//        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
//
//        queueManagerApi.sentMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "Допрое утро, Влатика", 1, false, "4");
//        sleep(5000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerPage = new QueueManagerPage();
//        queueManagerPage.queueShouldHaveSpecificMessage(localQueueForSopsName2, message);
//    }

    @Test
    public void checkSopsWebSocketAndWebSocket() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки WebSocketAndWebSocket-");
        String sopsName1 = "CОПС для проверки WebSocketAndWebSocket-1";
        String path = "192.168.57.240:" + portForCheckWebSocketAndWebSocket + "/echo";
        String message = "Сообщение для проверки Websocket";

        String[] point1_1 = new String[]{"Вход|WebSocket|/echo", "инпут-в-параметрах|Порт|" + portForCheckWebSocketAndWebSocket};
        String[] point1_2 = new String[]{"Выход|Установить тело сообщения|Новое ${body}"};
        String[] point1_3 = new String[]{"Выход|WebSocket|/echo", "инпут-в-параметрах|Порт|" + portForCheckWebSocketAndWebSocket};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        String asd = Selenide.executeAsyncJavaScript("var callback = arguments[arguments.length - 1];var socket = new WebSocket('ws://" + path + "');socket.onmessage = function(message) {callback(message.data);};socket.onopen = function() {socket.send('" + message + "');};");

        basePage.compareStringAndString("Новое " + message, asd);
    }

    @Test
    public void checkSopsCustomPointAndCustomPoint() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки CustomPointAndCustomPoint-");
        String sopsName1 = "CОПС для проверки CustomPointAndCustomPoint-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки CustomPointAndCustomPoint-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки CustomPointAndCustomPoint-2";
        String url1 = "localmq://" + localQueueForSopsName1;
        String url2 = "localmq://" + localQueueForSopsName2;

        String[] point1_1 = new String[]{"Вход|Пользовательская точка входа|" + url1};
        String[] point1_2 = new String[]{"Выход|Пользовательская точка выхода|" + url2};
        String[][] pointAll_1 = {point1_1, point1_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "textBody", 1, false, "4");
        sleep(2000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Description("Create errorhandler and check his working capacity")
    @Test
    public void errorHandlerSopsTest() {
        String DomainForErrorHandlerSopsTest = "DomainForErrorHandlerSopsTest";
        String localQueueIn = "ErrorIn";
        String localQueueOut = "ErrorOut";
        String localQueueError = "DEAD.LETTER.QUEUE2";
        String sopsName = "Sops_Error";
        String errorHandlerName = "CustomErrorHandler";
        String addConfigurationName = "CustomErrorHandler";

        String[] schemesAll = {"Обработчик ошибок|" + addConfigurationName, "CustomErrorHandler|Политика по умолчанию"};
        String domainID = sopsApi.createDomainAPI(cookies, DomainForErrorHandlerSopsTest);
        refresh();
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(DomainForErrorHandlerSopsTest, schemesAll);
        creationSOPSPage = new CreationSOPSPage();
        $x(String.format(creationSOPSPage.nameComponentInPointEnter, "Ссылка на СОПС")).shouldBe(Condition.visible);
        sopsApi.startDomainAPI(cookies, domainID);
        creationSOPSPage = new CreationSOPSPage();
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Менеджер очередей", managerName, localQueueError, null, null);
        basePage.click(creationSOPSPage.saveIcon);
        basePage.click(basePage.saveButton);
        creationSOPSPage.returnToSOPSList.shouldBe(enabled).click();
        sopsPage.clickToAddSOPSButton(DomainForErrorHandlerSopsTest);
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.INPUT, "Менеджер очередей", "", localQueueIn, null, null);
        creationSOPSPage.treatmentAddFormatConvertion("JSON → XML");
        creationSOPSPage.addLocalQueueInInputOrOutputPoint(CreationSOPSPage.Point.OUTPUT, "Менеджер очередей", "", localQueueOut, null, null);
        creationSOPSPage.changeSOPSErrorHandler(errorHandlerName);
        creationSOPSPage.inputSOPSNameAndSave(sopsName);
        creationSOPSPage.returnToSOPSList.shouldBe(enabled).click();
        basePage.click(sopsPage.sendMessageTab);
        messagePage = new MessagePage();
        messagePage.sendMessageInQueueNTimesAtOnce("Мультименеджер очередей", "QM", localQueueIn, "namePackage.txt", 2);
//        messagePage.queueListPage();
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveNMessagesInDepth(managerName, localQueueError, 2);
    }

    @Test
    public void checkSopsSftpAndSftp() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String sopsName2 = "CОПС для проверки " + testName.getMethodName() + "-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки SftpAndSftp-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки SftpAndSftp-2";
        String sftpLink1 = "192.168.194.69/test_sftp";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|SFTP|" + sftpLink1, "инпут-в-параметрах|Имя пользователя|fesb", "инпут-в-параметрах|Пароль|fesb1!", "чекбокс-в-параметрах|Использовать файл известных хостов|вкл"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|SFTP|" + sftpLink1, "инпут-в-параметрах|Имя пользователя|fesb", "инпут-в-параметрах|Пароль|fesb1!", "чекбокс-в-параметрах|Использовать файл известных хостов|вкл", "чекбокс-в-параметрах|Удалить файл|вкл"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test SftpAndSftp", 1, false, "4");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkSopsPop3AndSmtp() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки Pop3AndSmtp-");
        String sopsName1 = "CОПС для проверки Pop3AndSmtp-1";
        String sopsName2 = "CОПС для проверки Pop3AndSmtp-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки Pop3AndSmtp-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки Pop3AndSmtp-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|SMTP|smtp.yandex.ru|fesbfesb|465", "чек-бокс|SSL|вкл", "инпут-в-параметрах|Пароль|trambon2", "инпут-в-параметрах|Получатели почты|fesbfesb@yandex.ru", "инпут-в-параметрах|От кого (адрес)|fesbfesb@yandex.ru", "инпут-в-параметрах|Тема сообщения|Тестовое сообщение"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|POP3|pop.yandex.ru|fesbfesb|995", "чек-бокс|SSL|вкл", "инпут-в-параметрах|Пароль|trambon2", "чекбокс-в-параметрах|Режим отладки|вкл", "чекбокс-в-параметрах|Удалить сообщение|вкл"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test Pop3AndSmtp", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.searchQueue(managerName, localQueueForSopsName2);
        queueManagerMultyPage.autoUpdateOn();
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(exactText("1"), 360000);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Test Pop3AndSmtp");
    }

    @Test
    public void checkSopsImapAndSmtp() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки ImapAndSmtp-");
        String sopsName1 = "CОПС для проверки ImapAndSmtp-1";
        String sopsName2 = "CОПС для проверки ImapAndSmtp-2";
        String localQueueForSopsName1 = "Локальная очередь для проверки ImapAndSmtp-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки ImapAndSmtp-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1,};
        String[] point1_2 = new String[]{"Выход|SMTP|smtp.gmail.com|fesbfesb1|465", "чек-бокс|SSL|вкл", "инпут-в-параметрах|Пароль|trambon2", "инпут-в-параметрах|Получатели почты|fesbfesb1@gmail.com", "инпут-в-параметрах|От кого (адрес)|fesbfesb1@gmail.com", "инпут-в-параметрах|Тема сообщения|Тестовое сообщение"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|IMAP|pop.gmail.com|fesbfesb1|993", "чек-бокс|SSL|вкл", "инпут-в-параметрах|Пароль|trambon2"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test ImapAndSmtp", 1, false, "4");

//        queueManagerApi.sendMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "Test ImapAndSmtp", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.searchQueue(managerName, localQueueForSopsName2);
        queueManagerMultyPage.autoUpdateOn();
        queueManagerMultyPage.afterSearchQueueDepth.waitUntil(exactText("1"), 180000);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Test ImapAndSmtp");
    }

//    @Test
//    public void checkSopsRabbitmqAndRabbitmq() {
//        String domainName1 = commonComponents.createIndividualName("Домен для проверки RabbitmqAndRabbitmq-");
//        String sopsName1 = "CОПС для проверки RabbitmqAndRabbitmq-1";
//        String sopsName2 = "CОПС для проверки RabbitmqAndRabbitmq-2";
//        String localQueueForSopsName1 = "Локальная очередь для проверки RabbitmqAndRabbitmq-1";
//        String localQueueForSopsName2 = "Локальная очередь для проверки RabbitmqAndRabbitmq-2";
//
//        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + localQueueForSopsName1,};
//        String[] point1_2 = new String[]{"Выход|RabbitMQ|192.168.194.69:5672/", "инпут-в-параметрах|Имя пользователя|guest", "инпут-в-параметрах|Пароль|guest", "чекбокс-в-параметрах|Пропустить создание концентратора|вкл", "чекбокс-в-параметрах|Пропустить привязку очереди|вкл", "чекбокс-в-параметрах|Обязательно доставить|вкл", "чекбокс-в-параметрах|Исключение, если не доставлено|вкл", "инпут-в-параметрах|Ключ маршрутизации|new_queue_12345", "инпут-в-параметрах|Очередь|new_queue_12345", "чекбокс-в-параметрах|Пропустить создание очереди|выкл", "чекбокс-в-параметрах|Удалять автоматически|выкл"};
//        String[][] pointAll_1 = {point1_1, point1_2};
//
//        String[] point2_1 = new String[]{"Вход|RabbitMQ|192.168.194.69:5672/", "инпут-в-параметрах|Имя пользователя|guest", "инпут-в-параметрах|Пароль|guest", "чекбокс-в-параметрах|Пропустить создание концентратора|вкл", "чекбокс-в-параметрах|Пропустить создание очереди|вкл", "чекбокс-в-параметрах|Пропустить привязку очереди|вкл", "инпут-в-параметрах|Очередь|new_queue_12345", "инпут-в-параметрах|Ключ маршрутизации|new_queue_12345"};
//        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + localQueueForSopsName2};
//        String[][] pointAll_2 = {point2_1, point2_2};
//
//        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//
//        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
//        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
//        refresh();
//
//        creationSOPSPage = new CreationSOPSPage("empty");
//        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
//        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);
//        sleep(1000);
//        queueManagerApi.sentMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "Test RabbitmqAndRabbitmq", 1, false, "4");
//        sleep(3000);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//    }

    @Test
    public void checkSopsLocalQueueAndBin() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndBin-");
        String sopsName1 = "CОПС для проверки LocalQueueAndBin-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndBin-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndBin-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Бин|beanMethod|BeanExample"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        settingsPage = new SettingsPage();
        settingsPage.loadLibrary("/share/BeanExample-1.0-SNAPSHOT.jar");

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test LocalQueueAndBin", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Test LocalQueueAndBin123");
    }

    @Test
    public void checkSopsLocalQueueAndHandler() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndHandler-");
        String sopsName1 = "CОПС для проверки LocalQueueAndHandler-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndHandler-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndHandler-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Обработчик|ProcessorExample"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String[] settingConfig1 = {"Пользовательский|ProcessorExample", "ru.factorts.fesb.ProcessorExample"};
//        String[][] settingConfig1 = {classConf};

//        Object[] constant1 = {"constantNameOfAddConfiguration", "ProcessorExample", false};
//        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        settingsPage = new SettingsPage();
        settingsPage.loadLibrary("/share/files/ProcessorExample.jar");

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
//        sopsApi.createConstantsAPI(Cook1, domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingConfig1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test LocalQueueAndHandler", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Test LocalQueueAndHandler123");
    }

    @Test
    public void checkSopsLocalQueueAndCash() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndCash-");
        String sopsName1 = "CОПС для проверки LocalQueueAndCash-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndCash-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndCash-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Кэш|Добавить запись в кэш|keyName|cacheName|", "чек-бокс|Выполнять при условии|вкл|simple|${body} == '1'"};
        String[] point1_3 = new String[]{"Выход|Установить тело сообщения|test-${body}"};
        String[] point1_4 = new String[]{"Выход|Кэш|Взять из кэша по ключу|keyName|cacheName", "чек-бокс|Выполнять при условии|вкл|simple|${body} == 'test-1'"};
        String[] point1_5 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "1", 1, false, "4");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "1");

        queueManagerMultyApi.clearQueueAPI(Cook1, managerName, localQueueForSopsName2);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "2", 1, false, "4");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "test-2");
    }

    @Test
    public void checkSopsLocalQueueAndJsonValidation() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndJsonValidation-");
        String sopsName1 = "CОПС для проверки LocalQueueAndJsonValidation-1";
        String schema = "{" +
                "\"title\": \"Product\"," +
                "  \"description\": \"A product from Acme's catalog\"," +
                "  \"type\": \"object\"," +
                "  \"properties\": {" +
                "    \"productId\": {" +
                "      \"description\": \"The unique identifier for a product\"," +
                "      \"type\": \"integer\"" +
                "    }," +
                "    \"productName\": {" +
                "      \"description\": \"Name of the product\"," +
                "      \"type\": \"string\"" +
                "    }" +
                "  }," +
                "  \"required\": [ \"productId\", \"productName\" ]" +
                "}";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndJsonValidation-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndJsonValidation-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};

        String[] point1_2 = new String[]{"Выход|Попытка|java.lang.Throwable"};
        String[] point1_3 = new String[]{"Обработка-Попытка-0|JSON Валидация|Ручной ввод|" + schema};
        String[] point1_4 = new String[]{"Обработка-Попытка-0|Программная трансформация|Groovy|Ручной ввод|return 'OK';"};
        String[] point1_5 = new String[]{"Обработка-Попытка-1|Программная трансформация|Groovy|Ручной ввод|return 'NOT OK';"};
        String[] point1_6 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1,
                "{\\\"productId\\\": 1, \\\"productName\\\": \\\"name\\\"}", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "OK");

        queueManagerMultyApi.clearQueueAPI(Cook1, managerName, localQueueForSopsName2);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1,
                "{\\\"productId\\\": 1}", 1, false, "4");
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "NOT OK");
    }

    @Test
    public void checkSopsLocalQueueAndXsdValidation() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndXsdValidation-");
        String sopsName1 = "CОПС для проверки LocalQueueAndXsdValidation-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndXsdValidation-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndXsdValidation-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Попытка|org.apache.camel.ValidationException"};
        String[] point1_3 = new String[]{"Обработка-Попытка-0|XSD Валидация|Файл|resources/xsd/schemaXSD.xsd"};
        String[] point1_4 = new String[]{"Обработка-Попытка-0|Установить тело сообщения|OK"};
        String[] point1_5 = new String[]{"Обработка-Попытка-1|Установить тело сообщения|NOT OK: ${exception.message}"};
        String[] point1_6 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        sopsApi.createDirectoryAPI(Cook1, "xsd");
        String idFileModelData = sopsApi.uploadFileAPI(Cook1, "/home/fesb/Stand/share/schemaXSD.xsd");
//        String idFileModelData = sopsApi.uploadFileAPI(Cook1, "C:\\Users\\al198\\Desktop\\schemaXSD.xsd");
        sopsApi.setDirectoryForFileAPI(Cook1, "xsd", idFileModelData, "schemaXSD.xsd");

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1,
                "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>" +
                        "<mail>" +
                        "<subject>Subject</subject>" +
                        "<body>Body</body>" +
                        "</mail>", 1, false, "4");

        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "OK");
        queueManagerMultyApi.clearQueueAPI(Cook1, managerName, localQueueForSopsName2);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1,
                "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?>" +
                        "<mail>" +
                        "<subject>Subject</subject>" +
                        "</mail>", 1, false, "4");
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, "NOT OK");
    }

    @Test
    public void checkSopsLocalQueueAndBalancing() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки LocalQueueAndBalancing-");
        String sopsName1 = "CОПС для проверки LocalQueueAndBalancing-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки LocalQueueAndBalancing-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки LocalQueueAndBalancing-2";
        String localQueueForSopsName3 = "Локальная очередь для проверки LocalQueueAndBalancing-3";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Балансировка|Циклически (Равномерно)"};
        String[] point1_3 = new String[]{"Обработка-Балансировка-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[] point1_4 = new String[]{"Обработка-Балансировка-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test LocalQueueAndHandler", 500, false, "4");
//        queueManagerApi.sendMessgeInQueueAPI(Cook1, baseUrl(), localQueueForSopsName1, "Test LocalQueueAndHandler", 500, false, "4");
        sleep(10000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 250, 0, 0, 250, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 250, 0, 0, 250, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName3, 250, 0, 0, 250, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName3, 250, 0, 0, 250, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkSopsExecuteCommandLocally() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки ExecuteCommandLocally-");
        String sopsName1 = "CОПС для проверки LocalQueueAndBalancing-1";

        String localQueueForSopsName1 = "Локальная очередь для проверки ExecuteCommandLocally-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки ExecuteCommandLocally-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Исполнить команду локально|pwd"};
        String[] point1_3 = new String[]{"Выход|Преобразовать тело сообщения|java.lang.String|UTF-8"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        refresh();

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, "Test LocalQueueAndHandler", 1, false, "4");
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, "/fesb");
    }

    @Description("Cancel export domain")
    @Test
    public void cancelExportDomain() {
        String nameDomain = "Примеры";
        sopsPage = new SOPSPage();
        sopsPage.cancelExportDomain(nameDomain);
    }

    @Description("Cancel export domain")
    @Test
    public void cancelImportDomain() {
        String nameDomain = "Домен для проверки отмены импорта";
        sopsPage = new SOPSPage();
        sopsPage.cancelImportDomain("/share/testImportDomain.zip", nameDomain);
    }

    @Test
    public void checkTypeObgectInCreateAdditionalConfigOfSops() {
        String[] listOfConfig = {"Аутентификация FTP (Локальные пользователи)", "Аутентификация HTTP (LDAP)", "Аутентификация HTTP (Локальные пользователи)", "Конфигурация LDAP-соединения", "Параметры SSL контекста", "Источник данных", "Репозиторий идентификаторов", "Пользовательский", "Пространство имен XML", "Пул потоков", "Стратегия агрегации", "Формат данных", "Обработчик ошибок", "Политика повторной отправки брокера", "Политика повторной отправки менеджера очередей", "Менеджер транзакций", "Политика распространения транзакций", "Фабрика сессий IBM MQ", "Фабрика соединений Менеджера очередей", "Фабрика соединений Расширенного МО"};
        ArrayList<String> arrActual = new ArrayList<>();

        sopsPage = new SOPSPage();
        sopsPage.goToDomainInGroup("Архив", "Примеры");
        basePage.click(sopsPage.additionConfigurationTab);
        basePage.click(sopsPage.addConfigurationButton);
        basePage.click(sopsPage.typeObjectSelect);
        ArrayList<String> arrExpected = new ArrayList<>(Arrays.asList(listOfConfig));

        for (SelenideElement point : $$x(".//div[@label]")) {
            arrActual.add(point.getText());
        }

        System.out.println(arrActual);
        basePage.compareListAndList(arrExpected, arrActual);
        basePage.click(sopsPage.typeObjectSelect);

        for (String type : arrExpected) {
            basePage.click(sopsPage.typeObjectSelect);
            basePage.valAndSelectElement(sopsPage.typeObjectInput, type);
            sleep(2000);
            basePage.closeWindowIcon.shouldBe(visible);
        }
    }

    @Test
    public void copySopsToServer() {
        String domainName1 = commonComponents.createIndividualName("domain-1-" + testName.getMethodName());
        String domainName2 = commonComponents.createIndividualName("domain-2-" + testName.getMethodName());

        String ServerName = "NameForTest_CopySopsToServer";
        String hostOfServer = "http://192.168.57.240:9902/manager/";


        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        String domainID_2 = sopsApi.createDomainAPI(baseUrl_2(), Cook2, domainName2);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsApi.startDomainAPI(Cook2, baseUrl_2(), domainID_2);

        String SopsIDForCopySopsToServer = sopsApi.generateSopsIdAPI(Cook1, domainID_1, SopsForEnvironment1);
        sopsApi.createSopsWith2LocalQueueAPI(Cook1, baseUrl(), domainID_1, SopsIDForCopySopsToServer, DomainNameForForceStopTest, SopsForEnvironment1, LocalQueueForEnvironment5, LocalQueueForEnvironment6);
        api.addRemoteServerAPI(Cook1, ServerName, hostOfServer, "root", "root");
        sopsPage = new SOPSPage();
        sopsPage.copySopsToServer(domainName1, SopsForEnvironment1, ServerName, domainName2);
        sopsPage.checkCopiedSopsToServer(hostOfServer, domainName2, SopsForEnvironment1);
    }

    @Test
    public void forsedStopSendMessageAndStartSops() {
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String DomainIDForForceStopTest = sopsApi.createDomainAPI(Cook, DomainNameForForceStopTest);
        sopsApi.startDomainAPI(Cook, DomainIDForForceStopTest);
        String SopsIDForForceStopTest = sopsApi.generateSopsIdAPI(Cook, DomainIDForForceStopTest, SopsForEnvironment1);
        sopsApi.createSopsWith2LocalQueueAPI(Cook, baseUrl(), DomainIDForForceStopTest, SopsIDForForceStopTest, DomainNameForForceStopTest, SopsForEnvironment1, LocalQueueForEnvironment3, LocalQueueForEnvironment4);
        sleep(1000);

        int Times = 100000;
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(DomainNameForForceStopTest);
//        basePage.click($x(String.format(SOPSPage.specificDomainButtonXpath, DomainNameForForceStopTest)));
        sopsPage.sendMessage(SopsForEnvironment1, Times, "опа-опа-опа-па");
        int Processed1;
        int Processed2;
        int Sum;

        sopsPage.forcedStopSOPS(DomainNameForForceStopTest, SopsForEnvironment1);
        refresh();
        basePage.autoUpdateOff();
        sopsPage.searchSops(SopsForEnvironment1);

        sleep(10000);
        Processed1 = Integer.parseInt(sopsPage.cellProcessedSOPS.getText());
        basePage.sout("Обработано  до остановки: " + Processed1);
        Assert.assertTrue(Processed1 < Times);

        sleep(10000);
        Processed2 = Integer.parseInt(sopsPage.cellProcessedSOPS.getText());
        Assert.assertEquals(Processed1, Processed2);

        sopsPage.goToDomaineAndstartSOPS(DomainNameForForceStopTest, SopsForEnvironment1);
        basePage.sout("Должно быть обработано после остановки: " + (Times - Processed2));
        basePage.autoUpdateOn();
        basePage.sleepAndRefresh(120000);
        Sum = Integer.parseInt(sopsPage.cellProcessedSOPS.getText());
        basePage.sout("Обработано после повторного запуска: " + Sum);
        Assert.assertTrue(Sum == (Times - Processed2) || Sum == (Times - Processed2 - 1));
        sopsPage.sopsCheckAllParameters(SopsForEnvironment1, "Локальная очередь: " + LocalQueueForEnvironment3, Sum);
    }

    @Test
    public void checkSopsFtpServerAndFtp() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки FtpServerAndFtp-1");
        String domainName2 = commonComponents.createIndividualName("Домен для проверки FtpServerAndFtp-2");
        String sopsName1 = "CОПС для проверки FtpServerAndFtp-1";
        String sopsName2 = "CОПС для проверки FtpServerAndFtp-2";
        String localQueueForSopsName1 = "FtpServerAndFtp-1";
        String localQueueForSopsName2 = "FtpServerAndFtp-2";
        String ftpAdressSops1 = "fesb-test-0-2:" + portForCheckSopsFtpServerAndFtp;
        String ftpAdressSops2 = "0.0.0.0:" + portForCheckSopsFtpServerAndFtp;

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|FTP|" + ftpAdressSops1, "инпут-в-параметрах|Имя пользователя|user", "инпут-в-параметрах|Пароль|password"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|FTP сервер|" + ftpAdressSops2, "селект-в-параметрах|Авторизация FTP|ftpUsers"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2};

        Object[] constant1 = {"constantPassword", "password", false};
        Object[][] settingAllConstants = {constant1};
        String[] scheme1 = {"Аутентификация FTP (Локальные пользователи)|ftpUsers|", "user|${constantPassword}|/"};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        String domainID_2 = sopsApi.createDomainAPI(baseUrl_2(), Cook2, domainName2);
        sopsApi.createConstantsOfDomainAPI(Cook2, baseUrl_2(), domainID_2, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsApi.startDomainAPI(Cook2, baseUrl_2(), domainID_2);
        creationSOPSPage = new CreationSOPSPage("empty");

        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName2, scheme1);
        creationSOPSPage.createSOPS(domainName2, sopsName2, pointAll_2);

        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 0, 1, 0, 1, 1, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName1, 0, 1, 0, 1, 1, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook2, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl_2());
    }

    @Test
    public void checkSopsSQLAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки точки входа SQL-");
        String sopsName1 = "CОПС для проверки точки входа SQL";
        String localQueueForSopsName1 = "Локальная очередь для проверки точки входа SQL-2";
        String dbName = "DbForChekSqlIn";
        String dbPort = portForCheckSopsSQLAndLocalQueue;
        String sectionDbName = "sectionDbNameForChekSqlIn";
        String nameAddConfigForSQL = "NameAddConfigForSQL";
        String userBdName = "userBdNameLocSqlAndLocalQueue";
        String userBdPassword = "userBdPasswordLocSqlAndLocalQueue";
        String commandForExecuteInConsoleDbName = "create table a1000;" +
                "ALTER TABLE a1000 ADD COLUMN stolb1 VARCHAR (20);" +
                "ALTER TABLE a1000 ADD COLUMN stolb2 VARCHAR (20);" +
                "INSERT INTO a1000 (stolb1, stolb2) VALUES ('val1', 'val2');";
        String finalMessage = "[{\"STOLB1\":\"STOLB1\",\"COL2\":\"val2\"}]";


        String[] point1_1 = new String[]{"Вход|SQL|SELECT * FROM a1000", "селект-в-параметрах|Источник данных|" + nameAddConfigForSQL};
        String[] point1_2 = new String[]{"Выход|Преобразование форматов|OBJECT → JSON|"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String[] settingConfig1 = {"Источник данных|" + nameAddConfigForSQL, "org.h2.Driver|${constantClassJDBC}", userBdName + "|" + userBdPassword};
//        String[] url = {"${constantClassJDBC}"};
//        String[] user = {userBdName, userBdPassword};
//        String[][] settingConfig1 = {classJDBC, url, user};

        Object[] constant1 = {"constantClassJDBC", "jdbc:h2:tcp://fesb-test-0-1:" + dbPort + "/./" + sectionDbName, false};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        api.switchModuleAPI(Cook1, Base.baseUrl(), "factor-store", "activate");
        dataBasePage.createDb(DataBasePage.TypeDB.notInMemory, dbName, dbPort, "FarFarAwaySQLAndLocalQueue");
        dataBasePage.createSectioDb(dbName, sectionDbName);
        dataBasePage.executeCommandInDbConsole(dbName, sectionDbName, commandForExecuteInConsoleDbName);
        dataBasePage.createUserDb(dbName, sectionDbName, userBdName, "ALL", userBdPassword);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl_2(), Cook2, domainName1);
        sopsApi.createConstantsOfDomainAPI(Cook2, baseUrl_2(), domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook2, baseUrl_2(), domainID_1);
        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingConfig1);

        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName1, finalMessage);
    }

    @Test
    public void checkSopsLocalQueueAndSQL() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки точки обработки SQL-");
        String sopsName1 = "CОПС для проверки точки обработки SQL";
        String localQueueForSopsName1 = "Локальная очередь для проверки точки обработки SQL-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки точки обработки SQL-2";
        String dbName = "DbForChekSqlOut";
        String dbPort = portForCheckSopsLocalQueueAndSQL;
        String sectionDbName = "sectionDbNameForChekSqlOut";
        String nameAddConfigForSQL = "NameAddConfigForSQL";
        String userBdName = "userBdNameLocSqlAndLocalQueue";
        String userBdPassword = "userBdPasswordLocSqlAndLocalQueue";
        String commandForExecuteInConsoleDbName = "create table a1000;" +
                "ALTER TABLE a1000 ADD COLUMN stolb1 VARCHAR (20);" +
                "ALTER TABLE a1000 ADD COLUMN stolb2 VARCHAR (20);" +
                "INSERT INTO a1000 (stolb1, stolb2) VALUES ('val1', 'val2');";
        String finalMessage = "[{\"STOLB1\":\"STOLB1\",\"COL2\":\"val2\"}]";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|SQL|Запрос|SELECT * FROM a1000", "селект-в-параметрах|Источник данных|" + nameAddConfigForSQL};
        String[] point1_3 = new String[]{"Выход|Преобразование форматов|OBJECT → JSON|"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String[] settingConfig1 = {"Источник данных|" + nameAddConfigForSQL, "org.h2.Driver|jdbc:h2:tcp://fesb-test-0-1:" + dbPort + "/./" + sectionDbName, userBdName + "|" + userBdPassword};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        api.switchModuleAPI(Cook1, Base.baseUrl(), "factor-store", "activate");
        dataBasePage.createDb(DataBasePage.TypeDB.notInMemory, dbName, dbPort, "FarFarAwayLocalQueueAndSQL");
        dataBasePage.createSectioDb(dbName, sectionDbName);
        dataBasePage.executeCommandInDbConsole(dbName, sectionDbName, commandForExecuteInConsoleDbName);
        dataBasePage.createUserDb(dbName, sectionDbName, userBdName, "ALL", userBdPassword);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl_2(), Cook2, domainName1);
        sopsApi.startDomainAPI(Cook2, baseUrl_2(), domainID_1);
        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingConfig1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName, localQueueForSopsName1, "", 1, false, "4");
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, finalMessage);
    }

    @Test
    public void checkSopsMinaAndMina() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки MinaAndMina-");
        String sopsName1 = "CОПС для проверки MinaAndLocalQueue";
        String sopsName2 = "CОПС для проверки LocalQueueAndMina";
        String localQueueForSopsName1 = "LocalQueueAndMina-1";
        String localQueueForSopsName2 = "MinaAndLocalQueue-2";
        String linkToMina1 = "0.0.0.0:" + portForCheckSopsMinaAndMina;
        String linkToMina2 = "192.168.57.240:" + portForCheckSopsMinaAndMina;

        String[] point1_1 = new String[]{"Вход|Mina|" + linkToMina1 + "|tcp", "чекбокс-в-параметрах|Использовать текстовый формат|вкл"};
        String[] point1_2 = new String[]{"Выход|Установить заголовки", "параметры-заголовков|CamelMina2CloseSessionWhenComplete|Constant|По умолчанию|true"};
        String[] point1_3 = new String[]{"Выход|Установить тело сообщения|" +
                "HTTP/1.1 200 OK" +
                "Content-Type: text/html; charset=utf-8" +
                "Connection: close" +
                "Status: 200 OK" +
                "Go away!"};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String[] point2_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point2_2 = new String[]{"Выход|Установить заголовки", "параметры-заголовков|CamelMina2CloseSessionWhenComplete|Constant|По умолчанию|true"};
        String[] point2_3 = new String[]{"Выход|Установить тело сообщения|" +
                "GET / HTTP/1.1" +
                "Host: 192.168.57.240:29995" +
                "User-Agent: curl/7.64.1" +
                "Accept: */*"};
        String[] point2_4 = new String[]{"Выход|Mina|" + linkToMina2 + "|tcp", "чекбокс-в-параметрах|Использовать текстовый формат|вкл"};
        String[] point2_5 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_2 = {point2_1, point2_2, point2_3, point2_4, point2_5};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String Cook2 = api.loginAPI(Base.baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        String domainID_2 = sopsApi.createDomainAPI(baseUrl_2(), Cook2, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsApi.startDomainAPI(Cook2, baseUrl_2(), domainID_2);
        creationSOPSPage = new CreationSOPSPage("empty");
        open(baseUrl());
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        basePage.openPage(baseUrl_2(), "", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook2, baseUrl_2(), managerName, localQueueForSopsName1, "", 1, true, "4");
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook2, baseUrl_2(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook2, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl_2());
    }

    @Test
    public void checkSopsLocalQueueAndLocalBd() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки точки обработки LocalBd-");
        String sopsName1 = "CОПС для проверки точки обработки LocalBd-1";
        String localQueueForSopsName1 = "Локальная очередь для проверки точки обработки LocalBd-1";
        String localQueueForSopsName2 = "Локальная очередь для проверки точки обработки LocalBd-2";
        String dbName = "База данных для проверки точки обработки Лок.БД";
        String dbPort = portForCheckSopsLocalQueueAndLocalBd;
        String sectionDbName = "Раздел базы данных для проверки точки обработки Лок.БД";
        String commandForExecuteInConsoleDbName = "create table t1000;" +
                "ALTER TABLE t1000 ADD COLUMN col1 VARCHAR (20);" +
                "ALTER TABLE t1000 ADD COLUMN col2 VARCHAR (20);" +
                "INSERT INTO t1000 (col1, col2) VALUES ('val1', 'val2');";
        String finalMessage = "[{\"COL1\":\"val1\",\"COL2\":\"val2\"}]";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Локальная БД (H2)|" + dbName + "/" + sectionDbName + "|SELECT * FROM t1000"};
        String[] point1_3 = new String[]{"Выход|Преобразование форматов|OBJECT → JSON|"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        api.switchModuleAPI(Cook1, Base.baseUrl(), "factor-store", "activate");
        dataBasePage.createDb(DataBasePage.TypeDB.notInMemory, dbName, dbPort, "checkSopsLocalQueueAndLocalBd");
        dataBasePage.createSectioDb(dbName, sectionDbName);
        dataBasePage.executeCommandInDbConsole(dbName, sectionDbName, commandForExecuteInConsoleDbName);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        open(baseUrl());
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, finalMessage);
    }

    @Test
    public void checkSopsLocalBdAndLocalQueue() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName();
        String localQueueForSopsName2 = "Локальная очередь для проверки " + testName.getMethodName();
        String dbName = "База данных для проверки " + testName.getMethodName();
        String dbPort = portForCheckSopsLocalBdAndLocalQueue;
        String sectionDbName = "Раздел базы данных для проверки точки входа Лок.БД";
        String commandForExecuteInConsoleDbName1 = "create table t1000;" +
                "ALTER TABLE t1000 ADD COLUMN col1 VARCHAR (20);" +
                "ALTER TABLE t1000 ADD COLUMN col2 VARCHAR (20);" +
                "INSERT INTO t1000 (col1, col2) VALUES ('val1', 'val2');";
        String commandForExecuteInConsoleDbName2 = "INSERT INTO t1000 (col1, col2) VALUES ('val1', 'val2');";
        String finalMessage = "[{\"COL1\":\"val1\",\"COL2\":\"val2\"}]";

        String[] point1_1 = new String[]{"Вход|Локальная БД (H2)|" + dbName + "/" + sectionDbName + "|SELECT * FROM t1000"};
        String[] point1_2 = new String[]{"Выход|Преобразование форматов|OBJECT → JSON|"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        api.switchModuleAPI(Cook1, Base.baseUrl(), "factor-store", "activate");
        dataBasePage.createDb(DataBasePage.TypeDB.notInMemory, dbName, dbPort, "checkSopsLocalBdAndLocalQueue");
        dataBasePage.createSectioDb(dbName, sectionDbName);
        dataBasePage.executeCommandInDbConsole(dbName, sectionDbName, commandForExecuteInConsoleDbName1);

        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        open(baseUrl());
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, finalMessage);
    }

    @Test
    public void checkStatistic() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        int sumMessages = 150000;

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(Cook1, domainID_1, sopsName1);

        sopsApi.createSopsWith2LocalQueueAPI(Cook1, baseUrl(), domainID_1, SopsIDForStopTest, domainName, sopsName1, localQueueForSopsName1, localQueueForSopsName2);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", sumMessages);
        sopsApi.startDomainAPI(Cook1, domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(domainName1);
        sopsPage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(exactText(Integer.toString(sumMessages)), 300000);
        sopsPage.sopsCheckAllParameters(sopsName1, "Локальная очередь: " + localQueueForSopsName1, sumMessages);
        queueManagerMultyApi.clearQueueAPI(Cook1, managerName, localQueueForSopsName1);
        queueManagerMultyApi.clearQueueAPI(Cook1, managerName, localQueueForSopsName2);
        basePage.click(sopsPage.statisticsTab);
        $x(".//canvas").shouldBe(visible);

        Response response = sopsApi.getPerfomnceOfBrokerAPI(Cook1, baseUrl(), domainID_1, "5");
        System.out.println(response.getBody().asString());
        String aaa = response.path("meanRate").toString();

        for (String one : aaa.split(",")) {
            one = one.replace("[", "").replace("]", "").replace(" ", "");
            System.out.println(one);
            Assert.assertTrue(Float.parseFloat(one) >= 0);
        }

        basePage.click(sopsPage.clearStatisticButton);
        sleep(2000);
        Response response2 = sopsApi.getPerfomnceOfBrokerAPI(Cook1, baseUrl(), domainID_1, "5");
//        System.out.println(response2.getBody().asString());
        Assert.assertTrue(response2.getBody().asString().contains("\"routeName\":\"CОПС для проверки checkStatistic-1\",\"meanRate\":0.0,\"processed\":150000") || response2.getBody().asString().contains("[]"));

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", sumMessages);
        sleep(20000);

        Response response3 = sopsApi.getPerfomnceOfBrokerAPI(Cook1, baseUrl(), domainID_1, "5");
        System.out.println(response3.getBody().asString());
        String bbb = response3.path("meanRate").toString();

        Assert.assertTrue(Float.parseFloat(bbb.split(",")[0].replace("[", "").replace("]", "").replace(" ", "")) == 0);
        Assert.assertTrue(Float.parseFloat(bbb.split(",")[1].replace("[", "").replace("]", "").replace(" ", "")) > 0);

        for (String one : bbb.split(",")) {
            one = one.replace("[", "").replace("]", "").replace(" ", "");
            System.out.println(one);
            Assert.assertTrue(Float.parseFloat(one) >= 0);
        }
    }

    @Test
    public void checkLocalQueueAndLoop() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Цикл|3|Количество итераций"};
        String[] point1_3 = new String[]{"Обработка-Цикл-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 3, 0, 0, 3, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 3, 0, 0, 3, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkLocalQueueFilter() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " +
                testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String localQueueForSopsName3 = testName.getMethodName() + "-3";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Фильтр| ${body} == 'test' |simple"};
        String[] point1_3 = new String[]{"Обработка-Фильтр-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[] point1_4 = new String[]{"Обработка-Фильтр-1|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "test", 1);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueShouldNotExist(Cook1, baseUrl(), managerName, localQueueForSopsName3);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "test2", 1);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkLocalQueueAndBreakProcession() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Прервать обработку"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName1, 0, 1, 0, 1, 1, null, null, null, null);
        queueManagerMultyApi.queueShouldNotExist(Cook1, baseUrl(), managerName, localQueueForSopsName2);
    }

    @Test
    public void checkLocalQueueAndSegmentation() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String localQueueForSopsName3 = testName.getMethodName() + "-3";
        String localQueueForSopsName4 = testName.getMethodName() + "-4";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Сегментация|tokenize|@"};
        String[] point1_3 = new String[]{"Обработка-Сегментация-0|Программная трансформация|Groovy|Ручной ввод|request.body + \"BODY \""};
        String[] point1_4 = new String[]{"Обработка-Сегментация-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[] point1_5 = new String[]{"Обработка-Сегментация-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[] point1_6 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName4};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "A@B@C", 1);
        sleep(1000);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 3, 0, 0, 3, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 3, 0, 0, 3, 0, "local", "null", baseUrl());
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName2, 0, "ABODY ");
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName2, 1, "BBODY ");
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName2, 2, "CBODY ");

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName3, 3, 0, 0, 3, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName3, 3, 0, 0, 3, 0, "local", "null", baseUrl());
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName3, 0, "ABODY ");
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName3, 1, "BBODY ");
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName3, 2, "CBODY ");
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName4, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName4, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyPage.queueShouldHaveSomeSpecificMessages(managerName, localQueueForSopsName4, 0, "A@B@C");
    }

    @Test
    public void checkLocalQueueAndRestSwagger() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|REST Swagger|/share/petstore.json|getInventory"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(3000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 2);
        sleep(5000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 2, 0, 0, 2, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 2, 0, 0, 2, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkLocalQueueAndSoap() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Установить тело сообщения|" +
                "<GetCursOnDate xmlns=\"http://web.cbr.ru/\"><On_date>2020-11-22</On_date></GetCursOnDate>"};
        String[] point1_3 = new String[]{"Выход|SOAP|/share/cbr.wsdl|{http://web.cbr.ru/}DailyInfo|http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx|{http://web.cbr.ru/}DailyInfoSoap|GetCursOnDate|PAYLOAD"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(1000);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldContainsSpecificPhraseInMessage(managerName, localQueueForSopsName2, "GetCursOnDateResponse ");
    }

    @Test
    public void checkLocalQueueAndStreams() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String url = "/home/fesb/Stand/share/download/localFile/";

        String[] point1_1 = new String[]{"Вход|Локальный файл|/fesb/localFile"};
        String[] point1_2 = new String[]{"Выход|Логирование|About to process ${file:name} picked up using thread #${threadName}|sops-logger|Информационный"};
        String[] point1_3 = new String[]{"Выход|Потоки|PoolForTest"};
        String[] point1_4 = new String[]{"Обработка-Потоки-0|Логирование|Processing ${file:name} using thread #${threadName}|sops-logger|Информационный"};
        String[] point1_5 = new String[]{"Обработка-Потоки-0|Программная трансформация|Groovy|Ручной ввод|" +
                "import java.util.Random;" +
                "Random random = new Random();" +
                "// do some random delay between 0 - 5 sec" +
                "int delay = random.nextInt(5000);" +
                "Thread.sleep(delay);"};
        String[] point1_6 = new String[]{"Обработка-Потоки-0|Логирование|Processing ${file:name} using thread #${threadName}|sops-logger|Информационный"};
        String[] point1_7 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6, point1_7};

        String[] settingOfAddConfigAll = {"Пул потоков|PoolForTest", "10|15|${constantQueueName}|10"};
//        String[][] settingOfAddConfigAll = {settingOfAddConfig};

        Object[] constant1 = {"constantQueueName", "Queue1", true};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingOfAddConfigAll);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        File a = new File(url + "1.txt");
        File b = new File(url + "2.txt");
        try {
            a.createNewFile();
            b.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logsPage.setShowAllLogs("Информационный");
        basePage.autoUpdateOn();
        basePage.val(basePage.searchInput, "1.txt");
        sleep(2000);
        basePage.rowAfterSearch.shouldBe(visible);
        String logA = basePage.rowAfterSearch.getText().split("#")[2].split("-")[0];
        basePage.val(basePage.searchInput, "2.txt");
        sleep(2000);
        basePage.rowAfterSearch.shouldBe(visible);
        String logB = basePage.rowAfterSearch.getText().split("#")[2].split("-")[0];
        System.out.println(logA + " = " + logB);
        Assert.assertNotEquals("Значения равны", logA, logB);
    }

    @Test
    public void checkLocalQueueAndModelTransformation() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Преобразование модели| (FullStudent666)| (SmallStudent777)", "параметры-преобразования-модели|Трансформации|Параметр исходной модели|curs|curs", "параметры-преобразования-модели|Трансформации|Вычисляемое выражение|firstName|fullName|Simple|${body.firstName} ${body.lastName}|fullName"};
        String[] point1_3 = new String[]{"Выход|Преобразовать тело сообщения|java.lang.String|UTF-8"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createModelData(domainName1, "FullStudent666", false, "Пример XML файла", "-", "/share/ModelsData/FullStudent.xml");
        sopsPage.createModelData(domainName1, "SmallStudent777", false, "Пример XML файла", "-", "/share/ModelsData/SmallStudent.xml");
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1,
                "<Student>" +
                        "<id>5</id>" +
                        "<firstName>Pavel</firstName>" +
                        "<lastName>Morozov</lastName>" +
                        "<curs>666</curs>" +
                        "<city>Kazan</city>" +
                        "</Student>", 1);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Student>\n" +
                "    <fullName>Pavel Morozov</fullName>\n" +
                "    <curs>666</curs>\n" +
                "</Student>");
    }

    @Test
    public void checkLocalQueueAndAddAttachment() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Добавить вложение", "параметры-вложения|1.xml|Из тела||text/xml", "параметры-вложения|2.xml|Из тела||text/xml"};
        String[] point1_3 = new String[]{"Выход|Программная трансформация|Groovy|Файл не существует|/share/files/transformationForcheckLocalQueueAndAddAttachment.groovy|resources/transformationForcheckLocalQueueAndAddAttachment.groovy"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sleep(1000);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, testName.getMethodName(), 1);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "1.xml (MD5 = c00182a62b79b892bd44d400d5e93604), 2.xml (MD5 = c00182a62b79b892bd44d400d5e93604)");
    }

    @Test
    public void checkLocalQueueAndRecipientlist() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";
        String localQueueForSopsName3 = testName.getMethodName() + "-3";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Список получателей|constant"};
        String[] point1_3 = new String[]{"Обработка-Список получателей-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[] point1_4 = new String[]{"Обработка-Список получателей-0|Локальная очередь|" + managerName + "|" + localQueueForSopsName3};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkLocalQueueAndCustomProcessing() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Пользовательская обработка|<setBody><constant>Moonshine</constant></setBody>"};
        String[] point1_3 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(1000);
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "Moonshine");
    }

    @Test
    public void enumerationOfPointsSops() {
        sopsPage = new SOPSPage();
        String Cook1 = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.switchModuleAPI(Cook1, baseUrl(), "factor-qme", "activate");
        sopsPage.clickToAddSOPSButton("Main broker");
        sopsPage.enumerationOfPoints();
    }


    @Test
    public void checkCreateAndEditAdditionalConfigurationBrokerResendPolicy() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String addConfigurationNameResendPolicy = "ResendPolicy";
        String addConfigurationNameErrorHandler = "ErrorHandler_1";
        String guid = commonComponents.createIndividualName("guid");

        String pointIn1 = "{\"componentName\":\"InLocalMQEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь\",\"ssl\":false,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"" + localQueueForSopsName1 + "\",\"fullUri\":\"localmq://" + localQueueForSopsName1 + "\",\"isNonPooled\":false,\"prefix\":\"localmq\",\"endpointTag\":\"from\",\"breakpoint\":false,\"suspended\":false,\"debugData\":null,\"name\":\"Локальная очередь\",\"icon\":\"fa fa-envelope\",\"group\":\"Менеджеры очередей\"}";

        String pointOut1 = "{\"componentName\":\"OutLocalSQLEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная БД (H2)\",\"ssl\":false,\"properties\":[],\"hasCondition\":false,\"conditionType\":\"SIMPLE\",\"condition\":null,\"cacheAlias\":null,\"cacheKey\":null,\"useCache\":false,\"uri\":\"123\",\"fullUri\":\"localsql://123?dataSource=FACTOR_SYSTEM_DB%3ASTORE_PARTITION_9ee83495-926a-4882-b937-a080a4b09366\",\"prefix\":\"localsql\",\"endpointTag\":\"to\",\"breakpoint\":false,\"suspended\":false,\"debugData\":null,\"name\":\"Локальная БД (H2)\",\"icon\":\"fa fa-database\",\"sla\":null,\"isWireTap\":false,\"useVariables\":false,\"componentType\":\"QUERY\",\"dataSource\":\"FACTOR_SYSTEM_DB:STORE_PARTITION_9ee83495-926a-4882-b937-a080a4b09366\",\"group\":\"Точки выхода\"}";

        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":\"" + addConfigurationNameErrorHandler + "\",\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"rate\":0,\"entityXMLTag\":\"route\",\"oldId\":\"a35b9f4a-a09d-4efe-8132-a7c143dc0781\",\"domain\":{\"guid\":\"domain-77009abe-7d8d-4b74-8a8d-073c7e4d8f42\"}},\"comment\":\"\"}";
        String allPointsIn[] = {pointIn1};
        String allPointsOut[] = {pointOut1};

        String[] schemesAllErrorHandlers = {"Обработчик ошибок|" + addConfigurationNameErrorHandler, "Обработчик по умолчанию|" + addConfigurationNameResendPolicy};
        String[] settingOfAddConfigAll = {"Политика повторной отправки|" + addConfigurationNameResendPolicy, "INFO|1|1000|1|true|1|true|true|0.15|true"};
        String[] settingOfAddConfigAllNew = {"Политика повторной отправки|" + addConfigurationNameResendPolicy, "INFO|1|${constantDelay}|1|true|1|true|true|0.15|true"};
        Object[] constant1 = {"constantDelay", "7000", true};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.createConstantsOfDomainAPI(Cook1, domainID_1, settingAllConstants);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingOfAddConfigAll);
        sopsPage.createAdditionalConfiguration(domainName1, schemesAllErrorHandlers);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(Cook1, domainID_1, sopsName1);
        sopsApi.createSopsAPI(Cook1, baseUrl(), domainID_1, SopsIDForStopTest, sopsName1, allPointsIn, allPointsOut, settingDomain);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        refresh();
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, "DEAD.LETTER.QUEUE", 1, 0, 0, 1, 0, null, null, null, null);

        sopsPage.editAdditionalConfiguration(domainName1, addConfigurationNameResendPolicy, settingOfAddConfigAllNew);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "", 1);
        sleep(3000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, "DEAD.LETTER.QUEUE", 1, 0, 0, 1, 0, null, null, null, null);
        sleep(4000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, "DEAD.LETTER.QUEUE", 2, 0, 0, 2, 0, null, null, null, null);
    }

    @Test
    public void checkLocalQueueAndRoutsTable() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueOut1 = "outRoute1";
        String localQueueOut2 = "outRoute2";
        String localQueueOut3 = "outRoute3";

        String message = "<s:Envelope xmlns:s=\\\"http://www.w3.org/2003/05/soap-envelope\\\"" +
                "  xmlns:wsa=\\\"http://schemas.xmlsoap.org/ws/2004/08/addressing\\\">" +
                "  <s:Header>" +
                "    <wsa:MessageID>uuid:aaaabbbb-cccc-dddd-eeee-ffffffffffff</wsa:MessageID>" +
                "    <wsa:To>%s</wsa:To>" +
                "  </s:Header>" +
                "  <s:Body>" +
                "       Example Test Message" +
                "  </s:Body>" +
                "</s:Envelope>";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Выход в таблицу маршрутов", "параметры таблицы маршрутов|Новая таблица маршрутов|Маршрут по умолчанию-Ручной ввод-'localmq:" + localQueueOut1 + "'-|2-Ручной ввод-'localmq:outRoute$FESB_SRC_ADDR'|3-Точка выхода-Локальная очередь-" + localQueueOut3, "селект|Маршрутизировать по следующему типу|xpath", "инпут|Маршрутизировать по следующему значению|//S:Header/wsa:To", "селект|Тип таблицы маршрутов|Отправить по маршрутам", "селект|Таблица маршрутов|Новая таблица маршрутов"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] schemesAll_1 = {"Пространство имен XML| ", "S|http://www.w3.org/2003/05/soap-envelope"};
        String[] schemesAll_2 = {"Пространство имен XML| ", "wsa|http://schemas.xmlsoap.org/ws/2004/08/addressing"};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, schemesAll_1);
        sopsPage.createAdditionalConfiguration(domainName1, schemesAll_2);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, String.format(message, "1"), 1);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, String.format(message, "2"), 1);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, String.format(message, "3"), 1);
        sleep(1000);

        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueOut1, 1, 0, 0, 1, 0, null, null, null, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueOut2, 1, 0, 0, 1, 0, null, null, null, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueOut3, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueOut1, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueOut2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueOut3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkLocalQueueAndPrinter() {
        String url = "http://192.168.57.240:1111/";
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String sopsName2 = "CОПС для проверки " + testName.getMethodName() + "-2";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Принтер|TestPrinter"};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|Локальный файл|/var/spool/cups-pdf/root"};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};

        String[][] pointAll_2 = {point2_1, point2_2};

        basePage.openPage(url);
        String Cook1 = api.loginAPI(url, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(url, Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, url, domainID_1);
        sopsPage = new SOPSPage();
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        creationSOPSPage.createSOPS(domainName1, sopsName2, pointAll_2);

        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, url, managerName, localQueueForSopsName1, "1111", 1, true, "4");
        sleep(10000);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, url, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 1, 0, 0, 1, 0, "local", "null", url);
    }

    @Test
    public void checkaddConfigAggregationStrategy() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String localQueueForSopsName2 = "AGG.TEST.OUT";

        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/StrategyAggregation.zip", domainName1);

        String[] setAddConfig = {"Стратегия агрегации|aggregate|Объединение тел всех сообщений в список"};
        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));

        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, setAddConfig);
        open(baseUrl());
        sopsPage.startDomain(domainName1);
        sopsPage.sendMessage("parallelRun", 1, "");

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2, "1,2,3,4,5");
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueForSopsName2, 5, 0, 0, 5, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueForSopsName2, 5, 0, 0, 5, 0, "local", "null", baseUrl());
    }

    @Test
    public void checkaddConfigDataFormat() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueForSopsName1 = testName.getMethodName() + "-1";
        String localQueueForSopsName2 = testName.getMethodName() + "-2";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueForSopsName1};
        String[] point1_2 = new String[]{"Выход|Преобразование форматов|Пользовательский формат → OBJECT|jsonJackson"};
        String[] point1_3 = new String[]{"Выход|Преобразование форматов|OBJECT → Пользовательский формат|jsonJackson"};
        String[] point1_4 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueForSopsName2};
        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4};

        String[] setAddConfig = {"Формат данных|jsonJackson|JSON", "library|Jackson-prettyPrint|true"};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, setAddConfig);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueForSopsName1, "{\\\"id\\\":\\\"example\\\", \\\"value\\\": \\\"example\\\"}", 1);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueForSopsName2,
                "{\n" +
                        "  \"id\" : \"example\",\n" +
                        "  \"value\" : \"example\"\n" +
                        "}");
    }

    @Test
    public void deleteExistedDomainDuringImport() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String domain2 = "deleteExistedDomainDuringImport - 2";
        Object[] constant1 = {testName.getMethodName(), "test", true};
        Object[][] settingAllConstants = {constant1};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.createConstantsOfDomainAPI(Cook1, baseUrl(), domainID_1, settingAllConstants);
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(domainName1);
        sopsPage.checkConstantsOfDomain(0, 1, testName.getMethodName(), "****");

        sopsPage.importDomain(SOPSPage.typeOfDomain.EXISTED_AND_DELETE_OLD_DOMAIN, "/share/DomainForImport/deleteExistedDomainDuringImport1.zip", domainName1);
        sopsPage.goToDomain(domainName1);
        sopsPage.checkConstantsOfDomain(0, 0, "", "");
    }

    @Test
    public void parameterEnableSimpleLanguageSupport() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        sopsPage = new SOPSPage();
        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/parameterEnableSimpleLanguageSupport.zip", domainName1);
        sopsPage.startDomain(domainName1);

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, "Включить поддержку переменных - 1", "", 1);
//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, "Включить поддержку переменных - 2", "testval9");
    }

//    @Test
//    public void tracing() {
//        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
//        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        sopsPage = new SOPSPage();
//        sopsPage.importDomain(SOPSPage.typeOfDomain.NEW, "/share/DomainForImport/Tracing.zip", domainName1);
//        sopsPage.runDomain(domainName1);
//        queueManagerApi.sentMessgeInQueueAPI(Cook1, "PERF.IN", "", 1);
//        sleep(3000);
//
//        sopsPage = new SOPSPage();
////        sopsPage.goToDomain("Домен для проверки tracing-16833796");
//
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_Old_1", "1", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_Old_2", "1", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_Old_3", "1", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Локальная очередь_Old_2", "1", "0");
//
//        String[] points = {"0-Локальная очередь_New_1", "1-Ссылка на СОПС_New_1", "2-Ссылка на СОПС_New_2", "3-Ссылка на СОПС_New_3", "4-Локальная очередь_New_2"};
//        sopsPage.editPointOfSopsName(domainName1, "Perf&Tuning", points);
//
//        queueManagerApi.sentMessgeInQueueAPI(Cook1, "PERF.IN", "", 1);
//        sleep(3000);
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_New_1", "2", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_New_2", "2", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Ссылка на СОПС_New_3", "2", "0");
//        sopsPage.checkTracing("", "Perf&Tuning", "Локальная очередь_New_2", "2", "0");
//
//        basePage.click(sopsPage.expandTracingButton.get(0));
//        sopsPage.rowMessageInTrace.shouldHaveSize(2);
//        basePage.doubleClick(sopsPage.rowMessageInTrace.get(0));
//        for (int x = 0; x < 4; x++) {
//            basePage.compareElementTextShouldContainsText("_New_", $$x(".//div[contains(text(),\"Трассировка сообщения: ID\")]/../following-sibling::div//div[@class=\"ant-timeline-item-head ant-timeline-item-head-blue\"]/following-sibling::div[@class=\"ant-timeline-item-content\"]").get(x));
//        }
//    }

    @Test
    public void checkLocalQueueAndAggregation() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName1 = "CОПС для проверки " + testName.getMethodName() + "-1";
        String localQueueIn1 = "AGGREGATOR.IN";
        String localQueueOut1 = "AGG.OUT";
        String localQueueOut2 = "OUT.AGG";
        String message = "222";

        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + managerName + "|" + localQueueIn1};
        String[] point1_2 = new String[]{"Выход|Установить заголовки", "параметры-заголовков|id|Simple|По умолчанию|${body}"};
        String[] point1_3 = new String[]{"Выход|Установить переменные|Exchange.AGGREGATION_COMPLETE_ALL_GROUPS|Constant|По умолчанию|true", "чек-бокс|Выполнять при условии|вкл|simple|${body} == '6'"};
        String[] point1_4 = new String[]{"Выход|Агрегация|header|id|addAggStr", "чек-бокс|Установить выражение размера завершения|constant|2"};
        String[] point1_5 = new String[]{"Обработка-Агрегация-0|Локальная очередь|" + managerName + "|" + localQueueOut1};
        String[] point1_6 = new String[]{"Выход|Локальная очередь|" + managerName + "|" + localQueueOut2};

        String[][] pointAll_1 = {point1_1, point1_2, point1_3, point1_4, point1_5, point1_6};

        String[] settingConfig1 = {"Пользовательский|addAggStr", "ru.factorts.module.broker.strategy.aggregation.AppendAggregationStrategy"};

        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName1, settingConfig1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName1, sopsName1, pointAll_1);
        sleep(1000);
        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, localQueueIn1, message, 10);
//        queueManagerApi.sendMessgeInQueueAPI(Cook1, localQueueIn1, message, 10);

//        queueManagerPage = new QueueManagerPage();
        queueManagerMultyPage.queueShouldHaveSpecificMessage(managerName, localQueueOut1, message + "" + message);
//        queueManagerPage.queueShouldHaveSpecificMessage(localQueueOut1, message + "" + message);


        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueOut1, 5, 0, 0, 5, 0, null, null, null, null);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, localQueueOut2, 10, 0, 0, 10, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueOut1, 5, 0, 0, 5, 0, "local", "null", baseUrl());
//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, localQueueOut2, 10, 0, 0, 10, 0, "local", "null", baseUrl());
    }

    @Test
    public void copyPointOfSops() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String Cook1 = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(baseUrl(), Cook1, domainName1);
        sopsApi.startDomainAPI(Cook1, baseUrl(), domainID_1);
        String SopsID_1 = sopsApi.generateSopsIdAPI(Cook1, domainID_1, SopsForEnvironment1);
        String SopsID_2 = sopsApi.generateSopsIdAPI(Cook1, domainID_1, SopsForEnvironment2);
        sopsApi.createSopsWith2LocalQueueAPI(Cook1, baseUrl(), domainID_1, SopsID_1, domainName1, SopsForEnvironment1, LocalQueueForEnvironment1, LocalQueueForEnvironment2);
        sopsApi.createSopsWith2LocalQueueAPI(Cook1, baseUrl(), domainID_1, SopsID_2, domainName1, SopsForEnvironment2, LocalQueueForEnvironment3, LocalQueueForEnvironment4);

        sopsPage = new SOPSPage();
        sopsPage.goToSops(domainName1, SopsForEnvironment1);
        creationSOPSPage = new CreationSOPSPage();
        basePage.contextClick(creationSOPSPage.lastEndPointInSops);
        basePage.click(creationSOPSPage.copyInContextMenu);

        sopsPage.goToSops(domainName1, SopsForEnvironment2);
        basePage.contextClick(creationSOPSPage.lastEndPointInSops);
        basePage.click(creationSOPSPage.insertBeforeInContextMenu);
        creationSOPSPage.saveSOPS();

        queueManagerMultyApi.sendMessgeInQueueAPI(Cook1, managerName, LocalQueueForEnvironment3, "", 1);
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, LocalQueueForEnvironment4, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, LocalQueueForEnvironment4, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        queueManagerMultyApi.queueCheckAllParametersAPI(Cook1, baseUrl(), managerName, LocalQueueForEnvironment2, 1, 0, 0, 1, 0, null, null, null, null);


//        queueManagerApi.queueCheckAllParametersAPI(Cook1, managerName, LocalQueueForEnvironment2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }

    @Test
    public void createEditDeleteConstantOfDomain() {
        String constantOfBrokerName = "abc123_!@";
        String constantOfBrokerValue = "abc123_!@";
        String constantOfBrokerNameNew = "zxc)-=";
        String constantOfBrokerValueNew = "zxc)-=";
        sopsPage = new SOPSPage();
        sopsPage.createConstantOfBroker(constantOfBrokerName, constantOfBrokerValue);
        refresh();
        sopsPage.checkConstantsOfBroker(0, 1, constantOfBrokerName, constantOfBrokerValue);
        sopsPage.editConstantOfBroker(constantOfBrokerName, constantOfBrokerNameNew, constantOfBrokerValueNew);
        refresh();
        sopsPage.checkConstantsOfBroker(0, 1, constantOfBrokerNameNew, constantOfBrokerValueNew);
        sopsPage.deleteConstantOfBroker(constantOfBrokerNameNew);
        refresh();
        sopsPage.checkConstantsOfBroker(0, 0, null, null);
    }
}
