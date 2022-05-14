package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import utils.ConfigProperties;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static ru.factorts.page.CommonComponents.ContextMenuElementXpath;

public class SOPSPage extends BasePage {
    String urlDownload = "file:///home/selenium/Downloads/";
    String DownloadedFileName;

    QueueManagerPage queueManagerPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    QueueManagerApi queueManagerApi = new QueueManagerApi();
    QueueManagerMultyApi queueManagerMultyApi = new QueueManagerMultyApi();
    CreationSOPSPage creationSOPSPage;
    CommonComponents commonComponents = new CommonComponents();
    MessagePage messagePage = new MessagePage();
    Api api = new Api();
    SOPSApi sopsApi = new SOPSApi();
    BasePage basePage = new BasePage();
    RestPage restPage = new RestPage();

    String domainOld = null;
    static String specificDomainButtonXpath = ".//a[text()='Домены брокера']/../..//a[text()='%s']";
    static String serverSelect = "//div[text()='Копирование маршрута \"%s\" на другой сервер']/../..//div[text()='Выбрать...']";
    static String presentServerInList = "//div[text()='%s']";
    String domainInGroup = "//span[text()='%s']/../../following-sibling::div/a[text()='Перейти']";
    String copySopsSelect = ".//div[text()='Копирование маршрута \"%s\" на другой сервер']/../following-sibling::div//span[@class=\"ant-select-selection-search\"]";
    String copySopsInput = ".//div[text()='Копирование маршрута \"%s\" на другой сервер']/../following-sibling::div//input";

    static String fileForImport = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String constanteForImport = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String variableForImport = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";

    static String sopsInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String jsFileInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String xsdFileInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String groovyFileInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String routsFileInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String xsltFileInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String variablesInImportDomainCheckBox = "//td[text()='%s']/preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String directoryInFilesManager = "//td[text()='%s']/preceding-sibling::td/button";
    static String additionalСonfigurationImportDomainCheckBox2 = "//div[text()='%s']/../preceding-sibling::td[text()='%s']/preceding-sibling::td//input";
    static String expandConfigurationForImportButton = "//td[text()='%s']/preceding-sibling::td/button";
    static String expandConfigurationForAdditionalConfigurationButton = "//div[text()='%s']/../preceding-sibling::td//span[text()='%s']/../preceding-sibling::td/button";
    static String rowAndCellInTracingTable = "(.//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")])[%s]/td[%s]";
    static String cellInRowTracingTable = "(.//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")])[1]/td[%s]";


    SelenideElement domainProperties = $x(".//span[contains(text(),'Свойства домена')]"),
            importDomainInput = $x(".//span[text()=\"Импортировать домен\"]//input"),
            importAsNewDomainButton = $x(".//span[text()='Импортировать как новый домен']/preceding-sibling::button"),
            nameOfDamainInImportActivate = $x(".//label[text()='Имя домена']/../following-sibling::div"),
            nameOfDamainInImportInput = $x(".//label[text()='Имя домена']/../following-sibling::div//input"),
            deleteOldDomainheckBox = $x(".//span[text()='Удалить старый домен']/..//input/.."),
            historyInImportCheckBox = $x(".//span[text()='Импортировать историю']/..//input"),
            modelsInImportCheckBox = $x(".//span[text()='Импортировать модели']/..//input"),

    toChooseSopsButton = $x(".//span[text()='К выбору СОПС ']/.."),
            toGeneralSettingButton = $x(".//span[text()='К общим настройкам']/.."),
            toChooseConfigurationButton = $x(".//span[text()='К выбору конфигураций ']/.."),
            toChooseConstantsButton = $x(".//span[text()='К выбору констант ']/.."),
            toChooseMessagesButton = $x(".//span[text()='К выбору сообщений ']/.."),
            toChoosetableOfRoutsButton = $x(".//span[text()='К выбору таблиц маршрутизации ']/.."),
            toChooseFilesButton = $x(".//span[text()='К выбору файлов ']/.."),
            toChooseVariablesButton = $x(".//span[text()='К выбору констант ']/.."),

    createDomainButton = $x(".//span[contains(text(),'Создать домен')]"),
            createStandartDomain = $x(".//a[text()='Стандартный']"),
            addSOPSButton = $x(".//span[contains(text(), 'Добавить СОПС')]/.."),

    // Create domain page
    domainNameInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            saveDomainButton = $x(".//button[span[text()='Создать']]"),
    // Option panel
    nameDomainInHeader = $x(".//span[@class=\"ant-page-header-heading-title\"]"),
            domainStartedMode = $x(".//span[text()='Запущен']"),
            debugModeLabel = $x(".//span[text()=' Режим отладки']"),
            runDomainButton = $x(".//span[text()='Запустить']/.."),
            stopDomainButton = $x(".//span[text()='Остановить']/.."),
            actionsUnderDomainsButton = $x(".//span[text()='Действия ']/.."),

    settingDomainButton = $x(".//span[text()='Свойства']/.."),
    //            constantesDomainTab = $x(".//span[text()='Константы']/.."),
    exportDomainButton = $x(".//span[text()='Экспорт']/ancestor::button"),

    reloadDomainButton = $x(".//span[text()='Перезагрузить']"),
            restartBrokerButton = $x(".//span[text()='Перезапустить брокер']/.."),
            restartDomainButton = $x(".//span[text()='Перезапустить домен']/.."),
            resetCountersButton = $x(".//span[text()='Сбросить счетчики']"),
            moveDomainButton = $x(".//span[text()='Переместить в...']"),
            deployDomainButton = $x(".//span[text()='Развертывание']"),
            runDebugButton = $x(".//span[text()='Включить отладку'] "),
            stopDebugButton = $x(".//span[text()='Выключить отладку']"),
            deleteDomainButton = $x(".//span[text()='Удалить']"),

    addVariableButton = $x(".//i[@class=\"fa fa-fw fa-plus\"]"),


    nameDomainInputForExport = $x(".//div[@class=\"ant-spin-container\"]//label[text()='Имя файла']/../following-sibling::div//input"),
            geberalResouecesCheckBox = $x(".//span[text()='Общие ресурсы']"),
            modelsCheckBox = $x(".//span[text()='Модели домена']"),
            tablesOfRoutsCheckBox = $x(".//span[text()='Таблицы маршрутов']"),
            variablesOfDomainCheckBox = $x(".//span[text()='Константы домена']"),
            savedMessagesInLocalQueueCheckBox = $x(".//span[text()='Сохраненные сообщения точек входа локальная очередь']"),
            historyChangesSopsCheckBox = $x(".//span[text()='История изменения СОПС']"),
            exportButton = $x(".//div[text()='Экспорт домена']/../..//*[text()='Экспортировать']/.."),
            debugDomainButton = $x(".//span[text()='Включить отладку']/.."),


    //Table SOPS
    listSOPSTab = $x(".//*[text()=\"Список СОПС\"]"),
            autoUpdateSOPSTable = $x(".//input[@type=\"checkbox\"]/.."),
            tableHeaderSOPSID = $x(".//th[text()='Идентификатор СОПС']"),
            cellNameSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[1]/a"),
            cellPointInSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]"),
            cellProcessedSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[3]"),
            pendingtransactionsSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[4]"),
            firstProcessingSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[5]"),
            lastProcessingSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[6]"),
            averageSpeedSOPS = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[7]"),
            rowAfterSearchSOPS = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]").find(visible),
            rowAfterSearchAddConfiguration = $$x(".//span[text()='Добавить конфигурацию']/../../../../..//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]").find(visible),
    //            afterSearchProcessedQuantity = rowAfterSearchSOPS.$x(".//td[4]"),
    sopsSearchInput = $x(".//span[text()='Добавить СОПС']/../../..//label[text()='Поиск ']/input"),
            tracingSearchInput = $x(".//label[text()='Поиск ']/input"),
            addConfigurationSearchInput = $x(".//span[text()='Добавить конфигурацию']/../../..//label[text()='Поиск ']/input"),
            searchNameConfigurationIcon = $x(".//span[text()='Добавить конфигурацию']/../../../../..//span[text()='Имя']/../../../..//span[@role=\"button\"]"),
            searchNameConfigurationInput = $x(".//input[@placeholder=\"Найти Имя\"]"),
    //Context sops menu
    editSOPS = $$x(String.format(ContextMenuElementXpath, "Редактировать")).find(visible),
            startSOPS = $$x(".//div[text()=' Запустить']").find(visible),
            stopSOPS = $$x(".//div[text()=' Остановить']").find(visible),
            forcedStopSOPS = $$x(".//div[text()=' Принудительная остановка']").find(visible),
            sendMessage = $$x(".//div[text()=' Отправить сообщение']").find(visible),
            sendSavedMessage = $$x(".//div[text()=' Отправить сохраненное сообщение']").find(visible),
            cloneSOPS = $$x(".//div[text()=' Клонировать']").find(visible),
            moveSOPS = $$x(".//div[text()=' Переместить']").find(visible),
            copySopsToServer = $$x(".//div[text()=' Скопировать на сервер']").find(visible),
            domainToCopyToActivate = $x(".//label[text()='Домен']/../following-sibling::div//input/.."),
            domainToCopyToInput = $x(".//label[text()='Домен']/../following-sibling::div//input"),


    deleteSOPS = $$x(".//div[text()=' Удалить']").find(visible),
            indicatorOfStatus = $x(".//span[contains(@class,\"ant-badge-status-dot\")]"),


    //Confinrmation menu
    confirmationDeleteButton = $$x(".//div[text()='Подтверждение удаления']/../..//span[text()='Удалить']/..").find(visible),
    //    deleteButton = $x(".//div[@class=\"modal-footer\"]//span[text()='Удалить']/.."),
    copySopsButton = $x(".//span[text()='Скопировать']/.."),
    //Domain import page
    confirmationImportButton = $x(".//button[span[text()='Импортировать']]"),
            confirmationCopyButton = $x(".//span[text()='Скопировать']/.."),
    //Move menu
    domainToMoveActivateInput = $x(".//label[text()=\"Выберите домен\"]/../following-sibling::div"),
            domainToMoveInput = $x(".//label[text()=\"Выберите домен\"]/../following-sibling::div//input"),
            moveButton = $x(".//button[text()='Перенести']"),
            confirmationMoveButton = $x(".//span[text()='Переместить']/.."),

    //Models Of data
    modelsTab = $x(".//*[text()=\"Модели\"]"),
            addModelButton = $x(".//span[text()='Добавить модель']/.."),
            modelNameInput = $x(".//label[text()=\"Имя\"]/../following-sibling::div//input"),
            packageNameInput = $x(".//label[text()=\"Пакет\"]/../following-sibling::div//input"),
            sourceInput = $x(".//label[text()=\"Источник\"]/../following-sibling::div//input"),
            globalModelCheckBox = $x(".//span[text()='Глобальный']/preceding-sibling::span"),
            formatModelDataActivate = $x(".//label[text()='Формат модели данных']/../following-sibling::div"),
            formatModelDataInput = $x(".//label[text()='Формат модели данных']/../following-sibling::div//input"),
            classNameInput = $x(".//label[text()=\"Имя класса\"]/../following-sibling::div//input"),
            uploadModelDataInput = $x(".//label[text()='Выбрать файл']/../following-sibling::div//input"),
            fullUploadIcon = $x(".//span[@aria-label=\"check-circle\"]"),
            modelRowsAfterSearch = $$x(".//table/tbody/tr").find(visible),
            modelSearchInput = $x(".//span[text()='Добавить модель']/../../..//label[text()='Поиск ']/input"),
            modelNameInRowsAfterSearch = $$x(".//button[@aria-label=\"Развернуть строку\"]/../following-sibling::td[3]").find(visible),

    //Additional congfiguration
    acSaveButton = $$x(".//span[text()='Сохранить']/..").find(visible),
            additionConfigurationTab = $x(".//*[text()='Дополнительная конфигурация']"),
            addConfigurationButton = $x(".//span[text()='Добавить конфигурацию']/.."),
            editConfigurationInContextMenu = $x(".//div[text()=' Редактировать']"),
            copyConfigurationInContextMenu = $x(".//div[text()=' Скопировать в...']"),
            deleteConfigurationInContextMenu = $x(".//div[text()=' Удалить']"),

    addressForConnectionInput = $x(".//label[text()='Адрес подключения']/../following-sibling::div//input"),
            rootOfSearchInput = $x(".//label[text()='Корень поиска']/../following-sibling::div//input"),
            nameToBindInput = $x(".//label[text()='Имя для привязки']/../following-sibling::div//input"),
            passwordToBindInput = $x(".//label[text()='Пароль для привязки']/../following-sibling::div//input"),
            atributeForLoginInput = $x(".//label[text()='Атрибут для логина']/../following-sibling::div//input"),
            atributeForPasswordInput = $x(".//label[text()='Атрибут для пароля']/../following-sibling::div//input"),
            userSearchTreeInput = $x(".//label[text()='Дерево поиска пользователей']/../following-sibling::div//input"),
            rolesSearchTreeInput = $x(".//label[text()='Дерево поиска ролей']/../following-sibling::div//input"),
            rolesToEnterInput = $x(".//label[text()='Роли для входа (через запятую)']/../following-sibling::div//input"),

    //Tracing
    tracingTab = $x(".//*[text()=\"Трассировка\"]"),

    //Properties congfiguration
    propertiesOfDomainButton = $x(".//button[span[text()='Свойства']]"),
            nameDomainInPropertiesInput = $x(".//label[text()='Имя домена']/../following-sibling::div//input"),
            errorHandlerActivate = $x(".//label[text()='Обработчик ошибок']/../following-sibling::div"),
            errorHandlerInput = $x(".//label[text()='Обработчик ошибок']/../following-sibling::div//input"),
            domainTypeActivate = $x(".//label[text()='Тип домена']/../following-sibling::div"),
            domainTypeInput = $x(".//label[text()='Тип домена']/../following-sibling::div//input"),
            domainStrategyOfStartActivate = $x(".//label[text()='Стратегия запуска']/../following-sibling::div"),
            domainStrategyOfStartInput = $x(".//label[text()='Стратегия запуска']/../following-sibling::div//input"),

    propertiesErrorHandlerTab = $x(".//div[text()='Свойства обработчика ошибок']"),

    propertiesCustomerMqTab = $x(".//div[text()='Свойства клиента менеджера очередей']"),
            maximumNumberOfConnectionsInPropertiesInput = $x(".//label[text()='Максимальное количество соединений']/../following-sibling::div//input"),
            connectionsPerSessionInPropertiesInput = $x(".//label[text()='Количество сессий на соединение']/../following-sibling::div//input"),
            asynchronousRecipientsInPropertiesInput = $x(".//label[text()='Количество одновременных получателей']/../following-sibling::div//input"),

    propertiesConnectionMqTab = $x(".//div[text()='Свойства подключения к менеджеру очередей']"),
            useSettingOfBrokerCheckBoxActivate = $x(".//span[text()='Использовать настройки брокера']/preceding-sibling::span"),
            useSettingOfBrokerCheckBox = $x(".//span[text()='Использовать настройки брокера']/preceding-sibling::span/input"),
            typeOfconnectActivate = $x(".//label[text()='Тип подключения']/../following-sibling::div"),
            typeOfconnectInput = $x(".//label[text()='Тип подключения']/../following-sibling::div//input"),
            mqActivate = $x(".//label[text()='Менеджер очередей']/../following-sibling::div"),
            mqInput = $x(".//label[text()='Менеджер очередей']/../following-sibling::div//input"),

    expandPropertiesErrorHandlerButton = $x(".//div[text()='Свойства обработчика ошибок по умолчанию']/following-sibling::div"),
            useOriginalMessageCheckBox = $x(".//span[text()='Использовать оригинальное сообщение']/preceding-sibling::span"),
            policyResendActivate = $x(".//label[text()='Политика повторной отправки']/../following-sibling::div"),
            policyResendInput = $x(".//label[text()='Политика повторной отправки']/../following-sibling::div//input"),
            sopsErrorHandlingActivate = $x(".//label[text()='СОПС обработки ошибок']/../following-sibling::div"),
            sopsErrorHandlingInput = $x(".//label[text()='СОПС обработки ошибок']/../following-sibling::div//input"),

    expandPropertiePoliceResandMessageButton = $x(".//div[text()='Свойства политики повторной отправки сообщений ']/following-sibling::div"),
            loggingLevelActivate = $x(".//label[text()='Уровень логирования']/../following-sibling::div"),
            loggingLevelInput = $x(".//label[text()='Уровень логирования']/../following-sibling::div//input"),
            maximumAttemptsInput = $x(".//label[text()='Максимальное число попыток']/../following-sibling::div//input"),
            initialDelayBetweenRetriesInput = $x(".//label[text()='Исходная задержка между попытками (мс)']/../following-sibling::div//input"),
            maximumDelayBetweenRetriesInput = $x(".//label[text()='Максимальная задержка между попытками (мс)']/../following-sibling::div//input"),
            asynchronousDelayBetweenDeliveriesCheckBox = $x(".//span[text()='Асинхронная задержка между доставками']/preceding-sibling::span"),
            multiplierDelayRedeliveryInput = $x(".//label[text()='Множитель отсрочки повторной доставки']/../following-sibling::div//input"),
            enableExponentialBackoffCheckBox = $x(".//span[text()='Включить экспоненциальную отсрочку']/preceding-sibling::span"),
            enableExponentialDelayCheckBox = $x(".//span[text()='Включить экспоненциальную отсрочку']/preceding-sibling::span"),
            useCollisionAvoidanceCheckBox = $x(".//span[text()='Использовать предотвращение коллизий']/preceding-sibling::span"),
            collisionAvoidanceRatioInput = $x(".//label[text()='Коэффициент предотвращения коллизий']/../following-sibling::div//input"),
            allowForwardingWhileStoppedCheckBox = $x(".//span[text()='Разрешить переотправку во время остановки']/preceding-sibling::span"),
            useLocalAddressCheckBox = $x(".//span[text()='Использовать локальный адрес']/preceding-sibling::span"),
            mqcspAutorizationCheckBox = $x(".//span[text()='MQCSP Авторизация']/preceding-sibling::span"),

    expandPropertieDomainsStreamsTab = $x(".//div[text()='Настройка потоков домена']"),
            threadPoolSizeInPropertiesInput = $x(".//label[text()='Размер пула потоков']/../following-sibling::div//input"),
            threadPoolMaximumSizeInPropertiesInput = $x(".//label[text()='Максимальный размер пула потоков']/../following-sibling::div//input"),
            freeStreamLifetimeInPropertiesInput = $x(".//label[text()='Время жизни свободного потока (с)']/../following-sibling::div//input"),
            maximumNumberTasksInWorkQueueInPropertiesInput = $x(".//label[text()='Максимальное количество задач в рабочей очереди']/../following-sibling::div//input"),
            abandonmentStrategyInPropertiesActivate = $x(".//label[text()='Стратегия отказа']/../following-sibling::div//input/../../.."),
            abandonmentStrategyInPropertiesInput = $x(".//label[text()='Стратегия отказа']/../following-sibling::div//input"),
            abandonmentStrategyInPropertiesValue = $x(".//label[text()='Стратегия отказа']/../following-sibling::div//span[@class=\"ant-select-selection-item\"]"),

    constantesDomainTab = $x(".//*[text()=\"Константы\"]"),
            addConstantButton = $x(".//span[text()='Добавить константу']/.."),

    typeObjectSelect = $x(".//label[text()='Тип объекта']/../following-sibling::div"),
            typeObjectInput = $x(".//label[text()='Тип объекта']/../following-sibling::div//input"),
            prefixObjectInput = $x(".//label[text()='Префикс']/../following-sibling::div//input"),
            schemeObjectInput = $x(".//label[text()='Схема']/../following-sibling::div//input"),


    nameObjectInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            typeFabricActivate = $x(".//label[text()='Тип']/../following-sibling::div"),
            typeFabricInput = $x(".//label[text()='Тип']/../following-sibling::div//input"),
            nameConfigurationInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            classJdbcDriverInput = $x(".//label[text()='Класс JDBC драйвера']/../following-sibling::div//input"),
            classConfigurationInput = $x(".//label[text()='Класс']/../following-sibling::div//input"),
            typeConfigurationInput = $x(".//label[text()='Тип']/../following-sibling::div//input"),
            typeDataFormatInput = $x(".//label[text()='Тип формата данных']/../following-sibling::div//input"),
            urlDbInput = $x(".//label[text()='URL базы данных']/../following-sibling::div//input"),
            userNameDbOrMqInput = $x(".//label[text()='Имя пользователя']/../following-sibling::div//input"),
            userPasswordDbOrMqInput = $x(".//label[text()='Пароль']/../following-sibling::div//input"),
            poolSizeInput = $x(".//label[text()='Размер пула']/../following-sibling::div//input"),
            maxPoolSizeInput = $x(".//label[text()='Максимальный размер пула']/../following-sibling::div//input"),
            queueNameInput = $x(".//label[text()='Имя очереди']/../following-sibling::div//input"),
            maxQueueSizeInput = $x(".//label[text()='Максимальный размер очереди']/../following-sibling::div//input"),

    ipAddressInput = $x(".//label[text()='IP-адрес']/../following-sibling::div//input"),
            portInput = $x(".//label[text()='Порт']/../following-sibling::div//input"),
            queueManagerNameInput = $x(".//label[text()='Имя администратора очередей']/../following-sibling::div//input"),
            chanellNameInput = $x(".//label[text()='Канал']/../following-sibling::div//input"),
            userNameInput = $x(".//label[text()='Имя пользователя']/../following-sibling::div//input"),
            passwordInput = $x(".//label[text()='Пароль']/../following-sibling::div//input"),
            applicationInput = $x(".//label[text()='Приложение']/../following-sibling::div//input"),
            threadPoolSizeInput = $x(".//label[text()='Размер пула потоков']/../following-sibling::div//input"),
            TimeoutInput = $x(".//label[text()='Тайм-аут (мс)']/../following-sibling::div//input"),
            transactionPolicyInConfigurationActivate = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div"),
            transactionPolicyInConfigurationInput = $x(".//label[text()='Политика распространения транзакций']/../following-sibling::div//input"),
            entryPointConnectionParametersInConfigurationActivate = $x(".//label[text()='Параметры подключения точки входа']/../following-sibling::div"),
            entryPointConnectionParametersInConfigurationInput = $x(".//label[text()='Параметры подключения точки входа']/../following-sibling::div//input"),
            exitPointConnectionParametersInConfigurationActivate = $x(".//label[text()='Параметры подключения точки выхода']/../following-sibling::div"),
            exitPointConnectionParametersInConfigurationInput = $x(".//label[text()='Параметры подключения точки выхода']/../following-sibling::div//input"),

    identificatorErrorHandlerActivate = $x(".//label[text()='Идентификатор обработчика ошибок']/../following-sibling::div"),
            identificatorErrorHandlerInput = $x(".//label[text()='Идентификатор обработчика ошибок']/../following-sibling::div//input"),
            redeliveryPolicyErrorHandlerActivate = $x(".//label[text()='Политика повторной доставки']/../following-sibling::div"),
            redeliveryPolicyErrorHandlerInput = $x(".//label[text()='Политика повторной доставки']/../following-sibling::div//input"),
            addAdressButton = $$x(".//span[text()=\"Добавить адрес\"]/..").find(visible),
            ipObjectInput = $x(".//label[text()='IP-адрес']/../following-sibling::div//input"),
            portObjectInput = $x(".//label[text()='Порт']/../following-sibling::div//input"),
            managerObjectInput = $x(".//label[text()='Имя администратора очередей']/../following-sibling::div//input"),
            nameChanelObjectInput = $x(".//label[text()='Канал']/../following-sibling::div//input"),
            userNameObjectInput = $x(".//label[text()='Имя пользователя']/../following-sibling::div//input"),
            passwordObjectInput = $x(".//label[text()='Пароль']/../following-sibling::div//input"),
            nameAppObjectInput = $x(".//label[text()='Приложение']/../following-sibling::div//input"),
            threadPoolSizeObjectInput = $x(".//label[text()='Размер пула потоков']/../following-sibling::div//input"),
            prefixConfigurationInput = $x(".//label[text()='Префикс']/../following-sibling::div//input"),
            schemaConfigurationInput = $x(".//label[text()='Схема']/../following-sibling::div//input"),
            keyPasswordConfigurationInput = $x(".//div[text()='KeyManager']/../../..//label[text()='KeyPassword']/../following-sibling::div//input"),
            pathToSourceOfKeyPasswordConfigurationInput = $x(".//div[text()='KeyManager']/../../..//label[text()='Путь к ресурсу']/../following-sibling::div//input"),
            passwordOfSourceOfKeyPasswordConfigurationInput = $x(".//div[text()='KeyManager']/../../..//label[text()='Пароль']/../following-sibling::div//input"),
            pathToSourceOfTrustManagerConfigurationInput = $x(".//div[text()='TrustManager']/../../..//label[text()='Путь к ресурсу']/../following-sibling::div//input"),
            passwordOfSourceOfTrustManagerConfigurationInput = $x(".//div[text()='TrustManager']/../../..//label[text()='Пароль']/../following-sibling::div//input"),
            timeoutOfSessionInput = $x(".//div[text()='ServerParameters']/../../..//label[text()='Тайм-аут SSL сессии (с)']/../following-sibling::div//input"),
            autentificationOfCustomerActivate = $x(".//div[text()='ServerParameters']/../../..//label[text()='Клиентская аутентификация']/../following-sibling::div"),
            autentificationOfCustomerInput = $x(".//div[text()='ServerParameters']/../../..//label[text()='Клиентская аутентификация']/../following-sibling::div//input"),
            adressOfServerInConfigurationInput = $x(".//label[text()='Адрес сервера']/../following-sibling::div//input"),
            typeOfAutorizationInConfigurationActivate = $x(".//label[text()='Тип авторизации']/../following-sibling::div"),
            typeOfAutorizationInConfigurationInput = $x(".//label[text()='Тип авторизации']/../following-sibling::div//input"),
            loginInConfigurationInput = $x(".//label[text()='Логин']/../following-sibling::div//input"),
    //ErrorHandler
    acEHConfigurationName = $x(".//div[label[text()='Имя конфигурации']]//input"),
            acEHIdentifier = $x(".//div[label[text()='Идентификатор обработчика ошибок']]/div//input"),

    objectTypeButton = $x(".//div[label[text()='Тип объекта']]//select"),
            additionalСonfigurationImportDomainCheckBox = $x(".//span[text()='redeliveryPolicy: myRedeliveryPolicyConfig']/" +
                    "preceding-sibling::span[text()='deadLetterUri: direct-vm://myDLC']/preceding-sibling::span[text()='class:  org.apache.camel.builder.DeadLetterChannelBuilder']/" +
                    "../preceding-sibling::td/div[text()=' Обработчик ошибок']/../preceding-sibling::td[text()='test_configuration']//preceding-sibling::td/input");
    SelenideElement valueSelectedElement = $x("/../following-sibling::div//input/../following-sibling::span");
    SelenideElement statisticsTab = $x(".//*[text()='Статистика']");
    SelenideElement clearStatisticButton = $x(".//span[text()='Очистить']/..");
    SelenideElement perfomanceElementOfList = $x(".//li[text()='Точка выхода \"Производительность\"']");
    SelenideElement metricsElementOfList = $x(".//li[text()='Точка выхода \"Метрика\"']");
    SelenideElement sizeOfCacheSessionsInput = $x(".//label[text()='Размер кэша сессий']/../following-sibling::div//input");
    SelenideElement cacheProducersCheckBox = $x(".//span[text()='Кэшировать производителей']/preceding-sibling::span//input[@name=\"cacheProducers\"]/..");
    SelenideElement cacheCustomersCheckBox = $x(".//span[text()='Кэшировать потребителей']/preceding-sibling::span//input[@name=\"cacheConsumers\"]/..");
    SelenideElement reopenConnectionOnErrorCheckBox = $x(".//span[text()='Повторно открывать соединение в случае ошибки']/preceding-sibling::span//input[@name=\"reconnectOnException\"]/..");
    SelenideElement userNameActivate = $x(".//label[text()='Имя пользователя']/../following-sibling::div");
