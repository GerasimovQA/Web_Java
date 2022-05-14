package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.remote.SessionId;
import utils.ConfigProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ru.factorts.page.CommonComponents.ContextMenuElementXpath;


public class QueueManagerMultyPage extends BasePage {
    private static String typeQueue = "//div[@role=\"option\"][text()='%s']";
    static String specificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']";
    static String queuesOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Очереди\"]";
    static String topicsOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Разделы\"]";
    static String subscribersOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Подписчики\"]";
    static String receiversOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Приёмники\"]";
    static String channelsOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Каналы\"]";
    static String eventInterseptionOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Перехват событий\"]";
    static String deliveryPolicyOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Политика доставки\"]";
    static String managerSettingOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Настройки менеджера\"]";
//    static String queuesOfSpecificManagerButton = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/following-sibling::li[@title=\"Очереди\"]";

    BasePage basePage = new BasePage();
    private SpecificQueuePage specificQueuePage;
    private SpecificMessagePage specificMessagePage;
    CommonComponents commonComponents = new CommonComponents();
    MessagePage messagePage = new MessagePage();
    TransactionMonitorPage transactionMonitorPage = new TransactionMonitorPage();
    LoginPage loginPage = new LoginPage();
    Base base = new Base();

    private String DownloadedFileName = "";
    private String valueNameTopicInTable = "";
    private String valueDepthQueueInTable = "";
    private String valueCustomersTopicInTable = "";
    private String valueSentTopicInTable = "";
    private String valueTakenTopicInTable = "";
    private String valueTypeTopicInTable = "";
    private String valueCanalTopicInTable = "";
    private String valueRedirectionsTopicInTable = "";
    private String valueMinTopicInTable = "";
    private String valueMaxTopicInTable = "";
    private String valueAllTopicInTable = "";
    String exportedMQ = null;

    //Tabs
    public SelenideElement queueManagerPage = $x(".//a[text()='Менеджер очередей']/.."), queuesList = $x(".//a[@href=\"#/queue-manager/queues\"]"), downloadedFileText = $x(".//pre"), downloadedArchiveText = $x(".//td/a"), selectQueueForMovedMessage = $x(".//div[text()='Переместить выбранные сообщения в очередь']/../following-sibling::div//div[text()='Выбрать...']"), selectQueueForCopyedMessage = $x(".//div[text()='Копировать выбранные сообщения в очередь']/../following-sibling::div//div[text()='Выбрать...']"), selectOpinionQueue = $x(".//div[text()='Переместить выбранные сообщения в очередь']/../following-sibling::div//select[@data-placeholder=\"Выберите из списка...\"]"), queueNameForMovingOneMessagSelect = $x(".//div[text()='Переместить сообщение в очередь']/../following-sibling::div"), queueNameForMovingOneMessageInput = $x(".//div[text()='Переместить сообщение в очередь']/../following-sibling::div//input"), queueNameForMovingSeveralMessagSelect = $x(".//div[text()='Переместить выбранные сообщения в очередь']/../following-sibling::div"), queueNameForMovingSeveralMessageInput = $x(".//div[text()='Переместить выбранные сообщения в очередь']/../following-sibling::div//input"), queueNameForCopyedOneMessageSelect = $x(".//div[text()='Копировать сообщение в очередь']/../following-sibling::div"), queueNameForCopyedOneMessageInput = $x(".//div[text()='Копировать сообщение в очередь']/../following-sibling::div//input"), queueNameForCopyedSeveralMessageSelect = $x(".//div[text()='Копировать выбранные сообщения в очередь']/../following-sibling::div"), queueNameForCopyedSeveralMessageInput = $x(".//div[text()='Копировать выбранные сообщения в очередь']/../following-sibling::div//input"), buttonMove = $x(".//span[text()='Переместить']/.."), buttonCopy = $x(".//span[text()='Копировать']/.."),

    //Create Manager Form
    managerNameInput = $x(".//div[text()='Создание менеджера очередей']/../..//label[text()='Имя']/../following-sibling::div//input"),
            runManagerButton = $x(".//span[text()='Запустить']/.."),
            stopManagerButton = $x(".//span[text()='Остановить']/.."),
            deleteManagerButton = $x(".//span[text()='Удалить']/.."),

    //QueueForm
    addQueueButton = $x(".//span[text()= 'Добавить очередь']/.."), queueNameInput = $x(".//input[@name=\"name\"]"), queueNameInputForVirtualQueue = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            redirectionToQueuesSelect = $x(".//label[text()='Перенаправления в очереди']/../following-sibling::div//div"),
            redirectionToQueuesInput = $x(".//label[text()='Перенаправления в очереди']/../following-sibling::div//input"),
            redirectionToTopicsSelect = $x(".//label[text()='Перенаправления в разделы']/../following-sibling::div//div"),
            redirectionToTopicsInput = $x(".//label[text()='Перенаправления в разделы']/../following-sibling::div//input"),
            saveQueueButton = $$x(".//div[text()='Добавление очереди']/../..//span[text()='Сохранить']/..").find(visible), saveEditedQueueButton = $$x(".//div[text()='Редактирование очереди']/../..//span[text()='Сохранить']/..").find(visible), queueTypeSelect = $x(".//label[text()='Тип']/../following-sibling::div"), queueTypeInput = $x(".//label[text()='Тип']/../following-sibling::div//input"),
            nameQueue = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            getDeliveryQueueSelect = $x(".//div[label[text()='Очередь поставщика']]/div/div/span/div[@class=\"Select-input\"]"), destinationQueueSelect = $x(".//label[text()='Очередь назначения']/../following-sibling::div"), destinationQueueInput = $x(".//label[text()='Очередь назначения']/../following-sibling::div//input"), getDestinationQueueSelect = $x(".//div[label[text()='Очередь назначения']]/div/div/span/div[@class=\"Select-input\"]"), deliveryQueueText = $x(".//div[label[text()='Очередь поставщика']]//span[@role=\"option\"]"), destinationQueueText = $x(".//div[label[text()='Очередь назначения']]//span[@role=\"option\"]"),

    //Segregation queue
    fragmentSize = $x(".//label[text()='Размер фрагментов (в байтах)']/../following-sibling::div//input"),

    //TABLE
    table = $x(".//table[thead[tr[th[text()='Имя']]]]"),
            tableHeaderName = table.$x(".//tr/th[text()='Имя']"),
            searchNameQueueIcon = $x(".//span[text()='Имя']/../../../..//span[@role=\"button\"]"),
            activateSortByNameQueueIcon = $x(".//span[text()='Имя']/..//span[@aria-label=\"caret-up\"]"),
            searchNameCanalIcon = $x(".//span[text()='Имя']/../../../..//span[@role=\"button\"]"),
            searchNameQueueInput = $x(".//input[@placeholder=\"Найти Имя\"]"), searchNameCanalInput = $x(".//input[@placeholder=\"Найти Имя\"]"),
            searchNameButton = $$x(".//span[text()='Найти']/..").find(visible),
            afterSearchQueueRow = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]"),
            afterSearchQueueName = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td//a"),
            afterSearchQueueDepth = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][1]"),
            afterSearchQueueNumberOfConsumers = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][2]"),
            afterSearchQueueSentToQueue = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][3]"),
            afterSearchQueueTakenFromQueue = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][4]"),
            afterSearchQueueType = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][5]"),
            afterSearchQueueCanal = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][6]"),
            afterSearchQueueRedirections = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell forwardTo\"]"),
            afterSearchQueueMinSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][7]"),
            afterSearchQueueMaxSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][8]"),
            afterSearchQueueAllSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][9]"),

    afterSearchTopicRow = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]"),
            afterSearchTopicName = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td//a"),
            afterSearchTopicNumberOfConsumers = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][2]"),
            afterSearchTopicSent = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][3]"),
            afterSearchTopicTaken = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][4]"),
            afterSearchTopicType = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][5]"),
            afterSearchTopicCanal = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][6]"),
            afterSearchTopicRedirections = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell forwardTo\"]"),
            afterSearchTopicMinSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][7]"),
            afterSearchTopicMaxSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][8]"),
            afterSearchTopicAllSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][9]"),

    postedMessagesAfterSearch = $x(".//tbody[@class=\"ant-table-tbody\"]/tr/td[4]"),
            refreshSearchButton = $x(".//span[@class=\"anticon anticon-reload\"]/.."),
            noDataText = $x(".//div[text()='Нет данных']"),

    //Context queue menu
    deleteQueue = $$x(".//div[text()=' Удалить']").find(visible),
            contextSendMessage = $x(".//div[text()=' Отправить сообщение']"),
            sendSavedMessage = $x(".//div[text()=' Отправить сохраненное сообщение']"),
            clearQueue = $$x(".//div[text()=' Очистить']").find(visible),
    //            contentQueue = $$x(String.format(ContextMenuElementXpath,"Содержимое")).find(visible),
    contentQueue = $$x(String.format(ContextMenuElementXpath, "Содержимое")).find(visible),
            editQueue = $x(".//div[text()=' Редактировать']"),

    //Confinrmation menu
    confirmationDeleteButton = $$x(".//div[text()='Подтверждение удаления']/../..//div//span[text()='Удалить']/..").find(visible), clearButton = $$x(".//span[text()='Очистить']/..").find(visible), textInConfirmClearQueue = $x(".//p[contains(text(),'Вы уверены, что хотите очистить очередь')]"), confirmationClearMenuHeader = $x(".//h4[text()='Подтверждение удаления']"), headerInEditForm = $x(".//div[text()='Редактирование очереди']"), headerInClearForm = $x(".//div[text()='Подтверждение очистки']"),

    //    Options
    autoUpdatePageSOPSEmptyCheckBox = $x(".//span[text()=\"Обновление каждые\"]/../span"), autoUpdatePageListQueueCheckBox = $x(".//span[@class=\"ant-checkbox\"]"),

    refreshConfigAfterChangeButton = $x(".//span[text()=\"Перезапустить\"]/.."), updateCheckBox = $x(".//span/label[contains(text(), \" Обновление каждые\")]/input"),

    updateTimeIntervalInput = $x(".//span[label[contains(text(), \" Обновление каждые\")]]/input"), //    Info about queue
            returnInQueueListButton = $x(".//*[@data-icon=\"arrow-left\"]"),
            openQueueListIcon = $x(".//span[text()='Очередь:']"),
            nameChoosenQueueList = $x(".//form//span[@title]"),
            inputChoosenQueueList = $x(".//span[@title=\"Очередь:\"]/..//input"),
            sendMessageButton = $x(".//button//span[text()='Отправить сообщение']/.."), nameQueueInSendMessagePage = $x(".//label[text()='Адресат']/../following-sibling::div/div//span[@class=\"ant-select-selection-item\"]/div/span[1]"), typeQueueInSendMessagePage = $x(".//label[text()='Адресат']/../following-sibling::div/div//span[@class=\"ant-select-selection-item\"]/div/span[2]");


    ElementsCollection tableRows = table.$$x("/tbody/tr"), outerQueuesList = $$x(".//div[@class='Select-menu-outer']/div/div"),
            messageID = $$x(".//a[@title]"),
            afterSearchTableRows = $$x(".//table//tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]"), rowsMessagesInTable = $$x(".//div[@class=\"ant-table-content\"]//table/tbody//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]"),
            resultsQueueListForChoose = $$x("//div[@class=\"ant-select-item ant-select-item-option ant-select-item-option-active\"]/div[text()]");


    //=============================================================Topics====================================================================================
    String afterSearchTopicNameS = ".//a[text()=\"%s\"]/../../td[1]";
    String afterSearchTopicSendS = ".//a[text()=\"%s\"]/../../td[3]";


    SelenideElement topics = $x(".//div/a[text()='Разделы' and @href='#/topics/topic']"), subscribers = $x(".//div/a[text()='Подписчики' and @href='#/topics/subscribers']"),
            addTopicButton = $x(".//span[text()='Добавить раздел']/.."),
            topicsList = $x(".//a[@href=\"#/queue-manager/topics/topic\"]"), //Topicform
            topicNameInput = $x(".//input[@name='name']"),
            confirmationAddTopicButton = $$x(".//button[@type='submit' and contains(text(), \"Сохранить\")]").find(visible),
    //    //TABLE
