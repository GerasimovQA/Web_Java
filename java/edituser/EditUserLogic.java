package edituser;

import global.Ashot;
import global.GlobalPage;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ConfigProperties;

import java.io.File;

@RunWith(JUnitParamsRunner.class)
public class EditUserLogic {

    private static WebDriver driver;
    private static EditUserPage page;


    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.setProperty("webdriver.chrome.driver", ConfigProperties.getTestProperty("chromedriver"));
            ChromeOptions options = new ChromeOptions();
            options.addArguments(ConfigProperties.getTestProperty("head"));
            options.addArguments("window-size=1200,800");
            driver = new ChromeDriver(options);
            page = new EditUserPage(driver);
            driver.get(ConfigProperties.getTestProperty("baseurl"));
            page.auth(GlobalPage.LoginAUT, GlobalPage.PasswordAUT);
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }

        @Override
        protected void failed(Throwable e, Description description) {
            page.captureScreenshot(description.getMethodName());
        }
    };

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
        Assert.assertTrue(page.secondNameProfile.getText().equals(NewSecondName));

        System.out.println(page.firstNameProfile.getText() + " = " + NewFirstName);
        Assert.assertTrue(page.firstNameProfile.getText().equals(NewFirstName));

        System.out.println(page.middleNameProfile.getText() + " = " + NewMiddleName);
        Assert.assertTrue(page.middleNameProfile.getText().equals(NewMiddleName));
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
        Assert.assertTrue(page.secondNameProfile.getText().equals(Secondname));

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertTrue(page.firstNameProfile.getText().equals(Firstname));

        System.out.println(page.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertTrue(page.middleNameProfile.getText().equals(Middlename));
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
        Assert.assertTrue(page.secondNameProfile.getText().equals(Secondname));

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertTrue(page.firstNameProfile.getText().equals(Firstname));

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
        Assert.assertTrue(page.secondNameProfile.getText().equals(Secondname));

        System.out.println(page.firstNameProfile.getText() + " = " + Firstname);
        Assert.assertTrue(page.firstNameProfile.getText().equals(Firstname));

        System.out.println(page.middleNameProfile.getText() + " = " + Middlename);
        Assert.assertTrue(page.middleNameProfile.getText().equals(Middlename));
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
        Assert.assertTrue(page.loginProfile.getText().equals(NewLogin));

        System.out.println(page.emailProfile.getText() + " = " + NewEmail);
        Assert.assertTrue(page.emailProfile.getText().equals(NewEmail));

        System.out.println(page.phoneProfile.getText() + " = " + "+7 (" + NewPhone.charAt(0) + NewPhone.charAt(1) + NewPhone.charAt(2) + ") " + NewPhone.charAt(3) + NewPhone.charAt(4) + NewPhone.charAt(5) + "-" + NewPhone.charAt(6) + NewPhone.charAt(7) + "-" + NewPhone.charAt(8) + NewPhone.charAt(9));
        Assert.assertTrue(page.phoneProfile.getText().equals("+7 (" + NewPhone.charAt(0) + NewPhone.charAt(1) + NewPhone.charAt(2) + ") " + NewPhone.charAt(3) + NewPhone.charAt(4) + NewPhone.charAt(5) + "-" + NewPhone.charAt(6) + NewPhone.charAt(7) + "-" + NewPhone.charAt(8) + NewPhone.charAt(9)));

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
        Assert.assertTrue(page.loginProfile.getText().equals(Login));

        System.out.println(page.emailProfile.getText() + " = " + Email);
        Assert.assertTrue(page.emailProfile.getText().equals(Email));

        System.out.println(page.phoneProfile.getText() + " = " + Phone);
        Assert.assertTrue(page.phoneProfile.getText().equals(Phone));
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
            Assert.assertTrue("Статус не изменился", page.statusProfile.getText().equals("Отключен"));
            System.out.println("Статус изменился");
        } else {
            Assert.assertTrue("Статус не изменился", page.statusProfile.getText().equals("Активный"));
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

        Assert.assertTrue("Статус изменился", page.statusProfile.getText().equals(Status));
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
        Assert.assertTrue(page.educationProfile.getText().equals(NewEducation));
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
        Assert.assertTrue(page.educationProfile.getText().equals(Education));
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
        Assert.assertTrue(page.emailContactProfile.getText().equals(NewEmailContact));

        System.out.println(page.phoneContactProfile.getText() + " = " + "+7 (" + NewPhoneContact.charAt(0) + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + ") " + NewPhoneContact.charAt(3) + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + "-" + NewPhoneContact.charAt(6) + NewPhoneContact.charAt(7) + "-" + NewPhoneContact.charAt(8) + NewPhoneContact.charAt(9));
        Assert.assertTrue(page.phoneContactProfile.getText().equals("+7 (" + NewPhoneContact.charAt(0) + NewPhoneContact.charAt(1) + NewPhoneContact.charAt(2) + ") " + NewPhoneContact.charAt(3) + NewPhoneContact.charAt(4) + NewPhoneContact.charAt(5) + "-" + NewPhoneContact.charAt(6) + NewPhoneContact.charAt(7) + "-" + NewPhoneContact.charAt(8) + NewPhoneContact.charAt(9)));

//        System.out.println(page.phoneContactProfile.getText() + " = " + NewPhoneContact);
//        Assert.assertTrue(page.phoneContactProfile.getText().equals(NewPhoneContact));

        System.out.println(page.instagramContactProfile.getText() + " = " + NewInstagram);
        Assert.assertTrue(page.instagramContactProfile.getText().equals(NewInstagram));

        System.out.println(page.vkContactProfile.getText() + " = " + NewVk);
        Assert.assertTrue(page.vkContactProfile.getText().equals(NewVk));

        System.out.println(page.whatsappContactProfile.getText() + " = " + NewWhatsapp);
        Assert.assertTrue(page.whatsappContactProfile.getText().equals(NewWhatsapp));

        System.out.println(page.viberContactProfile.getText() + " = " + NewViber);
        Assert.assertTrue(page.viberContactProfile.getText().equals(NewViber));

        System.out.println(page.facebookContactProfile.getText() + " = " + NewFacebook);
        Assert.assertTrue(page.facebookContactProfile.getText().equals(NewFacebook));

        System.out.println(page.otherContactProfile.getText() + " = " + NewOther);
        Assert.assertTrue(page.otherContactProfile.getText().equals(NewOther));
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
        Assert.assertTrue(page.emailContactProfile.getText().equals(EmailContact));

        System.out.println(page.phoneContactProfile.getText() + " = " + PhoneContact);
        Assert.assertTrue(page.phoneContactProfile.getText().equals(PhoneContact));

        System.out.println(page.instagramContactProfile.getText() + " = " + InstagramContact);
        Assert.assertTrue(page.instagramContactProfile.getText().equals(InstagramContact));

        System.out.println(page.vkContactProfile.getText() + " = " + VkContact);
        Assert.assertTrue(page.vkContactProfile.getText().equals(VkContact));

        System.out.println(page.whatsappContactProfile.getText() + " = " + WhatsappContact);
        Assert.assertTrue(page.whatsappContactProfile.getText().equals(WhatsappContact));

        System.out.println(page.viberContactProfile.getText() + " = " + ViberContact);
        Assert.assertTrue(page.viberContactProfile.getText().equals(ViberContact));

        System.out.println(page.facebookContactProfile.getText() + " = " + FacebookContact);
        Assert.assertTrue(page.facebookContactProfile.getText().equals(FacebookContact));

        System.out.println(page.otherContactProfile.getText() + " = " + OtherContact);
        Assert.assertTrue(page.otherContactProfile.getText().equals(OtherContact));
    }

    public void editWorkerWorkplaces(String SecondName, String NewPost, String NewDepart) {
        page.moveToProfileUserChange(SecondName);
        page.waitE_ClickableAndClick(page.specialistWorkpaleces);
        page.waitE_ClickableAndClick(page.linkEditWorkplaces);
        page.waitE_ClickableAndClick(page.structureOrganizations);
        page.waitE_ClickableAndClick(page.bulletHeadOrganization);
        page.waitE_ClickableAndSendKeys(page.inputPosition, NewPost);
        page.waitE_ClickableAndClick(page.inputRoleEmployee);
        page.waitE_ClickableAndClick(page.roleEmployeeSpecialist);
        page.waitE_ClickableAndClick(page.buttonSaveWorkplace);
        page.waitE_visibilityOf(page.workplace);

        System.out.println(page.workplace.getText() + " = " + NewDepart);
        Assert.assertTrue(page.workplace.getText().equals(NewDepart));

        System.out.println(page.post.getText() + " = " + NewPost);
        Assert.assertTrue(page.post.getText().equals(NewPost));

        System.out.println(page.role.getText() + " = " + "Хирург");
        Assert.assertTrue(page.role.getText().equals("Хирург"));

        page.waitE_ClickableAndClick(page.linkEditSpecialty);
        page.waitE_ClickableAndClick(page.listSpecialty);
        page.waitE_ClickableAndClick(page.specialtySurgeon);
        page.waitE_ClickableAndClick(page.buttonSaveSpecialty);


    }
}