//    SelenideElement userNameInput = $x(".//label[text()='Имя пользователя']/../following-sibling::div//input");


    ElementsCollection sopsRowsAfterSearch = $$x(".//table[thead[tr[th[text()='Идентификатор СОПС']]]]/tbody/tr[@role=\"row\"]");
    private ElementsCollection afterSearchSOPSTableRows = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")][not(@style)][count(td) = 9]");
    private ElementsCollection afterSearchSOPSInTracingRows = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]");
    private ElementsCollection nameConfigurationInTable = $$x(".//span[@title=\"Не доступен\" or @title=\"Доступен\"]");
    private ElementsCollection typeConfigurationInTable = $$x(".//span[@title=\"Не доступен\" or @title=\"Доступен\"]/../following-sibling::td/div");
    private ElementsCollection nameVariableOfDomain = $$x(".//input[@name=\"key\"]");
    private ElementsCollection valueVariableOfDomain = $$x(".//input[@name=\"value\"]");
    private ElementsCollection nameVariableOfDomainOrBrokerInTable = $$x(".//td[@class=\"ant-table-cell key\"]");
    private ElementsCollection valueVariableOfDomainOrBrokerInTable = $$x(".//td[@class=\"ant-table-cell value\"]");
    SelenideElement nameVariableOfBroker = $x(".//input[@name=\"key\"]");
    SelenideElement valueVariableOfBroker = $x(".//input[@name=\"value\"]");
    ElementsCollection typeConfigurationList = $$x(".//div[@class=\"ant-select-item-option-content\"]");
    ElementsCollection rowInexpandConfiguration = $$x(".//span[text()='Свойства конфигурации']/../following-sibling::div//tr[@data-row-key]");
    ElementsCollection schemeHostAddConfigurationActivate = $$x(".//td[@class=\"ant-table-cell scheme\"]");
    ElementsCollection schemeHostAddConfigurationInput = $$x(".//td[@class=\"ant-table-cell scheme\"]//input");
    ElementsCollection addressHostAddConfigurationInput = $$x(".//td[@class=\"ant-table-cell host\"]//input");
    ElementsCollection portHostAddConfigurationInput = $$x(".//td[@class=\"ant-table-cell port\"]//input");
    ElementsCollection deleteAdressButton = $$x(".//span[@class=\"anticon anticon-delete\"]/..");
    ElementsCollection breackpointsIcon = $$x(".//i[@class=\"fa fa-fw fa-bug\"]");
    ElementsCollection selectedBreackpointsIcon = $$x(".//i[@class=\"fa fa-fw fa-bug red\"]");
    ElementsCollection playBreackpointsIcon = $$x(".//i[@class=\"fa fa-fw fa-play red\"]");
    ElementsCollection deletePointsIcon = $$x(".//i[@class=\"fa fa-fw fa-trash\"]");
    ElementsCollection nameVariableInput = $$x(".//input[@name=\"key\"]");
    ElementsCollection valueVariableInput = $$x(".//input[@name=\"value\"]");
    ElementsCollection constantesStatusInTable = $$x(".//span[@class=\"anticon anticon-unlock\"]");
    ElementsCollection modelListRowsAfterSearch = $$x(".//span[text()='Добавить модель']/../../../../..//table/tbody/tr[not(@aria-hidden)]");
    ElementsCollection listUserNameInput = $$x(".//input[@name=\"username\"]");
    ElementsCollection listUserPasswordInput = $$x(".//input[@name=\"password\"]");
    ElementsCollection listUserHomeDirectoryInput = $$x(".//input[@name=\"homeDirectory\"]");
    ElementsCollection attributeNameInput = $$x(".//label[text()='Имя']/../following-sibling::div//input");
    ElementsCollection attributeValueInput = $$x(".//label[text()='Значение']/../following-sibling::div//input");
    ElementsCollection listOfPointSops = $$x(".//div[@class=\"route-scheme-panel\"]//li[not(@class=\"endpoint-header\")]");
    ElementsCollection expandTracingButton = $$x(".//button[@aria-label=\"Развернуть строку\"]");
    ElementsCollection rowMessageInTrace = $$x(".//tr[contains(@data-row-key,\"ID\")]");

    public enum AdditionalConfigurationName {DATA_SOURCE, ERROR_HANDLER, WMQ_SESSION_FACTORY, MQ_SESSION_FACTORY, THREAD_POOL, WEB_SERVICE_CONFIG, DATA_FORMAT, NAMESPACE, LOCAL_USERS, LDAP}

    public enum PointEnabelOrDisable {ENABLED, DISABLED}

    public enum thingEnableOrDisable {ENABLED, DISABLED}

    public enum typeOfDomain {NEW, EXISTED, EXISTED_AND_DELETE_OLD_DOMAIN}

    public SOPSPage() {
        if (!domainsOfBrokerTab.attr("class").equals("active")) {
            click(domainsOfBrokerTab);
        }
    }

    public SOPSPage(String Empty) {
    }

    @Step("Creation {0}")
    public void createDomain(String domaimName) {
        String url1 = url();
        String url2 = url();
        click(createDomainButton);
        val(domainNameInput, domaimName);
        click(saveDomainButton);

        do {
            url2 = url();
        } while (url1.equals(url2) || saveDomainButton.isDisplayed());
    }


    @Step("Run {0}")
    public void startDomain(String domainName) {
        goToDomain(domainName);
        nameDomainInHeader.should(exactText("Домен: " + domainName));
        click(runDomainButton);
        elementShouldBeVisible(stopDomainButton);
        commonComponents.closeNotification();
    }

    @Step("Stop {0}")
    public void stopDomain(String domainName) {
        goToDomain(domainName);
        if (stopDomainButton.exists()) {
            click(stopDomainButton);
        }
        commonComponents.closeNotification();
    }

    @Step("Run {0}")
    public void startDomainInGroup(String domainGroup, String domainName) {
        goToDomainInGroup(domainGroup, domainName);
        nameDomainInHeader.should(exactText("Домен: " + domainGroup + "/" + domainName));
        click(runDomainButton);
        elementShouldBeVisible(stopDomainButton);
        commonComponents.closeNotification();
    }

    @Step("Deleting {0}")
    public void deleteDomain(String domainName) {
        goToDomain(domainName);
        click(actionsUnderDomainsButton);
        click(deleteDomainButton);
        click(confirmationDeleteButton);
    }

    @Step("Restart Domain In Widget {0}")
    public void restartDomainInWidget() {
        click(widgetsHistoryMenu);
        click($x(".//span[contains(text(),\"Перезапустить\")]"));
    }

    @Step("Click button to add SOPS in {0}")
    public void clickToAddSOPSButton(String domainName) {
        goToDomain(domainName);
        autoUpdateOff();
        click(listSOPSTab);
        click(addSOPSButton);
    }

    @Step("Import domain {2}")
    public void importDomain(typeOfDomain type, String domainfile, String domainName) {
        importDomainInput.sendKeys(domainfile);
        sleep(2000);
        sout(importAsNewDomainButton.getAttribute("aria-checked"));
        if (type.equals(typeOfDomain.NEW)) {
            if (!importAsNewDomainButton.getAttribute("aria-checked").equals("true")) {
                click(importAsNewDomainButton);
            }
            click(nameOfDamainInImportActivate);
            valAndSelectElement(nameOfDamainInImportInput, domainName);
        } else if (type.equals(typeOfDomain.EXISTED_AND_DELETE_OLD_DOMAIN)) {
            if (importAsNewDomainButton.getAttribute("aria-checked").equals("true")) {
                click(importAsNewDomainButton);
            }
            click(nameOfDamainInImportActivate);
            valAndSelectElement(nameOfDamainInImportInput, domainName);
            click(deleteOldDomainheckBox);
        } else if (type.equals(typeOfDomain.EXISTED)) {
            if (importAsNewDomainButton.getAttribute("aria-checked").equals("true")) {
                click(importAsNewDomainButton);
            }
            click(nameOfDamainInImportActivate);
            valAndSelectElement(nameOfDamainInImportInput, domainName);
        }
        click(confirmationImportButton);
    }

    @Step("Import domain")
    public void cancelImportDomain(String domainFile, String domainName) {
        importDomainInput.sendKeys(domainFile);
        closeForm();
        Assert.assertFalse($x(String.format(specificDomainButtonXpath, domainName)).exists());
    }

    @Step("Import domain")
    public void importDomain(String type, String domainName, String DomainFile, String pointIn, String nameAddConfig,
                             String typeAddConfig, String property1AddConfig, String property2AddConfig, String property3AddConfig,
                             String property4AddConfig,
                             String identificatorSops, String jsFileName, String jsPointName, String xsdFileName,
                             String xsdPointName, String groovyFileName, String groovyPointName, String routsFileName,
                             String routsUsedIN, String xsltFileName, String xsltPointName, String jsonFileName, String jsonPointName,
                             String localQueueMessageFileName, String localQueuemessagePointName, String constanteName, String constanteValue) {
        switch (type) {
            case ("все"):
                importDomainInput.sendKeys(DomainFile);
                click(nameOfDamainInImportActivate);
                valAndSelectElement(nameOfDamainInImportInput, domainName);

                historyInImportCheckBox.shouldBe(selected);
                modelsInImportCheckBox.shouldBe(selected);
                click(toChooseSopsButton);

                $x(String.format(sopsInImportDomainCheckBox, pointIn + "?transacted=true", identificatorSops)).isSelected();
                click(toChooseConfigurationButton);

                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
                click(toChooseConstantsButton);

                $x(String.format(variableForImport, constanteValue, constanteName)).isSelected();
                click(toChooseMessagesButton);

                $x(String.format(variableForImport, localQueuemessagePointName, localQueueMessageFileName)).isSelected();
                click(toChoosetableOfRoutsButton);

                $x(String.format(fileForImport, routsUsedIN, routsFileName.replace(".xml", ""))).isSelected();
                click(toChooseFilesButton);

                $x(String.format(fileForImport, jsonPointName, "resources/" + jsonFileName)).isSelected();
                $x(String.format(fileForImport, xsltPointName, "resources/" + xsltFileName)).isSelected();
                $x(String.format(fileForImport, xsdPointName, "resources/" + xsdFileName)).isSelected();
                $x(String.format(fileForImport, jsPointName, "resources/" + jsFileName)).isSelected();
                $x(String.format(fileForImport, groovyPointName, "resources/" + groovyFileName)).isSelected();

//                $x(String.format(fileForImport, localQueuemessagePointName, localQueueMessageFileName)).isSelected();
//                click(toChooseVariablesButton);


                break;
            case ("только домен"):
                importDomainInput.sendKeys(DomainFile);
                click(nameOfDamainInImportActivate);
                valAndSelectElement(nameOfDamainInImportInput, domainName);

                historyInImportCheckBox.shouldNotBe(selected);
                modelsInImportCheckBox.shouldNotBe(selected);
                click(toChooseSopsButton);

                $x(String.format(sopsInImportDomainCheckBox, pointIn + "?transacted=true", identificatorSops)).isSelected();
                click(toChooseConfigurationButton);

                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
                toChooseFilesButton.shouldNotBe(exist);
                toChooseVariablesButton.shouldNotBe(exist);
                break;
            case ("все с редактированием данных в форме импорта"):
//                domainOld = domainName + "-Old";
                String Cook = api.loginAPI("http://192.168.57.240:9912", ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
                sopsApi.createDomainAPI("http://192.168.57.240:9912", Cook, domainName);
                refresh();
                importDomainInput.sendKeys(DomainFile);
                historyInImportCheckBox.shouldBe(selected);
                click(historyInImportCheckBox.parent());
                historyInImportCheckBox.shouldNotBe(selected);
                modelsInImportCheckBox.shouldBe(selected);
                click(modelsInImportCheckBox.parent());
                modelsInImportCheckBox.shouldNotBe(selected);

                click(importAsNewDomainButton);
                click(nameOfDamainInImportActivate);
                valAndSelectElement(nameOfDamainInImportInput, domainName);
                click(toChooseSopsButton);

                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).shouldBe(selected);
                click($x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).parent());
                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).shouldNotBe(selected);
                click(toChooseConfigurationButton);

                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).shouldBe(selected);
                click($x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).parent());
                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).shouldNotBe(selected);
                click(toChooseConstantsButton);

                $x(String.format(variableForImport, constanteValue, constanteName)).isSelected();
                click($x(String.format(sopsInImportDomainCheckBox, constanteValue, constanteName)).parent());
                click(toChooseMessagesButton);

                $x(String.format(variableForImport, localQueuemessagePointName, localQueueMessageFileName)).isSelected();
                click($x(String.format(sopsInImportDomainCheckBox, localQueuemessagePointName, localQueueMessageFileName)).parent());
                click(toChoosetableOfRoutsButton);

                $x(String.format(fileForImport, routsUsedIN, routsFileName.replace(".xml", ""))).isSelected();
                click($x(String.format(sopsInImportDomainCheckBox, routsUsedIN, routsFileName.replace(".xml", ""))).parent());
                click(toChooseFilesButton);

                $x(String.format(fileForImport, jsonPointName, "resources/" + jsonFileName)).shouldBe(selected);
                click($x(String.format(fileForImport, jsonPointName, "resources/" + jsonFileName)).parent());
                $x(String.format(fileForImport, jsonPointName, "resources/" + jsonFileName)).shouldNotBe(selected);
                $x(String.format(fileForImport, xsltPointName, "resources/" + xsltFileName)).shouldBe(selected);
                click($x(String.format(fileForImport, xsltPointName, "resources/" + xsltFileName)).parent());
                $x(String.format(fileForImport, xsltPointName, "resources/" + xsltFileName)).shouldNotBe(selected);
                $x(String.format(fileForImport, xsdPointName, "resources/" + xsdFileName)).shouldBe(selected);
                click($x(String.format(fileForImport, xsdPointName, "resources/" + xsdFileName)).parent());
                $x(String.format(fileForImport, xsdPointName, "resources/" + xsdFileName)).shouldNotBe(selected);
                $x(String.format(fileForImport, jsPointName, "resources/" + jsFileName)).shouldBe(selected);
                click($x(String.format(fileForImport, jsPointName, "resources/" + jsFileName)).parent());
                $x(String.format(fileForImport, jsPointName, "resources/" + jsFileName)).shouldNotBe(selected);
                $x(String.format(fileForImport, groovyPointName, "resources/" + groovyFileName)).shouldBe(selected);
                click($x(String.format(fileForImport, groovyPointName, "resources/" + groovyFileName)).parent());
                $x(String.format(fileForImport, groovyPointName, "resources/" + groovyFileName)).shouldNotBe(selected);


