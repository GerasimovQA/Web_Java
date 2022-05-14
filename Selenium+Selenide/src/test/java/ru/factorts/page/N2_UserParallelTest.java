package ru.factorts.page;

import com.browserup.bup.client.ClientUtil;
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

import static com.codeborne.selenide.Selenide.open;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnitParamsRunner.class)
public class N2_UserParallelTest extends Base {

    @Rule
    public final TestName testName = new TestName();

    private BasePage basePage = new BasePage();
    private static BasePage staticBasePage = new BasePage();
    IndexPage indexPage;
    private LoginPage loginPage = new LoginPage();
    private SettingUserPage settingUserPage = new SettingUserPage();
    private CreationUserPage creationUserPage = new CreationUserPage();
    private UserPage userPage = new UserPage();
    static Api staticAPI = new Api();
    static Api api = new Api();
    CreationUserApi createUserAPI = new CreationUserApi();
    CommonComponents commonComponents = new CommonComponents();
    DashboardPage dashboardPage = new DashboardPage();

    @BeforeClass
    public static void beforClass() {
        staticAPI.startAllModules(baseUrl());
    }

    @Before
    public void setUpQueue() {
        Configuration.browserCapabilities.setCapability("name", testName.getMethodName().split("\\|")[0].replaceAll(" ", "_"));
        basePage.openWithLog("/manager/");
        System.out.println("Начало теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        logs = WebDriverRunner.getWebDriver().manage().logs();
        Configuration.proxyHost = ClientUtil.getConnectableAddress().getHostAddress();
    }

    @After
    public void afterTest() {
        basePage.sout("Конец теста: " + testName.getMethodName() + " - " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        if (!browser().equals("internet explorer")) {
            writeLogsOfBrowser(getClassName(), testName.getMethodName());
        }
        Selenide.closeWebDriver();
    }

    @AfterClass
    public static void afterClass() {
        try {
            staticBasePage.sout("Конец класса: " + ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId().toString());
        } catch (IllegalStateException | InvalidSelectorException e) {
            System.out.println("Браузер не запущен");
        }
    }


    @Test
    @Parameters(value = {
            "Администратор менеджера очередей | | AdministratorMQ | AdministratorMQPassword",
            "Разработчик | без доменов | Developer | DeveloperPassword",
            "Разработчик | Примеры | Developer | DeveloperPassword",
            "Разработчик | Примеры.Main broker | Developer | DeveloperPassword",
            "Оператор | | Operator1 | OperatorPassword",
            "Оператор панели мониторинга | без экранов | Dashboard | DashboardPassword",
            "Оператор панели мониторинга | Примеры | Dashboard | DashboardPassword",
            "Оператор панели мониторинга | Примеры.ВторойЭкран | Dashboard | DashboardPassword",
            "Администратор | | Administrator | AdministratorPassword",
    })
    public void createUser(String role, String condition, String blankLogin, String password) {
        String login = commonComponents.createIndividualName(blankLogin);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        if (condition.equals("Примеры.ВторойЭкран")) dashboardPage.createDashboard(condition.split("\\.")[1]);
        basePage.settingsPage();
        userPage.usersAndGroupPage();

        switch (role) {
            case "Разработчик":
            case "Оператор панели мониторинга":
                creationUserPage.createUser(login, password, role, condition);
                break;
            case "Администратор менеджера очередей":
            case "Оператор":
            case "Администратор":
                creationUserPage.createUser(login, password, role);
                break;
            default:
                throw new AssertionError("Пропущено создание юзера");
        }
        indexPage = new IndexPage("Empty");
        indexPage.logout();
        loginPage.loginClickButton(login, password);
        creationUserPage.chekUserPermissions(role, condition);
        creationUserPage.checkRoleUsers(role, login);
    }

    @Test
    @Parameters(value = {
            "Оператор | CancelCreateUser | CancelCreateUserPassword",
    })
    public void cancelCreateUser(String Role, String Login, String Password) {
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        creationUserPage.createUser(Login, Password, Role);
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.failLoginClickButton(Login, Password);
    }

    @Test
    @Parameters(value = {
            "ADMIN | Администратор | Administrator | AdministratorPassword | NewAdministratorPassword",
            "MQ_ADMIN | Администратор менеджера очередей | AdministratorMQ | AdministratorMQPassword | NewAdministratorMQPassword",
            "DEVELOPER | Разработчик | Developer | DeveloperPassword | NewDeveloperPassword",
            "OPERATOR | Оператор | Operator | OperatorPassword | NewOperatorPassword",
            "DASHBOARD | Оператор панели мониторинга | Dashboard | DashboardPassword | NewDashboardPassword",
    })
    public void editYourPassword(String RoleApi, String Role, String blankLogin, String OldPassword, String NewPassword) {
        String Login = commonComponents.createIndividualName(blankLogin);
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, OldPassword);
        loginPage.loginClickButton(Login, OldPassword);
        settingUserPage.editYourPassword(Login, OldPassword, NewPassword);

        if (Role.equals("Оператор панели мониторинга")) {
            indexPage = new IndexPage("Empty");
        } else {
            indexPage = new IndexPage();
        }

        indexPage.logout();
        loginPage.loginClickButton(Login, NewPassword);
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Оператор | CancelEditUserPassword | CancelEditUserPassword | NewCancelEditUserPassword",
    })
    public void cancelEditYourPassword(String RoleApi, String Role, String Login, String OldPassword, String NewPassword) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, OldPassword);
        loginPage.loginClickButton(Login, OldPassword);
        settingUserPage.editYourPassword(Login, OldPassword, NewPassword);
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.failLoginClickButton(Login, NewPassword);
    }

    @Test
    @Parameters(value = {
            "ADMIN | Администратор | Administrator | AdministratorPassword",
            "MQ_ADMIN | Администратор менеджера очередей | AdministratorMQ | AdministratorMQPassword",
            "DEVELOPER | Разработчик | Developer | DeveloperPassword",
            "OPERATOR | Оператор | Operator | OperatorPassword",
            "DASHBOARD | Оператор панели мониторинга | Dashboard | DashboardPassword",
    })
    public void editAnotherPassword(String RoleApi, String Role, String blankLogin, String Password) {
        String login = commonComponents.createIndividualName(blankLogin);
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, login, Password);

        if (Role.equals("Администратор")) {
            createUserAPI.createUserAPI(Cookies, "OPERATOR", "AnotherUs3", "AnotherUserPassword");
        }

        loginPage.loginClickButton(login, Password);
        userPage.editAnotherPassword(Role, "AnotherUs3", "NewAnotherUserPassword");
        if (!Role.equals("Администратор")) {
            return;
        }

        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.loginClickButton("AnotherUs3", "NewAnotherUserPassword");
    }

    @Test
    @Parameters(value = {
            "ADMIN | Администратор | cancelEditAnotherPassword | cancelEditAnotherPassword",
    })
    public void cancelEditAnotherPassword(String RoleApi, String Role, String Login, String Password) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, "OPERATOR", "AnotherUs4", "AnotherUserPassword");
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        userPage.editAnotherPassword(Role, "AnotherUs4", "NewAnotherUserPassword");
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.failLoginClickButton("AnotherUs4", "NewAnotherUserPassword");
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Operator | failAutorizationOperator | Operator | 5 | 2",
    })
    public void failAutorization(String RoleApi, String Role, String Login, String Password, String Num1, String Num2) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.changeNumberLoginAttempts(Num1);
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.loopFailedLogins(Login, Num1);

        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        creationUserPage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.changeNumberLoginAttempts(Num2);
        basePage.click(userPage.localUsersTab);
        userPage.search(Login);
        userPage.unlockingUser(Login);
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.loopFailedLogins(Login, Num2);
    }


    @Test
    @Parameters(value = {
            "OPERATOR | Operator | cancelUnlockUser | UserPassword",
    })
    public void cancelUnlockUser(String RoleApi, String Role, String Login, String Password) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        createUserAPI.lockUserAPI(Cookies, Login);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.search(Login);
        userPage.unlockingUser(Login);
        indexPage = new IndexPage();
        indexPage.logout();
        loginPage.loginLockedUserClickButton(Login, Password);
        basePage.elementShouldBeVisible(loginPage.userBlockInfo);
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Оператор | usertest | UserPassword | Оператор | USERTEST | UserPassword",
    })
    public void createUserWithNonUniqueLogin(String RoleApi, String Role1, String Login1, String Password1, String Role2, String Login2, String Password2) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login1, Password1);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        creationUserPage.createUser(Login2, Password2, Role2);
        userPage.search(Login1);
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Оператор | UserEditRole | UserPassword | Оператор панели мониторинга | DASHBOARD",
            "OPERATOR | Оператор | UserCancelEditRole | UserPassword | Оператор панели мониторинга | USER",
    })
    public void editRole(String RoleApi, String Role1, String Login, String Password, String NewRole, String ShortRole) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.search(Login);
        userPage.editRole(Login, Role1, NewRole, ShortRole);
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Оператор | UserManualLock | UserPassword",
            "OPERATOR | Оператор | UserCancelManualLock | UserPassword",
    })
    public void manualLock(String RoleApi, String Role1, String Login, String Password) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.search(Login);
        userPage.lockUser(Login);
        indexPage = new IndexPage();
        indexPage.logout();
        switch (Login) {
            case "UserManualLock":
                loginPage.loginLockedUserClickButton(Login, Password);
                basePage.elementShouldBeVisible(loginPage.userBlockInfo);
                break;

            case "UserCancelManualLock":
                loginPage.loginClickButton(Login, Password);
                basePage.elementShouldBeVisible(loginPage.userProfileMenu);
                break;

            default:
                throw new AssertionError("Пропущена авторизация пользователя и это плохо");
        }
    }

    @Test
    @Parameters(value = {
            "OPERATOR | Оператор | UserDelete | UserPassword",
            "OPERATOR | Оператор | UserCancelDelete | UserPassword",
    })
    public void deleteUser(String RoleApi, String Role1, String Login, String Password) {
        String Cookies = api.loginAPI(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        createUserAPI.createUserAPI(Cookies, RoleApi, Login, Password);
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginAdmin"), ConfigProperties.getTestProperty("PasswordAdmin"));
        basePage.settingsPage();
        userPage.usersAndGroupPage();
        userPage.search(Login);
        userPage.deleteUser(Login);
        userPage.searchDeletedUser(Login);
        indexPage = new IndexPage();
        indexPage.logout();
        if (Login.equals("UserCancelDelete")) {
            loginPage.loginClickButton(Login, Password);
            basePage.elementShouldBeVisible(loginPage.userProfileMenu);
        } else {
            loginPage.failLoginClickButton(Login, Password);
            basePage.elementShouldBeVisible(loginPage.failLoginOrPasswordInfo);
        }
    }

    @Test
    public void createRemoteServerForUser() {
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        settingUserPage.addRemoteServer("NameForTest_createRemoteServerForUser", "http", "192.168.57.240", "9902", "root", "root");

//        List<String> entries = Selenide.getWebDriverLogs(LogType.BROWSER, Level.ALL);
//        System.out.println(entries.size() + " " + LogType.BROWSER + " log entries found");
//        for (String entry : entries) {
//            System.out.println(entry);
//        }
    }

    @Test
    public void cancelCreateRemoteServerForUser() {
        loginPage.loginClickButton(ConfigProperties.getTestProperty("LoginRoot"), ConfigProperties.getTestProperty("PasswordRoot"));
        settingUserPage.cancelAddRemoteServer("NameForTest_cancelCreateRemoteServerForUser", "http", "192.168.194.59","8181", "toor", "toor");
    }

    @Test
    public void asdf() {
        basePage.openPage(baseUrl());
        basePage.settingsPage();
    }
}
