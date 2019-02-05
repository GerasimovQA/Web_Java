package User.EditUser;

import Global.Ashot;
import Global.Capa;
import Global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigProperties;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class EditUserLogic {
    public EditUserAPI editUserAPI = new EditUserAPI();

    int u = 0;

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private RemoteWebDriver driver;
    private EditUserPage p;
    private ArrayList<String> SelectedSpecialtys = new ArrayList<>();
    private ArrayList<String> SavedSpecialtys = new ArrayList<>();
    private ArrayList<String> ListRoles = new ArrayList<>();

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
            driver.setFileDetector(new LocalFileDetector());
            p = new EditUserPage(driver);
            driver.manage().window().setSize(Capa.getDimension());
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            p.auth(GlobalPage.LoginAUT_SA, GlobalPage.PasswordAUT_SA);

        }

        @Override
        protected void finished(Description description) {
//            driver.quit();
            tlDriver.get().quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            p.captureScreenshot(description.getMethodName(), GlobalPage.failedTestsEditUser);
        }
    };

    public void editWorkerFio(String SecondName, String NewSecondName, String NewFirstName, String NewMiddleName
            , String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Secondname = p.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = p.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = p.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        p.click(p.linkEditfioProfile);
        p.clickAndSendKeys(p.inputSecondName, NewSecondName);
        p.clickAndSendKeys(p.inputFirstName, NewFirstName);
        p.clickAndSendKeys(p.inputMiddleName, NewMiddleName);
        p.click(p.buttonSaveProfile);
        p.waitVisibility(p.firstNameProfile);
        p.sleep(500);

        System.out.println(p.secondNameProfile.getText() + " = " + NewSecondName);
        Assert.assertEquals(p.secondNameProfile.getText(), NewSecondName);

        System.out.println(p.firstNameProfile.getText() + " = " + NewFirstName);
        Assert.assertEquals(p.firstNameProfile.getText(), NewFirstName);

        System.out.println(p.middleNameProfile.getText() + " = " + NewMiddleName);
        Assert.assertEquals(p.middleNameProfile.getText(), NewMiddleName);
//Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(NewSecondName + " " + NewFirstName + " " + NewMiddleName, p.profFio);
    }

    public void editWorkerFioCancel(String SecondName, String NewSecondName, String NewFirstName,
                                    String NewMiddleName, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Secondname = p.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = p.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = p.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        p.click(p.linkEditfioProfile);
        p.clickAndSendKeys(p.inputSecondName, NewSecondName);
        p.clickAndSendKeys(p.inputFirstName, NewFirstName);
        p.clickAndSendKeys(p.inputMiddleName, NewMiddleName);
        p.click(p.buttonCancelProfile);
        p.waitVisibility(p.firstNameProfile);
        System.out.println(p.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(p.secondNameProfile.getText(), Secondname);

        System.out.println(p.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(p.firstNameProfile.getText(), Firstname);

        System.out.println(p.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertEquals(p.middleNameProfile.getText(), Middlename);
        //Проверка отсутствия сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Secondname + " " + Firstname + " " + Middlename, p.profFio);
    }

    public void editWorkerFioDeleteMiddlename(String SecondName, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Secondname = p.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = p.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = p.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        p.click(p.linkEditfioProfile);
        p.click(p.inputMiddleName);
//        p.inputMiddleName.clear();
        p.inputMiddleName.sendKeys(Keys.HOME, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE);
//        p.clickAndSendKeys(p.inputMiddleName, "");
        p.sleep(3000);
        p.click(p.buttonSaveProfileFIO);
        p.waitVisibility(p.firstNameProfile);
        p.sleep(5000);
        System.out.println(p.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(p.secondNameProfile.getText(), Secondname);

        System.out.println(p.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(p.firstNameProfile.getText(), Firstname);

        try {
            Assert.assertTrue("Отчество найдено", !p.middleNameProfile.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Отчество не найдено и это хорошо");
        }
        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Secondname + " " + Firstname, p.profFio);
    }

    public void editWorkerFioDeleteMiddlenameCancel(String SecondName, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Secondname = p.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = p.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = p.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        p.click(p.linkEditfioProfile);
        p.click(p.inputMiddleName);
        p.inputMiddleName.clear();
        p.click(p.buttonCancelProfile);
        p.waitVisibility(p.firstNameProfile);

        System.out.println(p.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(p.secondNameProfile.getText(), Secondname);

        System.out.println(p.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(p.firstNameProfile.getText(), Firstname);

        System.out.println(p.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertEquals(p.middleNameProfile.getText(), Middlename);

        //Проверка отсутствия сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Secondname + " " + Firstname + " " + Middlename, p.profFio);
    }

    public void editWorkerLoginEmailPhone(String SecondName, String NewLogin, String NewEmail, String NewPhone,
                                          String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Login = p.loginProfile.getText();
        System.out.println("Существующий логин: " + Login);

        String Email = p.emailProfile.getText();
        System.out.println("Существующий Email: " + Email);

        String Phone = p.phoneProfile.getText();
        System.out.println("Существующий номер телефона: " + Phone);

        p.click(p.linkEditAuthProfile);
        p.clickAndSendKeys(p.inputLogin, NewLogin);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputPhone, NewPhone);
        p.click(p.buttonSaveProfile);
        p.waitVisibility(p.loginProfile);
        p.sleep(2000);

        p.compareStringAndWebelement(NewLogin, p.loginProfile);

        p.compareStringAndWebelement(NewEmail, p.emailProfile);

        p.compareStringAndWebelement("+ " + NewPhone.charAt(0) + " (" + NewPhone.charAt(1) + NewPhone.charAt(2) +
                        NewPhone.charAt(3) + ") " + NewPhone.charAt(4) + NewPhone.charAt(5) + NewPhone.charAt(6) + "-" +
                        NewPhone.charAt(7) + NewPhone.charAt(8) + "-" + NewPhone.charAt(9) + NewPhone.charAt(10),
                p.phoneProfile);

        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(NewLogin, p.profAccLogin);
        p.compareStringAndWebelement(NewEmail, p.profAccEmail);
        p.compareStringAndWebelement("+ " + NewPhone.charAt(0) + " (" + NewPhone.charAt(1) + NewPhone.charAt(2) +
                NewPhone.charAt(3) + ") " + NewPhone.charAt(4) + NewPhone.charAt(5) + NewPhone.charAt(6) + "-" +
                NewPhone.charAt(7) + NewPhone.charAt(8) + "-" + NewPhone.charAt(9) + NewPhone.charAt(10), p.profAccPhone);
    }

    public void editWorkerLoginEmailPhoneCancel(String SecondName, String NewLogin, String NewEmail, String NewPhone,
                                                String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Login = p.loginProfile.getText();
        System.out.println("Существующий логин: " + Login);

        String Email = p.emailProfile.getText();
        System.out.println("Существующий Email: " + Email);

        String Phone = p.phoneProfile.getText();
        System.out.println("Существующий номер телефона: " + Phone);

        p.click(p.linkEditAuthProfile);
        p.clickAndSendKeys(p.inputLogin, NewLogin);
        p.clickAndSendKeys(p.inputEmail, NewEmail);
        p.clickAndSendKeys(p.inputPhone, NewPhone);
        p.click(p.buttonCancelProfile);
        p.waitVisibility(p.loginProfile);
        p.sleep(2000);

        p.compareWebelementAndString(p.loginProfile, Login);
        p.compareWebelementAndString(p.emailProfile, Email);
        p.compareWebelementAndString(p.phoneProfile, Phone);
        //Проверка отсутствия сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Login, p.profAccLogin);
        p.compareStringAndWebelement(Email, p.profAccEmail);
        p.compareStringAndWebelement(Phone, p.profAccPhone);
    }

    public void editWorkerPassword(String SecondName, String Login, String NewPassword, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.linkEditPasswordProfile);
        p.clickAndSendKeys(p.inputNewPassword, NewPassword);
        p.clickAndSendKeys(p.inputRepeatNewPassword, NewPassword);
        p.click(p.buttonSavePassword);
//        p.waitVisibility(p.helperProfileChange);
        p.scrollHomePage();
        p.sleep(5000);
        System.out.println("Поменяли пароль");
//        p.click(p.listActionProfile);
//        p.click(p.listActionProfileExit);
//        driver.get(ConfigProperties.getTestProperty("baseurl"));
        p.auth(Login, NewPassword);
        p.waitVisibility(p.userinfoname);
        System.out.println("Авторизовались с новым паролем");

    }

    public void editWorkerPasswordCancel(String SecondName, String Login, String OldPassword, String NewPassword,
                                         String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.linkEditPasswordProfile);
        p.clickAndSendKeys(p.inputNewPassword, NewPassword);
        p.clickAndSendKeys(p.inputRepeatNewPassword, NewPassword);
        p.click(p.buttonCancelPassword);
        p.click(p.listActionProfile);
        p.click(p.listActionProfileExit);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        p.auth(Login, OldPassword);
        p.waitVisibility(p.userinfoname);
    }

    public void editWorkerPasswordShowPassword(String SecondName, String NewPassword, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.linkEditPasswordProfile);
        p.clickAndSendKeys(p.inputNewPassword, NewPassword);
        p.clickAndSendKeys(p.inputRepeatNewPassword, NewPassword);
        p.moveMouse(p.notShowPassword);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 800, 2000);
        p.click(p.notShowPassword);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 800, 2000);
        File fileExpected = new File(GlobalPage.expectedDir + GlobalPage.ScreenshotAshot + ".png");
        File fileActual = new File(GlobalPage.actualDir + GlobalPage.ScreenshotAshot + ".png");
        File filediff = new File(GlobalPage.diffDir + GlobalPage.ScreenshotAshot + ".png");
        fileExpected.delete();
        fileActual.delete();
//        filediff.delete();
    }

    public void editWorkerStatus(String SecondName, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Status = p.statusProfile.getText();
        System.out.println("Status= " + Status);
        p.click(p.linkEditStatusProfile);
        p.click(p.listStatus);

        if (Status.equals("Активный")) {
            p.click(p.disbleStatus);
        } else {
            p.click(p.activeStatus);
        }
        p.click(p.buttonSaveStatus);
        p.sleep(1000);
        System.out.println("NewStatus= " + p.statusProfile.getText());

        if (Status.equals("Активный")) {
            p.compareStringAndWebelement("Отключен", p.statusProfile);
//            assertEquals("Статус не изменился", "Отключен", p.statusProfile.getText());
            System.out.println("Статус изменился");
        } else {
            p.compareStringAndWebelement("Активен", p.statusProfile);
//            assertEquals("Статус не изменился", "Активен", p.statusProfile.getText());
            System.out.println("Статус изменился");
        }
        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        if (Status.equals("Активный")) {
            p.compareStringAndWebelement("Отключен", p.profSystemStatus);
//            assertEquals("Статус не изменился", "Отключен", p.statusProfile.getText());
            System.out.println("Статус изменился");
        } else {
            p.compareStringAndWebelement("Активен", p.profSystemStatus);
//            assertEquals("Статус не изменился", "Активен", p.statusProfile.getText());
            System.out.println("Статус изменился");
        }
    }

    public void editWorkerStatusCancel(String SecondName, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        String Status = p.statusProfile.getText();
        System.out.println("Status= " + Status);
        p.click(p.linkEditStatusProfile);
        p.click(p.listStatus);

        if (Status.equals("Активный")) {
            p.click(p.disbleStatus);
        } else {
            p.click(p.activeStatus);
        }
        p.click(p.buttonCancelStatus);
        p.sleep(1000);
        p.compareStringAndWebelement(Status, p.statusProfile);
//        System.out.println("NewStatus= " + p.statusProfile.getText());
//
//        assertEquals("Статус изменился", p.statusProfile.getText(), Status);
        System.out.println("Статус Не изменился");
        //Проверка отсутствия сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Status, p.profSystemStatus);

    }

    public void editWorkerPhoto(String SecondName, String Photo, String UserID) {
        p.moveToProfileUserID(UserID);
        p.waitVisibility(p.buttonChangeProfile);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 10000, 15000);
        p.click(p.buttonChangeProfile);
        p.click(p.specialistData);
        p.waitVisibility(p.photoProfile);
        p.click(p.linkEditPhotoProfile);
        p.uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        p.click(p.buttonSavePhotoProfile);
        p.waitVisibility(p.photoProfile);
        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.waitVisibility(p.buttonChangeProfile);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 10000, 15000);
    }

    public void editWorkerPhotoCancel(String SecondName, String Photo, String UserID) {
        p.moveToProfileUserID(UserID);
        p.waitVisibility(p.buttonChangeProfile);
        p.mouseHoverOn(p.buttonChangeProfile);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 0, 1);
        p.click(p.buttonChangeProfile);
        p.click(p.specialistData);
        p.waitVisibility(p.photoProfile);
        p.click(p.linkEditPhotoProfile);
        p.uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        p.click(p.buttonSavePhotoCancelProfile);
        p.waitVisibility(p.photoProfile);
        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.waitVisibility(p.buttonChangeProfile);
        p.mouseHoverOn(p.buttonChangeProfile);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 0, 1);
    }

    public void editWorkerEducation(String SecondName, String NewEducation, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistData);

        String Education = p.educationProfile.getText();
        System.out.println("Существующее образование: " + Education);

        p.click(p.linkEditEducationProfile);
        p.clickAndSendKeys(p.inputEducation, NewEducation);
        p.click(p.buttonSaveEducation);
        p.sleep(500);
        System.out.println(p.educationProfile.getText() + " = " + NewEducation);
        assertEquals(p.educationProfile.getText(), NewEducation);
        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(NewEducation, p.profEducation);
    }

    public void editWorkerEducationCancel(String SecondName, String NewEducation, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistData);

        String Education = p.educationProfile.getText();
        System.out.println("Существующее образование: " + Education);

        p.click(p.linkEditEducationProfile);
        p.clickAndSendKeys(p.inputEducation, NewEducation);
        p.click(p.buttonCancelEducation);
        p.sleep(500);

        System.out.println(p.educationProfile.getText() + " = " + Education);
        assertEquals(p.educationProfile.getText(), Education);
        //Проверка отсутствия сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(Education, p.profEducation);
    }

    public void editWorkerCommunicationMethodsProfile(String SecondName, String NewEmailContact,
                                                      String NewPhoneContact, String NewInstagram, String NewVk,
                                                      String NewWhatsapp, String NewViber, String NewFacebook,
                                                      String NewOther, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistData);

        String EmailContact = p.emailContactProfile.getText();
        System.out.println("Существующий Email: " + EmailContact);

        String PhoneContact = p.phoneContactProfile.getText();
        System.out.println("Существующий номер телефона: " + PhoneContact);

        String InstagramContact = p.instagramContactProfile.getText();
        System.out.println("Существующий Instagram: " + InstagramContact);

        String VkContact = p.vkContactProfile.getText();
        System.out.println("Существующий Vk: " + VkContact);

        String WhatsappContact = p.whatsappContactProfile.getText();
        System.out.println("Существующий Whatsapp: " + WhatsappContact);

        String ViberContact = p.viberContactProfile.getText();
        System.out.println("Существующий Viber: " + ViberContact);

        String FacebookContact = p.facebookContactProfile.getText();
        System.out.println("Существующий Facebook: " + FacebookContact);

        String OtherContact = p.otherContactProfile.getText();
        System.out.println("Существующее Другое: " + OtherContact);

        p.click(p.linkEditCommunicationMethodsProfile);
        p.clickAndSendKeys(p.inputEmailContactProfile, NewEmailContact);
        p.clickAndSendKeys(p.inputPhoneContactProfile, NewPhoneContact);
        p.clickAndSendKeys(p.inputInstagramContactProfile, NewInstagram);
        p.clickAndSendKeys(p.inputVkContactProfile, NewVk);
        p.clickAndSendKeys(p.inputWhatsappContactProfile, NewWhatsapp);
        p.clickAndSendKeys(p.inputViberContactProfile, NewViber);
        p.clickAndSendKeys(p.inputFacebookContactProfile, NewFacebook);
        p.clickAndSendKeys(p.inputOtherContactProfile, NewOther);
        p.click(p.buttonSaveCommunicationMethodsProfile);
        p.waitVisibility(p.emailContactProfile);
        p.sleep(500);

        p.compareStringAndWebelement(NewEmailContact, p.emailContactProfile);