//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, routsUsedIN, routsFileName)).parent());
//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).shouldNotBe(selected);
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, groovyPointName, groovyFileName)).parent());
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).shouldNotBe(selected);
//                $x(String.format(fileForImport, jsPointName, jsFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, jsPointName, jsFileName)).parent());
//                $x(String.format(fileForImport, jsPointName, jsFileName)).shouldNotBe(selected);
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, xsltPointName, xsltFileName)).parent());
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).shouldNotBe(selected);
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, xsdPointName, xsdFileName)).parent());
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).shouldNotBe(selected);
//                $x(String.format(fileForImport, jsonPointName, jsonFileName)).shouldBe(selected);
//                click($x(String.format(fileForImport, jsonPointName, jsonFileName)).parent());
//                $x(String.format(fileForImport, jsonPointName, jsonFileName)).shouldNotBe(selected);
//                click(toChooseVariablesButton);
//
//                $x(String.format(variableForImport, constanteValue, constanteName)).shouldBe(selected);
//                click($x(String.format(variableForImport, constanteValue, constanteName)).parent());
//                $x(String.format(variableForImport, constanteValue, constanteName)).shouldNotBe(selected);
                break;
//            case ("домен + JS и Groovy"):
//                historyInImportCheckBox.shouldNotBe(selected);
//                modelsInImportCheckBox.shouldNotBe(selected);
//                click(toChooseSopsButton);
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                click(toChooseFilesButton);
//
//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).isSelected();
//                $x(String.format(fileForImport, jsPointName, jsFileName)).isSelected();
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, jsonPointName, jsonFileName)).shouldNotBe(exist);
////                $x(String.format(fileForImport, localQueuemessagePointName, localQueueMessageFileName)).isSelected();
//                toChooseVariablesButton.shouldNotBe(exist);
//                break;
//            case ("домен + XSLT"):
//                historyInImportCheckBox.shouldNotBe(selected);
//                modelsInImportCheckBox.shouldNotBe(selected);
//                click(toChooseSopsButton);
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                click(toChooseFilesButton);
//
//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, jsPointName, jsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).isSelected();
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, jsonPointName, jsonQueueFileName)).shouldNotBe(exist);
//                toChooseVariablesButton.shouldNotBe(exist);
//                break;
//            case ("домен + XSD"):
//                historyInImportCheckBox.shouldNotBe(selected);
//                click(toChooseSopsButton);
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                click(toChooseFilesButton);
//
//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, jsPointName, jsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).isSelected();
//                toChooseVariablesButton.shouldNotBe(exist);
//                break;
//            case ("домен + таблицы маршрутов"):
//                historyInImportCheckBox.shouldNotBe(selected);
//                click(toChooseSopsButton);
//
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                click(toChooseFilesButton);
//
//                $x(String.format(fileForImport, routsUsedIN, routsFileName)).isSelected();
//                $x(String.format(fileForImport, groovyPointName, groovyFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, jsPointName, jsFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsltPointName, xsltFileName)).shouldNotBe(exist);
//                $x(String.format(fileForImport, xsdPointName, xsdFileName)).shouldNotBe(exist);
//                toChooseVariablesButton.shouldNotBe(exist);
//                break;
//            case ("домен + переменные домена"):
//                historyInImportCheckBox.shouldNotBe(selected);
//                click(toChooseSopsButton);
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                toChooseFilesButton.shouldNotBe(exist);
//                click(toChooseVariablesButton);
//
//                $x(String.format(variableForImport, constanteValue, constanteName)).isSelected();
//                break;
//            case ("домен + история изменения СОПС"):
//                historyInImportCheckBox.shouldBe(selected);
//                click(toChooseSopsButton);
//
//                $x(String.format(sopsInImportDomainCheckBox, pointIn, identificatorSops)).isSelected();
//                click(toChooseConfigurationButton);
//
//                $x(String.format(additionalСonfigurationImportDomainCheckBox2, typeAddConfig, nameAddConfig)).isSelected();
//                click($x(String.format(expandConfigurationForImportButton, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//                compareStringAndElementText(property4AddConfig, rowInexpandConfiguration.get(3));
//                toChooseFilesButton.shouldNotBe(exist);
//                toChooseVariablesButton.shouldNotBe(exist);
//                break;

            default:
                throw new AssertionError("Пропущен выбор импортируемых параметров");
        }
        click(confirmationImportButton);
    }

    @Step("Search {0} SOPS")
    public void searchSops(String sopsName) {
//        autoUpdateOn();
        sopsSearchInput.clear();
        val(sopsSearchInput, sopsName);
        sleep(500);
//        if (!cellNameSOPS.isDisplayed()) click(refreshSearchButton);
//
//        try {
//            cellNameSOPS.shouldBe(visible);
//        } catch (ElementNotFound e) {
//            System.out.println("СОПС не нашелся, повторяю поиск");
//            refresh();
//            val(sopsSearchInput, sopsName);
//        }
//        autoUpdateOff();
    }

    @Step("Search {0} SOPS")
    public void searchModel(String modelName) {
        modelSearchInput.clear();
        val(modelSearchInput, modelName);
        try {
            modelNameInRowsAfterSearch.shouldBe(visible);
        } catch (ElementNotFound e) {
            System.out.println("Модель не нашлась, повторяю поиск");
            refresh();
            val(modelSearchInput, modelName);
        }
    }

    @Step
    public void searchAddConfiguration(String addConfigurationName) {
//        queueManagerPage.searchQueue("TO.IBM");
        click(searchNameConfigurationIcon);
        val(searchNameConfigurationInput, addConfigurationName);
        click(searchNameButton);
//        try {
//            $x(String.format(".//div[text()]/../preceding-sibling::td//span[text()='%s']/../preceding-sibling::td/button", addConfigurationName)).shouldBe(visible);
//        } catch (ElementNotFound e) {
//            System.out.println("Дополнительная конфигурация не нашлась, повторяю поиск");
//            refresh();
//            val(addConfigurationSearchInput, addConfigurationName);
//        }
    }

    @Step("Edit SOPS name {1} to {2} in {0}")
    public void editSOPSName(String domainName, String sopsName, String editedSOPSName) {
        click($x(String.format(specificDomainButtonXpath, domainName)));
        searchSops(sopsName);
        click(cellNameSOPS);
        creationSOPSPage = new CreationSOPSPage();
        val(creationSOPSPage.sopsNameInput, editedSOPSName);
        click(creationSOPSPage.saveIcon);
        val(creationSOPSPage.commentInput, "Ком");
        click(creationSOPSPage.saveCommentButton);
        sleep(1500);
        click(creationSOPSPage.returnToSOPSList);
    }

    @Step()
    public void editPointOfSopsName(String domainName, String sopsName, String[] points) {
        goToSops(domainName, sopsName);

        int x = 0;
        for (String point : points) {
//            click(listOfPointSops.get(Integer.parseInt(point.split("-")[0])));
            click(listOfPointSops.get(Integer.parseInt(point.split("-")[0])));
            creationSOPSPage = new CreationSOPSPage();
            val(creationSOPSPage.nameComponentInput, point.split("-")[1]);
            x++;
        }


//        creationSOPSPage = new CreationSOPSPage();
//        val(creationSOPSPage.sopsNameInput, editedSOPSName);
        click(creationSOPSPage.saveIcon);
        val(creationSOPSPage.commentInput, "Ком");
        click(creationSOPSPage.saveCommentButton);
        sleep(1500);
        click(creationSOPSPage.returnToSOPSList);
    }

    @Step("Open SOPS name {1} in {0}")
    public void goToSops(String domainName, String sopsName) {
        goToDomain(domainName);
//        click($x(String.format(specificDomainButtonXpath, domainName)));
        click(listSOPSTab);
        searchSops(sopsName);
        if (cellNameSOPS.is(not(visible))) {
            click(refreshSearchButton);
        }
        click(cellNameSOPS);
    }

    @Step("Clone SOPS {1} in {0}")
    public void cloneSOPS(String domainName, String sopsName) {
        click($x(String.format(specificDomainButtonXpath, domainName)));
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(cloneSOPS);
        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.saveIcon);
        val(creationSOPSPage.commentInput, "Ком");
        click(creationSOPSPage.saveCommentButton);
        sleep(1000);
        click(creationSOPSPage.returnToSOPSList);
    }

    @Step("Send messsage in {0}")
    public void sendMessage(String sopsName, Integer times, String Message) {
        commonComponents.sleepTime(3000);
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(sendMessage);
        if (messagePage.howManyMessagesToSend.isDisplayed()) {
            val(messagePage.messageTextAria, Message);
            val(messagePage.howManyMessagesToSend, times.toString());
            click(messagePage.sendMessageButton);
        } else {
            click(messagePage.sendMessageButton2);
            closeForm();
        }
    }

    @Step("Send generated messsage in {0}")
    public void sendGeneratedMessage(String sopsName, Integer times, String Size) {
        commonComponents.sleepTime(3000);
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(sendMessage);
        click(messagePage.kindOfContentMessageInput);
        click($x(String.format(messagePage.typeMessageInList, "Сгенерировать")));
        val(messagePage.sizeOfBodyMessageInput, Size);
        val(messagePage.howManyMessagesToSend, times.toString());
        click(messagePage.sendMessageButton);
    }

    @Step("Send {1} saved messsages in {0}")
    public void sendNSavedMessages(String sopsName, int messagesNumber) {
        commonComponents.sleepTime(3000);
        searchSops(sopsName);
        for (int i = 0; i < messagesNumber; i++) {
            contextClick(rowAfterSearchSOPS);
            click(sendSavedMessage);
            commonComponents.closeNotification();
        }
    }

    @Step("Send messsage in {0}")
    public void sendMessageInHttp(String sopsName, String Message, int num) {
        commonComponents.sleepTime(3000);
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(sendMessage);
        System.out.println($("textarea[name='message']").getValue());
        Selenide.executeJavaScript("document.querySelector(\"textarea[name='message']\").style=\"display: block;\"");
        System.out.println($("textarea[name='message']").getValue());
        messagePage.messageTextAriaForHttp.sendKeys(Message);

        for (int x = 0; x < num; x++) click(messagePage.sendMessageInModalFormButton);
        sleep(1000);
        closeForm();
    }

    @Step("Stop SOPS {1} from Domain {0}")
    public void stopSOPS(String domainName, String sopsName) {
//        goToDomain($x(String.format(specificDomainButtonXpath, domainName)));
//        goToSops(domainName, sopsName);
        goToDomain(domainName);
//        click($x(String.format(specificDomainButtonXpath, domainName)));
        searchSops(sopsName);
        basePage.autoUpdateOff();
        contextClick(rowAfterSearchSOPS);
        click(stopSOPS);
        indicatorOfStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-error"));
    }

    @Step("Forced Stop SOPS {1} from Domain {0}")
    public void forcedStopSOPS(String domainName, String sopsName) {
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainName)));
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(forcedStopSOPS);
        indicatorOfStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-error"));
    }

    @Step("Start SOPS {1} from Domain {0}")
    public void goToDomaineAndstartSOPS(String domainName, String sopsName) {
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainName)));
        autoUpdateOff();
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(startSOPS);
        indicatorOfStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-success"));
    }