//    SelenideElement afterSearchTopicRow = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]"),
//            afterSearchTopicName = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td//a"),
//            afterSearchTopicDepth = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][1]"),
//            afterSearchTopicNumberOfConsumers = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][2]"),
//            afterSearchTopicSent = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][3]"),
//            afterSearchTopicTaken = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][4]"),
//            afterSearchTopicType = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][5]"),
//            afterSearchTopicCanal = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][6]"),
//            afterSearchTopicMinSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][7]"),
//            afterSearchTopicMaxSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][8]"),
//            afterSearchTopicAllSize = $x(".//table/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not (@style)]/td[@class=\"ant-table-cell\"][9]"),
//    afterSearchTopicRow =    $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][@data-row-key=\"%s\"]"),

    afterSearchSentToTopic = afterSearchTopicRow.$x(".//td[3]"),

    //Context Menu
    deleteTopic =

            $$x(".//div[text()=' Удалить']").

                    find(visible);

    //=============================================================QM====================================================================================

    SelenideElement configuration = $x(".//a[text()='Настройки менеджера']");
    SelenideElement statusOfShedullerCheckBox = $x(".//span[text()='Включить поддержку планировщика']/preceding-sibling::span");
    SelenideElement canalsList = $x(".//a[text()=\"Каналы\"]");
    SelenideElement groupsAndusersTab = $x(".//div[text()='Локальные пользователи и группы']");
    SelenideElement addUserButton = $x(".//span[text()='Добавить пользователя']/..");
    SelenideElement userNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input");
    SelenideElement userPasswordInput = $x(".//label[text()='Пароль']/../following-sibling::div//input");
    SelenideElement userGroupsActivate = $x(".//label[text()='Группы']/../following-sibling::div");
    SelenideElement userGroupsInput = $x(".//label[text()='Группы']/../following-sibling::div//input");
    SelenideElement userNameInTable = $x(".//span[text()='Добавить пользователя']/../../../../following-sibling::div//td[@class=\"ant-table-cell\"][1]");
    SelenideElement userGroupInTable = $x(".//span[text()='Добавить пользователя']/../../../../following-sibling::div//td[@class=\"ant-table-cell\"][2]");


    // MQ Settings
    SelenideElement jvmHeapAcceptableUsage = $x(".//label[text()=\"Допустимое использование JVM Heap (в процентах)\"]/../following-sibling::div//input"), brokerNameInput = $x(".//input[@name=\"brokerName\"]"), saveQmButton = $x(".//span[text()='Сохранить']/..");

    //=============================================================Connectors====================================================================================

    SelenideElement addConnectorButton = $x(".//span[text()='Добавить приёмник']/..");
    SelenideElement connectorNameInput = $x(".//label[text()=\"Имя\"]/../following-sibling::div//input");
    SelenideElement connectorProtocolActivate = $x(".//label[text()='Протокол']/../following-sibling::div");
    SelenideElement connectorProtocolInput = $x(".//label[text()='Протокол']/../following-sibling::div//input");
    SelenideElement connectorIpInput = $x(".//label[text()='Адрес']/../following-sibling::div//input");
    SelenideElement connectorPortInput = $x(".//label[text()='Порт']/../following-sibling::div//input");
    SelenideElement connectorParametersInput = $x(".//label[text()='Параметры']/../following-sibling::div//input");
    ElementsCollection connectorParametersNameActivate = $$x(".//div[text()='Параметры']/following-sibling::div//div[@name=\"name\"]");
    ElementsCollection connectorParametersNameInput = $$x(".//div[text()='Параметры']/following-sibling::div//div[@name=\"name\"]//input");
    ElementsCollection connectorParametersValueInput = $$x(".//div[text()='Параметры']/following-sibling::div//input[@name=\"value\"]");

    SelenideElement afterSearchConnectorExpandButton = $x(".//button[@aria-label=\"Развернуть строку\"]");
    SelenideElement searchConnectorParametrIcon = $x("//span[text()='Название параметра']/../../../..//span[@role=\"button\"]");
    SelenideElement searchParameterNameInput = $x(".//input[@placeholder=\"Найти Название параметра\"]");
    SelenideElement afterSearchConnectorParameterName = $x(".//span[text()='Название параметра']/ancestor::thead/following-sibling::tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[1]");
    SelenideElement afterSearchConnectorParameterValue = $x(".//span[text()='Название параметра']/ancestor::thead/following-sibling::tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]");
    SelenideElement afterSearchConnectorName = $x(".//td[@class=\"ant-table-cell\"][1]");
    SelenideElement afterSearchConnectorProtocol = $x(".//td[@class=\"ant-table-cell\"][2]");
    SelenideElement afterSearchConnectorAdress = $x(".//td[@class=\"ant-table-cell\"][3]");
    SelenideElement afterSearchConnectorPort = $x(".//td[@class=\"ant-table-cell\"][4]");
    SelenideElement connectorConnectionInTable = $x(".//span[text()='Подключение']/ancestor::thead/following-sibling::tbody/tr[@data-row-key]/td[1]");
    SelenideElement connectorClientInTable = $x(".//span[text()='Подключение']/ancestor::thead/following-sibling::tbody/tr[@data-row-key]/td[2]");
    SelenideElement connectorTransactionInTable = $x(".//span[text()='Подключение']/ancestor::thead/following-sibling::tbody/tr[@data-row-key]/td[3]");
    SelenideElement connectorUserInTable = $x(".//span[text()='Подключение']/ancestor::thead/following-sibling::tbody/tr[@data-row-key]/td[4]");

    //=============================================================Canals====================================================================================

    SelenideElement addCanalButton = $x(".//span[text()=\"Добавить канал\"]/.."), canalNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input"), addHostButton = $x(".//th//span[@aria-label=\"plus\"]"), protocolList = $x(".//div[@class=\"Select has-value is-clearable Select--single\"]"), protocolListSelect = $x(".//select/option"),

    staticLinksForQueueSelect = $x(".//label[text()=\"Статические связи для очередей\"]/../..//span[text()='Выбрать статическую связь']/.."),
            clearStaticLinksForQueueIcon = $x(".//label[text()=\"Статические связи для очередей\"]/../..//*[@data-icon=\"close-circle\"]"),
            staticLinksForQueueInput = $x(".//label[text()=\"Статические связи для очередей\"]/../..//span[text()='Выбрать статическую связь']/..//input"),
            staticLinksForTopicSelect = $x(".//label[text()=\"Статические связи для разделов\"]/../..//span[text()='Выбрать статическую связь']/.."),
            clearStaticLinksForTopicIcon = $x(".//label[text()=\"Статические связи для разделов\"]/../..//*[@data-icon=\"close-circle\"]"),
            staticLinksForTopicInput = $x(".//label[text()=\"Статические связи для разделов\"]/../..//span[text()='Выбрать статическую связь']/..//input"),
            methodConnectionActivate = $x(".//label[text()='Метод подключения']/../following-sibling::div"), additionalParaametersOfConnection = $x(".//div[text()=\"Дополнительные параметры подключения\"]/following-sibling::div"),
            methodConnectionInput = $x(".//label[text()='Метод подключения']/../following-sibling::div//input"),
            userNameForautorisationInput = $x(".//label[text()='Имя пользователя для авторизации']/../following-sibling::div//input"),
            userPasswordForautorisationInput = $x(".//label[text()='Пароль для авторизации']/../following-sibling::div//input"),
            increaseTheWaitTimeCanalCheckBox = $x(".//span[text()='Увеличивать тайм-аут при ошибках во время переподключения']/preceding-sibling::span"),
            dynamicModeOnlyCanalCheckBox = $x(".//span[text()='Только динамический режим']/preceding-sibling::span"), ReduceTheNetworkPriorityCanalCheckBox = $x(".//span[text()='Уменьшить сетевой приоритет получателя']/preceding-sibling::span"),
            recipientsWorkAsOneCanalCheckBox = $x(".//span[text()='Получатели, подписанные к одному назначению, работают как один']/preceding-sibling::span"),
            duplexCanalCheckBox = $x(".//span[text()='Соединение будет использоваться для отправки и получения сообщений']/preceding-sibling::span"), duplicateSubscriptionsWillBeHiddenCanalCheckBox = $x(".//span[text()='Дублирующие подписки в сети будут скрываться']/preceding-sibling::span"), sendSystemMessagesCanalCheckBox = $x(".//span[text()='Передавать системные сообщения для временных очередей']/preceding-sibling::span"), sendNonpersistentMessagesUsingRequestReplyCanalCheckBox = $x(".//span[text()='Отправлять нестойкие сообщения с помощью request/reply.']/preceding-sibling::span"),
            turnOffAutomaticConsumerQueuingCanalCheckBox = $x(".//span[text()='Отключить автоматическое определение потребителей']/preceding-sibling::span"),
            saveCanalButton = $$x(".//span[text()='Сохранить']").find(visible),

    editCanalLink = $x(".//div[text()=' Редактировать']"), startCanalLink = $x(".//div[text()=' Запустить']"), stopCanalLink = $x(".//div[text()=' Остановить']"), deleteCanalLink = $x(".//div[text()=' Удалить']");

    SelenideElement executeTab = $x(".//div[text()='Выполнить']");
    SelenideElement executeButton = $x(".//span[text()='Выполнить (Ctrl+Enter)']/ancestor::button");
    SelenideElement successNotification = $x(".//span[contains(text(),'Выполнено успешно за ')]");
    SelenideElement exportTab = $x(".//div[text()='Экспорт']");
    SelenideElement exportMultyButton = $x(".//span[text()='Экспортировать']/..");
    SelenideElement exportButon = $x(".//span[text()='Экспорт']/ancestor::button");
    SelenideElement exportInFileButon = $x(".//span[text()='В файл']/ancestor::a");
    SelenideElement exportInFileMultyButon = $x(".//span[text()='Скачать как файл']/..");
    SelenideElement modeMq = $x(".//span[contains(@title,\"Менеджер очередей\")]//following-sibling::span/span[2]");


    ElementsCollection addressInput = $$x(".//input[contains(@name,\"host\")]"),
            portInput = $$x(".//input[@name=\"port\"]"),
            rowCanalInTable = $$x(".//tr[contains(@data-row-key,\"NETWORK_CONNECTOR\")]"),
            statusOnCanalInTable = $$x(".//span[@class=\"ant-badge-status-dot ant-badge-status-success\"]").filterBy(visible),
            statusOffCanalInTable = $$x(".//span[@class=\"ant-badge-status-dot ant-badge-status-error\"]").filterBy(visible), nameCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[2]").filterBy(visible), URICanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[3]").filterBy(visible), staticLinksCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[4]").filterBy(visible), QMCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[5]").filterBy(visible), duplexCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[6]").filterBy(visible), sendCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[7]").filterBy(visible), extractedCanalInTable = $$x(".//span[text()='Добавить канал']/../../../../..//table//td[8]").filterBy(visible);

