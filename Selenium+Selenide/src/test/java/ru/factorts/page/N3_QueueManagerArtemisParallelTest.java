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

import static com.codeborne.selenide.Selenide.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N3_QueueManagerArtemisParallelTest extends Base {
    private static String cook;
    private static String cook2;
    @Rule
    public TestName testName = new TestName();

    QueueManagerArtemisPage queueManagerArtemisPage = new QueueManagerArtemisPage();
    QueueManagerArtemisApi queueManagerArtemisApi = new QueueManagerArtemisApi();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    CommonComponents commonComponents = new CommonComponents();
    Api api = new Api();
    static Api staticApi = new Api();
    SOPSApi sopsApi = new SOPSApi();
    MessagePage messagePage = new MessagePage();
    CreationSOPSPage creationSOPSPage;
    SOPSPage sopsPage;

    String addressName = commonComponents.createIndividualName(testName.getMethodName());
    String addressSenderName1 = commonComponents.createIndividualName("addressSender-");
    String addressRecipient1 = commonComponents.createIndividualName("addressRecipient-");
    String addConfigurationName = commonComponents.createIndividualName("addConfigurationName_");
    String receiverName1 = commonComponents.createIndividualName("receiverName1-");
    String receiverName2 = commonComponents.createIndividualName("receiverName2-");
    String receiverAddress = "0.0.0.0";
    String receiverProtocol = "TCP";
    String connectorName = commonComponents.createIndividualName("connectorName-");
    String connectorAddress = "fesb-test-0-1";
    String connectorProtocol = "TCP";
    String channelName = commonComponents.createIndividualName("channelName-");
    String userName = commonComponents.createIndividualName("userName-");
    String userPassword = commonComponents.createIndividualName("userPassword-");
    String userRoles = "factor-admin-group";
    String[] user = {userName, userPassword, userRoles};
    String[] user2 = {userName + "2", userPassword + "2", userRoles + "2"};
    String[] user3 = {userName + "2", userPassword + "2", userRoles};
    String domainName = commonComponents.createIndividualName("domainName-");
    String domainName2_1 = commonComponents.createIndividualName("domainName2_1-");
    String domainName2_2 = commonComponents.createIndividualName("domainName2-2-");
    String domainID_1_1 = sopsApi.createDomainAPI(Base.baseUrl(), cook, domainName);
    String domainID_2_1 = sopsApi.createDomainAPI(Base.baseUrl_2(), cook2, domainName2_1);
    String domainID_2_2 = sopsApi.createDomainAPI(Base.baseUrl_2(), cook2, domainName2_2);
    String sopsName = commonComponents.createIndividualName("sopsName");
    String sopsName2 = commonComponents.createIndividualName("sopsName2");
    String localQueueName1 = commonComponents.createIndividualName("localQueueName1-");
    String localQueueName2 = commonComponents.createIndividualName("localQueueName2-");
    String localQueueName3 = commonComponents.createIndividualName("localQueueName3-");
    String localQueueName4 = commonComponents.createIndividualName("localQueueName4-");
    String localQueueArtemisName1 = commonComponents.createIndividualName("localQueueArtemisName1-");
    String localQueueArtemisName2 = commonComponents.createIndividualName("localQueueArtemisName2-");
    String localQueueArtemisName3 = commonComponents.createIndividualName("localQueueArtemisName3-");
    String localQueueArtemisName4 = commonComponents.createIndividualName("localQueueArtemisName4-");
    String localQueueArtemisSecondary1 = commonComponents.createIndividualName("localQueueArtemisSecondary1-");
    String localQueueAddress1 = commonComponents.createIndividualName("localQueueAddress1-");
    String localQueueAddress2 = commonComponents.createIndividualName("localQueueAddress2-");
    String localQueueAddress3 = commonComponents.createIndividualName("localQueueAddress3-");
    String localQueueAddress4 = commonComponents.createIndividualName("localQueueAddress4-");

    Object[] queue;
    Object[] queueNew;
    Object[] queueNew2;
    String message = "message";
    String managerName = "QM";

    @BeforeClass
    public static void beforeClass() {
        cook = staticApi.loginAPI(baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        cook2 = staticApi.loginAPI(baseUrl_2(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        staticApi.switchModuleAPI(cook, baseUrl(), "factor-qme", "activate");
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "activate");
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        basePage.openPage(baseUrl());
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void afterTest() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
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
    @Parameters(value = {
//            "ANYCAST",
            "MULTICAST",
    })
    public void createQueueArtemis(String typeRoutsQueue) {
        if (typeRoutsQueue.equals("ANYCAST")) {
            Object[] queue = {localQueueArtemisName2 + "|" + QueueManagerArtemisPage.addressEqualsNameQueue.NO + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "user-login|durable-выкл|exclusive-вкл"};
            queueManagerArtemisPage.createQueue(queue);
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "0", "0", "0", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
            queueManagerArtemisPage.queueCheckEditForm(queue);
            queueManagerArtemisPage.prepairForCreateQueueTest(cook, domainID_1_1, sopsName, localQueueArtemisName1, localQueueAddress2 + "::" + localQueueArtemisName2);
            queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID_1_1, localQueueArtemisName1, "String message");
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        } else {
            Object[] queue1 = {localQueueArtemisName1 + "|" + QueueManagerArtemisPage.addressEqualsNameQueue.NO + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "user-login|durable-выкл|exclusive-вкл"};
            Object[] queue2 = {localQueueArtemisName2 + "|" + QueueManagerArtemisPage.addressEqualsNameQueue.NO + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "user-login|durable-выкл|exclusive-вкл"};
            queueManagerArtemisPage.createQueue(queue1);
            queueManagerArtemisPage.createQueue(queue2);
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "0", "0", "0", "0", QueueManagerArtemisPage.typeRoutsQueue.MULTICAST.toString());
            queueManagerArtemisPage.queueCheckEditForm(queue1);

            queueManagerArtemisPage.prepairForCreateQueueTest2(domainName, sopsName, localQueueName1, localQueueArtemisName1, localQueueArtemisName2, localQueueAddress2, localQueueName3, localQueueName4, addConfigurationName, "61716", "factor-admin", "!fesbROOT");
            sleep(10000);
            queueManagerMultyApi.sendMessgeInQueueAPI(cook, managerName, localQueueName1, "", 1);
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "0", "1", "1", "1", QueueManagerArtemisPage.typeRoutsQueue.MULTICAST.toString());
//            queueManagerArtemisPage.queueCheckEditForm(queue1);
            queueManagerMultyApi.queueCheckAllParametersAPI(cook, baseUrl(), managerName, localQueueName3, 1, 0, 0, 1, 0, null, null, null, null);

//            queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localQueueName3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
            queueManagerMultyApi.queueCheckAllParametersAPI(cook, baseUrl(), managerName, localQueueName4, 1, 0, 0, 1, 0, null, null, null, null);
//            queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localQueueName4, 1, 0, 0, 1, 0, "local", "null", baseUrl());
        }
    }

    @Test
    @Parameters(value = {
            "ANYCAST",
//            "MULTICAST",
    })
    public void editQueueArtemis(String typeRoutsQueue) {
        // Второстепенная очередь, нужна для возможности редактирования типа основной очереди
        Object[] queueSecondary;

        if (typeRoutsQueue.equals("ANYCAST")) {
            queue = new Object[]{localQueueArtemisName2 + "|" + false + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
            queueSecondary = new Object[]{localQueueArtemisSecondary1 + "|" + false + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
        } else {
            queue = new Object[]{localQueueArtemisName1 + "|" + false + "|" + localQueueAddress1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
            queueSecondary = new Object[]{localQueueArtemisSecondary1 + "|" + false + "|" + localQueueAddress1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};

        }
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue);
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueSecondary);

        if (typeRoutsQueue.equals("ANYCAST")) {
            queueNew = new Object[]{localQueueArtemisName2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "groupFirstKey-key|groupRebalance-вкл"};
        } else {
            queueNew = new Object[]{localQueueArtemisName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "groupFirstKey-key|groupRebalance-вкл"};
        }
        queueManagerArtemisPage.editQueue(queueNew);

        if (typeRoutsQueue.equals("ANYCAST")) {
            queueNew2 = new Object[]{localQueueArtemisName2 + "|" + QueueManagerArtemisPage.addressEqualsNameQueue.NO + "|" + localQueueAddress2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "groupFirstKey-key|exclusive-вкл|groupRebalance-вкл"};
            queueManagerArtemisPage.queueCheckEditForm(queueNew2);
            queueManagerArtemisPage.prepairForCreateQueueTest2(domainName, sopsName, localQueueName1, localQueueArtemisSecondary1, localQueueArtemisName2, localQueueAddress2, localQueueName3, localQueueName4, addConfigurationName, "61716", "factor-admin", "!fesbROOT");

            queueManagerMultyApi.sendMessgeInQueueAPI(cook, managerName, localQueueName1, "", 1);
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "0", "1", "1", "1", QueueManagerArtemisPage.typeRoutsQueue.MULTICAST.toString());
//            queueManagerArtemisPage.queueCheckEditForm(queue1);
            queueManagerMultyApi.queueCheckAllParametersAPI(cook, baseUrl(), managerName, localQueueName3, 1, 0, 0, 1, 0, null, null, null, null);

//            queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localQueueName3, 1, 0, 0, 1, 0, "local", "null", baseUrl());
            queueManagerMultyApi.queueCheckAllParametersAPI(cook, baseUrl(), managerName, localQueueName4, 1, 0, 0, 1, 0, null, null, null, null);

//            queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localQueueName4, 1, 0, 0, 1, 0, "local", "null", baseUrl());


//            queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID_1_1, localQueueArtemisName1, "String message");
//            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.MULTICAST.toString());
        } else {
            queueNew2 = new Object[]{localQueueArtemisName1 + "|" + QueueManagerArtemisPage.addressEqualsNameQueue.NO + "|" + localQueueAddress1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "groupFirstKey-key|exclusive-вкл|groupRebalance-вкл"};
            queueManagerArtemisPage.queueCheckEditForm(queueNew2);
            queueManagerArtemisPage.prepairForCreateQueueTest(cook, domainID_1_1, sopsName, localQueueAddress1 + "::" + localQueueArtemisName1, localQueueAddress2 + "::" + localQueueArtemisName2);
            queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID_1_1, localQueueAddress1 + "::" + localQueueArtemisName1, "String message");
            queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueAddress2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        }
    }

    @Test
    @Parameters(value = {
            "Без параметров-нет потребителей-автосоздание адреса",
            "Без параметров-нет потребителей-НЕавтосоздание адреса",
            "Без параметров-есть потребители",
//            "Удалить потребителей",
            "Удалить адрес если эта очередь единственная-единственная",
            "Удалить адрес если эта очередь единственная-НЕединственная",
    })
    public void deleteQueueArtemis(String parametersDelete) {
        queueManagerArtemisPage.prepairForDeleteQueueTest(cook, testName.getMethodName(), parametersDelete);
        queueManagerArtemisPage.deleteQueue(queueManagerArtemisPage.queueName1, parametersDelete);
        queueManagerArtemisPage.checkAfterDelete(parametersDelete);
    }

    @Test
    @Parameters(value = {
            "ANYCAST",
            "MULTICAST",
    })
    public void createAddressArtemis(String typeRoutsAddress) {
        if (typeRoutsAddress.equals("ANYCAST")) {
            Object[] address = {addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "DLA-" + testName.getMethodName() + "DLQ|enableMetrics-выкл|defaultExclusiveQueue-вкл"};
            queueManagerArtemisPage.createAddress(address);
            queueManagerArtemisPage.addressCheckAllParameters(addressName, typeRoutsAddress, "0");
            queueManagerArtemisPage.addressCheckEditForm(address);
            Object[] queue = {localQueueArtemisName1 + "|" + false + "|" + addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
            queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue);
            queueManagerArtemisPage.addressCheckAllParameters(addressName, typeRoutsAddress, "1");
        } else {
            Object[] address = {addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "DLA-" + testName.getMethodName() + "DLQ|enableMetrics-выкл|defaultExclusiveQueue-вкл"};
            queueManagerArtemisPage.createAddress(address);
            queueManagerArtemisPage.addressCheckAllParameters(addressName, typeRoutsAddress, "0");
            queueManagerArtemisPage.addressCheckEditForm(address);
            Object[] queue = {localQueueArtemisName1 + "|" + false + "|" + addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
            queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue);
            Object[] queue2 = {localQueueArtemisName2 + "|" + false + "|" + addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
            queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue2);
            queueManagerArtemisPage.addressCheckAllParameters(addressName, typeRoutsAddress + ", ANYCAST", "2");
        }
    }

    @Test
    public void editAddressArtemis() {
        Object[] address = {addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "enableMetrics/false|defaultNonDestructive/true|defaultDelayBeforeDispatch/5"};
        queueManagerArtemisApi.createAddressArtemisAPI(cook, address);

        Object[] addressNew = {addressName, "defaultDelayBeforeDispatch-5|autoCreateExpiryResources-вкл"};
        queueManagerArtemisPage.editAddress(addressNew);

        Object[] addressNew2 = {addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "defaultDelayBeforeDispatch-5|defaultNonDestructive-вкл|autoCreateExpiryResources-вкл"};
        queueManagerArtemisPage.addressCheckEditForm(addressNew2);
    }

    @Test
    public void deleteAddressArtemis() {
        Object[] address = {addressName + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST, "enableMetrics/false|defaultNonDestructive/true|defaultDelayBeforeDispatch/5"};
        queueManagerArtemisApi.createAddressArtemisAPI(cook, address);

        queueManagerArtemisPage.deleteAddress(addressName);
        queueManagerArtemisPage.addressShouldNotExist(addressName);
    }

    @Test
    @Parameters(value = {
            "ANYCAST",
            "MULTICAST",
            "PASS",
            "STRIP",
    })
    public void createVirtualAddressArtemis(String typeRoutsVirtualAddress) throws AssertionError {
        String virtualAddressName = commonComponents.createIndividualName("addrVirt-" + typeRoutsVirtualAddress);
        String addressName = commonComponents.createIndividualName(testName.getMethodName() + "addr-");
        String addressRedirectName1 = commonComponents.createIndividualName(testName.getMethodName() + "addrRedirect1-");
        String addressRedirectName2 = commonComponents.createIndividualName(testName.getMethodName() + "addrRedirect2-");
        String queueName1 = testName.getMethodName() + "-IN";
        String filter = "AMQSize > 500";
        String exclusive = "Нет";
        String message1 = messagePage.createMessageWithBiteSize(1);
        String message2 = messagePage.createMessageWithBiteSize(600);
        Object[] virtualAddress = {virtualAddressName + "|" + addressName + "|" + addressRedirectName1 + "|" + filter + "|" + exclusive + "|" + typeRoutsVirtualAddress};

        queueManagerArtemisPage.prepairForCreateVirtualAddressArtemisTest(cook, domainID_1_1, queueName1, addressName, addressRedirectName1, addressRedirectName2, typeRoutsVirtualAddress);
        queueManagerArtemisPage.createVirtualAddress(virtualAddress);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressName, addressName, addressRedirectName1, exclusive);
        queueManagerArtemisPage.virtualAddressCheckEditForm(virtualAddress);
        queueManagerArtemisPage.checkWorkOfVirtualAddressArtemisTest(cook, domainID_1_1, queueName1, message1, message2, addressRedirectName1, addressRedirectName2, typeRoutsVirtualAddress);
    }

    @Test
    public void editVirtualAddressArtemis() {
        Object[] queueSender1 = {addressSenderName1 + "|" + true + "|" + addressSenderName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueSender1);
        Object[] queueRecipient1 = {addressRecipient1 + "|" + true + "|" + addressRecipient1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueRecipient1);

        String virtualAddressName = commonComponents.createIndividualName(testName.getMethodName() + "virtualAddressName-");
        Object[] virtualAddressOld = {virtualAddressName + "|" + addressSenderName1 + "|" + addressRecipient1 + "|AMQSize > 500|" + true + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST};
        queueManagerArtemisApi.createVirtualAddressArtemisAPI(cook, virtualAddressOld);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressName, addressSenderName1, addressRecipient1, "Да");

        String virtualAddressNameNew = commonComponents.createIndividualName(testName.getMethodName() + "virtualAddressNameNew-");
        Object[] virtualAddressNew = {virtualAddressNameNew + "|" + addressRecipient1 + "|" + addressSenderName1 + "|AMQSize > 1000|Нет|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        queueManagerArtemisPage.editVirtualAddress(virtualAddressOld, virtualAddressNew);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressNameNew, addressRecipient1, addressSenderName1, "Нет");
        queueManagerArtemisPage.virtualAddressCheckEditForm(virtualAddressNew);
    }

    @Test
    public void cloneVirtualAddressArtemis() {
        Object[] queueSender1 = {addressSenderName1 + "|" + true + "|" + addressSenderName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueSender1);
        Object[] queueRecipient1 = {addressRecipient1 + "|" + true + "|" + addressRecipient1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueRecipient1);

        String virtualAddressName = commonComponents.createIndividualName(testName.getMethodName() + "virtualAddressName-");
        Object[] virtualAddressOld = {virtualAddressName + "|" + addressSenderName1 + "|" + addressRecipient1 + "|AMQSize > 500|" + true + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST};
        queueManagerArtemisApi.createVirtualAddressArtemisAPI(cook, virtualAddressOld);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressName, addressSenderName1, addressRecipient1, "Да");

        String virtualAddressNameNew = commonComponents.createIndividualName(testName.getMethodName() + "virtualAddressNameNew-");
        Object[] virtualAddressNew = {virtualAddressNameNew + "|" + addressSenderName1 + "|" + addressRecipient1 + "|AMQSize > 500|Да|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST};
        queueManagerArtemisPage.cloneVirtualAddress(virtualAddressOld, virtualAddressNew);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressNameNew, addressSenderName1, addressRecipient1, "Да");
        queueManagerArtemisPage.virtualAddressCheckEditForm(virtualAddressNew);
    }

    @Test
    public void deleteVirtualAddressArtemis() {
        Object[] queueSender1 = {addressSenderName1 + "|" + true + "|" + addressSenderName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueSender1);
        Object[] queueRecipient1 = {addressRecipient1 + "|" + true + "|" + addressRecipient1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST, "exclusive/exclusive/true"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueRecipient1);

        String virtualAddressName = commonComponents.createIndividualName(testName.getMethodName() + "virtualAddressName-");
        Object[] virtualAddress = {virtualAddressName + "|" + addressSenderName1 + "|" + addressRecipient1 + "|AMQSize > 500|" + true + "|" + QueueManagerArtemisPage.typeRoutsQueue.MULTICAST};
        queueManagerArtemisApi.createVirtualAddressArtemisAPI(cook, virtualAddress);
        queueManagerArtemisPage.virtualAddressCheckAllParameters(virtualAddressName, addressSenderName1, addressRecipient1, "Да");

        queueManagerArtemisPage.deleteVirtualAddress(virtualAddress);
        queueManagerArtemisPage.virtualAddressShouldNotExist(virtualAddressName);
    }

    @Test
    public void createReceiverArtemis() {
        String message = testName.getMethodName();
        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + "QM" + "|" + localQueueName1};
        String[] point1_2 = new String[]{"Выход|Локальная очередь РМО|" + localQueueArtemisName2, "селект-в-параметрах|Фабрика соединений Расширенного МО|" + addConfigurationName};
        String[][] pointAll_1 = {point1_1, point1_2};
        String[] scheme1 = {"Фабрика соединений Расширенного МО|" + addConfigurationName + "|" + userName + "|" + userPassword, "Сетевое соединение|${constantHost}|" + portForCreateReceiverArtemis};
        Object[] constant1 = {"constantHost", "fesb-test-0-1", false};
        Object[][] settingAllConstants = {constant1};
        Object[] receiver = {receiverName1 + "|" + receiverAddress + "|" + portForCreateReceiverArtemis + "|" + receiverProtocol};

        queueManagerArtemisPage.createReceiver(receiver);
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverName1, receiverAddress, portForCreateReceiverArtemis, receiverProtocol, "0");
        queueManagerArtemisPage.recieverCheckEditForm(receiver);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user[0], user[1], user[2]);

        basePage.openPage(baseUrl_2());
        sopsApi.createConstantsOfDomainAPI(cook2, baseUrl_2(), domainID_2_1, settingAllConstants);
        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName2_1, scheme1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName2_1, sopsName, pointAll_1);
        sopsApi.startDomainAPI(cook2, baseUrl_2(), domainID_2_1);
        queueManagerMultyApi.sendMessgeInQueueAPI(cook2, baseUrl_2(), "QM", localQueueName1, message, 1, false, "4");

        basePage.openPage(baseUrl());
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverName1, receiverAddress, portForCreateReceiverArtemis, receiverProtocol, "1");
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        queueManagerArtemisPage.queueArtemisShouldHaveSpecificMessage(localQueueArtemisName2, message);
    }

    @Test
    public void editReceiverArtemis() {
        String receiverPort = portForEditReceiverArtemis1;
        String receiverNameNew = commonComponents.createIndividualName(testName.getMethodName() + "New-");
        String receiverAddressNew = "0.0.0.0";
        String receiverPortNew = portForEditReceiverArtemis2;
        String[] addConfigOld = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPort};
        String[] addConfigNew = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPortNew};
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + receiverPort + "|" + receiverProtocol.toLowerCase(), "wantClientAuth~true|autoStart~false|batchDelay~10"};

        queueManagerArtemisPage.preparationForReceiver(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, addConfigOld, user);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        Object[] receiverNew = {receiverNameNew + "|" + receiverAddressNew + "|" + receiverPortNew + "|" + receiverProtocol, "remotingThreads~20|directDeliver~false|connectionsAllowed~100"};
        queueManagerArtemisPage.editReseiver(receiver, receiverNew);
        Object[] receiverNewFinal = {receiverNameNew + "|" + receiverAddressNew + "|" + receiverPortNew + "|" + receiverProtocol, "remotingThreads~20|directDeliver~false|connectionsAllowed~100|wantClientAuth~false|batchDelay~10"};
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverNameNew, receiverAddressNew, receiverPortNew, receiverProtocol, "0");
        queueManagerArtemisPage.recieverCheckEditForm(receiverNewFinal);
        sleep(3000);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");

        sopsApi.editAddConfigurationAPI(cook2, baseUrl_2(), addConfigOld, addConfigNew);
        sopsApi.stopDomainAPI(cook2, baseUrl_2(), domainID_2_1);
        sopsApi.startDomainAPI(cook2, baseUrl_2(), domainID_2_1);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 2, 0, 2, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        Object[] receiverNew2 = {receiverNameNew + "|" + receiverAddressNew + "|" + receiverPortNew + "|" + receiverProtocol, "batchDelay~10|directDeliver~false|connectionsAllowed~100|wantClientAuth~false|remotingThreads~20"};
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverNameNew, receiverAddressNew, receiverPortNew, receiverProtocol, "0");
        queueManagerArtemisPage.recieverCheckEditForm(receiverNew2);
    }

    @Test
    public void stopAndStartReceiverArtemis() {
        String receiverPort = portForStopAndStartReceiverArtemis;
        String[] addConfigOld = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPort};
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + receiverPort + "|" + receiverProtocol.toLowerCase(), "wantClientAuth~true|autoStart~false|batchDelay~10"};

        queueManagerArtemisPage.preparationForReceiver(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, addConfigOld, user);
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverName1, receiverAddress, receiverPort, receiverProtocol, "1");
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        queueManagerArtemisPage.stopReseiver(receiver);
        sleep(2000);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        queueManagerArtemisPage.receiverCheckAllParameters("error", receiverName1, receiverAddress, receiverPort, receiverProtocol, "0");
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        queueManagerArtemisPage.startReseiver(receiver);
        refresh();
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverName1, receiverAddress, receiverPort, receiverProtocol, "1");
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 3, 0, 3, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
    }

    @Test
    public void cloneReceiverArtemis() {
        String receiverPort = portForCloneReceiverArtemis1;
        String receiverNameNew = commonComponents.createIndividualName(testName.getMethodName() + "New-");
        String receiverAddressNew = "0.0.0.0";
        String receiverPortNew = portForCloneReceiverArtemis2;
        String[] addConfigOld = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPort};
        String[] addConfigNew = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPortNew};

        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + receiverPort + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        queueManagerArtemisPage.preparationForReceiver(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, addConfigOld, user);

        Object[] receiverNew = {receiverNameNew + "|" + receiverAddressNew + "|" + receiverPortNew + "|" + receiverProtocol, "directDeliver~false|connectionsAllowed~100|remotingThreads~15"};
        queueManagerArtemisPage.cloneReseiver(receiver, receiverNew);
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverName1, receiverAddress, receiverPort, receiverProtocol, "1");
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverNameNew, receiverAddressNew, receiverPortNew, receiverProtocol, "0");
        Object[] receiverNewFinal = {receiverNameNew + "|" + receiverAddressNew + "|" + receiverPortNew + "|" + receiverProtocol, "directDeliver~false|connectionsAllowed~100|remotingThreads~15|batchDelay~10"};
        queueManagerArtemisPage.recieverCheckEditForm(receiverNewFinal);

        basePage.openPage(baseUrl_2());
        sopsApi.editAddConfigurationAPI(cook2, baseUrl_2(), addConfigOld, addConfigNew);
        sopsPage = new SOPSPage();
        sopsPage.stopDomain(domainName2_1);
        sopsPage.startDomain(domainName2_1);
