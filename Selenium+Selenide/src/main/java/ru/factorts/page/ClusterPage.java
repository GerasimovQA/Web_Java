package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class ClusterPage extends BasePage {
    SettingsPage settingsPage;
    QueueManagerMultyPage queueManagerMultyPage = new QueueManagerMultyPage();

    static String userForAutorizationOption = "//li[text()='%s']";

    SelenideElement pathToStorageOfQueueInput = $x(".//label[text()='Путь к хранилищу очередей']/../following-sibling::div//input");
    SelenideElement activateModeWorkOfClusterSelect = $x(".//label[text()='Режим работы кластера']/../following-sibling::div");
    SelenideElement modeWorkOfClusterInput = $x(".//label[text()='Режим работы кластера']/../following-sibling::div//input");
    SelenideElement activeNodeSwitchingDelayInput = $x(".//label[text()='Задержка переключения активного узла (мс)']/../following-sibling::div//input");
    SelenideElement continueTryingToReconnectCheckBox = $x(".//span[text()='Продолжать попытки восстановить соединение с основным узлом']/..//input/..");
    SelenideElement createAndHoldOpenSecondConnectionCheckBox = $x(".//span[text()='Создавать и удерживать открытым второе подключение для быстрого переключения']/..//input/..");
    SelenideElement delayBeforeFirstSwitchingInput = $x(".//label[text()='Задержка до первого переключения (мс)']/../following-sibling::div//input");
    SelenideElement trackedMessageCacheSizeInput = $x(".//label[text()='Размер кэша отслеживаемых сообщений (байт)']/../following-sibling::div//input");
    SelenideElement partialResetConfirmationButton = $x(".//div[text()='Сохранить изменения настроек кластера?']/../following-sibling::div//span[text()=\"Сохранить\"]/..");
    SelenideElement spinner = $x(".//span[@class=\"ant-btn-loading-icon\"]");

    SelenideElement addAdressButton = $x(".//i[@class=\"anticon anticon-plus\"]/..");
    ElementsCollection scheme = $$x(".//th[text()='Адрес менеджера очередей']/../../following-sibling::tbody/tr/td[0]//input");
    ElementsCollection adressQM = $$x(".//th[text()='Адрес менеджера очередей']/../../following-sibling::tbody/tr/td[1]//input");
    ElementsCollection port = $$x(".//th[text()='Адрес менеджера очередей']/../../following-sibling::tbody/tr/td[2]//input");
    ElementsCollection jmxPort = $$x(".//th[text()='Адрес менеджера очередей']/../../following-sibling::tbody/tr/td[3]//input");

    @Step
    public void createSettingCluster(String managerName, String modeWorkOfCluster, String pathToStorageOfQueue, String activeNodeSwitchingDelay) {
//        settingsPage = new SettingsPage();
        queueManagerMultyPage.goToManager(managerName);
        click(clusterTab);
        if (modeWorkOfCluster != null) {
            click(activateModeWorkOfClusterSelect);
            valAndSelectElement(modeWorkOfClusterInput, modeWorkOfCluster);
        }
        if (pathToStorageOfQueue != null) val(pathToStorageOfQueueInput, pathToStorageOfQueue);
        if (activeNodeSwitchingDelay != null) val(activeNodeSwitchingDelayInput, activeNodeSwitchingDelay);

        click(saveButton);
//        saveButton.waitUntil(Condition.not(Condition.visible),180000);
        click(partialResetConfirmationButton);
        partialResetConfirmationButton.waitUntil(Condition.not(Condition.visible), 240000);
    }

    @Step
    public void createSettingCluster850(String modeWorkOfCluster, String pathToStorageOfQueue, String activeNodeSwitchingDelay) {
        settingsPage = new SettingsPage();
        click(clusterTab);
        if (modeWorkOfCluster != null) {
            click(activateModeWorkOfClusterSelect);
            valAndSelectElement(modeWorkOfClusterInput, modeWorkOfCluster);
        }
        if (pathToStorageOfQueue != null) val(pathToStorageOfQueueInput, pathToStorageOfQueue);
        if (activeNodeSwitchingDelay != null) val(activeNodeSwitchingDelayInput, activeNodeSwitchingDelay);

        click(saveButton);
//        saveButton.waitUntil(Condition.not(Condition.visible),180000);
        click(partialResetConfirmationButton);
        partialResetConfirmationButton.waitUntil(Condition.not(Condition.visible),240000);
    }



    @Step
    public void createSettingCluster(String modeWorkOfCluster, String pathToStorageOfQueue, String activeNodeSwitchingDelay, ArrayList<String> schemeList, ArrayList<String> adressesMQList, ArrayList<String> portList, Boolean continueTryingToReconnect, Boolean createAndHoldOpenSecondConnection, String delayBeforeFirstSwitching, String trackedMessageCacheSize) {
        IndexPage indexPage = new IndexPage();
        String verFesb = indexPage.getVersionFESB();
        sout(verFesb);

        settingsPage = new SettingsPage();
        if (verFesb.equals("5.0.199")) {
            click(cluster199Tab);
            if (pathToStorageOfQueue != null) val(pathToStorageOfQueueInput, pathToStorageOfQueue);
            if (activeNodeSwitchingDelay != null) val(activeNodeSwitchingDelayInput, activeNodeSwitchingDelay);
        } else {
            click(clusterTab);
            if (modeWorkOfCluster != null) {
                click(activateModeWorkOfClusterSelect);
                valAndSelectElement(modeWorkOfClusterInput, modeWorkOfCluster);
            }
            if (pathToStorageOfQueue != null) val(pathToStorageOfQueueInput, pathToStorageOfQueue);
            if (activeNodeSwitchingDelay != null) val(activeNodeSwitchingDelayInput, activeNodeSwitchingDelay);
            if (adressesMQList != null) {
                for (int x = 0; x < adressesMQList.size(); x++) {
                    click(addAdressButton);
                    val(scheme.get(x), schemeList.get(x));
                    val(adressQM.get(x), adressesMQList.get(x));
                    val(port.get(x), portList.get(x));
                }
            }

            if (continueTryingToReconnect != null && continueTryingToReconnect && !continueTryingToReconnectCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(continueTryingToReconnectCheckBox);
            } else if (continueTryingToReconnect != null && !continueTryingToReconnect && continueTryingToReconnectCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(continueTryingToReconnectCheckBox);
            }

            if (createAndHoldOpenSecondConnection != null && createAndHoldOpenSecondConnection && !createAndHoldOpenSecondConnectionCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(createAndHoldOpenSecondConnectionCheckBox);
            } else if (createAndHoldOpenSecondConnection != null && !createAndHoldOpenSecondConnection && createAndHoldOpenSecondConnectionCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
                click(createAndHoldOpenSecondConnectionCheckBox);
            }

            if (delayBeforeFirstSwitching != null) val(delayBeforeFirstSwitchingInput, pathToStorageOfQueue);
            if (trackedMessageCacheSize != null) val(trackedMessageCacheSizeInput, trackedMessageCacheSize);
            click(saveButton);
            click(partialResetConfirmationButton);
        }
    }

    @Step
    public void checkSettingCluster(String modeWorkOfCluster, String pathToStorageOfQueue, String activeNodeSwitchingDelay,
                                    ArrayList<String> schemeList, ArrayList<String> adressesMQList, ArrayList<String> portList, Boolean continueTryingToReconnect, Boolean createAndHoldOpenSecondConnection, String delayBeforeFirstSwitching, String trackedMessageCacheSize) {
        settingsPage = new SettingsPage();
        click(clusterTab);
        if (modeWorkOfCluster != null) compareStringAndElementSelectedText(modeWorkOfCluster, modeWorkOfClusterInput);
        if (pathToStorageOfQueue != null)
            compareStringAndElementValue(pathToStorageOfQueue, pathToStorageOfQueueInput);
        if (activeNodeSwitchingDelay != null)
            compareStringAndElementValue(activeNodeSwitchingDelay, activeNodeSwitchingDelayInput);
        if (adressesMQList != null) {
            for (int x = 0; x < adressesMQList.size(); x++) {
                compareStringAndElementSelectedValue(schemeList.get(x), scheme.get(x));
                compareStringAndElementSelectedValue(adressesMQList.get(x), adressQM.get(x));
                compareStringAndElementSelectedValue(portList.get(x), port.get(x));
            }
        }

        if (continueTryingToReconnect != null && continueTryingToReconnect && !continueTryingToReconnectCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(continueTryingToReconnectCheckBox);
        } else if (continueTryingToReconnect != null && !continueTryingToReconnect && continueTryingToReconnectCheckBox.attr("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(continueTryingToReconnectCheckBox);
        }

        if (createAndHoldOpenSecondConnection != null && createAndHoldOpenSecondConnection) {
            Assert.assertEquals("ant-checkbox ant-checkbox-checked", createAndHoldOpenSecondConnectionCheckBox.attr("class"));
        } else if (createAndHoldOpenSecondConnection != null && !createAndHoldOpenSecondConnection) {
            Assert.assertNotEquals("ant-checkbox ant-checkbox-checked", createAndHoldOpenSecondConnectionCheckBox.attr("class"));
        }

        if (delayBeforeFirstSwitching != null)
            compareStringAndElementValue(delayBeforeFirstSwitching, delayBeforeFirstSwitchingInput);
        if (trackedMessageCacheSize != null)
            compareStringAndElementValue(trackedMessageCacheSize, trackedMessageCacheSizeInput);
    }
}