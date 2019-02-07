package HistoryChanges;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class HistoryChangesLogic {
    HistoryChangesAPI historyChangesAPI = new HistoryChangesAPI();

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private HistoryChangesPage p;

    private String Token = null;
    private String UserID = null;

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("\n\n\n");
            try {
                driver = Capa.getRemouteDriver(ConfigProperties.getTestProperty("sys"),
                        URI.create(ConfigProperties.getTestProperty("endpoint")).toURL(), description);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            tlDriver.set(driver);
            p = new HistoryChangesPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
            driver.get(ConfigProperties.getTestProperty("baseurl") + "/#/users/list");
        }

        @Override
        protected void finished(Description description) {
            //            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsHistoryChanges);
        }
    };

    public void logLogin() {
        String NowDate = GlobalPage.nowDate();
        int i = -1;
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.body);
        p.scrollHomePage();
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {

                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Логин");
                p.compareWebelementAndString(p.columnObject.get(i), "");
                return;
            }
        }
    }

    public void logLogout() {
        int i = -1;
        p.preloader();
        p.logout();
        p.waitVisibility(p.authInputLogin);
        p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
        p.click(p.listActionProfile);
        p.click(p.listActionProfileExit);
        String NowDate = GlobalPage.nowDate();
        p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        p.click(p.menuWorkers);
        p.click(p.buttonUserList);
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);
        p.sleep(3000);
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) && p.columnInitiator.get(i).getText().equals("Акулов Игорь Иванович")) {
                System.out.println(p.userinfoname.getText());
                p.compareStringAndWebelement("Логаут", p.columnAction.get(i));
                p.compareStringAndWebelement("", p.columnObject.get(i));
                return;
            }
        }
    }

    public void logCreate(String Login, String Password, String Email, String Phone, String Status, String SecondName
            , String FirstName,
                          String MiddleName, String Superuser, String SendEmail, String OrgID,
                          String Depart, String OrgStatus, String Post1, String Post2, String Role1, String Role2) {
        p.click(p.userinfoname);
        p.click(p.listActionProfileExit);
        p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);

        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        String NowDate = GlobalPage.nowDate();
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) && p.columnInitiator.get(i).getText().equals("Акулов Игорь Иванович")) {
                System.out.println(p.userinfoname.getText());
                p.compareWebelementAndString(p.columnAction.get(i), "Создание пользователя");
                p.compareWebelementAndString(p.columnObject.get(i), SecondName + " " + FirstName + " " + MiddleName);
                return;
            }
        }
    }


    public void logEditWorker(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                              String Email, String Phone, String Status, String SecondName, String FirstName,
                              String MiddleName, String Superuser, String SendEmail, String OrgID,
                              String Depart, String OrgStatus, String Post1, String Post2, String Role1,
                              String Role2) {
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);

        int i = -1;
        p.moveToProfileUserIDChange(UserID);
        p.click(p.linkEditAuthProfile);
        p.clickAndSendKeys(p.inputLogin, NewLogin);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputPhone, NewPhone);
        p.click(p.buttonSaveProfile);
        String NowDate = GlobalPage.nowDate();
        p.click(p.buttonUserList);
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Изменение профиля");
                System.out.println(p.columnAction.get(i).getText());
                Assert.assertEquals("Изменение профиля", p.columnAction.get(i).getText());

                p.compareWebelementAndString(p.columnObject.get(i), SecondName + " " + FirstName + " " + MiddleName);
                return;
            }
        }
    }

    public void logNoUnknown() {
        int i = -1;
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (int x = 0; x < 10; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());

            if (p.columnAction.size() == 100) {
                System.out.println("Необходимое количесво строк достигнуто");
            }
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            p.moveMouse(actionOne);
            System.out.println(p.columnTime.get(i).getText());
            System.out.println(p.columnInitiator.get(i).getText());
            System.out.println(actionOne.getText());
            System.out.println(p.columnObject.get(i).getText());
            System.out.println();
            if (p.columnAction.get(i).getText().equals("Неизвестное действие")
                    || p.columnObject.get(i).getText().contains("Неизвестный пользователь")
                    || p.columnInitiator.get(i).getText().contains("Неизвестный пользователь")) {
                throw new AssertionError("Присутствует неизвестное действие или неизвестный пользователь");
            }
        }
    }

    public void logSumReportsDefoulte() {
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.preloader();
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);
        System.out.println("Количество строк: " + p.columnAction.size());
        Assert.assertEquals("Количество записей по-умолчанию не равно 10", 10, p.columnAction.size());
    }

    public void popoverInfoBlock(String Login, String Password, String Email, String Phone, String Status,
                                 String SecondName, String FirstName, String MiddleName, String Superuser,
                                 String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                 String Role1, String Role2) {
        historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        System.out.println("1");
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);
        System.out.println("3");

        for (WebElement Object : p.columnObject) {
            p.moveMouse(Object);
            System.out.println(Object.getText());
            if (Object.getText().equals(SecondName + " " + FirstName + " " + MiddleName)) {
                p.click(Object);
                p.sleep(2000);
                p.waitVisibility(p.popoverPhone);
                System.out.println("4");
                break;
            }
        }
        p.compareWebelementAndString(p.popoverPhone, Phone);
        p.compareWebelementAndString(p.popoverEmail, Email);
        p.click(p.popoverMore);
        p.waitVisibility(p.userinfoname);
        p.compareWebelementAndString(p.fioUser, SecondName + " " + FirstName + " " + MiddleName);
        driver.navigate().back();
        p.scrollHomePage();
        for (WebElement Initiator : p.columnInitiator) {
            if (Initiator.getText().equals("Акулин Аристарх Анатольевич")) {
                p.click(Initiator);
                p.waitVisibility(p.popoverPhone);
                System.out.println("5");
                break;
            }
        }

        p.compareWebelementAndString(p.popoverPhone, "+ 7 (123) 456-78-99");
        p.compareWebelementAndString(p.popoverEmail, "ioanner@mail.ru");
        p.click(p.popoverMore);
        p.waitVisibility(p.userinfoname);
        p.compareWebelementAndString(p.fioUser, "Акулин Аристарх Анатольевич");
    }

    public void moreInfoBlockLogin() {
        int i = -1;
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логин")) {
                p.scrollHomePage();
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());
                p.scrollHomePage();
                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockLogout() {
        int i = -1;
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логаут")) {
                p.scrollHomePage();
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());
                p.scrollHomePage();
                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockCreate(String Login, String Password, String Email, String Phone,
                                    String Status, String SecondName, String FirstName,
                                    String MiddleName, String Superuser, String SendEmail, String OrgID,
                                    String Depart, String OrgStatus, String Post1,
                                    String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                    String Service1, String Service2, String Regalia,
                                    String EmailCont, String PhoneCont, String Instagram, String Vk, String Whatsapp,
                                    String Viber, String Facebook, String Other) {
        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        historyChangesAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont, Instagram, Vk
                , Whatsapp, Viber, Facebook, Other);
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Создание пользователя")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());

                p.moveMouse(p.blockMoreInfoContextDeviceValue);
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());

                p.moveMouse(p.blockMoreInfoContextIPValue);
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());

                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());
                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                p.moveMouse(p.blocInfoAboutEventDate);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());

                p.moveMouse(p.blocInfoAboutEventInitiator);
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());

                p.moveMouse(p.blocInfoAboutEventAction);
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());

                p.moveMouse(p.blocInfoAboutEventObject);
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());

                p.moveMouse(p.blockMoreInfoContextIDValue);
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());

                p.moveMouse(p.blockMoreInfoContextDeviceValue);
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());

                p.moveMouse(p.blockMoreInfoContextIPValue);
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());

                p.moveMouse(p.blockMoreInfoContextLoginValue);
                System.out.println("Логин: " + p.blockMoreInfoContextLoginValue.getText());

                p.moveMouse(p.blockMoreInfoContextEmailValue);
                System.out.println("Email: " + p.blockMoreInfoContextEmailValue.getText());

                p.moveMouse(p.blockMoreInfoContextPhoneValue);
                System.out.println("Телефон: " + p.blockMoreInfoContextPhoneValue.getText());

                p.moveMouse(p.blockMoreInfoContextFirstnameValue);
                System.out.println("Фамилия: " + p.blockMoreInfoContextFirstnameValue.getText());

                p.moveMouse(p.blockMoreInfoContextSecondnameValue);
                System.out.println("Имя: " + p.blockMoreInfoContextSecondnameValue.getText());

                p.moveMouse(p.blockMoreInfoContextMidlenameValue);
                System.out.println("Отчество: " + p.blockMoreInfoContextMidlenameValue.getText());

                p.moveMouse(p.blockMoreInfoContextStatusValue);
                System.out.println("Статус: " + p.blockMoreInfoContextStatusValue.getText());

                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & !p.blockMoreInfoContextLoginValue.getText().equals("") &
                                !p.blockMoreInfoContextEmailValue.getText().equals("") & !p.blockMoreInfoContextPhoneValue.getText().equals("") &
                                !p.blockMoreInfoContextFirstnameValue.getText().equals("") & !p.blockMoreInfoContextSecondnameValue.getText()
                                .equals("") & !p.blockMoreInfoContextMidlenameValue.getText().equals("") &
                                !p.blockMoreInfoContextStatusValue.getText().equals("") &
                                p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.waitE_ClickableAndClickAndUp(p.body);
                p.scrollHomePage();
                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockChange(String Login, String Email, String Phone,
                                    String Password, String Status, String SecondName, String FirstName,
                                    String MiddleName, String Superuser, String SendEmail, String OrgID,
                                    String Depart, String OrgStatus, String Post1,
                                    String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                    String Service1, String Service2) {
        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Изменение профиля")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());
                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.scrollHomePage();
                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void popover() {
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.sleep(1000);
        p.click(p.questionInitiator);

        p.compareStringAndWebelement("Тот кто совершает действие.", p.popoverInitiator);
        p.click(p.questionObject);
        p.compareStringAndWebelement("Над кем совершают действие.", p.popoverObject);
    }

    public void actionFilter() {
        String Action0 = "";
        String Action1 = "";
        String Action2 = "";
        String Action3 = "";
        String Action4 = "";
        String Action5 = "";

        int SumAction0case0 = 0;
        int SumAction0case1 = 0;
        int SumAction1case1 = 0;
        int SumAction0case2 = 0;
        int SumAction1case2 = 0;
        int SumAction2case2 = 0;
        int SumAction0case3 = 0;
        int SumAction1case3 = 0;
        int SumAction2case3 = 0;
        int SumAction3case3 = 0;
        int SumAction0case4 = 0;
        int SumAction1case4 = 0;
        int SumAction2case4 = 0;
        int SumAction3case4 = 0;
        int SumAction4case4 = 0;
        int SumAction0case5 = 0;
        int SumAction1case5 = 0;
        int SumAction2case5 = 0;
        int SumAction3case5 = 0;
        int SumAction4case5 = 0;
        int SumAction5case5 = 0;

        int i = -1;
        try {
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.click(p.inputAction);
        for (WebElement action : p.filterActionsList) {
            p.scrollHomePage();
            p.sleep(1500);
            i = i + 1;
            if (!p.filterActionsList.get(i).isDisplayed()) {
                p.click(p.inputAction);
            }
            p.sleep(1000);
            switch (i) {
                case 0:
                    Action0 = action.getText();
                    System.out.println("Действие 0: " + Action0);
                    break;
                case 1:
                    Action1 = action.getText();
                    System.out.println("Действие 1: " + Action1);
                    break;
                case 2:
                    Action2 = action.getText();
                    System.out.println("Действие 2: " + Action2);
                    break;
                case 3:
                    Action3 = action.getText();
                    System.out.println("Действие 3: " + Action3);
                    break;
                case 4:
                    Action4 = action.getText();
                    System.out.println("Действие 4: " + Action4);
                    break;
                case 5:
                    Action5 = action.getText();
                    System.out.println("Действие 5: " + Action5);
                    break;
                case 6:
                    throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг");
            }

            p.click(action);
            for (int x = 0; x < 9; x++) {
                p.click(p.buttonLoadMore);
                System.out.println("Количество строк: " + p.columnAction.size());
            }
            p.scrollHomePage();
            p.click(p.inputAction);
            p.sleep(2000);
            for (WebElement actionOne : p.columnAction) {
                p.moveMouse(actionOne);
                System.out.println("i= " + i);
                switch (i) {
                    case 0:

                        p.compareWebelementAndString(actionOne, Action0);
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case0 = SumAction0case0 + 1;
                        }
                        break;
                    case 1:
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
        }
        System.out.println("1 действие:");
        System.out.println(Action0 + "-" + SumAction0case0);
        Assert.assertTrue(SumAction0case0 >= 1);
        System.out.println("");
        System.out.println("2 действия:");
        System.out.println(Action0 + "-" + SumAction0case1);
        Assert.assertTrue(SumAction0case1 >= 1);
        System.out.println(Action1 + "-" + SumAction1case1);
        Assert.assertTrue(SumAction1case1 >= 1);
        System.out.println("");
        System.out.println("3 действия:");
        System.out.println(Action0 + "-" + SumAction0case2);
        Assert.assertTrue(SumAction0case2 >= 1);
        System.out.println(Action1 + "-" + SumAction1case2);
        Assert.assertTrue(SumAction1case2 >= 1);
        System.out.println(Action2 + "-" + SumAction2case2);
        Assert.assertTrue(SumAction2case2 >= 1);
        System.out.println("");
        System.out.println("4 действия:");
        System.out.println(Action0 + "-" + SumAction0case3);
        Assert.assertTrue(SumAction0case3 >= 1);
        System.out.println(Action1 + "-" + SumAction1case3);
        Assert.assertTrue(SumAction1case3 >= 1);
        System.out.println(Action2 + "-" + SumAction2case3);
        Assert.assertTrue(SumAction2case3 >= 1);
        System.out.println(Action3 + "-" + SumAction3case3);
        Assert.assertTrue(SumAction3case3 >= 1);
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
            p.click(p.buttonUserList);
        } catch (TimeoutException e) {
            System.out.println(e);
            p.click(p.menuWorkers);
            p.click(p.buttonUserList);
        }
        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.marker10History);
        p.scrollHomePage();
        p.click(p.calendar);
        p.click(p.calendarToday);
        p.sleep(500);
        p.moveMouse(p.calendarTodaySelect);
        p.click(p.calendarTodaySelect);

        for (int x = 0; x < 9; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement timeOne : p.columnTime) {
            p.moveMouse(timeOne);
            System.out.println(timeOne.getText());
            i = i + 1;

            p.moveMouse(timeOne);
            System.out.println(timeOne.getText() + " содержит " + p.todayDate());
            Assert.assertTrue(timeOne.getText().contains(p.todayDate()));
        }

        p.scrollHomePage();
        p.click(p.inputAction);
        p.click(p.filterActionsList.get(2));


        for (int x = 0; x < 9; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement timeOne : p.columnTime) {
            p.moveMouse(timeOne);
            System.out.println(timeOne.getText());
            i = i + 1;

            p.moveMouse(timeOne);
            System.out.println(timeOne.getText() + " содержит " + p.todayDate());
            Assert.assertTrue(timeOne.getText().contains(p.todayDate()));
        }
    }

    public void allReportToYearAPI() {
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        historyChangesAPI.BigDataAPI(Token);
    }

///////////////////////////////////////////Test-User////////////////////////////////////////////////////////////////////

    public void logLoginUser(String SecondName) {
        String NowDate = GlobalPage.nowDate();
        int i = -1;

        p.click(p.tabHistoryChanges);
        p.waitVisibility(p.columnTime.get(0));
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Логин");
                p.compareWebelementAndString(p.columnObject.get(i), "");
                return;

            }
        }

    }

    public void logLogoutUser(String SecondName) {
        int i = -1;

        p.click(p.listActionProfile);
        p.click(p.listActionProfileExit);
        String NowDate = GlobalPage.nowDate();
        p.waitVisibility(p.authInputLogin);
        p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        p.moveToProfileUser(SecondName);
        p.click(p.tabHistoryChanges);
        p.sleep(3000);
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 1) {
                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Логаут");
                p.compareWebelementAndString(p.columnObject.get(i), "");
                return;
            }
        }
    }

    public void logCreateUser(String Login, String Password, String Email, String Phone, String Status,
                              String SecondName, String FirstName, String MiddleName, String Superuser,
                              String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                              String Role1, String Role2) {
        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        String NowDate = GlobalPage.nowDate();
        p.moveToProfileUserID(UserID);
        p.click(p.tabHistoryChanges);
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {
                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Создание пользователя");
                p.compareWebelementAndString(p.columnObject.get(i), SecondName + " " + FirstName + " " + MiddleName);
                return;
            }
        }
    }


    public void logEditWorkerUser(String NewLogin, String NewEmail, String NewPhone, String Login, String Password,
                                  String Email, String Phone, String Status,
                                  String SecondName, String FirstName, String MiddleName, String Superuser,
                                  String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                  String Role1, String Role2) {
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);

        int i = -1;
        p.moveToProfileUserIDChange(UserID);
        p.click(p.linkEditAuthProfile);
        p.clickAndSendKeys(p.inputLogin, NewLogin);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputPhone, NewPhone);
        p.click(p.buttonSaveProfile);
        String NowDate = GlobalPage.nowDate();
        p.click(p.menuWorkers);
        p.moveToProfileUserID(UserID);
        p.click(p.tabHistoryChanges);
        p.sleep(1000);
        Assert.assertTrue(p.columnTime.size() > 0);
        for (WebElement timeOne : p.columnTime) {
            System.out.println(timeOne.getText());
            i = i + 1;
            if (timeOne.getText().equals(NowDate) & i == 0) {

                System.out.println(p.userinfoname.getText());
                Assert.assertEquals(p.columnInitiator.get(i).getText(), p.userinfoname.getText());

                p.compareWebelementAndString(p.columnAction.get(i), "Изменение профиля");
                p.compareWebelementAndString(p.columnObject.get(i), SecondName + " " + FirstName + " " + MiddleName);
                return;
            }
        }
    }

    public void logSumReportsDefoulteUser(String SecondName) {
        p.moveToProfileUser(SecondName);
        p.click(p.tabHistoryChanges);
        System.out.println("Количество строк: " + p.columnAction.size());
        Assert.assertEquals("Количество записей по-умолчанию не равно 10", 10, p.columnAction.size());
    }

    public void logLoadMoreUserUser(String SecondName) {
        p.click(p.tabHistoryChanges);

        for (int x = 0; x < 10; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());

            if (p.columnAction.size() == 100) {
                System.out.println("Необходимое количесво строк достигнуто");
            }
        }
    }

    public void popoverInfoBlockUser(String Login, String Password, String Email, String Phone, String Status,
                                     String SecondName, String FirstName, String MiddleName, String Superuser,
                                     String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                     String Role1, String Role2) {
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        System.out.println("1");
        p.click(p.listActionProfile);
        p.click(p.listActionProfileExit);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        p.auth(Login, Password);
        System.out.println("2");
        p.click(p.tabHistoryChanges);
        System.out.println("3");
        p.sleep(1000);
        p.click(p.columnInitiator.get(0));
        p.waitVisibility(p.popoverPhone);
        System.out.println("4");

        p.compareWebelementAndString(p.popoverPhone, Phone);
        p.compareWebelementAndString(p.popoverEmail, Email);
        p.click(p.popoverMore);
        p.waitVisibility(p.userinfoname);
        p.compareWebelementAndString(p.userinfoname, SecondName + " " + FirstName + " " + MiddleName);
        p.click(p.listActionProfile);
        p.click(p.listActionProfileExit);
        p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
    }

    public void moreInfoBlockLoginUser(String SecondName) {
        int i = -1;
        p.click(p.logoAll);
        p.click(p.tabHistoryChanges);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логин")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockLogoutUser(String SecondName) {
        int i = -1;
        p.click(p.tabHistoryChanges);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Логаут")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockCreateUser(String Login, String Email, String Phone,
                                        String Password, String Status, String SecondName, String FirstName,
                                        String MiddleName, String Superuser, String SendEmail, String OrgID,
                                        String Depart, String OrgStatus, String Post1,
                                        String Post2, String Role1, String Role2, String Spec1, String Spec2,
                                        String Service1, String Service2, String Regalia, String EmailCont,
                                        String PhoneCont,
                                        String Instagram, String Vk, String Whatsapp, String Viber, String Facebook,
                                        String Other) {
        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        historyChangesAPI.profileUserAPI(Token, UserID, Spec1, Spec2, Service1, Service2, Regalia, EmailCont, PhoneCont, Instagram, Vk
                , Whatsapp, Viber, Facebook, Other);
        p.moveToProfileUserID(UserID);
        p.click(p.tabHistoryChanges);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Создание пользователя")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                p.moveMouse(p.blockMoreInfoContextDeviceValue);
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());

                p.moveMouse(p.blockMoreInfoContextIPValue);
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                p.moveMouse(p.blocInfoAboutEventDate);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());

                p.moveMouse(p.blocInfoAboutEventInitiator);
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());

                p.moveMouse(p.blocInfoAboutEventAction);
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());

                p.moveMouse(p.blocInfoAboutEventObject);
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());

                p.moveMouse(p.blockMoreInfoContextIDValue);
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());

                p.moveMouse(p.blockMoreInfoContextDeviceValue);
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());

                p.moveMouse(p.blockMoreInfoContextIPValue);
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());

                p.moveMouse(p.blockMoreInfoContextLoginValue);
                System.out.println("Логин: " + p.blockMoreInfoContextLoginValue.getText());

                p.moveMouse(p.blockMoreInfoContextEmailValue);
                System.out.println("Email: " + p.blockMoreInfoContextEmailValue.getText());

                p.moveMouse(p.blockMoreInfoContextPhoneValue);
                System.out.println("Телефон: " + p.blockMoreInfoContextPhoneValue.getText());

                p.moveMouse(p.blockMoreInfoContextFirstnameValue);
                System.out.println("Фамилия: " + p.blockMoreInfoContextFirstnameValue.getText());

                p.moveMouse(p.blockMoreInfoContextSecondnameValue);
                System.out.println("Имя: " + p.blockMoreInfoContextSecondnameValue.getText());

                p.moveMouse(p.blockMoreInfoContextMidlenameValue);
                System.out.println("Отчество: " + p.blockMoreInfoContextMidlenameValue.getText());

                p.moveMouse(p.blockMoreInfoContextStatusValue);
                System.out.println("Статус: " + p.blockMoreInfoContextStatusValue.getText());

                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & !p.blockMoreInfoContextLoginValue.getText().equals("") &
                                !p.blockMoreInfoContextEmailValue.getText().equals("") & !p.blockMoreInfoContextPhoneValue.getText().equals("") &
                                !p.blockMoreInfoContextFirstnameValue.getText().equals("") &
                                !p.blockMoreInfoContextSecondnameValue.getText().equals("") &
                                !p.blockMoreInfoContextMidlenameValue.getText().equals("") &
                                !p.blockMoreInfoContextStatusValue.getText().equals("") &
                                p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.waitE_ClickableAndClickAndUp(p.body);
                p.scrollHomePage();
                p.waitE_ClickableAndClickAndUp(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void moreInfoBlockChangeUser(String Login, String Password, String Email, String Phone, String Status,
                                        String SecondName, String FirstName, String MiddleName, String Superuser,
                                        String SendEmail, String OrgID, String OrgStatus, String Post1, String Post2,
                                        String Role1, String Role2) {
        int i = -1;
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        UserID = historyChangesAPI.createUserAPI(Token, Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName,
                Superuser, SendEmail, OrgID, OrgStatus, Post1, Post2, Role1, Role2);
        p.moveToProfileUserID(UserID);
        p.click(p.tabHistoryChanges);

        for (int x = 0; x < 4; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement actionOne : p.columnAction) {
            i = i + 1;
            System.out.println(actionOne.getText());

            if (actionOne.getText().equals("Изменение профиля")) {
                p.click(p.columnTrigger.get(i));
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blockMoreInfoContextIDValue.getText().equals("")
                                & !p.blockMoreInfoContextDeviceValue.getText().equals("") & !p.blockMoreInfoContextIPValue.getText().equals("")
                                & p.blockMoreInfoContextButtonMoreInfo.isEnabled() & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                p.click(p.blockMoreInfoContextButtonMoreInfo);

                p.waitVisibility(p.blockMoreInfoContextIDValue);
                System.out.println("Дата: " + p.blocInfoAboutEventDate.getText());
                System.out.println("Инициатор: " + p.blocInfoAboutEventInitiator.getText());
                System.out.println("Действие: " + p.blocInfoAboutEventAction.getText());
                System.out.println("Объект: " + p.blocInfoAboutEventObject.getText());
                System.out.println("ID: " + p.blockMoreInfoContextIDValue.getText());
                System.out.println("Устройство: " + p.blockMoreInfoContextDeviceValue.getText());
                System.out.println("IP: " + p.blockMoreInfoContextIPValue.getText());
                Assert.assertTrue("Не все элементы присутствуют в блоке",
                        !p.blocInfoAboutEventDate.getText().equals("") & !p.blocInfoAboutEventInitiator.getText().equals("") &
                                !p.blocInfoAboutEventAction.getText().equals("") & !p.blocInfoAboutEventObject.getText().equals("") &
                                !p.blockMoreInfoContextIDValue.getText().equals("") & !p.blockMoreInfoContextDeviceValue.getText().equals("") &
                                !p.blockMoreInfoContextIPValue.getText().equals("") & p.blockMoreInfoContextButtonCopyLink.isEnabled());

                p.click(p.blockMoreInfoContextButtonCopyLink);
                return;
            }
        }
    }

    public void popoverUser(String SecondName) {
        p.preloader();
        p.click(p.tabHistoryChanges);
        p.sleep(1000);
        p.click(p.questionInitiator);

        p.compareWebelementAndString(p.popoverInitiator, "Тот кто совершает действие.");
        p.click(p.questionObject);

        p.compareWebelementAndString(p.popoverObject, "Над кем совершают действие.");
    }

    public void actionFilterUser(String SecondName) {
        String Action0 = "";
        String Action1 = "";
        String Action2 = "";
        String Action3 = "";
        String Action4 = "";
        String Action5 = "";

        int SumAction0case0 = 0;
        int SumAction0case1 = 0;
        int SumAction1case1 = 0;
        int SumAction0case2 = 0;
        int SumAction1case2 = 0;
        int SumAction2case2 = 0;
        int SumAction0case3 = 0;
        int SumAction1case3 = 0;
        int SumAction2case3 = 0;
        int SumAction3case3 = 0;
        int SumAction0case4 = 0;
        int SumAction1case4 = 0;
        int SumAction2case4 = 0;
        int SumAction3case4 = 0;
        int SumAction4case4 = 0;
        int SumAction0case5 = 0;
        int SumAction1case5 = 0;
        int SumAction2case5 = 0;
        int SumAction3case5 = 0;
        int SumAction4case5 = 0;
        int SumAction5case5 = 0;

        int i = -1;
        p.moveToProfileUser(SecondName);
        p.click(p.tabHistoryChanges);
        p.click(p.inputAction);

        for (WebElement action : p.filterActionsList) {
            p.scrollHomePage();
            p.sleep(1500);
            i = i + 1;

            if (!p.filterActionsList.get(i).isDisplayed()) {
                p.click(p.inputAction);
            }
            p.sleep(1000);
            switch (i) {
                case 0:
                    Action0 = action.getText();
                    System.out.println("Действие 0: " + Action0);
                    break;
                case 1:
                    Action1 = action.getText();
                    System.out.println("Действие 1: " + Action1);
                    break;
                case 2:
                    Action2 = action.getText();
                    System.out.println("Действие 2: " + Action2);
                    break;
                case 3:
                    Action3 = action.getText();
                    System.out.println("Действие 3: " + Action3);
                    break;
                case 4:
                    Action4 = action.getText();
                    System.out.println("Действие 4: " + Action4);
                    break;
                case 5:
                    Action5 = action.getText();
                    System.out.println("Действие 5: " + Action5);
                    break;
                case 6:
                    throw new AssertionError("Действий больше, чем проверок. Добавь парочку, абр-абр-абырвалг");
            }

            p.click(action);
            for (int x = 0; x < 9; x++) {
                p.click(p.buttonLoadMore);
                System.out.println("Количество строк: " + p.columnAction.size());
            }
            for (WebElement actionOne : p.columnAction) {
                System.out.println("i= " + i);
                switch (i) {
                    case 0:
                        p.compareWebelementAndString(actionOne, Action0);
                        if (actionOne.getText().equals(Action0)) {
                            SumAction0case0 = SumAction0case0 + 1;
                        }
                        break;

                    case 1:
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
                        p.moveMouse(actionOne);
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
        }
        System.out.println("1 действие:");
        System.out.println(Action0 + "-" + SumAction0case0);
        Assert.assertTrue(SumAction0case0 >= 1);
        System.out.println("");
        System.out.println("2 действия:");
        System.out.println(Action0 + "-" + SumAction0case1);
        Assert.assertTrue(SumAction0case1 >= 1);
        System.out.println(Action1 + "-" + SumAction1case1);
        Assert.assertTrue(SumAction1case1 >= 1);
        System.out.println("");
        System.out.println("3 действия:");
        System.out.println(Action0 + "-" + SumAction0case2);
        Assert.assertTrue(SumAction0case2 >= 1);
        System.out.println(Action1 + "-" + SumAction1case2);
        Assert.assertTrue(SumAction1case2 >= 1);
        System.out.println(Action2 + "-" + SumAction2case2);
        Assert.assertTrue(SumAction2case2 >= 1);
        System.out.println("");
        System.out.println("4 действия:");
        System.out.println(Action0 + "-" + SumAction0case3);
        Assert.assertTrue(SumAction0case3 >= 1);
        System.out.println(Action1 + "-" + SumAction1case3);
        Assert.assertTrue(SumAction1case3 >= 1);
        System.out.println(Action2 + "-" + SumAction2case3);
        Assert.assertTrue(SumAction2case3 >= 1);
        System.out.println(Action3 + "-" + SumAction3case3);
        Assert.assertTrue(SumAction3case3 >= 1);
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

//        p.moveToProfileUser(SecondName);
        p.click(p.logoAll);
        p.click(p.tabHistoryChanges);
        p.click(p.calendar);
        p.click(p.calendarToday);
        p.sleep(500);
        p.moveMouse(p.calendarTodaySelect);
        p.click(p.calendarTodaySelect);

        for (int x = 0; x < 9; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }
        p.scrollHomePage();
        for (WebElement timeOne : p.columnTime) {
            p.moveMouse(timeOne);
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + p.todayDate());
            Assert.assertTrue(timeOne.getText().contains(p.todayDate()));
        }
        p.scrollHomePage();
        p.click(p.inputAction);
        p.click(p.filterActionsList.get(2));
        p.sleep(1000);

        for (int x = 0; x < 9; x++) {
            p.click(p.buttonLoadMore);
            System.out.println("Количество строк: " + p.columnAction.size());
        }

        p.scrollHomePage();
        for (WebElement timeOne : p.columnTime) {
            p.moveMouse(timeOne);
            i = i + 1;

            System.out.println(timeOne.getText() + " содержит " + p.todayDate());
            Assert.assertTrue(timeOne.getText().contains(p.todayDate()));
        }
    }

    public void allReportToYearAPIUser() {
        Token = historyChangesAPI.loginAPI(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
        historyChangesAPI.BigDataAPIUser(Token);
    }

}