//    @Step
//    public void startDomain(String domainName) {
//        sopsPage();
//        click($x(String.format(specificDomainButtonXpath, domainName)));
//        click(runDomainButton);
//        stopDomainButton.shouldBe(visible);
//    }

    public void startSOPS(String domainName, String sopsName) {
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(startSOPS);
        indicatorOfStatus.shouldHave(attribute("class", "ant-badge-status-dot ant-badge-status-success"));
    }

    @Step("Copy SOPS {1} to {0}")
    public void copySopsToServer(String domainName1, String sopsName, String ServerName, String domainName2) {
        refresh();
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainName1)));
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(copySopsToServer);
        click($x(String.format(copySopsSelect, sopsName)));
        valAndSelectElement($x(String.format(copySopsInput, sopsName)), ServerName);
        click(domainToCopyToActivate);
        valAndSelectElement(domainToCopyToInput, domainName2);
        click(copySopsButton);
    }

    @Step("Check copied SOPS {1} to {0}")
    public void checkCopiedSopsToServer(String hostOfServer, String nameDomain, String nameSops) {
        open(hostOfServer);
        LoginPage loginPage = new LoginPage();
        loginPage.loginClickButton("root", "root");
        sopsPage();
        goToDomain(nameDomain);
        startSOPS(nameDomain, nameSops);
    }

    @Step("Deleting SOPS {1} in {0}")
    public void deleteSOPS(String domainName, String sopsName) {
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainName)));
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(deleteSOPS);
        click(confirmationDeleteButton);
        searchSops(sopsName);
        afterSearchSOPSTableRows.shouldHave(size(0));
    }

    @Step("Move SOPS {1} from Domain {0} to Domain {2}")
    public void moveSOPS(String domainNameFrom, String sopsName, String domainNameTo) {
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainNameFrom)));
        searchSops(sopsName);
        contextClick(rowAfterSearchSOPS);
        click(moveSOPS);
        click(domainToMoveActivateInput);
        valAndSelectElement(domainToMoveInput, domainNameTo);