//    public QueueManagerMultyPage() {
//        if (!queueManagerPage.attr("class").contains("active") || !url().contains("/download/")) {
//            click(queueManagerPage);
//        }
//    }
//
//    public QueueManagerMultyPage(String empty) {
//    }

    public void goToManager(String managerName) {
        System.out.printf((queuesOfSpecificManagerButton) + "%n", managerName);
        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).isDisplayed()) {
            click(queueManagersTab);
        }
        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).isDisplayed()) {
            click($x(String.format(specificManagerButton, managerName)));
        }
    }

    public void createManager(String managerName) {
        click(queueManagersTab);
        click(createManagerButton);
        val(managerNameInput, managerName);
        click(createButton);
    }

    public void startManager(String managerName) {
//        click(queueManagersTab);
//        if ($x(String.format(specificManagerButton, managerName)).is(not(visible))) {
//            refresh();
//        }
//        click($x(String.format(specificManagerButton, managerName)));
        goToManager(managerName);
        click(runManagerButton);
        elementShouldBeVisible(stopManagerButton);
    }

    public void deleteManager(String managerName) {
        click(queueManagersTab);
        if ($x(String.format(specificManagerButton, managerName)).is(not(visible))) {
            refresh();
        }
        click($x(String.format(specificManagerButton, managerName)));
        click(deleteManagerButton);
        click(confirmationDeleteButton);
    }

    public void checkManagerNotExist(String managerName) {
//        refresh();
        open(base.BaseUrl);
        click(queueManagersTab);
        $x(String.format(specificManagerButton, managerName)).shouldNotBe(exist);
    }

    public void createLocalQueueWhithoutSave(String managerName, String queueName) {
        goToManager(managerName);
        click($x(String.format(queuesOfSpecificManagerButton, managerName)));
        click(addQueueButton);
        val(queueNameInput, queueName);
    }

    public void successCreateLocalQueue(String managerName, String queueName) {
        createLocalQueueWhithoutSave(managerName, queueName);
        click(saveQueueButton);
        sleep(1000);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(text("Локальная"));
    }

    public void cancelCreateLocalQueue(String managerName, String queueName) {
        createLocalQueueWhithoutSave(managerName, queueName);
        click(closeWindowIcon);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldNotBe(visible);
        refresh();
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldNotBe(visible);
    }


    @Step("Create canal {0}")
    public void createChannel(String managerName, String CanalName, ArrayList<String> ipList, ArrayList<String> portList, String localQueueName, String topicName, String methodConnection, String typeCanal, String userName, String userPassword) {
        goToManager(managerName);
        click($x(String.format(channelsOfSpecificManagerButton, managerName)));

//        сanalsList();
        click(autoUpdatePageSOPSEmptyCheckBox);
        click(addCanalButton);
        val(canalNameInput, CanalName);

        for (int i = 0; i < ipList.size(); i++) {
            val(addressInput.get(i), ipList.get(i));
            val(portInput.get(i), portList.get(i));
            if (i < ipList.size() - 1) {
                click(addButton2);
            }
        }
        click(staticLinksForQueueSelect);
        valAndSelectElement(staticLinksForQueueInput, localQueueName);
        click(modalWindow);
        if (topicName!=null){
            click(staticLinksForTopicSelect);
            valAndSelectElement(staticLinksForTopicInput, topicName);
            click(modalWindow);
        }
        click(additionalParaametersOfConnection);
        click(methodConnectionActivate);
        valAndSelectElement(methodConnectionInput, methodConnection);

        val(userNameForautorisationInput, userName);
        val(userPasswordForautorisationInput, userPassword);

        increaseTheWaitTimeCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        dynamicModeOnlyCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        ReduceTheNetworkPriorityCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        recipientsWorkAsOneCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        duplexCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        duplicateSubscriptionsWillBeHiddenCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        sendSystemMessagesCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        sendNonpersistentMessagesUsingRequestReplyCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        turnOffAutomaticConsumerQueuingCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));

        switch (typeCanal) {
            case ("NotDuplex"):
                break;
            case ("Duplex"):
                click(duplexCanalCheckBox);
                break;
            default:
                throw new AssertionError("Пропушен выбор направленности канала");
        }

        click(saveCanalButton);
    }

    @Step
    public void channelCheckAllParameters(String managerName, String managerName2, String canalName, ArrayList<String> ipList, ArrayList<String> portList, String localQueueName, String topicName, String methodConnection, String typeCanal) {
        searchCanal(managerName, canalName);
        sleep(10000);
        statusOnCanalInTable.get(0).shouldBe(visible);
        nameCanalInTable.get(0).shouldHave(matchesText(canalName));
        if (ipList.size() == 1) {
            compareStringAndElementText(methodConnection + ":(tcp://" + ipList.get(0) + ":" + portList.get(0) + ")" + "?useExponentialBackOff=true&backOffMu...", URICanalInTable.get(0));
        } else if (ipList.size() == 2) {
            compareStringAndElementText(methodConnection + ":(tcp://" + ipList.get(0) + ":" + portList.get(0) + ",tcp://" + ipList.get(1) + ":" + portList.get(1) + ")", URICanalInTable.get(0));
        }
//        compareStringAndElementText(methodConnection + ":(tcp://" + ipList.get(0) + ":" + portList.get(0) + ",tcp://" + ipList.get(1) + ":" + portList.get(1) + ")", URICanalInTable.get(0));
//        URICanalInTable.get(0).shouldHave(text("static:(tcp://" + ipList + ":61616)"));
        compareStringAndElementText(localQueueName + ", " + topicName, staticLinksCanalInTable.get(0));
        compareStringAndElementText(managerName2, QMCanalInTable.get(0));
//        staticLinksCanalInTable.get(0).shouldHave(matchesText(localQueueName));
//        QMCanalInTable.get(0).shouldHave(text("QM"));

        switch (typeCanal) {
            case ("NotDuplex"):
                duplexCanalInTable.get(0).shouldHave(text("Нет"));
                break;
            case ("Duplex"):
                duplexCanalInTable.get(0).shouldHave(text("Да"));
                break;
            default:
                throw new AssertionError("Пропушен выбор направленности канала");
        }

        sendCanalInTable.get(0).shouldHave(text("0"));
        extractedCanalInTable.get(0).shouldHave(text("0"));
        click(autoUpdatePageSOPSEmptyCheckBox);
    }


    @Step("Edit canal {0}")
    public void editChannel(String managerName, String CanalOldName, String CanalName, ArrayList<String> ipList, ArrayList<String> portList, String localQueueName, String methodConnection, String typeCanal) {
        goToManager(managerName);
        click(canalsMultyTab);
//        сanalsList();
        click(searchNameCanalIcon);
        val(searchNameCanalInput, CanalOldName);
        click(searchNameButton);
        contextClick(afterSearchQueueRow);
        click(editInContextMenu);

        val(canalNameInput, CanalName);

        for (int i = 0; i < ipList.size(); i++) {
            val(addressInput.get(i), ipList.get(i));
            val(portInput.get(i), portList.get(i));
            if (i < ipList.size() - 1) {
                click(addButton2);
            }
        }
        $x(".//label[text()=\"Статические связи для очередей\"]/../..//input/..").hover();
        click(clearStaticLinksForQueueIcon);
        click(staticLinksForQueueSelect);
        valAndSelectElement(staticLinksForQueueInput, localQueueName);
        click(bodyPage);
        click(additionalParaametersOfConnection);
        click(methodConnectionActivate);
        valAndSelectElement(methodConnectionInput, methodConnection);
        increaseTheWaitTimeCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        dynamicModeOnlyCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        ReduceTheNetworkPriorityCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        recipientsWorkAsOneCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        duplexCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        duplicateSubscriptionsWillBeHiddenCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));
        sendSystemMessagesCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        sendNonpersistentMessagesUsingRequestReplyCanalCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        turnOffAutomaticConsumerQueuingCanalCheckBox.shouldHave(attribute("class", "ant-checkbox"));

        switch (typeCanal) {
            case ("NotDuplex"):
                break;
            case ("Duplex"):
                click(duplexCanalCheckBox);
                break;
            default:
                throw new AssertionError("Пропушен выбор направленности канала");
        }

        click(saveCanalButton);
    }

    @Step
    public void deleteChannel(String managerName, String channelName) {
        goToManager(managerName);
        click(canalsMultyTab);
        click(searchNameCanalIcon);
        val(searchNameCanalInput, channelName);
        click(searchNameButton);
        contextClick(afterSearchQueueRow);
        click(deleteInContextMenu);
        click(confirmationDeleteButton);
    }

    @Step
    public void startChannel(String managerName, String channelName) {
        goToManager(managerName);
        click(canalsMultyTab);
        click(searchNameCanalIcon);
        val(searchNameCanalInput, channelName);
        click(searchNameButton);
        contextClick(afterSearchQueueRow);
        click(startInContextMenu);
    }

    @Step
    public void stopChannel(String managerName, String channelName) {
        goToManager(managerName);
        click(canalsMultyTab);
        autoUpdateOff();
        click(searchNameCanalIcon);
        val(searchNameCanalInput, channelName);
        click(searchNameButton);
        contextClick(afterSearchQueueRow);
        click(stopInContextMenu);
    }

    /**
     * Search and filter by Name
     *
     * @param queueName
     */
    @Step("Searching {0}")
    public void searchQueue(String managerName, String queueName) {
        goToManager(managerName);
        click($x(String.format(queuesOfSpecificManagerButton, managerName)));
        autoUpdateOff();
        if (activateSortByNameQueueIcon.attr("class").equals("anticon anticon-caret-up ant-table-column-sorter-up")) {
            click(activateSortByNameQueueIcon);
        }
        click(searchNameQueueIcon);
        val(searchNameQueueInput, queueName);
        click(searchNameButton);
    }

    @Step
    public void openFormForSendMessageToQueue(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        contextClick(afterSearchQueueRow);
        click(contextSendMessage);
    }

    @Step("Searching {0}")
    public void searchTopic(String managerName, String topicName) {
//        click(queueManagersTab);
//        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).exists()) {
//            click($x(String.format(specificManagerButton, managerName)));
//
//        }
        goToManager(managerName);
        click($x(String.format(topicsOfSpecificManagerButton, managerName)));
        autoUpdateOff();
        click(searchNameQueueIcon);
        val(searchNameQueueInput, topicName);
        click(searchNameButton);
    }