//        System.out.println(p.emailContactProfile.getText() + " = " + NewEmailContact);
//        assertEquals(p.emailContactProfile.getText(), NewEmailContact);

        p.compareStringAndWebelement("+ " + NewPhoneContact.charAt(0) + " (" + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + NewPhoneContact.charAt(3) + ") " + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + NewPhoneContact.charAt(6) + "-" + NewPhoneContact.charAt(7) + NewPhoneContact.charAt(8) + "-" + NewPhoneContact.charAt(9) + NewPhoneContact.charAt(10), p.phoneContactProfile);
//        System.out.println(p.phoneContactProfile.getText() + " = " + "+ " + NewPhoneContact.charAt(0) + " (" + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + NewPhoneContact.charAt(3) + ") " + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + NewPhoneContact.charAt(6) + "-" + NewPhoneContact.charAt(7) + NewPhoneContact.charAt(8) + "-" + NewPhoneContact.charAt(9) + NewPhoneContact.charAt(10));
//        assertEquals(p.phoneContactProfile.getText(),
//                "+ " + NewPhoneContact.charAt(0) + " (" + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + NewPhoneContact.charAt(3) + ") " + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + NewPhoneContact.charAt(6) + "-" + NewPhoneContact.charAt(7) + NewPhoneContact.charAt(8) + "-" + NewPhoneContact.charAt(9) + NewPhoneContact.charAt(10));

