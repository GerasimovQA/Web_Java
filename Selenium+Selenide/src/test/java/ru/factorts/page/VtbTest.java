package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

@RunWith(JUnitParamsRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VtbTest extends Base {

    @Rule

    public TestName testName = new TestName();
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    SOPSPage sopsPage;
    static SOPSPage staticSopsPage;
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
    }

    @After
    public void after() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
//        String Cook = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
//        staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
    }

    @Test
    public void aUpdate() {
        String verShouldBe = api.getVerFromGitlabAPI("Версия ВТБ НПФ:");
        Update update = new Update();
        update.update(verShouldBe);
    }
}
