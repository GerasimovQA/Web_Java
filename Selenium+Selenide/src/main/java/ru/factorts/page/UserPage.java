package ru.factorts.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class UserPage extends BasePage {

    CommonComponents commonComponents = new CommonComponents();
    LoginPage loginPage = new LoginPage();


    private static String roleUser = "//div[@role=\"option\"][text()='%s']";
    private static String numberLoginAttemptsOld = null;
    private static String passwordPolicyOld = null;

    public SelenideElement
            localUsersTab = $x(".//*[text()='Локальные пользователи']"),
            addUserButton = $$x(".//span[text()=' Добавить']/..").findBy(visible),
            changePasswordOfRootButton = $x(".//span[text()=' Изменить root пароль']/.."),
            searchInput = $$x(".//label[text()='Поиск ']/..//input").findBy(visible),
            changePasswordLink = $x(".//div[text()=\" Изменить пароль\"]"),
            lockLink = $x(".//div[text()=\" Заблокировать\"]"),
            lockButton = $x(".//span[text()=\"Заблокировать\"]/.."),
            lockCancelButton = $x(".//span[text()=\"Отмена\"]/.."),
            unlockLink = $x(".//div[text()=\" Разблокировать\"]"),
            unlockButton = $x(".//span[text()=\"Разблокировать\"]/.."),
            editRoleLink = $x(".//div[text()=' Изменить роли']"),
            deleteLink = $x(".//div[text()=\" Удалить\"]"),
            deleteButton = $x(".//button[span[text()=\"Удалить\"]]"),
            generalSettingTab = $x(".//span[text()=' Настроить политики']/.."),
            numberLoginAttemptsInput = $x(".//label[text()='Количество попыток входа перед блокировкой']/../following-sibling::div//input"),
            passwordPolicyInput = $x(".//label[text()='Парольная политика']/../following-sibling::div//input"),
            saveButton = $$x(".//span[text()='Сохранить']/..").findBy(visible),
            savePasswordPolicyButton = $$x(".//button[text()='Сохранить']").findBy(visible),
            saveRootPasswordButton = $$x(".//button[text()='Сохранить']").findBy(visible),
            addButton = $$x(".//button[text()='Добавить']").findBy(visible),
            roleSelect = $x(".//label[text()='Роль']/../following-sibling::div"),
            roleInput = $x(".//label[text()='Роль']/../following-sibling::div//input"),
            domainsSelect = $x(".//label[text()='Домены']/../following-sibling::div//div"),
            domainsInput = $x(".//label[text()='Домены']/../following-sibling::div//input"),
            screensActivate = $x(".//label[text()='Экраны']/../following-sibling::div//div"),
            screensInput = $x(".//label[text()='Экраны']/../following-sibling::div//input"),
            afterSearchRoleCell = $x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[2]"),
            incorrectPasswordPolicyNotification = $x(".//div[text()=\"Ошибка на стороне сервера: Пароль не подходит под политику паролей\"]"),
            changePasswordButton = $x(".//span[text()='Изменить пароль']/.."),
            PasswordInput = $x(".//label[text()='Пароль']/../following-sibling::div//input"),
            repeatPasswordInput = $x(".//label[text()='Повторите пароль']/../following-sibling::div//input"),
            currentPasswordInput = $x(".//label[text()='Текущий пароль']/../following-sibling::div//input"),
            newPasswordInput = $x(".//label[text()='Новый пароль']/../following-sibling::div//input"),
            repeatNewPasswordInput = $x(".//label[text()='Повторите новый пароль']/../following-sibling::div//input");

    public SelenideElement
            ldaplUsersTab = $x(".//*[text()='LDAP пользователи']"),
            jmxUsersTab = $x(".//*[text()='JMX пользователи']");


    ElementsCollection afterSearchTableRows = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]").filterBy(visible),
            afterSearchLoginCell = $$x(".//tr[contains(@class,\"ant-table-row ant-table-row-level-0 table-row\")]/td[1]").filterBy(visible);


    @Step("Go to  Users and group page")
    public void usersAndGroupPage() {
        click(usersAndGroupTab);
    }

    @Step("Searching User {0}")
    public void search(String UserName) {
        val(searchInput, UserName);
        afterSearchTableRows.shouldHave(size(1));
        afterSearchLoginCell.get(0).shouldBe(text(UserName));
    }

    @Step("Searching User {0}")
    public void failedSsearchLocalUser(String UserName) {
        click(usersAndGroupTab);
        click(localUsersTab);
        val(searchInput, UserName);
        afterSearchTableRows.shouldHave(size(0));
    }

    @Step("Searching User {0}")
    public void failedSsearchJmxUser(String UserName) {
        click(usersAndGroupTab);
        click(jmxUsersTab);
        val(searchInput, UserName);
        afterSearchTableRows.shouldHave(size(0));
    }

    @Step("Searching a deleted user")
    public void searchDeletedUser(String UserName) {
        if (UserName.equals("UserCancelDelete")) {
            val(searchInput, UserName);
            afterSearchTableRows.shouldHave(size(1));
        } else {
            val(searchInput, UserName);
            afterSearchTableRows.shouldHave(size(0));
        }
    }

    @Step("Edit user role")
    public void editRole(String Login, String OldRole, String NewRole, String ShortRole) {
        contextClick(afterSearchTableRows.get(0));
        click(editRoleLink);
        click(roleSelect);
        valAndSelectElement(roleInput, NewRole);
//        click($x(String.format(roleUser, NewRole)));
        switch (Login) {
            case ("UserEditRole"):
                click(saveButton);
                afterSearchTableRows.get(0).shouldHave(text(NewRole));
                break;

            case ("UserCancelEditRole"):
                closeForm();
                afterSearchTableRows.get(0).shouldHave(text(OldRole));
                break;

            default:
                throw new AssertionError("Пропущена проверка роли и это плохо");
        }
    }

    @Step("Change the number of login attempts")
    public void changeNumberLoginAttempts(String Num) {
        click(generalSettingTab);
        val(numberLoginAttemptsInput, Num);
        click(saveButton);
    }

    @Step("Cancel change the number of login attempts")
    public void cancelChangeNumberLoginAttempts(String Num) {
        click(generalSettingTab);
        numberLoginAttemptsOld = numberLoginAttemptsInput.getValue();
        val(numberLoginAttemptsInput, Num);
        closeForm();
    }

    @Step("Check the number of login and password policy")
    public void checkTheNumberLoginAndPasswordPolicy() {
        click(generalSettingTab);
        Assert.assertEquals(numberLoginAttemptsOld, numberLoginAttemptsInput.getValue());
        Assert.assertEquals(passwordPolicyOld, passwordPolicyInput.getValue());
    }

    @Step("Locking the user")
    public void lockUser(String Login) {
        afterSearchTableRows.get(0).shouldHave(text("Активен"));
        contextClick(afterSearchTableRows.get(0));
        click(lockLink);
        switch (Login) {
            case ("UserManualLock"):
                click(lockButton);
                afterSearchTableRows.get(0).shouldHave(text("Заблокирован"));
                break;

            case ("UserCancelManualLock"):
                click(lockCancelButton);
                afterSearchTableRows.get(0).shouldHave(text("Активен"));
                break;

            default:
                throw new AssertionError("Пропущен шаг блокировки/разблокировки и это плохо");
        }
    }


    @Step("Unlocking the user")
    public void unlockingUser(String Login) {
        contextClick(afterSearchTableRows.get(0));
        click(unlockLink);

        switch (Login) {
            case ("failAutorizationOperator"):
                click(unlockButton);
                break;

            case ("cancelUnlockUser"):
                closeForm();
                break;

            default:
                throw new AssertionError("Пропущена блокировка/разблокировка юзера и это плохо");
        }
    }

    @Step("Deleting the user")
    public void deleteUser(String Login) {
        if (Login.equals("UserCancelDelete")) {
            contextClick(afterSearchTableRows.get(0));
            click(deleteLink);
            closeForm();
        } else {
            contextClick(afterSearchTableRows.get(0));
            click(deleteLink);
            click(deleteButton);
        }
    }

    @Step("Change anoter`s password")
    public void editAnotherPassword(String Role, String Login, String NewPassword) {
        switch (Role) {
            case ("Администратор"):
                settingsPage();
                usersAndGroupPage();
                click(localUsersTab);
                search(Login);
                contextClick(afterSearchTableRows.get(0));
                click(changePasswordLink);
                val(PasswordInput, NewPassword);
                val(repeatPasswordInput, NewPassword);

                switch (Login) {
                    case ("AnotherUs3"):
                        click(changePasswordButton);
                        break;

                    case ("AnotherUs4"):
                        closeForm();
                        break;

                    case ("OperatorIncorrectPasswordPolicy"):
                        click(changePasswordButton);
                        elementShouldBeVisible(incorrectPasswordPolicyNotification);
                        sleep(5000);
                        elementShouldBeVisible(changePasswordButton);
                        closeForm();
                        break;
                    default:
                        throw new AssertionError("Missing password saving and this is bad");
                }
                break;

            case ("Администратор менеджера очередей"):
            case ("Оператор"):
                click(settingsPageTab);
                sleep(5000);
                elementShouldNotBeVisible(usersAndGroupTab);
                break;

            case ("Разработчик"):
            case ("Пользователь"):
            case ("Оператор панели мониторинга"):
                sleep(5000);
                elementShouldNotBeVisible(settingsPageTab);
                break;

            default:
                throw new AssertionError("Пропущено редактирование пароля");
        }
    }

    public void changePasswordPolicy(String Text) {
        settingsPage();
        usersAndGroupPage();
        click(generalSettingTab);
        val(passwordPolicyInput, Text);
        click(saveButton);
    }

    public void cancelChangePasswordPolicy(String Text) {
        settingsPage();
        usersAndGroupPage();
        click(generalSettingTab);
        passwordPolicyOld = passwordPolicyInput.getValue();
        val(passwordPolicyInput, Text);
        closeForm();
    }

    public void changePasswordOfRoot(String OldPassword, String NewPassword) {
        settingsPage();
        usersAndGroupPage();
        click(changePasswordOfRootButton);
        val(currentPasswordInput, OldPassword);
        val(newPasswordInput, NewPassword);
        val(repeatNewPasswordInput, NewPassword);
        click(saveButton);
        sleep(1000);
//        click(commonComponents.reloadButton);
//        loginPage.loginInput.waitUntil(visible, 300000);
    }

    public void cancelChangePasswordOfRoot(String OldPassword, String NewPassword) {
        settingsPage();
        usersAndGroupPage();
        click(changePasswordOfRootButton);
        val(currentPasswordInput, OldPassword);
        val(newPasswordInput, NewPassword);
        val(repeatNewPasswordInput, NewPassword);
        closeForm();
        sleep(10000);
        commonComponents.reloadButton.shouldNotBe(visible);
        refresh();
        logout();
    }

    public void searchLocalUser(String Login) {
        settingsPage();
        usersAndGroupPage();
        search(Login);
    }

    public void searchJmxUser(String Login) {
        settingsPage();
        usersAndGroupPage();
        click(jmxUsersTab);
        search(Login);
    }
}
