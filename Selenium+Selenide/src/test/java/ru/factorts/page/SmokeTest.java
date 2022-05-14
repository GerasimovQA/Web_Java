package ru.factorts.page;

import interfaces.smoke;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import utils.ConfigProperties;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class SmokeTest extends Base {

    static String cookies = null;
    @Rule
    public final TestName testName = new TestName();

    static Api api = new Api();
    SmokeApi smokeApi = new SmokeApi();
    SOPSApi sopsApi = new SOPSApi();
    CommonComponents commonComponents = new CommonComponents();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    IndexPage indexPage = new IndexPage("Empty");
    BasePage basePage = new BasePage();

    @BeforeClass
    public static void beforeClass() {
        cookies = api.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot69"), ConfigProperties.getTestProperty("PasswordRoot69"));
    }

    @Test
    @Category(smoke.class)
    public void actevateModules() {
        smokeApi.startAllModules(baseUrl());
        smokeApi.checkStatusAllModulesAPI();

        api.switchModuleAPI(cookies, baseUrl(), "factor-qme", "deactivate");
//        api.switchModuleAPI(cookies, baseUrl(), "factor-qms", "deactivate");
        api.switchModuleAPI(cookies, baseUrl(), "factor-dashboard", "deactivate");
        api.switchModuleAPI(cookies, baseUrl(), "factor-store", "deactivate");
        api.switchModuleAPI(cookies, baseUrl(), "factor-transaction-monitor", "deactivate");
        api.switchModuleAPI(cookies, baseUrl(), "factor-webservices", "deactivate");
    }

    @Test
    @Category(smoke.class)
    public void checkWorkSopsWithLocAndLoc() {
        String domainName = commonComponents.createIndividualName(testName.getMethodName());
        String sopsName = testName.getMethodName();
        String localQueue1 = testName.getMethodName() + "-1";
        String localQueue2 = testName.getMethodName() + "-2";
        String managerName1 = testName.getMethodName();

        queueManagerMultyApi.createManagerAPI(cookies, baseUrl(), managerName1);
        queueManagerMultyApi.startManagerAPI(cookies, baseUrl(), managerName1);
        String domainID = sopsApi.createDomainAPI(baseUrl(), cookies, domainName);
        sopsApi.startDomainAPI(cookies, baseUrl(), domainID);
        sopsApi.generateSopsIdAPI(cookies, domainID, sopsName);
        sopsApi.createSopsWith2LocalQueueMultyAPI(cookies, baseUrl(), domainID, sopsName, sopsName, managerName1, localQueue1, managerName1, localQueue2);
        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName1, localQueue1, "text", 1, true, "1");
        queueManagerMultyApi.queueCheckAllParametersAPI(cookies, baseUrl(), managerName1, localQueue2, 1, 0, 0, 1, 0, null, null, null, null);
        sopsApi.deleteDomainAPI(cookies, baseUrl(), domainID);
        queueManagerMultyApi.deleteQueueAPI(cookies, baseUrl(), managerName1, localQueue1);
        queueManagerMultyApi.deleteQueueAPI(cookies, baseUrl(), managerName1, localQueue2);
        queueManagerMultyApi.deleteManagerAPI(cookies, baseUrl(), managerName1, true);
    }

    @Test
    @Category(smoke.class)
    public void checkVersion() {
        System.out.println(System.getProperty("lastVer"));
        String lastVer = System.getProperty("lastVer");
        basePage.openPage(baseUrl(), "", ConfigProperties.getTestProperty("LoginRoot69"), ConfigProperties.getTestProperty("PasswordRoot69"));
        indexPage.checkVersionFESB(lastVer);
    }
}
