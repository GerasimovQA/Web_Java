package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CreationSOPSPage extends BasePage {
    String addComponentEnterFromList = ".//h3[text()='Добавить точку входа']/following-sibling::div//a[text()='%s']";
    String addComponentExitFromList = ".//h3[text()='Добавить точку обработки']/following-sibling::div//a[text()='%s']";
    String addComponenInTreatmentFromList = ".//h3[text()='Добавить точку обработки для компонента \"%s\"']/following-sibling::div//a[text()='%s']";
    String addComponenInTreatmentFromListWithoutHeader = ".//a[text()='%s']";
    String nameComponentInPointEnter = "(.//header[text()='Точки входа']/../following-sibling::li//span[text()='%s'])[last()]";
    String nameComponentInPointExit = "(.//header[text()='Обработка']/../following-sibling::li//span[text()='%s'])[last()]";

    String parameterQueueInList = "//div[@class=\"Select-menu-outer\"]//span[text()='%s']";
    String policyFromTransactionalityPolicyList = "//div[@class=\"Select-menu-outer\"]//div[text()='%s']";
    String reportName = "//div[@class=\"Select-menu-outer\"]//div[text()='%s']";
    String loggerFromLoggersList = "//div[@class=\"Select-menu-outer\"]//div[text()='%s']";
    String loggerTabInSops = "//span[text()='%s']/../..";
    String deleteLoggerTabInSopsButton = "//span[text()='%s']/preceding-sibling::div/i[@class=\"fa fa-fw fa-trash\"]";
    String namePointInSops = "//span[@class=\"endpoint-item-uri\"][text()='%s']";
    String nameComponentPointInSops = "//span[@class=\"endpoint-item-name\"][text()='%s']";
    String checkBoxOfParameter = ".//span[text()='%s']/preceding-sibling::span/input";
    String checkBoxOfParameter2 = ".//span[text()='%s']/preceding-sibling::span";
    String addPointOfTreatment = ".//span[text()='%s']/../..//span[text()='Добавить']/..";
    String selectPointOfTreatment = ".//span[text()='%s']/../..//button[text()='Выбрать']";
    String originalModelColumn = "(.//div[@class='ant-tree-list'])[1]//b[text()='%s']";
    String targetModelColumn = "(.//div[@class='ant-tree-list'])[2]//b[text()='%s']";
    String pointInLeftMenu = ".//span[text()='%s']";

    CommonComponents commonComponents = new CommonComponents();
    BasePage basePage = new BasePage();
    SOPSPage sopsPage;
    SelenideElement uploadSpecificationSwaggerAndAdrressWsdl = $x(".//span[@class=\"factor-form-resource-uri-select__upload\"]//input[@type=\"file\"]");
    SelenideElement notificationPreserved = $x(".//div[text()='Сохранение...']"),
            returnToSOPSList = $x(".//span[@title=\"Назад к домену\"]"),
            addTreatmentButton = $x(".//header[text()='Обработка']/following-sibling::span//span[text()='Добавить']/.."),

    addOutputPointButton = $x(".//div[div[text()='Точка выхода']]//button"),
    //            addTreatmentButton = $x(".//header[text()='Обработка']/..//button"),
    searchComponent = $x(".//input[@placeholder=\"Поиск элемента\"]"),
            addInputPointButton = $x(".//header[text()='Точки входа']/..//button"),
            nameComponentInput = $x(".//label[text()='Имя компонента']/../following-sibling::div//input"),
            sendSavedMessageButton = $x(".//button[@title=\"Отправить сообщение\"]"),
            sopsNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            descriptionSopsInput = $x(".//label[text()='Описание']/../following-sibling::div//textarea"),
            sopsPriorityInput = $x(".//label[text()='Приоритет']/../following-sibling::div//input"),
            errorHandlingInsideSopsCheckbox = $x(".//span[text()='Обработка ошибок внутри СОПС']/../..//label/span[contains(@class,\"ant-checkbox\")]"),
            autostartSopsCheckbox = $x(".//span[text()='Автоматически запускать СОПС при запуске домена']/../..//label/span[contains(@class,\"ant-checkbox\")]"),
            streamCachingCheckbox = $x(".//span[text()='Кэширование потока']/../..//label/span[contains(@class,\"ant-checkbox\")]"),
            tracingCheckbox = $x(".//span[text()='Трассировка']/../..//label/span[contains(@class,\"ant-checkbox\")]"),
    //            transactionalityCheckbox = $x(".//span[text()='Транзакционность']/../..//label/span[contains(@class,\"ant-checkbox\")]"),
    transactionPropagationPolicyActivate = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div"),
            transactionPropagationPolicyInput = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div//input"),
            deleteButton = $x(".//span[text()='Удалить']/.."),
            typeConditionSelect = $x(".//select[@class=\"form-control input-sm\"]"),
            conditionInput = $x(".//label[text()=\"Условие\"]/../..//input"),
            editComponentHeader = $x(".//div[@class=\"endpoint-edit-panel\"]//h3"),
    //Parameter
    parameterNameSelect = $$x(".//div[text()='Добавление параметра']/../..//div[@name=\"nameEn\"]").find(visible),
            parameterInput = $$x(".//div[text()='Добавление параметра']/../..//div[@name=\"nameEn\"]//input").find(visible),
            parameterNameInput = $$x(".//label[text()='Имя']/../following-sibling::div//input").find(visible),
            parameterValueInput2 = $$x(".//label[text()='Значение']/../following-sibling::div//input").find(visible),
            parameterValueActivate = $$x(".//div[text()='Значение']/following-sibling::div").find(visible),
            parameterValueInput = $$x(".//div[text()='Значение']/following-sibling::div//input").find(visible),
            addParameterButton = $x(".//span[text()='Добавить параметр']/.."),
            parameterValueCheckbox = $x(".//div[text()='Значение']/..//label/span[contains(@class,\"ant-checkbox\")]"),
            parameterValueSelect = $x(".//div[text()='Значение']/following-sibling::div//div/div"),
            fewParameterValueSelect = $x(".//li[@class=\"ant-select-search ant-select-search--inline\"]"),
    //            parameterValueInput = $x(".//div[text()='Значение']/following-sibling::div//input"),
    parameterSelect = $x(".//div[text()='Значение']/following-sibling::div//div[@class=\"ant-col ant-form-item-control-wrapper\"]"),
            sizeOfFragmentInput = $x(".//label[text()=\"Размер фрагмента\"]/../following-sibling::div//input"),
            selectParameterInput = $$x("(//input[@role=\"combobox\"])[2]").find(visible),
            activateParameterCheckBox = $x(".//div[text()='Значение']/following-sibling::div/input"),
            saveParameterButton = $$x(".//button[@type='submit']").find(visible),

    //Modules for adding in SOPS

    wmq = $x(".//a[text()='IBM MQ']"),
            localQueue = $x(".//a[text()='Локальная очередь']"),
            localFile = $x(".//a[text()='Локальный файл']"),
            streaming = $x(".//a[text()='Потоковая передача']"),
            http = $x(".//a[text()='HTTP']"),
            exitInRoutingTable = $x(".//a[text()='Выход в таблицу маршрутов']"),
            xsltTransformation = $x(".//a[text()='XSLT Трансформация']"),
            formatConversion = $x(".//a[text()='Преобразование форматов']"),
            softwareTransformation = $x(".//a[text()='Программная трансформация']"),
            logging = $x(".//a[text()='Логирование']"),
            nameSoftwareTransformationInput = $x(".//input[@name=\"customName\"]"),
            syntaxSoftwareTransformationSelect = $x(".//select[@name=\"language\"]"),
            sourceSyntaxSoftwareTransformationSelect = $x(".//select[@name=\"source\"]"),
            codeSoftwareTransformationInput = $x(".//div[@class=\"ace_content\"]"),
            fileCodeSoftwareTransformationUpload = $x(".//div[@class=\"Select-control\"]//input"),
            linkToSops = $x(".//a[text()='Ссылка на СОПС']"),
            checkBoxGlobal = $x(".//span[text()='Глобальная']/preceding-sibling::span"),

    rollBack = $x(".//a[text()='Откат транзакции']"),
            messageInput = $x(".//label[text()=\"Сообщение\"]/../..//input"),

    enrichment = $x(".//a[text()='Обогащение']"),
            installHeadings = $x(".//a[text()='Установить заголовки']"),
            clearHeaders = $x(".//a[text()='Очистить заголовки']"),
            patternOfClearHeadersInput = $x(".//label[text()='Паттерн']/../following-sibling::div//input"),
            selectEnrichment = $x(".//span[text()='Выбрать']/.."),
            outputPoint = $$(byText("Точка выхода")).filterBy(cssClass("component-menu-item")).find(visible),

    moduleSelect = $x(".//label[text()=\"Модуль\"]/../..//input/../../.."),
            moduleInput = $x(".//label[text()=\"Модуль\"]/../..//input"),
            mqSelect = $x(".//label[text()=\"Менеджер очередей\"]/../..//input/../../.."),
            mqInput = $x(".//label[text()=\"Менеджер очередей\"]/../following-sibling::div//div[@name=\"broker\"]//input"),
            queueNameSelect = $x(".//label[text()=\"Имя очереди\"]/../..//input/../../.."),
            queueNameActivate = $x(".//label[text()=\"Имя очереди\"]/../..//input/.."),
            queueNameInput = $x(".//label[text()=\"Имя очереди\"]/../..//input"),
            localPathFileInput = $x(".//label[text()=\"Путь\"]/../..//input"),
            nameLinkInput = $x(".//label[text()='Имя ссылки']/../following-sibling::div//input"),
            nameComponentLoggingInput = $x(".//label[text()='Имя компонента']/../following-sibling::div//input"),
            messageOfLoggerInput = $x(".//label[text()='Сообщение логирования']/../following-sibling::div//input"),
            levelOfLoggerActivate = $x(".//label[text()='Уровень логирования']/../following-sibling::div"),
            levelOfLoggerInput = $x(".//label[text()='Уровень логирования']/../following-sibling::div//input"),
            nameOfLoggerActivate = $x(".//label[text()='Имя логгера']/../following-sibling::div"),
            nameOfLoggerInput = $x(".//label[text()='Имя логгера']/../following-sibling::div//input"),

    wmqNameInput = $x(".//input[@name=\"uri\"]"),

    //Options
    backToDomainIcon = $x(".//span[@title=\"Назад к домену\"]"),
            saveIcon = $x(".//span[@aria-label=\"save\"]"),
            commentInput = $x(".//label[text()='Комментарий']/../following-sibling::div//input"),
            saveCommentButton = $x(".//div[text()='Добавить комментарий к изменениям']/../..//span[text()='Сохранить']/.."),
            sopsPropertiesIcon = $x(".//span[@aria-label=\"setting\"]"),
            sopsHistoryIcon = $x(".//span[@aria-label=\"history\"]"),
            sendSavedMessageIcon = $x(".//span[@title=\"Отправить сохраненное сообщение\"]"),

    //SOPS properties menu
    transactionalityCheckbox = $x(".//span[text()='Транзакционность']/preceding-sibling::span"),
            transactionalityPolicySelect = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div"),
            transactionalityPolicyInput = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div//input"),

    flowCachingCheckBox = $x(".//span[text()='Кэширование потока']/preceding-sibling::span"),
            propertiesOKButton = $x(".//button[span[text()='ОК']]"),
            errorHandlerActivate = $x(".//label[text()='Обработчик ошибок']/../following-sibling::div"),
            errorHandlerInput = $x(".//label[text()='Обработчик ошибок']/../following-sibling::div//input"),

    //Exit in the routing table
    editRoutingTableButton = $x(".//div[text()=' Редактировать таблицу']"),
    //Routing table
    useExistingRoutingTableRadio = $x(".//label[text()='Использовать существующую таблицу маршрутов']/preceding-sibling::input[1]"),
            createNewRoutingTableRadio = $$x(".//label[text()='Создать новую таблицу маршрутов']/preceding-sibling::input").find(visible),
            routingTableSelect = $x(".//label[text()='Таблица маршрутов']/../following-sibling::div//input/.."),
            routingTableInput = $x(".//label[text()='Таблица маршрутов']/../following-sibling::div//input"),
            editTableLink = $x(".//div[text()=' Редактировать таблицу']"),
            useDafaultRouteCheckbox = $x(".//span[text()='Использовать маршрут по умолчанию']/preceding-sibling::span"),
            defaultRouteURLInput = $$x(".//input[@name=\"defaultRouteURL\"]").find(visible),
            addRuleForRouteButton = $$x(".//span[text()='Добавить правило']/..").find(visible),
            routingRuleInput = $$x(".//input[contains(@name,\"rule\")]").find(visible),
            redirectionURLInput = $$x(".//input[contains(@name,\"url\")]").find(visible),
            addRouteConfirmationButton = $$x(".//div[div[h4[text()='Редактирование маршрута']]]//div[@class=\"modal-footer\"]/button[1]").find(visible),
            addRoutingTableConfirmationButton = $$x(".//div[div[h4[text()='Таблица маршрутов']]]//div[@class='modal-footer']/button[1]").find(visible),
            routeByNextValueInput = $x(".//div[label[text()='Маршрутизировать по следующему значению']]/following-sibling::div/div/input"),
            useOnlyFirstSuitableRouteCheckbox = $x(".//label[text()='Использовать только первый подходящий маршрут']/input"),
            routingTableListSelect = $x(".//select[option[text()='Значение не выбрано']]"),
            saveRoutingTableButton = $x(".//span[text()='Сохранить таблицу маршрутов']/.."),
            returnToComponentButton = $x(".//span[text()='Вернуться к компоненту']/.."),

    //Types of Treatment
    //XSLT
    transformationTextAria = $$x(".//*[@id='brace-editor']/textarea").find(visible),
    //Install headins
    headingsNameInput = $x(".//input[@name=\"name\"]"),
            sintaxActivate = $x("(.//div[@model]//input[@role=\"combobox\"])[1]/../.."),
            sintaxInput = $x("(.//div[@model]//input[@role=\"combobox\"])[1]"),
            typeActivate = $x("(.//div[@model]//input[@role=\"combobox\"])[2]/../.."),
            typeInput = $x("(.//div[@model]//input[@role=\"combobox\"])[2]"),
            expressionInput = $x(".//label[text()='Выражение']/../following-sibling::div//textarea"),
    //Enrichment
    sendDataAndWaitForResponseCheckBox = $x(".//div[label[text()='Передать данные и ожидать ответ']]/following-sibling::div/input"),
            urlNameInput = $x(".//input[@name='uri']"),
            inOrOutSelect = $x(".//div[div[label[contains(text(), \"Использовать для обогащения\")]]]//select"),
    //Clear Headings
    patternInput = $x(".//input[@name=\"pattern\"]"),
    //Output point
    typePointSelect = $x(".//div[div[label[contains(text(), \"Тип точки\")]]]//select"),
            treatmentOutputPointQueueName = $x(".//div[div[label[contains(text(), 'Название очереди')]]]//input"),
            maxNumMessagePerPeriodInput = $x(".//label[text()='Максимум сообщений за период']/../following-sibling::div//input"),
            timeOfOnePeriodInput = $x(".//label[text()='Время одного периода (мс)']/../following-sibling::div//input"),
    //Format conversion
    conversionTypesActivate = $x(".//label[text()=\"Тип преобразования:\"]/../following-sibling::div"),
            conversionTypesInput = $x(".//label[text()=\"Тип преобразования:\"]/../following-sibling::div//input"),
            inputFormatSelect = $x(".//div[div[text()='Входной формат']]//select"),
            outputFormatSelect = $x(".//div[div[text()='Выходной формат']]//select");
    //Edit SOPS
    public ElementsCollection listPointsInSops = $$x(".//li[@draggable=\"true\"]//span[@class=\"endpoint-item-name\"]");
    public ElementsCollection sopsRowInHistory = $$x(".//h3[text()='История изменений']//../following-sibling::div//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]");
    public ElementsCollection listOfPoints = $$x(".//div[@class=\"endpoint-list\"]//a[text()]");
    SelenideElement lastEndPointInSops = $x(".//header[text()='Обработка']/../following-sibling::li[last()]//span[@class=\"endpoint-item-name\"]");
    ElementsCollection addTryButtons = $$x("..//span[text()='Попытка']/../..//button[text()='Добавить']");
    ElementsCollection listRoutingRuleInput = $$x(".//input[contains(@name,\"rule\")]");
    ElementsCollection listRoutingUrlInput = $$x(".//input[contains(@name,\"url\")]");
    SelenideElement nameComponentInSops = $x(".//input[@id=\"name\"]");
    SelenideElement nameQueueInSops = $x(".//label[text()=\"Имя очереди\"]/../following-sibling::div//div[@class=\"ant-select-selector\"]/span[2]/div");
    SelenideElement nameQueueInOffSops = $x("//label[text()=\"Имя очереди\"]/../following-sibling::div//span[@title]");
    SelenideElement pathXsdPatternInEditSops = $x(".//label[text()=\"XSD шаблон\"]/../following-sibling::div//span[@role=\"option\"]");
    SelenideElement syntaxSoftwareTransformationSelectInEditSops = $x(".//label[text()='Синтаксис']/../..//span[@title]");
    SelenideElement sourceToComponentSelectInEditSops = $x(".//label[text()=\"Источник\"]/../following-sibling::div//span[@title]");
    SelenideElement pathToComponentSelectInEditSops = $x(".//label[text()=\"Выберите файл\"]/../following-sibling::div//span[@title]");
    SelenideElement tableOfRoutsInEditSops = $x(".//label[text()=\"Таблица маршрутов\"]/../following-sibling::div//span[@title]");
    SelenideElement addClassExceptionButton = $x(".//i[@class=\"fa fa-fw fa-plus\"]/ancestor::button[@class=\"btn btn-sm btn-success\"]");
    SelenideElement loadMethodsButton = $x(".//span[text()='Загрузить методы']/../..//button");
    SelenideElement transformationTwoModelsButton = $x(".//span[@aria-label=\"swap\"]/..");
    SelenideElement addInPointButton = $x(".//h3[contains(text(),'Редактирование компонента')]/following-sibling::form//span[text()=\"Добавить\"]/..");
    SelenideElement fileNotFoundedInSopsMessage = $x(".//div[text()='Файл не найден']");
    SelenideElement copyInContextMenu = $x(".//div[contains(@style,\"position\")][1]//span[text()='Копировать']");
    SelenideElement insertBeforeInContextMenu = $x(".//div[contains(@style,\"position\")][1]//span[text()='Вставить перед']");

    ElementsCollection listTypeOfRuleActivate = $$x(".//th[text()='Тип']/../../..//span[@title][text()]");


    public enum Point {INPUT, OUTPUT}

    public enum RoutingTable {EXISTING, NEW}

    public enum SyntaxSoftwareTransformation {JavaScript, Groovy}

    public enum SourceSoftwareTransformation {MANUAL_INPUT, FILE}

    public enum ParameterType {checkBox, input, select}

    public enum Parameter {disableReplyTo, matchOnUriPrefix, bridgeEndpoint}

    public enum ParameterWMQ {sessionFactory, expiry, timeout, replyToQueueManagerName, replyToQueueName, report}

    public enum transactionalityPolicy {JMS_TRANSACTED, PROPAGATION_REQUIRED, PROPAGATION_REQUIRES_NEW, MQMTransactionPolicy}

    public enum propertySOPS {autostartOn, autostartOff, errorHandlingInsideSopsOn, errorHandlingInsideSopsOff, streamCachingOn, streamCachingOff, tracingOn, tracingOff, transactionalityOn, transactionalityOff}

    public CreationSOPSPage() {
        returnToSOPSList.shouldBe(visible);
    }

    public CreationSOPSPage(String empty) {
    }

    @Step("Creation SOPS with two local queues")
    public void createSOPSWithTwoLocalQueues(String sopsName, String moduleName, String mq, String localQueueInName, String localQueueOutName, String parameterName, Boolean parameterSet) {
        inputSOPSName(sopsName);
        addLocalQueueInInputOrOutputPoint(Point.INPUT, moduleName, mq, localQueueInName, parameterName, parameterSet);
        addLocalQueueInInputOrOutputPoint(Point.OUTPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        click(saveIcon);
        val(commentInput, "Комментарий 1");
        click(saveCommentButton);
    }

    @Step("Creation SOPS with local file and local queue")
    public void createSOPSWithLocalFileAndLocalQueue(String sopsName, String moduleName, String mq, String localFileInPath, String localQueueOutName, String parameterName, Boolean parameterSet) {
        inputSOPSName(sopsName);
        addLocalFileInInputOrOutputPoint(Point.INPUT, localFileInPath);
        addLocalQueueInInputOrOutputPoint(Point.OUTPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        click(saveIcon);
        val(commentInput, "Комментарий 1");
        click(saveCommentButton);
        click(returnToSOPSList);
    }

    @Step("Creation SOPS with local file and local queue")
    public void createSOPSWithLocalQueueAndLocalFile(String sopsName, String moduleName, String mq, String localFileInPath, String localQueueOutName, String parameterName, Boolean parameterSet) {
        inputSOPSName(sopsName);
        addLocalQueueInInputOrOutputPoint(Point.INPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        addLocalFileInInputOrOutputPoint(Point.OUTPUT, localFileInPath);
        click(saveIcon);
        val(commentInput, "Комментарий 1");
        click(saveCommentButton);
        click(returnToSOPSList);
    }

    @Step("Creation SOPS with streaming and local queue")
    public void createSOPSWithSreamingAndLocalQueue(String sopsName, String moduleName, String mq, String localFileInPath, String localQueueOutName, String parameterName, Boolean parameterSet) {
        inputSOPSName(sopsName);
        addStreamingInInputOrOutputPoint(Point.INPUT, localFileInPath);
        addLocalQueueInInputOrOutputPoint(Point.OUTPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        click(saveIcon);
        val(commentInput, "Комментарий 1");
        click(saveCommentButton);
        click(returnToSOPSList);
    }

    @Step("Creation SOPS with streaming and local queue")
    public void createSOPSWithLocalQueueAndSreaming(String sopsName, String moduleName, String mq, String localFileInPath, String localQueueOutName, String parameterName, Boolean parameterSet) {
        inputSOPSName(sopsName);
        addStreamingInInputOrOutputPoint(Point.OUTPUT, localFileInPath);
        addLocalQueueInInputOrOutputPoint(Point.INPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        click(saveIcon);
        val(commentInput, "Комментарий 1");
        click(saveCommentButton);
        click(returnToSOPSList);
    }

    @Step("Creation SOPS with local file and local queue")
    public void createSOPSWithLinkToSopsAndLocalQueue(String nameDomain, String moduleName, String mq, String sopsName, String localFileInPath, String localQueueOutName, String parameterName, Boolean parameterSet) {
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(nameDomain);
        click(sopsPage.addSOPSButton);
        inputSOPSName(sopsName);
        addLinkToSopsInInputOrOutputPoint(Point.INPUT, localFileInPath);
        addLocalQueueInInputOrOutputPoint(Point.OUTPUT, moduleName, mq, localQueueOutName, parameterName, parameterSet);
        click(saveIcon);
        val(commentInput, "");
        click(saveCommentButton);
        click(returnToSOPSList);
    }

    @Step("Send {0} saved messages in SOPS")
    public void sendNSavedMessages(int messagesNumber) {
        for (int i = 0; i < messagesNumber; i++) {
            click(sendSavedMessageButton);
            commonComponents.closeNotification();
        }
    }

    @Step("Adding local queue - {1} in SOPS {0} point")
    public void addLocalQueueInInputOrOutputPoint(Point point, String moduleName, String mq, String queueName, String parameterName, Boolean parameterSet) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
        }
        click(localQueue);
//        click(moduleSelect);
//        valAndSelectElement(moduleInput, moduleName);
        if (mq != null) {
            click(mqSelect);
            valAndSelectElement(mqInput, mq);
        }
        click(queueNameSelect);
        valAndSelectElement(queueNameInput, queueName);
        if (parameterName != null && parameterSet != null) addParameter(parameterName, parameterSet);
    }

    @Step("Adding parameter to local queue - {0}")
    public void addParametrToQueue(String parameter) {
        click(addParameterButton);
        val(selectParameterInput, parameter);
        click($x(String.format(parameterQueueInList, parameter)));
        if (activateParameterCheckBox.isDisplayed()) click(activateParameterCheckBox);
        click(saveParameterButton);
    }

    @Step("Add transactions to local queue")
    public void addTransactionsToConfigSops(transactionalityPolicy policy) {
        click(sopsPropertiesIcon);
        click(transactionalityCheckbox);
        click(transactionalityPolicySelect);
        if (policy.equals(transactionalityPolicy.MQMTransactionPolicy)) {
            valAndSelectElement(transactionalityPolicyInput, "MQMTransactionPolicy");
//            click($x(String.format(policyFromTransactionalityPolicyList, "MQMTransactionPolicy")));
        }
    }

    @Step("Add logger")
    public void addLoggerToConfigSops(String nameLogger, String messageOfLogger, String level, String nameOfLogger) {
        click(addTreatmentButton);
        click(logging);
        valAndSelectElement(nameComponentLoggingInput, nameLogger);
        valAndSelectElement(messageOfLoggerInput, messageOfLogger);
        click(nameOfLoggerActivate);
        valAndSelectElement(nameOfLoggerInput, nameOfLogger);
//        nameOfLoggerSelect.selectOption(nameLogger);
        click(levelOfLoggerActivate);
        valAndSelectElement(levelOfLoggerInput, level);
//        levelOfLoggerInput.selectOption(level);

//        click(nameOfLoggerSelect);
//        click($x(String.format(loggerFromLoggersList, nameOfLogger)));
    }

    @Step("Edit logger")
    public void editLoggerToConfigSops(String oldMessageOfLogger, String oldLevel, String oldNameOfLogger,
                                       String newMessageOfLogger, String newLevel, String newNameOfLogger) {
        click($x(String.format(loggerTabInSops, oldNameOfLogger)));
        Assert.assertEquals(oldNameOfLogger, nameComponentLoggingInput.attr("value"));
        valAndSelectElement(nameComponentLoggingInput, newNameOfLogger + "123");

        Assert.assertEquals(oldMessageOfLogger, messageOfLoggerInput.attr("value"));
        valAndSelectElement(messageOfLoggerInput, newMessageOfLogger);

//        Assert.assertEquals("ru.factorts.manager.FesbServer", nameOfLoggerInput.getSelectedText());
        click(nameOfLoggerActivate);
        valAndSelectElement(nameOfLoggerInput, newNameOfLogger);
//        nameOfLoggerSelect.selectOption(newNameOfLogger);

//        Assert.assertEquals(newLevel, levelOfLoggerInput.getSelectedOption().getText());
//        levelOfLoggerInput.selectOption(newLevel);
        click(levelOfLoggerActivate);
        valAndSelectElement(levelOfLoggerInput, newLevel);
    }

    @Step("Delete logger")
    public void deleteLoggerToConfigSops(String nameOfLogger) {
        click($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)));
        click(deleteButton);
        Assert.assertFalse($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)).exists());
    }

    @Step("Check delete logger")
    public void checkDeleteLoggerToConfigSops(String nameOfLogger) {
        Assert.assertFalse($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)).exists());
    }

    @Step("Check cancel delete logger")
    public void checkCancelDeleteLoggerToConfigSops(String nameOfLogger) {
        Assert.assertTrue($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)).exists());
    }

    @Step("Cancel delete logger")
    public void cancelDeleteLoggerToConfigSops(String nameOfLogger) {
        click($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)));
        closeForm();
        Assert.assertTrue($x(String.format(deleteLoggerTabInSopsButton, nameOfLogger)).exists());
    }

    @Step("Adding local file - {1} in SOPS {0} point")
    public void addLocalFileInInputOrOutputPoint(Point point, String localFileInPath) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
            click(localFile);
            val(localPathFileInput, localFileInPath);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
            click(localFile);
            val(localPathFileInput, localFileInPath);
        }
    }

    @Step("Adding streaming - {1} in SOPS {0} point")
    public void addStreamingInInputOrOutputPoint(Point point, String localFileInPath) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
            click(streaming);
            val(localPathFileInput, localFileInPath);