//        domainToMoveInput.selectOption(domainNameTo);
//        click(domainToMoveSelect.$x(String.format(".//option[text()='%s']", domainNameTo)));
        click(confirmationMoveButton);
        searchSops(sopsName);
        afterSearchSOPSTableRows.shouldHave(size(0));
        click($x(String.format(specificDomainButtonXpath, domainNameTo)));
        searchSops(sopsName);
        afterSearchSOPSTableRows.shouldHave(size(1));
    }

    @Step("Reload {0} domain")
    public void reloadDomain(String domainName) {
        sopsPage();
        click($x(String.format(specificDomainButtonXpath, domainName)));
        click(actionsUnderDomainsButton);
        click(reloadDomainButton);
        commonComponents.closeNotification();
    }

    /**
     * Check a processed quantity in a SOPS
     */
    @Step("SOPS {0} should has {1} in processed quantity")
    public void sopsShouldHasNInProcessedQuantity(String sopsName, Integer processedQuantity, int waitingTime) {
        click(autoUpdateSOPSTable);
        searchSops(sopsName);
        cellProcessedSOPS.waitUntil(text(processedQuantity.toString()), waitingTime);
        click(autoUpdateSOPSTable);
    }

    /**
     * SOPS rows after search should be exist
     */
    @Step("{0} SOPS rows after search should be exist")
    public void existNumRowsAfterSearch(String domainName, String sopsName, int expectedSize) {
        refresh();
        goToDomain(domainName);
        click(autoUpdateSOPSTable);
        searchSops(sopsName);
        compareIntAndSize(expectedSize, rowAfterSearchCollection.size());
    }

    /**
     *
     */
    @Step("Add ErrorHandler in additional configuration")
    public void createErrorHandler(String configurationName, String identifierName) {
        click(additionConfigurationTab);
        click(objectTypeButton);
        click($$x(".//*[text()='Обработчик ошибок']").find(visible));
        val(acEHConfigurationName, configurationName);
        valAndSelectElement(acEHIdentifier, identifierName);
        click(acSaveButton);
    }

    @Step
    public void fillAddConfigurationWithDataWithoutSave(String domainName, String[] configAll) {
        String typeConfiguration = configAll[0].split("\\|")[0];
        String nameConfiguration = configAll[0].split("\\|")[1];

        switch (typeConfiguration) {
            case "Фабрика соединений Менеджера очередей":
                String typeFabric = configAll[0].split("\\|")[2];
                val(nameObjectInput, nameConfiguration);
                click(typeFabricActivate);
                valAndSelectElement(typeFabricInput, typeFabric);

                if (typeFabric.equals("С пулом соединений")) {
                    throw new AssertionError("Надо написать эту часть");
                } else if (typeFabric.equals("Обычная")) {
                    for (int x = 0; x < configAll.length - 1; x++) {
                        if (x > 0) {
                            click(addAdressButton);
                        }
                        click(schemeHostAddConfigurationActivate.get(x));
                        valAndSelectElement(schemeHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[0]);
                        val(addressHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[1]);
                        val(portHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[2]);
                    }
                } else if (typeFabric.equals("С кэшированными соединениями")) {
                    val(nameObjectInput, nameConfiguration);
                    val(sizeOfCacheSessionsInput, configAll[1].split("\\|")[0]);
                    if (configAll[1].split("\\|")[1].equals("true") && !cacheProducersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(cacheProducersCheckBox);
                    }
                    if (configAll[1].split("\\|")[1].equals("false") && cacheProducersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(cacheProducersCheckBox);
                    }
                    if (configAll[1].split("\\|")[2].equals("true") && !cacheCustomersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(cacheCustomersCheckBox);
                    }
                    if (configAll[1].split("\\|")[2].equals("false") && cacheCustomersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(cacheCustomersCheckBox);
                    }
                    if (configAll[1].split("\\|")[3].equals("true") && !reopenConnectionOnErrorCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(reopenConnectionOnErrorCheckBox);
                    }
                    if (configAll[1].split("\\|")[3].equals("false") && reopenConnectionOnErrorCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(reopenConnectionOnErrorCheckBox);
                    }
                    if (!configAll[1].split("\\|")[4].equals("null")) {
                        click(userNameActivate);
                        valAndSelectElement(userNameInput, configAll[1].split("\\|")[4]);
                    }
                    if (!configAll[1].split("\\|")[5].equals("null")) {
                        val(passwordInput, configAll[1].split("\\|")[5]);
                    }
                    for (int x = 1; x < configAll.length - 1; x++) {
                        if (x > 1) {
                            click(addAdressButton);
                        }
                        click(schemeHostAddConfigurationActivate.get(x - 1));
                        valAndSelectElement(schemeHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[0]);
                        val(addressHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[1]);
                        val(portHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[2]);
                    }
                }
                break;
            case "Фабрика соединений Расширенного МО":
                val(nameObjectInput, nameConfiguration);
                val(userNameDbOrMqInput, configAll[0].split("\\|")[2]);
                val(userPasswordDbOrMqInput, configAll[0].split("\\|")[3]);
                for (int x = 0; x < configAll.length - 1; x++) {
                    if (x > 0) {
                        click(addAdressButton);
                    }
                    click(schemeHostAddConfigurationActivate.get(x));
                    valAndSelectElement(schemeHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[0]);
                    val(addressHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[1]);
                    val(portHostAddConfigurationInput.get(x), configAll[x + 1].split("\\|")[2]);
                }
                break;
            case "Фабрика кэшированных соединений Менеджера очередей":
//                val(nameObjectInput, nameConfiguration);
//                val(sizeOfCacheSessionsInput, configAll[1].split("\\|")[0]);
//                if (configAll[1].split("\\|")[1].equals("true") && !cacheProducersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(cacheProducersCheckBox);
//                }
//                if (configAll[1].split("\\|")[1].equals("false") && cacheProducersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(cacheProducersCheckBox);
//                }
//                if (configAll[1].split("\\|")[2].equals("true") && !cacheCustomersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(cacheCustomersCheckBox);
//                }
//                if (configAll[1].split("\\|")[2].equals("false") && cacheCustomersCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(cacheCustomersCheckBox);
//                }
//                if (configAll[1].split("\\|")[3].equals("true") && !reopenConnectionOnErrorCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(reopenConnectionOnErrorCheckBox);
//                }
//                if (configAll[1].split("\\|")[3].equals("false") && reopenConnectionOnErrorCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
//                    click(reopenConnectionOnErrorCheckBox);
//                }
//                if (!configAll[1].split("\\|")[4].equals("null")) {
//                    click(userNameActivate);
//                    valAndSelectElement(userNameInput, configAll[1].split("\\|")[4]);
//                }
//                if (!configAll[1].split("\\|")[5].equals("null")) {
//                    val(passwordInput, configAll[1].split("\\|")[5]);
//                }
//                for (int x = 1; x < configAll.length - 1; x++) {
//                    if (x > 1) {
//                        click(addAdressButton);
//                    }
//                    click(schemeHostAddConfigurationActivate.get(x - 1));
//                    valAndSelectElement(schemeHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[0]);
//                    val(addressHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[1]);
//                    val(portHostAddConfigurationInput.get(x - 1), configAll[x + 1].split("\\|")[2]);
//                }
                break;
            case "Фабрика сессий IBM MQ":
                val(nameObjectInput, nameConfiguration);
                if (configAll[1].split("\\|")[0].equals("true") && !useLocalAddressCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(useLocalAddressCheckBox);
                }
                val(ipAddressInput, configAll[1].split("\\|")[1]);
                val(portInput, configAll[1].split("\\|")[2]);
                val(queueManagerNameInput, configAll[1].split("\\|")[3]);
                val(chanellNameInput, configAll[1].split("\\|")[4]);
                if (configAll[1].split("\\|")[5].equals("true") && !mqcspAutorizationCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(mqcspAutorizationCheckBox);
                }
                val(userNameInput, configAll[1].split("\\|")[6]);
                val(passwordInput, configAll[1].split("\\|")[7]);
                val(applicationInput, configAll[1].split("\\|")[8]);
                val(threadPoolSizeInput, configAll[1].split("\\|")[9]);
                val(TimeoutInput, configAll[1].split("\\|")[10]);
                click(transactionPolicyInConfigurationActivate);
                valAndSelectElement(transactionPolicyInConfigurationInput, configAll[1].split("\\|")[11]);
                if (!configAll[1].split("\\|")[12].equals("null")) {
                    click(entryPointConnectionParametersInConfigurationActivate);
                    valAndSelectElement(entryPointConnectionParametersInConfigurationInput, configAll[1].split("\\|")[12]);
                }
                if (!configAll[1].split("\\|")[13].equals("null")) {
                    click(exitPointConnectionParametersInConfigurationActivate);
                    valAndSelectElement(exitPointConnectionParametersInConfigurationInput, configAll[1].split("\\|")[13]);
                }
                break;
            case "Аутентификация FTP (Локальные пользователи)":
                val(nameObjectInput, nameConfiguration);
                for (int x = 1; x < configAll.length; x++) {
                    click(addButton2);
                    val(listUserNameInput.get(x - 1), configAll[x].split("\\|")[0]);
                    val(listUserPasswordInput.get(x - 1), configAll[x].split("\\|")[1]);
                    val(listUserHomeDirectoryInput.get(x - 1), configAll[x].split("\\|")[2]);
                }
                break;
            case "Аутентификация HTTP (Локальные пользователи)":
                val(nameObjectInput, nameConfiguration);
                click(restPage.typeOfAutentificationActivate);
                valAndSelectElement(restPage.typeOfAutentificationInput, configAll[0].split("\\|")[2]);

                for (int x = 0; x < configAll[1].split("-").length; x++) {
                    click(restPage.addUserButton);
                    val(restPage.loginOfUserInput.get(x), configAll[1].split("-")[x].split("\\|")[0]);
                    val(restPage.passwordOfUserInput.get(x), configAll[1].split("-")[x].split("\\|")[1]);
                    val(restPage.rolesOfUserInput.get(x), configAll[1].split("-")[x].split("\\|")[2]);
                }

                for (int x = 0; x < configAll[2].split("-").length; x++) {
                    click(restPage.addRoleButton);
                    click(restPage.expandRoleButtonList.get(x));
                    click(restPage.methodActivate);
                    click($$x(String.format(restPage.methodInList, configAll[2].split("-")[x].split("\\|")[0])).find(visible));
                    val(restPage.pathOfRoleInput.get(0), configAll[2].split("-")[x].split("\\|")[1]);
                    val(restPage.roleOfRolesInput.get(0), configAll[2].split("-")[x].split("\\|")[2]);
                    if (configAll[2].split("-")[x].split("\\|")[3].equals("true") && !restPage.autentificationOfRoleCheckBox.get(0).attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(restPage.autentificationOfRoleCheckBox.get(x));
                    }
                    if (configAll[2].split("-")[x].split("\\|")[3].equals("false") && restPage.autentificationOfRoleCheckBox.get(0).attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(restPage.autentificationOfRoleCheckBox.get(0));
                    }
                    click(restPage.collapseRoleButtonList.get(0));
                }
                break;
            case "Аутентификация HTTP (LDAP)":
                val(nameObjectInput, nameConfiguration);
                click(restPage.typeOfAutentificationActivate);
                valAndSelectElement(restPage.typeOfAutentificationInput, configAll[0].split("\\|")[2]);
                valAndSelectElement(addressForConnectionInput, configAll[0].split("\\|")[3]);
                valAndSelectElement(rootOfSearchInput, configAll[0].split("\\|")[4]);
                valAndSelectElement(nameToBindInput, configAll[0].split("\\|")[5]);
                valAndSelectElement(passwordToBindInput, configAll[0].split("\\|")[6]);
                valAndSelectElement(atributeForLoginInput, configAll[0].split("\\|")[7]);
                valAndSelectElement(atributeForPasswordInput, configAll[0].split("\\|")[8]);
                valAndSelectElement(userSearchTreeInput, configAll[0].split("\\|")[9]);
                valAndSelectElement(rolesSearchTreeInput, configAll[0].split("\\|")[10]);
                valAndSelectElement(rolesToEnterInput, configAll[0].split("\\|")[11]);

                for (int x = 0; x < configAll[1].split("-").length; x++) {
                    click(addButton2);
                    click(restPage.expandRoleButtonList.get(x));
                    click(restPage.methodActivate);
                    click($$x(String.format(restPage.methodInList, configAll[1].split("-")[x].split("\\|")[0])).find(visible));
                    val(restPage.pathOfRoleInput.get(0), configAll[1].split("-")[x].split("\\|")[1]);
                    val(restPage.roleOfRolesInput.get(0), configAll[1].split("-")[x].split("\\|")[2]);
                    if (configAll[1].split("-")[x].split("\\|")[3].equals("true") && !restPage.autentificationOfRoleCheckBox.get(0).attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(restPage.autentificationOfRoleCheckBox.get(x));
                    }
                    if (configAll[1].split("-")[x].split("\\|")[3].equals("false") && restPage.autentificationOfRoleCheckBox.get(0).attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                        click(restPage.autentificationOfRoleCheckBox.get(0));
                    }
                    click(restPage.collapseRoleButtonList.get(0));
                }
                break;
            case "Обработчик ошибок":
                val(nameConfigurationInput, nameConfiguration);
                click(identificatorErrorHandlerActivate);
                valAndSelectElement(identificatorErrorHandlerInput, configAll[1].split("\\|")[0]);
                click(redeliveryPolicyErrorHandlerActivate);
                valAndSelectElement(redeliveryPolicyErrorHandlerInput, configAll[1].split("\\|")[1]);
                break;
            case "Политика повторной отправки":
                val(nameConfigurationInput, nameConfiguration);
                click(loggingLevelActivate);
                valAndSelectElement(loggingLevelInput, configAll[1].split("\\|")[0]);
                val(maximumAttemptsInput, configAll[1].split("\\|")[1]);
                val(initialDelayBetweenRetriesInput, configAll[1].split("\\|")[2]);
                val(maximumDelayBetweenRetriesInput, configAll[1].split("\\|")[3]);
                if (configAll[1].split("\\|")[4].equals("true") && !asynchronousDelayBetweenDeliveriesCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(asynchronousDelayBetweenDeliveriesCheckBox);
                }
                val(multiplierDelayRedeliveryInput, configAll[1].split("\\|")[5]);
                if (configAll[1].split("\\|")[6].equals("true") && !enableExponentialDelayCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(enableExponentialDelayCheckBox);
                }
                if (configAll[1].split("\\|")[7].equals("true") && !useCollisionAvoidanceCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(useCollisionAvoidanceCheckBox);
                }
                val(collisionAvoidanceRatioInput, configAll[1].split("\\|")[8]);
                if (configAll[1].split("\\|")[9].equals("true") && !allowForwardingWhileStoppedCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                    click(allowForwardingWhileStoppedCheckBox);
                }
                break;
            case "Пространство имен XML":
                val(prefixConfigurationInput, configAll[1].split("\\|")[0]);
                val(schemaConfigurationInput, configAll[1].split("\\|")[1]);
                break;
            case "Источник данных":
                val(nameConfigurationInput, nameConfiguration);
                val(classJdbcDriverInput, configAll[1].split("\\|")[0]);
                val(urlDbInput, configAll[1].split("\\|")[1]);
                if (configAll[2] != null) {
                    val(userNameDbOrMqInput, configAll[2].split("\\|")[0]);
                    val(userPasswordDbOrMqInput, configAll[2].split("\\|")[1]);
                }
                break;
            case "Параметры SSL контекста":
                val(nameConfigurationInput, nameConfiguration);
                val(keyPasswordConfigurationInput, configAll[1].split("\\|")[0]);
                val(pathToSourceOfKeyPasswordConfigurationInput, configAll[1].split("\\|")[1]);
                val(passwordOfSourceOfKeyPasswordConfigurationInput, configAll[1].split("\\|")[2]);

                val(pathToSourceOfTrustManagerConfigurationInput, configAll[2].split("\\|")[0]);
                val(passwordOfSourceOfTrustManagerConfigurationInput, configAll[2].split("\\|")[1]);

                val(timeoutOfSessionInput, configAll[3].split("\\|")[0]);
                click(autentificationOfCustomerActivate);
                valAndSelectElement(autentificationOfCustomerInput, configAll[3].split("\\|")[1]);
                break;
            case "Конфигурация LDAP-соединения":
                val(nameConfigurationInput, nameConfiguration);
                val(adressOfServerInConfigurationInput, configAll[1].split("\\|")[0]);
                click(typeOfAutorizationInConfigurationActivate);
                valAndSelectElement(typeOfAutorizationInConfigurationInput, configAll[1].split("\\|")[1]);
                val(loginInConfigurationInput, configAll[1].split("\\|")[2]);
                val(passwordObjectInput, configAll[1].split("\\|")[3]);
                break;
            case "Пользовательский":
                val(nameConfigurationInput, nameConfiguration);
                val(classConfigurationInput, configAll[1].split("\\|")[0]);
                break;
            case "Стратегия агрегации":
                val(nameConfigurationInput, nameConfiguration);
                valAndSelectElement(typeConfigurationInput, configAll[0].split("\\|")[2]);
                break;
            case "Формат данных":
                val(nameConfigurationInput, nameConfiguration);
                valAndSelectElement(typeDataFormatInput, configAll[0].split("\\|")[2]);
                if (configAll[1] != null) {
                    for (int x = 0; x < configAll[1].split("-").length; x++) {
                        click(addAttributeButton);
                        val($x(".//tr[" + (x + 1) + "]//input[@name='name']"), configAll[1].split("-")[x].split("\\|")[0]);
                        val($x(".//tr[" + (x + 1) + "]//input[@name='value']"), configAll[1].split("-")[x].split("\\|")[1]);
                    }
                }
                break;
            case "Пул потоков":
                val(nameConfigurationInput, nameConfiguration);
                val(poolSizeInput, configAll[1].split("\\|")[0]);
                val(maxPoolSizeInput, configAll[1].split("\\|")[1]);
                val(queueNameInput, configAll[1].split("\\|")[2]);
                val(maxQueueSizeInput, configAll[1].split("\\|")[3]);
                break;
            default:
                throw new AssertionError("Пропущен выбор типа конфигурации");
        }
    }

    public void createAdditionalConfiguration(String domainName, String[] configAll) {
        goToDomain(domainName);
        click(additionConfigurationTab);
        click(addConfigurationButton);
        click(typeObjectSelect);
        valAndSelectElement(typeObjectInput, configAll[0].split("\\|")[0]);
        fillAddConfigurationWithDataWithoutSave(domainName, configAll);
        click(acSaveButton);
        if (domainStartedMode.exists() && !configAll[0].split("\\|")[0].equals("Пространство имен XML")) {
            try {
                click(Base.basePage.restartDomainButtonInPopUp);
            } catch (ElementNotFound e) {
                restartDomainInWidget();
            }
        }
    }

    public void editAdditionalConfiguration(String domainName, String addConfigurationNameOld, String[] configAll) {
        goToDomain(domainName);
        click(additionConfigurationTab);
        searchAddConfiguration(addConfigurationNameOld);
        doubleClick(rowAfterSearchAddConfiguration);
        fillAddConfigurationWithDataWithoutSave(domainName, configAll);
        click(acSaveButton);
        if (domainStartedMode.exists() && !configAll[0].split("\\|")[0].equals("Пространство имен XML")) {
//            click(Base.basePage.restartDomainButtonInPopUp);
            restartDomainInWidget();
        }
        sleep(1000);
    }

    public void checkAdditionalConfiguration(String domainName, String typeAddConfiguration, String addConfigurationNameNew, String[] configAll) {
        goToDomain(domainName);
        click(additionConfigurationTab);
        searchAddConfiguration(addConfigurationNameNew);
        doubleClick(rowAfterSearchAddConfiguration);
        switch (typeAddConfiguration) {
            case "Обработчик ошибок":
                compareStringAndElementValue(addConfigurationNameNew, nameConfigurationInput);
                compareStringAndElementText(configAll[1].split("\\|")[0], $x(".//label[text()='Идентификатор обработчика ошибок']/../following-sibling::div//span[@title]"));
                compareStringAndElementText(configAll[1].split("\\|")[1], $x(".//label[text()='Политика повторной доставки']/../following-sibling::div//span[@title]"));
                break;
            default:
                throw new AssertionError("Пропущен выбор типа конфигурации");
        }
        click(closeWindowIcon);
    }

    /**
     *
     */
    @Step("Create NameSpace in Additional Configuration")
    public void createNameSpaceInAdditionalConfiguration(String prefix, String schema) {
        click(additionConfigurationTab);
        click(addConfigurationButton);
        click(typeObjectSelect);
        valAndSelectElement(typeObjectInput, "Пространство имен XML");
//        typeObjectSelect.selectOption("Пространство имен");
        val(prefixObjectInput, prefix);
        val(schemeObjectInput, schema);
        click(acSaveButton);
    }

    /**
     *
     */
    @Step("Create NameSpace in Additional Configuration")
    public void createWmqSessionFactoryInAdditionalConfiguration(String name, String ip, String port, String nameManager,
                                                                 String nameChanel, String user, String password,
                                                                 String nameApp, String threadPoolSize) {
        click(additionConfigurationTab);
        click(addConfigurationButton);
        click(typeObjectSelect);
        valAndSelectElement(typeObjectInput, "Фабрика сессий IBM MQ");
//        typeObjectSelect.selectOption("WMQ Session Factory");
        val(nameObjectInput, name);
        val(ipObjectInput, ip);
        val(portObjectInput, port);
        val(managerObjectInput, nameManager);
        val(nameChanelObjectInput, nameChanel);
        val(userNameObjectInput, user);
        val(passwordObjectInput, password);
        val(nameAppObjectInput, nameApp);
        val(threadPoolSizeObjectInput, threadPoolSize);
        click(acSaveButton);
    }

    @Step("Check all parameters SOPS {0}")
    public void sopsCheckAllParameters(String sopsName, String pointIn, Integer processed) {
        autoUpdateOn();
        searchSops(sopsName);
        autoUpdateOn();
        cellNameSOPS.shouldNotBe(empty);
        sout("Ожидаемая точка входа: " + pointIn + ". Фактическая: " + cellPointInSOPS.getText());
        cellPointInSOPS.shouldHave(exactText(pointIn));
        sout("Ожидаемое значение \"Обработано\": " + processed + ". Фактическая: " + cellProcessedSOPS.getText());
        cellProcessedSOPS.shouldHave(exactText(processed.toString()));
        pendingtransactionsSOPS.shouldNotBe(empty);
//        firstProcessingSOPS.shouldNotBe(empty);
//        lastProcessingSOPS.shouldNotBe(empty);
        averageSpeedSOPS.shouldNotBe(empty);
    }

    @Step("Export domain {1}")
    public void exportDomain(String type, String domainName) {
        goToDomain(domainName);
        click(exportDomainButton);
        val(nameDomainInputForExport, domainName);
        switch (type) {
            case ("все"):
            case ("все с редактированием данных в форме импорта"):
                click(savedMessagesInLocalQueueCheckBox);
                click(historyChangesSopsCheckBox);
                break;
            case ("только домен"):
                click(geberalResouecesCheckBox);
                click(modelsCheckBox);
                click(tablesOfRoutsCheckBox);
                click(variablesOfDomainCheckBox);
                break;
//            case ("домен + JS и Groovy"):
//                click(xsltCheckBox);
//                click(xsdCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
//                break;
//            case ("домен + XSLT"):
//                click(jsAndGroovyCheckBox);
//                click(xsdCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
//                break;
//            case ("домен + XSD"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
//                break;
//            case ("домен + JSON"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
//                break;
//            case ("домен + модели"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
////                click(historyChangesSopsCheckBox);
//                break;
//            case ("домен + константы"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
////                click(historyChangesSopsCheckBox);
//                break;
//            case ("домен + сообщения"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
////                click(historyChangesSopsCheckBox);
//                break;
//            case ("домен + таблицы маршрутов"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(xsdCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(variablesOfDomainCheckBox);
////                click(historyChangesSopsCheckBox);
//                break;
//            case ("домен + переменные домена"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(xsdCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
////                click(historyChangesSopsCheckBox);
//                break;
//            case ("домен + история изменения СОПС"):
//                click(jsAndGroovyCheckBox);
//                click(xsltCheckBox);
//                click(xsdCheckBox);
//                click(jsonCheckBox);
//                click(modelsCheckBox);
//                click(tablesOfRoutsCheckBox);
//                click(variablesOfDomainCheckBox);
//                click(historyChangesSopsCheckBox);
//                break;
            default:
                throw new AssertionError("Пропущен выбор экспортируемых параметров");
        }

        click(exportButton);
        sleep(6000);
        open(urlDownload);
//        sleep(30000);
//        refresh();
//        sleep(30000);
        queueManagerPage = new QueueManagerPage("Empty");
        DownloadedFileName = queueManagerPage.downloadedFileName.getText();
        Assert.assertEquals("download", DownloadedFileName);
    }

    @Step("Export domain {0}")
    public void cancelExportDomain(String domainName) {
        goToDomainInGroup("Архив", domainName);
        click(exportDomainButton);
        closeForm();
        sleep(5000);
        Assert.assertFalse(exportButton.exists());
        Assert.assertTrue(exportDomainButton.isDisplayed());

    }

    @Step("Check imported domain {1}")
    public void checkImportedDomain(String type, String domainName, String sopsName, String localQueueName, String xsdName,
                                    String xsdFileName, String jsName, String jsFileName, String groovyName,
                                    String groovyFileName, String xsltName, String xsltFileName, String jsonName,
                                    String jsonFileName,
                                    String routsDirectoryName, String routsName,
                                    String routsFileName, String variableName, String variableValue, String nameAddConfig,
                                    String typeAddConfig, String property1AddConfig, String property2AddConfig,
                                    String property3AddConfig,
                                    String model, String savedMessageLocalQueue) {


        switch (type) {
            case ("все"):
                startDomain(domainName);
                goToDomain(domainName);
                searchSops(sopsName);
                click(cellNameSOPS);
                creationSOPSPage = new CreationSOPSPage();
                checkLocalQueueInSops(0, 8, localQueueName);
                checkXsdValidationInSops(1, 8, true, xsdName, "Файл", "resources/" + xsdFileName);
                checkSoftwareTransformationInSops(2, 8, true, jsName, "JavaScript", "Файл", "resources/" + jsFileName);
                checkSoftwareTransformationInSops(3, 8, true, groovyName, "Groovy", "Файл", "resources/" + groovyFileName);
                checkXsltInSops(4, 8, true, xsltName, "Файл", "resources/" + xsltFileName);
                checkJsonInSops(5, 8, true, jsonName, "Файл", "resources/" + jsonFileName);
                checkRoutInSops(6, 8, routsName, routsFileName.replace(".xml", ""));
                checkHistoryInSops(11 + 2);
                click(creationSOPSPage.returnToSOPSList);
                checkConstantsOfDomain(0, 1, variableName, variableValue);

                click(additionConfigurationTab);
                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));

                click(propertiesOfDomainButton);
                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
                click(propertiesConnectionMqTab);
                useSettingOfBrokerCheckBox.shouldBe(checked);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
                closeForm();

                checkImportedFiles(thingEnableOrDisable.ENABLED, xsdFileName);
                checkImportedFiles(thingEnableOrDisable.ENABLED, jsFileName);
                checkImportedFiles(thingEnableOrDisable.ENABLED, groovyFileName);
                checkImportedFiles(thingEnableOrDisable.ENABLED, xsltFileName);
                checkImportedFiles(thingEnableOrDisable.ENABLED, jsonFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.ENABLED, routsFileName);

                goToDomain(domainName);
                click(modelsTab);
                compareElementTextShouldContainsText(model, modelRowsAfterSearch);

