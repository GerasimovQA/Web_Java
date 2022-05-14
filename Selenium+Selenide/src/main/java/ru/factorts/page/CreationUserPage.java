package ru.factorts.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;


public class CreationUserPage extends BasePage {

    UserPage userPage = new UserPage();
    BasePage basePage = new BasePage();
    IndexPage indexPage = new IndexPage("Empty");
    ResourceMonitor resourceMonitor = new ResourceMonitor();
    MessagePage messagePage = new MessagePage();
    QueueManagerPage queueManagerPage = new QueueManagerPage("Empty");
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();
    SOPSPage sopsPage = new SOPSPage("Empty");
    DataBasePage dataBasePage = new DataBasePage();
    RestPage restPage = new RestPage();
    WebServicesPage webServicesPage = new WebServicesPage();
    TransactionMonitorPage transactionMonitorPage = new TransactionMonitorPage();
    DashboardPage dashboardPage = new DashboardPage();
    LogsPage logsPage = new LogsPage();
    SettingsPage settingsPage = new SettingsPage("Empty");
    UpdatePage updatePage = new UpdatePage();

    private static String roleJmxUser = "//div[@role=\"option\"][text()='%s']";
    private static String loginJmxUserInTable = "//td[text()='%s']";

    private static String roleUser = "//div[@role=\"option\"][text()='%s']";
    private static String loginUserInTable = "//td[text()='%s']";
    private static String domainsOfDeveloper = "//div[@role=\"option\"]/span[text()='%s']";


    public SelenideElement
            loginLocalUserInput = $x(".//input[@name=\"username\"]"),
            passwordLocalInput = $x(".//input[@name=\"password\"]"),

    loginJmxUserInput = $x(".//input[@name=\"login\"]"),
            passwordJmxInput = $x(".//input[@name=\"password\"]"),
            newPasswordJmxInput = $x(".//input[@name=\"rePassword\"]"),

    addButton = $x(".//button[text()='Добавить']"),
            changeRoleJmxUser = $x(".//div[text()=' Изменить роль']"),
            changeRoleJmxUserButton = $x(".//button[text()='Изменить']"),
            changePasswordJmxUser = $x(".//div[text()=' Изменить пароль']"),
            changePasswordJmxUserButton = $x(".//span[text()='Изменить пароль']/.."),
            deletedJmxUser = $x(".//div[text()=' Удалить']"),
            deleteButton = $x(".//span[text()='Удалить']/.."),

    roleJmxUserInTable = $$x("(.//span[text()='Роли']/ancestor::table)[2]/tbody/tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]").findBy(visible);
    SelenideElement blockForOperatorOfDashboard = $x(".//div[@data-key=\"list\"]");
    SelenideElement phraseNotAvalibleMonitors = $x(".//div[text()=\"Нет доступных экранов\"]");

    public enum Availability {Availible, NotAvailible}

//    public enum TabInLeftMenu {
//        GeneralInformation, ResourceMonitor, SendMessage, QueueManagerPage, DomainsOfBroker,
//        DataBase, Dashboard, LogsPage, SettingsPage, Documentation, WidgetsBlock, WidgetMonitors, WidgetsMenu
//    }

    @Step("Create Local User")
    public void createUser(String login, String password, String role) {
        click(userPage.addUserButton);
        val(loginLocalUserInput, login);
        val(passwordLocalInput, password);
        click(userPage.roleSelect);
        valAndSelectElement(userPage.roleInput, role);

        switch (login) {
            case ("USERTEST"):
                sleep(5000);
                createUserSaveButton.shouldBe(visible);
                break;
            case ("CancelCreateUser"):
                closeForm();
                break;
            default:
                click(createUserSaveButton);
        }
    }

//    @Step("Create Local User - Developer")
//    public void createUser(String Login, String Password, String Role, String domain1, String domain2) {
//        click(userPage.addUserButton);
//        val(loginLocalUserInput, Login);
//        val(passwordLocalInput, Password);
//        click(userPage.roleSelect);
//        valAndSelectElement(userPage.roleInput, Role);
//        click(userPage.domainsSelect);
//        valAndSelectElement(userPage.domainsInput, domain1);
//        valAndSelectElement(userPage.domainsInput, domain2);
//        click(modalWindow);
//        click(createUserSaveButton);
//    }

