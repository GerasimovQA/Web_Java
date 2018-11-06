package historychanges;

import edituser.EditUserPage;
import global.EnvironmentUser;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.io.File;
import java.util.logging.Level;

@RunWith(JUnitParamsRunner.class)
public class HistoryChangesLogic {

    private static WebDriver driver;
    private static HistoryChangesPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new HistoryChangesPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            try {
                page.waitE_ClickableAndClick(page.menuCreateWorker);
            } catch (TimeoutException e) {
                System.out.println(e);
                page.waitE_ClickableAndClick(page.menuWorkers);
                page.waitE_ClickableAndClick(page.menuCreateWorker);
            }
            driver.navigate().refresh();
//            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

    @BeforeClass
    public static void beforClass() {
        System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(ConfigProperties.getTestProperty("head"));
        options.addArguments("window-size=1200,800");

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(options);
        page = new HistoryChangesPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void logLogin() {
        String NowDate = page.nowDate();
        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Логин"));

                System.out.println(page.columnObject.get(i).getText());
                Assert.assertTrue(page.columnObject.get(i).getText().equals(""));
                return;

            }
        }

    }

    public void logLogout() {
        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        String NowDate = page.nowDate();
        page.waitE_visibilityOf(page.authInputLogin);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.waitE_ClickableAndClick(page.menuWorkers);
        page.waitE_ClickableAndClick(page.buttonUserList);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        page.sleep(3000);
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 1) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Логаут"));

                System.out.println(page.columnObject.get(i).getText());
                Assert.assertTrue(page.columnObject.get(i).getText().equals(""));
                return;
            }

        }
    }

    public void logCreate(String Login, String Password, String Email, String Phone, String Status, String SecondName
            , String FirstName, String MiddleName, String Superuser) {
        page.waitE_ClickableAndClick(page.userinfoname);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);

        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        String NowDate = page.nowDate();
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Создание пользователя"));

                System.out.println(page.columnObject.get(i).getText() + "=" + SecondName + " " + FirstName + " " + MiddleName);
                Assert.assertTrue(page.columnObject.get(i).getText().equals(SecondName + " " + FirstName + " " + MiddleName));
                return;

            }
        }
    }


    public void logEditWorker(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                              String Email, String Phone, String Status, String SecondName, String FirstName,
                              String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);

        int i = -1;
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.linkEditAuthProfile);
        page.waitE_ClickableAndSendKeys(page.inputLogin, NewLogin);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputPhone, NewPhone);
        page.waitE_ClickableAndClick(page.buttonSaveProfile);
        String NowDate = page.nowDate();
        page.waitE_ClickableAndClick(page.buttonUserList);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Изменение профиля"));

                System.out.println(page.columnObject.get(i).getText() + "=" + SecondName + " " + FirstName + " " + MiddleName);
                Assert.assertTrue(page.columnObject.get(i).getText().equals(SecondName + " " + FirstName + " " + MiddleName));
                return;
            }
        }
    }

    public void logNoUnknown() {
        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (int x = 0; x < 10; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());

            if (page.columnAction.size() == 100) {
                System.out.println("Необходимое количесво строк достигнуто");
            }
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(page.columnTime.get(i).getText());
            System.out.println(page.columnInitiator.get(i).getText());
            System.out.println(actionOne.getText());
            System.out.println(page.columnObject.get(i).getText());
            System.out.println();
            if (page.columnAction.get(i).getText().equals("Неизвестное действие")
                    | page.columnObject.get(i).getText().contains("Неизвестный пользователь")
                    | page.columnInitiator.get(i).getText().contains("Неизвестный пользователь")) {
                throw new AssertionError("Присутствует неизвестное действие или неизвестный пользователь");
            }
        }
    }

    public void logSumReportsDefoulte() {
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        } ;
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        System.out.println("Количество строк: " + page.columnAction.size());
        Assert.assertTrue("Количество записей по-умолчанию не равно 10", page.columnAction.size() == 10);
    }

    public void popoverInfoBlock(String Login, String Password, String Email, String Phone, String Status,
                                 String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        System.out.println("1");
//        page.waitE_ClickableAndClick(page.listActionProfile);
//        page.waitE_ClickableAndClick(page.listActionProfileExit);
//        driver.get(ConfigProperties.getTestProperty("baseurl"));
//        page.auth(Login, Password);
//        System.out.println("2");
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        System.out.println("3");
        page.sleep(1000);
//        page.waitE_ClickableAndClick(page.columnInitiator.get(0));


        for (WebElement Object : page.columnObject) {
            System.out.println(Object.getText());
            if (Object.getText().equals(SecondName + " " + FirstName + " " + MiddleName)) {
                page.waitE_ClickableAndClick(Object);
                page.waitE_visibilityOf(page.popoverPhone);
                System.out.println("4");
                break;
            }
        }
        System.out.println(page.popoverPhone.getText() + " = " + Phone);
        Assert.assertEquals(page.popoverPhone.getText(), Phone);

        System.out.println(page.popoverEmail.getText() + " = " + Email);
        Assert.assertEquals(page.popoverEmail.getText(), Email);


        page.waitE_ClickableAndClick(page.popoverMore);
        page.waitE_visibilityOf(page.userinfoname);
        page.sleep(500);
        System.out.println(page.fioUser.getText() + " = " + SecondName + " " + FirstName + " " + MiddleName);
        Assert.assertEquals(page.fioUser.getText(), SecondName + " " + FirstName + " " + MiddleName);

        driver.navigate().back();
        page.waitE_visibilityOf(page.marker10History);

        for (WebElement Initiator : page.columnInitiator) {
            if (Initiator.getText().equals("Акулин Аристарх Анатольевич")) {
                page.waitE_ClickableAndClick(Initiator);
                page.waitE_visibilityOf(page.popoverPhone);
                System.out.println("5");
                break;
            }
        }
        System.out.println(page.popoverPhone.getText() + " = +7 (123) 456-78-99");
        Assert.assertEquals("+7 (123) 456-78-99", page.popoverPhone.getText());

        System.out.println(page.popoverEmail.getText() + " = ioanner@mail.ru");
        Assert.assertEquals("ioanner@mail.ru", page.popoverEmail.getText());


        page.waitE_ClickableAndClick(page.popoverMore);
        page.waitE_visibilityOf(page.userinfoname);
        System.out.println(page.fioUser.getText() + " = Акулин Аристарх Анатольевич");
        Assert.assertEquals("Акулин Аристарх Анатольевич", page.fioUser.getText());

    }

    public void moreInfoBlockLogin() {
        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логин")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());


                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockLogout() {
        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логаут")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockCreate(String Login, String Password, String Email, String Phone, String SecondName,
                                    String FirstName, String MiddleName, String Superuser, String Depart, String Post,
                                    String Ref, String Role, String Status, String Specialities, String Regalia,
                                    String EmailCont, String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                    String Viber, String Facebook, String Other) {
        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status,
                SecondName, FirstName, MiddleName, Superuser);
        EnvironmentUser.profileUserAPI(Depart, Post, Ref, Role, Status, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Создание пользователя")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                System.out.println("Логин: " + page.blockMoreInfoContextLoginValue.getText());
                System.out.println("Email: " + page.blockMoreInfoContextEmailValue.getText());
                System.out.println("Телефон: " + page.blockMoreInfoContextPhoneValue.getText());
                System.out.println("Фамилия: " + page.blockMoreInfoContextFirstnameValue.getText());
                System.out.println("Имя: " + page.blockMoreInfoContextSecondnameValue.getText());
                System.out.println("Отчество: " + page.blockMoreInfoContextMidlenameValue.getText());
                System.out.println("Статус: " + page.blockMoreInfoContextStatusValue.getText());

                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & !page.blockMoreInfoContextLoginValue.getText().equals("") &
                                !page.blockMoreInfoContextEmailValue.getText().equals("") & !page.blockMoreInfoContextPhoneValue.getText().equals("") &
                                !page.blockMoreInfoContextFirstnameValue.getText().equals("") & !page.blockMoreInfoContextSecondnameValue.getText().equals("") &
                                !page.blockMoreInfoContextMidlenameValue.getText().equals("") & !page.blockMoreInfoContextStatusValue.getText().equals("") &
                                page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockChange(String Login, String Password, String Email, String Phone, String Status,
                                    String SecondName, String FirstName, String MiddleName, String Superuser) {
        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Изменение профиля")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void popover() {
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.sleep(1000);
        page.waitE_ClickableAndClick(page.questionInitiator);
        System.out.println(page.popoverInitiator.getText() + " =  Тот кто совершает действие.");
        Assert.assertTrue(page.popoverInitiator.getText().equals("Тот кто совершает действие."));
        page.waitE_ClickableAndClick(page.questionObject);
        System.out.println(page.popoverObject.getText() + " =  Над кем совершают действие.");
        Assert.assertTrue(page.popoverObject.getText().equals("Над кем совершают действие."));
    }

    public void actionFilter() {
        String Action0 = ""; String Action1 = ""; String Action2 = ""; String Action3 = ""; String Action4 = "";
        String Action5 = "";

        int SumAction0case0 = 0;
        int SumAction0case1 = 0; int SumAction1case1 = 0;
        int SumAction0case2 = 0; int SumAction1case2 = 0; int SumAction2case2 = 0;
        int SumAction0case3 = 0; int SumAction1case3 = 0; int SumAction2case3 = 0; int SumAction3case3 = 0;
        int SumAction0case4 = 0; int SumAction1case4 = 0; int SumAction2case4 = 0; int SumAction3case4 = 0;
        int SumAction4case4 = 0;
        int SumAction0case5 = 0; int SumAction1case5 = 0; int SumAction2case5 = 0; int SumAction3case5 = 0;
        int SumAction4case5 = 0; int SumAction5case5 = 0;

        int i = -1;
        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_ClickableAndClick(page.inputAction);
        page.sleep(1000);
        for (WebElement action : page.filterActionsList) {
            i = i + 1;

            switch (i) {
                case 0:
                    Action0 = action.getText(); System.out.println("Действие 0: " + Action0); break;
                case 1:
                    Action1 = action.getText(); System.out.println("Действие 1: " + Action1); break;
                case 2:
                    Action2 = action.getText(); System.out.println("Действие 2: " + Action2); break;
                case 3:
                    Action3 = action.getText(); System.out.println("Действие 3: " + Action3); break;
                case 4:
                    Action4 = action.getText(); System.out.println("Действие 4: " + Action4); break;
                case 5:
                    Action5 = action.getText(); System.out.println("Действие 5: " + Action5); break;
                case 6:
                    throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг");
            }

            page.waitE_ClickableAndClick(action);
            for (int x = 0; x < 9; x++) {
                page.waitE_ClickableAndClick(page.buttonLoadMore);
                System.out.println("Количество строк: " + page.columnAction.size());
            }

            for (WebElement actionOne : page.columnAction) {
                System.out.println("i= " + i);
                switch (i) {
                    case 0:


                        System.out.println(actionOne.getText() + " = " + Action0);
                        Assert.assertEquals(actionOne.getText(), Action0);
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case0 = SumAction0case0 + 1;
                        }
                        break;
                    case 1:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1);
                        Assert.assertTrue(actionOne.getText().equals(Action0) | actionOne.getText().equals(Action1));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case1 = SumAction0case1 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case1 = SumAction1case1 + 1;
                        }
                        break;
                    case 2:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case2 = SumAction0case2 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case2 = SumAction1case2 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case2 = SumAction2case2 + 1;
                        }
                        break;
                    case 3:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2 +
                                " или " + Action3);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case3 = SumAction0case3 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case3 = SumAction1case3 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case3 = SumAction2case3 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case3 = SumAction3case3 + 1;
                        }
                        break;
                    case 4:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " +
                                Action2 + " или " + Action3 + " или " + Action4);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3) | actionOne.getText().equals(Action4));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case4 = SumAction0case4 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case4 = SumAction1case4 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case4 = SumAction2case4 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case4 = SumAction3case4 + 1;
                        }
                        if (actionOne.getText().equals(Action4)) {
                            SumAction4case4 = SumAction4case4 + 1;
                        }
                        break;
                    case 5:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2 +
                                " или " + Action3 + " или " + Action4 + " или " + Action5);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3) | actionOne.getText().equals(Action4)
                                | actionOne.getText().equals(Action5));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case5 = SumAction0case5 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case5 = SumAction1case5 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case5 = SumAction2case5 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case5 = SumAction3case5 + 1;
                        }
                        if (actionOne.getText().equals(Action4)) {
                            SumAction4case5 = SumAction4case5 + 1;
                        }
                        if (actionOne.getText().equals(Action5)) {
                            SumAction5case5 = SumAction5case5 + 1;
                        }

                        break;
                    case 6:
                        throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг (Не" +
                                " забудьте про проверку >1 чуть ниже)");
                }
            }

            page.waitE_ClickableAndClick(page.inputAction);
            page.sleep(1000);
        }
        System.out.println("1 действие:");
        System.out.println(Action0 + "-" + SumAction0case0); Assert.assertTrue(SumAction0case0 >= 1);
        System.out.println(""); System.out.println("2 действия:");
        System.out.println(Action0 + "-" + SumAction0case1); Assert.assertTrue(SumAction0case1 >= 1);
        System.out.println(Action1 + "-" + SumAction1case1); Assert.assertTrue(SumAction1case1 >= 1);
        System.out.println(""); System.out.println("3 действия:");
        System.out.println(Action0 + "-" + SumAction0case2); Assert.assertTrue(SumAction0case2 >= 1);
        System.out.println(Action1 + "-" + SumAction1case2); Assert.assertTrue(SumAction1case2 >= 1);
        System.out.println(Action2 + "-" + SumAction2case2); Assert.assertTrue(SumAction2case2 >= 1);
        System.out.println(""); System.out.println("4 действия:");
        System.out.println(Action0 + "-" + SumAction0case3); Assert.assertTrue(SumAction0case3 >= 1);
        System.out.println(Action1 + "-" + SumAction1case3); Assert.assertTrue(SumAction1case3 >= 1);
        System.out.println(Action2 + "-" + SumAction2case3); Assert.assertTrue(SumAction2case3 >= 1);
        System.out.println(Action3 + "-" + SumAction3case3); Assert.assertTrue(SumAction3case3 >= 1);
