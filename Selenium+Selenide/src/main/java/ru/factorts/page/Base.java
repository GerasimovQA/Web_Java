package ru.factorts.page;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.HarEntry;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.WebDriverRunner.url;


public class Base {
    @Rule
    public TestName testName = new TestName();
    Logs logs;

    BrowserUpProxy bmp;
    LoginPage loginPage = new LoginPage();
    static BasePage basePage = new BasePage();

    static final String alternativeBaseUrl_1 = System.getProperty("alternativeBaseUrl_1");
    static final String alternativeBaseUrl_2 = System.getProperty("alternativeBaseUrl_2");
    static public String BUrl;
    static public String BUrl_2;
    String BaseUrl = BUrl;
    String BaseUrl_2 = BUrl_2;
    static public String browser = "chrome";
    static final String alternativeBrowser = System.getProperty("alternativeBrowser");
    String managerNameDefault = "QM";

    final String portForCheckSOPSWithHttpAndLocalQueue = "2001";
    final String portForCheckWebSocketAndWebSocket = "2002";
    final String portForCheckSopsSQLAndLocalQueue = "2003";
    final String portForCheckSopsLocalQueueAndSQL = "2004";
    final String portForCheckSopsMinaAndMina = "2005";
    final String portForCheckSopsLocalQueueAndLocalBd = "2006";
    final String portForCheckSopsLocalBdAndLocalQueue = "2007";
    final String checkSOPSWithHttpAndAddBodyMessageAndAutentificationHttpLdap = "2008";
    final String portForCreateReceiverArtemis = "2009";
    final String portForEditReceiverArtemis1 = "2010";
    final String portForEditReceiverArtemis2 = "2011";
    final String portForStopAndStartReceiverArtemis = "2012";
    final String portForCloneReceiverArtemis1 = "2014";
    final String portForCloneReceiverArtemis2 = "2013";
    final String portForDeleteReceiverArtemis = "2015";
    final String portForCreateConnectorArtemis = "2016";
    final String portForEditConnectorArtemis1 = "2017";
    final String portForEditConnectorArtemis2 = "2018";
    final String portForCloneConnectorArtemis1 = "2019";
    final String portForCloneConnectorArtemis2 = "2020";
    final String portForDeleteConnectorArtemis1 = "2021";
    final String portForCreateChannelArtemis1 = "2022";
    final String portForEditChannelArtemis1 = "2023";
    final String portForEditChannelArtemis2 = "2024";
    final String portForDeleteChannelArtemis = "2025";

    final String portForCheckSopsFtpServerAndFtp = "2091";

    final String portForMyExamplesDomain16createAndCheckMyHTTPSOAP = "3001";
    final String portForMyExamplesDomain17createAndCheckMyHTTP_JSON2XML = "3002";
    final String portForMyExamplesDomain18createAndCheckMyHTTP_XML2JSON = "3003";
    final String portForMyExamplesDomain19createAndCheckMyGETCurrencyHTTP = "3004";
    final String portForCheckSopsJmsAndJms1 = "3005";
    final String portForcheckSopsJmsAndJmsAndCachedMq = "3006";

    final String portForCheckSopsJmsAndJms2 = "3090";

    final String portForCreateConnector = "8523";
    final String portForCreateConnectorMulty = "8600";
    final String portForCancelCreateConnector = "8524";
    final String portForCancelCreateConnectorMulty = "8601";
    final String portForEditConnector1 = "8525";
    final String portForEditConnectorMulty1 = "8602";
    final String portForEditConnector2 = "8526";
    final String portForEditConnectorMulty2 = "8603";
    final String portForCancelEditConnector1 = "8527";
    final String portForCancelEditConnectorMulty1 = "8604";
    final String portForCancelEditConnector2 = "8528";
    final String getPortForCancelEditConnectorMulty2 = "8605";
    final String portForDeleteConnector = "8529";
    final String portForDeleteConnectorMulty = "8606";
    final String portForCancelDeleteConnector = "8530";
    final String portForCancelDeleteConnectorMulty = "8607";
    final String portForcommandInterfaceMulty1 = "8608";