    @Step("Create Local User - Dashboard and Developer")
    public void createUser(String Login, String Password, String Role, String condition) {
        String[] conditions = condition.split("\\.");
        click(userPage.addUserButton);
        val(loginLocalUserInput, Login);
        val(passwordLocalInput, Password);
        click(userPage.roleSelect);
        valAndSelectElement(userPage.roleInput, Role);
        switch (Role) {
            case "Разработчик":
                click(userPage.domainsSelect);
                for (String unitCondition : conditions) {
                    valAndSelectElement(userPage.domainsInput, unitCondition);
                }
                break;
            case "Оператор панели мониторинга":
                click(userPage.screensActivate);
                for (String unitCondition : conditions) {
                    valAndSelectElement(userPage.screensInput, unitCondition);
                }
                break;
            default:
                throw new AssertionError("Пропущен выбор доменов или экранов");
        }
        click(modalWindow);
        click(createUserSaveButton);

    }

    @Step("Check user permissions")
    public void chekUserPermissions(String Role, String condition) {
        switch (Role) {
            case ("Администратор"):
                checkAvaliabilityTab(generalInformationTab, indexPage.version, Availability.Availible);
                checkAvaliabilityTab(resourceMonitorTab, resourceMonitor.maxSizeMemory, Availability.Availible);
                checkAvaliabilityTab(sendMessageTab, messagePage.messageTextAria, Availability.Availible);
                queueManagerMultyPage.goToManager("QM");
                checkAvaliabilityTab(queueManagerMultyTab, queueManagerMultyPage.createManagerButton, Availability.Availible);
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.queuesOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.topicsOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.subscribersOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.receiversOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.channelsOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.eventInterseptionOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.deliveryPolicyOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.managerSettingOfSpecificManagerButton, "QM")));
                checkAvaliabilityTab(domainsOfBrokerTab, sopsPage.createDomainButton, Availability.Availible);
                refresh();
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Dashboard примеры")));
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Main broker")));
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Transaction Monitor")));
                click($x(String.format(groupDomainsTab, "Архив")));
                elementShouldBeVisible($x(String.format(sopsPage.domainInGroup, "Примеры")));
                elementShouldBeVisible(customersComponentTab);
//                elementShouldBeVisible(constantsOfBrokerTab);
                checkAvaliabilityTab(constantsOfBrokerTab, sopsPage.addConstantButton, Availability.Availible);
                elementShouldBeVisible(createDomainTab);
                elementShouldBeVisible(importDomainTab);
//Проверка доступа к манипуляциям с СОПСом ->
                click($x(String.format(groupDomainsTab, "Архив")));
                click($x(String.format(sopsPage.domainInGroup, "Примеры")));
                sopsPage.rowAfterSearchSOPS.shouldBe(visible);
                sleep(1000);

//                click($x(String.format(SOPSPage.specificDomainButtonXpath, "Примеры")));
                contextClick(sopsPage.rowAfterSearchSOPS);
                elementShouldBeVisible(sopsPage.startSOPS);
                elementShouldBeVisible(sopsPage.stopSOPS);
                elementShouldBeVisible(sopsPage.forcedStopSOPS);
                elementShouldBeVisible(sopsPage.sendMessage);
                elementShouldBeVisible(sopsPage.sendSavedMessage);
                elementShouldBeVisible(sopsPage.cloneSOPS);
                elementShouldBeVisible(sopsPage.moveSOPS);
                elementShouldBeVisible(sopsPage.copySopsToServer);
                elementShouldBeVisible(sopsPage.deleteSOPS);
//Проверка доступа к манипуляциям с СОПСом <-
//Проверка доступа к манипуляциям с Доменом ->
                elementShouldBeVisible(sopsPage.runDomainButton);
                elementShouldBeVisible(sopsPage.settingDomainButton);
                elementShouldBeVisible(sopsPage.constantesDomainTab);
                elementShouldBeVisible(sopsPage.exportDomainButton);
                click(sopsPage.actionsUnderDomainsButton);
                elementShouldBeVisible(sopsPage.reloadDomainButton);
                elementShouldBeVisible(sopsPage.resetCountersButton);
                elementShouldBeVisible(sopsPage.moveDomainButton);
                elementShouldBeVisible(sopsPage.deployDomainButton);
                elementShouldBeVisible(sopsPage.runDebugButton);
                elementShouldBeVisible(sopsPage.deleteDomainButton);