//        System.out.println("");
//        System.out.println("5 действий:");
//        System.out.println(Action0 + "-" + SumAction0case4); Assert.assertTrue(SumAction0case4 >= 1);
//        System.out.println(Action1 + "-" + SumAction1case4); Assert.assertTrue(SumAction1case4 >= 1);
//        System.out.println(Action2 + "-" + SumAction2case4); Assert.assertTrue(SumAction2case4 >= 1);
//        System.out.println(Action3 + "-" + SumAction3case4); Assert.assertTrue(SumAction3case4 >= 1);
//        System.out.println(Action4 + "-" + SumAction4case4); Assert.assertTrue(SumAction4case4 >= 1);
//        System.out.println("");
//        System.out.println("6 действий:");
//        System.out.println(Action0 + "-" + SumAction0case5); Assert.assertTrue(SumAction0case5 >= 1);
//        System.out.println(Action1 + "-" + SumAction1case5); Assert.assertTrue(SumAction1case5 >= 1);
//        System.out.println(Action2 + "-" + SumAction2case5); Assert.assertTrue(SumAction2case5 >= 1);
//        System.out.println(Action3 + "-" + SumAction3case5); Assert.assertTrue(SumAction3case5 >= 1);
//        System.out.println(Action4 + "-" + SumAction4case5); Assert.assertTrue(SumAction4case5 >= 1);
//        System.out.println(Action5 + "-" + SumAction5case5); Assert.assertTrue(SumAction5case5 >= 1);

    }

    public void actionFilterCalendar() {
        int i = -1;

        try {
            page.waitE_ClickableAndClick(page.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            page.waitE_ClickableAndClick(page.menuWorkers);
            page.waitE_ClickableAndClick(page.buttonUserList);
        }
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.marker10History);
        page.waitE_ClickableAndClick(page.calendar);
        page.waitE_ClickableAndClick(page.calendarToday);
        page.sleep(500);
        page.moveMouse(page.calendarTodaySelect);
        page.waitE_ClickableAndClick(page.calendarTodaySelect);

        for (int x = 0; x < 9; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + page.todayDate());
            Assert.assertTrue(timeOne.getText().contains(page.todayDate()));
        }

        page.waitE_ClickableAndClick(page.inputAction);
        page.waitE_ClickableAndClick(page.filterActionsList.get(2));


        for (int x = 0; x < 9; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + page.todayDate());
            Assert.assertTrue(timeOne.getText().contains(page.todayDate()));
        }
    }

    public void allReportToYearAPI() {
        EnvironmentUser.login();
        EnvironmentUser.BigDataAPI();
    }