//    @Step
//    public void openFormForSendMessageToQueue(String queueName) {
//        searchQueue(queueName);
//        contextClick(afterSearchQueueRow);
//        click(contextSendMessage);
//
//    }

    @Step("Searching {0}")
    public void searchUser(String managerName, String userName) {
        goToManager(managerName);
        click($x(String.format(managerSettingOfSpecificManagerButton, managerName)));
        click(groupsAndusersTab);
//        autoUpdateOff();
        click(searchNameQueueIcon);
        val(searchNameQueueInput, userName);
        click(searchNameButton);
    }

    @Step("Deleting {0}")
    public void deleteQueueWithoutSave(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        sleep(500);
        contextClick(afterSearchQueueRow);
        click(deleteQueue);
    }

    @Step()
    public void sucessDeleteQueue(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        deleteQueueWithoutSave(managerName, queueName);
        click(confirmationDeleteButton);
        elementShouldNotBeVisible(confirmationDeleteButton);
        sleep(500);
        searchQueue(managerName, queueName);
        afterSearchTableRows.shouldHave(size(0));
    }

    @Step()
    public void cancelDeleteQueue(String managerName, String queueName) {
        deleteQueueWithoutSave(managerName, queueName);
        click(closeWindowIcon);
        sleep(1000);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(text("Локальная"));
        refresh();
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(text("Локальная"));
    }

    @Step()
    public void sucessDeleteTopic(String managerName, String topicName) {
        searchTopic(managerName, topicName);
        deleteTopicWithoutSave(managerName, topicName);
        click(confirmationDeleteButton);
        elementShouldNotBeVisible(confirmationDeleteButton);
        sleep(500);
        searchTopic(managerName, topicName);
        afterSearchTableRows.shouldHave(size(0));
    }

    @Step("Deleting {0}")
    public void deleteTopicWithoutSave(String managerName, String topicName) {
        searchTopic(managerName, topicName);
        sleep(500);
        contextClick(afterSearchQueueRow);
        click(deleteTopic);
    }

    @Step("Searching {0}")
    public void searchTopic(String topicName) {
//        if (!url().contains("/queue-manager/topics/topic")) {
//            click(topicsListTab);
//        }
//        refresh();
        click(topicsListTab);
        autoUpdateOff();
        click(searchNameQueueIcon);
        val(searchNameQueueInput, topicName);
        click(searchNameButton);
    }

    @Step
    public void searchCanal(String managerName, String canalName) {
//        refresh();
        goToManager(managerName);
        click($x(String.format(channelsOfSpecificManagerButton, managerName)));
        autoUpdateOff();
        click(searchNameCanalIcon);
        val(searchNameCanalInput, canalName);
        click(searchNameButton);
        autoUpdateOn();
    }

    @Step("Deleting {0}")
    public void deleteCanal(String managerName, String canalName) {
        searchCanal(managerName, canalName);
        contextClick(rowCanalInTable.get(0));
        click(deleteCanalLink);
        click(confirmationDeleteButton);
        elementShouldNotBeVisible(confirmationDeleteButton);
        sleep(500);
        refresh();
        searchCanal(managerName, canalName);
        rowCanalInTable.shouldHave(size(0));
    }

    @Step("Deleting {0} if exist")
    public void deleteQueueIfExist(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        if (afterSearchQueueRow.is(visible)) {
            contextClick(afterSearchQueueRow);
            click(deleteQueue);
            click(confirmationDeleteButton);
            searchQueue(managerName, queueName);
            afterSearchTableRows.shouldHave(size(0));
        }
    }

    @Step("update every 1 seconds")
    public void turnUpdateSetMin() {
        updateTimeIntervalInput.clear();
    }

    @Step("Click in queues list")
    public void queuesList() {
        click(queuesList);
    }

    @Step("Click in сanals list")
    public void сanalsList() {
        click(canalsList);
    }

    @Step("Click in queues list")
    public void topicsList() {
        click(topicsList);
    }


    @Step("Creation segregation queue - {0} with destination - {1} and fragment size - {2} bit")
    public void createSegregQueue(String managerName, String deliveryQueueName, String destinationQueueName, Integer size) {
        goToManager(managerName);
        click($x(String.format(queuesOfSpecificManagerButton, managerName)));
        click(addQueueButton);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Очередь сегментации");
//        click($x(String.format(typeQueue, "Очередь сегментации")));
//        queueTypeSelect.selectOption("Очередь сегментации");
        valAndSelectElement(nameQueue, deliveryQueueName);
        click(destinationQueueSelect);
//        valAndSelectElement(destinationQueueInput, destinationQueueName);
        valAndSelectElement(destinationQueueInput, destinationQueueName);
        val(fragmentSize, size.toString());
        click(saveQueueButton);
        elementShouldNotBeVisible(saveQueueButton);

    }

    @Step("Creation agregation queue - {0} with destination - {1}")
    public void createAgregQueue(String managerName, String deliveryQueueName, String destinationQueueName) {
        goToManager(managerName);
        click($x(String.format(queuesOfSpecificManagerButton, managerName)));
        click(addQueueButton);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Очередь агрегации");
//        click($x(String.format(typeQueue, "Очередь агрегации")));
        val(nameQueue, deliveryQueueName);
        click(destinationQueueSelect);
        valAndSelectElement(destinationQueueInput, destinationQueueName);
        click(saveQueueButton);
        elementShouldNotBeVisible(saveQueueButton);

    }

    @Step("Creation virtual queue - {0} with redirection - {1} ")
    public void createVirtualQueue(String managerName, String queueName, String redirectionQueueName) {
        click(addQueueButton);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Виртуальная очередь");
//        click($x(String.format(typeQueue, "Виртуальная очередь")));
        valAndSelectElement(redirectionToQueuesInput, redirectionQueueName);
        valAndSelectElement(queueNameInputForVirtualQueue, queueName);
        click(saveQueueButton);
        elementShouldNotBeVisible(confirmationDeleteButton);
        sleep(500);
        basePage.autoUpdateOff();
        //        click(refreshConfigAfterChangeButton);
        addQueueButton.waitUntil(enabled, 10000);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(text("Виртуальная очередь"));
    }

    @Step("Creation virtual queue - {0} with redirection - {1}, {2} ")
    public void createVirtualQueue(String managerName, String queueName, String redirectionQueueName1, String redirectionQueueName2, String redirectionTopicName1, String redirectionTopicName2) {
//        click(queueManagersTab);
//        click($x(String.format(specificManagerButton, managerName)));
//        click($x(String.format(queuesOfSpecificManagerButton, managerName)));

        goToManager(managerName);
        click($x(String.format(queuesOfSpecificManagerButton, managerName)));

        click(addQueueButton);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Виртуальная очередь");
//        click($x(String.format(typeQueue, "Виртуальная очередь")));
        click(redirectionToQueuesSelect);
        valAndSelectElement(redirectionToQueuesInput, redirectionQueueName1);
        valAndSelectElement(redirectionToQueuesInput, redirectionQueueName2);
        click(modalWindow);
        click(redirectionToTopicsSelect);
        valAndSelectElement(redirectionToTopicsInput, redirectionTopicName1);
        valAndSelectElement(redirectionToTopicsInput, redirectionTopicName2);
        val(queueNameInputForVirtualQueue, queueName);
        click(saveQueueButton);
        elementShouldNotBeVisible(saveQueueButton);
        sleep(500);
        autoUpdateOff();
//        restartModule();
        addQueueButton.waitUntil(enabled, 10000);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(text("Виртуальная"));
    }

    /**
     * Check a number of messages in a queue
     */
    @Step("Queue {0} should has {1} massages in depth")
    public void queueShouldHaveNMessagesInDepth(String managerName, String queueName, Integer messagesInDepth, int waitingTime) {
//        click(autoUpdatePageSOPSEmptyCheckBox);
        searchQueue(managerName, queueName);
        autoUpdateOn();
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), waitingTime);
        click(autoUpdatePageSOPSEmptyCheckBox);
    }

    /**
     * Returns the number of messages in queue
     */
    @Step("Returns the number of messages in queue: {0}")
    public String returnQueueDepth(String managerName, String queueName) {
        click(autoUpdatePageSOPSEmptyCheckBox);
        searchQueue(managerName, queueName);
        String dept = afterSearchQueueDepth.text();
        click(autoUpdatePageSOPSEmptyCheckBox);
        return dept;
    }

    /**
     * Get a number of messages in a queue
     */
    @Step(" Get a number of messages in a queue {0}")
    public String queueGetMessagesInDepth(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        return afterSearchQueueDepth.getText();
    }

    @Step("Check all parameters Queue {0}")
    public void queueCheckAllParameters(String managerName, String queueName, String messagesInDepth, String numberOfConsumers, String messagesInSent, String messagesInTaken, String queueType, String canalName, String redirections) {
//        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).exists()) {
//            click(queueManagersTab);
//            click($x(String.format(specificManagerButton, managerName)));
//            click($x(String.format(queuesOfSpecificManagerButton, managerName)));
//        }

        searchQueue(managerName, queueName);
        autoUpdateOn();
        compareStringAndElementText(queueName, afterSearchQueueName);
        compareStringAndElementText(messagesInDepth, afterSearchQueueDepth);
        compareStringAndElementText(numberOfConsumers, afterSearchQueueNumberOfConsumers);
        compareStringAndElementText(messagesInSent, afterSearchQueueSentToQueue);
        compareStringAndElementText(messagesInTaken, afterSearchQueueTakenFromQueue);
        compareStringAndElementText(queueType, afterSearchQueueType);
        compareStringAndElementText(canalName, afterSearchQueueCanal);
        compareStringAndElementText(redirections, afterSearchQueueRedirections);
        afterSearchQueueMinSize.shouldNotHave(exactText(""));
        afterSearchQueueMaxSize.shouldNotHave(exactText(""));
        afterSearchQueueAllSize.shouldNotHave(exactText(""));
        autoUpdateOff();
    }

    @Step("Check all parameters Topic {0}")
    public void topicCheckAllParameters(String managerName, String topicName, String numberOfConsumers, String messagesInSent, String messagesInTaken, String queueType, String canalName, String redirections) {

        searchTopic(managerName, topicName);
        autoUpdateOn();
        compareStringAndElementText(topicName, afterSearchTopicName);
//        compareStringAndElementText(messagesInDepth, afterSearchQueueDepth);
        compareStringAndElementText(numberOfConsumers, afterSearchTopicNumberOfConsumers);
        compareStringAndElementText(messagesInSent, afterSearchTopicSent);
        compareStringAndElementText(messagesInTaken, afterSearchTopicTaken);
        compareStringAndElementText(queueType, afterSearchTopicType);
        compareStringAndElementText(canalName, afterSearchTopicCanal);
        compareStringAndElementText(redirections, afterSearchQueueRedirections);
        afterSearchTopicMinSize.shouldNotHave(exactText(""));
        afterSearchTopicMaxSize.shouldNotHave(exactText(""));
        afterSearchTopicAllSize.shouldNotHave(exactText(""));
        autoUpdateOff();
    }

    public void checkParametersQueueInTableAndProperties(String managerName, String queueName, String type, String queuesDestination, String topicsDestination) {
        searchQueue(managerName, queueName);
        autoUpdateOff();
        valueNameTopicInTable = afterSearchQueueName.getText().trim();
        valueDepthQueueInTable = afterSearchQueueDepth.getText().trim();
        valueCustomersTopicInTable = afterSearchQueueNumberOfConsumers.getText().trim();
        valueSentTopicInTable = afterSearchQueueSentToQueue.getText().trim();
        valueTakenTopicInTable = afterSearchQueueTakenFromQueue.getText().trim();
        valueTypeTopicInTable = afterSearchQueueType.getText().trim();
        valueCanalTopicInTable = afterSearchQueueCanal.getText().trim();
        valueRedirectionsTopicInTable = afterSearchQueueRedirections.getText().trim();
        valueMinTopicInTable = afterSearchQueueMinSize.getText().trim();
        valueMaxTopicInTable = afterSearchQueueMaxSize.getText().trim();
        valueAllTopicInTable = afterSearchQueueAllSize.getText().trim();

        click(afterSearchQueueName);
        Assert.assertEquals(valueNameTopicInTable, nameChoosenQueueList.getText());

        specificQueuePage = new SpecificQueuePage("empty");

        switch (type) {
            case "Локальная очередь":
                click(specificQueuePage.propertiesButton);
                compareStringAndElementText(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesQueue);
                compareStringAndElementText("Локальная", specificQueuePage.valueTypeInPropertiesQueue);
                compareStringAndElementText(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesQueue);
                compareStringAndElementText(valueDepthQueueInTable, specificQueuePage.valueDepthInPropertiesQueue);
                compareStringAndElementText(valueSentTopicInTable, specificQueuePage.valueSentInPropertiesQueue);
                compareStringAndElementText(valueTakenTopicInTable, specificQueuePage.valueTakenInPropertiesQueue);
                compareStringAndElementText("-", specificQueuePage.valueCanalInPropertiesQueueMulty);
                compareStringAndElementText(valueMaxTopicInTable, specificQueuePage.valueMaxInPropertiesQueue);
                compareStringAndElementText(valueMinTopicInTable, specificQueuePage.valueMinInPropertiesQueue);
                compareStringAndElementText(valueAllTopicInTable, specificQueuePage.valueAllInPropertiesQueue);
//                specificQueuePage.valueCanalInPropertiesQueue.shouldNotBe(visible);
//                specificQueuePage.valueDestinationQueue.shouldNotBe(visible);
                break;

            case "Виртуальная очередь":
                click(specificQueuePage.propertiesButton);
                compareStringAndElementText(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesQueue);
                compareStringAndElementText("Виртуальная", specificQueuePage.valueTypeInPropertiesQueue);
                compareStringAndElementText(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesQueue);
                compareStringAndElementText("-", specificQueuePage.valueCanalInPropertiesQueueMulty);
                compareStringAndElementText(queuesDestination, specificQueuePage.valueDestinationQueueMulty);
                compareStringAndElementText(topicsDestination, specificQueuePage.valueDestinationTopicMulty);
                specificQueuePage.valueDepthInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueSentInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueTakenInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueMaxInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueMinInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueAllInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueCanalInPropertiesQueue.shouldNotBe(visible);
                break;

            case "Очередь сегментации":
                click(specificQueuePage.propertiesButton);
                compareStringAndElementText(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesQueue);
                compareStringAndElementText(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesQueue);
                compareStringAndElementText(valueDepthQueueInTable, specificQueuePage.valueDepthInPropertiesQueue);
                compareStringAndElementText(valueSentTopicInTable, specificQueuePage.valueSentInPropertiesQueue);
                compareStringAndElementText(valueTakenTopicInTable, specificQueuePage.valueTakenInPropertiesQueue);
                compareStringAndElementText("Сегментация", specificQueuePage.valueTypeInPropertiesQueue);
                compareStringAndElementText(valueMaxTopicInTable, specificQueuePage.valueMaxInPropertiesQueue);
                compareStringAndElementText(valueMinTopicInTable, specificQueuePage.valueMinInPropertiesQueue);
                compareStringAndElementText(valueAllTopicInTable, specificQueuePage.valueAllInPropertiesQueue);
                specificQueuePage.valueCanalInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueDestinationQueue.shouldNotBe(visible);
                break;

            case "Очередь агрегации":
                click(specificQueuePage.propertiesButton);
                compareStringAndElementText(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesQueue);
                compareStringAndElementText(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesQueue);
                compareStringAndElementText(valueDepthQueueInTable, specificQueuePage.valueDepthInPropertiesQueue);
                compareStringAndElementText(valueSentTopicInTable, specificQueuePage.valueSentInPropertiesQueue);
                compareStringAndElementText(valueTakenTopicInTable, specificQueuePage.valueTakenInPropertiesQueue);
                compareStringAndElementText("Агрегация", specificQueuePage.valueTypeInPropertiesQueue);
                compareStringAndElementText(valueMaxTopicInTable, specificQueuePage.valueMaxInPropertiesQueue);
                compareStringAndElementText(valueMinTopicInTable, specificQueuePage.valueMinInPropertiesQueue);
                compareStringAndElementText(valueAllTopicInTable, specificQueuePage.valueAllInPropertiesQueue);
                specificQueuePage.valueCanalInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueDestinationQueue.shouldNotBe(visible);
                break;
            case "Транспортная очередь":
                click(specificQueuePage.propertiesButton);
                Assert.assertEquals(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesQueue.getText());
                Assert.assertEquals(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesQueue.getText());
                Assert.assertEquals(valueDepthQueueInTable, specificQueuePage.valueDepthInPropertiesQueue.getText());
                Assert.assertEquals(valueSentTopicInTable, specificQueuePage.valueSentInPropertiesQueue.getText());
                Assert.assertEquals(valueTakenTopicInTable, specificQueuePage.valueTakenInPropertiesQueue.getText());
                Assert.assertEquals("Транспортная", specificQueuePage.valueTypeInPropertiesQueue.getText());
                Assert.assertEquals(valueMaxTopicInTable, specificQueuePage.valueMaxInPropertiesQueue.getText());
                Assert.assertEquals(valueMinTopicInTable, specificQueuePage.valueMinInPropertiesQueue.getText());
                Assert.assertEquals(valueAllTopicInTable, specificQueuePage.valueAllInPropertiesQueue.getText());
                specificQueuePage.valueCanalInPropertiesQueue.shouldNotBe(visible);
                specificQueuePage.valueDestinationQueue.shouldNotBe(visible);
                break;

            default:
                throw new AssertionError("Пропущена проверка канала в свойствах очереди");
        }


        click(returnInQueueListButton);
    }

    public void checkParametersTopicInTableAndProperties(String managerName, String topicName) {
        searchTopic(managerName, topicName);
        autoUpdateOff();
        valueNameTopicInTable = afterSearchTopicName.getText().trim();
        valueCustomersTopicInTable = afterSearchTopicNumberOfConsumers.getText().trim();
        valueSentTopicInTable = afterSearchTopicSent.getText().trim();
        valueTakenTopicInTable = afterSearchTopicTaken.getText().trim();
        valueTypeTopicInTable = afterSearchTopicType.getText().trim();
        valueCanalTopicInTable = afterSearchTopicCanal.getText().trim();
        valueRedirectionsTopicInTable = afterSearchTopicRedirections.getText().trim();
        valueMinTopicInTable = afterSearchTopicMinSize.getText().trim();
        valueMaxTopicInTable = afterSearchTopicMaxSize.getText().trim();
        valueAllTopicInTable = afterSearchTopicAllSize.getText().trim();

        click(afterSearchTopicName);
        Assert.assertEquals(valueNameTopicInTable, nameChoosenQueueList.getText());
        specificQueuePage = new SpecificQueuePage("empty");
        click(specificQueuePage.propertiesTopicButton);
        compareStringAndElementText(valueNameTopicInTable, specificQueuePage.valueNameIhInPropertiesTopic);
        compareStringAndElementText("Локальный", specificQueuePage.valueTypeInPropertiesTopic);
        compareStringAndElementText(valueCustomersTopicInTable, specificQueuePage.valueCustomersInPropertiesTopic);
        compareStringAndElementText(valueSentTopicInTable, specificQueuePage.valueSentInPropertiesTopic);
        compareStringAndElementText(valueTakenTopicInTable, specificQueuePage.valueTakenInPropertiesTopic);
        compareStringAndElementText("-", specificQueuePage.valueCanalInPropertiesTopic);
        compareStringAndElementText(valueMaxTopicInTable, specificQueuePage.valueMaxInPropertiesTopic);
        compareStringAndElementText(valueMinTopicInTable, specificQueuePage.valueMinInPropertiesTopic);
        compareStringAndElementText(valueAllTopicInTable, specificQueuePage.valueAllInPropertiesTopic);

        click(returnInQueueListButton);
    }


    public void checkMessagesQueueInTableAndProperties(String managerName, String queueName, String messageText) {
//        if (!queuesList.attr("class").contains("active"))
//            click(queuesList);
        searchQueue(managerName, queueName);
        autoUpdateOff();
        valueDepthQueueInTable = afterSearchQueueDepth.getText().trim();
        valueTypeTopicInTable = afterSearchQueueType.getText().trim();
        click(afterSearchQueueName);

        switch (valueTypeTopicInTable) {
            case "Локальная":
                click(specificQueuePage.messagesButton);

//                if (!valueDepthQueueInTable.equals("0") && !valueDepthQueueInTable.equals("-")) {
                rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueDepthQueueInTable));
