//package ru.factorts.page;
//
//import org.junit.Test;
//
//
//public class N100_SettingsTest extends Base {
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
//            LOCAL_QUEUE_OUT = "TEST_QUEUE_OUT";



//    @Before
//    public void setUpQueue()  {
//         basePage.getCPULoad(Integer.parseInt(ConfigProperties.getTestProperty("maxLoadCPU")));
//        open("/");
//        logs = WebDriverRunner.getWebDriver().manage().logs();
//        new IndexPage().settingsPageTab();
//    }
//
//
//import org.junit.After;
//
//@After
//public void afterTest() {
//        api.getStatusAllModulesAPI();
//System.out.println("Конец теста: " + testName.getMethodName());
// writeLogsOfBrowser(getClassName(), testName.getMethodName(),logs);
//        }



//}