//Проверка доступа к манипуляциям с Доменом <-
//Проверка доступа к манипуляциям с дополнительной конфигурацией ->
                click(sopsPage.additionConfigurationTab);
                contextClick(sopsPage.rowAfterSearchSOPS);
                elementShouldBeVisible(sopsPage.editConfigurationInContextMenu);
                elementShouldBeVisible(sopsPage.copyConfigurationInContextMenu);
                elementShouldBeVisible(sopsPage.deleteConfigurationInContextMenu);
//Проверка доступа к манипуляциям с дополнительной конфигурацией <-
                checkAvaliabilityTab(dataBaseTab, dataBasePage.identificatorHeader, Availability.Availible);
                elementShouldBeVisible(systemBdTab);
                elementShouldBeVisible(createDbTab);
//                elementShouldBeVisible(settingDataBaseTab);
                checkAvaliabilityTab(restTab, restTab, Availability.Availible);
                checkAvaliabilityTab(webServicesTab, webServicesPage.addServiceButton, Availability.Availible);
                checkAvaliabilityTab(transactionMonitorTab, transactionMonitorPage.connectionToDbTab, Availability.Availible);
                checkAvaliabilityTab(dashboardTab, dashboardPage.addGroupButton, Availability.Availible);
                elementShouldBeVisible(examplesOfdashboardTab);
                elementShouldBeVisible(newMonitorOfdashboardTab);
                checkAvaliabilityTab(logsPageTab, logsPage.getReportButton, Availability.Availible);
                checkAvaliabilityTab(settingsPageTab, updatePage.fullRestartFesbThroughManageOfSystemButton, Availability.Availible);
//Проверка доступа к манипуляциям с модулями системы ->
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Брокер")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Расширенный Менеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Dashboard")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Мультименеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "REST")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "База данных")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Монитор транзакций")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Веб-сервисы")));
//Проверка доступа к манипуляциям с модулями системы <-
                elementShouldBeVisible(systemManagmentTab);
                elementShouldBeVisible(configurationServerTab);
                elementShouldBeVisible(libraryManagmentTab);
                elementShouldBeVisible(loggingTab);
                elementShouldBeVisible(usersAndGroupTab);
//                elementShouldBeVisible(clusterTab);
                elementShouldBeVisible(fileManagerTab);
                elementShouldBeVisible(recoverPointsTab);
                checkAvaliabilityTabWithRedirect(documentationTab, documentationHeader1, Availability.Availible);
                basePage.changeWindow();
                elementShouldBeVisible(widgetsBlock);
                elementShouldNotBeVisible(widgetMonitors);
                elementShouldBeVisible(widgetsMenu);
                break;

            case ("Администратор менеджера очередей"):
                checkAvaliabilityTab(generalInformationTab, indexPage.version, Availability.Availible);
                checkAvaliabilityTab(resourceMonitorTab, resourceMonitor.maxSizeMemory, Availability.Availible);
                checkAvaliabilityTab(sendMessageTab, messagePage.messageTextAria, Availability.Availible);
                queueManagerMultyPage.goToManager("QM");
                checkAvaliabilityTab(queueManagerMultyTab, queueManagerMultyPage.createManagerButton, Availability.Availible);
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.queuesOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.topicsOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.subscribersOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.receiversOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.channelsOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.eventInterseptionOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.deliveryPolicyOfSpecificManagerButton, "QM")));
                elementShouldBeVisible($x(String.format(QueueManagerMultyPage.managerSettingOfSpecificManagerButton, "QM")));
//                elementShouldBeVisible(commandInterfaceTab);
                checkAvaliabilityTab(domainsOfBrokerTab, sopsPage.createDomainButton, Availability.NotAvailible);
                elementShouldNotBeVisible(constantsOfBrokerTab);
                checkAvaliabilityTab(dataBaseTab, dataBasePage.identificatorHeader, Availability.NotAvailible);
                checkAvaliabilityTab(restTab, restPage.addParameterButton, Availability.NotAvailible);
                checkAvaliabilityTab(webServicesTab, webServicesPage.addServiceButton, Availability.NotAvailible);
                checkAvaliabilityTab(transactionMonitorTab, transactionMonitorPage.connectionToDbTab, Availability.Availible);
                checkAvaliabilityTab(dashboardTab, dashboardPage.addGroupButton, Availability.NotAvailible);
                checkAvaliabilityTab(logsPageTab, logsPage.getReportButton, Availability.Availible);
                checkAvaliabilityTab(settingsPageTab, updatePage.fullRestartFesbThroughManageOfSystemButton, Availability.Availible);