//                } else {
//                    rowsMessagesInTable.shouldHaveSize(0);
//                    compareStringAndElementText("Нет данных",noDataText);
//                }
                click(sendMessageButton);
                Assert.assertEquals(queueName, nameQueueInSendMessagePage.getText());
                val(messagePage.messageTextAria, messageText);
                click(messagePage.sendMessageButton);
                back();

//                if (valueDepthQueueInTable.equals("-")) {
//                    rowsMessagesInTable.shouldHaveSize(1);
//                } else {
                rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueDepthQueueInTable) + 1);
//                }
                break;

            case "Виртуальная":
                specificQueuePage.messagesButton.shouldNotBe(visible);
                click(sendMessageButton);
                Assert.assertEquals(queueName, nameQueueInSendMessagePage.getText());
                val(messagePage.messageTextAria, messageText);
                click(messagePage.sendMessageButton);
                back();

                specificQueuePage.messagesButton.shouldNotBe(visible);

            case "Транспортная":
                click(specificQueuePage.messagesButton);

                if (!valueDepthQueueInTable.equals("0") && !valueDepthQueueInTable.equals("-")) {
                    rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueDepthQueueInTable));
                } else {
                    rowsMessagesInTable.shouldHaveSize(0);
                    compareStringAndElementText("Нет данных", noDataText);
                }
                click(sendMessageButton);
                Assert.assertEquals(queueName, nameQueueInSendMessagePage.getText());
                Assert.assertEquals(valueTypeTopicInTable + " очередь", typeQueueInSendMessagePage.getText());
                val(messagePage.messageTextAria, messageText);
                click(messagePage.sendMessageButton);
                back();

                compareStringAndElementText("Нет данных", noDataText);
                break;
            case "Агрегация":
                click(specificQueuePage.messagesButton);

                if (!valueDepthQueueInTable.equals("0") && !valueDepthQueueInTable.equals("-")) {
                    rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueDepthQueueInTable));
                } else {
                    rowsMessagesInTable.shouldHaveSize(0);
                    compareStringAndElementText("Нет данных", noDataText);
                }
                click(sendMessageButton);
                Assert.assertEquals(queueName, nameQueueInSendMessagePage.getText());
                Assert.assertEquals("Очередь агрегации", typeQueueInSendMessagePage.getText());
                val(messagePage.messageTextAria, messageText);
                click(messagePage.sendMessageButton);
                back();

                compareStringAndElementText("Нет данных", noDataText);

                break;
            case "Сегментация":
                click(specificQueuePage.messagesButton);
                sout("Глубина в таблице очереди сегментации = " + valueDepthQueueInTable);
//                if (!valueDepthQueueInTable.equals("0") && !valueDepthQueueInTable.equals("-")) {
//                    rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueDepthQueueInTable));
//                } else {
//                    rowsMessagesInTable.shouldHaveSize(0);
                compareStringAndElementText("Нет данных", noDataText);
//                }
                click(sendMessageButton);
                Assert.assertEquals(queueName, nameQueueInSendMessagePage.getText());
                Assert.assertEquals("Очередь сегментации", typeQueueInSendMessagePage.getText());
                val(messagePage.messageTextAria, messageText);
                click(messagePage.sendMessageButton);
                back();

                compareStringAndElementText("Нет данных", noDataText);
                break;
            default:
                basePage.sout(valueTypeTopicInTable);
                throw new AssertionError("Пропущена проверка типа очереди на странице отправки сообщений");
        }