///////////////////////////////////////////Test-User////////////////////////////////////////////////////////////////////

    public void logLoginUser(String SecondName) {
        String NowDate = page.nowDate();
        int i = -1;

        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_visibilityOf(page.columnTime.get(0));
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Логин"));

                System.out.println(page.columnObject.get(i).getText());
                Assert.assertTrue(page.columnObject.get(i).getText().equals(""));
                return;

            }
        }

    }

    public void logLogoutUser(String SecondName) {
        int i = -1;

        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        String NowDate = page.nowDate();
        page.waitE_visibilityOf(page.authInputLogin);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.sleep(3000);
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 1) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Логаут"));

                System.out.println(page.columnObject.get(i).getText());
                Assert.assertTrue(page.columnObject.get(i).getText().equals(""));
                return;
            }

        }
    }

    public void logCreateUser(String Login, String Password, String Email, String Phone, String Status,
                              String SecondName
            , String FirstName, String MiddleName, String Superuser) {
        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        String NowDate = page.nowDate();
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Создание пользователя"));

                System.out.println(page.columnObject.get(i).getText() + "=" + SecondName + " " + FirstName + " " + MiddleName);
                Assert.assertTrue(page.columnObject.get(i).getText().equals(SecondName + " " + FirstName + " " + MiddleName));
                return;

            }
        }
    }


    public void logEditWorkerUser(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                                  String Email, String Phone, String Status, String SecondName, String FirstName,
                                  String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);

        int i = -1;
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.linkEditAuthProfile);
        page.waitE_ClickableAndSendKeys(page.inputLogin, NewLogin);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputPhone, NewPhone);
        page.waitE_ClickableAndClick(page.buttonSaveProfile);
        String NowDate = page.nowDate();
        page.waitE_ClickableAndClick(page.menuWorkers);
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        Assert.assertTrue(page.columnTime.size() > 0);
        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(page.userinfoname.getText());
                Assert.assertTrue(page.columnInitiator.get(i).getText().equals(page.userinfoname.getText()));

                System.out.println(page.columnAction.get(i).getText());
                Assert.assertTrue(page.columnAction.get(i).getText().equals("Изменение профиля"));

                System.out.println(page.columnObject.get(i).getText() + "=" + SecondName + " " + FirstName + " " + MiddleName);
                Assert.assertTrue(page.columnObject.get(i).getText().equals(SecondName + " " + FirstName + " " + MiddleName));
                return;
            }
        }
    }

    public void logSumReportsDefoulteUser(String SecondName) {
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        System.out.println("Количество строк: " + page.columnAction.size());
        Assert.assertTrue("Количество записей по-умолчанию не равно 10", page.columnAction.size() == 10);
    }

    public void logLoadMoreUserUser(String SecondName) {
        int i = -1;
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);

        for (int x = 0; x < 10; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());

            if (page.columnAction.size() == 100) {
                System.out.println("Необходимое количесво строк достигнуто");
            }
        }
    }

    public void popoverInfoBlockUser(String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser) {
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser);
        System.out.println("1");
        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        page.auth(Login, Password);
        System.out.println("2");
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        System.out.println("3");
        page.sleep(1000);
        page.waitE_ClickableAndClick(page.columnInitiator.get(0));
        page.waitE_visibilityOf(page.popoverPhone);
        System.out.println("4");

        System.out.println(page.popoverPhone.getText() + " = " + Phone);
        Assert.assertTrue(page.popoverPhone.getText().equals(Phone));

        System.out.println(page.popoverEmail.getText() + " = " + Email);
        Assert.assertTrue(page.popoverEmail.getText().equals(Email));


        page.waitE_ClickableAndClick(page.popoverMore);
        page.waitE_visibilityOf(page.userinfoname);
        System.out.println(page.userinfoname.getText() + " = " + SecondName + " " + FirstName + " " + MiddleName);
        Assert.assertTrue(page.userinfoname.getText().equals(SecondName + " " + FirstName + " " + MiddleName));

        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    public void moreInfoBlockLoginUser(String SecondName) {
        int i = -1;
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логин")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());


                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockLogoutUser(String SecondName) {
        int i = -1;
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логаут")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockCreateUser(String Login, String Password, String Email, String Phone, String SecondName,
                                        String FirstName, String MiddleName, String Superuser, String Depart,
                                        String Post,
                                        String Ref, String Role, String Status, String Specialities, String Regalia,
                                        String EmailCont, String PhoneCont, String Instagram, String Vk,
                                        String Whatsapp,
                                        String Viber, String Facebook, String Other) {
        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status,
                SecondName, FirstName, MiddleName, Superuser);
        EnvironmentUser.profileUserAPI(Depart, Post, Ref, Role, Status, Specialities, Regalia, EmailCont, PhoneCont,
                Instagram, Vk, Whatsapp, Viber, Facebook, Other);
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Создание пользователя")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);


                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                System.out.println("Логин: " + page.blockMoreInfoContextLoginValue.getText());
                System.out.println("Email: " + page.blockMoreInfoContextEmailValue.getText());
                System.out.println("Телефон: " + page.blockMoreInfoContextPhoneValue.getText());
                System.out.println("Фамилия: " + page.blockMoreInfoContextFirstnameValue.getText());
                System.out.println("Имя: " + page.blockMoreInfoContextSecondnameValue.getText());
                System.out.println("Отчество: " + page.blockMoreInfoContextMidlenameValue.getText());
                System.out.println("Статус: " + page.blockMoreInfoContextStatusValue.getText());

                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & !page.blockMoreInfoContextLoginValue.getText().equals("") &
                                !page.blockMoreInfoContextEmailValue.getText().equals("") & !page.blockMoreInfoContextPhoneValue.getText().equals("") &
                                !page.blockMoreInfoContextFirstnameValue.getText().equals("") & !page.blockMoreInfoContextSecondnameValue.getText().equals("") &
                                !page.blockMoreInfoContextMidlenameValue.getText().equals("") & !page.blockMoreInfoContextStatusValue.getText().equals("") &
                                page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockChangeUser(String Login, String Password, String Email, String Phone, String Status,
                                        String SecondName, String FirstName, String MiddleName, String Superuser) {
        int i = -1;
        EnvironmentUser.login();
        EnvironmentUser.createUserAPI(Login, Email, Phone, Password, Status, SecondName, FirstName, MiddleName,
                Superuser);
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);

        for (int x = 0; x < 4; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement actionOne : page.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Изменение профиля")) {
                page.waitE_ClickableAndClick(page.columnTrigger.get(i));
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blockMoreInfoContextIDValue.getText().equals("")
                                & !page.blockMoreInfoContextDeviceValue.getText().equals("") & !page.blockMoreInfoContextIPValue.getText().equals("")
                                & page.blockMoreInfoContextButtonMoreInfo.isEnabled() & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonMoreInfo);

                page.waitE_visibilityOf(page.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + page.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + page.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + page.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + page.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + page.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + page.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + page.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !page.blocInfoAboutEventDate.getText().equals("") & !page.blocInfoAboutEventInitiator.getText().equals("") &
                                !page.blocInfoAboutEventAction.getText().equals("") & !page.blocInfoAboutEventObject.getText().equals("") &
                                !page.blockMoreInfoContextIDValue.getText().equals("") & !page.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !page.blockMoreInfoContextIPValue.getText().equals("") & page.blockMoreInfoContextButtonCopyLink.isEnabled());

                page.waitE_ClickableAndClick(page.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void popoverUser(String SecondName) {
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.sleep(1000);
        page.waitE_ClickableAndClick(page.questionInitiator);
        System.out.println(page.popoverInitiator.getText() + " =  Тот кто совершает действие.");
        Assert.assertTrue(page.popoverInitiator.getText().equals("Тот кто совершает действие."));
        page.waitE_ClickableAndClick(page.questionObject);
        System.out.println(page.popoverObject.getText() + " =  Над кем совершают действие.");
        Assert.assertTrue(page.popoverObject.getText().equals("Над кем совершают действие."));
//        File file = new File("/home/alex/Dropbox/IdeaProjects/LionsDigital/Files/2002.png");
//        if (file.exists()) {
//            System.out.println(page.getFileSizeKiloBytes(file));
//        } else System.out.println("Файла нет!");
    }

    public void actionFilterUser(String SecondName) {
        String Action0 = ""; String Action1 = ""; String Action2 = ""; String Action3 = ""; String Action4 = "";
        String Action5 = "";

        int SumAction0case0 = 0;
        int SumAction0case1 = 0; int SumAction1case1 = 0;
        int SumAction0case2 = 0; int SumAction1case2 = 0; int SumAction2case2 = 0;
        int SumAction0case3 = 0; int SumAction1case3 = 0; int SumAction2case3 = 0; int SumAction3case3 = 0;
        int SumAction0case4 = 0; int SumAction1case4 = 0; int SumAction2case4 = 0; int SumAction3case4 = 0;
        int SumAction4case4 = 0;
        int SumAction0case5 = 0; int SumAction1case5 = 0; int SumAction2case5 = 0; int SumAction3case5 = 0;
        int SumAction4case5 = 0; int SumAction5case5 = 0;

        int i = -1;
        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_ClickableAndClick(page.inputAction);
        page.sleep(1000);
        for (WebElement action : page.filterActionsList) {
            i = i + 1;

            switch (i) {
                case 0:
                    Action0 = action.getText(); System.out.println("Действие 0: " + Action0); break;
                case 1:
                    Action1 = action.getText(); System.out.println("Действие 1: " + Action1); break;
                case 2:
                    Action2 = action.getText(); System.out.println("Действие 2: " + Action2); break;
                case 3:
                    Action3 = action.getText(); System.out.println("Действие 3: " + Action3); break;
                case 4:
                    Action4 = action.getText(); System.out.println("Действие 4: " + Action4); break;
                case 5:
                    Action5 = action.getText(); System.out.println("Действие 5: " + Action5); break;
                case 6:
                    throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг");
            }

            page.waitE_ClickableAndClick(action);
            for (int x = 0; x < 9; x++) {
                page.waitE_ClickableAndClick(page.buttonLoadMore);
                System.out.println("Количество строк: " + page.columnAction.size());
            }
            for (WebElement actionOne : page.columnAction) {
                System.out.println("i= " + i);
                switch (i) {
                    case 0:
                        System.out.println(actionOne.getText() + " = " + Action0);
                        Assert.assertEquals(actionOne.getText(), Action0);
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case0 = SumAction0case0 + 1;
                        }
                        break;

                    case 1:
                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1);
                        Assert.assertTrue(actionOne.getText().equals(Action0) | actionOne.getText().equals(Action1));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case1 = SumAction0case1 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case1 = SumAction1case1 + 1;
                        }
                        break;
                    case 2:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case2 = SumAction0case2 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case2 = SumAction1case2 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case2 = SumAction2case2 + 1;
                        }
                        break;
                    case 3:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2 +
                                " или " + Action3);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case3 = SumAction0case3 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case3 = SumAction1case3 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case3 = SumAction2case3 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case3 = SumAction3case3 + 1;
                        }
                        break;
                    case 4:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " +
                                Action2 + " или " + Action3 + " или " + Action4);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3) | actionOne.getText().equals(Action4));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case4 = SumAction0case4 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case4 = SumAction1case4 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case4 = SumAction2case4 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case4 = SumAction3case4 + 1;
                        }
                        if (actionOne.getText().equals(Action4)) {
                            SumAction4case4 = SumAction4case4 + 1;
                        }
                        break;
                    case 5:

                        System.out.println(actionOne.getText() + " = " + Action0 + " или " + Action1 + " или " + Action2 +
                                " или " + Action3 + " или " + Action4 + " или " + Action5);
                        Assert.assertTrue(actionOne.getText().equals(Action0)
                                | actionOne.getText().equals(Action1) | actionOne.getText().equals(Action2)
                                | actionOne.getText().equals(Action3) | actionOne.getText().equals(Action4)
                                | actionOne.getText().equals(Action5));
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case5 = SumAction0case5 + 1;
                        }
                        if (actionOne.getText().equals(Action1)) {
                            SumAction1case5 = SumAction1case5 + 1;
                        }
                        if (actionOne.getText().equals(Action2)) {
                            SumAction2case5 = SumAction2case5 + 1;
                        }
                        if (actionOne.getText().equals(Action3)) {
                            SumAction3case5 = SumAction3case5 + 1;
                        }
                        if (actionOne.getText().equals(Action4)) {
                            SumAction4case5 = SumAction4case5 + 1;
                        }
                        if (actionOne.getText().equals(Action5)) {
                            SumAction5case5 = SumAction5case5 + 1;
                        }

                        break;
                    case 6:
                        throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг (Не" +
                                " забудьте про проверку >1 чуть ниже)");
                }
            }

            page.waitE_ClickableAndClick(page.inputAction);
            page.sleep(1000);
        }
        System.out.println("1 действие:");
        System.out.println(Action0 + "-" + SumAction0case0); Assert.assertTrue(SumAction0case0 >= 1);
        System.out.println(""); System.out.println("2 действия:");
        System.out.println(Action0 + "-" + SumAction0case1); Assert.assertTrue(SumAction0case1 >= 1);
        System.out.println(Action1 + "-" + SumAction1case1); Assert.assertTrue(SumAction1case1 >= 1);
        System.out.println(""); System.out.println("3 действия:");
        System.out.println(Action0 + "-" + SumAction0case2); Assert.assertTrue(SumAction0case2 >= 1);
        System.out.println(Action1 + "-" + SumAction1case2); Assert.assertTrue(SumAction1case2 >= 1);
        System.out.println(Action2 + "-" + SumAction2case2); Assert.assertTrue(SumAction2case2 >= 1);
        System.out.println(""); System.out.println("4 действия:");
        System.out.println(Action0 + "-" + SumAction0case3); Assert.assertTrue(SumAction0case3 >= 1);
        System.out.println(Action1 + "-" + SumAction1case3); Assert.assertTrue(SumAction1case3 >= 1);
        System.out.println(Action2 + "-" + SumAction2case3); Assert.assertTrue(SumAction2case3 >= 1);
        System.out.println(Action3 + "-" + SumAction3case3); Assert.assertTrue(SumAction3case3 >= 1);