//        System.out.println(p.phoneContactProfile.getText() + " = " + NewPhoneContact);
//        Assert.assertTrue(p.phoneContactProfile.getText().equals(NewPhoneContact));

        p.compareStringAndWebelement(NewInstagram, p.instagramContactProfile);
//        System.out.println(p.instagramContactProfile.getText() + " = " + NewInstagram);
//        assertEquals(p.instagramContactProfile.getText(), NewInstagram);

        p.compareStringAndWebelement(NewVk, p.vkContactProfile);
//        System.out.println(p.vkContactProfile.getText() + " = " + NewVk);
//        assertEquals(p.vkContactProfile.getText(), NewVk);

        p.compareStringAndWebelement(NewWhatsapp, p.whatsappContactProfile);
//        System.out.println(p.whatsappContactProfile.getText() + " = " + NewWhatsapp);
//        assertEquals(p.whatsappContactProfile.getText(), NewWhatsapp);

        p.compareStringAndWebelement(NewViber, p.viberContactProfile);
//        System.out.println(p.viberContactProfile.getText() + " = " + NewViber);
//        assertEquals(p.viberContactProfile.getText(), NewViber);

        p.compareStringAndWebelement(NewFacebook, p.facebookContactProfile);
//        System.out.println(p.facebookContactProfile.getText() + " = " + NewFacebook);
//        assertEquals(p.facebookContactProfile.getText(), NewFacebook);

        p.compareStringAndWebelement(NewOther, p.otherContactProfile);
