package ru.factorts.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import utils.ConfigProperties;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ConfigurationServer extends BasePage {
    String urlDownload = "file:///home/selenium/Downloads/";
    static String selectAdress = "//li[text()='%s']";
    static String selectServer = "//li[text()='%s']";
    static String mistakeWrongPasswordOfCertificate = "//div[text()='Ошибка на стороне сервера: Не удалось установить контейнер SSL (%s): Keystore was tampered with, or password was incorrect']";
    static String mistakeWrongCertificate = "//div[text()='Ошибка на стороне сервера: Не удалось установить контейнер SSL (%s): Расширение файла контейнера должно быть одним из: jks,p12,pfx']";

    SmokeApi workProtocolPage = new SmokeApi();
    SOPSPage sopsPage;
    LoginPage loginPage = new LoginPage();
    QueueManagerPage queueManagerPage;
    SettingsPage settingsPage;
    CommonComponents commonComponents = new CommonComponents();
    BasePage basePage = new BasePage();

    SelenideElement currentServerActivate = $x(".//div[text()='Отображать данные с сервера']/following-sibling::div"),
            currentServerInput = $x(".//div[text()='Отображать данные с сервера']/following-sibling::div//input"),
            currentServerValue = $x(".//div[text()='Отображать данные с сервера']/following-sibling::div//span[@class=\"ant-select-selection-item\"]"),
            saveCurrentServerButton = $x(".//div[text()='Отображать данные с сервера']/..//span[text()='Сохранить']/.."),

    uploadCertificateInput = $x(".//span[text()='Загрузить контейнер для SSL']/../input"),
            currentCerificate = $x(".//input[@name=\"sslPath\"]"),
            passwordStorageKeysInput = $x(".//label[text()='Пароль от хранилища ключей']/../following-sibling::div//input"),
            passwordManagerKeysInput = $x(".//label[text()='Пароль от менеджера ключей']/../following-sibling::div//input"),
            savePasswordsButton = $x(".//div[text()='Ввод паролей']/../..//span[text()='Сохранить']/.."),


    enableHttpsCheckBox = $x(".//span[text()='Включить HTTPS']/preceding-sibling::span"),
            statusHttpsCheckBox = $x(".//span[text()='Включить HTTPS']/preceding-sibling::span/input"),
            adressHttpsList = $x(".//label[text()='Адрес HTTPS']/../following-sibling::div"),
            adressHttpsInput = $x(".//label[text()='Адрес HTTPS']/../following-sibling::div//input"),
            portHttpsInput = $x(".//label[text()='Порт HTTPS']/../following-sibling::div//input"),

    enableHttpCheckBox = $x(".//span[text()='Включить HTTP']/preceding-sibling::span"),
            statusHttpCheckBox = $x(".//span[text()='Включить HTTP']/preceding-sibling::span/input"),
            adressHttpList = $x(".//label[text()='Адрес HTTP']/../following-sibling::div"),
            adressHttpSelect = $x(".//label[text()='Адрес HTTP']/../following-sibling::div//input"),
            portHttpInput = $x(".//label[text()='Порт HTTP']/../following-sibling::div//input"),

    enableAutorizationJmxCheckBox = $x(".//span[text()='Включить авторизацию JMX']/../span[contains(@class,'ant')]"),
            adressJmxList = $x(".//label[text()='Адрес JMX']/../following-sibling::div"),
            adressJmxInput = $x(".//label[text()='Адрес JMX']/../following-sibling::div//input"),
            portJmxInput = $x(".//label[text()='Порт JMX']/../following-sibling::div//input"),

    enableSshConnectionCheckBox = $x(".//span[text()='Включить доступ по SSH']/../span[contains(@class,'ant')]"),
            adressSshActivate = $x(".//label[text()='Адрес SSH']/../following-sibling::div"),
            adressSshInput = $x(".//label[text()='Адрес SSH']/../following-sibling::div//input"),
            portSshInput = $x(".//label[text()='Порт SSH']/../following-sibling::div//input"),

    timeLifeSessionInput = $x(".//label[text()='Время жизни сессии (с)']/../following-sibling::div//input"),
            saveSettingServerButton = $x(".//div[text()='Настройки веб-сервера']/..//span[text()='Сохранить']/.."),

    maxMemoryInput = $x(".//label[text()='Максимум памяти (МБ)']/../following-sibling::div//input"),
            usedMemoryInput = $x("//div[text()='Память']/../../following-sibling::div//div[text()='Используется сейчас']/following-sibling::div"),
            saveLaunchEnvironmentButton = $x(".//div[text()='Среда запуска']/..//span[text()='Сохранить']/..");


    public enum Enable {Yes, No}

    public enum ReqwestResult {Pass, Fail}

    @Step("Set Time Life Session")
    public void setTimeLifeSession(String time) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        val(timeLifeSessionInput, time);
        click(saveSettingServerButton);
        click(commonComponents.reloadButton);
        loginPage.loginInput.waitUntil(visible, 300000);
    }

    @Step("Check Life Session")
    public void checkLifeSession(String time) {
        switch (time) {
            case ("20"):
                sleep(Integer.parseInt(time) * 1500);
                open("/");
                loginPage.loginInput.waitUntil(visible, 5000);
                break;
            case ("-1"):
                sleep(15000);
                open("/");
                sleep(15000);
                elementShouldNotBeVisible(loginPage.loginInput);
                break;
            default:
                throw new AssertionError("Пропущена проверка жизни сессии");
        }
    }

    @Step("Upload Valid Certificate")
    public void uploadCertificate(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        uploadCertificateInput.sendKeys("/share/Cerificates/" + certificate);
        val(passwordStorageKeysInput, passwordStorageKeys);
        val(passwordManagerKeysInput, passwordManagerKeys);
        click(savePasswordsButton);
    }

    @Step("Cancel Upload Valid Certificate")
    public void cancelUploadCertificate(String certificate, String passwordStorageKeys, String passwordManagerKeys) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        uploadCertificateInput.sendKeys("/share/Cerificates/" + certificate);
        val(passwordStorageKeysInput, passwordStorageKeys);
        val(passwordManagerKeysInput, passwordManagerKeys);
        closeForm();
    }

    @Step
    public void checkCertificateWithNotValidPasswordsNotUploaded(String certificateName) {
//        settingsPage = new SettingsPage();
//        click(settingsPageTab.configurationServerTab);
        elementShouldBeVisible($x(String.format(mistakeWrongPasswordOfCertificate, certificateName)));
//        $x(String.format(mistakeWrongPasswordOfCertificate, certificateName)).waitUntil(visible,10000);
//        Assert.assertTrue($x(String.format(mistakeWrongPasswordOfCertificate, certificateName)).isDisplayed());
        refresh();
        settingsPage.configurationServerTab.waitUntil(visible, 10000);
        sleep(10000);
        elementShouldNotBeVisible(commonComponents.reloadButton);
//        Assert.assertFalse(commonComponents.reloadButton.isDisplayed());
    }

    @Step
    public void checkNonValideCertificateWithNotValidPasswordsNotUploaded(String certificateName) {
        $x(String.format(mistakeWrongCertificate, certificateName)).waitUntil(visible,10000);
//        Assert.assertTrue($x(String.format(mistakeWrongCertificate, certificateName)).isDisplayed());
        refresh();
        settingsPage.configurationServerTab.waitUntil(visible, 10000);
        sleep(10000);
        Assert.assertFalse(commonComponents.reloadButton.isDisplayed());
    }

    @Step
    public void checkNonUploadCertificate(String certificateName) {
//        settingsPage = new SettingsPage();
//        click(settingsPageTab.configurationServerTab);
        Assert.assertFalse($x(String.format(mistakeWrongPasswordOfCertificate, certificateName)).isDisplayed());
        Assert.assertFalse($x(String.format(mistakeWrongCertificate, certificateName)).isDisplayed());
        refresh();
        settingsPage.configurationServerTab.waitUntil(visible, 10000);
        sleep(10000);
        Assert.assertFalse(commonComponents.reloadButton.isDisplayed());
    }

    @Step("Upload Valid Certificate")
    public void checkCertificateName(String name) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        Assert.assertEquals(name, currentCerificate.getValue());
    }

    @Step
    public void enableAdressHttps(Enable enable) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        if (enable.equals(Enable.Yes) && !enableHttpsCheckBox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(enableHttpsCheckBox);
        }
        if (enable.equals(Enable.No) && enableHttpsCheckBox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(enableHttpsCheckBox);
        }
    }

    @Step
    public void enableAdressHttp(Enable enable) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        if (enable.equals(Enable.Yes) && !enableHttpCheckBox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(enableHttpCheckBox);
        }
        if (enable.equals(Enable.No) && enableHttpCheckBox.getAttribute("class").equals("ant-checkbox ant-checkbox-checked")) {
            click(enableHttpCheckBox);
        }
    }

    @Step
    public void editHttps(String ip, String port) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        click(adressHttpsList);
        valAndSelectElement(adressHttpsInput, ip);
