package edituser;

import editorganization.EditOrganizationPage;
import global.Ashot;
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
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import utils.ConfigProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class EditUserLogic {

    int u = 0;

    private static WebDriver driver;
    private static EditUserPage page;
    private ArrayList<String> SelectedSpecialtys = new ArrayList<>();
    private ArrayList<String> SavedSpecialtys = new ArrayList<>();

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
//            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments(ConfigProperties.getTestProperty("head"));
//            options.addArguments("window-size=1200,800");
//            driver = new ChromeDriver(options);
//            page = new EditUserPage(driver);
//            driver.get(ConfigProperties.getTestProperty("baseurl"));
//            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            page.SelectedServices.clear();
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
        page = new EditUserPage(driver);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        driver.manage().logs().get(LogType.BROWSER);
        page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }

    public void editWorkerFio(String SecondName, String NewSecondName, String NewFirstName, String NewMiddleName) {
        page.moveToProfileUserChange(SecondName);
        String Secondname = page.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = page.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = page.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        page.waitE_ClickableAndClick(page.linkEditfioProfile);
        page.waitE_ClickableAndSendKeys(page.inputSecondName, NewSecondName);
        page.waitE_ClickableAndSendKeys(page.inputFirstName, NewFirstName);
        page.waitE_ClickableAndSendKeys(page.inputMiddleName, NewMiddleName);
        page.waitE_ClickableAndClick(page.buttonSaveProfile);
        page.waitE_visibilityOf(page.firstNameProfile);
        page.sleep(500);

        System.out.println(page.secondNameProfile.getText() + " = " + NewSecondName);
        Assert.assertEquals(page.secondNameProfile.getText(), NewSecondName);

        System.out.println(page.firstNameProfile.getText() + " = " + NewFirstName);
        Assert.assertEquals(page.firstNameProfile.getText(), NewFirstName);

        System.out.println(page.middleNameProfile.getText() + " = " + NewMiddleName);
        Assert.assertEquals(page.middleNameProfile.getText(), NewMiddleName);
    }

    public void editWorkerFioCancel(String SecondName, String NewSecondName, String NewFirstName,
                                    String NewMiddleName) {
        page.moveToProfileUserChange(SecondName);
        String Secondname = page.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = page.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = page.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        page.waitE_ClickableAndClick(page.linkEditfioProfile);
        page.waitE_ClickableAndSendKeys(page.inputSecondName, NewSecondName);
        page.waitE_ClickableAndSendKeys(page.inputFirstName, NewFirstName);
        page.waitE_ClickableAndSendKeys(page.inputMiddleName, NewMiddleName);
        page.waitE_ClickableAndClick(page.buttonCancelProfile);
        page.waitE_visibilityOf(page.firstNameProfile);
        System.out.println(page.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(page.secondNameProfile.getText(), Secondname);

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(page.firstNameProfile.getText(), Firstname);

        System.out.println(page.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertEquals(page.middleNameProfile.getText(), Middlename);
    }

    public void editWorkerFioDeleteMiddlename(String SecondName) {
        page.moveToProfileUserChange(SecondName);
        String Secondname = page.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = page.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = page.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        page.waitE_ClickableAndClick(page.linkEditfioProfile);
        page.waitE_ClickableAndClick(page.inputMiddleName);
//        page.inputMiddleName.clear();
        page.inputMiddleName.sendKeys(Keys.HOME, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE, Keys.DELETE,
                Keys.DELETE, Keys.DELETE, Keys.DELETE);
//        page.waitE_ClickableAndSendKeys(page.inputMiddleName, "");
        page.sleep(3000);
        page.waitE_ClickableAndClick(page.buttonSaveProfileFIO);
        page.waitE_visibilityOf(page.firstNameProfile);
        page.sleep(5000);
        System.out.println(page.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(page.secondNameProfile.getText(), Secondname);

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(page.firstNameProfile.getText(), Firstname);

        try {
            Assert.assertTrue("Отчество найдено", !page.middleNameProfile.isEnabled());
        } catch (NoSuchElementException e) {
            System.out.println("Отчество не найдено и это хорошо");
        }
    }

    public void editWorkerFioDeleteMiddlenameCancel(String SecondName) {
        page.moveToProfileUserChange(SecondName);
        String Secondname = page.secondNameProfile.getText();
        System.out.println("Существующая фамилия: " + Secondname);

        String Firstname = page.firstNameProfile.getText();
        System.out.println("Существующее имя: " + Firstname);

        String Middlename = page.middleNameProfile.getText();
        System.out.println("Существующее отчество: " + Middlename);

        page.waitE_ClickableAndClick(page.linkEditfioProfile);
        page.waitE_ClickableAndClick(page.inputMiddleName);
        page.inputMiddleName.clear();
        page.waitE_ClickableAndClick(page.buttonCancelProfile);
        page.waitE_visibilityOf(page.firstNameProfile);

        System.out.println(page.secondNameProfile.getText() + " = " + Secondname);
        Assert.assertEquals(page.secondNameProfile.getText(), Secondname);

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertEquals(page.firstNameProfile.getText(), Firstname);

        System.out.println(page.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertEquals(page.middleNameProfile.getText(), Middlename);
    }

    public void editWorkerLoginEmailPhone(String SecondName, String NewLogin, String NewEmail, String NewPhone) {
        page.moveToProfileUserChange(SecondName);
        String Login = page.loginProfile.getText();
        System.out.println("Существующий логин: " + Login);

        String Email = page.emailProfile.getText();
        System.out.println("Существующий Email: " + Email);

        String Phone = page.phoneProfile.getText();
        System.out.println("Существующий номер телефона: " + Phone);

        page.waitE_ClickableAndClick(page.linkEditAuthProfile);
        page.waitE_ClickableAndSendKeys(page.inputLogin, NewLogin);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputPhone, NewPhone);
        page.waitE_ClickableAndClick(page.buttonSaveProfile);
        page.waitE_visibilityOf(page.loginProfile);
        page.sleep(2000);

        System.out.println(page.loginProfile.getText() + " = " + NewLogin);
        assertEquals(page.loginProfile.getText(), NewLogin);

        System.out.println(page.emailProfile.getText() + " = " + NewEmail);
        assertEquals(page.emailProfile.getText(), NewEmail);

        System.out.println(page.phoneProfile.getText() + " = " + "+7 (" + NewPhone.charAt(0) + NewPhone.charAt(1) + NewPhone.charAt(2) + ") " + NewPhone.charAt(3) + NewPhone.charAt(4) + NewPhone.charAt(5) + "-" + NewPhone.charAt(6) + NewPhone.charAt(7) + "-" + NewPhone.charAt(8) + NewPhone.charAt(9));
        assertEquals(page.phoneProfile.getText(),
                "+7 (" + NewPhone.charAt(0) + NewPhone.charAt(1) + NewPhone.charAt(2) + ") " + NewPhone.charAt(3) + NewPhone.charAt(4) + NewPhone.charAt(5) + "-" + NewPhone.charAt(6) + NewPhone.charAt(7) + "-" + NewPhone.charAt(8) + NewPhone.charAt(9));

    }

    public void editWorkerLoginEmailPhoneCancel(String SecondName, String NewLogin, String NewEmail, String NewPhone) {
        page.moveToProfileUserChange(SecondName);
        String Login = page.loginProfile.getText();
        System.out.println("Существующий логин: " + Login);

        String Email = page.emailProfile.getText();
        System.out.println("Существующий Email: " + Email);

        String Phone = page.phoneProfile.getText();
        System.out.println("Существующий номер телефона: " + Phone);

        page.waitE_ClickableAndClick(page.linkEditAuthProfile);
        page.waitE_ClickableAndSendKeys(page.inputLogin, NewLogin);
        page.waitE_ClickableAndSendKeys(page.inputEmail, NewEmail);
        page.waitE_ClickableAndSendKeys(page.inputPhone, NewPhone);
        page.waitE_ClickableAndClick(page.buttonCancelProfile);
        page.waitE_visibilityOf(page.loginProfile);
        page.sleep(2000);

        System.out.println(page.loginProfile.getText() + " = " + Login);
        Assert.assertEquals(page.loginProfile.getText(), Login);

        System.out.println(page.emailProfile.getText() + " = " + Email);
        Assert.assertEquals(page.emailProfile.getText(), Email);

        System.out.println(page.phoneProfile.getText() + " = " + Phone);
        Assert.assertEquals(page.phoneProfile.getText(), Phone);
    }


    public void editWorkerPassword(String SecondName, String Login, String NewPassword) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.linkEditPasswordProfile);
        page.waitE_ClickableAndSendKeys(page.inputNewPassword, NewPassword);
        page.waitE_ClickableAndSendKeys(page.inputRepeatNewPassword, NewPassword);
        page.waitE_ClickableAndClick(page.buttonSavePassword);
        page.waitE_visibilityOf(page.helperProfileChange);
        page.scrollUpPage();
        page.sleep(4000);
//        page.waitE_ClickableAndClick(page.helperProfileChangeClose);
        System.out.println("Поменяли пароль");
        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        page.auth(Login, NewPassword);
        page.waitE_visibilityOf(page.userinfoname);
        System.out.println("Авторизовались с новым паролем");

    }

    public void editWorkerPasswordCancel(String SecondName, String Login, String OldPassword, String NewPassword) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.linkEditPasswordProfile);
        page.waitE_ClickableAndSendKeys(page.inputNewPassword, NewPassword);
        page.waitE_ClickableAndSendKeys(page.inputRepeatNewPassword, NewPassword);
        page.waitE_ClickableAndClick(page.buttonCancelPassword);
        page.waitE_ClickableAndClick(page.listActionProfile);
        page.waitE_ClickableAndClick(page.listActionProfileExit);
        driver.get(ConfigProperties.getTestProperty("baseurl"));
        page.auth(Login, OldPassword);
        page.waitE_visibilityOf(page.userinfoname);
    }

    public void editWorkerPasswordShowPassword(String SecondName, String NewPassword) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.linkEditPasswordProfile);
        page.waitE_ClickableAndSendKeys(page.inputNewPassword, NewPassword);
        page.waitE_ClickableAndSendKeys(page.inputRepeatNewPassword, NewPassword);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 1000);
        page.waitE_ClickableAndClick(page.notShowPassword);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 1000);
        File fileExpected = new File(GlobalPage.expectedDir + GlobalPage.ScreenshotAshot + ".png");
        File fileActual = new File(GlobalPage.actualDir + GlobalPage.ScreenshotAshot + ".png");
        File filediff = new File(GlobalPage.diffDir + GlobalPage.ScreenshotAshot + ".png");
        fileExpected.delete();
        fileActual.delete();