//        System.out.println(p.otherContactProfile.getText() + " = " + NewOther);
//        assertEquals(p.otherContactProfile.getText(), NewOther);

        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(NewEmailContact, p.profEmail);
        p.compareStringAndWebelement("+ " + NewPhoneContact.charAt(0) + " (" + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + NewPhoneContact.charAt(3) + ") " + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + NewPhoneContact.charAt(6) + "-" + NewPhoneContact.charAt(7) + NewPhoneContact.charAt(8) + "-" + NewPhoneContact.charAt(9) + NewPhoneContact.charAt(10), p.profContPhone);
        p.compareStringAndWebelement(NewInstagram, p.profInstagram);
        p.compareStringAndWebelement(NewVk, p.profVk);
        p.compareStringAndWebelement(NewWhatsapp, p.profWhatsapp);
        p.compareStringAndWebelement(NewViber, p.profViber);
        p.compareStringAndWebelement(NewFacebook, p.profFacebook);
        p.compareStringAndWebelement(NewOther, p.profOther);
    }

    public void editWorkerCommunicationMethodsProfileCancel(String SecondName, String NewEmailContact,
                                                            String NewPhoneContact, String NewInstagram, String NewVk,
                                                            String NewWhatsapp, String NewViber, String NewFacebook,
                                                            String NewOther, String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistData);

        String EmailContact = p.emailContactProfile.getText();
        System.out.println("Существующий Email: " + EmailContact);

        String PhoneContact = p.phoneContactProfile.getText();
        System.out.println("Существующий номер телефона: " + PhoneContact);

        String InstagramContact = p.instagramContactProfile.getText();
        System.out.println("Существующий Instagram: " + InstagramContact);

        String VkContact = p.vkContactProfile.getText();
        System.out.println("Существующий Vk: " + VkContact);

        String WhatsappContact = p.whatsappContactProfile.getText();
        System.out.println("Существующий Whatsapp: " + WhatsappContact);

        String ViberContact = p.viberContactProfile.getText();
        System.out.println("Существующий Viber: " + ViberContact);

        String FacebookContact = p.facebookContactProfile.getText();
        System.out.println("Существующий Facebook: " + FacebookContact);

        String OtherContact = p.otherContactProfile.getText();
        System.out.println("Существующее Другое: " + OtherContact);

        p.click(p.linkEditCommunicationMethodsProfile);
        p.clickAndSendKeys(p.inputEmailContactProfile, NewEmailContact);
        p.clickAndSendKeys(p.inputPhoneContactProfile, NewPhoneContact);
        p.clickAndSendKeys(p.inputInstagramContactProfile, NewInstagram);
        p.clickAndSendKeys(p.inputVkContactProfile, NewVk);
        p.clickAndSendKeys(p.inputWhatsappContactProfile, NewWhatsapp);
        p.clickAndSendKeys(p.inputViberContactProfile, NewViber);
        p.clickAndSendKeys(p.inputFacebookContactProfile, NewFacebook);
        p.clickAndSendKeys(p.inputOtherContactProfile, NewOther);
        p.click(p.buttonCancelCommunicationMethodsProfile);
        p.waitVisibility(p.emailContactProfile);
        p.sleep(500);

        p.compareStringAndWebelement(EmailContact, p.emailContactProfile);