//        sopsApi.stopDomainAPI(cook2, baseUrl_2(), domainID_2_1);
//        sopsApi.startDomainAPI(cook2, baseUrl_2(), domainID_2_1);
//        refresh();
        basePage.openPage(baseUrl());
        queueManagerArtemisPage.receiverCheckAllParameters("success", receiverNameNew, receiverAddressNew, receiverPortNew, receiverProtocol, "1");
    }

    @Test
    public void deleteReceiverArtemis() {
        String receiverPort = portForDeleteReceiverArtemis;
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + receiverPort + "|" + receiverProtocol, "autoStart~false|batchDelay~10"};
        String[] addConfigOld = {"Фабрика соединений Расширенного МО", domainID_2_1, addConfigurationName, userName, userPassword, "tcp~fesb-test-0-1~" + receiverPort};
        queueManagerArtemisPage.preparationForReceiver(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, addConfigOld, user);

        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        queueManagerArtemisPage.deleteReseiver(receiver);
        queueManagerArtemisPage.receiverShouldNotExist(receiverName1);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, "message");
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
    }

    @Test
    public void createConnectorArtemis() {
        String port = portForCreateConnectorArtemis;
        String message = testName.getMethodName();
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + port + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + port};
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};

        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.createConnector(connector);
        queueManagerArtemisPage.connectorCheckAllParameters(connectorName, connectorProtocol, connectorAddress, port);
        queueManagerArtemisPage.connectorCheckEditForm(connector);

        queueManagerArtemisPage.preparationForConnector(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, null, channel);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        queueManagerArtemisPage.queueArtemisShouldHaveSpecificMessage(localQueueArtemisName2, message);
    }

    @Test
    public void editConnectorArtemis() {
        String portOld = portForEditConnectorArtemis1;
        String portNew = portForEditConnectorArtemis2;
        String connectorNameNew = commonComponents.createIndividualName("connectorNameNew-");
        String connectorProtocolNew = "TCP";
        String connectorAddressNew = "fesb-test-0-1";
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + portOld + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] receiverNew = {receiverName1 + "|" + receiverAddress + "|" + portNew + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + portOld, "remotingThreads~3|tcpNoDelay~false"};
        String[] connectorNew = {connectorNameNew + "|" + connectorProtocolNew + "|" + connectorAddressNew + "|" + portNew, "batchDelay~10|trustAll~вкл"};
        String[] connectorNewFinal = {connectorNameNew + "|" + connectorProtocolNew + "|" + connectorAddressNew + "|" + portNew, "batchDelay~10|trustAll~вкл|tcpNoDelay~вкл"};
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        String[] channelNew = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorNameNew, "user~" + userName + "|password~" + userPassword};
        queueManagerArtemisPage.preparationForConnector(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, connector, channel);
        sleep(2000);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.editConnector(connector, connectorNew);
        queueManagerArtemisPage.connectorCheckAllParameters(connectorNameNew, connectorProtocolNew, connectorAddressNew, portNew);
        queueManagerArtemisPage.connectorCheckEditForm(connectorNewFinal);
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "deactivate");
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "activate");
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        queueManagerArtemisApi.editChanneArtemisAPI(cook2, baseUrl_2(), channel, channelNew);
        queueManagerArtemisApi.editReceiverArtemisAPI(cook, baseUrl(), receiver, receiverNew);
        sleep(2000);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 3, 0, 3, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
    }

    @Test
    public void cloneConnectorArtemis() {
        String portOld = portForCloneConnectorArtemis1;
        String portNew = portForCloneConnectorArtemis2;
        String connectorNameNew = commonComponents.createIndividualName("connectorNameNew-");
        String connectorProtocolNew = "TCP";
        String connectorAddressNew = "fesb-test-0-1";
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + portOld + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] receiver2 = {receiverName2 + "|" + receiverAddress + "|" + portNew + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + portOld, "remotingThreads~3|tcpNoDelay~false"};
        String[] connectorNew = {connectorNameNew + "|" + connectorProtocolNew + "|" + connectorAddressNew + "|" + portNew, "batchDelay~10|trustAll~вкл"};
        String[] connectorNewFinal = {connectorNameNew + "|" + connectorProtocolNew + "|" + connectorAddressNew + "|" + portNew, "batchDelay~10|trustAll~вкл|tcpNoDelay~вкл"};
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        String[] channel2 = {channelName + "2" + "|" + localQueueArtemisName4 + "|" + localQueueArtemisName4 + "|" + connectorNameNew, "user~" + userName + "|password~" + userPassword};
        queueManagerArtemisPage.preparationForConnector(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, connector, channel);
        queueManagerArtemisPage.preparationForConnector(cook, cook2, receiver2, domainID_2_2, sopsName, localQueueArtemisName3, localQueueArtemisName4, user2, null, channel2);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.cloneConnector(connector, connectorNew);
        queueManagerArtemisPage.connectorCheckAllParameters(connectorNameNew, connectorProtocolNew, connectorAddressNew, portNew);
        queueManagerArtemisPage.connectorCheckEditForm(connectorNewFinal);
        queueManagerArtemisApi.deleteConnectorArtemisAPI(cook2, Base.baseUrl_2(), connector);
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "deactivate");
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "activate");
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_2, localQueueArtemisName3, message);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName4, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
    }

    @Test
    public void deleteConnectorArtemis() {
        String port = portForDeleteConnectorArtemis1;
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + port + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + port, "remotingThreads~3|tcpNoDelay~false"};
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        queueManagerArtemisPage.preparationForConnector(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, connector, channel);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        sleep(2000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);

        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.deleteConnector(connector);
        queueManagerArtemisPage.connectorShouldNotExist(receiverName1);
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "deactivate");
        staticApi.switchModuleAPI(cook2, baseUrl_2(), "factor-qme", "activate");
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        sleep(5000);
        queueManagerArtemisApi.queueCheckAllParametersAPI(cook, baseUrl(), localQueueArtemisName2, 1, 0, 1, 0, QueueManagerArtemisPage.typeRoutsQueue.ANYCAST);
    }

    @Test
    public void createChannelArtemis() {
        String port = portForCreateChannelArtemis1;
        String message = testName.getMethodName();
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + port + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + port};

        queueManagerArtemisPage.preparationForCreateChannel(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, connector);
        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.createChannel(channel);
        queueManagerArtemisPage.channelCheckAllParameters(channelName, localQueueArtemisName2, localQueueArtemisName2, connectorName, "0", "0");
        queueManagerArtemisPage.connectorCheckEditForm(connector);

        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        queueManagerArtemisPage.queueArtemisShouldHaveSpecificMessage(localQueueArtemisName2, message);
    }

    @Test
    public void editChannelArtemis() {
        String port = portForEditChannelArtemis1;
        String portNew = portForEditChannelArtemis2;
        String message = testName.getMethodName();
        String channelNameNew = "ChannelNew";
        String connectorNameNew = "Коннектор New";

        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        String[] channelNew = {channelNameNew + "|" + localQueueArtemisName4 + "|" + localQueueArtemisName4 + "|" + connectorNameNew, "user~" + userName + "2" + "|password~" + userPassword + "2"};
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + port + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] receiverNew = {receiverName2 + "|" + receiverAddress + "|" + portNew + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + port};
        String[] connectorNew = {connectorNameNew + "|" + connectorProtocol + "|" + connectorAddress + "|" + portNew};

        queueManagerArtemisPage.preparationForEditChannel(cook, cook2, receiver, receiverNew, domainID_2_1, domainID_2_2, sopsName, sopsName2, localQueueArtemisName1, localQueueArtemisName2, localQueueArtemisName3, localQueueArtemisName4, user, user3, connector, connectorNew, channel);

        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());

        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.editChannel(channel, channelNew);
        queueManagerArtemisPage.channelCheckAllParameters(channelNameNew, localQueueArtemisName4, localQueueArtemisName4, connectorNameNew, "0", "0");
        refresh();
        queueManagerArtemisPage.channelCheckAllParameters(channelNameNew, localQueueArtemisName4, localQueueArtemisName4, connectorNameNew, "0", "0");

        basePage.openPage(baseUrl());
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_2, localQueueArtemisName3, message);

        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName4, localQueueArtemisName4, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
        queueManagerArtemisPage.queueArtemisShouldHaveSpecificMessage(localQueueArtemisName4, message);
    }

    @Test
    public void deleteChannelArtemis() {
        String port = portForDeleteChannelArtemis;
        String message = testName.getMethodName();
        String[] channel = {channelName + "|" + localQueueArtemisName2 + "|" + localQueueArtemisName2 + "|" + connectorName, "user~" + userName + "|password~" + userPassword};
        String[] receiver = {receiverName1 + "|" + receiverAddress + "|" + port + "|" + receiverProtocol.toLowerCase(), "autoStart~false|batchDelay~10"};
        String[] connector = {connectorName + "|" + connectorProtocol + "|" + connectorAddress + "|" + port};

        queueManagerArtemisPage.preparationForCreateChannel(cook, cook2, receiver, domainID_2_1, sopsName, localQueueArtemisName1, localQueueArtemisName2, user, connector);
        basePage.openPage(baseUrl_2());
        queueManagerArtemisApi.createChanneArtemisAPI(cook2, Base.baseUrl_2(), channel);
        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());

        basePage.openPage(baseUrl_2());
        queueManagerArtemisPage.deleteChannel(channel);
        queueManagerArtemisPage.channelShouldNotExist(channelName);
        refresh();
        queueManagerArtemisPage.channelShouldNotExist(channelName);

        queueManagerArtemisApi.sendMessageInQueueAPI(cook2, baseUrl_2(), domainID_2_1, localQueueArtemisName1, message);
        basePage.openPage(baseUrl());
        queueManagerArtemisPage.queueCheckAllParameters(localQueueArtemisName2, localQueueArtemisName2, "1", "0", "1", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
    }
}
