package ru.factorts.page;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.remote.SessionId;
import utils.ConfigProperties;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class BasePage {

    //    Api api = new Api();
    BrowserUpProxy bmp;

    String SendMessageFormUrl = "/" + "manager/#/send";
    String directoryListInFileManagerString = ".//*[@data-icon=\"folder\"]/ancestor::button//span[text()='%s']/ancestor::button";
    String fileListInFileManagerString = ".//*[@data-icon=\"file\"]/ancestor::button//span[not(@aria-label=\"folder\")][text()='%s']/ancestor::button";
    String settingManagerMultyString = ".//a[text()='Менеджеры очередей']/../..//li[text()='%s']/..//a[text()='Настройки менеджера']";

    public SelenideElement bodyPage = $x(".//body");

    //TABS
    public SelenideElement generalInformationTab = $x(".//a[text()='Общая информация']");
    public SelenideElement resourceMonitorTab = $x(".//a[text()='Монитор ресурсов']");
    public SelenideElement sendMessageTab = $x(".//a[text()='Отправить сообщение']");
    public SelenideElement queueManagersTab = $x(".//a[text()='Менеджеры очередей']/..");
    public SelenideElement createManagerButton = $x(".//a[text()='Менеджеры очередей']/../..//span[text()='Создать менеджер']");
    public SelenideElement queueManagerTab = $x(".//a[text()='Менеджер очередей']/..");
    public SelenideElement queueManagerMultyTab = $x(".//a[text()='Менеджеры очередей']/..");
    public SelenideElement queueManagerArtemisTab = $x(".//a[text()='Расширенный МО']/..");
    public SelenideElement queuelistTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Очереди']");
    public SelenideElement queuelistArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Очереди']");
    public SelenideElement adresseslistArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Адреса']");
    public SelenideElement virtualAdresseslistArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Виртуальные адреса']");
    public SelenideElement receiversArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Приёмники']");
    public SelenideElement connectorsArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Коннекторы']");
    public SelenideElement channelsArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Каналы']");
    public SelenideElement securityArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Безопасность']");
    public SelenideElement settingOfConfigurationArtemisTab = $x(".//a[text()='Расширенный МО']/../..//a[text()='Настройки']");
    public SelenideElement usersArtemisTab = $x(".//div[text()='Пользователи']");
    public SelenideElement topicsListTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Разделы']");
    public SelenideElement describersTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Подписчики']");
    public SelenideElement receiversTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Приёмники']");
    public SelenideElement receiversMultyTab = $x(".//a[text()='Менеджеры очередей']/../..//a[text()='Приёмники']");
    public SelenideElement activeConnectorsTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Активные приёмники']");
    public SelenideElement canalsTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Каналы']");
    public SelenideElement canalsMultyTab = $x(".//a[text()='Менеджеры очередей']/../..//a[text()='Каналы']");
    public SelenideElement eventInterceptionTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Перехват событий']");
    public SelenideElement eventInterceptionMultyTab = $x(".//a[text()='Менеджеры очередей']/../..//a[text()='Перехват событий']");
    public SelenideElement deliveryPolicyTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Политика доставки']");
    public SelenideElement deliveryPolicyMultyTab = $x(".//a[text()='Менеджеры очередей']/../..//a[text()='Политика доставки']");
    public SelenideElement settingManagerTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Настройки менеджера']");
    public SelenideElement mainSettingManagerMultyTab = $x(".//div[text()='Основные настройки']");
    public SelenideElement commandInterfaceTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Командный интерфейс']");
    public SelenideElement commandInterfaceMultyTab = $x(".//div[text()='Командный интерфейс']");
    public SelenideElement remoteAccessTab = $x(".//a[text()='Менеджер очередей']/../..//a[text()='Удаленный доступ']");
    public SelenideElement connectJmsInput = $x(".//label[text()='Подключение JMS']/../following-sibling::div//input");
    public SelenideElement remoteAccessSwitchOnCheckBox = $x(".//span[text()='Включить удаленный доступ']/preceding-sibling::span");
    public SelenideElement userJmsIput = $x(".//label[text()='Пользователь JMS']/../following-sibling::div//input");
    public SelenideElement passwordJmsInput = $x(".//label[text()='Пароль JMS']/../following-sibling::div//input");
    public SelenideElement connectJmxInput = $x(".//label[text()='Подключение JMX']/../following-sibling::div//input");
    public SelenideElement userJmxInput = $x(".//label[text()='Пользователь JMX']/../following-sibling::div//input");
    public SelenideElement passwordJmxInput = $x(".//label[text()='Пароль JMX']/../following-sibling::div//input");

    public SelenideElement currentUser = $x(".//a[@id=\"current-user\"]");
    public SelenideElement topicsPage = $x(".//i[@class='fa fa-power-off']");
    public SelenideElement configurationPage = $x(".//li[@class='dropdown']//a[@href='#']");
    public SelenideElement domainsOfBrokerTab = $x(".//a[text()='Домены брокера']/..");
    public SelenideElement customersComponentTab = $x(".//a[text()='Домены брокера']/../..//a[text()='Пользовательские компоненты']");
    public SelenideElement constantsOfBrokerTab = $x(".//a[text()='Домены брокера']/../..//a[text()='Константы брокера']");
    public SelenideElement createDomainTab = $x(".//a[text()='Домены брокера']/../..//span[text()='Создать домен']");

    public SelenideElement importDomainTab = $x(".//a[text()='Домены брокера']/../..//span[text()='Импортировать домен']");
    public SelenideElement dataBaseTab = $x(".//a[text()='База данных']");
    public SelenideElement systemBdTab = $x(".//a[text()='База данных']/../following-sibling::ul//a[text()='Системная база данных']");
    public SelenideElement createDbTab = $x(".//a[text()='База данных']/../following-sibling::ul//span[text()='Создать БД']");
    public SelenideElement settingDataBaseTab = $x(".//a[text()='База данных']/following-sibling::ul//a[text()='Настройки']");
    public SelenideElement restTab = $x(".//a[text()='REST']/..");

    public SelenideElement webServicesTab = $x(".//a[text()='Веб-сервисы']");
    public SelenideElement transactionMonitorTab = $x(".//a[text()='Монитор транзакций']");
    public SelenideElement dashboardTab = $x(".//a[@href=\"#/dashboard\"]");
    public SelenideElement examplesOfdashboardTab = $x(".//a[@href=\"#/dashboard\"]/../following-sibling::ul//a[text()='Примеры']");
    public SelenideElement newMonitorOfdashboardTab = $x(".//a[@href=\"#/dashboard\"]/../following-sibling::ul//span[text()='Создать экран']");
    public SelenideElement logsPageTab = $x(".//a[text()='Протокол работы']/..");
    public SelenideElement wrenchPage = $x(".//i[@class='fa fa-wrench']");
    public SelenideElement settingsPageTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/..");
    public SelenideElement systemManagmentTab199 = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../ul//a[text()='Управление системой']");
    public SelenideElement systemManagmentTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Управление системой']");
    public SelenideElement configurationServerTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Конфигурация сервера']");
    public SelenideElement libraryManagmentTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Управление библиотеками']");
    public SelenideElement libraryManagmentTab199 = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../../ul//a[text()='Управление библиотеками']");
    public SelenideElement loggingTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Журналирование']");
    public SelenideElement usersAndGroupTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Пользователи и группы']");
    public SelenideElement clusterTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Кластер']");
    public SelenideElement cluster199Tab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../../ul//a[text()='Кластер']");

    public SelenideElement fileManagerTab = $x(".//a[@href=\"#/settings\"][text()='Настройки']/../../ul//a[text()='Менеджер файлов']");
    public SelenideElement recoverPointsTab = $x(".//a[text()='Точки восстановления']");
    public ElementsCollection filesAndDirectoryListInFileManager = $$x(".//table[@class=\"table table-striped\"]//span");
    public ElementsCollection directoryListInFileManager = $$x(".//*[@data-icon=\"folder\"]/ancestor::button//span[not(@aria-label=\"folder\")]/ancestor::button");
    public ElementsCollection fileListInFileManager = $$x(".//*[@data-icon=\"file\"]/ancestor::button//span[not(@aria-label=\"folder\")]/ancestor::button");
    public SelenideElement documentationTab = $x(".//a[text()='Документация']/..");
    public SelenideElement documentationHeader1 = $x(".//h1[text()='Общие положения']");
    public SelenideElement widgetsBlock = $x(".//div[@class=\"widgets\"]");
    public SelenideElement widgetMonitors = $x(".//div[@class=\"widgets\"]//span[text()='Экраны']");
    public SelenideElement widgetsMenu = $x(".//span[text()='Виджеты']");
    public SelenideElement widgetsHistoryMenu = $x(".//span[@class=\"ant-badge\"]");
    public SelenideElement closeWindowIcon = $$x(".//span[@aria-label=\"close\"][@class=\"anticon anticon-close ant-modal-close-icon\"]").find(visible);
    public SelenideElement closeNotificationIcon = $$x(".//span[@aria-label=\"close\"][@class=\"anticon anticon-close ant-notification-close-icon\"]").find(visible);
    public SelenideElement deleteIcon = $$x(".//*[@data-icon=\"delete\"]").find(visible);
    public SelenideElement plusIcon = $$x(".//i[@class=\"fa fa-fw fa-plus\"]").find(visible);
    public SelenideElement addAttributeButton = $$x(".//span[text()=\"Добавить атрибут\"]/..").find(visible);
    public SelenideElement spinner = $x(".//div[@class='spinner']");
    public SelenideElement checkBoxAutoUpdate = $x(".//span[text()='Обновление каждые']/preceding-sibling::span");
    public SelenideElement rowAfterSearch = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]").find(visible);
    public SelenideElement reactSelect = $x(".//input[contains(@aria-activedescendant,\"react-select\")]");
    public SelenideElement headerCopyMoveMessage = $x(".//div[contains(text(),\"выбранные сообщения в очередь\")]");
    public SelenideElement headerAddQueue = $x(".//div[contains(text(),\"Добавление очереди\")]");
    public SelenideElement headerAddParameter = $x(".//div[contains(text(),\"Добавление параметра\")]");
    public SelenideElement headerMarshrutsTable = $x(".//div[contains(text(),\"Таблица маршрутов\")]");
    public SelenideElement noDataInTable = $x(".//div[text()='Нет данных']");
    public SelenideElement saveButton = $$x(".//span[text()=\"Сохранить\"]/..").find(visible);
    public SelenideElement editButton = $$x(".//div[text()=\" Редактировать\"]/..").find(visible);
    public SelenideElement oldSaveButton = $x(".//button[text()='Сохранить']");
    public SelenideElement addButton = $$x(".//span[text()=\" Добавить\"]/..").find(visible);
    public SelenideElement addButton2 = $$x(".//span[text()=\"Добавить\"]/..").find(visible);
    public SelenideElement deleteButton = $$x(".//span[text()=\"Удалить\"]/..").find(visible);
    public SelenideElement actionButton = $$x(".//span[text()='Действия ']/..").find(visible);
    public SelenideElement createButton = $$x(".//span[text()=\"Создать\"]/..").find(visible);
    public SelenideElement addMethodButton = $$x(".//span[text()=\"Добавить метод\"]/..").find(visible);
    public SelenideElement executeButton = $$x(".//span[text()=\"Выполнить\"]/..").find(visible);
    public SelenideElement continueButton = $$x(".//span[text()=\"Продолжить\"]/..").find(visible);
    public SelenideElement importButton = $$x(".//span[text()=\"Импортировать\"]/..").find(visible);
    public SelenideElement restartDomainButtonInPopUp = $$x(".//span[text()=\"Перезапустить домен\"]/..").find(visible);
    public SelenideElement searchInput = $$x(".//label[text()='Поиск ']/input").find(visible);
    public SelenideElement refreshSearchButton = $x(".//span[@class=\"anticon anticon-reload\"]/..");
    public SelenideElement createUserSaveButton = $$x(".//div[text()='Новый пользователь']/../..//span[text()=\"Сохранить\"]/..").find(visible);
    public SelenideElement restartConfigurationButton = $x("//span[text()='Перезапустить FESB']/..");
    public ElementsCollection restartModuleButton = $$x("//span[text()='Перезапустить модуль']/..");
    public ElementsCollection restartDomainButton = $$x(".//span[text()='Перезапустить домен']/..");
    public ElementsCollection restartFesbButton = $$x("//span[text()='Перезапустить FESB']/..");
    SelenideElement fullRestartFesbThroughManageOfSystemButton = $x(".//span[text()='Полный перезапуск']/..");
    SelenideElement confirmFullRestartFesbThroughManageOfSystemButton = $x(".//div[text()='Полный перезапуск FESB']/../following-sibling::div//p[text()='Вы уверены, что хотите выполнить полный перезапуск системы?']/following-sibling::div//span[text()='Перезапустить']/..");
    public SelenideElement restartRestButton = $x("//span[text()='Перезапустить \"REST\"']/..");
    public SelenideElement confirmationDeleteButton = $$x(".//div[text()='Подтверждение удаления']/../..//div//span[text()='Удалить']/..").find(visible);
    public SelenideElement modalWindow = $$x(".//div[@class=\"ant-modal-header\"]/div[@class=\"ant-modal-title\"]").find(visible);
    public SelenideElement editInContextMenu = $x(".//div[text()=' Редактировать']");
    public SelenideElement stopInContextMenu = $x(".//div[text()=' Остановить']");
    public SelenideElement startInContextMenu = $x(".//div[text()=' Запустить']");
    public SelenideElement cloneInContextMenu = $x(".//div[text()=' Клонировать']");
    public SelenideElement viewInContextMenu = $x(".//div[text()=' Просмотреть']");
    public SelenideElement downloadInContextMenu = $x(".//div[text()=' Скачать']");
    public SelenideElement deleteInContextMenu = $x(".//div[text()=' Удалить']");
    public SelenideElement searchNameButton = $$x(".//span[text()='Найти']/..").find(visible);
    public SelenideElement downloadedFileName = $x(".//a[@class=\"icon file\"]");

    String somethingActivate1 = ".//label[text()='%s']/../..//input/..";
    String somethingActivate2 = ".//label[text()='%s']/../..//input/../..";
    String somethingActivate3 = ".//label[text()='%s']/../..//input/../../..";
    String somethingActivate4 = ".//label[text()='%s']/..//input/../..";
    String somethingActivate5 = ".//label[text()='%s']/..//input/../../..";
    String somethingInput = ".//label[text()='%s']/../..//input";
    String somethingInput2 = ".//label[text()='%s']/..//input";

    //    ElementsCollection spinners = $$x(".//div[@class='spinner']");
    public ElementsCollection filesInFileManager = $$x(".//a[@class=\"dashed\"]/span");
    public ElementsCollection rowAfterSearchCollection = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]");
    public ElementsCollection expandString = $$x(".//button[@aria-label='Развернуть строку']");
    public ElementsCollection rollUpString = $$x(".//button[@aria-label='Свернуть строку']");
    public ElementsCollection deleteIconList = $$x(".//*[@data-icon=\"delete\"]");

    public SelenideElement logoutButton = $x(".//li[text()='Выйти']"),
            userProfileMenu = $x(".//span[@class=\"fa fa-fw fa-user\"]/following-sibling::span[@class=\"widget-title\"]"),
            changePasswordLink = $x(".//li/a[text()='Смена пароля']"),
            errorLogLink = $x(".//span[text()='журнал']/.."),
            settingsLink = $x("//div[text()='Учетная запись']/../../../..//li/a[text()='Настройки']");


    static String CurrentURL = "";
    String checkBox = ".//span[text()='%s']/preceding-sibling::span";
    String groupDomainsTab = ".//a[text()='%s']";

    public enum thingEnableOrDisable {ENABLED, DISABLED}

    public enum FileExist {Yes, No}

    @Step("Logout")
    public void logout() {
        if (!url().contains("login") && !$x(".//div[@class=\"widgets right\"]/div[2]").toString().contains("open")) {
            sout($x(".//div[@class=\"widgets right\"]/div[2]").toString());
            click(userProfileMenu);
            click(logoutButton);
        }
        if (!url().contains("login") && $x(".//div[@class=\"widgets right\"]/div[2]").toString().contains("open")) {
            sout($x(".//div[@class=\"widgets right\"]/div[2]").toString());
            click(logoutButton);
        }

    }

    @Step("Go to Index page")
    public void indexPage() {
        click(generalInformationTab);
    }


    @Step("Go to Queue Manager page")
    public void queueManagerPage() {
        queueManagerTab.waitUntil(enabled, 5000);
        if (!queueManagerTab.attr("class").equals("active")) {
            click(queueManagerTab);
        }
    }

    @Step("Go to Topics list")
    public void topicsList() {
        queueManagerTab.shouldBe(enabled);
        if (!queueManagerTab.attr("class").equals("active")) {
            click(queueManagerTab);
        }
        click(topicsListTab);
    }

    @Step("Go to Queue list")
    public void queueListPage() {
        queueManagerTab.shouldBe(enabled);
        if (!queueManagerTab.attr("class").equals("active")) {
            click(queueManagerTab);
        }
        click(queuelistTab);
    }


    @Step("Go to Message page")
    public void goToMessagePage() {
        sleep(5000);
        click(sendMessageTab);
    }

    @Step("Go to SOPS page")
    public void sopsPage() {
        domainsOfBrokerTab.shouldBe(enabled);
        if (!domainsOfBrokerTab.attr("class").equals("active")) {
            click(domainsOfBrokerTab);
        }
    }

    @Step("Go to Configuration page")
    public void configurationPage() {
        click(configurationPage);
    }

    @Step("Go to Wrench page")
    public void wrenchPage() {
        click(wrenchPage);
    }

    @Step("Go to Logs page")
    public void logsPage() {
        click(logsPageTab);
    }

    @Step("Go to settings page")
    public void settingsPage() {
        settingsPageTab.waitUntil(enabled, 3000);
        if (!settingsPageTab.attr("class").contains("active")) {
            click(settingsPageTab);
        }
    }

    @Step("Click {0}")
    public void click(SelenideElement element) {
        element.waitUntil(visible, 20000);
        try {
            Selenide.executeJavaScript("arguments[0].style[\"box-shadow\"] = '" + "inset 0 0 5px red" + "'", element);
            sleep(100);
            Selenide.executeJavaScript("arguments[0].style[\"box-shadow\"] = '" + "inset 0 0 0px red" + "'", element);
        } catch (StaleElementReferenceException e) {
            sout("Не удалось изменить цвет элемента");
        }
        String Locator = element.toString();
        element.click();
        sout("click " + Locator);
        sleep(500);

        if (!url().equals(CurrentURL)) {
            CurrentURL = url();
            sout("Текущий URL: " + CurrentURL);
            sleep(2000);
        }
    }

    @Step("Context Click {0}")
    public void contextClick(SelenideElement element) {
        element.shouldBe(visible);
        String Locator = element.toString();
        if (!url().contains("users") && !url().contains("browse") && !url().contains("broker") && !url().contains("log") && !url().contains("restorepoints") && !url().contains("libs")) {
            autoUpdateOff();
        }
        element.contextClick();
        sout("context click " + Locator);
        sleep(1000);
    }

    @Step("Double Click {0}")
    public void doubleClick(SelenideElement element) {
        element.waitUntil(visible, 10000);
        sleep(1000);
        String Locator = element.toString();
        element.doubleClick();
        sleep(500);
        sout("doubleclick " + Locator);
        if (!url().equals(CurrentURL)) {
            CurrentURL = url();
            sout("Текущий URL: " + CurrentURL);
            sleep(4000);
        }
    }

    @Step("Write {1} in {0}")
    public void val(SelenideElement element, String text) {
        element.waitUntil(visible, 10000);
        String Locator = element.toString();
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        sleep(500);
        element.sendKeys(text);

        if (!element.attr("value").equals(text)) {
            element.shouldHave(matchText(text));
        }
        sout("Написали: " + text + " в: " + Locator);
    }

    public void valInTextarea(String message) {
        Selenide.executeJavaScript("document.querySelector(\"textarea[name]\").style=\"display: block; width: 600px; height: 600px;\"");
        $x(".//textarea[@name]").sendKeys(message);
    }

    public void valInTextarea2(String message) {
        Selenide.executeJavaScript("document.querySelector(\"textarea\").style=\"display: block; width: 600px; height: 600px;\"");
        $x(".//textarea").sendKeys(message);
    }

    public void valInTextareaWithCodemirror(String message) {
        SelenideElement element = $x(".//textarea[@mode][last()]");
        Selenide.executeJavaScript("document.querySelector(\"textarea[mode][style='display: none;']\").style=\"overflow: visible; display: block; width: 600px; height: 600px;\"");
//        Selenide.executeJavaScript("arguments[0].value='" + message.replace("\"", "\\\"") + "';", element);
        Selenide.executeJavaScript("arguments[0].value=\"" + message.replace("\"", "\\\"") + "\";", element);
//        Selenide.executeJavaScript("$(\".CodeMirror\").get(0).CodeMirror.setValue(\"" + message.replace("\"", "\\\"") + "\");");
        sleep(1000);
        SelenideElement codeMirror = $x("(.//div[contains(@class,'factor-form-code-mirror')]/div[contains(@class,'CodeMirror')])[last()]");
        sout("Готовимся написать в textarea: " + message.replace("\"", "\\\""));
        Selenide.executeJavaScript("arguments[0].CodeMirror.setValue(\"" + message.replace("\"", "\\\"") + "\");", codeMirror);
    }

    @Step("Write {1} in {0} and press Enter")
    public void valAndSelectElement(SelenideElement element, String text) {
        element.shouldBe(visible);
        String Locator = element.toString();
        element.clear();
        deleteNumsimbolsInInput(element, element.getValue().length());
        element.val(text);
        sleep(1000);

        if ($x(String.format(MessagePage.nameLocalQueue_2, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №-1");
            click($$x(String.format(MessagePage.nameLocalQueue_2, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue_1, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №-1");
            click($$x(String.format(MessagePage.nameLocalQueue_1, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue0, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №0");
            click($$x(String.format(MessagePage.nameLocalQueue0, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue1, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №1");
            click($$x(String.format(MessagePage.nameLocalQueue1, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue2, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №2");
            click($$x(String.format(MessagePage.nameLocalQueue2, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue3, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №3");
            click($$x(String.format(MessagePage.nameLocalQueue3, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue4, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №4");
            click($$x(String.format(MessagePage.nameLocalQueue4, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue5, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №5");
            click($$x(String.format(MessagePage.nameLocalQueue5, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue6, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №6");
            click($$x(String.format(MessagePage.nameLocalQueue6, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue7, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №7");
            click($$x(String.format(MessagePage.nameLocalQueue7, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue8, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №8");
            click($$x(String.format(MessagePage.nameLocalQueue8, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue9, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №9");
            click($$x(String.format(MessagePage.nameLocalQueue9, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue10, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №10");
            click($$x(String.format(MessagePage.nameLocalQueue10, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue11, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №11");
            click($$x(String.format(MessagePage.nameLocalQueue11, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue12, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №12");
            click($$x(String.format(MessagePage.nameLocalQueue12, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue13, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №13");
            click($$x(String.format(MessagePage.nameLocalQueue13, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue14, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №14");
            click($$x(String.format(MessagePage.nameLocalQueue14, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue15, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №15");
            click($$x(String.format(MessagePage.nameLocalQueue15, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue16, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №16");
            click($$x(String.format(MessagePage.nameLocalQueue16, text)).find(visible));
        } else if ($x(String.format(MessagePage.nameLocalQueue17, text)).is(visible)) {
            sout("Используем для выбора элемента в селекте локатор №17");
            click($$x(String.format(MessagePage.nameLocalQueue17, text)).find(visible));
        }
        sout("Написали: " + text + " в: " + Locator + "и выбрали элемент из списка");

    }

    public void autoUpdateOn() {
        if (!checkBoxAutoUpdate.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(checkBoxAutoUpdate);
        }
    }

    public void autoUpdateOff() {
        if (checkBoxAutoUpdate.isDisplayed() && checkBoxAutoUpdate.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(checkBoxAutoUpdate);
        }
    }

    @Step
    public void search(String name) {
        searchInput.clear();
        val(searchInput, name);
        try {
            rowAfterSearch.shouldBe(visible);
        } catch (ElementNotFound e) {
            System.out.println(name + " не нашелся, повторяю поиск");
            refresh();
            val(rowAfterSearch, name);
        }
    }

    @Step
    public void searchWithoutRepeat(String name) {
        searchInput.clear();
        val(searchInput, name);
    }

    public void openPage(String page) {
        open(page);
        sout("Открыли главную страницу перед очисткой кук");
        LoginPage loginPage2 = new LoginPage();
        sleep(2000);
        Assert.assertTrue(loginPage2.loginButton.isDisplayed() || userProfileMenu.isDisplayed());

        String Cookies = null;
        Cookies = StaticAPI.loginAPI(page, ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        String[] partsCookies = Cookies.split("=");
        Cookie ck = new Cookie(partsCookies[0], partsCookies[1], "/manager");
        Cookie ck2 = new Cookie("hashPart", "", "/manager");
//        sout("hash:" + ck2.toString());
//        sout("sess:" + ck.toString());
//        sleep(1000);
        sout("Куки до очистки: " + WebDriverRunner.getWebDriver().manage().getCookies());
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
//        sout("Удалили старые куки");
//        sout("Куки после очистки: " + WebDriverRunner.getWebDriver().manage().getCookies());

//        sleep(1000);
        WebDriverRunner.getWebDriver().manage().addCookie(ck2);
//        sout("Добавили новые куки 1: " + ck2.toString());
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
//        sout("Добавили новые куки 2: " + ck.toString());
//        sleep(3000);

        sout("Установленные куки: " + WebDriverRunner.getWebDriver().manage().getCookies());

        open(page);
        sout("Открыли страницу - " + page);
        userProfileMenu.shouldBe(visible);
//        if (!userProfileMenu.isDisplayed()) {
//            refresh();
//            sout("Не найден профиль юзера, обновляем страницу");
//        }
    }

    public void openPage(String adress, String uri, String login, String password) {
        String Cookies = null;
        Cookie ck = null;
        Cookie ck2 = null;
        Cookies = StaticAPI.loginAPI(adress, login, password);
        String[] partsCookies = Cookies.split("=");
        ck = new Cookie(partsCookies[0], partsCookies[1], "/manager");
        ck2 = new Cookie("hashPart", "", "/manager");
        sout("hash:" + ck2.toString());
        sout("sess: " + ck.toString());
        open(adress + uri);
        sout("Открыли главную страницу");
        sleep(1000);
        System.out.println("Куки до очистки: " + WebDriverRunner.getWebDriver().manage().getCookies());
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        sout("Удалили старые куки");

        System.out.println("Куки после очистки: " + WebDriverRunner.getWebDriver().manage().getCookies());

        sleep(1000);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
        sout("Добавили новые куки 1: " + ck.toString());
        WebDriverRunner.getWebDriver().manage().addCookie(ck2);
        sout("Добавили новые куки 2 " + ck2.toString());
        sleep(3000);

        System.out.println("Установленные куки: " + WebDriverRunner.getWebDriver().manage().getCookies());
        sleep(1000);
        open(adress + uri);
        System.out.println("Открыли: " + adress + uri);
        if (!userProfileMenu.isDisplayed()) {
            refresh();
            sout("Не найден профиль юзера, обновляем страницу");
        }
    }

    public void openWithLog(String url) {
        open(url);
        if (ConfigProperties.getTestProperty("Debug").equals("true")) {
            bmp = WebDriverRunner.getSelenideProxy().getProxy();
            // запоминать тело запросов (по умолчанию тело не запоминается, ибо может быть большим)
            bmp.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
            // запоминать как запросы, так и ответы
            bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
            // начинай запись!
            bmp.newHar("pofig");
        }
    }

    public void elementShouldBeVisible(SelenideElement element) {
        try {
            element.shouldBe(visible);
            String Locator = element.toString();
            sout("Виден элемент: " + Locator);
        } catch (ElementNotFound e) {
            sout("Элемент не найден, обновляю страницу и повторяю поиск");
            refresh();
            element.shouldBe(visible);
            String Locator = element.toString();
            sout("Виден элемент: " + Locator);
        }
    }

    public void elementShouldNotBeVisible(SelenideElement element) {
        element.shouldNotBe(visible);
        String Locator = element.toString();
        sout("НЕвиден элемент: " + Locator);
    }

    public void waitAvailibleUrl(String urlSite, int maxTime) throws IOException {
        String connectionRefused = "Enable";
        int response = 0;
        int waitedTime = 0;
        URL url = new URL(urlSite);
        sout(" УРЛ - " + url);
        do {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            try {
                response = http.getResponseCode();
                connectionRefused = "Disable";
            } catch (IOException e) {
                connectionRefused = "Enable";
            }
            sout(url + " - " + response);
            sleep(5000);
            waitedTime = waitedTime + 5000;
            if (waitedTime >= maxTime) {
                throw new AssertionError("Страница недоступна");
            }
//            open("/");
        }
        while (response != 200 && connectionRefused.equals("Enable"));
    }

    public void chekDisableUrl(String urlSite, int maxTime) throws IOException {
        String connectionRefused = "Enable";
        int response = 200;
        int waitedTime = 0;
        URL url = new URL(urlSite);
        sout(" УРЛ - " + url);
        do {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            try {
                response = http.getResponseCode();
                connectionRefused = "Disable";
            } catch (IOException e) {
                connectionRefused = "Enable";
            }
            sout(url + " - " + response);
            sleep(5000);
            waitedTime = waitedTime + 5000;
            if (waitedTime >= maxTime) {
                sout("Страница недоступна и это хорошо");
                break;
            }
        }
        while (response == 200 && connectionRefused.equals("Disable"));
    }


    public void changeWindow() {
        Set<String> winSet = WebDriverRunner.getWebDriver().getWindowHandles();
        List<String> winList = new ArrayList<String>(winSet);
        String currentWindow = WebDriverRunner.getWebDriver().getWindowHandle();

        for (String win : winList) {
            if (!win.equals(currentWindow)) {
                Selenide.switchTo().window(win);
            }
        }
    }

    public String currentTime() {
        LocalTime currentDayAndTime = LocalTime.now();
//        sout("Неизмененное время:" + currentDayAndTime.toString());
        return currentDayAndTime.toString();
    }

    public LocalTime stringToTime(String stamp) {
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS").withLocale(Locale.US);
        LocalTime parsedTime = LocalTime.parse(stamp.split(" GMT")[0], newFormatter);
        sout("Неизмененное время:" + newFormatter.format(parsedTime));
        return parsedTime;
    }

    public LocalDateTime changePatternDate(String stamp) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss.SSS").withLocale(Locale.US);
        LocalDateTime parsedDate = LocalDateTime.parse(stamp.split(" GMT")[0], formatter1);
        sout("Ещё неотформатированное время:" + parsedDate);
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss.SSS").withLocale(Locale.US);
        String parsedDate2 = parsedDate.format(newFormatter);
        sout("Отформатированное время:" + parsedDate2);
        LocalDateTime changedDate = LocalDateTime.parse(parsedDate2, newFormatter);
        return changedDate;
    }

    public String addTimeForDateAndTime(LocalDateTime time, String addSec) {
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy HH:mm:ss").withLocale(Locale.US);
        time = time.plusSeconds(Integer.parseInt(addSec));
        String formattedString = time.format(newFormatter);
        sout("Измененное время:" + formattedString);
        return formattedString;
    }

    public void switchCheckBox(thingEnableOrDisable state, String nameParameter) {
        if (state.equals(thingEnableOrDisable.ENABLED) && !$x(String.format(checkBox, nameParameter)).parent().attr("class").equals("ant-checkbox-wrapper ant-checkbox-wrapper-checked")) {
            click($x(String.format(checkBox, nameParameter)));
        }
        if (state.equals(thingEnableOrDisable.DISABLED) && $x(String.format(checkBox, nameParameter)).parent().attr("class").equals("ant-checkbox-wrapper ant-checkbox-wrapper-checked")) {
            click($x(String.format(checkBox, nameParameter)));
        }
    }

    public void deleteNumsimbolsInInput(SelenideElement element, int num) {
        for (int i = 0; i < num; i++) {
            element.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
        }
    }

    public void sout(String text) {
        System.out.println(currentTime() + " - " + text);
    }

    public void compareStringAndString(String textExpect, String textActual) {
        sout("Text: " + textExpect + " = " + textActual);
        Assert.assertEquals(textExpect, textActual);
    }

    public void compareStringAndStringShouldContainsText(String textExpect, String textActual) {
        try {
            sout("Text: " + textExpect + " = " + textActual);
            Assert.assertTrue(textExpect.contains(textActual));
        } catch (Exception e) {
            sleep(3000);
            sout("Text: " + textExpect + " = " + textActual);
            Assert.assertTrue(textExpect.contains(textActual));
        }
    }

    public void compareStringAndElementText(String text, SelenideElement element) {
        sout("Text: " + text + " = " + element.getText());
        Assert.assertEquals(text, element.getText());
    }

    public void compareStringAndElementTextMustBeNotEqual(String text, SelenideElement element) {
        sout("Text: " + text + " = " + element.getText());
        Assert.assertNotEquals(text, element.getText());
    }

    public void compareElementTextShouldContainsText(String text, SelenideElement element) {
        sout("Text: " + text + " = " + element.getText());
        Assert.assertTrue(element.getText().contains(text));
    }

    public void compareElementTextShouldNotContainsText(String text, SelenideElement element) {
        sout("Text: " + text + " = " + element.getText());
        Assert.assertFalse(element.getText().contains(text));
    }

    public void compareStringAndElementValue(String text, SelenideElement element) {
        sout("Value: " + text + " = " + element.getValue());
//        element.shouldHave(value(text));
        Assert.assertEquals(text, element.getValue());
    }

    public void compareStringAndElementSelectedValue(String text, SelenideElement element) {
        sout("SelectedValue: " + text + " = " + element.getSelectedValue());
        Assert.assertEquals(text, element.getSelectedValue());
    }

    public void compareStringAndElementSelectedText(String text, SelenideElement element) {
        sout("SelectedValue: " + text + " = " + element.$x("./../following-sibling::span").getText());
        Assert.assertEquals(text, element.$x("./../following-sibling::span").getText());
    }

    public void compareIntAndSize(int expectedSum, int actualSum) {
        sout(expectedSum + " = " + actualSum);
        Assert.assertEquals(expectedSum, actualSum);
    }

    public void compareIntAndInt(int expectedSum, int actualSum) {
        sout(expectedSum + " = " + actualSum);
        Assert.assertEquals(expectedSum, actualSum);
    }

    public void compareArrayAndArray(String[] expectedList, String[] actualList) {
//        sout(Arrays.toString(expectedList));
//        sout(Arrays.toString(actualList));

        for (int i = 0; i < expectedList.length; i++) {
            compareStringAndString(expectedList[i], actualList[i]);
        }
        compareIntAndInt(expectedList.length, actualList.length);
    }

    public void compareListAndList(ArrayList<String> expectedList, ArrayList<String> actualList) {
        sout(expectedList.toString());
        sout(actualList.toString());

        for (int i = 0; i < expectedList.size(); i++) {
            compareStringAndString(expectedList.get(i), actualList.get(i));
        }
        compareIntAndInt(expectedList.size(), actualList.size());
    }

    public void isElementNotEmpty(SelenideElement element) {
        sout("ElementNotEmpty: " + element.getText());
        element.shouldNotBe(empty);
    }

    public void sleepAndRefresh(int milliseconds) {
        int perSec = 0;
        int wait = 5000;
        while (perSec < milliseconds) {
            sleep(wait);
            sout("Подождали " + wait / 1000 + " сек");
            refresh();
            perSec = perSec + wait;
        }
    }

    public void waitVisibleAndRefresh(SelenideElement element, int milliseconds) {
        int perSec = 0;
        int wait = 5000;
        while (!element.exists() && perSec < milliseconds) {
            sleep(wait);
            sout("Подождали " + wait / 1000 + " сек");
            refresh();
            perSec = perSec + wait;
        }
    }

    public void chekExistFile(FileExist fileExist, String filePath) {
        File file = new File(filePath);

        if (fileExist.equals(FileExist.Yes)) {
            if (file.exists()) {
                Assert.assertTrue(file.length() > 100);
                sout("Файл существует и это хорошо: " + filePath);
                sout("Размер файла: " + file.length());
            } else throw new AssertionError("Файл должен существовать, но отсутствует: " + filePath);
        }

        if (fileExist.equals(FileExist.No)) {
            if (file.exists()) {
                throw new AssertionError("Файл должен отсутствовать, но существует: " + filePath);
            } else sout("Файл отсутствует и это хорошо: " + filePath);
        }


//    public void makeElementVisible(SelenideElement element) {
//        String asd = Selenide.executeJavaScript("document.evaluate(\"" + element + "\", document.body, null, XPathResult.ANY_TYPE, null)");
//        sout(asd);
//        Selenide.executeJavaScript("document.evaluate(\"" + element + "\", document.body, null, XPathResult.ANY_TYPE, null).style.opacity = 100");
//        Selenide.executeJavaScript("$x('//label[text()=\"Имя очереди\"]/../..//input')[0].style.opacity = 100");
//        Selenide.executeJavaScript("document.querySelector(\"" + element + "\").style.opacity = 100");
//        Selenide.executeJavaScript("document.querySelector(\"div#brace-editor.ace_editor.ace-chrome textarea.ace_text-input\").style.opacity = 100");
//        System.out.println("Аттр: " + element.getAttribute("style"));
//    }
    }

    public void executeBashCommand(String command) {
        if (command.contains(".sh")) {
            try {
//        Загрузить скрипт из файла
                Process pb = new ProcessBuilder("/bin/bash", command).start();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AssertionError("Скрипт невыполнен");
            }
        } else {
            sout(command);
            try {
//        Загрузить скрипт из строки
                Process pb = new ProcessBuilder("/bin/bash", "-c", command).start();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AssertionError("Скрипт невыполнен");
            }
        }
    }

    public String executeBashCommandToSsh(String host, int port, String user, String password, String command) {
        sout(host + " " + port + " " + user + " " + password);
        sout(command);
        String response = null;
        try {

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig(config);
            session.connect();
            System.out.println("Connected");

            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    response = new String(tmp, 0, i);
//                    System.out.print(new String(tmp, 0, i));
//                    System.out.print("aaaa"+new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
            channel.disconnect();
            session.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void writeMessageToFile(String message, String nameFile) {
        File file = new File(nameFile);
        try {

            PrintWriter print = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
            print.write(message);
            print.close();
        } catch (IOException e) {
            throw new AssertionError("Неудалось записать статистику в файл");
        }
    }

//    public void getCPULoad(double maxLoad) {
//        double load = -1.0;
//        OperatingSystemMXBean osMBean
//                = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        System.out.println("Операционка: " + System.getProperty("os.name"));
//        System.out.println("Количество процессоров: " + osMBean.getAvailableProcessors());

//        if (System.getProperty("os.name").equals("Windows 10")) {
//            load = osMBean.getProcessCpuLoad();
//        } else if (System.getProperty("os.name").equals("Linux")) {
//            load = osMBean.getSystemLoadAverage ();
//        }
//
//        System.out.println("Средняя загрузка процессоров: " + load);

//        do {
//            sleep(3000);
//            if (System.getProperty("os.name").equals("Windows 10")) {
//                load = osMBean.getProcessCpuLoad();
//            } else if (System.getProperty("os.name").equals("Linux")) {
//                load = osMBean.getSystemLoadAverage();
//            }
//            System.out.println("Средняя загрузка процессоров: " + load);
//
//        } while (load > maxLoad);

//        do {
//            sleep(3000);
//            if (System.getProperty("os.name").equals("Windows 10")) {
//                load = osMBean.getProcessCpuLoad();
//            } else if (System.getProperty("os.name").equals("Linux")) {
//                load = osMBean.getSystemLoadAverage();
//            }
//            System.out.println("Средняя загрузка процессоров: " + load);
//
//        } while (load > maxLoad);

//        return load;
//    }


    public void getCPULoad(int permittedLoad) {
        try {
            String user1 = "fesb";
            String password = "fesb2016";
            String shortUrl1 = ConfigProperties.getTestProperty("BaseUrl").split("//")[1].split(":")[0];
            String bashCommandGetSumCpu = "cat /proc/cpuinfo|grep processor|wc -l";
            String bashCommandGetCurrentLoadCpu = "top -b -d 1 -n 2 | awk '$1 == \"PID\" {block_num++; next} block_num == 2 {sum += $9;} END {print sum}'";

            int SumCpu = Integer.parseInt(executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandGetSumCpu).trim());
            System.out.println("Операционка: " + System.getProperty("os.name"));
            System.out.println("Количество процессоров: " + SumCpu);
            int maxLoad = SumCpu * 100;
            int currentLoad = 1000000;
            currentLoad = Integer.parseInt(executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandGetCurrentLoadCpu).trim().split(",")[0]);

            System.out.println("Текущая нагрузка " + currentLoad + "  - Максимальная нагрузка " + maxLoad);
            while ((maxLoad / 100 * permittedLoad) < currentLoad) {
                sleep(15000);
                currentLoad = Integer.parseInt(executeBashCommandToSsh(shortUrl1, 22, user1, password, bashCommandGetCurrentLoadCpu).trim().split(",")[0]);
                System.out.println(currentLoad + "  -  " + maxLoad);
            }
        } catch (NullPointerException e) {
            sout("Не удалось получить информацию о нагрузке на CPU");
            e.printStackTrace();
        }
    }

    public int getCPUTemperature() {
        int cpu1Temp = 0;
        try {
            String user1 = "root";
            String password = "1qaz@WSX3edc";
//            String shortUrl1 = ConfigProperties.getTestProperty("BaseUrl").split("//")[1].split(":")[0];
            String bashCommandGetCPUTemperature = "esxcli hardware ipmi sdr list | grep 'CPU1 Temp' | awk '{print $7}'";
            cpu1Temp = Integer.parseInt(executeBashCommandToSsh("192.168.57.200", 22, user1, password, bashCommandGetCPUTemperature).trim());
        } catch (NullPointerException e) {
            sout("Не удалось получить информацию о температуре CPU-1");
            e.printStackTrace();
        }
        return cpu1Temp;
    }

    @Step
    public void closeForm() {
        click(closeWindowIcon);
        elementShouldNotBeVisible(closeWindowIcon);
    }

    @Step
    public void restartModule() {
        do {
            sleep(1000);
        } while (restartModuleButton.size() > 1);
        sleep(4000);
        click(restartModuleButton.find(visible));
        userProfileMenu.waitUntil(visible, 20000);
    }

    @Step
    public void restartDomain() {
        do {
            sleep(1000);
        } while (restartDomainButton.size() > 1);
        sleep(2000);
        click(restartDomainButton.get(0));
        userProfileMenu.waitUntil(visible, 20000);
    }

    @Step
    public void restartFesb() {
        do {
            sleep(1000);
        } while (restartFesbButton.size() > 1);
        sleep(4000);
        click(restartFesbButton.find(visible));
    }

    @Step
    public void restartFesbthroughManageOfSystem() {
        open("/manager/#/settings/modules");
        click(fullRestartFesbThroughManageOfSystemButton);
        click(confirmFullRestartFesbThroughManageOfSystemButton);
    }

    @Step
    public void writeContentDowloadedFile(String filePath, SessionId sessionId, String downloadedFileName) {
        File file = new File(filePath);
        URL urlDonor = null;
        try {
            urlDonor = new URL("http://192.168.57.240:4444/download/" + sessionId + "/" + downloadedFileName);
            System.out.println("Copy from: " + urlDonor);
            System.out.println("Copy to: " + filePath);
            ReadableByteChannel rbc = Channels.newChannel(urlDonor.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Проблема с копированием файла на сервер");
        }
    }

    @Step
    public String readContentFile(String downloadedFileName) {
        String asd = null;
        try {
            sout("Read from: " + downloadedFileName);
            return new String(Files.readAllBytes(Paths.get(downloadedFileName)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new AssertionError("Проблема с чтением файла на сервере");
        }
    }


    @Step
    public void deleteFile(String downloadedFilePath) {
        File file = new File(downloadedFilePath);
        if (!System.getProperty("os.name").equals("Windows 10")) {
            if (file.delete()) {
                System.out.println(downloadedFilePath + "  файл удален");
            }
            if (file.exists()) {
                throw new AssertionError(downloadedFilePath + "  файл не удалился");
            } else {
                System.out.println(downloadedFilePath + " файл не существует и это хорошо");
            }
        }
    }

    @Step
    public void checkFallenInterface() {
        if (errorLogLink.is(Condition.visible)) {
            click(errorLogLink);
            System.out.println("УПАЛ ИНТЕРФЕЙС");
            sleep(2000);
        }
    }
}
