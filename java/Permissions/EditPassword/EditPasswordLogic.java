package Permissions.EditPassword;

import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.net.MalformedURLException;
import java.net.URI;

@RunWith(JUnitParamsRunner.class)
public class EditPasswordLogic {

    private String NowDate = "";

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditPasswordPage p;

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
            p = new EditPasswordPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
        }

        @Override
        protected void finished(Description description) {
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditPassword);
        }
    };

    public void editPasswordMyself(String SecondNameIN, String FirstNameIN, String MiddleNameIN, String LoginIN,
                                   String PasswordIN, String NewPasswordIN) {
        switch (LoginIN) {
            case ("SuperAdmin2"):
                p.auth(LoginIN, PasswordIN);
                p.sleep(2000);
                p.preloader();
                p.click(p.buttonChangeProfile);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordIN);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordIN);
                p.click(p.buttonSavePassword);
                NowDate = p.nowHourMin();
                p.waitTextPresent(p.authButtonEnter, "Войти");
                p.auth(LoginIN, NewPasswordIN);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameIN + " " + FirstNameIN + " " + MiddleNameIN);
                p.switchTab(driver);
                break;

            case ("Admin2"):
                p.auth(LoginIN, PasswordIN);
                p.preloader();
                p.click(p.buttonChangeProfile);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordIN);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordIN);
                p.click(p.buttonSavePassword);
                p.auth(LoginIN, NewPasswordIN);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameIN + " " + FirstNameIN + " " + MiddleNameIN);
                break;

            case ("Spec2"):
                p.auth(LoginIN, PasswordIN);
                p.preloader();
                p.click(p.buttonChangeProfile);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputCurrentPassword, PasswordIN);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordIN);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordIN);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordIN);
                p.click(p.buttonSavePassword);
                p.auth(LoginIN, NewPasswordIN);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameIN + " " + FirstNameIN + " " + MiddleNameIN);
        }
    }

    public void recoveryPasswordMyself(String SecondNameIN, String FirstNameIN, String MiddleNameIN, String LoginIN,
                                       String PasswordIN, String EmailIN) {
        p.logout();
        p.click(p.linkForgotPassword);
        p.clickAndSendKeys(p.recoveryInputEmail, EmailIN);
        p.click(p.buttonRecoveryPassword);
        driver.close();
    }


    public void editPasswordOfSuperAdmin(String RoleIn, String SecondNameOBJ, String FirstNameOBJ,
                                         String MiddleNameOBJ, String LoginOBJ, String NewPasswordOBJ, String UserID) {
        switch (RoleIn) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.logout();
                p.switchTab(driver);
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Администратор видит кнопку редактирования Суперадмина");

                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Администратор НЕ видит кнопку редактирования Суперадмина");
                }
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                //                p.moveToProfileUserID(UserID);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопуку редактирования Суперадмина");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопуку редактирования Суперадмина");
                }
        }
    }

    public void editPasswordOfAdmin(String RoleIn, String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                    String LoginOBJ, String NewPasswordOBJ, String UserID) {
        p.logout();
        switch (RoleIn) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.sleep(3000);
                p.preloader();
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.sleep(3000);
                p.preloader();
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.sleep(3000);
                p.preloader();
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопуку редактирования Суперадмина");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопуку редактирования админа");
                }
        }
    }

    public void editPasswordOfAdminAnotherOrgTest(String RoleIn, String SecondNameOBJ, String FirstNameOBJ,
                                                  String MiddleNameOBJ, String LoginOBJ, String NewPasswordOBJ, String UserID) {
        switch (RoleIn) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Админ видит кнопуку редактирования Суперадмина");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Админ НЕ видит кнопуку редактирования админа");
                }
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопуку редактирования Суперадмина");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопку редактирования админа");
                }
        }
    }

    public void editPasswordOfSpecMyselfOrgTest(String RoleIn, String SecondNameOBJ, String FirstNameOBJ,
                                                String MiddleNameOBJ, String LoginOBJ, String NewPasswordOBJ, String UserID) {
        switch (RoleIn) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопку редактирования Суперадмина");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопку редактирования специалиста");
                }
        }
    }

    public void editPasswordOfSpecAnotherOrgTest(String RoleIn, String SecondNameOBJ, String FirstNameOBJ,
                                                 String MiddleNameOBJ, String LoginOBJ, String NewPasswordOBJ, String UserID) {
        switch (RoleIn) {
            case ("SuperAdmin"):
                p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Admin"):
                p.auth(GlobalPage.LoginAUT_A, GlobalPage.PasswordAUT_A);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопуку редактирования специалиста из другой " +
                                "организации");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопуку редактирования специалиста из другой " +
                            "организации");
                }
                break;

            case ("Spec"):
                p.auth(GlobalPage.LoginAUT_S, GlobalPage.PasswordAUT_S);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопуку редактирования специалиста из другой " +
                                "организации");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопку редактирования специалиста из другой " +
                            "организации");
                }
        }
    }

    public void cleanYandex(String Login, String Password, String Email) {
        if (Login.equals("Admin3")) {
            p.openNewTab("https://yandex.ru/");
            p.click(p.enterToPostYandex);
            p.clickAndSendKeys(p.loginFormYandex, Email);
            p.click(p.enterButtonYandex);
            p.clickAndSendKeys(p.passwordFormYandex, Password);
            p.click(p.enterButtonYandex);

            p.sleep(8000);
            p.click(p.checkboxAllLettersYandex);
            try {
                p.click(p.iconDeleteAllLettersYandex);
            } catch (TimeoutException e) {
                System.out.println("Ящик пуст");
            }
            p.changeTab(driver, 2);
            p.sleep(2000);
        }
    }

    public void findLetterYandex(String LoginIN, String Password, String Email, String SecondNameIN,
                                 String FirstNameIN) {
        if (LoginIN.equals("SuperAdmin3") || LoginIN.equals("Spec3")) {
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            return;
        }
        p.changeTab(driver, 1);
        for (int i = 0; i < 20; i++) {
            p.sleep(10000);
            driver.navigate().refresh();
            try {
                p.waitVisibility(p.nameLetterYandex);
                if (p.nameLetterYandex.getText().equals("Банк здоровья")) {
                    break;
                }
            } catch (TimeoutException | StaleElementReferenceException | NoSuchElementException e) {
                System.out.println("Письмо еще не пришло");
            }
        }

        p.waitTextPresent(p.nameLetterYandex, "Банк здоровья");
        p.waitTextPresent(p.descriptionLetterYandex, "Сброс пароля");
        p.click(p.descriptionLetterYandex);
        p.sleep(8000);
        p.waitVisibility(p.imgLogoLetter);
        Assert.assertTrue(p.imgLogoLetter.isDisplayed());
        Assert.assertTrue(p.nameLogoLetter.isDisplayed());
        p.waitTextPresent(p.headerLetterYandex, "Сброс пароля в Банке здоровья\n" +
                "Код восстановления");
        p.waitTextPresent(p.bodyLetterYandex, "Данные для сброса пароля\n" +
                "Код восстановления");
        p.waitTextPresent(p.bodyLetterYandex, "* Если вы не запрашивали сброс пароля, проигнорируйте это " +
                "письмо");
        Assert.assertTrue(!p.codeLetterYandex.getText().equals(""));
        System.out.println("Количество символов в пароле: " + p.codeLetterYandex.getText().length() + " - " +
                p.codeLetterYandex.getText());
        Assert.assertEquals(6, p.codeLetterYandex.getText().length());
        String Code = p.codeLetterYandex.getText();
        Assert.assertTrue(p.imgLogoSechenovLetter.isDisplayed());
        Assert.assertTrue(p.imgLogoLionsLetter.isDisplayed());
        p.click(p.buttonEnterLetter);
        p.closeTabSwitchTab(driver);
        p.clickAndSendKeys(p.inputRecoveryNewPassword, "111111");
        p.clickAndSendKeys(p.inputRecoveryRepeatNewPasswordi, "111111");
        p.click(p.buttonSetNewPassword);
        p.auth(LoginIN, "111111");
        p.waitTextPresent(p.userinfoname, SecondNameIN + " " + FirstNameIN);
    }

    public void cleanMail(String Login, String Password, String Email) {
        if (Login.equals("SuperAdmin6")) {
            p.openNewTab("https://mail.ru/");
            p.clickAndSendKeys(p.loginFormMailRu, Email);
            p.clickAndSendKeys(p.passwordFormMailRu, Password);
            p.click(p.enterButtonMailRu);
            p.sleep(8000);
            p.click(p.checkboxAllLettersMailRu);
            try {
                p.click(p.iconDeleteAllLettersMailRu);
            } catch (TimeoutException e) {
                System.out.println("Ящик пуст");
            }
            p.sleep(1000);
            p.changeTab(driver, 2);
        }
    }

    public void findLetterMailRu(String LoginOBJ, String NewPasswordOBJ, String EmailOBJ, String SecondNameOBJ,
                                 String FirstNameOBG) {
        if (LoginOBJ.equals("Admin7") || LoginOBJ.equals("Spec8888")) {
            return;
        }
        p.changeTab(driver, 1);
        for (int i = 0; i < 20; i++) {
            p.sleep(10000);
            driver.navigate().refresh();
            try {
                p.waitVisibility(p.nameLetterMailRu);
                if (p.nameLetterMailRu.getText().equals("Банк здоровья")) {
                    break;
                }
            } catch (TimeoutException | StaleElementReferenceException | NoSuchElementException e) {
                System.out.println("Письмо еще не пришло");
            }
        }
        p.waitTextPresent(p.nameLetterMailRu, "Банк здоровья");
        p.waitTextPresent(p.descriptionLetterMailRu, "Был изменен пароль");
        p.click(p.descriptionLetterMailRu);
        p.sleep(8000);
        Assert.assertTrue(p.imgLogoLetterMailRu.isDisplayed());
        Assert.assertTrue(p.nameLogoLetterMailRu.isDisplayed());
        p.waitTextPresent(p.headerLetterMailRu, "Новый пароль в Банке здоровья\n" +
                "Пароль был изменен");
        p.waitTextPresent(p.bodyLetterMailRu, "Данные вашего акаунта были изменены\n" +
                "Ваш пароль был изменен администратором, обратитесь к вашему непосредственному руководителю для " +
                "уточнения этой информации.\n" +
                "Войти");
        Assert.assertTrue(p.imgLogoSechenovLetterMailRu.isDisplayed());
        Assert.assertTrue(p.imgLogoLionsLetterMailRu.isDisplayed());
        p.click(p.buttonEnterLetterMailRu);
        p.closeTabSwitchTab(driver);
        p.sleep(1000);
        p.auth(LoginOBJ, NewPasswordOBJ);
        p.waitVisibility(p.userinfoname);
        System.out.println("Повторно Авторизовались с новым паролем");
        p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBG);
    }

    public void cleanGoogle(String Login, String Password, String Email) {
        if (Login.equals("SuperAdmin2")) {
            p.openNewTab("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false" +
                    "&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1" +
                    "&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
            p.sleep(3000);
            try {
                System.out.println("Вводим логин");
                p.clickAndSendKeysAndEnter(p.loginFormGoogleMail, Email);
            } catch (NoSuchElementException e) {
                driver.navigate().refresh();
                p.sleep(3000);
                System.out.println("Вводим логин");
                p.clickAndSendKeysAndEnter(p.loginFormGoogleMail, Email);
            }
            p.waitVisibility(p.passwordFormGoogleMail);
            p.clickAndSendKeysAndEnter(p.passwordFormGoogleMail, Password);
            p.sleep(8000);
            p.click(p.checkboxAllLettersGoogleMail);
            try {
                p.click(p.iconDeleteAllLettersGoogleMail);
            } catch (TimeoutException e) {
                System.out.println("Ящик пуст");
            }
            p.sleep(1000);
            p.changeTab(driver, 2);
        }
    }

    public void findLetterGoogleMail(String LoginIN, String PasswordIN, String Email, String NewPasswordIN) {
        if (LoginIN.equals("Admin2") || LoginIN.equals("Spec2")) {
            return;
        }
        p.changeTab(driver, 1);
        for (int i = 0; i < 20; i++) {
            p.sleep(10000);
            driver.navigate().refresh();
            try {
                p.waitVisibility(p.nameLetterGoogleMail);
                if (p.nameLetterGoogleMail.getText().equals("Банк здоровья")) {
                    break;
                }
            } catch (TimeoutException | StaleElementReferenceException | NoSuchElementException e) {
                System.out.println("Письмо еще не пришло");
            }
        }

        p.waitTextPresent(p.nameLetterGoogleMail, "Банк здоровья");
        p.waitTextPresent(p.descriptionLetterGoogleMail, "Был изменен пароль");
        p.click(p.descriptionLetterGoogleMail);
        p.sleep(8000);
        p.waitVisibility(p.imgLogoLetter);
        Assert.assertTrue(p.imgLogoLetter.isDisplayed());
        Assert.assertTrue(p.nameLogoLetter.isDisplayed());
        p.waitTextPresent(p.headerLetter, "Новый пароль в Банке здоровья\n" +
                "Пароль был изменен");
        p.waitTextPresent(p.bodyLetter, "Данные вашего акаунта были изменены\n" +
                "Ваш пароль был изменен администратором, обратитесь к вашему непосредственному руководителю для " +
                "уточнения этой информации.\n" +
                "Войти");
        Assert.assertTrue(p.imgLogoSechenovLetter.isDisplayed());
        Assert.assertTrue(p.imgLogoLionsLetter.isDisplayed());
        p.click(p.buttonEnterLetter);
        p.closeTabSwitchTab(driver);
        p.sleep(2000);
        p.auth(LoginIN, NewPasswordIN);
        p.waitVisibility(p.userinfoname);
        Assert.assertEquals(ConfigProperties.getTestProperty("baseurl") + "/#/profile/summary",
                driver.getCurrentUrl());
    }

    public void editPasswordOfUserHighLevelOrg(String RoleIN, String LoginIN, String PasswordIN, String SecondNameOBJ,
                                               String FirstNameOBJ, String MiddleNameOBJ, String LoginOBJ,
                                               String NewPasswordOBJ, String UserID) {
        p.logout();
        switch (RoleIN) {
            case ("SuperAdmin"):
                p.auth(LoginIN, PasswordIN);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Admin"):
                p.auth(LoginIN, PasswordIN);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Админ видит кнопку редактирования вышестоящего админа");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопку редактирования вышестоящего админа");
                }
                break;

            case ("Spec"):
                p.auth(LoginIN, PasswordIN);
                p.moveToProfileUserID(UserID);
                p.sleep(5000);
                try {
                    if (p.buttonChangeProfile.isDisplayed()) {
                        throw new AssertionError("Специалист видит кнопку редактирования вышестоящего админа");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Все ОК: Специалист НЕ видит кнопку редактирования вышестоящего админа");
                }
        }
    }

    public void adminSpecEditPasswordOfSpecMyselfOrg(String RoleIN, String LoginIN, String PasswordIN,
                                                     String SecondNameOBJ, String FirstNameOBJ, String MiddleNameOBJ,
                                                     String LoginOBJ, String NewPasswordOBJ, String UserID) {
        p.logout();
        switch (RoleIN) {
            case ("SuperAdmin"):
                break;

            case ("Admin"):
                p.auth(LoginIN, PasswordIN);
                p.moveToProfileUserIDChange(UserID);
                p.click(p.linkEditPasswordProfile);
                p.clickAndSendKeys(p.inputNewPassword, NewPasswordOBJ);
                p.clickAndSendKeys(p.inputRepeatNewPassword, NewPasswordOBJ);
                p.click(p.buttonSavePassword);
                p.auth(LoginOBJ, NewPasswordOBJ);
                p.waitVisibility(p.userinfoname);
                System.out.println("Авторизовались с новым паролем");
                p.waitTextPresent(p.userinfoname, SecondNameOBJ + " " + FirstNameOBJ + " " + MiddleNameOBJ);
                break;

            case ("Spec"):
        }
    }
}