//        System.out.println(p.emailContactProfile.getText() + " = " + EmailContact);
//        assertEquals(p.emailContactProfile.getText(), EmailContact);

        p.compareStringAndWebelement(PhoneContact, p.phoneContactProfile);
//        System.out.println(p.phoneContactProfile.getText() + " = " + PhoneContact);
//        assertEquals(p.phoneContactProfile.getText(), PhoneContact);

        p.compareStringAndWebelement(InstagramContact, p.instagramContactProfile);
//        System.out.println(p.instagramContactProfile.getText() + " = " + InstagramContact);
//        assertEquals(p.instagramContactProfile.getText(), InstagramContact);

        p.compareStringAndWebelement(VkContact, p.vkContactProfile);
//        System.out.println(p.vkContactProfile.getText() + " = " + VkContact);
//        assertEquals(p.vkContactProfile.getText(), VkContact);

        p.compareStringAndWebelement(WhatsappContact, p.whatsappContactProfile);
//        System.out.println(p.whatsappContactProfile.getText() + " = " + WhatsappContact);
//        assertEquals(p.whatsappContactProfile.getText(), WhatsappContact);

        p.compareStringAndWebelement(ViberContact, p.viberContactProfile);
//        System.out.println(p.viberContactProfile.getText() + " = " + ViberContact);
//        assertEquals(p.viberContactProfile.getText(), ViberContact);

        p.compareStringAndWebelement(FacebookContact, p.facebookContactProfile);