//Проверка доступа к манипуляциям с модулями системы ->
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Брокер")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Расширенный Менеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Dashboard")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Мультименеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "REST")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "База данных")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Монитор транзакций")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Веб-сервисы")));
//Проверка доступа к манипуляциям с модулями системы <-
                elementShouldBeVisible(systemManagmentTab);
                elementShouldNotBeVisible(configurationServerTab);
                elementShouldNotBeVisible(libraryManagmentTab);
                elementShouldNotBeVisible(loggingTab);
                elementShouldNotBeVisible(usersAndGroupTab);
                elementShouldNotBeVisible(clusterTab);
                elementShouldNotBeVisible(fileManagerTab);
                elementShouldNotBeVisible(recoverPointsTab);
                checkAvaliabilityTabWithRedirect(documentationTab, documentationHeader1, Availability.Availible);
                basePage.changeWindow();
                elementShouldBeVisible(widgetsBlock);
                elementShouldNotBeVisible(widgetMonitors);
                elementShouldBeVisible(widgetsMenu);
                break;

            case ("Разработчик"):
                checkAvaliabilityTab(generalInformationTab, indexPage.version, Availability.Availible);
                checkAvaliabilityTab(resourceMonitorTab, resourceMonitor.maxSizeMemory, Availability.Availible);
                checkAvaliabilityTab(sendMessageTab, messagePage.messageTextAria, Availability.NotAvailible);
                checkAvaliabilityTab(queueManagerTab, queueManagerPage.addQueueButton, Availability.NotAvailible);
                checkAvaliabilityTab(domainsOfBrokerTab, constantsOfBrokerTab, Availability.Availible);
                refresh();
                elementShouldNotBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Dashboard примеры")));
                try {
                    click($x(String.format(groupDomainsTab, "Архив")));
                } catch (NoSuchElementException e) {
                    Selenide.clearBrowserCookies();
                    Selenide.clearBrowserLocalStorage();
                    refresh();
                    click($x(String.format(groupDomainsTab, "Архив")));
                }

                switch (condition) {
                    case "без доменов":
                        elementShouldNotBeVisible($x(String.format(sopsPage.domainInGroup, "Примеры")));
                        elementShouldNotBeVisible($x(String.format(sopsPage.specificDomainButtonXpath, "Main broker")));
                        break;
                    case "Примеры":
                        elementShouldBeVisible($x(String.format(sopsPage.domainInGroup, "Примеры")));
                        elementShouldNotBeVisible($x(String.format(sopsPage.specificDomainButtonXpath, "Main broker")));
                        break;
                    case "Примеры.Main broker":
                        elementShouldBeVisible($x(String.format(sopsPage.domainInGroup, "Примеры")));
                        elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Main broker")));
                        break;
                    default:
                        throw new AssertionError("Пропущена проверка видимости доменов");
                }

                elementShouldNotBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Transaction Monitor")));
                elementShouldBeVisible(customersComponentTab);
                elementShouldNotBeVisible(createDomainTab);
                elementShouldNotBeVisible(importDomainTab);

                switch (condition) {
                    case "без доменов":
                        break;
                    case "Примеры":
                    case "Примеры.Main broker":
                        //Проверка доступа к манипуляциям с СОПСом ->
                        click($x(String.format(sopsPage.domainInGroup, "Примеры")));
                        sopsPage.rowAfterSearchSOPS.shouldBe(visible);
                        sleep(1000);
                        contextClick(sopsPage.rowAfterSearchSOPS);
                        elementShouldBeVisible(sopsPage.startSOPS);
                        elementShouldBeVisible(sopsPage.stopSOPS);
                        elementShouldBeVisible(sopsPage.forcedStopSOPS);
                        elementShouldBeVisible(sopsPage.sendMessage);
                        elementShouldBeVisible(sopsPage.sendSavedMessage);
                        elementShouldBeVisible(sopsPage.cloneSOPS);
                        elementShouldBeVisible(sopsPage.moveSOPS);
                        elementShouldBeVisible(sopsPage.copySopsToServer);
                        elementShouldBeVisible(sopsPage.deleteSOPS);
//Проверка доступа к манипуляциям с СОПСом <-
//Проверка доступа к манипуляциям с Доменом ->
                        elementShouldBeVisible(sopsPage.runDomainButton);
                        elementShouldBeVisible(sopsPage.settingDomainButton);
                        elementShouldBeVisible(sopsPage.constantesDomainTab);
                        elementShouldBeVisible(sopsPage.exportDomainButton);
                        click(sopsPage.actionsUnderDomainsButton);
                        elementShouldBeVisible(sopsPage.reloadDomainButton);
                        elementShouldBeVisible(sopsPage.resetCountersButton);
                        elementShouldBeVisible(sopsPage.moveDomainButton);
                        elementShouldBeVisible(sopsPage.deployDomainButton);
                        elementShouldBeVisible(sopsPage.runDebugButton);
                        elementShouldNotBeVisible(sopsPage.deleteDomainButton);
//Проверка доступа к манипуляциям с Доменом <-
//Проверка доступа к манипуляциям с дополнительной конфигурацией ->
                        click(sopsPage.additionConfigurationTab);
                        contextClick(sopsPage.rowAfterSearchSOPS);
                        elementShouldBeVisible(sopsPage.editConfigurationInContextMenu);
                        elementShouldBeVisible(sopsPage.copyConfigurationInContextMenu);
                        elementShouldBeVisible(sopsPage.deleteConfigurationInContextMenu);
//Проверка доступа к манипуляциям с дополнительной конфигурацией <-

                        click(constantsOfBrokerTab);
                        elementShouldNotBeVisible(sopsPage.addConstantButton);

                        checkAvaliabilityTab(dataBaseTab, dataBasePage.identificatorHeader, Availability.NotAvailible);
                        checkAvaliabilityTab(restTab, restTab, Availability.Availible);
                        checkAvaliabilityTab(webServicesTab, webServicesPage.addServiceButton, Availability.Availible);
                        checkAvaliabilityTab(transactionMonitorTab, transactionMonitorPage.connectionToDbTab, Availability.Availible);
                        checkAvaliabilityTab(dashboardTab, dashboardPage.addGroupButton, Availability.NotAvailible);
                        checkAvaliabilityTab(logsPageTab, logsPage.getReportButton, Availability.Availible);
                        checkAvaliabilityTabWithRedirect(documentationTab, documentationHeader1, Availability.Availible);
                        basePage.changeWindow();
                        elementShouldBeVisible(widgetsBlock);
                        elementShouldNotBeVisible(widgetMonitors);
                        elementShouldBeVisible(widgetsMenu);
                        break;
                    default:
                        throw new AssertionError("Пропущена проверка взаимодействия с доменом");
                }
                break;
            case ("Оператор"):
                checkAvaliabilityTab(generalInformationTab, indexPage.version, Availability.Availible);
                checkAvaliabilityTab(resourceMonitorTab, resourceMonitor.maxSizeMemory, Availability.Availible);
                checkAvaliabilityTab(sendMessageTab, messagePage.messageTextAria, Availability.NotAvailible);
                checkAvaliabilityTab(queueManagerTab, queueManagerPage.addQueueButton, Availability.NotAvailible);
                checkAvaliabilityTab(domainsOfBrokerTab, sopsPage.actionsUnderDomainsButton, Availability.Availible);
                refresh();
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Dashboard примеры")));
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Main broker")));
                elementShouldBeVisible($x(String.format(SOPSPage.specificDomainButtonXpath, "Transaction Monitor")));
                click($x(String.format(groupDomainsTab, "Архив")));
                elementShouldBeVisible($x(String.format(sopsPage.domainInGroup, "Примеры")));
                /* Должно быть невидимо после исправления тикета */
                elementShouldBeVisible(customersComponentTab);
                elementShouldNotBeVisible(createDomainTab);
                elementShouldNotBeVisible(importDomainTab);
                /* Должно быть невидимо после исправления тикета */