//        switch (valueTypeQueueInTable) {
//
//            case "Агрегация":
//            case "Сегментация":
//            case "Транспортная":
//                rowsMessagesInTable.shouldHaveSize(1);
//                Assert.assertEquals("Записи отсутствуют.", rowsMessagesInTable.get(0).getText());
//                break;
//            default:
//                throw new AssertionError("Пропущена проверка возможности отправить сообщение со страницы информации об очереди");
//        }

    }

    public void checkActiveCustomersQueue(String managerName, String queueName) {
        if (!queuesList.attr("class").contains("active"))
            click(queuesList);
        searchQueue(managerName, queueName);
        autoUpdateOff();
        valueCustomersTopicInTable = afterSearchQueueNumberOfConsumers.getText().trim();
        click(afterSearchQueueName);
        click(specificQueuePage.activeCustomersButton);

        sout("Количество потребителей очереди = " + valueCustomersTopicInTable);
        if (!valueCustomersTopicInTable.equals("0")) {
            rowsMessagesInTable.shouldHaveSize(Integer.parseInt(valueCustomersTopicInTable));
            specificQueuePage.idConnectionInTable.shouldNotHave(exactText(""));
            specificQueuePage.idCustomerInTable.shouldNotHave(exactText(""));
            specificQueuePage.idSessionInTable.shouldNotHave(exactText(""));
            specificQueuePage.selectorInTable.shouldHave(exactText(""));
            specificQueuePage.placedInTable.shouldNotHave(exactText(""));
            specificQueuePage.takenInTable.shouldNotHave(exactText(""));
            specificQueuePage.routedInTable.shouldNotHave(exactText(""));
            specificQueuePage.routedQueueInTable.shouldNotHave(exactText(""));
            specificQueuePage.cachingAndMaximumWaitingInTable.shouldNotHave(exactText(""));
            specificQueuePage.exceptionalRetroactiveInTable.shouldNotHave(exactText(""));
        } else {
            rowsMessagesInTable.shouldHaveSize(0);
            Assert.assertEquals("Записи отсутствуют.", rowsMessagesInTable.get(0).getText());
        }
    }

    public void checkActiveProducersQueue(String queueName) {
    }

    @Step("Queue {0} should has {1} massages in depth")
    public void queueShouldHaveNMessagesInDepth(String managerName, String queueName, Integer messagesInDepth) {
        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).exists()) {
            click(queueManagersTab);
            click($x(String.format(specificManagerButton, managerName)));
            click($x(String.format(queuesOfSpecificManagerButton, managerName)));
        }

        autoUpdateOff();
        searchQueue(managerName, queueName);
        afterSearchQueueDepth.waitUntil(exactText(messagesInDepth.toString()), 5000);
    }

    @Step("Queue {0} should has {1} massages in depth")
    public void queueShouldHaveNMessagesInDepthWithBeforeWait(String managerName, String queueName, Integer messagesInDepth, int watingTime) {
        searchQueue(managerName, queueName);
        sleep(watingTime);
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), 5000);
    }

    /**
     * Check a number of messages in sent in from queue
     */
    @Step("Queue {0} should has {1} massages in sent")
    public void queueShouldHaveNMessagesInSent(String managerName, String queueName, Integer messagesInSent, int waitingTime) {
        searchQueue(managerName, queueName);
        afterSearchQueueSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in sent and {2} in taken")
    public void queueShouldHaveNMessagesInSentAndTaken(String managerName, String queueName, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        searchQueue(managerName, queueName);
        afterSearchQueueSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchQueueTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
    }

    /**
     * Check a number of messages in a queue, in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in depth, {2} in sent and {3} in taken")
    public void queueShouldHaveNMessagesInDepthAndSentAndTaken(String managerName, String queueName, Integer messagesInDepth, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        searchQueue(managerName, queueName);
        afterSearchQueueDepth.waitUntil(text(messagesInDepth.toString()), waitingTime);
        afterSearchQueueSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchQueueTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} consumer, {2} in sent and {3} in taken")
    public void queueShouldHaveNConsumersAndNMessagesInSentAndTaken(String managerName, String queueName, Integer numberOfConsumers, Integer messagesInSent, Integer messagesInTaken) {
        autoUpdateOff();
        searchQueue(managerName, queueName);
        int waitingTime = 7000;
        afterSearchQueueSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchQueueTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        afterSearchQueueNumberOfConsumers.waitUntil(text(numberOfConsumers.toString()), waitingTime);
    }

    /**
     * Check a number of messages in sent in and taken from queue
     */
    @Step("Queue {0} should has {1} massages in depth, {2} in sent and {3} in taken, waiting time - {4}")
    public void queueShouldHaveNConsumersAndNMessagesInSentAndTaken(String managerName, String queueName, Integer numberOfConsumers, Integer messagesInSent, Integer messagesInTaken, int waitingTime) {
        searchQueue(managerName, queueName);
        afterSearchQueueSentToQueue.waitUntil(text(messagesInSent.toString()), waitingTime);
        afterSearchQueueTakenFromQueue.waitUntil(text(messagesInTaken.toString()), waitingTime);
        afterSearchQueueNumberOfConsumers.waitUntil(text(numberOfConsumers.toString()), waitingTime);
    }

    /**
     * Send saved messages, after context click on queue
     */
    @Step("Send {1} saved messsages in {0}")
    public void sendNSavedMessagesToQueue(String managerName, String queueName, Integer messagesNumber) {
        searchQueue(managerName, queueName);
        for (int i = 0; i < messagesNumber; i++) {
            contextClick(afterSearchQueueRow);
            click(sendSavedMessage);
            sleep(500);
            commonComponents.closeNotification();
        }
        afterSearchQueueSentToQueue.shouldHave(text(messagesNumber.toString()));
    }

    @Step("Send {1} saved messsages in {0}")
    public void sendNSavedMessagesToTopic(String managerName, String topicName, Integer messagesNumber) {
        searchTopic(managerName, topicName);
        for (int i = 0; i < messagesNumber; i++) {
            contextClick(afterSearchTopicRow);
            click(sendSavedMessage);
            sleep(500);
            commonComponents.closeNotification();
        }
        afterSearchTopicSent.shouldHave(text(messagesNumber.toString()));
    }

    /**
     * Send saved messages, after context click on queue
     */
    @Step("Send {1} saved messsages in {0}")
    public void sendNMessages(String managerName, String queueName, String Message, Integer Times) {
        searchQueue(managerName, queueName);
        contextClick(afterSearchQueueRow);
        click(contextSendMessage);
        val(messagePage.howManyMessagesToSend, Times.toString());
        val(messagePage.messageTextAria, Message);
        click(messagePage.sendMessageButton);
    }

    /**
     * Send saved messages, after context click on queue without check
     */
    @Step("Send {1} saved messsages in {0} without check")
    public void sendNSavedMessagesWithoutCheck(String managerName, String queueName, Integer messagesNumber) {
//        if (!$x(String.format(queuesOfSpecificManagerButton, managerName)).exists()) {
//            click(queueManagersTab);
//            click($x(String.format(specificManagerButton, managerName)));
//            click($x(String.format(queuesOfSpecificManagerButton, managerName)));
//        }

//        goToManager(managerName);
        searchQueue(managerName, queueName);
        for (int i = 0; i < messagesNumber; i++) {
            contextClick(afterSearchQueueRow);
            click(sendSavedMessage);
            commonComponents.closeNotification();
        }
    }

    @Step
    public void editSavedMessages(String managerName, String queueName, String textMessage) {
        searchQueue(managerName, queueName);
        contextClick(afterSearchQueueRow);
        click(contextSendMessage);
        val(messagePage.messageTextAria, textMessage);
        click(messagePage.saveDefaultMessageButton);
    }

    @Step("Double click in {0}")
    public void doubleClickInSpecificQueue(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        sleep(1000);
        doubleClick(afterSearchQueueRow);
    }

    @Step("Clear {0}")
    public void clearQueueWithoutSave(String managerName, String queueName) {
        click(autoUpdatePageSOPSEmptyCheckBox);
        searchQueue(managerName, queueName);
        contextClick(afterSearchQueueRow);
        click(clearQueue);

    }

    @Step()
    public void successClearQueue(String managerName, String queueName) {
        clearQueueWithoutSave(managerName, queueName);
        click(clearButton);
        confirmationClearMenuHeader.waitWhile(visible, 10000);
        searchQueue(managerName, queueName);
        afterSearchQueueDepth.shouldHave(text("0"));
        click(autoUpdatePageSOPSEmptyCheckBox);
    }

    @Step()
    public void cancelClearQueue(String managerName, String queueName, String sumMessages) {
        clearQueueWithoutSave(managerName, queueName);
        click(closeWindowIcon);
        searchQueue(managerName, queueName);
        afterSearchQueueDepth.shouldHave(text(sumMessages));
    }

    @Step("Content {0}")
    public void contentSpecificQueue(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        if (!afterSearchQueueRow.isDisplayed()) {
            click(refreshSearchButton);
        }
        doubleClick(afterSearchQueueRow);
        sleep(1000);
    }

    /**
     * Method for edit queues type in local queue
     */
    @Step("Edit {0} type in local queue")
    public void editQueueTypeInLocalQueue(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        contextClick(afterSearchQueueRow);
        click(editQueue);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Локальная очередь");
//        click($x(String.format(typeQueue, "Локальная очередь")));
        click(saveEditedQueueButton);
        sleep(1000);
        searchQueue(managerName, queueName);
        afterSearchQueueType.shouldHave(exactText("Локальная"));
    }

    /**
     * Method for edit queues type in segmintation queue
     */
    @Step("Edit {0} type in local queue")
    public void editQueueTypeInSegmetationQueue(String managerName, String deliveryQueueName, String destinationQueueName, Integer size) {
        contextClick(afterSearchQueueName);
        click(editQueue);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Очередь сегментации");
//        click($x(String.format(typeQueue, "Очередь сегментации")));
        click(destinationQueueSelect);
        valAndSelectElement(destinationQueueInput, destinationQueueName);
        val(fragmentSize, size.toString());
        click(saveEditedQueueButton);
        searchQueue(managerName, deliveryQueueName);
        afterSearchQueueType.shouldHave(exactText("Сегментация"));
    }

    /**
     * Method for edit queues type in agregation queue
     */
    @Step("Edit {0} type in local queue")
    public void editQueueTypeInAgregationQueue(String managerName, String deliveryQueueName, String destinationQueueName) {
        contextClick(afterSearchQueueName);
        click(editQueue);
        click(queueTypeSelect);
        valAndSelectElement(queueTypeInput, "Очередь агрегации");
//        click($x(String.format(typeQueue, "Очередь агрегации")));
//        valAndPressEnter(deliveryQueue, deliveryQueueName);
        click(destinationQueueSelect);
        valAndSelectElement(destinationQueueInput, destinationQueueName);
        click(saveEditedQueueButton);
        searchQueue(managerName, deliveryQueueName);
        afterSearchQueueType.shouldHave(exactText("Агрегация"));
    }

    /**
     * Method for check that queue has specific message
     */
    @Step("{0} should has specific message")
    public void queueShouldHaveSpecificMessage(String managerName, String queueName, String text) {
        contentSpecificQueue(managerName, queueName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.openFirstMessage();
        specificMessagePage = new SpecificMessagePage("");
        compareStringAndElementText(text, specificMessagePage.messageBody);
        click(specificMessagePage.returnInQueueMultyButton);
        click(specificQueuePage.returnInQueuesListPageMultyButton);
    }

    @Step("{0} should has specific message")
    public void queueShouldHaveSomeSpecificMessages(String managerName, String queueName, int numerOfMessages, String text) {
        contentSpecificQueue(managerName, queueName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.openMessageWithNumber(numerOfMessages);
        specificMessagePage = new SpecificMessagePage("");
        compareStringAndElementText(text, specificMessagePage.messageBody);
        click(specificMessagePage.returnInQueueMultyButton);
        click(specificQueuePage.returnInQueuesListPageMultyButton);
    }

    /**
     * Method for check that queue contains specific phrase
     */
    @Step("{0} should contains specific phrase")
    public void queueShouldContainsSpecificPhraseInMessage(String managerName, String queueName, String message) {
        contentSpecificQueue(managerName, queueName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.openFirstMessage();
        specificMessagePage = new SpecificMessagePage("");
        specificMessagePage.messageBody.shouldHave(matchesText(message));
        click(specificMessagePage.returnInQueueMultyButton);
        click(specificQueuePage.returnInQueuesListPageMultyButton);
    }

    @Step("queue Should Have Depth {0} ")
    public void queueShouldHaveDepth(String managerName, String queueName, int depth) {
        searchQueue(managerName, queueName);
        autoUpdateOn();
        afterSearchQueueDepth.waitUntil(exactText(Integer.toString(depth)), 20000);
//        Assert.assertEquals(depth, Integer.parseInt(afterSearchQueueDepth.getText()));
    }

    @Step("{0} should contains specific phrase")
    public void queueShouldBeEmpty(String managerName, String queueName) {
        contentSpecificQueue(managerName, queueName);
        specificQueuePage = new SpecificQueuePage("");
        specificQueuePage.tableRowsIDCollon.shouldHaveSize(0);
        click(specificQueuePage.returnInQueuesListPageMultyButton);
    }

    @Step("queue Should Exist {0} ")
    public void queueShouldExisth(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        Assert.assertTrue("Очередь не существует", afterSearchQueueDepth.exists());
    }

    @Step("queue Should Not Exist {0} ")
    public void queueShouldNotExist(String managerName, String queueName) {
        searchQueue(managerName, queueName);
        Assert.assertFalse("Очередь существует", afterSearchQueueDepth.exists());
    }

    /**
     * Method for deleting an arbitrary number of queues
     */
    @Step("Deleting queues")
    public void deleteSeveralQueues(String managerName, String... queueNames) {
        for (String queue : queueNames)
            sucessDeleteQueue(managerName, queue);
    }

    /**
     * Download message
     */
    @Step("Download message")
    public String downloadMessage(String managerName, String queue, String Type) {
        String urlDownload = "file:///home/selenium/Downloads/";
        String fileID = null;
        switch (Type) {
            case ("файл"):
                searchQueue(managerName, queue);
                click(afterSearchQueueName);
                specificQueuePage = new SpecificQueuePage("");

                fileID = specificQueuePage.fileName.get(0).getText();
                basePage.sout("Имя скачиваемого файла: " + fileID);

                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.downloadMessage);
                sleep(5000);
                open(urlDownload);
                compareStringAndElementText(fileID, downloadedFileName);


//                DownloadedFileName = downloadedFileName.getText();
//                basePage.sout("Файл: " + DownloadedFileName);
//                Assert.assertEquals(fileID , DownloadedFileName);
                break;
            case ("1 без галки"):
                searchQueue(managerName, queue);
                click(afterSearchQueueName);
                specificQueuePage = new SpecificQueuePage("");

                fileID = specificQueuePage.fileID.get(0).getText().replaceAll(":", "-");
                basePage.sout("ID скачиваемого сообщения: " + fileID);

                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.downloadMessage);
                sleep(5000);
                open(urlDownload);
                DownloadedFileName = downloadedFileName.getText();
                basePage.sout("Файл: " + DownloadedFileName);
                Assert.assertEquals(fileID + ".txt", DownloadedFileName);
                break;

            case ("1 с галкой"):
                searchQueue(managerName, queue);
                click(afterSearchQueueName);
                specificQueuePage = new SpecificQueuePage("");

                fileID = specificQueuePage.fileID.get(0).getText().replaceAll(":", "-");
                basePage.sout("ID скачиваемого сообщения: " + fileID);

                click(specificQueuePage.fileCheckBox.get(0));
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.downloadMessage);
                sleep(5000);
                open(urlDownload);
                DownloadedFileName = downloadedFileName.getText();
                basePage.sout("Файл: " + DownloadedFileName);
                Assert.assertEquals(fileID + ".txt", downloadedFileName.getText());
                break;

            case ("все"):
                searchQueue(managerName, queue);
                click(afterSearchQueueName);
                specificQueuePage = new SpecificQueuePage("");

                fileID = specificQueuePage.fileID.get(0).getText().replaceAll(":", "-");
                basePage.sout("ID скачиваемого сообщения: " + fileID);

                click(specificQueuePage.allFilesCheckBox);
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.downloadMessage);
                sleep(5000);
                open(urlDownload);
                DownloadedFileName = downloadedFileName.getText();
                basePage.sout("Файл: " + DownloadedFileName);
                Assert.assertTrue(downloadedFileName.getText().contains(".zip"));
                break;

            default:
                throw new AssertionError("Пропущено скачивание файла");
        }
        return fileID;
    }

    /**
     * Check content dowloaded file
     */
    @Step("Check content downloaded file")
    public void checkContentDowloadedFile(String SessionID, String Text, String Type, String filePath, SessionId sessionId, String downloadedFileName, String fileDownloadedPath) {
        String fileName = null;
        String contentDownloadedFile = null;
        open("http://192.168.57.240:4444/download/" + SessionID + "/" + DownloadedFileName);
        basePage.sout("http://192.168.57.240:4444/download/" + SessionID + "/" + DownloadedFileName);
        switch (Type) {
            case ("файл"):
                click($x(".//*[text()='forTests.txt']"));
                compareStringAndElementText(Text, downloadedFileText);
                writeContentDowloadedFile(filePath, sessionId, downloadedFileName);
                contentDownloadedFile = basePage.readContentFile(fileDownloadedPath);
                compareStringAndString(Text, contentDownloadedFile);
                break;
            case ("1 без галки"):
            case ("1 с галкой"):
                compareStringAndElementText(Text, downloadedFileText);
                writeContentDowloadedFile(filePath, sessionId, downloadedFileName + ".txt");
                contentDownloadedFile = basePage.readContentFile(filePath);
                compareStringAndString(Text, contentDownloadedFile);
                break;
            case ("все"):
                compareStringAndStringShouldContainsText(downloadedArchiveText.getText(), "zip");
                writeContentDowloadedFile(filePath + downloadedArchiveText.getText(), sessionId, downloadedArchiveText.getText());

                String bashCommandUnzipFile = "unzip /home/fesb/Stand/share/forDownloadedMessagesMulty/" + downloadedArchiveText.getText() + "  -d /home/fesb/Stand/share/forDownloadedMessagesMulty/tmp";
                String shortUrl1 = base.BaseUrl.replace("http://", "").replace(":8181", "");

//                basePage.executeBashCommandToSsh(shortUrl1, 22, "fesb", "fesb2016", bashCommandUnzipFile);
                basePage.executeBashCommand(bashCommandUnzipFile);

                sleep(3000);
                int SumLocalFilesIn = Objects.requireNonNull(new File("/home/fesb/Stand/share/forDownloadedMessagesMulty/tmp").listFiles()).length;
                basePage.sout("В директории /home/fesb/Stand/share/forDownloadedMessages/tmp:  находится " + SumLocalFilesIn + " файлов");
                Assert.assertEquals(3, SumLocalFilesIn);


                contentDownloadedFile = basePage.readContentFile("/home/fesb/Stand/share/forDownloadedMessagesMulty/tmp/" + downloadedFileName + "/" + downloadedFileName + ".txt");
                compareStringAndString(Text, contentDownloadedFile);
                break;
            case ("очередь"):
                click($x(".//*[contains(text(),'" + Text + "')]"));
                compareElementTextShouldContainsText(Text, downloadedFileText);
                writeContentDowloadedFile(filePath, sessionId, downloadedFileName);
                break;
            case ("empty"):
                click($x(".//*[text()='" + downloadedFileName + "']"));
                compareStringAndElementText(Text, downloadedFileText);
                break;
            case ("экспорт менеджера"):
                click($x(".//*[contains(text(),'factor-mq-dump-')]"));
                exportedMQ = downloadedFileText.getText();
                break;
            default:
                throw new AssertionError("Пропущена проверка содержимого скачанного файла");
        }
    }

    /**
     * Move message
     */
    @Step("Move message")
    public void moveMessage(String managerName, String Type, String localOueueName2, String LocalOueueName1, int SumMessage, String text) {
        searchQueue(managerName, localOueueName2);
        click(afterSearchQueueName);
        specificQueuePage = new SpecificQueuePage("");

        String movedMessageID1 = messageID.get(0).getText();
        basePage.sout("Название переносимого файла: " + movedMessageID1);

        Assert.assertEquals(SumMessage, specificQueuePage.tableRowsIDCollon.size());


        switch (Type) {
            case ("Переместить 1"):
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.moveMessage);
//                click(selectQueueForMovedMessage);
                click(queueNameForMovingOneMessagSelect);
                valAndSelectElement(queueNameForMovingOneMessageInput, LocalOueueName1);
                click(buttonMove);
                break;

            case ("Переместить все"):
                click(specificQueuePage.allFilesCheckBox);
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.moveMessage);
//                click(selectQueueForMovedMessage);
                click(queueNameForMovingSeveralMessagSelect);
                valAndSelectElement(queueNameForMovingSeveralMessageInput, LocalOueueName1);
                click(buttonMove);
                break;

            case ("Скопировать 1"):
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.copyMessage);
//                click(selectQueueForCopyedMessage);
                click(queueNameForCopyedOneMessageSelect);
                valAndSelectElement(queueNameForCopyedOneMessageInput, LocalOueueName1);
                click(buttonCopy);
                break;

            case ("Скопировать все"):
                click(specificQueuePage.allFilesCheckBox);
                contextClick(specificQueuePage.tableRowsIDCollon.get(0));
                click(specificQueuePage.copyMessage);