    final String portForCreateServiceRest = "7000";
    final String portForEditService = "7001";
    final String portForCancelEditService = "7002";
    final String portForDeleteService = "7003";
    final String portForCancelDeleteService = "7004";
    final String portForCreateMethodRestGet = "7005";
    final String portForCreateMethodRestPost = "7006";
    final String portForCreateMethodRestPut = "7007";
    final String portForCreateMethodRestDelete = "7008";
    final String portForCancelCreateMetodth = "7009";
    final String portForEditMethod = "7010";
    final String portForCancelEditMethod = "7011";
    final String portForDeleteMethod = "7012";
    final String portForCancelDeleteMethod = "7013";
    final String portForCreateAutentificationHttpLocalUsers = "7014";
    final String portForCancelCreateAutentificationHttpLocalUsers = "7015";
    final String portForEditAutentificationHttpLocalUsers = "7016";
    final String portForCancelEditAutentificationHttpLocalUsers = "7017";
    final String portForDeleteAutentificationHttpLocalUsers = "7018";
    final String portForCancelDeleteAutentificationHttpLocalUsers = "7019";
    final String portForChangeSetting = "7020";
    final String portForChangeSettingNew = "7021";


    /**
     * Create and run driver and login
     */

    @BeforeClass
    public static void setUp() {
        basePage.getCPULoad(Integer.parseInt(ConfigProperties.getTestProperty("maxLoadCPU")));
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.driverManagerEnabled = true;
        Configuration.reopenBrowserOnFail = true;
        Configuration.timeout = 20000;
        baseUrlForSelenide();
        BUrl = baseUrl();

        if (browser().equals("chrome")) {
            System.out.println("Запускаем тесты в Хроме");
            Configuration.remote = ConfigProperties.getTestProperty("endpointChrome");
            Configuration.browser = "chrome";
            Configuration.browserCapabilities.setCapability("version", "93.0");
//            Configuration.fileDownload = FileDownloadMode.FOLDER;
//            Configuration.downloadsFolder="/home/selenium/Downloads";
        } else if (browser().equals("internet explorer")) {
            System.out.println("Запускаем тесты в Експлорере");
            Configuration.remote = ConfigProperties.getTestProperty("endpointIE");
            Configuration.browser = "internet explorer";
//            Configuration.browserCapabilities.setVersion("11");
            Configuration.browserCapabilities.setCapability("version", "11");
            Configuration.browserCapabilities.setCapability("acceptInsecureCerts", false);
        }
        Configuration.startMaximized = true;
        Configuration.browserCapabilities.setCapability("enableVNC", true);
        Configuration.browserCapabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities.setCapability("enableLog", false);
    }

    @Before
    public void before() {
        if (ConfigProperties.getTestProperty("Debug").equals("true")) {
            System.out.println("РЕЖИМ ОТЛАДКИ ВКЛЮЧЕН");
            Configuration.proxyEnabled = Boolean.parseBoolean(ConfigProperties.getTestProperty("Debug"));
            Configuration.proxyHost = ConfigProperties.getTestProperty("BaseUrl").split("//")[1].split(":")[0];
        }

        basePage.getCPULoad(Integer.parseInt(ConfigProperties.getTestProperty("maxLoadCPU")));
//        open("/");
//
//        if (ConfigProperties.getTestProperty("Debug").equals("true")) {
//            bmp = WebDriverRunner.getSelenideProxy().getProxy();
//            // запоминать тело запросов (по умолчанию тело не запоминается, ибо может быть большим)
//            bmp.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
//            // запоминать как запросы, так и ответы
//            bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
//            // начинай запись!
//            bmp.newHar("pofig");
//        }
    }

    @After
    public void tearDownClass() throws IOException {
        try {
            basePage.checkFallenInterface();
        } catch (IllegalStateException | InvalidSelectorException | ElementNotFound e) {
            System.out.println("Браузер не запущен");
            System.out.println(e);
        }

        if (ConfigProperties.getTestProperty("Debug").equals("true")) {
            List<HarEntry> requests = new ArrayList<>(bmp.getHar().getLog().getEntries());
            for (HarEntry entry : requests) {
                try {
                    if (entry.getRequest().getUrl() != null && entry.getRequest().getUrl().contains("api")) {
                        System.out.println("URL: " + entry.getRequest().getUrl());
                        System.out.println("\tTimestamp:" + entry.getStartedDateTime());
                        System.out.println("\tRequest body:\n " + entry.getRequest().getPostData().getText() + "\n");
                        System.out.println("\tResponse body:\n" + entry.getResponse().getContent().getText() + "\n");
                        System.out.println("\n\n");
                    }
                } catch (NullPointerException e) {
                    System.out.println("ПРОБЛЕМА С ЗАПИСЬЮ ЛОГОВ: ");
                    e.printStackTrace();
                }
            }
        }
        try {
            screenForAllure();
        } catch (IllegalStateException | InvalidSelectorException | NullPointerException e) {
            System.out.println("Браузер не запущен");
        }
        Selenide.closeWebDriver();
    }