//            val(sizeOfFragmentInput, "1024000");
            sizeOfFragmentInput.sendKeys(Keys.HOME, "100");
//            sizeOfFragmentInput.sendKeys("1000 KB");
//            sizeOfFragmentInput.setValue("1000 KB");
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
            click(streaming);
            val(localPathFileInput, localFileInPath);
        }
    }

    @Step("Adding local file - {1} in SOPS {0} point")
    public void addLinkToSopsInInputOrOutputPoint(Point point, String nameLink) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
            click(linkToSops);
            val(nameLinkInput, nameLink);
            click(checkBoxGlobal);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
            click(linkToSops);
            val(localPathFileInput, nameLink);
            click(checkBoxGlobal);
        }
    }

    @Step("Adding local queue - {1} in SOPS {0} point with parameter - {2} {3}")
    public void addLocalQueueWithParameterInInputOrOutputPoint(Point point, String managerName, String queueName, String parameterValue, boolean set) {
 if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
        }

        click(localQueue);
        click($x(String.format(somethingActivate3, "Менеджер очередей")));
        valAndSelectElement($x(String.format(somethingInput, "Менеджер очередей")), managerName);
        click(bodyPage);
        click($x(String.format(somethingActivate3, "Имя очереди")));
        valAndSelectElement($x(String.format(somethingInput, "Имя очереди")), queueName);
        click(bodyPage);