//                click(selectQueueForCopyedMessage);
                click(queueNameForCopyedSeveralMessageSelect);
                valAndSelectElement(queueNameForCopyedSeveralMessageInput, LocalOueueName1);
                click(buttonCopy);
                break;

            default:
                throw new AssertionError("Пропущено действие над сообщением");
        }

        specificQueuePage = new SpecificQueuePage("");
        switch (Type) {
            case ("Переместить 1"):
                Assert.assertEquals(SumMessage - 1, specificQueuePage.tableRowsIDCollon.size());
                break;

            case ("Переместить все"):
                Assert.assertEquals(0, specificQueuePage.tableRowsIDCollon.size());
                break;

            case ("Скопировать 1"):
                Assert.assertEquals(SumMessage, specificQueuePage.tableRowsIDCollon.size());
                break;

            case ("Скопировать все"):
                Assert.assertEquals(SumMessage, specificQueuePage.tableRowsIDCollon.size());
                break;

            default:
                throw new AssertionError("Пропущена проверка количества файлов");
        }

//        if (!queueManagerPage.attr("class").contains("open") || !url().contains("/download/")) {
//            click(queueManagerPage);
//        }
//        queuesList();
        searchQueue(managerName, LocalOueueName1);
        click(afterSearchQueueName);
        specificQueuePage = new SpecificQueuePage("");

        switch (Type) {
            case ("Переместить 1"):
            case ("Скопировать 1"):
                Assert.assertEquals(SumMessage - 2, specificQueuePage.tableRowsIDCollon.size());
                break;

            case ("Переместить все"):
            case ("Скопировать все"):
                click(messagePage.sortByID);

                Assert.assertEquals(SumMessage, specificQueuePage.tableRowsIDCollon.size());
                break;

            default:
                throw new AssertionError("Пропущена проверка количества файлов");
        }

        basePage.sout("Первое переносимое сообщение: " + movedMessageID1);

        int x = 0;
        for (SelenideElement Row : messageID) {
            basePage.sout("Список перенесенных сообщений: " + Row.getText());
            if (movedMessageID1.equals(Row.getText())) {
                x++;
            }
        }
        Assert.assertEquals(1, x);


//            String movedMessageID2 = messageID.getText();
//        Assert.assertEquals(movedMessageID1, movedMessageID2);
        click(messageID.get(0));
        specificMessagePage = new SpecificMessagePage("");
        Assert.assertEquals(specificMessagePage.messageBody.getText(), text);
    }