//        System.out.println(p.facebookContactProfile.getText() + " = " + FacebookContact);
//        assertEquals(p.facebookContactProfile.getText(), FacebookContact);

        p.compareStringAndWebelement(OtherContact, p.otherContactProfile);
//        System.out.println(p.otherContactProfile.getText() + " = " + OtherContact);
//        assertEquals(p.otherContactProfile.getText(), OtherContact);

        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        p.compareStringAndWebelement(EmailContact, p.profEmail);
        p.compareStringAndWebelement(PhoneContact, p.phoneContactProfile);
        p.compareStringAndWebelement(InstagramContact, p.profInstagram);
        p.compareStringAndWebelement(VkContact, p.profVk);
        p.compareStringAndWebelement(WhatsappContact, p.profWhatsapp);
        p.compareStringAndWebelement(ViberContact, p.profViber);
        p.compareStringAndWebelement(FacebookContact, p.profFacebook);
        p.compareStringAndWebelement(OtherContact, p.profOther);
    }

    public void editWorkerWorkplaces(String SecondName, String NewPost, String NewDepart, String Post1, String UserID) {
//        Редактирование места работы и специальности
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistWorkpaleces);
        p.click(p.linkEditWorkplaces.get(0));
        p.sleep(500);
        switch (SecondName) {
            case ("Мистеров"):
                p.clickAndSendKeys(p.inputPosition, NewPost);
                p.inputPosition.sendKeys(Keys.ENTER);
                p.sleep(1000);
                break;
            case ("Цаплин"):
                p.clickAndSendKeys(p.inputPosition, NewPost);
                p.inputPosition.sendKeys(Keys.ENTER);
                p.sleep(1000);
                break;
            default:
                throw new AssertionError("Пропущен блок 'Выбор должности'");
        }

        p.click(p.inputRoleEmployee);
        switch (SecondName) {
            case ("Мистеров"):
                p.click(p.selectWorkerRoleSpecialist);
                p.click(p.userinfoname);
                p.click(p.checkBoxAmbulance);
                p.click(p.userinfoname);
                p.click(p.buttonSaveWorkplace.get(1));
                break;

            case ("Цаплин"):
                p.click(p.iconDeleteRole.get(1));
                p.click(p.userinfoname);
                p.click(p.buttonSaveWorkplace.get(1));
                break;
            default:
                throw new AssertionError("Пропущен блок 'Выбор роли и амб.приема'");
        }