//        click($x(String.format(selectAdress, ip)));
        val(portHttpsInput, port);
        click(saveSettingServerButton);
    }

    @Step
    public void editHttp(String ip, String port) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        click(adressHttpList);
        valAndSelectElement(adressHttpSelect, ip);
//        click($x(String.format(selectAdress, ip)));
        val(portHttpInput, port);
        click(saveSettingServerButton);
    }

    @Step
    public void switchAutorizationJmx(Enable enable) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        if (enable.equals(Enable.Yes) && !enableAutorizationJmxCheckBox.isSelected()) {
            click(enableAutorizationJmxCheckBox);
        }
        if (enable.equals(Enable.No) && enableAutorizationJmxCheckBox.isSelected()) {
            click(enableAutorizationJmxCheckBox);
        }
        click(saveSettingServerButton);
    }

    @Step
    public void editJmx(String ip, String port) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        click(adressJmxList);
        valAndSelectElement(adressJmxInput, ip);
//        click($x(String.format(selectAdress, ip)));
        val(portJmxInput, port);
        click(saveSettingServerButton);
    }

    @Step
    public void editWrapper(String sizeMemmory) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        val(maxMemoryInput, sizeMemmory);
        click(saveLaunchEnvironmentButton);
    }

    @Step
    public void checkSizeMemmoryWrapperInConfig(String sizeMemmory) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        Assert.assertEquals(sizeMemmory, maxMemoryInput.getValue());
    }

    @Step
    public void restartWrapperInDocker(String com) {
//        Загрузить скрипт из файла
//        String com = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" +
//                File.separator + "java" + File.separator + "resources" + File.separator + "123.sh";
//        Загрузить скрипт из строки
//        String com = "sudo docker restart stand_fesb_test_4_1";

        basePage.sout("Выполняем скрипт");
        executeBashCommand(com);
    }


    public void editCurrentServer(String nameServer) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        click(currentServerActivate);
        valAndSelectElement(currentServerInput, nameServer);
