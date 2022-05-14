package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.factorts.page.ConfigurationPage.ReloadType.Broker;
import static ru.factorts.page.ConfigurationPage.ReloadType.Full;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CbrTest extends Base {

    @Rule

    public TestName testName = new TestName();
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    SOPSPage sopsPage;
    static  SOPSPage staticSopsPage;
//    QueueManagerPage queueManagerPage;
    CbrPage cbrPage = new CbrPage();
    IbmMqPage ibmMqPage = new IbmMqPage();
    ConfigurationPage configuration = new ConfigurationPage();
    static ConfigurationPage staticConfiguration = new ConfigurationPage();
    CreationSOPSPage creationSOPSPage;
    SettingsPage settingsPage;
    N6_SystemSingleTest n6_systemSingleTest;
    static SOPSApi staticSopsApi = new SOPSApi();
    public IbmMqApi ibmMqApi = new IbmMqApi();
    GeneralInformationPage generalInformationPage = new GeneralInformationPage();

    static IbmMqApi staticIbmMqApi = new IbmMqApi();
    static Api api = new Api();

    private static final String DOMAIN_NAME = "SA5CTUPF",
    //            URL_IBM = "https://192.168.57.201:9443/ibmmq/console/login.html";
    URL_IBM = ConfigProperties.getTestProperty("IbmUrl") + "/ibmmq/console/login.html";

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @BeforeClass
    static public void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
        staticBasePage.openPage(baseUrl());
        staticConfiguration.importConfiguration("/share/configCBR.zip", Full);

         String CookWMQ = staticIbmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.KCOI.SNTSBP", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "SBP_Access", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "SBP_Access2", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "SBP_AccessInfoRequest", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "SBP_AccessInfoRequest2", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "SBP_AccessInfo", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "TO.FP", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.TRASH", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.ERROR.SNT", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.KCOI.SNTSBP_OUT", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.ERROR.SNT", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "STREAM.KCOI.SNT_TAB", "QM1");
        staticIbmMqApi.createQueueToWmqAPI(CookWMQ, "TO.KBR.SNT", "QM1");

        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.KCOI.SNTSBP", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SBP_Access", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SBP_Access2", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SBP_AccessInfoRequest", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SBP_AccessInfoRequest2", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "SBP_AccessInfo", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "TO.FP", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.TRASH", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.ERROR.SNT", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.KCOI.SNTSBP_OUT", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.ERROR.SNT", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "STREAM.KCOI.SNT_TAB", "500000", "QM1");
        staticIbmMqApi.setMaxQueueDepthWMQ(CookWMQ, "TO.KBR.SNT", "500000", "QM1");

        Selenide.closeWindow();
    }

    @After
    public void after() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticSopsApi.stopDomainAPI(Cook, "bae5d1cd-7ba5-416f-817c-0d7f2aebaf1d");

        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-cbr");
        }

        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    public void aUpdate() {
        String verShouldBe = api.getVerFromGitlabAPI("Версия ЦБ ТШКБР:");
        Update update = new Update();
        update.update(verShouldBe);
    }


    @Test
    @Parameters(value = {
            "Игнорировать повторные маршруты",
            "НЕ игнорировать повторные маршруты",
    })
    public void sendSavedMessage(String Type) {
        basePage.sout("Альтернатичный URL: " + System.getProperty("alternativeBaseUrl"));
        int StreamErrorSntDepthQueueBefore = 0;
        int ToKbrSntQueueBefore = 0;
        int StreamErrorSntDepthQueueAfter = 0;
        int ToKbrSntQueueAfter = 0;
        int differenceStreamErrorSnt;
        int differenceToKbrSnt = 1;

        if (Type.equals("НЕ игнорировать повторные маршруты")) {
            differenceStreamErrorSnt = 387;
        } else {
            differenceStreamErrorSnt = 1;
        }


        String CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        StreamErrorSntDepthQueueBefore = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "STREAM.ERROR.SNT");
        System.out.println(StreamErrorSntDepthQueueBefore);
        ToKbrSntQueueBefore = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "TO.KBR.SNT");
        System.out.println(ToKbrSntQueueBefore);

        basePage.openPage(baseUrl());
        cbrPage.ignoreRepeatedRoutes(Type, "SA5CTUPF", "SNT_TAB");
        cbrPage.sendTheSavedMessageToIbmAndCheckQueue();

        CookWMQ1 = ibmMqApi.loginToWmqAPI(ConfigProperties.getTestProperty("LoginWMQ"), ConfigProperties.getTestProperty("PasswordWMQ"));
        StreamErrorSntDepthQueueAfter = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "STREAM.ERROR.SNT");
        System.out.println(StreamErrorSntDepthQueueAfter);
        ToKbrSntQueueAfter = ibmMqApi.getQueueDepthWMQ(CookWMQ1, "TO.KBR.SNT");
        System.out.println(ToKbrSntQueueAfter);

        ibmMqPage.compareQueuesBeforeAndAfter(StreamErrorSntDepthQueueBefore, StreamErrorSntDepthQueueAfter, differenceStreamErrorSnt);
        ibmMqPage.compareQueuesBeforeAndAfter(ToKbrSntQueueBefore, ToKbrSntQueueAfter, differenceToKbrSnt);
    }

    @Test
    public void send_100000_Messages() {
        int numMessage = 200000;
        basePage.openPage(baseUrl());
        String versionFesb = generalInformationPage.productVersion.getText();
        sopsPage = new SOPSPage();
        sopsPage.stopDomain("SA5CTUPF");
        staticConfiguration.importConfiguration("/share/configCBR-speed.zip", Broker);

        cbrPage.send_100000_MessageToIbmAndCheckQueue("TO.WMQ.SNTSBP", numMessage);
        sopsPage = new SOPSPage();
        sopsPage.startDomain("IN");
        sopsPage.searchSops("SNT_SBP_BATCH");
        basePage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(matchText(Integer.toString(numMessage)), 90000);
        sleep(1000);

        Locale rus = new Locale("ru", "RU");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String FirstProcessing = sopsPage.firstProcessingSOPS.getText();
        String LastProcessing = sopsPage.lastProcessingSOPS.getText();

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = formatter.parse(FirstProcessing);
            date2 = formatter.parse(LastProcessing);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        basePage.sout("Дата 1: " + date1);
        basePage.sout("Дата 2: " + date2);

        long difference1 = (date2.getTime() - date1.getTime()) / 1000;
        long optimalTime1 = 80;
        long optimalSpeed1 = 3000;
        float AverageSpeed1 = Float.parseFloat(sopsPage.averageSpeedSOPS.getText());

        sopsPage.checkSpeedAndWriteToFile("SNT_SBP_BATCH", versionFesb, optimalTime1, optimalSpeed1, "speedStatistic_Cbr.txt");
        sopsPage.sopsCheckAllParameters("SNT_SBP_BATCH", "IBM MQ: queue/STREAM.KCOI.SNTSBP", numMessage);

        cbrPage.send_100000_MessageToIbmAndCheckQueue("TO.WMQ.SNTSBPOUT", numMessage);
        sopsPage = new SOPSPage();
        sopsPage.startDomain("OUT");
        sopsPage.searchSops("SNT_SBP_OUT");
        basePage.autoUpdateOn();
        sopsPage.cellProcessedSOPS.waitUntil(matchText(Integer.toString(numMessage)), 1200000);
        sleep(1000);
        FirstProcessing = sopsPage.firstProcessingSOPS.getText();
        LastProcessing = sopsPage.lastProcessingSOPS.getText();

        date1 = null;
        date2 = null;
        try {
            date1 = formatter.parse(FirstProcessing);
            date2 = formatter.parse(LastProcessing);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        basePage.sout("Дата 1: " + date1);
        basePage.sout("Дата 2: " + date2);

        long difference2 = (date2.getTime() - date1.getTime()) / 1000;
        long optimalTime2 = 30;
        long optimalSpeed2 = 10000;
        float AverageSpeed2 = Float.parseFloat(sopsPage.averageSpeedSOPS.getText());

        sopsPage.checkSpeedAndWriteToFile("SNT_SBP_OUT", versionFesb, optimalTime2, optimalSpeed2, "speedStatistic_Cbr.txt");
        sopsPage.sopsCheckAllParameters("SNT_SBP_OUT", "IBM MQ: queue/STREAM.KCOI.SNTSBP_OUT", numMessage);

        Assert.assertTrue("Время обработки сообщений СОПСом SNT_SBP_BATCH превышено", difference1 <= optimalTime1);
        Assert.assertTrue("Скорость обработки сообщений СОПСом SNT_SBP_BATCH упала", AverageSpeed1 >= optimalSpeed1);

        Assert.assertTrue("Время обработки сообщений СОПСом SNT_SBP_OUT превышено", difference2 <= optimalTime2);
        Assert.assertTrue("Скорость обработки сообщений СОПСом SNT_SBP_OUT упала", AverageSpeed2 >= optimalSpeed2);
    }
}