//Проверка доступа к манипуляциям с СОПСом ->
                click($x(String.format(sopsPage.domainInGroup, "Примеры")));
                contextClick(sopsPage.rowAfterSearchSOPS);
                elementShouldBeVisible(sopsPage.startSOPS);
                elementShouldBeVisible(sopsPage.stopSOPS);
                elementShouldBeVisible(sopsPage.forcedStopSOPS);
                elementShouldNotBeVisible(sopsPage.sendMessage);
                elementShouldNotBeVisible(sopsPage.sendSavedMessage);
                elementShouldNotBeVisible(sopsPage.cloneSOPS);
                elementShouldNotBeVisible(sopsPage.moveSOPS);
                elementShouldNotBeVisible(sopsPage.copySopsToServer);
                elementShouldNotBeVisible(sopsPage.deleteSOPS);
//Проверка доступа к манипуляциям с СОПСом <-
//Проверка доступа к манипуляциям с Доменом ->
                elementShouldBeVisible(sopsPage.constantesDomainTab);
                elementShouldBeVisible(sopsPage.runDomainButton);
                elementShouldNotBeVisible(sopsPage.settingDomainButton);
                elementShouldNotBeVisible(sopsPage.exportDomainButton);
                click(sopsPage.actionsUnderDomainsButton);
                elementShouldBeVisible(sopsPage.reloadDomainButton);
                elementShouldBeVisible(sopsPage.resetCountersButton);
                elementShouldNotBeVisible(sopsPage.moveDomainButton);
                elementShouldNotBeVisible(sopsPage.deployDomainButton);
                elementShouldNotBeVisible(sopsPage.runDebugButton);
                elementShouldNotBeVisible(sopsPage.deleteDomainButton);
