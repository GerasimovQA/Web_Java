package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import utils.ConfigProperties;

import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class NoUi extends Base {

    @Rule
    public TestName testName = new TestName();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    SOPSApi sopsApi = new SOPSApi();
    CommonComponents commonComponents = new CommonComponents();
    Api api = new Api();

    String cookies = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
    String managerName = "QM";

    @BeforeClass
    static public void beforeClass() {
        Configuration.browserCapabilities.setCapability("name", getClassName());
    }

    @Test
    public void fesbNoUi() {
        String domainName1 = commonComponents.createIndividualName("Домен для проверки " + testName.getMethodName() + "-1");
        String sopsName = "СОПС для проверки " + testName.getMethodName();
        String localQueueName1 = "Лок.Оч для проверки " + testName.getMethodName() + "-1";
        String localQueueName2 = "Лок.Оч для проверки " + testName.getMethodName() + "-2";

        open(baseUrl() + "/manager/login/");
        basePage.compareStringAndElementText("Данный экземпляр FESB не имеет веб-интерфейса", $x(".//h1"));
        basePage.compareStringAndElementText("Используйте удаленное управление на экземпляре с веб-интерфейсом, чтобы управлять данным экземпляром", $x(".//h2"));

        String domainID = sopsApi.createDomainAPI(baseUrl(), cookies, domainName1);
        sopsApi.startDomainWithoutCheckResponseAPI(cookies, baseUrl(), domainID);
        String SopsID = sopsApi.generateSopsIdAPI(cookies, baseUrl(), domainID, sopsName);
        sopsApi.createSopsWith2LocalQueueAPI(cookies, baseUrl(), domainID, SopsID, domainName1, sopsName, localQueueName1, localQueueName2);

        queueManagerMultyApi.sendMessgeInQueueAPI(cookies, baseUrl(), managerName,localQueueName1, "pwd", 1, false, "4");
        sleep(1000);
        queueManagerMultyApi.queueCheckAllParametersAPI(cookies, baseUrl(), managerName, localQueueName2, 1, 0, 0, 1, 0, null, null, null, null);
//        queueManagerApi.queueCheckAllParametersAPI(cookies, managerName, localQueueName2, 1, 0, 0, 1, 0, "local", "null", baseUrl());
    }
}