//        Второе место работы для Цаплина
        if (SecondName.equals("Цаплин")) {
            p.click(p.buttonAddWorkplace);
            p.click(p.structureOrganizations);
            p.clickAndSendKeys(p.inputSearchOrganizations, "Степашин");
            p.click(p.bulletStepashin3);


            p.clickAndSendKeys(p.inputPosition, "Хворелеч");
            p.inputPosition.sendKeys(Keys.ENTER);
            p.sleep(1000);
            p.click(p.inputRoleEmployee);
            p.click(p.selectWorkerRoleAdmin);
            p.click(p.selectWorkerRoleSpecialist);
            p.click(p.userinfoname);
            p.click(p.buttonAddNewWorkplace);
        }

        p.click(p.body);
        p.scrollEndPage();
        p.click(p.linkChangeSpeciality);
        p.click(p.listSpecialty);

        switch (SecondName) {
            case ("Мистеров"):
                p.click(p.listWorkerSpecialtyMed);
                p.click(p.workerSpecialtyLor);
                p.click(p.workerSpecialtyDentist);
                p.click(p.userinfoname);
                p.sleep(1000);

                for (WebElement Speciality : p.selectedWorkerSpecialty) {
                    SelectedSpecialtys.add(Speciality.getText().replace(",", ""));
                }
                System.out.println("Выбранны специальности: " + SelectedSpecialtys);
                break;

            case ("Цаплин"):
                p.click(p.listWorkerSpecialtyMed);
                p.click(p.workerSpecialtyLor);
                p.click(p.userinfoname);
                p.sleep(1000);

                for (WebElement Speciality : p.selectedWorkerSpecialty) {
                    SelectedSpecialtys.add(Speciality.getText().replace(",", ""));
                }
                System.out.println("Выбранны специальности: " + SelectedSpecialtys);
                break;
            default:
                throw new AssertionError("Пропущен блок 'Выбор специальности'");
        }

        p.click(p.linkSaveSpeciality);
        p.waitVisibility(p.workplace.get(0));
        p.preloader();

//Проверка сохраненных места работы и специальности
        switch (SecondName) {
            case ("Мистеров"):
                p.compareStringAndWebelement(NewDepart, p.workplace.get(0));
                p.compareStringAndWebelement(Post1 + "\n" + ", \n" + NewPost, p.post.get(0));
                for (WebElement Speciality : p.savedWorkerSpecialty) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareStringAndWebelement("Администратор\n" + ", \n" + "Специалист", p.role.get(0));
                p.waitTextPresent(p.ambulance, "Да");
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 2);
                break;

            case ("Цаплин"):
                p.compareStringAndWebelement("ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)", p.workplace.get(0));
                p.compareStringAndWebelement(Post1 + "\n" + ", \nЛекарь", p.post.get(0));
                p.compareStringAndWebelement("Администратор", p.role.get(0));
                p.waitTextPresent(p.ambulance, "Нет");

                p.compareStringAndWebelement("ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\n" + " >\n" + "Клиника имени Степашина\n" + " >\n" + "1 подразделение Степашина\n" + " >\n" +
                        "2 подразделение Степашина\n" + " >\n" + "3 подразделение Степашина младшего", p.workplace.get(1));
                p.compareStringAndWebelement("Хворелеч", p.post.get(1));
                p.compareStringAndWebelement("Администратор\n" + ", \n" + "Специалист", p.role.get(1));
                p.waitTextPresent(p.ambulance, "Нет");

                for (WebElement Speciality : p.savedWorkerSpecialty) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 3);
                break;

            default:
                throw new AssertionError("Пропущен блок 'Проверка сохраненных данных'");
        }
