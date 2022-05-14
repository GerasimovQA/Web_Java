//package ru.factorts.page;
//
//import org.junit.*;
//import org.junit.runners.MethodSorters;
//import com.codeborne.selenide.SelenideElement;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import ru.yandex.qatools.allure.annotations.Description;
//import ru.yandex.qatools.allure.annotations.Step;
//
//import java.io.File;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//
//import static com.codeborne.selenide.Selenide.open;
//import static com.codeborne.selenide.Selenide.sleep;
//import static ru.factorts.page.CommonComponents.createIndividualName;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class PerformanceTest extends Base {
//
//    private SOPSPage sopsPage;
//
//    private QueueManagerPage queueManagerTab;
//
//    private MessagePage sendMessageTab;
//
//    private SpecifecTopicPage specifecTopicPage;
//
//    private SpecificQueuePage specificQueuePage;
//
//    private SpecificMessagePage specificMessagePage;
//
//    private CreationSOPSPage creationSOPSPage;
//
//    private SettingsPage settingsPageTab;
//
//    private static final String DOMAIN_NAME = "TEST_DOMAIN",
//            SOPS_NAME = "TEST_SOPS",
//            LOCAL_QUEUE_IN = "TEST_QUEUE_IN",
//            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT",
//            URL="jdbc:h2:tcp://192.168.57.232:9099/default"        ;
//
//
//    private static String current_version;
//
//
//
//    @Before
//    public void setUpQueue()  {
//        basePage.getCPULoad(Double.parseDouble(ConfigProperties.getTestProperty("maxLoadCPU")));
//        open("/");
////        sleep(700);
//
//    }
//@After
//public void afterTest() {
//        api.getStatusAllModulesAPI();
//        }
//
//    /**
//     * Display Running time
//     */
//    @Step("1 000 000 messages received in the queue in {0}")
//    public void displayTime(String time) {
//        basePage.sout("RunTime is " + time);
//    }
//
//    /**
//     * Display quantity
//     */
//    @Step("Queue: {0} have {1} messages")
//    public void displayQuantity(String queueName, String messagesQuantity) {
//        basePage.sout(queueName + " have " + messagesQuantity);
//    }
//
//
//
//    /**
//     * Create connection to db and execute query
//     */
//    @Step("Create connection to db and execute query {3}")
//    public ResultSet executeQuery(String url, String user, String pass, String query) {
//        ResultSet resultSet=null;
//        try {
//            Class.forName("org.h2.Driver");
//            Connection conn = DriverManager.getConnection(url,user,pass);
//            Statement statement = conn.createStatement();
//            resultSet =  statement.executeQuery(query);
//
//        }
//        catch (Exception e)
//            {
//                System.err.println("D'oh! Got an exception!");
//                System.err.println(e.getMessage());
//            }
//        return resultSet;
//    }
//
//    /**
//     * Create connection to db and execute update
//     */
//    @Step("Create connection to db and execute update {3}")
//    public int execute(String url, String user, String pass, String query) {
//        int result=-1;
//        try {
//            Class.forName("org.h2.Driver");
//            Connection conn = DriverManager.getConnection(url,user,pass);
//            Statement statement = conn.createStatement();
//            result =  statement.executeUpdate(query);
//
//        }
//        catch (Exception e)
//        {
//            System.err.println("D'oh! Got an exception!");
//            System.err.println(e.getMessage());
//        }
//        return result;
//    }
//
//
//
//
//    /**
//     * Send 1 000 000 by SOPS and measure time
//     */
//
//    @Description("Send 1 000 000 by SOPS and measure time")
//    @Test
//    public void check1AReceivingPersistent1M() throws SQLException {
//        String domainName = "SENDMESSAGES";
//        String localQueueOutName = "OUT_SM";
//        IndexPage generalInformationTab = new IndexPage();
//        current_version = generalInformationTab.getVersionFESB();
//        generalInformationTab.sopsPage();
//        sopsPage = new SOPSPage();
//        sopsPage.importDomain(new File(System.getProperty("user.dir") +
//                File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator +
//                "resources" + File.separator + "sops_send_messages.xml"));
//        sopsPage.runDomain(domainName);
//        long startTime = System.currentTimeMillis();
//        sopsPage.queueListPage();
//        queueManagerTab = new QueueManagerPage();
//        queueManagerTab.turnUpdateSetMin();
//        CommonComponents.sleepTime(300000);
//        displayQuantity(localQueueOutName, queueManagerTab.returnQueueDepth(localQueueOutName));
//        queueManagerTab.queueShouldHaveNMessagesInDepth(localQueueOutName, 1000000, 500000);
//        Long runTime = System.currentTimeMillis() - startTime;
//        StringBuilder stringBuilder = new StringBuilder(runTime.toString());
//        for (String s: new ArrayList<String>(Arrays.asList(" ms or ", ((Long)(runTime/(1000*60))).toString(), "min ", ((Long)((runTime/1000)%60)).toString(), "s" ))
//             ) {
//            stringBuilder.append(s);
//        }
//        displayTime(stringBuilder.toString());
//        while (true) {
//            ResultSet resultSet = executeQuery(URL, "", "", String.format("select VERSION from performance where VERSION='%s'", current_version));
//            if (resultSet.last()) current_version += "N";
//            else break;
//        }
//        int result = execute(URL, "", "", String.format("Insert into Performance (VERSION, TIME_TO_RECIEVE_1M_C) VALUES ('%s', '%s')", current_version, stringBuilder.toString()));
//        assert result==1  : String.format("Данные:%s, %s - не записаны в базу, либо уже существуют)", current_version, stringBuilder);
//        queueManagerTab.curtail();
//        queueManagerTab.sopsPage();
//        sopsPage.deleteDomain(domainName);
//    }
//
//    /**
//     * Put 1 000 000 from one queue to another
//     */
//
//    @Description("Put 1 000 000 from one queue to another")
//    @Test
//    public void check2BPutPersistent1M() {
//        String domainName = "PUTMESSAGES";
//        String localQueueOutName = "OUT_PUT";
//        new IndexPage().sopsPage();
//        sopsPage = new SOPSPage();
//        sopsPage.importDomain(new File(System.getProperty("user.dir") +
//                File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator +
//                "resources" + File.separator + "PUTMESSAGES.xml"));
//        sopsPage.runDomain(domainName);
//        long startTime = System.currentTimeMillis();
//        sopsPage.queueListPage();
//        queueManagerTab = new QueueManagerPage();
//        queueManagerTab.turnUpdateSetMin();
//        CommonComponents.sleepTime(500000);
//        displayQuantity(localQueueOutName, queueManagerTab.returnQueueDepth(localQueueOutName));
//        queueManagerTab.queueShouldHaveNMessagesInDepth(localQueueOutName, 1000000, 600000);
//        Long runTime = System.currentTimeMillis() - startTime;
//        StringBuilder stringBuilder = new StringBuilder(runTime.toString());
//        for (String s: new ArrayList<String>(Arrays.asList(" ms or ", ((Long)(runTime/(1000*60))).toString(), "min ", ((Long)((runTime/1000)%60)).toString(), "s" ))
//                ) {
//            stringBuilder.append(s);
//        }
//        displayTime(stringBuilder.toString());
//        int result = execute(URL, "", "", String.format("UPDATE Performance SET TIME_TO_PUT_1M_C = '%s' WHERE  VERSION = '%s'", stringBuilder.toString(), current_version ));
//        assert result==1  : String.format("Данные:%s, %s - не записаны в базу, либо уже существуют)", current_version, stringBuilder.toString());
//        queueManagerTab.curtail();
//        queueManagerTab.sopsPage();
//        sopsPage.deleteDomain(domainName);
//    }
//
//
//}