//Проверка доступа к манипуляциям с Доменом <-

                /* Должно быть невидимо после исправления тикета */
//Проверка доступа к манипуляциям с дополнительной конфигурацией ->
                elementShouldBeVisible(sopsPage.additionConfigurationTab);
//Проверка доступа к манипуляциям с дополнительной конфигурацией <-
                /* Должно быть невидимо после исправления тикета */
                click(constantsOfBrokerTab);
                elementShouldNotBeVisible(sopsPage.addConstantButton);

                checkAvaliabilityTab(dataBaseTab, dataBasePage.identificatorHeader, Availability.NotAvailible);
                checkAvaliabilityTab(restTab, restPage.exportButton, Availability.Availible);
                checkAvaliabilityTab(webServicesTab, webServicesPage.addServiceButton, Availability.NotAvailible);
                checkAvaliabilityTab(transactionMonitorTab, transactionMonitorPage.connectionToDbTab, Availability.Availible);
                checkAvaliabilityTab(dashboardTab, examplesOfdashboardTab, Availability.Availible);
                elementShouldBeVisible(examplesOfdashboardTab);
                elementShouldNotBeVisible(newMonitorOfdashboardTab);
                checkAvaliabilityTab(logsPageTab, logsPage.getReportButton, Availability.Availible);
                checkAvaliabilityTab(settingsPageTab, updatePage.fullRestartFesbThroughManageOfSystemButton, Availability.Availible);
//Проверка доступа к манипуляциям с модулями системы ->
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Брокер")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Расширенный Менеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Dashboard")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Мультименеджер очередей")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "REST")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "База данных")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Монитор транзакций")));
                elementShouldBeVisible($x(String.format(CommonComponents.moduleTurnOffXpath, "Веб-сервисы")));