//        System.out.println("");
//        System.out.println("5 действий:");
//        System.out.println(Action0 + "-" + SumAction0case4); Assert.assertTrue(SumAction0case4 >= 1);
//        System.out.println(Action1 + "-" + SumAction1case4); Assert.assertTrue(SumAction1case4 >= 1);
//        System.out.println(Action2 + "-" + SumAction2case4); Assert.assertTrue(SumAction2case4 >= 1);
//        System.out.println(Action3 + "-" + SumAction3case4); Assert.assertTrue(SumAction3case4 >= 1);
//        System.out.println(Action4 + "-" + SumAction4case4); Assert.assertTrue(SumAction4case4 >= 1);
//        System.out.println("");
//        System.out.println("6 действий:");
//        System.out.println(Action0 + "-" + SumAction0case5); Assert.assertTrue(SumAction0case5 >= 1);
//        System.out.println(Action1 + "-" + SumAction1case5); Assert.assertTrue(SumAction1case5 >= 1);
//        System.out.println(Action2 + "-" + SumAction2case5); Assert.assertTrue(SumAction2case5 >= 1);
//        System.out.println(Action3 + "-" + SumAction3case5); Assert.assertTrue(SumAction3case5 >= 1);
//        System.out.println(Action4 + "-" + SumAction4case5); Assert.assertTrue(SumAction4case5 >= 1);
//        System.out.println(Action5 + "-" + SumAction5case5); Assert.assertTrue(SumAction5case5 >= 1);

    }

    public void actionFilterCalendarUser(String SecondName) {
        int i = -1;

        page.moveToProfileUser(SecondName);
        page.waitE_ClickableAndClick(page.linkHistoryChanges);
        page.waitE_ClickableAndClick(page.calendar);
        page.waitE_ClickableAndClick(page.calendarToday);
        page.sleep(500);
        page.moveMouse(page.calendarTodaySelect);
        page.waitE_ClickableAndClick(page.calendarTodaySelect);

        for (int x = 0; x < 9; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + page.todayDate());
            Assert.assertTrue(timeOne.getText().contains(page.todayDate()));
        }

        page.waitE_ClickableAndClick(page.inputAction);
        page.waitE_ClickableAndClick(page.filterActionsList.get(2));


        for (int x = 0; x < 9; x++) {
            page.waitE_ClickableAndClick(page.buttonLoadMore);
            System.out.println("Количество строк: " + page.columnAction.size());
        }

        for (WebElement timeOne : page.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + page.todayDate());
            Assert.assertTrue(timeOne.getText().contains(page.todayDate()));
        }
    }

    public void allReportToYearAPIUser() {
        EnvironmentUser.login();
        EnvironmentUser.BigDataAPIUser();
    }

}
