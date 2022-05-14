package ru.factorts.page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N2_UserSingleTest extends Base {

    @Rule
    public final TestName testName = new TestName();

    private BasePage basePage = new BasePage();
    static BasePage staticBasePage = new BasePage();
    IndexPage indexPage = new IndexPage("Empty");
    private LoginPage loginPage = new LoginPage();
    private SettingUserPage settingUserPage = new SettingUserPage();
    private CreationUserPage creationUserPage = new CreationUserPage();
    private UserPage userPage = new UserPage();
    Api api = new Api();
    static Api staticApi = new Api();
    CreationUserApi createUserAPI = new CreationUserApi();

    public N2_UserSingleTest() {
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
    }

    @After
    public void afterTest() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
//        writeLogsOfBrowser(getClassName(), testName.getMethodName());
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        if (!alternativeBrowser.equals("internet explorer")){
            String cook = staticApi.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
            staticApi.checkStatusAllModulesAPI(cook, baseUrl(), "true|true", "true|true", "false|false", "false|false", "true|true", "false|false", "false|false", "false|false");
        }

        System.out.println("Переменная для остановки контейнера: " + System.getProperty("stopContainer"));
        if (System.getProperty("stopContainer") != null && System.getProperty("stopContainer").equals("true")) {
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-2-1");
            staticBasePage.executeBashCommand("sudo docker stop fesb-test-2-2");
        }
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Администратор | OperatorIncorrectPasswordPolicy | OperatorIncorrectPassword | OperatorIncorrectPasswordPolicy",
    })
    public void incorrectPasswordPolicy(String RoleApi, String Role, String Login, String Password, String NewPassword) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        basePage.openWithLog("/manager/");
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        userPage.changePasswordPolicy(".*123");
        userPage.editAnotherPassword(Role, Login, NewPassword);
        indexPage.logout();
        loginPage.failLoginClickButton(Login, NewPassword);
        loginPage.loginClickButton(Login, Password);
        Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        api.changeSettingAutorizationAPI(Cookies, "2", ".*");
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Operator | cancelEditPasswordPolicyOperator | Operator | 123 | 2456",
    })
    public void cancelEditNumberLoginAttemptsAndPasswordPolicy(String RoleApi, String Role, String Login, String Password, String sumLogin, String passwordPolicy) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        basePage.openWithLog("/manager/");
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.cancelChangeNumberLoginAttempts(sumLogin);
        userPage.cancelChangePasswordPolicy(passwordPolicy);
        userPage.checkTheNumberLoginAndPasswordPolicy();
    }

    @Test
    @Parameters(value = {
            "MQ_ADMIN | Администратор менеджера очередей | AdministratorMQChangePasswordOfRoot | AdministratorMQPassword | root | 123",
            "DEVELOPER | Разработчик | DeveloperChangePasswordOfRoot | DeveloperPassword | root | 1234",
            "OPERATOR | Оператор | OperatorChangePasswordOfRoot | OperatorPassword | root | 12345",
            "DASHBOARD | Оператор панели мониторинга | DashboardChangePasswordOfRoot | DashboardPassword | root | 1234567",
            "ADMIN | Администратор | AdministratorChangePasswordOfRoot | AdministratorPassword | root | 12",
            "ADMIN | Root | RootChangePasswordOfRoot | RootPassword | root | 123",
    })
    public void changePasswordOfRoot(String RoleApi, String Role, String Login, String Password,
                                     String OldPasswordOfRoot, String NewPasswordOfRoot) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);

        switch (Role) {
            case ("Администратор"):
                loginPage.loginClickButton(Login, Password);
                userPage.changePasswordOfRoot(OldPasswordOfRoot, NewPasswordOfRoot);
                loginPage.logout();
                loginPage.failLoginClickButton(ConfigProperties.getTestProperty("LoginRoot"), OldPasswordOfRoot);
                loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), NewPasswordOfRoot);
                userPage.changePasswordOfRoot(NewPasswordOfRoot, OldPasswordOfRoot);
                break;

            case ("Root"):
                loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"),
                        ConfigProperties.getTestProperty("PasswordRoot"));
                userPage.changePasswordOfRoot(OldPasswordOfRoot, NewPasswordOfRoot);
                loginPage.logout();
                loginPage.failLoginClickButton(ConfigProperties.getTestProperty("LoginRoot"), OldPasswordOfRoot);
                loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), NewPasswordOfRoot);
                userPage.changePasswordOfRoot(NewPasswordOfRoot, OldPasswordOfRoot);
                break;

            case ("Администратор менеджера очередей"):
            case ("Разработчик"):
            case ("Оператор"):
            case ("Пользователь"):
            case ("Оператор панели мониторинга"):
                Cookies = api.loginAPI(Login, Password);
                createUserAPI.changePasswordOfRootAPI(Cookies, OldPasswordOfRoot, NewPasswordOfRoot);
                break;

            default:
                throw new AssertionError("Пропущено изменение пароля");
        }
    }

    @Test
    public void cancelChangePasswordOfRoot() {
        String oldPasswordOfRoot = ConfigProperties.getTestProperty("PasswordRoot");
        String newPasswordOfRoot = "NewPasswordOfRoot";

        basePage.openWithLog("/manager/");
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        userPage.cancelChangePasswordOfRoot(oldPasswordOfRoot, newPasswordOfRoot);
        loginPage.failLoginClickButton(ConfigProperties.getTestProperty("LoginRoot"), newPasswordOfRoot);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), oldPasswordOfRoot);
    }
}