//Проверка доступа к манипуляциям с модулями системы <-
                elementShouldBeVisible(systemManagmentTab);
                elementShouldNotBeVisible(configurationServerTab);
                elementShouldNotBeVisible(libraryManagmentTab);
                elementShouldNotBeVisible(loggingTab);
                elementShouldNotBeVisible(usersAndGroupTab);
                elementShouldNotBeVisible(clusterTab);
                elementShouldNotBeVisible(fileManagerTab);
                elementShouldNotBeVisible(recoverPointsTab);
                checkAvaliabilityTabWithRedirect(documentationTab, documentationHeader1, Availability.Availible);
                basePage.changeWindow();
                elementShouldBeVisible(widgetsBlock);
                elementShouldNotBeVisible(widgetMonitors);
                elementShouldBeVisible(widgetsMenu);
                break;

            case ("Оператор панели мониторинга"):
                checkAvaliabilityTab(generalInformationTab, indexPage.version, Availability.NotAvailible);
                checkAvaliabilityTab(resourceMonitorTab, resourceMonitor.maxSizeMemory, Availability.NotAvailible);
                checkAvaliabilityTab(sendMessageTab, messagePage.messageTextAria, Availability.NotAvailible);
                checkAvaliabilityTab(queueManagerTab, queueManagerPage.addQueueButton, Availability.NotAvailible);
                checkAvaliabilityTab(domainsOfBrokerTab, sopsPage.createDomainButton, Availability.NotAvailible);
                checkAvaliabilityTab(dataBaseTab, dataBasePage.identificatorHeader, Availability.NotAvailible);
                checkAvaliabilityTab(restTab, restPage.addParameterButton, Availability.NotAvailible);
                checkAvaliabilityTab(webServicesTab, webServicesPage.addServiceButton, Availability.NotAvailible);
                checkAvaliabilityTab(transactionMonitorTab, transactionMonitorPage.processingTimeHeader, Availability.NotAvailible);
                checkAvaliabilityTab(dashboardTab, dashboardPage.addGroupButton, Availability.NotAvailible);
                checkAvaliabilityTab(logsPageTab, logsPage.getReportButton, Availability.NotAvailible);
                checkAvaliabilityTab(settingsPageTab, updatePage.fullRestartFesbButton, Availability.NotAvailible);
                elementShouldNotBeVisible(widgetsMenu);

                click(widgetMonitors);
                switch (condition) {
                    case "без экранов":
                        $x(String.format(dashboardPage.specificMonitor, "Примеры")).shouldNotBe(visible);
                        $x(String.format(dashboardPage.specificMonitor, "ВторойЭкран")).shouldNotBe(visible);
                        phraseNotAvalibleMonitors.shouldBe(visible);

                        break;
                    case "Примеры":
                        click($x(String.format(dashboardPage.specificMonitor, "Примеры")));
                        phraseNotAvalibleMonitors.shouldNotBe(visible);
                        elementShouldNotBeVisible(widgetsMenu);
                        $x(String.format(dashboardPage.specificMonitor, "ВторойЭкран")).shouldNotBe(visible);
                        break;
                    case "Примеры.ВторойЭкран":
                        click($x(String.format(dashboardPage.specificMonitor, "Примеры")));
                        phraseNotAvalibleMonitors.shouldNotBe(visible);
                        elementShouldNotBeVisible(widgetsMenu);
                        sleep(2000);
                        click(widgetMonitors);
                        try {
                            click($x(String.format(dashboardPage.specificMonitor, "ВторойЭкран")));
                        } catch (ElementShould e) {
                            sout("Список экранов опять не отобразился");
                            Selenide.clearBrowserCookies();
                            Selenide.clearBrowserLocalStorage();
                            refresh();
                            click(widgetMonitors);
                            click($x(String.format(dashboardPage.specificMonitor, "ВторойЭкран")));
                        }
                        elementShouldNotBeVisible(widgetsMenu);
                        break;
                    default:
                        throw new AssertionError("Пропущена проверка пермишенов для Дашборда");
                }
                break;
            default:
                throw new AssertionError("Permission check skipped and this error");
        }
    }

    public void checkRoleUsers(String role, String login) {
        if (role.equals("Администратор")) {
            settingsPage();
            userPage.usersAndGroupPage();
            userPage.search(login);
            userPage.afterSearchRoleCell.shouldHave(text("Администратор"));
        }
    }

    @Step("Create JMX User")
    public void createJmxUserWithoutSave(String Login, String Password, String Role) {
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        click(userPage.jmxUsersTab);
        click(userPage.addUserButton);
        val(loginJmxUserInput, Login);
        val(passwordJmxInput, Password);
        click(userPage.roleSelect);
        valAndSelectElement(userPage.roleInput, Role);
    }

    @Step()
    public void successCreateJmxUser(String Login, String Password, String Role) {
        createJmxUserWithoutSave(Login, Password, Role);
        click(saveButton);
    }

    @Step()
    public void cancelCreateJmxUser(String Login, String Password, String Role) {
        createJmxUserWithoutSave(Login, Password, Role);
        click(closeWindowIcon);
    }

    @Step("Edit Role JMX User")
    public void editRoleJmxUserWhithoutSave(String login, String role) {
        if (!url().contains("/settings/users")) {
            basePage.settingsPage();
            userPage.usersAndGroupPage();
            click(userPage.jmxUsersTab);
        }
        userPage.search(login);
        contextClick($x(String.format(loginJmxUserInTable, login)));
        click(changeRoleJmxUser);
        click(userPage.roleSelect);
        valAndSelectElement(userPage.roleInput, role);
    }

    @Step()
    public void successEditRoleJmxUser(String login, String role) {
        editRoleJmxUserWhithoutSave(login, role);
        click(saveButton);
    }

    @Step()
    public void cancelEditRoleJmxUser(String login, String role) {
        editRoleJmxUserWhithoutSave(login, role);
        click(closeWindowIcon);
    }

    public void checkRoleJmxUsers(String login, String role) {
        if (!url().contains("/settings/users")) {
            basePage.settingsPage();
            userPage.usersAndGroupPage();
            click(userPage.jmxUsersTab);
        }
        userPage.search(login);
        Assert.assertEquals(role, roleJmxUserInTable.getText());
    }

    @Step("Edit Password JMX User")
    public void editPasswordJmxUserWhithoutSave(String login, String password) {
        if (!url().contains("/settings/users")) {
            basePage.settingsPage();
            userPage.usersAndGroupPage();
            click(userPage.jmxUsersTab);
        }
        userPage.search(login);
        contextClick($x(String.format(loginJmxUserInTable, login)));
        click(changePasswordJmxUser);
        val(passwordJmxInput, password);
        val(newPasswordJmxInput, password);
    }

    @Step()
    public void successEditPasswordJmxUser(String login, String password) {
        editPasswordJmxUserWhithoutSave(login, password);
        click(changePasswordJmxUserButton);
    }

    @Step()
    public void cancelEditPasswordJmxUser(String login, String password) {
        editPasswordJmxUserWhithoutSave(login, password);
        click(closeWindowIcon);
    }

    @Step("Delete JMX User")
    public void deleteJmxUserWithoutSave(String login) {
        refresh();
        if (!url().contains("/settings/users")) {
            basePage.settingsPage();
            userPage.usersAndGroupPage();
        }
        click(userPage.jmxUsersTab);
        userPage.search(login);
        contextClick($x(String.format(loginJmxUserInTable, login)));
        click(deletedJmxUser);
    }

    @Step()
    public void successDeleteJmxUser(String login) {
        deleteJmxUserWithoutSave(login);
        click(deleteButton);
    }

    @Step()
    public void cancelDeleteJmxUser(String login) {
        deleteJmxUserWithoutSave(login);
        click(closeWindowIcon);
    }

    public void checkDeletedJmxUsers(String login) {
        refresh();
        if (!url().contains("/settings/users")) {
            basePage.settingsPage();
            userPage.usersAndGroupPage();
        }
        click(userPage.jmxUsersTab);
        userPage.failedSsearchLocalUser(login);
        Assert.assertFalse($x(String.format(loginJmxUserInTable, login)).exists());
    }

    public void checkAvaliabilityTab(SelenideElement tab, SelenideElement check, Availability availability) {
        if (availability.equals(Availability.Availible)) {
            click(tab);
            /*      Удалить после исправления ->*/
            if (tab.exists() && tab.toString().equals("<a aria-current=\"page\" class=\"active\" href=\"#/store\">База данных</a>"))
                click(systemBdTab);
            if (tab.exists() && tab.toString().contains("value=\"0\">Настройки")) click(systemManagmentTab);
            /*      Удалить после исправления <-*/
            elementShouldBeVisible(check);
        } else {
            elementShouldNotBeVisible(tab);
        }
    }

    public void checkAvaliabilityTabWithRedirect(SelenideElement tab, SelenideElement check, Availability availability) {
        if (availability.equals(Availability.Availible)) {
            click(tab);
            basePage.changeWindow();
            elementShouldBeVisible(check);
        } else {
            elementShouldNotBeVisible(tab);
        }
    }
}