//    /**
//     * Check moved message
//     */
//    @Step("Check moved message")
//    public void checkMovedMessage(String LocalOueueName1, int SumMessage, String text) {
//        search(LocalOueueName1);
//        click(afterSearchQueueName);
//        specificQueuePage = new SpecificQueuePage();
//        Assert.assertEquals(SumMessage - 2, specificQueuePage.tableRowsIDCollon.size());
//        String movedMessageID2 = messageID.getText();
//        Assert.assertEquals(MovedMessageID1, movedMessageID2);
//        click(messageID);
//        Assert.assertEquals(specificMessagePage.messageBody.getText(), text);
//
//    }

    //==================================================================================Topics=========================================================
    @Step("Creating {0} Topic")
    public void createTopic(String managerName, String topicName) {
        goToManager(managerName);
        click($x(String.format(topicsOfSpecificManagerButton, managerName)));
        click(addTopicButton);
        val(topicNameInput, topicName);
        click(saveButton);
        searchTopic(managerName, topicName);
        afterSearchTableRows.shouldHave(size(1), 3000);
    }

    @Step("Deleting {0} Topic")
    public void deleteTopic(String managerName, String topicName) {
//        goToManager(managerName);
//        click($x(String.format(topicsOfSpecificManagerButton, managerName)));
        searchTopic(managerName, topicName);
        contextClick($x(String.format(afterSearchTopicNameS, topicName)));
        click(deleteTopic);
        click(confirmationDeleteButton);
        searchTopic(managerName, topicName);
        afterSearchTableRows.shouldHave(size(0), 3000);
    }

    /**
     * Check a number of messages in sent
     */
    @Step("Topic {0} should has {1} messages")
    public void topicShouldHaveNMessagesInSent(String managerName, String topicName, Integer messagesInSent) {
//        goToManager(managerName);
//        click($x(String.format(topicsOfSpecificManagerButton, managerName)));
//        click(autoUpdatePageSOPSEmptyCheckBox);
        searchTopic(managerName, topicName);
        $x(String.format(afterSearchTopicSendS, topicName)).waitUntil(text(messagesInSent.toString()), 5000);
        click(autoUpdatePageSOPSEmptyCheckBox);
    }


    //==================================================================================QM=========================================================


    @Step("Click {1} option in context menu {0} Topic")
    public void clickOptionInContextMenu(String managerName, String topicName, String optionName) {
        goToManager(managerName);
        click($x(String.format(topicsOfSpecificManagerButton, managerName)));
        searchTopic(managerName, topicName);
        contextClick($x(String.format(afterSearchTopicNameS, topicName)).should(text(topicName)));
        click($$x(String.format(ContextMenuElementXpath, optionName)).find(visible));
    }

    public void renameMQ(String newName) {
        click(configuration);
        val(brokerNameInput, newName);
        click(saveQmButton);
        commonComponents.closeNotification();
//        refresh();
        click(commonComponents.reloadButton);
        loginPage.loginInput.waitUntil(visible, 300000);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        $x(String.format(".//input[@value=\"%s\"]", newName)).shouldBe(visible);
    }

    public void checkTransactionMonitor(ArrayList<String> eventList1) {
        click(basePage.transactionMonitorTab);
        click(transactionMonitorPage.expandRowAfterSearchButton);

        for (int x = 0; x < eventList1.size(); x++) {
            isElementNotEmpty($$x(String.format(transactionMonitorPage.infoAboutCellEvent, x + 1)).get(0));
            isElementNotEmpty($$x(String.format(transactionMonitorPage.infoAboutCellEvent, x + 1)).get(1));
            compareStringAndElementText(eventList1.get(x).split("-")[0], $$x(String.format(transactionMonitorPage.infoAboutCellEvent, x + 1)).get(2));
            compareStringAndElementText(eventList1.get(x).split("-")[1], $$x(String.format(transactionMonitorPage.infoAboutCellEvent, x + 1)).get(3));
        }
    }

    public void createConnectorWithoutSave(String managerName, String connectorName, String connectorProtocol, String connectorIp, String connectorPort, String[] connectorParameters) {
        goToManager(managerName);
        click(receiversMultyTab);
        click(addConnectorButton);
        val(connectorNameInput, connectorName);
        click(connectorProtocolActivate);
        valAndSelectElement(connectorProtocolInput, connectorProtocol);
        val(connectorIpInput, connectorIp);
        val(connectorPortInput, connectorPort);
        for (int x = 0; x < connectorParameters.length; x++) {
            click(addButton2);
            click(connectorParametersNameActivate.get(x));
            valAndSelectElement(connectorParametersNameInput.get(x), connectorParameters[x].split("\\|")[0]);
            val(connectorParametersValueInput.get(x), connectorParameters[x].split("\\|")[1]);
        }
    }

    public void successCreateConnector(String managerName, String connectorName, String connectorProtocol, String connectorIp, String connectorPort, String[] connectorParameters) {
        createConnectorWithoutSave(managerName, connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
        click(saveButton);
    }

    public void cancelCreateConnector(String managerName, String connectorName, String connectorProtocol, String connectorIp, String connectorPort, String[] connectorParameters) {
        createConnectorWithoutSave(managerName, connectorName, connectorProtocol, connectorIp, connectorPort, connectorParameters);
        closeForm();
    }

    public void editConnectorWithoutSave(String managerName, String connectorName, String connectorNameNew, String connectorProtocolNew, String connectorIpNew, String connectorPortNew, String[] connectorParametersNew) {
        goToManager(managerName);
        click(receiversMultyTab);
        searchConnector(connectorName);
        contextClick(afterSearchConnectorName);
        click(editInContextMenu);
        val(connectorNameInput, connectorNameNew);
        click(connectorProtocolActivate);
        valAndSelectElement(connectorProtocolInput, connectorProtocolNew);
        val(connectorIpInput, connectorIpNew);
        val(connectorPortInput, connectorPortNew);
        for (int x = 0; x < connectorParametersNew.length; x++) {
            click(connectorParametersNameActivate.get(x));
            valAndSelectElement(connectorParametersNameInput.get(x), connectorParametersNew[x].split("\\|")[0]);
            val(connectorParametersValueInput.get(x), connectorParametersNew[x].split("\\|")[1]);
        }
//        val(connectorParametersInput, connectorParametersNew);

    }

    public void successEditConnector(String managerName, String connectorName, String connectorNameNew, String connectorProtocolNew, String connectorIpNew, String connectorPortNew, String[] connectorParametersNew) {
        editConnectorWithoutSave(managerName, connectorName, connectorNameNew, connectorProtocolNew, connectorIpNew, connectorPortNew, connectorParametersNew);
        click(saveButton);
    }

    public void cancelEditConnector(String managerName, String connectorName, String connectorNameNew, String connectorProtocolNew, String connectorIpNew, String connectorPortNew, String[] connectorParametersNew) {
        editConnectorWithoutSave(managerName, connectorName, connectorNameNew, connectorProtocolNew, connectorIpNew, connectorPortNew, connectorParametersNew);
        closeForm();
    }

    public void deleteConnector(String managerName, String connectorName) {
        goToManager(managerName);
        click(receiversMultyTab);
        searchConnector(connectorName);
        contextClick(afterSearchConnectorName);
        click(deleteInContextMenu);

    }

    public void successDeleteConnector(String managerName, String connectorName) {

        deleteConnector(managerName, connectorName);
        click(confirmationDeleteButton);
    }

    public void cancelDeleteConnector(String managerName, String connectorName) {
        deleteConnector(managerName, connectorName);
        closeForm();
    }

    @Step("Check all parameters Connector {0}")
    public void connectorCheckAllParameters(String managerName, String connectorName, String connectorProtocol, String connectorIp, String connectorPort, String[] connectorParameters) {
        goToManager(managerName);
//        if (!queueManagerTab.attr("class").contains("active"))
//            click(queueManagerTab);
        if (!receiversMultyTab.attr("class").contains("active"))
            click(receiversMultyTab);
        searchConnector(connectorName);
        compareStringAndElementText(connectorName, afterSearchConnectorName);
        compareStringAndElementText(connectorProtocol.toUpperCase(Locale.US), afterSearchConnectorProtocol);
        compareStringAndElementText(connectorIp, afterSearchConnectorAdress);
        compareStringAndElementText(connectorPort, afterSearchConnectorPort);

        click(afterSearchConnectorExpandButton);
        for (int x = 0; x < connectorParameters.length; x++) {
            if (!connectorParameters[x].split("\\|")[0].equals("null")) {
//                compareStringAndElementText(connectorParameters[x].split("\\|")[0], connectorConnectionInTable);
                connectorConnectionInTable.shouldNotBe(empty);
                connectorClientInTable.shouldNotBe(empty);
                connectorTransactionInTable.shouldNotBe(empty);
                connectorUserInTable.shouldNotBe(empty);
            }
            if (!connectorParameters[x].split("\\|")[1].equals("null")) {
                compareStringAndElementText(connectorParameters[x].split("\\|")[1], connectorUserInTable);
            }
        }
    }

    @Step("Searching connector {0}")
    public void searchConnector(String connectorName) {
        if (!url().contains("/transport-connectors")) {
            click(receiversMultyTab);
        }
        val(searchInput, connectorName);
    }

    @Step("Searching connector {0}")
    public void connectorShouldNotExist(String managerName, String connectorName) {
        goToManager(managerName);
        click(receiversMultyTab);

        val(searchInput, connectorName);
        afterSearchConnectorName.shouldHave(exactText("Нет данных"));
    }

    @Step("Searching connector {0}")
    public void searchConnectorParameter(String parameterName) {
        click(searchConnectorParametrIcon);
        val(searchParameterNameInput, parameterName);
        click(searchNameButton);
    }

    @Step("Searching connector {0}")
    public void connectorShouldNotHaveStatistic(String managerName, String connectorName) {
        goToManager(managerName);
        click(receiversMultyTab);
        val(searchInput, connectorName);
        afterSearchConnectorExpandButton.shouldNotBe(visible);
    }

    @Step("Searching connector {0}")
    public void checkSettingOfShedullerManager(String jvmHeapUsage) {
//        click(queueManagersTab);
        goToManager("QM");
        click($x(String.format(settingManagerMultyString, "QM")));
        click(mainSettingManagerMultyTab);
        compareStringAndString(jvmHeapUsage, statusOfShedullerCheckBox.attr("Class"));
    }

    @Step("Setting remote access {0}")
    public void settingRemoteAccess(String state, String connectJms, String userJms, String passwordJms, String connectJmx, String userJmx, String passwordJmx) {
        click(queueManagerTab);
        click(remoteAccessTab);
        if (state.equals("on") && !remoteAccessSwitchOnCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(remoteAccessSwitchOnCheckBox);
        } else if (state.equals("off") && remoteAccessSwitchOnCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(remoteAccessSwitchOnCheckBox);
        }
        val(connectJmsInput, connectJms);
        val(userJmsIput, userJms);
        val(passwordJmsInput, passwordJms);
        val(connectJmxInput, connectJmx);
        val(userJmxInput, userJmx);
        val(passwordJmxInput, passwordJmx);
        click(saveButton);
    }

    @Step
    public void viewExport() {
        click(queueManagerTab);
        click(commandInterfaceTab);
        click(exportTab);
        click(exportButon);
    }

    @Step
    public void exportInFile(String managerName) {
        goToManager(managerName);
//        click(queueManagerTab);
        click($x(String.format(settingManagerMultyString, managerName)));
        click(commandInterfaceMultyTab);
        click(exportMultyButton);
        click(exportInFileMultyButon);
    }

    @Step
    public void executeCommand(String managerName, String command) {
        sout(command);
//        click(queueManagerTab);
        goToManager(managerName);
        click($x(String.format(settingManagerMultyString, managerName)));
        click(commandInterfaceMultyTab);
//        click(executeTab);
        valInTextareaWithCodemirror(command);
        click(executeButton);
//        successNotification.shouldBe(visible);
    }

    public void createUser(String managerName, String userName, String userPassword, String[] userGroups) {
        goToManager(managerName);
        click($x(String.format(managerSettingOfSpecificManagerButton, managerName)));
        click(groupsAndusersTab);
        click(addUserButton);
        val(userNameInput, userName);
        val(userPasswordInput, userPassword);
        click(userGroupsActivate);
        for (String role : userGroups) {
            valAndSelectElement(userGroupsInput, role);
        }
        click(modalWindow);
        click(saveButton);
    }

    public void checkAllParametersUser(String managerName, String userName, String[] userGroups) {
        searchUser(managerName, userName);
        compareStringAndString(userName, userNameInTable.getText());
        compareStringAndString(Arrays.toString(userGroups).replace("[", "").replace("]", ""), userGroupInTable.getText());
    }

    @Step("Searching connector {0}")
    public void checkSettingQueueManager(String jvmHeapUsage) {
        click(queueManagerTab);
        click(settingManagerTab);
        compareStringAndElementValue(jvmHeapUsage, jvmHeapAcceptableUsage);
    }

    public void checkModeQM (String managerName, String ExpactedModeQm){
        goToManager(managerName);
        click($x(String.format(settingManagerMultyString, managerName)));
        modeMq.shouldHave(Condition.exactText(ExpactedModeQm));
    }
}