//        click(queueNameActivate);
//        valAndSelectElement(queueNameInput, queueName);
        addParameter(parameterValue, set);
    }

    @Step("Adding exit in the routing table in SOPS output point with {0} routing table - {1}, useOnlyFirstSuitableRoute - {2}, valueForRouting -{3}")
    public void addExitInRoutingTableInOutputPoint(RoutingTable routingTable, String routingTableName, Boolean useOnlyFirstSuitableRoute, String valueForRouting) {
        click(exitInRoutingTable);
        click(editRoutingTableButton);
        if (routingTable.equals(RoutingTable.EXISTING)) {
            click(useExistingRoutingTableRadio);
            click(routingTableListSelect);
            click(routingTableListSelect.$x(String.format(".//option[text()='%s']", routingTableName)));
        } else if (routingTable.equals(RoutingTable.NEW)) {
            click(createNewRoutingTableRadio);
        }
        click(addRoutingTableConfirmationButton);
        if (useOnlyFirstSuitableRoute)
            click(useOnlyFirstSuitableRouteCheckbox);
        if (valueForRouting != null)
            val(routeByNextValueInput, valueForRouting);
    }

    @Step("Adding exit in the routing table in SOPS output point with new routing table - {0} and using all parameters: defaultRouteURL - {1}, routingRule - {2}, redirectionURL - {3}, useOnlyFirstSuitableRoute - {4}, valueForRouting -{5} ")
    public void addExitInRoutingTableInOutputPoint(String routingTableName, String defaultRouteURL, String routingRule, String redirectionURL, Boolean useOnlyFirstSuitableRoute, String valueForRouting) {
        click(addTreatmentButton);
        click(exitInRoutingTable);
        click(routingTableSelect);
        valAndSelectElement(routingTableInput, routingTableName);
        click(editRoutingTableButton);

        if (!useDafaultRouteCheckbox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(useDafaultRouteCheckbox);
        }

        val(defaultRouteURLInput, defaultRouteURL);
        if (routingTableName.equals("MY_EXAMPLE_SOAP") || routingTableName.equals("MY_HTTP_SOAP")) {
            click(addRuleForRouteButton);
        }

        val(routingRuleInput, routingRule);
        val(redirectionURLInput, redirectionURL);
        click(commonComponents.saveButton);
        if (useOnlyFirstSuitableRoute)
            click(useOnlyFirstSuitableRouteCheckbox);
        if (valueForRouting != null)
            val(routeByNextValueInput, valueForRouting);
    }

    @Step("Input SOPS name - {0}")
    public void inputSOPSName(String sopsName) {
        click(sopsPropertiesIcon);
        val(sopsNameInput, sopsName);
    }

    @Step("save SOPS")
    public void saveSOPS() {
        click(saveIcon);
        click(saveCommentButton);
        commonComponents.sopsSavedMessage.waitUntil(visible, 10000);
    }

    @Step("Input SOPS name - {0} and save SOPS")
    public void inputSOPSNameAndSave(String sopsName) {
        click(sopsPropertiesIcon);
        val(sopsNameInput, sopsName);
        click(saveIcon);
        click(saveButton);
        commonComponents.sopsSavedMessage.waitUntil(visible, 10000);
    }

    @Step("Adding XSLT transformation in SOPS treatment")
    public void addXSLTTransformation(String transformationText) {
        click(addTreatmentButton);
        click(xsltTransformation);
        valInTextareaWithCodemirror(transformationText);
    }

    @Step("Adding format convertion - {0} to {1} in SOPS treatment")
    public void treatmentAddFormatConvertion(String fromTo) {
        click(addTreatmentButton);
        click(formatConversion);
        click(conversionTypesActivate);
        valAndSelectElement(conversionTypesInput, fromTo);
//        conversionTypesActivate.selectOptionByValue(fromTo);
//        click($x(String.format(".//*[text()=\"%s\"]", fromTo)));
//        inputFormatSelect.$x(String.format(".//span[text()=\"%s\"]", fromTo));
    }

    @Step("Adding install headings in SOPS treatment: headingName - {0}, fieldType - {1},  expression - {2}")
    public void treatmentAddInstallHeadings(String headingName, String sintax, String fieldType, String expression) {
        click(addTreatmentButton);
        click(installHeadings);
//        click(addButton);
        val(headingsNameInput, headingName);
        click(sintaxActivate);
        valAndSelectElement(sintaxInput, sintax);
        click(typeActivate);
        valAndSelectElement(typeInput, fieldType);
//        Selenide.executeJavaScript("document.querySelector(\"#value > textarea\").style=\"opacity: 1;\"");
//        expressionInput.sendKeys(expression);
        valInTextareaWithCodemirror(expression);
    }

    @Step("Adding clear headings in SOPS treatment: headingName - {0}, fieldType - {1},  expression - {2}")
    public void treatmentAddClearHeadings(String pattern) {
        click(addTreatmentButton);
        click(clearHeaders);
        val(patternInput, pattern);
    }

    @Step("Adding output point, type - {0}, name - {1}")
    public void treatmentOutputPoint(String type, String name) {
        click(addTreatmentButton);
//        click(outputPoint);
//        click(typePointSelect);
        click($x(String.format(".//a[text()=\"%s\"]", type)));
        if (type.equals("Локальная очередь")) {
            click(queueNameSelect);
            valAndSelectElement(queueNameInput, name);
        }
    }

    @Step("Adding enrichment in SOPS treatment: headingName - {0}, fieldType - {1},  expression - {2}")
    public void treatmentAddEnrichment(String out, String urlName) {
        click(addTreatmentButton);
        click(enrichment);
        click(selectEnrichment);
        if (out.equals("Точки выхода")) {
            click($x(String.format(".//h4[text()=\"%s\"]/following-sibling::div//a[text()=\"Пользовательская точка выхода\"]", out)));
//            click(inOrOutSelect);
//            click($x(".//option[contains(text(), 'Точку выхода')]"));
        }
        val(urlNameInput, urlName);
    }

    @Step("Adding http link - {0} in SOPS input point")
    public void addHttpInInputOrOutputPoint(Point point, String link) {
        if (point.equals(Point.INPUT)) {
            addInputPointButton.click();
            click(addInputPointButton);
            click(http);
            val(urlNameInput, link);
        } else if (point.equals(Point.OUTPUT)) {
            addInputPointButton.click();
            click(addTreatmentButton);
            click(http);
            val(urlNameInput, link);
        }
    }

    @Step("Adding http link - {0} with parameter - {1} {2} in SOPS input point")
    public void inputPointAddHTTPWithParameter(String link, String parameterValue, boolean set) {
        click(addInputPointButton);
        click(http);
        val(urlNameInput, link);
        addParameter(parameterValue, set);
    }

    @Step("Adding http link - {0} with parameter1 - {1} {2}, parameter1 - {3} {4} in SOPS input point")
    public void inputPointAddHTTPWith2Parameters(String link, String parameterValue1, boolean set1, String parameterValue2, String set2) {
        click(addInputPointButton);
        click(http);
        val(urlNameInput, link);
        addParameter(parameterValue1, set1);
        addParameter(parameterValue2, set2);
    }

    @Step("Adding WMQ - {1} in SOPS {0} point")
    public void addWmqInInputOrOutputPoint(Point point, String nameWmq) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
            click(wmq);
            val(queueNameInput, nameWmq);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
            click(wmq);
            val(queueNameInput, nameWmq);
        }
    }

    @Step("Adding RollBack in SOPS {0} point")
    public void addRollBackInInputOrOutputPoint(Point point, String message) {
        if (point.equals(Point.INPUT)) {
            click(addInputPointButton);
            click(rollBack);
            val(messageInput, message);
        } else if (point.equals(Point.OUTPUT)) {
            click(addTreatmentButton);
            click(rollBack);
            val(messageInput, message);
        }
    }

    /**
     * Adding parameter in local queue
     */
    @Step("Adding parameter - {0} {1} in point")
    public void addParameter(String parameterValue, boolean set) {
        click(addParameterButton);
        sleep(500);
        click(parameterNameSelect);
        valAndSelectElement(parameterInput, parameterValue);
        $x(".//div[text()='Значение']").shouldBe(visible);
        if (set) {
            if (!parameterValueCheckbox.isSelected())
                click(parameterValueCheckbox);
        }
        click(saveParameterButton);
    }

    @Step("Adding parameter - {0} {1} in point")
    public void addParameter(String parameterValue, String set) {
        click(addParameterButton);
        sleep(500);
        click(parameterNameSelect);
        valAndSelectElement(parameterInput, parameterValue);
        val(parameterNameInput, set.split("\\.")[0]);
        val(parameterValueInput2, set.split("\\.")[0]);
        click(saveParameterButton);
    }

    /**
     * Adding parameter in WMQ
     */
    @Step("Adding parameter - {0} {1} in point")
    public void addParameterWMQ(String parameterName, ParameterType parameterType, String defaultValue, String parameterValue, boolean set) {
        String valueParam = null;
        click(addParameterButton);
        click(parameterNameSelect);
        valAndSelectElement(parameterInput, parameterName);
        sleep(500);
        switch (parameterType) {
            case checkBox:
                break;
            case input:
                valueParam = parameterValueInput.getValue();
                basePage.sout("Значение пармаметра по-умолчанию: " + valueParam);
                Assert.assertEquals(defaultValue, valueParam);
                click(parameterValueSelect);
                valAndSelectElement(parameterValueInput, parameterValue);
                break;
            case select:
                click(parameterValueSelect);
                valAndSelectElement(parameterValueInput, parameterValue);
                click(modalWindow);
//                click($x(String.format(reportName, parameterValue)));
                break;

            default:
                throw new AssertionError("Пропущен выбор параметра");
        }
        click(saveParameterButton);
    }

    /**
     * Adding several parameter in WMQ
     */
    @Step("Adding parameter - {0} {1} in point")
    public void addSeveralParametersWMQ(String parameterName, ParameterType parameterType, String defaultValue, ArrayList<String> parameters, boolean set) {
        String valueParam = null;
        click(addParameterButton);
        click(parameterNameSelect);
        valAndSelectElement(parameterInput, parameterName);
        sleep(500);


        switch (parameterType) {
            case checkBox:
                break;
            case input:
                valueParam = parameterValueInput.getValue();
                basePage.sout("Значение пармаметра по-умолчанию: " + valueParam);
                Assert.assertEquals(defaultValue, valueParam);
                click(fewParameterValueSelect);
                for (String param : parameters) {
                    valAndSelectElement(parameterValueInput, param);
                }
                break;
            case select:
                click(parameterValueSelect);
                for (String param : parameters) {
                    valAndSelectElement(parameterValueInput, param);
                }
                click(modalWindow);
                break;

            default:
                throw new AssertionError("Пропущен выбор параметра");
        }
        click(saveParameterButton);
    }


    @Step("Adding http link - {0} with parameter1 - {1} {2}, parameter1 - {3} {4} in SOPS input point")
    public void clearHeadersSops(String pattern) {
        click(addTreatmentButton);
        click(clearHeaders);
        val(patternOfClearHeadersInput, pattern);
    }

    /**
     * Changing SOPS properties
     */
    @Step("Changing SOPS properties: transactionality {0}, flow caching {1}")
    public void changeSOPSProperties(boolean transactionality, boolean flowCaching) {
        click(sopsPropertiesIcon);
        sleep(500);
        if (flowCaching)
            click(flowCachingCheckBox);
        if (transactionality)
            click(transactionalityCheckbox);
        click(saveIcon);
        click(saveCommentButton);
    }

    /**
     * Changing SOPS error handler
     */
    @Step("Changing SOPS error handler to {0}")
    public void changeSOPSErrorHandler(String errorHandlerName) {
        click(sopsPropertiesIcon);
        click(errorHandlerActivate);
        valAndSelectElement(errorHandlerInput, errorHandlerName);
//        click($$x(String.format(".//option[text()='%s']", errorHandlerName)).find(visible));
//        click(propertiesOKButton);
    }

    @Step("Adding local queue - {1} in SOPS {0} point with parameter - {2} {3}")
    public void addSoftwareTransformationOutputPoint(String name, SyntaxSoftwareTransformation syntax, SourceSoftwareTransformation source, String file, String code) {
        click(addTreatmentButton);
        click(softwareTransformation);
        val(nameSoftwareTransformationInput, name);
        click(syntaxSoftwareTransformationSelect);

        if (syntax.equals(SyntaxSoftwareTransformation.JavaScript)) {
            syntaxSoftwareTransformationSelect.selectOption("JavaScript");
        }
        if (syntax.equals(SyntaxSoftwareTransformation.Groovy)) {
            syntaxSoftwareTransformationSelect.selectOption("Groovy");
        }

        if (source.equals(SourceSoftwareTransformation.MANUAL_INPUT)) {
            syntaxSoftwareTransformationSelect.selectOption("Ручной ввод");
            val(codeSoftwareTransformationInput, code);
        }
        if (source.equals(SourceSoftwareTransformation.FILE) && syntax.equals(SyntaxSoftwareTransformation.JavaScript)) {
            sourceSyntaxSoftwareTransformationSelect.selectOption("Файл");
            fileCodeSoftwareTransformationUpload.sendKeys("JS.js");
        }
        if (source.equals(SourceSoftwareTransformation.FILE) && syntax.equals(SyntaxSoftwareTransformation.Groovy)) {
            sourceSyntaxSoftwareTransformationSelect.selectOption("Файл");
            fileCodeSoftwareTransformationUpload.sendKeys("Groovy.groovy");
        }
    }

    @Step
    public void createSOPS(String nameDomain, String sopsName, String[][] pointAll) {
        sopsPage = new SOPSPage();
        sopsPage.goToDomain(nameDomain);
        click(sopsPage.listSOPSTab);
        click(sopsPage.addSOPSButton);
        inputSOPSName(sopsName);

        for (String[] point : pointAll) {
            addPoint(point);
        }
        click(saveIcon);
        val(commentInput, "");
        click(saveCommentButton);
        click(returnToSOPSList);
        sleep(1000);
    }

    @Step
    public void createSOPS(String nameDomain, String[] property, String[][] pointAll) {
        String sopsName = property[0];
        String sopsDescription = property[1];
        String sopsPriority = property[2];
        String sopsErrorHandlingInsideSops = property[3];
        String sopsErrorHandler = property[4];
        String sopsAutostart = property[5];
        String sopsStreamCaching = property[6];
        String sopsTracing = property[7];
        String sopsTransactionalityState = property[8].split("\\|")[0];
        String sopsTransactionalityValue = property[8].split("\\|")[1];

        sopsPage = new SOPSPage();
        sopsPage.goToDomain(nameDomain);
        click(sopsPage.listSOPSTab);
        click(sopsPage.addSOPSButton);
        inputSOPSName(sopsName);
        valInTextarea(sopsDescription);
        val(sopsPriorityInput, sopsPriority);
        click(sopsPage.errorHandlerActivate);

        if (sopsErrorHandlingInsideSops.equals(String.valueOf(propertySOPS.errorHandlingInsideSopsOn)) && !errorHandlingInsideSopsCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(errorHandlingInsideSopsCheckbox);
        } else if (sopsErrorHandlingInsideSops.equals(String.valueOf(propertySOPS.errorHandlingInsideSopsOff)) && errorHandlingInsideSopsCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(errorHandlingInsideSopsCheckbox);
        }

        valAndSelectElement(sopsPage.errorHandlerInput, sopsErrorHandler);

        if (sopsAutostart.equals(String.valueOf(propertySOPS.autostartOn)) && !autostartSopsCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(autostartSopsCheckbox);
        } else if (sopsAutostart.equals(String.valueOf(propertySOPS.autostartOff)) && autostartSopsCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(autostartSopsCheckbox);
        }

        if (sopsStreamCaching.equals(String.valueOf(propertySOPS.streamCachingOn)) && !streamCachingCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(streamCachingCheckbox);
        } else if (sopsStreamCaching.equals(String.valueOf(propertySOPS.streamCachingOff)) && streamCachingCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(streamCachingCheckbox);
        }

        if (sopsTracing.equals(String.valueOf(propertySOPS.tracingOn)) && !tracingCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(tracingCheckbox);
        } else if (sopsTracing.equals(String.valueOf(propertySOPS.tracingOff)) && tracingCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(tracingCheckbox);
        }


        if (sopsTransactionalityState.equals(String.valueOf(propertySOPS.transactionalityOn)) && !transactionalityCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(transactionalityCheckbox);
        } else if (sopsTransactionalityState.equals(String.valueOf(propertySOPS.transactionalityOff)) && transactionalityCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(transactionalityCheckbox);
        }

        if (sopsTransactionalityState.equals(String.valueOf(propertySOPS.transactionalityOn))) {
            click(transactionPropagationPolicyActivate);
            valAndSelectElement(transactionPropagationPolicyInput, sopsTransactionalityValue);
        }

        for (String[] point : pointAll) {
            addPoint(point);
        }
        click(saveIcon);
        val(commentInput, "");
        click(saveCommentButton);
        click(returnToSOPSList);
        sleep(1000);
    }

    @Step
    public void addPoint(String[] point) {
        String typePoint = point[0].split("\\|")[0];
        String NameComponentPoint = point[0].split("\\|")[1];
        String message = null;
        if ("Установить тело сообщения".equals(NameComponentPoint)) {
            message = point[0].split("\\|")[2];
        } else if ("JSON Валидация".equals(NameComponentPoint) || "XSD Валидация".equals(NameComponentPoint)) {
            message = point[0].split("\\|")[3];
        }

        System.out.println(typePoint);
        if (typePoint.contains("Настройки СОПС")) {
            valInTextarea(point[0].split("\\|")[2]);
            click($x(".//label[text()='Обработчик ошибок']/../following-sibling::div"));
            valAndSelectElement($x(String.format(somethingInput, "Обработчик ошибок")), point[0].split("\\|")[3]);
        } else if (typePoint.contains("Вход")) {
            click(addInputPointButton);
            val(searchComponent, NameComponentPoint);
            click($x(String.format(addComponentEnterFromList, NameComponentPoint)));
            click($x(String.format(nameComponentInPointEnter, NameComponentPoint)));
        } else if (typePoint.contains("Выход")) {
            click(addTreatmentButton);
            val(searchComponent, NameComponentPoint);
            click($x(String.format(addComponentExitFromList, NameComponentPoint)));
            click($x(String.format(nameComponentInPointExit, NameComponentPoint)));
        } else if (typePoint.contains("Обработка")) {
            switch (typePoint.split("-")[1]) {
                case "Попытка":
                case "Балансировка":
                case "Цикл":
                case "Сегментация":
                case "Агрегация":
                case "Фильтр":
                case "Потоки":
                case "Список получателей":
                    click($$x(String.format(addPointOfTreatment, typePoint.split("-")[1])).get(Integer.parseInt(typePoint.split("-")[2])));
                    val(searchComponent, NameComponentPoint);
                    click($x(String.format(addComponenInTreatmentFromList, typePoint.split("-")[1], NameComponentPoint)));
                    click($x(String.format(nameComponentInPointExit, NameComponentPoint)));
                    break;
                case "Обогащение":
                    click($$x(String.format(selectPointOfTreatment, typePoint.split("-")[1])).get(Integer.parseInt(typePoint.split("-")[2])));
                    val(searchComponent, NameComponentPoint);
                    click($x(String.format(addComponenInTreatmentFromListWithoutHeader, point[0].split("\\|")[1], NameComponentPoint)));
                    click($x(String.format(nameComponentInPointExit, NameComponentPoint)));
                    break;
                default:
                    throw new AssertionError("Пропущен выбор места вложения");
            }
        } else {
            throw new AssertionError("Пропущен выбор типа очереди");
        }

        if (!typePoint.contains("Настройки СОПС")) {
            val(nameComponentInput, NameComponentPoint);
        }
        switch (NameComponentPoint) {
            case "Преобразование форматов":
                click($x(".//label[text()='Тип преобразования:']/../following-sibling::div"));
                valAndSelectElement($x(String.format(somethingInput, "Тип преобразования:")), point[0].split("\\|")[2]);
                if (point[0].split("\\|")[2].equals("Пользовательский формат → OBJECT") || point[0].split("\\|")[2].equals("OBJECT → Пользовательский формат")) {
                    click($x(String.format(somethingActivate2, "Ссылка на формат данных")));
                    valAndSelectElement($x(String.format(somethingInput, "Ссылка на формат данных")), point[0].split("\\|")[3]);
                }
                break;
            case "Преобразование модели":
                click($x(String.format(somethingActivate4, "Исходная модель:")));
                valAndSelectElement($x(String.format(somethingInput2, "Исходная модель:")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate4, "Целевая модель:")));
                valAndSelectElement($x(String.format(somethingInput2, "Целевая модель:")), point[0].split("\\|")[3]);
                break;
            case "Локальная БД (H2)":
                if (typePoint.equals("Вход")) {
                    click($x(String.format(somethingActivate1, "Подключение к БД")));
                    valAndSelectElement($x(String.format(somethingInput, "Подключение к БД")), point[0].split("\\|")[2]);
                    val($x(String.format(somethingInput, "SQL запрос")), point[0].split("\\|")[3]);
                }
                if (typePoint.equals("Выход")) {
                    valAndSelectElement($x(String.format(somethingInput, "Тип SQL компонента")), "Запрос");
                    click($x(String.format(somethingActivate1, "Подключение к БД")));
                    valAndSelectElement($x(String.format(somethingInput, "Подключение к БД")), point[0].split("\\|")[2]);
                    val($x(String.format(somethingInput, "SQL запрос")), point[0].split("\\|")[3]);
                }
                break;
            case "SQL":
                if (typePoint.equals("Вход")) {
                    val($x(String.format(somethingInput, "SQL запрос")), point[0].split("\\|")[2]);
                }
                if (typePoint.equals("Выход")) {
                    click($x(".//label[text()='Тип SQL компонента']/../following-sibling::div//span[text()='Запрос']"));
                    click($x(String.format(".//div[@class=\"ant-select-item-option-content\"][text()='%s']", "Запрос")));
                    val($x(String.format(somethingInput, "SQL запрос")), point[0].split("\\|")[3]);
                }
                break;
            case "HTTP":
                val($x(String.format(somethingInput, "Ссылка")), point[0].split("\\|")[2]);
                break;
            case "FTP":
            case "SFTP":
            case "SSH":
            case "FTPS":
                val($x(String.format(somethingInput, "Адрес")), point[0].split("\\|")[2]);
                break;
            case "FTP сервер":
                val($x(String.format(somethingInput, "Адрес сервера")), point[0].split("\\|")[2]);
                break;
            case "Ссылка на СОПС":
                if (typePoint.equals("Вход")) {
                    val($x(String.format(somethingInput, "Имя ссылки")), point[0].split("\\|")[2]);
                }
                if (typePoint.equals("Выход")) {
                    click($x(String.format(somethingActivate1, "СОПС")));
                    valAndSelectElement($x(String.format(somethingInput, "СОПС")), point[0].split("\\|")[2]);
                }
                break;
            case "Локальная очередь":
                click($x(String.format(somethingActivate3, "Менеджер очередей")));
                valAndSelectElement($x(String.format(somethingInput, "Менеджер очередей")), point[0].split("\\|")[2]);
                click(bodyPage);
                click($x(String.format(somethingActivate3, "Имя очереди")));
                valAndSelectElement($x(String.format(somethingInput, "Имя очереди")), point[0].split("\\|")[3]);
                click(bodyPage);
                break;
            case "Локальная очередь РМО":
                click($x(String.format(somethingActivate1, "Адрес")));
                valAndSelectElement($x(String.format(somethingInput, "Адрес")), point[0].split("\\|")[2]);
                break;
            case "Установить тело сообщения":
                valInTextareaWithCodemirror(message);
                break;
            case "Установить переменные":
                val($x(String.format(somethingInput, "Имя")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate3, "Синтаксис")));
                valAndSelectElement($x(String.format(somethingInput, "Синтаксис")), point[0].split("\\|")[3]);
                if (!point[0].split("\\|")[3].equals("Constant")) {
                    click($x(String.format(somethingActivate3, "Тип")));
                    valAndSelectElement($x(String.format(somethingInput, "Тип")), point[0].split("\\|")[4]);
                }
                valInTextareaWithCodemirror(point[0].split("\\|")[5]);
                break;
            case "Записать глобальную переменную":
                click($x(String.format(somethingActivate3, "Область видимости")));
                valAndSelectElement($x(String.format(somethingInput, "Область видимости")), point[0].split("\\|")[2]);
                val($x(String.format(somethingInput, "Имя")), point[0].split("\\|")[3]);
                click($x(String.format(somethingActivate3, "Синтаксис")));
                valAndSelectElement($x(String.format(somethingInput, "Синтаксис")), point[0].split("\\|")[4]);
                click($x(String.format(somethingActivate3, "Тип")));
                valAndSelectElement($x(String.format(somethingInput, "Тип")), point[0].split("\\|")[5]);
                valInTextareaWithCodemirror(point[0].split("\\|")[6]);
                break;
            case "Прочитать глобальную переменную":
                click($x(String.format(somethingActivate3, "Область видимости")));
                valAndSelectElement($x(String.format(somethingInput, "Область видимости")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate3, "Записать переменную в")));
                valAndSelectElement($x(String.format(somethingInput, "Записать переменную в")), point[0].split("\\|")[3]);
                val($x(String.format(somethingInput, "Имя переменной")), point[0].split("\\|")[4]);
                break;
            case "Преобразовать тело сообщения":
                click($x(String.format(somethingActivate3, "Тип")));
                valAndSelectElement($x(String.format(somethingInput, "Тип")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate3, "Кодировка")));
                valAndSelectElement($x(String.format(somethingInput, "Кодировка")), point[0].split("\\|")[3]);
                break;
            case "RabbitMQ":
                valAndSelectElement($x(String.format(somethingInput, "Подключение")), point[0].split("\\|")[2]);
                break;
            case "Kafka":
                valAndSelectElement($x(String.format(somethingInput, "Раздел")), point[0].split("\\|")[2]);
                break;
            case "Mina":
                val($x(String.format(somethingInput, "Ссылка")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate2, "Протокол:")));
                valAndSelectElement($x(String.format(somethingInput, "Протокол:")), point[0].split("\\|")[3]);
                break;
            case "JMS":
            case "IBM MQ":
                val($x(String.format(somethingInput, "Имя очереди")), point[0].split("\\|")[2]);
                break;
            case "SMB":
                val($x(String.format(somethingInput, "Путь")), point[0].split("\\|")[2]);
                break;
            case "WebSocket":
                val($x(String.format(somethingInput, "Ссылка")), point[0].split("\\|")[2]);
                break;
            case "Бин":
                val($x(String.format(somethingInput, "Метод")), point[0].split("\\|")[2]);
                val($x(String.format(somethingInput, "Ссылка на бин")), point[0].split("\\|")[3]);
                break;
            case "Обработчик":
                click($x(String.format(somethingActivate1, "Ссылка на бин")));
                valAndSelectElement($x(String.format(somethingInput, "Ссылка на бин")), point[0].split("\\|")[2]);
                break;
            case "Пользовательская точка входа":
            case "Пользовательская точка выхода":
                val($x(String.format(somethingInput, "Имя URL")), point[0].split("\\|")[2]);
                break;
            case "Таймер":
                val($x(String.format(somethingInput, "Имя")), point[0].split("\\|")[2]);
                break;
            case "Планировщик":
                val($x(String.format(somethingInput, "Имя")), point[0].split("\\|")[1]);
                val($x(String.format(somethingInput, "Cron-строка")), point[0].split("\\|")[3]);
                break;
            case "Принтер":
                click($x(String.format(somethingActivate2, "Имя принтера")));
                valAndSelectElement($x(String.format(somethingInput, "Имя принтера")), point[0].split("\\|")[2]);
                break;
            case "Телеграм":
                val($x(String.format(somethingInput, "Токен авторизации")), point[0].split("\\|")[2]);
                break;
            case "Кэш":
                val($x(String.format(somethingInput, "Имя компонента")), NameComponentPoint);
                click($x(String.format(somethingActivate2, "Действие")));
                valAndSelectElement($x(String.format(somethingInput, "Действие")), point[0].split("\\|")[2]);
                val($x(String.format(somethingInput, "Значение ключа")), point[0].split("\\|")[3]);
                val($x(String.format(somethingInput, "Имя кэша")), point[0].split("\\|")[4]);
                break;
            case "SMTP":
            case "POP3":
            case "IMAP":
                val($x(String.format(somethingInput, "Имя хоста/IP адрес")), point[0].split("\\|")[2]);
                val($x(String.format(somethingInput, "Имя пользователя")), point[0].split("\\|")[3]);
                val($x(String.format(somethingInput, "Порт")), point[0].split("\\|")[4]);
                break;
            case "Попытка":
                click(addClassExceptionButton);
                val($x(String.format(" .//th[text()='%s']/../../..//input[not(@disabled)]", "Класс исключения")), point[0].split("\\|")[2]);
                break;
            case "Балансировка":
                click($x(String.format(somethingActivate2, "Тип балансировки")));
                valAndSelectElement($x(String.format(somethingInput, "Тип балансировки")), point[0].split("\\|")[2]);
                break;
            case "Цикл":
                val($x(".//label[text()='Условие']/../following-sibling::div//input"), point[0].split("\\|")[2]);

                click($x(String.format(somethingActivate2, "Тип")));
                click($x(String.format(".//*[text()='%s']", point[0].split("\\|")[3])));