    public File screen() throws IOException {
        return Screenshots.takeScreenShotAsFile();
    }

    @Attachment(value = "Page screenForAllure", type = "image/png")
    public byte[] screenForAllure() throws IOException {
        File file = screen();
        return Files.toByteArray(file);
    }

    public static void baseUrlForSelenide() {
        if (alternativeBaseUrl_1 != null) {
            Configuration.baseUrl = alternativeBaseUrl_1;
        } else {
            Configuration.baseUrl = ConfigProperties.getTestProperty("BaseUrl");
        }
    }

    public static String baseUrl() {
        if (alternativeBaseUrl_1 != null) {
            BUrl = alternativeBaseUrl_1;
        } else {
            BUrl = ConfigProperties.getTestProperty("BaseUrl");
        }
        return BUrl;
    }

    public static String baseUrl_2() {
        if (alternativeBaseUrl_2 != null) {
            BUrl_2 = alternativeBaseUrl_2;
        } else {
            BUrl_2 = ConfigProperties.getTestProperty("BaseUrl_2");
        }
        return BUrl_2;
    }

    public static String browser() {
//        System.out.println("Основной браузер: " + ConfigProperties.getTestProperty("Browser"));
//        System.out.println("Альтернативный браузер: " + alternativeBrowser);
        if (alternativeBrowser != null && !alternativeBrowser.equals("")) {
            browser = alternativeBrowser;
        }
//        System.out.println("Итоговый браузер: " + browser);
        return browser;
    }


    public ArrayList<String> getControlSumAllFileInDirectory(File folder, ArrayList<String> arrayList) {
        String controlSum = null;
        File[] folderEntries = folder.listFiles();
        assert folderEntries != null;
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
//                getControlSumFile(entry);
                continue;
            }
            System.out.println(entry);
            byte[] b = new byte[0];
            try {
                b = java.nio.file.Files.readAllBytes(Paths.get(String.valueOf(entry)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] hash = new byte[0];
            try {
                hash = MessageDigest.getInstance("MD5").digest(b);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            controlSum = DatatypeConverter.printHexBinary(hash);
            basePage.sout(controlSum);
            arrayList.add(controlSum);
        }
        return arrayList;
    }

    static public void recordFesbLogs(String className) {
        String DIR1 = ("/home/fesb/Stand/FesbLogs/" + className + "-" + new Date().toString()).replace(" ", "_");
        System.out.println(DIR1);
        String user1 = "fesb";
        String password = "fesb2016";
        String shortUrl1 = "192.168.57.240";

        if (className.equals("ru.factorts.page.N8_RestSingleTest") || className.equals("ru.factorts.page.N6_SystemSingleTest")) {
            new File(DIR1).mkdir();
            String bashCommandRebootMachine = "echo '1qaz!QAZ' | sudo cp -R /home/fesb/Stand/app/conf " + DIR1;
            String bashCommandRebootMachine2 = "echo '1qaz!QAZ' | sudo cp -R /home/fesb/Stand/app/data/logs " + DIR1;
            basePage.executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandRebootMachine);
            basePage.executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandRebootMachine2);
        }
    }

    public void writeLogsOfBrowser(String className, String testName) {
        String DIR = ("/home/fesb/Stand/BrowserLogs/" + className + "/");
        String file = (testName.split("\\|")[0].replace(" ", "_") + "-" + new Date().toString().replace(" ", "_") + ".testlog");

        System.out.println(DIR);
        System.out.println(file);
        File fileOfLog = new File(DIR + file);

        try {
            FileWriter writer = new FileWriter(fileOfLog);

            for (String line : logs.get(LogType.BROWSER).getAll().toString().split(",")) {
                writer.write(line);
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

//        for (String line : logs.get(LogType.BROWSER).getAll().toString().split(",")) {
//            if (line.contains("WARN") || line.contains("ERR") || line.contains("FAT")) {
//                System.out.println("Проблемная строка в логах" + line);
//                throw new AssertionError("В логе браузера присутствует WARN");
//            }
//        }
        System.out.println("Логи успешно записаны");
    }

    public void writeStringInFile(String filePath, String text) {
        String DIR = (filePath);
        System.out.println(DIR);
        File fileOfLog = new File(DIR);

        try {
            FileWriter writer = new FileWriter(fileOfLog);
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getClassName() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}

