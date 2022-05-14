package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.ConfigProperties;

import java.util.Arrays;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class QueueManagerArtemisPage extends BasePage {
    private static String typeQueue = "//div[@role=\"option\"][text()='%s']";

    BasePage basePage = new BasePage();
    Base base = new Base();
    private SpecificQueuePage specificQueuePage;
    private SpecificMessagePage specificMessagePage;
    CommonComponents commonComponents = new CommonComponents();
    MessagePage messagePage = new MessagePage();
    TransactionMonitorPage transactionMonitorPage = new TransactionMonitorPage();
    LoginPage loginPage = new LoginPage();
    QueueManagerPage queueManagerPage = new QueueManagerPage("empty");
    SOPSApi sopsApi = new SOPSApi();
    QueueManagerArtemisApi queueManagerArtemisApi = new QueueManagerArtemisApi();
    Api api = new Api();
    SOPSPage sopsPage;
    CreationSOPSPage creationSOPSPage;

    String domainName = null;
    String sopsName = null;
    String queueName1 = null;
    String queueName2 = null;
    String queueAddress = null;
    String guid = null;

    //TABLE QUEUE
    SelenideElement table = $x(".//table[thead[tr[th[text()='Имя']]]]");
    SelenideElement tableHeaderName = table.$x(".//tr/th[text()='Имя']");
    SelenideElement searchNameQueueIcon = $x(".//span[text()='Имя']/../../../..//span[@role=\"button\"]");
    SelenideElement searchAdressIcon = $x(".//span[text()='Адрес']/../../../..//span[@role=\"button\"]");
    SelenideElement searchNameQueueInput = $x(".//input[@placeholder=\"Найти Имя\"]");
    SelenideElement searchAddressInput = $x(".//input[@placeholder=\"Найти Адрес\"]");
    SelenideElement searchNameButton = $$x(".//span[text()='Найти']/..").find(visible);
    SelenideElement afterSearchQueueRow = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]");
    SelenideElement afterSearchQueueName = $x(".//td[@class=\"ant-table-cell\"][1]");
    SelenideElement afterSearchQueueAddress = $x(".//td[@class=\"ant-table-cell\"][2]");
    SelenideElement afterSearchQueueDepth = $x(".//td[@class=\"ant-table-cell\"][3]");
    SelenideElement afterSearchNumberOfConsumers = $x(".//td[@class=\"ant-table-cell\"][4]");
    SelenideElement afterSearchSentToQueue = $x(".//td[@class=\"ant-table-cell\"][5]");
    SelenideElement afterSearchTakenFromQueue = $x(".//td[@class=\"ant-table-cell\"][6]");
    SelenideElement afterSearchRoutingType = $x(".//td[@class=\"ant-table-cell\"][7]");
    SelenideElement afterSearchAll = $x(".//td[@class=\"ant-table-cell\"][8]");
    SelenideElement firstMessage = $x(".//tr[@data-row-key]");
    SelenideElement messageBody = $x(".//div[text()='Тело сообщения']/following-sibling::div//textarea");
    SelenideElement returnToQueueButton = $x(".//a[text()='Вернуться в очередь']");
    SelenideElement messagesTabInQueue = $x(".//div[text()='Сообщения']");

    //CREATE QUEUE
    SelenideElement addressEqualsNameQueueCheckbox = $x(".//span[text()='Адрес совпадает с именем очереди']/../..//label/span[contains(@class,\"ant-checkbox\")]");
    SelenideElement addressEqualsNameQueueEnabledCheckbox = $x(".//span[text()='Адрес совпадает с именем очереди']/../..//label/span[contains(@class,\"ant-checkbox-checked\")]");
    SelenideElement addressInput = $x(".//label[text()='Адрес']/../..//input");
    SelenideElement typeRoutsActivateSelect = $x(".//label[text()='Тип маршрутизации']/../following-sibling::div");
    SelenideElement typeRoutInput = $x(".//label[text()='Тип маршрутизации']/../following-sibling::div//input");
    SelenideElement typeRoutValue = $x(".//label[text()='Тип маршрутизации']/../following-sibling::div//input/../following-sibling::span");
    SelenideElement nameLastParameterInput = $x(".//tr[last()]//div[@name=\"name\"]//input");
    SelenideElement valueLastParameteInput = $x(".//tr[last()]//input[@name=\"value\"]");

    ElementsCollection nameParametersInputList = $$x("(.//div[@name=\"name\"]//input)");
    ElementsCollection valueParametersInputList = $$x("(.//input[@name=\"value\"])");

    //DELETE QUEUE
    SelenideElement deleteCustomersOfQueueCheckbox = $x(".//span[text()='Удалить потребителей']/../..//label/span[contains(@class,\"ant-checkbox\")]");
    SelenideElement deleteAddressIfSoleQueueCheckbox = $x(".//span[text()='Удалить адрес, если эта очередь единственная']/../..//label/span[contains(@class,\"ant-checkbox\")]");

    //TABLE ADDRESS
    SelenideElement addAddressButton = $x(".//span[text()= 'Добавить адрес']/..");
    SelenideElement addVirtualAddressButton = $x(".//span[text()= 'Добавить виртуальный адрес']/..");
    SelenideElement afterSearchAddressAddress = $x(".//td[contains(@class,\"ant-table-cell\")][1]");
    SelenideElement afterSearchAddressTypeOfRouting = $x(".//td[contains(@class,\"ant-table-cell\")][2]");
    SelenideElement afterSearchAddressNumberOfQueues = $x(".//td[contains(@class,\"ant-table-cell\")][3]");
    SelenideElement afterSearchNameVirtualAddress = $x(".//td[contains(@class,\"ant-table-cell\")][1]");
    SelenideElement afterSearchAddressVirtualAddress = $x(".//td[contains(@class,\"ant-table-cell\")][2]");
    SelenideElement afterSearchAddressOfRedirectVirtualAddress = $x(".//td[contains(@class,\"ant-table-cell\")][3]");
    SelenideElement afterSearchExclusive = $x(".//td[contains(@class,\"ant-table-cell\")][4]");

    //ADDRESS FORM
    SelenideElement addressActivateSelect = $x(".//label[text()='Адрес']/../following-sibling::div");
    SelenideElement addressInVirtAddrInput = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    SelenideElement nameValue = $x(".//label[text()='Имя']/../following-sibling::div//input");
    SelenideElement addressValue = $x(".//label[text()='Адрес']/../following-sibling::div//input/../following-sibling::span/div/span[not(@style)]");
    SelenideElement addressRedirectActivateSelect = $x(".//label[text()='Адрес перенаправления']/../following-sibling::div");
    SelenideElement addressRedirectInput = $x(".//label[text()='Адрес перенаправления']/../following-sibling::div//input");
    SelenideElement addressRedirectValue = $x(".//label[text()='Адрес перенаправления']/../following-sibling::div//input/../following-sibling::span/div/span[not(@style)]");
    SelenideElement filterInput = $x(".//label[text()='Фильтр']/../following-sibling::div//input");
    SelenideElement filterValue = $x(".//label[text()='Фильтр']/../following-sibling::div//input");
    SelenideElement exclusiveCheckBox = $x(".//span[text()='Эксклюзивный']/ancestor::label//input/..");
    SelenideElement typeOfRoutingValue = $x(".//label[text()='Тип маршрутизации']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");

    //TABLE RESEIVER
    SelenideElement addReseiverButton = $x(".//span[text()= 'Добавить приёмник']/..");
    SelenideElement searchNameReceiverIcon = $x(".//span[text()='Имя']/../../../..//span[@role=\"button\"]");
    SelenideElement afterSearchReceiverStatus = $x(".//td[@class=\"ant-table-cell\"][1]/span/span/span[1]");
    SelenideElement afterSearchReceiverName = $x(".//td[@class=\"ant-table-cell\"][1]");
    SelenideElement afterSearchReceiverProtocol = $x(".//td[@class=\"ant-table-cell\"][2]");
    SelenideElement afterSearchReceiverAddress = $x(".//td[@class=\"ant-table-cell\"][3]");
    SelenideElement afterSearchReceiverPort = $x(".//td[@class=\"ant-table-cell\"][4]");
    SelenideElement afterSearchReceiverConnectors = $x(".//td[@class=\"ant-table-cell\"][5]");

    //RESEIVER FORM
    SelenideElement portReceiverInput = $x(".//label[text()='Порт']/../following-sibling::div//input");
    SelenideElement addressReceiverValue = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    SelenideElement portReceiverValue = $x(".//label[text()='Порт']/../following-sibling::div//input");
    SelenideElement protocolReceiverValue = $x(".//label[text()='Протокол']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]");

    //TABLE CONNECTOR
    SelenideElement addConnectorButton = $x(".//span[text()= 'Добавить коннектор']/..");
    SelenideElement searchNameConnectorIcon = $x(".//span[text()='Имя']/../../../..//span[@role=\"button\"]");
    SelenideElement afterSearchNameConnector = $x(".//td[contains(@class,\"ant-table-cell\")][1]");
    SelenideElement afterSearchProtocolConnector = $x(".//td[contains(@class,\"ant-table-cell\")][2]");
    SelenideElement afterSearchAddressConnector = $x(".//td[contains(@class,\"ant-table-cell\")][3]");
    SelenideElement afterSearchPortConnector = $x(".//td[contains(@class,\"ant-table-cell\")][4]");

    //CONNECTOR FORM
    SelenideElement protocolConnectorActivate = $x(".//label[text()='Протокол']/../following-sibling::div");
    SelenideElement protocolConnectorInput = $x(".//label[text()='Протокол']/../following-sibling::div//input");
    SelenideElement nameConnectorValue = $x(".//label[text()='Имя']/../following-sibling::div//input");
    SelenideElement protocolConnectorValue = $x(".//label[text()='Протокол']/../following-sibling::div//input/../following-sibling::span");
    SelenideElement addressConnectorValue = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    SelenideElement portConnectorValue = $x(".//label[text()='Порт']/../following-sibling::div//input");

    //TABLE CHANNEL
    SelenideElement addChannelButton = $x(".//span[text()= 'Добавить канал']/..");
    SelenideElement afterSearchNameChannel = $x(".//td[contains(@class,\"ant-table-cell\")][1]");
    SelenideElement afterSearchNameQueue = $x(".//td[contains(@class,\"ant-table-cell\")][2]");
    SelenideElement afterSearchRedirectAddress = $x(".//td[contains(@class,\"ant-table-cell\")][3]");
    SelenideElement afterSearchConnectors = $x(".//td[contains(@class,\"ant-table-cell\")][4]");
    SelenideElement afterSearchSent = $x(".//td[contains(@class,\"ant-table-cell\")][5]");
    SelenideElement afterSearchConfirmed = $x(".//td[contains(@class,\"ant-table-cell\")][6]");

    //CHANNEL FORM
    SelenideElement channelNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    SelenideElement queueNameActivate = $x(".//label[text()='Имя']/../following-sibling::div");
    SelenideElement redirectionAddressInput = $x(".//label[text()='Адрес переадресации']/../following-sibling::div//input");
    SelenideElement connectorsCleanField = $x(".//label[text()='Коннекторы']/../following-sibling::div//div[@class=\"ant-select-selector\"]");
    SelenideElement connectorsActivate = $x(".//label[text()='Коннекторы']/../following-sibling::div");
    SelenideElement connectorsInput = $x(".//label[text()='Коннекторы']/../following-sibling::div//input");
    SelenideElement queueNameInput = $x(".//label[text()='Имя очереди']/../following-sibling::div//input");

    //TABLE SECURITY
    SelenideElement addSettingOfSecurityButton = $x(".//span[text()= 'Добавить настройку безопасности']/..");
    SelenideElement addUserButton = $x(".//span[text()= 'Добавить пользователя']/..");

    //SECURITY FORM
    SelenideElement userNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    SelenideElement userPasswordInput = $x(".//label[text()='Пароль']/../following-sibling::div//input");
    SelenideElement rolesActivateSelect = $x(".//label[text()='Роли']/../following-sibling::div");
    SelenideElement rolesInput = $x(".//label[text()='Роли']/../following-sibling::div//input");

    //SETTING CONFIGURATION FORM
    SelenideElement hardInput = $x(".//label[text()='Максимальный процент использования жесткого диска']/../following-sibling::div//input");
    SelenideElement ramInput = $x(".//label[text()='Количество выделенной оперативной памяти для сообщений']/../following-sibling::div//input");

    enum addressEqualsNameQueue {YES, NO}

    enum typeRoutsQueue {ANYCAST, MULTICAST, PASS, STRIP}

    @Step
    public void createQueue(Object[] queueSetting) {
        String address = null;
        String nameQueue = queueSetting[0].toString().split("\\|")[0];
        String addressEqualsName = queueSetting[0].toString().split("\\|")[1];
        if (addressEqualsName.equals("NO")) {
            address = queueSetting[0].toString().split("\\|")[2];
        }
        String typeRouts = queueSetting[0].toString().split("\\|")[3];

        if (!url().contains("/artemis-queue-manager")) {
            click(queueManagerArtemisTab);
            click(queuelistArtemisTab);
        }
        click(queueManagerPage.addQueueButton);
        val(queueManagerPage.queueNameInput, nameQueue);

        if (addressEqualsName.equals(addressEqualsNameQueue.YES.toString()) && !addressEqualsNameQueueCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(addressEqualsNameQueueCheckbox);
        } else if (addressEqualsName.equals(addressEqualsNameQueue.NO.toString()) && addressEqualsNameQueueCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(addressEqualsNameQueueCheckbox);
            val(addressInput, address);
        }

        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRouts);

        for (int x = 0; x < queueSetting[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, queueSetting[1].toString().split("\\|")[x].split("-")[0]);

            switch (queueSetting[1].toString().split("\\|")[x].split("-")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, queueSetting[1].toString().split("\\|")[x].split("-")[1]);
            }
        }
        click(saveButton);
    }

    @Step("Searching queue {0}")
    public void searchQueue(String queueName) {
        if (!url().endsWith("/artemis-queue-manager/queues")) {
            click(queueManagerArtemisTab);
            click(queuelistArtemisTab);
        }
        autoUpdateOff();
        click(searchNameQueueIcon);
        val(searchNameQueueInput, queueName);
        click(searchNameButton);
    }

    @Step("Searching address {0}")
    public void searchAddress(String addressName) {
        if (!url().contains("/artemis-queue-manager/addresses")) {
            click(queueManagerArtemisTab);
            click(adresseslistArtemisTab);
        }
        autoUpdateOff();
        click(searchAdressIcon);
        val(searchAddressInput, addressName);
        click(searchNameButton);
    }

    @Step("Searching address {0}")
    public void searchVirtualAddress(String addressName) {
        if (!url().contains("/artemis-queue-manager/diverts")) {
            click(queueManagerArtemisTab);
            click(virtualAdresseslistArtemisTab);
        }
        autoUpdateOff();
        click(searchNameQueueIcon);
        val(searchNameQueueInput, addressName);
        click(searchNameButton);
    }

    @Step("Searching receiver {0}")
    public void searchReceiver(String receiverName) {
        if (!url().contains("artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }
        click(searchNameReceiverIcon);
        val(searchNameQueueInput, receiverName);
        click(searchNameButton);
    }

    @Step("Searching connector {0}")
    public void searchConnector(String connectorName) {
        if (!url().contains("/artemis-queue-manager/transport/connectors")) {
            click(queueManagerArtemisTab);
            click(connectorsArtemisTab);
        }
        click(searchNameConnectorIcon);
        val(searchNameQueueInput, connectorName);
        click(searchNameButton);
    }

    @Step("Searching connector {0}")
    public void searchChannel(String channelName) {
        if (!url().contains("/artemis-queue-manager/bridges")) {
            click(queueManagerArtemisTab);
            click(channelsArtemisTab);
        }
        click(searchNameConnectorIcon);
        val(searchNameQueueInput, channelName);
        click(searchNameButton);
    }

    @Step
    public void queueCheckAllParameters(String queueName, String address, String messagesInDepth, String numberOfConsumers, String messagesInSent, String messagesInTaken, String routingType) {
        click(queueManagerArtemisTab);
        if (!queuelistArtemisTab.attr("class").contains("active"))
            click(queuelistArtemisTab);
        searchQueue(queueName);
        autoUpdateOn();
        compareStringAndElementText(queueName, afterSearchQueueName);
        compareStringAndElementText(address, afterSearchQueueAddress);
        compareStringAndElementText(messagesInDepth, afterSearchQueueDepth);
        compareStringAndElementText(numberOfConsumers, afterSearchNumberOfConsumers);
        compareStringAndElementText(messagesInSent, afterSearchSentToQueue);
        compareStringAndElementText(messagesInTaken, afterSearchTakenFromQueue);
        compareStringAndElementText(routingType, afterSearchRoutingType);
        afterSearchAll.shouldNotHave(exactText(""));
        autoUpdateOff();
    }

    @Step
    public void addressCheckAllParameters(String address, String typeOfRouting, String numberOfQueues) {
        click(queueManagerArtemisTab);
        if (!adresseslistArtemisTab.attr("class").contains("active"))
            click(adresseslistArtemisTab);
        searchAddress(address);
        autoUpdateOn();
        compareStringAndElementText(address, afterSearchAddressAddress);
        compareStringAndElementText(typeOfRouting, afterSearchAddressTypeOfRouting);
        compareStringAndElementText(numberOfQueues, afterSearchAddressNumberOfQueues);
        autoUpdateOff();
    }

    @Step
    public void receiverCheckAllParameters(String receiverStatus, String receiverName, String receiveraddress, String receiverPort, String receiverProtocol, String receiverConnects) {
        click(queueManagerArtemisTab);
        if (!receiversArtemisTab.attr("class").contains("active"))
            click(receiversArtemisTab);
        searchReceiver(receiverName);
        if (receiverStatus.equals("success")) {
            afterSearchReceiverStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-success"));
        } else if (receiverStatus.equals("error")) {
            afterSearchReceiverStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-error"));
        }

        compareStringAndElementText(receiverName, afterSearchReceiverName);
        compareStringAndElementText(receiverProtocol, afterSearchReceiverProtocol);
        compareStringAndElementText(receiveraddress, afterSearchReceiverAddress);
        compareStringAndElementText(receiverPort, afterSearchReceiverPort);
        compareStringAndElementText(receiverConnects, afterSearchReceiverConnectors);
    }

    @Step
    public void queueCheckEditForm(Object[] queueSetting) {
        String address = null;
        String nameQueue = queueSetting[0].toString().split("\\|")[0];
        String addressEqualsName = queueSetting[0].toString().split("\\|")[1].split("-")[0];
        String[] parameters = queueSetting[1].toString().split("\\|");
        if (addressEqualsName.equals("NO")) {
            address = queueSetting[0].toString().split("\\|")[2];
        }
        String typeRout = queueSetting[0].toString().split("\\|")[3];

        click(queueManagerArtemisTab);
        if (!queuelistArtemisTab.attr("class").contains("active"))
            click(queuelistArtemisTab);
        searchQueue(nameQueue);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        compareStringAndElementValue(nameQueue, queueManagerPage.queueNameInput);
        if (addressEqualsName.equals("YES")) {
            addressEqualsNameQueueEnabledCheckbox.shouldBe(visible);
        } else {
            compareStringAndElementValue(address, addressInput);
        }
        compareStringAndElementText(typeRout, typeRoutValue);

        sout(Arrays.toString(parameters));
        Arrays.sort(parameters);
        sout(Arrays.toString(parameters));

        for (int x = 0; x < parameters.length; x++) {
            compareStringAndElementText(parameters[x].split("-")[0], nameParametersInputList.get(x).parent().sibling(0));
            if (parameters[x].split("-")[1].equals("вкл")) {
                valueParametersInputList.get(x).shouldHave(attribute("checked"));
            } else if (parameters[x].split("-")[1].equals("выкл")) {
                valueParametersInputList.get(x).shouldHave(attribute("disabled"));
            } else {
                compareStringAndElementValue(parameters[x].split("-")[1], valueParametersInputList.get(x));
            }
        }
        click(closeWindowIcon);
    }

    @Step
    public void editQueue(Object[] queueSetting) {

        String nameQueue = queueSetting[0].toString().split("\\|")[0];
        String typeRouts = queueSetting[0].toString().split("\\|")[1];

        if (!url().contains("/artemis-queue-manager")) {
            click(queueManagerArtemisTab);
            click(queuelistArtemisTab);
        }

        searchQueue(nameQueue);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(editInContextMenu);
        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRouts);

        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < queueSetting[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, queueSetting[1].toString().split("\\|")[x].split("-")[0]);

            switch (queueSetting[1].toString().split("\\|")[x].split("-")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, queueSetting[1].toString().split("\\|")[x].split("-")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void deleteQueue(String nameQueue, String parametersDelete) {
        if (!url().contains("/artemis-queue-manager")) {
            click(queueManagerArtemisTab);
            click(queuelistArtemisTab);
        }

        searchQueue(nameQueue);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);

        switch (parametersDelete) {
            case "Без параметров-есть потребители":
            case "Без параметров-нет потребителей-автосоздание адреса":
            case "Без параметров-нет потребителей-НЕавтосоздание адреса":
                break;
            case "Удалить потребителей":
                click(deleteCustomersOfQueueCheckbox);
                break;
            case "Удалить адрес если эта очередь единственная-единственная":
            case "Удалить адрес если эта очередь единственная-НЕединственная":
                click(deleteAddressIfSoleQueueCheckbox);
                break;
            default:
                throw new AssertionError("Пропущены выбор параметров удаления");
        }


        click(deleteButton);
    }

    @Step("queue Should Not Exist {0} ")
    public void queueShouldNotExist(String queueName) {
        searchQueue(queueName);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step("address Should Not Exist {0} ")
    public void addressShouldNotExist(String addressName) {
        searchAddress(addressName);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step("virtual address Should Not Exist {0} ")
    public void virtualAddressShouldNotExist(String virtualAddressName) {
        searchVirtualAddress(virtualAddressName);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step("receiver Should Not Exist {0} ")
    public void receiverShouldNotExist(String receiverName) {
        searchReceiver(receiverName);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step("connector Should Not Exist {0} ")
    public void connectorShouldNotExist(String connectorName) {
        searchReceiver(connectorName);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step
    public void prepairForCreateQueueTest(String cook, String domainID, String sopsName, String queueName1, String queueName2) {
        guid = commonComponents.createIndividualName("guid");

        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + queueName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";

        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + queueName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";

        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};

        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
        sopsApi.createSopsAPI(cook, Base.baseUrl(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook, Base.baseUrl(), domainID);
    }

    @Step
    public void prepairForCreateQueueTest2(String domainName, String sopsName, String NameLocalQueue1, String artemisQueue1, String artemisQueue2, String artemisAddress, String NameLocalQueue3, String NameLocalQueue4, String addConfigurationName, String port, String userName, String userPassword) {
        String[] point1_1 = new String[]{"Вход|Локальная очередь|" + "QM" + "|" + NameLocalQueue1};
        String[] point1_2 = new String[]{"Выход|JMS|" + "topic:" + artemisAddress, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[][] pointAll_1 = {point1_1, point1_2};

        String[] point2_1 = new String[]{"Вход|JMS|" + artemisAddress + "::" + artemisQueue1, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[] point2_2 = new String[]{"Выход|Локальная очередь|" + "QM" + "|" + NameLocalQueue3};
        String[][] pointAll_2 = {point2_1, point2_2};

        String[] point3_1 = new String[]{"Вход|JMS|" + artemisAddress + "::" + artemisQueue2, "селект-в-параметрах|Фабрика соединений Менеджера очередей|" + addConfigurationName};
        String[] point3_2 = new String[]{"Выход|Локальная очередь|" + "QM" + "|" + NameLocalQueue4};
        String[][] pointAll_3 = {point3_1, point3_2};

        String[] scheme1 = {"Фабрика соединений Расширенного МО|" + addConfigurationName + "|" + userName + "|" + userPassword, "Сетевое соединение|fesb-test-0-1|" + port};

        sopsPage = new SOPSPage();
        sopsPage.createAdditionalConfiguration(domainName, scheme1);
        creationSOPSPage = new CreationSOPSPage("empty");
        creationSOPSPage.createSOPS(domainName, sopsName, pointAll_1);
        creationSOPSPage.createSOPS(domainName, sopsName, pointAll_2);
        creationSOPSPage.createSOPS(domainName, sopsName, pointAll_3);

        sopsPage.startDomain(domainName);
    }

    @Step
    public void prepairForDeleteQueueTest(String cook, String testName, String parametersDelete) {
        domainName = commonComponents.createIndividualName(testName);
        sopsName = commonComponents.createIndividualName(testName);
        queueName1 = commonComponents.createIndividualName(testName);
        queueName2 = commonComponents.createIndividualName(testName);
        queueAddress = commonComponents.createIndividualName(testName);
        guid = commonComponents.createIndividualName("guid");

        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + queueName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";

        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + queueName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";

        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};
        Object[] queue1;

        switch (parametersDelete) {
            case "Без параметров-нет потребителей-НЕавтосоздание адреса":
                Object[] address = {queueAddress + "|" + typeRoutsQueue.ANYCAST, "enableMetrics/false|defaultNonDestructive/true|defaultDelayBeforeDispatch/5"};
                queueManagerArtemisApi.createAddressArtemisAPI(cook, address);
                queue1 = new Object[]{queueName1 + "|" + false + "|" + queueAddress + "|" + typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
                queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue1);
                break;
            case "Без параметров-есть потребители":
            case "Удалить потребителей":
                String domainID = sopsApi.createDomainAPI(Base.baseUrl(), cook, domainName);
                String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
                sopsApi.createSopsAPI(cook, Base.baseUrl(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
                sopsApi.startDomainAPI(cook, Base.baseUrl(), domainID);
                break;
            case "Удалить адрес если эта очередь единственная-НЕединственная": {
                queue1 = new Object[]{queueName1 + "|" + false + "|" + queueAddress + "|" + typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
                Object[] queue2 = {queueName2 + "|" + false + "|" + queueAddress + "|" + typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
                queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue1);
                queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue2);
                break;
            }
            default: {
                queue1 = new Object[]{queueName1 + "|" + false + "|" + queueAddress + "|" + typeRoutsQueue.ANYCAST, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
                queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue1);
                break;
            }
        }
    }

    @Step
    public void checkAfterDelete(String parametersDelete) {
        switch (parametersDelete) {
            case "Без параметров-есть потребители":
                basePage.click(messagePage.closeWindowIcon);
                queueCheckAllParameters(queueName1, queueName1, "0", "1", "0", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
                queueCheckAllParameters(queueName2, queueName2, "0", "0", "0", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
                addressCheckAllParameters(queueName1, "ANYCAST", "1");
                addressCheckAllParameters(queueName2, "ANYCAST", "1");
                break;
            case "Удалить потребителей":
                queueShouldNotExist(queueName1);
                queueCheckAllParameters(queueName2, queueName2, "0", "0", "0", "0", QueueManagerArtemisPage.typeRoutsQueue.ANYCAST.toString());
                addressCheckAllParameters(queueName1, "ANYCAST", "0");
                addressCheckAllParameters(queueName2, "ANYCAST", "0");
                break;
            case "Удалить адрес если эта очередь единственная-единственная":
            case "Без параметров-нет потребителей-автосоздание адреса":
                queueShouldNotExist(queueName1);
                sleep(20000);
                addressShouldNotExist(queueAddress);
                break;
            case "Удалить адрес если эта очередь единственная-НЕединственная":
                queueShouldNotExist(queueName1);
                sleep(20000);
                addressCheckAllParameters(queueAddress, "ANYCAST", "1");
                break;
            case "Без параметров-нет потребителей-НЕавтосоздание адреса":
                queueShouldNotExist(queueName1);
                sleep(20000);
                addressCheckAllParameters(queueAddress, "ANYCAST", "0");
                break;
            default:
                throw new AssertionError("Пропущена проверка удаления");
        }
    }

    @Step
    public void createAddress(Object[] addressSetting) {
        String nameAddress = addressSetting[0].toString().split("\\|")[0];
        String typeRouts = addressSetting[0].toString().split("\\|")[1];

        if (!url().contains("/artemis-queue-manager/addresses")) {
            click(queueManagerArtemisTab);
            click(adresseslistArtemisTab);
        }

        click(addAddressButton);
        val(queueManagerPage.queueNameInput, nameAddress);
        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRouts);

        for (int x = 0; x < addressSetting[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, addressSetting[1].toString().split("\\|")[x].split("-")[0]);

            switch (addressSetting[1].toString().split("\\|")[x].split("-")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, addressSetting[1].toString().split("\\|")[x].split("-")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void addressCheckEditForm(Object[] addressSetting) {
        String nameAddress = addressSetting[0].toString().split("\\|")[0];
        String typeRout = addressSetting[0].toString().split("\\|")[1];
        String[] parameters = addressSetting[1].toString().split("\\|");

        click(queueManagerArtemisTab);
        if (!adresseslistArtemisTab.attr("class").contains("active"))
            click(adresseslistArtemisTab);
        searchAddress(nameAddress);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        compareStringAndElementValue(nameAddress, queueManagerPage.queueNameInput);
        compareStringAndElementText(typeRout, typeRoutValue);

        sout(Arrays.toString(parameters));
        Arrays.sort(parameters);
        sout(Arrays.toString(parameters));

        for (int x = 0; x < parameters.length; x++) {
            compareStringAndElementText(parameters[x].split("-")[0], nameParametersInputList.get(x).parent().sibling(0));
            if (parameters[x].split("-")[1].equals("вкл")) {
                valueParametersInputList.get(x).shouldHave(attribute("checked"));
            } else if (parameters[x].split("-")[1].equals("выкл")) {
                valueParametersInputList.get(x).shouldNotHave(attribute("checked"));
            } else {
                compareStringAndElementValue(parameters[x].split("-")[1], valueParametersInputList.get(x));
            }
        }
        click(closeWindowIcon);
    }

    @Step
    public void editAddress(Object[] addressSetting) {
        String nameAddress = addressSetting[0].toString().split("\\|")[0];

        if (!url().contains("/artemis-queue-manager/addresses")) {
            click(queueManagerArtemisTab);
            click(adresseslistArtemisTab);
        }

        searchAddress(nameAddress);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < addressSetting[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, addressSetting[1].toString().split("\\|")[x].split("-")[0]);

            switch (addressSetting[1].toString().split("\\|")[x].split("-")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, addressSetting[1].toString().split("\\|")[x].split("-")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void deleteAddress(String nameAddress) {
        if (!url().contains("/artemis-queue-manager")) {
            click(queueManagerArtemisTab);
            click(adresseslistArtemisTab);
        }
        searchAddress(nameAddress);
        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(deleteButton);
    }


    @Step
    public void createVirtualAddress(Object[] addressSetting) {
        String nameAddressVirtual = addressSetting[0].toString().split("\\|")[0];
        String addressName = addressSetting[0].toString().split("\\|")[1];
        String addressRedirectName = addressSetting[0].toString().split("\\|")[2];
        String filter = addressSetting[0].toString().split("\\|")[3];
        String exclusive = addressSetting[0].toString().split("\\|")[4];
        String typeRouts = addressSetting[0].toString().split("\\|")[5];

        if (!url().contains("/artemis-queue-manager/diverts")) {
            click(queueManagerArtemisTab);
            click(virtualAdresseslistArtemisTab);
        }

        click(addVirtualAddressButton);
        val(queueManagerPage.queueNameInput, nameAddressVirtual);
        click(addressActivateSelect);
        valAndSelectElement(addressInVirtAddrInput, addressName);
        click(addressRedirectActivateSelect);
        valAndSelectElement(addressRedirectInput, addressRedirectName);
        valAndSelectElement(filterInput, filter);

        switch (exclusive) {
            case "Да":
                if (!exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            case "Нет":
                if (exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            default:
                throw new AssertionError("Пропущен выбор состояния Exclusive");
        }

        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRouts);
        click(saveButton);
    }

    @Step
    public void editVirtualAddress(Object[] addressSettingOld, Object[] addressSettingNew) {
        String nameAddressVirtualOld = addressSettingOld[0].toString().split("\\|")[0];
        String nameAddressVirtualNew = addressSettingNew[0].toString().split("\\|")[0];
        String addressNameNew = addressSettingNew[0].toString().split("\\|")[1];
        String addressRedirectNameNew = addressSettingNew[0].toString().split("\\|")[2];
        String filterNew = addressSettingNew[0].toString().split("\\|")[3];
        String exclusiveNew = addressSettingNew[0].toString().split("\\|")[4];
        String typeRoutsNew = addressSettingNew[0].toString().split("\\|")[5];

        if (!url().contains("/artemis-queue-manager/diverts")) {
            click(queueManagerArtemisTab);
            click(virtualAdresseslistArtemisTab);
        }

        searchVirtualAddress(nameAddressVirtualOld);
        contextClick(rowAfterSearch);
        click(editInContextMenu);
        val(queueManagerPage.queueNameInput, nameAddressVirtualNew);
        click(addressActivateSelect);
        valAndSelectElement(addressInVirtAddrInput, addressNameNew);
        click(addressRedirectActivateSelect);
        valAndSelectElement(addressRedirectInput, addressRedirectNameNew);
        valAndSelectElement(filterInput, filterNew);

        switch (exclusiveNew) {
            case "Да":
                if (!exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            case "Нет":
                if (exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            default:
                throw new AssertionError("Пропущен выбор состояния Exclusive");
        }

        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRoutsNew);
        click(saveButton);
    }

    @Step
    public void cloneVirtualAddress(Object[] addressSetting, Object[] addressSettingNew) {
        String nameAddressVirtual = addressSetting[0].toString().split("\\|")[0];
        String addressName = addressSetting[0].toString().split("\\|")[1];
        String addressRedirectName = addressSetting[0].toString().split("\\|")[2];
        String filter = addressSetting[0].toString().split("\\|")[3];
        String exclusive = addressSetting[0].toString().split("\\|")[4];
        String typeRouts = addressSetting[0].toString().split("\\|")[5];

        String nameAddressVirtualNew = addressSettingNew[0].toString().split("\\|")[0];
        String addressNameNew = addressSettingNew[0].toString().split("\\|")[1];
        String addressRedirectNameNew = addressSettingNew[0].toString().split("\\|")[2];
        String filterNew = addressSettingNew[0].toString().split("\\|")[3];
        String exclusiveNew = addressSettingNew[0].toString().split("\\|")[4];
        String typeRoutsNew = addressSettingNew[0].toString().split("\\|")[5];

        if (!url().contains("/artemis-queue-manager/diverts")) {
            click(queueManagerArtemisTab);
            click(virtualAdresseslistArtemisTab);
        }

        searchVirtualAddress(nameAddressVirtual);
        contextClick(rowAfterSearch);
        click(cloneInContextMenu);
        compareStringAndElementValue(nameAddressVirtual + "_Клонированный", nameValue);
        compareStringAndElementText(addressName, addressValue);
        compareStringAndElementText(addressRedirectName, addressRedirectValue);
        compareStringAndElementValue(filter, filterValue);

        if (exclusive.equals("Да")) {
            exclusiveCheckBox.shouldHave(attribute("class", "class=\"ant-checkbox ant-checkbox-checked\""));
        } else if (exclusive.equals("Нет")) {
            exclusiveCheckBox.shouldNotHave(attribute("class", "class=\"ant-checkbox ant-checkbox-checked\""));
        }
        compareStringAndElementText(typeRouts, typeOfRoutingValue);

        val(queueManagerPage.queueNameInput, nameAddressVirtualNew);
        click(addressActivateSelect);
        valAndSelectElement(addressInVirtAddrInput, addressNameNew);
        click(addressRedirectActivateSelect);
        valAndSelectElement(addressRedirectInput, addressRedirectNameNew);
        valAndSelectElement(filterInput, filterNew);

        switch (exclusiveNew) {
            case "Да":
                if (!exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            case "Нет":
                if (exclusiveCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(exclusiveCheckBox);
                }
                break;
            default:
                throw new AssertionError("Пропущен выбор состояния Exclusive");
        }

        click(typeRoutsActivateSelect);
        valAndSelectElement(typeRoutInput, typeRoutsNew);
        click(saveButton);
    }

    @Step
    public void deleteVirtualAddress(Object[] addressSetting) {
        String nameAddressVirtual = addressSetting[0].toString().split("\\|")[0];

        if (!url().contains("/artemis-queue-manager/diverts")) {
            click(queueManagerArtemisTab);
            click(virtualAdresseslistArtemisTab);
        }

        searchVirtualAddress(nameAddressVirtual);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(deleteButton);
    }

    @Step
    public void virtualAddressCheckAllParameters(String virtualAddressName, String addressName, String addressRedirectName, String exclusive) {
        click(queueManagerArtemisTab);
        if (!virtualAdresseslistArtemisTab.attr("class").contains("active"))
            click(virtualAdresseslistArtemisTab);
        searchVirtualAddress(virtualAddressName);
//        autoUpdateOn();
        compareStringAndElementText(virtualAddressName, afterSearchNameVirtualAddress);
        compareStringAndElementText(addressName, afterSearchAddressVirtualAddress);
        compareStringAndElementText(addressRedirectName, afterSearchAddressOfRedirectVirtualAddress);
        compareStringAndElementText(exclusive, afterSearchExclusive);
//        autoUpdateOff();
    }

    @Step
    public void virtualAddressCheckEditForm(Object[] addressSetting) {
        String nameAddressVirtual = addressSetting[0].toString().split("\\|")[0];
        String addressName = addressSetting[0].toString().split("\\|")[1];
        String addressRedirectName = addressSetting[0].toString().split("\\|")[2];
        String filter = addressSetting[0].toString().split("\\|")[3];
        String exclusive = addressSetting[0].toString().split("\\|")[4];
        String typeRouts = addressSetting[0].toString().split("\\|")[5];

        click(queueManagerArtemisTab);
        if (!virtualAdresseslistArtemisTab.attr("class").contains("active"))
            click(virtualAdresseslistArtemisTab);
        searchVirtualAddress(nameAddressVirtual);
//        autoUpdateOff();
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        compareStringAndElementValue(nameAddressVirtual, nameValue);
        compareStringAndElementText(addressName, addressValue);
        compareStringAndElementText(addressRedirectName, addressRedirectValue);
        compareStringAndElementValue(filter, filterValue);

        if (exclusive.equals("Да")) {
            exclusiveCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        } else if (exclusive.equals("Нет")) {
            exclusiveCheckBox.shouldNotHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        }
        compareStringAndElementText(typeRouts, typeOfRoutingValue);
        click(closeWindowIcon);
    }

    @Step
    public void prepairForCreateVirtualAddressArtemisTest(String cook, String domainID, String queueName1, String addressName, String addressRedirectName1, String addressRedirectName2, String typeRoutsAddress) {

        sopsName = commonComponents.createIndividualName("CreateVirtualAddressArtemis");
        guid = commonComponents.createIndividualName("guid");
        String typeRoutsQueueAddr1 = null;
        String typeRoutsQueueRedirect1 = null;
        String typeRoutsQueueRedirect2 = null;

        switch (typeRoutsAddress) {
            case ("ANYCAST"):
            case ("PASS"):
            case ("STRIP"):
                typeRoutsQueueAddr1 = "MULTICAST";
                typeRoutsQueueRedirect1 = "ANYCAST";
                typeRoutsQueueRedirect2 = "MULTICAST";
                break;
            case ("MULTICAST"):
                typeRoutsQueueAddr1 = "ANYCAST";
                typeRoutsQueueRedirect1 = "MULTICAST";
                typeRoutsQueueRedirect2 = "ANYCAST";
                break;
            default:
                throw new AssertionError("Пропущен выбор типа маршрутизации очередей");
        }

        Object[] queueAddr1 = {addressName + "|" + false + "|" + addressName + "|" + typeRoutsQueueAddr1, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueAddr1);
        Object[] queueRedirect = {addressRedirectName1 + "|" + false + "|" + addressRedirectName1 + "|" + typeRoutsQueueRedirect1, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueRedirect);
        Object[] queueRedirect2 = {addressRedirectName2 + "|" + false + "|" + addressRedirectName1 + "|" + typeRoutsQueueRedirect2, "exclusive/exclusive/true|non-destructive/nonDestructive/true|delay-before-dispatch/delayBeforeDispatch/5"};
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queueRedirect2);

        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + queueName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";

        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + addressName + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";

        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};

        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook, domainID, sopsName);
        sopsApi.createSopsAPI(cook, Base.baseUrl(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook, Base.baseUrl(), domainID);
    }

    @Step
    public void checkWorkOfVirtualAddressArtemisTest(String cook, String domainID, String queueName1, String message1, String message2, String addressRedirectName1, String addressRedirectName2, String typeRoutsAddress) {
        switch (typeRoutsAddress) {
            case ("ANYCAST"):
            case ("PASS"):
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message1);
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message2);
                queueCheckAllParameters(addressRedirectName1, addressRedirectName1, "1", "0", "1", "0", "ANYCAST");
                queueArtemisShouldHaveSpecificMessage(addressRedirectName1, message2);
                queueCheckAllParameters(addressRedirectName2, addressRedirectName1, "0", "0", "0", "0", "MULTICAST");
                break;
            case ("MULTICAST"):
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message1);
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message2);
                queueCheckAllParameters(addressRedirectName1, addressRedirectName1, "1", "0", "1", "0", "MULTICAST");
                queueCheckAllParameters(addressRedirectName2, addressRedirectName1, "0", "0", "0", "0", "ANYCAST");
                break;
            case ("STRIP"):
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message1);
                queueManagerArtemisApi.sendMessageInQueueAPI(cook, Base.baseUrl(), domainID, queueName1, message2);
                queueCheckAllParameters(addressRedirectName1, addressRedirectName1, "1", "0", "1", "0", "ANYCAST");
                queueArtemisShouldHaveSpecificMessage(addressRedirectName1, message2);
                queueCheckAllParameters(addressRedirectName2, addressRedirectName1, "1", "0", "1", "0", "MULTICAST");
                break;
            default:
                throw new AssertionError("Пропущена проверка работы виртуального адреса Artemis");
        }

    }

    @Step("{0} should has specific message")
    public void queueArtemisShouldHaveSpecificMessage(String queueName, String message) {
        if (!url().endsWith("/artemis-queue-manager/queues")) {
            click(queueManagerArtemisTab);
            click(queuelistArtemisTab);
        }
        searchQueue(queueName);
        doubleClick(afterSearchQueueName);
        click(messagesTabInQueue);
        doubleClick(firstMessage);
        messageBody.shouldHave(text(message));
    }


    @Step
    public void createReceiver(Object[] addressSetting) {
        String name = addressSetting[0].toString().split("\\|")[0];
        String address = addressSetting[0].toString().split("\\|")[1];
        String port = addressSetting[0].toString().split("\\|")[2];

        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }

        click(addReseiverButton);
        val(queueManagerPage.queueNameInput, name);
        val(addressInVirtAddrInput, address);
        val(portReceiverInput, port);

        click(saveButton);
    }

    @Step
    public void recieverCheckEditForm(Object[] receiverSetting) {
        String nameReceiver = receiverSetting[0].toString().split("\\|")[0];
        String addressReceiver = receiverSetting[0].toString().split("\\|")[1];
        String portReceiver = receiverSetting[0].toString().split("\\|")[2];
        String protocolReceiver = receiverSetting[0].toString().split("\\|")[3];
        String[] parameters = null;
        if (receiverSetting.length > 1)
            parameters = receiverSetting[1].toString().split("\\|");

        click(queueManagerArtemisTab);
        if (!receiversArtemisTab.attr("class").contains("active"))
            click(receiversArtemisTab);
        searchReceiver(nameReceiver);
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        compareStringAndElementValue(nameReceiver, queueManagerPage.queueNameInput);
        compareStringAndElementValue(addressReceiver, addressReceiverValue);
        compareStringAndElementValue(portReceiver, portReceiverValue);
        compareStringAndElementText(protocolReceiver, protocolReceiverValue);

        if (parameters != null) {
            sout(Arrays.toString(parameters));
            Arrays.sort(parameters);
            sout(Arrays.toString(parameters));

            for (int x = 0; x < parameters.length; x++) {
                compareStringAndElementText(parameters[x].split("~")[0], nameParametersInputList.get(x).parent().sibling(0));
                if (parameters[x].split("~")[1].equals("true")) {
                    valueParametersInputList.get(x).shouldHave(attribute("checked"));
                } else if (parameters[x].split("~")[1].equals("false")) {
                    valueParametersInputList.get(x).shouldNotHave(attribute("checked"));
                } else {
                    compareStringAndElementValue(parameters[x].split("~")[1], valueParametersInputList.get(x));
                }
            }
        }
        click(closeWindowIcon);
    }

    @Step
    public void receiverCheckAllParameters(String virtualAddressName, String addressName, String addressRedirectName, String exclusive) {
        click(queueManagerArtemisTab);
        if (!virtualAdresseslistArtemisTab.attr("class").contains("active"))
            click(virtualAdresseslistArtemisTab);
        searchVirtualAddress(virtualAddressName);
//        autoUpdateOn();
        compareStringAndElementText(virtualAddressName, afterSearchNameVirtualAddress);
        compareStringAndElementText(addressName, afterSearchAddressVirtualAddress);
        compareStringAndElementText(addressRedirectName, afterSearchAddressOfRedirectVirtualAddress);
        compareStringAndElementText(exclusive, afterSearchExclusive);
//        autoUpdateOff();
    }

    @Step
    public void editReseiver(Object[] receiverOld, Object[] receiverNew) {
        String nameReceiverOld = receiverOld[0].toString().split("\\|")[0];
        String nameReceiverNew = receiverNew[0].toString().split("\\|")[0];
        String addressReceiverNew = receiverNew[0].toString().split("\\|")[1];
        String portReceiverNew = receiverNew[0].toString().split("\\|")[2];

        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }

        searchReceiver(nameReceiverOld);
        contextClick(rowAfterSearch);
        click(editInContextMenu);
        val(queueManagerPage.queueNameInput, nameReceiverNew);
        val(addressInVirtAddrInput, addressReceiverNew);
        val(portReceiverInput, portReceiverNew);
        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < receiverNew[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, receiverNew[1].toString().split("\\|")[x].split("~")[0]);

            switch (receiverNew[1].toString().split("\\|")[x].split("~")[1]) {
                case "true":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "false":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, receiverNew[1].toString().split("\\|")[x].split("~")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void stopReseiver(Object[] receiver) {
        String nameReceiver = receiver[0].toString().split("\\|")[0];
        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }
        searchReceiver(nameReceiver);
        contextClick(rowAfterSearch);
        click(stopInContextMenu);
    }

    @Step
    public void startReseiver(Object[] receiver) {
        String nameReceiver = receiver[0].toString().split("\\|")[0];
        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }
        searchReceiver(nameReceiver);
        contextClick(rowAfterSearch);
        click(startInContextMenu);
    }

    @Step
    public void cloneReseiver(Object[] receiverOld, Object[] receiverNew) {
        String nameReceiverOld = receiverOld[0].toString().split("\\|")[0];
        String addressReceiverOld = receiverOld[0].toString().split("\\|")[1];
        String portReceiverOld = receiverOld[0].toString().split("\\|")[2];
        String protocolReceiverOld = receiverOld[0].toString().split("\\|")[3];
        String[] parametersOld = receiverOld[1].toString().split("\\|");
        String nameReceiverNew = receiverNew[0].toString().split("\\|")[0];
        String addressReceiverNew = receiverNew[0].toString().split("\\|")[1];
        String portReceiverNew = receiverNew[0].toString().split("\\|")[2];
        String protocolReceiverNew = receiverNew[0].toString().split("\\|")[3];
        String[] parametersNew = receiverNew[1].toString().split("\\|");

        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }

        searchReceiver(nameReceiverOld);
        contextClick(rowAfterSearch);
        click(cloneInContextMenu);
        compareStringAndElementValue(nameReceiverOld + "_Клонированный", nameValue);
        compareStringAndElementValue(addressReceiverOld, addressReceiverValue);
//        compareStringAndElementValue(portReceiverOld, portReceiverValue);
        compareStringAndElementValue(Integer.toString(Integer.parseInt(portReceiverOld) + 1), portReceiverValue);
        compareStringAndElementText(protocolReceiverOld.toUpperCase(), protocolReceiverValue);

        for (int x = 0; x < parametersOld.length; x++) {
            compareStringAndElementText(parametersOld[x].split("~")[0], nameParametersInputList.get(x).parent().sibling(0));
            if (parametersOld[x].split("~")[1].equals("true")) {
                valueParametersInputList.get(x).shouldHave(attribute("checked"));
            } else if (parametersOld[x].split("~")[1].equals("false")) {
                valueParametersInputList.get(x).shouldNotHave(attribute("checked"));
            } else {
                compareStringAndElementValue(parametersOld[x].split("~")[1], valueParametersInputList.get(x));
            }
        }

        val(queueManagerPage.queueNameInput, nameReceiverNew);
        val(addressInVirtAddrInput, addressReceiverNew);
        val(portReceiverInput, portReceiverNew);
        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < receiverNew[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, receiverNew[1].toString().split("\\|")[x].split("~")[0]);

            switch (receiverNew[1].toString().split("\\|")[x].split("~")[1]) {
                case "true":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "false":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, receiverNew[1].toString().split("\\|")[x].split("~")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void deleteReseiver(Object[] receiverOld) {
        String nameReceiver = receiverOld[0].toString().split("\\|")[0];
        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(receiversArtemisTab);
        }
        searchReceiver(nameReceiver);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(deleteButton);
    }

    @Step
    public void createUserArtemis(Object[] user) {
        String name = user[0].toString().split("\\|")[0];
        String password = user[0].toString().split("\\|")[1];

        if (!url().contains("/artemis-queue-manager/security")) {
            click(queueManagerArtemisTab);
            click(securityArtemisTab);
            click(usersArtemisTab);
        }

        click(addUserButton);
        val(userNameInput, name);
        val(userPasswordInput, password);

        for (int x = 0; x < user[1].toString().split("\\|").length; x++) {
            click(rolesActivateSelect);
            valAndSelectElement(rolesInput, user[1].toString().split("\\|")[x]);
        }
        click(modalWindow);
        click(saveButton);
    }

    public void preparationForReceiver(String cook, String cook2, String[] receiver, String domainID, String sopsName, String localQueueArtemisName1, String localQueueArtemisName2, String[] addConfig, String[] user) {
        guid = commonComponents.createIndividualName("guid");
        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "1" + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";
        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "2" + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[{\"nameEn\":\"connectionFactory\",\"nameRu\":\"Фабрика соединений Расширенного МО\",\"type\":\"BEAN\",\"value\":\"#" + addConfig[2] + "\"}],\"uri\":\"" + localQueueArtemisName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";
        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};

        queueManagerArtemisApi.createReceiverArtemisAPI(cook, receiver);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user[0], user[1], user[2]);
        sopsApi.createAddConfigurationAPI(cook2, Base.baseUrl_2(), addConfig);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook2, Base.baseUrl_2(), domainID, sopsName);
        sopsApi.createSopsAPI(cook2, Base.baseUrl_2(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook2, Base.baseUrl_2(), domainID);
    }

    @Step
    public void createConnector(Object[] connectorSetting) {
        String name = connectorSetting[0].toString().split("\\|")[0];
        String protocol = connectorSetting[0].toString().split("\\|")[1];
        String address = connectorSetting[0].toString().split("\\|")[2];
        String port = connectorSetting[0].toString().split("\\|")[3];

        if (!url().contains("/artemis-queue-manager/transport/connectors")) {
            click(queueManagerArtemisTab);
            click(connectorsArtemisTab);
        }

        click(addConnectorButton);
        val(queueManagerPage.queueNameInput, name);
//        click(protocolConnectorActivate);
//        valAndSelectElement(protocolConnectorInput, protocol);
        val(addressInVirtAddrInput, address);
        val(portReceiverInput, port);

        if (connectorSetting.length > 1) {
            for (int x = 0; x < connectorSetting[1].toString().split("\\|").length; x++) {
                click(addButton2);
                valAndSelectElement(nameLastParameterInput, connectorSetting[1].toString().split("\\|")[x].split("~")[0]);

                switch (connectorSetting[1].toString().split("\\|")[x].split("~")[1]) {
                    case "вкл":
                        if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    case "выкл":
                        if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    default:
                        val(valueLastParameteInput, connectorSetting[1].toString().split("\\|")[x].split("~")[1]);
                }
            }
        }
        click(saveButton);
    }

    @Step
    public void connectorCheckAllParameters(String connectorName, String protocolConnector, String addressConnector, String portConnector) {
        click(queueManagerArtemisTab);
        if (!virtualAdresseslistArtemisTab.attr("class").contains("active"))
            click(connectorsArtemisTab);
        searchConnector(connectorName);
        compareStringAndElementText(connectorName, afterSearchNameConnector);
        compareStringAndElementText(protocolConnector, afterSearchProtocolConnector);
        compareStringAndElementText(addressConnector, afterSearchAddressConnector);
        compareStringAndElementText(portConnector, afterSearchPortConnector);
    }

    @Step
    public void connectorCheckEditForm(Object[] connectorSetting) {
        String connectorName = connectorSetting[0].toString().split("\\|")[0];
        String connectorProtocol = connectorSetting[0].toString().split("\\|")[1];
        String connectorAddress = connectorSetting[0].toString().split("\\|")[2];
        String connectorPort = connectorSetting[0].toString().split("\\|")[3];

        click(queueManagerArtemisTab);
        if (!connectorsArtemisTab.attr("class").contains("active"))
            click(connectorsArtemisTab);
        searchConnector(connectorName);
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        compareStringAndElementValue(connectorName, nameConnectorValue);
        compareStringAndElementText(connectorProtocol, protocolConnectorValue);
        compareStringAndElementValue(connectorAddress, addressConnectorValue);
        compareStringAndElementValue(connectorPort, portConnectorValue);
        click(closeWindowIcon);
    }

    @Step
    public void editConnector(String[] connectorOld, String[] connectorNew) {
        String nameConnectorOld = connectorOld[0].split("\\|")[0];
        String connectorNameNew = connectorNew[0].split("\\|")[0];
        String connectorProtocolNew = connectorNew[0].split("\\|")[1];
        String connectorAddressNew = connectorNew[0].split("\\|")[2];
        String connectorPortNew = connectorNew[0].split("\\|")[3];

        if (!url().contains("/artemis-queue-manager/transport/acceptors")) {
            click(queueManagerArtemisTab);
            click(connectorsArtemisTab);
        }

        searchConnector(nameConnectorOld);
        contextClick(rowAfterSearch);
        click(editInContextMenu);
        val(queueManagerPage.queueNameInput, connectorNameNew);
//        click(protocolConnectorActivate);
//        valAndSelectElement(protocolConnectorInput, connectorProtocolNew);
        val(addressInVirtAddrInput, connectorAddressNew);
        val(portReceiverInput, connectorPortNew);

        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < connectorNew[1].toString().split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, connectorNew[1].toString().split("\\|")[x].split("~")[0]);

            switch (connectorNew[1].toString().split("\\|")[x].split("~")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, connectorNew[1].toString().split("\\|")[x].split("~")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void cloneConnector(String[] connectorOld, String[] connectorNew) {
        String nameConnectorOld = connectorOld[0].split("\\|")[0];
        String connectorProtocolOld = connectorOld[0].split("\\|")[1];
        String connectorAddressOld = connectorOld[0].split("\\|")[2];
        String connectorPortOld = connectorOld[0].split("\\|")[3];
        String connectorNameNew = connectorNew[0].split("\\|")[0];
        String connectorProtocolNew = connectorNew[0].split("\\|")[1];
        String connectorAddressNew = connectorNew[0].split("\\|")[2];
        String connectorPortNew = connectorNew[0].split("\\|")[3];

        if (!url().contains("/artemis-queue-manager/transport/connectors")) {
            click(queueManagerArtemisTab);
            click(connectorsArtemisTab);
        }

        searchConnector(nameConnectorOld);
        contextClick(rowAfterSearch);
        click(cloneInContextMenu);

        compareStringAndElementValue(nameConnectorOld + "_Клонированный", nameConnectorValue);
        compareStringAndElementText(connectorProtocolOld, protocolConnectorValue);
        compareStringAndElementValue(connectorAddressOld, addressConnectorValue);
        compareStringAndElementValue(connectorPortOld, portConnectorValue);

        val(queueManagerPage.queueNameInput, connectorNameNew);
        click(protocolConnectorActivate);
        val(addressInVirtAddrInput, connectorAddressNew);
        val(portReceiverInput, connectorPortNew);

        click(deleteIcon);
        click(valueLastParameteInput.parent());

        for (int x = 0; x < connectorNew[1].split("\\|").length; x++) {
            click(addButton2);
            valAndSelectElement(nameLastParameterInput, connectorNew[1].split("\\|")[x].split("~")[0]);

            switch (connectorNew[1].split("\\|")[x].split("~")[1]) {
                case "вкл":
                    if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                case "выкл":
                    if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(valueLastParameteInput.parent());
                    }
                    break;
                default:
                    val(valueLastParameteInput, connectorNew[1].split("\\|")[x].split("~")[1]);
            }
        }
        click(saveButton);
    }

    @Step
    public void deleteConnector(String[] connector) {
        String nameConnector = connector[0].toString().split("\\|")[0];
        if (!url().contains("/artemis-queue-manager/transport/connectors")) {
            click(queueManagerArtemisTab);
            click(connectorsArtemisTab);
        }
        searchConnector(nameConnector);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(deleteButton);
    }

    public void preparationForConnector(String cook, String cook2, String[] receiver, String domainID, String sopsName, String localQueueArtemisName1, String localQueueArtemisName2, String[] user, String[] connector, String[] channel) {
        guid = commonComponents.createIndividualName("guid");
        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";
        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";
        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};
        String[] queue1 = {localQueueArtemisName1 + "|" + false + "|" + localQueueArtemisName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        String[] queue2 = {localQueueArtemisName2 + "|" + false + "|" + localQueueArtemisName2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};

        queueManagerArtemisApi.createReceiverArtemisAPI(cook, receiver);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user[0], user[1], user[2]);
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue2);

        if (connector != null)
            queueManagerArtemisApi.createConnectorArtemisAPI(cook2, Base.baseUrl_2(), connector);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue1);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue2);
        queueManagerArtemisApi.createChanneArtemisAPI(cook2, Base.baseUrl_2(), channel);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook2, Base.baseUrl_2(), domainID, sopsName);
        sopsApi.createSopsAPI(cook2, Base.baseUrl_2(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook2, Base.baseUrl_2(), domainID);
    }

    @Step
    public void createChannel(Object[] channelSetting) {
        String name = channelSetting[0].toString().split("\\|")[0];
        String queueName = channelSetting[0].toString().split("\\|")[1];
        String redirectionAddress = channelSetting[0].toString().split("\\|")[2];
        String connectors = channelSetting[0].toString().split("\\|")[3];

        if (!url().contains("/artemis-queue-manager/bridges")) {
            click(queueManagerArtemisTab);
            click(channelsArtemisTab);
        }

        click(addChannelButton);
        val(channelNameInput, name);
        click(queueNameActivate);
        valAndSelectElement(queueNameInput, queueName);
        val(redirectionAddressInput, redirectionAddress);
        click(connectorsActivate);
        valAndSelectElement(connectorsInput, connectors);


        if (channelSetting.length > 1) {

            click(deleteIcon);
            click(deleteIcon);

//            for (SelenideElement iconDeleteParameter : deleteIconList) {
//                click(iconDeleteParameter);
//            }

            for (int x = 0; x < channelSetting[1].toString().split("\\|").length; x++) {
                click(addButton2);
                valAndSelectElement(nameLastParameterInput, channelSetting[1].toString().split("\\|")[x].split("~")[0]);

                switch (channelSetting[1].toString().split("\\|")[x].split("~")[1]) {
                    case "true":
                        if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    case "false":
                        if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    default:
                        val(valueLastParameteInput, channelSetting[1].toString().split("\\|")[x].split("~")[1]);
                }
            }
        }
        click(saveButton);
    }

    @Step
    public void editChannel(Object[] channelSettingOld, Object[] channelSettingNew) {
        String nameOld = channelSettingOld[0].toString().split("\\|")[0];
        String name = channelSettingNew[0].toString().split("\\|")[0];
        String queueName = channelSettingNew[0].toString().split("\\|")[1];
        String redirectionAddress = channelSettingNew[0].toString().split("\\|")[2];
        String connectors = channelSettingNew[0].toString().split("\\|")[3];

        if (!url().contains("/artemis-queue-manager/bridges")) {
            click(queueManagerArtemisTab);
            click(channelsArtemisTab);
        }
        searchChannel(nameOld);
        contextClick(rowAfterSearch);
        click(editInContextMenu);

        val(channelNameInput, name);
        click(queueNameActivate);
        valAndSelectElement(queueNameInput, queueName);
        val(redirectionAddressInput, redirectionAddress);
        click(connectorsCleanField);
        click(connectorsActivate);
        valAndSelectElement(connectorsInput, connectors);
        click(modalWindow);

        if (channelSettingNew.length > 1) {
            click(deleteIcon);
            click(deleteIcon);
            for (int x = 0; x < channelSettingNew[1].toString().split("\\|").length; x++) {
                click(addButton2);
                valAndSelectElement(nameLastParameterInput, channelSettingNew[1].toString().split("\\|")[x].split("~")[0]);

                switch (channelSettingNew[1].toString().split("\\|")[x].split("~")[1]) {
                    case "true":
                        if (!valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    case "false":
                        if (valueLastParameteInput.parent().attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(valueLastParameteInput.parent());
                        }
                        break;
                    default:
                        val(valueLastParameteInput, channelSettingNew[1].toString().split("\\|")[x].split("~")[1]);
                }
            }
        }
        click(saveButton);
    }

    @Step
    public void deleteChannel(Object[] channelSetting) {
        String name = channelSetting[0].toString().split("\\|")[0];

        if (!url().contains("/artemis-queue-manager/bridges")) {
            click(queueManagerArtemisTab);
            click(channelsArtemisTab);
        }
        searchChannel(name);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(confirmationDeleteButton);
    }

    @Step("address Should Not Exist {0} ")
    public void channelShouldNotExist(String channel) {
        searchChannel(channel);
        sleep(3000);
        noDataInTable.waitUntil(visible, 5000);
    }

    @Step
    public void channelCheckAllParameters(String channelName, String localQueueName, String redirectAddress, String connectors, String sent, String confirmed) {
        click(queueManagerArtemisTab);
        if (!channelsArtemisTab.attr("class").contains("active"))
            click(channelsArtemisTab);
        searchChannel(channelName);
        compareStringAndElementText(channelName, afterSearchNameChannel);
        compareStringAndElementText(localQueueName, afterSearchNameQueue);
        compareStringAndElementText(redirectAddress, afterSearchRedirectAddress);
        compareStringAndElementText(connectors, afterSearchConnectors);
        compareStringAndElementText(sent, afterSearchSent);
        compareStringAndElementText(confirmed, afterSearchConfirmed);
    }

    public void preparationForCreateChannel(String cook, String cook2, String[] receiver, String domainID, String sopsName, String localQueueArtemisName1, String localQueueArtemisName2, String[] user, String[] connector) {
        guid = commonComponents.createIndividualName("guid");
        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";
        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";
        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};
        String[] queue1 = {localQueueArtemisName1 + "|" + false + "|" + localQueueArtemisName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        String[] queue2 = {localQueueArtemisName2 + "|" + false + "|" + localQueueArtemisName2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};

        queueManagerArtemisApi.createReceiverArtemisAPI(cook, receiver);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user[0], user[1], user[2]);
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue2);

        if (connector != null)
            queueManagerArtemisApi.createConnectorArtemisAPI(cook2, Base.baseUrl_2(), connector);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue1);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue2);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook2, Base.baseUrl_2(), domainID, sopsName);
        sopsApi.createSopsAPI(cook2, Base.baseUrl_2(), domainID, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook2, Base.baseUrl_2(), domainID);
    }

    public void preparationForEditChannel(String cook, String cook2, String[] receiver, String[] receiverNew, String domainID1, String domainID2, String sopsName, String sopsName2, String localQueueArtemisName1, String localQueueArtemisName2, String localQueueArtemisName3, String localQueueArtemisName4, String[] user, String[] user2, String[] connector, String[] connectorNew, String[] channel) {
        guid = commonComponents.createIndividualName("guid");
        String pointIn1 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName1 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";
        String pointOut1 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName2 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";
        String settingDomain = "],\"revision\":null,\"transacted\":false,\"trace\":false,\"startupOrder\":null,\"autoStartup\":true,\"streamCache\":false,\"processedQty\":0,\"lastProcessed\":null,\"firstProcessed\":null,\"errorHandlerId\":null,\"exchangesInflight\":0,\"minProcessingTime\":0,\"meanProcessingTime\":0,\"maxProcessingTime\":0,\"lastModified\":null,\"oldId\":\"0d0683fb-3a0e-4d12-8cc4-25aabb926705\",\"entityXMLTag\":\"route\",\"rate\":0,\"domain\":{\"guid\":\"domain-0f5d814b-20f0-4f55-80da-b483c0b5377b\"}},\"comment\":\"\"}";
        String[] allPointsIn = {pointIn1};
        String[] allPointsOut = {pointOut1};
        String[] queue1 = {localQueueArtemisName1 + "|" + false + "|" + localQueueArtemisName1 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        String[] queue2 = {localQueueArtemisName2 + "|" + false + "|" + localQueueArtemisName2 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        String[] queue3 = {localQueueArtemisName3 + "|" + false + "|" + localQueueArtemisName3 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};
        String[] queue4 = {localQueueArtemisName4 + "|" + false + "|" + localQueueArtemisName4 + "|" + QueueManagerArtemisPage.typeRoutsQueue.ANYCAST};

        queueManagerArtemisApi.createReceiverArtemisAPI(cook, receiver);
        queueManagerArtemisApi.createReceiverArtemisAPI(cook, receiverNew);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user[0], user[1], user[2]);
        queueManagerArtemisApi.createUserArtemisAPI(cook, Base.baseUrl(), user2[0], user2[1], user2[2]);
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue2);
        queueManagerArtemisApi.createQueueArtemisAPI(cook, Base.baseUrl(), queue4);

        if (connector != null)
            queueManagerArtemisApi.createConnectorArtemisAPI(cook2, Base.baseUrl_2(), connector);
        if (connectorNew != null)
            queueManagerArtemisApi.createConnectorArtemisAPI(cook2, Base.baseUrl_2(), connectorNew);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue1);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue2);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue3);
        queueManagerArtemisApi.createQueueArtemisAPI(cook2, Base.baseUrl_2(), queue4);
        String SopsIDForStopTest = sopsApi.generateSopsIdAPI(cook2, Base.baseUrl_2(), domainID1, sopsName);
        sopsApi.createSopsAPI(cook2, Base.baseUrl_2(), domainID1, SopsIDForStopTest, sopsName, allPointsIn, allPointsOut, settingDomain);
        sopsApi.startDomainAPI(cook2, Base.baseUrl_2(), domainID1);

        queueManagerArtemisApi.createChanneArtemisAPI(cook2, Base.baseUrl_2(), channel);

        guid = commonComponents.createIndividualName("guid");
        String pointIn2 = "{\"componentName\":\"InSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName3 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Менеджеры очередей\"}";
        String pointOut2 = "{\"componentName\":\"OutSJMS2QmeEndpoint\",\"guid\":\"" + guid + "\",\"customName\":\"Локальная очередь 2\",\"properties\":[],\"uri\":\"" + localQueueArtemisName4 + "\",\"parentGuid\":null,\"parentComponentName\":null,\"name\":\"Локальная очередь 2\",\"icon\":\"fa fa-envelope\",\"sla\":{},\"group\":\"Точки выхода\"}";
        String[] allPointsIn2 = {pointIn2};
        String[] allPointsOut2 = {pointOut2};
        String SopsIDForStopTest2 = sopsApi.generateSopsIdAPI(cook2, Base.baseUrl_2(), domainID2, sopsName2);
        sopsApi.createSopsAPI(cook2, Base.baseUrl_2(), domainID2, SopsIDForStopTest2, sopsName2, allPointsIn2, allPointsOut2, settingDomain);
        sopsApi.startDomainAPI(cook2, Base.baseUrl_2(), domainID2);
    }

    @Step("Check setting of configuration Artemis {0}")
    public void checkSettingConfigurationArtemis(String hard, String ram) {
        click(queueManagerArtemisTab);
        if (!settingOfConfigurationArtemisTab.attr("class").contains("active"))
            click(settingOfConfigurationArtemisTab);
        compareStringAndElementValue(hard, hardInput);
        compareStringAndElementValue(ram, ramInput);
    }
}