//                $x(".//th[text()='Тип']/../../../tbody/tr/td/select").selectOption(point[0].split("\\|")[3]);
                break;
            case "Фильтр":
                click($x(".//span[text()='Добавить условие']/.."));
                val($x(".//th[text()='Условие']/../../..//input[@name=\"condition\"][not(@disabled)]"), point[0].split("\\|")[2]);
                click($x(".//th[text()='Тип']/../../..//div[@name=\"type\"]"));
                valAndSelectElement($x(String.format(".//th[text()='Тип']/../../..//div[@name=\"type\"]//input")), point[0].split("\\|")[3]);
                break;
            case "Потоки":
                click($x(String.format(somethingActivate2, "Ссылка на службу исполнения (пул потоков)")));
                valAndSelectElement($x(String.format(somethingInput, "Ссылка на службу исполнения (пул потоков)")), point[0].split("\\|")[2]);
                break;
            case "Список получателей":
                click($x(String.format(somethingActivate2, "Тип выражения")));
                click($x(String.format(".//div[text()='%s']", point[0].split("\\|")[2])));
                break;
            case "Сегментация":
                click($x(".//label[text()='Тип выражения']/../..//input/../.."));
                click($x(String.format(".//div[text()='%s']", point[0].split("\\|")[2])));
                valAndSelectElement($x(String.format(somethingInput, "Токен")), point[0].split("\\|")[3]);
                break;
            case "Агрегация":
                click($x(String.format(somethingActivate2, "Тип")));
                click($x(String.format(".//div[text()='%s']", point[0].split("\\|")[2])));
                val($x(String.format(somethingInput, "Значение")), point[0].split("\\|")[3]);
                click($x(String.format(somethingActivate2, "Ссылка на стратегию агрегации")));
                valAndSelectElement($x(String.format(somethingInput, "Ссылка на стратегию агрегации")), point[0].split("\\|")[4]);
                break;
            case "JSON Валидация":
            case "XSD Валидация":
                click($x(String.format(".//label[text()='%s']/../..//input/../..", "Источник")));
                valAndSelectElement($x(String.format(somethingInput, "Источник")), point[0].split("\\|")[2]);
                if (point[0].split("\\|")[2].equals("Ручной ввод")) {
                    valInTextareaWithCodemirror(message);
                } else if (point[0].split("\\|")[2].equals("Файл")) {
                    click($x(String.format(".//label[text()='%s']/../..//input/../..", "Выберите файл")));
                    valAndSelectElement($x(String.format(somethingInput, "Выберите файл")), point[0].split("\\|")[3]);
                } else {
                    throw new AssertionError("Пропущен выбор инпута");
                }
                break;
            case "Программная трансформация":
                String mess = point[0].split("\\|")[4].replace("$]", "");
                click($x(String.format(somethingActivate2, "Синтаксис")));
                click($x(String.format(".//div[text()='%s']", point[0].split("\\|")[2])));

                if (point[0].split("\\|")[3].equals("Ручной ввод")) {
                    valAndSelectElement($x(String.format(somethingInput, "Источник")), point[0].split("\\|")[3]);
                    valInTextareaWithCodemirror(mess);
                } else if (point[0].split("\\|")[3].equals("Файл не существует")) {
                    valAndSelectElement($x(String.format(somethingInput, "Источник")), "Файл");
                    click($x(String.format(somethingActivate2, "Выберите файл")));
                    click($x(".//label[text()='Выберите файл']/../following-sibling::div"));
                    $x(".//span[text()='Выберите файл']/../..//input[@type=\"file\"]").sendKeys(point[0].split("\\|")[4]);
                    elementShouldBeVisible($x(String.format(".//span[@title='%s']", point[0].split("\\|")[5])));
                } else if (point[0].split("\\|")[3].equals("Файл существует")) {
                    valAndSelectElement($x(String.format(somethingInput, "Источник")), "Файл");
                }
                break;
            case "Производительность":
                valAndSelectElement($x(String.format(somethingInput, "Имя события")), point[0].split("\\|")[2]);
                break;
            case "Пользовательская обработка":
                valInTextareaWithCodemirror(point[0].split("\\|")[2]);
                break;
            case "Метрика":
                valAndSelectElement($x(String.format(somethingInput, "Имя метрики")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate2, "Тип метрики")));
                click($x(String.format(".//div[text()='%s']", point[0].split("\\|")[3])));
                valAndSelectElement($x(String.format(somethingInput, "Значение")), point[0].split("\\|")[4]);
                break;
            case "Исполнить команду локально":
                valAndSelectElement($x(String.format(somethingInput, "Команда")), point[0].split("\\|")[2]);
                break;
            case "Локальный файл":
                valAndSelectElement($x(String.format(somethingInput, "Путь")), point[0].split("\\|")[2]);
                break;
            case "LDAP":
                click($x(String.format(somethingActivate1, "Ссылка на конфигурацию LDAP-соединения")));
                valAndSelectElement($x(String.format(somethingInput, "Ссылка на конфигурацию LDAP-соединения")), point[0].split("\\|")[2]);
                val($x(String.format(somethingInput, "Базовый DN поиска")), point[0].split("\\|")[3]);
                break;
            case "REST Swagger":
                click($x(String.format(somethingActivate2, "Swagger спецификация")));
                uploadSpecificationSwaggerAndAdrressWsdl.sendKeys(point[0].split("\\|")[2]);
                click($x(String.format(".//div[text()='file:resources/swagger/%s'][not(@role)]", point[0].split("\\|")[2].replace("/share/", ""))));
                sleep(4000);
                click($x(String.format(somethingActivate2, "Идентификатор операции (метода)")));
                valAndSelectElement($x(String.format(somethingInput, "Идентификатор операции (метода)")), point[0].split("\\|")[3]);
                break;
            case "SOAP":
                click($x(String.format(somethingActivate2, "Адрес WSDL")));
                uploadSpecificationSwaggerAndAdrressWsdl.sendKeys(point[0].split("\\|")[2]);
                click($x(String.format(".//div[text()='file:resources/wsdl/%s'][not(@role)]", point[0].split("\\|")[2].replace("/share/", ""))));
                click($x(String.format(somethingActivate2, "Сервис")));
                valAndSelectElement($x(String.format(somethingInput, "Сервис")), point[0].split("\\|")[3]);
                click($x(String.format(somethingActivate2, "Адрес сервиса")));
                valAndSelectElement($x(String.format(somethingInput, "Адрес сервиса")), point[0].split("\\|")[4]);
                click($x(String.format(somethingActivate2, "Порт")));
                valAndSelectElement($x(String.format(somethingInput, "Порт")), point[0].split("\\|")[5]);
                click($x(String.format(somethingActivate2, "Метод")));
                valAndSelectElement($x(String.format(somethingInput, "Метод")), point[0].split("\\|")[6]);
                click($x(String.format(somethingActivate2, "Формат запроса")));
                valAndSelectElement($x(String.format(somethingInput, "Формат запроса")), point[0].split("\\|")[7]);
                break;
            case "Логирование":
                val($x(String.format(somethingInput, "Сообщение логирования")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate2, "Имя логгера")));
                valAndSelectElement($x(String.format(somethingInput, "Имя логгера")), point[0].split("\\|")[3]);
                click($x(String.format(somethingActivate2, "Уровень логирования")));
                valAndSelectElement($x(String.format(somethingInput, "Уровень логирования")), point[0].split("\\|")[4]);
                break;
            case "Обогащение":
                val($x(String.format(somethingInput, "Тайм-аут")), point[0].split("\\|")[2]);
                click($x(String.format(somethingActivate2, "Финальный объект обмена")));
                valAndSelectElement($x(String.format(somethingInput, "Финальный объект обмена")), point[0].split("\\|")[3]);
                click($x(String.format(somethingActivate2, "Ссылка на стратегию агрегации")));
                valAndSelectElement($x(String.format(somethingInput, "Ссылка на стратегию агрегации")), point[0].split("\\|")[4]);
                break;
            case "Выход в таблицу маршрутов":
                click(editTableLink);
                break;
            case "Очистить заголовки":
                val($x(String.format(somethingInput, "Паттерн")), point[0].split("\\|")[2]);
                break;
            case "Установить заголовки":
            case "Прервать обработку":
            case "Добавить вложение":
            case "Настройки СОПС":
                break;
            default:
                throw new AssertionError("Пропущен выбор точки: NameComponentPoint");
        }
        if (point.length > 1) {
            int i = 1;
            for (int x = 0; x < point.length - 1; x++) {
                String typeParameter = point[x + 1].split("\\|")[0];
                String nameParameter = point[x + 1].split("\\|")[1];
                String valueParameter = point[x + 1].split("\\|")[2];
                String value4 = null;
                String value5 = null;
                if (point[x + 1].split("\\|").length > 3) value4 = point[x + 1].split("\\|")[3];
                if (point[x + 1].split("\\|").length > 4) value5 = point[x + 1].split("\\|")[4];
                System.out.println(typeParameter + " " + nameParameter + " " + valueParameter);

                switch (typeParameter) {
                    case "чек-бокс":
                        if (valueParameter.equals("вкл") && !$x(String.format(checkBoxOfParameter2, nameParameter)).isSelected()) {
                            click($x(String.format(checkBoxOfParameter2, nameParameter)));
                        } else if (valueParameter.equals("выкл") && $x(String.format(checkBoxOfParameter2, nameParameter)).isSelected()) {
                            click($x(String.format(checkBoxOfParameter2, nameParameter)));
                        }
                        if (nameParameter.equals("Выполнять при условии")) {
                            click($x(String.format(somethingActivate2, "Тип условия")));
                            valAndSelectElement($x(String.format(somethingInput, "Тип условия")), value4);
                            val($x(String.format(somethingInput, "Условие")), value5);
                        }
                        if (nameParameter.equals("Установить выражение размера завершения")) {
                            click($x(".//span[text()='Установить выражение размера завершения']/../../../../../following-sibling::div//label[text()='Тип']/../following-sibling::div"));
                            click($x(String.format(".//div[text()='%s']", valueParameter)));
                            val($x(".//span[text()='Установить выражение размера завершения']/../../../../../following-sibling::div//label[text()='Значение']/../following-sibling::div//input"), value4);
                        }
                        break;
                    case "чек-бокс2":
                        if (valueParameter.equals("вкл") && !$x(String.format(checkBoxOfParameter2, nameParameter)).parent().attr("class").equals("ant-checkbox-wrapper ant-checkbox-wrapper-checked")) {
                            click($x(String.format(checkBoxOfParameter2, nameParameter)));
                        } else if (valueParameter.equals("выкл") && $x(String.format(checkBoxOfParameter2, nameParameter)).attr("class").equals("ant-checkbox-wrapper ant-checkbox-wrapper-checked")) {
                            click($x(String.format(checkBoxOfParameter2, nameParameter)));
                        }
                        if (nameParameter.equals("Выполнять при условии")) {
                            $x(".//label[text()='Тип условия']/../..//select").selectOption(value4);
                            val($x(String.format(somethingInput, "Условие")), value5);
                        }
                        break;
                    case "селект":
                        click($x(String.format(somethingActivate2, nameParameter)));
                        valAndSelectElement($x(String.format(somethingInput, nameParameter)), valueParameter);
                        break;
                    case "инпут":
                        valAndSelectElement($x(String.format(somethingInput, nameParameter)), valueParameter);
                        break;
                    case "селект-в-параметрах":
                        click(addParameterButton);
                        click(parameterNameSelect);
                        valAndSelectElement(parameterInput, nameParameter);
                        click(parameterValueActivate);
                        valAndSelectElement(parameterValueInput, valueParameter);
                        click(saveParameterButton);
                        break;
                    case "инпут-в-параметрах":
                        click(addParameterButton);
                        click(parameterNameSelect);
                        valAndSelectElement(parameterInput, nameParameter);
                        val(parameterValueInput, valueParameter);
                        click(saveParameterButton);
                        break;
                    case "чекбокс-в-параметрах":
                        click(addParameterButton);
                        click(parameterNameSelect);
                        valAndSelectElement(parameterInput, nameParameter.toString());
                        $x(".//div[text()='Значение']").shouldBe(visible);
                        if (valueParameter.equals("вкл") && !parameterValueCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(parameterValueCheckbox);
                        }
                        if (valueParameter.equals("выкл") && parameterValueCheckbox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
                            click(parameterValueCheckbox);
                        }
                        click(saveParameterButton);
                        break;
                    case "параметры-заголовков":
                        if (i > 1) {
                            click(addInPointButton);
                        }
                        val($$x(String.format(somethingInput, "Имя")).get(i - 1), point[i].split("\\|")[1]);
                        click($$x(String.format(".//label[text()='%s']/../..//input/../../..", "Синтаксис")).get(i - 1));
                        valAndSelectElement($x(String.format("(.//label[text()='%s'])[" + i + "]/../..//input", "Синтаксис")), point[i].split("\\|")[2]);

                        if (!point[i].split("\\|")[2].equals("Constant")) {
                            click($$x(String.format(".//label[text()='%s']/../..//input/../../..", "Тип")).get(i - 1));
                            valAndSelectElement($$x(String.format("(.//label[text()='%s'])[\" + Integer.toString(i) + \"]/../..//input", "Тип")).get(i - 1), point[i].split("\\|")[3]);
                        }
                        valInTextareaWithCodemirror(point[i].split("\\|")[4]);
                        i++;
                        break;
                    case "параметры-преобразования-модели":
                        if (point[x + 1].split("\\|")[1].equals("Трансформации")) {
                            click($x(String.format(originalModelColumn, point[x + 1].split("\\|")[3])));
                            click($x(String.format(targetModelColumn, point[x + 1].split("\\|")[4])));
                            click(transformationTwoModelsButton);
                            if (point[x + 1].split("\\|")[2].equals("Вычисляемое выражение")) {
                                click($x("(.//button[@aria-label=\"Развернуть строку\"])[last()]"));
                                click($x(".//label//span[@aria-label=\"down\"]"));
                                click($x(".//li[text()='Вычисляемое выражение']"));
                                click($x(".//label//span[@aria-label=\"down\"]/../../../following-sibling::div"));
                                click($x(".//div[text()='" + point[x + 1].split("\\|")[5] + "']"));
                                valInTextareaWithCodemirror(point[x + 1].split("\\|")[6]);

                                val($x(String.format(somethingInput, "Параметр целевой модели:")), point[x + 1].split("\\|")[7]);
                            }
                        }
                        break;
                    case "параметры-вложения":
                        if (i > 1) {
                            click(addInPointButton);
                        }
                        val($$x(String.format(somethingInput, "Имя файла")).get(i - 1), point[i].split("\\|")[1]);
                        click($$x(String.format(".//label[text()='%s']/../..//input/../../..", "Тип")).get(i - 1));
                        valAndSelectElement($x(String.format("(.//label[text()='%s'])[" + Integer.toString(i) + "]/../..//input", "Тип")), point[i].split("\\|")[2]);

                        if (!point[i].split("\\|")[2].equals("Из тела")) {
                            click($$x(String.format(".//label[text()='%s']/../..//input/../../..", "Тип")).get(i - 1));
                            valAndSelectElement($$x(String.format("(.//label[text()='%s'])[\" + Integer.toString(i) + \"]/../..//input", "Тип")).get(i - 1), point[i].split("\\|")[3]);
                        }

                        click($$x(String.format(".//label[text()='%s']/../..//input/../../..", "MIME-тип")).get(i - 1));
                        valAndSelectElement($x(String.format("(.//label[text()='%s'])[" + Integer.toString(i) + "]/../..//input", "MIME-тип")), point[i].split("\\|")[4]);
                        i++;
                        break;
                    case "параметры таблицы маршрутов":
                        valAndSelectElement($x(String.format(somethingInput, "Название таблицы маршрутов")), point[1].split("\\|")[1]);

                        for (int a = 2; a < point[1].split("\\|").length - 1; a++) {
                            sout(point[1].split("\\|")[a]);

                            if (point[1].split("\\|")[a].split("-")[0].equals("Маршрут по умолчанию")) {
                                click(useDafaultRouteCheckbox);
                                click(listTypeOfRuleActivate.get(0));
                                click($x(String.format(".//div[text()='%s']", point[1].split("\\|")[a].split("-")[1])));
                                if (point[1].split("\\|")[a].split("-")[1].equals("Ручной ввод")) {
                                    val(listRoutingUrlInput.get(a - 2), point[1].split("\\|")[a].split("-")[2]);
                                } else if (point[1].split("\\|")[a].split("-")[1].equals("Точка выхода")) {
                                    click($x(String.format(".//a[text()='%s']", point[1].split("\\|")[a].split("-")[2])));
                                }

                            }

                            click(addRuleForRouteButton);
                            val(listRoutingRuleInput.get(a - 2), point[1].split("\\|")[a + 1].split("-")[0]);
                            click($x(String.format(somethingActivate2, "Тип")));
                            click($x(String.format(".//label[text()='Тип']/../../../../..//div[text()='%s']", point[1].split("\\|")[a + 1].split("-")[1])));

                            if (point[1].split("\\|")[a + 1].split("-")[1].equals("Ручной ввод")) {
                                val($x(".//label[text()=\"Тип\"]/../../../../..//input[contains(@name,\"url\")]"), point[1].split("\\|")[a + 1].split("-")[2]);
                            } else if (point[1].split("\\|")[a + 1].split("-")[1].equals("Точка выхода")) {
                                click($x(String.format(".//a[text()='%s']", point[1].split("\\|")[a + 1].split("-")[2])));
                            }

                            click(saveButton);

                            if (point[1].split("\\|")[a + 1].split("-")[2].equals("Локальная очередь")) {
                                click($x(String.format(somethingActivate3, "Менеджер очередей")));
                                valAndSelectElement($x(String.format(somethingInput, "Менеджер очередей")), "QM");
                                click($x(String.format(somethingActivate3, "Имя очереди")));
                                valAndSelectElement($x(String.format(somethingInput, "Имя очереди")), point[1].split("\\|")[a + 1].split("-")[3]);
                                for (SelenideElement unit : rollUpString) {
                                    click(unit);
                                }
                            }
                        }
                        click(saveRoutingTableButton);
                        click(saveButton);
                        click(returnToComponentButton);
                        break;
                    default:
                        throw new AssertionError("Пропущена настройка параметра");
                }
            }
        }