//        filediff.delete();
    }

    public void editWorkerStatus(String SecondName) {
        page.moveToProfileUserChange(SecondName);
        String Status = page.statusProfile.getText();
        System.out.println("Status= " + Status);
        page.waitE_ClickableAndClick(page.linkEditStatusProfile);
        page.waitE_ClickableAndClick(page.listStatus);

        if (Status.equals("Активный")) {
            page.waitE_ClickableAndClick(page.disbleStatus);
        } else {
            page.waitE_ClickableAndClick(page.activeStatus);
        }
        page.waitE_ClickableAndClick(page.buttonSaveStatus);
        page.sleep(1000);
        System.out.println("NewStatus= " + page.statusProfile.getText());

        if (Status.equals("Активный")) {
            assertEquals("Статус не изменился", "Отключен", page.statusProfile.getText());
            System.out.println("Статус изменился");
        } else {
            assertEquals("Статус не изменился", "Активный", page.statusProfile.getText());
            System.out.println("Статус изменился");
        }
    }

    public void editWorkerStatusCancel(String SecondName) {
        page.moveToProfileUserChange(SecondName);
        String Status = page.statusProfile.getText();
        System.out.println("Status= " + Status);
        page.waitE_ClickableAndClick(page.linkEditStatusProfile);
        page.waitE_ClickableAndClick(page.listStatus);

        if (Status.equals("Активный")) {
            page.waitE_ClickableAndClick(page.disbleStatus);
        } else {
            page.waitE_ClickableAndClick(page.activeStatus);
        }
        page.waitE_ClickableAndClick(page.buttonCancelStatus);
        page.sleep(1000);
        System.out.println("NewStatus= " + page.statusProfile.getText());

        assertEquals("Статус изменился", page.statusProfile.getText(), Status);
        System.out.println("Статус Не изменился");

    }

    public void editWorkerPhoto(String SecondName, String Photo) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);
        page.waitE_visibilityOf(page.photoProfile);
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 10000);
        page.waitE_ClickableAndClick(page.linkEditPhotoProfile);
        page.uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        page.waitE_ClickableAndClick(page.buttonSavePhotoProfile);
        page.waitE_visibilityOf(page.photoProfile);
        page.scrollUpPage();
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 10000);
    }

    public void editWorkerPhotoCancel(String SecondName, String Photo) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);
        page.waitE_visibilityOf(page.photoProfile);
        page.scrollUpPage();
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 0);
        page.waitE_ClickableAndClick(page.linkEditPhotoProfile);
        page.uploadPhoto.sendKeys(ConfigProperties.getTestProperty("Files") + Photo);
        page.waitE_ClickableAndClick(page.buttonSavePhotoCancelProfile);
        page.waitE_visibilityOf(page.photoProfile);
        page.scrollUpPage();
        Ashot.screenshot(driver, GlobalPage.ScreenshotAshot, 0);
    }

    public void editWorkerEducation(String SecondName, String NewEducation) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);

        String Education = page.educationProfile.getText();
        System.out.println("Существующее образование: " + Education);

        page.waitE_ClickableAndClick(page.linkEditEducationProfile);
        page.waitE_ClickableAndSendKeys(page.inputEducation, NewEducation);
        page.waitE_ClickableAndClick(page.buttonSaveEducation);
        page.sleep(500);

        System.out.println(page.educationProfile.getText() + " = " + NewEducation);
        assertEquals(page.educationProfile.getText(), NewEducation);
    }

    public void editWorkerEducationCancel(String SecondName, String NewEducation) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);

        String Education = page.educationProfile.getText();
        System.out.println("Существующее образование: " + Education);

        page.waitE_ClickableAndClick(page.linkEditEducationProfile);
        page.waitE_ClickableAndSendKeys(page.inputEducation, NewEducation);
        page.waitE_ClickableAndClick(page.buttonCancelEducation);
        page.sleep(500);

        System.out.println(page.educationProfile.getText() + " = " + Education);
        assertEquals(page.educationProfile.getText(), Education);
    }

    public void editWorkerCommunicationMethodsProfile(String SecondName, String NewEmailContact,
                                                      String NewPhoneContact, String NewInstagram, String NewVk,
                                                      String NewWhatsapp, String NewViber, String NewFacebook,
                                                      String NewOther) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);

        String EmailContact = page.emailContactProfile.getText();
        System.out.println("Существующий Email: " + EmailContact);

        String PhoneContact = page.phoneContactProfile.getText();
        System.out.println("Существующий номер телефона: " + PhoneContact);

        String InstagramContact = page.instagramContactProfile.getText();
        System.out.println("Существующий Instagram: " + InstagramContact);

        String VkContact = page.vkContactProfile.getText();
        System.out.println("Существующий Vk: " + VkContact);

        String WhatsappContact = page.whatsappContactProfile.getText();
        System.out.println("Существующий Whatsapp: " + WhatsappContact);

        String ViberContact = page.viberContactProfile.getText();
        System.out.println("Существующий Viber: " + ViberContact);

        String FacebookContact = page.facebookContactProfile.getText();
        System.out.println("Существующий Facebook: " + FacebookContact);

        String OtherContact = page.otherContactProfile.getText();
        System.out.println("Существующее Другое: " + OtherContact);

        page.waitE_ClickableAndClick(page.linkEditCommunicationMethodsProfile);
        page.waitE_ClickableAndSendKeys(page.inputEmailContactProfile, NewEmailContact);
        page.waitE_ClickableAndSendKeys(page.inputPhoneContactProfile, NewPhoneContact);
        page.waitE_ClickableAndSendKeys(page.inputInstagramContactProfile, NewInstagram);
        page.waitE_ClickableAndSendKeys(page.inputVkContactProfile, NewVk);
        page.waitE_ClickableAndSendKeys(page.inputWhatsappContactProfile, NewWhatsapp);
        page.waitE_ClickableAndSendKeys(page.inputViberContactProfile, NewViber);
        page.waitE_ClickableAndSendKeys(page.inputFacebookContactProfile, NewFacebook);
        page.waitE_ClickableAndSendKeys(page.inputOtherContactProfile, NewOther);
        page.waitE_ClickableAndClick(page.buttonSaveCommunicationMethodsProfile);
        page.waitE_visibilityOf(page.emailContactProfile);
        page.sleep(500);

        System.out.println(page.emailContactProfile.getText() + " = " + NewEmailContact);
        assertEquals(page.emailContactProfile.getText(), NewEmailContact);

        System.out.println(page.phoneContactProfile.getText() + " = " + "+7 (" + NewPhoneContact.charAt(0) + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + ") " + NewPhoneContact.charAt(3) + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + "-" + NewPhoneContact.charAt(6) + NewPhoneContact.charAt(7) + "-" + NewPhoneContact.charAt(8) + NewPhoneContact.charAt(9));
        assertEquals(page.phoneContactProfile.getText(),
                "+7 (" + NewPhoneContact.charAt(0) + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + ") " + NewPhoneContact.charAt(3) + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + "-" + NewPhoneContact.charAt(6) + NewPhoneContact.charAt(7) + "-" + NewPhoneContact.charAt(8) + NewPhoneContact.charAt(9));

