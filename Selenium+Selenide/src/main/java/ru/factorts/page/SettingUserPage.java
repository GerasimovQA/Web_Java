package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.$x;


public class SettingUserPage extends BasePage {


    UserPage userPage = new UserPage();
    BasePage basePage = new BasePage();

    public SelenideElement
            changePasswordButtonInMenu = $x(".//a[text()='Смена пароля']"),
            serversButtonInMenu = $x(".//a[text()='Сервера']"),
            settingButtonInMenu = $x(".//a[text()='Настройки']"),
            addRemoteServerButton = $x(".//span[text()='Добавить удаленный сервер']/.."),
            nameOfServerInput = $x(".//label[text()='Имя']/../following-sibling::div//input"),
            protocolOfServerActivate = $x(".//label[text()='Протокол']/../following-sibling::div//input/../.."),
            protocolOfServerInput = $x(".//label[text()='Протокол']/../following-sibling::div//input"),
            hostOfServerInput = $x(".//label[text()='Адрес']/../following-sibling::div//input"),
            portOfServerInput = $x(".//label[text()='Порт']/../following-sibling::div//input"),
            loginOfServerInput = $x(".//label[text()='Имя пользователя']/../following-sibling::div//input"),
            passwordOfServerInput = $x(".//label[text()='Пароль']/../following-sibling::div//input"),
            testConnectionServerButton = $x(".//span[text()='Тестовое соединение']/.."),
            testConnectionToServerStatusText = $x(".//div[@class=\"ant-alert-message\"]"),
            saveServerButton = $x(".//button[text()='Сохранить']"),
            searchServerInput = $x(".//label[text()='Поиск ']/input"),
            nameOfServerInTable = $x(" //tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]//td[1]"),
            hostOfServerInTable = $x(" //tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]//td[2]"),
            loginOfServerInTable = $x(" //tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]//td[3]"),


    currentPasswordInput = $x(".//label[text()='Текущий пароль']/../following-sibling::div//input"),
            newPasswordInput = $x(".//label[text()='Новый пароль']/../following-sibling::div//input"),
            repeatNewPasswordInput = $x(".//label[text()='Повторите новый пароль']/../following-sibling::div//input"),
            savePasswordButton = $x(".//button[text()='Сохранить']");


    @Step("Change user`s password")
    public void editYourPassword(String Login, String OldPassword, String NewPassword) {
        click(userProfileMenu);
        click(changePasswordLink);

        val(currentPasswordInput, OldPassword);
        val(newPasswordInput, NewPassword);
        val(repeatNewPasswordInput, NewPassword);
        if (!Login.equals("CancelEditUserPassword")) click(saveButton);
    }


    @Step("Add Remote Server")
    public void addRemoteServer(String NameOfServer, String protocol, String HostOfServer, String portOfServer, String LoginOfServer, String PasswordOfServer) {
        click(userProfileMenu);
        click(basePage.settingsLink);
        click(addRemoteServerButton);
        val(nameOfServerInput, NameOfServer);
        click(protocolOfServerActivate);
        valAndSelectElement(protocolOfServerInput, protocol);
        val(hostOfServerInput, HostOfServer);
        val(portOfServerInput, portOfServer);
        val(loginOfServerInput, LoginOfServer);
        val(passwordOfServerInput, PasswordOfServer);
        click(testConnectionServerButton);
        testConnectionToServerStatusText.waitUntil(Condition.exactText(protocol + "://" + HostOfServer + ":" + portOfServer + "/manager Успешное соединение"),10000);
//        compareStringAndElementText(protocol + "://" + HostOfServer + ":" + portOfServer + "/manager Успешное соединение", testConnectionToServerStatusText);

        click(saveButton);
        val(searchServerInput, NameOfServer);
        compareStringAndElementText(NameOfServer, nameOfServerInTable);
        compareStringAndElementText(protocol + "://" + HostOfServer + ":" + portOfServer + "/manager", hostOfServerInTable);
        compareStringAndElementText(LoginOfServer, loginOfServerInTable);

//        Assert.assertEquals(NameOfServer, nameOfServerInTable.getText());
//        Assert.assertEquals(HostOfServer, hostOfServerInTable.getText());
//        Assert.assertEquals(LoginOfServer, loginOfServerInTable.getText());
    }

    @Step("Cancel Add Remote Server")
    public void cancelAddRemoteServer(String NameOfServer, String protocol, String HostOfServer, String portOfServer, String LoginOfServer, String PasswordOfServer) {
        click(userProfileMenu);
        click(basePage.settingsLink);
        click(addRemoteServerButton);
        val(nameOfServerInput, NameOfServer);
        click(protocolOfServerActivate);
        valAndSelectElement(protocolOfServerInput, protocol);
        val(hostOfServerInput, HostOfServer);
        val(portOfServerInput, portOfServer);
        val(loginOfServerInput, LoginOfServer);
        val(passwordOfServerInput, PasswordOfServer);
        closeForm();
        val(searchServerInput, NameOfServer);
        nameOfServerInTable.shouldNotBe(Condition.exist);
        hostOfServerInTable.shouldNotBe(Condition.exist);
        loginOfServerInTable.shouldNotBe(Condition.exist);
    }
}