//        switch (pointInput[0]) {
//            case "Локальная очередь":
//                click(addInputPointButton);
//                for (int x = 0; x < pointInput.length - 1; x++) {
//                    if (x == 0) click($x(String.format(nameComponent, pointInput[x])));
//                    if (x == 1) {
//                        click(queueNameSelect);
//                        valAndSelectElement(queueNameInput, pointInput[x]);
//                    }
//                    if (x > 1 && x % 2 == 0) {
//                        click(addParameterButton);
//                        click(parameterNameSelect);
//                        valAndSelectElement(parameterInput, pointInput[x].toString());
//                        sleep(500);
//                    }
//                    if (x > 1 && x % 2 != 0) {
//                        addParameterToPoint(pointInput[x - 1], pointInput[x]);
//                    }
//                }
//                break;
//            default:
//                throw new AssertionError("Пропущена настройка точки входа");
//        }
    }

//    public void addParameterToPoint(String parameterName, String set) {
//        switch (parameterName) {
//            case "disableReplyTo":
//                valAndSelectElement(parameterInput, parameterName);
//                $x(".//div[text()='Значение']").shouldBe(visible);
//                if (set.equals("true")) {
//                    if (!parameterValueCheckbox.isSelected())
//                        click(parameterValueCheckbox);
//                } else if (parameterValueCheckbox.isSelected())
//                    click(parameterValueCheckbox);
//                break;
//            default:
//                throw new AssertionError("Пропущен выбор параметра с чек-поинтом");
//        }
//        click(saveParameterButton);
//    }
}