//                startDomain(domainName);
//                queueManagerPage = new QueueManagerPage();
                queueManagerMultyPage.searchQueue("QM", localQueueName);
                contextClick(queueManagerMultyPage.afterSearchQueueName);
                click(queueManagerMultyPage.contextSendMessage);
                compareStringAndElementText(savedMessageLocalQueue, messagePage.messageTextAria);
                break;
            case ("только домен"):
                goToDomain(domainName);
                searchSops(sopsName);
                click(cellNameSOPS);
                creationSOPSPage = new CreationSOPSPage();
                checkLocalQueueInSops(0, 8, localQueueName);


                checkXsdValidationInSops(1, 8, false, xsdName, "Файл", "resources/" + xsdFileName);
                checkSoftwareTransformationInSops(2, 8, false, jsName, "JavaScript", "Файл",
                        "resources/" + jsFileName);
                checkSoftwareTransformationInSops(3, 8, false, groovyName, "Groovy", "Файл",
                        "resources/" + groovyFileName);
                checkXsltInSops(4, 8, false, xsltName, "Файл", "resources/" + xsltFileName);
                checkJsonInSops(5, 8, false, jsonName, "Файл", "resources/" + jsonFileName);


                checkRoutInSops(6, 8, routsName, routsFileName.replace(".xml", ""));
                checkHistoryInSops(1);
                click(creationSOPSPage.returnToSOPSList);
                checkConstantsOfDomain(0, 0, variableName, variableValue);

                click(additionConfigurationTab);
                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));

                click(propertiesOfDomainButton);
                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
                click(propertiesConnectionMqTab);
                useSettingOfBrokerCheckBox.shouldBe(checked);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
                closeForm();

                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, jsonFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);

                goToDomain(domainName);
                click(modelsTab);
                sout(modelRowsAfterSearch.getText());
                compareStringAndElementText("Нет данных", modelRowsAfterSearch);
                break;
            case ("все с редактированием данных в форме импорта"):
                startDomain(domainName);
                goToDomain(domainName);
                searchSops(sopsName);
                cellNameSOPS.shouldNotBe(exist);
                checkConstantsOfDomain(0, 0, variableName, variableValue);

                click(additionConfigurationTab);
                $x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)).shouldNotBe(exist);

                click(propertiesOfDomainButton);
                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
                click(propertiesConnectionMqTab);
                useSettingOfBrokerCheckBox.shouldBe(checked);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
                closeForm();

                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
                checkImportedFiles(thingEnableOrDisable.DISABLED, jsonFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);

                goToDomain(domainName);
                click(modelsTab);
                sout(modelRowsAfterSearch.getText());
                compareStringAndElementText("Нет данных", modelRowsAfterSearch);

                break;