//        Редактирование места работы и специальности
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistWorkpaleces);
        p.sleep(500);
        p.click(p.linkEditWorkplaces.get(0));
        switch (SecondName) {
            case ("Мистеров"):
                p.click(p.checkBoxAmbulance);
                p.click(p.buttonSaveWorkplace.get(1));
                p.waitVisibility(p.workplace.get(0));
                p.preloader();
                break;
            case ("Цаплин"):
                p.click(p.linkDeleteWorkplaces);
                p.click(p.linkConfirmDeleteWorkplaces);
                break;
            default:
                throw new AssertionError("Пропущен блок 'Выбор должности'");
        }
        //Проверка сохраненных места работы и специальности
        SavedSpecialtys.clear();
        switch (SecondName) {
            case ("Мистеров"):
                p.compareStringAndWebelement(NewDepart, p.workplace.get(0));
                p.compareStringAndWebelement(Post1 + "\n" + ", \n" + NewPost, p.post.get(0));
                for (WebElement Speciality : p.savedWorkerSpecialty) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareStringAndWebelement("Администратор\n" + ", \n" + "Специалист", p.role.get(0));
                p.waitTextPresent(p.ambulance, "Нет");
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 2);
                break;

            case ("Цаплин"):
                p.compareStringAndWebelement("ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\n" + " >\n" + "Клиника имени Степашина\n" + " >\n" + "1 подразделение Степашина\n" + " >\n" +
                        "2 подразделение Степашина\n" + " >\n" + "3 подразделение Степашина младшего", p.workplace.get(0));
                p.compareStringAndWebelement("Хворелеч", p.post.get(0));
                p.compareStringAndWebelement("Администратор\n" + ", \n" + "Специалист", p.role.get(0));
                p.waitTextPresent(p.ambulance, "Нет");

                for (WebElement Speciality : p.savedWorkerSpecialty) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 3);
                break;

            default:
                throw new AssertionError("Пропущен блок 'Проверка сохраненных данных'");
        }


        //Проверка сохранения изменений
        p.moveToProfileUserID(UserID);
        SavedSpecialtys.clear();
        switch (SecondName) {
            case ("Мистеров"):
                ListRoles.add("Администратор\n" + " ");
                ListRoles.add(" ");
                ListRoles.add("Специалист");
                p.compareStringAndWebelement(NewDepart, p.listProfNameWorkspace.get(0));
                p.compareStringAndWebelement(Post1 + "\n" + ", \n" + NewPost, p.listProfPosition.get(0));
                for (WebElement Speciality : p.listProfSpeciality) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareArrayListAndWebelementsList(ListRoles, p.listProfRole, 3);
                p.waitTextPresent(p.listProfAmbulanceNot.get(0), "Нет");
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 2);
                break;

            case ("Цаплин"):
                ListRoles.add("Администратор\n" + " ");
                ListRoles.add(" ");
                ListRoles.add("Специалист");
                p.compareStringAndWebelement("ФГАОУ ВО Первый МГМУ им. И.М. Сеченова Минздрава России (Сеченовский Университет)\n" +
                        " >\n" + "Клиника имени Степашина\n" + " >\n" + "1 подразделение Степашина\n" + " >\n" +
                        "2 подразделение Степашина\n" + " >\n" + "3 подразделение Степашина младшего", p.listProfNameWorkspace.get(0));
                p.compareStringAndWebelement("Хворелеч", p.listProfPosition.get(0));
                for (WebElement Speciality : p.listProfSpeciality) {
                    if (!Speciality.getText().equals(",")) {
                        SavedSpecialtys.add(Speciality.getText().replace(",", ""));
                    }
                }
                p.compareArrayListAndWebelementsList(ListRoles, p.listProfRole, 3);
                p.waitTextPresent(p.listProfAmbulanceNot.get(0), "Нет");
                p.compareArrayListAndArrayList(SelectedSpecialtys, SavedSpecialtys, 3);
                break;
            default:
                throw new AssertionError("Пропущен блок 'Проверка сохраненных данных в карточке'");
        }


    }

    public void editWorkerServices(String Login, String Password, String Email, String Phone, String Status,
                                   String SecondName, String FirstName, String MiddleName, String Superuser,
                                   String Depart, String Regalia, String EmailCont, String PhoneCont, String Instagram,
                                   String Vk, String Whatsapp, String Viber, String Facebook, String Other,
                                   String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty,
                                   String UserID) {
        p.moveToProfileUserIDChange(UserID);
        p.click(p.specialistServices);
        p.click(p.buttonChangeServices);
        p.editServices(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                Depart, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other, CheckedAndFocus, Checkeds, Indeterminate,
                Empty);
    }
}