//        System.out.println(page.phoneContactProfile.getText() + " = " + NewPhoneContact);
//        Assert.assertTrue(page.phoneContactProfile.getText().equals(NewPhoneContact));

        System.out.println(page.instagramContactProfile.getText() + " = " + NewInstagram);
        assertEquals(page.instagramContactProfile.getText(), NewInstagram);

        System.out.println(page.vkContactProfile.getText() + " = " + NewVk);
        assertEquals(page.vkContactProfile.getText(), NewVk);

        System.out.println(page.whatsappContactProfile.getText() + " = " + NewWhatsapp);
        assertEquals(page.whatsappContactProfile.getText(), NewWhatsapp);

        System.out.println(page.viberContactProfile.getText() + " = " + NewViber);
        assertEquals(page.viberContactProfile.getText(), NewViber);

        System.out.println(page.facebookContactProfile.getText() + " = " + NewFacebook);
        assertEquals(page.facebookContactProfile.getText(), NewFacebook);

        System.out.println(page.otherContactProfile.getText() + " = " + NewOther);
        assertEquals(page.otherContactProfile.getText(), NewOther);
    }

    public void editWorkerCommunicationMethodsProfileCancel(String SecondName, String NewEmailContact,
                                                            String NewPhoneContact, String NewInstagram, String NewVk
            , String NewWhatsapp, String NewViber, String NewFacebook, String NewOther) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistData);

        String EmailContact = page.emailContactProfile.getText();
        System.out.println("Существующий Email: " + EmailContact);

        String PhoneContact = page.phoneContactProfile.getText();
        System.out.println("Существующий номер телефона: " + PhoneContact);

        String InstagramContact = page.instagramContactProfile.getText();
        System.out.println("Существующий Instagram: " + InstagramContact);

        String VkContact = page.vkContactProfile.getText();
        System.out.println("Существующий Vk: " + VkContact);

        String WhatsappContact = page.whatsappContactProfile.getText();
        System.out.println("Существующий Whatsapp: " + WhatsappContact);

        String ViberContact = page.viberContactProfile.getText();
        System.out.println("Существующий Viber: " + ViberContact);

        String FacebookContact = page.facebookContactProfile.getText();
        System.out.println("Существующий Facebook: " + FacebookContact);

        String OtherContact = page.otherContactProfile.getText();
        System.out.println("Существующее Другое: " + OtherContact);

        page.waitE_ClickableAndClick(page.linkEditCommunicationMethodsProfile);
        page.waitE_ClickableAndSendKeys(page.inputEmailContactProfile, NewEmailContact);
        page.waitE_ClickableAndSendKeys(page.inputPhoneContactProfile, NewPhoneContact);
        page.waitE_ClickableAndSendKeys(page.inputInstagramContactProfile, NewInstagram);
        page.waitE_ClickableAndSendKeys(page.inputVkContactProfile, NewVk);
        page.waitE_ClickableAndSendKeys(page.inputWhatsappContactProfile, NewWhatsapp);
        page.waitE_ClickableAndSendKeys(page.inputViberContactProfile, NewViber);
        page.waitE_ClickableAndSendKeys(page.inputFacebookContactProfile, NewFacebook);
        page.waitE_ClickableAndSendKeys(page.inputOtherContactProfile, NewOther);
        page.waitE_ClickableAndClick(page.buttonCancelCommunicationMethodsProfile);
        page.waitE_visibilityOf(page.emailContactProfile);
        page.sleep(500);

        System.out.println(page.emailContactProfile.getText() + " = " + EmailContact);
        assertEquals(page.emailContactProfile.getText(), EmailContact);

        System.out.println(page.phoneContactProfile.getText() + " = " + PhoneContact);
        assertEquals(page.phoneContactProfile.getText(), PhoneContact);

        System.out.println(page.instagramContactProfile.getText() + " = " + InstagramContact);
        assertEquals(page.instagramContactProfile.getText(), InstagramContact);

        System.out.println(page.vkContactProfile.getText() + " = " + VkContact);
        assertEquals(page.vkContactProfile.getText(), VkContact);

        System.out.println(page.whatsappContactProfile.getText() + " = " + WhatsappContact);
        assertEquals(page.whatsappContactProfile.getText(), WhatsappContact);

        System.out.println(page.viberContactProfile.getText() + " = " + ViberContact);
        assertEquals(page.viberContactProfile.getText(), ViberContact);

        System.out.println(page.facebookContactProfile.getText() + " = " + FacebookContact);
        assertEquals(page.facebookContactProfile.getText(), FacebookContact);

        System.out.println(page.otherContactProfile.getText() + " = " + OtherContact);
        assertEquals(page.otherContactProfile.getText(), OtherContact);
    }

    public void editWorkerWorkplaces(String SecondName, String NewPost, String NewDepart, String Post) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistWorkpaleces);
        page.waitE_ClickableAndClick(page.linkAddWorkplaces);
        page.waitE_ClickableAndClick(page.structureOrganizations);
        page.waitE_ClickableAndClick(page.bulletHeadOrganization);

        page.sleep(500);
        page.waitE_ClickableAndClick(page.inputWorkerPosition.get(0));
        page.sleep(1000);
        WebElement SelectedPosition = driver.findElement(By.xpath("//li[@class=\"el-select-dropdown__item\"]/span" +
                "[contains(text(),\"" + NewPost + "\")]"));
        page.waitE_ClickableAndClick(SelectedPosition);
        page.waitE_ClickableAndClick(page.userinfoname);
        page.sleep(1000);
        page.waitE_ClickableAndClick(page.selectWorkerRole);
        page.waitE_ClickableAndClick(page.selectWorkerRoleSpecialist);
        page.waitE_ClickableAndClick(page.userinfoname);
        page.waitE_ClickableAndClick(page.buttonAddWorkplace);
        page.waitE_ClickableAndClick(page.linkChangeSpeciality);
        page.waitE_ClickableAndClick(page.selectWorkerSpecialty);
        page.waitE_ClickableAndClick(page.selectWorkerSpecialtyMed);
        page.waitE_ClickableAndClick(page.userinfoname);
        page.sleep(1000);

        for (WebElement Speciality : page.selectedWorkerSpecialty) {
            SelectedSpecialtys.add(Speciality.getText().replace(",", ""));
        }

        page.waitE_ClickableAndClick(page.linkSaveSpeciality);
        page.waitE_visibilityOf(page.workplace);

        System.out.println(page.workplace.getText() + " = " + NewDepart);
        assertEquals(page.workplace.getText(), NewDepart);

        System.out.println(page.post.getText() + " = " + NewPost);
        assertEquals(page.post.getText(), NewPost);

        System.out.println(page.role.getText() + " = " + "Специалист");
        assertEquals("Специалист", page.role.getText());

        for (WebElement Speciality : page.savedWorkerSpecialty) {
            if (!Speciality.getText().equals(",")) {
                SavedSpecialtys.add(Speciality.getText().replace(",", ""));
            }
        }

        int i = -1;
        for (String Spec : SelectedSpecialtys) {
            i = i + 1;
            for (int x = 0; x < SavedSpecialtys.size(); x++) {
                u = 0;
                System.out.println("Специальность №" + x);
                System.out.println("Сравниваем специальности: " + SelectedSpecialtys.get(x) + " = " +
                        Spec);
                if (SelectedSpecialtys.get(x).equals(Spec)) {
                    u = u + 1;
                    System.out.println("!+Speciality finded+!");
                    break;
                } else {System.out.println("Специальности не совпали");}
            }
            Assert.assertEquals("Специальности различаются", 1, u);
        }

    }

    public void editWorkerServices(String Login, String Password, String Email, String Phone, String Status,
                                   String SecondName, String FirstName, String MiddleName, String Superuser,
                                   String Depart, String Ref, String Post, String Role, String Specialities,
                                   String Label, String ID, String LabelChild, String Cost, String IDchild,
                                   String Regalia, String EmailCont, String PhoneCont, String Instagram, String Vk,
                                   String Whatsapp, String Viber, String Facebook, String Other,
                                   String CheckedAndFocus, String Checkeds, String Indeterminate, String Empty) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistServices);
        page.waitE_ClickableAndClick(page.buttonChangeServices);
        page.editServices(Login, Password, Email, Phone, Status, SecondName, FirstName, MiddleName, Superuser,
                Depart, Ref, Post, Role, Specialities, Label, ID, LabelChild, Cost, IDchild, Regalia, EmailCont,
                PhoneCont, Instagram, Vk, Whatsapp, Viber, Facebook, Other, CheckedAndFocus, Checkeds, Indeterminate,
                Empty);
    }
}