//            case ("домен + JS и Groovy"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 8, localQueueName);
//                checkXsdValidationInSops(1, 8, xsdName, "Файл", "/" + xsdFileName + " (не найден)");
//                checkSoftwareTransformationInSops(2, 8, jsName, "JavaScript", "Файл", "/" + jsFileName);
//                checkSoftwareTransformationInSops(3, 8, groovyName, "Groovy", "Файл", "/" + groovyFileName);
//                checkXsltInSops(4, 8, xsltName, "Файл", "/" + xsltFileName + " (не найден)");
//                checkJsonInSops(5, 8, jsonName, "Файл", "/" + jsonFileName + " (не найден)");
//                checkRoutInSops(6, 8, routsName, routsFileName);
//                checkHistoryInSops(0);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 0, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.ENABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.ENABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsonFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);
//
//                goToDomain(domainName);
//                click(modelsTab);
//                sout(modelRowsAfterSearch.getText());
//                compareStringAndElementText("Нет данных", modelRowsAfterSearch);
//                break;
//            case ("домен + XSLT"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 8, localQueueName);
//                checkXsdValidationInSops(1, 8, xsdName, "Файл", "/" + xsdFileName + " (не найден)");
//                checkSoftwareTransformationInSops(2, 8, jsName, "JavaScript", "Файл", "/" + jsFileName + " (не найден)");
//                checkSoftwareTransformationInSops(3, 8, groovyName, "Groovy", "Файл", "/" + groovyFileName + " (не найден)");
//                checkXsltInSops(4, 8, xsltName, "Файл", "/" + xsltFileName);
//                checkJsonInSops(5, 8, jsonName, "Файл", "/" + jsonFileName + " (не найден)");
//                checkRoutInSops(6, 8, routsName, routsFileName);
//                checkHistoryInSops(0);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 0, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.ENABLED, xsltFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsonFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);
//
//                goToDomain(domainName);
//                click(modelsTab);
//                sout(modelRowsAfterSearch.getText());
//                compareStringAndElementText("Нет данных", modelRowsAfterSearch);
//                break;
//            case ("домен + XSD"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 6, localQueueName);
//                checkXsdValidationInSops(1, 6, xsdName, "Файл", "/" + xsdFileName);
//                checkSoftwareTransformationInSops(2, 6, jsName, "JavaScript", "Файл", "/" + jsFileName + " (не найден)");
//                checkSoftwareTransformationInSops(3, 6, groovyName, "Groovy", "Файл", "/" + groovyFileName + " (не найден)");
//                checkXsltInSops(4, 6, xsltName, "Файл", "/" + xsltFileName + " (не найден)");
//                checkRoutInSops(5, 6, routsName, routsFileName);
//                checkHistoryInSops(0);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 0, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.ENABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);
//                break;
//            case ("домен + таблицы маршрутов"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 6, localQueueName);
//                checkXsdValidationInSops(1, 6, xsdName, "Файл", "/" + xsdFileName + " (не найден)");
//                checkSoftwareTransformationInSops(2, 6, jsName, "JavaScript", "Файл", "/" + jsFileName + " (не найден)");
//                checkSoftwareTransformationInSops(3, 6, groovyName, "Groovy", "Файл", "/" + groovyFileName + " (не найден)");
//                checkXsltInSops(4, 6, xsltName, "Файл", "/" + xsltFileName + " (не найден)");
//                checkRoutInSops(5, 6, routsName, routsFileName);
//                checkHistoryInSops(0);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 0, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.ENABLED, routsFileName);
//                break;
//            case ("домен + переменные домена"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 6, localQueueName);
//                checkXsdValidationInSops(1, 6, xsdName, "Файл", "/" + xsdFileName + " (не найден)");
//                checkSoftwareTransformationInSops(2, 6, jsName, "JavaScript", "Файл", "/" + jsFileName + " (не найден)");
//                checkSoftwareTransformationInSops(3, 6, groovyName, "Groovy", "Файл", "/" + groovyFileName + " (не найден)");
//                checkXsltInSops(4, 6, xsltName, "Файл", "/" + xsltFileName + " (не найден)");
//                checkRoutInSops(5, 6, routsName, routsFileName);
//                checkHistoryInSops(0);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 1, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);
//                break;
//            case ("домен + история изменения СОПС"):
//                click(cellNameSOPS);
//                creationSOPSPage = new CreationSOPSPage();
//                checkLocalQueueInSops(0, 6, localQueueName);
//                checkXsdValidationInSops(1, 6, xsdName, "Файл", "/" + xsdFileName + " (не найден)");
//                checkSoftwareTransformationInSops(2, 6, jsName, "JavaScript", "Файл", "/" + jsFileName + " (не найден)");
//                checkSoftwareTransformationInSops(3, 6, groovyName, "Groovy", "Файл", "/" + groovyFileName + " (не найден)");
//                checkXsltInSops(4, 6, xsltName, "Файл", "/" + xsltFileName + " (не найден)");
//                checkRoutInSops(5, 6, routsName, routsFileName);
//                checkHistoryInSops(11);
//                click(creationSOPSPage.returnToSOPSList);
//                checkVariableInSops(0, 0, variableName, variableValue);
//
//                click(additionConfigurationTab);
//                click($x(String.format(expandConfigurationForAdditionalConfigurationButton, typeAddConfig, nameAddConfig)));
//                compareStringAndElementText(property1AddConfig, rowInexpandConfiguration.get(0));
//                compareStringAndElementText(property2AddConfig, rowInexpandConfiguration.get(1));
//                compareStringAndElementText(property3AddConfig, rowInexpandConfiguration.get(2));
//
//                click(propertiesConfigurationButton);
//                click(expandPropertiesConnectionMQButton);
//                compareStringAndElementValue(domainName, nameDomainInPropertiesInput);
//                compareStringAndElementValue("111", maximumNumberOfConnectionsInPropertiesInput);
//                compareStringAndElementValue("222", connectionsPerSessionInPropertiesInput);
//                compareStringAndElementValue("333", asynchronousRecipientsInPropertiesInput);
//
//                click(expandPropertieDomainsStreamsButton);
//                compareStringAndElementValue("444", threadPoolSizeInPropertiesInput);
//                compareStringAndElementValue("555", threadPoolMaximumSizeInPropertiesInput);
//                compareStringAndElementValue("666", freeStreamLifetimeInPropertiesInput);
//                compareStringAndElementValue("777", maximumNumberTasksInWorkQueueInPropertiesInput);
//                compareStringAndElementText("DiscardOldest", abandonmentStrategyInPropertiesActivate);
//                closeForm();
//
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsdFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, jsFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, groovyFileName);
//                checkImportedFiles(thingEnableOrDisable.DISABLED, xsltFileName);
//                checkImportedFilesInDirectory(routsDirectoryName, thingEnableOrDisable.DISABLED, routsFileName);
//                break;
            default:
                throw new AssertionError("Пропущена проверка импортированного домена");
        }
    }

    public void goToDomain(String domainName) {
        sopsPage();
        if ($x(String.format(specificDomainButtonXpath, domainName)).is(not(visible))) {
            refresh();
        }
        click($x(String.format(specificDomainButtonXpath, domainName)));
    }

    public void goToDomainInGroup(String groupDomains, String domainName) {
        sopsPage();
        if ($x(String.format(groupDomainsTab, groupDomains)).is(not(visible))) {
            refresh();
        }
        click($x(String.format(groupDomainsTab, groupDomains)));
        if ($x(String.format(domainInGroup, groupDomains)).is(not(visible))) {
            refresh();
        }
        click($x(String.format(domainInGroup, domainName)));
    }

    public void checkLocalQueueInSops(int index, int sizeList, String localQueueName) {
        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.listPointsInSops.get(index));
        sout("Проверяется количество точек в СОПС: " + sizeList + " = " + creationSOPSPage.listPointsInSops.size());
        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        sout("Проверяется имя компонента точки в СОПС: " + localQueueName + " = " + creationSOPSPage.nameComponentInSops.getValue());
        Assert.assertEquals(localQueueName, creationSOPSPage.nameComponentInSops.getValue());
        if (localQueueName.equals("Локальная очередь для иморта домена без параметров")) {
            sout("Проверяется имя очереди в точке в СОПС: " + localQueueName + "Локальная очередь" + " = " + creationSOPSPage.nameQueueInOffSops.getText());
            Assert.assertEquals(localQueueName, creationSOPSPage.nameQueueInOffSops.getText());
        } else {
            sout("Проверяется имя очереди в точке в СОПС: " + localQueueName + "Локальная очередь" + " = " + creationSOPSPage.nameQueueInSops.getText());
            Assert.assertEquals(localQueueName + "Локальная очередь", creationSOPSPage.nameQueueInSops.getText());
        }
    }

    public void checkXsdValidationInSops(int index, int sizeList, boolean existence, String xsdName, String source, String xsdFileName) {
        click(creationSOPSPage.listPointsInSops.get(index));
        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        Assert.assertEquals(xsdName, creationSOPSPage.nameComponentInSops.getValue());
        Assert.assertEquals(source, creationSOPSPage.sourceToComponentSelectInEditSops.getAttribute("title"));
        Assert.assertEquals(xsdFileName, creationSOPSPage.pathToComponentSelectInEditSops.getText());
        if (existence) {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldNotBe(exist);
        } else {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldBe(exist);
        }
    }

    public void checkSoftwareTransformationInSops(int index, int sizeList, boolean existence, String Name, String syntax, String source, String FileName) {
        click(creationSOPSPage.listPointsInSops.get(index));
        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        Assert.assertEquals(Name, creationSOPSPage.nameComponentInSops.getValue());
        Assert.assertEquals(syntax, creationSOPSPage.syntaxSoftwareTransformationSelectInEditSops.getAttribute("title"));
        Assert.assertEquals(source, creationSOPSPage.sourceToComponentSelectInEditSops.getAttribute("title"));
        Assert.assertEquals(FileName, creationSOPSPage.pathToComponentSelectInEditSops.getText());
        if (existence) {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldNotBe(exist);
        } else {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldBe(exist);
        }
    }

    public void checkXsltInSops(int index, int sizeList, boolean existence, String xsltName, String source, String xsltFileName) {
        click(creationSOPSPage.listPointsInSops.get(index));
        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        Assert.assertEquals(xsltName, creationSOPSPage.nameComponentInSops.getValue());
        Assert.assertEquals(source, creationSOPSPage.sourceToComponentSelectInEditSops.getAttribute("title"));
        Assert.assertEquals(xsltFileName, creationSOPSPage.pathToComponentSelectInEditSops.getText());
        if (existence) {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldNotBe(exist);
        } else {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldBe(exist);
        }
    }

    public void checkJsonInSops(int index, int sizeList, boolean existence, String jsonName, String source, String jsonFileName) {
        click(creationSOPSPage.listPointsInSops.get(index));
        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        Assert.assertEquals(jsonName, creationSOPSPage.nameComponentInSops.getValue());
        Assert.assertEquals(source, creationSOPSPage.sourceToComponentSelectInEditSops.getAttribute("title"));
        Assert.assertEquals(jsonFileName, creationSOPSPage.pathToComponentSelectInEditSops.getText());
        if (existence) {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldNotBe(exist);
        } else {
            creationSOPSPage.fileNotFoundedInSopsMessage.shouldBe(exist);
        }
    }

    public void checkRoutInSops(int index, int sizeList, String routName, String routFileName) {
        click(creationSOPSPage.listPointsInSops.get(index));
//        Assert.assertEquals(sizeList, creationSOPSPage.listPointsInSops.size());
        compareIntAndSize(sizeList, creationSOPSPage.listPointsInSops.size());
//        Assert.assertEquals(routName, creationSOPSPage.nameComponentInSops.getValue());
        compareStringAndElementValue(routName, creationSOPSPage.nameComponentInSops);
//        Assert.assertEquals(routFileName, creationSOPSPage.tableOfRoutsInEditSops.getText());
        compareStringAndElementText(routFileName, creationSOPSPage.tableOfRoutsInEditSops);
    }

    public void checkHistoryInSops(int sizeList) {
        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.sopsHistoryIcon);
        Assert.assertEquals(sizeList, creationSOPSPage.sopsRowInHistory.size());
    }

    public void checkConstantsOfDomain(int index, int sizeList, String variableName, String variableValue) {
        click(constantesDomainTab);
        Assert.assertEquals(sizeList, nameVariableOfDomainOrBrokerInTable.size());
        if (sizeList != 0) {
            Assert.assertEquals(variableName, nameVariableOfDomainOrBrokerInTable.get(index).getText());
            Assert.assertEquals(variableValue, valueVariableOfDomainOrBrokerInTable.get(index).getText());
        }
    }

    public void createConstantOfBroker(String variableName, String variableValue) {
        click(constantsOfBrokerTab);
        click(addConstantButton);
        val(nameVariableOfBroker, variableName);
        val(valueVariableOfBroker, variableName);
        click(saveButton);
    }

    public void editConstantOfBroker(String variableNameOld, String variableNameNew, String variableValueNew) {
        click(constantsOfBrokerTab);
        search(variableNameOld);
        contextClick(rowAfterSearch);
        click(editInContextMenu);
        val(nameVariableOfBroker, variableNameNew);
        val(valueVariableOfBroker, variableValueNew);
        click(saveButton);
    }

    public void deleteConstantOfBroker(String variableName) {
        click(constantsOfBrokerTab);
        search(variableName);
        contextClick(rowAfterSearch);
        click(deleteInContextMenu);
        click(confirmationDeleteButton);
    }

    public void checkConstantsOfBroker(int index, int sizeList, String variableName, String variableValue) {
        click(constantsOfBrokerTab);
//        search(variableName);
        Assert.assertEquals(sizeList, nameVariableOfDomainOrBrokerInTable.size());
        if (sizeList != 0) {
            Assert.assertEquals(variableName, nameVariableOfDomainOrBrokerInTable.get(index).getText());
            Assert.assertEquals(variableValue, valueVariableOfDomainOrBrokerInTable.get(index).getText());
        }
    }

    public void checkAdditionalCofigurationInSops(thingEnableOrDisable availability, int index, int sizeList, String nameConfiguration, String typeConfiguration) {
        click(additionConfigurationTab);
        Assert.assertEquals(sizeList, nameConfigurationInTable.size());

        if (availability.equals(thingEnableOrDisable.ENABLED)) {
            if (sizeList != 0) {
                nameConfigurationInTable.get(index).should(exactText(nameConfiguration));
                typeConfigurationInTable.get(index).should(exactText(typeConfiguration));
            }
        } else {
            if (sizeList != 0) {
                for (SelenideElement element : nameConfigurationInTable) {
                    if (nameConfiguration.equals(element.getText())) {
                        throw new AssertionError("Импортировалась невыбранное название конфигрурации");
                    }
                }
                for (SelenideElement element : typeConfigurationInTable) {
                    if (typeConfiguration.equals(element.getText())) {
                        throw new AssertionError("Импортировался невыбранный тип конфигрурации");
                    }
                }
            }
        }
    }

    public void checkImportedFiles(thingEnableOrDisable availability, String fileName) {
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);
        for (SelenideElement element : filesAndDirectoryListInFileManager) {
            if (fileName.equals(element.getText())) {
                sout("Файл " + fileName + " присутствует в менеджере файлов");
                if (availability.equals(thingEnableOrDisable.ENABLED)) {
                    return;
                } else {
                    throw new AssertionError("Найден файл, который не должен был импортироваться");
                }

            }
        }
    }

    public void checkImportedFileInDirectory(thingEnableOrDisable availability, String directoryName, String fileName) {
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);
        click($x(String.format(directoryListInFileManagerString, directoryName)));

        if (availability.equals(thingEnableOrDisable.ENABLED)) {
            elementShouldBeVisible($x(String.format(fileListInFileManagerString, fileName)));
        } else {
            elementShouldNotBeVisible($x(String.format(fileListInFileManagerString, fileName)));
        }
    }

    public void checkImportedDirectory(thingEnableOrDisable availability, String directoryName) {
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);

        if (availability.equals(thingEnableOrDisable.ENABLED)) {
            elementShouldBeVisible($x(String.format(directoryListInFileManagerString, directoryName)));
        } else {
            elementShouldNotBeVisible($x(String.format(directoryListInFileManagerString, directoryName)));
        }
    }

    public void editNameImportedFiles(String fileNameOld, String fileNameNew) {
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);
        search(fileNameOld);
        contextClick(rowAfterSearch);
        click($x(".//div[text()=' Переименовать']"));
        valAndSelectElement($x(String.format(somethingInput, "Имя файла/директории")), fileNameNew);
        click(saveButton);

    }

    public void checkImportedFilesInDirectory(String nameDirectory, thingEnableOrDisable availability, String fileName) {
        refresh();
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);
//        searchInput.clear();
        searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchInput.sendKeys(Keys.chord(Keys.DELETE));
        click($x(String.format(directoryInFilesManager, nameDirectory)));
        for (SelenideElement element : filesAndDirectoryListInFileManager) {
            if (fileName.equals(element.getText())) {
                sout("Файл " + fileName + " присутствует в менеджере файлов");
                if (availability.equals(thingEnableOrDisable.ENABLED)) {
                    return;
                } else {
                    throw new AssertionError("Найден файл, который не должен был импортироваться");
                }
            }
        }
    }

    public void editNameImportedFilesInDirectory(String nameDirectory, String fileNameOld, String fileNameNew) {
        if (!settingsPageTab.getAttribute("class").equals("submenu active submenu-open")) {
            click(settingsPageTab);
        }
        click(fileManagerTab);
//        searchInput.clear();
        searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchInput.sendKeys(Keys.chord(Keys.DELETE));
        click($x(String.format(directoryInFilesManager, nameDirectory)));
        search(fileNameOld);
        contextClick(rowAfterSearch);
        click($x(".//div[text()=' Переименовать']"));
        valAndSelectElement($x(String.format(somethingInput, "Название файла/директории")), fileNameNew);
        click(saveButton);
    }

    public void createConstantsOfDomain(String domainName, String[][] allConstants) {
        goToDomain(domainName);
        click(constantesDomainTab);
        for (String[] allConstant : allConstants) {
            click(addConstantButton);
            val(nameVariableInput.get(0), allConstant[0]);
            val(valueVariableInput.get(0), allConstant[1]);
            click(saveButton);
        }
        if (domainStartedMode.exists()) {
            basePage.click(restartDomainButton);
        }
    }

    public void editConstantsOfDomain(String domainName, String constantOldName, String[][] allConstants) {
        goToDomain(domainName);
        click(constantesDomainTab);
        for (String[] allConstant : allConstants) {
            search(constantOldName);
            doubleClick(constantesStatusInTable.get(0));
            val(nameVariableInput.get(0), allConstant[0]);
            val(valueVariableInput.get(0), allConstant[1]);
            click(saveButton);
        }

        basePage.click(restartDomainButton);
    }

    public void checkSpeedAndWriteToFile(String nameSops, String versionFesb, long optimalTime,
                                         float optimalSpeed, String nameFile) {
        Date date1 = null;
        Date date2 = null;
        Locale rus = new Locale("ru", "RU");
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String FirstProcessing = firstProcessingSOPS.getText();
        String LastProcessing = lastProcessingSOPS.getText();

        try {
            date1 = formatter.parse(FirstProcessing);
            date2 = formatter.parse(LastProcessing);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sout("Дата 1: " + date1);
        sout("Дата 2: " + date2);

        long difference2 = (date2.getTime() - date1.getTime()) / 1000;
//        long optimalTime = 30;
        int temperature = 0;
        try {
            temperature = basePage.getCPUTemperature();
        } catch (NullPointerException e) {
            new AssertionError("Не удалось получить температуру процессора");
        }

        System.out.println("Температура процессора = " + temperature + "'C");
        sout(nameSops + ":\nРазница в секундах = " + difference2 + " при норме ~ " + optimalTime + " сек");

//        float optimalSpeed = 7500;
        float AverageSpeed2 = Float.parseFloat(averageSpeedSOPS.getText());
        sout("Средняя скорость = " + AverageSpeed2 + " при норме ~" + optimalSpeed + " соб/сек\n");


        File file = new File("/home/fesb/Stand/" + nameFile);
        Date nowDate = new Date();
        try {

            PrintWriter print = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));

            print.write(versionFesb + " - " + nowDate.toString() + " - " + temperature + "'C - " + nameSops + "\nРазница в секундах = " + difference2 + " при норме ~ " + optimalTime + " соб/сек\n" + "Средняя скорость = " + AverageSpeed2 + " при норме ~" + optimalSpeed + " соб/сек\n\n");
            print.close();
        } catch (IOException e) {
            throw new AssertionError("Неудалось записать статистику в файл");
        }

        Assert.assertTrue("Время обработки сообщений СОПСом превышено", difference2 <= optimalTime);
        Assert.assertTrue("Скорость обработки ниже средней", AverageSpeed2 >= optimalSpeed);
    }

    public void createModelData(String domainName, String modelName, Boolean statusGlobal, String
            formatModelData, String classlName, String modelDataFile) {
        goToDomain(domainName);
        click(modelsTab);
        click(addModelButton);
        val(modelNameInput, modelName);

        if (statusGlobal && !globalModelCheckBox.isSelected()) {
            click(globalModelCheckBox);
        }
        if (!statusGlobal && globalModelCheckBox.isSelected()) {
            click(globalModelCheckBox);
        }

        click(formatModelDataActivate);
        switch (formatModelData) {
            case "Схема в формате JSON":
            case "Пример JSON файла":
                valAndSelectElement(formatModelDataInput, formatModelData);
                val(classNameInput, classlName);
                break;
            case "Пример XML файла":
                valAndSelectElement(formatModelDataInput, formatModelData);
                break;
            default:
                throw new AssertionError("Пропущен выбор формата модели данныж");
        }
        uploadModelDataInput.sendKeys(modelDataFile);
        elementShouldBeVisible(deleteIcon);
        click(saveButton);
    }

    public void editModelData(String domainName, String modelNameOld, String modelNameNew, String packageName, String source, Boolean statusGlobal) {
        goToDomain(domainName);
        click(modelsTab);
        searchModel(modelNameOld);
        contextClick(modelNameInRowsAfterSearch);
        click(editButton);
        if (modelNameNew != null) val(modelNameInput, modelNameNew);
        if (packageName != null) val(packageNameInput, packageName);
        if (source != null) val(sourceInput, source);
        if (statusGlobal != null) {
            if (statusGlobal && !globalModelCheckBox.isSelected()) {
                click(globalModelCheckBox);
            }
            if (!statusGlobal && globalModelCheckBox.isSelected()) {
                click(globalModelCheckBox);
            }
        }
        click(saveButton);
    }

    public void checkModelData(String domainName, String modelNameNew, String packageName, String source, Boolean statusGlobal) {
        goToDomain(domainName);
        click(modelsTab);
        searchModel(modelNameNew);
        contextClick(modelNameInRowsAfterSearch);
        click(editButton);
        if (modelNameNew != null) compareStringAndElementValue(modelNameNew, modelNameInput);
        if (packageName != null) compareStringAndElementValue(packageName, packageNameInput);
        if (source != null) compareStringAndElementValue(source, sourceInput);
        if (statusGlobal != null) {
            if (statusGlobal) globalModelCheckBox.shouldBe(selected);
            if (!statusGlobal) globalModelCheckBox.shouldNotBe(selected);
        }
        click(saveButton);
    }

    public void startDebugMode(String domainName) {
        goToDomain(domainName);
        click(actionsUnderDomainsButton);
        click(runDebugButton);
    }

    public void stopDebugMode(String domainName) {
        goToDomain(domainName);
        click(actionsUnderDomainsButton);
        click(stopDebugButton);
    }

    @Step
    public void checkDebugMode(String cook, String domainName, String sopsName, String managerName, String localOut, String url) {
        startDebugMode(domainName);
        debugModeLabel.shouldBe(Condition.visible);
        goToSops(domainName, sopsName);
        selectedBreackpointsIcon.get(0).shouldNotBe(visible);
        click(breackpointsIcon.get(0));
        selectedBreackpointsIcon.get(0).shouldBe(visible);
        playBreackpointsIcon.get(0).shouldNotBe(visible);
        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.sendSavedMessageIcon);
        selectedBreackpointsIcon.get(0).shouldBe(visible);
        playBreackpointsIcon.get(0).shouldBe(visible);

        queueManagerApi.queueShouldNotExist(cook, managerName, localOut, url);


//        try {
//            queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localOut, -0, -0, -0, -0, -0, "alkdjlks", "", url);
//        } catch (NullPointerException e) {
//            sout("Очередь не найдена и это хорошо");
//        }
        click(playBreackpointsIcon.get(0));
        selectedBreackpointsIcon.get(0).shouldBe(visible);
        playBreackpointsIcon.get(0).shouldNotBe(visible);

        queueManagerMultyApi.queueCheckAllParametersAPI(cook, url, managerName, localOut, 1, 0, 0, 1, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localOut, 1, 0, 0, 1, 0, "local", "null", url);

        click(creationSOPSPage.backToDomainIcon);
        stopDebugMode(domainName);
        debugModeLabel.shouldNotBe(Condition.visible);
        goToSops(domainName, sopsName);
        selectedBreackpointsIcon.get(0).shouldNotBe(visible);
        playBreackpointsIcon.get(0).shouldNotBe(visible);
        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.sendSavedMessageIcon);
        selectedBreackpointsIcon.get(0).shouldNotBe(visible);
        playBreackpointsIcon.get(0).shouldNotBe(visible);
        queueManagerMultyApi.queueCheckAllParametersAPI(cook, url, managerName, localOut, 2, 0, 0, 2, 0, null, null, null, null);

