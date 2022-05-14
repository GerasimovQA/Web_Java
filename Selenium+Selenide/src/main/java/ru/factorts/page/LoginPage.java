package ru.factorts.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    public SelenideElement loginInput = $x(".//input[@name=\"username\"]");
    public SelenideElement passwordInput = $x(".//input[@name=\"password\"]");
    public SelenideElement loginButton = $x(".//button[@type='submit']");
    public SelenideElement failLoginOrPasswordInfo = $x(".//span[text()='Неверный логин или пароль.']");
    public SelenideElement userBlockInfo = $x(".//span[text()='Пользователь заблокирован.']");

    @Step("Login, press enter")
    public void login(String username, String password) {
        val(loginInput, username);
        val(passwordInput, password);
    }

    @Step("Login, click to button - {0}/{1}")
    public void loginClickButton(String Login, String Password) {
        val(loginInput, Login);
        val(passwordInput, Password);
        click(loginButton);
        userProfileMenu.waitUntil(text(Login), 10000);
    }

    @Step("Fail login, click to button")
    public void failLoginWithoutCheck(String Login, String Password) {
        val(loginInput, Login);
        val(passwordInput, Password);
        click(loginButton);
    }

    @Step("Fail login, click to button")
    public void failLoginClickButton(String Login, String Password) {
        failLoginWithoutCheck(Login, Password);
        elementShouldBeVisible(failLoginOrPasswordInfo);
    }

    @Step("Fail login, click to button")
    public void loginLockedUserClickButton(String Login, String Password) {
        val(loginInput, Login);
        val(passwordInput, Password);
        click(loginButton);
        elementShouldBeVisible(userBlockInfo);
    }

    @Step("Loop failed logins")
    public void loopFailedLogins(String Login, String Num) {
        for (int i = 0; i < Integer.parseInt(Num) + 1; i++) {
            failLoginWithoutCheck(Login, "Fail");
            if (i <= Integer.parseInt(Num) - 1) {
                elementShouldBeVisible(failLoginOrPasswordInfo);
            } else {
                elementShouldBeVisible(userBlockInfo);
            }
        }
    }
}