//        click($x(String.format(selectServer, nameServer)));
        click(saveCurrentServerButton);
    }

    public void valueCurrentServerEquals(String nameServer) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        Assert.assertEquals(nameServer, currentServerValue.getText());
    }

    public void valueCurrentServerNotEquals(String nameServer) {
        settingsPage = new SettingsPage();
        click(settingsPage.configurationServerTab);
        Assert.assertNotEquals(nameServer, currentServerValue.getText());
    }

    public void jmxRead(String host, int port, String login, String password, ReqwestResult reqResult) throws Exception {
        String rout = "route-39ba21dc-1a02-449f-902a-4859292d1fb3";
        String url = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        System.out.println(url);
        JMXServiceURL serviceUrl = new JMXServiceURL(url);
        AttributeList attrs = null;

        Map<String, String[]> env = new HashMap<>();
        String[] credentials = {login, password};
        env.put(JMXConnector.CREDENTIALS, credentials);
        JMXConnector jmxConnector;
        basePage.sout("\n\n\n Проверка роли readonly для " + login + " / " + password);
        try {
            jmxConnector = JMXConnectorFactory.connect(serviceUrl, env);
        } catch (IOException e) {
            System.out.println(e);
            throw new AssertionError("Соединение не установлено");
        }

        try {
            MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();
            Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
            System.out.println("Бины: " + beanSet);

            Set<ObjectName> objectInstanceNames = mbeanConn.queryNames(new ObjectName("org.apache.camel:context=examples,type=routes,name=\"" + rout + "\""), null);
            for (ObjectName on : objectInstanceNames) {
                attrs = mbeanConn.getAttributes(on, new String[]{"ExchangesCompleted", "ExchangesFailed"});
                for (Attribute attribute : attrs.asList()) {
                    basePage.sout("Вывод: " + attribute.getName() + ": " + attribute.getValue());
                }
            }
        } finally {
            if (reqResult.equals(ReqwestResult.Pass)) {
                Assert.assertNotNull("Пришел пустой ответ и это полохо1", reqResult);
                Assert.assertNotNull("Пришел пустой ответ и это полохо2", attrs);
                sout("То, что могло бы быть null - " + attrs.toString());
                Assert.assertEquals("Нет прав на чтение", "[ExchangesCompleted = 0, ExchangesFailed = 0]", attrs.toString());
                basePage.sout("Доступ к чтению имеется");
            }
            if (reqResult.equals(ReqwestResult.Fail)) {
                try {
                    Assert.assertNotNull("Пришел пустой ответ и это полохо", reqResult);
                } catch (AssertionError e) {
                    throw new AssertionError("Пользователь не имеющий прав получил ответ");
//                    basePage.sout("Пришел пустой ответ и это хорошо - Доступ к чтению отсутствует");
                }
            }
            jmxConnector.close();
        }
    }

    public void jmxWrite(String host, int port, String login, String password, ReqwestResult reqResult) throws Exception {
        String rout = "route-39ba21dc-1a02-449f-902a-4859292d1fb3";
        String url = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        JMXServiceURL serviceUrl = new JMXServiceURL(url);
        Object result = null;

        Map<String, String[]> env = new HashMap<>();
        String[] credentials = {login, password};
        env.put(JMXConnector.CREDENTIALS, credentials);
        JMXConnector jmxConnector;
        basePage.sout("\n\n\n Проверка роли readwrite для " + login + " / " + password);
        try {
            jmxConnector = JMXConnectorFactory.connect(serviceUrl, env);
        } catch (IOException e) {
            System.out.println(e);
            throw new AssertionError("Соединение не установлено");
        }

        try {
            MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();
            Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
            System.out.println(beanSet);

            ObjectName routeQuery = new ObjectName("org.apache.camel:context=examples,type=routes,name=\"" + rout + "\"");
            Set<ObjectName> objectInstanceNames = mbeanConn.queryNames(routeQuery, null);
            for (ObjectName on : objectInstanceNames) {
                AttributeList attrs = mbeanConn.getAttributes(on, new String[]{"ExchangesCompleted", "ExchangesFailed"});
                System.out.println(attrs);
                result = mbeanConn.invoke(on, "getExchangesTotal", null, null);
                basePage.sout("Результат: " + result);
            }
        } finally {
            if (reqResult.equals(ReqwestResult.Pass)) {
                Assert.assertNotNull("Пришел пустой ответ", result);
                Assert.assertEquals("Нет прав на запись", "0", result.toString());
                basePage.sout("Доступ к записи имеется");
            }
            if (reqResult.equals(ReqwestResult.Fail)) {
                try {
                    Assert.assertNotNull("Пришел пустой ответ и это полохо", reqResult);
                } catch (AssertionError e) {
                    throw new AssertionError("Пользователь не имеющий прав получил ответ");
//                    basePage.sout("Пришел пустой ответ и это хорошо - Доступ к записи отсутствует");
                }
            }
            jmxConnector.close();
        }
    }

    @Step("Check certificate not exist")
    public void setupSshconnection(String adressSsh, String portSsh) {
        click(settingsPageTab);
        click(configurationServerTab);
        click(enableSshConnectionCheckBox);
        click(adressSshActivate);
        valAndSelectElement(adressSshInput, adressSsh);
        val(portSshInput, portSsh);
        click(saveSettingServerButton);
        click(commonComponents.reloadButton);
        loginPage.loginInput.waitUntil(Condition.visible, 300000);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
    }
}