//        queueManagerApi.queueCheckAllParametersAPI(cook, managerName, localOut, 2, 0, 0, 2, 0, "local", "null", url);
    }

    public void editProperties(String domainNameOld, String domainNameNew, String strategyOfStart, String threadPoolSize, String maximumThreadPoolSize, String freeFlowLifetime, String maximumNumberTasksInWorkQueue, String abandonmentStrategy, String errorHandler, String policyResend, String loggingLevel, String maximumAttempts, String initialDelayBetweenRetries, String maximumDelayBetweenRetries, String asynchronousDelayBetweenDeliveries, String multiplierDelayRedelivery, String useCollisionAvoidance, String collisionAvoidanceRatio, String maximumNumberOfConnections, String connectionsPerSession, String asynchronousRecipients, String typeOfconnect, String managerName) {
        goToDomain(domainNameOld);
        click(propertiesOfDomainButton);
//Общие свойства
        if (domainNameNew != null) val(nameDomainInPropertiesInput, domainNameNew);
        if (strategyOfStart != null) {
            click(domainStrategyOfStartActivate);
            valAndSelectElement(domainStrategyOfStartInput, strategyOfStart);
        }
        if (threadPoolSize != null) val(threadPoolSizeInPropertiesInput, threadPoolSize);
        if (maximumThreadPoolSize != null) val(threadPoolMaximumSizeInPropertiesInput, maximumThreadPoolSize);
        if (freeFlowLifetime != null) val(freeStreamLifetimeInPropertiesInput, freeFlowLifetime);
        if (maximumNumberTasksInWorkQueue != null)
            val(maximumNumberTasksInWorkQueueInPropertiesInput, maximumNumberTasksInWorkQueue);

        if (abandonmentStrategy != null) {
            click(abandonmentStrategyInPropertiesActivate);
            valAndSelectElement(abandonmentStrategyInPropertiesInput, abandonmentStrategy);
        }
//Свойства обработчика ошибок
        if (errorHandler != null) {
            click(propertiesErrorHandlerTab);
        }
        if (errorHandler != null) {
            click(errorHandlerActivate);
            valAndSelectElement(errorHandlerInput, errorHandler);
        }
//        if (policyResend != null || sopsErrorHandling != null) {
//            click(useOriginalMessageCheckBox);
//        }
        if (policyResend != null) {
            click(policyResendActivate);
            valAndSelectElement(policyResendInput, policyResend);
        }
        if (loggingLevel != null) {
            click(loggingLevelActivate);
            valAndSelectElement(loggingLevelInput, loggingLevel);
        }
        if (maximumAttempts != null) val(maximumAttemptsInput, maximumAttempts);
        if (initialDelayBetweenRetries != null) val(initialDelayBetweenRetriesInput, initialDelayBetweenRetries);
        if (maximumDelayBetweenRetries != null) val(maximumDelayBetweenRetriesInput, maximumDelayBetweenRetries);
        if (multiplierDelayRedelivery != null) val(multiplierDelayRedeliveryInput, multiplierDelayRedelivery);
        if (loggingLevel != null || maximumAttempts != null || initialDelayBetweenRetries != null || maximumDelayBetweenRetries != null || multiplierDelayRedelivery != null || collisionAvoidanceRatio != null) {
            if (asynchronousDelayBetweenDeliveries != null) click(asynchronousDelayBetweenDeliveriesCheckBox);
            click(enableExponentialDelayCheckBox);
            if (useCollisionAvoidance != null) click(useCollisionAvoidanceCheckBox);
            click(allowForwardingWhileStoppedCheckBox);
        }
        if (collisionAvoidanceRatio != null) val(collisionAvoidanceRatioInput, collisionAvoidanceRatio);
//Свойства клиента менеджера очередей
        if (maximumNumberOfConnections != null || connectionsPerSession != null || asynchronousRecipients != null) {
            click(propertiesCustomerMqTab);
        }
        if (maximumNumberOfConnections != null)
            val(maximumNumberOfConnectionsInPropertiesInput, maximumNumberOfConnections);
        if (connectionsPerSession != null)
            val(connectionsPerSessionInPropertiesInput, connectionsPerSession);
        if (asynchronousRecipients != null)
            val(asynchronousRecipientsInPropertiesInput, asynchronousRecipients);

//Свойства подключения к менеджеру очередей
        if (managerName != null || typeOfconnect != null) {
            click(propertiesConnectionMqTab);
        }
        if (managerName != null) {
            click(useSettingOfBrokerCheckBoxActivate);
            click(typeOfconnectActivate);
            valAndSelectElement(typeOfconnectInput, typeOfconnect);
            click(mqActivate);
            valAndSelectElement(mqInput, managerName);
        }
//Настройка потоков домена
//        if (threadPoolSize != null || maximumThreadPoolSize != null || freeFlowLifetime != null || maximumNumberTasksInWorkQueue != null || abandonmentStrategy != null) {
//            click(expandPropertieDomainsStreamsTab);
//
//        }
//        if (threadPoolSize != null) val(threadPoolSizeInPropertiesInput, threadPoolSize);
//        if (maximumThreadPoolSize != null) val(threadPoolMaximumSizeInPropertiesInput, maximumThreadPoolSize);
//        if (freeFlowLifetime != null) val(freeStreamLifetimeInPropertiesInput, freeFlowLifetime);
//        if (maximumNumberTasksInWorkQueue != null)
//            val(maximumNumberTasksInWorkQueueInPropertiesInput, maximumNumberTasksInWorkQueue);
//
//        if (abandonmentStrategy != null) {
//            click(abandonmentStrategyInPropertiesActivate);
//            valAndSelectElement(abandonmentStrategyInPropertiesInput, abandonmentStrategy);
//        }
        click(saveButton);
    }

    public void checkProperties(String domainNameNew, String domainType, String strategyOfStart, String threadPoolSize, String maximumThreadPoolSize, String freeFlowLifetime, String maximumNumberTasksInWorkQueue, String abandonmentStrategy, String errorHandler, String policyResend, String loggingLevel, String maximumAttempts, String initialDelayBetweenRetries, String maximumDelayBetweenRetries, String asynchronousDelayBetweenDeliveries, String multiplierDelayRedelivery, String useCollisionAvoidance, String collisionAvoidanceRatio, String maximumNumberOfConnections, String connectionsPerSession, String asynchronousRecipients, String typeOfconnect, String managerName) {
        goToDomain(domainNameNew);
        click(propertiesOfDomainButton);

//Общие свойства
        if (domainNameNew != null) compareStringAndElementValue(domainNameNew, nameDomainInPropertiesInput);
        if (strategyOfStart != null) compareStringAndElementSelectedText(strategyOfStart, domainStrategyOfStartInput);
        if (threadPoolSize != null) compareStringAndElementValue(threadPoolSize, threadPoolSizeInPropertiesInput);
        if (maximumThreadPoolSize != null)
            compareStringAndElementValue(maximumThreadPoolSize, threadPoolMaximumSizeInPropertiesInput);
        if (freeFlowLifetime != null)
            compareStringAndElementValue(freeFlowLifetime, freeStreamLifetimeInPropertiesInput);
        if (maximumNumberTasksInWorkQueue != null)
            compareStringAndElementValue(maximumNumberTasksInWorkQueue, maximumNumberTasksInWorkQueueInPropertiesInput);
        if (abandonmentStrategy != null)
            compareStringAndElementSelectedText(abandonmentStrategy, abandonmentStrategyInPropertiesInput);
//Свойства обработчика ошибок
        if (errorHandler != null || policyResend != null || loggingLevel != null) {
            click(propertiesErrorHandlerTab);
        }
        if (errorHandler != null) {
            click(errorHandlerActivate);
            compareStringAndElementSelectedText(errorHandler, errorHandlerInput);
        }
        if (policyResend != null) compareStringAndElementSelectedText(policyResend, policyResendInput);
//        if (sopsErrorHandling != null) compareStringAndElementSelectedText(sopsErrorHandling, sopsErrorHandlingInput);
        if (loggingLevel != null) compareStringAndElementSelectedText(loggingLevel, loggingLevelInput);
        if (maximumAttempts != null) compareStringAndElementValue(maximumAttempts, maximumAttemptsInput);
        if (initialDelayBetweenRetries != null)
            compareStringAndElementValue(initialDelayBetweenRetries, initialDelayBetweenRetriesInput);
        if (maximumDelayBetweenRetries != null)
            compareStringAndElementValue(maximumDelayBetweenRetries, maximumDelayBetweenRetriesInput);
        if (multiplierDelayRedelivery != null)
            compareStringAndElementValue(multiplierDelayRedelivery, multiplierDelayRedeliveryInput);
        if (loggingLevel != null || maximumAttempts != null || initialDelayBetweenRetries != null || maximumDelayBetweenRetries != null || multiplierDelayRedelivery != null || collisionAvoidanceRatio != null) {
//            click(expandPropertiePoliceResandMessageButton);
            asynchronousDelayBetweenDeliveriesCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            enableExponentialDelayCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            useCollisionAvoidanceCheckBox.shouldHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
            allowForwardingWhileStoppedCheckBox.shouldNotHave(attribute("class", "ant-checkbox ant-checkbox-checked"));
        }
        if (collisionAvoidanceRatio != null)
            compareStringAndElementValue(collisionAvoidanceRatio, collisionAvoidanceRatioInput);
//Свойства клиента менеджера очередей
        if (maximumNumberOfConnections != null || connectionsPerSession != null || asynchronousRecipients != null) {
            click(propertiesCustomerMqTab);
        }
        if (maximumNumberOfConnections != null)
            compareStringAndElementValue(maximumNumberOfConnections, maximumNumberOfConnectionsInPropertiesInput);
        if (connectionsPerSession != null)
            compareStringAndElementValue(connectionsPerSession, connectionsPerSessionInPropertiesInput);
        if (asynchronousRecipients != null)
            compareStringAndElementValue(asynchronousRecipients, asynchronousRecipientsInPropertiesInput);
//Свойства подключения к менеджеру очередей
        if (domainType != null || managerName != null || typeOfconnect != null) {
            click(propertiesConnectionMqTab);
        }
        if (managerName != null) compareStringAndElementSelectedText(managerName, mqInput);
        if (typeOfconnect != null) compareStringAndElementSelectedText(typeOfconnect, typeOfconnectInput);
//Настройка потоков домена
//        if (threadPoolSize != null || maximumThreadPoolSize != null || freeFlowLifetime != null || maximumNumberTasksInWorkQueue != null || abandonmentStrategy != null) {
//            click(expandPropertieDomainsStreamsTab);
//        }
//        if (threadPoolSize != null) compareStringAndElementValue(threadPoolSize, threadPoolSizeInPropertiesInput);
//        if (maximumThreadPoolSize != null)
//            compareStringAndElementValue(maximumThreadPoolSize, threadPoolMaximumSizeInPropertiesInput);
//        if (freeFlowLifetime != null)
//            compareStringAndElementValue(freeFlowLifetime, freeStreamLifetimeInPropertiesInput);
//        if (maximumNumberTasksInWorkQueue != null)
//            compareStringAndElementValue(maximumNumberTasksInWorkQueue, maximumNumberTasksInWorkQueueInPropertiesInput);
//        if (abandonmentStrategy != null)
//            compareStringAndElementSelectedText(abandonmentStrategy, abandonmentStrategyInPropertiesInput);
        closeForm();
    }

    public void enumerationOfPoints() {
        String[] pointsIn = {"Локальная очередь", "Локальная очередь РМО", "JMS", "IBM MQ", "RabbitMQ", "Kafka", "Локальный файл", "SMB", "Потоковая передача", "FTP", "SFTP", "FTPS", "POP3", "IMAP", "FTP сервер", "HTTP", "WebSocket", "Телеграм", "Локальная БД (H2)", "SQL", "SSH", "Таймер", "Планировщик", "Ссылка на СОПС", "Последовательный порт", "Mina", "Пользовательская точка входа"};
        String[] pointsOut = {"Обогащение", "Установить заголовки", "Очистить заголовки", "Установить переменные", "Установить тело сообщения", "XSLT Трансформация", "XSD Валидация", "JSON Валидация", "Преобразование форматов", "Преобразование модели", "Преобразовать тело сообщения", "Пользовательская обработка", "Программная трансформация", "Обработчик", "Бин", "Push", "Кэш", "Логирование", "Откат транзакции", "Записать глобальную переменную", "Прочитать глобальную переменную", "Добавить вложение", "Криптография", "Прервать обработку", "Тестовый пользовательский компонент", "Фильтр","Проверка на уникальность", "Цикл", "Попытка", "Сегментация", "Агрегация", "Список получателей", "Потоки", "Балансировка", "Локальная очередь", "Локальная очередь РМО", "JMS", "IBM MQ", "RabbitMQ", "Kafka", "Локальный файл", "SMB", "Потоковая передача", "FTP", "SFTP", "FTPS", "SMTP", "HTTP", "REST Swagger", "SOAP", "WebSocket", "Mina", "LDAP", "Телеграм", "Локальная БД (H2)", "SQL", "SSH", "Исполнить команду локально", "Принтер", "Ссылка на СОПС", "Выход в таблицу маршрутов", "Распределенное хранилище", "Последовательный порт", "Пользовательская точка выхода"};

        ArrayList<String> pointInActual = new ArrayList<>();
        ArrayList<String> pointOutActual = new ArrayList<>();

        creationSOPSPage = new CreationSOPSPage();
        click(creationSOPSPage.addInputPointButton);
        for (SelenideElement point : creationSOPSPage.listOfPoints) {
            pointInActual.add(point.getText());
        }
        basePage.compareArrayAndArray(pointsIn, pointInActual.toArray(new String[pointInActual.size()]));

        for (int i = 0; i < pointsIn.length; i++) {
            String expectedHeaderOfPoint = "Редактирование компонента «" + creationSOPSPage.listOfPoints.get(i).getText() + "»";
            click(creationSOPSPage.listOfPoints.get(i));
            compareStringAndElementText(expectedHeaderOfPoint, creationSOPSPage.editComponentHeader);
            click(creationSOPSPage.addInputPointButton);
        }

        click(creationSOPSPage.addTreatmentButton);
        for (SelenideElement point : creationSOPSPage.listOfPoints) {
            pointOutActual.add(point.getText());
        }
        basePage.compareArrayAndArray(pointsOut, pointOutActual.toArray(new String[pointOutActual.size()]));

        for (int i = 0; i < pointsOut.length; i++) {
            String expectedHeaderOfPoint = "Редактирование компонента «" + creationSOPSPage.listOfPoints.get(i).getText() + "»";
            click(creationSOPSPage.listOfPoints.get(i));
            compareStringAndElementText(expectedHeaderOfPoint, creationSOPSPage.editComponentHeader);
            click(creationSOPSPage.addTreatmentButton);
        }
    }

    public void checkTracing(String plus, String sopsName, String processing, String numMessages, String size) {
        if (!tracingTab.attr("aria-selected").equals("true")) click(tracingTab);
        tracingSearchInput.clear();
        val(tracingSearchInput, processing);
        sleep(500);
        compareStringAndElementText(plus, $x(String.format(cellInRowTracingTable, "1")));
        compareStringAndElementText(sopsName, $x(String.format(cellInRowTracingTable, "2")));
        compareStringAndElementText(processing, $x(String.format(cellInRowTracingTable, "3")));
        compareStringAndElementText(numMessages, $x(String.format(cellInRowTracingTable, "4")));
        compareStringAndElementText(size, $x(String.format(cellInRowTracingTable, "5")));
        $x(String.format(cellInRowTracingTable, "6")).shouldNotBe(empty);
        $x(String.format(cellInRowTracingTable, "7")).shouldNotBe(empty);
        $x(String.format(cellInRowTracingTable, "8")).shouldNotBe(empty);
    }

    public void prepareForCheckTransactions(String domainName, String sopsName) {
        String[][] pointAll_1 = null;
        String localQueueIn1 = null;
        String localQueueOut1 = null;
        String[] point1_1 = null;
        String[] point1_2 = null;
        String[] point1_3 = null;
        String Cook = api.loginAPI(Base.baseUrl(), ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String domainID_1 = sopsApi.createDomainAPI(Base.baseUrl(), Cook, domainName);
        sopsApi.startDomainAPI(Cook, Base.baseUrl(), domainID_1);

        switch (sopsName) {
            case "SPRING TIMER REQUIRED TM":
                localQueueOut1 = "TRANSACTION.LOCAL.SPRING.REQUIRED.OUT";
                point1_1 = new String[]{"Вход|Таймер|" + "Таймер для проверки TimerAndLocalQueue", "инпут-в-параметрах|Количество повторений|1"};
                point1_2 = new String[]{"Выход|Локальная очередь|" + localQueueOut1};
                point1_3 = new String[]{"Выход|Программная трансформация|Groovy|Ручной ввод|throw new RuntimeException(\"ERROR NEW\");"};
                pointAll_1 = new String[][]{point1_1, point1_2, point1_3};

                editProperties(domainName, null, null, null, null, null, null, null, "По умолчанию", null, null, null, null, null, null, null, null, null, null, null, null, null, null);

                creationSOPSPage = new CreationSOPSPage("empty");
                creationSOPSPage.createSOPS(domainName, sopsName, pointAll_1);
                break;
            case "SPRING ENDPOINT TM":
                localQueueIn1 = "TRANSACTION.LOCAL.SPRING.ENDPOINT.IN";
                localQueueOut1 = "TRANSACTION.LOCAL.SPRING.ENDPOINT.OUT";
                point1_1 = new String[]{"Вход|Локальная очередь|" + localQueueIn1};
                point1_2 = new String[]{"Выход|Локальная очередь|" + localQueueOut1};
                point1_3 = new String[]{"Выход|Программная трансформация|Groovy|Ручной ввод|throw new RuntimeException(\"ERROR NEW\");"};
                pointAll_1 = new String[][]{point1_1, point1_2, point1_3};

                editProperties(domainName, null, null, null, null, null, null, null, "По умолчанию", null, null, null, null, null, null, null, null, null, null, null, null, null, null);

                String[] propertySops1 = {sopsName, "Описание", "1", String.valueOf(CreationSOPSPage.propertySOPS.errorHandlingInsideSopsOff), "По умолчанию (Транзакционная точка входа)", String.valueOf(CreationSOPSPage.propertySOPS.autostartOn), String.valueOf(CreationSOPSPage.propertySOPS.streamCachingOff), String.valueOf(CreationSOPSPage.propertySOPS.tracingOff), String.valueOf(CreationSOPSPage.propertySOPS.transactionalityOn) + "|" + "LOCALMQ_PROPAGATION_REQUIRED"};

                creationSOPSPage = new CreationSOPSPage("empty");
                creationSOPSPage.createSOPS(domainName, propertySops1, pointAll_1);
                break;
            case "LOCAL TM":
                break;
            default:
                throw new AssertionError("Нет настроек для этого СОПС");
        }

    }
}
